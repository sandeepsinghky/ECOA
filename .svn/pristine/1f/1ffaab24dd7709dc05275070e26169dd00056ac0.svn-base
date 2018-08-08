/**
 * 
 */
package nc.dhhs.nccss.acts.dao.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Types;
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
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import nc.dhhs.nccss.acts.dao.FeedBackDao;
import nc.dhhs.nccss.acts.ecoa.beans.CaseApplication;
import nc.dhhs.nccss.acts.ecoa.beans.TaskBean;
import nc.dhhs.nccss.acts.ecoa.beans.TaskFormBean;
import nc.dhhs.nccss.acts.ecoa.beans.TaskNotes;
import nc.dhhs.nccss.acts.ecoa.web.config.WebsiteConfiguration;

import nc.dhhs.nccss.acts.ecoa.web.config.WebsiteConfiguration;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * @author Phani Konuru
 *
 */

@Repository
public class FeedBackDaoImpl implements FeedBackDao
{

	protected final Logger	logger	= Logger.getLogger(FeedBackDaoImpl.class);

	private JdbcTemplate	jdbcTemplate;
	
	private SimpleJdbcCall		procFeedback1, procFeedback2;
	
	private static final String	SCREEN_SP	= "FEEDBACK";
	
	// Insert a row in form
	protected final String INSERT_FORM_SQL =
			"INSERT INTO "
				+ WebsiteConfiguration.getDbSchemaSQL()+"."
				+ AppConstants.TABLE_CSESRV_FORMS
				+ " (ID_REFERENCE, CD_STAT, TS_CREATE, ID_WRKR_CREATE, TS_UPDATE, ID_WRKR_UPDATE, CD_RFRL, ID_PART, NB_CASE, NB_SSN, NB_DKT, ID_EMAIL, NB_TEL_ACD, NB_TEL_EXC, NB_TEL_LN, NB_TEL_EXT, NM_COUNTY, NB_CONTROL, NM_CP_L, NM_CP_F, NM_CP_M, NM_NCP_L, NM_NCP_F, NM_NCP_M, NM_CUSTOMER_L, NM_CUSTOMER_F, NM_CUSTOMER_M, ID_STAFF_1, ID_STAFF_2, ID_STAFF_3, ID_STAFF_4, MO_CREATE, YR_CREATE, TS_ASSIGNED) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	// Insert a row in form tracking table
	protected final String INSERT_FRMTRK_SQL =
			"INSERT INTO "
				+  WebsiteConfiguration.getDbSchemaSQL()+"."
				+ AppConstants.TABLE_CSESRV_FRMTRK
				+ " (ID_REFERENCE, TS_CREATE, CD_STAT, CD_RESOLUTION, ID_WRKR_ASSIGN, TS_WRKR_START, TS_WRKR_END, ID_NOTE, ID_WRKR_CREATE, TS_UPDATE, ID_WRKR_UPDATE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	// Insert a row in notes table
	protected final String INSERT_NOTE_SQL =
			"INSERT INTO "
				+ WebsiteConfiguration.getDbSchemaSQL()+"."
				+ AppConstants.TABLE_CSESRV_NOTES
				+ " (ID_NOTE, ID_NOTE_NXT, NM_NOTE_SEG_REF, DT_LST_UPD, TM_LST_UPD, ID_WRKR_LST_UPD, ID_TRML_LST_UPD, DE_NOTE_TXT) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	

	
	/**
	 * @param dataSource
	 */
	@Autowired
	public void setDataSource(DataSource dataSource)
	{

		logger.debug("\n********** IN FeedBackDaoImpl: SETDATASOURCE**********\n");

		this.jdbcTemplate = new JdbcTemplate(dataSource);

		this.jdbcTemplate.setSkipUndeclaredResults(true);
		
		procFeedback1 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_U_CSESRV_CTLNBR");
		procFeedback2 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_U_CSESRV_FORM_WORKER");

	}
	
	/* (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.dao.FeedBackDao#getCSSCtrlNbr()
	 */
	public int getCSSCtrlNbr() throws SQLException
	{
		logger.debug("\n********** IN FeedBackDaoImpl: getCSSCtrlNbr**********\n");
		int cntrlNbr = 0;
		try{
			procFeedback1.declareParameters(new SqlInOutParameter("WEBOAPRM_NB_CNTL", Types.INTEGER));
			SqlParameterSource in = new MapSqlParameterSource();
			Map<String, Object> results = procFeedback1.execute(in);
			if (results.get("WEBOAPRM_NB_CNTL") != null)
			{
				cntrlNbr = ((Integer)results.get("WEBOAPRM_NB_CNTL")).intValue();
				logger.info("Get CSSCtrlNbr returned:" + cntrlNbr);
			}
		}catch(Exception ex){
			logger.error(ex.getMessage());
		}
		return cntrlNbr;
	}


