package nc.dhhs.nccss.acts.ecoa.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import nc.dhhs.nccss.acts.ecoa.beans.EcoaUser;
import nc.dhhs.nccss.acts.ecoa.beans.Permission;
import nc.dhhs.nccss.acts.ecoa.beans.PreNcIdUser;
import nc.dhhs.nccss.acts.ecoa.beans.RoleToPermission;
import nc.dhhs.nccss.acts.ecoa.beans.UserAuthority;
import nc.dhhs.nccss.acts.ecoa.beans.UserInformation;
import nc.dhhs.nccss.acts.ecoa.beans.UserProfile;
import nc.dhhs.nccss.acts.ecoa.web.EcoaSession;
import nc.dhhs.nccss.acts.ecoa.web.config.WebsiteConfiguration;
import nc.dhhs.nccss.acts.ecoa.web.exception.ErrorDescriptor;
import nc.dhhs.nccss.acts.ecoa.web.service.CaseApplicationService;
import nc.dhhs.nccss.acts.ecoa.web.service.ParentUserInfoService;
import nc.dhhs.nccss.acts.ecoa.web.service.PermissionService;
import nc.dhhs.nccss.acts.ecoa.web.service.RoleToPermissionService;
import nc.dhhs.nccss.acts.ecoa.web.service.UserAuthorityService;
import nc.dhhs.nccss.acts.ecoa.web.service.UserService;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * LoginRoute Controller acts as a gateway for the application access
 * redirecting to the appropriate landing page after successful user
 * authentication and authorization.
 * 
 * User roles & permissions (if at all) are retrieved from the database and
 * authorities are set here for the application access.
 * 
 * New case application creation or forward to edit pending application logic is
 * set here.
 * 
 * @author Vijay Peddapalli
 *
 */
