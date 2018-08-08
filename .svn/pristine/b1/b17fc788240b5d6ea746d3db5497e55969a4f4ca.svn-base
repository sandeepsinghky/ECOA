package nc.dhhs.nccss.acts.ecoa.web.controller.parents;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import nc.dhhs.nccss.acts.ecoa.web.exception.ErrorDescriptor;
import nc.dhhs.nccss.acts.ecoa.beans.ParentCaseInfo;
import nc.dhhs.nccss.acts.ecoa.beans.Schedule;
import nc.dhhs.nccss.acts.ecoa.beans.UserInformation;
import nc.dhhs.nccss.acts.ecoa.web.controller.BasicAnnotatedFormController;
import nc.dhhs.nccss.acts.ecoa.web.service.CaseScheduleService;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * @author Mallika Velagapudi
 *
 */
@Controller
public class ParentCaseSchedulesController extends BasicAnnotatedFormController
{

	@Autowired
	private CaseScheduleService caseScheduleService;

	@SuppressWarnings("unchecked")
	@RequestMapping("/cssp/user/caseSchedule.htm")
	public String getParentSchedules(HttpServletRequest request, Model model) throws Exception

	{
		logger.debug("\n********** In ParentCaseSchedulesController: getParentSchedules **********\n");

		String returnPage = "";
		String otherName = "";
		ArrayList<ParentCaseInfo> caseList = null;

		ArrayList<Schedule> scheduleList = new ArrayList<Schedule>();

		try
		{
			UserInformation userInfoBean = (UserInformation) request.getSession().getAttribute(AppConstants.USERINFORMATION);

			String mpi = userInfoBean.getMpi();
			
			if (mpi.isEmpty()) {
				request.setAttribute("errorMsg",
						"Registration process has to be completed to check appointments/hearings information");

				return "forward:/cssp/user/userWelcome.htm";

			}

			if (userInfoBean.getCaseRelshp().trim().isEmpty()) //if user has dual role and role is not selected.

			{
				request.setAttribute("errorMsg", "Custodial Parent or Non-Custodial Parent selection is required");

				returnPage = "forward:/cssp/user/parentWelcome.htm";
			}

			else
			{
				if (request.getParameter("ivdCase") == null) //ivdCase request parameter will  be null when request comes by clicking on Appointments/hearings link.
				{
					if (request.getSession().getAttribute(AppConstants.ivdCase) != null)

					{
						String ivdCase = (String) request.getSession().getAttribute(AppConstants.ivdCase);

						scheduleList = caseScheduleService.getCaseScheduleList(ivdCase, userInfoBean.getCaseRelshp());

						addDisplayText(scheduleList);

						model.addAttribute("scheduleList", scheduleList);

						returnPage = AppConstants.ECOA_PARENT_CASE_SCHEDULE;

					}
					else
					{
						returnPage = "redirect:/cssp/user/CaseList/caseSchedule.htm"; //user needs to select case to see details.so redirected to caseList

					}
				}

				else //when request comes by selecting case from  caseList. 
				{
					if (request.getParameter("errorMsg") != null && request.getParameter("errorMsg").trim().isEmpty())
					{
						String ivdCase = request.getParameter("ivdCase");

						request.getSession().setAttribute(AppConstants.ivdCase, ivdCase);

						//below we get otherParticipant/otherParty name for the given ivdCase from caseList stored in session.

						if (request.getSession().getAttribute(AppConstants.caseList) != null)
						{
							caseList = (ArrayList<ParentCaseInfo>) request.getSession().getAttribute(AppConstants.caseList);

							if (caseList.size() > 0)
							{
								for (ParentCaseInfo caseInfo : caseList)
								{
									if (caseInfo.getIvdCase().trim().equals(ivdCase))

									{
										otherName = caseInfo.getOtherName();

										break;

									}
								}

							}
						}

						request.getSession().setAttribute(AppConstants.otherPartyName, otherName);

						scheduleList = caseScheduleService.getCaseScheduleList(ivdCase, userInfoBean.getCaseRelshp());

						addDisplayText(scheduleList);

						model.addAttribute("scheduleList", scheduleList);

						returnPage = AppConstants.ECOA_PARENT_CASE_SCHEDULE;

					}
					else
					{
						if (!request.getParameter("errorMsg").trim().isEmpty())
						{

							returnPage = "forward:/cssp/user/CaseList/caseSchedule.htm";//forward is used to use request object in the caseList controller.
						}
					}
				}

			}

		}

		catch (Exception e)
		{

			logger.error(e.getMessage());

			ErrorDescriptor error = new ErrorDescriptor(this.getClass().getName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage(), e);

			request.setAttribute("errorBean", error);

			error = null;

			return "forward:/parentError.htm";
		}

		return returnPage;
	}

	private void addDisplayText(ArrayList<Schedule> scheduleList) throws Exception

	{
		logger.debug("\n********** In ParentCaseSchedulesController: addDisplayText **********\n");

		if (scheduleList.size() > 0)
		{
			for (Schedule s : scheduleList)
			{

				String strType = "";

				if (s.isFuture())
				{
					strType = " is scheduled for ";
				}
				else
				{
					if ((s.getScheduleCode().equals("HEAR")) || (s.getScheduleCode().equals("APPL")))
					{
						if (s.getScheduleDispRsn().equals(""))
						{
							strType = " was scheduled for ";
						}
						else
						{
							strType = " was held on ";
						}
					}
					else
					{
						if (s.getScheduleDispRsn().equals(""))
						{
							strType = " was scheduled for ";
						}
						else
						{
							strType = " was scheduled on ";
						}
					}
				}
				String disposition = "";

				if (s.getScheduleDispRsn().equals(""))
				{
					if (!s.isFuture())
					{
						disposition = "Please contact your local Child Support Enforcement office for more information.";
					}
				}
				else
				{
					disposition = s.getScheduleDispRsn();
				}

				if (s.getScheduleCounty().equals(""))
				{
					strType = s.getScheduleReason() + strType + s.getScheduleDateStrPg() + ". " + disposition;
				}
				else
				{
					if (s.getScheduleCode().equals("APPT"))
					{
						strType = s.getScheduleReason() + strType + s.getScheduleDateStrPg() + ". " + disposition;
					}
					else
					{
						if (s.isFuture())
						{
							strType = s.getScheduleReason() + strType + s.getScheduleDateStrPg()+ " at " + s.getScheduleCounty() + " County court. " + disposition;
						}
						else
						{
							strType = s.getScheduleReason() + strType + s.getScheduleDateStrPg() + ". " + disposition;
						}
					}
				}

				s.setDispText(strType);

			}

		}

	}
}
