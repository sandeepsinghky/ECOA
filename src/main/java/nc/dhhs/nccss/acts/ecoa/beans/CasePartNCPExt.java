package nc.dhhs.nccss.acts.ecoa.beans;

import java.io.Serializable;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * @author Mallika Velagapudi
 * 
 */
//This bean corresponds to the table ACTW_NCP_EXT table
public class CasePartNCPExt implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 2750664216691675550L;

	private String				applicationId		= "";

	private String				applicantId			= "";

	private String				birthCityNm			= "";

	private String				birthStateNm		= "";

	private String				birthCountyNm		= "";

	private String				birthCountryNm		= "";

	private String				height				= "";

	private double				weight;

	private String				eyeColor			= " ";

	private String				hairColor			= " ";

	private String				marks				= " ";

	private String				driverlicNum		= " ";

	private String				licenseState		= " ";

	private Date				maritualDt;

	private String				maritalStatus		= " ";

	private String				miltryBranch		= " ";

	private String				miltryStatus		= " ";

	private String				miltryStation		= " ";

	private String				parole				= " ";

	private String				incarcerated		= " ";

	private String				workRelease			= " ";

	private Date				createdDt;

	private Date				lastUpdatDt;

	private Time				lastUpdateTm;

	private String				additionalNote		= "";

	private Date				arrestDt;

	private String				arrestCity			= " ";

	private String				arrestState			= " ";

	private String				arrestStateDes		= " ";

	private String				driverLicStatus		= "";

	private String				ownVehicleStatus	= "";

	private String				mltryStatus			= "";

	private String				arrestStatus		= "";

	private String				paroleStatus		= "";

	private String				incarStatus			= "";

	private String				workReleaseStatus	= "";

	private String				employmentStatus	= "";

	private String				orderStatus			= "";

	/**
	 * @return the arrestStateDes
	 */
	public String getArrestStateDes()
	{
		return arrestStateDes;
	}

	/**
	 * @return the driverLicStatus
	 */
	public String getDriverLicStatus()
	{
		return driverLicStatus;
	}

	/**
	 * @param driverLicStatus
	 *            the driverLicStatus to set
	 */
	public void setDriverLicStatus(String driverLicStatus)
	{
		this.driverLicStatus = driverLicStatus;
	}

	/**
	 * @return the ownVehicleStatus
	 */
	public String getOwnVehicleStatus()
	{
		return ownVehicleStatus;
	}

	/**
	 * @param ownVehicleStatus
	 *            the ownVehicleStatus to set
	 */
	public void setOwnVehicleStatus(String ownVehicleStatus)
	{
		this.ownVehicleStatus = ownVehicleStatus;
	}

	/**
	 * @return the mltryStatus
	 */
	public String getMltryStatus()
	{
		return mltryStatus;
	}

	/**
	 * @param mltryStatus
	 *            the mltryStatus to set
	 */
	public void setMltryStatus(String mltryStatus)
	{
		this.mltryStatus = mltryStatus;
	}

	/**
	 * @return the arrestStatus
	 */
	public String getArrestStatus()
	{
		return arrestStatus;
	}

	/**
	 * @param arrestStatus
	 *            the arrestStatus to set
	 */
	public void setArrestStatus(String arrestStatus)
	{
		this.arrestStatus = arrestStatus;
	}

	/**
	 * @return the paroleStatus
	 */
	public String getParoleStatus()
	{
		return paroleStatus;
	}

	/**
	 * @param paroleStatus
	 *            the paroleStatus to set
	 */
	public void setParoleStatus(String paroleStatus)
	{
		this.paroleStatus = paroleStatus;
	}

	/**
	 * @return the incarStatus
	 */
	public String getIncarStatus()
	{
		return incarStatus;
	}

	/**
	 * @param incarStatus
	 *            the incarStatus to set
	 */
	public void setIncarStatus(String incarStatus)
	{
		this.incarStatus = incarStatus;
	}

	/**
	 * @return the workReleaseStatus
	 */
	public String getWorkReleaseStatus()
	{
		return workReleaseStatus;
	}

	/**
	 * @param workReleaseStatus
	 *            the workReleaseStatus to set
	 */
	public void setWorkReleaseStatus(String workReleaseStatus)
	{
		this.workReleaseStatus = workReleaseStatus;
	}

	/**
	 * @return the employmentStatus
	 */
	public String getEmploymentStatus()
	{
		return employmentStatus;
	}

	/**
	 * @param employmentStatus
	 *            the employmentStatus to set
	 */
	public void setEmploymentStatus(String employmentStatus)
	{
		this.employmentStatus = employmentStatus;
	}

	/**
	 * @return the orderStatus
	 */
	public String getOrderStatus()
	{
		return orderStatus;
	}

	/**
	 * @param orderStatus
	 *            the orderStatus to set
	 */
	public void setOrderStatus(String orderStatus)
	{
		this.orderStatus = orderStatus;
	}

	/**
	 * @param arrestStateDes
	 *            the arrestStateDes to set
	 */
	public void setArrestStateDes(String arrestStateDes)
	{
		this.arrestStateDes = arrestStateDes;
	}

	SimpleDateFormat	DATE_FORMAT			= new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat	DATE_FORMAT_SCREEN	= new SimpleDateFormat("MM/dd/yyyy");

	/**
	 * @return the maritalStatus
	 */
	public String getMaritalStatus()
	{
		return maritalStatus;
	}

	/**
	 * @param maritalStatus
	 *            the maritalStatus to set
	 */
	public void setMaritalStatus(String maritalStatus)
	{
		this.maritalStatus = maritalStatus;
	}

	/**
	 * @return the createdDt
	 */
	public Date getCreatedDt()
	{
		return createdDt;
	}

	public String getCreatedDtStr()
	{
		if (createdDt == null) return null;
		else
			return DATE_FORMAT.format(createdDt).trim();
	}

	/**
	 * @param createdDt
	 *            the createdDt to set
	 */
	public void setCreatedDt(Date createdDt)
	{
		this.createdDt = createdDt;
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
	 * @return the birthCityNm
	 */
	public String getBirthCityNm()
	{
		return birthCityNm;
	}

	/**
	 * @param birthCityNm
	 *            the birthCityNm to set
	 */
	public void setBirthCityNm(String birthCityNm)
	{
		this.birthCityNm = birthCityNm;
	}

	/**
	 * @return the birthStateNm
	 */
	public String getBirthStateNm()
	{
		return birthStateNm;
	}

	/**
	 * @param birthStateNm
	 *            the birthStateNm to set
	 */
	public void setBirthStateNm(String birthStateNm)
	{
		this.birthStateNm = birthStateNm;
	}

	/**
	 * @return the birthCountyNm
	 */
	public String getBirthCountyNm()
	{
		return birthCountyNm;
	}

	/**
	 * @param birthCountyNm
	 *            the birthCountyNm to set
	 */
	public void setBirthCountyNm(String birthCountyNm)
	{
		this.birthCountyNm = birthCountyNm;
	}

	/**
	 * @return the birthCountryNm
	 */
	public String getBirthCountryNm()
	{
		return birthCountryNm;
	}

	/**
	 * @param birthCountryNm
	 *            the birthCountryNm to set
	 */
	public void setBirthCountryNm(String birthCountryNm)
	{
		this.birthCountryNm = birthCountryNm;
	}

	/**
	 * @return the height
	 */
	public String getHeight()
	{
		return height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(String height)
	{
		this.height = height;
	}

	/**
	 * @return the weight
	 */
	public double getWeight()
	{
		return weight;
	}

	/**
	 * @param weight
	 *            the weight to set
	 */
	public void setWeight(double weight)
	{
		this.weight = weight;
	}

	public String getWeightDisplay()

	{
		String weightDis = "";

		if (weight != 0.0) weightDis = Double.toString(weight);

		return weightDis;
	}

	/**
	 * @return the eyeColor
	 */
	public String getEyeColor()
	{
		return eyeColor;
	}

	/**
	 * @param eyeColor
	 *            the eyeColor to set
	 */
	public void setEyeColor(String eyeColor)
	{
		this.eyeColor = eyeColor;
	}

	/**
	 * @return the hairColor
	 */
	public String getHairColor()
	{
		return hairColor;
	}

	/**
	 * @param hairColor
	 *            the hairColor to set
	 */
	public void setHairColor(String hairColor)
	{
		this.hairColor = hairColor;
	}

	/**
	 * @return the marks
	 */
	public String getMarks()
	{
		return marks;
	}

	/**
	 * @param marks
	 *            the marks to set
	 */
	public void setMarks(String marks)
	{
		this.marks = marks;
	}

	/**
	 * @return the driverlicNum
	 */
	public String getDriverlicNum()
	{
		return driverlicNum;
	}

	/**
	 * @param driverlicNum
	 *            the driverlicNum to set
	 */
	public void setDriverlicNum(String driverlicNum)
	{
		this.driverlicNum = driverlicNum;
	}

	/**
	 * @return the licenseState
	 */
	public String getLicenseState()
	{
		return licenseState;
	}

	/**
	 * @param licenseState
	 *            the licenseState to set
	 */
	public void setLicenseState(String licenseState)
	{
		this.licenseState = licenseState;
	}

	/**
	 * @return the maritualDt
	 */
	public Date getMaritualDt()
	{
		return maritualDt;
	}

	public String getMaritualDtStr()
	{
		if (maritualDt == null) return null;
		else
			return DATE_FORMAT.format(maritualDt).trim();
	}

	public String getMaritualDtStrPg()
	{

		if (maritualDt == null) return "";
		else
			return DATE_FORMAT_SCREEN.format(maritualDt).trim().equals(AppConstants.PAGE_DEFAULT_DATE) ? "" : DATE_FORMAT_SCREEN.format(maritualDt).trim();

	}

	/**
	 * @param maritualDt
	 *            the maritualDt to set
	 */
	public void setMaritualDt(Date maritualDt)
	{
		this.maritualDt = maritualDt;
	}

	/**
	 * @return the miltryBranch
	 */
	public String getMiltryBranch()
	{
		return miltryBranch;
	}

	/**
	 * @param miltryBranch
	 *            the miltryBranch to set
	 */
	public void setMiltryBranch(String miltryBranch)
	{
		this.miltryBranch = miltryBranch;
	}

	/**
	 * @return the miltryStatus
	 */
	public String getMiltryStatus()
	{
		return miltryStatus;
	}

	/**
	 * @param miltryStatus
	 *            the miltryStatus to set
	 */
	public void setMiltryStatus(String miltryStatus)
	{
		this.miltryStatus = miltryStatus;
	}

	/**
	 * @return the miltryStation
	 */
	public String getMiltryStation()
	{
		return miltryStation;
	}

	/**
	 * @param miltryStation
	 *            the miltryStation to set
	 */
	public void setMiltryStation(String miltryStation)
	{
		this.miltryStation = miltryStation;
	}

	/**
	 * @return the parole
	 */
	public String getParole()
	{
		return parole;
	}

	/**
	 * @param parole
	 *            the parole to set
	 */
	public void setParole(String parole)
	{
		this.parole = parole;
	}

	/**
	 * @return the incarcerated
	 */
	public String getIncarcerated()
	{
		return incarcerated;
	}

	/**
	 * @param incarcerated
	 *            the incarcerated to set
	 */
	public void setIncarcerated(String incarcerated)
	{
		this.incarcerated = incarcerated;
	}

	/**
	 * @return the workRelease
	 */
	public String getWorkRelease()
	{
		return workRelease;
	}

	/**
	 * @param workRelease
	 *            the workRelease to set
	 */
	public void setWorkRelease(String workRelease)
	{
		this.workRelease = workRelease;
	}

	/**
	 * @return the lastUpdatDt
	 */
	public Date getLastUpdatDt()
	{
		return lastUpdatDt;
	}

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

	/**
	 * @return the additionalNote
	 */
	public String getAdditionalNote()
	{
		return additionalNote;
	}

	/**
	 * @param additionalNote
	 *            the additionalNote to set
	 */
	public void setAdditionalNote(String additionalNote)
	{
		this.additionalNote = additionalNote;
	}

	/**
	 * @return the arrestDt
	 */
	public Date getArrestDt()
	{
		return arrestDt;
	}

	/**
	 * @param arrestDt
	 *            the arrestDt to set
	 */
	public void setArrestDt(Date arrestDt)
	{
		this.arrestDt = arrestDt;
	}

	public String getArrestDtStr()
	{
		if (arrestDt == null) return null;
		else
			return DATE_FORMAT.format(arrestDt).trim();
	}

	public String getArrestDtStrPg()
	{
		if (arrestDt == null) return "";
		else
			return DATE_FORMAT_SCREEN.format(arrestDt).trim().equals(AppConstants.PAGE_DEFAULT_DATE) ? "" : DATE_FORMAT_SCREEN.format(arrestDt).trim();
	}

	/**
	 * @return the arrestCity
	 */
	public String getArrestCity()
	{
		return arrestCity;
	}

	/**
	 * @param arrestCity
	 *            the arrestCity to set
	 */
	public void setArrestCity(String arrestCity)
	{
		this.arrestCity = arrestCity;
	}

	/**
	 * @return the arrestState
	 */
	public String getArrestState()
	{
		return arrestState;
	}

	/**
	 * @param arrestState
	 *            the arrestState to set
	 */
	public void setArrestState(String arrestState)
	{
		this.arrestState = arrestState;
	}

}