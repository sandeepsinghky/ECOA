package nc.dhhs.nccss.acts.dao.impl;

import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import nc.dhhs.nccss.acts.dao.CasePartOtherDao;
import nc.dhhs.nccss.acts.dao.rowmap.CasePartOtherRowMapper;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartOther;
import nc.dhhs.nccss.acts.ecoa.web.config.WebsiteConfiguration;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * @author Mallika Velagapudi
 *
 */

// This dao works on ACTW_PART_OTHER table, which stores names relations like
// mother, father, spouse of either NCP, OR CP(any participant)
// NCP father relationcode goes like 1 , mNCP Mother goes like 2 and NCP spouse
// goes like 3....

@Repository
public class CasePartOtherDaoImpl implements CasePartOtherDao
{

	protected final Logger		logger		= Logger.getLogger(CasePartOtherDaoImpl.class);

	private JdbcTemplate		jdbcTemplate;

	private SimpleJdbcCall		procCaseApplication1, procCaseApplication2, procCaseApplication3;

	private static final String	SCREEN_SP	= "APP_RESP";

	@Autowired
	// @Required
	public void setDataSource(DataSource dataSource)
	{

		logger.debug("\n********** IN CaseApplicationDaoImpl: SETDATASOURCE**********\n");

		this.jdbcTemplate = new JdbcTemplate(dataSource);

		this.jdbcTemplate.setSkipUndeclaredResults(true);

		procCaseApplication1 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_R_PART_OTHER");

		procCaseApplication2 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_R_PART_OTHER_REL");

		procCaseApplication3 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_UPDATE_PART_OTHER");

	}

	@SuppressWarnings("unchecked")
	@Override

	public List<CasePartOther> getCasePartOtherByPartId(String applId, String applicantId) throws SQLException
	{

		logger.debug("\n********** IN CasePartOtherDaoImpl: getCasePartOtherByPartId(appId: " + applId + "," + "applicantId: " + applicantId + ")" + "**********\n");

		procCaseApplication1.declareParameters(new SqlParameter("ID_APPLICATION", Types.CHAR), new SqlParameter("ID_APPLICANT", Types.CHAR)).returningResultSet("partRelations", new CasePartOtherRowMapper());

		SqlParameterSource in = new MapSqlParameterSource().addValue("ID_APPLICATION", applId).addValue("ID_APPLICANT", applicantId);

		Map<String, Object> results = procCaseApplication1.execute(in);

		return (List<CasePartOther>) results.get("partRelations");
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.CasePartOtherDao#getCasePartOtherByRelation(java.
	 * lang.String, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public CasePartOther getCasePartOtherByRelation(String applId, String applicantId, String relationId)
			throws SQLException

	{

		logger.debug("\n********** IN CasePartOtherDaoImpl:getCasePartOtherByRelation(appId: " + applId + "," + "applicantId: " + applicantId + "," + "relationId:" + relationId + ")" + "**********\n");

		procCaseApplication2.declareParameters(new SqlParameter("ID_APPLICATION", Types.CHAR), new SqlParameter("ID_APPLICANT", Types.CHAR), new SqlParameter("CD_OTHER_RELSHP", Types.CHAR), new SqlOutParameter("lastNm", Types.CHAR), new SqlOutParameter("firstNm", Types.CHAR), new SqlOutParameter("middleNm", Types.CHAR));

		SqlParameterSource in = new MapSqlParameterSource().addValue("ID_APPLICATION", applId).addValue("ID_APPLICANT", applicantId).addValue("CD_OTHER_RELSHP", relationId);

		Map<String, Object> results = procCaseApplication2.execute(in);

		CasePartOther relationBean = new CasePartOther();

		if (results.get("lastNm") != null)
		{
			relationBean.setLastNm(results.get("lastNm").toString().trim());

			logger.info("\n********** retrieveFirst Name :  first name" + relationBean.getLastNm() + " **********\n");
		}
		if (results.get("firstNm") != null)
		{
			relationBean.setFirstNm(results.get("firstNm").toString().trim());

			logger.info("\n********** IN retrieve middle name: IN Middle name" + relationBean.getFirstNm() + " **********\n");
		}
		if (results.get("middleNm") != null)
		{
			relationBean.setMiddleNm(results.get("middleNm").toString().trim());

			logger.info("\n********** IN retrieve Middle Name: signature Last name" + relationBean.getMiddleNm() + " **********\n");
		}

		return relationBean;

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.CasePartOtherDao#createCasePartOTher(nc.dhhs.nccss
	 * .acts.ecoa.beans.CasePartOther, java.lang.String)
	 */
	@Override
	public String createCasePartOTher(CasePartOther relationBean, String ncid) throws SQLException
	{
		logger.debug("\n********** IN CasePartOtherDaoImpl:  createCasePartOTher(CasePartOther " + "," + "ncid: " + ncid + ")" + "**********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_INSERT, ncid);

		String returnCode = ",,,,";

		String dataFields = buildDataParam(relationBean);

		String applId = "";

		try
		{
			procCaseApplication3.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR));

			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

			Map<String, Object> results = procCaseApplication3.execute(in);

			logger.info(" createCasePartOTher  returned code: " + results.get("RETURNCODE"));

			dataFields = results.get("DATAFIELDS").toString();
			returnCode = results.get("RETURNCODE").toString();
		}

		catch (Exception ex)
		{
			logger.error(ex.getMessage());
		}

		return returnCode;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.CasePartOtherDao#updateCasePartOTher(nc.dhhs.nccss
	 * .acts.ecoa.beans.CasePartOther, java.lang.String)
	 */
	@Override
	public String updateCasePartOTher(CasePartOther relationBean, String ncid) throws SQLException
	{
		logger.debug("\n********** IN CaseApplicationDaoImpl: updateCaseApplication**********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_UPDATE, ncid);

		String returnCode = ",,,,";

		String dataFields = buildDataParam(relationBean);

		// logger.info("User NCID: " + applBean.getNcId().trim());

		try
		{
			procCaseApplication3.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR));

			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

			Map<String, Object> results = procCaseApplication3.execute(in);

			logger.info("Update Case Application returned:" + results.get("RETURNCODE"));

			returnCode = results.get("RETURNCODE").toString();

			System.out.println("the return code is" + returnCode);
		}

