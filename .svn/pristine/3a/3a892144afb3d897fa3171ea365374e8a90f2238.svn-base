package nc.dhhs.nccss.acts.ecoa.web.controller.applentry;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
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
import nc.dhhs.nccss.acts.ecoa.beans.CasePartBenefit;
import nc.dhhs.nccss.acts.ecoa.beans.CaseParticipant;
import nc.dhhs.nccss.acts.ecoa.beans.CodeLookUp;
import nc.dhhs.nccss.acts.ecoa.beans.ThirdParty;
import nc.dhhs.nccss.acts.ecoa.beans.UserSignature;
import nc.dhhs.nccss.acts.ecoa.beans.WorkFlow;
import nc.dhhs.nccss.acts.ecoa.web.controller.BasicAnnotatedFormController;
import nc.dhhs.nccss.acts.ecoa.web.exception.ErrorDescriptor;
import nc.dhhs.nccss.acts.ecoa.web.service.CaseApplicationService;
import nc.dhhs.nccss.acts.ecoa.web.service.CaseParticipantService;
import nc.dhhs.nccss.acts.ecoa.web.service.UserSignatureService;
import nc.dhhs.nccss.acts.ecoa.web.service.WorkFlowService;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * @author Phani Konuru
 *
 */
@Controller
public class ApplicantInfoController extends BasicAnnotatedFormController
{

	@Autowired
	protected CaseApplicationService	caseApplService;

	@Autowired
	protected CaseParticipantService	casePartService;

	@Autowired
	protected WorkFlowService			workFlowService;

	@Autowired
	protected UserSignatureService		userSignatureService;

