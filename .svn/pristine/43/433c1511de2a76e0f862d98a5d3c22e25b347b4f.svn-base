package nc.dhhs.nccss.acts.ecoa.web.controller.applentry;

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

import nc.dhhs.nccss.acts.ecoa.beans.CasePartAddress;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartBenefit;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartChldAffil;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartContact;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartInsurance;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartOther;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartPaternty;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartProfile;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartProfileExt;
import nc.dhhs.nccss.acts.ecoa.beans.CaseParticipant;
import nc.dhhs.nccss.acts.ecoa.beans.CodeLookUp;
import nc.dhhs.nccss.acts.ecoa.beans.WorkFlow;
import nc.dhhs.nccss.acts.ecoa.web.controller.BasicAnnotatedFormController;
import nc.dhhs.nccss.acts.ecoa.web.controller.beans.ChldparamData;
import nc.dhhs.nccss.acts.ecoa.web.controller.beans.HelperParamData;
import nc.dhhs.nccss.acts.ecoa.web.controller.beans.ParamData;
import nc.dhhs.nccss.acts.ecoa.web.exception.ErrorDescriptor;
import nc.dhhs.nccss.acts.ecoa.web.service.CaseParticipantService;
import nc.dhhs.nccss.acts.ecoa.web.service.WorkFlowService;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * @author Phani Konuru
 *
 */
@Controller
public class CaseApplicationChildController extends BasicAnnotatedFormController
{

	@Autowired
	protected CaseParticipantService	casePartService;
	@Autowired
	protected WorkFlowService			workFlowService;

