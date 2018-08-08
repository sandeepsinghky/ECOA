/**
 * 
 */
package nc.dhhs.nccss.acts.dao.rowmap;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import nc.dhhs.nccss.acts.ecoa.beans.UserAuthority;

/**
 * @author Vijay Peddapalli
 *
 */
public class UserAuthorityRowMapper implements RowMapper<UserAuthority>
{
	protected final Logger logger = Logger.getLogger(UserAuthorityRowMapper.class);

	/*
	 * (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
	 * int)
	 */
	@Override
	public UserAuthority mapRow(ResultSet rs, int arg1) throws SQLException
	{
		logger.debug("IN: UserAuthorityRowMapper- mapRow");

		UserAuthority userAuthority = new UserAuthority();

		if (rs.getLong("ID_USER") != 0)
		{
			userAuthority.setUserId(rs.getLong("ID_USER"));
		}

		if (rs.getLong("ID_ROLE") != 0)
		{
			userAuthority.setRoleId(rs.getLong("ID_ROLE"));
		}

		if (rs.getLong("ID_APPL") != 0)
		{
			userAuthority.setApplId(rs.getLong("ID_APPL"));
		}

		return userAuthority;
	}

}
