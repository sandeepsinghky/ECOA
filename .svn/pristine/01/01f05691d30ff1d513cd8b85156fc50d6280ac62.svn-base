package nc.dhhs.nccss.acts.ecoa.web.controller.applentry;

import java.sql.SQLException;
import java.util.ArrayList;
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
import nc.dhhs.nccss.acts.ecoa.beans.CasePartAddress;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartContact;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartEmp;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartIncome;
import nc.dhhs.nccss.acts.ecoa.beans.CaseParticipant;
import nc.dhhs.nccss.acts.ecoa.beans.CodeLookUp;
import nc.dhhs.nccss.acts.ecoa.beans.ThirdParty;
import nc.dhhs.nccss.acts.ecoa.beans.WorkFlow;
import nc.dhhs.nccss.acts.ecoa.web.controller.BasicAnnotatedFormController;
import nc.dhhs.nccss.acts.ecoa.web.controller.beans.CPparamData;
import nc.dhhs.nccss.acts.ecoa.web.controller.beans.HelperParamData;
import nc.dhhs.nccss.acts.ecoa.web.controller.beans.ParamData;
import nc.dhhs.nccss.acts.ecoa.web.service.CaseApplicationService;
import nc.dhhs.nccss.acts.ecoa.web.service.CaseParticipantService;
import nc.dhhs.nccss.acts.ecoa.web.service.WorkFlowService;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;
import nc.dhhs.nccss.acts.ecoa.web.util.EmailManager;
import nc.dhhs.nccss.acts.ecoa.web.config.WebsiteConfiguration;
import nc.dhhs.nccss.acts.ecoa.web.exception.ErrorDescriptor;

/**
 * @author Phani Konuru
 *
 */
@Controller
public class CaseApplicationCPController extends BasicAnnotatedFormController
{
	@Autowired
	protected CaseApplicationService	caseApplService;
	@Autowired
	protected CaseParticipantService	casePartService;
	@Autowired
	protected WorkFlowService			workFlowService;
	@Autowired
	protected EmailManager				emailManager;

