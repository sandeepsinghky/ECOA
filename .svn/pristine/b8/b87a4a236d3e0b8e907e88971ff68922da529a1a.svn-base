package nc.dhhs.nccss.acts.ecoa.beans;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;

import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * A JavaBean Class that helps store the Journal Tran Table entries This class
 * is used an intermediate storage while building the payment history data
 * 
 * @author:Mallika Velagapudi
 */
public class JournalTranBean implements java.io.Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private BigDecimal			amountApplied;
	private Date				dateTran;
	private short				nbMonth;
	private int					nbBatch;
	private short				nbItem;
	private BigDecimal			amountRemaining;

	SimpleDateFormat			DATE_FORMAT			= new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat			DATE_FORMAT_SCREEN	= new SimpleDateFormat("MM/dd/yyyy");

	/**
	 * JournalTranBean constructor comment.
	 */
	public JournalTranBean()
	{
		super();
	}

	/**
	 * Standard Getter Methods for the JournalTranBean Creation date: (4/30/2001
	 * 10:31:54 PM)
	 * 
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getAmountApplied()
	{
		return amountApplied;
	}

	/**
	 * Insert the method's description here. Creation date: (10/26/2001 1:18:03
	 * PM)
	 * 
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getAmountRemaining()
	{
		return amountRemaining;
	}

	/**
	 * Standard Getter Methods for the JournalTranBean Creation date: (4/30/2001
	 * 10:31:54 PM)
	 * 
	 * @return java.sql.Date
	 */
	public java.sql.Date getDateTran()
	{
		return dateTran;
	}

	public String getDateTranStrPg()
	{
		if (dateTran == null) return "";
		else
			return DATE_FORMAT_SCREEN.format(dateTran).trim().equals(AppConstants.PAGE_DEFAULT_DATE) ? "" : DATE_FORMAT_SCREEN.format(dateTran).trim();
	}

	public String getDateTranStr()
	{
		if (dateTran == null) return null;
		else
			return DATE_FORMAT.format(dateTran).trim();
	}

	/**
	 * Standard Getter Methods for the JournalTranBean Creation date: (4/30/2001
	 * 10:31:54 PM)
	 * 
	 * @return int
	 */
	public int getNbBatch()
	{
		return nbBatch;
	}

	/**
	 * Standard Getter Methods for the JournalTranBean Creation date: (4/30/2001
	 * 10:31:54 PM)
	 * 
	 * @return short
	 */
	public short getNbItem()
	{
		return nbItem;
	}

	/**
	 * Standard Getter Methods for the JournalTranBean Creation date: (4/30/2001
	 * 10:31:54 PM)
	 * 
	 * @return short
	 */
	public short getNbMonth()
	{
		return nbMonth;
	}

	/**
	 * Standard Setter Methods for the JournalTranBean Creation date: (4/30/2001
	 * 10:31:54 PM)
	 * 
	 * @param newAmountApplied
	 *            java.math.BigDecimal
	 */
	public void setAmountApplied(java.math.BigDecimal newAmountApplied)
	{
		amountApplied = newAmountApplied;
	}

	/**
	 * Insert the method's description here. Creation date: (10/26/2001 1:18:03
	 * PM)
	 * 
	 * @param newAmountRemaining
	 *            java.math.BigDecimal
	 */
	public void setAmountRemaining(java.math.BigDecimal newAmountRemaining)
	{
		amountRemaining = newAmountRemaining;
	}

	/**
	 * Standard Setter Methods for the JournalTranBean Creation date: (4/30/2001
	 * 10:31:54 PM)
	 * 
	 * @param newDateTran
	 *            java.sql.Date
	 */
	public void setDateTran(java.sql.Date newDateTran)
	{
		dateTran = newDateTran;
	}

	/**
	 * Standard Setter Methods for the JournalTranBean Creation date: (4/30/2001
	 * 10:31:54 PM)
	 * 
	 * @param newNbBatch
	 *            int
	 */
	public void setNbBatch(int newNbBatch)
	{
		nbBatch = newNbBatch;
	}

	/**
	 * Standard Setter Methods for the JournalTranBean Creation date: (4/30/2001
	 * 10:31:54 PM)
	 * 
	 * @param newNbItem
	 *            short
	 */
	public void setNbItem(short newNbItem)
	{
		nbItem = newNbItem;
	}

	/**
	 * Standard Setter Methods for the JournalTranBean Creation date: (4/30/2001
	 * 10:31:54 PM)
	 * 
	 * @param newNbMonth
	 *            short
	 */
	public void setNbMonth(short newNbMonth)
	{
		nbMonth = newNbMonth;
	}
}
