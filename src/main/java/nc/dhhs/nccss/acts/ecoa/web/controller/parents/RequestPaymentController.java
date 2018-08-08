/**
 * 
 */
package nc.dhhs.nccss.acts.ecoa.web.controller.parents;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import nc.dhhs.nccss.acts.ecoa.web.exception.ErrorDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import nc.dhhs.nccss.acts.ecoa.beans.CheckBean;
import nc.dhhs.nccss.acts.ecoa.beans.PaymentBean;
import nc.dhhs.nccss.acts.ecoa.beans.UserInformation;
import nc.dhhs.nccss.acts.ecoa.web.config.WebsiteConfiguration;
import nc.dhhs.nccss.acts.ecoa.web.controller.BasicAnnotatedFormController;
import nc.dhhs.nccss.acts.ecoa.web.exception.ErrorDescriptor;
import nc.dhhs.nccss.acts.ecoa.web.service.RequestPaymentService;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;
import nc.dhhs.nccss.acts.ecoa.web.util.EmailManager;

/**
 * @author Mallika Velagapudi
 *
 */
@Controller
public class RequestPaymentController extends BasicAnnotatedFormController {
	@Autowired
	private RequestPaymentService requestPaymentService;

	@Autowired
	protected EmailManager emailManager;

	@RequestMapping("/cssp/user/requestPayment.htm")
	public String requestPaymentViaEmail(HttpServletRequest request, Model model) {

		logger.info("\n************ in  RequestPaymentController:requestPaymentViaEmail *************\n");

		String returnPage = "";

		ArrayList<CheckBean> checkList = null;

		ArrayList<PaymentBean> paymentList = null;

		try {
			UserInformation userInfoBean = (UserInformation) request.getSession()
					.getAttribute(AppConstants.USERINFORMATION);

			String mpi = userInfoBean.getMpi();

			if (mpi.isEmpty()) {
				request.setAttribute("errorMsg",
						"Registration process has to be completed to check payment information.");

				return "forward:/cssp/user/userWelcome.htm";

			}

			if (userInfoBean.getCaseRelshp().trim().isEmpty()) // if user has
																// dual role and
																// role is not
																// selected.

			{
				request.setAttribute("errorMsg", "Custodial Parent or Non-Custodial Parent selection is required");

				returnPage = "forward:/cssp/user/parentWelcome.htm";
			} else {

				if (userInfoBean.getCaseRelshp().trim().equals("CLI")) // 'CLI'
																		// means
																		// user
																		// role
																		// as
																		// CP.
				{

					StringBuffer body = requestPaymentService.buildCPPayments(userInfoBean);

					logger.info(
							"\n************ in  RequestPaymentController:requestPaymentViaEmail :building email body process is completed as role CP*************\n");

					try {

						emailManager.sendEmail(userInfoBean.getEmailId(), AppConstants.MAIL_FROM,
								AppConstants.PAYMENT_MAIL_SUBJECT, body.toString());

						model.addAttribute("confirmation",
								"Your request for Thirteen Month Payment History has been successfully  processed by the North Carolina eChild Support Online");

						returnPage = AppConstants.ECOA_REQUEST_CONFIRM;

					}

					catch (Exception e) {

						logger.error(e.getMessage());

						model.addAttribute("errMsg",
								"There is some exception in processing the request to send 13 month payment Information through email. please try in some time.");

						returnPage = AppConstants.ECOA_REQUEST_CONFIRM;

					}

				}

				else {

					if (userInfoBean.getCaseRelshp().trim().equals("AP")) // 'AP'
																			// means
																			// role
																			// is
																			// NCP.

					{

						StringBuffer body = requestPaymentService.buildNCPPayments(userInfoBean);

						logger.info(
								"\n************ in  RequestPaymentController:requestPaymentViaEmail :building email body process is completed as role NCP*************\n");

						try {

							emailManager.sendEmail(userInfoBean.getEmailId(), AppConstants.MAIL_FROM,
									AppConstants.PAYMENT_MAIL_SUBJECT, body.toString());

							model.addAttribute("confirmation",
									"Your request for Thirteen Month Payment History has been successfully processed by the North Carolina eChild Support Online");

							returnPage = AppConstants.ECOA_REQUEST_CONFIRM;

						}

						catch (Exception e) {

							logger.error(e.getMessage());

							model.addAttribute("errMsg",
									"There is some exception in processing the request to send 13 month payment Information through email. please try in some time.");

							returnPage = AppConstants.ECOA_REQUEST_CONFIRM;

						}

					}

				}

			}

		} catch (Exception e)

		{

			logger.error(e.getMessage());

			UserInformation userInfoBean = (UserInformation) request.getSession()
					.getAttribute(AppConstants.USERINFORMATION);

			ErrorDescriptor errorBean = new ErrorDescriptor(this.getClass().getName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage(), e);

			String body = errorBean.getParentEmailBody(userInfoBean);

			emailManager.sendEmail(WebsiteConfiguration.getEmailNotify(), AppConstants.MAIL_FROM,
					AppConstants.MAIL_ERROR_SUBJECT, body);

			model.addAttribute("errMsg",
					"There is some exception in processing the request to send 13 month payment Information through email. please try in some time.");

			returnPage = AppConstants.ECOA_REQUEST_CONFIRM;

		}

		return AppConstants.ECOA_REQUEST_CONFIRM;

	}

