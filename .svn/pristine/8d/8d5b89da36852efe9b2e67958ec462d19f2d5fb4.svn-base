package nc.dhhs.nccss.acts.ecoa.web.service;

import nc.dhhs.nccss.acts.ecoa.beans.UserInformation;

/**
 * @author Mallika Velagapudi
 *
 */

public interface RequestPaymentService {

	/**
	 * @param ivdCase
	 * @param caseRelshp
	 * @return
	 * @throws Exception
	 */
	public StringBuffer buildCPPayments(UserInformation userInfoBean) throws Exception;

	/**
	 * @param userInfoBean
	 * @return
	 * @throws Exception
	 */
	public StringBuffer buildNCPPayments(UserInformation userInfoBean) throws Exception;

	/**
	 * @param mpi
	 * @return
	 * @throws Exception
	 */
	public boolean getMailAddress(String mpi) throws Exception;

	/**
	 * @param mpi
	 * @param caseRelshp
	 * @param ivdCase
	 * @throws Exception
	 */
	public void createWebTran(String mpi, String caseRelshp, String ivdCase) throws Exception;
}
