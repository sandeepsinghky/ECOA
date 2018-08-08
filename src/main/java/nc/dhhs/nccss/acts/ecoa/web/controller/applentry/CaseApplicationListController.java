package nc.dhhs.nccss.acts.ecoa.web.controller.applentry;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import nc.dhhs.nccss.acts.ecoa.beans.CaseApplication;
import nc.dhhs.nccss.acts.ecoa.web.controller.BasicAnnotatedFormController;
import nc.dhhs.nccss.acts.ecoa.web.exception.ErrorDescriptor;
import nc.dhhs.nccss.acts.ecoa.web.service.CaseApplicationService;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * @author Phani Konuru
 *
 */
@Controller
public class CaseApplicationListController extends BasicAnnotatedFormController
{

	@Autowired
	protected CaseApplicationService caseApplService;

	/**
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/cssp/user/caseApplicationList/{id}.htm")
	public String userCaseApplicationList(HttpServletRequest request, Model model, @PathVariable("id") int id)
	{
		logger.debug("\n********** IN CaseApplicationListController: userCaseApplicationList**********\n");

		HttpSession session = request.getSession();
		session.setAttribute(AppConstants.IS_APPLICATION_SELECTED, "false");

		session.setAttribute("navid", id); //setting here to pass it on to aplicationRNR.jsp for previous button to work correctly.

		String userId = (String) session.getAttribute(AppConstants.USER_LOGIN_NAME);

		session.setAttribute(AppConstants.HAS_ACTIVE_ONLINE_APPLICATION, false);
		session.setAttribute(AppConstants.SELECTED_APPLICATION, null);
		session.setAttribute(AppConstants.SELECTED_NCP, null);
		session.setAttribute(AppConstants.SELECTED_CHILD, null);
		session.setAttribute(AppConstants.APPL_CUSTODIAL_PARENT, null);
		session.setAttribute(AppConstants.APPL_APPLICANT, null);
		try
		{
			List<CaseApplication> appList = caseApplService.getCaseApplicationByFieldName(AppConstants.APP_FIELD_NCID, userId);

			if (appList.size() > 0)
			{
				for (CaseApplication app : appList)
				{
					if (app.getApplStatus().equals(AppConstants.APP_STATUS_ACTIVE))
					{
						session.setAttribute(AppConstants.HAS_ACTIVE_ONLINE_APPLICATION, true);
						break;
					}
				}
			}

			logger.info("Applications for: " + userId);

			model.addAttribute("appList", appList);
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
			model.addAttribute("message",AppConstants.APPLICATION_ERROR);
			ErrorDescriptor error = new ErrorDescriptor(this.getClass().getName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage(), e);
			if(session.getAttribute("selectedApp") != null)
				error.setApplicationId((String)session.getAttribute("selectedApp"));
			request.setAttribute("errorBean", error);
			error = null;
			return "forward:/error.htm";
		}
		if (id == 2) { return AppConstants.ECOA_PARENT_CASE_APPLN_LIST; }
		return AppConstants.ECOA_CASE_APPLN_LIST;
	}

	/**
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/cssp/user/caseApplicationList/{id}.htm", method = RequestMethod.POST)
	public String processCaseApplication(HttpServletRequest request, Model model, @PathVariable("id") int id)
	{
		logger.debug("\n********** IN CaseApplicationListController: processCaseApplication**********\n");

		String action = (String) request.getParameter("action");
		String selectedapp = (String) request.getParameter("selectappl");
		String userId = (String) request.getSession().getAttribute(AppConstants.USER_LOGIN_NAME);
		String screen_to_go = "";
		HttpSession session = request.getSession();
		try
		{
			if (action.equals("deleteApplication"))
			{
				logger.info("action: deleteApplication");

				String rValue = caseApplService.deleteCaseApplication(selectedapp, userId);

				if (rValue != null && rValue.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
				{
					model.addAttribute("success", "Application has been deleted successfully.");
					
				}
				else
				{
					//model.addAttribute("message", "Problem encountered during delete!!");
					throw new Exception("CaseApplicationListController: processCaseApplication: application delete failed. Return code: "+rValue);
				}

				if (id == 2)
				{
					//screen_to_go = AppConstants.ECOA_PARENT_CASE_APPLN_LIST;
					screen_to_go = "redirect:/cssp/user/caseApplicationList/2.htm";
				}
				else
				{
					//screen_to_go = AppConstants.ECOA_CASE_APPLN_LIST;
					screen_to_go = "redirect:/cssp/user/caseApplicationList/1.htm";
				}
			}
			else if (action.equals("selectApplication"))
			{
				logger.info("action: selectApplication");

				session.setAttribute("selectedApp", selectedapp);
				session.setAttribute(AppConstants.IS_APPLICATION_SELECTED, "true");
				screen_to_go = "redirect:/cssp/user/applicantRNR.htm";
				//screen_to_go = "redirect:applicantRNR.htm";
			}
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
			/*if (id == 2)
			{
				screen_to_go = "redirect:/cssp/user/caseApplicationList/2.htm";
			}
			else
			{
				screen_to_go = "redirect:/cssp/user/caseApplicationList/1.htm";
			}*/
			model.addAttribute("message",AppConstants.APPLICATION_ERROR);
			ErrorDescriptor error = new ErrorDescriptor(this.getClass().getName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage(), e);
			if(session.getAttribute("selectedApp") != null)
				error.setApplicationId((String)session.getAttribute("selectedApp"));
			request.setAttribute("errorBean", error);
			error = null;
			return "forward:/error.htm";
		}

		return screen_to_go;

	}

}