	/**
	 * @return
	 */
	@RequestMapping("/cssp/user/caseApplicationChildInfo.htm")
	public String getChildInfo(HttpServletRequest request, Model model)
	{

		logger.debug("\n********** IN CaseApplicationChildController: getChildInfo **********\n");

		HttpSession session = request.getSession();
		boolean applSubmit = false;

		session.setAttribute("selectedChld", null);

		try
		{
			String applicationId = (String) request.getSession().getAttribute(AppConstants.SELECTED_APPLICATION);
			List<CaseParticipant> chldPartList = casePartService.getParticipantByPartType(applicationId, AppConstants.CHLD_PARTICIPANT_TYPE);

			//get the step number from DB
			long stepNum = workFlowService.getStepNumber(applicationId, AppConstants.FLOW_TYPE_APPL_SCREEN);

			WorkFlow workFlow = new WorkFlow();

			workFlow.setStepNum(stepNum);

			if (request.getSession().getAttribute(AppConstants.APPL_SUBMITTED) != null)
			{
				applSubmit = ((Boolean) request.getSession().getAttribute(AppConstants.APPL_SUBMITTED)).booleanValue();
			}

			model.addAttribute(AppConstants.APPL_SUBMITTED, applSubmit);

			model.addAttribute("flow", workFlow);

			model.addAttribute("chldPartList", chldPartList);

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

		model.addAttribute("isApp", session.getAttribute(AppConstants.IS_APPLICATION_SELECTED));
		return AppConstants.ECOA_CASE_APPLN_CHILD_INFO;
	}

	@RequestMapping(value = "/cssp/user/caseApplicationChildInfo.htm", method = RequestMethod.POST)
	public String processChildParticipant(HttpServletRequest request, Model model)
	{
		logger.debug("\n********** IN CaseApplicationChildController: processChildParticipant **********\n");

		String action = (String) request.getParameter("action");
		String selectedChld = (String) request.getParameter(AppConstants.SELECTED_CHILD);
		String ncId = (String) request.getSession().getAttribute(AppConstants.USER_LOGIN_NAME);
		String screenToRedirect = "";
		HttpSession session = request.getSession();
		try
		{
			String applicationId = (String) request.getSession().getAttribute(AppConstants.SELECTED_APPLICATION);
			if (action.equals(AppConstants.CHILD_DELETE))
			{
				logger.info("action= " + AppConstants.CHILD_DELETE + "...........");

				String rValue = casePartService.deleteParticipant(applicationId, selectedChld, ncId);

				if (rValue != null && rValue.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
				{
					model.addAttribute("success", "Deleted successfully.");
				}
				else
				{
					//model.addAttribute("message", "Problem encountered during delete!!");
					throw new Exception("CaseApplicationChildController: processChildParticipant: child delete failed. Return code: "+rValue);
					
				}

				List<CaseParticipant> chldPartList = casePartService.getParticipantByPartType(applicationId, AppConstants.CHLD_PARTICIPANT_TYPE);

				if (chldPartList.size() == 0)
				{
					//update step progress to previous step
					rValue = workFlowService.updateStepNumber(applicationId, AppConstants.FLOW_TYPE_APPL_SCREEN, 3, ncId);

					if (rValue != null && !rValue.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
					{
						//model.addAttribute("message", "Problem encountered during saving!!");
						throw new Exception("CaseApplicationChildController: processChildParticipant: update step number failed. Return code: "+rValue);
					}
				}
				screenToRedirect = "redirect:/cssp/user/caseApplicationChildInfo.htm";
			}
			else if (action.equals(AppConstants.CHILD_SELECT))
			{
				logger.info("action= " + AppConstants.CHILD_SELECT + "...........");

				session.setAttribute("selectedChld", selectedChld);
				screenToRedirect = "redirect:/cssp/user/caseApplicationChildDetails.htm";
			}
			model.addAttribute("isApp", session.getAttribute(AppConstants.IS_APPLICATION_SELECTED));
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

		return screenToRedirect;

	}

	/**
	 * @return
	 */
	/**
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/cssp/user/caseApplicationChildDetails.htm")
	public String getChildDetails(HttpServletRequest request, Model model)
	{
		logger.debug("\n********** IN CaseApplicationChildController: getChildDetails **********\n");

		HttpSession session = request.getSession();

		ServletContext ctx = request.getServletContext();

		boolean applSubmit = false;
		HashMap<String, CasePartBenefit> benefitsMap = new HashMap();
		HashMap<String, String> chldNcpMap = new HashMap();
		HashMap<String, CasePartInsurance> insuranceMap = new HashMap();
		HashMap<String, CasePartOther> partOtherMap = new HashMap();
		CasePartProfile partProfile = new CasePartProfile();
		CasePartPaternty partPaternty = new CasePartPaternty();
		HashMap<String, CasePartProfileExt> partProfileExtMap = new HashMap();

		try
		{

			String applicationId = (String) request.getSession().getAttribute(AppConstants.SELECTED_APPLICATION);
			List<CaseParticipant> ncpList = casePartService.getParticipantByPartType(applicationId, AppConstants.NCP_PARTICIPANT_TYPE);
			if (session.getAttribute("selectedChld") != null)
			{

				String chldPartId = (String) session.getAttribute(AppConstants.SELECTED_CHILD);

				CaseParticipant partInfo = casePartService.getParticipantByPartId(applicationId, chldPartId).get(0);

				//part profile
				List<CasePartProfile> partProfileList = casePartService.getPartProfile(partInfo.getApplicationId(), partInfo.getApplicantId());
				if (partProfileList.size() > 0)
				{
					partProfile = partProfileList.get(0);

				}

				//part other address
				List<CasePartAddress> addrList = casePartService.getParticipantAddr(partInfo.getApplicationId(), partInfo.getApplicantId());
				if (addrList.size() > 0)
				{
					for (CasePartAddress addr : addrList)
					{
						if (addr.getAddrType().equals(AppConstants.ADDRESS_OTHER))
						{
							model.addAttribute("otherAddr", addr);
						}
					}
				}

				//part other contact
				List<CasePartContact> contactList = casePartService.getPartContact(partInfo.getApplicationId(), partInfo.getApplicantId());
				if (contactList.size() > 0)
				{
					for (CasePartContact cont : contactList)
					{
						if (cont.getContactType().equals(AppConstants.CONTACT_TYPE_OTHER))
						{
							model.addAttribute("otPh", cont.getContactInfo());
						}
					}
				}

				//benefits
				List<CasePartBenefit> casePartBenefits = casePartService.getCasePartBenefitsByFieldId(applicationId, partInfo.getApplicantId(), AppConstants.CHLD_PARTICIPANT_TYPE);

				if (casePartBenefits != null && casePartBenefits.size() > 0)

				{
					for (CasePartBenefit benefit : casePartBenefits)
					{
						if (benefit.getBenefitCode().trim().equals(AppConstants.VETERN_BENEFIt_TYPE))
						{
							benefitsMap.put(benefit.getBenefitCode().trim(), benefit);
						}
						else
						{
							benefitsMap.put(benefit.getBenefitCode().trim(), null);
						}
					}

				}

				//child ncp linkage

				List<CasePartChldAffil> ncpChldAffil = casePartService.getChldNcpAffil(applicationId, partInfo.getApplicantId());
				if (ncpChldAffil != null && ncpChldAffil.size() > 0)

				{
					for (CasePartChldAffil chldNcp : ncpChldAffil)
					{
						chldNcpMap.put(chldNcp.getApplicantId(), null);
					}

				}

				//paternity information
				List<CasePartPaternty> paterntyLst = casePartService.getPartPaternty(applicationId, partInfo.getApplicantId());
				if (paterntyLst != null && paterntyLst.size() > 0)
				{
					partPaternty = paterntyLst.get(0);
				}

				//child insurance information
				List<CasePartInsurance> casePartInsurance = casePartService.getPartInsurance(applicationId, partInfo.getApplicantId());
				if (casePartInsurance != null && casePartInsurance.size() > 0)

				{
					for (CasePartInsurance insurance : casePartInsurance)
					{
						insuranceMap.put(insurance.getInsuranceType().trim(), insurance);

					}

				}

				//part other information
				List<CasePartOther> casePartOther = casePartService.getPartOther(applicationId, partInfo.getApplicantId());

				if (casePartOther != null && casePartOther.size() > 0)

				{
					for (CasePartOther partOther : casePartOther)
					{

						partOtherMap.put(partOther.getRelationship().trim(), partOther);

					}

				}

				//part profile ext information
				List<CasePartProfileExt> casePartProfileExtList = casePartService.getPartProfileExt(applicationId, partInfo.getApplicantId());

				if (casePartProfileExtList != null && casePartProfileExtList.size() > 0)

				{
					for (CasePartProfileExt partProfileExt : casePartProfileExtList)
					{

						partProfileExtMap.put(partProfileExt.getProfileExtType().trim(), partProfileExt);

					}

				}

				model.addAttribute("mode", AppConstants.DB_UPDATE);
				model.addAttribute("partInfo", partInfo);
				model.addAttribute("benefitsMap", benefitsMap);
				model.addAttribute("chldNcpMap", chldNcpMap);
				model.addAttribute("chldInsurnceMap", insuranceMap);
				model.addAttribute("partOtherMap", partOtherMap);
				model.addAttribute("partProfile", partProfile);
				model.addAttribute("partPaternty", partPaternty);
				model.addAttribute("partProfileExtMap", partProfileExtMap);
			}
			else
			{
				model.addAttribute("mode", AppConstants.DB_CREATE);
			}
			//get the step number from DB
			long stepNum = workFlowService.getStepNumber(applicationId, AppConstants.FLOW_TYPE_APPL_SCREEN);

			WorkFlow workFlow = new WorkFlow();

			workFlow.setStepNum(stepNum);

			if (request.getSession().getAttribute(AppConstants.APPL_SUBMITTED) != null)
			{
				applSubmit = ((Boolean) request.getSession().getAttribute(AppConstants.APPL_SUBMITTED)).booleanValue();
			}

			List<CodeLookUp> famRel = (List<CodeLookUp>) ctx.getAttribute(AppConstants.CODE_LOOKUP_FAM_REL_LIST);
			String famRelStr = "";
			for (CodeLookUp relation : famRel)
			{
				famRelStr = famRelStr + relation.getCodeDesc() + ",";
			}
			famRelStr =famRelStr.substring(0, famRelStr.length()-1);
			model.addAttribute(AppConstants.APPL_SUBMITTED, applSubmit);
			model.addAttribute("famRelStr", famRelStr);
			model.addAttribute("ncpList", ncpList);
			model.addAttribute("flow", workFlow);
			model.addAttribute("isApp", session.getAttribute(AppConstants.IS_APPLICATION_SELECTED));
			model.addAttribute(AppConstants.CODE_LOOKUP_LANG_LIST, (List<CodeLookUp>) ctx.getAttribute(AppConstants.CODE_LOOKUP_LANG_LIST));
			model.addAttribute(AppConstants.CODE_LOOKUP_GENDER_LIST, (List<CodeLookUp>) ctx.getAttribute(AppConstants.CODE_LOOKUP_GENDER_LIST));
			model.addAttribute(AppConstants.CODE_LOOKUP_ETHNCGRP_LIST, (List<CodeLookUp>) ctx.getAttribute(AppConstants.CODE_LOOKUP_ETHNCGRP_LIST));
			model.addAttribute(AppConstants.CODE_LOOKUP_SUFFIX_LIST, (List<CodeLookUp>) ctx.getAttribute(AppConstants.CODE_LOOKUP_SUFFIX_LIST));
			model.addAttribute(AppConstants.CODE_LOOKUP_SASSIST_LIST, (List<CodeLookUp>) ctx.getAttribute(AppConstants.CODE_LOOKUP_SASSIST_LIST));
			model.addAttribute(AppConstants.CODE_LOOKUP_HEALTH_COV_LIST, (List<CodeLookUp>) ctx.getAttribute(AppConstants.CODE_LOOKUP_HEALTH_COV_LIST));
			model.addAttribute(AppConstants.CODE_LOOKUP_FAM_REL_LIST, (List<CodeLookUp>) ctx.getAttribute(AppConstants.CODE_LOOKUP_FAM_REL_LIST));
			model.addAttribute(AppConstants.CODE_LOOKUP_STATE_LIST, (List<CodeLookUp>) ctx.getAttribute(AppConstants.CODE_LOOKUP_STATE_LIST));
			model.addAttribute(AppConstants.CODE_LOOKUP_CTRY_LIST, (List<CodeLookUp>) ctx.getAttribute(AppConstants.CODE_LOOKUP_CTRY_LIST));
			model.addAttribute(AppConstants.CODE_LOOKUP_COUNTY_LIST, (List<CodeLookUp>) ctx.getAttribute(AppConstants.CODE_LOOKUP_COUNTY_LIST));
			model.addAttribute(AppConstants.CODE_LOOKUP_MARITAL_LIST, (List<CodeLookUp>) ctx.getAttribute(AppConstants.CODE_LOOKUP_MARITAL_LIST));
			model.addAttribute(AppConstants.CODE_LOOKUP_PATERNTY_LIST, (List<CodeLookUp>) ctx.getAttribute(AppConstants.CODE_LOOKUP_PATERNTY_LIST));
			model.addAttribute(AppConstants.CODE_LOOKUP_SELOPTION_LIST, (List<CodeLookUp>) ctx.getAttribute(AppConstants.CODE_LOOKUP_SELOPTION_LIST));

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
		return AppConstants.ECOA_CASE_APPLN_CHILD_DETAILS;
	}

	@RequestMapping(value = "/cssp/user/caseApplicationChildDetails.htm", method = RequestMethod.POST)
	public String createUpdateChldParticipant(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("partInfo") CaseParticipant partInfo)
	{
		logger.debug("\n********** IN CaseApplicationChildController: createUpdateChldParticipant **********\n");

		String mode = request.getParameter("mode");
		HttpSession session = request.getSession();

		String[] benefits = null;
		String[] chldNCP = null;
		String[] insuranceLst = null;
		CasePartProfile childInfo = new CasePartProfile();
		List<CaseParticipant> ncpPartList;

		String userId = (String) session.getAttribute("userLoginName");
		partInfo.setApplicationId((String) session.getAttribute(AppConstants.SELECTED_APPLICATION));

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
			ChldparamData pageData = new ChldparamData();
			HelperParamData helper = new HelperParamData();
			pageData.setParticipant(partInfo);
	
			//child information
			if (request.getParameter("relation") != null)
			{
				childInfo.setFamRel((String) request.getParameter("relation"));
	
				if (request.getParameter("relation").equals("4"))
				{
					if (request.getParameter("otherRel") != null)
					{
						childInfo.setRelOther((String) request.getParameter("otherRel"));
					}
				}
				else
				{
					childInfo.setRelOther("");
				}
			}
	
			if (request.getParameter("reside") != null)
			{
				childInfo.setChldLiveWith((String) request.getParameter("reside"));
				if (request.getParameter("reside").equalsIgnoreCase("y"))
				{
					if (request.getParameter("ntsYr") != null)
					{
						childInfo.setNtsYr((String) request.getParameter("ntsYr"));
					}
					if (request.getParameter("yrs") != null)
					{
						childInfo.setNumYr((String) request.getParameter("yrs"));
					}
					if (request.getParameter("mths") != null)
					{
						childInfo.setNumMo((String) request.getParameter("mths"));
					}
				}
				else if (request.getParameter("reside").equalsIgnoreCase("n"))
				{
					helper.populatePartOther(pageData, paramList, AppConstants.CHLD_OTHER_RES_DESC, AppConstants.CHLD_OTHER_RES_CODE, AppConstants.CHLD_PARTICIPANT_TYPE);
					//part ext address information
					helper.populateAddress(pageData, paramList, AppConstants.ADDRESS_OTHER, AppConstants.ADDRESS_OTHER_CODE, AppConstants.CHLD_PARTICIPANT_TYPE);
					//contact information		
					helper.populateContact(pageData, paramList, AppConstants.CONTACT_TYPE_OTHER_CODE, AppConstants.CONTACT_TYPE_OTHER, AppConstants.CHLD_PARTICIPANT_TYPE);
				}
			}
	
			if (request.getParameter("wedlock") != null)
			{
				childInfo.setChldWdlck((String) request.getParameter("wedlock"));
				if (request.getParameter("wedlock").equals(AppConstants.SELOPTION_NO))
				{
					if (request.getParameter("affidavit") != null)
					{
						childInfo.setAffidCmplt((String) request.getParameter("affidavit"));
						if (request.getParameter("affidavit").equals(AppConstants.SELOPTION_YES))
						{
							if (request.getParameter("affState") != null)
							{
								childInfo.setAffidSt((String) request.getParameter("affState"));
							}
						}
					}
	
				}
			}
	
			if (request.getParameter("mariedBth") != null)
			{
				childInfo.setMothMariedBth(request.getParameter("mariedBth"));
				if (request.getParameter("mariedBth").equals(AppConstants.SELOPTION_YES))
				{
					helper.populatePartOther(pageData, paramList, AppConstants.CHLD_OTHER_MO_MRID_TO_DESC, AppConstants.CHLD_OTHER_MO_MRID_TO_CODE, AppConstants.CHLD_PARTICIPANT_TYPE);
				}
			}
	
			if (request.getParameter("marital") != null)
			{
				childInfo.setMaritalStatus(request.getParameter("marital"));
	
				if (!request.getParameter("marital").equals(AppConstants.MARITAL_NOT_MARRIED) && !request.getParameter("marital").equals(AppConstants.BLANK_VALUE) && !request.getParameter("marital").equals(AppConstants.MARITAL_UNKNOWN))
				{
					if (request.getParameter("maDt") != null)
					{
						childInfo.setMaritalStatusDt(request.getParameter("maDt"));
					}
					helper.populatePartProfileExt(pageData, paramList, AppConstants.CHLD_PROFILE_EXT_RELA_DESC, AppConstants.CHLD_PROFILE_EXT_RELA_CODE);
				}
			}
	
			pageData.setPartProfile(childInfo);
	
			//child other information
			helper.populatePartOther(pageData, paramList, AppConstants.CHLD_OTHER_PRNT1_DESC, AppConstants.CHLD_OTHER_PRNT1_CODE, AppConstants.CHLD_PARTICIPANT_TYPE);
			helper.populatePartOther(pageData, paramList, AppConstants.CHLD_OTHER_PRNT2_DESC, AppConstants.CHLD_OTHER_PRNT2_CODE, AppConstants.CHLD_PARTICIPANT_TYPE);
	
			//child profile ext
			helper.populatePartProfileExt(pageData, paramList, AppConstants.CHLD_PROFILE_EXT_CONCIVE_DESC, AppConstants.CHLD_PROFILE_EXT_CONCIVE_CODE);
			helper.populatePartProfileExt(pageData, paramList, AppConstants.CHLD_PROFILE_EXT_BTH_DESC, AppConstants.CHLD_PROFILE_EXT_BTH_CODE);
	
			//part ext address information
			//helper.populateAddress(pageData, paramList, AppConstants.ADDRESS_MAILING, AppConstants.ADDRESS_MAILING_CODE, AppConstants.CHLD_PARTICIPANT_TYPE);
	
			//child benefits
			if (request.getParameter("assistType") != null)
			{
				benefits = request.getParameterValues("assistType");
				if (benefits.length > 0)
				{
					Map<String, CasePartBenefit> benefitsMap = new HashMap<String, CasePartBenefit>();
					for (String benefit : benefits)
					{
						if (benefit.equals(AppConstants.VETERN_BENEFIt_TYPE))
						{
							CasePartBenefit bean = new CasePartBenefit();
							bean.setBenefitCode(benefit);
							if (request.getParameter("vfname") != null)
							{
								bean.setVeteranNmF(request.getParameter("vfname"));
							}
							if (request.getParameter("vlname") != null)
							{
								bean.setVeteranNmL(request.getParameter("vlname"));
							}
							bean.setPartType(AppConstants.CHLD_PARTICIPANT_TYPE);
							benefitsMap.put(benefit, bean);
						}
						else
						{
							benefitsMap.put(benefit, null);
						}
					}
					pageData.setBenifitList(benefitsMap);
				}
	
			}
	
			//Child NCP linking
	
			if (request.getParameter("ncpchldAffil") != null)
			{
				chldNCP = request.getParameterValues("ncpchldAffil");
				if (chldNCP.length > 0)
				{
					Map<String, String> chldNCPMap = new HashMap<String, String>();
					for (String ncpId : chldNCP)
					{
						chldNCPMap.put(ncpId, null);
					}
					pageData.setChldNcpList(chldNCPMap);
				}
	
			}
			//get new NCP to add and link to the child
			String ncpToAddLst = (String) paramList.get("ncpToAddLst");
			List<CasePartChldAffil> ncpToAddList = new ArrayList<CasePartChldAffil>();
	
			if (!ncpToAddLst.trim().equals(""))
			{
				String[] ncpList = ncpToAddLst.split(",");
				for (int i = 0; i < ncpList.length; i++)
				{
					String ncpToAdd = ncpList[i];
					CasePartChldAffil ncpToAddBean = new CasePartChldAffil();
					String nameF = (String) paramList.get("NCP_fName_" + ncpToAdd);
					String nameL = (String) paramList.get("NCP_lName_" + ncpToAdd);
					ncpToAddBean.setNameF(nameF);
					ncpToAddBean.setNameL(nameL);
					ncpToAddList.add(ncpToAddBean);
				}
				pageData.setChldNcpToAddList(ncpToAddList);
			}
	
			//paternity information
			helper.populatePaterntyInfo(pageData, paramList);
	
			//insurance list
			if (request.getParameter("hcoverage") != null)
			{
				childInfo.setHasIns((String)request.getParameter("hcoverage"));
	
				insuranceLst = request.getParameterValues("healthCov");
				
				if (insuranceLst != null && insuranceLst.length > 0)
				{
					insuranceLst = request.getParameterValues("healthCov");
					if(!childInfo.getHasIns().equals(AppConstants.BLANK_VALUE) && !childInfo.getHasIns().equals(AppConstants.SELOPTION_NO) && !childInfo.getHasIns().equals(AppConstants.SELOPTION_UNKNOWN))
					{	
						Map<String, CasePartInsurance> insuranceMap = new HashMap<String, CasePartInsurance>();
						for (String insurance : insuranceLst)
						{
		
							CasePartInsurance insBean = new CasePartInsurance();
							insBean.setInsuranceType(insurance);
							insBean.setInsuranceProvider((String) paramList.get("insProvider_" + insurance));
							insBean.setInsHolderF((String) paramList.get("holderNmF_" + insurance));
							insBean.setInsHolderL((String) paramList.get("holderNmL_" + insurance));
							insBean.setInsHolderRelshp((String) paramList.get("holderRel_" + insurance));
							insuranceMap.put(insurance, insBean);
		
						}
						pageData.setInsuranceList(insuranceMap);
					}
				}
	
			}

			if (mode.equals(AppConstants.DB_CREATE))
			{
				logger.info("mode= " + AppConstants.DB_CREATE + "...........");

				String chldPartId = casePartService.CreateCaseParticipant(partInfo, ((ParamData) pageData), userId, AppConstants.CHLD_PARTICIPANT_TYPE);

				if (chldPartId != "")
				{
					String rValue = AppConstants.OPERATION_SUCCESS;
					//update step progress
					ncpPartList = casePartService.getParticipantByPartType(partInfo.getApplicationId(), AppConstants.NCP_PARTICIPANT_TYPE);
					//get the step number from DB
					long stepNum = workFlowService.getStepNumber(partInfo.getApplicationId(), AppConstants.FLOW_TYPE_APPL_SCREEN);
					
					if(ncpPartList.size() > 0 && stepNum < 5){
						rValue = workFlowService.updateStepNumber(partInfo.getApplicationId(), AppConstants.FLOW_TYPE_APPL_SCREEN, 5, userId);
					}
					else if (workFlowService.getStepNumber(partInfo.getApplicationId(), AppConstants.FLOW_TYPE_APPL_SCREEN) < 4)
					{
						rValue = workFlowService.updateStepNumber(partInfo.getApplicationId(), AppConstants.FLOW_TYPE_APPL_SCREEN, 4, userId);
					}
					if (rValue != null && rValue.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
					{
						model.addAttribute("success", "Information is saved successfully!!");
					}
					else{
						throw new Exception("CaseApplicationChildController: createUpdateChldParticipant: update step number failed. Return code: "+rValue);
					}

				}
				else
				{
					//model.addAttribute("message", "Problem encountered during saving!!");
					throw new Exception("CaseApplicationChildController: createUpdateChldParticipant: create child failed.");
				}

				logger.info("chldPartId= " + chldPartId + "...........");

				session.setAttribute("selectedChld", chldPartId);
			}
			else if (mode.equals(AppConstants.DB_UPDATE))
			{
				logger.info("mode= " + AppConstants.DB_UPDATE + "..........");

				partInfo.setApplicantId((String) session.getAttribute("selectedChld"));

				String rValue = casePartService.updateParticipant(partInfo, ((ParamData) pageData), userId, AppConstants.CHLD_PARTICIPANT_TYPE);

				if (rValue != null && rValue.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
				{
					//update step progress
					ncpPartList = casePartService.getParticipantByPartType(partInfo.getApplicationId(), AppConstants.NCP_PARTICIPANT_TYPE);
					//get the step number from DB
					long stepNum = workFlowService.getStepNumber(partInfo.getApplicationId(), AppConstants.FLOW_TYPE_APPL_SCREEN);
					
					if(ncpPartList.size() > 0 && stepNum < 5){
						rValue = workFlowService.updateStepNumber(partInfo.getApplicationId(), AppConstants.FLOW_TYPE_APPL_SCREEN, 5, userId);
					}
					else if (workFlowService.getStepNumber(partInfo.getApplicationId(), AppConstants.FLOW_TYPE_APPL_SCREEN) < 4)
					{
						rValue = workFlowService.updateStepNumber(partInfo.getApplicationId(), AppConstants.FLOW_TYPE_APPL_SCREEN, 4, userId);
					}
					if (rValue != null && rValue.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
					{
						model.addAttribute("success", "Information is saved successfully!! Go back to child list to add more children or select next on child list page to proceed to section 4");
					}
					else
					{
						throw new Exception("CaseApplicationChildController: createUpdateChldParticipant: update step number failed. Return code: "+rValue);
					}
				}
				else
				{
					//model.addAttribute("message", "Problem encountered during saving!!");
					throw new Exception("CaseApplicationChildController: createUpdateChldParticipant: update child failed. Return code: "+rValue);
				}
			}

			model.addAttribute("isApp", session.getAttribute(AppConstants.IS_APPLICATION_SELECTED));
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
		return "redirect:/cssp/user/caseApplicationChildDetails.htm";
	}

}
