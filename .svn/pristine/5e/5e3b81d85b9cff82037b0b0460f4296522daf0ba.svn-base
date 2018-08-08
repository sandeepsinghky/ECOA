/**
 * 
 */
package nc.dhhs.nccss.acts.dao.impl;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import nc.dhhs.nccss.acts.dao.ParentUserInfoDao;
import nc.dhhs.nccss.acts.ecoa.web.config.WebsiteConfiguration;

/**
 * @author Mallika Velagapudi
 *
 */

@Repository
public class ParentUserInfoDaoImpl implements ParentUserInfoDao
{

	protected final Logger	logger	= Logger.getLogger(UserDaoImpl.class);

	private JdbcTemplate	jdbcTemplate, jdbcTemplate1;

	/**
	 * @param dataSource
	 */
	@Autowired
	public void setDataSource(DataSource dataSource)
	{

		logger.debug("\n********** IN UserDaoImpl: SETDATASOURCE**********\n");

		this.jdbcTemplate = new JdbcTemplate(dataSource);

		this.jdbcTemplate.setSkipUndeclaredResults(true);

		this.jdbcTemplate1 = new JdbcTemplate(dataSource);

		this.jdbcTemplate1.setSkipUndeclaredResults(true); //this template generates max one row as result for query.

		this.jdbcTemplate1.setMaxRows(1);

	}

	//
	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.ParentUserInfoDao#getDualRole(java.lang.String)
	 */
	@Override
	public SqlRowSet getDualRole(String mpi) throws Exception
	{
		logger.debug("\n********** IN ParentUserInfoDaoImpl:getDualRole");

		String sql = "Select *  from " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_part_case " + " Where id_part = '" + mpi + "'" + "   and cd_case_relshp in ('AP','CLI') " + "   and cd_part_stat = 'A' " + " Order by  cd_case_relshp ";

		SqlRowSet rowSet = new SimpleJdbcCall(jdbcTemplate).getJdbcTemplate().queryForRowSet(sql);

		return rowSet;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.ParentUserInfoDao#getPartDetails(java.lang.String)
	 */
	@Override
	public SqlRowSet getPartDetails(String mpi) throws Exception
	{
		logger.debug("\n********** IN ParentUserInfoDaoImpl:getPartDetails");

		String sql = "Select a.id_part,a.nm_part_l, a.nm_part_f, a.nm_part_m, a.nb_ssn, a.dt_brth" + "         from " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_participant a    " + "	where a.id_part = '" + mpi + "'";

		SqlRowSet rowSet = new SimpleJdbcCall(jdbcTemplate1).getJdbcTemplate().queryForRowSet(sql);

		return rowSet;
	}

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.dao.ParentUserInfoDao#updateLastLogin(java.lang.
	 * String)
	 */
	@Override
	public int updateLastLogin(String mpi) throws Exception
	{
		//update FKKT_WEB_USER_AUTH table with current date and time for DT_LST_UPD, TM_LST_UPD and DT_LST_LOGIN columns

		logger.debug("\n********** IN ParentUserInfoDaoImpl:updateLastLogin");

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		Date currentDate = new Date(timestamp.getTime());

		Time currentTime = new Time(timestamp.getTime());

		String sql = "UPDATE " + WebsiteConfiguration.getDbSchemaSQL() + ".FKKT_WEB_USER_AUTH " + "SET DT_LST_UPD = '" + currentDate + "'" + ",TM_LST_UPD = '" + currentTime + "'" + ", DT_LST_LOGIN = '" + currentDate + "' WHERE ID_PART ='" + mpi + "'";

		int count = new SimpleJdbcCall(jdbcTemplate).getJdbcTemplate().update(sql);

		return count;
	}

	/**
	 * Method to build the SQL Query to obtain all the hearings for the
	 * requested participant.
	 * 
	 * @return java.lang.String
	 */
	@Override
	public int getAllAppointments(String mpi) throws Exception
	{
		logger.debug("\n********** IN ParentUserInfoDaoImpl:getAllAppointments");

		java.util.Date dt = new java.util.Date();

		java.sql.Date currentDate = new java.sql.Date(dt.getTime());

		String sql = "Select count(*) " + "from " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_case_event a, " + " " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_part_case b " + "Where b.id_part = '" + mpi + "'" + "  and b.cd_case_relshp in ('AP','CLI')" + "  and b.cd_part_stat = 'A'" + "  and a.nb_acct = b.nb_case" + "  and dt_evt_eff >= '" + currentDate + "'" + "  and cd_disptn_evt <> 'OOPS'" + "  and cd_evt_type in ('CLFI','GCLI','GTAP','ICLI','ICL2'," + " 'ICWM','IMMI','APFI','GTAP','IAPI') ";

		int count = new SimpleJdbcCall(jdbcTemplate).getJdbcTemplate().queryForInt(sql);

		return count;
	}

