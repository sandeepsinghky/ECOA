/**
 * 
 */
package nc.dhhs.nccss.acts.dao.rowmap;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import nc.dhhs.nccss.acts.ecoa.beans.CasePartEmp;

/**
 * @author Phani Konuru
 *
 */
public class CasePartEmpRowMapper implements RowMapper<CasePartEmp>
{

	protected final Logger logger = Logger.getLogger(CasePartEmpRowMapper.class);

	public CasePartEmpRowMapper()
	{

	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
	 * int)
	 */
	@Override
	public CasePartEmp mapRow(ResultSet rs, int rowNum) throws SQLException
	{
		logger.debug("IN: CasePartEmpRowMapper- mapRow");

		CasePartEmp partEmp = new CasePartEmp();

		if (rs.getString("ID_APPLICATION") != null && !rs.getString("ID_APPLICATION").equals(""))
		{
			partEmp.setApplicationId(rs.getString("ID_APPLICATION").trim());
		}

		if (rs.getString("ID_APPLICANT") != null && !rs.getString("ID_APPLICANt").equals(""))
		{
			partEmp.setApplicantId(rs.getString("ID_APPLICANt").trim());
		}

		if (rs.getString("ID_3PTY") != null && !rs.getString("ID_3PTY").equals(""))
		{
			partEmp.setThirdPartyId(rs.getString("ID_3PTY").trim());
		}

		if (rs.getDate("DT_EMPLM_END") != null && !rs.getDate("DT_EMPLM_END").toString().equals(""))
		{
			partEmp.setEmplmEndDt(rs.getDate("DT_EMPLM_END"));

		}

		if (rs.getString("DE_EMPLM_END_RSN") != null && !rs.getString("DE_EMPLM_END_RSN").equals(""))
		{
			partEmp.setEmplmEndReasn(rs.getString("DE_EMPLM_END_RSN").trim());

		}

		if (rs.getString("DE_OCCUPATION") != null && !rs.getString("DE_OCCUPATION").equals(""))
		{
			partEmp.setOcupation(rs.getString("DE_OCCUPATION").trim());

		}

		return partEmp;

	}

}
