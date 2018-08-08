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

import nc.dhhs.nccss.acts.dao.ParentCaseInfoDao;
import nc.dhhs.nccss.acts.ecoa.web.config.WebsiteConfiguration;

/**
 * @author Mallika Velagapudi
 *
 */

/**
 * @author mvelagapudi
 *
 */
@Repository
public class ParentCaseInfoDaoImpl implements ParentCaseInfoDao
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

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.ParentCaseInfoDao#getCaseList(java.lang.String)
	 */
	@Override
	public SqlRowSet getCaseList(String mpi) throws SQLException
	{

		logger.debug("\n********** IN ParentCaseInfoDaoImpl: getCaseList");

		String sql = "Select b.nb_case,b.cd_case_relshp,b.cd_part_stat,b.cd_case_type,b.cd_case_stat, a.cd_rsn_clse, a.in_prot_ord, a.cd_rsn_unwrk  from " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_case a, " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_part_case b where b.id_part = '" + mpi + "'" + "  and b.cd_part_stat = 'A'   and a.nb_case = b.nb_case   order by b.nb_case ";

		SqlRowSet rowSet = new SimpleJdbcCall(jdbcTemplate).getJdbcTemplate().queryForRowSet(sql);

		return rowSet;
	}

	//
	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.ParentCaseInfoDao#getPartProfile(java.lang.String)
	 */
	@Override
	public String getPartProfile(String mpi) throws SQLException
	{
		logger.debug("\n********** IN ParentCaseInfoDaoImpl:  getPartProfile");

		String sql = "Select in_dangerous  from " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_part_profile where id_part = '" + mpi + "'";

		String isDangerous = new SimpleJdbcCall(jdbcTemplate1).getJdbcTemplate().queryForObject(sql, String.class);

		return isDangerous.trim();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.ParentCaseInfoDao#getSecondParty(java.lang.String,
	 * java.lang.String)
	 */

	@Override
	public SqlRowSet getSecondParty(String mpi, String strCase) throws SQLException
	{
		//Method to build and execute the SQL Query to obtain other party on a case Creation 

		logger.debug("\n********** IN ParentCaseInfoDaoImpl:  getSecondParty");

		String sql = "Select id_part, cd_case_relshp  from " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_part_case    where nb_case  = '" + strCase + "'" + " and cd_case_relshp in ('AP','CLI')" + "    and id_part !='" + mpi + "'" + "	  and cd_part_stat = 'A'";

		SqlRowSet rowSet = new SimpleJdbcCall(jdbcTemplate).getJdbcTemplate().queryForRowSet(sql);

		return rowSet;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.ParentCaseInfoDao#getParticipantName(java.lang.
	 * String)
	 */
	@Override
	public SqlRowSet getParticipantName(String mpi) throws SQLException

	{
		logger.debug("\n********** IN ParentCaseInfoDaoImpl: getParticipantName");

		String sql = "Select id_part, nm_part_l, nm_part_f, nm_part_m " + " from " + WebsiteConfiguration.getDbSchemaSQL() + " .fkkt_participant " + "  where id_part = '" + mpi + "'";

		SqlRowSet rowSet = new SimpleJdbcCall(jdbcTemplate).getJdbcTemplate().queryForRowSet(sql);

		return rowSet;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.ParentCaseInfoDao#getAgencyName(java.lang.String)
	 */
	@Override
	public SqlRowSet getAgencyName(String id3pty) throws SQLException
	{

		logger.debug("\n********** IN ParentCaseInfoDaoImpl: getAgencyName");

		String sql = "Select * from " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_third_party " + " Where id_3pty = '" + id3pty + "' " + "   and cd_3pty_type = 'SYST' ";

		SqlRowSet rowSet = new SimpleJdbcCall(jdbcTemplate1).getJdbcTemplate().queryForRowSet(sql);

		return rowSet;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.ParentCaseInfoDao#getUnderOrder(java.lang.String)
	 */
	@Override
	public SqlRowSet getUnderOrder(String strCase) throws SQLException
	{
		// This Query returns Acts Case Numbers and amounts

		logger.debug("\n********** IN ParentCaseInfoDaoImpl:  getUnderOrder");

		String sql = "Select a.nb_case " + " from " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_court_order a " + "  where a.nb_case  = '" + strCase + "'" + " and a.cd_terms_1 != 'DOCK'" + " and a.nb_blind_key = (select max(b.nb_blind_key) " + " from " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_court_order b " + "where b.nb_case  = '" + strCase + "'" + " and b.cd_terms_1  != 'DOCK')";

		SqlRowSet rowSet = new SimpleJdbcCall(jdbcTemplate1).getJdbcTemplate().queryForRowSet(sql);

		return rowSet;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.ParentCaseInfoDao#getCaseDetail(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public SqlRowSet getCaseDetail(String mpi, String ivdCase) throws SQLException
	{
		logger.debug("\n********** IN ParentCaseInfoDaoImpl:  getCaseDetail");

		String sql = "Select b.nb_case,b.cd_case_relshp,b.cd_part_stat,b.cd_case_type,b.cd_case_stat" + "  from " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_participant a,    " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_part_case b    " + "	where a.id_part = '" + mpi + "'" + "    and b.id_part = a.id_part" + "    and b.nb_case = '" + ivdCase + "' " + " and b.cd_case_stat = 'OPEN' ";

		SqlRowSet rowSet = new SimpleJdbcCall(jdbcTemplate1).getJdbcTemplate().queryForRowSet(sql);

		return rowSet;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.ParentCaseInfoDao#getSecondPartyQuery(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public SqlRowSet getSecondPartyQuery(String mpi, String ivdCase) throws SQLException
	{
		//Method to build the SQL Query to obtain other party on a case Creation.

		logger.debug("\n********** IN ParentCaseInfoDaoImpl:  getSecondPartyQuery");

		String sql = "Select a.id_part, a.cd_case_relshp, b.nm_part_l, b.nm_part_f, b.nm_part_m " + "  from " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_part_case a, " + "     " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_participant b " + "  where a.nb_case  = '" + ivdCase + "'" + "	  and a.cd_case_relshp in ('AP','CLI')" + "    and a.id_part !='" + mpi + "'" + "    and b.id_part = a.id_part" + "	  and a.cd_part_stat = 'A'";

		SqlRowSet rowSet = new SimpleJdbcCall(jdbcTemplate1).getJdbcTemplate().queryForRowSet(sql);

		return rowSet;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.ParentCaseInfoDao#getChildren(java.lang.String)
	 */
	@Override
	public int getChildren(String ivdCase) throws SQLException
	{
		//Method to build the SQL Query to obtain child count on a case Creation.

		logger.debug("\n********** IN ParentCaseInfoDaoImpl: getChildren");

		String sql = "Select count(*) from " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_part_case " + " where nb_case = '" + ivdCase + "'" + "   and cd_case_relshp = 'CHLD'" + "   and cd_part_stat = 'A'";

		int childCount = new SimpleJdbcCall(jdbcTemplate1).getJdbcTemplate().queryForInt(sql);

		return childCount;
	}

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.dao.ParentCaseInfoDao#getWorker(java.lang.String)
	 */
	@Override
	public SqlRowSet getWorker(String ivdCase) throws SQLException
	{
		//Method to build the SQL Query to obtain the Case Worker Information

		logger.debug("\n********** IN ParentCaseInfoDaoImpl: getWorker");

		String sql = "Select cd_prcs_stat, id_wrkr_resp," + "  nm_wrkr_f, nm_wrkr_l, nm_wrkr_mi," + "  ad_3pty_ln_1, ad_3pty_ln_2, ad_3pty_cty, ad_3pty_st,ad_3pty_zip_5,ad_3pty_zip_4," + "  nb_tel_wrkr_acd, nb_tel_wrkr_exc, nb_tel_wrkr_ln," + "  ad_internet_wrkr,cd_fips_wrkr ,nb_tel_wrkr_ext" + "  from " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_case  a, " + "       " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_worker  b, " + "       " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_third_party  c " + " Where nb_case = '" + ivdCase + "'" + "   and b.id_wrkr = a.id_wrkr_resp" + "   and c.id_3pty = b.cd_fips_wrkr" + "   and c.cd_3pty_type = 'AGCY'";

		SqlRowSet rowSet = new SimpleJdbcCall(jdbcTemplate1).getJdbcTemplate().queryForRowSet(sql);

		return rowSet;
	}

}
