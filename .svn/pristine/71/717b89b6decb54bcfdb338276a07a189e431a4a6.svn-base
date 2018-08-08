
package nc.dhhs.nccss.acts.ecoa.beans;

import java.text.SimpleDateFormat;
import java.util.Date;

import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * A JavaBean Class that helps store the schedule information that will be used
 * to display the appointments and hearings scheduled for a child support
 * participant.
 * 
 */

public class Schedule implements java.io.Serializable
{

	/**
	 * @return the scheduleDate
	 */
	public Date getScheduleDate()
	{
		return scheduleDate;
	}

	/**
	 * @param scheduleDate
	 *            the scheduleDate to set
	 */
	public void setScheduleDate(Date scheduleDate)
	{
		this.scheduleDate = scheduleDate;
	}

	public String getScheduleDateStrPg()
	{
		if (scheduleDate == null) return "";
		else
			return DATE_FORMAT_SCREEN.format(scheduleDate).trim().equals(AppConstants.PAGE_DEFAULT_DATE) ? "" : DATE_FORMAT_SCREEN.format(scheduleDate).trim();
	}

	public String getScheduleDateStr()
	{
		if (scheduleDate == null) return null;
		else
			return DATE_FORMAT.format(scheduleDate).trim();
	}

	/**
	 * @return the scheduleTime
	 */
	public String getScheduleTime()
	{
		return scheduleTime;
	}

	/**
	 * @param scheduleTime
	 *            the scheduleTime to set
	 */
	public void setScheduleTime(String scheduleTime)
	{
		this.scheduleTime = scheduleTime;
	}

	/**
	 * @return the scheduleType
	 */
	public String getScheduleType()
	{
		return scheduleType;
	}

	/**
	 * @param scheduleType
	 *            the scheduleType to set
	 */
	public void setScheduleType(String scheduleType)
	{
		this.scheduleType = scheduleType;
	}

	/**
	 * @return the scheduleCode
	 */
	public String getScheduleCode()
	{
		return scheduleCode;
	}

	/**
	 * @param scheduleCode
	 *            the scheduleCode to set
	 */
	public void setScheduleCode(String scheduleCode)
	{
		this.scheduleCode = scheduleCode;
	}

	/**
	 * @return the scheduleReason
	 */
	public String getScheduleReason()
	{
		return scheduleReason;
	}

	/**
	 * @param scheduleReason
	 *            the scheduleReason to set
	 */
	public void setScheduleReason(String scheduleReason)
	{
		this.scheduleReason = scheduleReason;
	}

	/**
	 * @return the scheduleAddr1
	 */
	public String getScheduleAddr1()
	{
		return scheduleAddr1;
	}

	/**
	 * @param scheduleAddr1
	 *            the scheduleAddr1 to set
	 */
	public void setScheduleAddr1(String scheduleAddr1)
	{
		this.scheduleAddr1 = scheduleAddr1;
	}

	/**
	 * @return the scheduleAddr2
	 */
	public String getScheduleAddr2()
	{
		return scheduleAddr2;
	}

	/**
	 * @param scheduleAddr2
	 *            the scheduleAddr2 to set
	 */
	public void setScheduleAddr2(String scheduleAddr2)
	{
		this.scheduleAddr2 = scheduleAddr2;
	}

	/**
	 * @return the scheduleCity
	 */
	public String getScheduleCity()
	{
		return scheduleCity;
	}

	/**
	 * @param scheduleCity
	 *            the scheduleCity to set
	 */
	public void setScheduleCity(String scheduleCity)
	{
		this.scheduleCity = scheduleCity;
	}

	/**
	 * @return the scheduleCounty
	 */
	public String getScheduleCounty()
	{
		return scheduleCounty;
	}

	/**
	 * @param scheduleCounty
	 *            the scheduleCounty to set
	 */
	public void setScheduleCounty(String scheduleCounty)
	{
		this.scheduleCounty = scheduleCounty;
	}

	/**
	 * @return the scheduleState
	 */
	public String getScheduleState()
	{
		return scheduleState;
	}

	/**
	 * @param scheduleState
	 *            the scheduleState to set
	 */
	public void setScheduleState(String scheduleState)
	{
		this.scheduleState = scheduleState;
	}

	/**
	 * @return the scheduleZip
	 */
	public String getScheduleZip()
	{
		return scheduleZip;
	}

	/**
	 * @param scheduleZip
	 *            the scheduleZip to set
	 */
	public void setScheduleZip(String scheduleZip)
	{
		this.scheduleZip = scheduleZip;
	}

	/**
	 * @return the scheduleDisp
	 */
	public String getScheduleDisp()
	{
		return scheduleDisp;
	}

	/**
	 * @param scheduleDisp
	 *            the scheduleDisp to set
	 */
	public void setScheduleDisp(String scheduleDisp)
	{
		this.scheduleDisp = scheduleDisp;
	}

	/**
	 * @return the scheduleDispRsn
	 */
	public String getScheduleDispRsn()
	{
		return scheduleDispRsn;
	}

	/**
	 * @param scheduleDispRsn
	 *            the scheduleDispRsn to set
	 */
	public void setScheduleDispRsn(String scheduleDispRsn)
	{
		this.scheduleDispRsn = scheduleDispRsn;
	}

	/**
	 * @return the future
	 */
	public boolean isFuture()
	{
		return future;
	}

	/**
	 * @param future
	 *            the future to set
	 */
	public void setFuture(boolean future)
	{
		this.future = future;
	}

	private static final long	serialVersionUID	= 275066820656775550L;
	private Date				scheduleDate;
	private String				scheduleTime		= "";
	private String				scheduleType		= "";
	private String				scheduleCode		= "";
	private String				scheduleReason		= "";
	private String				scheduleAddr1		= "";
	private String				scheduleAddr2		= "";
	private String				scheduleCity		= "";
	private String				scheduleCounty		= "";
	private String				scheduleState		= "";
	private String				scheduleZip			= "";
	private String				scheduleDisp		= "";
	private String				scheduleDispRsn		= "";
	private boolean				future				= false;
	private String				dispText			= "";

	SimpleDateFormat			DATE_FORMAT			= new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat			DATE_FORMAT_SCREEN	= new SimpleDateFormat("MM/dd/yyyy");

	/**
	 * @return the dispText
	 */
	public String getDispText()
	{
		return dispText;
	}

	/**
	 * @param dispText
	 *            the dispText to set
	 */
	public void setDispText(String dispText)
	{
		this.dispText = dispText;
	}
}
