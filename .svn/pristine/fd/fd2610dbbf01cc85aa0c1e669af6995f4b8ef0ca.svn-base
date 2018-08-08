package nc.dhhs.nccss.acts.ecoa.web.controller.parents;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import nc.dhhs.nccss.acts.ecoa.web.exception.ErrorDescriptor;
import nc.dhhs.nccss.acts.ecoa.beans.Obligation;
import nc.dhhs.nccss.acts.ecoa.beans.ParentCaseInfo;
import nc.dhhs.nccss.acts.ecoa.beans.UserInformation;
import nc.dhhs.nccss.acts.ecoa.web.controller.BasicAnnotatedFormController;
import nc.dhhs.nccss.acts.ecoa.web.service.CaseObligationService;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * @author Mallika Velagapudi
 *
 */
@Controller
public class ParentCaseObligationController extends BasicAnnotatedFormController
{

	@Autowired
	private CaseObligationService caseObligationService;

	@SuppressWarnings("unchecked")
	@RequestMapping("/cssp/user/caseObligation.htm")
	public String getParentObligation(HttpServletRequest request, Model model) throws Exception

	{
		logger.debug("\n********** In ParentCaseSchedulesController: getParentSchedules **********\n");

		String returnPage = "";
		String otherName = "";
		ArrayList<ParentCaseInfo> caseList = null;

		Obligation ob = null;

		try
		{
			UserInformation userInfoBean = (UserInformation) request.getSession().getAttribute(AppConstants.USERINFORMATION);

			String mpi = userInfoBean.getMpi();
			
			if (mpi.isEmpty()) {
				request.setAttribute("errorMsg",
						"Registration process has to be completed to check obligation summary");

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

						ob = caseObligationService.getCaseObligation(ivdCase, userInfoBean.getCaseRelshp());

						model.addAttribute("ob", ob);

						returnPage = AppConstants.ECOA_PARENT_CASE_OBLIGATION;

					}
					else
					{
						returnPage = "redirect:/cssp/user/CaseList/caseObligation.htm"; //user needs to select case to see details.so redirected to caseList

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

						ob = caseObligationService.getCaseObligation(ivdCase, userInfoBean.getCaseRelshp());

						model.addAttribute("ob", ob);

						returnPage = AppConstants.ECOA_PARENT_CASE_OBLIGATION;

					}
					else
					{
						if (!request.getParameter("errorMsg").trim().isEmpty())
						{

							returnPage = "forward:/cssp/user/CaseList/caseObligation.htm";//forward is used to use request object in the caseList controller.
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
}