	/**
	 * @return
	 */
	@RequestMapping("/cssp/user/caseApplicationCPInfo.htm")
	public String getCustodialInfo(HttpServletRequest request, Model model)
	{
		logger.debug("\n********** IN CaseApplicationCPController: getCustodialInfo **********\n");

		HttpSession session = request.getSession();

		ServletContext ctx = request.getServletContext();
		boolean applSubmit = false;

		try
		{
			String applicationId = (String) request.getSession().getAttribute("selectedApp");

			CaseApplication caseApp = caseApplService.getCaseApplicationByAppId((String) session.getAttribute(AppConstants.SELECTED_APPLICATION)).get(0);

			List<CaseParticipant> cpPartInfo = new ArrayList<CaseParticipant>();

			if (caseApp.getApplicantId() != null && !caseApp.getApplicantId().equals(""))
			{
				cpPartInfo = casePartService.getParticipantByPartId(applicationId, caseApp.getApplicantId());

				if (cpPartInfo != null && cpPartInfo.size() > 0)
				{
					if (!cpPartInfo.get(0).getPartType().equals(AppConstants.CP_PARTICIPANT_TYPE) && !cpPartInfo.get(0).getPartType().equals(AppConstants.CHLD_PARTICIPANT_TYPE))
					{
						cpPartInfo = casePartService.getParticipantByPartType(applicationId, AppConstants.CP_PARTICIPANT_TYPE);
					}
				}

				if (cpPartInfo != null && cpPartInfo.size() > 0)
				{
					CaseParticipant partInfo = (CaseParticipant) cpPartInfo.get(0);

					List<CasePartAddress> addrList = casePartService.getParticipantAddr(partInfo.getApplicationId(), partInfo.getApplicantId());
					if (addrList.size() > 0)
					{
						for (CasePartAddress addr : addrList)
						{
							if (addr.getAddrType().equals(AppConstants.ADDRESS_MAILING))
							{
								model.addAttribute("mailAddr", addr);
							}
							else if (addr.getAddrType().equals(AppConstants.ADDRESS_RESIDENTIAL))
							{
								model.addAttribute("resAddr", addr);
							}
						}
					}

					List<CasePartContact> contactList = casePartService.getPartContact(partInfo.getApplicationId(), partInfo.getApplicantId());
					if (contactList.size() > 0)
					{
						for (CasePartContact cont : contactList)
						{
							if (cont.getContactType().equals(AppConstants.CONTACT_TYPE_HOME))
							{
								model.addAttribute("hPh", cont.getContactInfo());
							}
							else if (cont.getContactType().equals(AppConstants.CONTACT_TYPE_WORK))
							{
								model.addAttribute("wPh", cont.getContactInfo());
							}
							else if (cont.getContactType().equals(AppConstants.CONTACT_TYPE_CELL))
							{
								model.addAttribute("cPh", cont.getContactInfo());
							}
							else if (cont.getContactType().equals(AppConstants.CONTACT_TYPE_EMAIL))
							{
								model.addAttribute("email", cont.getContactInfo());
							}
						}
					}

					//Employment information
					CasePartEmp empBean = new CasePartEmp();
					List<CasePartEmp> empList = casePartService.getPartEmp(partInfo.getApplicationId(), partInfo.getApplicantId());
					if (empList.size() > 0)
					{
						empBean = empList.get(0);
						ThirdParty thrdPartyBean = new ThirdParty();
						List<ThirdParty> trdPtyList = casePartService.getThirdPartyBy3ptyId(partInfo.getApplicationId(), empBean.getThirdPartyId());
						if (trdPtyList.size() > 0)
						{
							empBean.setThirdPartyInfo(trdPtyList.get(0));
						}
					}

					//income source
					List<CasePartIncome> incomeList = casePartService.getParticipantIncome(partInfo.getApplicationId(), partInfo.getApplicantId());

					model.addAttribute("empInfo", empBean);
					model.addAttribute("incList", incomeList);

					model.addAttribute("mode", AppConstants.DB_UPDATE);
					model.addAttribute("partInfo", partInfo);
					model.addAttribute("partType", partInfo.getPartType());
					session.setAttribute("cpParticipant", partInfo.getApplicantId());
				}
				else
				{
					model.addAttribute("mode", AppConstants.DB_CREATE);
					model.addAttribute("partType", AppConstants.CP_PARTICIPANT_TYPE);
					logger.debug("\n********** IN CaseApplicationCPController: getCustodialInfo applId: " + applicationId + "**********\n");
				}
			}
			else
			{
				model.addAttribute("mode", AppConstants.DB_CREATE);
				model.addAttribute("partType", AppConstants.CP_PARTICIPANT_TYPE);
				logger.debug("\n********** IN CaseApplicationCPController: getCustodialInfo applId: " + applicationId + "**********\n");

			}
			// retrieve step #
			long stepNum = workFlowService.getStepNumber(applicationId, AppConstants.FLOW_TYPE_APPL_SCREEN);

			WorkFlow workFlow = new WorkFlow();

			workFlow.setStepNum(stepNum);
			if (request.getSession().getAttribute(AppConstants.APPL_SUBMITTED) != null)
			{
				applSubmit = ((Boolean) request.getSession().getAttribute(AppConstants.APPL_SUBMITTED)).booleanValue();
			}

			model.addAttribute(AppConstants.APPL_SUBMITTED, applSubmit);
			model.addAttribute("flow", workFlow);
			model.addAttribute("isApp", request.getSession().getAttribute(AppConstants.IS_APPLICATION_SELECTED));
			model.addAttribute(AppConstants.CODE_LOOKUP_LANG_LIST, (List<CodeLookUp>) ctx.getAttribute(AppConstants.CODE_LOOKUP_LANG_LIST));
			model.addAttribute(AppConstants.CODE_LOOKUP_GENDER_LIST, (List<CodeLookUp>) ctx.getAttribute(AppConstants.CODE_LOOKUP_GENDER_LIST));
			model.addAttribute(AppConstants.CODE_LOOKUP_ETHNCGRP_LIST, (List<CodeLookUp>) ctx.getAttribute(AppConstants.CODE_LOOKUP_ETHNCGRP_LIST));
			model.addAttribute(AppConstants.CODE_LOOKUP_SUFFIX_LIST, (List<CodeLookUp>) ctx.getAttribute(AppConstants.CODE_LOOKUP_SUFFIX_LIST));
			model.addAttribute(AppConstants.CODE_LOOKUP_SASSIST_LIST, (List<CodeLookUp>) ctx.getAttribute(AppConstants.CODE_LOOKUP_SASSIST_LIST));
			model.addAttribute(AppConstants.CODE_LOOKUP_STATE_LIST, (List<CodeLookUp>) ctx.getAttribute(AppConstants.CODE_LOOKUP_STATE_LIST));
			model.addAttribute(AppConstants.CODE_LOOKUP_COUNTY_LIST, (List<CodeLookUp>) ctx.getAttribute(AppConstants.CODE_LOOKUP_COUNTY_LIST));
			model.addAttribute(AppConstants.CODE_LOOKUP_INCOME_TYPE_LIST, (List<CodeLookUp>) ctx.getAttribute(AppConstants.CODE_LOOKUP_INCOME_TYPE_LIST));
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
			model.addAttribute("message",AppConstants.APPLICATION_ERROR);
			ErrorDescriptor error = new ErrorDescriptor(this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage(), e);
			if(session.getAttribute("selectedApp") != null)
				error.setApplicationId((String)session.getAttribute("selectedApp"));
			request.setAttribute("errorBean", error);
			error = null;
			return "forward:/apperror.htm";
		}
		return AppConstants.ECOA_CASE_APPLN_CP_INFO;
	}

