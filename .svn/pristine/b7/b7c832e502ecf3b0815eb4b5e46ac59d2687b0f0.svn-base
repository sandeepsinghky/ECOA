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

import nc.dhhs.nccss.acts.ecoa.beans.CaseApplication;
import nc.dhhs.nccss.acts.ecoa.beans.UserSignature;
import nc.dhhs.nccss.acts.ecoa.beans.WorkFlow;
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
public class ApplicantRNRController extends BasicAnnotatedFormController
{

	@Autowired
	protected CaseApplicationService	caseApplService;

	@Autowired
	protected UserSignatureService		userSignatureService;

	@Autowired
	protected WorkFlowService			workFlowService;

	@Autowired
	protected EmailManager				emailManager;

	/**
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/cssp/user/applicantRNR.htm")
	public String getRNR(HttpServletRequest request, Model model)
	{
		logger.debug("\n********** IN ApplicantRNRController: execute**********\n");
		HttpSession session = request.getSession();
		long progNum = 0;

		try
		{
			WorkFlow workFlow = new WorkFlow();
			//UserSignature signBean = new UserSignature();

			if (session.getAttribute("selectedApp") != null)
			{
				logger.info(" Session.getAttribute: " + (String) session.getAttribute(AppConstants.SELECTED_APPLICATION) + ".......");
				CaseApplication caseApp = caseApplService.getCaseApplicationByAppId((String) session.getAttribute(AppConstants.SELECTED_APPLICATION)).get(0);

				if (caseApp != null)
				{
					progNum = workFlowService.getStepNumber(caseApp.getApplicationId(), AppConstants.FLOW_TYPE_APPL_SCREEN);
					List<UserSignature> signBean = userSignatureService.getCaseApplicationSignature(caseApp.getApplicationId(), AppConstants.USER_SIGN_TYPE_1);

					workFlow.setStepNum(progNum);

					model.addAttribute("signBean", signBean.get(0));
				}
				request.getSession().setAttribute(AppConstants.IS_APPLICATION_SELECTED, "true");
				
				if(caseApp.getApplicantId() != null && !caseApp.getApplicantId().equals("")){
					request.getSession().setAttribute(AppConstants.APPL_APPLICANT, caseApp.getApplicantId());
				}
				if (caseApp.isApplSbmtd())
				{
					request.getSession().setAttribute(AppConstants.APPL_SUBMITTED, true);
				}
				else
				{
					request.getSession().setAttribute(AppConstants.APPL_SUBMITTED, false);
				}

				model.addAttribute("appBean", caseApp);
				//vjmodel.addAttribute("signBean", signBean);
				model.addAttribute("mode", AppConstants.DB_UPDATE);
			}
			else
			{
				CaseApplication appBean = new CaseApplication();
				
				if(session.getAttribute(AppConstants.USER_LOGIN_NAME) != null)
				{
					appBean.setNcId((String) session.getAttribute(AppConstants.USER_LOGIN_NAME));
				}
				else{
					logger.error("NCID id is null");
				}
				
				if(session.getAttribute(AppConstants.USER_EMAIL) != null)
				{
					appBean.setEmailId((String) session.getAttribute(AppConstants.USER_EMAIL));
				}
				else{
					logger.error("email id is null for  NCID: "+(String) session.getAttribute(AppConstants.USER_LOGIN_NAME));
				}
				
				workFlow.setStepNum(progNum);

				request.getSession().setAttribute(AppConstants.APPL_SUBMITTED, false);

				model.addAttribute("appBean", appBean);
				model.addAttribute("mode", AppConstants.DB_CREATE);
			}
			model.addAttribute("defaultDate", new SimpleDateFormat("MM/dd/yyyy").format(new Date()));
			model.addAttribute("flow", workFlow);
			model.addAttribute("isApp", request.getSession().getAttribute(AppConstants.IS_APPLICATION_SELECTED));
			model.addAttribute("navid", session.getAttribute("navid")); //adding to make previous button on the page to work correctly.
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

		return AppConstants.ECOA_APPLICANT_RNR;
	}

	@RequestMapping(value = "/cssp/user/applicantRNR.htm", method = RequestMethod.POST)
	public String processApplication(HttpServletRequest request, HttpServletResponse response, Model model, @ModelAttribute("appBean") CaseApplication appBean)
	{
		logger.debug("\n********** IN ApplicantRNRController: createNewApplication **********\n");

		String mode = request.getParameter("mode");
		HttpSession session = request.getSession();

		String rValue = "";
		String signFN = "";
		String signLN = "";
		String signMI = "";

		try
		{
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

			if (mode.equals(AppConstants.DB_CREATE))
			{
				logger.info("mode= " + AppConstants.DB_CREATE + "...........");

				// For the first time signature names are saved as the case application names to create application Id.
				// On the applicant info screen, user can change the name if they prefer to do so.
				// Signature names can stay separate to the given applicant names

				if (!signFN.isEmpty())
				{
					appBean.setFirstName(signFN);
				}
				if (!signMI.isEmpty())
				{
					appBean.setMiddleName(signMI);
				}
				if (!signLN.isEmpty())
				{
					appBean.setLastName(signLN);
				}

				String caseApplId = caseApplService.CreateCaseApplication(appBean);

				if (caseApplId != null && !caseApplId.equals(""))
				{
					session.setAttribute(AppConstants.HAS_ACTIVE_ONLINE_APPLICATION, true);
					
					session.setAttribute(AppConstants.APPL_APPLICANT, null);

					UserSignature signBean = setUserSignature(caseApplId, signFN, signMI, signLN);

					rValue = userSignatureService.insertOrUpdateSignature(signBean, appBean.getNcId(), AppConstants.OPERATION_INSERT);
					
					
					if (rValue != null && rValue.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
					{
						//insert flow# in db for the first time
						rValue = workFlowService.createNewStepNum(caseApplId, AppConstants.FLOW_TYPE_APPL_SCREEN, 1, appBean.getNcId());

						if (rValue != null && rValue.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
						{
							model.addAttribute("success", "Information is saved successfully.");

							//send email to the user with application reference number
							if(appBean.getEmailId()!=null && !appBean.getEmailId().equals(""))
							{
								sendEmail(caseApplId, appBean.getEmailId());
							}else{
								logger.error("No email id to send email");
							}
						}
						else
						{
							logger.debug("\n********** IN ApplicantRNRController: problem encountered when creating applKey **********\n");
							throw new Exception("ApplicantRNRController: processApplication: create: update step number failed. Return code: "+rValue);
						}
					}
					else
					{
						logger.debug("\n********** IN ApplicantRNRController: problem encountered when insertOrUpdateSignature **********\n");
						throw new Exception("ApplicantRNRController: processApplication: insertOrUpdateSignature failed. Return code: "+rValue);
					}

					request.getSession().setAttribute(AppConstants.SELECTED_APPLICATION, caseApplId);
					request.getSession().setAttribute(AppConstants.IS_APPLICATION_SELECTED, "true");
				}
				else
				{
					logger.debug("\n********** IN ApplicantRNRController: problem encountered when creating application **********\n");
						model.addAttribute("message", "Problem encountered during application creation!!");
					return "redirect:/cssp/user/applicantRNR.htm";
				}
			}
			else if (mode.equals(AppConstants.DB_UPDATE))
			{
				logger.info("mode= " + AppConstants.DB_UPDATE + ".........");

				//String rValue = caseApplService.updateCaseApplicationRNR(appBean);

				if (appBean.getApplicationId() != null && appBean.getApplicationId() != "")
				{

					UserSignature signBean = setUserSignature(appBean.getApplicationId(), signFN, signMI, signLN);

					rValue = userSignatureService.insertOrUpdateSignature(signBean, appBean.getNcId(), AppConstants.OPERATION_UPDATE);
					
					if(rValue != null && !rValue.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
					{
						throw new Exception("ApplicantRNRController: processApplication: insertOrUpdateSignature. Return code: "+rValue);
					}
					
					// check if the flow prog is 0, in that case, we missed it in the create
					if (workFlowService.getStepNumber(appBean.getApplicationId(), AppConstants.FLOW_TYPE_APPL_SCREEN) == 0)
					{
						rValue = workFlowService.updateStepNumber(appBean.getApplicationId(), AppConstants.FLOW_TYPE_APPL_SCREEN, 1, appBean.getNcId());
					}

					if (rValue != null && rValue.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
					{
						model.addAttribute("success", "Information is saved successfully.");
					}
					else
					{
						//model.addAttribute("message", "Problem encountered during saving!!");
						throw new Exception("ApplicantRNRController: processApplication: update step number failed. Return code: "+rValue);
					}
				}

			}

			model.addAttribute("isApp", request.getSession().getAttribute(AppConstants.IS_APPLICATION_SELECTED));
			appBean = caseApplService.getCaseApplicationByAppId((String) session.getAttribute(AppConstants.SELECTED_APPLICATION)).get(0);

			model.addAttribute("appBean", appBean);
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

		return "redirect:/cssp/user/applicantRNR.htm";
	}

	private void sendEmail(String idReference, String toAddr)
	{
		// Send an email to the Customer
		StringBuffer sb1 = new StringBuffer();
		sb1.append(AppConstants.MAIL_TEXT);
		sb1.append(AppConstants.MAIL_TEXT1 + idReference + ". ");
		sb1.append(AppConstants.MAIL_TEXT2);
		sb1.append("\n\n");

		try
		{
			emailManager.sendEmail(toAddr, AppConstants.MAIL_FROM, AppConstants.MAIL_SUBJECT, sb1.toString());
		}
		catch (Exception e)
		{
			logger.error("Email error: " + e.getMessage());
		}
	}

	private UserSignature setUserSignature(String applId, String signFN, String signMI, String signLN)
	{
		UserSignature signBean = new UserSignature();

		signBean.setSignId(applId);

		signBean.setSignType(AppConstants.USER_SIGN_TYPE_1);

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
		
		signBean.setHasChecked(AppConstants.CONDITION_TRUE);

		return signBean;
	}

}
