package nc.dhhs.nccss.acts.dao;

import java.sql.SQLException;
import java.util.List;

import nc.dhhs.nccss.acts.ecoa.beans.CaseParticipant;

/**
 * @author Phani Konuru
 */
public interface CaseParticipantDao
{
	/**
	 * @param partInfo
	 * @param ncid
	 * @return
	 * @throws SQLException
	 */
	public String createParticipant(CaseParticipant partInfo, String ncid) throws SQLException;

	/**
	 * @param applId
	 * @param partId
	 * @return
	 * @throws SQLException
	 */
	public List<CaseParticipant> getParticipantByPartId(String applId, String partId) throws SQLException;

	/**
	 * @param applId
	 * @param partType
	 * @return
	 * @throws SQLException
	 */
	public List<CaseParticipant> getParticipantByPartType(String applId, String partType) throws SQLException;

	/**
	 * @param partInfo
	 * @return
	 * @throws SQLException
	 */
	public String updateParticipant(CaseParticipant partInfo, String ncid) throws SQLException;

	/**
	 * @param partInfo
	 * @return
	 * @throws SQLException
	 */
	public String deleteParticipant(CaseParticipant partInfo, String ncid) throws SQLException;
}
