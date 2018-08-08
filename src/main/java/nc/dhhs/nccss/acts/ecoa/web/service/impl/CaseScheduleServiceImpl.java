/**
 * 
 */
package nc.dhhs.nccss.acts.ecoa.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.transaction.annotation.Transactional;

import nc.dhhs.nccss.acts.dao.CaseObligationDao;
import nc.dhhs.nccss.acts.dao.CaseScheduleDao;
import nc.dhhs.nccss.acts.ecoa.beans.Schedule;
import nc.dhhs.nccss.acts.ecoa.web.service.CaseScheduleService;

/**
 * @author Mallika Velagapudi
 *
 */
public class CaseScheduleServiceImpl implements CaseScheduleService
{
	protected final Logger						logger			= Logger.getLogger(CaseScheduleServiceImpl.class);

	private static final Map<String, String>	scheduleCodeMap	= new HashMap<String, String>()
																{
																	{
																		put("APFI", "A non-custodial parent follow up interview ");
																		put("CLFI", "A custodian follow-up interview ");
																		put("GAPI", " A group absent parent interview ");
																		put("GCLI", "A custodian interview ");
																		put("GTAP", "A paternity testing appointment ");
																		put("IAPI", "A first interview with the non-custodial parent ");
																		put("ICLI", "A first interview with the custodian ");
																		put("ICL2 ", "A second interview with the custodian");
																		put("ICWM", "An interview with a custodian under 18 and adult representative ");
																		put("IMMI", " A first interview with a custodian who is under 18 ");
																		put("HAPP", " A hearing for appeal ");
																		put("HCGB", "A hearing to require paternity tests ");
																		put("HCIS", "A hearing to establish paternity and support ");
																		put("HCJL", "A hearing to convert a judgement to a lien ");
																		put("HCRC", "A hearing for failure to comply with a court order ");
																		put("HCRS", "A hearing to establish support ");
																		put("HCVE", "A hearing to move the court order to a different county ");
																		put("HEFA", "A hearing for failure to appear for paternity tests ");
																		put("HELB", "A hearing to establish a lien or bond against the non-custodial parent");
																		put("HEMS", "A hearing to establish an order for medical insurance coverage ");
																		put("HENC", "A hearing to require the custodian to help the child support agency obtain support ");
																		put("HEPS", "A hearing to establish paternity and support ");
																		put("HESB", "A hearing to extend support beyond child's 18th birthday ");
																		put("HESU", "A civil court hearing to establish support ");
																		put("HESV", "A civil court hearing to establish support");
																		put("HFPM ", "A hearing for failure to provide medical insurance coverage  as ordered ");
																		put("HINT", "A hearing to change responsibility for managing this case to the N.C. Child Support Enforcement Office ");
																		put("HJEM", "A hearing to require an employer to obey a court order ");
																		put("HLAR", "A civil court hearing to establish paternity and support ");
																		put("HOSC", "A hearing for failure to comply with a court order ");
																		put("HOTH", "A hearing");
																		put("HPPA", "A hearing to establish the amount owed to repay the State for public assistance given to the child ");
																		put("HRAD", "A hearing for review of the terms of the order ");
																		put("HRED", "A hearing to change responsibility for managing the case ");
																		put("HREV", "A hearing to review the terms of the order ");
																		put("HRFS", "A hearing on a court order from another state ");
																		put("HURS", "An interstate hearing ");
																		put("ACBR", "Appeal Trade-line Reporting");
																		put("ADIS", "Appeal Distribution");
																		put("AFTX", "Appeal Federal Tax Intercept");
																		put("ASTX", "Appeal State Tax Intercept");
																	}
																};

	private static final Map<String, String>	scheduleDispMap	= new HashMap<String, String>()
																{
																	{
																		put("APPR", "The appointment was kept. ");
																		put("CONT", "The case was continued to another date. ");
																		put("CTMP", "An Order of Contempt was issued.");
																		put("DISI", "The court action was dismissed .");
																		put("DISL", "The court action was dismissed.");
																		put("DISO", "The court action was dismissed.");
																		put("DISR", "The court action was dismissed.");
																		put("DMWP", "The court action was dismissed. ");
																		put("DSMS", "The court action was dismissed. ");
																		put("MDFD", "A request to modify the court order was denied. ");
																		put("NOSH", "The appointment was not kept. ");
																		put("NTMP", "The non-custodial parent was found not in contempt of the order. ");
																		put("ORDA", " An order for arrest of the non-custodial parent was issued.");
																		put("ORDE", "A support order was established. ");
																		put("RSCH", "The appointment was rescheduled. ");
																		put("UPHD", "The existing order was not changed.");
																		put("NTSV", "No action was taken because Non-Custodial Parent was not served.");
																	}
																};

