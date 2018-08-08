/**
 * 
 */
package nc.dhhs.nccss.acts.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import nc.dhhs.nccss.acts.dao.AlertDAO;
import nc.dhhs.nccss.acts.dao.rowmap.AlertRowMapper;
import nc.dhhs.nccss.acts.ecoa.beans.Alert;
import nc.dhhs.nccss.acts.ecoa.web.config.WebsiteConfiguration;

/**
 * @author Vijay Peddapalli
 *
 */
@Repository
public class AlertDaoImpl implements AlertDAO
{

	protected final Logger		logger		= Logger.getLogger(AlertDaoImpl.class);

	private JdbcTemplate		jdbcTemplate;

	private SimpleJdbcCall		procCSSNewsAlert;

	private static final String	SCREEN_SP	= "FKWEB_R_CSS_NEWS";

	@Autowired
	// @Required
	public void setDataSource(DataSource dataSource)
	{

		logger.debug("\n********** IN AlertDaoImpl: SETDATASOURCE**********\n");

		this.jdbcTemplate = new JdbcTemplate(dataSource);

		this.jdbcTemplate.setSkipUndeclaredResults(true);

		procCSSNewsAlert = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_R_CSS_NEWS");

	}

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.dao.AlertDAO#retrieveMessages()
	 */
	@SuppressWarnings("unchecked")
	public List<Alert> retrieveMessages() throws SQLException

	{
		logger.debug("\n********** IN AlertDaoImpl: retrieveMessages()" + "**********\n");

		procCSSNewsAlert.returningResultSet("newsAlert", new AlertRowMapper());

		SqlParameterSource in = new MapSqlParameterSource();

		Map<String, Object> results = procCSSNewsAlert.execute(in);

		return (List<Alert>) results.get("newsAlert");
	}

}
