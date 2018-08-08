/**
 * 
 */
package nc.dhhs.nccss.acts.dao.rowmap;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import nc.dhhs.nccss.acts.ecoa.beans.CasePartProfile;

/**
 * @author Phani Konuru
 *
 */
public class CasePartProfileRowMapper implements RowMapper<CasePartProfile>
{
	protected final Logger logger = Logger.getLogger(CasePartProfileRowMapper.class);

	public CasePartProfileRowMapper()
	{

	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
	 * int)
	 */
	@Override
	public CasePartProfile mapRow(ResultSet rs, int rowNum) throws SQLException
	{
		logger.debug("IN: CasePartProfileRowMapper- mapRow");

		CasePartProfile caseParticipant = new CasePartProfile();

		if (rs.getString("ID_APPLICATION") != null && !rs.getString("ID_APPLICATION").equals(""))
		{
			caseParticipant.setApplicationId(rs.getString("ID_APPLICATION").trim());
		}

		if (rs.getString("ID_APPLICANT") != null && !rs.getString("ID_APPLICANT").equals(""))
		{
			caseParticipant.setApplicantId(rs.getString("ID_APPLICANT").trim());
		}

		if (rs.getString("CD_FAM_RELSHP") != null && !rs.getString("CD_FAM_RELSHP").equals(""))
		{
			caseParticipant.setFamRel(rs.getString("CD_FAM_RELSHP").trim());
		}

		if (rs.getString("IN_CHLD_LIVE_WITH") != null && !rs.getString("IN_CHLD_LIVE_WITH").equals(""))
		{
			caseParticipant.setChldLiveWith(rs.getString("IN_CHLD_LIVE_WITH").trim());
		}

		if (rs.getString("NB_STAY_LENGTH_NT_YR") != null && !rs.getString("NB_STAY_LENGTH_NT_YR").equals(""))
		{
			caseParticipant.setNtsYr(rs.getString("NB_STAY_LENGTH_NT_YR").trim());
		}

		if (rs.getString("NB_STAY_LENGTH_YR") != null && !rs.getString("NB_STAY_LENGTH_YR").equals(""))
		{
			caseParticipant.setNumYr(rs.getString("NB_STAY_LENGTH_YR").trim());
		}

		if (rs.getString("NB_STAY_LENGTH_MO") != null && !rs.getString("NB_STAY_LENGTH_MO").equals(""))
		{
			caseParticipant.setNumMo(rs.getString("NB_STAY_LENGTH_MO").trim());
		}

		if (rs.getString("CD_WEDLCK") != null && !rs.getString("CD_WEDLCK").equals(""))
		{
			caseParticipant.setChldWdlck(rs.getString("CD_WEDLCK").trim());
		}

		if (rs.getString("IN_PARN_AFFID_COMP") != null && !rs.getString("IN_PARN_AFFID_COMP").equals(""))
		{
			caseParticipant.setAffidCmplt(rs.getString("IN_PARN_AFFID_COMP").trim());
		}

		if (rs.getString("AD_PARN_AFFID_ST") != null && !rs.getString("AD_PARN_AFFID_ST").equals(""))
		{
			caseParticipant.setAffidSt(rs.getString("AD_PARN_AFFID_ST").trim());
		}

		if (rs.getString("IN_MOTH_MARIED_BTH") != null && !rs.getString("IN_MOTH_MARIED_BTH").equals(""))
		{
			caseParticipant.setMothMariedBth(rs.getString("IN_MOTH_MARIED_BTH").trim());
		}

		if (rs.getString("CD_MARITAL_STATUS") != null && !rs.getString("CD_MARITAL_STATUS").equals(""))
		{
			caseParticipant.setMaritalStatus(rs.getString("CD_MARITAL_STATUS").trim());
		}

		if (rs.getString("DT_MARITAL_STATUS") != null && !rs.getString("DT_MARITAL_STATUS").equals(""))
		{
			caseParticipant.setMaritalStatusDt(rs.getDate("DT_MARITAL_STATUS"));
		}

		if (rs.getString("DE_RELATION_OTHER") != null && !rs.getString("DE_RELATION_OTHER").equals(""))
		{
			caseParticipant.setRelOther(rs.getString("DE_RELATION_OTHER").trim());
		}
		
		if (rs.getString("CD_PATN_EST") != null && !rs.getString("CD_PATN_EST").equals(""))
		{
			caseParticipant.setPatnEst(rs.getString("CD_PATN_EST").trim());
		}
		
		if (rs.getString("CD_PATN_TEST") != null && !rs.getString("CD_PATN_TEST").equals(""))
		{
			caseParticipant.setPatnTst(rs.getString("CD_PATN_TEST").trim());
		}
		
		if (rs.getString("CD_HAS_INSU") != null && !rs.getString("CD_HAS_INSU").equals(""))
		{
			caseParticipant.setHasIns(rs.getString("CD_HAS_INSU").trim());
		}


		return caseParticipant;
	}

}
