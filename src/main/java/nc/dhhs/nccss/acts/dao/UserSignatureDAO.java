/**
 * 
 */
package nc.dhhs.nccss.acts.dao;

import java.sql.SQLException;
import java.util.List;

import nc.dhhs.nccss.acts.ecoa.beans.UserSignature;

/**
 * @author Vijay Peddapalli
 *
 */
public interface UserSignatureDAO
{

	/**
	 * @param signId
	 * @param signType
	 * @return
	 */

	public List<UserSignature> retrieveSignature(String signId, String signType) throws SQLException;

	/**
	 * @param signBean
	 * @param userId
	 * @param dbOperationType
	 * @return
	 */
	public String insertOrUpdateSignature(UserSignature signBean, String userId, String dbOperationType) throws SQLException;

	/**
	 * @param signId
	 * @param signType
	 * @return
	 * @throws SQLException
	 */
	public List<UserSignature> retrieveAllSignatures(String signId) throws SQLException;
}
