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
public interface ParentCaseInfoDao
{

	/**
	 * @param mpi
	 * @return
	 * @throws SQLException
	 */
	public SqlRowSet getCaseList(String mpi) throws SQLException;

	/**
	 * @param mpi
	 * @return
	 * @throws SQLException
	 */
	public String getPartProfile(String mpi) throws SQLException;

	/**
	 * @param mpi
	 * @param strCase
	 * @return
	 * @throws SQLException
	 */
	/**
	 * @param mpi
	 * @param strCase
	 * @return
	 * @throws SQLException
	 */
	public SqlRowSet getSecondParty(String mpi, String strCase) throws SQLException;

	/**
	 * @param mpi
	 * @return
	 * @throws SQLException
	 */
	public SqlRowSet getParticipantName(String mpi) throws SQLException;

	/**
	 * @param id3pty
	 * @return
	 * @throws SQLException
	 */
	public SqlRowSet getAgencyName(String id3pty) throws SQLException;

	/**
	 * @param strCase
	 * @return
	 * @throws SQLException
	 */
	public SqlRowSet getUnderOrder(String strCase) throws SQLException;

	/**
	 * @param mpi
	 * @param ivdCase
	 * @return
	 * @throws SQLException
	 */
	public SqlRowSet getCaseDetail(String mpi, String ivdCase) throws SQLException;

	/**
	 * @param mpi
	 * @param ivdCase
	 * @return
	 * @throws SQLException
	 */
	public SqlRowSet getSecondPartyQuery(String mpi, String ivdCase) throws SQLException;

	/**
	 * @param ivdCase
	 * @return
	 * @throws SQLException
	 */
	public int getChildren(String ivdCase) throws SQLException;

	/**
	 * @param ivdCase
	 * @return
	 * @throws SQLException
	 */
	public SqlRowSet getWorker(String ivdCase) throws SQLException;

}
