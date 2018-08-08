package nc.dhhs.nccss.acts.ecoa.web.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.security.Principal;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import nc.dhhs.nccss.acts.ecoa.web.exception.ErrorDescriptor;
import nc.dhhs.nccss.acts.ecoa.beans.GuideLines;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;


/**
 * @author Mallika Velagapudi
 *
 */
@Controller
public class CSSGeneralInfoController extends BasicAnnotatedFormController
{

    @Autowired
	ServletContext servletContext;
	/**
	 * @return
	 */
	@RequestMapping("/loggedInHome.htm")
	public String getLoggedIndex(final Principal principal)
	{
		if (null == principal) return "redirect:/index.jsp";

		return AppConstants.ECOA_LOGGEDIN_HOME;
	}

	@RequestMapping("/caseApplicationInfo/{id}.htm")
	public String getApplInfo(@PathVariable("id") int id)
	{
		if (id == 1) { return AppConstants.ECOA_CASE_APPLN_INFO; }

		return AppConstants.ECOA_PARENT_CASE_APPLN_INFO;
	}

	/**
	 * @return
	 */
	@RequestMapping("/caseApplicationSteps/{id}.htm")
	public String getApplSteps(@PathVariable("id") int id)
	{
		if (id == 1) { return AppConstants.ECOA_CASE_APPLN_STEPS; }
		return AppConstants.ECOA_PARENT_CASE_APPLN_STEPS;
	}

	/**
	 * @return
	 */
	@RequestMapping("/anonymousTipLine.htm")
	public String getAnonymousTipLine()
	{
		return AppConstants.ECOA_CSS_TIP_LINE;
	}

	/**
	 * @return
	 */
	@RequestMapping("/cssProgramInfo/{id}.htm")
	public String getProgramInfo(@PathVariable("id") int id)
	{
		if (id == 1) { return AppConstants.ECOA_CSS_PRG_INFO; }

		return AppConstants.ECOA_PARENT_CSS_PRG_INFO;
	}

	@RequestMapping(value = "/cssProgramInfo/{id}.htm", method = RequestMethod.POST)
	public String getCaseApplicationList(@PathVariable("id") int id, final Principal principal, HttpServletRequest request)
	{

		logger.debug("\n********** IN getCaseApplicationList(id= " + id + ", principal, HttpServletRequest request) **********\n");

		String returnPage = "";
		//check if the user is authenticated or not to move forward

		if (null == principal) return "redirect:/login.htm?u=ncId";

		try
		{
			HttpSession session = request.getSession();

			String userType = session.getAttribute("userType").toString();
			logger.info("&&&&&&&&&&& USER TYPE  " + userType + "****************");
			// existing parents should logout and log backin with ncid credentials 
			if (null != principal && userType != null && userType.equals("tcaId")) { return "redirect:/interimLogout.htm?"; }
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
		}

		if (id == 1) { return "redirect:/cssp/user/caseApplicationList/1.htm"; }
		if (id == 2) { return "redirect:/cssp/user/caseApplicationList/2.htm"; }

		logger.info("\n************ returnPage: " + returnPage + "*************\n");

		return returnPage;

	}

	/**
	 * @return
	 */
	@RequestMapping(value = "/interimLogout.htm", method = RequestMethod.POST)
	public String getLoginScreenAfterLogOut(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		String userType = "ncId"; // set it to ncId for sending existing user to the appropriate login screen to login as ncid user

		session.setAttribute("userType", userType);

		return "redirect:/logout.htm";
	}

	@RequestMapping("/interimLogout.htm")
	public String getParentsInterimLogOut()
	{
		return AppConstants.INTLOGOUT;
	}

	@RequestMapping("/progFeesAndPolicies/{id}.htm")
	public String getProgramFeesAndPoliciesInfo(@PathVariable("id") int id)
	{
		if (id == 1)

			return AppConstants.ECOA_HOME_PRG_FEES_N_POLICIES;
		else
			return AppConstants.ECOA_PARENT_PRG_FEES_N_POLICIES;
	}

	@RequestMapping(value = "/contactUs/{id}.htm")
	public String getContactInformation(@PathVariable("id") int id)
	{
		if (id == 1)

			return AppConstants.ECOA_HOME_CONTACT;
		else
			return AppConstants.ECOA_PARENT_CONTACT;
	}

	@RequestMapping(value = "/feedBack/{id}.htm")
	public String getFeedBack(@PathVariable("id") int id, Model model)
	{
		model.addAttribute("pageId", id);
		if (id == 1)

			return AppConstants.ECOA_HOME_FEEDBACK;
		else
			return AppConstants.ECOA_PARENT_FEEDBACK;
	}

