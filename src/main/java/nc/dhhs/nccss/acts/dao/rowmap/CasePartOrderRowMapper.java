/**
 * 
 */
package nc.dhhs.nccss.acts.dao.rowmap;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import nc.dhhs.nccss.acts.ecoa.beans.CasePartOrder;

/**
 * @author Mallika Velagapudi
 *
 */
public class CasePartOrderRowMapper implements RowMapper<CasePartOrder> {

	protected final Logger logger = Logger.getLogger(CasePartOrderRowMapper.class);

	public CasePartOrderRowMapper() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
	 * int)
	 */
	@Override
	public CasePartOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
		logger.debug("IN: childBeanRowMapper- mapRow");

		CasePartOrder childBean = new CasePartOrder();

		if (rs.getString("ID_APPLICATION") != null && !rs.getString("ID_APPLICATION").equals("")) {
			childBean.setApplicationId(rs.getString("ID_APPLICATION").trim());
		}

		if (rs.getString("ID_APPLICANT") != null && !rs.getString("ID_APPLICANT").equals("")) {
			childBean.setApplicantId(rs.getString("ID_APPLICANT").trim());
		}

		if (rs.getString("NB_ORDER") != null && !rs.getString("NB_ORDER").equals("")) {
			childBean.setOrderNm(rs.getString("NB_ORDER").trim());

		}

		if (rs.getString("ID_APPLICANT_CHILD") != null && !rs.getString("ID_APPLICANT_CHILD").equals("")) {
			childBean.setChildApplicantId(rs.getString("ID_APPLICANT_CHILD").trim());

		}

		return childBean;

	}

}
