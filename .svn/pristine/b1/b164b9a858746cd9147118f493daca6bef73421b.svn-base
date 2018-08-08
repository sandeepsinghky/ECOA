/**
 * 
 */
package nc.dhhs.nccss.acts.dao.rowmap;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import nc.dhhs.nccss.acts.ecoa.beans.RoleToPermission;

/**
 * @author Vijay Peddapalli
 *
 */
public class RoleToPermissionRowMapper implements RowMapper<RoleToPermission>
{

	protected final Logger logger = Logger.getLogger(RoleToPermissionRowMapper.class);

	/*
	 * (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
	 * int)
	 */
	@Override
	public RoleToPermission mapRow(ResultSet rs, int arg1) throws SQLException
	{
		logger.debug("IN: RoleToPermissionRowMapper- mapRow");

		RoleToPermission roleToPermission = new RoleToPermission();

		if (rs.getLong("ID_ROLE") != 0)
		{
			roleToPermission.setRoleId(rs.getLong("ID_ROLE"));
		}

		if (rs.getLong("ID_PERMISSION") != 0)
		{
			roleToPermission.setPermissionId(rs.getLong("ID_PERMISSION"));
		}

		return roleToPermission;
	}

}
