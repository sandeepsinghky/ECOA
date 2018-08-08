/**
 * 
 */
package nc.dhhs.nccss.acts.dao.rowmap;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import nc.dhhs.nccss.acts.ecoa.beans.EcoaUser;

/**
 * @author Vijay Peddapalli
 *
 */
public class EcoaUserRowMapper implements RowMapper<EcoaUser> {

	protected final Logger logger = Logger.getLogger(EcoaUserRowMapper.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
	 * int)
	 */
	@Override
	public EcoaUser mapRow(ResultSet rs, int rowNum) throws SQLException {
		logger.debug("IN: EcoaUserRowMapper- mapRow");

		EcoaUser ecoaUser = new EcoaUser();

		if (rs.getLong("ID_USER") != 0) {
			ecoaUser.setUserId(rs.getLong("ID_USER"));
		}

		if (rs.getString("ID_LOGIN") != null && !rs.getString("ID_LOGIN").equals("")) {
			ecoaUser.setLoginId(rs.getString("ID_LOGIN").trim());
		}

		if (rs.getString("IN_DIS_LOGIN") != null && !rs.getString("IN_DIS_LOGIN").equals("")) {
			ecoaUser.setUserStatus(rs.getString("IN_DIS_LOGIN").trim());
		}

		if (rs.getString("NM_LAST") != null && !rs.getString("NM_LAST").equals("")) {
			ecoaUser.setLastName(rs.getString("NM_LAST").trim());
		}

		if (rs.getString("NM_FIRST") != null && !rs.getString("NM_FIRST").equals("")) {
			ecoaUser.setFirstName(rs.getString("NM_FIRST").trim());
		}

		if (rs.getString("NM_MIDDLE") != null && !rs.getString("NM_MIDDLE").equals("")) {
			ecoaUser.setMiddleName(rs.getString("NM_MIDDLE").trim());
		}

		if (rs.getLong("ID_BUSINESS_AREA") != 0) {
			ecoaUser.setBusinessAreaId(rs.getLong("ID_BUSINESS_AREA"));
		}

		if (rs.getString("NM_NCID_LAST") != null && !rs.getString("NM_NCID_LAST").equals("")) {
			ecoaUser.setNcidLastNM(rs.getString("NM_NCID_LAST").trim());
		}

		if (rs.getString("NM_NCID_FIRST") != null && !rs.getString("NM_NCID_FIRST").equals("")) {
			ecoaUser.setNcidFirstNM(rs.getString("NM_NCID_FIRST").trim());
		}

		if (rs.getString("NM_NCID_MIDDLE") != null && !rs.getString("NM_NCID_MIDDLE").equals("")) {
			ecoaUser.setNcidMiddleNM(rs.getString("NM_NCID_MIDDLE").trim());
		}

		if (rs.getString("NCID_EMAIL") != null && !rs.getString("NCID_EMAIL").equals("")) {
			ecoaUser.setNcIdEmail(rs.getString("NCID_EMAIL").trim());
		}

		if (rs.getTimestamp("TS_LOGIN") != null && !rs.getTimestamp("TS_LOGIN").equals("")) {
			ecoaUser.setLoginTime(rs.getTimestamp("TS_LOGIN"));
		}

		if (rs.getTimestamp("TS_LOGOUT") != null && !rs.getTimestamp("TS_LOGOUT").equals("")) {
			ecoaUser.setLogoutTime(rs.getTimestamp("TS_LOGOUT"));
		}

		if (rs.getDate("DT_DEACTIVATED") != null && !rs.getDate("DT_DEACTIVATED").equals("")) {
			ecoaUser.setDateDeactivated(rs.getDate("DT_DEACTIVATED"));
		}

		if (rs.getString("ID_PART") != null && !rs.getString("ID_PART").equals("")) {
			ecoaUser.setPartId(rs.getString("ID_PART").trim());
		}

		if (rs.getString("CD_NCID_GUID") != null && !rs.getString("CD_NCID_GUID").equals("")) {
			ecoaUser.setNcIdGuid(rs.getString("CD_NCID_GUID").trim());
		}

		if (rs.getString("NB_ATTEMPT") != null && !rs.getString("NB_ATTEMPT").equals("")) {
			ecoaUser.setNbAttempt(rs.getDouble("NB_ATTEMPT"));

		}

		return ecoaUser;
	}

}
