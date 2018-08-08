/**
 * 
 */
package nc.dhhs.nccss.acts.dao.rowmap;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import nc.dhhs.nccss.acts.ecoa.beans.CasePartAddress;

/**
 * @author Phani Konuru
 *
 */
public class CasePartAddrRowMapper implements RowMapper<CasePartAddress>
{

	protected final Logger logger = Logger.getLogger(CasePartAddrRowMapper.class);

	public CasePartAddrRowMapper()
	{

	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
	 * int)
	 */
	@Override
	public CasePartAddress mapRow(ResultSet rs, int rowNum) throws SQLException
	{
		logger.debug("IN: CasePartAddrRowMapper- mapRow");

		CasePartAddress casePartAddr = new CasePartAddress();

		if (rs.getString("ID_APPLICATION") != null && !rs.getString("ID_APPLICATION").equals(""))
		{
			casePartAddr.setApplicationId(rs.getString("ID_APPLICATION").trim());
		}

		if (rs.getString("ID_APPLICANT") != null && !rs.getString("ID_APPLICANT").equals(""))
		{
			casePartAddr.setApplicantId(rs.getString("ID_APPLICANT").trim());
		}

		if (rs.getString("CD_ADDRESS_TYPE") != null && !rs.getString("CD_ADDRESS_TYPE").equals(""))
		{
			casePartAddr.setAddrType(rs.getString("CD_ADDRESS_TYPE").trim());
		}

		if (rs.getString("AD_PART_LN_1") != null && !rs.getString("AD_PART_LN_1").equals(""))
		{
			casePartAddr.setAddrLn1(rs.getString("AD_PART_LN_1").trim());
		}

		if (rs.getString("AD_PART_LN_2") != null && !rs.getString("AD_PART_LN_2").equals(""))
		{
			casePartAddr.setAddrLn2(rs.getString("AD_PART_LN_2").trim());
		}

		if (rs.getString("AD_PART_CTY") != null && !rs.getString("AD_PART_CTY").equals(""))
		{
			casePartAddr.setAddrCty(rs.getString("AD_PART_CTY").trim());
		}

		if (rs.getString("AD_PART_ST") != null && !rs.getString("AD_PART_ST").equals(""))
		{
			casePartAddr.setAddrSt(rs.getString("AD_PART_ST").trim());
		}

		if (rs.getString("AD_PART_ST") != null && !rs.getString("AD_PART_ST").equals(""))
		{
			casePartAddr.setAddrSt(rs.getString("AD_PART_ST").trim());
		}

		if (rs.getString("AD_PART_ZIP_5") != null && !rs.getString("AD_PART_ZIP_5").equals(""))
		{
			casePartAddr.setAddrZip5(rs.getString("AD_PART_ZIP_5").trim());
		}

		if (rs.getString("AD_PART_ZIP_CARR") != null && !rs.getString("AD_PART_ZIP_CARR").equals(""))
		{
			casePartAddr.setAddrZipCarr(rs.getString("AD_PART_ZIP_CARR").trim());
		}

		if (rs.getString("AD_PART_COUNTY") != null && !rs.getString("AD_PART_COUNTY").equals(""))
		{
			casePartAddr.setAddrCnty(rs.getString("AD_PART_COUNTY").trim());
		}

		if (rs.getString("AD_PART_COUNTRY") != null && !rs.getString("AD_PART_COUNTRY").equals(""))
		{
			casePartAddr.setAddrCntry(rs.getString("AD_PART_COUNTRY").trim());
		}

		return casePartAddr;
	}

}
