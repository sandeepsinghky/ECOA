package nc.dhhs.nccss.acts.dao;

import java.sql.SQLException;
import java.util.List;

import nc.dhhs.nccss.acts.ecoa.beans.CasePartPaternty;

/**
 * @author Phani Konuru
 */
public interface CasePartPaterntyDao
{

	/**
	 * @param partPaterntyBean
	 * @param ncid
	 * @return
	 * @throws SQLException
	 */
	public String createPartPaternty(CasePartPaternty partPaterntyBean, String ncid) throws SQLException;

	/**
	 * @param applicationId
	 * @param applicantId
	 * @return
	 * @throws SQLException
	 */
	public List<CasePartPaternty> getPartPaternty(String applicationId, String childId) throws SQLException;

	/**
	 * @param partPaterntyBean
	 * @param ncid
	 * @return
	 * @throws SQLException
	 */
	public String updatePartPaternty(CasePartPaternty partPaterntyBean, String ncid) throws SQLException;

	/**
	 * @param partPaterntyBean
	 * @param ncid
	 * @return
	 * @throws SQLException
	 */
	public String deletePartPaternty(CasePartPaternty partPaterntyBean, String ncid) throws SQLException;
}
