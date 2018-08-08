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

import nc.dhhs.nccss.acts.dao.UserSignatureDAO;
import nc.dhhs.nccss.acts.dao.rowmap.UserSignatureRowMapper;
import nc.dhhs.nccss.acts.ecoa.beans.UserSignature;
import nc.dhhs.nccss.acts.ecoa.web.config.WebsiteConfiguration;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * @author Vijay Peddapalli
 *
 */
@Repository
public class UserSignatureDaoImpl implements UserSignatureDAO
{

	protected final Logger	logger	= Logger.getLogger(UserSignatureDaoImpl.class);

	private JdbcTemplate	jdbcTemplate;

	private SimpleJdbcCall	procInsertnUpdateSignature, procReadSignature, procReadAllSignatures;

	/**
	 * @param dataSource
	 */
	@Autowired
	public void setDataSource(DataSource dataSource)
	{

		logger.debug("\n********** IN UserSignatureDaoImpl: SETDATASOURCE**********\n");

		this.jdbcTemplate = new JdbcTemplate(dataSource);

		this.jdbcTemplate.setSkipUndeclaredResults(true);

		//this.jdbcTemplate.setResultsMapCaseInsensitive(true);

		procInsertnUpdateSignature = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_UPDATE_SIGNATURE");

		procReadSignature = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_R_SIGNATURE");

		procReadAllSignatures = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_R_ALL_SIGNATURE");

	}

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.dao.UserSignatureDAO#retrieveSignature(java.lang.
	 * String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UserSignature> retrieveSignature(String signId, String signType) throws SQLException

	{
		logger.debug("\n********** IN UserSignatureDaoImpl: retrieveSignature(" + signId + ", " + signType + ") **********\n");

		procReadSignature.declareParameters(new SqlParameter("ID_SIGNATURE", Types.CHAR), new SqlParameter("CD_SIGNATURE", Types.CHAR)).returningResultSet("userSignature", new UserSignatureRowMapper());

		SqlParameterSource in = new MapSqlParameterSource().addValue("ID_SIGNATURE", signId).addValue("CD_SIGNATURE", signType);

		Map<String, Object> results = procReadSignature.execute(in);

		return (List<UserSignature>) results.get("userSignature");

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.UserSignatureDAO#insertOrUpdateSignature(nc.dhhs.
	 * nccss.acts.ecoa.beans.UserSignature, java.lang.String, java.lang.String)
	 */
	public String insertOrUpdateSignature(UserSignature signBean, String userId, String dbOpType) throws SQLException

	{
		logger.debug("\n********** IN UserSignatureDaoImpl: insertOrUpdateSignature(signBean" + ", userId " + userId + ",dbOpType" + dbOpType + ")**********\n");

		String commonParam = buildCommonParam(dbOpType, userId, signBean.getSignType());

		String fldSeperator = AppConstants.FLD_SEPARATOR;

		String data = signBean.getSignId() + fldSeperator + signBean.getSignType() + fldSeperator + signBean.getNmSignF() + fldSeperator + signBean.getNmSignMI() + fldSeperator + signBean.getNmSignL() + fldSeperator + signBean.getHasChecked();

		String returnCode = ",,,,";

		procInsertnUpdateSignature.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR));

		SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", data).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

		Map<String, Object> results = procInsertnUpdateSignature.execute(in);

		returnCode = results.get("RETURNCODE").toString();

		return returnCode;
	}

	/**
	 * @param op
	 * @param ncid
	 * @param signType
	 * @return
	 */
	private String buildCommonParam(String op, String ncid, String signType)
	{
		logger.debug("\n********** IN UserSignatureDaoImpl: buildCommonParam(op: " + op + "," + "id: " + ncid + "," + "SignType:" + signType + ")**********\n");

		String commonParam = op + ",,," + signType + "," + ncid + "," + AppConstants.INTERFACE_CODE + ",,";

		logger.info("*****commonParam value: " + commonParam + "*****");

		return commonParam;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.UserSignatureDAO#retrieveAllSignatures(java.lang.
	 * String)
	 */
	@SuppressWarnings("unchecked")
	public List<UserSignature> retrieveAllSignatures(String signId) throws SQLException
	{
		logger.debug("\n********** IN UserSignatureDaoImpl: retrieveAllSignatures(" + signId + ") **********\n");

		procReadAllSignatures.declareParameters(new SqlParameter("ID_SIGNATURE", Types.CHAR)).returningResultSet("userSignature", new UserSignatureRowMapper());

		SqlParameterSource in = new MapSqlParameterSource().addValue("ID_SIGNATURE", signId);

		Map<String, Object> results = procReadAllSignatures.execute(in);

		return (List<UserSignature>) results.get("userSignature");
	}

}
