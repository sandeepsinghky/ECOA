package nc.dhhs.nccss.acts.dao;

import java.sql.SQLException;
import nc.dhhs.nccss.acts.ecoa.beans.TaskFormBean;
import nc.dhhs.nccss.acts.ecoa.beans.TaskNotes;
import nc.dhhs.nccss.acts.ecoa.beans.TaskBean;


/**
 * @author pkonuru
 *
 */

public interface FeedBackDao
{

	/**
	 * @return
	 * @throws SQLException
	 */
	public int getCSSCtrlNbr() throws SQLException;
	
	/**
	 * @param taskBean
	 * @throws SQLException
	 */
	public int insertCSENotes(TaskNotes taskNotes) throws SQLException;
	
	/**
	 * @param taskBean
	 * @throws SQLException
	 */
	public int insertCSEForm(TaskFormBean tskFrmBean) throws SQLException;
	
	/**
	 * @param taskBean
	 * @return
	 * @throws SQLException
	 */
	public int insertCSETrk(TaskBean taskBean) throws SQLException;
	
	/**
	 * @param referenceId
	 * @param ncId
	 * @return
	 * @throws SQLException
	 */
	public int updateTaskAssignedWorker(int referenceId, String lastNm, String email) throws SQLException;

}
