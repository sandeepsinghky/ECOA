package nc.dhhs.nccss.acts.ecoa.web.service;

import java.util.List;

import nc.dhhs.nccss.acts.ecoa.beans.UserAuthority;

/**
 * @author Vijay Peddapalli
 *
 */
public interface UserAuthorityService
{

	/**
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<UserAuthority> getUserAuthorities(long userId) throws Exception;

	/**
	 * @param userAuthority
	 * @param ncid
	 * @param dbOpType
	 * @return
	 * @throws Exception
	 */
	public String createOrModifyUserAuthority(UserAuthority userAuthority, String ncid, String dbOpType) throws Exception;

}
