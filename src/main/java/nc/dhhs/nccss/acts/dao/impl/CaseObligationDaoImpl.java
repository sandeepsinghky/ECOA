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

import nc.dhhs.nccss.acts.dao.CaseObligationDao;
import nc.dhhs.nccss.acts.ecoa.web.config.WebsiteConfiguration;

/**
 * @author Mallika Velagapudi
 *
 */

@Repository
public class CaseObligationDaoImpl implements CaseObligationDao
{

	protected final Logger	logger	= Logger.getLogger(CaseObligationDaoImpl.class);

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
	 * Method to build the SQL Query to Get Arrears for a Case Creation date:
	 */

	public SqlRowSet getArrearsFrequency(String ivdCase) throws Exception
	{

		logger.debug("\n********** IN CaseObligationDaoImpl: getArrearsFrequency");

		String sql = "Select am_ord,cd_pyt_freq" + " from " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_subaccount  " + "	where nb_acct = '" + ivdCase + "'" + "    and cd_pyt_freq > ' '" + "    and cd_pyt_freq != 'NOC'" + "    and am_ord > 0" + "    and cd_subacct_stat = 'OPEN'" + "    and (id_subacct in ('OSTA','AFLG','AFBT1','AFBT2','AFBT3','AFBT4')" + "     or substr(id_subacct,5,1) = 'A' " + "     or substr(id_subacct,5,1) = 'U')";

		SqlRowSet rowSet = new SimpleJdbcCall(jdbcTemplate).getJdbcTemplate().queryForRowSet(sql);

		return rowSet;

	}

	/**
	 * Method to build the SQL Query to Get CSUP Arrears for a Case Creation
	 * 
	 */
	public double getCsupQuery(String ivdCase, String role) throws Exception
	{

		logger.debug("\n********** IN CaseObligationDaoImpl: getCsupQuery");

		String sql = "";
		double value = 0;

		if (role.equals("CLI"))
		{

			sql = "Select value(sum(am_subacc_bal_mth),0)" + "  from " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_subaccount  " + "	where nb_acct = '" + ivdCase + "'" + "    and cd_subacct_stat = 'OPEN'" + "    and id_subacct = 'CSUP'";
		}
		else if (role.equals("AP"))
		{

			sql = "Select value(sum(am_subacct_bal),0)" + "  from " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_subaccount  " + "	where nb_acct = '" + ivdCase + "'" + "    and cd_subacct_stat = 'OPEN'" + "    and id_subacct = 'CSUP'";
		}

		Double doubleObj = new SimpleJdbcCall(jdbcTemplate).getJdbcTemplate().queryForObject(sql, Double.class);

		value = doubleObj.doubleValue();

		return value;
	}

	/**
	 * Method to build the SQL Query to Get Arrears for a Case Creation date:
	 */
	public double getArrearsQuery(String ivdCase, String role) throws Exception
	{
		logger.debug("\n********** IN CaseObligationDaoImpl: getArrearsQuery");

		String sql = "";

		double value = 0;

		if (role.equals("CLI"))
		{

			sql = "Select value(sum(am_subacc_bal_mth),0)" + "   from " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_subaccount  " + "	where nb_acct = '" + ivdCase + "'" + "    and cd_subacct_stat = 'OPEN'" + "    and (id_subacct in ('OSTA','AFLG','AFBT1','AFBT2','AFBT3','AFBT4')" + "     or substr(id_subacct,5,1) = 'A' " + "     or substr(id_subacct,5,1) = 'U')";
		}
		else if (role.equals("AP"))
		{

			sql = "Select value(sum(am_subacct_bal),0)" + "  from " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_subaccount  " + "	where nb_acct = '" + ivdCase + "'" + "    and cd_subacct_stat = 'OPEN'" + "    and (id_subacct in ('OSTA','AFLG','AFBT1','AFBT2','AFBT3','AFBT4')" + "     or substr(id_subacct,5,1) = 'A' " + "     or substr(id_subacct,5,1) = 'U')";
		}

		Double doubleObj = new SimpleJdbcCall(jdbcTemplate).getJdbcTemplate().queryForObject(sql, Double.class);

		value = doubleObj.doubleValue();

		return value;

	}

	/**
	 * Method to build the SQL Query to Get Current Support for a Case Creation
	 * 
	 */

	public SqlRowSet getCsupFrequency(String ivdCase) throws Exception
	{
		logger.debug("\n********** IN CaseObligationDaoImpl: getCsupFrequency");

		String sql = "Select am_ord,cd_pyt_freq" + "	 from " + WebsiteConfiguration.getDbSchemaSQL() + ".fkkt_subaccount" + "	where nb_acct = '" + ivdCase + "'" + "    and cd_subacct_stat = 'OPEN'" + "    and id_subacct = 'CSUP'";

		SqlRowSet rowSet = new SimpleJdbcCall(jdbcTemplate).getJdbcTemplate().queryForRowSet(sql);

		return rowSet;

	}

}
