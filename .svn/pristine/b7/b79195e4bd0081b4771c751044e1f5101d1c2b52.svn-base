/**
 * 
 */
package nc.dhhs.nccss.acts.dao.rowmap;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import nc.dhhs.nccss.acts.ecoa.beans.UserSignature;;

/**
 * @author Vijay Peddapalli
 *
 */
public class UserSignatureRowMapper implements RowMapper<UserSignature>
{

	protected final Logger logger = Logger.getLogger(UserSignatureRowMapper.class);

	/*
	 * (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
	 * int)
	 */
	@Override
	public UserSignature mapRow(ResultSet rs, int arg1) throws SQLException
	{
		logger.debug("IN: UserSignatureRowMapper- mapRow");

		UserSignature userSignature = new UserSignature();

		if (rs.getString("ID_SIGNATURE") != null && !rs.getString("ID_SIGNATURE").equals(""))
		{
			userSignature.setSignId(rs.getString("ID_SIGNATURE").toString());
		}

		if (rs.getString("CD_SIGNATURE") != null && !rs.getString("CD_SIGNATURE").equals(""))
		{
			userSignature.setSignType(rs.getString("CD_SIGNATURE").toString());
		}

		if (rs.getString("NM_SIGN_F") != null && !rs.getString("NM_SIGN_F").equals(""))
		{
			userSignature.setNmSignF(rs.getString("NM_SIGN_F").toString());
		}
		if (rs.getString("NM_SIGN_MI") != null && !rs.getString("NM_SIGN_MI").equals(""))
		{
			userSignature.setNmSignMI(rs.getString("NM_SIGN_MI").toString());
		}
		if (rs.getString("NM_SIGN_L") != null && !rs.getString("NM_SIGN_L").equals(""))
		{
			userSignature.setNmSignL(rs.getString("NM_SIGN_L").toString());
		}
		if (rs.getDate("DT_LST_UPD") != null && !rs.getDate("DT_LST_UPD").toString().equals(""))
		{
			userSignature.setDateSigned(rs.getDate("DT_LST_UPD"));
		}
		if (rs.getString("CD_HAVE_READ") != null && !rs.getString("CD_HAVE_READ").equals(""))
		{
			userSignature.setHasChecked(rs.getString("CD_HAVE_READ").toString());
		}

		return userSignature;
	}

}
