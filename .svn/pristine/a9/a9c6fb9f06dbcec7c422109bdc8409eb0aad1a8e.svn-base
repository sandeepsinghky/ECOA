package nc.dhhs.nccss.acts.ecoa.web.controller.applentry;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import nc.dhhs.nccss.acts.ecoa.beans.CaseCourtOrder;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartAddress;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartChldAffil;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartContact;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartEmp;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartIncome;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartNCPExt;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartOrder;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartOther;
import nc.dhhs.nccss.acts.ecoa.beans.CaseParticipant;
import nc.dhhs.nccss.acts.ecoa.beans.CodeLookUp;
import nc.dhhs.nccss.acts.ecoa.beans.ThirdParty;
import nc.dhhs.nccss.acts.ecoa.beans.Vehicle;
import nc.dhhs.nccss.acts.ecoa.beans.WorkFlow;
import nc.dhhs.nccss.acts.ecoa.web.controller.BasicAnnotatedFormController;
import nc.dhhs.nccss.acts.ecoa.web.controller.beans.HelperParamData;
import nc.dhhs.nccss.acts.ecoa.web.controller.beans.NCPparamData;
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
public class CaseApplicationNCPController extends BasicAnnotatedFormController {

	@Autowired
	protected CaseParticipantService casePartService;

	@Autowired
	protected WorkFlowService workFlowService;

	/**
	 * @return
	 */
	@RequestMapping("/cssp/user/caseApplicationNCPInfo.htm")
	public String getNCPInfo(HttpServletRequest request, Model model) {
		logger.debug("\n********** IN CaseApplicationNCPController: getNCPInfo **********\n");

		boolean applSubmit = false;
		HttpSession session = request.getSession();
		session.setAttribute(AppConstants.SELECTED_NCP, null);
		List<CaseParticipant> ncpPartList = new ArrayList<CaseParticipant>();

		WorkFlow workFlow = new WorkFlow();
		long stepNum;
		try {
			String applicationId = (String) request.getSession().getAttribute(AppConstants.SELECTED_APPLICATION);
			ncpPartList = casePartService.getParticipantByPartType(applicationId, AppConstants.NCP_PARTICIPANT_TYPE);

			// to track progress flow
			stepNum = workFlowService.getStepNumber(applicationId, AppConstants.FLOW_TYPE_APPL_SCREEN);
			workFlow.setStepNum(stepNum);
		} catch (Exception e) {
			logger.error(e.getMessage());
			model.addAttribute("message",AppConstants.APPLICATION_ERROR);
			ErrorDescriptor error = new ErrorDescriptor(this.getClass().getName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage(), e);
			if(session.getAttribute("selectedApp") != null)
				error.setApplicationId((String)session.getAttribute("selectedApp"));
			request.setAttribute("errorBean", error);
			error = null;
			return "forward:/apperror.htm";
		}

		if (request.getSession().getAttribute(AppConstants.APPL_SUBMITTED) != null) {
			applSubmit = ((Boolean) request.getSession().getAttribute(AppConstants.APPL_SUBMITTED)).booleanValue();
		}

		model.addAttribute(AppConstants.APPL_SUBMITTED, applSubmit);

		model.addAttribute("flow", workFlow);
		model.addAttribute("ncpPartList", ncpPartList);
		model.addAttribute("isApp", session.getAttribute(AppConstants.IS_APPLICATION_SELECTED));
		return AppConstants.ECOA_CASE_APPLN_NCP_INFO;
	}

