/**
 * 
 */
package nc.dhhs.nccss.acts.ecoa.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * @author Vijay Peddapalli
 *
 */

public class Alert implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -985139059123995374L;

	protected final Logger		logger				= Logger.getLogger(Alert.class);

	private String				newsId				= "";

	private String				priorityID			= "";

	private Date				newsStartDt;

	private Date				newsEndDt;

	private String				newsTitle			= "";

	private String				newsText			= "";

	SimpleDateFormat			DATE_FORMAT			= new SimpleDateFormat("yyyy-MM-dd");

	public Alert()
	{
		logger.debug("IN: Alert()");
	}

	/**
	 * @return the newsId
	 */
	public String getNewsId()
	{
		return newsId;
	}

	/**
	 * @param newsId
	 *            the newsId to set
	 */
	public void setNewsId(String newsId)
	{
		this.newsId = newsId;
	}

	/**
	 * @return the priorityID
	 */
	public String getPriorityID()
	{
		return priorityID;
	}

	/**
	 * @param priorityID
	 *            the priorityID to set
	 */
	public void setPriorityID(String priorityID)
	{
		this.priorityID = priorityID;
	}

	/**
	 * @return the newsTitle
	 */
	public String getNewsTitle()
	{
		return newsTitle;
	}

	/**
	 * @param newsTitle
	 *            the newsTitle to set
	 */
	public void setNewsTitle(String newsTitle)
	{
		this.newsTitle = newsTitle;
	}

	/**
	 * @return the newsText
	 */
	public String getNewsText()
	{
		return newsText;
	}

	/**
	 * @param newsText
	 *            the newsText to set
	 */
	public void setNewsText(String newsText)
	{
		this.newsText = newsText;
	}

	/**
	 * @return the newsStartDt
	 */
	public Date getNewsStartDt()
	{
		return newsStartDt;
	}

	/**
	 * @param newsStartDt
	 *            the newsStartDt to set
	 */
	public void setNewsStartDt(Date newsStartDt)
	{
		this.newsStartDt = newsStartDt;
	}

	/**
	 * @return the newsEndDt
	 */
	public Date getNewsEndDt()
	{
		return newsEndDt;

	}

	/**
	 * @param newsEndDt
	 *            the newsEndDt to set
	 */
	public void setNewsEndDt(Date newsEndDt)
	{
		this.newsEndDt = newsEndDt;
	}

}
