package nc.dhhs.nccss.acts.ecoa.web.controller.applentry;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import nc.dhhs.nccss.acts.ecoa.beans.CaseApplication;
import nc.dhhs.nccss.acts.ecoa.beans.UserSignature;
import nc.dhhs.nccss.acts.ecoa.beans.WorkFlow;
import nc.dhhs.nccss.acts.ecoa.web.config.WebsiteConfiguration;
import nc.dhhs.nccss.acts.ecoa.web.controller.BasicAnnotatedFormController;
import nc.dhhs.nccss.acts.ecoa.web.exception.ErrorDescriptor;
import nc.dhhs.nccss.acts.ecoa.web.service.CaseApplicationService;
import nc.dhhs.nccss.acts.ecoa.web.service.UserSignatureService;
import nc.dhhs.nccss.acts.ecoa.web.service.WorkFlowService;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;
import nc.dhhs.nccss.acts.ecoa.web.util.EmailManager;

/**
 * @author Phani Konuru
 *
 */
@Controller
@SessionAttributes("applicationId")
public class CaseApplicationSubmitController extends BasicAnnotatedFormController
{

	@Autowired
	protected CaseApplicationService	caseApplService;

	@Autowired
	protected WorkFlowService			workFlowService;

	@Autowired
	protected UserSignatureService		userSignatureService;
	
	@Autowired
	protected EmailManager				emailManager;

