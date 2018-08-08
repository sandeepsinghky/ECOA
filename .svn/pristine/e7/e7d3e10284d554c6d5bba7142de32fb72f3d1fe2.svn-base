package nc.dhhs.nccss.acts.dao;

import java.sql.SQLException;
import java.util.List;

import nc.dhhs.nccss.acts.ecoa.beans.CasePartProfile;

/**
 * @author Phani Konuru
 */
public interface CasePartProfileDao
{
	/**
	 * @param partInfo
	 * @param ncid
	 * @return
	 * @throws SQLException
	 */
	public String createPartProfile(CasePartProfile partProfileInfo, String ncid) throws SQLException;

	/**
	 * @param applId
	 * @param partId
	 * @return
	 * @throws SQLException
	 */
	public List<CasePartProfile> getPartProfile(String applId, String partId) throws SQLException;

	/**
	 * @param partInfo
	 * @return
	 * @throws SQLException
	 */
	public String updatePartProfile(CasePartProfile partProfileInfo, String ncid) throws SQLException;

	/**
	 * @param partInfo
	 * @return
	 * @throws SQLException
	 */
	public String deletePartProfile(CasePartProfile partProfileInfo, String ncid) throws SQLException;
}
