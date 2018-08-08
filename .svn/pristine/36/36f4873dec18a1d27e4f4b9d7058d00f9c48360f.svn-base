/**
 * 
 */
package nc.dhhs.nccss.acts.dao;

import java.sql.SQLException;

/**
 * @author Vijay Peddapalli
 *
 */
public interface WorkFlowDAO
{

	/**
	 * @param flowId
	 * @param flowType
	 * @return
	 * @throws SQLException
	 */
	public Long retriveFlowProgressStep(String flowId, String flowType) throws SQLException;

	/**
	 * @param flowId
	 * @param flowType
	 * @param stepNum
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	public String updateFlowProgressStep(String flowId, String flowType, long stepNum, String userId) throws SQLException;

	/**
	 * @param flowId
	 * @param flowType
	 * @param stepNum
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	public String insertFlowProgressStep(String flowId, String flowType, long stepNum, String userId) throws SQLException;

}