	/**
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/cssp/user/caseApplicationSubmission.htm")
	public String execute(HttpServletRequest request, Model model)
	{
		logger.debug("\n********** IN CaseApplicationSubmitController: execute **********\n");

		HttpSession session = request.getSession();
		try
		{
			if (session.getAttribute("selectedApp") != null)
			{
				model.addAttribute("isApp", request.getSession().getAttribute(AppConstants.IS_APPLICATION_SELECTED));
				CaseApplication appBean = caseApplService.getCaseApplicationByAppId((String) session.getAttribute(AppConstants.SELECTED_APPLICATION)).get(0);
				WorkFlow workFlow = new WorkFlow();

				if (appBean != null)
				{
					if (appBean.isApplSbmtd())
					{
						request.getSession().setAttribute(AppConstants.APPL_SUBMITTED, true);
					}
					long progNum = workFlowService.getStepNumber(appBean.getApplicationId(), AppConstants.FLOW_TYPE_APPL_SCREEN);
					workFlow.setStepNum(progNum);

					logger.info("\n**********StepNum from DB: " + progNum + "**********\n");

					List<UserSignature> signBean = userSignatureService.getCaseApplicationSignature(appBean.getApplicationId(), AppConstants.USER_SIGN_TYPE_3);

					if (signBean != null && signBean.size() > 0)
					{
						model.addAttribute("signBean", signBean.get(0));
					}
					else
					{
						model.addAttribute("defaultDate", new SimpleDateFormat("MM/dd/yyyy").format(new Date()));
					}

				}

				model.addAttribute("flow", workFlow);
				model.addAttribute("appBean", appBean);
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
		return AppConstants.ECOA_CASE_APPLN_SUBMISSION;
	}

	/**
	 * @param request
	 * @param response
	 * @param appBean
	 * @return
	 */
	@RequestMapping(value = "/cssp/user/caseApplicationSubmission.htm", method = RequestMethod.POST)
	public String updateCaseApplicationSubmit(HttpServletRequest request, HttpServletResponse response, Model model, @ModelAttribute("appBean") CaseApplication appBean)
	{

		logger.debug("\n********** IN CaseApplicationSubmitController: updateCaseApplicationSubmit post**********\n");

		HttpSession session = request.getSession();

		String action = request.getParameter("action");
		String rValue = AppConstants.OPERATION_SUCCESS;

		String signFN = "";
		String signLN = "";
		String signMI = "";

		try
		{
			if (action.equals("print"))
			{
				logger.info("\n********* *********** Going to PRINT ********** ********** ");

				return AppConstants.ECOA_REPORTS;
			}

			if (action.equals("sbmtbtn"))
			{
				rValue = caseApplService.updateCaseApplicationSubmit(appBean);
			}

			if (rValue != null && rValue.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
			{
				String userId = (String) session.getAttribute(AppConstants.USER_LOGIN_NAME);

				if (request.getParameter("signFirstName") != null && request.getParameter("signFirstName") != "")
				{
					signFN = request.getParameter("signFirstName").trim();
				}

				if (request.getParameter("signMiddleInitial") != null && request.getParameter("signMiddleInitial") != "")
				{
					signMI = request.getParameter("signMiddleInitial").trim();
				}

				if (request.getParameter("signLastName") != null && request.getParameter("signLastName") != "")
				{
					signLN = request.getParameter("signLastName").trim();
				}

				UserSignature signBean = setUserSignature(appBean.getApplicationId(), signFN, signMI, signLN);
				if (request.getParameter("ackSbmt") != null)
				{
					if (request.getParameter("ackSbmt") != null)
					{
						signBean.setHasChecked(AppConstants.CONDITION_TRUE);
					}
					else
					{
						signBean.setHasChecked("");
					}
				}
				rValue = userSignatureService.insertOrUpdateSignature(signBean, userId, AppConstants.OPERATION_INSERT);

				if (rValue != null && rValue.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
				{
					if (action.equals("sbmtbtn") && workFlowService.getStepNumber(appBean.getApplicationId(), AppConstants.FLOW_TYPE_APPL_SCREEN) < 7)
					{
						rValue = workFlowService.updateStepNumber(appBean.getApplicationId(), AppConstants.FLOW_TYPE_APPL_SCREEN, 7, userId);

						if (rValue != null && rValue.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
						{
							model.addAttribute("success", "Your application has been submitted successfully!!");
						}
						else
						{
							//model.addAttribute("message", "Problem encountered during saving!!");
							throw new Exception("CaseApplicationSubmitController: updateCaseApplicationSubmit: update step number failed. Return code: "+rValue);
						}
					}
					else
					{
						if (action.equals("sbmtbtn"))
						{
							model.addAttribute("success", "Your application has been submitted successfully!!");
						}
						else
						{
							model.addAttribute("success", "Your application has been saved successfully!!");
						}
					}
				}
				else
				{
					/*if (action.equals("sbmtbtn"))
					{
						model.addAttribute("message", "Problem encountered during submission!!");
					}
					else
					{
						model.addAttribute("message", "Problem encountered during save!!");
					}*/
					throw new Exception("CaseApplicationSubmitController: updateCaseApplicationSubmit: save/submit failed. Return code: "+rValue);
					
				}

				//check if there are any active applications
				List<CaseApplication> appList = caseApplService.getCaseApplicationByFieldName(AppConstants.APP_FIELD_NCID, userId);

				if (appList.size() > 0)
				{
					boolean activeApp = false;
					for (CaseApplication app : appList)
					{
						if (app.getApplStatus().equals(AppConstants.APP_STATUS_ACTIVE))
						{
							activeApp = true;
							break;
						}
					}

					if (!activeApp) session.setAttribute(AppConstants.HAS_ACTIVE_ONLINE_APPLICATION, false);
				}
				if (action.equals("sbmtbtn"))
				{
					//send email
					CaseApplication caseApp = caseApplService.getCaseApplicationByAppId(appBean.getApplicationId()).get(0);
					sendEmail(caseApp.getApplicationId(), caseApp.getEmailId());
				}
			}
			else
			{
				//model.addAttribute("message", "Problem encountered during submission!!");
				throw new Exception("CaseApplicationSubmitController: updateCaseApplicationSubmit: application submit failed. Return code: "+rValue);
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
		return "redirect:/cssp/user/caseApplicationSubmission.htm";
		//return AppConstants.ECOA_CASE_APPLN_SUBMISSION;
	}

	private UserSignature setUserSignature(String applId, String signFN, String signMI, String signLN)
	{
		UserSignature signBean = new UserSignature();

		signBean.setSignId(applId);

		signBean.setSignType(AppConstants.USER_SIGN_TYPE_3);

		if (signFN != "")
		{
			signBean.setNmSignF(signFN);
		}

		if (signMI != "")
		{
			signBean.setNmSignMI(signMI);
		}

		if (signLN != "")
		{
			signBean.setNmSignL(signLN);
		}

		return signBean;
	}
	
	private void sendEmail(String idReference, String toAddr)
	{
		// Send an email to the Customer
		StringBuffer sb1 = new StringBuffer();
		sb1.append(AppConstants.MAIL_TEXT);
		sb1.append(AppConstants.MAIL_SUBMIT_TEXT1 + idReference + ". ");
		sb1.append(AppConstants.MAIL_SUBMIT_TEXT2);
		sb1.append("\n\n");
		sb1.append(WebsiteConfiguration.getCseOfficeLocator());

		try
		{
			emailManager.sendEmail(toAddr, AppConstants.MAIL_FROM, AppConstants.MAIL_SUBJECT, sb1.toString());
		}
		catch (Exception e)
		{
			logger.error("Email error: " + e.getMessage());
		}
	}

}
