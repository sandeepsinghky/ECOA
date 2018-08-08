package nc.dhhs.nccss.acts.ecoa.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import nc.dhhs.nccss.acts.ecoa.beans.EcoaUser;
import nc.dhhs.nccss.acts.ecoa.web.service.UserService;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * Logout Controller takes care of setting usage related aspects before logging
 * out
 * 
 * @author Vijay Peddapalli
 *
 */
@Controller
public class LogoutController extends BasicAnnotatedFormController
{

	private static String	SPRING_LOGOUT_URL	= "j_spring_security_logout";

	private static String	uT;

	@Autowired
	private UserService		userService;

	/**
	 * @param request
	 * @return
	 */
	@RequestMapping("/logout.htm")
	public String execute(HttpServletRequest request)
	{
		HttpSession session = request.getSession();

		if (session != null)
		{
			//long usageId = (Long) session.getAttribute("usageId");

			try
			{
				logger.info("logging out .....");

				//Usage usage = getUsageService().getUsage(usageId);
				//usage.setLogoutTime(new Date());
				//getUsageService().updateUsage(usage);

				String userId = (String) session.getAttribute(AppConstants.USER_LOGIN_NAME);

				uT = session.getAttribute("userType").toString();

				EcoaUser dbUser = userService.getDBUser(userId);

				userService.createOrUpdateUserInfo(dbUser, AppConstants.OPERATION_UPDATE, AppConstants.LOGOUT);

				session.setAttribute(AppConstants.LOGOUT, true);

				logger.info("..........log out is successful .....");

			}
			catch (Exception e)
			{
				logger.error("Error occured while updating Usage" + e.getMessage());
			}

		}

		return "redirect:" + SPRING_LOGOUT_URL;
	}

	/**
	 * @return the usageService
	 */
	//public UsageService getUsageService()
	//{
	//	return usageService;
	//}

	/**
	 * @param usageService
	 *            the usageService to set
	 */
	//public void setUsageService(UsageService usageService)
	//{
	//	this.usageService = usageService;
	//}

	@RequestMapping("/logoutRoute.htm")
	public String getParentLoginScreens(HttpServletRequest request, Model model)
	{
		//HttpSession session = request.getSession();
		//String 		uT = session.getAttribute("userType").toString();

		String returnLogin = "";

		logger.debug("..............in  logout controller getParentLogin for " + uT + "......... .....");

		//String uT = session.getAttribute("userType").toString();
		try
		{
			if (uT != null && uT != "")
			{

				/*
				 * if (uT.equals("tcaId")) { // existing parents who are preNcId
				 * users returnLogin = "redirect:/loginp.htm?u=tcaId"; }
				 */

				/*
				 * if (uT.equals("pNcId")) { // PreNcId users migrated to NCID
				 * returnLogin = "redirect:/loginnp.htm?u=pNcId"; }
				 */
				if (uT.equals("ncId"))
				{
					// ncID users
					returnLogin = "redirect:/login.htm?u=ncId";
				}

				if (uT.equals("pNcId") || uT.equals("tcaId"))
				{
					// existing parents who are preNcId users 
					returnLogin = "redirect:/parentsIndex.jsp";
				}
			}
			else
				returnLogin = "redirect:/index.jsp";
		}
		catch (Exception e)
		{
			logger.error("Error occured while routing to login screen" + e.getMessage());
		}

		logger.info("..........logged out " + uT + "user .....");
		return returnLogin;
	}
}
