/**
 * 
 */
package nc.dhhs.nccss.acts.ecoa.web.service;

import java.sql.SQLException;
import java.util.List;

import nc.dhhs.nccss.acts.ecoa.beans.UserSignature;

/**
 * @author Vijay Peddapalli
 *
 */
public interface UserSignatureService
{

	/**
	 * @param signatureId
	 * @param signatureType
	 * @return
	 * @throws Exception
	 */
	public List<UserSignature> getCaseApplicationSignature(String signatureId, String signatureType) throws Exception;

	/**
	 * @param signatureId
	 * @return
	 * @throws Exception
	 */
	public List<UserSignature> getCaseApplicationAllSignatures(String signatureId) throws Exception;

	/**
	 * @param signBean
	 * @param userId
	 * @param dbOpType
	 * @return
	 * @throws SQLException
	 */
	public String insertOrUpdateSignature(UserSignature signBean, String userId, String dbOpType) throws SQLException;
}
