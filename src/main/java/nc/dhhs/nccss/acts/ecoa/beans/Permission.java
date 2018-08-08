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
public class Permission implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	protected final Logger		logger				= Logger.getLogger(Permission.class);

	private long				permissionId;

	private String				permissionName;

	private String				permissionDescription;

	/**
	 * @return the permissionId
	 */
	public long getPermissionId()
	{
		return permissionId;
	}

	/**
	 * @param permissionId the permissionId to set
	 */
	public void setPermissionId(long permissionId)
	{
		this.permissionId = permissionId;
	}

	/**
	 * @return the permissionName
	 */
	public String getPermissionName()
	{
		return permissionName;
	}

	/**
	 * @param permissionName the permissionName to set
	 */
	public void setPermissionName(String permissionName)
	{
		this.permissionName = permissionName;
	}

	/**
	 * @return the permissionDescription
	 */
	public String getPermissionDescription()
	{
		return permissionDescription;
	}

	/**
	 * @param permissionDescription the permissionDescription to set
	 */
	public void setPermissionDescription(String permissionDescription)
	{
		this.permissionDescription = permissionDescription;
	}

}
