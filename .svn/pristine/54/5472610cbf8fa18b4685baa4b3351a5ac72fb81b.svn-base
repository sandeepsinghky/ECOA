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

import nc.dhhs.nccss.acts.dao.CasePartAddrDao;
import nc.dhhs.nccss.acts.dao.rowmap.CasePartAddrRowMapper;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartAddress;
import nc.dhhs.nccss.acts.ecoa.web.config.WebsiteConfiguration;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * @author Phani Konuru
 *
 */
@Repository
public class CasePartAddrDaoImpl implements CasePartAddrDao
{

	protected final Logger		logger		= Logger.getLogger(CasePartAddrDaoImpl.class);

	private JdbcTemplate		jdbcTemplate;

	private SimpleJdbcCall		procCasePartAddr1, procCasePartAddr2;

	private static final String	SCREEN_SP	= "APP_RESP";

	@Autowired
	//@Required
	public void setDataSource(DataSource dataSource)
	{

		logger.debug("\n********** IN CasePartAddrDaoImpl: SETDATASOURCE**********\n");

		this.jdbcTemplate = new JdbcTemplate(dataSource);

		this.jdbcTemplate.setSkipUndeclaredResults(true);

		//this.jdbcTemplate.setResultsMapCaseInsensitive(true);

		procCasePartAddr1 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_R_ADDR_APPLICANT");

		procCasePartAddr2 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_UPDATE_PART_ADDR");

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CasePartAddress> getCasePartAddr(String applicationId, String applicantId) throws SQLException
	{

		logger.debug("\n********** IN CasePartAddrDaoImpl: getCasePartAddr **********\n");

		Map<String, Object> results = null;

		logger.debug("field = " + AppConstants.APP_FIELD_NCID);

		procCasePartAddr1.declareParameters(new SqlParameter("Application ID", Types.CHAR), new SqlParameter("Participant ID", Types.CHAR)).returningResultSet("partAddr", new CasePartAddrRowMapper());

		SqlParameterSource in = new MapSqlParameterSource().addValue("Application ID", applicationId).addValue("Participant ID", applicantId);

		results = procCasePartAddr1.execute(in);

		return (List<CasePartAddress>) results.get("partAddr");
	}

	@Override
	public String createPartAddr(CasePartAddress addrBean, String ncid) throws SQLException
	{
		logger.debug("\n********** IN CasePartAddrDaoImpl: createPartAddr **********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_INSERT, ncid);

		String returnCode = ",,,,";

		String dataFields = buildDataParam(addrBean);

		logger.info("\n********** Users NCID: " + ncid + "**********\n");

		try
		{
			procCasePartAddr2.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR));

			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

			Map<String, Object> results = procCasePartAddr2.execute(in);

			logger.info("Create createPartAddr returned code: " + results.get("RETURNCODE"));

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

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.CaseApplicationDao#updateCaseApplication(nc.dhhs.
	 * nccss.acts.ecoa.beans.CaseApplication)
	 */
	@Override
	public String updatePartAddr(CasePartAddress addrBean, String ncid) throws SQLException
	{
		logger.debug("\n********** IN CasePartAddrDaoImpl: updatePartAddr**********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_UPDATE, ncid);

		String returnCode = ",,,,";

		String dataFields = buildDataParam(addrBean);

		logger.info("User NCID: " + ncid);

		try
		{
			procCasePartAddr2.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR));

			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

			Map<String, Object> results = procCasePartAddr2.execute(in);

			logger.info("Update Part Address returned:" + results.get("RETURNCODE"));

			returnCode = results.get("RETURNCODE").toString();
		}

		catch (Exception ex)
		{
			logger.error(ex.getMessage());
		}

		return returnCode;
	}

	@Override
	public String deletePartAddr(CasePartAddress addrBean, String ncid) throws SQLException
	{
		logger.debug("\n********** IN CasePartAddrDaoImpl:  deletePartAddr(partinfo,ncid: " + ncid + ")" + "**********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_DELETE, ncid.trim());

		String returnCode = ",,,,";

		String dataFields = buildDataParam(addrBean);

		try
		{
			procCasePartAddr2.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlParameter("DATAFIELDS", Types.VARCHAR));

			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

			Map<String, Object> results = procCasePartAddr2.execute(in);

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
	private String buildDataParam(CasePartAddress addrBean)
	{

		logger.debug("\n********** IN CasePartAddrDaoImpl:  buildDataParam(addrBean)" + "**********\n");

		//string to separate field values passed to the SP
		String fldSeperator = AppConstants.FLD_SEPARATOR;

		StringBuffer dataParam = new StringBuffer(" ");

		dataParam.append(addrBean.getApplicationId().trim() + fldSeperator);

		dataParam.append(addrBean.getApplicantId().trim() + fldSeperator);

		dataParam.append(addrBean.getAddrType().trim() + fldSeperator);

		dataParam.append(addrBean.getAddrLn1().trim().replaceAll(fldSeperator, "") + fldSeperator);

		dataParam.append(addrBean.getAddrLn2().trim().replaceAll(fldSeperator, "") + fldSeperator);

		dataParam.append(addrBean.getAddrCty().trim().replaceAll(fldSeperator, "") + fldSeperator);

		dataParam.append(addrBean.getAddrSt().trim() + fldSeperator);

		dataParam.append(addrBean.getAddrZip5().trim() + fldSeperator);

		dataParam.append(addrBean.getAddrZip4().trim() + fldSeperator);

		dataParam.append(addrBean.getAddrZipCarr().trim() + fldSeperator);

		dataParam.append(addrBean.getAddrCnty().trim() + fldSeperator);

		dataParam.append(addrBean.getAddrCntry().trim() + fldSeperator);

		if (addrBean.getCreateDtStr() != null) dataParam.append(addrBean.getCreateDtStr().trim() + fldSeperator);
		else
			dataParam.append(AppConstants.DEFAULT_DATE + fldSeperator);
		if (addrBean.getLastUpdatDtStr() != null) dataParam.append(addrBean.getLastUpdatDtStr().trim() + fldSeperator);
		else
			dataParam.append(AppConstants.DEFAULT_DATE + fldSeperator);
		if (addrBean.getLastUpdateTmStr() != null)
			dataParam.append(addrBean.getLastUpdateTmStr().trim() + fldSeperator);
		else
			dataParam.append(fldSeperator);

		logger.info("buildDataParam =: " + dataParam.toString().trim());

		return dataParam.toString().trim();
	}

}
