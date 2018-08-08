/**
 * 
 */
package nc.dhhs.nccss.acts.dao.rowmap;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import nc.dhhs.nccss.acts.ecoa.beans.PreNcIdUser;

/**
 * @author Mallika Velagapudi
 *
 */
public class PreNcIdUserRowMapper implements RowMapper<PreNcIdUser> {

	protected final Logger logger = Logger.getLogger(PreNcIdUserRowMapper.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
	 * int)
	 */
	@Override
	public PreNcIdUser mapRow(ResultSet rs, int rowNum) throws SQLException {
		logger.debug("IN: PreNcIdUserRowMapper- mapRow");

		PreNcIdUser preNcIdUser = new PreNcIdUser();

		if (rs.getString("ID_PART") != null && !rs.getString("ID_PART").equals("")) {
			preNcIdUser.setId_part(rs.getString("ID_PART").trim());
		}

		if (rs.getString("ID_USER") != null && !rs.getString("ID_USER").equals("")) {
			preNcIdUser.setId_user(rs.getString("ID_USER").trim());
		}

		if (rs.getString("ID_PSWD") != null && !rs.getString("ID_PSWD").equals("")) {
			preNcIdUser.setId_pswd(rs.getString("ID_PSWD").trim());
		}

		if (rs.getString("IN_DIS_LOGIN") != null && !rs.getString("IN_DIS_LOGIN").equals("")) {
			preNcIdUser.setIn_dis_login(rs.getString("IN_DIS_LOGIN").trim());
		}

		if (rs.getString("NM_PART_L") != null && !rs.getString("NM_PART_L").equals("")) {
			preNcIdUser.setLastName(rs.getString("NM_PART_L").trim());
		}

		if (rs.getString("NM_PART_F") != null && !rs.getString("NM_PART_F").equals("")) {
			preNcIdUser.setFirstName(rs.getString("NM_PART_F").trim());
		}

		if (rs.getString("NM_PART_M") != null && !rs.getString("NM_PART_M").equals("")) {
			preNcIdUser.setMiddleName(rs.getString("NM_PART_M").trim());
		}

		if (rs.getString("AD_PART_LN_1") != null && !rs.getString("AD_PART_LN_1").equals("")) {
			preNcIdUser.setAd_part_ln_1(rs.getString("AD_PART_LN_1").trim());
		}

		if (rs.getString("AD_PART_LN_2") != null && !rs.getString("AD_PART_LN_2").equals("")) {
			preNcIdUser.setAd_part_ln2(rs.getString("AD_PART_LN_2").trim());
		}

		if (rs.getString("AD_PART_CTY") != null && !rs.getString("AD_PART_CTY").equals("")) {
			preNcIdUser.setCity(rs.getString("AD_PART_CTY").trim());
		}

		if (rs.getString("AD_PART_ST") != null && !rs.getString("AD_PART_ST").equals("")) {
			preNcIdUser.setState(rs.getString("AD_PART_ST").trim());
		}

		if (rs.getString("AD_PART_ZIP_5") != null && !rs.getString("AD_PART_ZIP_5").equals("")) {
			preNcIdUser.setAd_part_zip_5(rs.getString("AD_PART_ZIP_5").trim());
		}
		if (rs.getString("AD_PART_ZIP_4") != null && !rs.getString("AD_PART_ZIP_4").equals("")) {
			preNcIdUser.setAd_part_zip_4(rs.getString("AD_PART_ZIP_4").trim());
		}

		if (rs.getString("DT_DEACTIVATED") != null && !rs.getString("DT_DEACTIVATED").equals("")) {
			preNcIdUser.setDateDeactivated(rs.getDate("DT_DEACTIVATED"));
		}

		if (rs.getString("ID_PSWD") != null && !rs.getString("ID_PSWD").equals("")) {
			preNcIdUser.setId_pswd(rs.getString("ID_PSWD").trim());
		}
		if (rs.getLong("ID_USER_AUTH") != 0) {
			preNcIdUser.setId_user_auth(rs.getLong("ID_USER_AUTH"));
		}

		if (rs.getString("DT_LST_LOGIN") != null && !rs.getString("DT_LST_LOGIN").equals("")) {
			preNcIdUser.setDt_lst_login(rs.getDate("DT_LST_LOGIN"));
		}

		if (rs.getString("ID_PSWD_QSTN") != null && !rs.getString("ID_PSWD_QSTN").equals("")) {
			preNcIdUser.setPswd_qstn(rs.getString("ID_PSWD_QSTN"));
		}

		if (rs.getString("ID_PSWD_ANS") != null && !rs.getString("ID_PSWD_ANS").equals("")) {
			preNcIdUser.setPswd_ans(rs.getString("ID_PSWD_ANS"));
		}

		if (rs.getString("NB_GRACE_LOGIN") != null && !rs.getString("NB_GRACE_LOGIN").equals("")) {
			preNcIdUser.setNb_grace_login(rs.getInt("NB_GRACE_LOGIN"));
		}

		if (rs.getString("ID_E_MAIL") != null && !rs.getString("ID_E_MAIL").equals("")) {
			preNcIdUser.setEmailId(rs.getString("ID_E_MAIL").trim());
		}

		if (rs.getString("NB_TEL") != null && !rs.getString("NB_TEL").equals("")) {
			preNcIdUser.setNb_tel(rs.getString("NB_TEL").trim());
		}

		return preNcIdUser;

	}

}