	/**
	 * @return
	 */
	@RequestMapping("/cssp/user/applicantInfo.htm")
	public String getApplicantInfo(HttpServletRequest request, Model model)
	{
		logger.debug("\n**********IN ApplicantInfoController: getApplicantInfo(request,model)**********\n");

		HttpSession session = request.getSession();
		ServletContext ctx = request.getServletContext();

		boolean enforceExists = false;
		boolean benefitExists = false;
		boolean thirdPartyAgencyExists = false;

		HashMap<String, String> benefitsMap = new HashMap();
		ThirdParty agencyBean = null;

		try
		{
			if (session.getAttribute(AppConstants.SELECTED_APPLICATION) != null)
			{

				String applId = (String) request.getSession().getAttribute(AppConstants.SELECTED_APPLICATION);

				logger.info("applId: " + applId + ".......");

				CaseApplication appInfo = caseApplService.getCaseApplicationByAppId(applId).get(0);

				CaseParticipant applicantInfo = new CaseParticipant();

				if (appInfo.getApplicantId() != null && !appInfo.getApplicantId().equals(""))
				{
					request.getSession().setAttribute(AppConstants.APPL_APPLICANT, appInfo.getApplicantId());
					
					model.addAttribute("isApp", request.getSession().getAttribute(AppConstants.IS_APPLICATION_SELECTED));

					String applicantId = appInfo.getApplicantId();

					applicantInfo = casePartService.getParticipantByPartId(applId, applicantId).get(0);

					logger.info("applicantId: " + applicantId + ".......");

					List<CaseParticipant> childrenInfo = casePartService.getParticipantByPartType(applId, AppConstants.CHLD_PARTICIPANT_TYPE);

					List<CasePartBenefit> casePartBenefits = casePartService.getCasePartBenefitsByFieldId(applId, applicantId, "");

					if (casePartBenefits != null && casePartBenefits.size() > 0)

					{
						for (CasePartBenefit benefit : casePartBenefits)

							benefitsMap.put(benefit.getBenefitCode().trim(), benefit.getBenefitCode().trim());

						benefitExists = true;

					}
					// Here trying to get Agency type thirdpary details , so its
					// value is "1"
					List<ThirdParty> thirdParties = casePartService.getThirdPartyByApplIdandType(applId, AppConstants.THIRDPARTY_TYPE_AGENCY);

					if (thirdParties.size() > 0)
					{
						agencyBean = thirdParties.get(0);

						thirdPartyAgencyExists = true;

					}

					model.addAttribute("mode", AppConstants.DB_UPDATE);
					model.addAttribute("applicantInfo", applicantInfo);
					model.addAttribute("appInfo", appInfo);
					model.addAttribute("childrenInfo", childrenInfo);
					enforceExists = checkEnforceExists(appInfo);

					model.addAttribute("benefitsMap", benefitsMap);
					model.addAttribute("agencyBean", agencyBean);

				}
				else
				{

					model.addAttribute("mode", AppConstants.DB_CREATE);

					applicantInfo.setApplicantFNm(appInfo.getFirstName());
					applicantInfo.setApplicantMNm(appInfo.getMiddleName());
					applicantInfo.setApplicantLNm(appInfo.getLastName());

					model.addAttribute("applicantInfo", applicantInfo);
					model.addAttribute("appInfo", appInfo);
					model.addAttribute("defaultDate", new SimpleDateFormat("MM/dd/yyyy").format(new Date()));
				}

				//UserSignature signBean = new UserSignature();
				List<UserSignature> signBean = userSignatureService.getCaseApplicationSignature(applId, AppConstants.USER_SIGN_TYPE_2);

				if (signBean != null && signBean.size() > 0)
				{
					model.addAttribute("signBean", signBean.get(0));
				}

				model.addAttribute(AppConstants.CODE_LOOKUP_COUNTY_LIST, (List<CodeLookUp>) ctx.getAttribute(AppConstants.CODE_LOOKUP_COUNTY_LIST));
				model.addAttribute(AppConstants.CODE_LOOKUP_STATE_LIST, (List<CodeLookUp>) ctx.getAttribute(AppConstants.CODE_LOOKUP_STATE_LIST));
				model.addAttribute(AppConstants.CODE_LOOKUP_CTRY_LIST, (List<CodeLookUp>) ctx.getAttribute(AppConstants.CODE_LOOKUP_CTRY_LIST));
				model.addAttribute("enforceExists", enforceExists);

				model.addAttribute("benefitExists", benefitExists);

				model.addAttribute("thirdPartyAgencyExists", thirdPartyAgencyExists);

				// get the step number from DB
				long stepNum = workFlowService.getStepNumber(appInfo.getApplicationId(), AppConstants.FLOW_TYPE_APPL_SCREEN);

				WorkFlow workFlow = new WorkFlow();
				workFlow.setStepNum(stepNum);
				model.addAttribute("flow", workFlow);

				logger.info("\n**********IN ApplicantInfoController:StepNum : +" + stepNum + "**********\n");

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
		return AppConstants.ECOA_APPLICANT_INFO;
	}

	/**
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/cssp/user/applicantInfo.htm", method = RequestMethod.POST)
	public String processApplicant(HttpServletRequest request, HttpServletResponse response, Model model, @ModelAttribute("partInfo") CaseParticipant partInfo)
	{
		logger.debug("\n**********IN ApplicantInfoController: processApplicant(request,response) post**********\n");

		HttpSession session = request.getSession();
		String mode = request.getParameter("mode");

		String[] benefits = null;

		ThirdParty thirdPartyBean = null;

		String rValue = "";
		String signFN = "";
		String signLN = "";
		String signMI = "";

		try
		{
			CaseApplication applBean = caseApplService.getCaseApplicationByAppId((String) session.getAttribute(AppConstants.SELECTED_APPLICATION)).get(0);

			if (applBean.getApplicationId() != null && !applBean.getApplicationId().equals(""))
			{
				partInfo.setApplicationId(applBean.getApplicationId());

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

				if (request.getParameter("applCounty") != null && !request.getParameter("applCounty").equals(""))
				{
					applBean.setApplCounty(request.getParameter("applCounty"));
				}

				if (request.getParameter("enforecmentExists") != null && request.getParameter("enforecmentExists").equals("y"))

				{

					if (request.getParameter("enforceSt") != null && !request.getParameter("enforceSt").equals(""))
					{
						applBean.setEnforState(request.getParameter("enforceSt"));

					}

					if (request.getParameter("enforceCntry") != null && !request.getParameter("enforceCntry").equals(""))
					{
						applBean.setEnforCountry(request.getParameter("enforceCntry"));

					}

					if (request.getParameter("enforceNm") != null && !request.getParameter("enforceNm").equals(""))
					{
						applBean.setEnforCompanyName(request.getParameter("enforceNm"));

					}

				}
				else
				{
					applBean.setEnforState("");
					applBean.setEnforCountry("");
					applBean.setEnforCompanyName("");
				}
				if (request.getParameter("canContact") != null && !request.getParameter("canContact").equals(""))
				{
					applBean.setContactViaEmail(request.getParameter("canContact"));

				}

				// receiving benefit parameters

				if (request.getParameter("benefits") != null)
				{
					benefits = request.getParameterValues("benefits");

				}

				Map<String, CasePartBenefit> benefitsMap = new HashMap<String, CasePartBenefit>();

				if (benefits != null && benefits.length > 0)
				{
					for (String benefit : benefits)
					{
						benefitsMap.put(benefit, null);
					}
				}

				// receiving ThirdPartyAgency related details.

				boolean thirdParty_entered = false;

				String agency = request.getParameter("agencyExists");

				if (request.getParameter("agencyExists") != null && request.getParameter("agencyExists").equals("y"))
				{
					thirdPartyBean = new ThirdParty();
					thirdPartyBean.setThirdPartyType(AppConstants.THIRDPARTY_TYPE_AGENCY);
					thirdPartyBean.setApplicationId(applBean.getApplicationId());

					if (request.getParameter("agencyNm") != null && !request.getParameter("agencyNm").equals(""))
					{
						thirdPartyBean.setThirdPartyNm(request.getParameter("agencyNm"));
						thirdParty_entered = true;
					}

					if (request.getParameter("a_ph1") != null && !request.getParameter("a_ph1").equals(""))
					{
						thirdPartyBean.setPhoneAreaCode(request.getParameter("a_ph1"));
						thirdParty_entered = true;
					}

					if (request.getParameter("a_ph2") != null && !request.getParameter("a_ph2").equals(""))
					{
						thirdPartyBean.setPhoneExc(request.getParameter("a_ph2"));
						thirdParty_entered = true;
					}

					if (request.getParameter("a_ph3") != null && !request.getParameter("a_ph3").equals(""))
					{
						thirdPartyBean.setPhoneLn(request.getParameter("a_ph3"));
						thirdParty_entered = true;
					}

					if (request.getParameter("agStreet") != null && !request.getParameter("agStreet").equals(""))
					{
						thirdPartyBean.setAddressLn1(request.getParameter("agStreet"));
						thirdParty_entered = true;
					}

					if (request.getParameter("agCity") != null && !request.getParameter("agCity").equals(""))
					{
						thirdPartyBean.setThirdPartyCity(request.getParameter("agCity"));
						thirdParty_entered = true;
					}
					if (request.getParameter("agZip") != null && !request.getParameter("agZip").equals(""))
					{
						thirdPartyBean.setThirdPartyZip5(request.getParameter("agZip"));
						thirdParty_entered = true;
					}

					if (request.getParameter("agSt") != null && !request.getParameter("agSt").equals(""))
					{
						thirdPartyBean.setThirdPartyState(request.getParameter("agSt"));
						thirdParty_entered = true;
					}

					if (request.getParameter("agCtry") != null && !request.getParameter("agCtry").equals(""))
					{
						thirdPartyBean.setCountryNm(request.getParameter("agCtry"));
						thirdParty_entered = true;
					}

				}

				if (!thirdParty_entered)
				{
					thirdPartyBean = null;
				}

				if (mode.equals(AppConstants.DB_CREATE))
				{
					logger.info("mode= " + AppConstants.DB_CREATE + ".......");

					rValue = casePartService.CreateApplicant(applBean, partInfo, applBean.getNcId(), benefitsMap, thirdPartyBean);

					if (rValue != null && rValue.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
					{
						int flow_step = 2;
						//if the role selected is CP or Child, then CP participant is already created in the system
						if (partInfo.getPartType().equals(AppConstants.CP_PARTICIPANT_TYPE))
						{
							flow_step = 3;
						}else if (partInfo.getPartType().equals(AppConstants.CHLD_PARTICIPANT_TYPE)){
							flow_step = 4;
						}
						// update step progress to step 2 for any other role
						rValue = workFlowService.updateStepNumber(applBean.getApplicationId(), AppConstants.FLOW_TYPE_APPL_SCREEN, flow_step, applBean.getNcId());
						if(rValue != null && !rValue.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
						{
							throw new Exception("ApplicantInfoController: processApplicant: create: update step number failed. Return code: "+rValue);
						}
					}
					else
					{
						throw new Exception("ApplicantInfoController: processApplicant: Create applicant failed. Return code: "+rValue);
					}

					UserSignature signBean = setUserSignature(applBean.getApplicationId(), signFN, signMI, signLN);

					rValue = userSignatureService.insertOrUpdateSignature(signBean, applBean.getNcId(), AppConstants.OPERATION_INSERT);
					if(rValue != null && !rValue.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
					{
						throw new Exception("ApplicantInfoController: processApplicant: create: insertOrUpdateSignature failed. Return code: "+rValue);
					}

				}
				else if (mode.equals(AppConstants.DB_UPDATE))
				{
					logger.info("mode= " + AppConstants.DB_UPDATE + "..........");

					CaseParticipant applicantInfo = new CaseParticipant();

					applicantInfo = casePartService.getParticipantByPartId(applBean.getApplicationId(), applBean.getApplicantId()).get(0);

					applicantInfo.setApplicantFNm(partInfo.getApplicantFNm());

					applicantInfo.setApplicantMNm(partInfo.getApplicantMNm());

					applicantInfo.setApplicantLNm(partInfo.getApplicantLNm());

					rValue = casePartService.updateApplicant(applBean, applicantInfo, applBean.getNcId(), benefitsMap, thirdPartyBean);
				}
				UserSignature signBean = setUserSignature(applBean.getApplicationId(), signFN, signMI, signLN);

				rValue = userSignatureService.insertOrUpdateSignature(signBean, applBean.getNcId(), AppConstants.OPERATION_UPDATE);
				
				if(rValue != null && !rValue.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
				{
					throw new Exception("ApplicantInfoController: processApplicant: update: insertOrUpdateSignature failed. Return code: "+rValue);
				}
			}

			if (rValue != null && rValue.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
			{
				model.addAttribute("success", "Information is saved successfully.");
			}
			else
			{
				//model.addAttribute("message", "Problem encountered during saving!!");
				// return AppConstants.ECOA_APPLICANT_INFO;
				//return "redirect:/cssp/user/applicantInfo.htm";
				throw new Exception("ApplicantInfoController: processApplicant: save/update failed. Return code: "+rValue);
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

		return "redirect:/cssp/user/applicantInfo.htm";

	}

	private boolean checkEnforceExists(CaseApplication appInfo)
	{

		boolean enforce = false;

		StringBuffer dataParam = new StringBuffer(" ");

		dataParam.append(appInfo.getEnforCountry().trim());

		dataParam.append(appInfo.getEnforState().trim());

		dataParam.append(appInfo.getEnforCompanyName().trim());

		if (dataParam.toString().trim().equals(""))

			return false;
		else
			return true;

	}

	private UserSignature setUserSignature(String applId, String signFN, String signMI, String signLN)
	{
		UserSignature signBean = new UserSignature();

		signBean.setSignId(applId);

		signBean.setSignType(AppConstants.USER_SIGN_TYPE_2);

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

}