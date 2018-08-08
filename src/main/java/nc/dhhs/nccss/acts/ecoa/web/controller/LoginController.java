package nc.dhhs.nccss.acts.ecoa.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * @author Vijay Peddapalli
 *
 */
@Controller
public class LoginController extends BasicAnnotatedFormController {

	/**
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/login.htm")
	public String getNewApplicantUserLogin(HttpServletRequest request, Model model) {

		logger.debug("\n********** IN LoginController: getNewApplicantUserLogin **********\n");
		try {
			String userType = request.getParameter("u");
			HttpSession session = request.getSession();

			// to get erroneous login page case
			String err = request.getParameter("login_error");

			if (err != null && err != "") {
				userType = (String) request.getSession().getAttribute("userType");

				logger.info("\n********** error in logging " + err + " type of user " + userType + "**********\n");

				// redirect to appropriate login pages after hitting error
				if (userType != null && userType != "") {
					if (userType.equals("tcaId")) {
						logger.debug("\n********** IN redirecting to : loginp.htm?u=" + userType + "**********\n");

						return AppConstants.LOGINP;
					}
					if (userType.equals("ncId")) {
						logger.debug("\n********** IN redirecting to : login.htm?u=" + userType + "**********\n");

						return AppConstants.LOGIN;
					}
					if (userType.equals("pNcId")) {
						logger.debug("\n********** IN redirecting to : loginnp.htm?u=" + userType + "**********\n");

						return AppConstants.LOGINNP;
					}
				}
			}

			if (userType != null && userType != "") {
				logger.info("\n********** IN LoginController: getNewApplicantUserLogin " + userType + " **********\n");
				session.setAttribute("userType", userType);
			}
		}

		catch (Exception e) {
			logger.error("Error occured in Login Controller" + e.getMessage());
		}

		return AppConstants.LOGIN;
	}

	/**
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/loginp.htm")
	public String getParentLogin(HttpServletRequest request, Model model) {
		logger.debug("\n********** IN LoginController: getParentLogin **********\n");

		String userType = request.getParameter("u");
		HttpSession session = request.getSession();

		if (userType != null && userType != "") {
			logger.info("\n********** IN LoginController: getParentLogin " + userType + " **********\n");
			session.setAttribute("userType", userType);
		}

		return AppConstants.LOGINP;
	}

	/**
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/loginnp.htm")
	public String getNcIdParentLogin(HttpServletRequest request, Model model) {
		logger.debug("\n********** IN LoginController: getNcIdParentLogin **********\n");
		String userType = request.getParameter("u");

		// This errMsg is added to show errMsg when SP identifies same ipAPPress
		// has sent other login request.

		if (request.getAttribute("errMsg") != null) {

			model.addAttribute("errMsg", request.getAttribute("errMsg"));
		}
		HttpSession session = request.getSession();

		if (userType != null && userType != "") {
			logger.info("\n********** IN LoginController: getNcIdParentLogin " + userType + " **********\n");
			session.setAttribute("userType", userType);
		}

		return AppConstants.LOGINNP;
	}
	
	/**
	 * @return
	 */
	@RequestMapping("/loginerr.htm")
	public String errorLogin(HttpServletRequest request, Model model)
	{
		HttpSession session = request.getSession();
		if(session.getAttribute(AppConstants.LOGIN_ERROR_MSG) != null){
			logger.info((String)session.getAttribute(AppConstants.LOGIN_ERROR_MSG));
			model.addAttribute("message", (String)session.getAttribute(AppConstants.LOGIN_ERROR_MSG));
			session.setAttribute(AppConstants.LOGIN_ERROR_MSG, null);
		}
		return "redirect:/login.htm?login_error=1";
	}
}
