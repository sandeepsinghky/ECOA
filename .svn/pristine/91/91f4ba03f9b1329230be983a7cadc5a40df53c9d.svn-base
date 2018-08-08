/**
 * 
 */
package nc.dhhs.nccss.acts.dao.rowmap;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import nc.dhhs.nccss.acts.ecoa.beans.Permission;

/**
 * @author Vijay Peddapalli
 *
 */
public class PermissionRowMapper implements RowMapper<Permission>
{

	protected final Logger logger = Logger.getLogger(PermissionRowMapper.class);

	/*
	 * (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
	 * int)
	 */
	@Override
	public Permission mapRow(ResultSet rs, int arg1) throws SQLException
	{
		logger.debug("IN: PermissionRowMapper- mapRow");

		Permission permission = new Permission();

		if (rs.getLong("ID_PERMISSION") != 0)
		{
			permission.setPermissionId(rs.getLong("ID_PERMISSION"));
		}

		if (rs.getString("NM_PERMISSION") != null && !rs.getString("NM_PERMISSION").equals(""))
		{
			permission.setPermissionName(rs.getString("NM_PERMISSION"));
		}

		if (rs.getString("DE_PERMISSION") != null && !rs.getString("DE_PERMISSION").equals(""))
		{
			permission.setPermissionDescription(rs.getString("DE_PERMISSION"));
		}

		return permission;
	}

}
