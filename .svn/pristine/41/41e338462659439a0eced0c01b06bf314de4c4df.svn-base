package nc.dhhs.nccss.acts.dao;

import java.sql.SQLException;
import java.util.List;

import nc.dhhs.nccss.acts.ecoa.beans.CaseCourtOrder;

/**
 * @author Mallika Velagapudi
 */
public interface CaseCourtOrderDao
{
	/**
	 * @param orderBean
	 * @param ncid
	 * @return
	 * @throws SQLException
	 */
	public String createCaseCourtOrder(CaseCourtOrder orderBean, String ncid) throws SQLException;

	/**
	 * @param applId
	 * @param partId
	 * @return
	 * @throws SQLException
	 */
	public List<CaseCourtOrder> getCaseCourtOrderByPartId(String applId, String partId) throws SQLException;

	/**
	 * @param applId
	 * @param partId
	 * @param supportType
	 * @return
	 * @throws SQLException
	 */

	public List<CaseCourtOrder> getCaseCourtOrderByType(String applId, String applicantId, String supportType)
			throws SQLException;

	/**
	 * @param orderBean
	 * @param ncid
	 * @return
	 * @throws SQLException
	 */
	public String updateCaseCourtOrder(CaseCourtOrder orderBean, String ncid) throws SQLException;

	/**
	 * @param orderBean
	 * @param ncid
	 * @return
	 * @throws SQLException
	 */
	public String deleteCaseCourtOrder(CaseCourtOrder orderBean, String ncid) throws SQLException;
}
