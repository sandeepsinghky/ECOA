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

import nc.dhhs.nccss.acts.dao.RequestPaymentDao;
import nc.dhhs.nccss.acts.ecoa.web.config.WebsiteConfiguration;

/**
 * @author Mallika Velagapudi
 *
 */

@Repository
public class RequestPaymentDaoImpl implements RequestPaymentDao {

	protected final Logger logger = Logger.getLogger(RequestPaymentDaoImpl.class);

	private JdbcTemplate jdbcTemplate, jdbcTemplate1;

	/**
	 * @param dataSource
	 */
	@Autowired
	public void setDataSource(DataSource dataSource) {

		logger.debug("\n********** IN RequestPaymentDaoImpl: SETDATASOURCE**********\n");

		this.jdbcTemplate = new JdbcTemplate(dataSource);

		this.jdbcTemplate.setSkipUndeclaredResults(true);

		this.jdbcTemplate1 = new JdbcTemplate(dataSource);

		this.jdbcTemplate1.setSkipUndeclaredResults(true); // this template
															// generates max one
															// row as result for
															// query.

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * nc.dhhs.nccss.acts.dao.RequestPaymentDao#getCallCenterAddress(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public SqlRowSet getCallCenterAddress(String id3pty, String id3ptyType) throws Exception {

		logger.debug("\n********** IN RequestPaymentDaoImpl: getCallCenterAddress**********\n");

		SqlRowSet rowSet = null;

		String sql = "Select * " + "  from " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_third_party "
				+ "	Where id_3pty = '" + id3pty + "' " + "   and cd_3pty_type = '" + id3ptyType + "' "
				+ " Order by ad_3pty_cty ";

		this.jdbcTemplate1.setMaxRows(1);

		rowSet = new SimpleJdbcCall(jdbcTemplate1).getJdbcTemplate().queryForRowSet(sql);

		return rowSet;

	}