	@Autowired
	private CaseScheduleDao						scheduleDao;

	@Autowired
	private CaseObligationDao					obligationDao;

	@Override
	@Transactional(readOnly = true)
	public ArrayList<Schedule> getCaseScheduleList(String ivdCase, String caseRelshp) throws Exception

	{
		logger.debug("\n********** IN ParentCaseScheduleServiceImpl:getCaseSchedulesList **********\n");

		ArrayList<Schedule> scheduleList = new ArrayList<Schedule>();

		/*
		 * Obtain the Current Date for Comparision Purposes
		 */
		java.util.Date dt = new java.util.Date();

		java.sql.Date currentDate = new java.sql.Date(dt.getTime());

		// Case Relationship already exists in the UserInfo Bean We do not need a query for this

		SqlRowSet rs = scheduleDao.getAppointments(ivdCase, caseRelshp);

		int iSchCount = 0;

		while (rs.next())
		{
			java.sql.Date scheduleDate = rs.getDate(1);

			Schedule s = new Schedule();
			s.setScheduleCode("APPT");

			if ((scheduleDate.compareTo(currentDate) < 0) && iSchCount < 3)
			{
				s.setFuture(false);

				iSchCount++;

			}
			else if ((scheduleDate.compareTo(currentDate) < 0) && iSchCount >= 3)
			{
				break;
			}
			else
			{
				s.setFuture(true);
			}

			if (rs.getString(1) != null && !rs.getString(1).equals(""))
			{
				s.setScheduleDate(rs.getDate(1));

			}
			//s.setScheduleDate(scheduleDate.toString());
			s.setScheduleType(rs.getString(2).trim());
			s.setScheduleDisp(rs.getString(3).trim());
			scheduleList.add(s);
		}

		SqlRowSet rowSet = scheduleDao.getHearing(ivdCase);

		int iHearCount = 0;

		while (rowSet.next())
		{
			java.sql.Date hearingDate = rowSet.getDate(1);

			Schedule s = new Schedule();

			s.setScheduleCode("HEAR");

			if ((hearingDate.compareTo(currentDate) < 0) && iHearCount < 3)
			{
				s.setFuture(false);

				iHearCount++;

			}
			else if ((hearingDate.compareTo(currentDate) < 0) && iHearCount >= 3)
			{
				break;

			}
			else
			{
				s.setFuture(true);
			}
			s.setScheduleDate(rowSet.getDate(1));
			s.setScheduleType(rowSet.getString(2).trim());
			s.setScheduleDisp(rowSet.getString(3).trim());
			scheduleList.add(s);
		}

		for (int i = 0; i < scheduleList.size(); i++)
		{
			String id3pty = "";
			String cd3pty = "";
			// Create the Statement Object

			Schedule s = (Schedule) scheduleList.get(i);

			rs = scheduleDao.getScheduleInfo(ivdCase, s.getScheduleDateStr(), s.getScheduleType());

			s.setScheduleReason(getScheduleCode(s.getScheduleType()));

			s.setScheduleDispRsn(getScheduleCodedisp(s.getScheduleDisp()));

			while (rs.next())
			{
				s.setScheduleCode(rs.getString(1));

				id3pty = rs.getString(2).trim();

				if (rs.getString(3).trim().equals("AG"))
				{
					cd3pty = "AGCY";
				}
				else if (rs.getString(3).trim().equals("CT"))
				{
					cd3pty = "CORT";
				}
			}

			// Convert the Schedule Date from ccyy-mm-dd format to mm/dd/ccyy

			if (!id3pty.equals(""))
			{

				// Obtain the location of the appointment or hearing

				rs = scheduleDao.getLocation(id3pty, cd3pty);

				while (rs.next())
				{
					s.setScheduleAddr1(rs.getString(1).trim());
					s.setScheduleAddr2(rs.getString(2).trim());
					s.setScheduleCity(rs.getString(3).trim());
					s.setScheduleState(rs.getString(4).trim());
					s.setScheduleZip(rs.getString(5).trim());
				}

				// Create the Resultset object from the sql query
				// Obtain the County Name
				rs = scheduleDao.getCounty(id3pty);

				while (rs.next())
				{
					s.setScheduleCounty(rs.getString(1).trim());
				}

			}

		}

		return scheduleList;

	}

	/**
	 * Method to Convert Schedule Codes to Descriptions Creation date:
	 * 
	 */
	private String getScheduleCode(String type)
	{
		String codeDesc = "";

		if (scheduleCodeMap.containsKey(type)) return scheduleCodeMap.get(type);

		else
			return codeDesc;
	}

	private String getScheduleCodedisp(String type)
	{
		String codeDisp = "";

		if (scheduleDispMap.containsKey(type)) return scheduleDispMap.get(type);

		return codeDisp;
	}

}
