package nc.dhhs.nccss.acts.ecoa.web.security;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import nc.dhhs.nccss.acts.ecoa.beans.PreNcIdUser;

/**
 * This object gets instantiated right after user gets successfully
 * authenticated in EcoaUserDetailsService implementor.
 * 
 * @author Vijay Peddapalli
 *
 */

public class EcoaUserDetails implements UserDetails
{

	private static final long						serialVersionUID	= 1L;

	protected final Logger							logger				= Logger.getLogger(EcoaUserDetails.class);

	private String									username;

	private String									password;

	private Collection<? extends GrantedAuthority>	authorities;

	private boolean									accountNonExpired;

	private boolean									accountNonLocked;

	private boolean									credentialsNonExpired;

	private boolean									enabled;

	private String									userIPAddress;

	private String									userBrowserAgent;

	private PreNcIdUser								preNCIdUser			= null;
	
	private String									errorMessage;

	/**
	 * @param userId
	 * @param password
	 * @param authorities
	 * @param accountNonExpired
	 * @param accountNonLocked
	 * @param credentialsNonExpired
	 * @param enabled
	 * @param ipaddress
	 * @param browserAgent
	 * @param preNCIdUser
	 */
	public EcoaUserDetails(String userId, String password, Collection<? extends GrantedAuthority> authorities,
			boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled,
			String ipaddress, String browserAgent, PreNcIdUser preNCIdUser, String errorMsg)
	{
		super();
		logger.debug("\n********** IN EcoaUserDetails: EcoaUserDetails constructor userId: " + userId + " **********\n");

		this.username = userId;
		this.password = password;
		this.authorities = authorities;
		this.accountNonExpired = accountNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.credentialsNonExpired = credentialsNonExpired;
		this.enabled = enabled;
		this.userIPAddress = ipaddress;
		this.userBrowserAgent = browserAgent;
		this.setPreNCIdUser(preNCIdUser);
		this.errorMessage = errorMsg;
		
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.security.core.userdetails.UserDetails#getAuthorities(
	 * )
	 */
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		// TODO Auto-generated method stub
		return authorities;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.security.core.userdetails.UserDetails#getPassword()
	 */
	public String getPassword()
	{
		// TODO Auto-generated method stub
		return password;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.security.core.userdetails.UserDetails#getUsername()
	 */
	public String getUsername()
	{
		// TODO Auto-generated method stub
		return username;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#
	 * isAccountNonExpired()
	 */
	public boolean isAccountNonExpired()
	{
		// TODO Auto-generated method stub
		return accountNonExpired;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#
	 * isAccountNonLocked()
	 */
	public boolean isAccountNonLocked()
	{
		// TODO Auto-generated method stub
		return accountNonLocked;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#
	 * isCredentialsNonExpired()
	 */
	public boolean isCredentialsNonExpired()
	{
		// TODO Auto-generated method stub
		return credentialsNonExpired;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.security.core.userdetails.UserDetails#isEnabled()
	 */
	public boolean isEnabled()
	{
		// TODO Auto-generated method stub
		return enabled;
	}

	/**
	 * @param username
	 */
	public void setUsername(String username)
	{
		this.username = username;
	}

	/**
	 * @param password
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}

	/**
	 * @param accountNonExpired
	 */
	public void setAccountNonExpired(boolean accountNonExpired)
	{
		this.accountNonExpired = accountNonExpired;
	}

	/**
	 * @param accountNonLocked
	 */
	public void setAccountNonLocked(boolean accountNonLocked)
	{
		this.accountNonLocked = accountNonLocked;
	}

	/**
	 * @param credentialsNonExpired
	 */
	public void setCredentialsNonExpired(boolean credentialsNonExpired)
	{
		this.credentialsNonExpired = credentialsNonExpired;
	}

	/**
	 * @param enabled
	 */
	public void setEnabled(boolean enabled)
	{
		this.enabled = enabled;
	}

	/**
	 * @return the userIPAddress
	 */
	public String getUserIPAddress()
	{
		return userIPAddress;
	}

	/**
	 * @param userIPAddress
	 *            the userIPAddress to set
	 */
	public void setUserIPAddress(String userIPAddress)
	{
		this.userIPAddress = userIPAddress;
	}

	/**
	 * @return the userBrowserAgent
	 */
	public String getUserBrowserAgent()
	{
		return userBrowserAgent;
	}

	/**
	 * @param userBrowserAgent
	 *            the userBrowserAgent to set
	 */
	public void setUserBrowserAgent(String userBrowserAgent)
	{
		this.userBrowserAgent = userBrowserAgent;
	}

	/**
	 * @return the preNCIdUser
	 */
	public PreNcIdUser getPreNCIdUser()
	{
		return preNCIdUser;
	}

	/**
	 * @param preNCIdUser
	 *            the preNCIdUser to set
	 */
	public void setPreNCIdUser(PreNcIdUser preNCIdUser)
	{
		this.preNCIdUser = preNCIdUser;
	}
	
	/**
	 * @return
	 */
	public boolean isError()
	{
		return !accountNonExpired || !accountNonLocked || !credentialsNonExpired || !enabled;
	}

	public String getErrorMessage()
	{
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage)
	{
		this.errorMessage = errorMessage;
	}

}
