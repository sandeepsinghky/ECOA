package nc.dhhs.nccss.acts.ecoa.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import nc.dhhs.nccss.acts.ecoa.beans.UserInformation;
import nc.dhhs.nccss.acts.ecoa.beans.WorkFlow;
import nc.dhhs.nccss.acts.ecoa.web.config.WebsiteConfiguration;
import nc.dhhs.nccss.acts.ecoa.web.exception.ErrorDescriptor;
import nc.dhhs.nccss.acts.ecoa.web.service.WorkFlowService;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;
import nc.dhhs.nccss.acts.ecoa.web.util.EmailManager;

/**
 * @author Vijay Peddapalli
 *
 */
@Controller
public class ErrorController extends BasicAnnotatedFormController {
	@Autowired
	protected WorkFlowService workFlowService;
	@Autowired
	protected EmailManager emailManager;

	/**
	 * <p>
	 * Renders error page
	 * </p>
	 * 
	 * @return page to load
	 */
	@RequestMapping("/apperror.htm")
	public String processApplerror(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		ErrorDescriptor errorBean = (ErrorDescriptor) request.getAttribute("errorBean");
		errorBean.setUserId((String) session.getAttribute("userLoginName"));
		try {
			if (!errorBean.getApplicationId().equals("")) {
				// retrieve step #
				long stepNum = workFlowService.getStepNumber(errorBean.getApplicationId(),
						AppConstants.FLOW_TYPE_APPL_SCREEN);
				WorkFlow workFlow = new WorkFlow();
				workFlow.setStepNum(stepNum);
				model.addAttribute("flow", workFlow);
			}
			emailManager.sendEmail(WebsiteConfiguration.getEmailNotify(), AppConstants.MAIL_FROM,
					AppConstants.MAIL_ERROR_SUBJECT, errorBean.getEmailBody());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		// model.addAttribute("message", AppConstants.APPLICATION_ERROR);
		return AppConstants.APP_ERROR;
	}

	@RequestMapping("/error.htm")
	public String processError(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		ErrorDescriptor errorBean = (ErrorDescriptor) request.getAttribute("errorBean");
		errorBean.setUserId((String) session.getAttribute("userLoginName"));
		try {
			emailManager.sendEmail(WebsiteConfiguration.getEmailNotify(), AppConstants.MAIL_FROM,
					AppConstants.MAIL_ERROR_SUBJECT, errorBean.getEmailBody());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return AppConstants.ERROR;
	}

	@RequestMapping("/parentError.htm")
	public String getParentError(HttpServletRequest request, Model model) {

		logger.info("\n********** IN error Controller: getParentError **********\n");

		String body = "";

		boolean isLogOut = false;

		ErrorDescriptor errorBean = (ErrorDescriptor) request.getAttribute("errorBean");

		UserInformation userInfoBean = (UserInformation) request.getSession()
				.getAttribute(AppConstants.USERINFORMATION);

		String requestName = (String) request.getAttribute("requestName");

		if (requestName != null && requestName.contains("loginRoute.htm")) {

			isLogOut = true;

			errorBean.setUserType((String) request.getAttribute("userType"));

			logger.info("\n********** IN error Controller: loginRoute loop :userType: "
					+ (String) request.getAttribute("userType") + " **********\n");

			errorBean.setUserId((String) request.getAttribute("loginName"));

			logger.info("\n********** IN error Controller: loginRoute loop :loginUser: "
					+ (String) request.getAttribute("loginName") + " **********\n");

			body = errorBean.getEmailBody();

			logger.info("\n********** IN error Controller: loginRoute loop :after getEmailBody call  **********\n");

		} else {

			if (userInfoBean != null) {

				body = errorBean.getParentEmailBody(userInfoBean);

			} else {

				body = errorBean.getEmailBody();
			}

		}

		emailManager.sendEmail(WebsiteConfiguration.getEmailNotify(), AppConstants.MAIL_FROM,
				AppConstants.MAIL_ERROR_SUBJECT, body);

		model.addAttribute("isLogOut", isLogOut);

		return AppConstants.ECOA_PARENT_ERRORPAGE;
	}

	@RequestMapping("/404.htm")
	public String getPageNotFoundErrPage() {
		return AppConstants.PAGE_NOT_FOUND;
	}

	@RequestMapping("/403.htm")
	public String getAccessDeniedErrPage() {
		return AppConstants.ACCESS_DENIED;
	}
}
