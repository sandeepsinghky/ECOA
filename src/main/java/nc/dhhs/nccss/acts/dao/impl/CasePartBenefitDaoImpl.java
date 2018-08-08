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

import nc.dhhs.nccss.acts.dao.CasePartBenefitDao;
import nc.dhhs.nccss.acts.dao.rowmap.CasePartBenefitRowMapper;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartBenefit;
import nc.dhhs.nccss.acts.ecoa.web.config.WebsiteConfiguration;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * @author Mallika Velagapudi
 *
 */
@Repository
public class CasePartBenefitDaoImpl implements CasePartBenefitDao
{

	protected final Logger		logger		= Logger.getLogger(CasePartBenefitDaoImpl.class);

	private JdbcTemplate		jdbcTemplate;

	private SimpleJdbcCall		procCaseApplication1, procCaseApplication2, procCaseApplication3, procCaseApplication4;

	private static final String	SCREEN_SP	= "APP_RESP";

	@Autowired
	// @Required
	public void setDataSource(DataSource dataSource)
	{

		logger.debug("\n********** IN CaseApplicationDaoImpl: SETDATASOURCE**********\n");

		this.jdbcTemplate = new JdbcTemplate(dataSource);

		this.jdbcTemplate.setSkipUndeclaredResults(true);

		procCaseApplication1 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_R_PART_BENEFIT");

		procCaseApplication2 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_UPDATE_PART_BENEFIT ");

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CasePartBenefit> getCasePartBenefitsByFieldId(String applId, String applicantId, String partType)
			throws SQLException
	{

		logger.debug("\n********** IN CaseApplicationDaoImpl: getCaseApplicationByField **********\n");

		logger.debug("\n********** IN CaseApplicationDaoImpl: getCaseApplicationByAppId(applID:" + applId + ")" + "**********\n");

		procCaseApplication1.declareParameters(new SqlParameter("ID_APPLICATION", Types.CHAR), new SqlParameter("ID_APPLICANT", Types.CHAR), new SqlParameter("CD_PART_TYPE", Types.CHAR)).returningResultSet("caseBenefits", new CasePartBenefitRowMapper());

		SqlParameterSource in = new MapSqlParameterSource().addValue("ID_APPLICATION", applId).addValue("ID_APPLICANT", applicantId).addValue("CD_PART_TYPE", partType);

		Map<String, Object> results = procCaseApplication1.execute(in);

		return (List<CasePartBenefit>) results.get("caseBenefits");
	}

	@Override
	public String createCasePartBenefit(CasePartBenefit benefitBean, String ncid) throws SQLException
	{
		logger.debug("\n********** IN CasePartBenefitDaoImpl: createCasePartBenefit **********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_INSERT, ncid);

		String returnCode = ",,,,";

		String dataFields = buildDataParam(benefitBean);

		String applId = "";

		// logger.info("\n********** Users NCID: " +
		// benefitBean.getNcId().trim() + "**********\n");

		try
		{
			procCaseApplication2.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR));

			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

			Map<String, Object> results = procCaseApplication2.execute(in);

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
	 * nc.dhhs.nccss.acts.dao.CaseApplicationDao#updateCaseApplication(nc.dhhs.
	 * nccss.acts.ecoa.beans.CaseApplication)
	 */
	@Override
	public String updateCasePartBenefit(CasePartBenefit benefitBean, String ncid) throws SQLException
	{
		logger.debug("\n********** IN CaseApplicationDaoImpl: updateCaseApplication**********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_UPDATE, ncid);

		String returnCode = ",,,,";

		String dataFields = buildDataParam(benefitBean);

		// logger.info("User NCID: " + applBean.getNcId().trim());

		try
		{
			procCaseApplication2.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR));

			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

			Map<String, Object> results = procCaseApplication2.execute(in);

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
	 * nc.dhhs.nccss.acts.dao.CaseApplicationDao#deleteCaseApplication(nc.dhhs.
	 * nccss.acts.ecoa.beans.CaseApplication)
	 */
	@Override
	public String deleteCasePartBenefit(CasePartBenefit benefitBean, String ncid) throws SQLException
	{
		logger.debug("\n********** IN CaseApplicationDaoImpl: deleteCaseApplication**********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_DELETE, ncid);

		String returnCode = ",,,,";

		logger.info("*****delete appId: " + benefitBean.getApplicationId() + "*****");

		String dataFields = buildDataParam(benefitBean);

		try
		{
			procCaseApplication2.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR));

			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

			Map<String, Object> results = procCaseApplication2.execute(in);

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
	private String buildDataParam(CasePartBenefit benefitBean)
	{

		logger.debug("\n********** IN CasePartApplicationDaoImpl: buildDataParam **********\n");

		StringBuffer dataParam = new StringBuffer(" ");

		//string to separate field values passed to the SP
		String fldSeparator = AppConstants.FLD_SEPARATOR;

		dataParam.append(benefitBean.getApplicationId().trim() + fldSeparator);

		dataParam.append(benefitBean.getApplicantId().trim() + fldSeparator);

		dataParam.append(benefitBean.getPartType().trim() + fldSeparator);

		dataParam.append(benefitBean.getBenefitCode().trim() + fldSeparator);

		dataParam.append(benefitBean.getVeteranNmL().trim().replaceAll(fldSeparator, "") + fldSeparator);

		dataParam.append(benefitBean.getVeteranNmF().trim().replaceAll(fldSeparator, "") + fldSeparator);

		logger.info("********** buildDataParam value is: " + dataParam.toString() + "," + "**********");

		return dataParam.toString().trim();
	}

}
