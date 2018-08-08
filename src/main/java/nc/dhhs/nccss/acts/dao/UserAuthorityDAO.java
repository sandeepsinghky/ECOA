/**
 * 
 */
package nc.dhhs.nccss.acts.dao;

import java.sql.SQLException;
import java.util.List;

import nc.dhhs.nccss.acts.ecoa.beans.UserAuthority;

/**
 * @author Vijay Peddapalli
 *
 */
public interface UserAuthorityDAO
{
	/**
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<UserAuthority> retrieveUserAuthorities(long userId) throws Exception;

	/**
	 * @param authority
	 * @param ncid
	 * @param dbOpType
	 * @return
	 * @throws SQLException
	 */
	public String insertOrUpdateUserAuthority(UserAuthority authority, String ncid, String dbOpType) throws SQLException;

}
