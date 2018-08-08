package nc.dhhs.nccss.acts.ecoa.web.controller.parents;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import nc.dhhs.nccss.acts.ecoa.web.controller.BasicAnnotatedFormController;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;
import nc.dhhs.nccss.acts.ecoa.web.util.EmailManager;
import nc.dhhs.nccss.acts.ecoa.web.service.FeedbackService;
import nc.dhhs.nccss.acts.ecoa.beans.TaskFormBean;
import nc.dhhs.nccss.acts.ecoa.web.config.WebsiteConfiguration;

/**
 * @author Phani Konuru
 *
 */
@Controller
public class FeedbackController extends BasicAnnotatedFormController
{

	@Autowired
	protected FeedbackService	feedbackService;
	
	@Autowired
	protected EmailManager				emailManager;

	

	@RequestMapping(value = "/feedBack.htm", method = RequestMethod.POST)
	public String processFeedBack(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		logger.debug("\n********** IN FeedbackController: processFeedBack **********\n");

		HttpSession session = request.getSession();
		String pageId = request.getParameter("pageId");

		try
		{
			String firstName = ((String) request.getParameter("firstName") != null ? (String) request.getParameter("firstName") : "");
			String middleInit = ((String) request.getParameter("middleInit") != null ? (String) request.getParameter("middleInit") : "");
			String lastName = ((String) request.getParameter("lastName") != null ? (String) request.getParameter("lastName") : "");
			String mpi = ((String) request.getParameter("mpi") != null ? (String) request.getParameter("mpi") : "");
			String addressLine = ((String) request.getParameter("addressLine") != null ? (String) request.getParameter("addressLine") : "");
			String city = ((String) request.getParameter("city") != null ? (String) request.getParameter("city") : "");
			String state = ((String) request.getParameter("state") != null ? (String) request.getParameter("state") : "");
			String county = ((String) request.getParameter("county") != null ? (String) request.getParameter("county") : "");
			String email = ((String) request.getParameter("email") != null ? (String) request.getParameter("email") : "");
			String phone = ((String) request.getParameter("phone") != null ? (String) request.getParameter("phone") : "");
			String support = ((String) request.getParameter("support") != null ? (String) request.getParameter("support") : "");
			String enforcement = ((String) request.getParameter("enforcement") != null ? (String) request.getParameter("enforcement") : "");
			String address = ((String) request.getParameter("address") != null ? (String) request.getParameter("address") : "");
			String other = ((String) request.getParameter("other") != null ? (String) request.getParameter("other") : "");
			String feedback = ((String) request.getParameter("feedback") != null ? (String) request.getParameter("feedback") : "");

			String chfname1 = ((String) request.getParameter("child1fname") != null ? (String) request.getParameter("child1fname") : "");
			String chlname1 = ((String) request.getParameter("child1lname") != null ? (String) request.getParameter("child1lname") : "");
			String chmname1 = ((String) request.getParameter("child1mname") != null ? (String) request.getParameter("child1mname") : "");

			String chfname2 = ((String) request.getParameter("child2fname") != null ? (String) request.getParameter("child2fname") : "");
			String chlname2 = ((String) request.getParameter("child2lname") != null ? (String) request.getParameter("child2lname") : "");
			String chmname2 = ((String) request.getParameter("child2mname") != null ? (String) request.getParameter("child2mname") : "");

			String chfname3 = ((String) request.getParameter("child3fname") != null ? (String) request.getParameter("child3fname") : "");
			String chlname3 = ((String) request.getParameter("child3lname") != null ? (String) request.getParameter("child3lname") : "");
			String chmname3 = ((String) request.getParameter("child3mname") != null ? (String) request.getParameter("child3mname") : "");

			String chfname4 = ((String) request.getParameter("child4fname") != null
					? (String) request.getParameter("child4fname") : "");
			String chlname4 = ((String) request.getParameter("child4lname") != null
					? (String) request.getParameter("child4lname") : "");
			String chmname4 = ((String) request.getParameter("child4mname") != null
					? (String) request.getParameter("child4mname") : "");

			String pageSource = ((String) request.getParameter("source") != null
					? (String) request.getParameter("source") : "");

			String emailDestination = ((String) request.getParameter("dest") != null
					? (String) request.getParameter("dest") : "");


			if (emailDestination.equals("ecse")) {
				int idReference = 0;
				TaskFormBean tb = new TaskFormBean();
				tb.setCdStatus("OPEN");
				tb.setCdType("WEB");
				tb.setSelfAssigned(false);

				tb.setNbTelAcd("");
				tb.setNbTelExc("");

				if ((phone != null) && (phone.length() == 10)) {
					tb.setNbTelAcd(phone.substring(0, 3));
					tb.setNbTelExc(phone.substring(3, 6));
					tb.setNbTelLn(phone.substring(6));
				}

				tb.setNbSSN("0");
				tb.setDtReceived("");
				tb.setNbCase("");
				tb.setIdEmail(email);
				tb.setIdPart(mpi);
				tb.setIdWorker("");
				tb.setNbDocket("");
				tb.setNbControl("");
				tb.setNmCounty(county);
				tb.setNmCustomerFirst(firstName);
				tb.setNmCustomerLast(lastName);
				tb.setNmCustomerMi(middleInit);
				tb.setNmCustParFirst("");
				tb.setNmCustParLast("");
				tb.setNmCustParMi("");
				tb.setNmNonCustParFirst("");
				tb.setNmNonCustParLast("");
				tb.setNmNonCustParMi("");
				tb.setNmRefSource1("");
				tb.setNmRefSource2("");
				tb.setNmRefSource3("");
				tb.setNmRefSource4("");

				StringBuffer sb = new StringBuffer();
				sb.append(feedback);

				sb.append("\n");
				sb.append("Child(ren):\n");
				sb.append("                 " + chfname1 + " " + chmname1 + " " + chlname1 + "\n");
				sb.append("                 " + chfname2 + " " + chmname2 + " " + chlname2 + "\n");
				sb.append("                 " + chfname3 + " " + chmname3 + " " + chlname3 + "\n");
				sb.append("                 " + chfname4 + " " + chmname4 + " " + chlname4 + "\n");
				sb.append("Address Information\n");
				sb.append("Street Address: " + addressLine);
				sb.append(" City: " + city + " State: " + state + "\n");
				sb.append("\nArea of Interest\n");
				sb.append("Support Issues: " + (support.equals("Y") ? "Yes" : "No"));
				sb.append("\n");
				sb.append("Enforcement Issues: " + (enforcement.equals("Y") ? "Yes" : "No"));
				sb.append("\n");
				sb.append("Address Change: " + (address.equals("Y") ? "Yes" : "No"));
				sb.append("\n");
				sb.append("Other Issues: " + (other.equals("Y") ? "Yes" : "No"));
				sb.append("\n");

				String resolution = sb.toString();

				if (resolution.length() > 3900) {
					resolution = resolution.substring(0, 3900);
				}
				tb.setNtResolution(resolution);

				idReference = feedbackService.createFeedback(tb);
				
				if(idReference != 0)
				{
					logger.info("Reference id created for feedback process "+idReference);
					// send an email
	
					StringBuffer sb1 = new StringBuffer();
					sb1.append(AppConstants.MAIL_TEXT);
					sb1.append(AppConstants.MAIL_TEXT3 +idReference + ". ");
					sb1.append(AppConstants.MAIL_TEXT4);
					sb1.append("\n\n");
					sendEmail(email, AppConstants.MAIL_FROM, AppConstants.MAIL_SUBJECT1, sb1.toString());
					model.addAttribute("success", "Your request for FeedBack Form has been successfully processed by the North Carolina eChild Support Online.");
	
				}else{
					model.addAttribute("message", "Problem encountered during feedback form process. Please try later.");
				}
				
			} else {

				try {
					EmailManager em = new EmailManager();
					String strBody = "NOTE: PLEASE DO NOT RESPOND DIRECTLY TO THIS E-MAIL MESSAGE. THIS ADDRESS IS NOT MONITORED.\n\n"
							+ "Feedback Form\n\n\n\n" + "First Name: " + firstName + " Middle Initial: " + middleInit
							+ " Last Name: " + lastName + "\n\n" + "MPI Number: " + mpi + "\n\n" + "Child(ren): \n"
							+ "            " + chfname1 + " " + chmname1 + " " + chlname1 + "\n" + "            "
							+ chfname2 + " " + chmname2 + " " + chlname2 + "\n" + "            " + chfname3 + " "
							+ chmname3 + " " + chlname3 + "\n" + "            " + chfname4 + " " + chmname4 + " "
							+ chlname4 + "\n\n" + "Address Information:\n\n" + "Street Address: " + addressLine + "\n"
							+ "City:           " + city + " State: " + state + "\n\n" + "County:         " + county
							+ "\n\n" + "Email Address:  " + email + "\n\n" + "Phone Number:   " + phone + "\n\n"
							+ "Area of Interest\n\n" + "Support Issues: " + (support.equals("Y") ? "Yes" : "No") + "\n"
							+ "Enforcement Issues: " + (enforcement.equals("Y") ? "Yes" : "No") + "\n"
							+ "Address Change: " + (address.equals("Y") ? "Yes" : "No") + "\n" + "Other Issues: "
							+ (other.equals("Y") ? "Yes" : "No") + "\n"
							+ "--------------------------------------------------------\n\n"
							+ "             Brief Description of the Request           \n\n"
							+ "--------------------------------------------------------\n\n" + feedback;
					sendEmail(WebsiteConfiguration.getOtherEmail(), email, AppConstants.MAIL_SUBJECT2, strBody);
					model.addAttribute("success", "Your request for FeedBack Form has been successfully processed by the North Carolina eChild Support Online.");
				} catch (Exception e) {
					logger.error("Email error: " + e.getMessage());
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage());

			model.addAttribute("message", "Problem encountered during feedback form process. Please try later.");

		}
		return "redirect:/feedBack/"+pageId+".htm";
	}

	private void sendEmail(String toAddr, String fromAddr, String subject, String mailBody) {

		try {
			emailManager.sendEmail(toAddr, AppConstants.MAIL_FROM, subject, mailBody);
		} catch (Exception e) {
			logger.error("Email error: " + e.getMessage());
		}
	}

}
