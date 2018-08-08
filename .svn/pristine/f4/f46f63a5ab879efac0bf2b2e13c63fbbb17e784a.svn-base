package nc.dhhs.nccss.acts.ecoa.web.servlets.listeners;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import nc.dhhs.nccss.acts.dao.GuideLinesDao;
import nc.dhhs.nccss.acts.ecoa.beans.Alert;
import nc.dhhs.nccss.acts.ecoa.beans.CodeLookUp;
import nc.dhhs.nccss.acts.ecoa.beans.GuideLines;
import nc.dhhs.nccss.acts.ecoa.web.service.AlertService;
import nc.dhhs.nccss.acts.ecoa.web.service.CodeLookUpService;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * 
 * 
 * @author Phani Konuru
 *
 */

@WebListener
public class ApplicationStartUpListener implements ServletContextListener
{

	protected final Logger		logger	= Logger.getLogger(ApplicationStartUpListener.class);

	@Autowired
	protected CodeLookUpService	codeLookUpService;

	@Autowired
	protected AlertService		alertService;

	@Autowired
	private GuideLinesDao		guideLinesDao;

	/*
	 * (non-Javadoc)
	 * @see
	 * javax.servlet.ServletContextListener#contextInitialized(javax.servlet.
	 * ServletContextEvent)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * javax.servlet.ServletContextListener#contextInitialized(javax.servlet.
	 * ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent event)
	{

		logger.debug("#######---- initialize servlet context -----");
		// add initialization code here

		ServletContext ctx = event.getServletContext();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

		// Load look up tables at application startup once and place it on
		// context to be used for all users on the screens.
		// Application restart is required to refresh the lookup tables data

		loadLookUp(ctx);

		// Loads GuideLines information to Guidelines.dat file at the server
		// startup.

		loadGuideLinesData(ctx);

		loadCSSAnnouncements(ctx);

	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.
	 * ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent event)
	{
		logger.debug("#######---- destroying servlet context -----");
		// clean up resources
	}

	private void loadLookUp(ServletContext ctx)
	{
		// Load look up tables at application startup
		try
		{
			List<CodeLookUp> appStatusLookup = codeLookUpService.getCodeLookup(AppConstants.CODE_LOOKUP_APP_STATUS_VALUE);
			List<CodeLookUp> langLookup = codeLookUpService.getCodeLookup(AppConstants.CODE_LOOKUP_LANG_VALUE);
			List<CodeLookUp> genderLookup = codeLookUpService.getCodeLookup(AppConstants.CODE_LOOKUP_GENDER_VALUE);
			List<CodeLookUp> relationLookup = codeLookUpService.getCodeLookup(AppConstants.CODE_LOOKUP_RELATION_VALUE);
			List<CodeLookUp> cntyLookup = codeLookUpService.getCodeLookup(AppConstants.CODE_LOOKUP_COUNTY_VALUE);
			List<CodeLookUp> stateLookup = codeLookUpService.getCodeLookup(AppConstants.CODE_LOOKUP_STATE_VALUE);
			List<CodeLookUp> ctryLookup = codeLookUpService.getCodeLookup(AppConstants.CODE_LOOKUP_CTRY_VALUE);
			List<CodeLookUp> ethncgrpLookup = codeLookUpService.getCodeLookup(AppConstants.CODE_LOOKUP_ETHNCGRP_VALUE);
			List<CodeLookUp> suffixLookup = codeLookUpService.getCodeLookup(AppConstants.CODE_LOOKUP_SUFFIX_VALUE);
			List<CodeLookUp> sassistLookup = codeLookUpService.getCodeLookup(AppConstants.CODE_LOOKUP_SASSIST_VALUE);
			List<CodeLookUp> incomeTypeLookup = codeLookUpService.getCodeLookup(AppConstants.CODE_LOOKUP_INCOME_TYPE_VALUE);
			List<CodeLookUp> healthCovLookup = codeLookUpService.getCodeLookup(AppConstants.CODE_LOOKUP_HEALTH_COV_VALUE);
			List<CodeLookUp> famlrelLookup = codeLookUpService.getCodeLookup(AppConstants.CODE_LOOKUP_FAM_REL_VALUE);
			List<CodeLookUp> maritalLookup = codeLookUpService.getCodeLookup(AppConstants.CODE_LOOKUP_MARITAL_VALUE);
			List<CodeLookUp> paterntyLookup = codeLookUpService.getCodeLookup(AppConstants.CODE_LOOKUP_PATERNTY_VALUE);
			List<CodeLookUp> militaryLookup = codeLookUpService.getCodeLookup(AppConstants.CODE_LOOKUP_MILITARY_VALUE);
			List<CodeLookUp> seloptionLookup = codeLookUpService.getCodeLookup(AppConstants.CODE_LOOKUP_SELOPTION_VALUE);
			List<CodeLookUp> milBranchLookup = codeLookUpService.getCodeLookup(AppConstants.CODE_LOOKUP_MALBRANCH_VALUE);
			List<CodeLookUp> hairColorLookup = codeLookUpService.getCodeLookup(AppConstants.CODE_LOOKUP_HAIRCOLOR_VALUE);
			List<CodeLookUp> eyeColorLookup = codeLookUpService.getCodeLookup(AppConstants.CODE_LOOKUP_EYECOLOR_VALUE);
			List<CodeLookUp> payFreqLookup = codeLookUpService.getCodeLookup(AppConstants.CODE_LOOKUP_PAYFREQ_VALUE);


			ctx.setAttribute(AppConstants.CODE_LOOKUP_APP_STATUS_LIST, appStatusLookup);
			ctx.setAttribute(AppConstants.CODE_LOOKUP_LANG_LIST, langLookup);
			ctx.setAttribute(AppConstants.CODE_LOOKUP_GENDER_LIST, genderLookup);
			ctx.setAttribute(AppConstants.CODE_LOOKUP_RELATION_LIST, relationLookup);
			ctx.setAttribute(AppConstants.CODE_LOOKUP_COUNTY_LIST, cntyLookup);
			ctx.setAttribute(AppConstants.CODE_LOOKUP_STATE_LIST, stateLookup);
			ctx.setAttribute(AppConstants.CODE_LOOKUP_CTRY_LIST, ctryLookup);
			ctx.setAttribute(AppConstants.CODE_LOOKUP_ETHNCGRP_LIST, ethncgrpLookup);
			ctx.setAttribute(AppConstants.CODE_LOOKUP_SUFFIX_LIST, suffixLookup);
			ctx.setAttribute(AppConstants.CODE_LOOKUP_SASSIST_LIST, sassistLookup);
			ctx.setAttribute(AppConstants.CODE_LOOKUP_INCOME_TYPE_LIST, incomeTypeLookup);
			ctx.setAttribute(AppConstants.CODE_LOOKUP_HEALTH_COV_LIST, healthCovLookup);
			ctx.setAttribute(AppConstants.CODE_LOOKUP_FAM_REL_LIST, famlrelLookup);
			ctx.setAttribute(AppConstants.CODE_LOOKUP_MARITAL_LIST, maritalLookup);
			ctx.setAttribute(AppConstants.CODE_LOOKUP_PATERNTY_LIST, paterntyLookup);
			ctx.setAttribute(AppConstants.CODE_LOOKUP_MILITARY_LIST, militaryLookup);
			ctx.setAttribute(AppConstants.CODE_LOOKUP_SELOPTION_LIST, seloptionLookup);
			ctx.setAttribute(AppConstants.CODE_LOOKUP_MALBRANCH_LIST, milBranchLookup);
			ctx.setAttribute(AppConstants.CODE_LOOKUP_HAIRCOLOR_LIST, hairColorLookup);
			ctx.setAttribute(AppConstants.CODE_LOOKUP_EYECOLOR_LIST, eyeColorLookup);
			ctx.setAttribute(AppConstants.CODE_LOOKUP_PAYFREQ_LIST, payFreqLookup);
		}
		catch (Exception e)
		{
			logger.error("########error in startup" + e.getMessage());
		}

	}

	/**
	 * This method loads data from GuideLines table at the application
	 * startup/server startup and the data to the file GuideLines.dat...and this
	 * file will be placed in the ------------ so that any flow like WorkSheet
	 * A,B & C can refer to this file for GuideLines data with out going to the
	 * db always.
	 **/
	public void loadGuideLinesData(ServletContext servletContext)
	{

		try
		{
			List<GuideLines> guideLinesList = guideLinesDao.getGuideLines();

			String path = servletContext.getRealPath("/WEB-INF");

			path = path + "/GuideLines.dat";

			File f = new File(path);
			if (f.exists() && f.isFile())
			{
				f.delete();
				f.createNewFile();
			}

			FileOutputStream fos = new FileOutputStream(f);
			DataOutputStream dos = new DataOutputStream(fos);

			for (GuideLines gl : guideLinesList)
			{
				String glRec = gl.toString();

				dos.writeBytes(glRec);
			}

			dos.flush();
			fos.close();

		}
		catch (

		Exception e)
		{

			logger.error("IOException in writing to  GuideLines.dat file" + e.getMessage());
		}

	}

	private void loadCSSAnnouncements(ServletContext ctx)
	{
		try
		{
			List<Alert> newsList = alertService.retrieveCSSAnnouncements();

			ctx.setAttribute("news", newsList);
		}

		catch (Exception e)
		{
			logger.error("########error in startup" + e.getMessage());
		}
	}

}