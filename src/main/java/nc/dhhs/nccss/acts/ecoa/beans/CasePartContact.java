package nc.dhhs.nccss.acts.ecoa.beans;

import java.io.Serializable;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Phani Konuru
 * 
 */

public class CasePartContact implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1373642615195021353L;

	private String				applicationId		= "";

	private String				applicantId			= "";

	private String				contactType			= "";

	private String				contactInfo			= "";

	private String				famRelshp			= "";

	private Date				createDt;

	private Date				lastUpdatDt;

	private Time				lastUpdateTm;

	SimpleDateFormat			DATE_FORMAT			= new SimpleDateFormat("yyyy-MM-dd");

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
	 * @return the contactType
	 */
	public String getContactType()
	{
		return contactType;
	}

	/**
	 * @param contactType
	 *            the contactType to set
	 */
	public void setContactType(String contactType)
	{
		this.contactType = contactType;
	}

	/**
	 * @return the contactInfo
	 */
	public String getContactInfo()
	{
		return contactInfo;
	}

	/**
	 * @param contactInfo
	 *            the contactInfo to set
	 */
	public void setContactInfo(String contactInfo)
	{
		this.contactInfo = contactInfo;
	}

	/**
	 * @return the famRelshp
	 */
	public String getFamRelshp()
	{
		return famRelshp;
	}

	/**
	 * @param famRelshp
	 *            the famRelshp to set
	 */
	public void setFamRelshp(String famRelshp)
	{
		this.famRelshp = famRelshp;
	}

	/**
	 * @return the createDt
	 */
	public Date getCreateDt()
	{
		return createDt;
	}

	/**
	 * @return
	 */
	public String getCreateDtStr()
	{
		if (createDt == null) return null;
		else
			return DATE_FORMAT.format(createDt).trim();
	}

	/**
	 * @param createDt
	 *            the createDt to set
	 */
	public void setCreateDt(Date createDt)
	{
		this.createDt = createDt;
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
	 *            the lastUpdatDt to set
	 */
	public void setLastUpdatDt(Date lastUpdatDt)
	{
		this.lastUpdatDt = lastUpdatDt;
	}

	/**
	 * @return the lastUpdateTm
	 */
	public Time getLastUpdateTm()
	{
		return lastUpdateTm;
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
	 * @param lastUpdateTm
	 *            the lastUpdateTm to set
	 */
	public void setLastUpdateTm(Time lastUpdateTm)
	{
		this.lastUpdateTm = lastUpdateTm;
	}

}