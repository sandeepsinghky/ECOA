/**
 * 
 */
package nc.dhhs.nccss.acts.dao.rowmap;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import nc.dhhs.nccss.acts.ecoa.beans.Vehicle;

/**
 * @author Mallika Velagapudi
 *
 */
public class VehicleRowMapper implements RowMapper<Vehicle> {

	protected final Logger logger = Logger.getLogger(VehicleRowMapper.class);

	public VehicleRowMapper() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
	 * int)
	 */
	@Override
	public Vehicle mapRow(ResultSet rs, int rowNum) throws SQLException {
		logger.debug("IN:VehicleRowMapper- mapRow");

		Vehicle vehicle = new Vehicle();

		if (rs.getString("ID_APPLICATION") != null && !rs.getString("ID_APPLICATION").equals("")) {
			vehicle.setApplicantId(rs.getString("ID_APPLICATION").trim());
		}

		if (rs.getString("ID_APPLICANT") != null && !rs.getString("ID_APPLICANT").equals("")) {
			vehicle.setApplicationId(rs.getString("ID_APPLICANT").trim());
		}

		if (rs.getString("CD_MAKE_VEHICLE") != null && !rs.getString("CD_MAKE_VEHICLE").equals("")) {
			vehicle.setMake(rs.getString("CD_MAKE_VEHICLE").trim());

		}

		if (rs.getString("CD_MODEL_VEHICLE") != null && !rs.getString("CD_MODEL_VEHICLE").equals("")) {
			vehicle.setModel(rs.getString("CD_MODEL_VEHICLE").trim());

		}

		if (rs.getString("DT_YEAR_VEHICLE") != null && !rs.getString("DT_YEAR_VEHICLE").equals("")) {
			vehicle.setYear(rs.getString("DT_YEAR_VEHICLE").trim());

		}

		return vehicle;

	}

}
