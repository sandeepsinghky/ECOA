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

import nc.dhhs.nccss.acts.dao.CaseVehicleDao;
import nc.dhhs.nccss.acts.dao.rowmap.VehicleRowMapper;
import nc.dhhs.nccss.acts.ecoa.beans.Vehicle;
import nc.dhhs.nccss.acts.ecoa.web.config.WebsiteConfiguration;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * @author Mallika Velagapudi
 *
 */
@Repository
public class CaseVehicleDaoImpl implements CaseVehicleDao
{

	protected final Logger		logger		= Logger.getLogger(CaseVehicleDaoImpl.class);

	private JdbcTemplate		jdbcTemplate;

	private SimpleJdbcCall		procCaseApplication1, procCaseApplication2;

	private static final String	SCREEN_SP	= "APP_RESP";

	/**
	 * @param dataSource
	 */
	@Autowired

	public void setDataSource(DataSource dataSource)
	{

		logger.debug("\n********** IN CaseVehicleDaoImpl: SETDATASOURCE**********\n");

		this.jdbcTemplate = new JdbcTemplate(dataSource);

		this.jdbcTemplate.setSkipUndeclaredResults(true);

		procCaseApplication1 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_R_NCP_VEHICLE");

		procCaseApplication2 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_UPDATE_NCP_VEHICLE");

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.CaseVehicleDao#getCaseVehiclesByPartId(java.lang.
	 * String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Vehicle> getCaseVehiclesByPartId(String applId, String applicantId) throws SQLException
	{

		logger.debug("\n********** IN CaseVehicleDaoImpl:getCaseVehiclesByPartId(appId: " + applId + "," + "applicantId: " + applicantId + ")" + "**********\n");

		procCaseApplication1.declareParameters(new SqlParameter("ID_APPLICATION", Types.CHAR), new SqlParameter("ID_APPLICANT", Types.CHAR)).returningResultSet("caseVehicles", new VehicleRowMapper());

		SqlParameterSource in = new MapSqlParameterSource().addValue("ID_APPLICATION", applId).addValue("ID_APPLICANT", applicantId);

		Map<String, Object> results = procCaseApplication1.execute(in);

		return (List<Vehicle>) results.get("caseVehicles");
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.CaseVehicleDao#createCaseVehile(nc.dhhs.nccss.acts
	 * .ecoa.beans.Vehicle, java.lang.String)
	 */
	@Override
	public String createCaseVehile(Vehicle vehicle, String ncid) throws SQLException
	{
		logger.debug("\n********** IN CaseVehicleDaoImpl:createCaseVehile(vehicle " + "," + "ncid: " + ncid + ")" + "**********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_INSERT, ncid);

		String returnCode = ",,,,";

		String dataFields = buildDataParam(vehicle);

		String applId = "";

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
	 * nc.dhhs.nccss.acts.dao.CaseVehicleDao#deleteCaseVehicles(nc.dhhs.nccss.
	 * acts.ecoa.beans.Vehicle, java.lang.String)
	 */
	@Override
	public String deleteCaseVehicles(Vehicle vehicle, String ncid) throws SQLException
	{
		logger.debug("\n********** IN CaseVehicleDaoImpl:deleteCaseVehicles(vehicle " + "," + "ncid: " + ncid + ")" + "**********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_DELETE, ncid);

		String returnCode = ",,,,";

		logger.info("*****delete appId: " + vehicle.getApplicationId() + "*****");

		String dataFields = buildDataParam(vehicle);

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
	 * @param vehicleBean
	 * @return
	 */
	private String buildDataParam(Vehicle vehicleBean)
	{

		logger.debug("\n********** IN CaseVehicleDaoImpl: buildDataParam **********\n");

		StringBuffer dataParam = new StringBuffer(" ");

		//string to separate field values passed to the SP
		String fldSeparator = AppConstants.FLD_SEPARATOR;

		dataParam.append(vehicleBean.getApplicationId().trim() + fldSeparator);

		dataParam.append(vehicleBean.getApplicantId().trim() + fldSeparator);

		dataParam.append(vehicleBean.getMake().trim().replaceAll(fldSeparator, "") + fldSeparator);

		dataParam.append(vehicleBean.getModel().trim().replaceAll(fldSeparator, "") + fldSeparator);

		dataParam.append(vehicleBean.getYear().trim() + fldSeparator);

		logger.info("********** buildDataParam value is: " + dataParam.toString() + "**********");

		return dataParam.toString().trim();
	}

}
