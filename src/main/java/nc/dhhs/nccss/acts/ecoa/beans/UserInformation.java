/**
 * 
 */
package nc.dhhs.nccss.acts.ecoa.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * @author mvelagapudi
 *
 */
public class UserInformation implements Serializable {

	private static final long serialVersionUID = -1792754984477964889L;

	// Variables for the Login
	private String userID;
	private String password;
	private String userType;

	private boolean isRegLock = false;

	private boolean isMpiTied = false;

	private String mpi = "";

	// Variables for authentication

	private String ssn = "";
	private String dob = "";

	// Variables for Registration
	private String webLastName = "";
	private String webFirstName = "";
	private String webMiddleName = "";
	private String emailId = "";
	// participant name.
	private String lastName = "";
	private String firstName = "";
	private String middleName = "";

	private String lastLoginDate = "";

	// the Child Support Participant's account reminders.

	private boolean isScheduled = false;
	private boolean isEmployed = false;
	private boolean isDelinquent = false;
	private boolean isMailPresent = false;

	private ArrayList pymtReminders = null;

	// boolean value to check the validity
	private boolean loginActive;
	private boolean fvExists;

	// Variable to hold the role of the person
	private String caseRelshp = "";

	/**
	 * @return the isRegLock
	 */
	public boolean isRegLock() {
		return isRegLock;
	}

	/**
	 * @param isRegLock
	 *            the isRegLock to set
	 */
	public void setRegLock(boolean isRegLock) {
		this.isRegLock = isRegLock;
	}

	/**
	 * @param pymtReminders
	 *            the pymtReminders to set
	 */
	public void setPymtReminders(ArrayList pymtReminders) {
		this.pymtReminders = pymtReminders;
	}

	// Variable to indicate if the user has different roles in
	// different cases.
	private boolean dualRole = false;

	// Boolean Value that indicates if the data access is from realtime
	// or from backup database
	private boolean realTime;
	private String dateAsOf = "";
	private String timeAsOf = "";

	SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * @return the userID
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * @param userID
	 *            the userID to set
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}

	/**
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * @param userType
	 *            the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the mpi
	 */
	public String getMpi() {
		return mpi;
	}

	/**
	 * @param mpi
	 *            the mpi to set
	 */
	public void setMpi(String mpi) {
		this.mpi = mpi;
	}

	/**
	 * @return the ssn
	 */
	public String getSsn() {
		return ssn;
	}

	/**
	 * @param ssn
	 *            the ssn to set
	 */
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	/**
	 * @return the dob
	 */
	public String getDob() {
		return dob;
	}

	/**
	 * @param dob
	 *            the dob to set
	 */
	public void setDob(String dob) {
		this.dob = dob;
	}

	/**
	 * @return the webLastName
	 */
	public String getWebLastName() {
		return webLastName;
	}

	/**
	 * @param webLastName
	 *            the webLastName to set
	 */
	public void setWebLastName(String webLastName) {
		this.webLastName = webLastName;
	}

	/**
	 * @return the webFirstName
	 */
	public String getWebFirstName() {
		return webFirstName;
	}

	/**
	 * @param webFirstName
	 *            the webFirstName to set
	 */
	public void setWebFirstName(String webFirstName) {
		this.webFirstName = webFirstName;
	}

	/**
	 * @return the webMiddleName
	 */
	public String getWebMiddleName() {
		return webMiddleName;
	}

	/**
	 * @param webMiddleName
	 *            the webMiddleName to set
	 */
	public void setWebMiddleName(String webMiddleName) {
		this.webMiddleName = webMiddleName;
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
	 * @return the lastLoginDate
	 */
	public String getLastLoginDate() {
		return lastLoginDate;
	}

	/**
	 * @param lastLoginDate
	 *            the lastLoginDate to set
	 */
	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	/**
	 * @return the loginActive
	 */
	public boolean isLoginActive() {
		return loginActive;
	}

	/**
	 * @param loginActive
	 *            the loginActive to set
	 */
	public void setLoginActive(boolean loginActive) {
		this.loginActive = loginActive;
	}

	/**
	 * @return the fvExists
	 */
	public boolean isFvExists() {
		return fvExists;
	}

	/**
	 * @param fvExists
	 *            the fvExists to set
	 */
	public void setFvExists(boolean fvExists) {
		this.fvExists = fvExists;
	}

	/**
	 * @return the caseRelshp
	 */
	public String getCaseRelshp() {
		return caseRelshp;
	}

	/**
	 * @param caseRelshp
	 *            the caseRelshp to set
	 */
	public void setCaseRelshp(String caseRelshp) {
		this.caseRelshp = caseRelshp;
	}

	/**
	 * @return the dualRole
	 */
	public boolean isDualRole() {
		return dualRole;
	}

	/**
	 * @param dualRole
	 *            the dualRole to set
	 */
	public void setDualRole(boolean dualRole) {
		this.dualRole = dualRole;
	}

	/**
	 * @return the realTime
	 */
	public boolean isRealTime() {
		return realTime;
	}

	/**
	 * @param realTime
	 *            the realTime to set
	 */
	public void setRealTime(boolean realTime) {
		this.realTime = realTime;
	}

	/**
	 * @return the dateAsOf
	 */
	public String getDateAsOf() {
		return dateAsOf;
	}

	/**
	 * @param dateAsOf
	 *            the dateAsOf to set
	 */
	public void setDateAsOf(String dateAsOf) {
		this.dateAsOf = dateAsOf;
	}

	/**
	 * @return the timeAsOf
	 */
	public String getTimeAsOf() {
		return timeAsOf;
	}

	/**
	 * @param timeAsOf
	 *            the timeAsOf to set
	 */
	public void setTimeAsOf(String timeAsOf) {
		this.timeAsOf = timeAsOf;
	}

	/**
	 * @return the isScheduled
	 */
	public boolean isScheduled() {
		return isScheduled;
	}

	/**
	 * @param isScheduled
	 *            the isScheduled to set
	 */
	public void setScheduled(boolean isScheduled) {
		this.isScheduled = isScheduled;
	}

	/**
	 * @return the isEmployed
	 */
	public boolean isEmployed() {
		return isEmployed;
	}

	/**
	 * @param isEmployed
	 *            the isEmployed to set
	 */
	public void setEmployed(boolean isEmployed) {
		this.isEmployed = isEmployed;
	}

	/**
	 * @return the isDelinquent
	 */
	public boolean isDelinquent() {
		return isDelinquent;
	}

	/**
	 * @param isDelinquent
	 *            the isDelinquent to set
	 */
	public void setDelinquent(boolean isDelinquent) {
		this.isDelinquent = isDelinquent;
	}

	/**
	 * @return the isMailPresent
	 */
	public boolean isMailPresent() {
		return isMailPresent;
	}

	/**
	 * @param isMailPresent
	 *            the isMailPresent to set
	 */
	public void setMailPresent(boolean isMailPresent) {
		this.isMailPresent = isMailPresent;
	}

	/**
	 * @return the pymtReminders
	 */
	public ArrayList getPymtReminders() {
		return pymtReminders;
	}

	/**
	 * @return the isMpiTied
	 */
	public boolean isMpiTied() {
		return isMpiTied;
	}

	/**
	 * @param isMpiTied
	 *            the isMpiTied to set
	 */
	public void setMpiTied(boolean isMpiTied) {
		this.isMpiTied = isMpiTied;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId
	 *            the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

}
