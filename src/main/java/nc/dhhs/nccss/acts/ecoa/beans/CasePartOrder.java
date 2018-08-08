package nc.dhhs.nccss.acts.ecoa.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;

/**
 * @author Mallika Velagapudi
 * 
 */

public class CasePartOrder implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 275066820681675550L;

	private String				applicationId		= "";

	private String				applicantId			= "";

	private String				orderNm				= " ";

	private String				childApplicantId	= " ";

	SimpleDateFormat			DATE_FORMAT			= new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * @return the applicationId
	 */
	public String getApplicationId()
	{
		return applicationId;
	}

	/**
	 * @return the childApplicantId
	 */
	public String getChildApplicantId()
	{
		return childApplicantId;
	}

	/**
	 * @param childApplicantId
	 *            the childApplicantId to set
	 */
	public void setChildApplicantId(String childApplicantId)
	{
		this.childApplicantId = childApplicantId;
	}

	/**
	 * @return the orderNm
	 */
	public String getOrderNm()
	{
		return orderNm;
	}

	/**
	 * @param orderNm
	 *            the orderNm to set
	 */
	public void setOrderNm(String orderNm)
	{
		this.orderNm = orderNm;
	}

	/**
	 * @return the applicantChildId
	 */

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

}