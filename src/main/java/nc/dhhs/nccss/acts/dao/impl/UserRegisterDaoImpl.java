package nc.dhhs.nccss.acts.dao.impl;

import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import nc.dhhs.nccss.acts.dao.UserRegisterDao;
import nc.dhhs.nccss.acts.dao.rowmap.PreNcIdUserRowMapper;
import nc.dhhs.nccss.acts.ecoa.beans.PreNcIdUser;
import nc.dhhs.nccss.acts.ecoa.web.config.WebsiteConfiguration;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * @author Mallika Velagapudi
 *
 */

// This dao works on ACTW_PART_ORDER table, which stores the involved children
// list for the specific ordertype..

@Repository
public class UserRegisterDaoImpl implements UserRegisterDao {

	protected final Logger logger = Logger.getLogger(UserRegisterDaoImpl.class);

	private JdbcTemplate jdbcTemplate, jdbcTemplate1;

	private SimpleJdbcCall procCaseApplication1, procCaseApplication2, procCaseApplication3;

	private static final String SCREEN_SP = "APP_RESP";

	@Autowired
	// @Required
	public void setDataSource(DataSource dataSource) {

		logger.debug("\n********** IN UserRegisterDaoImpl: SETDATASOURCE**********\n");

		this.jdbcTemplate = new JdbcTemplate(dataSource);

		this.jdbcTemplate1 = new JdbcTemplate(dataSource);
		this.jdbcTemplate1.setSkipUndeclaredResults(true);

		this.jdbcTemplate.setSkipUndeclaredResults(true);

		procCaseApplication1 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess()
				.withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_GET_USERID");

		procCaseApplication2 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess()
				.withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_VALIDATE_PERSON");

		procCaseApplication3 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess()
				.withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_R_CHECK_MPI_USED");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * nc.dhhs.nccss.acts.dao.UserRegisterDao#getRegisteredOldUser(java.lang.
	 * String, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override

	public PreNcIdUser getRegisteredOldUser(String mpi, String ssn, String dob) throws SQLException {

		logger.debug("\n********** IN UserRegisterDaoImpl:getRegisteredOldUser**********\n");

		PreNcIdUser oldUser = null;

		String dataFields = buildDataParam(mpi, ssn, dob);

		List<PreNcIdUser> oldUserList = null;

		String commonParam = buildCommonParam(AppConstants.OPERATION_SELECT, "");

		String returnCode = ",,,,";

		procCaseApplication1.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR),
				new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR))
				.returningResultSet("oldUserList", new PreNcIdUserRowMapper());

		SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields)
				.addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

		Map<String, Object> results = procCaseApplication1.execute(in);

		if (results.get("RETURNCODE").toString().substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS)) {

			oldUserList = (List<PreNcIdUser>) results.get("oldUserList");

			if (oldUserList != null && oldUserList.size() > 0) {

				oldUser = oldUserList.get(0);

			}

		}

		else {

			throw new RuntimeException("FKWEB_GET_USERID: SP has failed, Rollback this transaction! " + "returnCode:"
					+ results.get("RETURNCODE").toString());

		}

		return oldUser;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * nc.dhhs.nccss.acts.dao.UserRegisterDao#getParticipantStatus(java.lang.
	 * String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override

	public String getParticipantStatus(String mpi, String ssn, String dob, StringBuffer idPart) throws SQLException

	{
		logger.debug("\n********** IN UserRegisterDaoImpl:getParticipantStatus**********\n");

		String dataFields = buildDataParam(mpi, ssn, dob);

		String commonParam = buildCommonParam(AppConstants.OPERATION_SELECT, "");

		String returnCode = ",,,,";

		String mpiStatus = "";

		String errDesc = "";

		procCaseApplication2.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR),
				new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR));

		SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields)
				.addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

		Map<String, Object> results = procCaseApplication2.execute(in);

		dataFields = (String) results.get("DATAFIELDS");

		if (results.get("RETURNCODE").toString().substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS)) {
			mpiStatus = results.get("DATAFIELDS").toString().substring(29, 30).trim(); // mpiStaus
																						// can
																						// have
																						// values
																						// A,I,
																						// N
																						// and
																						// U.

			// int dum = results.get("DATAFIELDS").toString().length();

			if (mpiStatus.equals("A")) {

				mpi = results.get("DATAFIELDS").toString().substring(0, 10).trim();

				idPart.append(mpi);
			}

		} else {

			throw new RuntimeException("FKWEB_VALIDATE_PERSON: SP has failed, Rollback this transaction! "
					+ "returnCode:" + results.get("RETURNCODE").toString());
		}

		return mpiStatus;

	}

	@SuppressWarnings("unchecked")
	@Override
	// It checks in ACTW_USR_DETAIL table to see if any other has already
	// registered to provided MPI.
	public String getMPIUsedUser(String mpi) throws SQLException {

		logger.debug("\n**********  In UserRegisterDaoImpl: checkMPIUsedUser **********\n");

		String ncId = "";

		procCaseApplication3.declareParameters(new SqlParameter("ID_PART", Types.CHAR),
				new SqlOutParameter("NCID", Types.CHAR));

		SqlParameterSource in = new MapSqlParameterSource().addValue("ID_PART", mpi);

		Map<String, Object> results = procCaseApplication3.execute(in);

		ncId = (String) results.get("NCID");

		return ncId;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nc.dhhs.nccss.acts.dao.UserRegisterDao#updateNbAttempt(int,
	 * java.lang.String)
	 */
	@Override
	public int updateNbAttempt(int attempts, String ncId) throws SQLException {
		logger.debug("\n********** In UserRegisterDaoImpl:  updateNbAttempt");

		String attempt = Integer.toString(attempts);

		String sql = "UPDATE " + WebsiteConfiguration.getDbSchemaSQL() + ".ACTW_USR_DETAIL SET NB_ATTEMPT = '" + attempt
				+ "' WHERE ID_LOGIN = '" + ncId + "'";

		int count = new SimpleJdbcCall(jdbcTemplate).getJdbcTemplate().update(sql);

		return count;
	}

	/**
	 * @param op
	 * @param ncid
	 * @return
	 */
	private String buildCommonParam(String op, String ncid) {
		logger.debug("\n********** IN In UserRegisterDaoImpl: buildCommonParam(op: " + op + "," + "ncid: " + ncid
				+ ")**********\n");

		String commonParam = op + ",,," + SCREEN_SP + "," + ncid + "," + AppConstants.INTERFACE_CODE + ",,";

		logger.info("*****commonParam value: " + commonParam + "*****");
		return commonParam;
	}

	private String buildDataParam(String mpi, String ssn, String dob) {

		logger.debug("\n********** IN UserRegisterDaoImpl: buildDataParam **********\n");

		StringBuffer dataParam = new StringBuffer(" ");

		// string to separate field values passed to the SP

		String fldSeparator = AppConstants.FLD_SEPARATOR;

		dataParam.append(mpi.trim() + fldSeparator);

		dataParam.append(ssn.trim() + fldSeparator);

		dataParam.append(dob.trim() + fldSeparator);

		logger.info("buildDataParam =: " + dataParam.toString().trim());

		return dataParam.toString().trim();
	}
}
