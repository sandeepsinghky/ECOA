package nc.dhhs.nccss.acts.dao;

import java.sql.SQLException;
import java.util.List;

import nc.dhhs.nccss.acts.ecoa.beans.CasePartInsurance;

/**
 * @author Phani Konuru
 */
public interface CasePartInsuranceDao
{

	/**
	 * @param insuranceBean
	 * @param ncid
	 * @return
	 * @throws SQLException
	 */
	public String createCasePartInsurance(CasePartInsurance insuranceBean, String ncid) throws SQLException;

	/**
	 * @param applicationId
	 * @param applicantId
	 * @return
	 * @throws SQLException
	 */
	public List<CasePartInsurance> getCasePartInsurance(String applicationId, String applicantId) throws SQLException;

	/**
	 * @param insuranceBean
	 * @param ncid
	 * @return
	 * @throws SQLException
	 */

	public String updateCasePartInsurance(CasePartInsurance insuranceBean, String ncid) throws SQLException;

	/**
	 * @param insuranceBean
	 * @param ncid
	 * @return
	 * @throws SQLException
	 */
	public String deleteCasePartInsurance(CasePartInsurance insuranceBean, String ncid) throws SQLException;
}
