package nc.dhhs.nccss.acts.ecoa.web.service;

import java.sql.SQLException;
import nc.dhhs.nccss.acts.ecoa.beans.UserProfile;
import nc.dhhs.nccss.acts.ecoa.beans.EcoaUser;
import nc.dhhs.nccss.acts.ecoa.beans.PreNcIdUser;

/**
 * @author Vijay Peddapalli
 *
 */

public interface UserService {

	/**
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public boolean userExists(String userId) throws Exception;

	/**
	 * @param user
	 * @param dbOpType
	 * @param userEvent
	 * @return
	 * @throws Exception
	 */
	public String createOrUpdateUserInfo(EcoaUser user, String dbOpType, String userEvent) throws Exception;

	/**
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public EcoaUser getNCIdResponseUser(String userId) throws Exception;

	/**
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public EcoaUser getDBUser(String userName) throws Exception;

	/**
	 * @param userName
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public PreNcIdUser preNCIDUserAuthentication(String userName, String password) throws Exception;

	/**
	 * @param oldUserId
	 * @param updateField
	 * @return
	 * @throws SQLException
	 */
	public void updateDeActivateOrGraceLogin(String oldUserId, String updateField) throws Exception;

    /**
	 * @param loginId
	 * @return
	 * @throws SQLException
	 */
	public UserProfile getEcoaUserProfile(String loginId) throws Exception;

	/**
	 * @param userProfile
	 * @param loginId
	 * @return
	 * @throws Exception
	 */
	public String updateUserProfile(UserProfile userProfile, String loginId) throws Exception;


    
}
