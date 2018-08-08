/**
 * 
 */
package nc.dhhs.nccss.acts.dao;

import org.springframework.jdbc.support.rowset.SqlRowSet;

/**
 * @author Mallika Velagapudi
 *
 */

public interface CaseObligationDao
{

	/**
	 * @param ivdCase
	 * @param role
	 * @return
	 * @throws Exception
	 */
	public double getCsupQuery(String ivdCase, String role) throws Exception;

	/**
	 * @param ivdCase
	 * @param role
	 * @return
	 * @throws Exception
	 */
	public double getArrearsQuery(String ivdCase, String role) throws Exception;

	/**
	 * @param ivdCase
	 * @return
	 * @throws Exception
	 */
	public SqlRowSet getCsupFrequency(String ivdCase) throws Exception;

	/**
	 * @param ivdCase
	 * @return
	 * @throws Exception
	 */
	public SqlRowSet getArrearsFrequency(String ivdCase) throws Exception;

}
