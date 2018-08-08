/**
 * 
 */
package nc.dhhs.nccss.acts.dao.impl;

import java.sql.SQLException;
import java.sql.Types;
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

import nc.dhhs.nccss.acts.dao.WorkFlowDAO;
import nc.dhhs.nccss.acts.ecoa.web.config.WebsiteConfiguration;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * @author Vijay Peddapalli
 *
 */
@Repository
public class WorkFlowDaoImpl implements WorkFlowDAO
{

	protected final Logger	logger	= Logger.getLogger(WorkFlowDaoImpl.class);

	private JdbcTemplate	jdbcTemplate;

	private SimpleJdbcCall	procInsertnUpdateFlowProgress, procReadFlowProgress;

	@Autowired
	public void setDataSource(DataSource dataSource)
	{

		logger.debug("\n********** IN WorkFlowDaoImpl: SETDATASOURCE**********\n");

		this.jdbcTemplate = new JdbcTemplate(dataSource);

		this.jdbcTemplate.setSkipUndeclaredResults(true);

		//this.jdbcTemplate.setResultsMapCaseInsensitive(true);

		procInsertnUpdateFlowProgress = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_UPDATE_WORK_FLOW");

		procReadFlowProgress = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_R_WORK_FLOW");

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.WorkFlowDAO#retriveFlowProgressStep(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public Long retriveFlowProgressStep(String flowId, String flowType) throws SQLException
	{

		logger.debug("\n********** IN WorkFlowDaoImpl: retriveFlowProgressStep(" + flowId + ", " + flowType + ") **********\n");

		procReadFlowProgress.declareParameters(new SqlParameter("ID_FLOW", Types.CHAR), new SqlParameter("FLOW_TYPE", Types.CHAR), new SqlOutParameter("CD_PROGRESS", Types.BIGINT));

		SqlParameterSource in = new MapSqlParameterSource().addValue("FLOW_TYPE", flowType).addValue("ID_FLOW", flowId);

		Map<String, Object> results = procReadFlowProgress.execute(in);

		Long stepNum = (Long) results.get("CD_PROGRESS");

		logger.info("***** DB stepNum: " + stepNum + "*****");

		return stepNum;

	}

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.dao.WorkFlowDAO#updateFlowProgressStep(java.lang.
	 * String, java.lang.String, long, java.lang.String)
	 */
	@Override
	public String updateFlowProgressStep(String flowId, String flowType, long stepNum, String userId) throws SQLException
	{
		logger.debug("\n********** IN WorkFlowDaoImpl: updateFlowProgressStep(flowId: " + flowId + ", flowType: " + flowType + ", stepNum: " + stepNum + ", userId: " + userId + ")**********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_UPDATE, userId, flowType);

		String fldSeperator = AppConstants.FLD_SEPARATOR;

		String data = flowId + fldSeperator + flowType + fldSeperator + stepNum + fldSeperator;

		String returnCode = ",,,,";

		procInsertnUpdateFlowProgress.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR));

		SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", data).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

		Map<String, Object> results = procInsertnUpdateFlowProgress.execute(in);

		returnCode = results.get("RETURNCODE").toString();

		logger.info("***** updateFlowProgressStep returnCode: " + returnCode + "*****");

		return returnCode;
	}

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.dao.WorkFlowDAO#insertFlowProgressStep(java.lang.
	 * String, java.lang.String, long, java.lang.String)
	 */
	@Override
	public String insertFlowProgressStep(String flowId, String flowType, long stepNum, String userId) throws SQLException
	{
		logger.debug("\n********** IN WorkFlowDaoImpl: insertFlowProgressStep(flowId: " + flowId + ", flowType: " + flowType + ", stepNum: " + stepNum + ", userId: " + userId + ")**********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_INSERT, userId, flowType);

		String fldSeperator = AppConstants.FLD_SEPARATOR;

		String data = flowId + fldSeperator + flowType + fldSeperator + stepNum + fldSeperator;

		String returnCode = ",,,,";

		procInsertnUpdateFlowProgress.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR));

		SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", data).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

		Map<String, Object> results = procInsertnUpdateFlowProgress.execute(in);

		returnCode = results.get("RETURNCODE").toString();

		logger.info("***** insertFlowProgressStep returnCode: " + returnCode + "*****");

		return returnCode;
	}

	/**
	 * @param op
	 * @param ncid
	 * @param flowType
	 * @return
	 */
	private String buildCommonParam(String op, String ncid, String flowType)
	{
		logger.debug("\n********** IN WorkFlowDaoImpl: buildCommonParam(op: " + op + "," + "id: " + ncid + "," + "FlowType:" + flowType + ")**********\n");

		String commonParam = op + ",,," + flowType + "," + ncid + "," + AppConstants.INTERFACE_CODE + ",,";

		logger.info("*****commonParam value: " + commonParam + "*****");

		return commonParam;
	}

}
