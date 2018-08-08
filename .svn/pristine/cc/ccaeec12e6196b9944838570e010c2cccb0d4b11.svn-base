/**
 * 
 */
package nc.dhhs.nccss.acts.ecoa.web.reports.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import nc.dhhs.nccss.acts.ecoa.web.service.CaseApplicationService;
import nc.dhhs.nccss.acts.ecoa.web.service.CaseParticipantService;
import nc.dhhs.nccss.acts.ecoa.web.service.UserSignatureService;
import nc.dhhs.nccss.acts.ecoa.web.service.impl.CaseApplicationServiceImpl;
import nc.dhhs.nccss.acts.ecoa.web.service.impl.CaseParticipantServiceImpl;
import nc.dhhs.nccss.acts.ecoa.web.service.impl.UserSignatureServiceImpl;

/**
 * @author Vijay Peddapalli
 *
 */
@Configuration
public class ReportDataServiceConfiguration
{

	/**
	 * @return
	 */
	@Bean
	public CaseApplicationService caseApplService()
	{
		return new CaseApplicationServiceImpl();
	}

	@Bean
	public UserSignatureService userSignatureService()
	{
		return new UserSignatureServiceImpl();
	}

	@Bean
	public CaseParticipantService casePartService()
	{
		return new CaseParticipantServiceImpl();
	}

}
