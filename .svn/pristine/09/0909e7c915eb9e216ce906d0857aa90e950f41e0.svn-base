
package nc.dhhs.nccss.acts.dao;

import java.sql.SQLException;
import java.util.List;
import nc.dhhs.nccss.acts.ecoa.beans.UserProfile;
import nc.dhhs.nccss.acts.ecoa.beans.EcoaUser;
import nc.dhhs.nccss.acts.ecoa.beans.PreNcIdUser;

/**
 * @author Vijay Peddapalli
 *
 */
public interface UserDAO
{

	/**
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	public List<EcoaUser> retrieveUserInfo(String userId) throws SQLException;

	/**
	 * @param userbean
	 * @param dbOperationType
	 * @param userEvent
	 * @return
	 * @throws SQLException
	 */
	public String insertOrUpdateUserInfo(EcoaUser userbean, String dbOperationType, String userEvent) throws SQLException;

	/**
	 * @param userName
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public List<PreNcIdUser> authenticatePreNcIdUser(String userName, String password) throws SQLException;
	
	/**
	 * @param oldUserId
	 * @param opType
	 * @return
	 * @throws SQLException
	 */
	public String updateDeActivateOrGraceLogin(String oldUserId, String updateField) throws SQLException;

    /**
	 * @param loginId
	 * @return
	 * @throws SQLException
	 */
	public UserProfile getEcoaUserProfile(String loginId) throws SQLException;

    /**
	 * @param userProfile
	 * @param loginId
	 * @return
	 * @throws SQLException
	 */
	public String updateUserProfile(UserProfile userProfile, String loginId) throws SQLException;

    

     


}
