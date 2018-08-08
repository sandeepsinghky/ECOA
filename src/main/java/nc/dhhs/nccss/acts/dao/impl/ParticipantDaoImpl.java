package nc.dhhs.nccss.acts.dao.impl;

import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import nc.dhhs.nccss.acts.dao.ParticipantDao;
import nc.dhhs.nccss.acts.dao.rowmap.ParticipantRowMapper;
import nc.dhhs.nccss.acts.ecoa.beans.Participant;
import nc.dhhs.nccss.acts.ecoa.web.config.WebsiteConfiguration;

/**
 * @author Mallika Velagapudi
 *
 */

@Repository
public class ParticipantDaoImpl implements ParticipantDao
{

	protected final Logger		logger		= Logger.getLogger(ParticipantDaoImpl.class);

	private JdbcTemplate		jdbcTemplate;

	private SimpleJdbcCall		procParticipant, procDualRole;

	private static final String	SCREEN_SP	= "FKWEB_R_ACTS_PART";

	@Autowired
	// @Required
	public void setDataSource(DataSource dataSource)
	{

		logger.debug("\n********** ParticipantDaoImpl: SETDATASOURCE**********\n");

		this.jdbcTemplate = new JdbcTemplate(dataSource);

		this.jdbcTemplate.setSkipUndeclaredResults(true);

		// work
		procParticipant = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_R_ACTS_PART");

		procDualRole = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_R_DUAL_ROLE ");

	}

	/*
	 * The stored procedure :( FKWEB_R_ACTS_PART) gets data using the following:
	 * Query: Select a.id_part,a.nm_part_l, a.nm_part_f, a.nm_part_m, a.nb_ssn,
	 * a.dt_brth from RegionManager.getPrimary_region().fkkt_participant a where
	 * a.id_part = 'strMPI';
	 */
	@Override
	public List<Participant> getParticipant(String mpi) throws SQLException
	{
		logger.debug("\n********** IN ParticipantDaoImpl: getParticipant()" + "**********\n");
		List<Participant> participantList = null;

		try
		{

			procParticipant.returningResultSet("ParticipantList", new ParticipantRowMapper()).declareParameters(new SqlParameter("MPI", Types.CHAR));

			SqlParameterSource userParam = new MapSqlParameterSource().addValue("MPI", mpi);

			Map<String, Object> results = procParticipant.execute(userParam);

			participantList = (List<Participant>) results.get("ParticipantList");

		}

		catch (Exception e)
		{

			logger.error(e.getMessage());

		}

		return participantList;

	}

	/*
	 * SP Name: FKWEB_R_DUAL_ROLE It uses the following to determine whether the
	 * user associated with MPI is dual or not. Query:Select * from
	 * RegionManager.getPrimary_region().fkkt_part_case Where id_part = 'strMPI'
	 * and cd_case_relshp in ('AP','CLI') and cd_part_stat = 'A' Order by
	 * cd_case_relshp ; we call user is dual if he has different relationships
	 * in different cases. NON DUAL:if he/she is either CLI or AP in all his
	 * cases.
	 */
	@Override
	public boolean dualRole(String mpi) throws SQLException
	{
		logger.debug("\n********** IN ParticipantDaoImpl: getParticipant()" + "**********\n");
		String dual = "";

		boolean isDual = true;

		try
		{

			procDualRole.declareParameters(new SqlParameter("MPI", Types.CHAR), new SqlOutParameter("DUAL", Types.CHAR));

			SqlParameterSource in = (SqlParameterSource) new MapSqlParameterSource().addValue("MPI", mpi);

			Map<String, Object> results = procDualRole.execute(in);

			dual = (String) results.get("DUAL");

		}
		catch (Exception ex)
		{
			logger.error(ex.getMessage());
		}

		if (dual != null)
		{

			isDual = ("Y".equalsIgnoreCase(dual.trim())) ? true : false;
		}
		return isDual;
	}

}
