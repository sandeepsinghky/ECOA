/**
 * 
 */
package nc.dhhs.nccss.acts.dao.rowmap;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import nc.dhhs.nccss.acts.ecoa.beans.ThirdParty;

/**
 * @author Mallika Velagapudi
 *
 */
public class ThirdPartyRowMapper implements RowMapper<ThirdParty>
{

	protected final Logger logger = Logger.getLogger(ThirdPartyRowMapper.class);

	public ThirdPartyRowMapper()
	{

	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
	 * int)
	 */
	@Override
	public ThirdParty mapRow(ResultSet rs, int rowNum) throws SQLException
	{
		logger.debug("IN: thirdPartyRowMapper- mapRow");

		ThirdParty thirdParty = new ThirdParty();

		if (rs.getString("ID_APPLICATION") != null && !rs.getString("ID_APPLICATION").equals(""))
		{
			thirdParty.setApplicationId(rs.getString("ID_APPLICATION").trim());
		}

		if (rs.getString("ID_3PTY") != null && !rs.getString("ID_3PTY").equals(""))
		{
			thirdParty.setThirdPartyId(rs.getString("ID_3PTY").trim());
		}

		if (rs.getString("CD_3PTY_TYPE") != null && !rs.getString("CD_3PTY_TYPE").equals(""))
		{
			thirdParty.setThirdPartyType(rs.getString("CD_3PTY_TYPE").trim());

		}

		if (rs.getString("NM_3PTY") != null && !rs.getString("NM_3PTY").equals(""))
		{
			thirdParty.setThirdPartyNm(rs.getString("NM_3PTY").trim());

		}

		if (rs.getString("AD_3PTY_LN_1") != null && !rs.getString("AD_3PTY_LN_1").equals(""))
		{
			thirdParty.setAddressLn1(rs.getString("AD_3PTY_LN_1").trim());

		}

		if (rs.getString("AD_3PTY_ST") != null && !rs.getString("AD_3PTY_ST").equals(""))
		{
			thirdParty.setThirdPartyState(rs.getString("AD_3PTY_ST").trim());

		}

		if (rs.getString("AD_3PTY_CTY") != null && !rs.getString("AD_3PTY_CTY").equals(""))
		{
			thirdParty.setThirdPartyCity(rs.getString("AD_3PTY_CTY").trim());

		}

		if (rs.getString("NB_TEL_3PTY_ACD") != null && !rs.getString("NB_TEL_3PTY_ACD").equals(""))
		{
			thirdParty.setPhoneAreaCode(rs.getString("NB_TEL_3PTY_ACD").trim());

		}

		if (rs.getString("NB_TEL_3PTY_EXC") != null && !rs.getString("NB_TEL_3PTY_EXC").equals(""))
		{
			thirdParty.setPhoneExc(rs.getString("NB_TEL_3PTY_EXC").trim());

		}

		if (rs.getString("NB_TEL_3PTY_LN") != null && !rs.getString("NB_TEL_3PTY_LN").equals(""))
		{
			thirdParty.setPhoneLn(rs.getString("NB_TEL_3PTY_LN").trim());

		}

		if (rs.getString("NM_COUNTRY") != null && !rs.getString("NM_COUNTRY").equals(""))
		{
			thirdParty.setCountryNm(rs.getString("NM_COUNTRY").trim());

		}

		if (rs.getString("AD_3PTY_ZIP_5") != null && !rs.getString("AD_3PTY_ZIP_5").equals(""))
		{
			thirdParty.setThirdPartyZip5(rs.getString("AD_3PTY_ZIP_5").trim());

		}

		return thirdParty;

	}

}
