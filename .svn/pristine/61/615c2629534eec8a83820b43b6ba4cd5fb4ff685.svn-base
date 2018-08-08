/**
 * @author Mallika Velagapudi
 *
 */

package nc.dhhs.nccss.acts.ecoa.beans;

import java.text.DecimalFormat;

/**
 * A JavaBean Class that helps store the child support amounts and arrears
 * amounts. This class is used an intermediate storage while building the
 * Obligation Summary data.
 **/
@SuppressWarnings("serial")
public class Obligation implements java.io.Serializable
{
	private double	csupAmt		= 0.0;
	private String	csupFreq	= "";
	private String	csupDate	= "";
	private double	csupTotal	= 0.0;

	private double	arrsAmt		= 0.0;
	private String	arrsFreq	= "";
	private String	arrsDate	= "";
	private double	arrsTotal	= 0.0;

	DecimalFormat	pf			= new DecimalFormat("###,###,##0.00");

	public String decimalFormat(double value)
	{
		return (pf.format(value));

	}

	/**
	 * @return the csupAmt
	 */
	public double getCsupAmt()
	{
		return csupAmt;
	}

	/**
	 * @param csupAmt
	 *            the csupAmt to set
	 */
	public void setCsupAmt(double csupAmt)
	{
		this.csupAmt = csupAmt;
	}

	/**
	 * @return the csupFreq
	 */
	public String getCsupFreq()
	{
		return csupFreq;
	}

	/**
	 * @param csupFreq
	 *            the csupFreq to set
	 */
	public void setCsupFreq(String csupFreq)
	{
		this.csupFreq = csupFreq;
	}

	/**
	 * @return the csupDate
	 */
	public String getCsupDate()
	{
		return csupDate;
	}

	/**
	 * @param csupDate
	 *            the csupDate to set
	 */
	public void setCsupDate(String csupDate)
	{
		this.csupDate = csupDate;
	}

	/**
	 * @return the csupTotal
	 */
	public double getCsupTotal()
	{
		return csupTotal;
	}

	/**
	 * @param csupTotal
	 *            the csupTotal to set
	 */
	public void setCsupTotal(double csupTotal)
	{
		this.csupTotal = csupTotal;
	}

	/**
	 * @return the arrsAmt
	 */
	public double getArrsAmt()
	{
		return arrsAmt;
	}

	/**
	 * @param arrsAmt
	 *            the arrsAmt to set
	 */
	public void setArrsAmt(double arrsAmt)
	{
		this.arrsAmt = arrsAmt;
	}

	/**
	 * @return the arrsFreq
	 */
	public String getArrsFreq()
	{
		return arrsFreq;
	}

	/**
	 * @param arrsFreq
	 *            the arrsFreq to set
	 */
	public void setArrsFreq(String arrsFreq)
	{
		this.arrsFreq = arrsFreq;
	}

	/**
	 * @return the arrsDate
	 */
	public String getArrsDate()
	{
		return arrsDate;
	}

	/**
	 * @param arrsDate
	 *            the arrsDate to set
	 */
	public void setArrsDate(String arrsDate)
	{
		this.arrsDate = arrsDate;
	}

	/**
	 * @return the arrsTotal
	 */
	public double getArrsTotal()
	{
		return arrsTotal;
	}

	/**
	 * @param arrsTotal
	 *            the arrsTotal to set
	 */
	public void setArrsTotal(double arrsTotal)
	{
		this.arrsTotal = arrsTotal;
	}

}
