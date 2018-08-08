/**
 * 
 */
package nc.dhhs.nccss.acts.dao.rowmap;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import nc.dhhs.nccss.acts.ecoa.beans.CasePartContact;

/**
 * @author Phani Konuru
 *
 */
public class CasePartContactRowMapper implements RowMapper<CasePartContact>
{

	protected final Logger logger = Logger.getLogger(CasePartContactRowMapper.class);

	public CasePartContactRowMapper()
	{

	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
	 * int)
	 */
	@Override
	public CasePartContact mapRow(ResultSet rs, int rowNum) throws SQLException
	{
		logger.debug("IN: CasePartAddrRowMapper- mapRow");

		CasePartContact casePartContact = new CasePartContact();

		if (rs.getString("ID_APPLICATION") != null && !rs.getString("ID_APPLICATION").equals(""))
		{
			casePartContact.setApplicationId(rs.getString("ID_APPLICATION").trim());
		}

		if (rs.getString("ID_APPLICANT") != null && !rs.getString("ID_APPLICANT").equals(""))
		{
			casePartContact.setApplicantId(rs.getString("ID_APPLICANT").trim());
		}

		if (rs.getString("CD_CONTACT_TYPE") != null && !rs.getString("CD_CONTACT_TYPE").equals(""))
		{
			casePartContact.setContactType(rs.getString("CD_CONTACT_TYPE").trim());
		}

		if (rs.getString("DE_CONTACT") != null && !rs.getString("DE_CONTACT").equals(""))
		{
			casePartContact.setContactInfo(rs.getString("DE_CONTACT").trim());
		}

		if (rs.getString("CD_FAM_RELSHP") != null && !rs.getString("CD_FAM_RELSHP").equals(""))
		{
			casePartContact.setFamRelshp(rs.getString("CD_FAM_RELSHP").trim());
		}

		if (rs.getDate("DT_CREATE") != null && !rs.getDate("DT_CREATE").toString().equals(""))
		{
			casePartContact.setCreateDt(rs.getDate("DT_CREATE"));
		}

		//if (rs.getDate("DT_LAST_UPDATE") != null && !rs.getDate("DT_LAST_UPDATE").toString().equals(""))
		//{
		//	casePartContact.setLastUpdatDt(rs.getDate("DT_LAST_UPDATE"));
		//}

		//if (rs.getTime("TM_LAST_UPDATE") != null && !rs.getTime("TM_LAST_UPDATE").toString().equals(""))
		//{
		//	casePartContact.setLastUpdateTm(rs.getTime("TM_LAST_UPDATE"));
		//}

		return casePartContact;
	}

}
