/**
 * 
 */
package nc.dhhs.nccss.acts.dao.rowmap;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import nc.dhhs.nccss.acts.ecoa.beans.UserProfile;

/**
 * @author Vijay Peddapalli
 *
 */
public class UserProfileRowMapper implements RowMapper<UserProfile> {

	protected final Logger logger = Logger.getLogger(UserProfileRowMapper.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
	 * int)
	 */
	@Override
	public UserProfile mapRow(ResultSet rs, int arg1) throws SQLException {

		logger.debug("IN: UserProfileRowMapper- mapRow");

		UserProfile userProfile = new UserProfile();

		if (rs.getString("NM_FIRST") != null && !rs.getString("NM_FIRST").equals("")) {
			userProfile.setFirstName(rs.getString("NM_FIRST").trim());
		}

		if (rs.getString("NM_MIDDLE") != null && !rs.getString("NM_MIDDLE").equals("")) {
			userProfile.setMidName(rs.getString("NM_MIDDLE").trim());
		}
		if (rs.getString("NM_LAST") != null && !rs.getString("NM_LAST").equals("")) {
			userProfile.setLastName(rs.getString("NM_LAST").trim());
		}
		if (rs.getString("AD_USR_LN_1") != null && !rs.getString("AD_USR_LN_1").equals("")) {
			userProfile.setAddrLine1(rs.getString("AD_USR_LN_1").trim());
		}
		if (rs.getString("AD_USR_LN_2") != null && !rs.getString("AD_USR_LN_2").toString().equals("")) {
			userProfile.setAddrLine2(rs.getString("AD_USR_LN_2").trim());
		}

		if (rs.getString("AD_USR_CTY") != null && !rs.getString("AD_USR_CTY").toString().equals("")) {
			userProfile.setCity(rs.getString("AD_USR_CTY").trim());
		}
		if (rs.getString("AD_USR_ST") != null && !rs.getString("AD_USR_ST").toString().equals("")) {
			userProfile.setState(rs.getString("AD_USR_ST").trim());
		}
		if (rs.getString("AD_USR_ZIP_5") != null && !rs.getString("AD_USR_ZIP_5").toString().equals("")) {
			userProfile.setZipCode(rs.getString("AD_USR_ZIP_5").trim());
		}

		if (rs.getString("NB_PHONE") != null && !rs.getString("NB_PHONE").toString().equals("")) {
			userProfile.setPhone(rs.getString("NB_PHONE").trim());
		}

		if (rs.getString("AD_EMAIL") != null && !rs.getString("AD_EMAIL").toString().equals("")) {
			userProfile.setEmail(rs.getString("AD_EMAIL").trim());

		}

		return userProfile;

	}
}
