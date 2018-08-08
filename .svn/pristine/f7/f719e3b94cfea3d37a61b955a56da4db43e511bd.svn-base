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
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import nc.dhhs.nccss.acts.dao.CasePartContactDao;
import nc.dhhs.nccss.acts.dao.rowmap.CasePartContactRowMapper;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartContact;
import nc.dhhs.nccss.acts.ecoa.web.config.WebsiteConfiguration;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * @author Phani Konuru
 *
 */
@Repository
public class CasePartContactDaoImpl implements CasePartContactDao
{

	protected final Logger		logger		= Logger.getLogger(CasePartContactDaoImpl.class);

	private JdbcTemplate		jdbcTemplate;

	private SimpleJdbcCall		procCasePartContact1, procCasePartContact2, procCasePartContact3;

	private static final String	SCREEN_SP	= "APP_RESP";

	@Autowired
	//@Required
	public void setDataSource(DataSource dataSource)
	{

		logger.debug("\n********** IN CasePartContactDaoImpl: SETDATASOURCE**********\n");

		this.jdbcTemplate = new JdbcTemplate(dataSource);

		this.jdbcTemplate.setSkipUndeclaredResults(true);

		//this.jdbcTemplate.setResultsMapCaseInsensitive(true);

		procCasePartContact1 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_R_PART_ALL_CONTACT");

		procCasePartContact2 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_UPDATE_CONTACT");

		procCasePartContact3 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_R_PART_CONTACT");

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CasePartContact> getCasePartContact(String applicationId, String applicantId) throws SQLException
	{

		logger.debug("\n********** IN CasePartContactDaoImpl: getCasePartContact **********\n");

		Map<String, Object> results = null;

		logger.debug("field = " + AppConstants.APP_FIELD_NCID);

		procCasePartContact1.declareParameters(new SqlParameter("Application ID", Types.CHAR), new SqlParameter("Participant ID", Types.CHAR)).returningResultSet("partContact", new CasePartContactRowMapper());

		SqlParameterSource in = new MapSqlParameterSource().addValue("Application ID", applicationId).addValue("Participant ID", applicantId);

		results = procCasePartContact1.execute(in);

		return (List<CasePartContact>) results.get("partContact");
	}

	/**
	 * @param applicationId
	 * @param applicantId
	 * @param contactType
	 * @return
	 * @throws SQLException
	 */
	@Override
	public CasePartContact getCasePartContactType(String applicationId, String applicantId, String contactType) throws SQLException
	{
		logger.debug("\n********** IN CasePartContactDaoImpl: getCasePartContactType**********\n");

		procCasePartContact3.declareParameters(new SqlInOutParameter("Application ID", Types.CHAR), new SqlInOutParameter("Participant ID", Types.CHAR), new SqlInOutParameter("CD_CONTACT_TYPE", Types.CHAR), new SqlOutParameter("DE_CONTACT", Types.CHAR), new SqlOutParameter("CD_FAM_RELSHP", Types.CHAR), new SqlOutParameter("DT_CREATE", Types.DATE));

		SqlParameterSource in = new MapSqlParameterSource().addValue("Application ID", applicationId).addValue("Participant ID", applicantId).addValue("CD_CONTACT_TYPE", contactType);

		Map<String, Object> results = procCasePartContact3.execute(in);

		CasePartContact contactBean = null;

		if (results.get("DE_CONTACT") != null)
		{
			contactBean = new CasePartContact();
			contactBean.setApplicationId(applicationId);
			contactBean.setApplicantId(applicantId);
			contactBean.setContactType(contactType);
			contactBean.setContactInfo(results.get("DE_CONTACT").toString());

			logger.info("\n********** getCasePartContactType: contact info: " + contactBean.getContactInfo() + " **********\n");
		}

		return contactBean;
	}

	@Override
	public String createPartContact(CasePartContact contactBean, String ncid) throws SQLException
	{
		logger.debug("\n********** IN CasePartContactDaoImpl: createPartContact **********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_INSERT, ncid);

		String returnCode = ",,,,";

		String dataFields = buildDataParam(contactBean);

		logger.info("\n********** Users NCID: " + ncid + "**********\n");

		try
		{
			procCasePartContact2.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR));

			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

			Map<String, Object> results = procCasePartContact2.execute(in);

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
	public String updatePartContact(CasePartContact contactBean, String ncid) throws SQLException
	{
		logger.debug("\n********** IN CasePartContactDaoImpl: updatePartContact**********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_UPDATE, ncid);

		String returnCode = ",,,,";

		String dataFields = buildDataParam(contactBean);

		logger.info("User NCID: " + ncid);

		try
		{
			procCasePartContact2.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR));

			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

			Map<String, Object> results = procCasePartContact2.execute(in);

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
	public String deletePartContact(CasePartContact contactBean, String ncid) throws SQLException
	{
		logger.debug("\n********** IN CasePartContactDaoImpl:  deletePartAddr(addrBean,ncid: " + ncid + ")" + "**********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_DELETE, ncid.trim());

		String returnCode = ",,,,";

		String dataFields = buildDataParam(contactBean);

		try
		{
			procCasePartContact2.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlParameter("DATAFIELDS", Types.VARCHAR));

			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

			Map<String, Object> results = procCasePartContact2.execute(in);

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
	private String buildDataParam(CasePartContact contactBean)
	{

		logger.debug("\n********** IN CaseParticipantDaoImpl:  buildDataParam(partInfo)" + "**********\n");

		//string to separate field values passed to the SP
		String fldSeperator = AppConstants.FLD_SEPARATOR;

		StringBuffer dataParam = new StringBuffer(" ");

		dataParam.append(contactBean.getApplicationId().trim() + fldSeperator);

		dataParam.append(contactBean.getApplicantId().trim() + fldSeperator);

		dataParam.append(contactBean.getContactType().trim() + fldSeperator);

		dataParam.append("0" + fldSeperator);

		dataParam.append(contactBean.getContactInfo().trim() + fldSeperator);

		dataParam.append(contactBean.getFamRelshp().trim() + fldSeperator);

		if (contactBean.getCreateDtStr() != null) dataParam.append(contactBean.getCreateDtStr().trim() + fldSeperator);
		else
			dataParam.append(AppConstants.DEFAULT_DATE + fldSeperator);

		if (contactBean.getLastUpdatDtStr() != null)
			dataParam.append(contactBean.getLastUpdatDtStr().trim() + fldSeperator);
		else
			dataParam.append(AppConstants.DEFAULT_DATE + fldSeperator);

		if (contactBean.getLastUpdateTmStr() != null)
			dataParam.append(contactBean.getLastUpdateTmStr().trim() + fldSeperator);
		else
			dataParam.append(fldSeperator);

		logger.info("buildDataParam =: " + dataParam.toString().trim());

		return dataParam.toString().trim();
	}

}
