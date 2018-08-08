/**
 * 
 */
package nc.dhhs.nccss.acts.dao.rowmap;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import nc.dhhs.nccss.acts.ecoa.beans.CasePartPaternty;

/**
 * @author Phani Konuru
 *
 */
public class CasePartPaterntyRowMapper implements RowMapper<CasePartPaternty>
{

	protected final Logger logger = Logger.getLogger(CasePartPaterntyRowMapper.class);

	public CasePartPaterntyRowMapper()
	{

	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
	 * int)
	 */
	@Override
	public CasePartPaternty mapRow(ResultSet rs, int rowNum) throws SQLException
	{
		logger.debug("IN: CasePartPaterntyRowMapper- mapRow");

		CasePartPaternty partPaternty = new CasePartPaternty();

		if (rs.getString("ID_APPLICATION") != null && !rs.getString("ID_APPLICATION").equals(""))
		{
			partPaternty.setApplicationId(rs.getString("ID_APPLICATION").trim());
		}

		if (rs.getString("ID_APPLICANT") != null && !rs.getString("ID_APPLICANt").equals(""))
		{
			partPaternty.setApplicantId(rs.getString("ID_APPLICANt").trim());
		}

		if (rs.getString("ID_APPLICANT_CHLD") != null && !rs.getString("ID_APPLICANT_CHLD").equals(""))
		{
			partPaternty.setApplicantChildId(rs.getString("ID_APPLICANT_CHLD").trim());
		}

		if (rs.getDate("DT_PATN_TEST") != null && !rs.getDate("DT_PATN_TEST").toString().equals(""))
		{
			partPaternty.setPatnTestDt(rs.getDate("DT_PATN_TEST"));

		}

		if (rs.getString("DE_PATN_RESULT") != null && !rs.getString("DE_PATN_RESULT").equals(""))
		{
			partPaternty.setPatnResult(rs.getString("DE_PATN_RESULT").trim());

		}

		if (rs.getString("CD_PATN_EST") != null && !rs.getString("CD_PATN_EST").equals(""))
		{
			partPaternty.setPatnEst(rs.getString("CD_PATN_EST").trim());

		}

		if (rs.getDate("DT_PATN_EST") != null && !rs.getDate("DT_PATN_EST").toString().equals(""))
		{
			partPaternty.setPatnEstDt(rs.getDate("DT_PATN_EST"));

		}

		if (rs.getString("CD_PATN_EST_CNTY") != null && !rs.getString("CD_PATN_EST_CNTY").equals(""))
		{
			partPaternty.setPatnEstCnty(rs.getString("CD_PATN_EST_CNTY").trim());

		}

		if (rs.getString("CD_PATN_EST_ST") != null && !rs.getString("CD_PATN_EST_ST").equals(""))
		{
			partPaternty.setPatnEstSt(rs.getString("CD_PATN_EST_ST").trim());

		}
		
		/*if (rs.getString("CD_PATN_TEST") != null && !rs.getString("CD_PATN_TEST").equals(""))
		{
			partPaternty.setIsPatnTstSel(rs.getString("CD_PATN_TEST").trim());

		}*/

		return partPaternty;

	}

}
