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

import nc.dhhs.nccss.acts.dao.CasePartInsuranceDao;
import nc.dhhs.nccss.acts.dao.rowmap.CasePartInsuranceRowMapper;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartInsurance;
import nc.dhhs.nccss.acts.ecoa.web.config.WebsiteConfiguration;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * @author Phani Konuru
 *
 */
@Repository
public class CasePartInsuranceDaoImpl implements CasePartInsuranceDao
{

	protected final Logger		logger		= Logger.getLogger(CasePartInsuranceDaoImpl.class);

	private JdbcTemplate		jdbcTemplate;

	private SimpleJdbcCall		procCasePartInsurance1, procCasePartInsurance2;

	private static final String	SCREEN_SP	= "APP_RESP";

	@Autowired
	// @Required
	public void setDataSource(DataSource dataSource)
	{

		logger.debug("\n********** IN CaseApplicationDaoImpl: SETDATASOURCE**********\n");

		this.jdbcTemplate = new JdbcTemplate(dataSource);

		this.jdbcTemplate.setSkipUndeclaredResults(true);

		procCasePartInsurance1 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_R_INS_COV");

		procCasePartInsurance2 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_UPDATE_INS_COVERAGE");

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.CasePartInsuranceDao#getCasePartInsurance(java.
	 * lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CasePartInsurance> getCasePartInsurance(String applicationId, String applicantId) throws SQLException
	{

		logger.debug("\n********** IN CasePartInsuranceDaoImpl: getCasePartInsurance **********\n");

		logger.debug("\n********** IN CasePartInsuranceDaoImpl: getCasePartInsurance(applicationID:" + applicationId + ")" + "**********\n");

		procCasePartInsurance1.declareParameters(new SqlParameter("ID_APPLICATION", Types.CHAR), new SqlParameter("ID_APPLICANT", Types.CHAR)).returningResultSet("partInsurance", new CasePartInsuranceRowMapper());

		SqlParameterSource in = new MapSqlParameterSource().addValue("ID_APPLICATION", applicationId).addValue("ID_APPLICANT", applicantId);

		Map<String, Object> results = procCasePartInsurance1.execute(in);

		return (List<CasePartInsurance>) results.get("partInsurance");
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.CasePartInsuranceDao#createCasePartInsurance(nc.
	 * dhhs.nccss.acts.ecoa.beans.CasePartInsurance, java.lang.String)
	 */
	@Override
	public String createCasePartInsurance(CasePartInsurance insuranceBean, String ncid) throws SQLException
	{
		logger.debug("\n********** IN CasePartInsuranceDaoImpl: createCasePartInsurance **********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_INSERT, ncid);

		String returnCode = ",,,,";

		String dataFields = buildDataParam(insuranceBean);

		String applId = "";

		// logger.info("\n********** Users NCID: " +
		// benefitBean.getNcId().trim() + "**********\n");

		try
		{
			procCasePartInsurance2.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR));

			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

			Map<String, Object> results = procCasePartInsurance2.execute(in);

			logger.info("CreateCasePartBenefit  returned code: " + results.get("RETURNCODE"));

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
	 * nc.dhhs.nccss.acts.dao.CasePartInsuranceDao#updateCasePartInsurance(nc.
	 * dhhs.nccss.acts.ecoa.beans.CasePartInsurance, java.lang.String)
	 */
	@Override
	public String updateCasePartInsurance(CasePartInsurance insuranceBean, String ncid) throws SQLException
	{
		logger.debug("\n********** IN CasePartInsuranceDaoImpl: updateCasePartInsurance**********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_UPDATE, ncid);

		String returnCode = ",,,,";

		String dataFields = buildDataParam(insuranceBean);

		try
		{
			procCasePartInsurance2.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR));

			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

			Map<String, Object> results = procCasePartInsurance2.execute(in);

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
	 * nc.dhhs.nccss.acts.dao.CasePartInsuranceDao#deleteCasePartInsurance(nc.
	 * dhhs.nccss.acts.ecoa.beans.CasePartInsurance, java.lang.String)
	 */
	@Override
	public String deleteCasePartInsurance(CasePartInsurance insuranceBean, String ncid) throws SQLException
	{
		logger.debug("\n********** IN CasePartInsuranceDaoImpl: deleteCasePartInsurance**********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_DELETE, ncid);

		String returnCode = ",,,,";

		logger.info("*****delete appId: " + insuranceBean.getApplicationId() + "*****");

		String dataFields = buildDataParam(insuranceBean);

		try
		{
			procCasePartInsurance2.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR));

			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

			Map<String, Object> results = procCasePartInsurance2.execute(in);

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
	 * @param applBean
	 * @return
	 */
	private String buildDataParam(CasePartInsurance insuranceBean)
	{

		logger.debug("\n********** IN CasePartInsuranceDaoImpl: buildDataParam **********\n");

		//string to separate field values passed to the SP
		String fldSeperator = AppConstants.FLD_SEPARATOR;

		StringBuffer dataParam = new StringBuffer(" ");

		dataParam.append(insuranceBean.getApplicationId().trim() + fldSeperator);

		dataParam.append(insuranceBean.getApplicantId().trim() + fldSeperator);

		dataParam.append(insuranceBean.getInIns().trim() + fldSeperator);

		dataParam.append(insuranceBean.getInsuranceType().trim() + fldSeperator);

		dataParam.append(insuranceBean.getInsuranceProvider().trim().replaceAll(fldSeperator, "") + fldSeperator);

		dataParam.append(insuranceBean.getInsHolderL().trim().replaceAll(fldSeperator, "") + fldSeperator);

		dataParam.append(insuranceBean.getInsHolderF().trim().replaceAll(fldSeperator, "") + fldSeperator);

		dataParam.append(insuranceBean.getInsHolderRelshp().trim() + fldSeperator);

		logger.info("********** buildDataParam value is: " + dataParam.toString() + "**********");

		return dataParam.toString().trim();
	}

}
