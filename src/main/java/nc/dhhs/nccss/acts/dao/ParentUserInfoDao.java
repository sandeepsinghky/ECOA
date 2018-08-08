/**
 * 
 */
package nc.dhhs.nccss.acts.dao;

import java.sql.SQLException;

import org.springframework.jdbc.support.rowset.SqlRowSet;

/**
 * @author Mallika Velagapudi
 *
 */
public interface ParentUserInfoDao
{

	/**
	 * @param mpi
	 * @return
	 * @throws SQLException
	 */

	public SqlRowSet getDualRole(String mpi) throws Exception;

	/**
	 * @param mpi
	 * @return
	 * @throws SQLException
	 */
	public SqlRowSet getPartDetails(String mpi) throws Exception;

	/**
	 * @param mpi
	 * @return
	 * @throws SQLException
	 */
	public int updateLastLogin(String mpi) throws Exception;

	/**
	 * @param mpi
	 * @return
	 * @throws SQLException
	 */
	public int getAllAppointments(String mpi) throws Exception;

	/**
	 * @param mpi
	 * @param caseRelshp
	 * @return
	 * @throws SQLException
	 */
	public int getAppointments(String mpi, String caseRelshp) throws Exception;

	/**
	 * @param mpi
	 * @return
	 * @throws Exception
	 */
	public int getHearing(String mpi) throws Exception;

	/**
	 * @param mpi
	 * @return
	 * @throws Exception
	 */
	public int getMailAddress(String mpi) throws Exception;

	/**
	 * @param mpi
	 * @return
	 * @throws Exception
	 */
	public SqlRowSet getAOCMailAddress(String mpi) throws Exception;

	/**
	 * @param mpi
	 * @return
	 * @throws Exception
	 */
	public SqlRowSet getEmployment(String mpi) throws Exception;

}