	/*
	 * this request helps the user of this class request a payment coupon to be
	 * mailed to them. Note: The records inserted by this servlet into a db2
	 * table are processed by the Acts Batch on a nightly basis.
	 */

	@RequestMapping("/cssp/user/requestCoupon.htm")
	public String requestCoupon(HttpServletRequest request, Model model) {

		logger.info("\n************ in  RequestPaymentController:requestCoupon *************\n");

		String returnPage = "";

		try {
			UserInformation userInfoBean = (UserInformation) request.getSession()
					.getAttribute(AppConstants.USERINFORMATION);

			String mpi = userInfoBean.getMpi();

			if (mpi.isEmpty()) {
				request.setAttribute("errorMsg",
						"Registration process has to be completed to check payment information.");

				return "forward:/cssp/user/userWelcome.htm";

			}

			if (userInfoBean.getCaseRelshp().trim().isEmpty()) // if user has
																// dual role and
																// role is not
																// selected.

			{
				request.setAttribute("errorMsg", "Custodial Parent or Non-Custodial Parent selection is required");

				returnPage = "forward:/cssp/user/parentWelcome.htm";
			} else {

				if (userInfoBean.getCaseRelshp().trim().equals("AP")) {

					String ivdCase = (String) request.getSession().getAttribute(AppConstants.ivdCase);

					boolean mailExist = requestPaymentService.getMailAddress(userInfoBean.getMpi());

					if (mailExist) {

						logger.info(
								"\n************ in  RequestPaymentController:requestCoupon: participant mail address is not available to send coupon.\n");

						requestPaymentService.createWebTran(mpi, "N", ivdCase); // if
																				// caseRelshp="AP",
																				// place
																				// "N"
																				// for
																				// it

						model.addAttribute("confirmation",
								"Your request for Payment Coupon  processed successfully  by the North Carolina eChild Support Online .");

						returnPage = AppConstants.ECOA_REQUEST_CONFIRM;

					}

					else {

						model.addAttribute("errMsg",
								"Your request for Payment Coupon cannot be processed successfully  by the North Carolina eChild Support Online since valid mail address was not available .");

						returnPage = AppConstants.ECOA_REQUEST_CONFIRM;

					}
				}
			}
		}

		catch (Exception e)

		{

			logger.error(e.getMessage());

			UserInformation userInfoBean = (UserInformation) request.getSession()
					.getAttribute(AppConstants.USERINFORMATION);

			ErrorDescriptor errorBean = new ErrorDescriptor(this.getClass().getName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage(), e);

			String body = errorBean.getParentEmailBody(userInfoBean);

			model.addAttribute("errMsg",
					"Your request for Payment Coupon cannot be processed successfully  by the North Carolina eChild Support Online due to some application error. Please try after some time.");

			emailManager.sendEmail(WebsiteConfiguration.getEmailNotify(), AppConstants.MAIL_FROM,
					AppConstants.MAIL_ERROR_SUBJECT, body);

			returnPage = AppConstants.ECOA_REQUEST_CONFIRM;
		}

		return returnPage;

	}

}
