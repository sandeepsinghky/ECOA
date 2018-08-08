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

import nc.dhhs.nccss.acts.dao.CasePartEmpDao;
import nc.dhhs.nccss.acts.dao.rowmap.CasePartEmpRowMapper;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartEmp;
import nc.dhhs.nccss.acts.ecoa.web.config.WebsiteConfiguration;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * @author Phani Konuru
 *
 */
@Repository
public class CasePartEmpDaoImpl implements CasePartEmpDao
{

	protected final Logger		logger		= Logger.getLogger(CasePartEmpDaoImpl.class);

	private JdbcTemplate		jdbcTemplate;

	private SimpleJdbcCall		procCasePartEmp1, procCasePartEmp2;

	private static final String	SCREEN_SP	= "APP_RESP";

	@Autowired
	// @Required
	public void setDataSource(DataSource dataSource)
	{

		logger.debug("\n********** IN CasePartEmpDaoImpl: SETDATASOURCE**********\n");

		this.jdbcTemplate = new JdbcTemplate(dataSource);

		this.jdbcTemplate.setSkipUndeclaredResults(true);

		procCasePartEmp1 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_R_EMPLOYMENT");

		procCasePartEmp2 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_UPDATE_EMPLOYMENT");

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CasePartEmp> getPartEmp(String applicationId, String applicantId) throws SQLException
	{

		logger.debug("\n********** CasePartEmpDaoImpl: getPartEmp **********\n");

		logger.debug("\n********** IN CasePartEmpDaoImpl:getPartEmp(applicationID:" + applicationId + ")" + "**********\n");

		procCasePartEmp1.declareParameters(new SqlParameter("ID_APPLICATION", Types.CHAR), new SqlParameter("ID_APPLICANT", Types.CHAR))

				.returningResultSet("partEmp", new CasePartEmpRowMapper());

		SqlParameterSource in = new MapSqlParameterSource().addValue("ID_APPLICATION", applicationId).addValue("ID_APPLICANT", applicantId);

		Map<String, Object> results = procCasePartEmp1.execute(in);

		return (List<CasePartEmp>) results.get("partEmp");
	}

	@Override
	public String createPartEmp(CasePartEmp partEmpBean, String ncid) throws SQLException
	{
		logger.debug("\n********** IN CasePartEmpDaoImpl: createCasePartEmp **********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_INSERT, ncid);

		String returnCode = ",,,,";

		String dataFields = buildDataParam(partEmpBean);

		String applId = "";

		try
		{
			procCasePartEmp2.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR));

			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

			Map<String, Object> results = procCasePartEmp2.execute(in);

			logger.info("CreateCasePartEmp  returned code: " + results.get("RETURNCODE"));

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
	public String updatePartEmp(CasePartEmp partEmpBean, String ncid) throws SQLException
	{
		logger.debug("\n********** IN CasePartEmpDaoImpl: updatePartEmp**********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_UPDATE, ncid);

		String returnCode = ",,,,";

		String dataFields = buildDataParam(partEmpBean);

		// logger.info("User NCID: " + applBean.getNcId().trim());

		try
		{
			procCasePartEmp2.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR));

			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

			Map<String, Object> results = procCasePartEmp2.execute(in);

			logger.info("updatePartEmp returned:" + results.get("RETURNCODE"));

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
	public String deletePartEmp(CasePartEmp partEmpBean, String ncid) throws SQLException
	{
		logger.debug("\n********** IN CasePartEmpDaoImpl: deletePartEmp**********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_DELETE, ncid);

		String returnCode = ",,,,";

		logger.info("*****delete appId: " + partEmpBean.getApplicationId() + "*****");

		String dataFields = buildDataParam(partEmpBean);

		try
		{
			procCasePartEmp2.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR));

			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

			Map<String, Object> results = procCasePartEmp2.execute(in);

			returnCode = results.get("RETURNCODE").toString();

			logger.info("deletePartEmp returned:" + returnCode);
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
		logger.debug("\n********** IN CasePartEmpDaoImpl: buildCommonParam(op: " + op + "," + "ncid: " + ncid + ")**********\n");

		String commonParam = op + ",,," + SCREEN_SP + "," + ncid + "," + AppConstants.INTERFACE_CODE + ",,";

		logger.info("*****commonParam value: " + commonParam + "*****");
		return commonParam;
	}

	/**
	 * @param applBean
	 * @return
	 */
	private String buildDataParam(CasePartEmp partEmpBean)
	{

		logger.debug("\n********** IN CasePartEmpDaoImpl: buildDataParam **********\n");

		//string to separate field values passed to the SP
		String fldSeperator = AppConstants.FLD_SEPARATOR;

		StringBuffer dataParam = new StringBuffer(" ");

		dataParam.append(partEmpBean.getApplicationId().trim() + fldSeperator);

		dataParam.append(partEmpBean.getApplicantId().trim() + fldSeperator);

		dataParam.append(partEmpBean.getThirdPartyId().trim() + fldSeperator);

		if (partEmpBean.getEmplmEndDtStr() != null)
		{
			dataParam.append(partEmpBean.getEmplmEndDtStr().trim() + fldSeperator);
		}
		else
		{
			dataParam.append(AppConstants.DEFAULT_DATE + fldSeperator);
		}

		dataParam.append(partEmpBean.getEmplmEndReasn().trim().replaceAll(fldSeperator, "") + fldSeperator);

		dataParam.append(partEmpBean.getOcupation().trim().replaceAll(fldSeperator, "") + fldSeperator);

		logger.info("********** buildDataParam value is: " + dataParam.toString() + "**********");

		return dataParam.toString().trim();
	}

}
