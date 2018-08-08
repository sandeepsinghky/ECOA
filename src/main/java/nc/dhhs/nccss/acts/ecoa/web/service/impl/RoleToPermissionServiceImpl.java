/**
 * 
 */
package nc.dhhs.nccss.acts.ecoa.web.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import nc.dhhs.nccss.acts.dao.RoleToPermissionDao;
import nc.dhhs.nccss.acts.ecoa.beans.RoleToPermission;
import nc.dhhs.nccss.acts.ecoa.web.service.RoleToPermissionService;

/**
 * @author Vijay Peddapalli
 *
 */
public class RoleToPermissionServiceImpl implements RoleToPermissionService
{

	protected final Logger		logger	= Logger.getLogger(RoleToPermissionServiceImpl.class);

	@Autowired
	private RoleToPermissionDao	roleToPermissionDao;

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecoa.web.service.RoleToPermissionService#
	 * getListofRolePermissions(long)
	 */
	@Transactional
	public List<RoleToPermission> getListofRolePermissions(long roleId) throws Exception
	{
		logger.debug("\n********** IN RoleToPermissionServiceImpl: getListofRolePermissions(roleId: " + roleId + ") **********\n");

		return roleToPermissionDao.retrieveRolePermissions(roleId);
	}

}
