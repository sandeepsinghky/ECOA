package nc.dhhs.nccss.acts.ecoa.beans;

import java.io.Serializable;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * @author Phani Konuru
 * 
 */

public class CaseApplication implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -400591535734667100L;

	protected final Logger		logger				= Logger.getLogger(CaseApplication.class);

	private String				applicationId		= "";

	private String				applicantId			= "";

	private String				firstName			= "";

	private String				lastName			= "";

	private String				middleName			= "";

	private String				ncId				= "";

	private String				emailId				= "";

	private Date				applCreateDt;

	private Date				applSubmitDt;

	private String				applStatus			= "";

	private String				applStatusDesc		= "";

	private String				applCounty			= "";

	private String				applCountyDesc		= "";

	private String				applNotes			= "";

	private Date				applEndDt;

	private Date				applCertSignDt;

	private Date				applCmpltDt;

	private String				applFeeRcvd			= "";

	private Date				applFeeRcvdDt;

	private String				applFeeRcvdBy		= "";

	private Date				lastUpdatDt;

	private Time				lastUpdateTm;

	private Date				applicantSignDt;

	private String				contactViaEmail		= "";

	private String				enforState			= "";

	private String				enforCountry		= "";

	private String				enforCompanyName	= "";

	SimpleDateFormat			DATE_FORMAT			= new SimpleDateFormat("yyyy-MM-dd");

	SimpleDateFormat			DATE_FORMAT_PAGE	= new SimpleDateFormat("MM/dd/yyyy");

	public CaseApplication()
	{
		logger.debug("IN: CaseApplication()");
	}

	/**
	 * @return
	 */
	public String getApplicationId()
	{
		return applicationId.trim();
	}

	/**
	 * @param applicationId
	 */
	public void setApplicationId(String applicationId)
	{
		this.applicationId = applicationId;
	}

	/**
	 * @return
	 */
	public String getApplicantId()
	{
		return applicantId.trim();
	}

	/**
	 * @param applicantId
	 */
	public void setApplicantId(String applicantId)
	{
		this.applicantId = applicantId;
	}

	/**
	 * @return
	 */
	public String getFirstName()
	{
		return firstName.trim();
	}

	/**
	 * @param firstName
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	/**
	 * @return
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * @param lastName
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	/**
	 * @return
	 */
	public String getMiddleName()
	{
		return middleName.trim();
	}

	/**
	 * @param middleName
	 */
	public void setMiddleName(String middleName)
	{
		this.middleName = middleName;
	}

	/**
	 * @return
	 */
	public String getNcId()
	{
		return ncId.trim();
	}

	/**
	 * @param ncId
	 */
	public void setNcId(String ncId)
	{
		this.ncId = ncId;
	}

	/**
	 * @return
	 */
	public String getEmailId()
	{
		return emailId.trim();
	}

	/**
	 * @param emailId
	 */
	public void setEmailId(String emailId)
	{
		this.emailId = emailId;
	}

	/**
	 * @return
	 */
	public Date getApplCreateDt()
	{
		return applCreateDt;
	}

	/**
	 * @return
	 */
	public String getApplCreateDtStr()
	{
		if (applCreateDt == null) return null;
		else
			return (DATE_FORMAT.format(applCreateDt)).trim();
	}

	/**
	 * @param applCreateDt
	 */
	public void setApplCreateDt(Date applCreateDt)
	{
		this.applCreateDt = applCreateDt;
	}

	/**
	 * @return
	 */
	public String getApplCreateDtPgStr()
	{
		if (applCreateDt == null) return DATE_FORMAT_PAGE.format(new Date()).trim();
		else
			return DATE_FORMAT_PAGE.format(applCreateDt).trim().equals(AppConstants.PAGE_DEFAULT_DATE) ? DATE_FORMAT_PAGE.format(new Date()).trim() : DATE_FORMAT_PAGE.format(applCreateDt).trim();
	}

	/**
	 * @return
	 */
	public Date getApplSubmitDt()
	{
		return applSubmitDt;
	}

	/**
	 * @return
	 */
	public String getApplSubmitDtStr()
	{
		if (applSubmitDt == null) return null;
		else
			return DATE_FORMAT.format(applSubmitDt).trim();
	}

	/**
	 * @return
	 */
	public String getApplSubmitDtPgStr()
	{
		if (applSubmitDt == null) return DATE_FORMAT_PAGE.format(new Date()).trim();
		else
			return DATE_FORMAT_PAGE.format(applSubmitDt).trim().equals(AppConstants.PAGE_DEFAULT_DATE) ? DATE_FORMAT_PAGE.format(new Date()).trim() : DATE_FORMAT_PAGE.format(applSubmitDt).trim();
	}

	/**
	 * @param applSubmitDt
	 */
	public void setApplSubmitDt(Date applSubmitDt)
	{
		this.applSubmitDt = applSubmitDt;
	}

	/**
	 * @return
	 */
	public String getApplStatus()
	{
		return applStatus.trim();
	}

	/**
	 * @param applStatus
	 */
	public void setApplStatus(String applStatus)
	{
		this.applStatus = applStatus;
	}

	/**
	 * @return the applStatusDesc
	 */
	public String getApplStatusDesc()
	{
		return applStatusDesc.trim();
	}

	/**
	 * @param applStatusDesc
	 *            the applStatusDesc to set
	 */
	public void setApplStatusDesc(String applStatusDesc)
	{
		this.applStatusDesc = applStatusDesc;
	}

	/**
	 * @return
	 */
	public String getApplCounty()
	{
		return applCounty.trim();
	}

	/**
	 * @param applCounty
	 */
	public void setApplCounty(String applCounty)
	{
		this.applCounty = applCounty;
	}

	/**
	 * @return the applCountyDesc
	 */
	public String getApplCountyDesc()
	{
		return applCountyDesc.trim();
	}

	/**
	 * @param applCountyDesc
	 *            the applCountyDesc to set
	 */
	public void setApplCountyDesc(String applCountyDesc)
	{
		this.applCountyDesc = applCountyDesc;
	}

	/**
	 * @return
	 */
	public String getApplNotes()
	{
		return applNotes.trim();
	}

	/**
	 * @param applNotes
	 */
	public void setApplNotes(String applNotes)
	{
		this.applNotes = applNotes;
	}

	/**
	 * @return
	 */
	public Date getApplEndDt()
	{
		return applEndDt;
	}

	/**
	 * @return
	 */
	public String getApplEndDtStr()
	{
		if (applEndDt == null) return null;
		else
			return DATE_FORMAT.format(applEndDt).trim();
	}

	/**
	 * @param applEndDt
	 */
	public void setApplEndDt(Date applEndDt)
	{
		this.applEndDt = applEndDt;
	}

	/**
	 * @return
	 */
	public Date getApplCertSignDt()
	{
		return applCertSignDt;
	}

	/**
	 * @return
	 */
	public String getApplCertSignDtStr()
	{
		if (applCertSignDt == null) return null;
		else
			return DATE_FORMAT.format(applCertSignDt).trim();
	}

	/**
	 * @param applCertSignDt
	 */
	public void setApplCertSignDt(Date applCertSignDt)
	{
		this.applCertSignDt = applCertSignDt;
	}

	/**
	 * @return
	 */
	public Date getApplCmpltDt()
	{
		return applCmpltDt;
	}

	/**
	 * @return
	 */
	public String getApplCmpltDtStr()
	{
		if (applCmpltDt == null) return null;
		else
			return DATE_FORMAT.format(applCmpltDt).trim();
	}

	/**
	 * @param applCmpltDt
	 */
	public void setApplCmpltDt(Date applCmpltDt)
	{
		this.applCmpltDt = applCmpltDt;
	}

	/**
	 * @return
	 */
	public String getApplFeeRcvd()
	{
		return applFeeRcvd.trim();
	}

	/**
	 * @param applFeeRcvd
	 */
	public void setApplFeeRcvd(String applFeeRcvd)
	{
		this.applFeeRcvd = applFeeRcvd;
	}

	/**
	 * @return
	 */
	public Date getApplFeeRcvdDt()
	{
		return applFeeRcvdDt;
	}

	/**
	 * @return
	 */
	public String getApplFeeRcvdDtStr()
	{
		if (applFeeRcvdDt == null) return null;
		else
			return DATE_FORMAT.format(applFeeRcvdDt).trim();
	}

	/**
	 * @param feeRcvdDt
	 */
	public void setApplFeeRcvdDt(Date feeRcvdDt)
	{
		this.applFeeRcvdDt = feeRcvdDt;
	}

	/**
	 * @return
	 */
	public String getApplFeeRcvdBy()
	{
		return applFeeRcvdBy.trim();
	}

	/**
	 * @param applFeeRcvdBy
	 */
	public void setApplFeeRcvdBy(String applFeeRcvdBy)
	{
		this.applFeeRcvdBy = applFeeRcvdBy;
	}

	/**
	 * @return
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
	 * @return
	 */
	public Time getLastUpdateTm()
	{
		return lastUpdateTm;
	}

	/**
	 * @param lastUpdateTm
	 */
	public void setLastUpdateTm(Time lastUpdateTm)
	{
		this.lastUpdateTm = lastUpdateTm;
	}

	/**
	 * @return
	 */
	public String getLastUpdateTmStr()
	{
		if (lastUpdateTm == null) return null;
		else
			return lastUpdateTm.toString().trim();
	}

	/**
	 * @return
	 */
	public Date getApplicantSignDt()
	{
		return applicantSignDt;
	}

	/**
	 * @return
	 */
	public String getApplicantSignDtStr()
	{
		if (applicantSignDt == null) return null;
		else
			return DATE_FORMAT.format(applicantSignDt).trim();
	}

	public String getApplicantSignDtPgStr()
	{
		if (applicantSignDt == null) return DATE_FORMAT_PAGE.format(new Date()).trim();
		else
			return DATE_FORMAT_PAGE.format(applicantSignDt).trim().equals(AppConstants.PAGE_DEFAULT_DATE) ? DATE_FORMAT_PAGE.format(new Date()).trim() : DATE_FORMAT_PAGE.format(applicantSignDt).trim();

	}

	/**
	 * @param applicantSignDt
	 */
	public void setApplicantSignDt(Date applicantSignDt)
	{
		this.applicantSignDt = applicantSignDt;
	}

	public boolean isApplSbmtd()
	{
		boolean result = false;
		//if (getApplSubmitDtStr() != null && !getApplSubmitDtStr().equals("") && !getApplSubmitDtStr().equals(AppConstants.DEFAULT_DATE))
		if (getApplStatus() != null && (getApplStatus().equals(AppConstants.APP_STATUS_SUBMIT) || getApplStatus().equals(AppConstants.APP_STATUS_CMPLT)) && getApplSubmitDtStr() != null && !getApplSubmitDtStr().equals("") && !getApplSubmitDtStr().equals(AppConstants.DEFAULT_DATE))
		{
			result = true;
		}
		return result;
	}
	
	public boolean isApplCmplt()
	{
		boolean result = false;
		if (getApplStatus() != null && getApplStatus().equals(AppConstants.APP_STATUS_CMPLT) /*&& getApplCmpltDtStr() != null && !getApplCmpltDtStr().equals("") && !getApplCmpltDtStr().equals(AppConstants.DEFAULT_DATE)*/)
		{
			result = true;
		}
		return result;
	}

	/**
	 * @return the contactViaEmail
	 */
	public String getContactViaEmail()
	{
		return contactViaEmail;
	}

	/**
	 * @param contactViaEmail
	 *            the contactViaEmail to set
	 */
	public void setContactViaEmail(String contactViaEmail)
	{
		this.contactViaEmail = contactViaEmail;
	}

	/**
	 * @return the enforState
	 */
	public String getEnforState()
	{
		return enforState;
	}

	/**
	 * @param enforState
	 *            the enforState to set
	 */
	public void setEnforState(String enforState)
	{
		this.enforState = enforState;
	}

	/**
	 * @return the enforCountry
	 */
	public String getEnforCountry()
	{
		return enforCountry;
	}

	/**
	 * @param enforCountry
	 *            the enforCountry to set
	 */
	public void setEnforCountry(String enforCountry)
	{
		this.enforCountry = enforCountry;
	}

	/**
	 * @return the enforCompanyName
	 */
	public String getEnforCompanyName()
	{
		return enforCompanyName;
	}

	/**
	 * @param enforCompanyName
	 *            the enforCompanyName to set
	 */
	public void setEnforCompanyName(String enforCompanyName)
	{
		this.enforCompanyName = enforCompanyName;
	}

}