package nc.dhhs.nccss.acts.dao;

import java.sql.SQLException;
import java.util.List;

import nc.dhhs.nccss.acts.ecoa.beans.CasePartAddress;

/**
 * @author Phani Konuru
 */
public interface CasePartAddrDao
{

	/**
	 * @param addrBean
	 * @param ncid
	 * @return
	 * @throws SQLException
	 */
	public String createPartAddr(CasePartAddress addrBean, String ncid) throws SQLException;

	/**
	 * @param aooId
	 * @param partId
	 * @return
	 * @throws SQLException
	 */
	public List<CasePartAddress> getCasePartAddr(String applicationId, String applicantId) throws SQLException;

	/**
	 * @param addrBean
	 * @return
	 * @throws SQLException
	 */
	public String updatePartAddr(CasePartAddress addrBean, String ncid) throws SQLException;

	/**
	 * @param addrBean
	 * @param ncid
	 * @return
	 * @throws SQLException
	 */
	public String deletePartAddr(CasePartAddress addrBean, String ncid) throws SQLException;

}
