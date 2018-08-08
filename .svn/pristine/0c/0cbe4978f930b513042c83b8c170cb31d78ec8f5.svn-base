/**
 * 
 */
package nc.dhhs.nccss.acts.dao.rowmap;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import nc.dhhs.nccss.acts.ecoa.beans.CasePartInsurance;

/**
 * @author Phani Konuru
 *
 */
public class CasePartInsuranceRowMapper implements RowMapper<CasePartInsurance>
{

	protected final Logger logger = Logger.getLogger(CasePartInsuranceRowMapper.class);

	public CasePartInsuranceRowMapper()
	{

	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
	 * int)
	 */
	@Override
	public CasePartInsurance mapRow(ResultSet rs, int rowNum) throws SQLException
	{
		logger.debug("IN: CasePartBenefitRowMapper- mapRow");

		CasePartInsurance insuranceRow = new CasePartInsurance();

		if (rs.getString("ID_APPLICATION") != null && !rs.getString("ID_APPLICATION").equals(""))
		{
			insuranceRow.setApplicationId(rs.getString("ID_APPLICATION").trim());
		}

		if (rs.getString("ID_APPLICANT") != null && !rs.getString("ID_APPLICANT").equals(""))
		{
			insuranceRow.setApplicantId(rs.getString("ID_APPLICANT").trim());
		}

		if (rs.getString("IN_INS") != null && !rs.getString("IN_INS").equals(""))
		{
			insuranceRow.setInIns(rs.getString("IN_INS").trim());

		}

		if (rs.getString("CD_INS_COVER_TYPE") != null && !rs.getString("CD_INS_COVER_TYPE").equals(""))
		{
			insuranceRow.setInsuranceType(rs.getString("CD_INS_COVER_TYPE").trim());

		}

		if (rs.getString("DE_INS_COVER_TYPE") != null && !rs.getString("DE_INS_COVER_TYPE").equals(""))
		{
			insuranceRow.setInsuranceTypeStr(rs.getString("DE_INS_COVER_TYPE").trim());

		}

		if (rs.getString("NM_INS_PROVIDER") != null && !rs.getString("NM_INS_PROVIDER").equals(""))
		{
			insuranceRow.setInsuranceProvider(rs.getString("NM_INS_PROVIDER").trim());

		}

		if (rs.getString("NM_PLCYHLDR_L") != null && !rs.getString("NM_PLCYHLDR_L").equals(""))
		{
			insuranceRow.setInsHolderL(rs.getString("NM_PLCYHLDR_L").trim());

		}

		if (rs.getString("NM_PLCYHLDR_F") != null && !rs.getString("NM_PLCYHLDR_F").equals(""))
		{
			insuranceRow.setInsHolderF(rs.getString("NM_PLCYHLDR_F").trim());

		}

		if (rs.getString("CD_PLCYHLDR_RELSHP") != null && !rs.getString("CD_PLCYHLDR_RELSHP").equals(""))
		{
			insuranceRow.setInsHolderRelshp(rs.getString("CD_PLCYHLDR_RELSHP").trim());

		}

		return insuranceRow;

	}

}
