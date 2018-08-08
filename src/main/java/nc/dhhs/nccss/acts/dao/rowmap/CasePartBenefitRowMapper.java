/**
 * 
 */
package nc.dhhs.nccss.acts.dao.rowmap;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import nc.dhhs.nccss.acts.ecoa.beans.CasePartBenefit;

/**
 * @author Mallika Velagapudi
 *
 */
public class CasePartBenefitRowMapper implements RowMapper<CasePartBenefit>
{

	protected final Logger logger = Logger.getLogger(CasePartBenefitRowMapper.class);

	public CasePartBenefitRowMapper()
	{

	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
	 * int)
	 */
	@Override
	public CasePartBenefit mapRow(ResultSet rs, int rowNum) throws SQLException
	{
		logger.debug("IN: CasePartBenefitRowMapper- mapRow");

		CasePartBenefit benefitRow = new CasePartBenefit();

		if (rs.getString("ID_APPLICATION") != null && !rs.getString("ID_APPLICATION").equals(""))
		{
			benefitRow.setApplicationId(rs.getString("ID_APPLICATION").trim());
		}

		if (rs.getString("ID_APPLICANT") != null && !rs.getString("ID_APPLICANT").equals(""))
		{
			benefitRow.setApplicantId(rs.getString("ID_APPLICANT").trim());
		}

		if (rs.getString("CD_PART_TYPE") != null && !rs.getString("CD_PART_TYPE").equals(""))
		{
			benefitRow.setPartType(rs.getString("CD_PART_TYPE").trim());
		}

		if (rs.getString("CD_BENEFIT") != null && !rs.getString("CD_BENEFIT").equals(""))
		{
			benefitRow.setBenefitCode(rs.getString("CD_BENEFIT").trim());
		}

		if (rs.getString("NM_VETERAN_L") != null && !rs.getString("NM_VETERAN_L").equals(""))
		{
			benefitRow.setVeteranNmL(rs.getString("NM_VETERAN_L").trim());
		}

		if (rs.getString("NM_VETERAN_F") != null && !rs.getString("NM_VETERAN_F").equals(""))
		{
			benefitRow.setVeteranNmF(rs.getString("NM_VETERAN_F").trim());
		}

		return benefitRow;

	}

}
