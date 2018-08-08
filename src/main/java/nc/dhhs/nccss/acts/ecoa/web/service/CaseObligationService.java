package nc.dhhs.nccss.acts.ecoa.web.service;

import nc.dhhs.nccss.acts.ecoa.beans.Obligation;

/**
 * @author Mallika Velagapudi
 *
 */

public interface CaseObligationService
{

	/**
	 * @param ivdCase
	 * @param caseRelshp
	 * @return
	 * @throws Exception
	 */
	public Obligation getCaseObligation(String ivdCase, String caseRelshp) throws Exception;

}
