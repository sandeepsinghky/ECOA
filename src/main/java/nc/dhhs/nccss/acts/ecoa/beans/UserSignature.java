/**
 * 
 */
package nc.dhhs.nccss.acts.ecoa.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Vijay Peddapalli
 *
 */
public class UserSignature implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -2044267502504531075L;

	private String				signId				= "";

	private String				signType			= "";

	private String				nmSignF				= "";

	private String				nmSignMI			= "";

	private String				nmSignL				= "";

	private String				hasChecked			= "";

	private Date				dateSigned;

	SimpleDateFormat			DATE_FORMAT			= new SimpleDateFormat("yyyy-MM-dd");

	SimpleDateFormat			DATE_FORMAT_PAGE	= new SimpleDateFormat("MM/dd/yyyy");

	public UserSignature()
	{

	}

	/**
	 * @return the signId
	 */
	public String getSignId()
	{
		return signId;
	}

	/**
	 * @param signId
	 *            the signId to set
	 */
	public void setSignId(String signId)
	{
		this.signId = signId.trim();
	}

	/**
	 * @return the signType
	 */
	public String getSignType()
	{
		return signType;
	}

	/**
	 * @param signType
	 *            the signType to set
	 */
	public void setSignType(String signType)
	{
		this.signType = signType.trim();
	}

	/**
	 * @return the nmSignF
	 */
	public String getNmSignF()
	{
		return nmSignF;
	}

	/**
	 * @param nmSignF
	 *            the nmSignF to set
	 */
	public void setNmSignF(String nmSignF)
	{
		this.nmSignF = nmSignF.trim();
	}

	/**
	 * @return the nmSignMI
	 */
	public String getNmSignMI()
	{
		return nmSignMI;
	}

	/**
	 * @param nmSignMI
	 *            the nmSignMI to set
	 */
	public void setNmSignMI(String nmSignMI)
	{
		this.nmSignMI = nmSignMI.trim();
	}

	/**
	 * @return the nmSignL
	 */
	public String getNmSignL()
	{
		return nmSignL;
	}

	/**
	 * @param nmSignL
	 *            the nmSignL to set
	 */
	public void setNmSignL(String nmSignL)
	{
		this.nmSignL = nmSignL.trim();
	}

	/**
	 * @return the dateSigned
	 */
	public Date getDateSigned()
	{
		return dateSigned;
	}

	/**
	 * @param dateSigned
	 *            the dateSigned to set
	 */
	public void setDateSigned(Date dateSigned)
	{
		this.dateSigned = dateSigned;
	}

	/**
	 * @return
	 */
	public String getDateSignedDtStr()
	{
		if (dateSigned == null) return null;
		else
			return DATE_FORMAT_PAGE.format(dateSigned).trim();
	}

	/**
	 * @return the hasChecked
	 */
	public String getHasChecked()
	{
		return hasChecked;
	}

	/**
	 * @param hasChecked
	 *            the hasChecked to set
	 */
	public void setHasChecked(String hasChecked)
	{
		this.hasChecked = hasChecked;
	}

}
