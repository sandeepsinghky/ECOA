/**
 * 
 */
package nc.dhhs.nccss.acts.ecoa.web.service;

import java.sql.SQLException;

/**
 * @author Vijay Peddapalli
 *
 */
public interface WorkFlowService
{

	/**
	 * @param flowId
	 * @param flowType
	 * @return
	 * @throws SQLException
	 */
	public long getStepNumber(String flowId, String flowType) throws SQLException;

	/**
	 * @param flowId
	 * @param flowType
	 * @param stepNum
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	public String updateStepNumber(String flowId, String flowType, long stepNum, String userId) throws SQLException;

	/**
	 * @param flowId
	 * @param flowType
	 * @param stepNum
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	public String createNewStepNum(String flowId, String flowType, long stepNum, String userId) throws SQLException;

}
