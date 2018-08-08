package nc.dhhs.nccss.acts.ecoa.web.controller.parents;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import nc.dhhs.nccss.acts.ecoa.web.exception.ErrorDescriptor;
import nc.dhhs.nccss.acts.ecoa.beans.ParentCaseInfo;
import nc.dhhs.nccss.acts.ecoa.beans.UserInformation;
import nc.dhhs.nccss.acts.ecoa.web.controller.BasicAnnotatedFormController;
import nc.dhhs.nccss.acts.ecoa.web.service.ParentCaseInfoService;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * @author Mallika Velagapudi
 *
 */
@Controller
public class ParentCaseDetailController extends BasicAnnotatedFormController
{

	@Autowired
	private ParentCaseInfoService caseInfoService;

	@RequestMapping("/cssp/user/caseDetail.htm")
	public String getParentCaseDetail(HttpServletRequest request, Model model) throws Exception

	{
		logger.debug("\n********** In ParentCaseInfoController: getParentCaseDetail **********\n");

		String returnPage = "";

		try
		{
			UserInformation userInfoBean = (UserInformation) request.getSession().getAttribute(AppConstants.USERINFORMATION);

			String mpi = userInfoBean.getMpi();
			
			if (mpi.isEmpty()) {

				request.setAttribute("errorMsg",
						"Registration process has to be completed to check Case Detail Information");

				return "forward:/cssp/user/userWelcome.htm";

			}

			if (userInfoBean.getCaseRelshp().trim().isEmpty()) //if user has dual role and role is not selected.

			{
				request.setAttribute("errorMsg", "Custodial Parent or Non-Custodial Parent selection is required");

				returnPage = "forward:/cssp/user/parentWelcome.htm";
			}

			else
			{

				if (request.getParameter("ivdCase") == null) //ivdCase request parameter will not be there when request comes by clicking on case information link.
				{
					if (request.getSession().getAttribute(AppConstants.ivdCase) != null)

					{
						String ivdCase = (String) request.getSession().getAttribute(AppConstants.ivdCase);

						ParentCaseInfo caseBean = caseInfoService.getCaseDetail(mpi, ivdCase);

						model.addAttribute("caseBean", caseBean);

						returnPage = AppConstants.ECOA_PARENT_CASE_DETAIL;

					}
					else
					{
						returnPage = "redirect:/cssp/user/CaseList/caseDetail.htm"; //user needs to select case to see details.so redirected to caseList

					}
				}

				else
				{
					if (request.getParameter("errorMsg") != null && request.getParameter("errorMsg").trim().isEmpty())
					{
						String ivdCase = request.getParameter("ivdCase");

						request.getSession().setAttribute(AppConstants.ivdCase, ivdCase);

						ParentCaseInfo caseBean = caseInfoService.getCaseDetail(mpi, ivdCase);
						
						request.getSession().setAttribute(AppConstants.otherPartyName, caseBean.getOtherName());

						model.addAttribute("caseBean", caseBean);

						returnPage = AppConstants.ECOA_PARENT_CASE_DETAIL;

					}
					else
					{
						if (!request.getParameter("errorMsg").trim().isEmpty())
						{

							returnPage = "forward:/cssp/user/CaseList/caseDetail.htm";//forward is used to use request object in the caseList controller.
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
