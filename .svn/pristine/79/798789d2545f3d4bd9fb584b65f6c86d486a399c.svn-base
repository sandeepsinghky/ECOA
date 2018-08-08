package nc.dhhs.nccss.acts.ecoa.web.service;

/**
 * @author Vijay Peddapalli
 *
 *         capture user specific attributes like last login etc.
 */
public interface UsageService
{

	public static int	EXPIRED		= 0;
	public static int	LOGGED_OUT	= 1;

	/**
	 * Saves usage instance when the user successfully logs into the
	 * application.
	 * 
	 * @param usage
	 *            usage to insert
	 * @return if save was successful
	 * @throws Exception
	 */
	//public boolean saveUsage(Usage usage) throws Exception;

	/**
	 * Gets maximum usageId from the database, this is useful to insert new
	 * usageId.
	 * 
	 * @return
	 * @throws Exception
	 */
	public long getMaxUsageId() throws Exception;

	/**
	 * Used to update usage details during application log out.
	 * 
	 * @param usage
	 * @throws Exception
	 */
	//public boolean updateUsage(Usage usage) throws Exception;

	/**
	 * Retrieves usage instance for a given usageId.
	 * 
	 * @param usgaeId
	 * @return
	 * @throws DAOException
	 */
	//public Usage getUsage(long usageId) throws Exception;
}
