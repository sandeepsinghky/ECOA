
package nc.dhhs.nccss.acts.ecoa.beans;

import java.text.SimpleDateFormat;

/**
 * A JavaBean Class that helps store the Child Support Participant's case
 * specific information.
 * 
 * @author: Mallika Velagapudi
 */
public class ParentCaseInfo implements java.io.Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private String				ivdCase				= "";

	private String				partType			= "";

	private String				partStatus			= "";

	private String				caseType			= "";

	private String				caseTypeDesc		= "";

	private String				caseStatus			= "";

	private String				closureReason		= "";

	private String				otherName			= "";

	private String				otherPartType		= "";

	private String				processStatus		= "";

	private String				prcsStatDesc		= "";

	private boolean				underOrder			= false;

	private int					childCount;

	private boolean				voilenceExists		= false;

	private boolean				protectiveOrder		= false;

	// Case Worker Details
	private String				wrkrName			= "";
	private String				wrkrAddr1			= "";
	private String				wrkrAddr2			= "";
	private String				wrkrCity			= "";
	private String				wrkrState			= "";

	//case view properties

	private String				errorMsg			= "";

	/**
	 * @return the errorMsg
	 */
	public String getErrorMsg()
	{
		return errorMsg;
	}

	/**
	 * @param errorMsg
	 *            the errorMsg to set
	 */
	public void setErrorMsg(String errorMsg)
	{
		this.errorMsg = errorMsg;
	}

	/**
	 * @return the colorCode
	 */
	public String getColorCode()
	{
		return ColorCode;
	}

	/**
	 * @param colorCode
	 *            the colorCode to set
	 */
	public void setColorCode(String colorCode)
	{
		ColorCode = colorCode;
	}

	private String ColorCode = "";

	/**
	 * @return the ivdCase
	 */
	public String getIvdCase()
	{
		return ivdCase;
	}

	/**
	 * @param ivdCase
	 *            the ivdCase to set
	 */
	public void setIvdCase(String ivdCase)
	{
		this.ivdCase = ivdCase;
	}

	/**
	 * @return the partType
	 */
	public String getPartType()
	{
		return partType;
	}

	/**
	 * @param partType
	 *            the partType to set
	 */
	public void setPartType(String partType)
	{
		this.partType = partType;
	}

	/**
	 * @return the partStatus
	 */
	public String getPartStatus()
	{
		return partStatus;
	}

	/**
	 * @param partStatus
	 *            the partStatus to set
	 */
	public void setPartStatus(String partStatus)
	{
		this.partStatus = partStatus;
	}

	/**
	 * @return the caseType
	 */
	public String getCaseType()
	{
		return caseType;
	}

	/**
	 * @param caseType
	 *            the caseType to set
	 */
	public void setCaseType(String caseType)
	{
		this.caseType = caseType;
	}

	/**
	 * @return the caseTypeDesc
	 */
	public String getCaseTypeDesc()
	{
		return caseTypeDesc;
	}

	/**
	 * @param caseTypeDesc
	 *            the caseTypeDesc to set
	 */
	public void setCaseTypeDesc(String caseTypeDesc)
	{
		this.caseTypeDesc = caseTypeDesc;
	}

	/**
	 * @return the caseStatus
	 */
	public String getCaseStatus()
	{
		return caseStatus;
	}

	/**
	 * @param caseStatus
	 *            the caseStatus to set
	 */
	public void setCaseStatus(String caseStatus)
	{
		this.caseStatus = caseStatus;
	}

	/**
	 * @return the closureReason
	 */
	public String getClosureReason()
	{
		return closureReason;
	}

	/**
	 * @param closureReason
	 *            the closureReason to set
	 */
	public void setClosureReason(String closureReason)
	{
		this.closureReason = closureReason;
	}

	/**
	 * @return the otherName
	 */
	public String getOtherName()
	{
		return otherName;
	}

	/**
	 * @param otherName
	 *            the otherName to set
	 */
	public void setOtherName(String otherName)
	{
		this.otherName = otherName;
	}

	/**
	 * @return the otherPartType
	 */
	public String getOtherPartType()
	{
		return otherPartType;
	}

	/**
	 * @param otherPartType
	 *            the otherPartType to set
	 */
	public void setOtherPartType(String otherPartType)
	{
		this.otherPartType = otherPartType;
	}

	/**
	 * @return the processStatus
	 */
	public String getProcessStatus()
	{
		return processStatus;
	}

	/**
	 * @param processStatus
	 *            the processStatus to set
	 */
	public void setProcessStatus(String processStatus)
	{
		this.processStatus = processStatus;
	}

	/**
	 * @return the prcsStatDesc
	 */
	public String getPrcsStatDesc()
	{
		return prcsStatDesc;
	}

	/**
	 * @param prcsStatDesc
	 *            the prcsStatDesc to set
	 */
	public void setPrcsStatDesc(String prcsStatDesc)
	{
		this.prcsStatDesc = prcsStatDesc;
	}

	/**
	 * @return the underOrder
	 */
	public boolean isUnderOrder()
	{
		return underOrder;
	}

	/**
	 * @param underOrder
	 *            the underOrder to set
	 */
	public void setUnderOrder(boolean underOrder)
	{
		this.underOrder = underOrder;
	}

	/**
	 * @return the childCount
	 */
	public int getChildCount()
	{
		return childCount;
	}

	/**
	 * @param childCount
	 *            the childCount to set
	 */
	public void setChildCount(int childCount)
	{
		this.childCount = childCount;
	}

	/**
	 * @return the voilenceExists
	 */
	public boolean isVoilenceExists()
	{
		return voilenceExists;
	}

	/**
	 * @param voilenceExists
	 *            the voilenceExists to set
	 */
	public void setVoilenceExists(boolean voilenceExists)
	{
		this.voilenceExists = voilenceExists;
	}

	/**
	 * @return the protectiveOrder
	 */
	public boolean isProtectiveOrder()
	{
		return protectiveOrder;
	}

	/**
	 * @param protectiveOrder
	 *            the protectiveOrder to set
	 */
	public void setProtectiveOrder(boolean protectiveOrder)
	{
		this.protectiveOrder = protectiveOrder;
	}

	/**
	 * @return the wrkrName
	 */
	public String getWrkrName()
	{
		return wrkrName;
	}

	/**
	 * @param wrkrName
	 *            the wrkrName to set
	 */
	public void setWrkrName(String wrkrName)
	{
		this.wrkrName = wrkrName;
	}

	/**
	 * @return the wrkrAddr1
	 */
	public String getWrkrAddr1()
	{
		return wrkrAddr1;
	}

	/**
	 * @param wrkrAddr1
	 *            the wrkrAddr1 to set
	 */
	public void setWrkrAddr1(String wrkrAddr1)
	{
		this.wrkrAddr1 = wrkrAddr1;
	}

	/**
	 * @return the wrkrAddr2
	 */
	public String getWrkrAddr2()
	{
		return wrkrAddr2;
	}

	/**
	 * @param wrkrAddr2
	 *            the wrkrAddr2 to set
	 */
	public void setWrkrAddr2(String wrkrAddr2)
	{
		this.wrkrAddr2 = wrkrAddr2;
	}

	/**
	 * @return the wrkrCity
	 */
	public String getWrkrCity()
	{
		return wrkrCity;
	}

	/**
	 * @param wrkrCity
	 *            the wrkrCity to set
	 */
	public void setWrkrCity(String wrkrCity)
	{
		this.wrkrCity = wrkrCity;
	}

	/**
	 * @return the wrkrState
	 */
	public String getWrkrState()
	{
		return wrkrState;
	}

	/**
	 * @param wrkrState
	 *            the wrkrState to set
	 */
	public void setWrkrState(String wrkrState)
	{
		this.wrkrState = wrkrState;
	}

	/**
	 * @return the wrkrZip
	 */
	public String getWrkrZip()
	{
		return wrkrZip;
	}

	/**
	 * @param wrkrZip
	 *            the wrkrZip to set
	 */
	public void setWrkrZip(String wrkrZip)
	{
		this.wrkrZip = wrkrZip;
	}

	/**
	 * @return the wrkrPhone
	 */
	public String getWrkrPhone()
	{
		return wrkrPhone;
	}

	/**
	 * @param wrkrPhone
	 *            the wrkrPhone to set
	 */
	public void setWrkrPhone(String wrkrPhone)
	{
		this.wrkrPhone = wrkrPhone;
	}

	/**
	 * @return the wrkrPhoneExt
	 */
	public String getWrkrPhoneExt()
	{
		return wrkrPhoneExt;
	}

	/**
	 * @param wrkrPhoneExt
	 *            the wrkrPhoneExt to set
	 */
	public void setWrkrPhoneExt(String wrkrPhoneExt)
	{
		this.wrkrPhoneExt = wrkrPhoneExt;
	}

	/**
	 * @return the wrkrEmail
	 */
	public String getWrkrEmail()
	{
		return wrkrEmail;
	}

	/**
	 * @param wrkrEmail
	 *            the wrkrEmail to set
	 */
	public void setWrkrEmail(String wrkrEmail)
	{
		this.wrkrEmail = wrkrEmail;
	}

	private String		wrkrZip			= "";
	private String		wrkrPhone		= "";
	private String		wrkrPhoneExt	= "";
	private String		wrkrEmail		= "";

	SimpleDateFormat	DATE_FORMAT		= new SimpleDateFormat("yyyy-MM-dd");

	public String toString()
	{

		StringBuffer buf = new StringBuffer();

		buf.append("*** Case Bean ***\n");
		buf.append("Case Status: " + caseStatus + "\n");
		buf.append("Case Type: " + caseType + "\n");
		buf.append("Child Count : " + childCount + "\n");
		buf.append("IVD Case: " + ivdCase + "\n");
		buf.append("**********************\n");

		return buf.toString();
	}
}
