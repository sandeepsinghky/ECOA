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

import nc.dhhs.nccss.acts.dao.CaseParticipantDao;
import nc.dhhs.nccss.acts.dao.rowmap.CaseParticipantRowMapper;
import nc.dhhs.nccss.acts.ecoa.beans.CaseParticipant;
import nc.dhhs.nccss.acts.ecoa.web.config.WebsiteConfiguration;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * @author Vijay Peddapalli
 *
 */

@Repository
public class CaseParticipantDaoImpl implements CaseParticipantDao
{

	protected final Logger		logger		= Logger.getLogger(CaseParticipantDaoImpl.class);

	private JdbcTemplate		jdbcTemplate;

	private SimpleJdbcCall		procCaseApplication1, procCaseApplication2, procCaseApplication3;

	private static final String	SCREEN_SP	= "APP_APPLICANT";

	@Autowired
	//@Required
	public void setDataSource(DataSource dataSource)
	{

		logger.debug("\n********** IN CaseParticipantDaoImpl: SET DATASOURCE **********\n");

		this.jdbcTemplate = new JdbcTemplate(dataSource);

		this.jdbcTemplate.setSkipUndeclaredResults(true);

		//works
		procCaseApplication1 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_R_APPL_ROLE_PART");

		// works
		procCaseApplication2 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_UPDATE_PARTICIPANT");

		//works
		procCaseApplication3 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_R_APPLICANT_PART");

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
	 * nc.dhhs.nccss.acts.dao.CaseParticipantDao#getParticipantByPartType(java.
	 * lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CaseParticipant> getParticipantByPartType(String applId, String partType) throws SQLException
	{
		logger.debug("\n********** IN CaseParticipantDaoImpl:  getParticipantByPartType(appId: " + applId + "," + "partType: " + partType + ")" + "**********\n");

		procCaseApplication1.declareParameters(new SqlParameter("Application ID", Types.CHAR), new SqlParameter("Participant Type", Types.CHAR)).returningResultSet("caseParticipant", new CaseParticipantRowMapper());

		SqlParameterSource in = new MapSqlParameterSource().addValue("Application ID", applId).addValue("Participant Type", partType);

		Map<String, Object> results = procCaseApplication1.execute(in);

		return (List<CaseParticipant>) results.get("caseParticipant");
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.CaseParticipantDao#updateParticipant(nc.dhhs.nccss
	 * .acts.ecoa.beans.CaseParticipant, java.lang.String)
	 */
	@Override
	public String updateParticipant(CaseParticipant partInfo, String ncid) throws SQLException
	{
		logger.debug("\n********** IN CaseParticipantDaoImpl:  updateParticipant(applicantId: " + partInfo.getApplicantId() + ", ncid: " + ncid + ")" + "**********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_UPDATE, ncid.trim());
		String returnCode = ",,,,";

		String dataFields = buildDataParam(partInfo);

		try
		{
			procCaseApplication2.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR));

			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

			Map<String, Object> results = procCaseApplication2.execute(in);

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
	 * nc.dhhs.nccss.acts.dao.CaseParticipantDao#deleteParticipant(nc.dhhs.nccss
	 * .acts.ecoa.beans.CaseParticipant, java.lang.String)
	 */
	@Override
	public String deleteParticipant(CaseParticipant partInfo, String ncid) throws SQLException
	{
		logger.debug("\n********** IN CaseParticipantDaoImpl:  deleteParticipant(applicantId: " + partInfo.getApplicantId() + ", ncid: " + ncid + ")" + "**********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_DELETE, ncid.trim());

		String returnCode = ",,,,";

		String dataFields = buildDataParam(partInfo);

		try
		{
			procCaseApplication2.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR));

			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

			Map<String, Object> results = procCaseApplication2.execute(in);

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
	public String createParticipant(CaseParticipant partInfo, String ncid) throws SQLException
	{
		logger.debug("\n********** IN CaseParticipantDaoImpl: createParticipant(applicationId: " + partInfo.getApplicationId() + ", ncid: " + ncid + ")" + "**********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_INSERT, ncid.trim());
		String returnCode = ",,,,";
		String dataFields = buildDataParam(partInfo);
		String applicantId = "";

		try
		{
			procCaseApplication2.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR));

			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

			Map<String, Object> results = procCaseApplication2.execute(in);

			logger.info("createParticipant returned: " + results.get("RETURNCODE"));

			if ((results.get("RETURNCODE")).toString().substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
			{
				if (results.get("DATAFIELDS") != null)
				{
					logger.info("createParticipant: datafields returned from SP: " + results.get("DATAFIELDS").toString());
					if (results.get("DATAFIELDS").toString().substring(10, 20).trim() != null)
					{
						applicantId = results.get("DATAFIELDS").toString().substring(10, 20).trim();
						logger.info("createParticipant; ID_APPLICANT returned: " + applicantId + "...........");
					}
					else
					{
						logger.info("createParticipant: ID_APPLICANT returned: null");
					}
				}
				else
				{
					logger.info("createParticipant: ID_APPLICANT returned from SP: null");
				}
			}

		}

		catch (Exception ex)
		{
			logger.error(ex.getMessage());
		}
		return applicantId;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.CaseParticipantDao#getParticipantByPartId(java.
	 * lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CaseParticipant> getParticipantByPartId(String applId, String partId) throws SQLException
	{
		logger.debug("\n********** IN CaseParticipantDaoImpl:  getParticipantByPartId(applId:" + applId + ", partId: " + partId + ")" + "**********\n");

		procCaseApplication3.declareParameters(new SqlParameter("Application ID", Types.CHAR), new SqlParameter("Applicant ID", Types.CHAR)).returningResultSet("caseParticipant", new CaseParticipantRowMapper());

		SqlParameterSource in = new MapSqlParameterSource().addValue("Application ID", applId).addValue("Applicant ID", partId);

		Map<String, Object> results = procCaseApplication3.execute(in);

		return (List<CaseParticipant>) results.get("caseParticipant");
	}

	/**
	 * @param applBean
	 * @return
	 */
	private String buildDataParam(CaseParticipant partInfo)
	{

		logger.debug("\n********** IN CaseParticipantDaoImpl:  buildDataParam(partInfo)" + "**********\n");

		StringBuffer dataParam = new StringBuffer(" ");

		//string to separate field values passed to the SP
		String fldSeperator = AppConstants.FLD_SEPARATOR;

		dataParam.append(partInfo.getApplicationId().trim() + fldSeperator);

		dataParam.append(partInfo.getApplicantId().trim() + fldSeperator);

		dataParam.append(partInfo.getPartId().trim() + fldSeperator);

		dataParam.append(partInfo.getPartIdResolved().trim() + fldSeperator);

		dataParam.append(partInfo.getApplicantLNm().trim().replaceAll(fldSeperator, "") + fldSeperator);

		dataParam.append(partInfo.getApplicantFNm().trim().replaceAll(fldSeperator, "") + fldSeperator);

		dataParam.append(partInfo.getApplicantMNm().trim().replaceAll(fldSeperator, "") + fldSeperator);

		dataParam.append(partInfo.getApplicantSufix().trim() + fldSeperator);

		dataParam.append(partInfo.getApplicantMdnNm().trim().replaceAll(fldSeperator, "") + fldSeperator);

		dataParam.append(partInfo.getApplicantSex().trim() + fldSeperator);

		if (partInfo.getBrthDt() != null) dataParam.append(partInfo.getBrthDtStr().trim() + fldSeperator);
		else
			dataParam.append(AppConstants.DEFAULT_DATE + fldSeperator);

		dataParam.append(partInfo.getSsnNb().trim() + fldSeperator);

		dataParam.append(partInfo.getEthncGrp().trim() + fldSeperator);

		dataParam.append(partInfo.getLangPref().trim() + fldSeperator);

		dataParam.append(partInfo.getSpecialAssist().trim() + fldSeperator);

		dataParam.append(partInfo.getProtOrd().trim() + fldSeperator);

		dataParam.append(String.format("%.2f", partInfo.getIncome()) + fldSeperator);

		if (partInfo.getLastUpdatDtStr() != null) dataParam.append(partInfo.getLastUpdatDtStr().trim() + fldSeperator);
		else
			dataParam.append(AppConstants.DEFAULT_DATE + fldSeperator);

		dataParam.append(partInfo.getApplicantAliasNm().trim().replaceAll(fldSeperator, "") + fldSeperator);

		dataParam.append(partInfo.getPartType().trim() + fldSeperator);

		dataParam.append(partInfo.getPartPrntGrdian().trim() + fldSeperator);

		dataParam.append(partInfo.getResCnty().trim() + fldSeperator);

		dataParam.append(partInfo.getSpecialAssistOt().trim().replaceAll(fldSeperator, "") + fldSeperator);

		//logger.info("buildDataParam =: " + dataParam.toString().trim());

		return dataParam.toString().trim();
	}

}