	@RequestMapping(value = "/feedBack.htm")
	public String getFeedBack()
	{

		return AppConstants.ECOA_HOME_FEEDBACK;

	}

	@RequestMapping("/privacyPolicy/{id}.htm")
	public String getPrivacyPolicy(@PathVariable("id") int id)
	{
		if (id == 1)

			return AppConstants.ECOA_HOME_PRIVACY_POLICY;
		else
			return AppConstants.ECOA_PARENT_PRIVACY_POLICY;
	}

	@RequestMapping("/caseFinancialHelp/{id}.htm")
	public String getCaseFinancialHelp(@PathVariable("id") int id)
	{
		if (id == 1)
		{
			return AppConstants.ECOA_CASE_FINANCIAL_HELP;
		}
		else
		{
			return AppConstants.ECOA_PARENT_CASE_FINANCIAL_HELP;
		}
	}

	@RequestMapping("/loginHelp/{id}.htm")
	public String getLoginHelp(@PathVariable("id") int id)
	{
		if (id == 1)
		{
			return AppConstants.ECOA_LOGIN_HELP;
		}
		else
			return AppConstants.ECOA_PARENTS_LOGIN_HELP;
	}

	@RequestMapping("/guideLinesTable.htm")
	public String getGuideLinesTable()
	{
		return AppConstants.ECOA_PARENT_GUIDELINES_TABLE;
	}

	@RequestMapping("/cseGuideLines.htm")
	public String getGuideLines()
	{
		return AppConstants.ECOA_PARENT_GUIDELINES;
	}

	@RequestMapping("/cseGuideLineDetails.htm")
	public String getGuideLineDetails()
	{
		return AppConstants.ECOA_PARENT_GUIDELINE_DETAILS;
	}

	@RequestMapping("/workSheetA.htm")
	public String getPrimaryCustody()
	{
		return AppConstants.ECOA_PARENT_WORKSHEETA;
	}

	@RequestMapping("/legend.htm")
	public String getColorInfo()
	{
		return AppConstants.ECOA_PARENTS_COLOR_LEGEND;
	}

	@RequestMapping("/workSheetB.htm")
	public String getJointCustody()
	{
		return AppConstants.ECOA_PARENT_WORKSHEETB;
	}

	@RequestMapping("/workSheetC.htm")
	public String getSplitCustody()
	{
		return AppConstants.ECOA_PARENT_WORKSHEETC;
	}
	
	@RequestMapping("/getGuideLinesTable.htm")
	public String getChildObligation(HttpServletRequest request, Model model) {
		logger.debug("\n********** IN CSSGeneralInfoController: getChildObligation**********\n");

		String returnPage = "";

		GuideLines guideLine = null;

		ArrayList<GuideLines> guideLineList = new ArrayList<GuideLines>();

		try {

			String filePath = servletContext.getRealPath("/WEB-INF");

			filePath = filePath + "/GuideLines.dat";

			FileReader fin = null;

			BufferedReader din = null;

			String str = null;

			fin = new FileReader(filePath);

			din = new BufferedReader(fin);

			int shadeCounter = 0;

			int childmin = 1;
			int child1max = 8;
			int child2max = 15;
			int child3max = 21;
			int child4max = 26;
			int child5max = 32;
			int child6max = 38;

			while ((str = din.readLine()) != null) {
				StringTokenizer token = new StringTokenizer(str, ":");

				guideLine = new GuideLines();

				while (token.hasMoreTokens()) {
					try {

						shadeCounter++;
						guideLine.setGdlnIncome(Double.parseDouble(token.nextToken()));
						guideLine.setGdlnAmtChild1(Double.parseDouble(token.nextToken()));
						guideLine.setGdlnAmtChild2(Double.parseDouble(token.nextToken()));
						guideLine.setGdlnAmtChild3(Double.parseDouble(token.nextToken()));
						guideLine.setGdlnAmtChild4(Double.parseDouble(token.nextToken()));
						guideLine.setGdlnAmtChild5(Double.parseDouble(token.nextToken()));
						guideLine.setGdlnAmtChild6(Double.parseDouble(token.nextToken()));
					} catch (NumberFormatException e) {
						logger.error(e.getMessage());
					}
				}

				guideLineList.add(guideLine);

			}

			model.addAttribute("guideLines", guideLineList);

			returnPage = AppConstants.ECOA_PARENT_GUIDELINES_TABLE;
		}

		catch (Exception e) {

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
