/**
 * 
 */
package nc.dhhs.nccss.acts.dao.rowmap;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import nc.dhhs.nccss.acts.ecoa.beans.GuideLines;

/**
 * @author Mallika Velagapudi
 *
 */
public class GuideLinesRowMapper implements RowMapper<GuideLines>
{

	protected final Logger logger = Logger.getLogger(GuideLinesRowMapper.class);

	public GuideLinesRowMapper()
	{

	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
	 * int)
	 */
	@Override
	public GuideLines mapRow(ResultSet rs, int rowNum) throws SQLException
	{
		logger.debug("IN: GuideLinesRowMapper- mapRow");

		GuideLines guideLines = new GuideLines();

		if (rs.getString("AM_INCM_COMB") != null && !rs.getString("AM_INCM_COMB").equals(""))
		{
			guideLines.setGdlnIncome(rs.getDouble("AM_INCM_COMB"));
		}

		if (rs.getString("AM_PAYBL_1CHLD") != null && !rs.getString("AM_PAYBL_1CHLD").equals(""))
		{
			guideLines.setGdlnAmtChild1(rs.getDouble("AM_PAYBL_1CHLD"));
		}

		if (rs.getString("AM_PAYBL_2CHLD") != null && !rs.getString("AM_PAYBL_2CHLD").equals(""))
		{
			guideLines.setGdlnAmtChild2(rs.getDouble("AM_PAYBL_2CHLD"));

		}

		if (rs.getString("AM_PAYBL_3CHLD") != null && !rs.getString("AM_PAYBL_3CHLD").equals(""))
		{
			guideLines.setGdlnAmtChild3(rs.getDouble("AM_PAYBL_3CHLD"));

		}

		if (rs.getString("AM_PAYBL_4CHLD") != null && !rs.getString("AM_PAYBL_4CHLD").equals(""))
		{
			guideLines.setGdlnAmtChild4(rs.getDouble("AM_PAYBL_4CHLD"));

		}

		if (rs.getString("AM_PAYBL_5CHLD") != null && !rs.getString("AM_PAYBL_5CHLD").equals(""))
		{
			guideLines.setGdlnAmtChild5(rs.getDouble("AM_PAYBL_5CHLD"));

		}

		if (rs.getString("AM_PAYBL_6CHLD") != null && !rs.getString("AM_PAYBL_6CHLD").equals(""))
		{
			guideLines.setGdlnAmtChild6(rs.getDouble("AM_PAYBL_6CHLD"));

		}

		return guideLines;

	}

}
