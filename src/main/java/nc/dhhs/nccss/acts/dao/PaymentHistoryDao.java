package nc.dhhs.nccss.acts.dao;

import java.sql.SQLException;

import org.springframework.jdbc.support.rowset.SqlRowSet;

/**
 * @author Mallika Velagapudi
 *
 */
public interface PaymentHistoryDao
{

	/**
	 * @param mpi
	 * @return
	 * @throws SQLException
	 */
	public SqlRowSet getCheckComp(String mpi) throws Exception;

	/**
	 * @param mpi
	 * @return
	 * @throws SQLException
	 */
	public SqlRowSet getEFTDetails(String mpi) throws Exception;

	/**
	 * @param mpi
	 * @return
	 * @throws Exception
	 */
	public SqlRowSet getJtrnQuery(String mpi) throws Exception;

	/**
	 * @param strID
	 * @param nb_mo_jnlevt
	 * @param nb_btch_jnlevt
	 * @param nb_itm_jnlevt
	 * @return
	 * @throws Exception
	 */
	public SqlRowSet getAllocQuery(String strID, short nb_mo_jnlevt, int nb_btch_jnlevt, short nb_itm_jnlevt)
			throws Exception;

	/**
	 * @param strID
	 * @param nb_mo_jnlevt
	 * @param nb_btch_jnlevt
	 * @param nb_itm_jnlevt
	 * @return
	 * @throws Exception
	 */
	public SqlRowSet getManualAdj(String strID, short nb_mo_jnlevt, int nb_btch_jnlevt, short nb_itm_jnlevt)
			throws Exception;

	/**
	 * @param strID
	 * @return
	 * @throws Exception
	 */
	public SqlRowSet getPcasQuery(String strID) throws Exception;

	/**
	 * @param id3pty
	 * @param id3ptyType
	 * @return
	 * @throws Exception
	 */
	public SqlRowSet getSystemAccount(String id3pty, String id3ptyType) throws Exception;

	/**
	 * @param strID
	 * @return
	 * @throws Exception
	 */
	public SqlRowSet getParticipant(String strID) throws Exception;

}
