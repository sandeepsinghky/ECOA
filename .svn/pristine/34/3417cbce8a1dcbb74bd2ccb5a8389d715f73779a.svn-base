package nc.dhhs.nccss.acts.ecoa.web.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import gov.nc.ncidng.ncidngwebservice.schemas.SearchUserByLoginIDResponse;
import nc.dhhs.nccss.acts.dao.UserDAO;
import nc.dhhs.nccss.acts.ecoa.beans.EcoaUser;
import nc.dhhs.nccss.acts.ecoa.beans.PreNcIdUser;
import nc.dhhs.nccss.acts.ecoa.beans.UserProfile;
import nc.dhhs.nccss.acts.ecoa.web.service.AuthenticationWebService;
import nc.dhhs.nccss.acts.ecoa.web.service.UserService;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * @author Vijay Peddapalli
 *
 */
public class UserServiceImpl implements UserService {

	protected final Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private AuthenticationWebService authenticationService;

	@Autowired
	private UserDAO userDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * nc.dhhs.nccss.acts.ecoa.web.service.UserService#userExists(java.lang.
	 * String)
	 */
	@Transactional(readOnly = true)
	public boolean userExists(String userId) throws Exception {
		logger.debug("\n********** IN UserServiceImpl: userExists(userId: " + userId + ") **********\n");

		// call the dao layer to see if the user exists,

		EcoaUser user = userDAO.retrieveUserInfo(userId).get(0);

		if (user != null && user.getUserId() != 0) {
			return true;
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * nc.dhhs.nccss.acts.ecoa.web.service.UserService#createOrUpdateUserInfo(nc
	 * .dhhs.nccss.acts.ecoa.beans.EcoaUser, java.lang.String, java.lang.String)
	 */
	@Transactional
	public String createOrUpdateUserInfo(EcoaUser user, String dbOperationType, String userEvent) throws Exception {
		logger.debug("\n********** IN UserServiceImpl: createOrUpdateUserInfo(EcoaUser) **********\n");

		String returnCode = "";

		returnCode = userDAO.insertOrUpdateUserInfo(user, dbOperationType, userEvent);

		if (returnCode == null || !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
			throw new RuntimeException("createOrUpdateUserInfo: Insert/update failed, Rollback this transaction! "
					+ "returnCode:" + returnCode);

		return returnCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * nc.dhhs.nccss.acts.ecoa.web.service.UserService#updateUserProfile(nc.dhhs
	 * .nccss.acts.ecoa.beans.UserProfile, java.lang.String)
	 */
	@Override
	@Transactional
	public String updateUserProfile(UserProfile userProfile, String loginId) throws Exception

	{
		logger.debug("\n********** IN UserServiceImpl:  getEcoaUserProfile(loginId: " + loginId + ") **********\n");

		String returnCode = userDAO.updateUserProfile(userProfile, loginId);

		return returnCode;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * nc.dhhs.nccss.acts.ecoa.web.service.UserService#getEcoaUserProfile(java.
	 * lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public UserProfile getEcoaUserProfile(String loginId) throws Exception {

		logger.debug("\n********** IN UserServiceImpl:  getEcoaUserProfile(loginId: " + loginId + ") **********\n");

		UserProfile userProfile = userDAO.getEcoaUserProfile(loginId);

		return userProfile;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * nc.dhhs.nccss.acts.ecoa.web.service.UserService#getNCIdResponseUser(java.
	 * lang.String)
	 */
	public EcoaUser getNCIdResponseUser(String ncId) throws Exception {
		return buildUserFromNCIDResponse(ncId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nc.dhhs.nccss.acts.ecoa.web.service.UserService#getDBUser(java.lang.
	 * String)
	 */
	public EcoaUser getDBUser(String loginId) throws Exception {
		logger.debug("\n********** IN UserServiceImpl: getDBUser(loginId: " + loginId + ") **********\n");

		return ((userDAO.retrieveUserInfo(loginId).isEmpty()) ? null : userDAO.retrieveUserInfo(loginId).get(0));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * nc.dhhs.nccss.acts.ecoa.web.service.UserService#preNCIDUserAuthentication
	 * (java.lang.String, java.lang.String)
	 */
	@Transactional
	public PreNcIdUser preNCIDUserAuthentication(String userName, String password) throws Exception {
		logger.debug("\n********** IN UserServiceImpl: preNCIDUserAuthentication(userName: " + userName + ","
				+ "Password ) **********\n");

		return ((userDAO.authenticatePreNcIdUser(userName, password)).isEmpty() ? null
				: userDAO.authenticatePreNcIdUser(userName, password).get(0));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nc.dhhs.nccss.acts.ecoa.web.service.UserService#
	 * updateDeActivateOrGraceLogin(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional

	public void updateDeActivateOrGraceLogin(String oldUserId, String updateField) throws Exception

	{
		logger.debug("\n********** IN UserServiceImpl: updateDeActivateOrGraceLogin **********\n");

		userDAO.updateDeActivateOrGraceLogin(oldUserId, updateField); // it
																		// increases
																		// current
																		// graceLogins
																		// of
																		// user
																		// by 1.
	}

	/**
	 * @param loginId
	 * @return
	 * @throws Exception
	 */
	private EcoaUser buildUserFromNCIDResponse(String loginId) throws Exception {

		logger.debug(
				"\n********** IN UserServiceImpl: buildUserFromNCIDResponse(loginId: " + loginId + ") **********\n");

		SearchUserByLoginIDResponse slogInIDResponse = null;

		slogInIDResponse = authenticationService.searchUserByLoginID(loginId);

		if (slogInIDResponse == null) {
			return null;
		}

		EcoaUser user = new EcoaUser();

		if (slogInIDResponse.getSearchUserByLoginIDResult().getEntryArray().getEntry().get(0).getGUID() != null) {
			user.setNcIdGuid(
					slogInIDResponse.getSearchUserByLoginIDResult().getEntryArray().getEntry().get(0).getGUID().trim());
		}

		if (slogInIDResponse.getSearchUserByLoginIDResult().getEntryArray().getEntry().get(0).getFirstName() != null) {
			user.setNcidFirstNM(slogInIDResponse.getSearchUserByLoginIDResult().getEntryArray().getEntry().get(0)
					.getFirstName().trim());
		}
		if (slogInIDResponse.getSearchUserByLoginIDResult().getEntryArray().getEntry().get(0)
				.getMiddleInitial() != null) {
			user.setNcidMiddleNM(slogInIDResponse.getSearchUserByLoginIDResult().getEntryArray().getEntry().get(0)
					.getMiddleInitial().trim());
		}

		if (slogInIDResponse.getSearchUserByLoginIDResult().getEntryArray().getEntry().get(0).getLastName() != null) {
			user.setNcidLastNM(slogInIDResponse.getSearchUserByLoginIDResult().getEntryArray().getEntry().get(0)
					.getLastName().trim());
		}

		if (slogInIDResponse.getSearchUserByLoginIDResult().getEntryArray().getEntry().get(0).getEMail() != null) {
			user.setNcIdEmail(slogInIDResponse.getSearchUserByLoginIDResult().getEntryArray().getEntry().get(0)
					.getEMail().trim());
		}

		if (slogInIDResponse.getSearchUserByLoginIDResult().getEntryArray().getEntry().get(0).getUserID() != null) {
			user.setLoginId(slogInIDResponse.getSearchUserByLoginIDResult().getEntryArray().getEntry().get(0)
					.getUserID().trim());
		}

		if (slogInIDResponse.getSearchUserByLoginIDResult().getEntryArray().getEntry().get(0).getUserType() != null) {
			user.setUserType(slogInIDResponse.getSearchUserByLoginIDResult().getEntryArray().getEntry().get(0)
					.getUserType().trim());
		}

		return user;

	}

}
