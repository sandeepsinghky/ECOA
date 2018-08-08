package nc.dhhs.nccss.acts.ecoa.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * @author Phani Konuru
 * 
 */

public class CasePartPaternty implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 4113430031512186781L;

	private String				applicationId		= "";

	private String				applicantId			= "";

	private String				applicantChildId	= "";

	private Date				patnTestDt			= null;

	private String				patnResult			= "";

	private String				patnEst				= "";

	private Date				patnEstDt			= null;

	private String				patnEstCnty			= "";

	private String				patnEstSt			= "";
	
	private String				isPatnTstSel			= "";

	SimpleDateFormat			DATE_FORMAT			= new SimpleDateFormat("yyyy-MM-dd");

	SimpleDateFormat			DATE_FORMAT_PAGE	= new SimpleDateFormat("MM/dd/yyyy");

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
	 * @return the applicantChildId
	 */
	public String getApplicantChildId()
	{
		return applicantChildId;
	}

	/**
	 * @param applicantChildId
	 *            the applicantChildId to set
	 */
	public void setApplicantChildId(String applicantChildId)
	{
		this.applicantChildId = applicantChildId;
	}

	/**
	 * @return the patnTestDt
	 */
	public Date getPatnTestDt()
	{
		return patnTestDt;
	}

	/**
	 * @param patnTestDt
	 *            the patnTestDt to set
	 */
	public void setPatnTestDt(Date patnTestDt)
	{
		this.patnTestDt = patnTestDt;
	}

	public void setPatnTestDt(String patnTestDt)
	{
		try
		{
			this.patnTestDt = DATE_FORMAT_PAGE.parse(patnTestDt);
		}
		catch (Exception e)
		{
		}
	}

	/**
	 * @return
	 */
	public String getPatnTestDtStr()
	{
		if (patnTestDt == null) return null;
		else
			return DATE_FORMAT.format(patnTestDt).trim();
	}

	/**
	 * @return
	 */
	public String getPatnTestDtStrPg()
	{
		if (patnTestDt == null) return "";
		else
			return DATE_FORMAT_PAGE.format(patnTestDt).trim().equals(AppConstants.PAGE_DEFAULT_DATE) ? "" : DATE_FORMAT_PAGE.format(patnTestDt).trim();
	}

	/**
	 * @return the patnResult
	 */
	public String getPatnResult()
	{
		return patnResult;
	}

	/**
	 * @param patnResult
	 *            the patnResult to set
	 */
	public void setPatnResult(String patnResult)
	{
		this.patnResult = patnResult;
	}

	/**
	 * @return the patnEst
	 */
	public String getPatnEst()
	{
		return patnEst;
	}

	/**
	 * @param patnEst
	 *            the patnEst to set
	 */
	public void setPatnEst(String patnEst)
	{
		this.patnEst = patnEst;
	}

	/**
	 * @return the patnEstDt
	 */
	public Date getPatnEstDt()
	{
		return patnEstDt;
	}

	/**
	 * @param patnEstDt
	 *            the patnEstDt to set
	 */
	public void setPatnEstDt(Date patnEstDt)
	{
		this.patnEstDt = patnEstDt;
	}

	/**
	 * @param patnEstDt
	 */
	public void setPatnEstDt(String patnEstDt)
	{
		try
		{
			this.patnEstDt = DATE_FORMAT_PAGE.parse(patnEstDt);
		}
		catch (Exception e)
		{
		}
	}

	/**
	 * @return
	 */
	public String getPatnEstDtStr()
	{
		if (patnEstDt == null) return null;
		else
			return DATE_FORMAT.format(patnEstDt).trim();
	}

	public String getPatnEstDtStrPg()
	{
		if (patnEstDt == null) return "";
		else
			return DATE_FORMAT_PAGE.format(patnEstDt).trim().equals(AppConstants.PAGE_DEFAULT_DATE) ? "" : DATE_FORMAT_PAGE.format(patnEstDt).trim();
	}

	/**
	 * @return the patnEstCnty
	 */
	public String getPatnEstCnty()
	{
		return patnEstCnty;
	}

	/**
	 * @param patnEstCnty
	 *            the patnEstCnty to set
	 */
	public void setPatnEstCnty(String patnEstCnty)
	{
		this.patnEstCnty = patnEstCnty;
	}

	/**
	 * @return the patnEstST
	 */
	public String getPatnEstSt()
	{
		return patnEstSt;
	}

	/**
	 * @param patnEstST
	 *            the patnEstST to set
	 */
	public void setPatnEstSt(String patnEstSt)
	{
		this.patnEstSt = patnEstSt;
	}

	/**
	 * @return
	 */
	public boolean getIsPatnEst()
	{
		return (patnEstDt != null && !getPatnEstDtStrPg().trim().equals("") && !getPatnEstDtStrPg().trim().equals("01/01/0001")) || !patnEstSt.trim().equals("") || !patnEstCnty.trim().equals("");
	}

	public boolean getIsPatnTst()
	{
		return (patnTestDt != null && !getPatnTestDtStrPg().trim().equals("") && !getPatnTestDtStrPg().trim().equals("01/01/0001")) || !patnResult.trim().equals("");
	}

	/**
	 * @return the isPatnTstSel
	 */
	public String getIsPatnTstSel() {
		//return isPatnTstSel;
		return "1";
	}

	/**
	 * @param isPatnTstSel the isPatnTstSel to set
	 */
	public void setIsPatnTstSel(String isPatnTstSel) {
		this.isPatnTstSel = isPatnTstSel;
	}

}