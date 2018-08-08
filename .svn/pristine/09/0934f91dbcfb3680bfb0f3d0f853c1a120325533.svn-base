/**
 * 
 */
package nc.dhhs.nccss.acts.ecoa.web.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import nc.dhhs.nccss.acts.dao.UserAuthorityDAO;
import nc.dhhs.nccss.acts.ecoa.beans.UserAuthority;
import nc.dhhs.nccss.acts.ecoa.web.service.UserAuthorityService;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * @author Vijay Peddapalli
 *
 */
public class UserAuthorityServiceImpl implements UserAuthorityService {

	protected final Logger logger = Logger.getLogger(UserAuthorityServiceImpl.class);

	@Autowired
	private UserAuthorityDAO userAuthorityDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see nc.dhhs.nccss.acts.ecoa.web.service.UserAuthorityService#
	 * getUserAuthorities(long)
	 */
	@Transactional(readOnly = true)
	public List<UserAuthority> getUserAuthorities(long userId) throws Exception {
		logger.debug(
				"\n********** IN UserAuthorityServiceImpl: getUserAuthorities(userid: " + userId + ") **********\n");

		return ((userAuthorityDAO.retrieveUserAuthorities(userId).isEmpty()) ? null
				: userAuthorityDAO.retrieveUserAuthorities(userId));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nc.dhhs.nccss.acts.ecoa.web.service.UserAuthorityService#
	 * createOrModifyUserAuthority(nc.dhhs.nccss.acts.ecoa.beans.UserAuthority,
	 * java.lang.String, java.lang.String)
	 */
	@Transactional
	public String createOrModifyUserAuthority(UserAuthority userAuthority, String ncid, String dbOpType)
			throws Exception {
		String returnCode = "";

		logger.debug("\n********** IN UserAuthorityServiceImpl: createOrModifyUserAuthority **********\n");

		returnCode = userAuthorityDAO.insertOrUpdateUserAuthority(userAuthority, ncid, dbOpType);

		if (returnCode == null || !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
			throw new RuntimeException("createOrModifyUserAuthority: Insert/update failed, Rollback this transaction!"
					+ "returnCode :" + returnCode);

		return returnCode;

	}

}
