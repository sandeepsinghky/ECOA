package nc.dhhs.nccss.acts.ecoa.beans;

/**
 * A JavaBean Class that helps store the Payment History entries This class is
 * used by the payment history option to display the entries.
 * 
 * @author: Mallika Velagapudi
 */
public class PaymentBean implements java.io.Serializable
{

	private static final long	serialVersionUID	= 1L;
	private String				payorMpi;
	private String				tranDate;
	private String				amtApplied;
	private String				amtRemaining;
	private boolean				applied;
	private String[]			payeeCase			= { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" };
	private String[]			payeeMpi			= { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" };
	private String[]			amtDisbursed		= { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" };
	private String[]			payeeName			= { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" };

	/**
	 * PaymentBean constructor comment.
	 */
	public PaymentBean()
	{
		for (int i = 0; i < 20; i++)
		{
			payeeCase[i] = "";
			payeeMpi[i] = "";
			amtDisbursed[i] = "";
			payeeName[i] = "";
		}
	}

	/**
	 * @return java.lang.String
	 */
	public java.lang.String getAmtApplied()
	{
		return amtApplied;
	}

	/**
	 * 
	 * @return java.lang.String[]
	 */
	public java.lang.String[] getAmtDisbursed()
	{
		return amtDisbursed;
	}

	/**
	 * @return java.lang.String
	 */
	public String getAmtDisbursed(int indx)
	{
		return amtDisbursed[indx];
	}

	/**
	 * @return java.lang.String
	 */
	public java.lang.String getAmtRemaining()
	{
		return amtRemaining;
	}

	/**
	 * @return java.lang.String[]
	 */
	public java.lang.String[] getPayeeCase()
	{
		return payeeCase;
	}

	/**
	 * 
	 * 
	 * @return java.lang.String[]
	 */
	public java.lang.String[] getPayeeMpi()
	{
		return payeeMpi;
	}

	/**
	 * @return java.lang.String[]
	 */
	public java.lang.String[] getPayeeName()
	{
		return payeeName;
	}

	/**
	 * @return java.lang.String
	 * @param indx
	 *            int
	 */
	public String getPayeeName(int indx)
	{
		return payeeName[indx];
	}

	/**
	 * @return java.lang.String
	 */
	public java.lang.String getPayorMpi()
	{
		return payorMpi;
	}

	/**
	 * @return java.lang.String
	 */
	public java.lang.String getTranDate()
	{
		return tranDate;
	}

	/**
	 * @return boolean
	 */
	public boolean isApplied()
	{
		return applied;
	}

	/**
	 * @param newAmtApplied
	 *            java.lang.String
	 */
	public void setAmtApplied(java.lang.String newAmtApplied)
	{
		amtApplied = newAmtApplied;
	}

	/**
	 * @param newAmtDisbursed
	 *            java.lang.String[]
	 */
	public void setAmtDisbursed(java.lang.String[] newAmtDisbursed)
	{
		amtDisbursed = newAmtDisbursed;
	}

	/**
	 * @param indx
	 *            int
	 * @param amt
	 *            java.lang.String
	 */
	public void setAmtDisbursed(int indx, String amt)
	{

		if (indx <= amtDisbursed.length)
		{
			amtDisbursed[indx] = amt;
		}
	}

	/**
	 * @param newAmtRemaining
	 *            java.lang.String
	 */
	public void setAmtRemaining(java.lang.String newAmtRemaining)
	{
		amtRemaining = newAmtRemaining;
	}

	/**
	 * @param newApplied
	 *            boolean
	 */
	public void setApplied(boolean newApplied)
	{
		applied = newApplied;
	}

	/**
	 * @param newPayeeCase
	 *            java.lang.String[]
	 */
	public void setPayeeCase(java.lang.String[] newPayeeCase)
	{
		payeeCase = newPayeeCase;
	}

	/**
	 * 
	 * @param indx
	 *            int
	 * @param caseID
	 *            java.lang.String
	 */
	public void setPayeeCase(int indx, String caseID)
	{
		if (indx <= payeeCase.length)
		{
			payeeCase[indx] = caseID;
		}

	}

	/**
	 * @param newPayeeMpi
	 *            java.lang.String[]
	 */
	public void setPayeeMpi(java.lang.String[] newPayeeMpi)
	{
		payeeMpi = newPayeeMpi;
	}

	/**
	 * @param indx
	 *            int
	 * @param mpi
	 *            java.lang.String
	 */
	public void setPayeeMpi(int indx, String mpi)
	{
		if (indx <= payeeMpi.length)
		{
			payeeMpi[indx] = mpi;
		}

	}

	/**
	 * @param newPayeeName
	 *            java.lang.String[]
	 */
	public void setPayeeName(java.lang.String[] newPayeeName)
	{
		payeeName = newPayeeName;
	}

	/**
	 * @param indx
	 *            int
	 * @param name
	 *            java.lang.String
	 */
	public void setPayeeName(int indx, String name)
	{

		if (indx <= payeeName.length)
		{
			payeeName[indx] = name;
		}

	}

	/**
	 * @param newPayorMpi
	 *            java.lang.String
	 */
	public void setPayorMpi(java.lang.String newPayorMpi)
	{
		payorMpi = newPayorMpi;
	}

	/**
	 * @param newTranDate
	 *            java.lang.String
	 */
	public void setTranDate(java.lang.String newTranDate)
	{
		tranDate = newTranDate;
	}
}
