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
import nc.dhhs.nccss.acts.ecoa.web.controller.BasicAnnotatedFormController;
import nc.dhhs.nccss.acts.ecoa.web.service.PaymentHistoryService;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * @author Mallika Velagapudi
 *
 */
@Controller
public class PaymentHistoryController extends BasicAnnotatedFormController
{
	@Autowired
	private PaymentHistoryService paymentHistService;

	@RequestMapping("/cssp/user/paymentHistory.htm")
	public String getPaymentHistory(HttpServletRequest request, Model model)
	{
		logger.info("\n************ in PaymentHistoryController:getPaymentHistory *************\n");

		String returnPage = "";

		ArrayList<CheckBean> checkList = null;

		ArrayList<PaymentBean> paymentList = null;

		try
		{
			UserInformation userInfoBean = (UserInformation) request.getSession().getAttribute(AppConstants.USERINFORMATION);

			String mpi = userInfoBean.getMpi();
			
			if (mpi.isEmpty()) {
				request.setAttribute("errorMsg",
						"Registration process has to be completed to check payment information.");

				return "forward:/cssp/user/userWelcome.htm";

			}

			if (userInfoBean.getCaseRelshp().trim().isEmpty()) //if user has dual role and role is not selected.

			{
				request.setAttribute("errorMsg", "Custodial Parent or Non-Custodial Parent selection is required");

				returnPage = "forward:/cssp/user/parentWelcome.htm";
			}
			else
			{

				if (userInfoBean.getCaseRelshp().trim().equals("CLI")) //'CLI' means user role as CP.
				{

					checkList = paymentHistService.getCreditForCP(mpi);

					model.addAttribute("checkList", checkList);

					returnPage = AppConstants.ECOA_PARENTS_CLIENT_CHECKS;

				}

				else
				{

					if (userInfoBean.getCaseRelshp().trim().equals("AP")) // 'AP' means user role as NCP.

					{

						paymentList = paymentHistService.getPaymentsByNCP(mpi);

						model.addAttribute("paymentList", paymentList);

						returnPage = AppConstants.ECOA_PARENTS_NCP_PAYMENTS;
					}

				}

			}

		}
		catch (Exception e)

		{

			logger.error(e.getMessage());

			ErrorDescriptor error = new ErrorDescriptor(this.getClass().getName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage(), e);

			request.setAttribute("errorBean", error);

			error = null;

			return "forward:/parentError.htm";

		}

		return returnPage;

	}

}
