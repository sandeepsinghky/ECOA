package nc.dhhs.nccss.acts.dao;

import java.sql.SQLException;
import java.util.List;

import nc.dhhs.nccss.acts.ecoa.beans.CasePartChldAffil;

/**
 * @author Phani Konuru
 */
public interface CasePartChldAffilDao
{

	/**
	 * @param chldNcpBean
	 * @param ncid
	 * @return
	 * @throws SQLException
	 */
	public String createPartChld(CasePartChldAffil chldNcpBean, String ncid) throws SQLException;

	/**
	 * @param applicationId
	 * @param applicantId
	 * @return
	 * @throws SQLException
	 */
	public List<CasePartChldAffil> getPartChld(String applicationId, String chldId) throws SQLException;

	/**
	 * @param applicationId
	 * @param applicantId
	 * @return
	 * @throws SQLException
	 */
	public List<CasePartChldAffil> getNCPLinkedChildren(String applId, String applicantId) throws SQLException;

	/**
	 * @param incomeBean
	 * @param ncid
	 * @return
	 * @throws SQLException
	 */
	public String updatePartChld(CasePartChldAffil chldNcpBean, String ncid) throws SQLException;

	/**
	 * @param incomeBean
	 * @param ncid
	 * @return
	 * @throws SQLException
	 */
	public String deletePartChld(CasePartChldAffil chldNcpBean, String ncid) throws SQLException;

}
