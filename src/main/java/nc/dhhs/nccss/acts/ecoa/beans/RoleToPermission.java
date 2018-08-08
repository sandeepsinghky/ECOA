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
public class RoleToPermission implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	protected final Logger		logger				= Logger.getLogger(RoleToPermission.class);

	private long				roleId;

	private long				permissionId;

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
	 * @return the permissionId
	 */
	public long getPermissionId()
	{
		return permissionId;
	}

	/**
	 * @param permissionId
	 *            the permissionId to set
	 */
	public void setPermissionId(long permissionId)
	{
		this.permissionId = permissionId;
	}

}