	/**
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/cssp/user/caseApplicationNCPInfo.htm", method = RequestMethod.POST)
	public String processNCPParticipant(HttpServletRequest request, Model model) {
		logger.debug("\n********** IN CaseApplicationNCPController: processNcpParticipant **********\n");

		String action = (String) request.getParameter("action");
		String selectedNCP = (String) request.getParameter(AppConstants.SELECTED_NCP);
		String ncId = (String) request.getSession().getAttribute(AppConstants.USER_LOGIN_NAME);
		String screenToRedirect = "";
		HttpSession session = request.getSession();
		try {
			String applicationId = (String) request.getSession().getAttribute(AppConstants.SELECTED_APPLICATION);
			if (action.equals(AppConstants.NCP_DELETE)) {
				logger.info("action= " + AppConstants.NCP_DELETE + "...............");

				String rValue = casePartService.deleteParticipant(applicationId, selectedNCP, ncId);

				if (rValue != null && rValue.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS)) {
					model.addAttribute("success", "NCP has been deleted successfully.");
				} else {
					//model.addAttribute("message", "Problem encountered during delete!!");
					throw new Exception("CaseApplicationNCPController: processNcpParticipant: NCP delete failed. Return code: "+rValue);
				}

				List<CaseParticipant> ncpPartList = casePartService.getParticipantByPartType(applicationId,
						AppConstants.NCP_PARTICIPANT_TYPE);

				if (ncpPartList.size() == 0) {
					// update step progress
					rValue = workFlowService.updateStepNumber(applicationId, AppConstants.FLOW_TYPE_APPL_SCREEN, 4,
							ncId);

					if (rValue != null && !rValue.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS)) {
						//model.addAttribute("message", "Problem encountered during saving!!");
						throw new Exception("CaseApplicationNCPController: processNCPParticipant: update step number failed. Return code: "+rValue);
					}
				}

				// model.addAttribute("ncpPartList", ncpPartList);

				// screenToRedirect = AppConstants.ECOA_CASE_APPLN_NCP_INFO;
				screenToRedirect = "redirect:/cssp/user/caseApplicationNCPInfo.htm";
			} else if (action.equals(AppConstants.NCP_SELECT)) {
				logger.info("action= " + AppConstants.NCP_SELECT + "...............");

				CaseParticipant partInfo = casePartService.getParticipantByPartId(applicationId, selectedNCP).get(0);

				session.setAttribute(AppConstants.SELECTED_NCP, selectedNCP);

				screenToRedirect = "redirect:/cssp/user/caseApplicationNCPDetails.htm";
			}
			model.addAttribute("isApp", session.getAttribute(AppConstants.IS_APPLICATION_SELECTED));
		} catch (Exception e) {
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
	@RequestMapping("/cssp/user/caseApplicationNCPDetails.htm")
	public String getNCPDetails(HttpServletRequest request, Model model) {
		logger.debug("\n********** IN CaseApplicationNCPController: getNCPDetails **********\n");

		HttpSession session = request.getSession();
		ServletContext ctx = request.getServletContext();
		boolean applSubmit = false;
		CasePartNCPExt ncpExtDetails = null;
		List<Vehicle> vehicleList = null;
		List<CasePartChldAffil> ncpChildList = new ArrayList<CasePartChldAffil>();
		Set<String> courtChildSet = new HashSet<String>();
		Set<String> medicalChildSet = new HashSet<String>();
		Set<String> spousalChildSet = new HashSet<String>();
		Set<String> voluntaryChildSet = new HashSet<String>();

		Map<String, CasePartOther> ncpRelNameMap = new HashMap<String, CasePartOther>();
		Map<String, CaseCourtOrder> ncpOrderMap = new HashMap<String, CaseCourtOrder>();

		try {
			String applicationId = (String) request.getSession().getAttribute(AppConstants.SELECTED_APPLICATION);

			if (session.getAttribute("selectedNCP") != null) {

				String ncpPartId = (String) session.getAttribute(AppConstants.SELECTED_NCP);
				CaseParticipant partInfo = casePartService.getParticipantByPartId(applicationId, ncpPartId).get(0);
				// get NCP extra details.

				List<CasePartNCPExt> ncpExtDetailsList = casePartService.getCasePartNCPExtByPartId(applicationId,
						ncpPartId);
				if (ncpExtDetailsList.size() > 0) {
					ncpExtDetails = ncpExtDetailsList.get(0);

				}

				List<CasePartOther> ncpRelNameList = casePartService.getCasePartOtherByPartId(applicationId, ncpPartId);

				if (ncpRelNameList.size() > 0) {
					for (int i = 0; i < ncpRelNameList.size(); i++) {
						CasePartOther existRelationBean = ncpRelNameList.get(i);
						ncpRelNameMap.put(existRelationBean.getRelationship().trim(), existRelationBean);

					}

				}

				// if any data section has drop down with values
				// yes/no/unknown/blank , then data will be retrieved from db
				// ,if it's corresponding status column had value as '1'.

				if (ncpExtDetails != null && ncpExtDetails.getOwnVehicleStatus().equals("1")) {
					vehicleList = casePartService.getCaseVehiclesByPartId(applicationId, ncpPartId);
				}

				List<CasePartAddress> addrList = casePartService.getParticipantAddr(partInfo.getApplicationId(),
						partInfo.getApplicantId());
				if (addrList.size() > 0) {
					for (CasePartAddress addr : addrList) {
						if (addr.getAddrType().equals(AppConstants.ADDRESS_MAILING)) {
							model.addAttribute("mailAddr", addr);
						} else if (addr.getAddrType().equals(AppConstants.ADDRESS_RESIDENTIAL)) {
							model.addAttribute("resAddr", addr);
						} else if (addr.getAddrType().equals(AppConstants.ADDRESS_OTHER)) {
							model.addAttribute("otherAddr", addr);
						}
					}
				}

				List<CasePartContact> contactList = casePartService.getPartContact(partInfo.getApplicationId(),
						partInfo.getApplicantId());

				if (contactList.size() > 0) {
					for (CasePartContact cont : contactList) {
						if (cont.getContactType().equals(AppConstants.CONTACT_TYPE_HOME)) {
							model.addAttribute("hPh", cont.getContactInfo());
						} else if (cont.getContactType().equals(AppConstants.CONTACT_TYPE_WORK)) {
							model.addAttribute("wPh", cont.getContactInfo());
						} else if (cont.getContactType().equals(AppConstants.CONTACT_TYPE_CELL)) {
							model.addAttribute("cPh", cont.getContactInfo());
						} else if (cont.getContactType().equals(AppConstants.CONTACT_TYPE_EMAIL)) {
							model.addAttribute("email", cont.getContactInfo());
						}
					}
				}

				// Employment information
				CasePartEmp empBean = new CasePartEmp();
				List<CasePartEmp> empList = casePartService.getPartEmp(partInfo.getApplicationId(),
						partInfo.getApplicantId());
				if (empList.size() > 0) {
					empBean = empList.get(0);
					ThirdParty thrdPartyBean = new ThirdParty();
					List<ThirdParty> trdPtyList = casePartService.getThirdPartyBy3ptyId(partInfo.getApplicationId(),
							empBean.getThirdPartyId());
					if (trdPtyList.size() > 0) {
						empBean.setThirdPartyInfo(trdPtyList.get(0));
					}
				}

				List<CasePartIncome> incomeList = casePartService.getParticipantIncome(partInfo.getApplicationId(),
						partInfo.getApplicantId());

				// List all the children linked to the NCP.

				ncpChildList = casePartService.getNCPLinkedChildren(applicationId, ncpPartId);

				// get relevant court orders for NCP.

				if (ncpExtDetails != null && ncpExtDetails.getOrderStatus().equals("1")) {
					List<CaseCourtOrder> ncpOrderList = casePartService.getCaseCourtOrderByPartId(applicationId,
							ncpPartId);

					if (ncpOrderList.size() > 0) {
						// orderExists = true;

						for (int i = 0; i < ncpOrderList.size(); i++) {
							CaseCourtOrder existOrderBean = ncpOrderList.get(i);
							ncpOrderMap.put(existOrderBean.getSupportType().trim(), existOrderBean);

							// get the linked children for each order type from
							// ACTW_PART_ORDER table.

							String orderNum = existOrderBean.getOrderNm();

							if (orderNum != null && !orderNum.equals("")) {
								// get list of children linked to the given
								// support
								// order.

								List<CasePartOrder> selectChildList = casePartService
										.getCasePartOrderByOrderNum(orderNum);

								int OrderType = Integer.parseInt(existOrderBean.getSupportType());

								switch (OrderType) {

								case 1:

									for (CasePartOrder child : selectChildList)
										courtChildSet.add(child.getChildApplicantId());

									break;

								case 2:

									for (CasePartOrder child : selectChildList)
										medicalChildSet.add(child.getChildApplicantId());

									break;

								case 3:

									for (CasePartOrder child : selectChildList)
										spousalChildSet.add(child.getChildApplicantId());

									break;

								case 4:

									for (CasePartOrder child : selectChildList)
										voluntaryChildSet.add(child.getChildApplicantId());

									break;

								}

							}

						}
					}
				}

				model.addAttribute("ncpExtDetails", ncpExtDetails);
				model.addAttribute("ncpRelNameMap", ncpRelNameMap);
				model.addAttribute("vehicles", vehicleList);
				model.addAttribute("empInfo", empBean);
				model.addAttribute("incList", incomeList);

				model.addAttribute("incList", incomeList);
				model.addAttribute("ncpOrderMap", ncpOrderMap);

				model.addAttribute("courtChildSet", courtChildSet);
				model.addAttribute("medicalChildSet", medicalChildSet);
				model.addAttribute("spousalChildSet", spousalChildSet);
				model.addAttribute("voluntaryChildSet", voluntaryChildSet);

				model.addAttribute("mode", AppConstants.DB_UPDATE);
				model.addAttribute("partInfo", partInfo);
			} else {
				model.addAttribute("mode", AppConstants.DB_CREATE);
			}
			// to track progress flow
			long stepNum = workFlowService.getStepNumber(applicationId, AppConstants.FLOW_TYPE_APPL_SCREEN);

			WorkFlow workFlow = new WorkFlow();

			workFlow.setStepNum(stepNum);

			if (request.getSession().getAttribute(AppConstants.APPL_SUBMITTED) != null) {
				applSubmit = ((Boolean) request.getSession().getAttribute(AppConstants.APPL_SUBMITTED)).booleanValue();
			}

			model.addAttribute(AppConstants.APPL_SUBMITTED, applSubmit);

			model.addAttribute("flow", workFlow);
			model.addAttribute("isApp", session.getAttribute(AppConstants.IS_APPLICATION_SELECTED));
			model.addAttribute(AppConstants.CODE_LOOKUP_LANG_LIST,
					(List<CodeLookUp>) ctx.getAttribute(AppConstants.CODE_LOOKUP_LANG_LIST));
			model.addAttribute(AppConstants.CODE_LOOKUP_GENDER_LIST,
					(List<CodeLookUp>) ctx.getAttribute(AppConstants.CODE_LOOKUP_GENDER_LIST));
			model.addAttribute(AppConstants.CODE_LOOKUP_ETHNCGRP_LIST,
					(List<CodeLookUp>) ctx.getAttribute(AppConstants.CODE_LOOKUP_ETHNCGRP_LIST));
			model.addAttribute(AppConstants.CODE_LOOKUP_SUFFIX_LIST,
					(List<CodeLookUp>) ctx.getAttribute(AppConstants.CODE_LOOKUP_SUFFIX_LIST));
			model.addAttribute(AppConstants.CODE_LOOKUP_SASSIST_LIST,
					(List<CodeLookUp>) ctx.getAttribute(AppConstants.CODE_LOOKUP_SASSIST_LIST));
			model.addAttribute(AppConstants.CODE_LOOKUP_STATE_LIST,
					(List<CodeLookUp>) ctx.getAttribute(AppConstants.CODE_LOOKUP_STATE_LIST));
			model.addAttribute(AppConstants.CODE_LOOKUP_COUNTY_LIST,
					(List<CodeLookUp>) ctx.getAttribute(AppConstants.CODE_LOOKUP_COUNTY_LIST));
			model.addAttribute(AppConstants.CODE_LOOKUP_INCOME_TYPE_LIST,
					(List<CodeLookUp>) ctx.getAttribute(AppConstants.CODE_LOOKUP_INCOME_TYPE_LIST));
			model.addAttribute(AppConstants.CODE_LOOKUP_MARITAL_LIST,
					(List<CodeLookUp>) ctx.getAttribute(AppConstants.CODE_LOOKUP_MARITAL_LIST));
			model.addAttribute(AppConstants.CODE_LOOKUP_MILITARY_LIST,
					(List<CodeLookUp>) ctx.getAttribute(AppConstants.CODE_LOOKUP_MILITARY_LIST));
			model.addAttribute(AppConstants.CODE_LOOKUP_SELOPTION_LIST,
					(List<CodeLookUp>) ctx.getAttribute(AppConstants.CODE_LOOKUP_SELOPTION_LIST));
			model.addAttribute(AppConstants.CODE_LOOKUP_MALBRANCH_LIST,
					(List<CodeLookUp>) ctx.getAttribute(AppConstants.CODE_LOOKUP_MALBRANCH_LIST));
			model.addAttribute(AppConstants.CODE_LOOKUP_HAIRCOLOR_LIST,
					(List<CodeLookUp>) ctx.getAttribute(AppConstants.CODE_LOOKUP_HAIRCOLOR_LIST));
			model.addAttribute(AppConstants.CODE_LOOKUP_EYECOLOR_LIST,
					(List<CodeLookUp>) ctx.getAttribute(AppConstants.CODE_LOOKUP_EYECOLOR_LIST));
			model.addAttribute(AppConstants.CODE_LOOKUP_PAYFREQ_LIST,
					(List<CodeLookUp>) ctx.getAttribute(AppConstants.CODE_LOOKUP_PAYFREQ_LIST));

			model.addAttribute("ncpChildList", ncpChildList);

		} catch (Exception e) {
			logger.error(e.getMessage());
			model.addAttribute("message",AppConstants.APPLICATION_ERROR);
			ErrorDescriptor error = new ErrorDescriptor(this.getClass().getName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage(), e);
			if(session.getAttribute("selectedApp") != null)
				error.setApplicationId((String)session.getAttribute("selectedApp"));
			request.setAttribute("errorBean", error);
			error = null;
			return "forward:/apperror.htm";
		}
		return AppConstants.ECOA_CASE_APPLN_NCP_DETAILS;
	}

	/**
	 * @param request
	 * @param response
	 * @param partInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cssp/user/caseApplicationNCPDetails.htm", method = RequestMethod.POST)
	public String processNCPDetails(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("partInfo") CaseParticipant partInfo) throws Exception {
		logger.debug("\n********** IN CaseApplicationNCPController: processNCPParticipant **********\n");

		ServletContext ctx = request.getServletContext();
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		CasePartNCPExt ncpExtDetails = new CasePartNCPExt();
		String[] selectedOrderTypes = null;

		String mode = request.getParameter("mode");
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute(AppConstants.USER_LOGIN_NAME);
		partInfo.setApplicationId((String) session.getAttribute(AppConstants.SELECTED_APPLICATION));
		ncpExtDetails.setApplicationId((String) session.getAttribute(AppConstants.SELECTED_APPLICATION));
		List<Vehicle> vehicleList = new ArrayList<Vehicle>();
		Map<String, CasePartOther> selectRelNamesMap = new HashMap<String, CasePartOther>();
		Map<String, String[]> childrenMap = new HashMap<String, String[]>();

		Map<String, CaseCourtOrder> selectedOrderMap = new HashMap<String, CaseCourtOrder>();

		// Map<String, String[]> parameterMap = request.getParameterMap();
		Map paramList = new HashMap();

		for (Object obj : request.getParameterMap().entrySet()) {
			Map.Entry<String, String[]> entry = (Map.Entry<String, String[]>) obj;
			if (entry.getValue() != null && entry.getValue().length > 0) {
				paramList.put(entry.getKey(), entry.getValue()[0]);
			} else {
				paramList.put(entry.getKey(), entry.getValue());
			}
		}
		try{
			NCPparamData pageData = new NCPparamData();
			HelperParamData helper = new HelperParamData();
			pageData.setParticipant(partInfo);
	
			// mailing address
			helper.populateAddress(pageData, paramList, AppConstants.ADDRESS_MAILING, AppConstants.ADDRESS_MAILING_CODE,
					AppConstants.NCP_PARTICIPANT_TYPE);
	
			if (paramList.get("res_addr") == null) {
				helper.populateAddress(pageData, paramList, AppConstants.ADDRESS_RESIDENTIAL,
						AppConstants.ADDRESS_RESIDENTIAL_CODE, AppConstants.NCP_PARTICIPANT_TYPE);
			}
	
			helper.populateAddress(pageData, paramList, AppConstants.ADDRESS_OTHER, AppConstants.ADDRESS_OTHER_CODE,
					AppConstants.NCP_PARTICIPANT_TYPE);
	
			// contact information
			helper.populateContact(pageData, paramList, AppConstants.CONTACT_TYPE_HOME_CODE, AppConstants.CONTACT_TYPE_HOME,
					AppConstants.NCP_PARTICIPANT_TYPE);
			helper.populateContact(pageData, paramList, AppConstants.CONTACT_TYPE_WORK_CODE, AppConstants.CONTACT_TYPE_WORK,
					AppConstants.NCP_PARTICIPANT_TYPE);
			helper.populateContact(pageData, paramList, AppConstants.CONTACT_TYPE_CELL_CODE, AppConstants.CONTACT_TYPE_CELL,
					AppConstants.NCP_PARTICIPANT_TYPE);
			helper.populateContact(pageData, paramList, null, AppConstants.CONTACT_TYPE_EMAIL,
					AppConstants.NCP_PARTICIPANT_TYPE);
	
			// employment information
	
			if (paramList.get("emp_chk").equals(AppConstants.CONDITION_TRUE)) {
				helper.populateCurrEmpInfo(pageData, paramList, AppConstants.NCP_PARTICIPANT_TYPE);
			} else {
				helper.populatePrevEmpInfo(pageData, paramList, AppConstants.NCP_PARTICIPANT_TYPE);
			}
	
			// income source information
			helper.populateIncomeSrc(pageData, paramList, AppConstants.NCP_PARTICIPANT_TYPE);
	
			// collect Participant (NCP type) extra details to save in ACTW_NCP_EXT
			// table.
	
			if (request.getParameter("biCty") != null && !request.getParameter("biCty").equals("")) {
				ncpExtDetails.setBirthCityNm(request.getParameter("biCty"));
	
			}
	
			if (request.getParameter("state") != null && !request.getParameter("state").equals("")) {
				ncpExtDetails.setBirthStateNm(request.getParameter("state"));
	
			}
	
			if (request.getParameter("cnty") != null && !request.getParameter("cnty").equals("")) {
				ncpExtDetails.setBirthCountyNm(request.getParameter("cnty"));
	
			}
	
			if (request.getParameter("cntry") != null && !request.getParameter("cntry").equals("")) {
				ncpExtDetails.setBirthCountryNm(request.getParameter("cntry"));
	
			}
	
			if (request.getParameter("height") != null && !request.getParameter("height").equals("")) {
				ncpExtDetails.setHeight(request.getParameter("height"));
	
			}
	
			if (request.getParameter("weight") != null && !request.getParameter("weight").equals("")) {
				ncpExtDetails.setWeight(Double.parseDouble(request.getParameter("weight")));
	
			}
	
			if (request.getParameter("hairclr") != null && !request.getParameter("hairclr").equals("")) {
				ncpExtDetails.setHairColor(request.getParameter("hairclr"));
	
			}
			if (request.getParameter("eyeClr") != null && !request.getParameter("eyeClr").equals("")) {
				ncpExtDetails.setEyeColor(request.getParameter("eyeClr"));
	
			}
			if (request.getParameter("idMrks") != null && !request.getParameter("idMrks").equals("")) {
				ncpExtDetails.setMarks(request.getParameter("idMrks"));
	
			}
			if (request.getParameter("additionalInfo") != null && request.getParameter("additionalInfo").equals("1")) {
	
				if (request.getParameter("noteText") != null)
	
					ncpExtDetails.setAdditionalNote(request.getParameter("noteText"));
			}
			// collect driverLicence section details
			if (request.getParameter("hasLicense") != null && !request.getParameter("hasLicense").equals("")) {
				ncpExtDetails.setDriverLicStatus(request.getParameter("hasLicense"));
			}
	
			if (request.getParameter("hasLicense") != null && request.getParameter("hasLicense").equals("1")) {
	
				if (request.getParameter("licNum") != null && !request.getParameter("licNum").equals("")) {
					ncpExtDetails.setDriverlicNum(request.getParameter("licNum"));
	
				}
	
				if (request.getParameter("licState") != null && !request.getParameter("licState").equals("")) {
					ncpExtDetails.setLicenseState(request.getParameter("licState"));
	
				}
			}
	
			if (request.getParameter("maritalStatus") != null)
	
			{
	
				String maritalStatus = request.getParameter("maritalStatus");
	
				int marStatus = Integer.parseInt(maritalStatus);
	
				CasePartOther ncpSpouseName = new CasePartOther();
				switch (marStatus) {
				case 1:
	
					ncpExtDetails.setMaritalStatus("1");
	
					if (request.getParameter("mDt") != null && !request.getParameter("mDt").equals("")) {
	
						ncpExtDetails.setMaritualDt(df.parse(request.getParameter("mDt")));
	
					}
	
					if (request.getParameter("first_m") != null && !request.getParameter("first_m").equals("")) {
						ncpSpouseName.setFirstNm(request.getParameter("first_m"));
	
					}
	
					if (request.getParameter("last_m") != null && !request.getParameter("last_m").equals("")) {
						ncpSpouseName.setLastNm(request.getParameter("last_m"));
	
					}
	
					if (request.getParameter("middle_m") != null && !request.getParameter("middle_m").equals("")) {
						ncpSpouseName.setMiddleNm(request.getParameter("middle_m"));
	
					}
	
					break;
	
				case 3:
	
					ncpExtDetails.setMaritalStatus("3");
	
					if (request.getParameter("sDt") != null && !request.getParameter("sDt").equals("")) {
	
						ncpExtDetails.setMaritualDt(df.parse(request.getParameter("sDt")));
	
					}
	
					if (request.getParameter("s_first") != null && !request.getParameter("s_first").equals("")) {
						ncpSpouseName.setFirstNm(request.getParameter("s_first"));
	
					}
	
					if (request.getParameter("s_last") != null && !request.getParameter("s_last").equals("")) {
						ncpSpouseName.setLastNm(request.getParameter("s_last"));
	
					}
	
					if (request.getParameter("s_middle") != null && !request.getParameter("s_middle").equals("")) {
						ncpSpouseName.setMiddleNm(request.getParameter("s_middle"));
	
					}
	
					break;
	
				case 2:
					ncpExtDetails.setMaritalStatus("2");
	
					if (request.getParameter("dDt") != null && !request.getParameter("dDt").equals("")) {
	
						ncpExtDetails.setMaritualDt(df.parse(request.getParameter("dDt")));
	
					}
	
					if (request.getParameter("d_first") != null && !request.getParameter("d_first").equals("")) {
						ncpSpouseName.setFirstNm(request.getParameter("d_first"));
	
					}
	
					if (request.getParameter("d_last") != null && !request.getParameter("d_last").equals("")) {
						ncpSpouseName.setLastNm(request.getParameter("d_last"));
	
					}
	
					if (request.getParameter("d_middle") != null && !request.getParameter("d_middle").equals("")) {
						ncpSpouseName.setMiddleNm(request.getParameter("d_middle"));
	
					}
	
					break;
	
				default:
	
					ncpExtDetails.setMaritalStatus(maritalStatus);
	
					break;
				}
	
				if (checkRelNameExists(ncpSpouseName))
	
				{ // In CODE_DETAIL table , 3 --> NCP spouse name
					ncpSpouseName.setRelationship(AppConstants.NCP_SPOUSE_NAME);
	
					selectRelNamesMap.put(AppConstants.NCP_SPOUSE_NAME, ncpSpouseName);
	
				}
			}
			// collect Military section details.
	
			if (request.getParameter("inMilitary") != null && !request.getParameter("inMilitary").equals("")) {
				ncpExtDetails.setMltryStatus(request.getParameter("inMilitary"));
	
			}
	
			if (request.getParameter("inMilitary") != null && request.getParameter("inMilitary").equals("1")) {
				if (request.getParameter("branch") != null && !request.getParameter("branch").equals("")) {
					ncpExtDetails.setMiltryBranch(request.getParameter("branch"));
	
				}
				if (request.getParameter("status") != null && !request.getParameter("status").equals("")) {
					ncpExtDetails.setMiltryStatus(request.getParameter("status"));
	
				}
	
				if (request.getParameter("station") != null && !request.getParameter("station").equals("")) {
					ncpExtDetails.setMiltryStation(request.getParameter("station"));
	
				}
			}
			// collect details for parole section
	
			if (request.getParameter("onParole") != null && !request.getParameter("onParole").equals("")) {
	
				ncpExtDetails.setParoleStatus(request.getParameter("onParole"));
			}
	
			if (request.getParameter("onParole") != null && request.getParameter("onParole").equals("1")) {
				if (request.getParameter("paroleLoc") != null && !request.getParameter("paroleLoc").equals("")) {
					ncpExtDetails.setParole(request.getParameter("paroleLoc"));
	
				}
			}
	
			// collect details for incarcerated section.
	
			if (request.getParameter("isIncarcerated") != null && !request.getParameter("isIncarcerated").equals("")) {
	
				ncpExtDetails.setIncarStatus(request.getParameter("isIncarcerated"));
	
			}
	
			if (request.getParameter("isIncarcerated") != null && request.getParameter("isIncarcerated").equals("1")) {
				if (request.getParameter("incarcLoc") != null && !request.getParameter("incarcLoc").equals("")) {
					ncpExtDetails.setIncarcerated(request.getParameter("incarcLoc"));
	
				}
			}
	
			if (request.getParameter("onWrkRel") != null && !request.getParameter("onWrkRel").equals("")) {
	
				ncpExtDetails.setWorkReleaseStatus(request.getParameter("onWrkRel"));
	
			}
	
			if (request.getParameter("onWrkRel") != null && request.getParameter("onWrkRel").equals("1")) {
				if (request.getParameter("wrkRelLoc") != null && !request.getParameter("wrkRelLoc").equals("")) {
					ncpExtDetails.setWorkRelease(request.getParameter("wrkRelLoc"));
	
				}
			}
			// collect details for arrest section
			if (request.getParameter("isArrested") != null && !request.getParameter("isArrested").equals("")) {
				ncpExtDetails.setArrestStatus(request.getParameter("isArrested"));
	
			}
	
			if (request.getParameter("isArrested") != null && request.getParameter("isArrested").equals("1")) {
				if (request.getParameter("arrestCy") != null && !request.getParameter("arrestCy").equals("")) {
					ncpExtDetails.setArrestCity(request.getParameter("arrestCy"));
	
				}
	
				if (request.getParameter("arrestSt") != null && !request.getParameter("arrestSt").equals("")) {
					ncpExtDetails.setArrestState(request.getParameter("arrestSt"));
	
				}
	
				if (request.getParameter("arrestDt") != null && !request.getParameter("arrestDt").equals("")) {
	
					ncpExtDetails.setArrestDt(df.parse(request.getParameter("arrestDt")));
				}
			}
	
			// collect NCP father name
			CasePartOther ncpFatherName = new CasePartOther();
	
			if (request.getParameter("f_first") != null && !request.getParameter("f_first").equals("")) {
				ncpFatherName.setFirstNm(request.getParameter("f_first"));
	
			}
	
			if (request.getParameter("f_last") != null && !request.getParameter("f_last").equals("")) {
				ncpFatherName.setLastNm(request.getParameter("f_last"));
	
			}
	
			if (request.getParameter("f_middle") != null && !request.getParameter("f_middle").equals("")) {
				ncpFatherName.setMiddleNm(request.getParameter("f_middle"));
	
			}
	
			if (checkRelNameExists(ncpFatherName))
	
			{ // In CODE_DETAIL table , 1 --> NCP father name
				ncpFatherName.setRelationship(AppConstants.NCP_FATHER_NAME);
				selectRelNamesMap.put(AppConstants.NCP_FATHER_NAME, ncpFatherName);
	
			}
	
			// collect NCP mother name
			CasePartOther ncpMotherName = new CasePartOther();
	
			if (request.getParameter("m_first") != null && !request.getParameter("m_first").equals("")) {
				ncpMotherName.setFirstNm(request.getParameter("m_first"));
	
			}
	
			if (request.getParameter("m_last") != null && !request.getParameter("m_last").equals("")) {
				ncpMotherName.setLastNm(request.getParameter("m_last"));
	
			}
	
			if (request.getParameter("m_middle") != null && !request.getParameter("m_middle").equals("")) {
				ncpMotherName.setMiddleNm(request.getParameter("m_middle"));
	
			}
	
			if (checkRelNameExists(ncpMotherName))
	
			{ // In CODE_DETAIL table , 2 --> NCP mother name
				ncpMotherName.setRelationship(AppConstants.NCP_MOTHER_NAME);
				selectRelNamesMap.put(AppConstants.NCP_MOTHER_NAME, ncpMotherName);
	
			}
	
			// collect details for vehicleInfo section.
			String vehicleLst = null;
			if (request.getParameter("vehicleInfo") != null && !request.getParameter("vehicleInfo").equals("")) {
				ncpExtDetails.setOwnVehicleStatus(request.getParameter("vehicleInfo"));
			}
	
			if (request.getParameter("vehicleInfo") != null && request.getParameter("vehicleInfo").equals("1")) {
				boolean data_entered = false;
	
				if (request.getParameter("vehicleLst") != null) {
					vehicleLst = request.getParameter("vehicleLst");
	
				}
	
				if (!vehicleLst.trim().equals("")) {
					String[] typeList = vehicleLst.split(",");
					for (int i = 0; i < typeList.length; i++) {
						Vehicle vehicle = new Vehicle();
	
						String incType = typeList[i];
	
						if (request.getParameter("make_" + incType) != null
								&& !request.getParameter("make_" + incType).equals("")) {
							vehicle.setMake(request.getParameter("make_" + incType));
						}
						if (request.getParameter("model_" + incType) != null
								&& !request.getParameter("model_" + incType).equals("")) {
							vehicle.setModel(request.getParameter("model_" + incType));
						}
						if (request.getParameter("year_" + incType) != null
								&& !request.getParameter("year_" + incType).equals("")) {
							vehicle.setYear(request.getParameter("year_" + incType));
						}
						vehicleList.add(vehicle);
	
					}
				}
	
			}
	
			// receiving court order parameters
	
			if (request.getParameter("isSupportOrder") != null && !request.getParameter("isSupportOrder").equals("")) {
				ncpExtDetails.setOrderStatus(request.getParameter("isSupportOrder"));
			}
	
			if (request.getParameter("isSupportOrder") != null && request.getParameter("isSupportOrder").equals("1")) {
				if (request.getParameter("support_order") != null) {
					selectedOrderTypes = request.getParameterValues("support_order");
	
					for (int i = 0; i < selectedOrderTypes.length; i++)
	
					{
	
						int selectedOrderType = Integer.parseInt(selectedOrderTypes[i]);
	
						CaseCourtOrder orderBean = new CaseCourtOrder();
	
						switch (selectedOrderType)
	
						{
						case 1:
							if (request.getParameter("court_dnumber") != null
									&& !request.getParameter("court_dnumber").equals("")) {
								orderBean.setDocketNm(request.getParameter("court_dnumber"));
	
							}
	
							if (request.getParameter("court_odate") != null
									&& !request.getParameter("court_odate").equals("")) {
								orderBean.setOrderEffDt(df.parse(request.getParameter("court_odate")));
							}
	
							if (request.getParameter("court_ostate") != null
									&& !request.getParameter("court_ostate").equals("")) {
								orderBean.setState((request.getParameter("court_ostate")));
	
							}
	
							if (request.getParameter("court_ocnty") != null
									&& !request.getParameter("court_ocnty").equals("")) {
								orderBean.setCounty((request.getParameter("court_ocnty")));
	
							}
	
							if (request.getParameter("court_oamt") != null
									&& !request.getParameter("court_oamt").equals("")) {
								orderBean.setOrderAmount(Double.parseDouble(request.getParameter("court_oamt")));
	
							}
	
							if (request.getParameter("court_ofcy") != null
									&& !request.getParameter("court_ofcy").equals("")) {
								orderBean.setPayFreq(request.getParameter("court_ofcy"));
	
							}
	
							if (request.getParameter("court_oamt_due") != null
									&& !request.getParameter("court_oamt_due").equals("")) {
								orderBean.setAmtPastDue(Double.parseDouble(request.getParameter("court_oamt_due")));
	
							}
							if (request.getParameter("court_payor") != null
									&& !request.getParameter("court_payor").equals("")) {
								orderBean.setPayorNm(request.getParameter("court_payor"));
	
							}
	
							if (request.getParameter("court_recip") != null
									&& !request.getParameter("court_recip").equals("")) {
								orderBean.setRecipientNm(request.getParameter("court_recip"));
	
							}
	
							orderBean.setSupportType("1");
	
							String[] courtChildren = request.getParameterValues("court_child");
	
							// having children selected for court order is
							// mandatory, otherwise no need to save the court order.
	
							if (courtChildren != null && courtChildren.length >= 1) {
	
								selectedOrderMap.put("1", orderBean);
	
								childrenMap.put("1", courtChildren);
							}
	
							break;
	
						case 2:
							if (request.getParameter("medical_dnumber") != null
									&& !request.getParameter("medical_dnumber").equals("")) {
								orderBean.setDocketNm(request.getParameter("medical_dnumber"));
	
							}
	
							if (request.getParameter("medical_odate") != null
									&& !request.getParameter("medical_odate").equals("")) {
								orderBean.setOrderEffDt(df.parse(request.getParameter("medical_odate")));
							}
	
							if (request.getParameter("medical_ostate") != null
									&& !request.getParameter("medical_ostate").equals("")) {
								orderBean.setState(request.getParameter("medical_ostate"));
	
							}
	
							if (request.getParameter("medical_ocnty") != null
									&& !request.getParameter("medical_ocnty").equals("")) {
								orderBean.setCounty(request.getParameter("medical_ocnty"));
	
							}
	
							if (request.getParameter("medical_oamt") != null
									&& !request.getParameter("medical_oamt").equals("")) {
								orderBean.setOrderAmount(Double.parseDouble(request.getParameter("medical_oamt")));
	
							}
	
							if (request.getParameter("medical_ofcy") != null
									&& !request.getParameter("medical_ofcy").equals("")) {
								orderBean.setPayFreq(request.getParameter("medical_ofcy"));
	
							}
	
							if (request.getParameter("medical_oamt_due") != null
									&& !request.getParameter("medical_oamt_due").equals("")) {
								orderBean.setAmtPastDue(Double.parseDouble(request.getParameter("medical_oamt_due")));
	
							}
							if (request.getParameter("medical_payor") != null
									&& !request.getParameter("medical_payor").equals("")) {
								orderBean.setPayorNm(request.getParameter("medical_payor"));
	
							}
	
							if (request.getParameter("medical_recip") != null
									&& !request.getParameter("medical_recip").equals("")) {
								orderBean.setRecipientNm(request.getParameter("medical_recip"));
	
							}
	
							orderBean.setSupportType("2");
	
							String[] medicalChildren = request.getParameterValues("medical_child");
	
							if (medicalChildren != null && medicalChildren.length >= 1) {
	
								selectedOrderMap.put("2", orderBean);
	
								childrenMap.put("2", medicalChildren);
							}
	
							break;
						case 3:
							if (request.getParameter("spousal_dnumber") != null
									&& !request.getParameter("spousal_dnumber").equals("")) {
								orderBean.setDocketNm(request.getParameter("spousal_dnumber"));
	
							}
	
							if (request.getParameter("spousal_odate") != null
									&& !request.getParameter("spousal_odate").equals("")) {
								orderBean.setOrderEffDt(df.parse(request.getParameter("spousal_odate")));
							}
	
							if (request.getParameter("spousal_ostate") != null
									&& !request.getParameter("spousal_ostate").equals("")) {
								orderBean.setState(request.getParameter("spousal_ostate"));
	
							}
	
							if (request.getParameter("spousal_ocnty") != null
									&& !request.getParameter("spousal_ocnty").equals("")) {
								orderBean.setCounty(request.getParameter("spousal_ocnty"));
	
							}
	
							if (request.getParameter("spousal_oamt") != null
									&& !request.getParameter("spousal_oamt").equals("")) {
								orderBean.setOrderAmount(Double.parseDouble(request.getParameter("spousal_oamt")));
	
							}
	
							if (request.getParameter("spousal_ofcy") != null
									&& !request.getParameter("spousal_ofcy").equals("")) {
								orderBean.setPayFreq(request.getParameter("spousal_ofcy"));
	
							}
	
							if (request.getParameter("spousal_oamt_due") != null
									&& !request.getParameter("spousal_oamt_due").equals("")) {
								orderBean.setAmtPastDue(Double.parseDouble(request.getParameter("spousal_oamt_due")));
	
							}
							if (request.getParameter("spousal_payor") != null
									&& !request.getParameter("spousal_payor").equals("")) {
								orderBean.setPayorNm(request.getParameter("spousal_payor"));
	
							}
	
							if (request.getParameter("spousal_recip") != null
									&& !request.getParameter("spousal_recip").equals("")) {
								orderBean.setRecipientNm(request.getParameter("spousal_recip"));
	
							}
	
							orderBean.setSupportType("3");
	
							String[] spousalChildren = request.getParameterValues("spousal_child");
	
							if (spousalChildren != null && spousalChildren.length >= 1) {
	
								selectedOrderMap.put("3", orderBean);
	
								childrenMap.put("3", spousalChildren);
							}
	
							break;
	
						case 4:
							if (request.getParameter("voluntary_dnumber") != null
									&& !request.getParameter("voluntary_dnumber").equals("")) {
								orderBean.setDocketNm(request.getParameter("voluntary_dnumber"));
	
							}
	
							if (request.getParameter("voluntary_odate") != null
									&& !request.getParameter("voluntary_odate").equals("")) {
								orderBean.setOrderEffDt(df.parse(request.getParameter("voluntary_odate")));
							}
	
							if (request.getParameter("voluntary_ostate") != null
									&& !request.getParameter("voluntary_ostate").equals("")) {
								orderBean.setState(request.getParameter("voluntary_ostate"));
	
							}
	
							if (request.getParameter("voluntary_ocnty") != null
									&& !request.getParameter("voluntary_ocnty").equals("")) {
								orderBean.setCounty(request.getParameter("voluntary_ocnty"));
	
							}
	
							if (request.getParameter("voluntary_oamt") != null
									&& !request.getParameter("voluntary_oamt").equals("")) {
								orderBean.setOrderAmount(Double.parseDouble(request.getParameter("voluntary_oamt")));
	
							}
	
							if (request.getParameter("voluntary_ofcy") != null
									&& !request.getParameter("voluntary_ofcy").equals("")) {
								orderBean.setPayFreq(request.getParameter("voluntary_ofcy"));
	
							}
	
							if (request.getParameter("voluntary_oamt_due") != null
									&& !request.getParameter("voluntary_oamt_due").equals("")) {
								orderBean.setAmtPastDue(Double.parseDouble(request.getParameter("voluntary_oamt_due")));
	
							}
							if (request.getParameter("voluntary_payor") != null
									&& !request.getParameter("voluntary_payor").equals("")) {
								orderBean.setPayorNm(request.getParameter("voluntary_payor"));
	
							}
	
							if (request.getParameter("voluntary_recip") != null
									&& !request.getParameter("voluntary_recip").equals("")) {
								orderBean.setRecipientNm(request.getParameter("voluntary_recip"));
	
							}
	
							orderBean.setSupportType("4");
	
							String[] voluntaryChildren = request.getParameterValues("voluntary_child");
	
							if (voluntaryChildren != null && voluntaryChildren.length >= 1) {
	
								selectedOrderMap.put("4", orderBean);
	
								childrenMap.put("4", voluntaryChildren);
							}
	
						}
	
					}
	
				}
			}
			pageData.setNcpExtDetails(ncpExtDetails);
			pageData.setRelNamesMap(selectRelNamesMap);
			pageData.setVehicleList(vehicleList);
			pageData.setSelectedOrderMap(selectedOrderMap);
			pageData.setChildrenMap(childrenMap);


			if (mode.equals(AppConstants.DB_CREATE)) {
				logger.info("mode= " + AppConstants.DB_CREATE + " ...........");

				String ncpPartId = casePartService.CreateCaseParticipant(partInfo, ((ParamData) pageData), userId,
						AppConstants.NCP_PARTICIPANT_TYPE);

				if (ncpPartId != "") {
					String rValue = AppConstants.OPERATION_SUCCESS;
					// update step progress
					if (workFlowService.getStepNumber(partInfo.getApplicationId(),
							AppConstants.FLOW_TYPE_APPL_SCREEN) < 5) {
						rValue = workFlowService.updateStepNumber(partInfo.getApplicationId(),
								AppConstants.FLOW_TYPE_APPL_SCREEN, 5, userId);
					}

					if (rValue != null && rValue.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS)) {
						model.addAttribute("success", "Information is saved successfully!! Go back to NCP list to add other noncustodial parents or select next to proceed to section 5.");
					}else{
						throw new Exception("CaseApplicationNCPController: processNCPDetails: update step number failed. Return code: "+rValue);
					}
				} else {
					//model.addAttribute("message", "Problem encountered during saving!!");
					throw new Exception("CaseApplicationNCPController: processNCPDetails: NCP create failed.");
				}

				session.setAttribute(AppConstants.SELECTED_NCP, ncpPartId);
			} else if (mode.equals(AppConstants.DB_UPDATE)) {
				logger.info("mode= " + AppConstants.DB_UPDATE + " ...........");

				partInfo.setApplicantId((String) session.getAttribute(AppConstants.SELECTED_NCP));

				String rValue = casePartService.updateParticipant(partInfo, ((ParamData) pageData), userId,
						AppConstants.NCP_PARTICIPANT_TYPE);

				if (rValue != null && rValue.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS)) {
					model.addAttribute("success", "Information is saved successfully!! Go back to NCP list to add other noncustodial parents or select next to proceed to section 5.");
				} else {
					//model.addAttribute("message", "Problem encountered during saving!!");
					throw new Exception("CaseApplicationNCPController: processNCPDetails: update NCP failed. Return code: "+rValue);
				}
			}
			model.addAttribute(AppConstants.CODE_LOOKUP_LANG_LIST,
					(List<CodeLookUp>) ctx.getAttribute(AppConstants.CODE_LOOKUP_LANG_LIST));
			model.addAttribute(AppConstants.CODE_LOOKUP_GENDER_LIST,
					(List<CodeLookUp>) ctx.getAttribute(AppConstants.CODE_LOOKUP_GENDER_LIST));
			model.addAttribute(AppConstants.CODE_LOOKUP_ETHNCGRP_LIST,
					(List<CodeLookUp>) ctx.getAttribute(AppConstants.CODE_LOOKUP_ETHNCGRP_LIST));
			model.addAttribute(AppConstants.CODE_LOOKUP_SUFFIX_LIST,
					(List<CodeLookUp>) ctx.getAttribute(AppConstants.CODE_LOOKUP_SUFFIX_LIST));
			model.addAttribute(AppConstants.CODE_LOOKUP_SASSIST_LIST,
					(List<CodeLookUp>) ctx.getAttribute(AppConstants.CODE_LOOKUP_SASSIST_LIST));
			model.addAttribute(AppConstants.CODE_LOOKUP_STATE_LIST,
					(List<CodeLookUp>) ctx.getAttribute(AppConstants.CODE_LOOKUP_STATE_LIST));
			model.addAttribute(AppConstants.CODE_LOOKUP_COUNTY_LIST,
					(List<CodeLookUp>) ctx.getAttribute(AppConstants.CODE_LOOKUP_COUNTY_LIST));

		} catch (Exception e) {
			logger.error(e.getMessage());
			model.addAttribute("message",AppConstants.APPLICATION_ERROR);
			ErrorDescriptor error = new ErrorDescriptor(this.getClass().getName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage(), e);
			if(session.getAttribute("selectedApp") != null)
				error.setApplicationId((String)session.getAttribute("selectedApp"));
			request.setAttribute("errorBean", error);
			return "forward:/apperror.htm";
		}

		return "redirect:/cssp/user/caseApplicationNCPDetails.htm";
	}

	private boolean checkRelNameExists(CasePartOther relationName) {

		StringBuffer dataParam = new StringBuffer(" ");

		dataParam.append(relationName.getFirstNm().trim());

		dataParam.append(relationName.getLastNm().trim());

		dataParam.append(relationName.getMiddleNm().trim());

		if (dataParam.toString().trim().equals(""))

			return false;
		else
			return true;

	}

}