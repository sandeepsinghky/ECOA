package nc.dhhs.nccss.acts.dao;

import java.sql.SQLException;
import java.util.List;

import nc.dhhs.nccss.acts.ecoa.beans.CasePartOrder;

/**
 * @author Mallika Velagapudi
 */
public interface CasePartOrderDao
{
	/**
	 * @param orderBean
	 * @param ncid
	 * @return
	 * @throws SQLException
	 */
	public String createCasePartOrder(CasePartOrder orderChildBean, String ncid) throws SQLException;

	/**
	 * @param applId
	 * @param partId
	 * @return
	 * @throws SQLException
	 */
	public List<CasePartOrder> getCasePartOrderByOrderNum(String orderNum) throws SQLException;

	/**
	 * @param applId
	 * @param partId
	 * @param supportType
	 * @return
	 * @throws SQLException
	 */

	public String deleteCasePartOrder(CasePartOrder orderChildBean, String ncid) throws SQLException;

}
