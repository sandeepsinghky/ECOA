/**
 * 
 */
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
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import nc.dhhs.nccss.acts.dao.UserDAO;
import nc.dhhs.nccss.acts.dao.rowmap.EcoaUserRowMapper;
import nc.dhhs.nccss.acts.dao.rowmap.PreNcIdUserRowMapper;
import nc.dhhs.nccss.acts.dao.rowmap.UserProfileRowMapper;
import nc.dhhs.nccss.acts.ecoa.beans.EcoaUser;
import nc.dhhs.nccss.acts.ecoa.beans.PreNcIdUser;
import nc.dhhs.nccss.acts.ecoa.beans.UserProfile;
import nc.dhhs.nccss.acts.ecoa.web.config.WebsiteConfiguration;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * @author Vijay Peddapalli
 *
 */

@Repository
public class UserDaoImpl implements UserDAO {

	protected final Logger logger = Logger.getLogger(UserDaoImpl.class);

	private JdbcTemplate jdbcTemplate;

	private SimpleJdbcCall procReadUserDetails, procInsertUpdateUserDetails, procPreNcIdUserAuth,
			procUpdateGraceOrDeactivate, procReadEcoaUserProfile, procUpdateEcoaUserProfile;

	/**
	 * @param dataSource
	 */
	@Autowired
	public void setDataSource(DataSource dataSource) {

		logger.debug("\n********** IN UserDaoImpl: SETDATASOURCE**********\n");

		this.jdbcTemplate = new JdbcTemplate(dataSource);

		this.jdbcTemplate.setSkipUndeclaredResults(true);

		procReadUserDetails = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess()
				.withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_R_USER_DETAIL");

		procInsertUpdateUserDetails = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess()
				.withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_UPDATE_USR_DETAIL");

		procPreNcIdUserAuth = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess()
				.withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_R_OLD_USER");

		procUpdateGraceOrDeactivate = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess()
				.withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_UPDATE_GRACE_DEACTIVATE");

		procReadEcoaUserProfile = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess()
				.withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_R_USR_PROFILE");

		procUpdateEcoaUserProfile = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess()
				.withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_UPDATE_USR_PROFILE");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nc.dhhs.nccss.acts.dao.UserDAO#retrieveUserInfo(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<EcoaUser> retrieveUserInfo(String loginID) throws SQLException {

		logger.debug("\n********** IN UserDaoImpl: retrieveUserDetails(loginID:" + loginID + ")" + "**********\n");

		procReadUserDetails.declareParameters(new SqlParameter("ID_LOGIN", Types.CHAR)).returningResultSet("ecoaUser",
				new EcoaUserRowMapper());

		SqlParameterSource in = new MapSqlParameterSource().addValue("ID_LOGIN", loginID);

		Map<String, Object> results = procReadUserDetails.execute(in);

		logger.info("\n********** Executed SP: FKWEB_R_USER_DETAIL **********\n");

		return (List<EcoaUser>) results.get("ecoaUser");

	}

	// This SP reads all the three tables to get NCID user profile required
	// details from:
	// ACTW_USR_DETAIL,ACTW_USR_CONTACT and ACTW_USR_ADDRESS tables.
	@SuppressWarnings("unchecked")
	@Override
	public UserProfile getEcoaUserProfile(String loginId) throws SQLException {

		logger.debug("\n********** IN UserDaoImpl: retrieveUserDetails(loginID:" + loginId + ")" + "**********\n");

		UserProfile userProfile = null;

		procReadEcoaUserProfile.declareParameters(new SqlParameter("ID_LOGIN", Types.CHAR))
				.returningResultSet("userProfileList", new UserProfileRowMapper());

		SqlParameterSource in = new MapSqlParameterSource().addValue("ID_LOGIN", loginId);

		Map<String, Object> results = procReadEcoaUserProfile.execute(in);

		logger.info("\n********** Executed SP: FKWEB_R_USER_PROFILE **********\n");

		List<UserProfile> userProfileList = (List<UserProfile>) results.get("userProfileList");

		if (userProfileList != null && userProfileList.size() > 0) {

			userProfile = userProfileList.get(0);

		}
		return userProfile;

	}

