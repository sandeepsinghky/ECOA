package nc.dhhs.nccss.acts.ecoa.web.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import nc.dhhs.nccss.acts.dao.CaseApplicationDao;
import nc.dhhs.nccss.acts.dao.CaseCourtOrderDao;
import nc.dhhs.nccss.acts.dao.CasePartAddrDao;
import nc.dhhs.nccss.acts.dao.CasePartBenefitDao;
import nc.dhhs.nccss.acts.dao.CasePartChldAffilDao;
import nc.dhhs.nccss.acts.dao.CasePartContactDao;
import nc.dhhs.nccss.acts.dao.CasePartEmpDao;
import nc.dhhs.nccss.acts.dao.CasePartIncomeDao;
import nc.dhhs.nccss.acts.dao.CasePartInsuranceDao;
import nc.dhhs.nccss.acts.dao.CasePartNCPExtDao;
import nc.dhhs.nccss.acts.dao.CasePartOrderDao;
import nc.dhhs.nccss.acts.dao.CasePartOtherDao;
import nc.dhhs.nccss.acts.dao.CasePartPaterntyDao;
import nc.dhhs.nccss.acts.dao.CasePartProfileDao;
import nc.dhhs.nccss.acts.dao.CasePartProfileExtDao;
import nc.dhhs.nccss.acts.dao.CaseParticipantDao;
import nc.dhhs.nccss.acts.dao.CaseVehicleDao;
import nc.dhhs.nccss.acts.dao.CodeLookUpDao;
import nc.dhhs.nccss.acts.dao.ThirdPartyDao;
import nc.dhhs.nccss.acts.ecoa.beans.CaseApplication;
import nc.dhhs.nccss.acts.ecoa.beans.CaseCourtOrder;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartAddress;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartBenefit;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartChldAffil;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartContact;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartEmp;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartIncome;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartInsurance;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartNCPExt;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartOrder;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartOther;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartPaternty;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartProfile;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartProfileExt;
import nc.dhhs.nccss.acts.ecoa.beans.CaseParticipant;
import nc.dhhs.nccss.acts.ecoa.beans.CodeLookUp;
import nc.dhhs.nccss.acts.ecoa.beans.ThirdParty;
import nc.dhhs.nccss.acts.ecoa.beans.Vehicle;
import nc.dhhs.nccss.acts.ecoa.web.controller.beans.CPparamData;
import nc.dhhs.nccss.acts.ecoa.web.controller.beans.ChldparamData;
import nc.dhhs.nccss.acts.ecoa.web.controller.beans.NCPparamData;
import nc.dhhs.nccss.acts.ecoa.web.controller.beans.ParamData;
import nc.dhhs.nccss.acts.ecoa.web.service.CaseParticipantService;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * @author pkonuru
 *
 */

public class CaseParticipantServiceImpl implements CaseParticipantService
{
	protected final Logger			logger	= Logger.getLogger(CaseParticipantServiceImpl.class);

	@Autowired
	private CaseParticipantDao		casePartDao;

	@Autowired
	private CaseApplicationDao		caseApplDao;

	@Autowired
	private CasePartAddrDao			casePartAddrDao;

	@Autowired
	private CasePartContactDao		casePartContactDao;

	@Autowired
	private CasePartBenefitDao		benefitDao;

	@Autowired
	private ThirdPartyDao			thirdPartyDao;

	@Autowired
	private CasePartIncomeDao		casePartIncomeDao;

	@Autowired
	private CodeLookUpDao			codeLookUpDao;

	@Autowired
	private CasePartChldAffilDao	casePartChldAffilDAO;

	@Autowired
	private CasePartEmpDao			casePartEmpDAO;

	@Autowired
	private CasePartInsuranceDao	casePartInsuranceDAO;

	@Autowired
	private CasePartProfileDao		casePartProfileDao;

	@Autowired
	private CasePartNCPExtDao		partNCPExtDao;

	@Autowired
	private CaseVehicleDao			caseVehicleDao;

	@Autowired
	private CasePartOtherDao		casePartOtherDao;

	@Autowired
	private CaseCourtOrderDao		caseOrderDao;

	@Autowired
	private CasePartOrderDao		caseOrderChildDao;

	@Autowired
	private CasePartPaterntyDao		casePartPaterntyDao;

	@Autowired
	private CasePartProfileExtDao	casePartProfileExtDao;

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecoa.web.service.CaseParticipantService#
	 * CreateCaseParticipant(nc.dhhs.nccss.acts.ecoa.beans.CaseParticipant,
	 * java.util.Map, java.lang.String, java.lang.String)
	 */
	@Transactional
	public String CreateCaseParticipant(CaseParticipant partInfo, ParamData pageData, String ncid, String partType) throws SQLException
	{

		String partId = "";
		logger.debug("\n********** IN CaseParticipantServiceImpl: CreateCaseParticipant(partInfo:" + partInfo + ",ncid:" + ncid + ")**********\n");

		if (partType != null && partType.equals(AppConstants.CP_PARTICIPANT_TYPE))
		{
			partId = CreateCaseParticipantCP(partInfo, pageData, ncid);
		}
		else if (partType != null && partType.equals(AppConstants.NCP_PARTICIPANT_TYPE))
		{
			partId = CreateCaseParticipantNCP(partInfo, pageData, ncid);
		}
		else if (partType != null && partType.equals(AppConstants.CHLD_PARTICIPANT_TYPE))
		{
			partId = CreateCaseParticipantChld(partInfo, pageData, ncid);
		}
		else
		{
			partId = casePartDao.createParticipant(partInfo, ncid);
		}

		return partId;
	}

	@Transactional
	public String CreateCaseParticipantCP(CaseParticipant partInfo, ParamData pageData, String ncId) throws SQLException
	{

		String partId = "";
		String returnCode = "";
		CPparamData pageInfo = (CPparamData) pageData;
		logger.debug("\n********** IN CaseParticipantServiceImpl: CreateCaseParticipantCP(partInfo:" + partInfo + ",ncId:" + ncId + ")**********\n");

		//partId = casePartDao.createParticipant(partInfo, ncId);
		partId = casePartDao.createParticipant(pageInfo.getParticipant(), ncId);
		if (partId == null || partId.trim().equals("")) 
		{ 
			throw new RuntimeException("CreateCaseParticipantCP: CP Applicant Id is empty."); 
		}
		if (partId != null && !partId.equals(""))
		{
			//process address information
			returnCode = processPartAddressData(partInfo.getApplicationId(), partId, pageInfo.getAddressList(), ncId, AppConstants.OPERATION_INSERT, AppConstants.CP_PARTICIPANT_TYPE);

			//process contact information
			returnCode = processPartContactData(partInfo.getApplicationId(), partId, pageInfo.getContactList(), ncId, AppConstants.OPERATION_INSERT, AppConstants.CP_PARTICIPANT_TYPE);

			//process Employment
			returnCode = processPartEmp(partInfo.getApplicationId(), partId, pageInfo.getEmpInfo(), ncId, AppConstants.OPERATION_INSERT);

			// process income
			returnCode = processPartIncome(partInfo.getApplicationId(), partId, pageInfo.getIncSrcList(), ncId, AppConstants.OPERATION_INSERT);
		}
		return partId;
	}

	@Transactional
	public String CreateCaseParticipantNCP(CaseParticipant partInfo, ParamData pageData, String ncId) throws SQLException
	{

		String partId = "";
		String returnCode = "";
		NCPparamData pageInfo = (NCPparamData) pageData;
		logger.debug("\n********** IN CaseParticipantServiceImpl: CreateCaseParticipantCP(partInfo:" + partInfo + ",ncId:" + ncId + ")**********\n");

		//partId = casePartDao.createParticipant(partInfo, ncId);
		partId = casePartDao.createParticipant(pageInfo.getParticipant(), ncId);
		if ((partId == null) || partId.trim().equals(""))
			throw new RuntimeException("CreateCaseParticipantNCP: partId is returned nul.");

		if (partId != null && !partId.equals(""))
		{

			// process participant extra information to get saved in
			// ACTW_NCP_Ext table.

			returnCode = processPartExtDetails(pageInfo.getNcpExtDetails(), partId, ncId, AppConstants.OPERATION_INSERT);
			// process NCP Relation names like father , mother and spouse.

			returnCode = processPartRelNamesInfo(pageInfo.getRelNamesMap(), partInfo.getApplicationId(), partId, ncId, AppConstants.OPERATION_INSERT);
			// process vehicle information.

			returnCode = processPartVehicleInfo(pageInfo.getVehicleList(), partInfo.getApplicationId(), partId, ncId, AppConstants.OPERATION_INSERT);

			// process court order details

			returnCode = processCourtOrderInfo(pageInfo.getSelectedOrderMap(), pageInfo.getChildrenMap(), partInfo.getApplicationId(), partId, ncId, AppConstants.OPERATION_INSERT);

			// process address information
			returnCode = processPartAddressData(partInfo.getApplicationId(), partId, pageInfo.getAddressList(), ncId, AppConstants.OPERATION_INSERT, AppConstants.NCP_PARTICIPANT_TYPE);

			// process contact information
			returnCode = processPartContactData(partInfo.getApplicationId(), partId, pageInfo.getContactList(), ncId, AppConstants.OPERATION_INSERT, AppConstants.NCP_PARTICIPANT_TYPE);

			//process Employment
			returnCode = processPartEmp(partInfo.getApplicationId(), partId, pageInfo.getEmpInfo(), ncId, AppConstants.OPERATION_INSERT);

			// process income
			returnCode = processPartIncome(partInfo.getApplicationId(), partId, pageInfo.getIncSrcList(), ncId, AppConstants.OPERATION_INSERT);
		}

		return partId;

	}

