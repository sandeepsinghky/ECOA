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

import nc.dhhs.nccss.acts.dao.CaseCourtOrderDao;
import nc.dhhs.nccss.acts.dao.rowmap.CaseCourtOrderRowMapper;
import nc.dhhs.nccss.acts.ecoa.beans.CaseCourtOrder;
import nc.dhhs.nccss.acts.ecoa.web.config.WebsiteConfiguration;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * @author Mallika Velagapudi
 *
 */
@Repository
public class CaseCourtOrderDaoImpl implements CaseCourtOrderDao
{

	protected final Logger		logger		= Logger.getLogger(CaseCourtOrderDaoImpl.class);

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

		logger.debug("\n********** IN CaseCourtOrderDaoImpl: SETDATASOURCE**********\n");

		this.jdbcTemplate = new JdbcTemplate(dataSource);

		this.jdbcTemplate.setSkipUndeclaredResults(true);

		procCaseApplication1 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_R_COURT_ORDER");

		procCaseApplication2 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_UPDATE_COURT_ORD");

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.CaseCourtOrderDao#getCaseCourtOrderByPartId(java.
	 * lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CaseCourtOrder> getCaseCourtOrderByPartId(String applId, String applicantId) throws SQLException
	{

		logger.debug("\n********** IN CaseCourtOrderDaoImpl:getCaseCourtOrderByPartId(appId: " + applId + "," + "applicantId: " + applicantId + ")" + "**********\n");

		procCaseApplication1.declareParameters(new SqlParameter("ID_APPLICATION", Types.CHAR), new SqlParameter("ID_APPLICANT", Types.CHAR)).returningResultSet("courtOrderList", new CaseCourtOrderRowMapper());

		SqlParameterSource in = new MapSqlParameterSource().addValue("ID_APPLICATION", applId).addValue("ID_APPLICANT", applicantId);

		Map<String, Object> results = procCaseApplication1.execute(in);

		return (List<CaseCourtOrder>) results.get("courtOrderList");

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.CaseCourtOrderDao#getCaseCourtOrderByType(java.
	 * lang.String, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CaseCourtOrder> getCaseCourtOrderByType(String applId, String applicantId, String supportType)
			throws SQLException
	{

		logger.debug("\n********** IN CaseCourtOrderDaoImpl: getCaseCourtOrderByType(appId: " + applId + "," + "applicantId: " + applicantId + "supportType:" + supportType + "**********\n");

		procCaseApplication1.declareParameters(new SqlParameter("ID_APPLICATION", Types.CHAR), new SqlParameter("ID_APPLICANT", Types.CHAR), new SqlParameter("supportType", Types.CHAR)).returningResultSet("courtOrder", new CaseCourtOrderRowMapper());

		SqlParameterSource in = new MapSqlParameterSource().addValue("ID_APPLICATION", applId).addValue("ID_APPLICANT", applicantId);

		Map<String, Object> results = procCaseApplication1.execute(in);

		return (List<CaseCourtOrder>) results.get("courtOrder");

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.CaseCourtOrderDao#createCaseCourtOrder(nc.dhhs.
	 * nccss.acts.ecoa.beans.CaseCourtOrder, java.lang.String)
	 */
	@Override
	public String createCaseCourtOrder(CaseCourtOrder orderBean, String ncid) throws SQLException
	{
		logger.debug("\n********** IN CaseCourtOrderDaoImpl: createCaseCourtOrder(orderBean" + ", userId " + ncid + ")**********\n");
		String commonParam = buildCommonParam(AppConstants.OPERATION_INSERT, ncid);

		String returnCode = ",,,,";

		String dataFields = buildDataParam(orderBean);

		String orderNum = "";

		try
		{
			procCaseApplication2.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR));

			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

			Map<String, Object> results = procCaseApplication2.execute(in);

			logger.info("createCaseCourtOrder  returned code: " + results.get("RETURNCODE"));

			returnCode = results.get("RETURNCODE").toString();

			if ((results.get("RETURNCODE")).toString().substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))

