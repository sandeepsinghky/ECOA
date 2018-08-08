package nc.dhhs.nccss.acts.dao;

import java.sql.SQLException;
import java.util.List;

import nc.dhhs.nccss.acts.ecoa.beans.CasePartNCPExt;

/**
 * @author Mallika Velagapudi
 */
public interface CasePartNCPExtDao
{
	/**
	 * @param applNCPExtBean
	 * @param ncid
	 * @return
	 * @throws SQLException
	 */
	public String createCasePartNCPExt(CasePartNCPExt applNCPExtBean, String ncid) throws SQLException;

	/**
	 * @param applId
	 * @param partId
	 * @return
	 * @throws SQLException
	 */
	public List<CasePartNCPExt> getCasePartNCPExtByPartId(String applId, String partId) throws SQLException;

	/**
	 * @param applNCPExtBean
	 * @param ncid
	 * @return
	 * @throws SQLException
	 */
	public String updateCasePartNCPExt(CasePartNCPExt applNCPExtBean, String ncid) throws SQLException;

	/**
	 * @param applNCPExtBean
	 * @param ncid
	 * @return
	 * @throws SQLException
	 */
	public String deleteCasePartNCPExt(CasePartNCPExt applNCPExtBean, String ncid) throws SQLException;
}
