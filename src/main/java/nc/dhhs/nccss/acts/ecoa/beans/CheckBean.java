
package nc.dhhs.nccss.acts.ecoa.beans;

import java.text.SimpleDateFormat;
import java.util.Date;

import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * A JavaBean Class that helps store the checks that were issued to the Child
 * Support Participant.
 * 
 * @author: Mallika Velagapudi
 */
public class CheckBean implements java.io.Serializable
{

	private static final long	serialVersionUID	= 1L;
	private String				checkMpi			= "";
	private String				checkAmount			= "";
	private Date				checkDate;
	private String				checkAccount		= "";
	private String				checkName			= "";
	private boolean				checkDeposit		= false;
	private boolean				debitCardDeposit	= false;
	private String				checkNumber			= "";
	private Date				checkCreditDate;
	SimpleDateFormat			DATE_FORMAT			= new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat			DATE_FORMAT_SCREEN	= new SimpleDateFormat("MM/dd/yyyy");

	/**
	 * @return the checkMpi
	 */
	public String getCheckMpi()
	{
		return checkMpi;
	}

	/**
	 * @param checkMpi
	 *            the checkMpi to set
	 */
	public void setCheckMpi(String checkMpi)
	{
		this.checkMpi = checkMpi;
	}

	/**
	 * @return the checkAmount
	 */
	public String getCheckAmount()
	{
		return checkAmount;
	}

	/**
	 * @param checkAmount
	 */
	public void setCheckAmount(String checkAmount)
	{
		this.checkAmount = checkAmount;
	}

	/**
	 * @return the checkAccount
	 */
	public String getCheckAccount()
	{
		return checkAccount;
	}

	/**
	 * @param checkAccount
	 * 
	 */
	public void setCheckAccount(String checkAccount)
	{
		this.checkAccount = checkAccount;
	}

	/**
	 * @return the checkName
	 */
	public String getCheckName()
	{
		return checkName;
	}

	/**
	 * @param checkName
	 *            the checkName to set
	 */
	public void setCheckName(String checkName)
	{
		this.checkName = checkName;
	}

	/**
	 * @return the checkDeposit
	 */
	public boolean isCheckDeposit()
	{
		return checkDeposit;
	}

	/**
	 * @param checkDeposit
	 *            the checkDeposit to set
	 */
	public void setCheckDeposit(boolean checkDeposit)
	{
		this.checkDeposit = checkDeposit;
	}

	/**
	 * @return the debitCardDeposit
	 */
	public boolean isDebitCardDeposit()
	{
		return debitCardDeposit;
	}

	/**
	 * @param debitCardDeposit
	 */
	public void setDebitCardDeposit(boolean debitCardDeposit)
	{
		this.debitCardDeposit = debitCardDeposit;
	}

	/**
	 * @return the checkNumber
	 */
	public String getCheckNumber()
	{
		return checkNumber;
	}

	/**
	 * @param checkNumber
	 *            the checkNumber to set
	 */
	public void setCheckNumber(String checkNumber)
	{
		this.checkNumber = checkNumber;
	}

	/**
	 * @return the checkDate
	 */
	public Date getCheckDate()
	{
		return checkDate;
	}

	/**
	 * @param checkDate
	 *            the checkDate to set
	 */
	public void setCheckDate(Date checkDate)
	{
		this.checkDate = checkDate;
	}

	public String getCheckDateStrPg()
	{
		if (checkDate == null) return "";
		else
			return DATE_FORMAT_SCREEN.format(checkDate).trim().equals(AppConstants.PAGE_DEFAULT_DATE) ? "" : DATE_FORMAT_SCREEN.format(checkDate).trim();
	}

	public String getCheckDateStr()
	{
		if (checkDate == null) return null;
		else
			return DATE_FORMAT.format(checkDate).trim();
	}

	/**
	 * @return the checkCreditDate
	 */
	public Date getCheckCreditDate()
	{
		return checkCreditDate;
	}

	/**
	 * @param checkCreditDate
	 *            the checkCreditDate to set
	 */
	public void setCheckCreditDate(Date checkCreditDate)
	{
		this.checkCreditDate = checkCreditDate;
	}

	public String getCheckCreditDateStrPg()
	{
		if (checkCreditDate == null) return "";
		else
			return DATE_FORMAT_SCREEN.format(checkCreditDate).trim().equals(AppConstants.PAGE_DEFAULT_DATE) ? "" : DATE_FORMAT_SCREEN.format(checkCreditDate).trim();
	}

	public String getCheckCreditDateStr()
	{
		if (checkCreditDate == null) return null;
		else
			return DATE_FORMAT.format(checkCreditDate).trim();
	}

	/**
	 * @return the checkCreditDate
	 */

}
