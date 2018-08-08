package nc.dhhs.nccss.acts.dao;

import java.sql.SQLException;
import java.util.List;

import nc.dhhs.nccss.acts.ecoa.beans.CasePartIncome;

/**
 * @author Phani Konuru
 */
public interface CasePartIncomeDao
{

	/**
	 * @param incomeBean
	 * @param ncid
	 * @return
	 * @throws SQLException
	 */
	public String createPartIncome(CasePartIncome incomeBean, String ncid) throws SQLException;

	/**
	 * @param applicationId
	 * @param applicantId
	 * @return
	 * @throws SQLException
	 */
	public List<CasePartIncome> getCasePartIncome(String applicationId, String applicantId) throws SQLException;

	/**
	 * @param incomeBean
	 * @param ncid
	 * @return
	 * @throws SQLException
	 */
	public String updatePartIncome(CasePartIncome incomeBean, String ncid) throws SQLException;

	/**
	 * @param incomeBean
	 * @param ncid
	 * @return
	 * @throws SQLException
	 */
	public String deletePartIncome(CasePartIncome incomeBean, String ncid) throws SQLException;

}
