/**
 * 
 */
package nc.dhhs.nccss.acts.dao.rowmap;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import nc.dhhs.nccss.acts.ecoa.beans.CasePartOther;

/**
 * @author Mallika Velagapudi
 *
 */
public class CasePartOtherRowMapper implements RowMapper<CasePartOther> {

	protected final Logger logger = Logger.getLogger(CasePartOtherRowMapper.class);

	public CasePartOtherRowMapper() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
	 * int)
	 */
	@Override
	public CasePartOther mapRow(ResultSet rs, int rowNum) throws SQLException {
		logger.debug("IN: relationBeanRowMapper- mapRow");

		CasePartOther relationBean = new CasePartOther();

		if (rs.getString("ID_APPLICATION") != null && !rs.getString("ID_APPLICATION").equals("")) {
			relationBean.setApplicationId(rs.getString("ID_APPLICATION").trim());
		}

		if (rs.getString("ID_APPLICANT") != null && !rs.getString("ID_APPLICANT").equals("")) {
			relationBean.setApplicantId(rs.getString("ID_APPLICANT").trim());
		}

		if (rs.getString("CD_OTHER_RELSHP") != null && !rs.getString("CD_OTHER_RELSHP").equals("")) {
			relationBean.setRelationship(rs.getString("CD_OTHER_RELSHP").trim());

		}

		if (rs.getString("NM_PART_L") != null && !rs.getString("NM_PART_L").equals("")) {
			relationBean.setLastNm(rs.getString("NM_PART_L").trim());

		}

		if (rs.getString("NM_PART_F") != null && !rs.getString("NM_PART_F").equals("")) {
			relationBean.setFirstNm(rs.getString("NM_PART_F").trim());

		}
		if (rs.getString("NM_PART_M") != null && !rs.getString("NM_PART_M").equals("")) {
			relationBean.setMiddleNm(rs.getString("NM_PART_M").trim());

		}

		return relationBean;

	}

}
