/**
 * 
 */
package nc.dhhs.nccss.acts.dao.rowmap;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import nc.dhhs.nccss.acts.ecoa.beans.CasePartNCPExt;

/**
 * @author Mallika Velagapudi
 *
 */
public class CasePartNCPExtRowMapper implements RowMapper<CasePartNCPExt>
{

	protected final Logger logger = Logger.getLogger(CasePartNCPExtRowMapper.class);

	public CasePartNCPExtRowMapper()
	{

	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
	 * int)
	 */
	@Override
	public CasePartNCPExt mapRow(ResultSet rs, int rowNum) throws SQLException
	{
		logger.debug("IN: ncpExtDetailsRowMapper- mapRow");

		CasePartNCPExt ncpExtDetails = new CasePartNCPExt();

		if (rs.getString("ID_APPLICATION") != null && !rs.getString("ID_APPLICATION").equals(""))
		{
			ncpExtDetails.setApplicationId(rs.getString("ID_APPLICATION").trim());
		}

		if (rs.getString("ID_APPLICANT") != null && !rs.getString("ID_APPLICANT").equals(""))
		{
			ncpExtDetails.setApplicantId(rs.getString("ID_APPLICANT").trim());
		}

		if (rs.getString("NM_BIRTH_CITY") != null && !rs.getString("NM_BIRTH_CITY").equals(""))
		{
			ncpExtDetails.setBirthCityNm(rs.getString("NM_BIRTH_CITY").trim());

		}

		if (rs.getString("NM_BIRTH_STATE") != null && !rs.getString("NM_BIRTH_STATE").equals(""))
		{
			ncpExtDetails.setBirthStateNm(rs.getString("NM_BIRTH_STATE").trim());

		}

		if (rs.getString("NM_BIRTH_COUNTY") != null && !rs.getString("NM_BIRTH_COUNTY").equals(""))
		{
			ncpExtDetails.setBirthCountyNm(rs.getString("NM_BIRTH_COUNTY").trim());

		}

		if (rs.getString("NM_BIRTH_COUNTRY") != null && !rs.getString("NM_BIRTH_COUNTRY").equals(""))
		{
			ncpExtDetails.setBirthCountryNm(rs.getString("NM_BIRTH_COUNTRY").trim());

		}

		if (rs.getString("DE_PART_HGHT") != null && !rs.getString("DE_PART_HGHT").equals(""))
		{
			ncpExtDetails.setHeight(rs.getString("DE_PART_HGHT").trim());

		}
		if (rs.getString("DE_PART_WGHT") != null && !rs.getString("DE_PART_WGHT").equals(""))
		{
			ncpExtDetails.setWeight(rs.getDouble("DE_PART_WGHT"));

		}

		if (rs.getString("CD_EYE") != null && !rs.getString("CD_EYE").equals(""))
		{
			ncpExtDetails.setEyeColor(rs.getString("CD_EYE").trim());

		}
		if (rs.getString("CD_HAIR") != null && !rs.getString("CD_HAIR").equals(""))
		{
			ncpExtDetails.setHairColor(rs.getString("CD_HAIR").trim());

		}
		if (rs.getString("DE_PART_MARKS") != null && !rs.getString("DE_PART_MARKS").equals(""))
		{
			ncpExtDetails.setMarks(rs.getString("DE_PART_MARKS").trim());

		}
		if (rs.getString("NB_LIC_DRVR") != null && !rs.getString("NB_LIC_DRVR").equals(""))
		{
			ncpExtDetails.setDriverlicNum(rs.getString("NB_LIC_DRVR").trim());

		}
		if (rs.getString("CD_LIC_DRVR_ST") != null && !rs.getString("CD_LIC_DRVR_ST").equals(""))
		{
			ncpExtDetails.setLicenseState(rs.getString("CD_LIC_DRVR_ST").trim());

		}
		if (rs.getString("CD_MARITAL_STATUS") != null && !rs.getString("CD_MARITAL_STATUS").equals(""))
		{
			ncpExtDetails.setMaritalStatus(rs.getString("CD_MARITAL_STATUS").trim());

		}
		if (rs.getString("DT_MARRIED") != null && !rs.getString("DT_MARRIED").equals(""))
		{
			ncpExtDetails.setMaritualDt(rs.getDate("DT_MARRIED"));

		}

		if (rs.getString("CD_MLTRY_BRNCH") != null && !rs.getString("CD_MLTRY_BRNCH").equals(""))
		{
			ncpExtDetails.setMiltryBranch(rs.getString("CD_MLTRY_BRNCH").trim());

		}
		if (rs.getString("CD_MLTRY_STAT") != null && !rs.getString("CD_MLTRY_STAT").equals(""))
		{
			ncpExtDetails.setMiltryStatus(rs.getString("CD_MLTRY_STAT").trim());

		}
		if (rs.getString("DE_MLTRY_BAS") != null && !rs.getString("DE_MLTRY_BAS").equals(""))
		{
			ncpExtDetails.setMiltryStation(rs.getString("DE_MLTRY_BAS").trim());

		}
		if (rs.getString("DE_PAROLE") != null && !rs.getString("DE_PAROLE").equals(""))
		{
			ncpExtDetails.setParole(rs.getString("DE_PAROLE").trim());

		}

		if (rs.getString("DE_INCARCERATED") != null && !rs.getString("DE_INCARCERATED").equals(""))
		{
			ncpExtDetails.setIncarcerated(rs.getString("DE_INCARCERATED").trim());

		}
		if (rs.getString("DE_WORK_RELEASE") != null && !rs.getString("DE_WORK_RELEASE").equals(""))
		{
			ncpExtDetails.setWorkRelease(rs.getString("DE_WORK_RELEASE").trim());

		}
		if (rs.getString("DE_NOTE_TEXT") != null && !rs.getString("DE_NOTE_TEXT").equals(""))
		{
			ncpExtDetails.setAdditionalNote(rs.getString("DE_NOTE_TEXT").trim());

		}

		if (rs.getString("DT_ARREST") != null && !rs.getString("DT_ARREST").equals(""))
		{
			ncpExtDetails.setArrestDt(rs.getDate("DT_ARREST"));

		}

		if (rs.getString("CD_ARREST_CITY") != null && !rs.getString("CD_ARREST_CITY").equals(""))
		{
			ncpExtDetails.setArrestCity(rs.getString("CD_ARREST_CITY").trim());

		}

		if (rs.getString("CD_ARREST_STATE") != null && !rs.getString("CD_ARREST_STATE").equals(""))
		{
			ncpExtDetails.setArrestState(rs.getString("CD_ARREST_STATE").trim());

		}

		if (rs.getString("CD_LIC_DRVR") != null && !rs.getString("CD_LIC_DRVR").equals(""))
		{
			ncpExtDetails.setDriverLicStatus(rs.getString("CD_LIC_DRVR").trim());

		}

		if (rs.getString("CD_OWN_VEHICLE") != null && !rs.getString("CD_OWN_VEHICLE").equals(""))
		{
			ncpExtDetails.setOwnVehicleStatus(rs.getString("CD_OWN_VEHICLE").trim());

		}

		if (rs.getString("CD_MLTRY_STATUS") != null && !rs.getString("CD_MLTRY_STATUS").equals(""))
		{
			ncpExtDetails.setMltryStatus(rs.getString("CD_MLTRY_STATUS").trim());

		}
		if (rs.getString("CD_ARREST_STATUS") != null && !rs.getString("CD_ARREST_STATUS").equals(""))
		{
			ncpExtDetails.setArrestStatus(rs.getString("CD_ARREST_STATUS").trim());

		}
		if (rs.getString("CD_PAROLE_STATUS") != null && !rs.getString("CD_PAROLE_STATUS").equals(""))
		{
			ncpExtDetails.setParoleStatus(rs.getString("CD_PAROLE_STATUS").trim());

		}

		if (rs.getString("CD_INCARCERATED_STATUS") != null && !rs.getString("CD_INCARCERATED_STATUS").equals(""))
		{
			ncpExtDetails.setIncarStatus(rs.getString("CD_INCARCERATED_STATUS").trim());

		}
		if (rs.getString("CD_WORK_RELEASE_STATUS") != null && !rs.getString("CD_WORK_RELEASE_STATUS").equals(""))
		{
			ncpExtDetails.setWorkReleaseStatus(rs.getString("CD_WORK_RELEASE_STATUS").trim());

		}
		if (rs.getString("CD_EMPLOYMENT_STATUS") != null && !rs.getString("CD_EMPLOYMENT_STATUS").equals(""))
		{
			ncpExtDetails.setEmploymentStatus(rs.getString("CD_EMPLOYMENT_STATUS").trim());

		}
		if (rs.getString("CD_ORDER_STATUS") != null && !rs.getString("CD_ORDER_STATUS").equals(""))
		{
			ncpExtDetails.setOrderStatus(rs.getString("CD_ORDER_STATUS").trim());

		}

		return ncpExtDetails;

	}

}