package nc.dhhs.nccss.acts.dao;

import org.springframework.jdbc.support.rowset.SqlRowSet;

/**
 * @author Mallika Velagapudi
 *
 */
public interface RequestPaymentDao {

	/**
	 * @param id3pty
	 * @param id3ptyType
	 * @return
	 * @throws Exception
	 */
	public SqlRowSet getCallCenterAddress(String id3pty, String id3ptyType) throws Exception;

	/**
	 * @param mpi
	 * @return
	 * @throws Exception
	 */
	public SqlRowSet getPartInfo(String mpi) throws Exception;

	/**
	 * @param mpi
	 * @param relshp
	 * @return
	 * @throws Exception
	 */
	public SqlRowSet getCases(String mpi, String relshp) throws Exception;

	/**
	 * @param ivdCase
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public SqlRowSet getParent(String ivdCase, String type) throws Exception;

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

	/**
	 * @param ivdCase
	 * @return
	 * @throws Exception
	 */
	public SqlRowSet getChildren(String ivdCase) throws Exception;

	/**
	 * @param mpi
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws Exception
	 */
	public SqlRowSet getCPPayments(String mpi, java.sql.Date startDate, java.sql.Date endDate) throws Exception;

	/**
	 * @param mpi
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws Exception
	 */
	public SqlRowSet getNcpPayments(String mpi, java.sql.Date startDate, java.util.Date endDate) throws Exception;

	/**
	 * @param mpi
	 * @return
	 * @throws Exception
	 */
	public SqlRowSet getMailAddress(String mpi) throws Exception;

	/**
	 * @param mpi
	 * @param caseRelshp
	 * @param ivdCase
	 * @throws Exception
	 */
	public void createWebTran(String mpi, String caseRelshp, String ivdCase) throws Exception;

}
