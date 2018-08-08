/**
 * 
 */
package nc.dhhs.nccss.acts.ecoa.web.service;

import java.util.List;

import nc.dhhs.nccss.acts.ecoa.beans.RoleToPermission;

/**
 * @author Vijay Peddapalli
 *
 */
public interface RoleToPermissionService
{
	/**
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<RoleToPermission> getListofRolePermissions(long userId) throws Exception;
}
