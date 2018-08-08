package nc.dhhs.nccss.acts.ecoa.web.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import nc.dhhs.nccss.acts.ecoa.beans.PreNcIdUser;
import nc.dhhs.nccss.acts.ecoa.web.EcoaSession;
import nc.dhhs.nccss.acts.ecoa.web.security.EcoaUserDetails;
import nc.dhhs.nccss.acts.ecoa.web.typeconverters.DateTypeConverter;

/**
 * Base Class for all ecoa controllers.
 * 
 * 
 * @author Vijay Peddapalli
 * 
 */
@Controller
public class BasicAnnotatedFormController
{

	protected final Logger logger = Logger.getLogger(getClass());

	/**
	 * <p>
	 * Registers all custom type handlers
	 * </p>
	 * 
	 * @param binder
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder)
	{
		binder.registerCustomEditor(Date.class, new DateTypeConverter());
	}

	/**
	 * 
	 * @param request
	 * @return instance of EcoaSession object.
	 */
	protected EcoaSession getEcoaSession(HttpServletRequest request)
	{

		HttpSession session = request.getSession();
		EcoaSession ecoaSession = (EcoaSession) session.getAttribute("ecoaSession");
		if (ecoaSession == null)
		{
			ecoaSession = new EcoaSession();
			session.setAttribute("ecoaSession", ecoaSession);
			logger.info("Ecoa session has been created....");
		}
		return ecoaSession;
	}

	/**
	 * @return
	 */
	protected EcoaUserDetails getAuthenticatedPrincipal()
	{
		EcoaUserDetails userDetails = (EcoaUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userDetails;
	}

	/**
	 * @return
	 */
	protected String getUserNameFromAuthPrincipal()
	{
		return getAuthenticatedPrincipal().getUsername();
	}

	/**
	 * @return
	 */
	protected PreNcIdUser getPreNcIdUserFromAuthPrincipal()
	{
		return getAuthenticatedPrincipal().getPreNCIdUser();
	}

}
