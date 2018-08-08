package nc.dhhs.nccss.acts.ecoa.web.security;

import java.sql.Date;
import java.util.ArrayList;
import java.util.zip.DataFormatException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import gov.nc.ncidng.ncidngwebservice.schemas.AuthenticateToNCIDv2Response;
import gov.nc.ncidng.ncidngwebservice.schemas.SearchUserByLoginIDResponse;
import nc.dhhs.nccss.acts.ecoa.beans.PreNcIdUser;
import nc.dhhs.nccss.acts.ecoa.web.config.WebsiteConfiguration;
import nc.dhhs.nccss.acts.ecoa.web.exception.ErrorDescriptor;
import nc.dhhs.nccss.acts.ecoa.web.service.AuthenticationWebService;
import nc.dhhs.nccss.acts.ecoa.web.service.UserService;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;
import nc.dhhs.nccss.acts.ecoa.web.util.EmailManager;

/**
 * This class retrieves user details from the authentication web services.
 * 
 * NCID web service call & logic should go in here.
 * 
 * @author Vijay Peddapalli
 *
 */
public class EcoaUserDetailsServiceImpl implements UserDetailsService {

	private AuthenticationWebService authenticationService = null;

	@Autowired
	private UserService userService;

	@Autowired
	protected EmailManager emailManager;

	protected final Logger logger = Logger.getLogger(EcoaUserDetailsServiceImpl.class);

