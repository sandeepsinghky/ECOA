/**
 * 
 */
package nc.dhhs.nccss.acts.ecoa.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import nc.dhhs.nccss.acts.ecoa.web.service.CaseApplicationService;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * @author Vijay Peddapalli
 *
 */
@Controller
public class ReportsController extends BasicAnnotatedFormController
{
	@Autowired
	protected CaseApplicationService caseApplService;

	@RequestMapping("/cssp/user/reports.htm")
	public String execute(HttpServletRequest request, Model model)
	{
		logger.info("\n********** in ReportsController execute ************");
		return AppConstants.ECOA_REPORTS;
	}
}
