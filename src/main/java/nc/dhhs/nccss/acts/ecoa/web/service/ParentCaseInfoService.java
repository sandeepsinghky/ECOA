package nc.dhhs.nccss.acts.ecoa.web.service;

import java.util.ArrayList;

import nc.dhhs.nccss.acts.ecoa.beans.ParentCaseInfo;
import nc.dhhs.nccss.acts.ecoa.beans.UserInformation;

/**
 * @author Mallika Velagapudi
 *
 */
/**
 * @author mvelagapudi
 *
 */
public interface ParentCaseInfoService
{

	/**
	 * @param userInfoBean
	 * @return
	 * @throws Exception
	 */
	public ArrayList<ParentCaseInfo> getCaseList(UserInformation userInfoBean) throws Exception;

	/**
	 * @param mpi
	 * @param ivdCase
	 * @return
	 * @throws Exception
	 */
	public ParentCaseInfo getCaseDetail(String mpi, String ivdCase) throws Exception;

}
