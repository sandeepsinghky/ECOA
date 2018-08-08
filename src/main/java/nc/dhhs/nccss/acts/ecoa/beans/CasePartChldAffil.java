package nc.dhhs.nccss.acts.ecoa.beans;

import java.io.Serializable;

import org.apache.log4j.Logger;

/**
 * @author Phani Konuru
 * 
 */

public class CasePartChldAffil implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 8112293790298385930L;

	protected final Logger		logger				= Logger.getLogger(CasePartChldAffil.class);

	private String				applicationId		= "";

	private String				applicantId			= "";

	private String				childId				= "";

	private String				caseRelshp			= "";

	private String				famiRelshp			= "";

	private String				chldLiveWith		= "";

	private int					noOfYears			= 0;

	private int					noOfMonths			= 0;

	private String				nameF				= "";

	private String				nameL				= "";

	private String				nameM				= "";

	/**
	 * @return the nameM
	 */
	public String getNameM()
	{
		return nameM;
	}

	/**
	 * @param nameM
	 *            the nameM to set
	 */
	public void setNameM(String nameM)
	{
		this.nameM = nameM;
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
	 * @return the childId
	 */
	public String getChildId()
	{
		return childId;
	}

	/**
	 * @param childId
	 *            the childId to set
	 */
	public void setChildId(String childId)
	{
		this.childId = childId;
	}

	/**
	 * @return the caseRelshp
	 */
	public String getCaseRelshp()
	{
		return caseRelshp;
	}

	/**
	 * @param caseRelshp
	 *            the caseRelshp to set
	 */
	public void setCaseRelshp(String caseRelshp)
	{
		this.caseRelshp = caseRelshp;
	}

	/**
	 * @return the famiRelshp
	 */
	public String getFamiRelshp()
	{
		return famiRelshp;
	}

	/**
	 * @param famiRelshp
	 *            the famiRelshp to set
	 */
	public void setFamiRelshp(String famiRelshp)
	{
		this.famiRelshp = famiRelshp;
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
	 * @return the noOfYears
	 */
	public int getNoOfYears()
	{
		return noOfYears;
	}

	/**
	 * @param noOfYears
	 *            the noOfYears to set
	 */
	public void setNoOfYears(int noOfYears)
	{
		this.noOfYears = noOfYears;
	}

	/**
	 * @return the noOfMonths
	 */
	public int getNoOfMonths()
	{
		return noOfMonths;
	}

	/**
	 * @param noOfMonths
	 *            the noOfMonths to set
	 */
	public void setNoOfMonths(int noOfMonths)
	{
		this.noOfMonths = noOfMonths;
	}

	/**
	 * @return the nameF
	 */
	public String getNameF()
	{
		return nameF;
	}

	/**
	 * @param nameF
	 *            the nameF to set
	 */
	public void setNameF(String nameF)
	{
		this.nameF = nameF;
	}

	/**
	 * @return the nameL
	 */
	public String getNameL()
	{
		return nameL;
	}

	/**
	 * @param nameL
	 *            the nameL to set
	 */
	public void setNameL(String nameL)
	{
		this.nameL = nameL;
	}

}
