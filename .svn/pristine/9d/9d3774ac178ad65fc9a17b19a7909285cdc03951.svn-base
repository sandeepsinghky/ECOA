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

import nc.dhhs.nccss.acts.dao.ThirdPartyDao;
import nc.dhhs.nccss.acts.dao.rowmap.ThirdPartyRowMapper;
import nc.dhhs.nccss.acts.ecoa.beans.ThirdParty;
import nc.dhhs.nccss.acts.ecoa.web.config.WebsiteConfiguration;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * @author Mallika Velagapudi
 *
 */
@Repository
public class ThirdPartyDaoImpl implements ThirdPartyDao
{

	protected final Logger		logger		= Logger.getLogger(ThirdPartyDaoImpl.class);

	private JdbcTemplate		jdbcTemplate;

	private SimpleJdbcCall		procCaseApplication1, procCaseApplication2, procCaseApplication3;

	private static final String	SCREEN_SP	= "APP_RESP";

	@Autowired
	// @Required
	public void setDataSource(DataSource dataSource)
	{

		logger.debug("\n********** IN ThirdPartyDaoImpl: SETDATASOURCE**********\n");

		this.jdbcTemplate = new JdbcTemplate(dataSource);

		this.jdbcTemplate.setSkipUndeclaredResults(true);

		procCaseApplication1 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_R_3PTY_APPL");

		procCaseApplication2 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_UPDATE_THIRD_PARTY");

		procCaseApplication3 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_R_3PTY_ID_3PTY");

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ThirdParty> getThirdPartyByApplIdandType(String applId, String thirdPartyType) throws SQLException
	{

		logger.debug("\n********** ThirdPartyDaoImpl: getThirdPartysByApplId **********\n");

		logger.debug("\n********** IN ThirdPartyDaoImpl:getThirdPartysByApplId(applID:" + applId + ")" + "**********\n");

		procCaseApplication1.declareParameters(new SqlParameter("ID_APPLICATION", Types.CHAR), new SqlParameter("CD_3PTY_TYPE", Types.CHAR))

				.returningResultSet("thirdPartyDetails", new ThirdPartyRowMapper());

		SqlParameterSource in = new MapSqlParameterSource().addValue("ID_APPLICATION", applId).addValue("CD_3PTY_TYPE", thirdPartyType);

		Map<String, Object> results = procCaseApplication1.execute(in);

		return (List<ThirdParty>) results.get("thirdPartyDetails");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ThirdParty> getThirdPartyBy3ptyId(String applId, String thirdPartyId) throws SQLException
	{

		logger.debug("\n********** ThirdPartyDaoImpl: getThirdPartyBy3ptyId **********\n");

		logger.debug("\n********** IN ThirdPartyDaoImpl:getThirdPartyBy3ptyId(applID:" + applId + ")" + "**********\n");

		procCaseApplication3.declareParameters(new SqlParameter("ID_APPLICATION", Types.CHAR), new SqlParameter("ID_3PTY", Types.CHAR))

				.returningResultSet("thirdPartyDetails", new ThirdPartyRowMapper());

		SqlParameterSource in = new MapSqlParameterSource().addValue("ID_APPLICATION", applId).addValue("ID_3PTY", thirdPartyId);

		Map<String, Object> results = procCaseApplication3.execute(in);

		return (List<ThirdParty>) results.get("thirdPartyDetails");
	}

	@Override
	public String createThirdParty(ThirdParty thirdPartyBean, String ncid) throws SQLException
	{
		logger.debug("\n********** IN ThirdPartyDaoImpl: createCasePartBenefit **********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_INSERT, ncid);

		String returnCode = ",,,,";

		String dataFields = buildDataParam(thirdPartyBean);

		String thirdPtyId = "";

		try
		{
			procCaseApplication2.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR));

			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

			Map<String, Object> results = procCaseApplication2.execute(in);

			logger.info("CreateCasePartBenefit  returned code: " + results.get("RETURNCODE"));

			returnCode = results.get("RETURNCODE").toString();
			if (returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
			{
				String data = (String) results.get("DATAFIELDS");
				thirdPtyId = results.get("DATAFIELDS").toString().substring(10, 20).trim();

				logger.info("thirdPtyId returned: " + thirdPtyId + "...........");
			}
		}

		catch (Exception ex)
		{
			logger.error(ex.getMessage());
		}

		return thirdPtyId;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.CaseApplicationDao#updateCaseApplication(nc.dhhs.
	 * nccss.acts.ecoa.beans.CaseApplication)
	 */
	@Override
	public String updateThirdParty(ThirdParty thirdPartyBean, String ncid) throws SQLException
	{
		logger.debug("\n********** IN ThirdPartyDaoImpl: updateCaseApplication**********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_UPDATE, ncid);

		String returnCode = ",,,,";

		String dataFields = buildDataParam(thirdPartyBean);

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
	public String deleteThirdParty(ThirdParty thirdPartyBean, String ncid) throws SQLException
	{
		logger.debug("\n********** IN ThirdPartyDaoImpl: deleteCaseApplication**********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_DELETE, ncid);

		String returnCode = ",,,,";

		logger.info("*****delete appId: " + thirdPartyBean.getApplicationId() + "*****");

		String dataFields = buildDataParam(thirdPartyBean);

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
		logger.debug("\n********** IN ThirdPartyDaoImpl: buildCommonParam(op: " + op + "," + "ncid: " + ncid + ")**********\n");

		String commonParam = op + ",,," + SCREEN_SP + "," + ncid + "," + AppConstants.INTERFACE_CODE + ",,";

		logger.info("*****commonParam value: " + commonParam + "*****");
		return commonParam;
	}

	/**
	 * @param applBean
	 * @return
	 */
	private String buildDataParam(ThirdParty thirdPartyBean)
	{

		logger.debug("\n********** IN ThirdPartyDaoImpl: buildDataParam **********\n");

		StringBuffer dataParam = new StringBuffer(" ");
		//string to separate field values passed to the SP
		String fldSeparator = AppConstants.FLD_SEPARATOR;

		dataParam.append(thirdPartyBean.getApplicationId().trim() + fldSeparator);

		dataParam.append(thirdPartyBean.getThirdPartyId().trim() + fldSeparator);

		dataParam.append(thirdPartyBean.getThirdPartyType().trim() + fldSeparator);
		dataParam.append(thirdPartyBean.getThirdPartyNm().trim().replaceAll(fldSeparator, "") + fldSeparator);
		dataParam.append(thirdPartyBean.getAddressLn1().trim().replaceAll(fldSeparator, "") + fldSeparator);
		dataParam.append(thirdPartyBean.getAddressLn2().trim().replaceAll(fldSeparator, "") + fldSeparator);
		dataParam.append(thirdPartyBean.getThirdPartyCity().trim().replaceAll(fldSeparator, "") + fldSeparator);
		dataParam.append(thirdPartyBean.getThirdPartyState().trim() + fldSeparator);
		dataParam.append(thirdPartyBean.getThirdPartyZip5().trim() + fldSeparator);
		dataParam.append(thirdPartyBean.getThirdPartyZip4().trim() + fldSeparator);
		dataParam.append(thirdPartyBean.getThirdPartyZipCarr().trim() + fldSeparator);
		dataParam.append(thirdPartyBean.getPhoneAreaCode().trim() + fldSeparator);
		dataParam.append(thirdPartyBean.getPhoneExc().trim() + fldSeparator);
		dataParam.append(thirdPartyBean.getPhoneLn().trim() + fldSeparator);
		dataParam.append(thirdPartyBean.getCountryNm().trim() + fldSeparator);

		logger.info("********** buildDataParam value is: " + dataParam.toString() + "**********");

		return dataParam.toString().trim();
	}

}