	// This SP updates all the three tables to store NCID user details/profile:
	// ACTW_USR_DETAIL,ACTW_USR_CONTACT and ACTW_USR_ADDRESS tables.
	@Override
	public String updateUserProfile(UserProfile userProfile, String loginId) throws SQLException {

		logger.debug("\n********** IN UserDaoImpl: updateUserProfile(userProfile " + "," + loginId + ")**********\n");

		String commonParam = "UPDATE" + ",,," + "99" + "," + loginId + "," + AppConstants.INTERFACE_CODE + ",,";

		String fldSeperator = AppConstants.FLD_SEPARATOR;

		String data = fldSeperator + userProfile.getFirstName() + fldSeperator + userProfile.getMidName() + fldSeperator
				+ userProfile.getLastName() + fldSeperator + userProfile.getAddrLine1() + fldSeperator
				+ userProfile.getAddrLine2() + fldSeperator + userProfile.getCity() + fldSeperator
				+ userProfile.getState() + fldSeperator + userProfile.getZipCode() + fldSeperator
				+ userProfile.getPhone() + fldSeperator + userProfile.getEmail() + fldSeperator;

		String returnCode = ",,,,,,,,,,,,,,";

		procUpdateEcoaUserProfile.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR),
				new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR));

		SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", data)
				.addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

		Map<String, Object> results = procUpdateEcoaUserProfile.execute(in);

		logger.info("\n********** Executed SP: procUpdateEcoaUserProfile **********\n");

		returnCode = results.get("RETURNCODE").toString();

		logger.info("\n********** updateUserProfile returned: " + returnCode + ")**********\n");

		if (returnCode == null || !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS)) {

			throw new RuntimeException(
					"In UserDaoImpl : FKWEB_UPDATE_USR_PROFILE: SP has failed" + "returnCode:" + returnCode);

		}

		return returnCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * nc.dhhs.nccss.acts.dao.UserDAO#authenticatePreNcIdUser(java.lang.String,
	 * java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<PreNcIdUser> authenticatePreNcIdUser(String loginID, String password) throws SQLException {

		logger.debug("\n********** IN UserDaoImpl: authenticatePreNcIdUser(loginID:" + loginID + ")" + "**********\n");

		procPreNcIdUserAuth
				.declareParameters(new SqlParameter("USERID", Types.VARCHAR), new SqlParameter("PWD", Types.VARCHAR))
				.returningResultSet("PreNcIdUserList", new PreNcIdUserRowMapper());

		SqlParameterSource in = new MapSqlParameterSource().addValue("USERID", loginID).addValue("PWD", password);

		Map<String, Object> results = procPreNcIdUserAuth.execute(in);

		logger.info("\n********** Executed SP: FKWEB_R_OLD_USER **********\n");

		return (List<PreNcIdUser>) results.get("PreNcIdUserList");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * nc.dhhs.nccss.acts.dao.UserDAO#insertOrUpdateUserInfo(nc.dhhs.nccss.acts.
	 * ecoa.beans.EcoaUser, java.lang.String, java.lang.String)
	 */
	@Override
	public String insertOrUpdateUserInfo(EcoaUser ecoaUrbean, String dbOpType, String inOutCD) throws SQLException {
		logger.debug("\n********** IN UserDaoImpl: insertOrUpdateUserInfo(ecoaUrbean, dbOpType: " + dbOpType + " ,"
				+ " inOutCD: " + inOutCD + ")**********\n");

		String commonParam = buildCommonParam(dbOpType, ecoaUrbean);

		String fldSeperator = AppConstants.FLD_SEPARATOR;

		String data = ecoaUrbean.getLoginId() + fldSeperator + ecoaUrbean.getUserStatus() + fldSeperator
				+ ecoaUrbean.getLastName() + fldSeperator + ecoaUrbean.getFirstName() + fldSeperator
				+ ecoaUrbean.getMiddleName() + fldSeperator + ecoaUrbean.getBusinessAreaId() + fldSeperator
				+ ecoaUrbean.getNcidLastNM() + fldSeperator + ecoaUrbean.getNcidFirstNM() + fldSeperator
				+ ecoaUrbean.getNcidMiddleNM() + fldSeperator + ecoaUrbean.getIpAddressPort() + fldSeperator + inOutCD
				+ fldSeperator + ecoaUrbean.getPartId() + fldSeperator + ecoaUrbean.getNcIdGuid() + fldSeperator
				+ ecoaUrbean.getBrowserAgent() + fldSeperator + ecoaUrbean.getNcIdEmail() + fldSeperator;

		String returnCode = ",,,,,,,,,,,,,,";

		procInsertUpdateUserDetails.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR),
				new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR));

		SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", data)
				.addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

		Map<String, Object> results = procInsertUpdateUserDetails.execute(in);

		logger.info("\n********** Executed SP: FKWEB_UPDATE_USR_DETAIL **********\n");

		returnCode = results.get("RETURNCODE").toString();

		logger.info("\n********** insertOrUpdateUserInfo returned: " + returnCode + ")**********\n");

		return returnCode;
	}

	@Override
	public String updateDeActivateOrGraceLogin(String oldUserId, String updateField) throws SQLException {

		logger.debug("\n********** IN UserDaoImpl:updateDeActivateOrGraceLogin");

		String fldSeperator = AppConstants.FLD_SEPARATOR;

		String commonParam = "UPDATE" + ",,," + "99" + "," + "," + AppConstants.INTERFACE_CODE + ",,";

		String data = oldUserId + fldSeperator + updateField + fldSeperator;

		String returnCode = ",,,,,,,,,,,,,,";

		procUpdateGraceOrDeactivate.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR),
				new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR));

		SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", data)
				.addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

		Map<String, Object> results = procUpdateGraceOrDeactivate.execute(in);

		logger.info("\n********** Executed SP: FKWEB_UPDATE_USR_DETAIL **********\n");

		returnCode = results.get("RETURNCODE").toString();

		logger.info("\n********** updateDeActivateOrGraceLogin returned: " + returnCode + ")**********\n");

		if (returnCode == null || !returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS)) {
			throw new RuntimeException(
					"In UserDaoImpl: updateDeActivateOrGraceLogin:update failed, Rollback this transaction! "
							+ "returnCode:" + returnCode);

		}

		return returnCode;
	}

	/**
	 * @param op
	 * @param user
	 * @return
	 */
	private String buildCommonParam(String op, EcoaUser user) {
		logger.debug("\n********** IN UserDaoImpl: buildCommonParam(op: " + op + "," + "id: " + user.getLoginId() + ","
				+ "Business Area Id:" + user.getBusinessAreaId() + ")**********\n");

		String commonParam = op + ",,," + user.getBusinessAreaId() + "," + user.getLoginId() + ","
				+ AppConstants.INTERFACE_CODE + ",,";

		logger.info("*****commonParam value: " + commonParam + "*****");

		return commonParam;
	}
}
