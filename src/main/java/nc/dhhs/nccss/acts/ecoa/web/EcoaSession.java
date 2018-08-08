package nc.dhhs.nccss.acts.ecoa.web;

import java.io.Serializable;
import java.util.List;

import nc.dhhs.nccss.acts.ecoa.beans.Permission;

/**
 * 
 * 
 * 
 * @author Vijay Peddapalli
 * @version 1.0
 */
public class EcoaSession implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -1792754884477964889L;

	private Permission			permObj;

	//private Long				permission;

	private List<Permission>	permissions;

	/**
	 * @param permissions
	 *            the permissions to set
	 */
	public void setPermissions(List<Permission> permissions)
	{
		this.permissions = permissions;
	}

	/**
	 * 
	 * @return
	 */
	public List<Permission> getPermissions()
	{
		return permissions;
	}

	/**
	 * @return the permObj
	 */
	public Permission getPermObj()
	{
		return permObj;
	}

	/**
	 * @param permObj
	 *            the permObj to set
	 */
	public void setPermObj(Permission permObj)
	{
		this.permObj = permObj;
	}
	/**
	 * 
	 * @param permission
	 */
	// public void setPermission(long permission)
	// {

	// this.permission = permission;
	// }
	/**
	 * 
	 * @return
	 */
	//public Long getPermission()
	//{
	//return permission;
	//}

}
