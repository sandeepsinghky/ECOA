/**
 * 
 */
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

import nc.dhhs.nccss.acts.dao.CasePartProfileDao;
import nc.dhhs.nccss.acts.dao.rowmap.CasePartProfileRowMapper;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartProfile;
import nc.dhhs.nccss.acts.ecoa.web.config.WebsiteConfiguration;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * @author Phani Konuru
 *
 */

@Repository
public class CasePartProfileDaoImpl implements CasePartProfileDao
{

	protected final Logger		logger		= Logger.getLogger(CasePartProfileDaoImpl.class);

	private JdbcTemplate		jdbcTemplate;

	private SimpleJdbcCall		procCasePartProfile1, procCasePartProfile2;

	private static final String	SCREEN_SP	= "APP_APPLICANT";

	@Autowired
	//@Required
	public void setDataSource(DataSource dataSource)
	{

		logger.debug("\n********** IN CasePartChldExtDaoImpl: SET DATASOURCE **********\n");

		this.jdbcTemplate = new JdbcTemplate(dataSource);

		this.jdbcTemplate.setSkipUndeclaredResults(true);

		procCasePartProfile1 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_R_PART_PROF");

		procCasePartProfile2 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_UPDATE_PROFILE");

	}

	/**
	 * @param operationInsert
	 * @param trim
	 * @return
	 */
	private String buildCommonParam(String op, String ncid)
	{
		logger.debug("\n********** IN CaseParticipantDaoImpl:  buildCommonParam(" + op + "," + ncid + ")" + "**********\n");
		// TODO Auto-generated method stub
		String commonParam = op + ",,," + SCREEN_SP + "," + ncid + "," + AppConstants.INTERFACE_CODE + ",,";

		logger.info("commonParam: " + commonParam);
		return commonParam;

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.CasePartProfileDao#updatePartProfile(nc.dhhs.nccss
	 * .acts.ecoa.beans.CasePartProfile, java.lang.String)
	 */
	@Override
	public String updatePartProfile(CasePartProfile partInfo, String ncid) throws SQLException
	{
		logger.debug("\n********** IN CasePartProfileDaoImpl:  updatePartProfile(partinfo," + ncid + ")" + "**********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_UPDATE, ncid.trim());
		String returnCode = ",,,,";

		String dataFields = buildDataParam(partInfo);

		try
		{
			procCasePartProfile2.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlParameter("DATAFIELDS", Types.VARCHAR));

			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

			Map<String, Object> results = procCasePartProfile2.execute(in);

			returnCode = results.get("RETURNCODE").toString();

			logger.info("updateParticipant returned: " + returnCode);
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
	 * nc.dhhs.nccss.acts.dao.CasePartProfileDao#deletePartProfile(nc.dhhs.nccss
	 * .acts.ecoa.beans.CasePartProfile, java.lang.String)
	 */
	@Override
	public String deletePartProfile(CasePartProfile partInfo, String ncid) throws SQLException
	{
		logger.debug("\n********** IN CasePartProfileDaoImpl:  deletePartProfile(partinfo,ncid: " + ncid + ")" + "**********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_DELETE, ncid.trim());

		String returnCode = ",,,,";

		String dataFields = buildDataParam(partInfo);

		try
		{
			procCasePartProfile2.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlParameter("DATAFIELDS", Types.VARCHAR));

			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

			Map<String, Object> results = procCasePartProfile2.execute(in);

			returnCode = results.get("RETURNCODE").toString();

			logger.info("deleteParticipant returned: " + results.get("RETURNCODE"));
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
	 * nc.dhhs.nccss.acts.dao.CaseParticipantDao#createParticipant(nc.dhhs.nccss
	 * .acts.ecoa.beans.CaseParticipant, java.lang.String)
	 */
	@Override
	public String createPartProfile(CasePartProfile partInfo, String ncid) throws SQLException
	{
		logger.debug("\n********** IN CasePartProfileDaoImpl: createPartProfile(partinfo,ncid: " + ncid + ")" + "**********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_INSERT, ncid.trim());
		String returnCode = ",,,,";
		String dataFields = buildDataParam(partInfo);
		String partProfileId = "";

		try
		{
			procCasePartProfile2.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR));

			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

			Map<String, Object> results = procCasePartProfile2.execute(in);

			returnCode = results.get("RETURNCODE").toString();

			logger.info("createParticipant returned: " + results.get("RETURNCODE"));

			if ((results.get("RETURNCODE")).toString().substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
			{
				partProfileId = results.get("DATAFIELDS").toString().substring(10, 20).trim();

				logger.info("partProfileId created: " + partProfileId + "...........");
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
	 * @see nc.dhhs.nccss.acts.dao.CasePartProfileDao#getPartProfile(java.lang.
	 * String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CasePartProfile> getPartProfile(String applId, String applicantId) throws SQLException
	{

		logger.debug("\n********** IN CasePartProfileDaoImpl:  getPartProfile(applId:" + applId + ", partId: " + applicantId + ")" + "**********\n");
		procCasePartProfile1.declareParameters(new SqlParameter("Application ID", Types.CHAR), new SqlParameter("Applicant ID", Types.CHAR)).returningResultSet("casePartProfile", new CasePartProfileRowMapper());
		SqlParameterSource in = new MapSqlParameterSource().addValue("Application ID", applId).addValue("Applicant ID", applicantId);
		Map<String, Object> results = procCasePartProfile1.execute(in);
		return (List<CasePartProfile>) results.get("casePartProfile");

	}

	/**
	 * @param applBean
	 * @return
	 */
	private String buildDataParam(CasePartProfile partProfileInfo)
	{

		logger.debug("\n********** IN CasePartChldExtDaoImpl:  buildDataParam(chldExtInfo)" + "**********\n");

		//string to separate field values passed to the SP
		String fldSeperator = AppConstants.FLD_SEPARATOR;

		StringBuffer dataParam = new StringBuffer(" ");

		dataParam.append(partProfileInfo.getApplicationId().trim() + fldSeperator);

		dataParam.append(partProfileInfo.getApplicantId().trim() + fldSeperator);

		dataParam.append(partProfileInfo.getFamRel().trim() + fldSeperator);

		dataParam.append(partProfileInfo.getChldLiveWith().trim() + fldSeperator);

		dataParam.append(partProfileInfo.getNtsYr().trim() + fldSeperator);

		dataParam.append(partProfileInfo.getNumYr().trim() + fldSeperator);

		dataParam.append(partProfileInfo.getNumMo().trim() + fldSeperator);

		dataParam.append(partProfileInfo.getChldWdlck().trim() + fldSeperator);

		dataParam.append(partProfileInfo.getAffidCmplt().trim() + fldSeperator);

		dataParam.append(partProfileInfo.getAffidSt().trim() + fldSeperator);

		dataParam.append(partProfileInfo.getMothMariedBth().trim() + fldSeperator);

		dataParam.append(partProfileInfo.getMaritalStatus().trim() + fldSeperator);

		if (partProfileInfo.getMaritalStatusDt() != null)
		{
			dataParam.append(partProfileInfo.getMaritalStatusDtStr().trim() + fldSeperator);
		}
		else
			dataParam.append(AppConstants.DEFAULT_DATE + fldSeperator);

		dataParam.append(partProfileInfo.getRelOther().trim() + fldSeperator);
		
		dataParam.append(partProfileInfo.getPatnEst().trim() + fldSeperator);
		
		dataParam.append(partProfileInfo.getPatnTst().trim() + fldSeperator);
		
		dataParam.append(partProfileInfo.getHasIns().trim() + fldSeperator);

		logger.info("buildDataParam =: " + dataParam.toString().trim());

		return dataParam.toString().trim();
	}

}
