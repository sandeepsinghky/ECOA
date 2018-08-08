/**
 * 
 */
package nc.dhhs.nccss.acts.ecoa.web.service.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import nc.dhhs.nccss.acts.dao.ParentUserInfoDao;
import nc.dhhs.nccss.acts.dao.UserDAO;
import nc.dhhs.nccss.acts.ecoa.beans.EcoaUser;
import nc.dhhs.nccss.acts.ecoa.beans.NameFormat;
import nc.dhhs.nccss.acts.ecoa.beans.PreNcIdUser;
import nc.dhhs.nccss.acts.ecoa.beans.UserInformation;
import nc.dhhs.nccss.acts.ecoa.web.security.EcoaUserDetails;
import nc.dhhs.nccss.acts.ecoa.web.service.ParentUserInfoService;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * @author Mallika Velagapudi
 *
 */
public class ParentUserInfoServiceImpl implements ParentUserInfoService {
	protected final Logger logger = Logger.getLogger(CaseParticipantServiceImpl.class);

	@Autowired
	private ParentUserInfoDao parentUserInfoDao;

	@Autowired
	private UserDAO userDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * nc.dhhs.nccss.acts.ecoa.web.service.ParentUserInfoService#getUserInfo(nc.
	 * dhhs.nccss.acts.ecoa.beans.UserInformation)
	 */
	@Override
	@Transactional
	public void getUserInfo(UserInformation userInfoBean) throws Exception

	{
		logger.debug("\n********** IN ParentUserinfoServiceImpl:getUserInfo **********\n");

		getUserRoleInfo(userInfoBean);

		getParticipantDetail(userInfoBean);

		setReminders(userInfoBean);

		if (userInfoBean.getUserType().equals(AppConstants.USERTYPE_PRENCID)) {
			updateLastLogin(userInfoBean.getMpi());

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * nc.dhhs.nccss.acts.ecoa.web.service.ParentUserInfoService#getUserWebInfo(
	 * nc.dhhs.nccss.acts.ecoa.beans.UserInformation)
	 */
	@Override
	@Transactional(readOnly = true)
	public void getUserWebInfo(UserInformation userInfoBean) throws Exception {

		logger.debug("\n********** IN ParentUserinfoServiceImpl:getUserWebInfo **********\n");

		// collecting web user or login user details from ACTW_USER_DETAILS
		// table if user type is ncId.collecting web user details from
		// FKKT_WEB_USER_AUTH table if user type is preNcId.

		if (userInfoBean.getUserType().equals(AppConstants.USERTYPE_PRENCID)) {
			// in the process of authentication web user details for preNcId
			// user is placed in security Context retrieving from
			// FKKT_WEB_USER_AUTH table.
			EcoaUserDetails userDetails = (EcoaUserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();

			PreNcIdUser preNcIdUser = userDetails.getPreNCIdUser();

			NameFormat nf = new NameFormat(preNcIdUser.getLastName(), preNcIdUser.getFirstName(),
					preNcIdUser.getMiddleName());

			userInfoBean.setWebFirstName(nf.inspectSuffixes(preNcIdUser.getFirstName()));

			userInfoBean.setWebLastName(nf.inspectSuffixes(preNcIdUser.getLastName()));

			userInfoBean.setMiddleName(nf.inspectSuffixes(preNcIdUser.getMiddleName()));

			userInfoBean.setMpi(preNcIdUser.getId_part()); // idPart is same as
															// MPI number.

			userInfoBean.setEmailId(preNcIdUser.getEmailId());

			// Get the date last login and reformat it

			String lastLoginDate = preNcIdUser.getDt_lst_loginStr();

			if (lastLoginDate.equals(AppConstants.DEFAULT_DATE)) {

				lastLoginDate = "You have successfully signed-in for the first time";
			} else {

				lastLoginDate = "Your last successful log-in was " + formatDate(lastLoginDate);
			}
			userInfoBean.setLastLoginDate(lastLoginDate);

		} else if (userInfoBean.getUserType().equals(AppConstants.USERTYPE_NCID)) {
			EcoaUser ncIdUser = userDAO.retrieveUserInfo(userInfoBean.getUserID()).get(0);

			if (ncIdUser != null) {

				NameFormat nf = new NameFormat(ncIdUser.getLastName(), ncIdUser.getFirstName(),
						ncIdUser.getMiddleName());

				userInfoBean.setWebFirstName(nf.inspectSuffixes(ncIdUser.getFirstName()));

				userInfoBean.setWebLastName(nf.inspectSuffixes(ncIdUser.getLastName()));

				userInfoBean.setWebMiddleName(nf.inspectSuffixes(ncIdUser.getMiddleName()));

				userInfoBean.setMpi(ncIdUser.getPartId());

				Date date = new Date(ncIdUser.getLogoutTime().getTime());

				if (userInfoBean.getLastLoginDate().trim().isEmpty()) {

					userInfoBean.setLastLoginDate("Your last successful log-in was " + formatDate(date.toString()));

				}
			}

		}

	}

	/**
	 * @param userInfoBean
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public void getUserRoleInfo(UserInformation userInfoBean) throws Exception {
		logger.debug("\n********** IN ParentUserinfoServiceImpl:getUserRoleInfo **********\n");

		// Check to see if the user has multiple cases in the system with
		// atleast one case with a different role than the other

		SqlRowSet rs = parentUserInfoDao.getDualRole(userInfoBean.getMpi());

		String previousRelshp = "";
		String currentRelshp = "";
		boolean dualRole = false;

		while (rs.next()) {

			// Get the current case relationship from the table
			currentRelshp = rs.getString(7).trim();

			// Check to see if this is not the first pass
			if (!previousRelshp.equals("")) {
				// Check to see if the previous relationship and current
				// relationship

				// are different and if true, set dual role flag to true

				if (!currentRelshp.equals(previousRelshp)) {
					dualRole = true;
					break;
				}
			}
			previousRelshp = currentRelshp;
		}

		userInfoBean.setDualRole(dualRole);

		if (dualRole) {
			userInfoBean.setCaseRelshp("");
		} else {
			userInfoBean.setCaseRelshp(currentRelshp);
		}

		userInfoBean.setLoginActive(true);

	}

	/**
	 * @param userInfoBean
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public void getParticipantDetail(UserInformation userInfoBean) throws Exception {
		logger.debug("\n********** IN ParentUserinfoServiceImpl:getParticipantDetail **********\n");

		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");

		SqlRowSet rs = parentUserInfoDao.getPartDetails(userInfoBean.getMpi());

		if (rs.next()) {

			if (rs.getDate(6) != null && !rs.getString(6).equals("")) {
				userInfoBean.setDob(DATE_FORMAT.format(rs.getDate(6)).trim());

			}

			if (rs.getString(5) != null && !rs.getString(5).equals("")) {
				userInfoBean.setSsn(reformatSSN(rs.getString(5)));
			}
			String fname = rs.getString(3).trim();
			String mname = rs.getString(4).trim();
			String lname = rs.getString(2).trim();

			NameFormat nf = new NameFormat(lname, fname, mname);

			fname = nf.inspectSuffixes(fname);
			mname = nf.inspectSuffixes(mname);

			lname = nf.inspectSuffixes(lname);

			userInfoBean.setFirstName(fname);
			userInfoBean.setMiddleName(mname);
			userInfoBean.setLastName(lname);
		}

	}

	/**
	 * This method updates FKKT_WEB_USER table with the last successful login
	 * time as this date will be used on the welcome page. Creation date:
	 * 
	 * @exception java.io.IOException
	 *                The exception description.
	 */
	@Override
	@Transactional
	public void updateLastLogin(String mpi) throws Exception {

		logger.debug("\n********** IN ParentUserinfoServiceImpl:updateLastLogin**********\n");

		parentUserInfoDao.updateLastLogin(mpi);

	}

	@Transactional(readOnly = true)
	public void setReminders(UserInformation userInfoBean) throws Exception {
		logger.debug("\n********** IN ParentUserinfoServiceImpl:setReminders**********\n");

		String mpi = userInfoBean.getMpi();

		// Check if the participant has any future appointments and
		// set the flag
		int appointments = 0;

		if (userInfoBean.isDualRole()) {
			appointments = parentUserInfoDao.getAllAppointments(mpi);
		} else {
			appointments = parentUserInfoDao.getAppointments(mpi, userInfoBean.getCaseRelshp());
		}

		if (appointments > 0) {
			userInfoBean.setScheduled(true);
		}

		int schedules = 0;

		schedules = parentUserInfoDao.getHearing(mpi);

		if (schedules > 0) {
			userInfoBean.setScheduled(true);
		}

		// Check if the participant has mailing address and
		// set the flag

		int mailCount = parentUserInfoDao.getMailAddress(mpi);

		boolean mailAddressPresent = false;

		if (mailCount > 0) {
			userInfoBean.setMailPresent(true);
			mailAddressPresent = true;
		}

		if (!mailAddressPresent) {
			// Check if the participant has AOC mailing address and
			// set the flag

			SqlRowSet rs = parentUserInfoDao.getAOCMailAddress(mpi);

			if (rs.next()) {
				if (!rs.getString(1).trim().equals("")) {
					userInfoBean.setMailPresent(true);
				}
			}

		}

		// Check if the participant is an AP and if so,
		// check if he/she is employed and set the flag

		if (userInfoBean.isDualRole() || userInfoBean.getCaseRelshp().trim().equals("AP")) {

			SqlRowSet rs = parentUserInfoDao.getEmployment(mpi);

			java.util.Date dt = new java.util.Date();
			java.sql.Date currentDate = new java.sql.Date(dt.getTime());

			userInfoBean.setEmployed(false);

			while (rs.next()) {
				String endDate = rs.getDate(1).toString();
				if (endDate.equals(AppConstants.DEFAULT_DATE)) {
					userInfoBean.setEmployed(true);
					break;
				} else if (rs.getDate(1).compareTo(currentDate) >= 0) {
					userInfoBean.setEmployed(true);
					break;
				}

			}

		}

	}

	/**
	 * This method is called to format the Social Security Number. Creation
	 * 
	 * @return java.lang.String
	 * @param date
	 *            java.lang.String
	 */
	private String reformatSSN(String ssn) {

		String strSSN = null;

		strSSN = ssn.substring(0, 3) + "-" + ssn.substring(3, 5) + "-" + ssn.substring(5);
		return strSSN;
	}

	String formatDate(String inDate)

	{
		try {
			java.util.Date outDate = null;

			SimpleDateFormat ds = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");

			outDate = ds.parse(inDate);

			if (outDate != null) {

				inDate = df.format(outDate).trim();

			}

		}

		catch (Exception e)

		{
			logger.error(e.getMessage());

		}

		return inDate;
	}

}