			{
				String total = results.get("DATAFIELDS").toString();
				orderNum = results.get("DATAFIELDS").toString().substring(10, 20).trim();

				logger.info("orderId returned: " + orderNum + "...........");
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
	 * nc.dhhs.nccss.acts.dao.CaseCourtOrderDao#updateCaseCourtOrder(nc.dhhs.
	 * nccss.acts.ecoa.beans.CaseCourtOrder, java.lang.String)
	 */
	@Override
	public String updateCaseCourtOrder(CaseCourtOrder orderBean, String ncid) throws SQLException
	{
		logger.debug("\n********** IN CaseCourtOrderDaoImpl: createCaseCourtOrder(orderBean" + ", userId " + ncid + ")**********\n");
		String commonParam = buildCommonParam(AppConstants.OPERATION_UPDATE, ncid);

		String returnCode = ",,,,";

		String orderNum = "";

		String dataFields = buildDataParam(orderBean);

		logger.info("User NCID: " + ncid.trim());

		try
		{
			procCaseApplication2.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR));

			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

			Map<String, Object> results = procCaseApplication2.execute(in);

			logger.info("Update Case Application returned:" + results.get("RETURNCODE"));

			returnCode = results.get("RETURNCODE").toString();
			if ((results.get("RETURNCODE")).toString().substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))

			{
				String total = results.get("DATAFIELDS").toString();
				orderNum = results.get("DATAFIELDS").toString().substring(10, 20).trim();

				logger.info("orderId returned: " + orderNum + "...........");
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
	 * nc.dhhs.nccss.acts.dao.CaseCourtOrderDao#deleteCaseCourtOrder(nc.dhhs.
	 * nccss.acts.ecoa.beans.CaseCourtOrder, java.lang.String)
	 */
	@Override
	public String deleteCaseCourtOrder(CaseCourtOrder orderBean, String ncid) throws SQLException
	{
		logger.debug("\n********** IN CaseCourtOrderDaoImpl: deleteCaseCourtOrder(CaseCourtOrder" + ", userId " + ncid + ")**********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_DELETE, ncid.trim());

		String returnCode = ",,,,";

		String orderNum = "";

		orderBean.setApplicationId(orderBean.getApplicationId());

		logger.info("*****delete appId: " + orderBean.getApplicationId() + "*****");

		String dataFields = buildDataParam(orderBean);

		try
		{
			procCaseApplication2.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR));

			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

			Map<String, Object> results = procCaseApplication2.execute(in);

			returnCode = results.get("RETURNCODE").toString();

			if ((results.get("RETURNCODE")).toString().substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))

			{
				String total = results.get("DATAFIELDS").toString();
				orderNum = results.get("DATAFIELDS").toString().substring(10, 20).trim();

				logger.info("orderId returned: " + orderNum + "...........");
			}

			logger.info("deleteCaseCourtOrder returned:" + returnCode);
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
		logger.debug("\n********** CaseCourtOrderDaoImpl: buildCommonParam(op: " + op + "," + "ncid: " + ncid + ")**********\n");

		String commonParam = op + ",,," + SCREEN_SP + "," + ncid + "," + AppConstants.INTERFACE_CODE + ",,";

		logger.info("*****commonParam value: " + commonParam + "*****");
		return commonParam;
	}

	/**
	 * @param orderBean
	 * @return
	 */
	private String buildDataParam(CaseCourtOrder orderBean)
	{

		logger.debug("\n********** IN CaseCourtOrderDaoImpl: buildDataParam **********\n");

		StringBuffer dataParam = new StringBuffer(" ");

		//string to separate field values passed to the SP
		String fldSeparator = AppConstants.FLD_SEPARATOR;

		dataParam.append(orderBean.getApplicationId().trim() + fldSeparator);

		dataParam.append(orderBean.getOrderNm().trim() + fldSeparator);

		dataParam.append(orderBean.getApplicantId().trim() + fldSeparator);

		dataParam.append(orderBean.getSupportType().trim() + fldSeparator);

		dataParam.append(orderBean.getOrderAmount() + fldSeparator);

		dataParam.append(orderBean.getPayFreq().trim().replaceAll(fldSeparator, "") + fldSeparator);

		dataParam.append(orderBean.getDocketNm().trim().replaceAll(fldSeparator, "") + fldSeparator);

		if (orderBean.getOrderEffDtStr() != null) dataParam.append(orderBean.getOrderEffDtStr().trim() + fldSeparator);

		else
			dataParam.append(AppConstants.DEFAULT_DATE + fldSeparator);

		dataParam.append(orderBean.getCounty().trim() + fldSeparator);

		dataParam.append(orderBean.getState().trim() + fldSeparator);
		dataParam.append(orderBean.getAmtPastDue() + fldSeparator);
		dataParam.append(orderBean.getPayorNm().trim() + fldSeparator);
		dataParam.append(orderBean.getRecipientNm().trim() + fldSeparator);

		return dataParam.toString().trim();
	}

}
