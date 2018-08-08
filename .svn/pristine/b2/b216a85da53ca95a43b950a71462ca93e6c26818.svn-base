package nc.dhhs.nccss.acts.ecoa.web;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author Vijay Peddapalli
 *
 */
public class ApplicationContextProvider implements ApplicationContextAware
{

	protected final Logger				logger	= Logger.getLogger(ApplicationContextProvider.class);

	private static ApplicationContext	applicationContext;

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.context.ApplicationContextAware#setApplicationContext
	 * (org.springframework.context.ApplicationContext)
	 */
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
	{
		ApplicationContextProvider.applicationContext = applicationContext;
	}

	/**
	 * @return
	 */
	public static ApplicationContext getApplicationContext()
	{
		return applicationContext;
	}

}
