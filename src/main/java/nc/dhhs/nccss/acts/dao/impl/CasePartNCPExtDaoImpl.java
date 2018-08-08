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

import nc.dhhs.nccss.acts.dao.CasePartNCPExtDao;
import nc.dhhs.nccss.acts.dao.rowmap.CasePartNCPExtRowMapper;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartNCPExt;
import nc.dhhs.nccss.acts.ecoa.web.config.WebsiteConfiguration;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * @author Mallika Velagapudi
 *
 */
@Repository
public class CasePartNCPExtDaoImpl implements CasePartNCPExtDao
{

	protected final Logger		logger		= Logger.getLogger(CasePartNCPExtDaoImpl.class);

	private JdbcTemplate		jdbcTemplate;

	private SimpleJdbcCall		procCaseApplication1, procCaseApplication2;

	private static final String	SCREEN_SP	= "APP_RESP";

	/**
	 * @param dataSource
	 */
	@Autowired
	// @Required
	public void setDataSource(DataSource dataSource)
	{

		logger.debug("\n********** IN CasePartNCPExtDaoImpl: SETDATASOURCE**********\n");

		this.jdbcTemplate = new JdbcTemplate(dataSource);

		this.jdbcTemplate.setSkipUndeclaredResults(true);

		// this.jdbcTemplate.setResultsMapCaseInsensitive(true);

		procCaseApplication1 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_R_NCP_EXT");

		procCaseApplication2 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_UPDATE_NCP_EXT");

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.CasePartNCPExtDao#getCasePartNCPExtByPartId(java.
	 * lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CasePartNCPExt> getCasePartNCPExtByPartId(String applId, String applicantId) throws SQLException
	{

		logger.debug("\n********** IN CasePartNCPExtDaoImpl: getCasePartNCPExtByPartId(appId: " + applId + "," + "applicantId: " + applicantId + ")" + "**********\n");

		procCaseApplication1.declareParameters(new SqlParameter("ID_APPLICATION", Types.CHAR), new SqlParameter("ID_APPLICANT", Types.CHAR)).returningResultSet("partExtDetails", new CasePartNCPExtRowMapper());

		SqlParameterSource in = new MapSqlParameterSource().addValue("ID_APPLICATION", applId).addValue("ID_APPLICANT", applicantId);

		Map<String, Object> results = procCaseApplication1.execute(in);

		return (List<CasePartNCPExt>) results.get("partExtDetails");

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.CasePartNCPExtDao#createCasePartNCPExt(nc.dhhs.
	 * nccss.acts.ecoa.beans.CasePartNCPExt, java.lang.String)
	 */
	@Override
	public String createCasePartNCPExt(CasePartNCPExt applNCPExtBean, String ncid) throws SQLException
	{
		logger.debug("\n********** IN CasePartNCPExtDaoImpl: createCasePartNCPExt(aCasePartNCPExt " + "," + "ncid: " + ncid + ")" + "**********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_INSERT, ncid);

		String returnCode = ",,,,";

		String dataFields = buildDataParam(applNCPExtBean);

		String applId = "";

		try
		{
			procCaseApplication2.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR));

			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

			Map<String, Object> results = procCaseApplication2.execute(in);

			logger.info("createCasePartNCPExt  returned code: " + results.get("RETURNCODE"));

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
	public String updateCasePartNCPExt(CasePartNCPExt applNCPExtBean, String ncid) throws SQLException
	{
		logger.debug("\n********** IN CasePartNCPExtDaoImpl: updateCaseApplication**********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_UPDATE, ncid);

		String returnCode = ",,,,";

		String dataFields = buildDataParam(applNCPExtBean);

		logger.info("User NCID: " + ncid.trim());

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
	public String deleteCasePartNCPExt(CasePartNCPExt applNCPExtBean, String ncid) throws SQLException
	{
		logger.debug("\n********** IN CasePartNCPExtDaoImpl: deleteCaseApplication**********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_DELETE, ncid.trim());

		String returnCode = ",,,,";

		applNCPExtBean.setApplicationId(applNCPExtBean.getApplicationId());

		logger.info("*****delete appId: " + applNCPExtBean.getApplicationId() + "*****");

		String dataFields = buildDataParam(applNCPExtBean);

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
		logger.debug("\n********** IN CasePartNCPExtDaoImpl: buildCommonParam(op: " + op + "," + "ncid: " + ncid + ")**********\n");

		String commonParam = op + ",,," + SCREEN_SP + "," + ncid + "," + AppConstants.INTERFACE_CODE + ",,";

		logger.info("*****commonParam value: " + commonParam + "*****");
		return commonParam;
	}

	/**
	 * @param applNCPExtBean
	 * @return
	 */
	private String buildDataParam(CasePartNCPExt applNCPExtBean)
	{

		logger.debug("\n********** IN CasePartNCPExtDaoImpl: buildDataParam **********\n");

		StringBuffer dataParam = new StringBuffer(" ");
		//string to separate field values passed to the SP
		String fldSeparator = AppConstants.FLD_SEPARATOR;

		dataParam.append(applNCPExtBean.getApplicationId().trim() + fldSeparator);

		dataParam.append(applNCPExtBean.getApplicantId().trim() + fldSeparator);

		dataParam.append(applNCPExtBean.getBirthCityNm().trim().replaceAll(fldSeparator, "") + fldSeparator);

		dataParam.append(applNCPExtBean.getBirthStateNm().trim() + fldSeparator);

		dataParam.append(applNCPExtBean.getBirthCountyNm().trim() + fldSeparator);

		dataParam.append(applNCPExtBean.getBirthCountryNm().trim() + fldSeparator);

		dataParam.append(applNCPExtBean.getHeight().trim().replaceAll(fldSeparator, "") + fldSeparator);

		if (applNCPExtBean.getWeight() == 0.0) dataParam.append(fldSeparator);
		else
			dataParam.append(applNCPExtBean.getWeight() + fldSeparator);

		dataParam.append(applNCPExtBean.getEyeColor().trim().replaceAll(fldSeparator, "") + fldSeparator);

		dataParam.append(applNCPExtBean.getHairColor().trim().replaceAll(fldSeparator, "") + fldSeparator);
		dataParam.append(applNCPExtBean.getMarks().trim().replaceAll(fldSeparator, "") + fldSeparator);
		dataParam.append(applNCPExtBean.getDriverlicNum().trim().replaceAll(fldSeparator, "") + fldSeparator);
		dataParam.append(applNCPExtBean.getLicenseState().trim() + fldSeparator);
		dataParam.append(applNCPExtBean.getMaritalStatus().trim() + fldSeparator);
		if (applNCPExtBean.getMaritualDt() != null)
			dataParam.append(applNCPExtBean.getMaritualDtStr().trim() + fldSeparator);
		else
			dataParam.append(AppConstants.DEFAULT_DATE + fldSeparator);
		dataParam.append(applNCPExtBean.getMiltryBranch().trim().replaceAll(fldSeparator, "") + fldSeparator);
		dataParam.append(applNCPExtBean.getMiltryStatus().trim() + fldSeparator);
		dataParam.append(applNCPExtBean.getMiltryStation().trim().replaceAll(fldSeparator, "") + fldSeparator);
		dataParam.append(applNCPExtBean.getParole().trim() + fldSeparator);
		dataParam.append(applNCPExtBean.getIncarcerated().trim().replaceAll(fldSeparator, "") + fldSeparator);
		dataParam.append(applNCPExtBean.getWorkRelease().trim().replaceAll(fldSeparator, "") + fldSeparator);

		if (applNCPExtBean.getCreatedDt() != null)
			dataParam.append(applNCPExtBean.getCreatedDtStr().trim() + fldSeparator);
		else
			dataParam.append(AppConstants.DEFAULT_DATE + fldSeparator);
		if (applNCPExtBean.getLastUpdatDt() != null)
			dataParam.append(applNCPExtBean.getLastUpdatDtStr().trim() + fldSeparator);
		else
			dataParam.append(AppConstants.DEFAULT_DATE + fldSeparator);

		if (applNCPExtBean.getLastUpdateTmStr() != null)
			dataParam.append(applNCPExtBean.getLastUpdateTmStr().trim() + fldSeparator);
		else
			dataParam.append(fldSeparator);

		if (applNCPExtBean.getArrestDt() != null)
			dataParam.append(applNCPExtBean.getArrestDtStr().trim() + fldSeparator);
		else
			dataParam.append(AppConstants.DEFAULT_DATE + fldSeparator);

		dataParam.append(applNCPExtBean.getArrestCity().trim().replaceAll(fldSeparator, "") + fldSeparator);
		dataParam.append(applNCPExtBean.getArrestState().trim() + fldSeparator);
		dataParam.append(applNCPExtBean.getDriverLicStatus().trim().replaceAll(fldSeparator, "") + fldSeparator);
		dataParam.append(applNCPExtBean.getOwnVehicleStatus().trim().replaceAll(fldSeparator, "") + fldSeparator);
		dataParam.append(applNCPExtBean.getMltryStatus().trim().replaceAll(fldSeparator, "") + fldSeparator);
		dataParam.append(applNCPExtBean.getArrestStatus().trim().replaceAll(fldSeparator, "") + fldSeparator);
		dataParam.append(applNCPExtBean.getParoleStatus().trim().replaceAll(fldSeparator, "") + fldSeparator);
		dataParam.append(applNCPExtBean.getIncarStatus().trim().replaceAll(fldSeparator, "") + fldSeparator);
		dataParam.append(applNCPExtBean.getWorkReleaseStatus().trim().replaceAll(fldSeparator, "") + fldSeparator);
		dataParam.append(applNCPExtBean.getEmploymentStatus().trim().replaceAll(fldSeparator, "") + fldSeparator);
		dataParam.append(applNCPExtBean.getOrderStatus().trim().replaceAll(fldSeparator, "") + fldSeparator);
		dataParam.append(applNCPExtBean.getAdditionalNote().trim().replaceAll(fldSeparator, "") + fldSeparator);
		logger.info("buildDataParam =: " + dataParam.toString().trim());

		return dataParam.toString().trim();
	}

}
