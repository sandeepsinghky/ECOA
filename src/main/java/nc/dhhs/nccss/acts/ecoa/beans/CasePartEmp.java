package nc.dhhs.nccss.acts.ecoa.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * @author Phani Konuru
 * 
 */

public class CasePartEmp implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 5011556403324270465L;

	private String				applicationId		= "";

	private String				applicantId			= "";

	private String				thirdPartyId		= "";

	private Date				emplmEndDt			= null;

	private String				emplmEndReasn		= "";

	private String				ocupation			= "";

	private ThirdParty			thirdPartyInfo		= new ThirdParty();

	SimpleDateFormat			DATE_FORMAT			= new SimpleDateFormat("yyyy-MM-dd");

	SimpleDateFormat			DATE_FORMAT_PAGE	= new SimpleDateFormat("MM/dd/yyyy");

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
	 * @return the thirdPartyId
	 */
	public String getThirdPartyId()
	{
		return thirdPartyId;
	}

	/**
	 * @param thirdPartyId
	 *            the thirdPartyId to set
	 */
	public void setThirdPartyId(String thirdPartyId)
	{
		this.thirdPartyId = thirdPartyId;
	}

	/**
	 * @return the emplmEndDt
	 */
	public Date getEmplmEndDt()
	{
		return emplmEndDt;
	}

	/**
	 * @param emplmEndDt
	 *            the emplmEndDt to set
	 */
	public void setEmplmEndDt(Date emplmEndDt)
	{
		this.emplmEndDt = emplmEndDt;
	}

	public void setEmplmEndDt(String emplmEndDt)
	{
		try
		{
			this.emplmEndDt = DATE_FORMAT_PAGE.parse(emplmEndDt);
		}
		catch (Exception e)
		{
		}
	}

	/**
	 * @return
	 */
	public String getEmplmEndDtStr()
	{
		if (emplmEndDt == null || emplmEndDt.equals("")) return null;
		else
			return DATE_FORMAT.format(emplmEndDt).trim();
	}

	/**
	 * @return
	 */
	public String getEmplmEndDtPgStr()
	{
		if (emplmEndDt == null || emplmEndDt.equals("")) return "";
		else
			return DATE_FORMAT_PAGE.format(emplmEndDt).trim().equals(AppConstants.PAGE_DEFAULT_DATE) ? "" : DATE_FORMAT_PAGE.format(emplmEndDt).trim();
	}

	/**
	 * @return the emplmEndRsn
	 */
	public String getEmplmEndReasn()
	{
		return emplmEndReasn;
	}

	/**
	 * @param emplmEndRsn
	 *            the emplmEndRsn to set
	 */
	public void setEmplmEndReasn(String emplmEndReasn)
	{
		this.emplmEndReasn = emplmEndReasn;
	}

	/**
	 * @return the ocuupation
	 */
	public String getOcupation()
	{
		return ocupation;
	}

	/**
	 * @param ocuupation
	 *            the ocuupation to set
	 */
	public void setOcupation(String ocupation)
	{
		this.ocupation = ocupation;
	}

	/**
	 * @return the thirdPartyInfo
	 */
	public ThirdParty getThirdPartyInfo()
	{
		return thirdPartyInfo;
	}

	/**
	 * @param thirdPartyInfo
	 *            the thirdPartyInfo to set
	 */
	public void setThirdPartyInfo(ThirdParty thirdPartyInfo)
	{
		this.thirdPartyInfo = thirdPartyInfo;
	}

}