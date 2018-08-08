/**
 * 
 */
package nc.dhhs.nccss.acts.ecoa.web.controller.parents;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import nc.dhhs.nccss.acts.ecoa.web.exception.ErrorDescriptor;
import nc.dhhs.nccss.acts.ecoa.beans.UserInformation;
import nc.dhhs.nccss.acts.ecoa.web.controller.BasicAnnotatedFormController;
import nc.dhhs.nccss.acts.ecoa.web.service.CaseApplicationService;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * @author Vijay Peddapalli
 *
 */
@Controller
public class ParentAccountSummaryController extends BasicAnnotatedFormController
{
	@Autowired
	protected CaseApplicationService caseApplService;
	
	@RequestMapping("/cssp/user/parentLoggedInHome.htm")
	public String getParentView(HttpServletRequest request, Model model)
	{
		logger.info("\n************ In ParentAccountSummaryController:getParentView *************\n");
		UserInformation userInfo = (UserInformation) request.getSession().getAttribute(AppConstants.USERINFORMATION);

		if (userInfo.isMpiTied())
		{
			return "redirect:/cssp/user/parentWelcome.htm"; 

		}
		else
			return "redirect:/cssp/user/userWelcome.htm";
	}

	
	@RequestMapping("/cssp/user/parentWelcome.htm")
	public String getParentWelcomeView(HttpServletRequest request, Model model)
	{
		logger.info("\n************ In ParentAccountSummaryController:getParentWelcomeView *************\n");

		if (request.getAttribute("errorMsg") != null)
		{
			if (!((String) request.getAttribute("errorMsg")).trim().isEmpty())
			{
				model.addAttribute("errorMsg", (String) request.getAttribute("errorMsg"));
			}

		}
		return AppConstants.ECOA_PARENT_WELCOME;
	}

	@RequestMapping("/cssp/user/processDualRole.htm")
	public String processDualRole(HttpServletRequest request, Model model)
	{
		logger.info("\n************ In ParentAccountSummaryController:in processDualRole *************\n");
		try
		{
			UserInformation userInfo = (UserInformation) request.getSession().getAttribute(AppConstants.USERINFORMATION);

			if (request.getParameter("selectedRole") != null && !request.getParameter("selectedRole").equals(""))
			{
				String selectedRole = request.getParameter("selectedRole");

				if (selectedRole.equals("CLI"))
				{
					userInfo.setCaseRelshp("CLI");
				}
				else if (selectedRole.equals("AP"))
				{
					userInfo.setCaseRelshp("AP");
				}
			}
			// Now invalidate the caseList and ivdCase that has been set in the session
			//need to add code here.

			request.getSession().removeAttribute(AppConstants.caseList);

			request.getSession().removeAttribute(AppConstants.ivdCase);
			
			request.getSession().removeAttribute(AppConstants.otherPartyName);
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
		return AppConstants.ECOA_PARENT_WELCOME;

	}

}
