/**
 * 
 */
package nc.dhhs.nccss.acts.dao.impl;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import nc.dhhs.nccss.acts.dao.SearchClerkOfCourtDao;
import nc.dhhs.nccss.acts.ecoa.web.config.WebsiteConfiguration;

/**
 * @author Mallika Velagapudi
 *
 */

@Repository
public class SearchClerkOfCourtDaoImpl implements SearchClerkOfCourtDao
{

	protected final Logger	logger	= Logger.getLogger(SearchClerkOfCourtDaoImpl.class);

	private JdbcTemplate	jdbcTemplate;

	/**
	 * @param dataSource
	 */
	@Autowired
	public void setDataSource(DataSource dataSource)
	{

		logger.debug("\n********** IN SearchClerkOfCourtDaoImpl: SETDATASOURCE**********\n");

		this.jdbcTemplate = new JdbcTemplate(dataSource);

		this.jdbcTemplate.setSkipUndeclaredResults(true);

	}

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.dao.SearchClerkOfCourtDao#getClerkOfCourt(int,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public SqlRowSet getClerkOfCourt(int type, String strCity, String strCounty, String rectype) throws Exception
	{
		logger.debug("\n********** SearchClerkOfCourtDaoImpl: getClerkOfCourt");

		String sql = "";

		SqlRowSet rowSet = null;

		switch (type)
		{
		case 0:
			sql = "Select * from " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_third_party " + " Where cd_3pty_type = '" + rectype + "'" + "   and substr(id_3pty,1,2) = '37' " + "   and substr(id_3pty,6,5) > '00000' " + "   and substr(id_3pty,6,5) < '99999' " + " Order by ad_3pty_cty ";
			rowSet = new SimpleJdbcCall(jdbcTemplate).getJdbcTemplate().queryForRowSet(sql);
			break;
		case 1:
			sql = "Select * from " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_third_party " + " Where cd_3pty_type = '" + rectype + "'" + "   and substr(id_3pty,1,2) = '37' " + "   and substr(id_3pty,6,5) > '00000' " + "   and substr(id_3pty,6,5) < '99999' " + "   and ad_3pty_cty = '" + strCity + "' " + " Order by ad_3pty_cty ";
			rowSet = new SimpleJdbcCall(jdbcTemplate).getJdbcTemplate().queryForRowSet(sql);
			break;
		case 2:
			sql = "Select * from " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_third_party " + " Where cd_3pty_type = '" + rectype + "'" + "   and substr(id_3pty,1,2) = '37' " + "   and substr(id_3pty,3,3) = '" + strCounty + "' " + "   and substr(id_3pty,6,5) > '00000' " + "   and substr(id_3pty,6,5) < '99999' " + "   and nm_3pty NOT LIKE '%*DO NOT USE*%' " + " Order by ad_3pty_cty ";
			rowSet = new SimpleJdbcCall(jdbcTemplate).getJdbcTemplate().queryForRowSet(sql);
			break;
		case 3:
			sql = "Select * from " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_third_party " + " Where cd_3pty_type = '" + rectype + "'" + "   and substr(id_3pty,1,2) = '37' " + "   and substr(id_3pty,3,3) = '" + strCounty + "' " + "   and substr(id_3pty,6,5) > '00000' " + "   and substr(id_3pty,6,5) < '99999' " + "   and ad_3pty_cty = '" + strCity + "' " + " Order by ad_3pty_cty ";
			rowSet = new SimpleJdbcCall(jdbcTemplate).getJdbcTemplate().queryForRowSet(sql);

			break;
		default:
			break;
		}
		return rowSet;

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.SearchClerkOfCourtDao#getClerkOfCourtDetail(java.
	 * lang.String, java.lang.String)
	 */
	@Override
	public SqlRowSet getClerkOfCourtDetail(String id3pty, String id3ptyType) throws Exception
	{
		logger.debug("\n********** SearchClerkOfCourtDaoImpl: getClerkOfCourtDetail");

		String sql = "";

		SqlRowSet rowSet = null;

		sql = "Select * from " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_third_party " + " Where id_3pty = '" + id3pty + "' " + "   and cd_3pty_type = '" + id3ptyType + "' " + " Order by ad_3pty_cty ";

		rowSet = new SimpleJdbcCall(jdbcTemplate).getJdbcTemplate().queryForRowSet(sql);

		return rowSet;
	}
}
