package nc.dhhs.nccss.acts.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import nc.dhhs.nccss.acts.dao.GuideLinesDao;
import nc.dhhs.nccss.acts.dao.rowmap.GuideLinesRowMapper;
import nc.dhhs.nccss.acts.ecoa.beans.GuideLines;
import nc.dhhs.nccss.acts.ecoa.web.config.WebsiteConfiguration;

/**
 * @author Mallika Velagapudi
 *
 */
@Repository
public class GuideLinesDaoImpl implements GuideLinesDao
{

	protected final Logger		logger		= Logger.getLogger(GuideLinesDaoImpl.class);

	private JdbcTemplate		jdbcTemplate;

	private SimpleJdbcCall		procGuideLines;

	private static final String	SCREEN_SP	= "FKWEB_R_ECS_GUIDE_LINE";

	@Autowired
	// @Required
	public void setDataSource(DataSource dataSource)
	{

		logger.debug("\n********** IN GuideLinesDaoImpl: SETDATASOURCE**********\n");

		this.jdbcTemplate = new JdbcTemplate(dataSource);

		this.jdbcTemplate.setSkipUndeclaredResults(true);

		// work
		procGuideLines = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_R_ECS_GUIDE_LINE");

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GuideLines> getGuideLines() throws SQLException
	{

		logger.debug("\n********** IN GuideLinesDaoImpl: getGuideLines()" + "**********\n");

		procGuideLines.returningResultSet("guideLinesList", new GuideLinesRowMapper());

		Map<String, Object> results = procGuideLines.execute();

		return (List<GuideLines>) results.get("guideLinesList");
	}

}
