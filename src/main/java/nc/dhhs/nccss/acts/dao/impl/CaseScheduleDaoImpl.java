/**
 * 
 */
package nc.dhhs.nccss.acts.dao.impl;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import nc.dhhs.nccss.acts.dao.CaseScheduleDao;
import nc.dhhs.nccss.acts.ecoa.web.config.WebsiteConfiguration;

/**
 * @author Mallika Velagapudi
 *
 */

@Repository
public class CaseScheduleDaoImpl implements CaseScheduleDao
{

	protected final Logger	logger	= Logger.getLogger(CaseScheduleDaoImpl.class);

	private JdbcTemplate	jdbcTemplate, jdbcTemplate1;

	/**
	 * @param dataSource
	 */
	@Autowired
	public void setDataSource(DataSource dataSource)
	{

		logger.debug("\n********** IN ParentCaseScheduleDaoImpl: SETDATASOURCE**********\n");

		this.jdbcTemplate = new JdbcTemplate(dataSource);

		this.jdbcTemplate.setSkipUndeclaredResults(true);

		this.jdbcTemplate1 = new JdbcTemplate(dataSource);

		this.jdbcTemplate1.setSkipUndeclaredResults(true); //this template generates max one row as result for query.

		this.jdbcTemplate1.setMaxRows(1);

	}

	/**
	 * Method to build the SQL Query to obtain all the appointments for the
	 * requested case.
	 */
	public SqlRowSet getAppointments(String ivdCase, String caseRelshp) throws SQLException
	{
		logger.debug("\n********** IN ParentCaseScheduleDaoImpl: getAppointments");

		String sql = "";

		if (caseRelshp.equals("CLI"))
		{
			sql = "Select dt_evt_eff, cd_evt_type, cd_disptn_evt, de_title_evt " + " from " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_case_event " + " Where nb_acct = '" + ivdCase + "'" + "   and cd_disptn_evt <> 'OOPS'" +

					"   and cd_evt_type in ('CLFI','GCLI','GTAP','ICLI','ICL2'," + "   'ICWM','IMMI','GAPI') " + "Order by dt_evt_eff desc ";
		}
		else
		{
			sql = "Select dt_evt_eff, cd_evt_type, cd_disptn_evt, de_title_evt " + " from " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_case_event " + " Where nb_acct = '" + ivdCase + "'" + "   and cd_disptn_evt <> 'OOPS'" +

					"   and cd_evt_type in ('APFI','GTAP','IAPI','GAPI') " + "Order by dt_evt_eff desc ";
		}

		SqlRowSet rowSet = new SimpleJdbcCall(jdbcTemplate).getJdbcTemplate().queryForRowSet(sql);

		return rowSet;

	}

	/**
	 * Method to build the SQL Query to obtain all the hearings for the
	 */
	public SqlRowSet getHearing(String ivdCase) throws Exception
	{
		logger.debug("\n********** IN ParentCaseScheduleDaoImpl: getHearing");

		String sql = "Select dt_evt_eff,cd_evt_type, cd_disptn_evt,de_title_evt " + "from " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_case_event " + "Where nb_acct = '" + ivdCase + "'" +

				"  and cd_disptn_evt <> 'OOPS'" + "  and cd_evt_type in ('HEPS','HESU','HCRS','HCIS','HLAR'," + " 'HOSC','HREV','HRAD','HAPP','HINT'," + " 'HCVE','HPPA','HFPM','HESB','HJEM'," + " 'HCRC','HELB','HURS','HRFS','HCGB'," + "  'HESV','HRED','HCJL','HOTH','HENC'," + "  'HEFA','HEMS','AFTX','ASTX','ACBR','ADIS') " + "Order by dt_evt_eff desc ";

		SqlRowSet rowSet = new SimpleJdbcCall(jdbcTemplate).getJdbcTemplate().queryForRowSet(sql);

		return rowSet;
	}

	/**
	 * Method to build the SQL Query to obtain Schedule Information for a case
	 * and schedule type
	 */
	public SqlRowSet getScheduleInfo(String ivdCase, String dt, String type) throws Exception
	{
		logger.debug("\n********** IN ParentCaseScheduleDaoImpl: getScheduleInfo");

		java.sql.Date date = java.sql.Date.valueOf(dt);

		String sql = "Select cd_sched_type,cd_fips_sched,id_sched_hear_loc" + " from " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_court_sched a " + " Where nb_case = '" + ivdCase + "'" + "   and cd_disptn_sched = '" + type + "'" + "   and dt_sched_eff = '" + date + "'";

		SqlRowSet rowSet = new SimpleJdbcCall(jdbcTemplate1).getJdbcTemplate().queryForRowSet(sql);

		return rowSet;
	}

	/**
	 * Method to build the SQL Query to obtain the addres for the third party
	 * agency .
	 */
	public SqlRowSet getLocation(String id3pty, String cd3pty) throws Exception
	{
		logger.debug("\n********** IN ParentCaseScheduleDaoImpl: getLocation");

		String sql = "Select ad_3pty_ln_1,ad_3pty_ln_2,ad_3pty_cty,ad_3pty_st,ad_3pty_zip_5,ad_3pty_zip_4 " + "  from " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_third_party  " + " Where id_3pty = '" + id3pty + "'" + "   and cd_3pty_type = '" + cd3pty + "'";

		SqlRowSet rowSet = new SimpleJdbcCall(jdbcTemplate).getJdbcTemplate().queryForRowSet(sql);

		return rowSet;
	}

	/**
	 * Insert the method's description here.
	 */
	public SqlRowSet getCounty(String id3pty) throws Exception
	{

		String state = id3pty.substring(0, 2);

		String county = id3pty.substring(2, 5);

		String sql = "Select nm_cnty " + "  from " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_fips_codes  " + " Where cd_state = '" + state + "'" + "   and cd_fips_cnty = '" + county + "'";

		SqlRowSet rowSet = new SimpleJdbcCall(jdbcTemplate).getJdbcTemplate().queryForRowSet(sql);

		return rowSet;
	}

}