	@Transactional
	public String CreateCaseParticipantChld(CaseParticipant partInfo, ParamData pageData, String ncId) throws SQLException
	{

		String partId = "";
		String returnCode = "";
		ChldparamData pageInfo = (ChldparamData) pageData;
		logger.debug("\n********** IN CaseParticipantServiceImpl: CreateCaseParticipantChld(partInfo:" + partInfo.getApplicantId() + ",ncId:" + ncId + ")**********\n");

		//partId = casePartDao.createParticipant(partInfo, ncId);
		partId = casePartDao.createParticipant(pageInfo.getParticipant(), ncId);
		if (partId == null || partId.trim().equals("")) 
		{ 
			logger.error("CreateCaseParticipantChld: Child Applicant Id is empty, Rollback this transaction!");
			throw new RuntimeException("CreateCaseParticipantChld: Child Applicant Id is empty."); 
		}
		if (partId != null && !partId.equals(""))
		{
			// child info
			returnCode = processPartProfile(partInfo.getApplicationId(), partId, pageInfo.getPartProfile(), ncId, AppConstants.OPERATION_INSERT);

			// process benefits
			returnCode = processPartBenefit(partInfo.getApplicationId(), partId, pageInfo.getBenifitList(), AppConstants.CHLD_PARTICIPANT_TYPE, ncId, AppConstants.OPERATION_INSERT);

			//process child ncp linking
			returnCode = processPartChldAffil(partInfo.getApplicationId(), partId, pageInfo.getChldNcpList(), pageInfo.getChldNcpToAddList(), ncId, AppConstants.OPERATION_INSERT);

			//process paternity information
			returnCode = processPartPaternty(partInfo.getApplicationId(), partId, pageInfo.getPartPaternity(), ncId, AppConstants.OPERATION_INSERT);

			//process insurance information
			returnCode = processPartInsurance(partInfo.getApplicationId(), partId, pageInfo.getInsuranceList(), ncId, AppConstants.OPERATION_INSERT);

			// process partother
			returnCode = processPartOther(partInfo.getApplicationId(), partId, pageInfo.getPartOtherList(), ncId, AppConstants.OPERATION_INSERT);

			// process part profile ext
			returnCode = processPartProfileExt(partInfo.getApplicationId(), partId, pageInfo.getPartProfileExtList(), ncId, AppConstants.OPERATION_INSERT);

			//process address information
			returnCode = processPartAddressData(partInfo.getApplicationId(), partId, pageInfo.getAddressList(), ncId, AppConstants.OPERATION_INSERT, AppConstants.CHLD_PARTICIPANT_TYPE);

			//process contact information
			returnCode = processPartContactData(partInfo.getApplicationId(), partId, pageInfo.getContactList(), ncId, AppConstants.OPERATION_INSERT, AppConstants.CHLD_PARTICIPANT_TYPE);

		}
		return partId;
	}

	@Transactional(readOnly = true)
	public List<CaseParticipant> getParticipantByPartType(String applId, String partType) throws SQLException
	{
		logger.debug("\n********** IN CaseParticipantServiceImpl: getParticipantByPartType (applId: " + applId + ",partType: " + partType + ")**********\n");

		return casePartDao.getParticipantByPartType(applId, partType);
	}