@Controller
@RequestMapping("/loginRoute.htm")
public class LoginRouteController extends BasicAnnotatedFormController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserAuthorityService userAuthorityService;

	@Autowired
	private RoleToPermissionService roleToPermissionService;

	@Autowired
	private PermissionService permissionService;

	@Autowired
	protected CaseApplicationService caseApplService;

	@Autowired
	private ParentUserInfoService userInfoService;

	// private String mpi = "";

	private List<Permission> permissionList = null;

	/**
	 * <p>
	 * Redirects user to correct page once logged in
	 * </p>
	 * 
	 * @param request
	 *            current request
	 * @param modelMap
	 *            model data
	 * @return page to load
	 */

	@RequestMapping(method = RequestMethod.GET)
	public String get(HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.debug("\n********** IN LoginRouteController: get(HttpServletRequest request,Model model) **********\n");

		String userType = (String) request.getSession().getAttribute("userType");

		String ssfirstNM = "", sslastNM = "", ssloginNM = "", ssEmailAdd = "";

		String returnPage = "";

		boolean ncidIndiv = false;

		UserInformation userInfoBean = new UserInformation();

		// Following steps occur before coming to below steps:
		// EcoaDAOAutheticationProvider calls retrieveUser -->
		// retrieveLoadedUser
		// -->calls EcoaUserDetailsServiceImpl.loadAuthentciate -->
		// loaduser-->AuthenticationWebService or preNCIDUserAuthentication
		// after successful authentication--> instantiates EcoaUserDetails

		// get userId from the authenticated principal casted to EcoaUserDetails
		// object
		// at this point user should already been authenticated against NCID
		// or ACTS DB if user is logging as preNcId user

		String loginName = getUserNameFromAuthPrincipal();

		logger.info("\n************ authentication success for UserNameFromAuthPrincipal: " + loginName
				+ "*************\n");

		try {

			// get the loaded Ecwa User from the authenticated NCID
			// serviceUserResponse.
			if (userType.equals("ncId") || userType.equals("pNcId")) {
				EcoaUser ncidUserInfo = userService.getNCIdResponseUser(loginName);
				if (ncidUserInfo.getUserType().equals("I")) {
					ncidIndiv = true;
				}
			}

			if (ncidIndiv || userType.equals("tcaId")) {
				List<UserAuthority> userAuthoritiesList = null;

				// first verify the type of customer logging into system.
				// we have three types of login scenarios:
				// login scenario 1: usertype: ncId --> typically brand new NCID
				// user who will be applying for support at home page
				// login scenario 2: usertype: tcaId --> acts user with existing
				// credentials
				// login scenario 3: usertype: pNcId --> parents who have
				// obtained
				// NCID and logging into the system.

				if (userType.equals("ncId") || userType.equals("pNcId")) {
					// user may be brand new user Or user logging in from home
					// page
					logger.info("\n************ Participant with NCID user credentials userType: userName " + userType
							+ ":" + loginName + "****\n");

					// check if the user exists in the db or not
					EcoaUser dbUser = userService.getDBUser(loginName);

					if (dbUser != null && dbUser.getUserId() != 0) {

						logger.info("\n************ User Exists in DB!! for user: " + loginName + " *************\n");

						String rValue = updateDbUserWithNCIdResponseInfo(dbUser, loginName);

						logger.info("\n************ updated user info successfully for user: " + loginName
								+ " *************\n");

						// get authorities
						userAuthoritiesList = userAuthorityService.getUserAuthorities(dbUser.getUserId());

						logger.info("\n************ retrieved user Authorities, size: " + userAuthoritiesList.size()
								+ " *************\n");

					} else {
						logger.info("\n************ User does not exist in the DB " + loginName + " *************\n");

						String rValue = createNewUserRecord(loginName);

						logger.info("\n************ New user record has been created successfully " + loginName
								+ "*************\n");

						dbUser = userService.getDBUser(loginName);

						userInfoBean.setLastLoginDate("You have successfully signed-in for first time today");

						// see if the user has authorities before, it shouldn't
						// be there, but just in case
						userAuthoritiesList = userAuthorityService.getUserAuthorities(dbUser.getUserId());

						if (userAuthoritiesList == null) {
							logger.info("\n************ No authorities exist; creating new user authorities "
									+ loginName + " *************\n");

							rValue = createNewUserAuthority(dbUser.getUserId(), dbUser.getLoginId(), userType);

							if (rValue != null
									&& rValue.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS)) {
								logger.info("\n************ User Authority created successfully in DB *************\n");
								userAuthoritiesList = userAuthorityService.getUserAuthorities(dbUser.getUserId());
								logger.info("\n************ retrieved user Authorities........ *************\n");
							}
						}

					}

					logger.info("\n************userprofile is retrieved  for user " + loginName + "************\n");

					UserProfile userProfile = userService.getEcoaUserProfile(loginName);

					if (userProfile == null) {
						logger.info("\n************userprofile is null  for user " + loginName + "************\n");

					}

					ssfirstNM = userProfile.getFirstName();
					sslastNM = userProfile.getLastName();
					ssloginNM = loginName;
					ssEmailAdd = userProfile.getEmail();

					// It checks If ncId user has registerd . If user not
					// registered
					// , checks to see reg is locked to the user.If registered
					// user
					// collects info related to the participant also.

					userInfoBean.setUserID(getUserNameFromAuthPrincipal());

					userInfoBean.setUserType((AppConstants.USERTYPE_NCID));

					userInfoService.getUserWebInfo(userInfoBean);

					logger.info(
							"\n************retrieved user web information for user" + loginName + "*************\n");
					userInfoBean.setEmailId(userProfile.getEmail());

					if (!userInfoBean.getMpi().isEmpty()) {
						userInfoService.getUserInfo(userInfoBean); // since user
																	// account
						logger.info("\n************retrieved participant information for user as he is registered user"
								+ loginName + "*************\n"); // is

						userInfoBean.setMpiTied(true);

					}

					else {
						int attempts = (int) dbUser.getNbAttempt();

						// if user has already used max 3 failed attempts,
						// registration will be locked.

						if (attempts >= WebsiteConfiguration.getMaxRegFailAttempts()) {

							userInfoBean.setRegLock(true);

							logger.info("\n************ User locked fron registration process for user" + loginName
									+ "*************\n");

						}

					}

					request.getSession().setAttribute(AppConstants.USERINFORMATION, userInfoBean);

					if (userType.equals("ncId")) {
						returnPage = "redirect:/cssp/user/caseApplicationList/1.htm";

					}

					if (userType.equals("pNcId")) // pNCID means user who logged
													// in
													// from parents section.

					{

						if (userInfoBean.isMpiTied()) {
							returnPage = "redirect:/cssp/user/parentWelcome.htm"; // it
																					// should
																					// be
																					// parentWelcome
																					// page.

						} else {

							returnPage = "forward:/cssp/user/userWelcome.htm";

						}

					}

				}

				else if (userType.equals("tcaId")) {
					// user is existing participant with no NCID yet!!

					logger.info("\n************ Participant with old user credentials userType: userName " + userType
							+ ":" + loginName + "****\n");

					PreNcIdUser preNcIdUser = getPreNcIdUserFromAuthPrincipal();

					userAuthoritiesList = userAuthorityService.getUserAuthorities(preNcIdUser.getId_user_auth());

					// this shouldn't be null, default authority is provided in
					// the
					// table for existing user

					if (userAuthoritiesList == null) {
						logger.info("\n************ No authorities exist; creating new user authorities  for user: "
								+ loginName + "***********\n");

						String rValue = createNewUserAuthority(preNcIdUser.getId_user_auth(), preNcIdUser.getId_user(),
								userType);

						if (rValue != null && rValue.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS)) {
							logger.info(
									"\n************ User Authority created successfully for PreNcIdUSer in DB for user "
											+ loginName + "*************\n");

							userAuthoritiesList = userAuthorityService
									.getUserAuthorities(preNcIdUser.getId_user_auth());

							logger.info("\n************ retrieved user Authorities for user:" + loginName
									+ "*************\n");
						}
					}

					// needs to update/increase the graceLogins of the old user
					// ,
					// since after 3 max grace logins , old user account needs
					// to be
					// deactivated.user should login with ncId account.

					int graceLogin = preNcIdUser.getNb_grace_login();

					userService.updateDeActivateOrGraceLogin(loginName, "GRACE");

					logger.info("\n************no of Grace periods already used  :" + graceLogin + 1 + "for user : "
							+ loginName + "*************\n");

					if (graceLogin + 1 >= WebsiteConfiguration.getMaxGraceLogins()) {

						userService.updateDeActivateOrGraceLogin(loginName, "DEACTIVATE");

						logger.info("\n************PreNcId user account   is deactivated for user :" + loginName
								+ "*************\n");

					}

					ssloginNM = preNcIdUser.getId_user();

					userInfoBean.setUserID(getUserNameFromAuthPrincipal());

					userInfoBean.setUserType(AppConstants.USERTYPE_PRENCID);

					userInfoService.getUserWebInfo(userInfoBean);

					logger.info("\n************retrived user web information fot the user : " + loginName
							+ " *************\n");

					userInfoService.getUserInfo(userInfoBean);

					logger.info("\n************ retrived participant information for user:" + loginName
							+ " *************\n");

					userInfoBean.setMpiTied(true); //// old userID login from
													//// KKKT_WEB_USER_AUTH are
													//// already tied user to
													//// MPI.

					request.getSession().setAttribute(AppConstants.USERINFORMATION, userInfoBean);

					returnPage = "redirect:/cssp/user/parentWelcome.htm";

					logger.info("\n************end of collecting old user specific details for user:" + loginName
							+ " *************\n");

				}

				// set authorities for the user to access the system.
				if (!userAuthoritiesList.isEmpty() || userAuthoritiesList.equals(null)) {
					setUserAuthorities(userAuthoritiesList);
				}

				EcoaSession ecoaSession = getEcoaSession(request);

				ecoaSession.setPermissions(permissionList);

				logger.info("\n*********after set permissionlist for user" + loginName + " *************\n");

				HttpSession session = request.getSession();
				session.setAttribute("userFirstName", ssfirstNM);
				session.setAttribute("userLastName", sslastNM);
				session.setAttribute("userLoginName", ssloginNM);
				session.setAttribute("userEmail", ssEmailAdd);
				session.setAttribute("userType", userType);

				logger.info("redirect to the appropriate screen  for authenticated  user" + loginName);
			} else {
				logger.info("NCID used was not Individual NCID.");
				model.addAttribute("message", AppConstants.NCID_PUBLIC_ERROR);
				returnPage = "redirect:/login.htm?login_error=1";
			}

		}

		catch (Exception e) {

			logger.error(e.getMessage());

			String requestName = request.getRequestURI();

			ErrorDescriptor error = new ErrorDescriptor(this.getClass().getName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage(), e);

			request.setAttribute("errorBean", error);

			request.setAttribute("requestName", requestName);

			request.setAttribute("userType", userType);

			request.setAttribute("loginName", loginName);

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();

			if (auth != null) {
				new SecurityContextLogoutHandler().logout(request, response, auth);
			}

			return "forward:/parentError.htm";

		}

		return returnPage;

	}

	/**
	 * @param userAuthoritiesList
	 */
	private void setUserAuthorities(List<UserAuthority> userAuthoritiesList) {

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		// List<Permission>
		permissionList = new ArrayList<Permission>();
		List<RoleToPermission> rolesToPermissionList = null;

		try {
			for (UserAuthority u : userAuthoritiesList) {
				logger.info("\n************ User Permissions Exist in DB *************\n");

				rolesToPermissionList = roleToPermissionService.getListofRolePermissions(u.getRoleId());

				int i = 0;

				while (i < rolesToPermissionList.size()) {
					logger.info("\n************ i = " + i + "*************\n");

					permissionList = permissionService
							.getListPermissions(rolesToPermissionList.get(i).getPermissionId());

					if (!permissionList.isEmpty() || permissionList.equals(null)) {
						for (Permission p : permissionList) {
							authorities.add(new SimpleGrantedAuthority("PERMISSION_" + p.getPermissionName().trim()));

							logger.info("\n************ permission:" + p.getPermissionName() + " *************\n");
						}
					}

					logger.info("\n************ User Permissions " + authorities.get(i).getAuthority().toString()
							+ " *************\n");

					i++;
				}
				SecurityContextHolder.getContext()
						.setAuthentication(new UsernamePasswordAuthenticationToken(
								SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
								SecurityContextHolder.getContext().getAuthentication().getCredentials(), authorities));
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * @param dbUser
	 * @param loginName
	 * @return
	 */
	private String updateDbUserWithNCIdResponseInfo(EcoaUser dbUser, String loginName) throws Exception {
		EcoaUser ncidUser;
		String rValue = "";
		logger.debug("\n************In loginRouteController:updateDbUserWithNCIdResponseInfo *************\n");
		ncidUser = userService.getNCIdResponseUser(loginName);

		dbUser.setLoginId(loginName);

		dbUser.setNcidFirstNM(ncidUser.getNcidFirstNM());
		dbUser.setNcidLastNM(ncidUser.getNcidLastNM());
		dbUser.setNcidMiddleNM(ncidUser.getNcidMiddleNM() != null ? ncidUser.getNcidMiddleNM() : "");
		dbUser.setNcIdGuid(ncidUser.getNcIdGuid());

		dbUser.setBrowserAgent(getAuthenticatedPrincipal().getUserBrowserAgent().replaceAll(",", ""));
		dbUser.setIpAddressPort(getAuthenticatedPrincipal().getUserIPAddress().trim());
		dbUser.setNcIdEmail(ncidUser.getNcIdEmail());

		logger.info("\n************ Updating user info..... *************\n");

		rValue = userService.createOrUpdateUserInfo(dbUser, AppConstants.OPERATION_UPDATE, AppConstants.LOGIN);

		return rValue;
	}

	/**
	 * @param loginName
	 * @return
	 */
	private String createNewUserRecord(String loginName) throws Exception {
		String rValue = "";
		EcoaUser newUser = new EcoaUser();

		logger.debug("\n************In loginRouteController:createNewUserRecord *************\n");

		// get ncid user from the ncid response and create a new user
		EcoaUser ncidUser = userService.getNCIdResponseUser(loginName);

		// when the user is created for the first time, insert NCID names
		// same as css names
		newUser.setLoginId(ncidUser.getLoginId());
		newUser.setUserStatus("N");
		newUser.setFirstName(ncidUser.getNcidFirstNM());
		newUser.setLastName(ncidUser.getNcidLastNM());
		newUser.setMiddleName(ncidUser.getMiddleName() != null ? ncidUser.getMiddleName() : "");
		newUser.setBusinessAreaId(WebsiteConfiguration.getBusinessAreaId());
		newUser.setNcidLastNM(ncidUser.getNcidLastNM());
		newUser.setNcidFirstNM(ncidUser.getNcidFirstNM());
		newUser.setNcidMiddleNM(ncidUser.getMiddleName() != null ? ncidUser.getMiddleName() : "");
		newUser.setNcIdEmail(ncidUser.getNcIdEmail());
		newUser.setIpAddressPort(getAuthenticatedPrincipal().getUserIPAddress());
		newUser.setPartId("");
		newUser.setNcIdGuid(ncidUser.getNcIdGuid());
		newUser.setBrowserAgent(getAuthenticatedPrincipal().getUserBrowserAgent().replaceAll(",", ""));

		logger.info("\n************ creating user record in db....... *************\n");

		rValue = userService.createOrUpdateUserInfo(newUser, AppConstants.OPERATION_INSERT, AppConstants.LOGIN);

		return rValue;
	}

	/**
	 * @param userId
	 * @param loginId
	 * @return
	 */
	private String createNewUserAuthority(long userId, String loginId, String userType) throws Exception {
		String rValue = "";

		logger.debug("\n************In loginRouteController:createNewUserAuthority *************\n");

		UserAuthority newUserAuthority = new UserAuthority();

		logger.debug("\n************In loginRouteController:createNewUserAuthority*************\n");

		// create user authority
		newUserAuthority.setUserId(userId);

		if (userType.equals("tcaId")) {
			newUserAuthority.setRoleId(2);// user authority to access
											// existing parent pages.
		}

		if (userType.equals("ncId") || userType.equals("pNcId")) {
			newUserAuthority.setRoleId(1); // user authority to access case
											// application pages.
		}

		// another authority role 4 is set on the pages when there is a
		// successful tying/identifying old user
		// separate controller will handle this

		newUserAuthority.setApplId(WebsiteConfiguration.getBusinessAreaId());

		rValue = userAuthorityService.createOrModifyUserAuthority(newUserAuthority, loginId,
				AppConstants.OPERATION_INSERT);

		return rValue;
	}

}
