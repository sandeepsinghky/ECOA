/**
 * 
 */
package nc.dhhs.nccss.acts.dao.rowmap;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import nc.dhhs.nccss.acts.ecoa.beans.CaseCourtOrder;

/**
 * @author Mallika Velagapudi
 *
 */
public class CaseCourtOrderRowMapper implements RowMapper<CaseCourtOrder> {

	protected final Logger logger = Logger.getLogger(CaseCourtOrderRowMapper.class);

	public CaseCourtOrderRowMapper() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
	 * int)
	 */
	@Override
	public CaseCourtOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
		logger.debug("IN: CaseCourtOrderRowMapper- mapRow");

		CaseCourtOrder courtOrder = new CaseCourtOrder();

		if (rs.getString("ID_APPLICATION") != null && !rs.getString("ID_APPLICATION").equals("")) {
			courtOrder.setApplicationId(rs.getString("ID_APPLICATION").trim());
		}

		if (rs.getString("ID_APPLICANT") != null && !rs.getString("ID_APPLICANT").equals("")) {
			courtOrder.setApplicantId(rs.getString("ID_APPLICANT").trim());
		}

		if (rs.getString("NB_ORDER") != null && !rs.getString("NB_ORDER").equals("")) {
			courtOrder.setOrderNm(rs.getString("NB_ORDER").trim());
		}

		if (rs.getString("CD_SUPPORT_TYPE") != null && !rs.getString("CD_SUPPORT_TYPE").equals("")) {
			courtOrder.setSupportType(rs.getString("CD_SUPPORT_TYPE").trim());

		}

		if (rs.getString("AM_ORDER") != null && !rs.getString("AM_ORDER").equals("")) {
			courtOrder.setOrderAmount((rs.getDouble("AM_ORDER")));

		}

		if (rs.getString("CD_PAY_FREQ") != null && !rs.getString("CD_PAY_FREQ").equals("")) {
			courtOrder.setPayFreq((rs.getString("CD_PAY_FREQ").trim()));

		}

		if (rs.getString("NB_DKT") != null && !rs.getString("NB_DKT").equals("")) {
			courtOrder.setDocketNm((rs.getString("NB_DKT").trim()));

		}

		if (rs.getString("DT_ORD_EFF") != null && !rs.getString("DT_ORD_EFF").equals("")) {
			courtOrder.setOrderEffDt(rs.getDate("DT_ORD_EFF"));

		}
		if (rs.getString("CD_ORD_COUNTY") != null && !rs.getString("CD_ORD_COUNTY").equals("")) {
			courtOrder.setCounty((rs.getString("CD_ORD_COUNTY").trim()));

		}

		if (rs.getString("CD_ORD_STATE") != null && !rs.getString("CD_ORD_STATE").equals("")) {
			courtOrder.setState(rs.getString("CD_ORD_STATE").trim());

		}
		if (rs.getString("AM_PAST_DUE") != null && !rs.getString("AM_PAST_DUE").equals("")) {
			courtOrder.setAmtPastDue((rs.getDouble("AM_PAST_DUE")));

		}
		if (rs.getString("NM_PAYOR") != null && !rs.getString("NM_PAYOR").equals("")) {
			courtOrder.setPayorNm((rs.getString("NM_PAYOR").trim()));

		}
		if (rs.getString("NM_RECIPIENT") != null && !rs.getString("NM_RECIPIENT").equals("")) {
			courtOrder.setRecipientNm((rs.getString("NM_RECIPIENT").trim()));

		}

		return courtOrder;

	}

}
