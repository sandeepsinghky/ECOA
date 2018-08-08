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

import nc.dhhs.nccss.acts.dao.CasePartProfileExtDao;
import nc.dhhs.nccss.acts.dao.rowmap.CasePartProfileExtRowMapper;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartProfileExt;
import nc.dhhs.nccss.acts.ecoa.web.config.WebsiteConfiguration;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * @author Phani Konuru
 *
 */
@Repository
public class CasePartProfileExtDaoImpl implements CasePartProfileExtDao
{

	protected final Logger		logger		= Logger.getLogger(CasePartProfileExtDaoImpl.class);

	private JdbcTemplate		jdbcTemplate;

	private SimpleJdbcCall		procCasePartProfileExt1, procCasePartProfileExt2;

	private static final String	SCREEN_SP	= "APP_RESP";

	@Autowired
	//@Required
	public void setDataSource(DataSource dataSource)
	{

		logger.debug("\n********** IN CasePartAddrDaoImpl: SETDATASOURCE**********\n");

		this.jdbcTemplate = new JdbcTemplate(dataSource);

		this.jdbcTemplate.setSkipUndeclaredResults(true);

		//this.jdbcTemplate.setResultsMapCaseInsensitive(true);

		procCasePartProfileExt1 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_R_PART_PROF_EXT");

		procCasePartProfileExt2 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_UPDATE_PROFILE_EXT");

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.CasePartProfileExtDao#getCasePartProfileExt(java.
	 * lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CasePartProfileExt> getCasePartProfileExt(String applicationId, String applicantId) throws SQLException
	{

		logger.debug("\n********** IN CasePartProfileExtDaoImpl: getCasePartProfileExt **********\n");

		Map<String, Object> results = null;

		logger.debug("field = " + AppConstants.APP_FIELD_NCID);

		procCasePartProfileExt1.declareParameters(new SqlParameter("Application ID", Types.CHAR), new SqlParameter("Applicant ID", Types.CHAR)).returningResultSet("profileExt", new CasePartProfileExtRowMapper());

		SqlParameterSource in = new MapSqlParameterSource().addValue("Application ID", applicationId).addValue("Applicant ID", applicantId);

		results = procCasePartProfileExt1.execute(in);

		return (List<CasePartProfileExt>) results.get("profileExt");
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.CasePartProfileExtDao#createPartProfileExt(nc.dhhs
	 * .nccss.acts.ecoa.beans.CasePartProfileExt, java.lang.String)
	 */
	@Override
	public String createPartProfileExt(CasePartProfileExt profileExtBean, String ncid) throws SQLException
	{
		logger.debug("\n********** IN CasePartProfileExtDaoImpl: createPartProfileExt **********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_INSERT, ncid);

		String returnCode = ",,,,";

		String dataFields = buildDataParam(profileExtBean);

		logger.info("\n********** Users NCID: " + ncid + "**********\n");

		try
		{
			procCasePartProfileExt2.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR));

			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

			Map<String, Object> results = procCasePartProfileExt2.execute(in);

			logger.info("Create createPartProfileExt returned code: " + results.get("RETURNCODE"));

			if (results.get("RETURNCODE").toString().substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
			{
				returnCode = results.get("RETURNCODE").toString();

			}
		}

		catch (Exception ex)
		{
			logger.error(ex.getMessage());
		}

		return returnCode;
	}

	@Override
	public String updatePartProfileExt(CasePartProfileExt profileExtBean, String ncid) throws SQLException
	{
		logger.debug("\n********** IN CasePartProfileExtDaoImpl: updatePartProfileExt**********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_UPDATE, ncid);

		String returnCode = ",,,,";

		String dataFields = buildDataParam(profileExtBean);

		logger.info("User NCID: " + ncid);

		try
		{
			procCasePartProfileExt2.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR));

			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

			Map<String, Object> results = procCasePartProfileExt2.execute(in);

			logger.info("updatePartProfileExt returned:" + results.get("RETURNCODE"));

			returnCode = results.get("RETURNCODE").toString();
		}

		catch (Exception ex)
		{
			logger.error(ex.getMessage());
		}

		return returnCode;
	}

	@Override
	public String deletePartProfileExt(CasePartProfileExt profileExtBean, String ncid) throws SQLException
	{
		logger.debug("\n********** IN CasePartProfileExtDaoImpl:  deletePartProfileExt(ncid: " + ncid + ")" + "**********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_DELETE, ncid.trim());

		String returnCode = ",,,,";

		String dataFields = buildDataParam(profileExtBean);

		try
		{
			procCasePartProfileExt2.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlParameter("DATAFIELDS", Types.VARCHAR));

			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

			Map<String, Object> results = procCasePartProfileExt2.execute(in);

			returnCode = results.get("RETURNCODE").toString();

			logger.info("deletePartAddr returned: " + results.get("RETURNCODE"));
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
	private String buildDataParam(CasePartProfileExt profileExtBean)
	{

		logger.debug("\n********** IN CasePartAddrDaoImpl:  buildDataParam(addrBean)" + "**********\n");

		//string to separate field values passed to the SP
		String fldSeperator = AppConstants.FLD_SEPARATOR;

		StringBuffer dataParam = new StringBuffer(" ");

		dataParam.append(profileExtBean.getApplicationId().trim() + fldSeperator);

		dataParam.append(profileExtBean.getApplicantId().trim() + fldSeperator);

		dataParam.append(profileExtBean.getProfileExtType().trim() + fldSeperator);

		dataParam.append(profileExtBean.getProfCty().trim() + fldSeperator);

		dataParam.append(profileExtBean.getProfSt().trim() + fldSeperator);

		dataParam.append(profileExtBean.getProfCnty().trim() + fldSeperator);

		dataParam.append(profileExtBean.getProfCntry().trim() + fldSeperator);

		logger.info("buildDataParam =: " + dataParam.toString().trim());

		return dataParam.toString().trim();
	}

}