	@Transactional(readOnly = true)
	public List<CaseParticipant> getParticipantByPartId(String applId, String partId) throws SQLException
	{
		logger.debug("\n********** IN CaseParticipantServiceImpl: getParticipantByPartId (applId: " + applId + ",partId: " + partId + ")**********\n");

		return casePartDao.getParticipantByPartId(applId, partId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CaseCourtOrder> getCaseCourtOrderByPartId(String applId, String applicantId) throws SQLException
	{
		logger.debug("\n********** IN CaseParticipantServiceImpl: getCaseCourtOrderByPartId(applId: " + applId + ",applicantId: " + applicantId + ")**********\n");

		return caseOrderDao.getCaseCourtOrderByPartId(applId, applicantId);
	}

	@Transactional(readOnly = true)
	public List<CasePartOrder> getCasePartOrderByOrderNum(String orderNum) throws SQLException

	{
		logger.debug("\n********** IN CaseParticipantServiceImpl:getCasePartOrderByOrderNum(orderNum: " + orderNum + " )**********\n");

		return caseOrderChildDao.getCasePartOrderByOrderNum(orderNum);

	}

	@Transactional
	public String deleteParticipant(String appId, String partId, String ncId) throws SQLException
	{
		logger.debug("\n********** IN CaseParticipantServiceImpl:deleteParticipant (applId: " + appId + ",partId: " + partId + ",ncId" + ncId + ")**********\n");

		CaseParticipant partInfo = getParticipantByPartId(appId, partId).get(0);

		partInfo.setApplicantSufix("");

		return casePartDao.deleteParticipant(partInfo, ncId);
	}

	@Transactional
	public String updateParticipantCP(CaseParticipant partInfo, ParamData pageData, String ncId) throws SQLException
	{
		String returnCode = "";
		CPparamData pageInfo = (CPparamData) pageData;

		logger.debug("\n********** IN CaseParticipantServiceImpl:updateParticipantCP (partInfo, ncid:" + ncId + ")**********\n");
		//returnCode = casePartDao.updateParticipant(partInfo, ncId);
		returnCode = casePartDao.updateParticipant(pageInfo.getParticipant(), ncId);
		if (returnCode.substring(0, 4).equals(AppConstants.OPERATION_SUCCESS))
		{

			//process address information
			returnCode = processPartAddressData(partInfo.getApplicationId(), partInfo.getApplicantId(), pageInfo.getAddressList(), ncId, AppConstants.OPERATION_UPDATE, AppConstants.CP_PARTICIPANT_TYPE);

			//process contact information
			returnCode = processPartContactData(partInfo.getApplicationId(), partInfo.getApplicantId(), pageInfo.getContactList(), ncId, AppConstants.OPERATION_UPDATE, AppConstants.CP_PARTICIPANT_TYPE);

			//process Employment
			returnCode = processPartEmp(partInfo.getApplicationId(), partInfo.getApplicantId(), pageInfo.getEmpInfo(), ncId, AppConstants.OPERATION_UPDATE);

			// process income
			returnCode = processPartIncome(partInfo.getApplicationId(), partInfo.getApplicantId(), pageInfo.getIncSrcList(), ncId, AppConstants.OPERATION_UPDATE);

		}
		return returnCode;
	}

	@Transactional
	public String updateParticipantNCP(CaseParticipant partInfo, ParamData pageData, String ncId) throws SQLException
	{
		String returnCode = "";
		NCPparamData pageInfo = (NCPparamData) pageData;

		logger.debug("\n********** IN CaseParticipantServiceImpl:updateParticipantNCP (partInfo, ncid:" + ncId + ")**********\n");
		returnCode = casePartDao.updateParticipant(partInfo, ncId);
		returnCode = casePartDao.updateParticipant(pageInfo.getParticipant(), ncId);

		if (returnCode.substring(0, 4).equals(AppConstants.OPERATION_SUCCESS))
		{
			// process NCP Additional details.
			returnCode = processPartExtDetails(pageInfo.getNcpExtDetails(), partInfo.getApplicantId(), ncId, AppConstants.OPERATION_UPDATE);

			// process NCP Relation names
			processPartRelNamesInfo(pageInfo.getRelNamesMap(), partInfo.getApplicationId(), partInfo.getApplicantId(), ncId, AppConstants.OPERATION_UPDATE);

			// process NCP Vehicle details.
			returnCode = processPartVehicleInfo(pageInfo.getVehicleList(), partInfo.getApplicationId(), partInfo.getApplicantId(), ncId, AppConstants.OPERATION_UPDATE);

			// process court order details

			returnCode = processCourtOrderInfo(pageInfo.getSelectedOrderMap(), pageInfo.getChildrenMap(), partInfo.getApplicationId(), partInfo.getApplicantId(), ncId, AppConstants.OPERATION_UPDATE);

			// process address information
			returnCode = processPartAddressData(partInfo.getApplicationId(), partInfo.getApplicantId(), pageInfo.getAddressList(), ncId, AppConstants.OPERATION_UPDATE, AppConstants.NCP_PARTICIPANT_TYPE);

			// process contact information
			returnCode = processPartContactData(partInfo.getApplicationId(), partInfo.getApplicantId(), pageInfo.getContactList(), ncId, AppConstants.OPERATION_UPDATE, AppConstants.NCP_PARTICIPANT_TYPE);

			//process Employment
			returnCode = processPartEmp(partInfo.getApplicationId(), partInfo.getApplicantId(), pageInfo.getEmpInfo(), ncId, AppConstants.OPERATION_UPDATE);

			// process income
			returnCode = processPartIncome(partInfo.getApplicationId(), partInfo.getApplicantId(), pageInfo.getIncSrcList(), ncId, AppConstants.OPERATION_UPDATE);

		}
		return returnCode;
	}

	@Transactional
	public String updateParticipantChld(CaseParticipant partInfo, ParamData pageData, String ncId) throws SQLException
	{
		String returnCode = "";
		ChldparamData pageInfo = (ChldparamData) pageData;

		logger.debug("\n********** IN CaseParticipantServiceImpl:updateParticipantCP (partInfo, ncid:" + ncId + ")**********\n");
		//returnCode = casePartDao.updateParticipant(partInfo, ncId);
		returnCode = casePartDao.updateParticipant(pageInfo.getParticipant(), ncId);
		if (returnCode.substring(0, 4).equals(AppConstants.OPERATION_SUCCESS))
		{
			// child info
			returnCode = processPartProfile(partInfo.getApplicationId(), partInfo.getApplicantId(), pageInfo.getPartProfile(), ncId, AppConstants.OPERATION_UPDATE);

			returnCode = processPartBenefit(partInfo.getApplicationId(), partInfo.getApplicantId(), pageInfo.getBenifitList(), AppConstants.CHLD_PARTICIPANT_TYPE, ncId, AppConstants.OPERATION_UPDATE);

			//process child ncp linking
			returnCode = processPartChldAffil(partInfo.getApplicationId(), partInfo.getApplicantId(), pageInfo.getChldNcpList(), pageInfo.getChldNcpToAddList(), ncId, AppConstants.OPERATION_UPDATE);

			//process paternity information
			returnCode = processPartPaternty(partInfo.getApplicationId(), partInfo.getApplicantId(), pageInfo.getPartPaternity(), ncId, AppConstants.OPERATION_UPDATE);

			//process insurance information
			returnCode = processPartInsurance(partInfo.getApplicationId(), partInfo.getApplicantId(), pageInfo.getInsuranceList(), ncId, AppConstants.OPERATION_UPDATE);

			//process part other information
			returnCode = processPartOther(partInfo.getApplicationId(), partInfo.getApplicantId(), pageInfo.getPartOtherList(), ncId, AppConstants.OPERATION_UPDATE);

			// process part profile ext
			returnCode = processPartProfileExt(partInfo.getApplicationId(), partInfo.getApplicantId(), pageInfo.getPartProfileExtList(), ncId, AppConstants.OPERATION_UPDATE);

			//process address information
			returnCode = processPartAddressData(partInfo.getApplicationId(), partInfo.getApplicantId(), pageInfo.getAddressList(), ncId, AppConstants.OPERATION_UPDATE, AppConstants.CHLD_PARTICIPANT_TYPE);

			//process contact information
			returnCode = processPartContactData(partInfo.getApplicationId(), partInfo.getApplicantId(), pageInfo.getContactList(), ncId, AppConstants.OPERATION_UPDATE, AppConstants.CHLD_PARTICIPANT_TYPE);

		}
		return returnCode;
	}

	@Transactional
	public String updateParticipant(CaseParticipant partInfo, ParamData pageData, String ncId, String partType) throws SQLException
	{
		String returnCode = "";

		logger.debug("\n********** IN CaseParticipantServiceImpl:updateParticipant (partInfo, ncid:" + ncId + ")**********\n");
		if (partType != null && partType.equals(AppConstants.CP_PARTICIPANT_TYPE))
		{
			returnCode = updateParticipantCP(partInfo, pageData, ncId);
		}
		else if (partType != null && partType.equals(AppConstants.NCP_PARTICIPANT_TYPE))
		{
			returnCode = updateParticipantNCP(partInfo, pageData, ncId);
		}
		else if (partType != null && partType.equals(AppConstants.CHLD_PARTICIPANT_TYPE))
		{
			returnCode = updateParticipantChld(partInfo, pageData, ncId);
		}
		else
		{
			returnCode = casePartDao.updateParticipant(partInfo, ncId);
		}
		return returnCode;
	}

	@Transactional
	public String CreateApplicant(CaseApplication applBean, CaseParticipant partInfo, String ncId, Map<String, CasePartBenefit> benefitsMap, ThirdParty thirdPartyBean) throws SQLException
	{
		logger.info("In: CreateApplicant (applBean, partInfo," + ncId + ")");
		String rValue = null;

		String thirdPtyId = "";

		String key = CreateCaseParticipant(partInfo, null, ncId, null);

		if (key == null || key.trim().equals("")) 
		{ 
			logger.error("CreateApplicant: Applicant Id for application is not returned from SP, Rollback this transaction!");
			throw new RuntimeException("CreateApplicant: Applicant Id for application is returned empty"); 
		}
		applBean.setApplicantId(key);
		applBean.setApplCreateDt(new Date());

		rValue = caseApplDao.updateCaseApplication(applBean);
		if (rValue != null && rValue.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))

			// process benefits
			rValue = processPartBenefit(applBean.getApplicationId(), applBean.getApplicantId(), benefitsMap, "", ncId, AppConstants.OPERATION_INSERT);

		if (thirdPartyBean != null)
		{
			if (rValue != null && rValue.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
			{
				thirdPtyId = thirdPartyDao.createThirdParty(thirdPartyBean, ncId);
				if (thirdPtyId == null || thirdPtyId.equals("")) 
				{ 
					logger.error("CreateApplicant: Insert for thirdParty: Save/Update failed, Rollback this transaction!");
					throw new RuntimeException("CreateApplicant: Insert for thirdParty: Save/Update failed."); 
				}
			}

		}

		if (rValue == null || !(rValue.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))) {
			logger.error("CreateApplicant: Insert for Benefits: Save/Update failed.");
			throw new RuntimeException("CreateApplicant: Insert for Benefits: Save/Update failed.");
		}
		return rValue;
	}

	@Transactional
	public String updateApplicant(CaseApplication applBean, CaseParticipant partInfo, String ncId, Map<String, CasePartBenefit> benefitsMap, ThirdParty thirdPartyBean) throws SQLException
	{

		logger.info("In: updateApplicant (applBean, partInfo," + ncId + ")");

		partInfo.setApplicationId(applBean.getApplicationId());
		partInfo.setApplicantId(applBean.getApplicantId());

		String rValue = casePartDao.updateParticipant(partInfo, ncId);

		if ((rValue != null && rValue.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS)))
		{
			rValue = caseApplDao.updateCaseApplication(applBean);
		}

		if (rValue != null && rValue.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
		{
			rValue = processPartBenefit(applBean.getApplicationId(), partInfo.getApplicantId(), benefitsMap, "", ncId, AppConstants.OPERATION_UPDATE);
		}
		if (rValue != null && rValue.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
		{

			rValue = updateThirdParty(thirdPartyBean, applBean.getApplicationId(), AppConstants.THIRDPARTY_TYPE_AGENCY, ncId);

		}

		return rValue;

	}

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecoa.web.service.CaseParticipantService#
	 * getParticipantAddr(java.lang.String, java.lang.String)
	 */
	public List<CasePartAddress> getParticipantAddr(String applId, String partId) throws SQLException
	{
		return casePartAddrDao.getCasePartAddr(applId, partId);
	}

	@Transactional(readOnly = true)
	public List<CasePartBenefit> getCasePartBenefitsByFieldId(String applId, String applicantId, String partType) throws SQLException
	{
		logger.debug("\n********** IN CaseParticipantServiceImpl: getCasePartBenefitsByFieldId(applId: " + applId + ",applicantId: " + applicantId + ")**********\n");

		return benefitDao.getCasePartBenefitsByFieldId(applId, applicantId, partType);

	}

	@Override
	@Transactional(readOnly = true)
	public List<ThirdParty> getThirdPartyByApplIdandType(String applicationId, String thirdPartyType) throws SQLException
	{
		logger.debug("\n**********IN CaseParticipantServiceImpl: getThirdPartyByApplIdandType(applicationId :" + applicationId + ")**********\n");

		return thirdPartyDao.getThirdPartyByApplIdandType(applicationId, thirdPartyType);

	}

	@Transactional(readOnly = true)
	public List<CasePartChldAffil> getNCPLinkedChildren(String applId, String partId) throws SQLException
	{
		logger.debug("\n********** IN CaseParticipantServiceImpl:getNCPLinkedChildren (applId: " + applId + ",partId: " + partId + ")**********\n");

		return casePartChldAffilDAO.getNCPLinkedChildren(applId, partId);

	}

	/**
	 * @param applicationId
	 * @param applicantId
	 * @param paramList
	 * @param ncId
	 * @return
	 * @throws SQLException
	 */
	@Transactional
	private String processPartAddress(CasePartAddress addrBean, String ncId, String opType) throws SQLException
	{
		String returnCode = AppConstants.OPERATION_SUCCESS;
		boolean addr_entered = false;

		boolean addr_update = false;

		List<CasePartAddress> addrList = new ArrayList<CasePartAddress>();

		if (opType.equals(AppConstants.OPERATION_UPDATE))
		{
			addrList = getParticipantAddr(addrBean.getApplicationId(), addrBean.getApplicantId());

			if (addrList != null && addrList.size() > 0)
			{
				for (CasePartAddress addr : addrList)
				{
					if (addr.getAddrType().equals(addrBean.getAddrType()))
					{
						addr_update = true;
					}
				}
			}
		}

		if (!addrBean.getAddrLn1().equals("") || !addrBean.getAddrCty().equals("") || !addrBean.getAddrZip5().equals("") || !addrBean.getAddrSt().equals(""))
		{
			addr_entered = true;
		}

		if (addr_update)
		{
			if (addr_entered)
			{
				returnCode = casePartAddrDao.updatePartAddr(addrBean, ncId);
			}
			else
			{
				returnCode = casePartAddrDao.deletePartAddr(addrBean, ncId);
			}
		}
		else if (addr_entered)
		{
			returnCode = casePartAddrDao.createPartAddr(addrBean, ncId);
		}

		//check for returncode other than success
		if (addr_entered || addr_update)
		{
			if (returnCode != null && !returnCode.equals("") && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
			{	
				throw new RuntimeException("processUpdatePartAddress: Save/Update failed. Return code: "+returnCode);
			}
		}

		return returnCode;
	}

	@Transactional
	private String deletePartAddress(CasePartAddress addrBean, String ncId) throws SQLException
	{
		String returnCode = AppConstants.OPERATION_SUCCESS;
		boolean addr_exist = false;
		List<CasePartAddress> addrList = getParticipantAddr(addrBean.getApplicationId(), addrBean.getApplicantId());
		if (addrList != null && addrList.size() > 0)
		{
			for (CasePartAddress addr : addrList)
			{
				if (addr.getAddrType().equals(addrBean.getAddrType()))
				{
					addr_exist = true;
				}
			}
		}
		if (addr_exist)
		{
			returnCode = casePartAddrDao.deletePartAddr(addrBean, ncId);
			if (returnCode != null && !returnCode.equals("") && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
				throw new RuntimeException("processUpdatePartAddress: delete address failed, address type: "+addrBean.getAddrType()+", Return code: "+returnCode);
		}
		return returnCode;
	}

	@Transactional
	private String processPartAddressData(String applicationId, String applicantId, List<CasePartAddress> addrList, String ncId, String opType, String role) throws SQLException
	{
		String returnCode = AppConstants.OPERATION_SUCCESS;

		List<CodeLookUp> addrCodeList = codeLookUpDao.getCodeLookup("ADDRTYPE");

		Map<String, String> addrCodeMap = new HashMap<String, String>();

		for (CodeLookUp addrCode : addrCodeList)
		{
			addrCodeMap.put(addrCode.getCodeId(), addrCode.getCodeDesc());
		}

		if (addrList != null && addrList.size() > 0)
		{
			for (CasePartAddress addr : addrList)
			{
				if (addr != null)
				{
					addr.setApplicationId(applicationId);
					addr.setApplicantId(applicantId);
					returnCode = processPartAddress(addr, ncId, opType);
					if (returnCode != null && !returnCode.equals("") && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
						throw new RuntimeException("processPartAddressData: Save/Update failed from processPartAddress method. Return code: "+returnCode);
					if (addrCodeMap.containsKey(addr.getAddrType()))
					{
						addrCodeMap.remove(addr.getAddrType());
					}
				}
			}
		}
		if (opType.equals(AppConstants.OPERATION_UPDATE))
		{
			for (String addrType : addrCodeMap.keySet())
			{
				if((role.equals(AppConstants.CHLD_PARTICIPANT_TYPE) && addrType.equals(AppConstants.ADDRESS_OTHER)) || (role.equals(AppConstants.CP_PARTICIPANT_TYPE) && !addrType.equals(AppConstants.ADDRESS_OTHER)) || role.equals(AppConstants.NCP_PARTICIPANT_TYPE))
				{	
					CasePartAddress delAddrBean = new CasePartAddress();
					delAddrBean.setApplicationId(applicationId);
					delAddrBean.setApplicantId(applicantId);
					delAddrBean.setAddrType(addrType);
					returnCode = deletePartAddress(delAddrBean, ncId);
					if (returnCode != null && !returnCode.equals("") && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
						throw new RuntimeException("processPartAddressData: delete failed.\nReturn Code:"+returnCode);
				}
			}
		}

		return returnCode;
	}

	/**
	 * @param contactBean
	 * @param paramList
	 * @param ncId
	 * @return
	 * @throws SQLException
	 */
	@Transactional
	private String processPartContactData(String applicationId, String applicantId, List<CasePartContact> contactList, String ncId, String opType, String role) throws SQLException
	{
		String returnCode = AppConstants.OPERATION_SUCCESS;

		List<CodeLookUp> contactCodeList = codeLookUpDao.getCodeLookup("CONTACT");

		Map<String, String> contactCodeMap = new HashMap<String, String>();

		for (CodeLookUp contactCode : contactCodeList)
		{
			contactCodeMap.put(contactCode.getCodeId(), contactCode.getCodeDesc());
		}

		if (contactList != null && contactList.size() > 0)
		{
			for (CasePartContact contactBean : contactList)
			{
				if (contactBean != null)
				{
					contactBean.setApplicationId(applicationId);
					contactBean.setApplicantId(applicantId);
					returnCode = processPartContact(contactBean, ncId, opType);
					if (returnCode != null && !returnCode.equals("") && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
						throw new RuntimeException("processPartContactData: processPartContact failed. Return Code:"+returnCode);
					if (contactCodeMap.containsKey(contactBean.getContactType()))
					{
						contactCodeMap.remove(contactBean.getContactType());
					}
				}
			}

		}
		//delete contacts if list is null or for removed data
		if (opType.equals(AppConstants.OPERATION_UPDATE))
		{
			for (String contactType : contactCodeMap.keySet())
			{
				if((role.equals(AppConstants.CHLD_PARTICIPANT_TYPE) && contactType.equals(AppConstants.CONTACT_TYPE_OTHER)) || (role.equals(AppConstants.CP_PARTICIPANT_TYPE) && !contactType.equals(AppConstants.CONTACT_TYPE_OTHER)) || role.equals(AppConstants.NCP_PARTICIPANT_TYPE))
				{	
					CasePartContact delContBean = new CasePartContact();
					delContBean.setApplicationId(applicationId);
					delContBean.setApplicantId(applicantId);
					delContBean.setContactType(contactType);
					returnCode = deletePartContact(delContBean, ncId);
					if (returnCode != null && !returnCode.equals("") && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
						throw new RuntimeException("processPartContactData: delete contact failed. Contact type: "+delContBean.getContactType()+"\nReturn Code: "+returnCode);
				}
			}
		}

		return returnCode;
	}

	/**
	 * @param contactBean
	 * @param ncId
	 * @return
	 * @throws SQLException
	 */
	@Transactional
	private String processPartContact(CasePartContact contactBean, String ncId, String opType) throws SQLException
	{
		String returnCode = AppConstants.OPERATION_SUCCESS;
		boolean contact_entered = false;
		boolean contact_exist = false;
		boolean contact_updated = false;

		CasePartContact contactBeanDb = null;

		if (opType.equals(AppConstants.OPERATION_UPDATE))
			contactBeanDb = getPartContactType(contactBean.getApplicationId(), contactBean.getApplicantId(), contactBean.getContactType());

		if (contactBeanDb != null)
		{
			if (contactBeanDb.getContactType().equals(contactBean.getContactType()))
			{
				contact_exist = true;
				if (!contactBean.getContactInfo().trim().equals("") && !contactBeanDb.getContactInfo().trim().equals(contactBean.getContactInfo().trim()))
				{
					contact_updated = true;
				}
			}
		}

		if (!contactBean.getContactInfo().trim().equals(""))
		{
			contact_entered = true;
		}

		if (contact_exist)
		{
			if (contact_updated)
			{
				returnCode = casePartContactDao.updatePartContact(contactBean, ncId);
			}
			else if (!contact_entered)
			{
				returnCode = casePartContactDao.deletePartContact(contactBean, ncId);
			}
		}
		else if (contact_entered)
		{
			returnCode = casePartContactDao.createPartContact(contactBean, ncId);
		}

		if (returnCode != null && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
			throw new RuntimeException("processPartContact: create failed."+returnCode);

		return returnCode;
	}

	public List<CasePartContact> getPartContact(String applId, String applicantId) throws SQLException
	{
		return casePartContactDao.getCasePartContact(applId, applicantId);
	}

	public CasePartContact getPartContactType(String applId, String applicantId, String contactType) throws SQLException
	{
		return casePartContactDao.getCasePartContactType(applId, applicantId, contactType);
	}

	/**
	 * @param contactBean
	 * @param ncId
	 * @return
	 * @throws SQLException
	 */
	public String deletePartContact(CasePartContact contactBean, String ncId) throws SQLException
	{
		String returnCode = AppConstants.OPERATION_SUCCESS;
		CasePartContact contactBeanDb = getPartContactType(contactBean.getApplicationId(), contactBean.getApplicantId(), contactBean.getContactType());
		if (contactBeanDb != null)
		{
			returnCode = casePartContactDao.deletePartContact(contactBean, ncId);
			if (returnCode != null && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
				throw new RuntimeException("deletePartContact: delete contact failed. Return code: "+returnCode);
		}

		return returnCode;
	}

	@SuppressWarnings("unused")
	@Transactional
	private String updateThirdParty(ThirdParty thirdPartyBean, String applicationId, String ThirdPartyType, String ncId) throws SQLException

	{
		logger.debug("\n**********IN CaseParticipantServiceImpl: updateThirdParty(applicationId :" + applicationId + "ncId:" + ncId + ")**********\n");

		String rValue = AppConstants.OPERATION_SUCCESS;

		List<ThirdParty> thirdParties = getThirdPartyByApplIdandType(applicationId, ThirdPartyType);

		if (thirdPartyBean != null && thirdParties.size() == 0)
		{

			rValue = thirdPartyDao.createThirdParty(thirdPartyBean, ncId);

			if (rValue == null || rValue.equals("")) {

			throw new RuntimeException("updateThirdParty: Insert for ThirdParty: Save failed"); }
		}

		if (thirdPartyBean != null && thirdParties.size() > 0)
		{

			rValue = thirdPartyDao.updateThirdParty(thirdPartyBean, ncId);

			if (rValue == null || !(rValue.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))) {

			throw new RuntimeException("updateThirdParty: Update for ThirdParty failed");

			}
		}

		if (thirdPartyBean == null && thirdParties.size() > 0)
		{
			thirdPartyBean = new ThirdParty();
			thirdPartyBean.setApplicationId(applicationId);
			thirdPartyBean.setThirdPartyType(ThirdPartyType);

			rValue = thirdPartyDao.deleteThirdParty(thirdPartyBean, ncId);
			if (rValue == null || !(rValue.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))) {

			throw new RuntimeException("updateThirdParty: Delete for ThirdParty failed.");

			}

		}

		return rValue;
	}

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecoa.web.service.CaseParticipantService#
	 * getParticipantIncome(java.lang.String, java.lang.String)
	 */
	public List<CasePartIncome> getParticipantIncome(String applId, String applicantId) throws SQLException
	{
		return casePartIncomeDao.getCasePartIncome(applId, applicantId);
	}

	/**
	 * @param applicationId
	 * @param applicantId
	 * @param paramList
	 * @param ncId
	 * @return
	 * @throws SQLException
	 */
	@Transactional
	private String processPartIncome(String applicationId, String applicantId, Map<String, String> incList, String ncId, String opType) throws SQLException
	{
		String returnCode = AppConstants.OPERATION_SUCCESS;
		boolean dbUpdate = false;

		List<CasePartIncome> incomeList = new ArrayList<CasePartIncome>();

		CasePartIncome incomeBeanToAdd = new CasePartIncome();
		incomeBeanToAdd.setApplicationId(applicationId);
		incomeBeanToAdd.setApplicantId(applicantId);

		if (incList != null && incList.size() > 0)
		{
			if (opType.equals(AppConstants.OPERATION_UPDATE))
			{
				incomeList = getParticipantIncome(applicationId, applicantId);
				if (incomeList.size() > 0)
				{
					dbUpdate = true;
				}
			}

			//insert new records in case of create or no existing db records
			if (opType.equals(AppConstants.OPERATION_INSERT) || !dbUpdate)
			{
				for (String key : incList.keySet())
				{
					String incType = key;
					incomeBeanToAdd.setIncomeSrc(incType);
					incomeBeanToAdd.setIncomeAmt(Double.parseDouble(incList.get(key)));
					returnCode = casePartIncomeDao.createPartIncome(incomeBeanToAdd, ncId);
					if (returnCode != null && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
						throw new RuntimeException("processPartIncome: Save/Update failed.\nReturn Code:"+returnCode);
				}
			}

			if (opType.equals(AppConstants.OPERATION_UPDATE) && dbUpdate)
			{
				for (CasePartIncome income : incomeList)
				{
					if (!incList.containsKey(income.getIncomeSrc().trim()))
					{
						// delete key for existing income source entry 
						returnCode = casePartIncomeDao.deletePartIncome(income, ncId);
					}
					else
					{
						// check for record update
						double amt = Double.parseDouble(incList.get(income.getIncomeSrc().trim()));
						incList.remove(income.getIncomeSrc());
						if (amt != income.getIncomeAmt())
						{
							income.setIncomeAmt(amt);
							returnCode = casePartIncomeDao.updatePartIncome(income, ncId);
						}
					}
					if (returnCode != null && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
						throw new RuntimeException("processPartIncome: Update failed. Return Code: "+returnCode);
				}

				// create record for newly added income source entry
				for (String src : incList.keySet())
				{
					incomeBeanToAdd.setIncomeSrc(src);
					incomeBeanToAdd.setIncomeAmt(Double.parseDouble(incList.get(src)));
					returnCode = casePartIncomeDao.createPartIncome(incomeBeanToAdd, ncId);
					if (returnCode != null && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
						throw new RuntimeException("processPartIncome: Create failed. Return Code: "+returnCode);
				}
			}
		}
		else
		{
			//delete any existing records from database
			if (opType.equals(AppConstants.OPERATION_UPDATE))
			{
				incomeList = getParticipantIncome(applicationId, applicantId);
				if (incomeList.size() > 0)
				{
					incomeBeanToAdd.setIncomeSrc("0");
					returnCode = casePartIncomeDao.deletePartIncome(incomeBeanToAdd, ncId);
					if (returnCode != null && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
						throw new RuntimeException("processPartIncome: delete failed. Return Code: "+returnCode);
				}
			}
		}

		return returnCode;
	}

	/**
	 * @param applicationId
	 * @param applicantId
	 * @param paramList
	 * @param ncId
	 * @return
	 * @throws SQLException
	 */
	@Transactional
	private String processPartBenefit(String applicationId, String applicantId, Map<String, CasePartBenefit> benefitList, String partType, String ncId, String opType) throws SQLException
	{
		String returnCode = AppConstants.OPERATION_SUCCESS;
		boolean dbUpdate = false;

		List<CasePartBenefit> bnftList = new ArrayList<CasePartBenefit>();

		CasePartBenefit bnftInfo = new CasePartBenefit();
		bnftInfo.setApplicationId(applicationId);
		bnftInfo.setApplicantId(applicantId);
		bnftInfo.setPartType(partType);

		if (benefitList != null && benefitList.size() > 0)
		{
			if (opType.equals(AppConstants.OPERATION_UPDATE))
			{
				bnftList = getParticipantBenefit(applicationId, applicantId, partType);
				if (bnftList.size() > 0)
				{
					dbUpdate = true;
				}
			}

			//insert new records in case of create or no existing db records
			if (opType.equals(AppConstants.OPERATION_INSERT) || !dbUpdate)
			{
				for (String key : benefitList.keySet())
				{
					bnftInfo.setVeteranNmF("");
					bnftInfo.setVeteranNmL("");
					bnftInfo.setBenefitCode(key);
					if (key.equals(AppConstants.VETERN_BENEFIt_TYPE))
					{
						String vnameF = benefitList.get(key).getVeteranNmF();
						String vnameL = benefitList.get(key).getVeteranNmL();

						if (vnameF != null)
						{
							bnftInfo.setVeteranNmF(vnameF);
						}
						if (vnameL != null)
						{
							bnftInfo.setVeteranNmL(vnameL);
						}
					}
					returnCode = benefitDao.createCasePartBenefit(bnftInfo, ncId);
					if (returnCode != null && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
						throw new RuntimeException("processPartBenefit: Save/Update failed. Return code: "+returnCode);
				}
				;
			}

			if (opType.equals(AppConstants.OPERATION_UPDATE) && dbUpdate)
			{
				for (CasePartBenefit bnft : bnftList)
				{
					if (!benefitList.containsKey(bnft.getBenefitCode().trim()))
					{

						returnCode = benefitDao.deleteCasePartBenefit(bnft, ncId);
					}
					else
					{
						// check for record update
						//update
						if (bnft.getBenefitCode().trim().equals(AppConstants.VETERN_BENEFIt_TYPE))
						{
							if (!bnft.getVeteranNmF().equals(benefitList.get(AppConstants.VETERN_BENEFIt_TYPE).getVeteranNmF()) || !bnft.getVeteranNmL().equals(benefitList.get(AppConstants.VETERN_BENEFIt_TYPE).getVeteranNmL()))
							{
								bnft.setVeteranNmF(benefitList.get(AppConstants.VETERN_BENEFIt_TYPE).getVeteranNmF());
								bnft.setVeteranNmL(benefitList.get(AppConstants.VETERN_BENEFIt_TYPE).getVeteranNmL());
								returnCode = benefitDao.updateCasePartBenefit(bnft, ncId);
								if (returnCode != null && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
									throw new RuntimeException("processPartBenefit: update failed. Return Code: "+returnCode);
							}
						}
						benefitList.remove(bnft.getBenefitCode().trim());
					}
					if (returnCode != null && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
						throw new RuntimeException("processPartBenefit: Save/Update failed. Return code: "+returnCode);
				}
				// create record for newly added income source entry
				for (String bnftcode : benefitList.keySet())
				{
					bnftInfo.setVeteranNmF("");
					bnftInfo.setVeteranNmL("");
					bnftInfo.setBenefitCode(bnftcode);
					if (bnftcode.equals(AppConstants.VETERN_BENEFIt_TYPE))
					{
						String vnameF = benefitList.get(bnftcode).getVeteranNmF();
						String vnameL = benefitList.get(bnftcode).getVeteranNmL();

						if (vnameF != null)
						{
							bnftInfo.setVeteranNmF(vnameF);
						}
						if (vnameL != null)
						{
							bnftInfo.setVeteranNmL(vnameL);
						}
					}
					returnCode = benefitDao.createCasePartBenefit(bnftInfo, ncId);
					if (returnCode != null && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
						throw new RuntimeException("processPartBenefit: Create failed. Return Code: "+returnCode);
				}
			}
		}
		else
		{
			//delete any existing records from database
			if (opType.equals(AppConstants.OPERATION_UPDATE))
			{
				bnftList = getParticipantBenefit(applicationId, applicantId, partType);
				if (bnftList.size() > 0)
				{
					bnftInfo.setBenefitCode("0");
					bnftInfo.setPartType(partType);
					returnCode = benefitDao.deleteCasePartBenefit(bnftInfo, ncId);
					if (returnCode != null && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
						throw new RuntimeException("processPartBenefit: delete failed. Return code: "+returnCode);
				}
			}
		}

		return returnCode;
	}

	/**
	 * @param applId
	 * @param applicantId
	 * @param partType
	 * @return
	 * @throws SQLException
	 */
	public List<CasePartBenefit> getParticipantBenefit(String applId, String applicantId, String partType) throws SQLException
	{
		return benefitDao.getCasePartBenefitsByFieldId(applId, applicantId, partType);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CasePartNCPExt> getCasePartNCPExtByPartId(String applId, String applicantId) throws SQLException
	{
		logger.debug("\n**********IN CaseParticipantServiceImpl: getCasePartNCPExtByPartId(applicationId :" + applId + ")**********\n");

		return partNCPExtDao.getCasePartNCPExtByPartId(applId, applicantId);

	}

	/**
	 * @param applId
	 * @param applicantId
	 * @return
	 * @throws SQLException
	 */

	@Override
	@Transactional(readOnly = true)
	public List<CasePartOther> getCasePartOtherByPartId(String applId, String applicantId) throws SQLException
	{
		logger.debug("\n********** IN CaseParticipantServiceImpl: getCasePartOtherByPartId(applId: " + applId + ",applicantId: " + applicantId + ")**********\n");

		return casePartOtherDao.getCasePartOtherByPartId(applId, applicantId);

	}

	@Override
	@Transactional(readOnly = true)
	public List<Vehicle> getCaseVehiclesByPartId(String applId, String applicantId) throws SQLException
	{
		logger.debug("\n********** IN CaseParticipantServiceImpl: getCaseVehicleByPartId(applId: " + applId + ",applicantId: " + applicantId + ")**********\n");

		return caseVehicleDao.getCaseVehiclesByPartId(applId, applicantId);

	}

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecoa.web.service.CaseParticipantService#
	 * getChldNcpAffil(java.lang.String, java.lang.String)
	 */
	public List<CasePartChldAffil> getChldNcpAffil(String applId, String childId) throws SQLException
	{
		return casePartChldAffilDAO.getPartChld(applId, childId);
	}

	/**
	 * @param applicationId
	 * @param childId
	 * @param ncpChldList
	 * @param ncpToAdd
	 * @param ncId
	 * @param opType
	 * @return
	 * @throws SQLException
	 */

	@Transactional
	private String processPartExtDetails(CasePartNCPExt ncpExtDetails, String partId, String ncId, String opType) throws SQLException
	{

		ncpExtDetails.setApplicantId(partId);
		String returnCode = AppConstants.OPERATION_SUCCESS;

		List<CasePartNCPExt> ncpExtDetailsList = getCasePartNCPExtByPartId(ncpExtDetails.getApplicationId(), partId);

		if (ncpExtDetailsList.size() == 0)

		{
			returnCode = partNCPExtDao.createCasePartNCPExt(ncpExtDetails, ncId);
			if (returnCode != null && !returnCode.equals("") && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
				throw new RuntimeException("processPartExtDetails: Insert failed. Return code: "+returnCode);

		}
		else
		{

			if (opType.equals(AppConstants.OPERATION_UPDATE))
			{
				returnCode = partNCPExtDao.updateCasePartNCPExt(ncpExtDetails, ncId);

				if (returnCode != null && !returnCode.equals("") && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
					throw new RuntimeException("processPartExtDetails: Update failed. Return code: "+returnCode);

			}

		}

		return returnCode;

	}

	@Transactional
	private String processPartRelNamesInfo(Map<String, CasePartOther> selectRelNameMap, String applId, String partId, String ncId, String opType) throws SQLException

	{

		String returnCode = AppConstants.OPERATION_SUCCESS;

		if (opType.equals(AppConstants.OPERATION_INSERT))
		{

			for (Map.Entry<String, CasePartOther> entry : selectRelNameMap.entrySet())
			{
				//String key = entry.getKey();
				CasePartOther relationBean = entry.getValue();
				relationBean.setApplicationId(applId);
				relationBean.setApplicantId(partId);
				returnCode = casePartOtherDao.createCasePartOTher(relationBean, ncId);
				if (returnCode != null && !returnCode.equals("") && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
					throw new RuntimeException("processPartRelNamesInfo: Insert failed. Return code: "+returnCode);

			}

		}
		else
		{
			if (opType.equals(AppConstants.OPERATION_UPDATE))
			{
				List<CasePartOther> existRelationList = getCasePartOtherByPartId(applId, partId);
				Map<String, CasePartOther> existRelationMap = new HashMap<String, CasePartOther>();

				for (int i = 0; i < existRelationList.size(); i++)
				{
					CasePartOther existRelationBean = existRelationList.get(i);
					existRelationMap.put(existRelationBean.getRelationship().trim(), existRelationBean);

				}

				for (Map.Entry<String, CasePartOther> entry : selectRelNameMap.entrySet())
				{

					String relShip = entry.getKey();
					if (existRelationMap.containsKey(relShip))
					{
						//update record
						CasePartOther updateRelationBean = selectRelNameMap.get(relShip);

						updateRelationBean.setApplicationId(applId);
						updateRelationBean.setApplicantId(partId);
						returnCode = casePartOtherDao.updateCasePartOTher(updateRelationBean, ncId);
						if (returnCode != null && !returnCode.equals("") && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
							throw new RuntimeException("processPartRelNamesInfo: Insert failed. Return code: "+returnCode);

					}
					else
					{
						//create record,since it does not exist in Map created from db existing.
						CasePartOther insertRelationBean = selectRelNameMap.get(relShip);
						insertRelationBean.setApplicationId(applId);
						insertRelationBean.setApplicantId(partId);
						returnCode = casePartOtherDao.createCasePartOTher(insertRelationBean, ncId);
						if (returnCode != null && !returnCode.equals("") && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
							throw new RuntimeException("processPartRelNamesInfo: Insert failed. Return code: "+returnCode);

					}

				}
				// Get records not exist in selectMap , but already saved in
				// db...we do not need this record any more so needs to remove
				// from db
				for (Map.Entry<String, CasePartOther> entry : existRelationMap.entrySet())
				{
					// String key = entry.getKey();
					String relShip = entry.getKey();
					if (!selectRelNameMap.containsKey(relShip))
					{
						// delete record
						CasePartOther deleteRelationBean = existRelationMap.get(relShip);

						returnCode = casePartOtherDao.deleteCasePartOTher(deleteRelationBean, ncId);
						if (returnCode != null && !returnCode.equals("") && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
							throw new RuntimeException("processPartRelNamesInfo: Insert failed. Return code: "+returnCode);

					}

				}

			}

		}

		return returnCode;

	}

	@Transactional
	private String processPartVehicleInfo(List<Vehicle> vehicleList, String applId, String partId, String ncId, String opType) throws SQLException
	{

		String returnCode = AppConstants.OPERATION_SUCCESS;

		if (opType.equals(AppConstants.OPERATION_INSERT))
		{ //
			for (int i = 0; i < vehicleList.size(); i++)
			{
				Vehicle insertVehicle = vehicleList.get(i);
				insertVehicle.setApplicationId(applId);
				insertVehicle.setApplicantId(partId);
				returnCode = caseVehicleDao.createCaseVehile(insertVehicle, ncId);

				if (returnCode != null && !returnCode.equals("") && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
					throw new RuntimeException("processPartVehicleInfo: Insert failed. Return code: "+returnCode);

			}
		}
		else
		{
			if (opType.equals(AppConstants.OPERATION_UPDATE))
			{

				List<Vehicle> vehicleExistList = getCaseVehiclesByPartId(applId, partId);
				if (vehicleExistList.size() > 0)
				{

					// delete all the Vehicle records in table for the specific
					// applicationId and applicantId.

					Vehicle deleteVehicles = new Vehicle();
					deleteVehicles.setApplicationId(applId);
					deleteVehicles.setApplicantId(partId);

					returnCode = caseVehicleDao.deleteCaseVehicles(deleteVehicles, ncId);
					if (returnCode != null && !returnCode.equals("") && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
						throw new RuntimeException("processPartVehicleInfo: Delete failed. Return code: "+returnCode);

					// insert new vehicle records after deletion is successful.
					for (int i = 0; i < vehicleList.size(); i++)
					{
						Vehicle insertVehicle = vehicleList.get(i);
						insertVehicle.setApplicationId(applId);
						insertVehicle.setApplicantId(partId);
						returnCode = caseVehicleDao.createCaseVehile(insertVehicle, ncId);

						if (returnCode != null && !returnCode.equals("") && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
							throw new RuntimeException("processPartVehicleInfo: Insert failed. Return code: "+returnCode);

					}

				}

				else
				{ // no records to delete, insert new records.
					for (int i = 0; i < vehicleList.size(); i++)
					{
						Vehicle insertVehicle = vehicleList.get(i);
						insertVehicle.setApplicationId(applId);
						insertVehicle.setApplicantId(partId);
						returnCode = caseVehicleDao.createCaseVehile(insertVehicle, ncId);

						if (returnCode != null && !returnCode.equals("") && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
							throw new RuntimeException("processPartVehicleInfo: Insert failed. Return code: "+returnCode);

					}

				}

			}

		}

		return returnCode;
	}

	@Transactional
	private String processCourtOrderInfo(Map<String, CaseCourtOrder> selectedOrderMap, Map<String, String[]> childrenMap, String applicationId, String applicantId, String ncId, String opType) throws SQLException
	{

		String returnCode = AppConstants.OPERATION_SUCCESS;

		if (opType.equals(AppConstants.OPERATION_INSERT))
		{

			for (Map.Entry<String, CaseCourtOrder> entry : selectedOrderMap.entrySet())
			{
				// String key = entry.getKey();
				CaseCourtOrder orderBean = entry.getValue();
				orderBean.setApplicationId(applicationId);
				orderBean.setApplicantId(applicantId);
				returnCode = caseOrderDao.createCaseCourtOrder(orderBean, ncId);
				if (returnCode == null || !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
					throw new RuntimeException("processCourtOrderInfo: Insert failed.");

			}

		}
		else
		{
			if (opType.equals(AppConstants.OPERATION_UPDATE))
			{
				List<CaseCourtOrder> existOrderList = getCaseCourtOrderByPartId(applicationId, applicantId);
				Map<String, CaseCourtOrder> existOrderMap = new HashMap<String, CaseCourtOrder>();

				for (int i = 0; i < existOrderList.size(); i++)
				{
					CaseCourtOrder existOrderBean = existOrderList.get(i);
					existOrderMap.put(existOrderBean.getSupportType().trim(), existOrderBean);

				}

				for (Map.Entry<String, CaseCourtOrder> entry : selectedOrderMap.entrySet())
				{

					String supportType = entry.getKey();
					if (existOrderMap.containsKey(supportType))
					{
						// update record
						CaseCourtOrder updateOrderBean = selectedOrderMap.get(supportType);

						updateOrderBean.setApplicationId(applicationId);
						updateOrderBean.setApplicantId(applicantId);
						returnCode = caseOrderDao.updateCaseCourtOrder(updateOrderBean, ncId);
						if (returnCode == null || !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
							throw new RuntimeException("processCourtOrderInfo: update failed.");

					}
					else
					{
						// create record,since it does not exist in Map created
						// from db existing.
						CaseCourtOrder insertOrderBean = selectedOrderMap.get(supportType);
						insertOrderBean.setApplicationId(applicationId);
						insertOrderBean.setApplicantId(applicantId);
						returnCode = caseOrderDao.createCaseCourtOrder(insertOrderBean, ncId);
						if (returnCode == null || !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
							throw new RuntimeException("processCourtOrderInfo: create failed.");

					}

				}
				// Get records not exist in selectMap , but already saved in
				// db...we do not need this record any more so needs to remove
				// from db
				for (Map.Entry<String, CaseCourtOrder> entry : existOrderMap.entrySet())
				{
					// String key = entry.getKey();
					String supportType = entry.getKey();
					if (!selectedOrderMap.containsKey(supportType))
					{
						// delete record
						CaseCourtOrder deleteOrderBean = existOrderMap.get(supportType);

						returnCode = caseOrderDao.deleteCaseCourtOrder(deleteOrderBean, ncId);
						if (returnCode == null || !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
							throw new RuntimeException("processCourtOrderInfo: delete failed.");

					}

				}

				for (CaseCourtOrder orderBean : existOrderList)

				{
					// get list of child entries exist in ACTW_PART_ORDER table
					List<CasePartOrder> orderChildList = getCasePartOrderByOrderNum(orderBean.getOrderNm());

					if (orderChildList.size() > 0)
					{
						// it is supposed to delete all the child entries with
						// given orderNumber.
						returnCode = caseOrderChildDao.deleteCasePartOrder(orderChildList.get(0), ncId);
						if (returnCode == null || !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
							throw new RuntimeException("processCourtOrderInfo: delete failed.");

					}

				}

				List<CaseCourtOrder> updatedOrderList = getCaseCourtOrderByPartId(applicationId, applicantId);

				for (CaseCourtOrder updatedOrderBean : updatedOrderList)
				{
					int OrderType = Integer.parseInt(updatedOrderBean.getSupportType());

					switch (OrderType)
					{

					case 1:
						String[] courtChildren = childrenMap.get("1");
						for (int i = 0; i < courtChildren.length; i++)
						{

							CasePartOrder insertChildBean = new CasePartOrder();
							insertChildBean.setApplicationId(applicationId);
							insertChildBean.setApplicantId(applicantId);
							insertChildBean.setOrderNm(updatedOrderBean.getOrderNm());
							insertChildBean.setChildApplicantId(courtChildren[i]);
							returnCode = caseOrderChildDao.createCasePartOrder(insertChildBean, ncId);
							if (returnCode == null || !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
								throw new RuntimeException("processCourtOrderInfo: create failed");

						}

						break;

					case 2:

						String[] medicalChildren = childrenMap.get("2");
						for (int i = 0; i < medicalChildren.length; i++)
						{

							CasePartOrder insertChildBean = new CasePartOrder();
							insertChildBean.setApplicationId(applicationId);
							insertChildBean.setApplicantId(applicantId);
							insertChildBean.setOrderNm(updatedOrderBean.getOrderNm());
							insertChildBean.setChildApplicantId(medicalChildren[i]);
							returnCode = caseOrderChildDao.createCasePartOrder(insertChildBean, ncId);
							if (returnCode == null || !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
								throw new RuntimeException("processCourtOrderInfo: create failed for medical.");

						}

						break;

					case 3:

						String[] spousalChildren = childrenMap.get("3");
						for (int i = 0; i < spousalChildren.length; i++)
						{

							CasePartOrder insertChildBean = new CasePartOrder();
							insertChildBean.setApplicationId(applicationId);
							insertChildBean.setApplicantId(applicantId);
							insertChildBean.setOrderNm(updatedOrderBean.getOrderNm());
							insertChildBean.setChildApplicantId(spousalChildren[i]);
							returnCode = caseOrderChildDao.createCasePartOrder(insertChildBean, ncId);
							if (returnCode == null || !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
								throw new RuntimeException("processCourtOrderInfo: create failed, Rollback this transaction!");

						}

						break;

					case 4:

						String[] voluntaryChildren = childrenMap.get("4");
						for (int i = 0; i < voluntaryChildren.length; i++)
						{

							CasePartOrder insertChildBean = new CasePartOrder();
							insertChildBean.setApplicationId(applicationId);
							insertChildBean.setApplicantId(applicantId);
							insertChildBean.setOrderNm(updatedOrderBean.getOrderNm());
							insertChildBean.setChildApplicantId(voluntaryChildren[i]);
							returnCode = caseOrderChildDao.createCasePartOrder(insertChildBean, ncId);
							if (returnCode == null || !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
								throw new RuntimeException("processCourtOrderInfo: create failed for voluntary.");

						}

						break;

					}

				}

			}

		}

		return returnCode;
	}

	@Transactional
	private String processPartChldAffil(String applicationId, String childId, Map<String, String> ncpChldList, List<CasePartChldAffil> ncpToAdd, String ncId, String opType) throws SQLException
	{
		String returnCode = AppConstants.OPERATION_SUCCESS;
		boolean dbUpdate = false;

		List<CasePartChldAffil> ncpChldDbList = new ArrayList<CasePartChldAffil>();

		CasePartChldAffil ncpChldBeanToAdd = new CasePartChldAffil();
		ncpChldBeanToAdd.setApplicationId(applicationId);
		ncpChldBeanToAdd.setChildId(childId);

		if (ncpChldList != null && ncpChldList.size() > 0)
		{
			if (opType.equals(AppConstants.OPERATION_UPDATE))
			{
				ncpChldDbList = getChldNcpAffil(applicationId, childId);
				if (ncpChldDbList.size() > 0)
				{
					dbUpdate = true;
				}
			}

			//insert new records in case of create or no existing db records
			if (opType.equals(AppConstants.OPERATION_INSERT) || !dbUpdate)
			{
				for (String key : ncpChldList.keySet())
				{
					ncpChldBeanToAdd.setApplicantId(key);
					returnCode = casePartChldAffilDAO.createPartChld(ncpChldBeanToAdd, ncId);
					if (returnCode != null && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
						throw new RuntimeException("processPartChldAffil: Save/Update failed. REturn Code: "+returnCode);
				}
			}

			if (opType.equals(AppConstants.OPERATION_UPDATE) && dbUpdate)
			{
				for (CasePartChldAffil chldNcp : ncpChldDbList)
				{
					if (!ncpChldList.containsKey(chldNcp.getApplicantId().trim()))
					{
						// delete key for existing income source entry 
						returnCode = casePartChldAffilDAO.deletePartChld(chldNcp, ncId);
					}
					else
					{
						// check for record update
						ncpChldList.remove(chldNcp.getApplicantId().trim());
					}
				}

				// create record for newly added income source entry
				for (String ncp : ncpChldList.keySet())
				{
					ncpChldBeanToAdd.setApplicantId(ncp);
					returnCode = casePartChldAffilDAO.createPartChld(ncpChldBeanToAdd, ncId);
					if (returnCode != null && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
						throw new RuntimeException("processPartChldAffil: create failed. Return code: "+returnCode);
				}
			}
		}
		else
		{
			//delete any existing records from database
			if (opType.equals(AppConstants.OPERATION_UPDATE))
			{
				ncpChldDbList = getChldNcpAffil(applicationId, childId);
				if (ncpChldDbList.size() > 0)
				{
					for (CasePartChldAffil ncpTodel : ncpChldDbList)
					{
						returnCode = casePartChldAffilDAO.deletePartChld(ncpTodel, ncId);
						if (returnCode != null && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
							throw new RuntimeException("processPartChldAffil: delete failed. Return code: "+returnCode);
					}

				}
			}
		}
		//if first name and last name are entered for NCP, create new NCP participant and link to the child

		if (ncpToAdd != null && ncpToAdd.size() > 0)
		{
			for (CasePartChldAffil ncpChldInfo : ncpToAdd)
			{
				CaseParticipant partInfo = new CaseParticipant();
				partInfo.setApplicationId(applicationId);
				partInfo.setApplicantFNm(ncpChldInfo.getNameF());
				partInfo.setApplicantLNm(ncpChldInfo.getNameL());
				partInfo.setPartType(AppConstants.NCP_PARTICIPANT_TYPE);
				String partId = casePartDao.createParticipant(partInfo, ncId);
				if (partId != null && !partId.equals(""))
				{
					ncpChldInfo.setApplicationId(applicationId);
					ncpChldInfo.setChildId(childId);
					ncpChldInfo.setApplicantId(partId);
					returnCode = casePartChldAffilDAO.createPartChld(ncpChldInfo, ncId);
					if (returnCode != null && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
						throw new RuntimeException("processPartChldAffil: Create failed. Return code: "+returnCode);
				}
				else
				{
					throw new RuntimeException("processPartChldAffil: Save/Update failed.");
				}

			}
		}

		return returnCode;
	}

	/**
	 * @param applId
	 * @param thirdPtyId
	 * @return
	 * @throws SQLException
	 */
	public List<ThirdParty> getThirdPartyBy3ptyId(String applId, String thirdPtyId) throws SQLException
	{
		return thirdPartyDao.getThirdPartyBy3ptyId(applId, thirdPtyId);
	}

	/**
	 * @param applicationId
	 * @param applicantId
	 * @return
	 * @throws SQLException
	 */
	public List<CasePartEmp> getPartEmp(String applicationId, String applicantId) throws SQLException
	{
		return casePartEmpDAO.getPartEmp(applicationId, applicantId);
	}

	/**
	 * @param applicationId
	 * @param applicantId
	 * @param empBean
	 * @param ncId
	 * @param opType
	 * @return
	 * @throws SQLException
	 */
	@Transactional
	private String processPartEmp(String applicationId, String applicantId, CasePartEmp empBean, String ncId, String opType) throws SQLException
	{
		String returnCode = AppConstants.OPERATION_SUCCESS;
		ThirdParty thirdPtyBean;
		String thirdPtyId = "";
		boolean dbUpdate = false;
		List<CasePartEmp> empList = new ArrayList<CasePartEmp>();

		if (empBean != null)
		{
			thirdPtyBean = empBean.getThirdPartyInfo();
			if (opType.equals(AppConstants.OPERATION_UPDATE))
			{
				empList = casePartEmpDAO.getPartEmp(applicationId, applicantId);
				if (empList != null && empList.size() > 0)
				{
					dbUpdate = true;
				}
			}

			//insert new records in case of create or no existing db records
			if (opType.equals(AppConstants.OPERATION_INSERT) || !dbUpdate)
			{
				thirdPtyBean.setApplicationId(applicationId);
				thirdPtyId = thirdPartyDao.createThirdParty(thirdPtyBean, ncId);
				if (thirdPtyId == null || thirdPtyId.equals(""))
				{
					throw new RuntimeException("processPartEmp: ThirdParty create failed.");
				}
				else
				{
					empBean.setApplicationId(applicationId);
					empBean.setApplicantId(applicantId);
					empBean.setThirdPartyId(thirdPtyId);
					returnCode = casePartEmpDAO.createPartEmp(empBean, ncId);
					if (returnCode != null && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
						throw new RuntimeException("processPartEmp: Employment create failed, Return code: "+returnCode);
				}

			}
			if (opType.equals(AppConstants.OPERATION_UPDATE) && dbUpdate)
			{
				thirdPtyBean.setApplicationId(applicationId);
				thirdPtyBean.setThirdPartyId(empList.get(0).getThirdPartyId());
				returnCode = thirdPartyDao.updateThirdParty(thirdPtyBean, ncId);
				if (returnCode != null && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
					throw new RuntimeException("processPartEmp: Employment update failed. Return Code: "+returnCode);

				empBean.setApplicationId(applicationId);
				empBean.setApplicantId(applicantId);
				empBean.setThirdPartyId(empList.get(0).getThirdPartyId());
				casePartEmpDAO.updatePartEmp(empBean, ncId);

				if (returnCode != null && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
					throw new RuntimeException("processPartEmp: Employment Update failed. Return code: "+returnCode);

			}
		}
		else
		{
			if (opType.equals(AppConstants.OPERATION_UPDATE))
			{
				empList = casePartEmpDAO.getPartEmp(applicationId, applicantId);
				if (empList != null && empList.size() > 0)
				{
					for (CasePartEmp emp : empList)
					{
						if (emp.getThirdPartyId() != null && !emp.getThirdPartyId().equals(""))
						{
							thirdPtyBean = thirdPartyDao.getThirdPartyBy3ptyId(applicationId, emp.getThirdPartyId()).get(0);
							if (thirdPtyBean != null)
							{
								returnCode = thirdPartyDao.deleteThirdParty(thirdPtyBean, ncId);
								if (returnCode != null && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
									throw new RuntimeException("processPartEmp: delete thirdparty failed. Return code: "+returnCode);
							}

						}
						returnCode = casePartEmpDAO.deletePartEmp(emp, ncId);

						if (returnCode != null && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
							throw new RuntimeException("processPartEmp: Employment delete failed. Return code:"+returnCode);

					}
				}
			}
		}

		return returnCode;
	}

	public List<CasePartInsurance> getPartInsurance(String applicationId, String applicantId) throws SQLException
	{
		return casePartInsuranceDAO.getCasePartInsurance(applicationId, applicantId);
	}

	@Transactional
	private String processPartInsurance(String applicationId, String applicantId, Map<String, CasePartInsurance> insuranceList, String ncId, String opType) throws SQLException
	{
		String returnCode = AppConstants.OPERATION_SUCCESS;
		boolean dbUpdate = false;

		List<CasePartInsurance> insdbList = new ArrayList<CasePartInsurance>();

		CasePartInsurance insuranceInfo = new CasePartInsurance();
		insuranceInfo.setApplicationId(applicationId);
		insuranceInfo.setApplicantId(applicantId);

		if (insuranceList != null && insuranceList.size() > 0)
		{
			if (opType.equals(AppConstants.OPERATION_UPDATE))
			{
				insdbList = getPartInsurance(applicationId, applicantId);
				if (insdbList.size() > 0)
				{
					dbUpdate = true;
				}
			}

			//insert new records in case of create or no existing db records
			if (opType.equals(AppConstants.OPERATION_INSERT) || !dbUpdate)
			{
				for (String key : insuranceList.keySet())
				{
					CasePartInsurance insBeanToAdd = insuranceList.get(key);
					insBeanToAdd.setApplicationId(applicationId);
					insBeanToAdd.setApplicantId(applicantId);
					returnCode = casePartInsuranceDAO.createCasePartInsurance(insBeanToAdd, ncId);
					if (returnCode != null && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
						throw new RuntimeException("processPartInsurance: create failed. Return code: "+returnCode);
				}
			}

			if (opType.equals(AppConstants.OPERATION_UPDATE) && dbUpdate)
			{
				for (CasePartInsurance insurnce : insdbList)
				{
					if (!insuranceList.containsKey(insurnce.getInsuranceType().trim()))
					{

						returnCode = casePartInsuranceDAO.deleteCasePartInsurance(insurnce, ncId);
					}
					else
					{
						// check for record update
						//update
						CasePartInsurance newInsInfo = insuranceList.get(insurnce.getInsuranceType().trim());
						if (!insurnce.getInsuranceProvider().trim().equals(newInsInfo.getInsuranceProvider().trim()) || !insurnce.getInsHolderF().trim().equals(newInsInfo.getInsHolderF().trim()) || !insurnce.getInsHolderL().trim().equals(newInsInfo.getInsHolderL().trim()) || !insurnce.getInsHolderRelshp().trim().equals(newInsInfo.getInsHolderRelshp().trim()))
						{
							newInsInfo.setApplicationId(applicationId);
							newInsInfo.setApplicantId(applicantId);
							returnCode = casePartInsuranceDAO.updateCasePartInsurance(newInsInfo, ncId);
							if (returnCode != null && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
								throw new RuntimeException("processPartInsurance: update failed. Return code:"+returnCode);
						}

						insuranceList.remove(insurnce.getInsuranceType().trim());

					}
					if (returnCode != null && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
						throw new RuntimeException("processPartInsurance: Save/Update failed. Return code: "+returnCode);
				}
				// create record for newly added income source entry
				for (String key : insuranceList.keySet())
				{
					CasePartInsurance insBeanToAdd = insuranceList.get(key);
					insBeanToAdd.setApplicationId(applicationId);
					insBeanToAdd.setApplicantId(applicantId);
					returnCode = casePartInsuranceDAO.createCasePartInsurance(insBeanToAdd, ncId);
					if (returnCode != null && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
						throw new RuntimeException("processPartInsurance: create failed. Return code: "+returnCode);
				}
			}
		}
		else
		{
			//delete any existing records from database
			if (opType.equals(AppConstants.OPERATION_UPDATE))
			{
				insdbList = getPartInsurance(applicationId, applicantId);
				if (insdbList.size() > 0)
				{
					insuranceInfo.setInsuranceType("0");
					returnCode = casePartInsuranceDAO.deleteCasePartInsurance(insuranceInfo, ncId);
					if (returnCode != null && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
						throw new RuntimeException("processPartInsurance: delete failed. Return code:"+returnCode);
				}
			}
		}

		return returnCode;
	}

	@Transactional
	private String processPartProfile(String applicationId, String applicantId, CasePartProfile partProfile, String ncId, String opType) throws SQLException
	{
		String returnCode = AppConstants.OPERATION_SUCCESS;
		partProfile.setApplicationId(applicationId);
		partProfile.setApplicantId(applicantId);
		if (opType.equals(AppConstants.OPERATION_INSERT))
		{
			returnCode = casePartProfileDao.createPartProfile(partProfile, ncId);
		}
		else if (opType.equals(AppConstants.OPERATION_UPDATE))
		{
			List<CasePartProfile> partProfileDB = getPartProfile(applicationId, applicantId);
			if (partProfileDB.size() > 0)
			{
				returnCode = casePartProfileDao.updatePartProfile(partProfile, ncId);
			}
			else
			{
				returnCode = casePartProfileDao.createPartProfile(partProfile, ncId);
			}
		}
		if (returnCode != null && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
			throw new RuntimeException("processPartProfile: Save/Update failed. Return code: "+returnCode);
		return returnCode;
	}

	@Transactional
	private String processPartOther(String applicationId, String applicantId, Map<String, CasePartOther> partOtherList, String ncId, String opType) throws SQLException
	{
		String returnCode = AppConstants.OPERATION_SUCCESS;
		boolean dbUpdate = false;

		List<CasePartOther> partOtherDbList = new ArrayList<CasePartOther>();

		CasePartOther partOtherBeanToAdd = new CasePartOther();
		partOtherBeanToAdd.setApplicationId(applicationId);
		partOtherBeanToAdd.setApplicantId(applicantId);

		if (partOtherList != null && partOtherList.size() > 0)
		{
			if (opType.equals(AppConstants.OPERATION_UPDATE))
			{
				partOtherDbList = getPartOther(applicationId, applicantId);
				if (partOtherDbList.size() > 0)
				{
					dbUpdate = true;
				}
			}

			//insert new records in case of create or no existing db records
			if (opType.equals(AppConstants.OPERATION_INSERT) || !dbUpdate)
			{
				for (String key : partOtherList.keySet())
				{

					CasePartOther partOtherToAdd = partOtherList.get(key);
					partOtherToAdd.setApplicationId(applicationId);
					partOtherToAdd.setApplicantId(applicantId);

					returnCode = casePartOtherDao.createCasePartOTher(partOtherToAdd, ncId);
					if (returnCode != null && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
						throw new RuntimeException("processPartOther: create failed. Return code: "+returnCode);
				}
			}

			if (opType.equals(AppConstants.OPERATION_UPDATE) && dbUpdate)
			{
				for (CasePartOther partOther : partOtherDbList)
				{
					if (!partOtherList.containsKey(partOther.getRelationship().trim()))
					{
						// delete key for existing income source entry 
						returnCode = casePartOtherDao.deleteCasePartOTher(partOther, ncId);
					}
					else
					{
						// check for record update
						String fname = partOtherList.get(partOther.getRelationship().trim()).getFirstNm();
						String lname = partOtherList.get(partOther.getRelationship().trim()).getLastNm();
						String mname = partOtherList.get(partOther.getRelationship().trim()).getMiddleNm();
						partOtherList.remove(partOther.getRelationship().trim());
						if (fname != partOther.getFirstNm() || lname != partOther.getLastNm() || mname != partOther.getMiddleNm())
						{
							partOther.setFirstNm(fname);
							partOther.setLastNm(lname);
							partOther.setMiddleNm(mname);
							returnCode = casePartOtherDao.updateCasePartOTher(partOther, ncId);
						}

					}
					if (returnCode != null && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
						throw new RuntimeException("processPartOther: Save/Update failed. Return code: "+returnCode);
				}

				// create record for newly added entry
				for (String key : partOtherList.keySet())
				{
					CasePartOther partOtherToAdd = partOtherList.get(key);
					partOtherToAdd.setApplicationId(applicationId);
					partOtherToAdd.setApplicantId(applicantId);

					returnCode = casePartOtherDao.createCasePartOTher(partOtherToAdd, ncId);
					if (returnCode != null && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
						throw new RuntimeException("processPartOther: create new partOther failed. Return code: "+returnCode);
				}
			}
		}
		else
		{
			//delete any existing records from database
			if (opType.equals(AppConstants.OPERATION_UPDATE))
			{
				partOtherDbList = getPartOther(applicationId, applicantId);
				if (partOtherDbList.size() > 0)
				{
					for (CasePartOther partOtherToDelete : partOtherDbList)
					{
						returnCode = casePartOtherDao.deleteCasePartOTher(partOtherToDelete, ncId);
						if (returnCode != null && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
							throw new RuntimeException("processPartOther: delete partOther failed. Return code: "+returnCode);
					}
				}
			}
		}

		return returnCode;
	}

	/**
	 * @param applicationId
	 * @param applicantId
	 * @return
	 * @throws SQLException
	 */
	public List<CasePartOther> getPartOther(String applicationId, String applicantId) throws SQLException
	{
		return casePartOtherDao.getCasePartOtherByPartId(applicationId, applicantId);
	}

	/**
	 * @param applicationId
	 * @param applicantId
	 * @return
	 * @throws SQLException
	 */
	public List<CasePartProfile> getPartProfile(String applicationId, String applicantId) throws SQLException
	{
		return casePartProfileDao.getPartProfile(applicationId, applicantId);
	}

	/**
	 * @param applicationId
	 * @param chldId
	 * @param paterntyBean
	 * @param ncId
	 * @param opType
	 * @return
	 * @throws SQLException
	 */
	@Transactional
	private String processPartPaternty(String applicationId, String chldId, CasePartPaternty paterntyBean, String ncId, String opType) throws SQLException
	{
		String returnCode = AppConstants.OPERATION_SUCCESS;
		boolean dbUpdate = false;
		List<CasePartPaternty> patrntyList = new ArrayList<CasePartPaternty>();

		if (paterntyBean != null)
		{
			if (opType.equals(AppConstants.OPERATION_UPDATE))
			{
				patrntyList = casePartPaterntyDao.getPartPaternty(applicationId, chldId);
				if (patrntyList != null && patrntyList.size() > 0)
				{
					dbUpdate = true;
				}
			}

			//insert new records in case of create or no existing db records
			if (opType.equals(AppConstants.OPERATION_INSERT) || !dbUpdate)
			{
				paterntyBean.setApplicationId(applicationId);
				paterntyBean.setApplicantChildId(chldId);
				returnCode = casePartPaterntyDao.createPartPaternty(paterntyBean, ncId);
				if (returnCode != null && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
					throw new RuntimeException("processPartPaternty: Paternty create failed. Return code :"+returnCode);

			}
			if (opType.equals(AppConstants.OPERATION_UPDATE) && dbUpdate)
			{
				paterntyBean.setApplicationId(applicationId);
				paterntyBean.setApplicantChildId(chldId);
				casePartPaterntyDao.updatePartPaternty(paterntyBean, ncId);

				if (returnCode != null && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
					throw new RuntimeException("processPartPaternty: Paternty update failed. Return code: "+returnCode);

			}
		}
		else
		{
			if (opType.equals(AppConstants.OPERATION_UPDATE))
			{
				patrntyList = casePartPaterntyDao.getPartPaternty(applicationId, chldId);
				if (patrntyList != null && patrntyList.size() > 0)
				{
					for (CasePartPaternty paterntyToDel : patrntyList)
					{
						returnCode = casePartPaterntyDao.deletePartPaternty(paterntyToDel, ncId);

						if (returnCode != null && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
							throw new RuntimeException("processPartPaternty: Paternty delete failed. Return code: "+returnCode);
					}

				}
			}
		}

		return returnCode;
	}

	/**
	 * @param applicationId
	 * @param chldId
	 * @return
	 * @throws SQLException
	 */
	public List<CasePartPaternty> getPartPaternty(String applicationId, String chldId) throws SQLException
	{
		return casePartPaterntyDao.getPartPaternty(applicationId, chldId);
	}

	@Transactional
	private String processPartProfileExt(String applicationId, String applicantId, Map<String, CasePartProfileExt> partProfileExtList, String ncId, String opType) throws SQLException
	{
		String returnCode = AppConstants.OPERATION_SUCCESS;
		boolean dbUpdate = false;

		List<CasePartProfileExt> partProfileExtDbList = new ArrayList<CasePartProfileExt>();

		CasePartProfileExt partProfileExtToAdd = new CasePartProfileExt();
		partProfileExtToAdd.setApplicationId(applicationId);
		partProfileExtToAdd.setApplicantId(applicantId);

		if (partProfileExtList != null && partProfileExtList.size() > 0)
		{
			if (opType.equals(AppConstants.OPERATION_UPDATE))
			{
				partProfileExtDbList = getPartProfileExt(applicationId, applicantId);
				if (partProfileExtDbList.size() > 0)
				{
					dbUpdate = true;
				}
			}

			//insert new records in case of create or no existing db records
			if (opType.equals(AppConstants.OPERATION_INSERT) || !dbUpdate)
			{
				for (String key : partProfileExtList.keySet())
				{

					partProfileExtToAdd = partProfileExtList.get(key);
					partProfileExtToAdd.setApplicationId(applicationId);
					partProfileExtToAdd.setApplicantId(applicantId);

					returnCode = casePartProfileExtDao.createPartProfileExt(partProfileExtToAdd, ncId);
					if (returnCode != null && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
						throw new RuntimeException("processPartProfileExt: create failed. Return code: "+returnCode);
				}
			}

			if (opType.equals(AppConstants.OPERATION_UPDATE) && dbUpdate)
			{
				for (CasePartProfileExt partProfileExt : partProfileExtDbList)
				{
					if (!partProfileExtList.containsKey(partProfileExt.getProfileExtType().trim()))
					{
						// delete key for existing income source entry 
						returnCode = casePartProfileExtDao.deletePartProfileExt(partProfileExt, ncId);
					}
					else
					{
						// check for record update
						CasePartProfileExt beanToUpdate = partProfileExtList.get(partProfileExt.getProfileExtType().trim());
						beanToUpdate.setApplicationId(applicationId);
						beanToUpdate.setApplicantId(applicantId);
						partProfileExtList.remove(partProfileExt.getProfileExtType().trim());
						returnCode = casePartProfileExtDao.updatePartProfileExt(beanToUpdate, ncId);

					}
					if (returnCode != null && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
						throw new RuntimeException("processPartProfileExt: update failed. Return code: "+returnCode);
				}

				// create record for newly added income source entry
				for (String key : partProfileExtList.keySet())
				{
					partProfileExtToAdd = partProfileExtList.get(key);
					partProfileExtToAdd.setApplicationId(applicationId);
					partProfileExtToAdd.setApplicantId(applicantId);

					returnCode = casePartProfileExtDao.createPartProfileExt(partProfileExtToAdd, ncId);
					if (returnCode != null && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
						throw new RuntimeException("processPartProfileExt: create failed. Return code: "+returnCode);
				}
			}
		}
		else
		{
			//delete any existing records from database
			if (opType.equals(AppConstants.OPERATION_UPDATE))
			{
				partProfileExtDbList = getPartProfileExt(applicationId, applicantId);
				if (partProfileExtDbList.size() > 0)
				{
					for (CasePartProfileExt partProfileExtToDelete : partProfileExtDbList)
					{
						returnCode = casePartProfileExtDao.deletePartProfileExt(partProfileExtToDelete, ncId);
						if (returnCode != null && !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
							throw new RuntimeException("processPartProfileExt: delete failed. Return code: "+returnCode);
					}
				}
			}
		}
		return returnCode;
	}

	/**
	 * @param applicationId
	 * @param applicantId
	 * @return
	 * @throws SQLException
	 */
	public List<CasePartProfileExt> getPartProfileExt(String applicationId, String applicantId) throws SQLException
	{
		return casePartProfileExtDao.getCasePartProfileExt(applicationId, applicantId);
	}

}
