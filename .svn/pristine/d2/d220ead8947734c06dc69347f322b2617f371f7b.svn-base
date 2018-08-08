/**
 * 
 */
package nc.dhhs.nccss.acts.ecoa.web.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import nc.dhhs.nccss.acts.dao.PermissionDao;
import nc.dhhs.nccss.acts.ecoa.beans.Permission;
import nc.dhhs.nccss.acts.ecoa.web.service.PermissionService;

/**
 * @author Vijay Peddapalli
 *
 */
public class PermissionServiceImpl implements PermissionService
{

	protected final Logger	logger	= Logger.getLogger(PermissionServiceImpl.class);

	@Autowired
	private PermissionDao	permissionDao;

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.ecoa.web.service.PermissionService#getListPermissions(
	 * long)
	 */
	@Transactional
	public List<Permission> getListPermissions(long permissionId) throws Exception
	{
		logger.debug("\n********** IN PermissionServiceImpl: getListPermissions(permissionId: " + permissionId + ") **********\n");

		return permissionDao.retrievePermissions(permissionId);
	}

}
