/**
 * 
 */
package nc.dhhs.nccss.acts.dao.rowmap;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import nc.dhhs.nccss.acts.ecoa.beans.CasePartIncome;

/**
 * @author Phani Konuru
 *
 */
public class CasePartIncomeRowMapper implements RowMapper<CasePartIncome>
{

	protected final Logger logger = Logger.getLogger(CasePartIncomeRowMapper.class);

	public CasePartIncomeRowMapper()
	{

	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
	 * int)
	 */
	@Override
	public CasePartIncome mapRow(ResultSet rs, int rowNum) throws SQLException
	{
		logger.debug("IN: CasePartIncomeRowMapper- mapRow");

		CasePartIncome casePartIncome = new CasePartIncome();

		if (rs.getString("ID_APPLICATION") != null && !rs.getString("ID_APPLICATION").equals(""))
		{
			casePartIncome.setApplicationId(rs.getString("ID_APPLICATION").trim());
		}

		if (rs.getString("ID_APPLICANT") != null && !rs.getString("ID_APPLICANT").equals(""))
		{
			casePartIncome.setApplicantId(rs.getString("ID_APPLICANT").trim());
		}

		if (rs.getString("CD_INCOME_SRC") != null && !rs.getString("CD_INCOME_SRC").equals(""))
		{
			casePartIncome.setIncomeSrc(rs.getString("CD_INCOME_SRC").trim());
		}

		if (rs.getString("DE_INCOME_SRC") != null && !rs.getString("DE_INCOME_SRC").equals(""))
		{
			casePartIncome.setIncomeSrcDesc(rs.getString("DE_INCOME_SRC").trim());
		}

		if (rs.getString("AM_INCOME") != null && !rs.getString("AM_INCOME").equals(""))
		{
			casePartIncome.setIncomeAmt(rs.getDouble("AM_INCOME"));
		}

		return casePartIncome;
	}

}