	public EcoaUserDetailsServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String)
	 */
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		logger.debug("\n********** IN EcoaUserDetailsServiceImpl: loadUserByUsername(userName: " + userName
				+ ")**********\n");

		return loadUser(userName, null, null, null, false);
	}

	/**
	 * @param userName
	 * @param password
	 * @param ipaddress
	 * @param browserUserAgent
	 * @return
	 */
	public UserDetails loadAndAuthenticate(String userName, String password, String ipaddress,
			String browserUserAgent) {
		logger.debug("\n**********IN EcoaUserDetailsServiceImpl: loadAndAuthenticate(" + userName + ",password:*****"
				+ ",ipaddress" + ipaddress + "," + browserUserAgent + ")**********\n");

		return loadUser(userName, password, ipaddress, browserUserAgent, true);
	}

	/**
	 * @param userName
	 * @param password
	 * @param ipaddress
	 * @param browserUserAgent
	 * @param userType
	 * @return
	 */
	public UserDetails loadAndAuthenticate(String userName, String password, String ipaddress, String browserUserAgent,
			String userType) {
		logger.debug("\n**********IN EcoaUserDetailsServiceImpl: loadAndAuthenticate(" + userName + ",password:*****"
				+ ",ipaddress" + ipaddress + "," + browserUserAgent + "authenticate: true , UserType" + userType
				+ ")**********\n");

		return loadUser(userName, password, ipaddress, browserUserAgent, true, userType);
	}

	/**
	 * @param username
	 * @param password
	 * @param ipaddress
	 * @param browserUserAgent
	 * @param authenticate
	 * @return
	 * @throws AuthenticationException
	 * @throws DataAccessException
	 */
	private UserDetails loadUser(String username, String password, String ipaddress, String browserUserAgent,
			boolean authenticate) throws AuthenticationException, DataAccessException {
		logger.debug("\n**********IN EcoaUserDetailsServiceImpl: loadUser(" + username + ",password:*****"
				+ ",ipaddress" + ipaddress + ",browserUserAgent" + browserUserAgent + "authenticate" + authenticate
				+ ")**********\n");

		UserDetails userDetails = null;
		AuthenticateToNCIDv2Response authResponse = null;
		SearchUserByLoginIDResponse slogInIDResponse = null;

		boolean isPasswordExpired = false;
		boolean isAccountExpired = false;
		boolean isAccountDisabled = false;
		boolean isAccountLocked = false;

		try {
			if (authenticate) {
				authResponse = getAuthenticationService().getNCIDAuthenticationReponse(username, password);
			}

			// check if the authentication is success or not
			String authenticationCode = authResponse.getAuthenticateToNCIDv2Result().getMessageArray().getMessage()
					.get(0).getCode();
			String authDesc = authResponse.getAuthenticateToNCIDv2Result().getMessageArray().getMessage().get(0)
					.getContent();
			logger.info("***NCID authentication code = " + authenticationCode + ", authentication description = "
					+ authDesc);
			if (authenticationCode.equals(AppConstants.NCID_AUTH_SUCCESS)) {
				slogInIDResponse = getAuthenticationService().searchUserByLoginID(username);

				String ncId = slogInIDResponse.getSearchUserByLoginIDResult().getEntryArray().getEntry().get(0)
						.getUserID();

				userDetails = new EcoaUserDetails(ncId, password, new ArrayList<GrantedAuthority>(), !isAccountExpired,
						!isAccountLocked, !isPasswordExpired, !isAccountDisabled, ipaddress, browserUserAgent, null,
						authDesc);
			} else {
				// else check for the authentication failures reason/ fault
				// codes.
				// have to include code for errors & should show appropriate
				// messages on the front end why user failed login.
				if (authenticationCode.equals(AppConstants.NCID_USER_LOCKED_CODE)) {
					userDetails = new EcoaUserDetails(username, password, new ArrayList<GrantedAuthority>(),
							!isAccountExpired, !isAccountLocked, !isPasswordExpired, !isAccountDisabled, ipaddress,
							browserUserAgent, null, authDesc);
				} else if (authenticationCode.equals(AppConstants.NCID_PASSWORD_EXPIRED)) {
					userDetails = new EcoaUserDetails(username, password, new ArrayList<GrantedAuthority>(),
							!isAccountExpired, !isAccountLocked, !isPasswordExpired, !isAccountDisabled, ipaddress,
							browserUserAgent, null, authDesc);
				}
			}

		}

		catch (UsernameNotFoundException unfex) {
			logger.debug(unfex.getMessage());
			throw unfex;
		} catch (AuthenticationServiceException asex) {
			logger.debug(asex.getMessage());
			throw asex;
		} catch (Exception ex) {
			logger.debug(ex.getMessage());
			throw new AuthenticationServiceException(
					"We are having technical problems. Please try login after few minutes. The Technical Team has been notified");
		}

		return userDetails;

	}

	/**
	 * @param userName
	 * @param password
	 * @param ipaddress
	 * @param browserUserAgent
	 * @param authenticate
	 * @param userType
	 * @return
	 * @throws AuthenticationException
	 * @throws DataAccessException
	 */
	private UserDetails loadUser(String userName, String password, String ipaddress, String browserUserAgent,
			boolean authenticate, String userType) throws AuthenticationException, DataAccessException {
		logger.debug("\n**********IN EcoaUserDetailsServiceImpl: loadUser(" + userName + ",password:*****"
				+ ",ipaddress" + ipaddress + ",browserUserAgent" + browserUserAgent + "authenticate" + authenticate
				+ ")**********\n");

		UserDetails userDetails = null;
		AuthenticateToNCIDv2Response authResponse = null;
		SearchUserByLoginIDResponse slogInIDResponse = null;

		boolean isPasswordExpired = false;
		boolean isAccountExpired = false;
		boolean isAccountDisabled = false;
		boolean isAccountLocked = false;

		try {
			if (authenticate) {
				if (userType.equalsIgnoreCase("ncid") || userType.equalsIgnoreCase("pncid")) {
					logger.info("\n********** Authenticating NCID user **********\n");

					authResponse = getAuthenticationService().getNCIDAuthenticationReponse(userName, password);

					// check if the authentication is success or not
					String authenticationCode = authResponse.getAuthenticateToNCIDv2Result().getMessageArray()
							.getMessage().get(0).getCode();
					String authDesc = authResponse.getAuthenticateToNCIDv2Result().getMessageArray().getMessage().get(0)
							.getContent();
					logger.info("***NCID authentication code = " + authenticationCode
							+ ", authentication description = " + authDesc);

					if (authenticationCode.equals(AppConstants.NCID_AUTH_SUCCESS)) {
						// successfully authenticated
						slogInIDResponse = getAuthenticationService().searchUserByLoginID(userName);

						String ncId = slogInIDResponse.getSearchUserByLoginIDResult().getEntryArray().getEntry().get(0)
								.getUserID();

						userDetails = new EcoaUserDetails(ncId, password, new ArrayList<GrantedAuthority>(),
								!isAccountExpired, !isAccountLocked, !isPasswordExpired, !isAccountDisabled, ipaddress,
								browserUserAgent, null, authDesc);
					} else {
						// else check for the authentication failures reason/
						// fault codes.
						// have to include code for errors & should show
						// appropriate
						// messages on the front end why user failed login.
						if (authenticationCode.equals(AppConstants.NCID_USER_LOCKED_CODE)) {
							userDetails = new EcoaUserDetails(userName, password, new ArrayList<GrantedAuthority>(),
									!isAccountExpired, isAccountLocked, !isPasswordExpired, !isAccountDisabled,
									ipaddress, browserUserAgent, null, authDesc);
						} else if (authenticationCode.equals(AppConstants.NCID_PASSWORD_EXPIRED)) {
							userDetails = new EcoaUserDetails(userName, password, new ArrayList<GrantedAuthority>(),
									!isAccountExpired, !isAccountLocked, isPasswordExpired, !isAccountDisabled,
									ipaddress, browserUserAgent, null, authDesc);
						} else {
							// for all other error codes
							userDetails = new EcoaUserDetails(userName, password, new ArrayList<GrantedAuthority>(),
									!isAccountExpired, !isAccountLocked, !isPasswordExpired, isAccountDisabled,
									ipaddress, browserUserAgent, null, authDesc);
						}
					}
				}

				if (userType.equalsIgnoreCase("tcaId")) {

					logger.info("\n********** Authenticating preNCID user **********\n");

					if (userName.isEmpty() || password.isEmpty()) {

						throw new DataFormatException("both old userId or old password should not be empty.");
					}

					if (!userName.matches("^[a-zA-Z0-9]+$")) {
						throw new DataFormatException("old useId  contains only characters and numbers.");
					}

					if (userName.length() < 5 || userName.length() > 10) {
						throw new DataFormatException("old userId should be between 5 and 10 characters");
					}

					if (password.length() < 5 || password.length() > 10) {
						throw new DataFormatException("password should be between 5 and 10 characters");
					}

					logger.info("end of backend validations for old user login. userId is: " + userName);

					PreNcIdUser preNcIdUser = userService.preNCIDUserAuthentication(userName, password);

					if (preNcIdUser != null) {

						Date deActivated = preNcIdUser.getDateDeactivated();

						if (("0001-01-01".equals(deActivated.toString()))) {
							isAccountExpired = false;
						} else {
							isAccountExpired = true;
						}
						userDetails = new EcoaUserDetails(preNcIdUser.getId_user(), password,
								new ArrayList<GrantedAuthority>(), !isAccountExpired, !isAccountLocked,
								!isPasswordExpired, !isAccountDisabled, ipaddress, browserUserAgent, preNcIdUser, "");
					}

				}

				// else check for the authentication failures reason/ fault
				// codes.
				// have to include code for errors & should show appropriate
				// messages on the front end why user failed login.

			}
		}

		catch (DataFormatException de)

		{
			throw new AuthenticationServiceException(de.getMessage());

		}

		catch (UsernameNotFoundException unfex) {
			logger.debug(unfex.getMessage());
			throw unfex;
		} catch (AuthenticationServiceException asex) {
			logger.debug(asex.getMessage());
			throw asex;
		} catch (Exception ex) {
			logger.debug(ex.getMessage());

			ErrorDescriptor errorBean = new ErrorDescriptor(this.getClass().getName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), ex.getMessage(), ex);

			String body = errorBean.getEmailBody();

			emailManager.sendEmail(WebsiteConfiguration.getEmailNotify(), AppConstants.MAIL_FROM,
					AppConstants.MAIL_ERROR_SUBJECT, body);

			throw new AuthenticationServiceException(
					"A technical problem with login has been detected.  Please try to login later. An Administrator has been notified of the error.");

		}

		return userDetails;

	}

	/**
	 * @return
	 */
	public AuthenticationWebService getAuthenticationService() {
		return authenticationService;
	}

	/**
	 * @param authenticationService
	 */
	public void setAuthenticationService(AuthenticationWebService authenticationService) {
		this.authenticationService = authenticationService;
	}

}
