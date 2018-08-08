/**
 * 
 */
package nc.dhhs.nccss.acts.dao.rowmap;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import nc.dhhs.nccss.acts.ecoa.beans.CaseApplication;

/**
 * @author Vijay Peddapalli
 *
 */
public class CaseApplicationRowMapper implements RowMapper<CaseApplication>
{

	protected final Logger logger = Logger.getLogger(CaseApplicationRowMapper.class);

	public CaseApplicationRowMapper()
	{

	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
	 * int)
	 */
	@Override
	public CaseApplication mapRow(ResultSet rs, int rowNum) throws SQLException
	{
		logger.debug("IN: CaseApplicationRowMapper- mapRow");

		CaseApplication caseApplication = new CaseApplication();

		if (rs.getString("ID_APPLICATION") != null && !rs.getString("ID_APPLICATION").equals(""))
		{
			caseApplication.setApplicationId(rs.getString("ID_APPLICATION").trim());
		}

		if (rs.getDate("DT_APPL_CREATE") != null && !rs.getDate("DT_APPL_CREATE").toString().equals(""))
		{
			caseApplication.setApplCreateDt(rs.getDate("DT_APPL_CREATE"));
		}

		if (rs.getDate("DT_APPL_SUBMIT") != null && !rs.getDate("DT_APPL_SUBMIT").toString().equals(""))
		{
			caseApplication.setApplSubmitDt(rs.getDate("DT_APPL_SUBMIT"));
		}

		if (rs.getString("CD_APPL_STATUS") != null && !rs.getString("CD_APPL_STATUS").equals(""))
		{
			caseApplication.setApplStatus(rs.getString("CD_APPL_STATUS").trim());
		}

		if (rs.getString("DE_APPL_STATUS") != null && !rs.getString("DE_APPL_STATUS").equals(""))
		{
			caseApplication.setApplStatusDesc(rs.getString("DE_APPL_STATUS").trim());
		}

		if (rs.getDate("DT_APPL_END") != null && !rs.getDate("DT_APPL_END").toString().equals(""))
		{
			caseApplication.setApplEndDt(rs.getDate("DT_APPL_END"));
		}

		if (rs.getDate("DT_CERT_SIGN") != null && !rs.getDate("DT_CERT_SIGN").toString().equals(""))
		{
			caseApplication.setApplCertSignDt(rs.getDate("DT_CERT_SIGN"));
		}

		if (rs.getString("ID_NCID") != null && !rs.getString("ID_NCID").equals(""))
		{
			caseApplication.setNcId(rs.getString("ID_NCID").trim());
		}

		if (rs.getString("ID_EMAIL") != null | !rs.getString("ID_EMAIL").equals(""))
		{
			caseApplication.setEmailId(rs.getString("ID_EMAIL").trim());
		}

		if (rs.getString("NM_APPLICANT_L") != null && !rs.getString("NM_APPLICANT_L").equals(""))
		{
			caseApplication.setLastName(rs.getString("NM_APPLICANT_L").trim());
		}

		if (rs.getString("NM_APPLICANT_F") != null && !rs.getString("NM_APPLICANT_F").equals(""))
		{
			caseApplication.setFirstName(rs.getString("NM_APPLICANT_F").trim());
		}

		if (rs.getString("NM_APPLICANT_M") != null && !rs.getString("NM_APPLICANT_M").equals(""))
		{
			caseApplication.setMiddleName(rs.getString("NM_APPLICANT_M").trim());
		}

		if (rs.getDate("DT_APPL_COMPLETED") != null && !rs.getDate("DT_APPL_COMPLETED").toString().equals(""))
		{
			caseApplication.setApplCmpltDt(rs.getDate("DT_APPL_COMPLETED"));
		}

		if (rs.getString("IN_FEE_RCVD") != null && !rs.getString("IN_FEE_RCVD").toString().equals(""))
		{
			caseApplication.setApplFeeRcvd(rs.getString("IN_FEE_RCVD").trim());
		}

		if (rs.getDate("DT_FEE_RCVD") != null && !rs.getDate("DT_FEE_RCVD").toString().equals(""))
		{
			caseApplication.setApplFeeRcvdDt(rs.getDate("DT_FEE_RCVD"));
		}

		if (rs.getString("ID_WRKR_FEE_RCVD_BY") != null && !rs.getString("ID_WRKR_FEE_RCVD_BY").equals(""))
		{
			caseApplication.setApplFeeRcvdBy(rs.getString("ID_WRKR_FEE_RCVD_BY").trim());
		}

		if (rs.getDate("DT_LST_UPD") != null && !rs.getDate("DT_LST_UPD").toString().equals(""))
		{
			caseApplication.setLastUpdatDt(rs.getDate("DT_LST_UPD"));
		}

		if (rs.getTime("TM_LST_UPD") != null && !rs.getTime("TM_LST_UPD").toString().equals(""))
		{
			caseApplication.setLastUpdateTm(rs.getTime("TM_LST_UPD"));
		}

		if (rs.getString("ID_APPLICANT") != null && !rs.getString("ID_APPLICANT").equals(""))
		{
			caseApplication.setApplicantId(rs.getString("ID_APPLICANT").trim());
		}

		if (rs.getDate("DT_APPLICANT_SIGN") != null && !rs.getDate("DT_APPLICANT_SIGN").toString().equals(""))
		{
			caseApplication.setApplicantSignDt(rs.getDate("DT_APPLICANT_SIGN"));
		}

		if (rs.getString("CD_APPL_COUNTY") != null && !rs.getString("CD_APPL_COUNTY").equals(""))
		{
			caseApplication.setApplCounty(rs.getString("CD_APPL_COUNTY").trim());
		}

		// won't work with setApplCountyDesc when null

		if (rs.getString("NM_COUNTY") != null && !rs.getString("NM_COUNTY").equals(""))
		{
			caseApplication.setApplCountyDesc(rs.getString("NM_COUNTY").trim());
		}

		if (rs.getString("IN_CNTC_VIA_EMAIL") != null && !rs.getString("IN_CNTC_VIA_EMAIL").equals(""))
		{
			caseApplication.setContactViaEmail(rs.getString("IN_CNTC_VIA_EMAIL").trim());
		}

		if (rs.getString("NM_ENFORC_STATE") != null && !rs.getString("NM_ENFORC_STATE").equals(""))
		{
			caseApplication.setEnforState(rs.getString("NM_ENFORC_STATE").trim());
		}

		if (rs.getString("NM_ENFORC_COUNTRY") != null && !rs.getString("NM_ENFORC_COUNTRY").equals(""))
		{
			caseApplication.setEnforCountry(rs.getString("NM_ENFORC_COUNTRY").trim());
		}

		if (rs.getString("NM_ENFORC_COMPANY") != null && !rs.getString("NM_ENFORC_COMPANY").equals(""))
		{
			caseApplication.setEnforCompanyName(rs.getString("NM_ENFORC_COMPANY").trim());
		}

		if (rs.getString("DE_NOTE_TEXT") != null && !rs.getString("DE_NOTE_TEXT").equals(""))
		{
			caseApplication.setApplNotes(rs.getString("DE_NOTE_TEXT").trim());
		}

		return caseApplication;
	}

}
