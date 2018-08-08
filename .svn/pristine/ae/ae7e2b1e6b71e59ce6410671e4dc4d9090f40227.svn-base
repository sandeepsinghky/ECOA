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

import nc.dhhs.nccss.acts.dao.UserAuthorityDAO;
import nc.dhhs.nccss.acts.dao.rowmap.UserAuthorityRowMapper;
import nc.dhhs.nccss.acts.ecoa.beans.UserAuthority;
import nc.dhhs.nccss.acts.ecoa.web.config.WebsiteConfiguration;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * @author Vijay Peddapalli
 *
 */
@Repository
public class UserAuthorityDaoImpl implements UserAuthorityDAO
{

	protected final Logger	logger	= Logger.getLogger(UserDaoImpl.class);

	private JdbcTemplate	jdbcTemplate;

	private SimpleJdbcCall	procReadUserAuthority, procCreateUserAuthority;

	/**
	 * @param dataSource
	 */
	@Autowired
	public void setDataSource(DataSource dataSource)
	{

		logger.debug("\n********** IN UserAuthorityDaoImpl: SETDATASOURCE**********\n");

		this.jdbcTemplate = new JdbcTemplate(dataSource);

		this.jdbcTemplate.setSkipUndeclaredResults(true);

		procReadUserAuthority = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_R_USER_AUTHORITY");

		procCreateUserAuthority = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_UPDATE_USR_AUTH ");

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.UserAuthorityDAO#retrieveUserAuthorities(long)
	 */
	@SuppressWarnings("unchecked")
	public List<UserAuthority> retrieveUserAuthorities(long userId) throws Exception
	{
		logger.debug("\n********** IN UserAuthorityDaoImpl: getUserAuthorities(userId:" + userId + ")" + "**********\n");

		procReadUserAuthority.declareParameters(new SqlParameter("ID_USER", Types.BIGINT)).returningResultSet("userAuth", new UserAuthorityRowMapper());

		SqlParameterSource in = new MapSqlParameterSource().addValue("ID_USER", userId);

		Map<String, Object> results = procReadUserAuthority.execute(in);

		logger.info("\n********** Executed SP: FKWEB_R_USER_AUTHORITY **********\n");

		return (List<UserAuthority>) results.get("userAuth");
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.UserAuthorityDAO#insertOrUpdateUserAuthority(nc.
	 * dhhs.nccss.acts.ecoa.beans.UserAuthority, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public String insertOrUpdateUserAuthority(UserAuthority authority, String ncid, String dbOpType) throws SQLException
	{
		logger.debug("\n********** IN UserAuthorityDaoImpl: insertOrUpdateUserAuthority(authorityBean" + ", userId: " + ncid + ",dbOpType: " + dbOpType + ")**********\n");

		String commonParam = buildCommonParam(dbOpType, authority);

		String fldSeperator = AppConstants.FLD_SEPARATOR;

		//String data = authority.getUserId() + "," + authority.getRoleId() + "," + authority.getApplId() + "," + authority.getUserIdLastUpdated() + ",";

		String data = authority.getUserId() + fldSeperator + authority.getRoleId() + fldSeperator + authority.getApplId() + fldSeperator + authority.getUserIdLastUpdated() + fldSeperator;

		String returnCode = ",,,,,,,,,,,";

		procCreateUserAuthority.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR));

		SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", data).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

		Map<String, Object> results = procCreateUserAuthority.execute(in);

		logger.info("\n********** Executed SP: FKWEB_UPDATE_USR_AUTH **********\n");

		returnCode = results.get("RETURNCODE").toString();

		logger.info("\n********** insertOrUpdateUserAuthority returned" + returnCode + ")**********\n");

		return returnCode;
	}

	/**
	 * @param op
	 * @param userAuthority
	 * @return
	 */
	private String buildCommonParam(String op, UserAuthority userAuthority)
	{
		logger.debug("\n********** IN UserDaoImpl: buildCommonParam(op: " + op + "," + "id: " + userAuthority.getUserIdLastUpdated() + "," + "Application Id:" + userAuthority.getApplId() + ")**********\n");

		String commonParam = op + ",,," + userAuthority.getApplId() + "," + userAuthority.getUserIdLastUpdated() + "," + AppConstants.INTERFACE_CODE + ",,";

		logger.info("*****commonParam value: " + commonParam + "*****");

		return commonParam;
	}

}
