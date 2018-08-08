package nc.dhhs.nccss.acts.dao;

import java.sql.SQLException;
import java.util.List;

import nc.dhhs.nccss.acts.ecoa.beans.Vehicle;

/**
 * @author Mallika Velagapudi
 */
public interface CaseVehicleDao
{
	/**
	 * @param appliBean
	 * @return
	 * @throws SQLException
	 */
	public String createCaseVehile(Vehicle vehicle, String ncid) throws SQLException;

	/**
	 * @param applId
	 * @return
	 * @throws SQLException
	 */
	public List<Vehicle> getCaseVehiclesByPartId(String applId, String applicantId) throws SQLException;

	/**
	 * @param applId
	 * @param ncId
	 * @return
	 * @throws SQLException
	 */
	public String deleteCaseVehicles(Vehicle vehicle, String ncid) throws SQLException;
}
