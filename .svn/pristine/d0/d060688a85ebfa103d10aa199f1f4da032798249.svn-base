/**
 * 
 */
package nc.dhhs.nccss.acts.dao.rowmap;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import nc.dhhs.nccss.acts.ecoa.beans.CodeLookUp;;

/**
 * @author Phani Konuru
 *
 */
public class CodeLookUpRowMapper implements RowMapper<CodeLookUp>
{

	protected final Logger logger = Logger.getLogger(CodeLookUpRowMapper.class);

	public CodeLookUpRowMapper()
	{

	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
	 * int)
	 */
	@Override
	public CodeLookUp mapRow(ResultSet rs, int rowNum) throws SQLException
	{
		logger.debug("IN: LookUpDetailsRowMapper- mapRow");

		CodeLookUp lookUpDetails = new CodeLookUp();

		if (rs.getString("CD_DTL_VALUE") != null && !rs.getString("CD_DTL_VALUE").equals(""))
		{
			lookUpDetails.setCodeId(rs.getString("CD_DTL_VALUE").trim());
		}

		if (rs.getString("DE_DTL_CODE_DESC") != null && !rs.getString("DE_DTL_CODE_DESC").toString().equals(""))
		{
			lookUpDetails.setCodeDesc(rs.getString("DE_DTL_CODE_DESC").trim());
		}

		return lookUpDetails;
	}

}
