package nc.dhhs.nccss.acts.dao;

import java.sql.SQLException;
import java.util.List;

import nc.dhhs.nccss.acts.ecoa.beans.CasePartEmp;

/**
 * @author Phani Konuru
 */
public interface CasePartEmpDao
{
	/**
	 * @param appliBean
	 * @return
	 * @throws SQLException
	 */
	public String createPartEmp(CasePartEmp partEmpBean, String ncid) throws SQLException;

	/**
	 * @param applId
	 * @return
	 * @throws SQLException
	 */
	public List<CasePartEmp> getPartEmp(String applicationId, String applicantId) throws SQLException;

	/**
	 * @param applBean
	 * @return
	 * @throws SQLException
	 */
	public String updatePartEmp(CasePartEmp partEmpBean, String ncid) throws SQLException;

	/**
	 * @param applId
	 * @param ncId
	 * @return
	 * @throws SQLException
	 */
	public String deletePartEmp(CasePartEmp partEmpBean, String ncid) throws SQLException;
}