	/* (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.dao.FeedBackDao#insertCSENotes(nc.dhhs.nccss.acts.ecoa.beans.TaskFormBean)
	 */
	@Override
	public int insertCSENotes(TaskNotes notesBean) throws SQLException
	{
		logger.debug("\n********** IN FeedBackDaoImpl: insertCSENotes**********\n");
		
		java.sql.Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
		
		int result = jdbcTemplate.update(INSERT_NOTE_SQL, 
				notesBean.getIdNotes(),
				notesBean.getNoteNext(), 
				notesBean.getNmNoteRef(), 
				notesBean.getLastUpdatDt(), 
				notesBean.getLastUpdateTm(), 
				notesBean.getIdWrkrLst(), 
				notesBean.getIdLstUpd(),
				notesBean.getNotesTxt());
		logger.info("Insert into csesrv Notes returned:" + result);
		return result;

	}
	
	/* (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.dao.FeedBackDao#insertCSETrk(nc.dhhs.nccss.acts.ecoa.beans.TaskBean)
	 */
	@Override
	public int insertCSETrk(TaskBean taskBean) throws SQLException
	{
		logger.debug("\n********** IN FeedBackDaoImpl: insertCSETrk**********\n");
		java.sql.Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
		
		int result = jdbcTemplate.update(INSERT_FRMTRK_SQL, 
				taskBean.getIdReference(), 
				taskBean.getTsCreate(), 
				taskBean.getCdType(), 
				taskBean.getCdResol(), 
				taskBean.getIdWrkrAssign() , 
				taskBean.getTsAssign(), 
				taskBean.getTsEnd(), 
				taskBean.getIdNotes(), 
				taskBean.getIdWrkrCreate(), 
				taskBean.getTsUpdate(), taskBean.getIdWrkrUpdate());
		logger.info("Insert into csesrv frmtrk returned:" + result);
		return result;
	}
	
	/* (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.dao.FeedBackDao#insertCSEForm(nc.dhhs.nccss.acts.ecoa.beans.TaskFormBean)
	 */
	@Override
	public int insertCSEForm(TaskFormBean tskFrmBean) throws SQLException
	{
		logger.debug("\n********** IN FeedBackDaoImpl: insertCSEForm**********\n");
		java.sql.Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
		
		int result = jdbcTemplate.update(INSERT_FORM_SQL, 
				tskFrmBean.getIdReference(), 
				tskFrmBean.getCdStatus(), 
				tskFrmBean.getTsCreate(), 
				tskFrmBean.getIdWrkrCreate(), 
				tskFrmBean.getTsUpdate(), 
				tskFrmBean.getIdWrkrUpdate(), 
				tskFrmBean.getCdType(), 
				tskFrmBean.getIdPart(), 
				tskFrmBean.getNbCase(), 
				Double.parseDouble(tskFrmBean.getNbSSN()), 
				tskFrmBean.getNbDocket(),
				tskFrmBean.getIdEmail(), 
				tskFrmBean.getNbTelAcd(), 
				tskFrmBean.getNbTelExc(),
				tskFrmBean.getNbTelLn(), 
				tskFrmBean.getNbTelExt(), 
				tskFrmBean.getNmCounty(),
				tskFrmBean.getNbControl(),
				tskFrmBean.getNmCustParLast(), 
				tskFrmBean.getNmCustParFirst(), 
				tskFrmBean.getNmCustParMi(), 
				tskFrmBean.getNmNonCustParLast(),
				tskFrmBean.getNmNonCustParFirst(), 
				tskFrmBean.getNmNonCustParMi(), 
				tskFrmBean.getNmCustomerLast(), 
				tskFrmBean.getNmCustomerFirst(),
				tskFrmBean.getNmCustomerMi(),
				tskFrmBean.getNmRefSource1(), 
				tskFrmBean.getNmRefSource2(),  
				tskFrmBean.getNmRefSource3(), 
				tskFrmBean.getNmRefSource4(), 
				tskFrmBean.getCreateMo(), 
				tskFrmBean.getCreateYr(), 
				tskFrmBean.getTsAssigned());
		
		logger.info("Insert into csesrv form returned:" + result);
		return result;
	}
	
	/**
	 * @param referenceId
	 * @param ncId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public int updateTaskAssignedWorker(int referenceId, String lastNm, String emailId) throws SQLException
	{
		logger.debug("\n********** IN FeedBackDaoImpl: updateTaskAssignedWorker**********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_UPDATE, "");

		String returnCode = ",,,,";

		String dataFields = referenceId+AppConstants.FLD_SEPARATOR+lastNm+AppConstants.FLD_SEPARATOR+emailId+AppConstants.FLD_SEPARATOR;


		try
		{
			procFeedback2.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR));

			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

			Map<String, Object> results = procFeedback2.execute(in);

			logger.info("Update task assigned worker returned:" + results.get("RETURNCODE"));

			returnCode = results.get("RETURNCODE").toString();

			System.out.println("the return code is" + returnCode);
		}

		catch (Exception ex)
		{
			logger.error(ex.getMessage());
		}
		
		if (returnCode != null && returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS)) 
			return 1;
		else
			return 0;
	}
	
	/**
	 * @param op
	 * @param ncid
	 * @return
	 */
	private String buildCommonParam(String op, String ncid)
	{
		logger.debug("\n********** IN CaseApplicationDaoImpl: buildCommonParam(op: " + op + "," + "ncid: " + ncid + ")**********\n");

		String commonParam = op + ",,," + SCREEN_SP + "," + ncid + "," + AppConstants.INTERFACE_CODE + ",,";

		logger.info("*****commonParam value: " + commonParam + "*****");
		return commonParam;
	}



}
