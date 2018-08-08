package nc.dhhs.nccss.acts.ecoa.web.reports.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.BeanNameViewResolver;

import nc.dhhs.nccss.acts.ecoa.web.reports.core.BirtEngineFactory;
import nc.dhhs.nccss.acts.ecoa.web.reports.core.BirtView;

/**
 * @author Vijay Peddapalli
 *
 */
@EnableWebMvc
@ComponentScan({ "nc.dhhs.nccss.acts.ecoa.web.reports.core", "nc.dhhs.nccss.acts.ecoa.web.reports.config", "nc.dhhs.nccss.acts.ecoa.web.controller", "nc.dhhs.nccss.acts.ecoa.web.reports.service" })
@Configuration
public class ReportWebConfiguration extends WebMvcConfigurerAdapter
{

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
	 * #addViewControllers(org.springframework.web.servlet.config.annotation.
	 * ViewControllerRegistry)
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry)
	{
		registry.addViewController("/reports").setViewName("birtView");

	}

	/**
	 * @return
	 */
	@Bean
	public BirtView birtView()
	{
		BirtView bv = new BirtView();
		// bv.setReportFormatRequestParameter("ReportFormat");
		// bv.setReportNameRequestParameter("ReportName");
		bv.setBirtEngine(this.engine().getObject());
		return bv;
	}

	/**
	 * @return
	 */
	@Bean
	public BeanNameViewResolver beanNameResolver()
	{
		BeanNameViewResolver br = new BeanNameViewResolver();
		return br;
	}

	/**
	 * @return
	 */
	@Bean
	protected BirtEngineFactory engine()
	{
		BirtEngineFactory factory = new BirtEngineFactory();
		// factory.setLogLevel( Level.FINEST);
		// factory.setLogDirectory ( new File ("c:/logs"));
		// factory.setLogDirectory( new FileSystemResource("/logs"));

		return factory;
	}
}
