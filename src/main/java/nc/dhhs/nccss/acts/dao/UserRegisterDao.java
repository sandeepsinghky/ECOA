/**
 * 
 */
package nc.dhhs.nccss.acts.dao;

import java.sql.SQLException;

import nc.dhhs.nccss.acts.ecoa.beans.PreNcIdUser;

/**
 * @author Mallika Velagapudi
 *
 */

/**
 * @author Mallika Velagapudi
 *
 */
public interface UserRegisterDao {

	/**
	 * @param mpi
	 * @param ssn
	 * @param dob
	 * @return
	 * @throws SQLException
	 */
	public PreNcIdUser getRegisteredOldUser(String mpi, String ssn, String dob) throws SQLException;

	/**
	 * @param mpi
	 * @param ssn
	 * @param dob
	 * @param idPart
	 * @return
	 * @throws SQLException
	 */
	public String getParticipantStatus(String mpi, String ssn, String dob, StringBuffer idPart) throws SQLException;

	/**
	 * @param attempts
	 * @param ncId
	 * @return
	 * @throws SQLException
	 */
	public int updateNbAttempt(int attempts, String ncId) throws SQLException;
	
	/**
	 * @param mpi
	 * @return
	 * @throws SQLException
	 */
	public String getMPIUsedUser(String mpi) throws SQLException;
}
