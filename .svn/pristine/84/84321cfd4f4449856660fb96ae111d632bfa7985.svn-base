package nc.dhhs.nccss.acts.ecoa.beans;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * @author Phani Konuru
 * 
 */

public class CaseParticipant implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -5878882458381095791L;

	private String				applicationId		= "";

	private String				applicantId			= "";

	private String				partId				= "";

	private String				partIdResolved		= "";

	private String				applicantFNm		= "";

	private String				applicantLNm		= "";

	private String				applicantMNm		= "";

	private String				applicantSufix		= "";

	private String				applicantMdnNm		= "";

	private String				applicantSex		= "";

	private String				applicantSexDesc	= "";

	private Date				brthDt;

	private String				brthDtStr			= "";

	private String				ssnNb				= "";

	private String				ethncGrp			= "";

	private String				ethncGrpDesc		= "";

	private String				langPref			= "";

	private String				langPrefDesc		= "";

	private String				specialAssist		= "";

	private String				specialAssistStr	= "";

	private double				income				= 0;

	private String				protOrd				= "";

	private String				applicantAliasNm	= "";

	private Date				lastUpdatDt;

	private String				partType			= "";

	private String				partPrntGrdian		= "";

	private String				resCnty				= "";

	private String				specialAssistOt		= "";

	SimpleDateFormat			DATE_FORMAT			= new SimpleDateFormat("yyyy-MM-dd");

	SimpleDateFormat			DATE_FORMAT_SCREEN	= new SimpleDateFormat("MM/dd/yyyy");

	NumberFormat				formatter			= new DecimalFormat("#0.00");

	public CaseParticipant()
	{

	}

	/**
	 * @return the applicationId
	 */
	public String getApplicationId()
	{
		return applicationId.trim();
	}

	/**
	 * @param applicationId
	 *            the applicationId to set
	 */
	public void setApplicationId(String applicationId)
	{
		this.applicationId = applicationId;
	}

	/**
	 * @return the applicantId
	 */
	public String getApplicantId()
	{
		return applicantId.trim();
	}

	/**
	 * @param applicantId
	 *            the applicantId to set
	 */
	public void setApplicantId(String applicantId)
	{
		this.applicantId = applicantId;
	}

	/**
	 * @return the partId
	 */
	public String getPartId()
	{
		return partId.trim();
	}

	/**
	 * @param partId
	 *            the partId to set
	 */
	public void setPartId(String partId)
	{
		this.partId = partId;
	}

	/**
	 * @return the partIdResolved
	 */
	public String getPartIdResolved()
	{
		return partIdResolved.trim();
	}

	/**
	 * @param partIdResolved
	 *            the partIdResolved to set
	 */
	public void setPartIdResolved(String partIdResolved)
	{
		this.partIdResolved = partIdResolved;
	}

	/**
	 * @return the applicantFNm
	 */
	public String getApplicantFNm()
	{
		return applicantFNm.trim();
	}

	/**
	 * @param applicantFNm
	 *            the applicantFNm to set
	 */
	public void setApplicantFNm(String applicantFNm)
	{
		this.applicantFNm = applicantFNm;
	}

	/**
	 * @return the applicantLNm
	 */
	public String getApplicantLNm()
	{
		return applicantLNm.trim();
	}

	/**
	 * @param applicantLNm
	 *            the applicantLNm to set
	 */
	public void setApplicantLNm(String applicantLNm)
	{
		this.applicantLNm = applicantLNm;
	}

	/**
	 * @return the applicantMNm
	 */
	public String getApplicantMNm()
	{
		return applicantMNm.trim();
	}

	/**
	 * @param applicantMNm
	 *            the applicantMNm to set
	 */
	public void setApplicantMNm(String applicantMNm)
	{
		this.applicantMNm = applicantMNm;
	}

	/**
	 * @return the applicantSufix
	 */
	public String getApplicantSufix()
	{
		return applicantSufix.trim();
	}

	/**
	 * @param applicantSufix
	 *            the applicantSufix to set
	 */
	public void setApplicantSufix(String applicantSufix)
	{
		this.applicantSufix = applicantSufix;
	}

	/**
	 * @return the applicantMdnNm
	 */
	public String getApplicantMdnNm()
	{
		return applicantMdnNm.trim();
	}

	/**
	 * @param applicantMdnNm
	 *            the applicantMdnNm to set
	 */
	public void setApplicantMdnNm(String applicantMdnNm)
	{
		this.applicantMdnNm = applicantMdnNm;
	}

	/**
	 * @return the applicantSex
	 */
	public String getApplicantSex()
	{
		return applicantSex.trim();
	}

	/**
	 * @param applicantSex
	 *            the applicantSex to set
	 */
	public void setApplicantSex(String applicantSex)
	{
		this.applicantSex = applicantSex;
	}

	/**
	 * @return the applicantSexDesc
	 */
	public String getApplicantSexDesc()
	{
		return applicantSexDesc.trim();
	}

	/**
	 * @param applicantSexDesc
	 *            the applicantSexDesc to set
	 */
	public void setApplicantSexDesc(String applicantSexDesc)
	{
		this.applicantSexDesc = applicantSexDesc;
	}

	/**
	 * @return the brthDt
	 */
	public Date getBrthDt()
	{
		return brthDt;
	}

	/**
	 * @param brthDt
	 *            the brthDt to set
	 */
	public void setBrthDt(Date brthDt)
	{
		this.brthDt = brthDt;
	}

	/**
	 * @return
	 */
	public String getBrthDtStr()
	{
		if (brthDt == null) return null;
		else
			return DATE_FORMAT.format(brthDt).trim();
	}

	/**
	 * @return
	 */
	public String getBrthDtStrPg()
	{
		if (brthDt == null) return "";
		else
			return DATE_FORMAT_SCREEN.format(brthDt).trim().equals(AppConstants.PAGE_DEFAULT_DATE) ? "" : DATE_FORMAT_SCREEN.format(brthDt).trim();
	}

	/**
	 * @return the ssnNb
	 */
	public String getSsnNb()
	{
		return ssnNb.trim();
	}

	/**
	 * @param ssnNb
	 *            the ssnNb to set
	 */
	public void setSsnNb(String ssnNb)
	{
		this.ssnNb = ssnNb;
	}

	/**
	 * @return the ethncGrp
	 */
	public String getEthncGrp()
	{
		return ethncGrp.trim();
	}

	/**
	 * @param ethncGrp
	 *            the ethncGrp to set
	 */
	public void setEthncGrp(String ethncGrp)
	{
		this.ethncGrp = ethncGrp;
	}

	/**
	 * @return the ethncGrpDesc
	 */
	public String getEthncGrpDesc()
	{
		return ethncGrpDesc.trim();
	}

	/**
	 * @param ethncGrpDesc
	 *            the ethncGrpDesc to set
	 */
	public void setEthncGrpDesc(String ethncGrpDesc)
	{
		this.ethncGrpDesc = ethncGrpDesc;
	}

	/**
	 * @return the langPref
	 */
	public String getLangPref()
	{
		return langPref.trim();
	}

	/**
	 * @param langPref
	 *            the langPref to set
	 */
	public void setLangPref(String langPref)
	{
		this.langPref = langPref;
	}

	/**
	 * @return the langPrefDesc
	 */
	public String getLangPrefDesc()
	{
		return langPrefDesc.trim();
	}

	/**
	 * @param langPrefDesc
	 *            the langPrefDesc to set
	 */
	public void setLangPrefDesc(String langPrefDesc)
	{
		this.langPrefDesc = langPrefDesc;
	}

	/**
	 * @return the specialAssist
	 */
	public String getSpecialAssist()
	{
		return specialAssist.trim();
	}

	/**
	 * @param specialAssist
	 *            the specialAssist to set
	 */
	public void setSpecialAssist(String specialAssist)
	{
		this.specialAssist = specialAssist;
	}

	/**
	 * @return the specialAssistStr
	 */
	public String getSpecialAssistStr()
	{
		return specialAssistStr;
	}

	/**
	 * @param specialAssistStr
	 *            the specialAssistStr to set
	 */
	public void setSpecialAssistStr(String specialAssistStr)
	{
		this.specialAssistStr = specialAssistStr;
	}

	/**
	 * @return the income
	 */
	public String getIncomeStr()
	{
		return formatter.format(income);
	}

	/**
	 * @return the income
	 */
	public double getIncome()
	{
		return income;
	}

	/**
	 * @param income
	 *            the income to set
	 */
	public void setIncome(double income)
	{
		this.income = income;
	}

	/**
	 * @return the protOrd
	 */
	public String getProtOrd()
	{
		return protOrd.trim();
	}

	/**
	 * @param protOrd
	 *            the protOrd to set
	 */
	public void setProtOrd(String protOrd)
	{
		this.protOrd = protOrd;
	}

	/**
	 * @return the applicantAliasNm
	 */
	public String getApplicantAliasNm()
	{
		return applicantAliasNm.trim();
	}

	/**
	 * @param applicantAliasNm
	 *            the applicantAliasNm to set
	 */
	public void setApplicantAliasNm(String applicantAliasNm)
	{
		this.applicantAliasNm = applicantAliasNm;
	}

	/**
	 * @return the lastUpdatDt
	 */
	public Date getLastUpdatDt()
	{
		return lastUpdatDt;
	}

	/**
	 * @return
	 */
	public String getLastUpdatDtStr()
	{
		if (lastUpdatDt == null) return null;
		else
			return DATE_FORMAT.format(lastUpdatDt).trim();
	}

	/**
	 * @param lastUpdatDt
	 */
	public void setLastUpdatDt(Date lastUpdatDt)
	{
		this.lastUpdatDt = lastUpdatDt;
	}

	/**
	 * @return the partType
	 */
	public String getPartType()
	{
		return partType.trim();
	}

	/**
	 * @param partType
	 *            the partType to set
	 */
	public void setPartType(String partType)
	{
		this.partType = partType;
	}

	/**
	 * @return the partPrntGrdian
	 */
	public String getPartPrntGrdian()
	{
		return partPrntGrdian.trim();
	}

	/**
	 * @param partPrntGrdian
	 *            the partPrntGrdian to set
	 */
	public void setPartPrntGrdian(String partPrntGrdian)
	{
		this.partPrntGrdian = partPrntGrdian;
	}

	/**
	 * @return the ResCnty
	 */
	public String getResCnty()
	{
		return resCnty;
	}

	/**
	 * @param ResCnty
	 *            the ResCnty to set
	 */
	public void setResCnty(String partResCnty)
	{
		this.resCnty = partResCnty;
	}

	/**
	 * @return the specialAssistOt
	 */
	public String getSpecialAssistOt()
	{
		return specialAssistOt;
	}

	/**
	 * @param specialAssistOt
	 *            the specialAssistOt to set
	 */
	public void setSpecialAssistOt(String specialAssistOt)
	{
		this.specialAssistOt = specialAssistOt;
	}

}