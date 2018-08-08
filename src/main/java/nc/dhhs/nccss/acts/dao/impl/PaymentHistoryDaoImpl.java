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

import nc.dhhs.nccss.acts.dao.PaymentHistoryDao;
import nc.dhhs.nccss.acts.ecoa.web.config.WebsiteConfiguration;

/**
 * @author Mallika Velagapudi
 *
 */

@Repository
public class PaymentHistoryDaoImpl implements PaymentHistoryDao
{

	protected final Logger	logger	= Logger.getLogger(PaymentHistoryDaoImpl.class);

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

	}

	/**
	 * Method to build the SQL Query to obtain Check Comp Amount
	 * 
	 * - Added cd_3pty_type to the query to filter Third Party Checks
	 * 
	 * @return java.lang.String
	 */
	@Override
	public SqlRowSet getCheckComp(String mpi) throws Exception
	{
		logger.debug("\n********** IN PaymentHistoryDaoImpl: getCheckComp**********\n");

		SqlRowSet rowSet = null;

		String sql = "Select Sum(a.am_disb) " + ", a.id_payee " + ", a.nb_chk " + ", a.dt_chk_prt " + ", a.nb_btch_jnlevt " + ", a.nb_itm_jnlevt " + ", a.nb_mo_jnlevt " + ", a.cd_type_jnlevt " + ", a.cd_chk_stat " + ", b.dt_trn " + ", a.cd_3pty_type " + ", a.cd_dc_dd " + "From    " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_check_comp a, " + "        " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_journal_tran b " + "Where   a.id_payee = '" + mpi + "'" + "  AND   A.NB_MO_JNLEVT  =  B.NB_MO_JNLEVT " + "  AND   A.NB_BTCH_JNLEVT = B.NB_BTCH_JNLEVT " + "  AND   A.NB_ITM_JNLEVT =  B.NB_ITM_JNLEVT " + "  AND   A.NB_TRN        =  B.NB_TRN " + "  AND   A.CD_TYPE_JNLEVT = B.CD_TYPE_JNLEVT " + "  and   a.cd_chk_stat not in ('RCKC','RCKS','RCKD') " + "Group by a.id_payee " + ", a.nb_chk " + ", a.dt_chk_prt " + ", a.nb_btch_jnlevt " + ", a.nb_itm_jnlevt " + ", a.nb_mo_jnlevt " + ", a.cd_type_jnlevt " + ", a.cd_chk_stat " + ", b.dt_trn " + ", a.cd_3pty_type " + ", a.cd_dc_dd " + "Order by b.dt_trn desc ";

		this.jdbcTemplate1.setMaxRows(12);

		rowSet = new SimpleJdbcCall(jdbcTemplate1).getJdbcTemplate().queryForRowSet(sql);

		return rowSet;
	}

	/**
	              
	  */
	@Override
	public SqlRowSet getEFTDetails(String mpi) throws Exception
	{
		logger.debug("\n********** IN PaymentHistoryDaoImpl:  getEFTDetails**********\n");

		SqlRowSet rowSet = null;

		String sql = "Select * " + "  from " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_eft_details " + "	where id_part = '" + mpi + "'" + "    and cd_eft_stat = 'A' ";

		this.jdbcTemplate1.setMaxRows(1);

		rowSet = new SimpleJdbcCall(jdbcTemplate1).getJdbcTemplate().queryForRowSet(sql);

		return rowSet;

	}

	//Here onwards queries to get paid by NCP recent 12 transactions info.
	/**
	 * Method to build the SQL Query to obtain all the Journal Tran Entries
	 * 
	 */
	@Override
	public SqlRowSet getJtrnQuery(String mpi) throws Exception
	{
		logger.debug("\n********** IN PaymentHistoryDaoImpl: getJtrnQuery**********\n");

		SqlRowSet rowSet = null;

		String sql = "Select nb_mo_jnlevt,nb_btch_jnlevt,nb_itm_jnlevt,dt_trn," + "       am_trn_apld, am_trn_rmng, ts_trn" + "         from " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_journal_tran  " + "	where cd_trn_type = 'POST'" +

				"    and id_acct_to = '" + mpi + "'" + "  order by ts_trn desc";

		this.jdbcTemplate1.setMaxRows(12);

		rowSet = new SimpleJdbcCall(jdbcTemplate1).getJdbcTemplate().queryForRowSet(sql);

		return rowSet;

	}

	/**
	 * Method to build the SQL Query to obtain case numbers and amount disbursed
	 * for the case
	 */
	@Override
	public SqlRowSet getAllocQuery(String strID, short nb_mo_jnlevt, int nb_btch_jnlevt, short nb_itm_jnlevt)
			throws Exception
	{
		logger.debug("\n********** IN PaymentHistoryDaoImpl: getAllocQuery**********\n");
		SqlRowSet rowSet = null;
		// This Query returns Acts Case Numbers and amounts
		String sql = "Select id_acct_to,sum(am_trn_apld)" + "         from " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_journal_tran  " + "  where id_acct_fr = '" + strID + "'" + "	  and cd_trn_type = 'ALOC'" + "    and nb_mo_jnlevt = " + nb_mo_jnlevt + "" + "    and nb_btch_jnlevt = " + nb_btch_jnlevt + "" + "    and nb_itm_jnlevt = " + nb_itm_jnlevt + "" + "  group by id_acct_to";

		this.jdbcTemplate1.setMaxRows(20);

		rowSet = new SimpleJdbcCall(jdbcTemplate1).getJdbcTemplate().queryForRowSet(sql);

		return rowSet;
	}

	/**
	 * Method to build the SQL Query to obtain case numbers and amount disbursed
	 * for the case
	 */
	@Override
	public SqlRowSet getManualAdj(String strID, short nb_mo_jnlevt, int nb_btch_jnlevt, short nb_itm_jnlevt)
			throws Exception

	{
		logger.debug("\n********** IN PaymentHistoryDaoImpl: getManualAdj**********\n");
		// This Query returns Acts Case Numbers and amounts
		SqlRowSet rowSet = null;
		String sql = "Select id_acct_to,sum(am_trn_apld)" + "         from " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_journal_tran  " + "  where id_acct_fr = '" + strID + "'" + "	  and cd_trn_type = 'ALOC'" + "    and nb_mo_jnlevt_srce = " + nb_mo_jnlevt + "" + "    and nb_btch_jnlevt_src = " + nb_btch_jnlevt + "" + "    and nb_itm_jnlevt_srce = " + nb_itm_jnlevt + "" + "  group by id_acct_to";
		this.jdbcTemplate1.setMaxRows(5);
		rowSet = new SimpleJdbcCall(jdbcTemplate1).getJdbcTemplate().queryForRowSet(sql);
		return rowSet;
	}

	/**
	 * Method to build the SQL Query to obtain Part Case Details for a Case
	 */
	@Override
	public SqlRowSet getPcasQuery(String strID) throws Exception
	{
		logger.debug("\n********** IN PaymentHistoryDaoImpl: getPcasQuery**********\n");
		SqlRowSet rowSet = null;
		String sql = "Select id_part " + "   from " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_part_case " + "  where nb_case = '" + strID + "'" + "	  and cd_case_relshp = 'CLI'" + "    and cd_part_stat = 'A'";
		this.jdbcTemplate1.setMaxRows(1);
		rowSet = new SimpleJdbcCall(jdbcTemplate1).getJdbcTemplate().queryForRowSet(sql);
		return rowSet;
	}

	/**
	 * Insert the method's description here.
	 */
	@Override
	public SqlRowSet getSystemAccount(String id3pty, String id3ptyType) throws Exception
	{
		logger.debug("\n********** IN PaymentHistoryDaoImpl: getSystemAccount**********\n");
		String sql = "";
		SqlRowSet rowSet = null;
		sql = "Select nm_3pty from " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_third_party " + " Where id_3pty = '" + id3pty + "' " + "   and cd_3pty_type = '" + id3ptyType + "'";
		this.jdbcTemplate1.setMaxRows(1);
		rowSet = new SimpleJdbcCall(jdbcTemplate1).getJdbcTemplate().queryForRowSet(sql);
		return rowSet;
	}

	/**
	 * Method to build the SQL Query to obtain Part Case Details for a Case
	 */
	public SqlRowSet getParticipant(String strID) throws Exception
	{
		logger.debug("\n********** IN PaymentHistoryDaoImpl: getParticipant**********\n");
		String sql = "";
		SqlRowSet rowSet = null;
		sql = "Select nm_part_l,nm_part_f,nm_part_m " + "   from " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_participant " + "  where id_part = '" + strID + "'";
		this.jdbcTemplate1.setMaxRows(1);
		rowSet = new SimpleJdbcCall(jdbcTemplate1).getJdbcTemplate().queryForRowSet(sql);
		return rowSet;
	}

}
