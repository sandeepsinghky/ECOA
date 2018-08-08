package nc.dhhs.nccss.acts.ecoa.web.servlets.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;

import nc.dhhs.nccss.acts.ecoa.web.EcoaSession;
import nc.dhhs.nccss.acts.ecoa.web.security.EcoaUserDetails;

/**
 * 
 * <p>
 * Filter used to check if each request to the application has a valid
 * <code>EcoaSession</code>, redirecting to login page otherwise
 * </p>
 * 
 * @author Vijay Peddapalli
 * 
 */
public class SiteSessionFilter implements Filter
{
	private ArrayList<String> urlList;

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig config) throws ServletException
	{
		String urls = config.getInitParameter("avoid-urls");
		StringTokenizer token = new StringTokenizer(urls, ",");

		urlList = new ArrayList<String>();

		while (token.hasMoreTokens())
		{
			urlList.add(token.nextToken());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException
	{
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		String requestURL = request.getRequestURL().toString();

		HttpSession session = request.getSession(false);// gets current session if it exist and will not create new one

		if (urlList.contains(requestURL))
		{
			chain.doFilter(servletRequest, servletResponse);
			return;
		}

		if (null == session)
		{
			response.sendRedirect(request.getContextPath() + "/index.htm");
			chain.doFilter(servletRequest, servletResponse);
			return;
		}

		if (session != null)
		{
			EcoaSession ecoaSession = (EcoaSession) session.getAttribute("ecoaSession");

			if (ecoaSession != null)
			{
				if (ecoaSession.getPermissions().size() > 0)
				{
					chain.doFilter(servletRequest, servletResponse);
					return;
				}
				else
				{
					response.sendRedirect(request.getContextPath() + "/login.htm");
					return;
				}
			}

			else if (requestURL.endsWith(request.getContextPath() + "/cssp/user/parentWelcome.htm") || requestURL.endsWith(request.getContextPath() + "/cssp/user/userIdentifyInfo.htm"))
			{
				Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				if (principal instanceof EcoaUserDetails)
				{
					chain.doFilter(servletRequest, servletResponse);
					return;
				}
				else
				{
					response.sendRedirect(request.getContextPath() + "/ecoa/parentsIndex.jsp");
					return;
				}
			}
		}

		//response.sendRedirect(request.getContextPath() + "/index.htm");

		//return;
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy()
	{
		// TODO Auto-generated method stub

	}

}
