package nc.dhhs.nccss.acts.ecoa.web.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * @author Vijay Peddapalli
 *
 */

public class EcoaDaoAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider
{

	protected final Logger				logger	= Logger.getLogger(EcoaDaoAuthenticationProvider.class);

	private EcoaUserDetailsServiceImpl	userDetailsService;

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.authentication.dao.
	 * AbstractUserDetailsAuthenticationProvider#additionalAuthenticationChecks(
	 * org.springframework.security.core.userdetails.UserDetails,
	 * org.springframework.security.authentication.
	 * UsernamePasswordAuthenticationToken)
	 */
	@Override
	protected void additionalAuthenticationChecks(UserDetails arg0, UsernamePasswordAuthenticationToken arg1) throws AuthenticationException
	{
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.authentication.dao.
	 * AbstractUserDetailsAuthenticationProvider#retrieveUser(java.lang.String,
	 * org.springframework.security.authentication.
	 * UsernamePasswordAuthenticationToken)
	 */
	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException
	{
		logger.debug("\n********** IN EcoaDaoAuthenticationProvider: retrieveUser **********\n");

		UserDetails loadedUser = null;

		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

		HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

		HttpSession session = request.getSession();

		String userType = (session.getAttribute("userType") != null ? session.getAttribute("userType").toString().trim() : "");

		loadedUser = retrieveLoadedUser(username, authentication, userType);
		
		if(loadedUser == null){
			session.setAttribute(AppConstants.LOGIN_ERROR_MSG, "Invalid user credentials supplied");
			logger.info("ERROR; Invalid user credentials supplied");
		}else if(((EcoaUserDetails)loadedUser).isError()){
			session.setAttribute(AppConstants.LOGIN_ERROR_MSG, ((EcoaUserDetails)loadedUser).getErrorMessage());
		}
		
		if (loadedUser == null)
		{ 
			throw new AuthenticationServiceException("Invalid user credentials supplied. Please try again.");
		}

		return loadedUser;
	}

	/**
	 * @param username
	 * @param authentication
	 * @param userType
	 * @return
	 * @throws AuthenticationException
	 */
	private UserDetails retrieveLoadedUser(String username, UsernamePasswordAuthenticationToken authentication, String userType) throws AuthenticationException
	{

		logger.debug("\n********** IN retrieveLoadedUser: (username: " + username + " UsernamePasswordAuthenticationToken, userType: " + userType + " )" + " **********\n");

		UserDetails loadedUser;
		
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

		HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
		
		HttpSession session = request.getSession();
		

		try
		{
			String password = authentication.getCredentials().toString();

			// PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


			String ipaddress = request.getRemoteAddr();

			String browserUserAgent = request.getHeader("User-Agent");

			logger.info("username:" + username + ",password: **,ipaddress: " + ipaddress + ",browserUserAgent:" + browserUserAgent + ", UserType" + userType + ")");

			loadedUser = getUserDetailsService().loadAndAuthenticate(username, password, ipaddress, browserUserAgent, userType);

		}
		catch (DataAccessException repositoryProblem)
		{
			throw new AuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
		}

		return loadedUser;
	}

	/**
	 * @return
	 */
	public EcoaUserDetailsServiceImpl getUserDetailsService()
	{
		return userDetailsService;
	}

	/**
	 * @param userDetailsService
	 */
	public void setUserDetailsService(EcoaUserDetailsServiceImpl userDetailsService)
	{
		this.userDetailsService = userDetailsService;
	}

}
