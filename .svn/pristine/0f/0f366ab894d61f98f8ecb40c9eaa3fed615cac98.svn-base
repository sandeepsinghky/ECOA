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
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import nc.dhhs.nccss.acts.dao.CasePartPaterntyDao;
import nc.dhhs.nccss.acts.dao.rowmap.CasePartPaterntyRowMapper;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartPaternty;
import nc.dhhs.nccss.acts.ecoa.web.config.WebsiteConfiguration;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * @author Phani Konuru
 *
 */
@Repository
public class CasePartPaterntyDaoImpl implements CasePartPaterntyDao
{

	protected final Logger		logger		= Logger.getLogger(CasePartPaterntyDaoImpl.class);

	private JdbcTemplate		jdbcTemplate;

	private SimpleJdbcCall		procCasePartPaternty1, procCasePartPaternty2;

	private static final String	SCREEN_SP	= "APP_RESP";

	@Autowired
	// @Required
	public void setDataSource(DataSource dataSource)
	{

		logger.debug("\n********** IN CasePartPaterntyDaoImpl: SETDATASOURCE**********\n");

		this.jdbcTemplate = new JdbcTemplate(dataSource);

		this.jdbcTemplate.setSkipUndeclaredResults(true);

		procCasePartPaternty1 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_R_PATERNITY");

		procCasePartPaternty2 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_UPDATE_PATERNITY");

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CasePartPaternty> getPartPaternty(String applicationId, String childId) throws SQLException
	{

		logger.debug("\n********** CasePartPaterntyDaoImpl: getPartPaternty **********\n");

		logger.debug("\n********** IN CasePartPaterntyDaoImpl:getPartPaternty(applicationID:" + applicationId + ")" + "**********\n");

		procCasePartPaternty1.declareParameters(new SqlParameter("ID_APPLICATION", Types.CHAR), new SqlParameter("ID_APPLICANT_CHLD", Types.CHAR))

				.returningResultSet("partPaternty", new CasePartPaterntyRowMapper());

		SqlParameterSource in = new MapSqlParameterSource().addValue("ID_APPLICATION", applicationId).addValue("ID_APPLICANT_CHLD", childId);

		Map<String, Object> results = procCasePartPaternty1.execute(in);

		return (List<CasePartPaternty>) results.get("partPaternty");
	}

	@Override
	public String createPartPaternty(CasePartPaternty partPaterntyBean, String ncid) throws SQLException
	{
		logger.debug("\n********** IN CasePartPaterntyDaoImpl: createPartPaternty **********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_INSERT, ncid);

		String returnCode = ",,,,";

		String dataFields = buildDataParam(partPaterntyBean);

		String applId = "";

		try
		{
			procCasePartPaternty2.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR));

			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

			Map<String, Object> results = procCasePartPaternty2.execute(in);

			logger.info("createPartPaternty  returned code: " + results.get("RETURNCODE"));

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
	 * nc.dhhs.nccss.acts.dao.CaseApplicationDao#updateCaseApplication(nc.dhhs.
	 * nccss.acts.ecoa.beans.CaseApplication)
	 */
	@Override
	public String updatePartPaternty(CasePartPaternty partPaterntyBean, String ncid) throws SQLException
	{
		logger.debug("\n********** IN CasePartPaterntyDaoImpl: updatePartPaternty**********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_UPDATE, ncid);

		String returnCode = ",,,,";

		String dataFields = buildDataParam(partPaterntyBean);

		// logger.info("User NCID: " + applBean.getNcId().trim());

		try
		{
			procCasePartPaternty2.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR));

			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

			Map<String, Object> results = procCasePartPaternty2.execute(in);

			logger.info("updatePartPaternty returned:" + results.get("RETURNCODE"));

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
	 * nc.dhhs.nccss.acts.dao.CaseApplicationDao#deleteCaseApplication(nc.dhhs.
	 * nccss.acts.ecoa.beans.CaseApplication)
	 */
	@Override
	public String deletePartPaternty(CasePartPaternty partPaterntyBean, String ncid) throws SQLException
	{
		logger.debug("\n********** IN CasePartPaterntyDaoImpl: deletePartPaternty**********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_DELETE, ncid);

		String returnCode = ",,,,";

		logger.info("*****delete appId: " + partPaterntyBean.getApplicationId() + "*****");

		String dataFields = buildDataParam(partPaterntyBean);

		try
		{
			procCasePartPaternty2.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR));

			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

			Map<String, Object> results = procCasePartPaternty2.execute(in);

			returnCode = results.get("RETURNCODE").toString();

			logger.info("deletePartPaternty returned:" + returnCode);
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
		logger.debug("\n********** IN CasePartPaternityDaoImpl: buildCommonParam(op: " + op + "," + "ncid: " + ncid + ")**********\n");

		String commonParam = op + ",,," + SCREEN_SP + "," + ncid + "," + AppConstants.INTERFACE_CODE + ",,";

		logger.info("*****commonParam value: " + commonParam + "*****");
		return commonParam;
	}

	/**
	 * @param applBean
	 * @return
	 */
	private String buildDataParam(CasePartPaternty partPaterntyBean)
	{

		logger.debug("\n********** IN CasePartPaternityDaoImpl: buildDataParam **********\n");

		//string to separate field values passed to the SP
		String fldSeperator = AppConstants.FLD_SEPARATOR;

		StringBuffer dataParam = new StringBuffer(" ");

		dataParam.append(partPaterntyBean.getApplicationId().trim() + fldSeperator);

		dataParam.append(partPaterntyBean.getApplicantId().trim() + fldSeperator);

		dataParam.append(partPaterntyBean.getApplicantChildId().trim() + fldSeperator);

		if (partPaterntyBean.getPatnTestDt() != null)
		{
			dataParam.append(partPaterntyBean.getPatnTestDtStr().trim() + fldSeperator);
		}
		else
		{
			dataParam.append(AppConstants.DEFAULT_DATE + fldSeperator);
		}

		dataParam.append(partPaterntyBean.getPatnResult().trim().replaceAll(fldSeperator, "") + fldSeperator);

		dataParam.append(partPaterntyBean.getPatnEst().trim() + fldSeperator);

		if (partPaterntyBean.getPatnEstDt() != null)
		{
			dataParam.append(partPaterntyBean.getPatnEstDtStr().trim() + fldSeperator);
		}
		else
		{
			dataParam.append(AppConstants.DEFAULT_DATE + fldSeperator);
		}

		dataParam.append(partPaterntyBean.getPatnEstCnty().trim() + fldSeperator);

		dataParam.append(partPaterntyBean.getPatnEstSt().trim() + fldSeperator);
		
		dataParam.append(partPaterntyBean.getIsPatnTstSel().trim() + fldSeperator);

		logger.info("********** buildDataParam value is: " + dataParam.toString() + "**********");

		return dataParam.toString().trim();
	}

}
