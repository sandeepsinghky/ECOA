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
/**
 * @author mvelagapudi
 *
 */
public interface CaseScheduleDao
{

	/**
	 * @param ivdCase
	 * @param caseRelshp
	 * @return
	 * @throws SQLException
	 */
	public SqlRowSet getAppointments(String ivdCase, String caseRelshp) throws SQLException;

	/**
	 * @param ivdCase
	 * @return
	 * @throws Exception
	 */
	public SqlRowSet getHearing(String ivdCase) throws Exception;

	/**
	 * @param ivdCase
	 * @param dt
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public SqlRowSet getScheduleInfo(String ivdCase, String dt, String type) throws Exception;

	/**
	 * @param id3pty
	 * @param cd3pty
	 * @return
	 * @throws Exception
	 */
	public SqlRowSet getLocation(String id3pty, String cd3pty) throws Exception;

	/**
	 * @param id3pty
	 * @return
	 * @throws Exception
	 */
	public SqlRowSet getCounty(String id3pty) throws Exception;

}
