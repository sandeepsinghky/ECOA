/**
 * 
 */
package nc.dhhs.nccss.acts.dao.impl;

import java.sql.Types;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import nc.dhhs.nccss.acts.dao.PermissionDao;
import nc.dhhs.nccss.acts.dao.rowmap.PermissionRowMapper;
import nc.dhhs.nccss.acts.ecoa.beans.Permission;
import nc.dhhs.nccss.acts.ecoa.web.config.WebsiteConfiguration;

/**
 * @author Vijay Peddapalli
 *
 */
@Repository
public class PermissionDaoImpl implements PermissionDao
{

	protected final Logger	logger	= Logger.getLogger(PermissionDaoImpl.class);

	private JdbcTemplate	jdbcTemplate;

	private SimpleJdbcCall	procReadPerm;

	/**
	 * @param dataSource
	 */
	@Autowired
	public void setDataSource(DataSource dataSource)
	{

		logger.debug("\n********** IN PermissionDaoImpl: SETDATASOURCE**********\n");

		this.jdbcTemplate = new JdbcTemplate(dataSource);

		this.jdbcTemplate.setSkipUndeclaredResults(true);

		procReadPerm = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_R_USER_PERMISSION");

	}

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.dao.PermissionDao#retrievePermissions(long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Permission> retrievePermissions(long permissionId) throws Exception
	{
		logger.debug("\n********** IN PermissionDaoImpl: retrievePermissions(permissionId:" + permissionId + ")" + "**********\n");

		procReadPerm.declareParameters(new SqlParameter("ID_PERMISSION", Types.BIGINT)).returningResultSet("Perm", new PermissionRowMapper());

		SqlParameterSource in = new MapSqlParameterSource().addValue("ID_PERMISSION", permissionId);

		Map<String, Object> results = procReadPerm.execute(in);

		logger.info("\n********** Executed SP: FKWEB_R_USER_PERMISSION **********\n");

		return (List<Permission>) results.get("Perm");
	}

}
