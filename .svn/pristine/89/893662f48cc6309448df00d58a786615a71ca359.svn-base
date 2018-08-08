/**
 * 
 */
package nc.dhhs.nccss.acts.dao.rowmap;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import nc.dhhs.nccss.acts.ecoa.beans.CasePartChldAffil;

/**
 * @author Phani Konuru
 *
 */
public class CasePartChldNCPRowMapper implements RowMapper<CasePartChldAffil> {

	protected final Logger logger = Logger.getLogger(CasePartChldNCPRowMapper.class);

	public CasePartChldNCPRowMapper() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
	 * int)
	 */
	@Override
	public CasePartChldAffil mapRow(ResultSet rs, int rowNum) throws SQLException {
		logger.debug("IN: CasePartIncomeRowMapper- mapRow");

		CasePartChldAffil casePartChld = new CasePartChldAffil();

		if (rs.getString("ID_APPLICATION") != null && !rs.getString("ID_APPLICATION").equals("")) {
			casePartChld.setApplicationId(rs.getString("ID_APPLICATION").trim());
		}

		if (rs.getString("ID_APPLICANT") != null && !rs.getString("ID_APPLICANT").equals("")) {
			casePartChld.setApplicantId(rs.getString("ID_APPLICANT").trim());
		}

		if (rs.getString("ID_PART_CHILD") != null && !rs.getString("ID_PART_CHILD").equals("")) {
			casePartChld.setChildId(rs.getString("ID_PART_CHILD").trim());
		}

		if (rs.getString("CD_CASE_RELSHP") != null && !rs.getString("CD_CASE_RELSHP").equals("")) {
			casePartChld.setCaseRelshp(rs.getString("CD_CASE_RELSHP").trim());
		}

		if (rs.getString("CD_FAM_RELSHP") != null && !rs.getString("CD_FAM_RELSHP").equals("")) {
			casePartChld.setFamiRelshp(rs.getString("CD_FAM_RELSHP").trim());
		}

		if (rs.getString("IN_CHLD_LIVE_WITH") != null && !rs.getString("IN_CHLD_LIVE_WITH").equals("")) {
			casePartChld.setChldLiveWith(rs.getString("IN_CHLD_LIVE_WITH").trim());
		}

		if (rs.getString("NB_STAY_LENGTH_YR") != null && !rs.getString("NB_STAY_LENGTH_YR").equals("")) {
			casePartChld.setNoOfYears(rs.getInt("NB_STAY_LENGTH_YR"));
		}

		if (rs.getString("NB_STAY_LENGTH_Mo") != null && !rs.getString("NB_STAY_LENGTH_Mo").equals("")) {
			casePartChld.setNoOfMonths(rs.getInt("NB_STAY_LENGTH_Mo"));
		}
		if (rs.getString("NM_APPLICANT_F") != null) {
			casePartChld.setNameF(rs.getString("NM_APPLICANT_F"));
		}

		if (rs.getString("NM_APPLICANT_L") != null) {
			casePartChld.setNameL(rs.getString("NM_APPLICANT_L"));
		}
		if (rs.getString("NM_APPLICANT_M") != null) {
			casePartChld.setNameM(rs.getString("NM_APPLICANT_M"));
		}

		return casePartChld;
	}

}