	/**
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/cssp/user/caseApplicationCPInfo.htm", method = RequestMethod.POST)
	public String processCPParticipant(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("partInfo") CaseParticipant partInfo)
	{
		logger.debug("\n********** IN CaseApplicationCPController: processCPParticipant **********\n");

		String mode = request.getParameter("mode");
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userLoginName");
		partInfo.setApplicationId((String) session.getAttribute("selectedApp"));

		// Map<String, String[]> parameterMap = request.getParameterMap();
		Map paramList = new HashMap();

		for (Object obj : request.getParameterMap().entrySet())
		{
			Map.Entry<String, String[]> entry = (Map.Entry<String, String[]>) obj;
			if (entry.getValue() != null && entry.getValue().length > 0)
			{
				paramList.put(entry.getKey(), entry.getValue()[0]);
			}
			else
			{
				paramList.put(entry.getKey(), entry.getValue());
			}
		}
		try
		{
			CPparamData pageData = new CPparamData();
			HelperParamData helper = new HelperParamData();
			pageData.setParticipant(partInfo);
	
			//mailing address
			helper.populateAddress(pageData, paramList, AppConstants.ADDRESS_MAILING, AppConstants.ADDRESS_MAILING_CODE, AppConstants.CP_PARTICIPANT_TYPE);
	
			//residential address
			if (paramList.get("res_addr") == null)
			{
				helper.populateAddress(pageData, paramList, AppConstants.ADDRESS_RESIDENTIAL, AppConstants.ADDRESS_RESIDENTIAL_CODE, AppConstants.CP_PARTICIPANT_TYPE);
			}
	
			//contact information		
			helper.populateContact(pageData, paramList, AppConstants.CONTACT_TYPE_HOME_CODE, AppConstants.CONTACT_TYPE_HOME, AppConstants.CP_PARTICIPANT_TYPE);
			helper.populateContact(pageData, paramList, AppConstants.CONTACT_TYPE_WORK_CODE, AppConstants.CONTACT_TYPE_WORK, AppConstants.CP_PARTICIPANT_TYPE);
			helper.populateContact(pageData, paramList, AppConstants.CONTACT_TYPE_CELL_CODE, AppConstants.CONTACT_TYPE_CELL, AppConstants.CP_PARTICIPANT_TYPE);
			helper.populateContact(pageData, paramList, null, AppConstants.CONTACT_TYPE_EMAIL, AppConstants.CP_PARTICIPANT_TYPE);
	
			//employment information
	
			if (paramList.get("emp_chk").equals(AppConstants.CONDITION_TRUE))
			{
				helper.populateCurrEmpInfo(pageData, paramList, AppConstants.CP_PARTICIPANT_TYPE);
			}
			else
			{
				helper.populatePrevEmpInfo(pageData, paramList, AppConstants.CP_PARTICIPANT_TYPE);
			}
	
			//income source information
			helper.populateIncomeSrc(pageData, paramList, AppConstants.CP_PARTICIPANT_TYPE);


			if (mode.equals(AppConstants.DB_CREATE))
			{
				logger.info("mode= " + AppConstants.DB_CREATE + " .............");

				String cpPartId = casePartService.CreateCaseParticipant(partInfo, ((ParamData) pageData), userId, AppConstants.CP_PARTICIPANT_TYPE);

				if (cpPartId != "")
				{
					// update step progress
					String rValue = workFlowService.updateStepNumber(partInfo.getApplicationId(), AppConstants.FLOW_TYPE_APPL_SCREEN, 3, userId);

					if (rValue != null && rValue.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
					{
						model.addAttribute("success", "Information is saved successfully!!");
					}
				}
				else
				{
					//model.addAttribute("message", "Problem encountered during saving!!");
					throw new RuntimeException("CaseApplicationCPController: processCPParticipant: CP participant creation failed.");
				}

				logger.info("cpPartId: " + cpPartId + " ...............");
				session.setAttribute("cpParticipant", cpPartId);
			}
			else if (mode.equals(AppConstants.DB_UPDATE))
			{
				logger.info("mode= " + AppConstants.DB_UPDATE + " .............");

				partInfo.setApplicantId((String) session.getAttribute("cpParticipant"));

				String rValue = casePartService.updateParticipant(partInfo, ((ParamData) pageData), userId, AppConstants.CP_PARTICIPANT_TYPE);

				if (rValue != null && rValue.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
				{
					model.addAttribute("success", "Information is saved successfully!!");
				}
				else
				{
					//model.addAttribute("message", "Problem encountered during saving!!");
					throw new Exception("CaseApplicationCPController: processCPParticipant: CP participant update failed. Return code: "+rValue);
				}
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
		return "redirect:/cssp/user/caseApplicationCPInfo.htm";

	}

}
