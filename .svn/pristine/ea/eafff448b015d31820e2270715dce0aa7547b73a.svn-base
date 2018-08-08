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

import nc.dhhs.nccss.acts.dao.CasePartOrderDao;
import nc.dhhs.nccss.acts.dao.rowmap.CasePartOrderRowMapper;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartOrder;
import nc.dhhs.nccss.acts.ecoa.web.config.WebsiteConfiguration;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * @author Mallika Velagapudi
 *
 */

// This dao works on ACTW_PART_ORDER table, which stores the involved children
// list for the specific ordertype..

@Repository
public class CasePartOrderDaoImpl implements CasePartOrderDao
{

	protected final Logger		logger		= Logger.getLogger(CasePartOrderDaoImpl.class);

	private JdbcTemplate		jdbcTemplate;

	private SimpleJdbcCall		procCaseApplication1, procCaseApplication2;

	private static final String	SCREEN_SP	= "APP_RESP";

	@Autowired
	// @Required
	public void setDataSource(DataSource dataSource)
	{

		logger.debug("\n********** IN CasePartOrderDaoImpl: SETDATASOURCE**********\n");

		this.jdbcTemplate = new JdbcTemplate(dataSource);

		this.jdbcTemplate.setSkipUndeclaredResults(true);

		procCaseApplication1 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_R_ORDER_CHILDREN");

		procCaseApplication2 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_UPDATE_PART_ORDER");

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.CasePartOrderDao#getCasePartOrderByOrderNum(java.
	 * lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	// this SP gets children list linked to NCP in the selected/provided order
	// type from ACTW_PART_ORDER table.

	public List<CasePartOrder> getCasePartOrderByOrderNum(String orderNum) throws SQLException
	{

		logger.debug("\n********** IN CasePartOrderDaoImpl: getCasePartOrderByOrderNum(orderNum:" + orderNum + ")" + "**********\n");

		procCaseApplication1.declareParameters(new SqlParameter("NB_ORDER", Types.CHAR)).returningResultSet("orderChildren", new CasePartOrderRowMapper());

		SqlParameterSource in = new MapSqlParameterSource().addValue("NB_ORDER", orderNum);

		Map<String, Object> results = procCaseApplication1.execute(in);

		return (List<CasePartOrder>) results.get("orderChildren");
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.CasePartOrderDao#createCasePartOrder(nc.dhhs.nccss
	 * .acts.ecoa.beans.CasePartOrder, java.lang.String)
	 */
	@Override
	public String createCasePartOrder(CasePartOrder orderChildBean, String ncid) throws SQLException
	{
		logger.debug("\n********** IN CasePartOtherDaoImpl: createCasePartOrder(CasePartOrder: " + "userid: " + ncid + ")" + "**********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_INSERT, ncid);

		String returnCode = ",,,,";

		String dataFields = buildDataParam(orderChildBean);

		String applId = "";

		try
		{
			procCaseApplication2.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR));

			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

			Map<String, Object> results = procCaseApplication2.execute(in);

			logger.info(" createCasePartOTher  returned code: " + results.get("RETURNCODE"));

			dataFields = results.get("DATAFIELDS").toString();
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
	 * nc.dhhs.nccss.acts.dao.CasePartOrderDao#deleteCasePartOrder(nc.dhhs.nccss
	 * .acts.ecoa.beans.CasePartOrder, java.lang.String)
	 */
	@Override
	public String deleteCasePartOrder(CasePartOrder orderChildBean, String ncid) throws SQLException
	{
		logger.debug("\n********** IN CasePartOtherDaoImpl: getCasePartOtherByPartId(CasePartOrder: " + "userid: " + ncid + ")" + "**********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_DELETE, ncid);

		String returnCode = ",,,,";

		logger.info("*****delete appId: " + orderChildBean.getOrderNm() + "*****");

		String dataFields = buildDataParam(orderChildBean);

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
	 * @param orderChildBean
	 * @return
	 */
	private String buildDataParam(CasePartOrder orderChildBean)
	{

		logger.debug("\n********** IN CasePartApplicationDaoImpl: buildDataParam **********\n");

		StringBuffer dataParam = new StringBuffer(" ");

		//string to separate field values passed to the SP
		String fldSeparator = AppConstants.FLD_SEPARATOR;

		dataParam.append(orderChildBean.getApplicationId().trim() + fldSeparator);

		dataParam.append(orderChildBean.getOrderNm().trim() + fldSeparator);

		dataParam.append(orderChildBean.getApplicantId().trim() + fldSeparator);

		dataParam.append(orderChildBean.getChildApplicantId().trim() + fldSeparator);

		logger.info("********** buildDataParam value is: " + dataParam.toString() + "**********");

		return dataParam.toString().trim();
	}

}