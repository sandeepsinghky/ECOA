/**
 * 
 */
package nc.dhhs.nccss.acts.dao.rowmap;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import nc.dhhs.nccss.acts.ecoa.beans.CasePartProfileExt;

/**
 * @author Phani Konuru
 *
 */
public class CasePartProfileExtRowMapper implements RowMapper<CasePartProfileExt>
{

	protected final Logger logger = Logger.getLogger(CasePartProfileExtRowMapper.class);

	public CasePartProfileExtRowMapper()
	{

	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
	 * int)
	 */
	@Override
	public CasePartProfileExt mapRow(ResultSet rs, int rowNum) throws SQLException
	{
		logger.debug("IN: CasePartAddrRowMapper- mapRow");

		CasePartProfileExt casePartProfileExt = new CasePartProfileExt();

		if (rs.getString("ID_APPLICATION") != null && !rs.getString("ID_APPLICATION").equals(""))
		{
			casePartProfileExt.setApplicationId(rs.getString("ID_APPLICATION").trim());
		}

		if (rs.getString("ID_APPLICANT") != null && !rs.getString("ID_APPLICANT").equals(""))
		{
			casePartProfileExt.setApplicantId(rs.getString("ID_APPLICANT").trim());
		}

		if (rs.getString("CD_PROFILE_EXT") != null && !rs.getString("CD_PROFILE_EXT").equals(""))
		{
			casePartProfileExt.setProfileExtType(rs.getString("CD_PROFILE_EXT").trim());
		}

		if (rs.getString("NM_CITY") != null && !rs.getString("NM_CITY").equals(""))
		{
			casePartProfileExt.setProfCty(rs.getString("NM_CITY").trim());
		}

		if (rs.getString("CD_STATE") != null && !rs.getString("CD_STATE").equals(""))
		{
			casePartProfileExt.setProfSt(rs.getString("CD_STATE").trim());
		}

		if (rs.getString("NM_COUNTY") != null && !rs.getString("NM_COUNTY").equals(""))
		{
			casePartProfileExt.setProfCnty(rs.getString("NM_COUNTY").trim());
		}

		if (rs.getString("NM_COUNTRY") != null && !rs.getString("NM_COUNTRY").equals(""))
		{
			casePartProfileExt.setProfCntry(rs.getString("NM_COUNTRY").trim());
		}

		return casePartProfileExt;
	}

}
