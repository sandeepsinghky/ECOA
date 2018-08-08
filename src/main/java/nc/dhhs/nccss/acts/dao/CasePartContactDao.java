package nc.dhhs.nccss.acts.dao;

import java.sql.SQLException;
import java.util.List;

import nc.dhhs.nccss.acts.ecoa.beans.CasePartContact;

/**
 * @author Phani Konuru
 */
public interface CasePartContactDao
{

	/**
	 * @param addrBean
	 * @param ncid
	 * @return
	 * @throws SQLException
	 */
	public String createPartContact(CasePartContact contactBean, String ncid) throws SQLException;

	/**
	 * @param aooId
	 * @param partId
	 * @return
	 * @throws SQLException
	 */
	public List<CasePartContact> getCasePartContact(String applicationId, String applicantId) throws SQLException;

	/**
	 * @param addrBean
	 * @return
	 * @throws SQLException
	 */
	public String updatePartContact(CasePartContact contactBean, String ncid) throws SQLException;

	/**
	 * @param addrBean
	 * @param ncid
	 * @return
	 * @throws SQLException
	 */
	public String deletePartContact(CasePartContact contactBean, String ncid) throws SQLException;

	public CasePartContact getCasePartContactType(String applicationId, String applicantId, String contactType)
			throws SQLException;

}
