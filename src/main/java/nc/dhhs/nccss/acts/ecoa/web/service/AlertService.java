/**
 * 
 */
package nc.dhhs.nccss.acts.ecoa.web.service;

import java.sql.SQLException;
import java.util.List;

import nc.dhhs.nccss.acts.ecoa.beans.Alert;

/**
 * @author Vijay Peddapalli
 *
 */
public interface AlertService
{

	/**
	 * @return
	 * @throws SQLException
	 */
	public List<Alert> retrieveCSSAnnouncements() throws SQLException;

}