	/**
	 * Method to build the SQL Query to obtain all the hearings for the
	 * requested participant.
	 * 
	 * @return java.lang.String
	 */
	@Override
	public int getAppointments(String mpi, String caseRelshp) throws Exception
	{
		logger.debug("\n********** IN ParentUserInfoDaoImpl:getAppointments");

		java.util.Date dt = new java.util.Date();

		java.sql.Date currentDate = new java.sql.Date(dt.getTime());

		String sql = "";

		int count;

		if (caseRelshp.equals("CLI"))
		{
			sql = "Select count(*) " + "from " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_case_event a, " + "     " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_part_case b " + "Where b.id_part = '" + mpi + "'" + "  and b.cd_case_relshp in ('CLI')" + "  and b.cd_part_stat = 'A'" + "  and a.nb_acct = b.nb_case" + "  and dt_evt_eff >= '" + currentDate + "'" + "   and cd_disptn_evt <> 'OOPS'" + "   and cd_evt_type in ('CLFI','GCLI','GTAP','ICLI','ICL2'," + "  'ICWM','IMMI') ";

			count = new SimpleJdbcCall(jdbcTemplate).getJdbcTemplate().queryForInt(sql);
		}
		else
		{
			sql = "Select count(*) " + "from " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_case_event a, " + "     " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_part_case b " + "Where b.id_part = '" + mpi + "'" + "  and b.cd_case_relshp in ('AP')" + "  and b.cd_part_stat = 'A'" + "  and a.nb_acct = b.nb_case" + "  and dt_evt_eff >= '" + currentDate + "'" + "   and cd_disptn_evt <> 'OOPS'" + "   and cd_evt_type in ('APFI','GTAP','IAPI') ";

			count = new SimpleJdbcCall(jdbcTemplate).getJdbcTemplate().queryForInt(sql);
		}
		return count;
	}

	/**
	 * Method to build the SQL Query to obtain all the hearings for the
	 * requested case.
	 * 
	 * @return java.lang.String
	 */
	@Override
	public int getHearing(String mpi) throws Exception
	{
		logger.debug("\n********** IN ParentUserInfoDaoImpl:getHearing");

		java.util.Date dt = new java.util.Date();

		java.sql.Date currentDate = new java.sql.Date(dt.getTime());

		// Build the sql string using strCase
		String sql = "Select count(*) " + "from " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_case_event a, " + "     " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_part_case  b " + "Where b.id_part = '" + mpi + "'" + "  and b.cd_case_relshp in ('AP','CLI') " + "  and a.nb_acct = b.nb_case " + "  and dt_evt_eff >= '" + currentDate + "'" + "  and cd_disptn_evt <> 'OOPS'" + "  and cd_evt_type in ('HEPS','HESU','HCRS','HCIS','HLAR'," + "                      'HOSC','HREV','HRAD','HAPP','HINT'," + "                      'HCVE','HPPA','HFPM','HESB','HJEM'," + "                      'HCRC','HELB','HURS','HRFS','HCGB'," + "                      'HESV','HRED','HCJL','HOTH','HENC'," + "                      'HEFA','HEMS','AFTX','ASTX','ACBR','ADIS') ";

		int count = new SimpleJdbcCall(jdbcTemplate).getJdbcTemplate().queryForInt(sql);

		return count;
	}

	/**
	 * Method to build the SQL Query to obtain the mail address of the requested
	 * participant.
	 */
	@Override
	public int getMailAddress(String mpi) throws Exception
	{
		logger.debug("\n********** IN ParentUserInfoDaoImpl:getMailAddress");

		String strAddrType = "MAIL";

		String sql = "Select count(*)" + "  from " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_part_address " + " Where id_part = '" + mpi + "'" + "   and cd_ad_type = '" + strAddrType + "'";

		int count = new SimpleJdbcCall(jdbcTemplate).getJdbcTemplate().queryForInt(sql);

		return count;
	}

	/**
	 * Method to build the SQL Query to obtain the mail address of the requested
	 * participant.
	 * 
	 */
	@Override
	public SqlRowSet getAOCMailAddress(String mpi) throws Exception
	{

		logger.debug("\n********** IN ParentUserInfoDaoImpl:getAOCMailAddress");

		String sql = "Select b.ad_invl_ln_1, b.ad_invl_cty " + "  from " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_aoc_invl a, " + "       " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_aoc_address b " + " Where a.nb_acts_id = '" + mpi + "'" + "   and a.in_acts_id = 'A' " + "   and b.nb_dkt = a.nb_dkt " + "   and b.cd_invl = a.cd_invl ";

		SqlRowSet rowSet = new SimpleJdbcCall(jdbcTemplate1).getJdbcTemplate().queryForRowSet(sql);

		return rowSet;

	}

	/**
	 * Method to build the SQL Query to obtain employment information for a
	 * Participant.
	 * 
	 * @param mpij
	 *            ava.lang.String
	 * 
	 */
	@Override
	public SqlRowSet getEmployment(String mpi) throws Exception
	{
		logger.debug("\n********** IN ParentUserInfoDaoImpl:getEmployment");

		java.util.Date dt = new java.util.Date();

		java.sql.Date currentDate = new java.sql.Date(dt.getTime());

		java.sql.Date defaultDate = new java.sql.Date(dt.getTime());

		String sql = "Select dt_emplm_end " + "  from " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_employment " + " Where id_part = '" + mpi + "'" + "   and dt_emplm_strt <= '" + currentDate + "'";

		SqlRowSet rowSet = new SimpleJdbcCall(jdbcTemplate).getJdbcTemplate().queryForRowSet(sql);

		return rowSet;
	}

}
