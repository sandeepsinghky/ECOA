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

import nc.dhhs.nccss.acts.dao.CasePartIncomeDao;
import nc.dhhs.nccss.acts.dao.rowmap.CasePartIncomeRowMapper;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartIncome;
import nc.dhhs.nccss.acts.ecoa.web.config.WebsiteConfiguration;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * @author Phani Konuru
 *
 */
@Repository
public class CasePartIncomeDaoImpl implements CasePartIncomeDao
{

	protected final Logger		logger		= Logger.getLogger(CasePartIncomeDaoImpl.class);

	private JdbcTemplate		jdbcTemplate;

	private SimpleJdbcCall		procCasePartIncome1, procCasePartIncome2;

	private static final String	SCREEN_SP	= "APP_RESP";

	@Autowired
	//@Required
	public void setDataSource(DataSource dataSource)
	{

		logger.debug("\n********** IN CasePartIncomeDaoImpl: SETDATASOURCE**********\n");

		this.jdbcTemplate = new JdbcTemplate(dataSource);

		this.jdbcTemplate.setSkipUndeclaredResults(true);

		//this.jdbcTemplate.setResultsMapCaseInsensitive(true);

		procCasePartIncome1 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_R_PART_INCOME");

		procCasePartIncome2 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_UPDATE_PART_INCOME");

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CasePartIncome> getCasePartIncome(String applicationId, String applicantId) throws SQLException
	{

		logger.debug("\n********** IN CasePartIncomeDaoImpl: getCasePartIncome **********\n");

		Map<String, Object> results = null;

		logger.debug("field = " + AppConstants.APP_FIELD_NCID);

		procCasePartIncome1.declareParameters(new SqlParameter("Application ID", Types.CHAR), new SqlParameter("Participant ID", Types.CHAR)).returningResultSet("partIncome", new CasePartIncomeRowMapper());

		SqlParameterSource in = new MapSqlParameterSource().addValue("Application ID", applicationId).addValue("Participant ID", applicantId);

		results = procCasePartIncome1.execute(in);

		return (List<CasePartIncome>) results.get("partIncome");
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.CasePartIncomeDao#createPartIncome(nc.dhhs.nccss.
	 * acts.ecoa.beans.CasePartIncome, java.lang.String)
	 */
	@Override
	public String createPartIncome(CasePartIncome incomeBean, String ncid) throws SQLException
	{
		logger.debug("\n********** IN CasePartIncomeDaoImpl: createPartIncome **********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_INSERT, ncid);

		String returnCode = ",,,,";

		String dataFields = buildDataParam(incomeBean);

		logger.info("\n********** Users NCID: " + ncid + "**********\n");

		try
		{
			procCasePartIncome2.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR));

			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

			Map<String, Object> results = procCasePartIncome2.execute(in);

			logger.info("Create createPartIncome returned code: " + results.get("RETURNCODE"));

			if (results.get("RETURNCODE").toString().substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
			{
				returnCode = results.get("RETURNCODE").toString();
				//dataFields = results.get("DATAFIELDS").toString();

			}
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
	 * nc.dhhs.nccss.acts.dao.CasePartIncomeDao#updatePartIncome(nc.dhhs.nccss.
	 * acts.ecoa.beans.CasePartIncome, java.lang.String)
	 */
	@Override
	public String updatePartIncome(CasePartIncome incomeBean, String ncid) throws SQLException
	{
		logger.debug("\n********** IN CasePartContactDaoImpl: updatePartContact**********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_UPDATE, ncid);

		String returnCode = ",,,,";

		String dataFields = buildDataParam(incomeBean);

		logger.info("User NCID: " + ncid);

		try
		{
			procCasePartIncome2.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR));

			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

			Map<String, Object> results = procCasePartIncome2.execute(in);

			logger.info("updatePartIncome:" + results.get("RETURNCODE"));

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
	 * nc.dhhs.nccss.acts.dao.CasePartIncomeDao#deletePartIncome(nc.dhhs.nccss.
	 * acts.ecoa.beans.CasePartIncome, java.lang.String)
	 */
	@Override
	public String deletePartIncome(CasePartIncome incomeBean, String ncid) throws SQLException
	{
		logger.debug("\n********** IN CasePartIncomeDaoImpl:  deletePartIncome(incomeBean,ncid: " + ncid + ")" + "**********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_DELETE, ncid.trim());

		String returnCode = ",,,,";

		String dataFields = buildDataParam(incomeBean);

		try
		{
			procCasePartIncome2.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlParameter("DATAFIELDS", Types.VARCHAR));

			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

			Map<String, Object> results = procCasePartIncome2.execute(in);

			returnCode = results.get("RETURNCODE").toString();

			logger.info("deletePartIncome returned: " + results.get("RETURNCODE"));
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
		logger.debug("\n********** IN CasePartIncomeDaoImpl: buildCommonParam(op: " + op + "," + "ncid: " + ncid + ")**********\n");

		String commonParam = op + ",,," + SCREEN_SP + "," + ncid + "," + AppConstants.INTERFACE_CODE + ",,";

		logger.info("*****commonParam value: " + commonParam + "*****");
		return commonParam;
	}

	/**
	 * @param applBean
	 * @return
	 */
	private String buildDataParam(CasePartIncome incomeBean)
	{

		logger.debug("\n********** IN CasePartIncomeDaoImpl:  buildDataParam(incomeBean)" + "**********\n");

		//string to separate field values passed to the SP
		String fldSeperator = AppConstants.FLD_SEPARATOR;

		StringBuffer dataParam = new StringBuffer(" ");

		dataParam.append(incomeBean.getApplicationId().trim() + fldSeperator);

		dataParam.append(incomeBean.getApplicantId().trim() + fldSeperator);

		dataParam.append(incomeBean.getIncomeSrc().trim() + fldSeperator);

		dataParam.append(String.format("%.2f", incomeBean.getIncomeAmt()) + fldSeperator);

		logger.info("buildDataParam =: " + dataParam.toString().trim());

		return dataParam.toString().trim();
	}

}
