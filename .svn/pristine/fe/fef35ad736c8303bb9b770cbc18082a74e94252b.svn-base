package nc.dhhs.nccss.acts.ecoa.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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
import nc.dhhs.nccss.acts.ecoa.beans.ThirdParty;
import nc.dhhs.nccss.acts.ecoa.beans.Vehicle;
import nc.dhhs.nccss.acts.ecoa.web.controller.beans.ParamData;

/*
 * @author Phani Konuru
 */

public interface CaseParticipantService
{

	/**
	 * @param partInfo
	 * @param ncId
	 * @return
	 * @throws SQLException
	 */
	public String CreateCaseParticipant(CaseParticipant partInfo, ParamData pageData, String ncid, String partType)
			throws SQLException;

	/**
	 * @param applId
	 * @param partType
	 * @return
	 * @throws SQLException
	 */
	public List<CaseParticipant> getParticipantByPartType(String applId, String partType) throws SQLException;

	/**
	 * @param appId
	 * @param partId
	 * @return
	 * @throws SQLException
	 */
	public List<CaseParticipant> getParticipantByPartId(String applId, String partId) throws SQLException;

	/**
	 * @param partId
	 * @param ncId
	 * @return
	 * @throws SQLException
	 */
	public String deleteParticipant(String appId, String partId, String ncId) throws SQLException;

	/**
	 * @param partInfo
	 * @param ncId
	 * @return
	 * @throws SQLException
	 */
	public String updateParticipant(CaseParticipant partInfo, ParamData pageData, String ncId, String partType)
			throws SQLException;

	/**
	 * @param applBean
	 * @param partInfo
	 * @param ncId
	 * @return
	 * @throws SQLException
	 */
	public String CreateApplicant(CaseApplication applBean, CaseParticipant partInfo, String ncId,
			Map<String, CasePartBenefit> benefits, ThirdParty thirdPartyBean) throws SQLException;

	/**
	 * @param partInfo
	 * @param ncId
	 * @return
	 * @throws SQLException
	 */
	public String updateApplicant(CaseApplication applBean, CaseParticipant partInfo, String ncId,
			Map<String, CasePartBenefit> benefits, ThirdParty thirdPartyBean) throws SQLException;

	/**
	 * @param applId
	 * @param partId
	 * @return
	 * @throws SQLException
	 */
	public List<CasePartAddress> getParticipantAddr(String applId, String partId) throws SQLException;

	/**
	 * @param applId
	 * @param applicantId
	 * @return
	 * @throws SQLException
	 */
	public List<CasePartBenefit> getCasePartBenefitsByFieldId(String applId, String applicantId, String PartType)
			throws SQLException;

	/**
	 * 
	 * @param applicationId
	 * @return
	 * @throws SQLException
	 */
	public List<ThirdParty> getThirdPartyByApplIdandType(String applicationId, String thirdPartyType)
			throws SQLException;

	/**
	 * @param applId
	 * @param applicantId
	 * @return
	 * @throws SQLException
	 */
	public List<CasePartIncome> getParticipantIncome(String applId, String applicantId) throws SQLException;

	/**
	 * @param applId
	 * @param partId
	 * @return
	 * @throws SQLException
	 */
	public List<CasePartContact> getPartContact(String applId, String applicantId) throws SQLException;

	/**
	 * @param applId
	 * @param childId
	 * @return
	 * @throws SQLException
	 */
	public List<CasePartChldAffil> getChldNcpAffil(String applId, String childId) throws SQLException;

	/**
	 * @param applicationId
	 * @param applicantId
	 * @return
	 * @throws SQLException
	 */

	public List<CasePartChldAffil> getNCPLinkedChildren(String applId, String applicantId) throws SQLException;

	/**
	 * @param orderNum
	 * @throws SQLException
	 */

	public List<CasePartOrder> getCasePartOrderByOrderNum(String orderNum) throws SQLException;

	/**
	 * @param applicationId
	 * @param applicantId
	 * @return
	 * @throws SQLException
	 */
	public List<CasePartEmp> getPartEmp(String applicationId, String applicantId) throws SQLException;

	/**
	 * @param applId
	 * @param thirdPartyId
	 * @return
	 * @throws SQLException
	 */
	public List<ThirdParty> getThirdPartyBy3ptyId(String applId, String thirdPartyId) throws SQLException;

	/**
	 * @param applicationId
	 * @param applicantId
	 * @return
	 * @throws SQLException
	 */
	public List<CasePartInsurance> getPartInsurance(String applicationId, String applicantId) throws SQLException;

	/**
	 * @param applicationId
	 * @param applicantId
	 * @return
	 * @throws SQLException
	 */
	public List<CasePartOther> getPartOther(String applicationId, String applicantId) throws SQLException;

	/**
	 * @param applicationId
	 * @param applicantId
	 * @return
	 * @throws SQLException
	 */

	public List<CasePartNCPExt> getCasePartNCPExtByPartId(String applId, String applicantId) throws SQLException;

	/**
	 * @param applicationId
	 * @param applicantId
	 * @return
	 * @throws SQLException
	 */

	public List<CasePartOther> getCasePartOtherByPartId(String applId, String applicantId) throws SQLException;

	/**
	 * @param applicationId
	 * @param applicantId
	 * @return
	 * @throws SQLException
	 */
	public List<Vehicle> getCaseVehiclesByPartId(String applId, String applicantId) throws SQLException;

	/**
	 * @param applId
	 * @param applicantId
	 * @return
	 * @throws SQLException
	 */
	public List<CaseCourtOrder> getCaseCourtOrderByPartId(String applId, String applicantId) throws SQLException;

	/**
	 * @param applicationId
	 * @param applicantId
	 * @return
	 * @throws SQLException
	 */
	public List<CasePartProfile> getPartProfile(String applicationId, String applicantId) throws SQLException;

	/**
	 * @param applicationId
	 * @param chldId
	 * @return
	 * @throws SQLException
	 */
	public List<CasePartPaternty> getPartPaternty(String applicationId, String chldId) throws SQLException;

	/**
	 * @param applicationId
	 * @param applicantId
	 * @return
	 * @throws SQLException
	 */
	public List<CasePartProfileExt> getPartProfileExt(String applicationId, String applicantId) throws SQLException;

}