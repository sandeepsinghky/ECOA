package nc.dhhs.nccss.acts.dao;

import java.sql.SQLException;
import java.util.List;

import nc.dhhs.nccss.acts.ecoa.beans.CaseApplication;

/**
 * @author Phani Konuru
 */
public interface CaseApplicationDao
{
	/**
	 * @param appliBean
	 * @return
	 * @throws SQLException
	 */
	public String createCaseApplication(CaseApplication applBean) throws SQLException;

	/**
	 * @param field
	 * @param value
	 * @return
	 * @throws SQLException
	 */
	public List<CaseApplication> getCaseApplicationByField(String field, String value) throws SQLException;

	/**
	 * @param applId
	 * @return
	 * @throws SQLException
	 */
	public List<CaseApplication> getCaseApplicationByAppId(String applId) throws SQLException;

	/**
	 * @param applBean
	 * @return
	 * @throws SQLException
	 */
	public String updateCaseApplication(CaseApplication applBean) throws SQLException;

	/**
	 * @param applId
	 * @param ncId
	 * @return
	 * @throws SQLException
	 */
	public String deleteCaseApplication(CaseApplication applBean) throws SQLException;
}
