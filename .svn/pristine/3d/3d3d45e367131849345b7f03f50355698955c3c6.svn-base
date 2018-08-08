/**
 * 
 */
package nc.dhhs.nccss.acts.dao.rowmap;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import nc.dhhs.nccss.acts.ecoa.beans.CaseParticipant;;

/**
 * @author Vijay Peddapalli
 *
 */
public class CaseParticipantRowMapper implements RowMapper<CaseParticipant>
{
	protected final Logger logger = Logger.getLogger(CaseParticipantRowMapper.class);

	public CaseParticipantRowMapper()
	{

	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
	 * int)
	 */
	@Override
	public CaseParticipant mapRow(ResultSet rs, int rowNum) throws SQLException
	{
		logger.debug("IN: CaseParticipantRowMapper- mapRow");

		CaseParticipant caseParticipant = new CaseParticipant();

		if (rs.getString("ID_APPLICATION") != null && !rs.getString("ID_APPLICATION").equals(""))
		{
			caseParticipant.setApplicationId(rs.getString("ID_APPLICATION").trim());
		}

		if (rs.getString("ID_APPLICANT") != null && !rs.getString("ID_APPLICANT").equals(""))
		{
			caseParticipant.setApplicantId(rs.getString("ID_APPLICANT").trim());
		}

		if (rs.getString("ID_PART") != null && !rs.getString("ID_PART").equals(""))
		{
			caseParticipant.setPartId(rs.getString("ID_PART").trim());
		}

		if (rs.getString("IN_ID_RESOLVED") != null && !rs.getString("IN_ID_RESOLVED").equals(""))
		{
			caseParticipant.setPartIdResolved(rs.getString("IN_ID_RESOLVED").trim());
		}

		if (rs.getString("NM_APPLICANT_L") != null && !rs.getString("NM_APPLICANT_L").equals(""))
		{
			caseParticipant.setApplicantLNm(rs.getString("NM_APPLICANT_L").trim());
		}

		if (rs.getString("NM_APPLICANT_F") != null && !rs.getString("NM_APPLICANT_F").equals(""))
		{
			caseParticipant.setApplicantFNm(rs.getString("NM_APPLICANT_F").trim());
		}

		if (rs.getString("NM_APPLICANT_M") != null && !rs.getString("NM_APPLICANT_M").equals(""))
		{
			caseParticipant.setApplicantMNm(rs.getString("NM_APPLICANT_M").trim());
		}

		if (rs.getString("NM_APPLICANT_SUFIX") != null && !rs.getString("NM_APPLICANT_SUFIX").equals(""))
		{
			caseParticipant.setApplicantSufix(rs.getString("NM_APPLICANT_SUFIX").trim());
		}

		if (rs.getString("NM_APPLICANT_MAIDN") != null && !rs.getString("NM_APPLICANT_MAIDN").equals(""))
		{
			caseParticipant.setApplicantMdnNm(rs.getString("NM_APPLICANT_MAIDN").trim());
		}

		if (rs.getString("CD_SEX") != null && !rs.getString("CD_SEX").equals(""))
		{
			caseParticipant.setApplicantSex(rs.getString("CD_SEX").trim());
		}

		if (rs.getString("DT_BRTH") != null && !rs.getString("DT_BRTH").equals(""))
		{
			caseParticipant.setBrthDt(rs.getDate("DT_BRTH"));
		}

		if (rs.getString("NB_SSN") != null && !rs.getString("NB_SSN").equals(""))
		{
			caseParticipant.setSsnNb(rs.getString("NB_SSN").trim());
		}

		if (rs.getString("CD_ETHNC_GRP") != null && !rs.getString("CD_ETHNC_GRP").equals(""))
		{
			caseParticipant.setEthncGrp(rs.getString("CD_ETHNC_GRP").trim());
		}

		if (rs.getString("CD_LANG_PREF") != null && !rs.getString("CD_LANG_PREF").equals(""))
		{
			caseParticipant.setLangPref(rs.getString("CD_LANG_PREF").trim());
		}

		if (rs.getString("CD_SPECIAL_ASSIST") != null && !rs.getString("CD_SPECIAL_ASSIST").equals(""))
		{
			caseParticipant.setSpecialAssist(rs.getString("CD_SPECIAL_ASSIST").trim());
		}

		if (rs.getString("DE_SPECIAL_ASSIST") != null && !rs.getString("DE_SPECIAL_ASSIST").equals(""))
		{
			caseParticipant.setSpecialAssistStr(rs.getString("DE_SPECIAL_ASSIST").trim());
		}

		if (rs.getString("IN_PROT_ORD") != null && !rs.getString("IN_PROT_ORD").equals(""))
		{
			caseParticipant.setProtOrd(rs.getString("IN_PROT_ORD").trim());
		}

		if (rs.getString("AM_SAL") != null && !rs.getString("AM_SAL").equals(""))
		{
			caseParticipant.setIncome(rs.getDouble("AM_SAL"));
		}

		if (rs.getString("DT_LST_UPD") != null && !rs.getString("DT_LST_UPD").equals(""))
		{
			caseParticipant.setLastUpdatDt(rs.getDate("DT_LST_UPD"));
		}

		if (rs.getString("NM_APPLICANT_ALIAS") != null && !rs.getString("NM_APPLICANT_ALIAS").equals(""))
		{
			caseParticipant.setApplicantAliasNm(rs.getString("NM_APPLICANT_ALIAS").trim());
		}

		if (rs.getString("CD_PARTICPANT_TYPE") != null && !rs.getString("CD_PARTICPANT_TYPE").equals(""))
		{
			caseParticipant.setPartType(rs.getString("CD_PARTICPANT_TYPE").trim());
		}

		if (rs.getString("CD_PARENT_GUARDIAN") != null && !rs.getString("CD_PARENT_GUARDIAN").equals(""))
		{
			caseParticipant.setPartPrntGrdian(rs.getString("CD_PARENT_GUARDIAN").trim());
		}
		
		if (rs.getString("CD_RES_COUNTY") != null && !rs.getString("CD_RES_COUNTY").equals(""))
		{
			caseParticipant.setResCnty(rs.getString("CD_RES_COUNTY").trim());
		}

		if (rs.getString("DE_ASSISTANCE_OTHER") != null && !rs.getString("DE_ASSISTANCE_OTHER").equals(""))
		{
			caseParticipant.setSpecialAssistOt(rs.getString("DE_ASSISTANCE_OTHER").trim());
		}

		return caseParticipant;
	}

}
