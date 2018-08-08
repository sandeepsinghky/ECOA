package nc.dhhs.nccss.acts.ecoa.web.controller.applentry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import nc.dhhs.nccss.acts.ecoa.beans.CaseApplication;
import nc.dhhs.nccss.acts.ecoa.beans.WorkFlow;
import nc.dhhs.nccss.acts.ecoa.web.controller.BasicAnnotatedFormController;
import nc.dhhs.nccss.acts.ecoa.web.exception.ErrorDescriptor;
import nc.dhhs.nccss.acts.ecoa.web.service.CaseApplicationService;
import nc.dhhs.nccss.acts.ecoa.web.service.WorkFlowService;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * @author Phani Konuru
 *
 */
@Controller
public class CaseApplicationMiscController extends BasicAnnotatedFormController
{

	@Autowired
	protected CaseApplicationService	caseApplService;
	@Autowired
	protected WorkFlowService			workFlowService;

	/**
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/cssp/user/caseApplicationMiscInfo.htm")
	public String getCaseApplictionMiscInfo(HttpServletRequest request, Model model)
	{
		logger.debug("\n********** IN CaseApplicationMiscController: getCaseApplictionMiscInfo**********\n");

		HttpSession session = request.getSession();
		try
		{
			if (session.getAttribute(AppConstants.SELECTED_APPLICATION) != null)
			{
				logger.info("session.getAttribute: " + AppConstants.SELECTED_APPLICATION);

				CaseApplication appBean = caseApplService.getCaseApplicationByAppId((String) session.getAttribute(AppConstants.SELECTED_APPLICATION)).get(0);

				WorkFlow workFlow = new WorkFlow();

				if (appBean != null)
				{
					long progNum = workFlowService.getStepNumber(appBean.getApplicationId(), AppConstants.FLOW_TYPE_APPL_SCREEN);

					workFlow.setStepNum(progNum);

					logger.info("\n**********StepNum from DB: " + progNum + "**********\n");
				}

				model.addAttribute("flow", workFlow);
				model.addAttribute("appBean", appBean);
				model.addAttribute("isApp", session.getAttribute(AppConstants.IS_APPLICATION_SELECTED));
			}
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
			return "forward:/apperror.htm";
		}
		return AppConstants.ECOA_CASE_APPLN_MISC_INFO;
	}

	/**
	 * @param request
	 * @param response
	 * @param appBean
	 * @return
	 */
	@RequestMapping(value = "/cssp/user/caseApplicationMiscInfo.htm", method = RequestMethod.POST)
	public String updateCaseApplicationNotes(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("appBean") CaseApplication appBean)
	{
		logger.debug("\n********** IN CaseApplicationMiscController: updateCaseApplicationNotes**********\n");
		HttpSession session = request.getSession();
		try
		{
			String rValue = caseApplService.updateCaseApplicationNotes(appBean);

			if (rValue != null && rValue.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
			{

				if (workFlowService.getStepNumber(appBean.getApplicationId(), AppConstants.FLOW_TYPE_APPL_SCREEN) < 6)
				{

					String userId = (String) session.getAttribute(AppConstants.USER_LOGIN_NAME);

					rValue = workFlowService.updateStepNumber(appBean.getApplicationId(), AppConstants.FLOW_TYPE_APPL_SCREEN, 6, userId);
				}

				if (rValue != null && rValue.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
				{
					model.addAttribute("success", "Information is saved successfully!!");
				}
				else
				{
					//model.addAttribute("message", "Problem encountered during saving!!");
					throw new Exception("CaseApplicationMiscController: updateCaseApplicationNotes: update step number failed. Return code: "+rValue);
				}
			}
			else
			{
				//model.addAttribute("message", "Problem encountered during saving!!");
				throw new Exception("CaseApplicationMiscController: updateCaseApplicationNotes: application notes update failed. Return code: "+rValue);
			}

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
			return "forward:/apperror.htm";
		}
		return "redirect:/cssp/user/caseApplicationMiscInfo.htm";
	}

}
