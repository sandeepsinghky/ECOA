package nc.dhhs.nccss.acts.dao;

import java.sql.SQLException;
import java.util.List;

import nc.dhhs.nccss.acts.ecoa.beans.ThirdParty;

/**
 * @author Mallika Velagapudi
 */
public interface ThirdPartyDao
{
	/**
	 * @param appliBean
	 * @return
	 * @throws SQLException
	 */
	public String createThirdParty(ThirdParty thirdPartyBean, String ncid) throws SQLException;

	/**
	 * @param applId
	 * @return
	 * @throws SQLException
	 */
	public List<ThirdParty> getThirdPartyByApplIdandType(String applId, String thirdPartyType) throws SQLException;

	/**
	 * @param applId
	 * @param thirdPartyId
	 * @return
	 * @throws SQLException
	 */
	public List<ThirdParty> getThirdPartyBy3ptyId(String applId, String thirdPartyId) throws SQLException;

	/**
	 * @param applBean
	 * @return
	 * @throws SQLException
	 */
	public String updateThirdParty(ThirdParty thirdPartyBean, String ncid) throws SQLException;

	/**
	 * @param applId
	 * @param ncId
	 * @return
	 * @throws SQLException
	 */
	public String deleteThirdParty(ThirdParty thirdPartyBean, String ncid) throws SQLException;

}
