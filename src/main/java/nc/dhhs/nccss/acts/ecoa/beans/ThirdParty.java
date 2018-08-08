package nc.dhhs.nccss.acts.ecoa.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;

/**
 * @author Mallika Velagapudi
 * 
 */

public class ThirdParty implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 2750665216691675550L;

	private String				applicationId		= "";

	private String				thirdPartyId		= "";

	private String				thirdPartyType		= "";

	private String				thirdPartyNm		= "";

	private String				addressLn1			= "";

	private String				addressLn2			= "";

	private String				thirdPartyCity		= "";

	private String				thirdPartyState		= "";

	private String				thirdPartyZip5		= "";

	private String				thirdPartyZip4		= "";

	private String				thirdPartyZipCarr	= "";

	private String				phoneAreaCode		= "";

	private String				phoneExc			= "";

	private String				phoneLn				= "";

	private String				CountryNm			= "";

	private String				contactName			= "";

	/**
	 * @return the contactName
	 */
	public String getContactName()
	{
		return contactName;
	}

	/**
	 * @param contactName
	 *            the contactName to set
	 */
	public void setContactName(String contactName)
	{
		this.contactName = contactName;
	}

	SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * @return the countryNm
	 */
	public String getCountryNm()
	{
		return CountryNm;
	}

	/**
	 * @param countryNm
	 *            the countryNm to set
	 */
	public void setCountryNm(String countryNm)
	{
		CountryNm = countryNm;
	}

	/**
	 * @return the applicationId
	 */
	public String getApplicationId()
	{
		return applicationId;
	}

	/**
	 * @param applicationId
	 */
	public void setApplicationId(String applicationId)
	{
		this.applicationId = applicationId;
	}

	/**
	 * @return the thirdPartyId
	 */
	public String getThirdPartyId()
	{
		return thirdPartyId;
	}

	/**
	 * @param thirdPartyId
	 */
	public void setThirdPartyId(String thirdPartyId)
	{
		this.thirdPartyId = thirdPartyId;
	}

	/**
	 * @return the thirdPartyType
	 */
	public String getThirdPartyType()
	{
		return thirdPartyType;
	}

	/**
	 * @param thirdPartyType
	 */
	public void setThirdPartyType(String thirdPartyType)
	{
		this.thirdPartyType = thirdPartyType;
	}

	/**
	 * @return the thirdPartyNm
	 */
	public String getThirdPartyNm()
	{
		return thirdPartyNm;
	}

	/**
	 * @param thirdPartyNm
	 */
	public void setThirdPartyNm(String thirdPartyNm)
	{
		this.thirdPartyNm = thirdPartyNm;
	}

	/**
	 * @return the addressLn1
	 */
	public String getAddressLn1()
	{
		return addressLn1;
	}

	/**
	 * @param addressLn1
	 */
	public void setAddressLn1(String addressLn1)
	{
		this.addressLn1 = addressLn1;
	}

	/**
	 * @return the addressLn2
	 */
	public String getAddressLn2()
	{
		return addressLn2;
	}

	/**
	 * @param addressLn2
	 */
	public void setAddressLn2(String addressLn2)
	{
		this.addressLn2 = addressLn2;
	}

	/**
	 * @return the thirdPartyCity
	 */
	public String getThirdPartyCity()
	{
		return thirdPartyCity;
	}

	/**
	 * @param thirdPartyCity
	 */
	public void setThirdPartyCity(String thirdPartyCity)
	{
		this.thirdPartyCity = thirdPartyCity;
	}

	/**
	 * @return the thirdPartyState
	 */
	public String getThirdPartyState()
	{
		return thirdPartyState;
	}

	/**
	 * @param thirdPartyState
	 */
	public void setThirdPartyState(String thirdPartyState)
	{
		this.thirdPartyState = thirdPartyState;
	}

	/**
	 * @return the thirdPartyZip5
	 */
	public String getThirdPartyZip5()
	{
		return thirdPartyZip5;
	}

	/**
	 * @param thirdPartyZip5
	 */
	public void setThirdPartyZip5(String thirdPartyZip5)
	{
		this.thirdPartyZip5 = thirdPartyZip5;
	}

	/**
	 * @return the thirdPartyZip4
	 */
	public String getThirdPartyZip4()
	{
		return thirdPartyZip4;
	}

	/**
	 * @param thirdPartyZip4
	 */
	public void setThirdPartyZip4(String thirdPartyZip4)
	{
		this.thirdPartyZip4 = thirdPartyZip4;
	}

	/**
	 * @return the thirdPartyZipCarr
	 */
	public String getThirdPartyZipCarr()
	{
		return thirdPartyZipCarr;
	}

	/**
	 * @param thirdPartyZipCarr
	 */
	public void setThirdPartyZipCarr(String thirdPartyZipCarr)
	{
		this.thirdPartyZipCarr = thirdPartyZipCarr;
	}

	/**
	 * @return the phoneAreaCode
	 */
	public String getPhoneAreaCode()
	{
		return phoneAreaCode;
	}

	/**
	 * @param phoneAreaCode
	 */
	public void setPhoneAreaCode(String phoneAreaCode)
	{
		this.phoneAreaCode = phoneAreaCode;
	}

	/**
	 * @return the phoneExc
	 */
	public String getPhoneExc()
	{
		return phoneExc;
	}

	/**
	 * @param phoneExc
	 */
	public void setPhoneExc(String phoneExc)
	{
		this.phoneExc = phoneExc;
	}

	/**
	 * @return the phoneLn
	 */
	public String getPhoneLn()
	{
		return phoneLn;
	}

	/**
	 * @param phoneLn
	 *            the phoneLn to set
	 */
	public void setPhoneLn(String phoneLn)
	{
		this.phoneLn = phoneLn;
	}

}