/**
 * 
 */
package nc.dhhs.nccss.acts.dao.rowmap;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import nc.dhhs.nccss.acts.ecoa.beans.Alert;

/**
 * @author Vijay Peddapalli
 *
 */
public class AlertRowMapper implements RowMapper<Alert>
{

	protected final Logger logger = Logger.getLogger(AlertRowMapper.class);

	/*
	 * (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
	 * int)
	 */

	@Override
	public Alert mapRow(ResultSet rs, int rowNum) throws SQLException
	{
		logger.debug("IN: AlertRowMapper- mapRow");

		Alert alert = new Alert();

		if (rs.getString("ID_NEWS") != null && !rs.getString("ID_NEWS").equals(""))
		{
			alert.setNewsId(rs.getString("ID_NEWS").trim());
		}

		if (rs.getString("ID_NEWS_PRIORITY") != null && !rs.getString("ID_NEWS_PRIORITY").equals(""))
		{
			alert.setPriorityID(rs.getString("ID_NEWS_PRIORITY").trim());
		}

		if (rs.getString("DT_NEWS_START") != null && !rs.getString("DT_NEWS_START").equals(""))
		{
			alert.setNewsStartDt(rs.getDate("DT_NEWS_START"));
		}
		if (rs.getString("DT_NEWS_END") != null && !rs.getString("DT_NEWS_END").equals(""))
		{
			alert.setNewsEndDt(rs.getDate("DT_NEWS_END"));
		}

		if (rs.getString("DE_NEWS_TITLE") != null && !rs.getString("DE_NEWS_TITLE").equals(""))
		{
			alert.setNewsTitle(rs.getString("DE_NEWS_TITLE").trim());
		}

		if (rs.getString("DE_NEWS_TEXT") != null && !rs.getString("DE_NEWS_TEXT").equals(""))
		{
			alert.setNewsText(rs.getString("DE_NEWS_TEXT").trim());
		}

		return alert;
	}

}
