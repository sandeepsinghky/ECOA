package nc.dhhs.nccss.acts.ecoa.web.service;

import java.util.ArrayList;

import nc.dhhs.nccss.acts.ecoa.beans.Schedule;

/**
 * @author Mallika Velagapudi
 *
 */

public interface CaseScheduleService
{

	/**
	 * @param ivdCase
	 * @param caseRelshp
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Schedule> getCaseScheduleList(String ivdCase, String caseRelshp) throws Exception;

}
