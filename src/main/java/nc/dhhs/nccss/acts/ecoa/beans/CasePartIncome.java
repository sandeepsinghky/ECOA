package nc.dhhs.nccss.acts.ecoa.beans;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.apache.log4j.Logger;

/**
 * @author Phani Konuru
 * 
 */

public class CasePartIncome implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -1934292004605739003L;

	protected final Logger		logger				= Logger.getLogger(CasePartIncome.class);

	private String				applicationId		= "";

	private String				applicantId			= "";

	private String				incomeSrc			= "";

	private String				incomeSrcDesc		= "";

	private double				incomeAmt			= 0.00;

	NumberFormat				formatter			= new DecimalFormat("#0.00");

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
	 * @return the incomeSrc
	 */
	public String getIncomeSrc()
	{
		return incomeSrc;
	}

	/**
	 * @param incomeSrc
	 *            the incomeSrc to set
	 */
	public void setIncomeSrc(String incomeSrc)
	{
		this.incomeSrc = incomeSrc;
	}

	/**
	 * @return the incomeAmt
	 */
	public double getIncomeAmt()
	{
		return incomeAmt;
	}

	public String getIncomeAmtStr()
	{
		return formatter.format(incomeAmt);
	}

	/**
	 * @param incomeAmt
	 *            the incomeAmt to set
	 */
	public void setIncomeAmt(double incomeAmt)
	{
		this.incomeAmt = incomeAmt;
	}

	/**
	 * @return the incomeSrcDesc
	 */
	public String getIncomeSrcDesc()
	{
		return incomeSrcDesc;
	}

	/**
	 * @param incomeSrcDesc
	 *            the incomeSrcDesc to set
	 */
	public void setIncomeSrcDesc(String incomeSrcDesc)
	{
		this.incomeSrcDesc = incomeSrcDesc;
	}

}
