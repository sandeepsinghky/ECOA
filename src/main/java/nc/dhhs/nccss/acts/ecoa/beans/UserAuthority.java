/**
 * 
 */
package nc.dhhs.nccss.acts.ecoa.beans;

import java.io.Serializable;

import org.apache.log4j.Logger;

/**
 * @author Vijay Peddapalli
 *
 */
public class UserAuthority implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	protected final Logger		logger				= Logger.getLogger(UserAuthority.class);

	private long				userIdLastUpdated;

	private long				userId;

	private long				roleId;

	private long				applId;

	/**
	 * @return the userId
	 */
	public long getUserId()
	{
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(long userId)
	{
		this.userId = userId;
	}

	/**
	 * @return the roleId
	 */
	public long getRoleId()
	{
		return roleId;
	}

	/**
	 * @param roleId
	 *            the roleId to set
	 */
	public void setRoleId(long roleId)
	{
		this.roleId = roleId;
	}

	/**
	 * @return the applId
	 */
	public long getApplId()
	{
		return applId;
	}

	/**
	 * @param applId
	 *            the applId to set
	 */
	public void setApplId(long applId)
	{
		this.applId = applId;
	}

	/**
	 * @return the userIdLastUpdated
	 */
	public long getUserIdLastUpdated()
	{
		return userIdLastUpdated;
	}

	/**
	 * @param userIdLastUpdated
	 *            the userIdLastUpdated to set
	 */
	public void setUserIdLastUpdated(long userIdLastUpdated)
	{
		this.userIdLastUpdated = userIdLastUpdated;
	}

}
