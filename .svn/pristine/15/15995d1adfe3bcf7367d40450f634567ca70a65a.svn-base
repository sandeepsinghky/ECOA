/**
 * 
 */
package nc.dhhs.nccss.acts.ecoa.beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Bean used to leverage NCID user to the DAO user.
 * 
 * @author Vijay Peddapalli
 *
 */
public class EcoaUser implements Serializable {

	private static final long serialVersionUID = 1L;

	private long userId;

	private String loginId;

	private String lastName;

	private String firstName;

	private String middleName;

	private long businessAreaId;

	private String ncidLastNM;

	private String ncidFirstNM;

	private String ncidMiddleNM;

	private String ncIdEmail;

	private String ipAddressPort;

	private Timestamp loginTime;

	private Timestamp logoutTime;

	private String browserAgent;

	private Date dateDeactivated;

	private double nbAttempt;
	
	private String	userType;

	/**
	 * @return the nbAttempt
	 */
	public double getNbAttempt() {
		return nbAttempt;
	}

	/**
	 * @param nbAttempt
	 *            the nbAttempt to set
	 */
	public void setNbAttempt(double nbAttempt) {
		this.nbAttempt = nbAttempt;
	}

	private Date dtLastUpd;

	SimpleDateFormat DATE_FORMAT_SCREEN = new SimpleDateFormat("MM/dd/yyyy");

	SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * @return the dtLastUpd
	 */
	public Date getDtLastUpd() {
		return dtLastUpd;
	}

	/**
	 * @param dtLastUpd
	 *            the dtLastUpd to set
	 */
	public void setDtLastUpd(Date dtLastUpd) {
		this.dtLastUpd = dtLastUpd;
	}

	public String getDtLastUpdStrPg() {
		if (dtLastUpd == null)
			return null;
		else
			return DATE_FORMAT_SCREEN.format(dtLastUpd).trim();
	}

	public String getDtLastUpdStr() {
		if (dtLastUpd == null)
			return null;
		else
			return DATE_FORMAT.format(dtLastUpd).trim();
	}

	private String partId;

	private String ncIdGuid;

	private String userStatus;

	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * @return the loginId
	 */
	public String getLoginId() {
		return loginId;
	}

	/**
	 * @param loginId
	 *            the loginId to set
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName
	 *            the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the ncidLastNM
	 */
	public String getNcidLastNM() {
		return ncidLastNM;
	}

	/**
	 * @param ncidLastNM
	 *            the ncidLastNM to set
	 */
	public void setNcidLastNM(String ncidLastNM) {
		this.ncidLastNM = ncidLastNM;
	}

	/**
	 * @return the ncidFirstNM
	 */
	public String getNcidFirstNM() {
		return ncidFirstNM;
	}

	/**
	 * @param ncidFirstNM
	 *            the ncidFirstNM to set
	 */
	public void setNcidFirstNM(String ncidFirstNM) {
		this.ncidFirstNM = ncidFirstNM;
	}

	/**
	 * @return the ncidMiddleNM
	 */
	public String getNcidMiddleNM() {
		return ncidMiddleNM;
	}

	/**
	 * @param ncidMiddleNM
	 *            the ncidMiddleNM to set
	 */
	public void setNcidMiddleNM(String ncidMiddleNM) {
		this.ncidMiddleNM = ncidMiddleNM;
	}

	/**
	 * @return the ipAddressPort
	 */
	public String getIpAddressPort() {
		return ipAddressPort;
	}

	/**
	 * @param ipAddressPort
	 *            the ipAddressPort to set
	 */
	public void setIpAddressPort(String ipAddressPort) {
		this.ipAddressPort = ipAddressPort;
	}

	/**
	 * @return the loginTime
	 */
	public Timestamp getLoginTime() {
		return loginTime;
	}

	/**
	 * @param loginTime
	 *            the loginTime to set
	 */
	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}

	/**
	 * @return the logoutTime
	 */
	public Timestamp getLogoutTime() {
		return logoutTime;
	}

	/**
	 * @param logoutTime
	 *            the logoutTime to set
	 */
	public void setLogoutTime(Timestamp logoutTime) {
		this.logoutTime = logoutTime;
	}

	/**
	 * @return the browserAgent
	 */
	public String getBrowserAgent() {
		return browserAgent;
	}

	/**
	 * @param browserAgent
	 *            the browserAgent to set
	 */
	public void setBrowserAgent(String browserAgent) {
		this.browserAgent = browserAgent;
	}

	/**
	 * @return the dateDeactivated
	 */
	public Date getDateDeactivated() {
		return dateDeactivated;
	}

	/**
	 * @param dateDeactivated
	 *            the dateDeactivated to set
	 */
	public void setDateDeactivated(Date dateDeactivated) {
		this.dateDeactivated = dateDeactivated;
	}

	/**
	 * @return the businessAreaId
	 */
	public long getBusinessAreaId() {
		return businessAreaId;
	}

	/**
	 * @param businessAreaId
	 *            the businessAreaId to set
	 */
	public void setBusinessAreaId(long businessAreaId) {
		this.businessAreaId = businessAreaId;
	}

	/**
	 * @return the idPart
	 */
	public String getPartId() {
		return partId;
	}

	/**
	 * @param idPart
	 *            the idPart to set
	 */
	public void setPartId(String partId) {
		this.partId = partId;
	}

	/**
	 * @return the ncIdGuid
	 */
	public String getNcIdGuid() {
		return ncIdGuid;
	}

	/**
	 * @param ncIdGuid
	 *            the ncIdGuid to set
	 */
	public void setNcIdGuid(String ncIdGuid) {
		this.ncIdGuid = ncIdGuid;
	}

	/**
	 * @return the userStatus
	 */
	public String getUserStatus() {
		return userStatus;
	}

	/**
	 * @param userStatus
	 *            the userStatus to set
	 */
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	/**
	 * @return the ncIdEmail
	 */
	public String getNcIdEmail() {
		return ncIdEmail;
	}

	/**
	 * @param ncIdEmail
	 *            the ncIdEmail to set
	 */
	public void setNcIdEmail(String ncIdEmail) {
		this.ncIdEmail = ncIdEmail;
	}
	
	/**
	 * @return the userType
	 */
	public String getUserType()
	{
		return userType;
	}

	/**
	 * @param userType
	 *            the userType to set
	 */
	public void setUserType(String userType)
	{
		this.userType = userType;
	}

	/*
	 * private String guiId; private String email; private String phone;
	 */

}