		catch (Exception ex)
		{
			logger.error(ex.getMessage());
		}

		return returnCode;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.CasePartOtherDao#deleteCasePartOTher(nc.dhhs.nccss
	 * .acts.ecoa.beans.CasePartOther, java.lang.String)
	 */
	@Override
	public String deleteCasePartOTher(CasePartOther relationBean, String ncid) throws SQLException
	{
		logger.debug("\n********** IN CaseApplicationDaoImpl: deleteCasePartOTher**********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_DELETE, ncid);

		String returnCode = ",,,,";

		logger.info("*****delete appId: " + relationBean.getApplicationId() + "*****");

		String dataFields = buildDataParam(relationBean);

		try
		{
			procCaseApplication3.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR));

			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

			Map<String, Object> results = procCaseApplication3.execute(in);

			returnCode = results.get("RETURNCODE").toString();

			logger.info("deleteCaseApplication returned:" + returnCode);
		}

		catch (Exception ex)
		{
			logger.error(ex.getMessage());
		}

		return returnCode;
	}

	/**
	 * @param op
	 * @param ncid
	 * @return
	 */
	private String buildCommonParam(String op, String ncid)
	{
		logger.debug("\n********** IN CaseApplicationDaoImpl: buildCommonParam(op: " + op + "," + "ncid: " + ncid + ")**********\n");

		String commonParam = op + ",,," + SCREEN_SP + "," + ncid + "," + AppConstants.INTERFACE_CODE + ",,";

		logger.info("*****commonParam value: " + commonParam + "*****");
		return commonParam;
	}

	/**
	 * @param relationBean
	 * @return
	 */
	private String buildDataParam(CasePartOther relationBean)
	{

		logger.debug("\n********** IN CasePartApplicationDaoImpl: buildDataParam **********\n");

		StringBuffer dataParam = new StringBuffer(" ");

		//string to separate field values passed to the SP
		String fldSeparator = AppConstants.FLD_SEPARATOR;

		dataParam.append(relationBean.getApplicationId().trim() + fldSeparator);

		dataParam.append(relationBean.getApplicantId().trim() + fldSeparator);

		dataParam.append(relationBean.getRelationship().trim() + fldSeparator);

		dataParam.append(relationBean.getLastNm().trim().replaceAll(fldSeparator, "") + fldSeparator);

		dataParam.append(relationBean.getFirstNm().trim().replaceAll(fldSeparator, "") + fldSeparator);
		dataParam.append(relationBean.getMiddleNm().trim().replaceAll(fldSeparator, "") + fldSeparator);

		logger.info("********** buildDataParam value is: " + dataParam.toString() + "**********");

		return dataParam.toString().trim();
	}

}