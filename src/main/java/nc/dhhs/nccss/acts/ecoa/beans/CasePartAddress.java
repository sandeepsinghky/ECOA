package nc.dhhs.nccss.acts.ecoa.beans;

import java.io.Serializable;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Phani Konuru
 * 
 */

public class CasePartAddress implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 2750665216681675550L;

	private String				applicationId		= "";

	private String				applicantId			= "";

	private String				addrType			= "";

	private String				addrLn1				= "";

	private String				addrLn2				= "";

	private String				addrCty				= "";

	private String				addrSt				= "";

	private String				addrZip5			= "";

	private String				addrZip4			= "";

	private String				addrZipCarr			= "";

	private String				addrCnty			= "";

	private String				addrCntry			= "";

	private Date				createDt;

	private Date				lastUpdatDt;

	private Time				lastUpdateTm;

	SimpleDateFormat			DATE_FORMAT			= new SimpleDateFormat("yyyy-MM-dd");

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
	 * @return the addrType
	 */
	public String getAddrType()
	{
		return addrType;
	}

	/**
	 * @param addrType
	 *            the addrType to set
	 */
	public void setAddrType(String addrType)
	{
		this.addrType = addrType;
	}

	/**
	 * @return the addrLn1
	 */
	public String getAddrLn1()
	{
		return addrLn1;
	}

	/**
	 * @param addrLn1
	 *            the addrLn1 to set
	 */
	public void setAddrLn1(String addrLn1)
	{
		this.addrLn1 = addrLn1;
	}

	/**
	 * @return the addrLn2
	 */
	public String getAddrLn2()
	{
		return addrLn2;
	}

	/**
	 * @param addrLn2
	 *            the addrLn2 to set
	 */
	public void setAddrLn2(String addrLn2)
	{
		this.addrLn2 = addrLn2;
	}

	/**
	 * @return the addrCty
	 */
	public String getAddrCty()
	{
		return addrCty;
	}

	/**
	 * @param addrCty
	 *            the addrCty to set
	 */
	public void setAddrCty(String addrCty)
	{
		this.addrCty = addrCty;
	}

	/**
	 * @return the addrSt
	 */
	public String getAddrSt()
	{
		return addrSt;
	}

	/**
	 * @param addrSt
	 *            the addrSt to set
	 */
	public void setAddrSt(String addrSt)
	{
		this.addrSt = addrSt;
	}

	/**
	 * @return the addrZip5
	 */
	public String getAddrZip5()
	{
		return addrZip5;
	}

	/**
	 * @param addrZip5
	 *            the addrZip5 to set
	 */
	public void setAddrZip5(String addrZip5)
	{
		this.addrZip5 = addrZip5;
	}

	/**
	 * @return the addrZip4
	 */
	public String getAddrZip4()
	{
		return addrZip4;
	}

	/**
	 * @param addrZip4
	 *            the addrZip4 to set
	 */
	public void setAddrZip4(String addrZip4)
	{
		this.addrZip4 = addrZip4;
	}

	/**
	 * @return the addrZipCarr
	 */
	public String getAddrZipCarr()
	{
		return addrZipCarr;
	}

	/**
	 * @param addrZipCarr
	 *            the addrZipCarr to set
	 */
	public void setAddrZipCarr(String addrZipCarr)
	{
		this.addrZipCarr = addrZipCarr;
	}

	/**
	 * @return the addrCnty
	 */
	public String getAddrCnty()
	{
		return addrCnty;
	}

	/**
	 * @param addrCnty
	 *            the addrCnty to set
	 */
	public void setAddrCnty(String addrCnty)
	{
		this.addrCnty = addrCnty;
	}

	/**
	 * @return the addrCntry
	 */
	public String getAddrCntry()
	{
		return addrCntry;
	}

	/**
	 * @param addrCntry
	 *            the addrCntry to set
	 */
	public void setAddrCntry(String addrCntry)
	{
		this.addrCntry = addrCntry;
	}

	/**
	 * @return the createDt
	 */
	public Date getCreateDt()
	{
		return createDt;
	}

	/**
	 * @return
	 */
	public String getCreateDtStr()
	{
		if (createDt == null) return null;
		else
			return DATE_FORMAT.format(createDt).trim();
	}

	/**
	 * @param createDt
	 *            the createDt to set
	 */
	public void setCreateDt(Date createDt)
	{
		this.createDt = createDt;
	}

	/**
	 * @return the lastUpdatDt
	 */
	public Date getLastUpdatDt()
	{
		return lastUpdatDt;
	}

	/**
	 * @return
	 */
	public String getLastUpdatDtStr()
	{
		if (lastUpdatDt == null) return null;
		else
			return DATE_FORMAT.format(lastUpdatDt).trim();
	}

	/**
	 * @param lastUpdatDt
	 *            the lastUpdatDt to set
	 */
	public void setLastUpdatDt(Date lastUpdatDt)
	{
		this.lastUpdatDt = lastUpdatDt;
	}

	/**
	 * @return the lastUpdateTm
	 */
	public Time getLastUpdateTm()
	{
		return lastUpdateTm;
	}

	/**
	 * @return
	 */
	public String getLastUpdateTmStr()
	{
		if (lastUpdateTm == null) return null;
		else
			return lastUpdateTm.toString().trim();
	}

	/**
	 * @param lastUpdateTm
	 *            the lastUpdateTm to set
	 */
	public void setLastUpdateTm(Time lastUpdateTm)
	{
		this.lastUpdateTm = lastUpdateTm;
	}

}