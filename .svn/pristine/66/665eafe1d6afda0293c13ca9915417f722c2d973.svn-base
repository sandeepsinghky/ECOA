package nc.dhhs.nccss.acts.ecoa.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * @author Phani Konuru
 * 
 */

public class CasePartProfile implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -5878882458381095791L;

	private String				applicationId		= "";

	private String				applicantId			= "";

	private String				famRel				= "";

	private String				chldLiveWith		= "";

	private String				ntsYr				= "";

	private String				numYr				= "";

	private String				numMo				= "";

	private String				chldWdlck			= "";

	private String				affidCmplt			= "";

	private String				affidSt				= "";

	private String				maritalStatus		= "";

	private Date				maritalStatusDt;

	private String				maritalStatusDtStr	= "";

	private String				mothMariedBth		= "";

	private String				relOther			= "";
	
	private String				patnEst				= "";
	
	private String				patnTst				= "";
	
	private String				hasIns				= "";

	SimpleDateFormat			DATE_FORMAT			= new SimpleDateFormat("yyyy-MM-dd");

	SimpleDateFormat			DATE_FORMAT_PAGE	= new SimpleDateFormat("MM/dd/yyyy");

	public CasePartProfile()
	{

	}

	/**
	 * @return the applicationId
	 */
	public String getApplicationId()
	{
		return applicationId;
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
		return applicantId;
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
	 * @return the famRel
	 */
	public String getFamRel()
	{
		return famRel;
	}

	/**
	 * @param famRel
	 *            the famRel to set
	 */
	public void setFamRel(String famRel)
	{
		this.famRel = famRel;
	}

	/**
	 * @return the chldLiveWith
	 */
	public String getChldLiveWith()
	{
		return chldLiveWith;
	}

	/**
	 * @param chldLiveWith
	 *            the chldLiveWith to set
	 */
	public void setChldLiveWith(String chldLiveWith)
	{
		this.chldLiveWith = chldLiveWith;
	}

	/**
	 * @return the ntsYr
	 */
	public String getNtsYr()
	{
		return ntsYr.equals("0") ? "" : ntsYr;
	}

	/**
	 * @param ntsYr
	 *            the ntsYr to set
	 */
	public void setNtsYr(String ntsYr)
	{
		this.ntsYr = ntsYr;
	}

	/**
	 * @return the numYr
	 */
	public String getNumYr()
	{
		return numYr.equals("0") ? "" : numYr;
	}

	/**
	 * @param numYr
	 *            the numYr to set
	 */
	public void setNumYr(String numYr)
	{
		this.numYr = numYr;
	}

	/**
	 * @return the numMo
	 */
	public String getNumMo()
	{
		return numMo.equals("0") ? "" : numMo;
	}

	/**
	 * @param numMo
	 *            the numMo to set
	 */
	public void setNumMo(String numMo)
	{
		this.numMo = numMo;
	}

	/**
	 * @return the chldWdlck
	 */
	public String getChldWdlck()
	{
		return chldWdlck;
	}

	/**
	 * @param chldWdlck
	 *            the chldWdlck to set
	 */
	public void setChldWdlck(String chldWdlck)
	{
		this.chldWdlck = chldWdlck;
	}

	/**
	 * @return the affidCmplt
	 */
	public String getAffidCmplt()
	{
		return affidCmplt;
	}

	/**
	 * @param affidCmplt
	 *            the affidCmplt to set
	 */
	public void setAffidCmplt(String affidCmplt)
	{
		this.affidCmplt = affidCmplt;
	}

	/**
	 * @return the affidSt
	 */
	public String getAffidSt()
	{
		return affidSt;
	}

	/**
	 * @param affidSt
	 *            the affidSt to set
	 */
	public void setAffidSt(String affidSt)
	{
		this.affidSt = affidSt;
	}

	/**
	 * @return the maritalStatus
	 */
	public String getMaritalStatus()
	{
		return maritalStatus;
	}

	/**
	 * @param maritalStatus
	 *            the maritalStatus to set
	 */
	public void setMaritalStatus(String maritalStatus)
	{
		this.maritalStatus = maritalStatus;
	}

	/**
	 * @return the maritalStatusDt
	 */
	public Date getMaritalStatusDt()
	{
		return maritalStatusDt;
	}

	/**
	 * @param maritalStatusDt
	 *            the maritalStatusDt to set
	 */
	public void setMaritalStatusDt(Date maritalStatusDt)
	{
		this.maritalStatusDt = maritalStatusDt;
	}

	/**
	 * @return the maritalStatusDtStr
	 */
	public String getMaritalStatusDtStr()
	{
		if (maritalStatusDt == null) return null;
		else
			return DATE_FORMAT.format(maritalStatusDt).trim();
	}

	/**
	 * @param maStatusDt
	 */
	public void setMaritalStatusDt(String maStatusDt)
	{
		try
		{
			this.maritalStatusDt = DATE_FORMAT_PAGE.parse(maStatusDt);
		}
		catch (Exception e)
		{
		}
	}

	/**
	 * @return the maritalStatusDtStr
	 */
	public String getMaritalStatusDtStrPg()
	{
		if (maritalStatusDt == null) return "";
		else
			return DATE_FORMAT_PAGE.format(maritalStatusDt).trim().equals(AppConstants.PAGE_DEFAULT_DATE) ? "" : DATE_FORMAT_PAGE.format(maritalStatusDt).trim();
	}

	/**
	 * @return the mothMariedBth
	 */
	public String getMothMariedBth()
	{
		return mothMariedBth;
	}

	/**
	 * @param mothMariedBth
	 *            the mothMariedBth to set
	 */
	public void setMothMariedBth(String mothMariedBth)
	{
		this.mothMariedBth = mothMariedBth;
	}

	/**
	 * @return the relOther
	 */
	public String getRelOther()
	{
		return relOther;
	}

	/**
	 * @param relOther
	 *            the relOther to set
	 */
	public void setRelOther(String relOther)
	{
		this.relOther = relOther;
	}

	/**
	 * @return the patnEst
	 */
	public String getPatnEst()
	{
		return patnEst;
	}

	/**
	 * @param patnEst the patnEst to set
	 */
	public void setPatnEst(String patnEst)
	{
		this.patnEst = patnEst;
	}

	/**
	 * @return the patnTst
	 */
	public String getPatnTst()
	{
		return patnTst;
	}

	/**
	 * @param patnTst the patnTst to set
	 */
	public void setPatnTst(String patnTst)
	{
		this.patnTst = patnTst;
	}

	/**
	 * @return the hasIns
	 */
	public String getHasIns()
	{
		return hasIns;
	}

	/**
	 * @param hasIns the hasIns to set
	 */
	public void setHasIns(String hasIns)
	{
		this.hasIns = hasIns;
	}

}