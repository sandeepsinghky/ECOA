package nc.dhhs.nccss.acts.ecoa.web.service;

import java.sql.SQLException;
import java.util.List;

import nc.dhhs.nccss.acts.ecoa.beans.CaseApplication;

/*
 * @author Phani Konuru
 */
public interface CaseApplicationService
{
	/**
	 * @param applBean
	 * @throws SQLException
	 */
	public String CreateCaseApplication(CaseApplication applBean) throws SQLException;

	/**
	 * @param field
	 * @param value
	 * @return
	 * @throws SQLException
	 */
	public List<CaseApplication> getCaseApplicationByFieldName(String field, String value) throws SQLException;

	/**
	 * @param appId
	 * @return
	 * @throws SQLException
	 */
	public List<CaseApplication> getCaseApplicationByAppId(String appId) throws SQLException;

	/**
	 * @param appId
	 * @param ncId
	 * @return
	 * @throws SQLException
	 */
	public String deleteCaseApplication(String appId, String ncId) throws SQLException;

	/**
	 * @param applBean
	 * @return
	 * @throws SQLException
	 */
	public String updateCaseApplicationRNR(CaseApplication applBean) throws SQLException;

	/**
	 * @param applBean
	 * @return
	 * @throws SQLException
	 */
	public String updateCaseApplicationNotes(CaseApplication applBean) throws SQLException;

	/**
	 * @param applBean
	 * @return
	 * @throws SQLException
	 */
	public String updateCaseApplicationSubmit(CaseApplication applBean) throws SQLException;

}
