package nc.dhhs.nccss.acts.ecoa.beans;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Mallika Velagapudi
 * 
 */

public class CaseCourtOrder implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 275066820681975550L;

	private String				applicationId		= "";

	private String				applicantId			= "";

	private String				orderNm				= " ";

	private String				supportType			= " ";

	private double				orderAmount			= 0.00;

	private String				payFreq				= " ";

	private String				docketNm			= " ";

	private Date				orderEffDt;

	private String				county				= " ";

	private String				state				= "";

	private double				amtPastDue			= 0.00;

	private String				payorNm				= "";

	private String				recipientNm			= "";

	SimpleDateFormat			DATE_FORMAT			= new SimpleDateFormat("yyyy-MM-dd");
	
	NumberFormat				formatter			= new DecimalFormat("#0.00");

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
	 * @return the supportType
	 */
	public String getSupportType()
	{
		return supportType;
	}

	/**
	 * @param supportType
	 *            the supportType to set
	 */
	public void setSupportType(String supportType)
	{
		this.supportType = supportType;
	}

	/**
	 * @return the orderAmount
	 */
	public double getOrderAmount()
	{
		return orderAmount;
	}

	/**
	 * @param orderAmount
	 *            the orderAmount to set
	 */
	public void setOrderAmount(double orderAmount)
	{
		this.orderAmount = orderAmount;
	}

	public String getOrderAmountDisplay()

	{
		String orderAmountDis = "";

		if (orderAmount != 0.0) orderAmountDis = formatter.format(orderAmount);

		return orderAmountDis;
	}

	/**
	 * @return the payFreq
	 */
	public String getPayFreq()
	{
		return payFreq;
	}

	/**
	 * @param payFreq
	 *            the payFreq to set
	 */
	public void setPayFreq(String payFreq)
	{
		this.payFreq = payFreq;
	}

	/**
	 * @return the docketNm
	 */
	public String getDocketNm()
	{
		return docketNm;
	}

	/**
	 * @param docketNm
	 *            the docketNm to set
	 */
	public void setDocketNm(String docketNm)
	{
		this.docketNm = docketNm;
	}

	/**
	 * @return the orderEffDt
	 */
	public Date getOrderEffDt()
	{
		return orderEffDt;
	}

	public String getOrderEffDtStr()
	{
		if (orderEffDt == null) return null;
		else
			return DATE_FORMAT.format(orderEffDt).trim();
	}

	public String getOrderEffDtStrPg()
	{
		if (orderEffDt == null) return null;

		if ("01/01/0001".equals(DATE_FORMAT_SCREEN.format(orderEffDt).trim())) return "";
		else
			return DATE_FORMAT_SCREEN.format(orderEffDt).trim();
	}

	/**
	 * @param orderEffDt
	 *            the orderEffDt to set
	 */
	public void setOrderEffDt(Date orderEffDt)
	{
		this.orderEffDt = orderEffDt;
	}

	/**
	 * @return the county
	 */
	public String getCounty()
	{
		return county;
	}

	/**
	 * @param county
	 *            the county to set
	 */
	public void setCounty(String county)
	{
		this.county = county;
	}

	/**
	 * @return the state
	 */
	public String getState()
	{
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state)
	{
		this.state = state;
	}

	/**
	 * @return the amtPastDue
	 */
	public double getAmtPastDue()
	{
		return amtPastDue;
	}

	public String getAmtPastDueDisplay()

	{
		String AmtPastDueDis = "";

		if (amtPastDue != 0.0) AmtPastDueDis = formatter.format(amtPastDue);

		return AmtPastDueDis;
	}

	/**
	 * @param amtPastDue
	 *            the amtPastDue to set
	 */
	public void setAmtPastDue(double amtPastDue)
	{
		this.amtPastDue = amtPastDue;
	}

	/**
	 * @return the payorNm
	 */
	public String getPayorNm()
	{
		return payorNm;
	}

	/**
	 * @param payorNm
	 *            the payorNm to set
	 */
	public void setPayorNm(String payorNm)
	{
		this.payorNm = payorNm;
	}

	/**
	 * @return the recipientNm
	 */
	public String getRecipientNm()
	{
		return recipientNm;
	}

	/**
	 * @param recipientNm
	 *            the recipientNm to set
	 */
	public void setRecipientNm(String recipientNm)
	{
		this.recipientNm = recipientNm;
	}

	SimpleDateFormat DATE_FORMAT_SCREEN = new SimpleDateFormat("MM/dd/yyyy");

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

}