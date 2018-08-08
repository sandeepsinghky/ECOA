/**
 * 
 */
package nc.dhhs.nccss.acts.dao.rowmap;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import nc.dhhs.nccss.acts.ecoa.beans.Participant;

/**
 * @author Mallika Velagapudi
 *
 */
public class ParticipantRowMapper implements RowMapper<Participant>
{

	protected final Logger logger = Logger.getLogger(ParticipantRowMapper.class);

	public ParticipantRowMapper()
	{

	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
	 * int)
	 */
	@Override
	public Participant mapRow(ResultSet rs, int rowNum) throws SQLException
	{
		logger.debug("IN: ParticipantRowMapper- mapRow");

		Participant participant = new Participant();

		if (rs.getString("ID_PART") != null && !rs.getString("ID_PART").equals(""))
		{
			participant.setIdPart(rs.getString("ID_PART"));
		}

		if (rs.getString("NM_PART_L") != null && !rs.getString("NM_PART_L").equals(""))
		{
			participant.setLastName(rs.getString("NM_PART_L"));
		}

		if (rs.getString("NM_PART_F") != null && !rs.getString("NM_PART_F").equals(""))
		{
			participant.setFirstName(rs.getString("NM_PART_F"));
		}

		if (rs.getString("NB_SSN") != null && !rs.getString("NB_SSN").equals(""))
		{
			participant.setSsn(rs.getString("NB_SSN"));

		}

		if (rs.getString("DT_BRTH") != null && !rs.getString("DT_BRTH").equals(""))
		{
			participant.setBithDate(rs.getDate("DT_BRTH"));

		}

		return participant;

	}

}