	/**
	 * Method to build the SQL Query to obtain Part Case Details for a Case
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * nc.dhhs.nccss.acts.dao.RequestPaymentDao#getPartInfo(java.lang.String)
	 */
	@Override
	public SqlRowSet getPartInfo(String mpi) throws Exception {
		logger.debug("\n********** IN RequestPaymentDaoImpl: getPartIn**********\n");
		String sql = "";
		SqlRowSet rowSet = null;
		sql = "Select a.id_part,a.nm_part_l, a.nm_part_f, a.nm_part_m, a.nb_ssn, a.dt_brth" + "   from "
				+ WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_participant a " + "  where id_part = '" + mpi + "'";
		this.jdbcTemplate1.setMaxRows(1);
		rowSet = new SimpleJdbcCall(jdbcTemplate1).getJdbcTemplate().queryForRowSet(sql);
		return rowSet;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nc.dhhs.nccss.acts.dao.RequestPaymentDao#getCases(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public SqlRowSet getCases(String mpi, String relshp) throws Exception {
		logger.debug("\n********** IN RequestPaymentDaoImpl: getCases**********\n");
		SqlRowSet rowSet = null;
		String sql =

				"Select nb_case, cd_case_stat " + " from " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_part_case "
						+ " Where id_part = '" + mpi + "'" + "   and cd_case_relshp = '" + relshp + "'"
						+ "   and cd_part_stat = 'A' " + " Order by nb_case";

		rowSet = new SimpleJdbcCall(jdbcTemplate).getJdbcTemplate().queryForRowSet(sql);
		return rowSet;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nc.dhhs.nccss.acts.dao.RequestPaymentDao#getParent(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public SqlRowSet getParent(String ivdCase, String type) throws Exception {

		logger.debug("\n********** IN RequestPaymentDaoImpl:getParent**********\n");

		SqlRowSet rowSet = null;

		String sql = "Select a.id_part " + " from " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_part_case a "
				+ " Where a.nb_case  = '" + ivdCase + "'" + "   and a.cd_case_relshp = '" + type + "'"
				+ "   and a.cd_part_stat = 'A' ";

		this.jdbcTemplate1.setMaxRows(1);

		rowSet = new SimpleJdbcCall(jdbcTemplate1).getJdbcTemplate().queryForRowSet(sql);

		return rowSet;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nc.dhhs.nccss.acts.dao.RequestPaymentDao#getSystemAccount(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public SqlRowSet getSystemAccount(String id3pty, String id3ptyType) throws Exception {

		logger.debug("\n********** IN RequestPaymentDaoImpl:getSystemAccount**********\n");

		String sql = "";

		SqlRowSet rowSet = null;

		sql = "Select nm_3pty from " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_third_party "
				+ " Where id_3pty = '" + id3pty + "' " + "   and cd_3pty_type = '" + id3ptyType + "'";

		this.jdbcTemplate1.setMaxRows(1);

		rowSet = new SimpleJdbcCall(jdbcTemplate1).getJdbcTemplate().queryForRowSet(sql);

		return rowSet;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * nc.dhhs.nccss.acts.dao.RequestPaymentDao#getParticipant(java.lang.String)
	 */
	@Override
	public SqlRowSet getParticipant(String strID) throws Exception {

		logger.debug("\n********** IN RequestPaymentDaoImpl:getParticipant**********\n");

		SqlRowSet rowSet = null;

		String sql = "Select nm_part_l,nm_part_f,nm_part_m " + "   from " + WebsiteConfiguration.getDbSchemaSQL()
				+ ".fkkt_participant " + "  where id_part = '" + strID + "'";

		this.jdbcTemplate1.setMaxRows(1);

		rowSet = new SimpleJdbcCall(jdbcTemplate1).getJdbcTemplate().queryForRowSet(sql);

		return rowSet;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * nc.dhhs.nccss.acts.dao.RequestPaymentDao#getChildren(java.lang.String)
	 */
	@Override
	public SqlRowSet getChildren(String ivdCase) throws Exception {

		logger.debug("\n********** IN RequestPaymentDaoImpl:getChildren**********\n");
		SqlRowSet rowSet = null;

		String sql = "Select b.nm_part_l, b.nm_part_f, b.nm_part_m " + " from " + WebsiteConfiguration.getDbSchemaSQL()
				+ ".fkkt_part_case a, " + "      " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_participant b "
				+ " Where a.nb_case  = '" + ivdCase + "'" + "   and a.cd_case_relshp = 'CHLD'"
				+ "   and a.cd_part_stat = 'A' " + "   and b.id_part = a.id_part ";

		rowSet = new SimpleJdbcCall(jdbcTemplate).getJdbcTemplate().queryForRowSet(sql);

		return rowSet;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * nc.dhhs.nccss.acts.dao.RequestPaymentDao#getCPPayments(java.lang.String,
	 * java.sql.Date, java.sql.Date)
	 */
	@Override
	public SqlRowSet getCPPayments(String mpi, java.sql.Date startDate, java.sql.Date endDate) throws Exception {
		// Build the sql string using uid and password passed from the client

		logger.debug("\n********** IN RequestPaymentDaoImpl:getCPPayments**********\n");

		SqlRowSet rowSet = null;

		String sql = "Select b.nb_chk, b.dt_chk_prt, b.nb_case, sum(b.am_disb) " + " from "
				+ WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_journal_tran a, " + "      "
				+ WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_check_comp b "
				+ " Where a.nb_mo_jnlevt = b.nb_mo_jnlevt " + "   and a.nb_btch_jnlevt = b.nb_btch_jnlevt "
				+ "   and a.nb_itm_jnlevt = b.nb_itm_jnlevt " + "   and a.nb_trn = b.nb_trn "
				+ "   and a.cd_type_jnlevt = b.cd_type_jnlevt" + "   and a.cd_type_jnlevt = 'DSB' "
				+ "   and b.id_payee = '" + mpi + "'" + "   and a.dt_trn between '" + startDate + "' and '" + endDate
				+ "'" + "   and b.cd_chk_stat not in ('RCKS','RCKC','RCKD') "
				+ " Group by b.nb_chk, b.dt_chk_prt, b.nb_case " + " Order by b.dt_chk_prt ";

		rowSet = new SimpleJdbcCall(jdbcTemplate).getJdbcTemplate().queryForRowSet(sql);

		return rowSet;

	}

	// Method to build SQL Query to obtain NCP Payments Creation date:

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * nc.dhhs.nccss.acts.dao.RequestPaymentDao#getNcpPayments(java.lang.String,
	 * java.sql.Date, java.util.Date)
	 */
	public SqlRowSet getNcpPayments(String mpi, java.sql.Date startDate, java.util.Date endDate) throws Exception {

		logger.debug("\n********** IN RequestPaymentDaoImpl:getNcpPayments**********\n");
		SqlRowSet rowSet = null;

		String sql = "Select b.dt_trn, b.am_trn_apld, a.cd_pyt_srce, b.id_acct_to, "
				+ "  b.nb_trn, b.nb_mo_jnlevt, b.nb_btch_jnlevt, "
				+ "       b.nb_itm_jnlevt, a.cd_type_jnlevt,b.cd_type_jnlevt_src " + " from "
				+ WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_journal_event a, " + "      "
				+ WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_journal_tran b "
				+ " Where a.cd_type_jnlevt in ('ADJ','RCP') " + "   and b.id_acct_fr = '" + mpi + "'"
				+ "   and b.cd_trn_type = 'ALOC'" + "   and b.dt_trn between '" + startDate + "' and '" + endDate + "'"
				+ "   and a.nb_mo_jnlevt = b.nb_mo_jnlevt " + "   and a.nb_btch_jnlevt = b.nb_btch_jnlevt "
				+ "   and a.nb_itm_jnlevt = b.nb_itm_jnlevt " + "   and a.cd_type_jnlevt = b.cd_type_jnlevt"
				+ " Order by b.dt_trn ";

		rowSet = new SimpleJdbcCall(jdbcTemplate).getJdbcTemplate().queryForRowSet(sql);

		return rowSet;
	}

	// request coupon queries
	// participant address to send coupon through mail.

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * nc.dhhs.nccss.acts.dao.RequestPaymentDao#getMailAddress(java.lang.String)
	 */
	public SqlRowSet getMailAddress(String mpi) throws Exception {
		logger.debug("\n********** IN RequestPaymentDaoImpl:getMailAddress**********\n");

		SqlRowSet rowSet = null;

		String sql = "Select * from " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_part_address "
				+ " Where id_part = '" + mpi + "' " + "   and cd_ad_type = 'MAIL' ";

		rowSet = new SimpleJdbcCall(jdbcTemplate).getJdbcTemplate().queryForRowSet(sql);

		return rowSet;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * nc.dhhs.nccss.acts.dao.RequestPaymentDao#createWebTran(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public void createWebTran(String mpi, String caseRelshp, String ivdCase) throws Exception {

		logger.debug("\n********** In RequestPaymentDaoImpl:createWebTran**********\n");

		java.util.Date dt = new java.util.Date();

		java.sql.Date currentDate = new java.sql.Date(dt.getTime());

		java.sql.Time currentTime = new java.sql.Time(dt.getTime());

		java.sql.Timestamp tsCreate = new java.sql.Timestamp(dt.getTime());

		String sql = "INSERT INTO " + WebsiteConfiguration.getDbSchemaSQL()
				+ ".FKKT_WEB_TRAN ( ID_PART, TS_CREATE, CD_REC_TYPE, IN_PROCESSED_IND, DT_LST_UPD, TM_LST_UPD, ID_WRKR_LST_UPD, ID_TRML_LST_UPD, DATA_SRC)	VALUES ( ? ,? ,? ,? ,? ,? ,? ,? ,? )";

		new SimpleJdbcCall(jdbcTemplate).getJdbcTemplate().update(sql, new Object[] { mpi, tsCreate, "PC", "N",
				currentDate, currentTime, "eonline", "eonline", caseRelshp + ivdCase });

	}

}
