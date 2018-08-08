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

import nc.dhhs.nccss.acts.dao.rowmap.RoleToPermissionRowMapper;
import nc.dhhs.nccss.acts.ecoa.beans.RoleToPermission;
import nc.dhhs.nccss.acts.ecoa.web.config.WebsiteConfiguration;

/**
 * @author Vijay Peddapalli
 *
 */
@Repository
public class RoleToPermissionDaoImpl implements nc.dhhs.nccss.acts.dao.RoleToPermissionDao
{

	protected final Logger	logger	= Logger.getLogger(RoleToPermissionDaoImpl.class);

	private JdbcTemplate	jdbcTemplate;

	private SimpleJdbcCall	procReadRoleToPerm;

	/**
	 * @param dataSource
	 */
	@Autowired
	public void setDataSource(DataSource dataSource)
	{

		logger.debug("\n********** IN RoleToPermissionDaoImpl: SETDATASOURCE**********\n");

		this.jdbcTemplate = new JdbcTemplate(dataSource);

		this.jdbcTemplate.setSkipUndeclaredResults(true);

		procReadRoleToPerm = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_R_USER_ROLE_TO_PERMISSION");

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.RoleToPermissionDao#retrieveRolePermissions(long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<RoleToPermission> retrieveRolePermissions(long roleId) throws Exception
	{
		logger.debug("\n********** IN RoleToPermissionDaoImpl: retrieveRolePermissions(roleId:" + roleId + ")" + "**********\n");

		procReadRoleToPerm.declareParameters(new SqlParameter("ID_ROLE", Types.BIGINT)).returningResultSet("rolesToPerm", new RoleToPermissionRowMapper());

		SqlParameterSource in = new MapSqlParameterSource().addValue("ID_ROLE", roleId);

		Map<String, Object> results = procReadRoleToPerm.execute(in);

		logger.info("\n********** Executed SP: FKWEB_R_USER_ROLE_TO_PERMISSION **********\n");

		return (List<RoleToPermission>) results.get("rolesToPerm");
	}

}
