package nc.dhhs.nccss.acts.ecoa.web.service;

import java.util.ArrayList;

import nc.dhhs.nccss.acts.ecoa.beans.ThirdParty;

/**
 * @author Mallika Velagapudi
 *
 */

public interface SearchClerkOfCourtService
{

	/**
	 * @param strCity
	 * @param strCounty
	 * @param fipsType
	 * @param searchType
	 * @param iPage
	 * @return
	 * @throws Exception
	 */
	public ArrayList<ThirdParty> getClerkOfCourtList(String strCity, String strCounty, String fipsType, int searchtype,
			int iPage) throws Exception;

	/**
	 * @param id3pty
	 * @param thirdPtyType
	 * @return
	 * @throws Exception
	 */
	public ThirdParty getClerkOfCourtDetail(String id3pty, String thirdPtyType) throws Exception;

	/**
	 * @param cocList
	 * @param iPage
	 * @return
	 * @throws Exception
	 */
	public ArrayList<ThirdParty> getClerkOfCourtPageList(ArrayList<ThirdParty> cocList, int iPage) throws Exception;

}
