/**
 * 
 */
package nc.dhhs.nccss.acts.ecoa.web.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import nc.dhhs.nccss.acts.dao.UserRegisterDao;
import nc.dhhs.nccss.acts.ecoa.beans.PreNcIdUser;
import nc.dhhs.nccss.acts.ecoa.web.service.UserRegisterService;

/**
 * @author Mallika Velagapudi
 *
 */
public class UserRegisterServiceImpl implements UserRegisterService {
	protected final Logger logger = Logger.getLogger(UserRegisterServiceImpl.class);

	@Autowired
	private UserRegisterDao registerDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see nc.dhhs.nccss.acts.ecoa.web.service.UserRegisterService#
	 * isRegisteredOldUser(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)

	public PreNcIdUser isRegisteredOldUser(String mpi, String ssn, String dob) throws Exception

	{
		logger.debug("\n********** IN UserRegisterServiceImpl:isRegisteredOldUser **********\n");

		dob = formatDate(dob);

		PreNcIdUser registeredOldUser = null;

		registeredOldUser = registerDao.getRegisteredOldUser(mpi, ssn, dob);

		return registeredOldUser;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nc.dhhs.nccss.acts.ecoa.web.service.UserRegisterService#
	 * getParticipantStatus(java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public String getParticipantStatus(String mpi, String ssn, String dob, StringBuffer idPart) throws Exception {
		logger.debug("\n********** IN UserRegisterServiceImpl:getParticipantStatus **********\n");

		String partStatus = "";

		dob = formatDate(dob);

		partStatus = registerDao.getParticipantStatus(mpi, ssn, dob, idPart);// if
																				// errDesc
																				// is
																				// empty
																				// means
																				// there
																				// exists
																				// valid
																				// participant
																				// .

		return partStatus;

	}


     // check to see any other ncId user is already registered to provided MPI.

	@Override
	@Transactional(readOnly = true)

	public String getMPIUsedUser(String mpi) throws Exception
	{

		logger.debug("\n********** IN UserRegisterServiceImpl:getMPIUsedUser**********\n");

		String ncId = "";

		ncId = registerDao.getMPIUsedUser(mpi); // return null value if it does
												// not find one.

		return ncId;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * nc.dhhs.nccss.acts.ecoa.web.service.UserRegisterService#updateNbAttempt(
	 * int, java.lang.String)
	 */
	@Override
	@Transactional
	public int updateNbAttempt(int attempts, String ncId) throws Exception

	{
		logger.debug("\n********** IN UserRegisterServiceImpl:updateNbAttempt**********\n");

		int update = 0;

		update = registerDao.updateNbAttempt(attempts, ncId);

		return update;

	}

	String formatDate(String inDate) throws Exception

	{
		Date outDate = null;

		SimpleDateFormat ds = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");

		outDate = df.parse(inDate);

		if (outDate != null)

			inDate = ds.format(outDate).trim();

		return inDate;

	}

}
