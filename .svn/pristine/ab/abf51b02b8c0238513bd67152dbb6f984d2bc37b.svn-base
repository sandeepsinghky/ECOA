package nc.dhhs.nccss.acts.dao;

import java.sql.SQLException;
import java.util.List;

import nc.dhhs.nccss.acts.ecoa.beans.Participant;

/**
 * @author Mallika Velagapudi
 */
public interface ParticipantDao {
	/**
	 * @param ncid
	 * @return
	 * @throws SQLException
	 */
	public List<Participant> getParticipant(String mpi) throws SQLException;

	public boolean dualRole(String mpi) throws SQLException;

}
