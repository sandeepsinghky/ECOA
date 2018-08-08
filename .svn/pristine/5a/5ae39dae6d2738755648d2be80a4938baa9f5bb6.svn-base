package nc.dhhs.nccss.acts.ecoa.web.controller.parents;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import nc.dhhs.nccss.acts.ecoa.web.exception.ErrorDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
public class ParentCaseInfoController extends BasicAnnotatedFormController
{

	@Autowired
	private ParentCaseInfoService caseInfoService;

	/**
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cssp/user/CaseList/{path}.htm")
	public String getParentCaseList(@PathVariable("path") String path, HttpServletRequest request, Model model)
			throws Exception

	{
		logger.debug("\n********** In ParentCaseInfoController: getParentCaseList **********\n");

		ArrayList<ParentCaseInfo> caseList = null;
		try
		{
			UserInformation userInfoBean = (UserInformation) request.getSession().getAttribute(AppConstants.USERINFORMATION);

			if (request.getSession().getAttribute(AppConstants.caseList) == null)

			{
				caseList = caseInfoService.getCaseList(userInfoBean);

				request.getSession().setAttribute(AppConstants.caseList, caseList);

			}

			else
			{

				caseList = (ArrayList) request.getSession().getAttribute(AppConstants.caseList);

			}

			for (ParentCaseInfo cb : caseList)
			{

				String colorCode = "#000000"; //black color
				String errorMsg = "";

				if (cb.getCaseStatus().equals("OPEN"))
				{
					if ((cb.isProtectiveOrder()) || (cb.isVoilenceExists()))
					{

						colorCode = "#630063"; ////purple color
						errorMsg = "Payment information only.";

					}
					else
					{
						colorCode = "#000000";
						//href= "Navigation?destinationServlet=" + destinationServlet + "&ivdCase=" + cb.getIvdCase();
						errorMsg = "";
					}
				}
				else if (cb.getCaseType().trim().equals("NIVD"))
				{
					if (cb.getClosureReason().equals("CSCR"))
					{
						colorCode = "#009C00"; //green color.
						errorMsg = "Payment information only.";
					}
					else if (cb.getClosureReason().equals("CDEL"))
					{
						colorCode = "#9C6300"; //brownColor
						errorMsg = "Case is closed. Payment information only.";
					}
				}
				else
				{
					colorCode = "#9C6300"; //brownColor
					errorMsg = "Case is closed. Payment information only.";
				}

				cb.setErrorMsg(errorMsg);
				cb.setColorCode(colorCode);

			}

			model.addAttribute("path", path);

			//when the request is forwarded from caseDetail.htm controller with the condition selected case has existing error message. 

			if (request.getParameter("errorMsg") != null && !request.getParameter("errorMsg").trim().isEmpty())

			{
				model.addAttribute("errorMsg", request.getParameter("errorMsg") + " For other information, please contact your local Child Support Office or Clerk of Court Office");
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

		return AppConstants.ECOA_PARENT_CASELIST_VIEW;
	}

}
