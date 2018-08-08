package nc.dhhs.nccss.acts.ecoa.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;

/**
 * @author Mallika Velagapudi
 * 
 */

public class Vehicle implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 275066520681675550L;

	private String				applicationId		= "";

	private String				applicantId			= "";

	private String				make				= " ";

	private String				model				= " ";

	private String				year				= " ";

	SimpleDateFormat			DATE_FORMAT			= new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * @return the applicationId
	 */
	public String getApplicationId()
	{
		return applicationId;
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
	 * @param applicationId
	 *            the applicationId to set
	 */
	public void setApplicationId(String applicationId)
	{
		this.applicationId = applicationId;
	}

	/**
	 * @return the make
	 */
	public String getMake()
	{
		return make;
	}

	/**
	 * @param make
	 *            the make to set
	 */
	public void setMake(String make)
	{
		this.make = make;
	}

	/**
	 * @return the model
	 */
	public String getModel()
	{
		return model;
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel(String model)
	{
		this.model = model;
	}

	/**
	 * @return the year
	 */
	public String getYear()
	{
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(String year)
	{
		this.year = year;
	}

}