package nc.dhhs.nccss.acts.ecoa.web.exception;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;

import nc.dhhs.nccss.acts.ecoa.beans.UserInformation;

/**
 * @author Phani Konuru
 * 
 */

public class ErrorDescriptor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7447090802173040180L;

	// The Class where the error occurred
	private String errClass = "";

	// The Method within the Class that the error occurred
	private String errMethod = "";

	// The Error Message String that was set
	private String errMessage = "";

	// The actual time the error descriptor object was created
	private Timestamp timestamp;

	private String userId = "";

	private String userType = "";

	private String applicationId = "";

	private String displayMsg = "";

	private Object errObject;

	private int sqlCode;

	private String strSQLState = "";

	private boolean isSQLerror = false;

	public ErrorDescriptor(String errClass, String errMethod, String errMessage, Object errObject) {
		this.errClass = errClass;

		this.errMethod = errMethod;

		this.errObject = errObject;

		this.errMessage = errMessage;

		if (errObject instanceof java.sql.SQLException) {
			java.sql.SQLException e = (java.sql.SQLException) errObject;
			this.sqlCode = e.getErrorCode();
			this.strSQLState = e.getSQLState();
			this.isSQLerror = true;
		}

		Calendar c = Calendar.getInstance();

		java.util.Date now = c.getTime();

		java.sql.Date date = new java.sql.Date(now.getTime());

		timestamp = new Timestamp(date.getTime());
	}

	/**
	 * @return the errClass
	 */
	public String getErrClass() {
		return errClass;
	}

	/**
	 * @param errClass
	 *            the errClass to set
	 */
	public void setErrClass(String errClass) {
		this.errClass = errClass;
	}

	/**
	 * @return the errMethod
	 */
	public String getErrMethod() {
		return errMethod;
	}

	/**
	 * @param errMethod
	 *            the errMethod to set
	 */
	public void setErrMethod(String errMethod) {
		this.errMethod = errMethod;
	}

	/**
	 * @return the errMessage
	 */
	public String getErrMessage() {
		return errMessage;
	}

	/**
	 * @param errMessage
	 *            the errMessage to set
	 */
	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}

	/**
	 * @return the timestamp
	 */
	public Timestamp getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp
	 *            the timestamp to set
	 */
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
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
	 * @return the applicationId
	 */
	public String getApplicationId() {
		return applicationId;
	}

	/**
	 * @param applicationId
	 *            the applicationId to set
	 */
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	/**
	 * @return the displayMsg
	 */
	public String getDisplayMsg() {
		return displayMsg;
	}

	/**
	 * @param displayMsg
	 *            the displayMsg to set
	 */
	public void setDisplayMsg(String displayMsg) {
		this.displayMsg = displayMsg;
	}

	public String getEmailBody() {
		StringBuffer sb1 = new StringBuffer();
		sb1.append(getTimestamp() + ": eChild Support WebSite Error\n\n");
		if (getUserId() != null && !getUserId().equals(""))
			sb1.append("UserId: " + getUserId() + "\n\n");
		if (getUserType() != null && !getUserType().equals(""))
			sb1.append("userType: " + getUserType() + "\n\n");
		if (getApplicationId() != null && !getApplicationId().equals(""))
			sb1.append("Application Id: " + getApplicationId() + "\n\n");
		if (isSQLerror) {
			sb1.append("Exception Type: SQLException, sqlCode=" + getSqlCode() + ", sqlState=" + getStrSQLState()
					+ "\n\n");
		}
		sb1.append("Error Class: " + getErrClass() + "\n\n");
		sb1.append("Error Method: " + getErrMethod() + "\n\n");
		sb1.append("Error Details: " + getErrMessage() + "\n\n");
		return sb1.toString();
	}

	public String getParentEmailBody(UserInformation userInfoBean) {
		StringBuffer body = new StringBuffer();
		body.append(getTimestamp() + ": eChild Support WebSite Error\n\n");

		if (isSQLerror) {
			body.append("Exception Type: SQLException, sqlCode=" + getSqlCode() + ", sqlState=" + getStrSQLState()
					+ "\n\n");
		}

		body.append("Error Class: " + getErrClass() + "\n\n");
		body.append("Error Method: " + getErrMethod() + "\n\n");
		body.append("Error Details: " + getErrMessage() + "\n\n");

		body.append("Login UserId :" + userInfoBean.getUserID() + "\n");

		body.append("UserCaseRelationShip : " + userInfoBean.getCaseRelshp() + "\n");

		body.append("User Type : " + userInfoBean.getUserType() + "\n\n");

		body.append("IsMPITied :" + userInfoBean.isMpiTied() + "\n");

		body.append("MPI : " + userInfoBean.getMpi() + "\n\n");

		return body.toString();

	}

	/**
	 * @return the errObject
	 */
	public Object getErrObject() {
		return errObject;
	}

	/**
	 * @param errObject
	 *            the errObject to set
	 */
	public void setErrObject(Object errObject) {
		this.errObject = errObject;
	}

	/**
	 * @return the strSQLState
	 */
	public String getStrSQLState() {
		return strSQLState;
	}

	/**
	 * @param strSQLState
	 *            the strSQLState to set
	 */
	public void setStrSQLState(String strSQLState) {
		this.strSQLState = strSQLState;
	}

	/**
	 * @return the sqlCode
	 */
	public int getSqlCode() {
		return sqlCode;
	}

	/**
	 * @param sqlCode
	 *            the sqlCode to set
	 */
	public void setSqlCode(int sqlCode) {
		this.sqlCode = sqlCode;
	}

	/**
	 * @return the isSQLerror
	 */
	public boolean isSQLerror() {
		return isSQLerror;
	}

	/**
	 * @param isSQLerror
	 *            the isSQLerror to set
	 */
	public void setSQLerror(boolean isSQLerror) {
		this.isSQLerror = isSQLerror;
	}

}