package nc.dhhs.nccss.acts.ecoa.web.controller.parents;

import java.util.zip.DataFormatException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import nc.dhhs.nccss.acts.ecoa.beans.EcoaUser;
import nc.dhhs.nccss.acts.ecoa.beans.PreNcIdUser;
import nc.dhhs.nccss.acts.ecoa.beans.UserInformation;
import nc.dhhs.nccss.acts.ecoa.web.config.WebsiteConfiguration;
import nc.dhhs.nccss.acts.ecoa.web.controller.BasicAnnotatedFormController;
import nc.dhhs.nccss.acts.ecoa.web.exception.ErrorDescriptor;
import nc.dhhs.nccss.acts.ecoa.web.service.ParentUserInfoService;
import nc.dhhs.nccss.acts.ecoa.web.service.UserRegisterService;
import nc.dhhs.nccss.acts.ecoa.web.service.UserService;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * @author Mallika Velagapudi
 *
 */
@Controller
public class UserRegistrationController extends BasicAnnotatedFormController {

	@Autowired
	private UserRegisterService registerService;

	@Autowired
	private UserService userService;

	@Autowired
	private ParentUserInfoService userInfoService;

	@SuppressWarnings("unchecked")
	@RequestMapping("/cssp/user/userWelcome.htm")
	public String getUserRegisterStatus(HttpServletRequest request, Model model) throws Exception

	{
		logger.debug("\n********** In  UserRegistrationController: getUserRegisterStatus **********\n");

		boolean isLocked = false;

		// here we need to keep code to check NB_ATTEMPTS.

		UserInformation userInfoBean = (UserInformation) request.getSession()
				.getAttribute(AppConstants.USERINFORMATION);

		String errorMsg = (String) request.getAttribute("errorMsg");

		if (errorMsg != null && !errorMsg.isEmpty()) {

			model.addAttribute("errorMsg", errorMsg);

		}

		return AppConstants.ECOA_USER_WELCOME;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/cssp/user/userIdentifyInfo.htm")
	public String getUserIdentifyInfo(HttpServletRequest request, Model model) throws Exception {
		logger.debug("\n********** In  UserRegistrationController: getUserIdentifyInfo **********\n");

		int attempts = 0;

		String returnPage = "";

		String errorMsg = "";

		try {
			errorMsg = (String) request.getAttribute("errorMsg");

			String ncId = getUserNameFromAuthPrincipal();// login name of user.

			EcoaUser loggedUser = userService.getDBUser(ncId);

			UserInformation userInfoBean = (UserInformation) request.getSession()
					.getAttribute(AppConstants.USERINFORMATION);

			if (loggedUser.getNbAttempt() >= WebsiteConfiguration.getMaxRegFailAttempts())

			{
				userInfoBean.setRegLock(true);

				returnPage = "forward:/cssp/user/userWelcome.htm";

			}

			else {

				if (errorMsg != null && !errorMsg.isEmpty()) {
					model.addAttribute("errorMsg", errorMsg);

				}

				returnPage = AppConstants.ECOA_USER_IDENTIFYINFO;

			}

		} catch (Exception e) {

			logger.error(e.getMessage());

			ErrorDescriptor error = new ErrorDescriptor(this.getClass().getName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage(), e);

			request.setAttribute("errorBean", error);

			error = null;

			return "forward:/parentError.htm";
		}

		return returnPage;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/cssp/user/userIdentifyProcess.htm")
	public String getUserIdentifyProcess(HttpServletRequest request, Model model) throws Exception {
		logger.debug("\n********** In  UserRegistrationController: getUserIdentifyInfo **********\n");

		String ncId = getUserNameFromAuthPrincipal();// login name of user.

		String mpi = "";

		String ssn = "";

		String dob = "";

		StringBuffer idPart = new StringBuffer();

		String returnPage = "";

		int attempts = 0;

		boolean isLocked = false;

		// required regex for ssn and mpi is "\\d{10}" and ^\d{9}$

		PreNcIdUser regOldUser = null;

		try {

			if (request.getParameter("mpi") != null && !request.getParameter("mpi").equals("")) {
				mpi = request.getParameter("mpi");

				if (!mpi.matches("\\d{10}")) {
					throw new DataFormatException("MPI should be 10 digits long number");

				}

			} else {

				if (request.getParameter("ssn") != null && !request.getParameter("ssn").equals("")) {
					ssn = request.getParameter("ssn");

					if (!ssn.matches("^\\d{3}[- ]?\\d{2}[- ]?\\d{4}$")) {
						throw new DataFormatException("SSN should be all digits in format xxx-xx-xxxx or NNNXXNNNN");
					}

					ssn = ssn.replaceAll("-", "");

				}

				if (mpi.isEmpty() && ssn.isEmpty()) {

					throw new DataFormatException("Either MPI or SSN should be provided.");
				}

			}

			if (request.getParameter("dob") != null && !request.getParameter("dob").equals("")) {
				dob = request.getParameter("dob");

				if (!dob.matches("^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$")) {
					throw new DataFormatException("Please enter date in mm/dd/yyyy format");

				}

			}

			if (dob.isEmpty()) {
				throw new DataFormatException("Data Of Birth should not be empty");

			}

			UserInformation userInfoBean = (UserInformation) request.getSession()
					.getAttribute(AppConstants.USERINFORMATION);

			regOldUser = registerService.isRegisteredOldUser(mpi, ssn, dob);

			if (regOldUser != null) {

				logger.debug(
						"\n********** In  UserRegistrationController: provided mpi/ssn details has existing old user.**********\n");

				request.getSession().setAttribute(AppConstants.registeredOldUser, regOldUser);

				String userMpi = regOldUser.getId_part().trim();

				String mpiUsedUser = registerService.getMPIUsedUser(userMpi);

				if (mpiUsedUser != null) {
					logger.debug(
							"\n********** In  UserRegistrationController: MPI is already registered to other user: **********\n");

					request.setAttribute("errorMsg", AppConstants.err_323);
					returnPage = "forward:/cssp/user/userIdentifyInfo.htm";

				} else {
					logger.debug(
							"\n********** In  UserRegistrationController: MPI is not already registered by any other user **********\n");
					returnPage = "forward:/cssp/user/preUserIdentify.htm";
				}

			} else {

				String partStatus = registerService.getParticipantStatus(mpi, ssn, dob, idPart);
				logger.debug("\n********** In  UserRegistrationController: status of th empi provided: " + partStatus
						+ "\n");

				EcoaUser loggedUser = userService.getDBUser(ncId);

				if (partStatus.equals("A")) {
					// user registration is successful so update mpi or id_part
					// in the NCID user table .Also needs to deactivate the old
					// user account.Also needs to retrieve

					logger.debug(
							"\n********** In  UserRegistrationController: part Status is 'A' to proceed with registration\n");

					String mpiUser = registerService.getMPIUsedUser(idPart.toString());

					if (mpiUser == null)

					{
						logger.debug(
								"\n********** In  UserRegistrationController: provided mpi/ssn details has no already registered ncId user.**********\n");

						loggedUser.setPartId(idPart.toString());

						userService.createOrUpdateUserInfo(loggedUser, AppConstants.OPERATION_UPDATE,
								AppConstants.PROFILE_UPDATE);// here it will
																// update
																// id_part /mpi
																// number to the
																// actw_usr_detail
																// table.

						userInfoBean.setMpi(idPart.toString());

						userInfoService.getUserInfo(userInfoBean);

						userInfoBean.setMpiTied(true);

						model.addAttribute("regSuccess", "Registration is successful");

						returnPage = AppConstants.ECOA_PARENT_WELCOME;

					}

					else {

						logger.debug(
								"\n********** In  UserRegistrationController: provided mpi/ssn details has  already registered ncId user.**********\n");

						request.setAttribute("errorMsg", AppConstants.err_323);
						returnPage = "forward:/cssp/user/userIdentifyInfo.htm";
					}

				}
				if (partStatus.equals("N")) {

					if (loggedUser != null) {

						attempts = (int) loggedUser.getNbAttempt();

						attempts = attempts + 1;

						registerService.updateNbAttempt(attempts, ncId);

						if (ssn.isEmpty()) {
							request.setAttribute("errorMsg", AppConstants.err_318);

						}

						else {
							request.setAttribute("errorMsg", AppConstants.err_319);

						}

						if (attempts >= WebsiteConfiguration.getMaxRegFailAttempts()) {

							isLocked = true;

							userInfoBean.setRegLock(true);

							model.addAttribute("regFail", AppConstants.err_305);

							returnPage = AppConstants.ECOA_USER_WELCOME;

						}

						else {
							returnPage = "forward:/cssp/user/userIdentifyInfo.htm";
						}

					}

				}

				if (partStatus.equals("I"))

				{

					request.setAttribute("errorMsg", AppConstants.err_320);

					returnPage = "forward:/cssp/user/userIdentifyInfo.htm";

				}

				if (partStatus.equals("U"))

				{

					if (ssn.isEmpty()) {
						request.setAttribute("errorMsg", AppConstants.err_321);

					}

					else {
						request.setAttribute("errorMsg", AppConstants.err_322);

					}

					returnPage = "forward:/cssp/user/userIdentifyInfo.htm";

				}

				if (!partStatus.equals("A")) {

				}

			}

		}

		catch (DataFormatException de)

		{
			logger.error(de.getMessage());

			model.addAttribute("errorMsg", de.getMessage());

			returnPage = AppConstants.ECOA_USER_IDENTIFYINFO;

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

		model.addAttribute("mpi", mpi);

		model.addAttribute("ssn", ssn);

		model.addAttribute("dob", dob);

		return returnPage;

	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/cssp/user/preUserIdentify.htm")
	public String getPreUserIdentifyInfo(HttpServletRequest request, Model model) throws Exception {
		logger.debug("\n********** In  UserRegistrationController: getPreUserIdentifyInfo **********\n");

		String errorMsg = "";

		errorMsg = (String) request.getAttribute("errorMsg");

		if (errorMsg != null && !errorMsg.isEmpty()) {
			model.addAttribute("errorMsg", errorMsg);
		}

		return AppConstants.ECOA_PRE_USER_IDENTIFY_INFO;

	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/cssp/user/preUserIdentifyProcess.htm")
	public String preUserIdentifyProcess(HttpServletRequest request, Model model) throws Exception {
		logger.debug("\n********** In  UserRegistrationController: getUserIdentifyInfo **********\n");

		String ncId = getUserNameFromAuthPrincipal();// login name of user.

		String userId = "";

		String pwd = "";

		String pwdQnAns = "";

		String errorMsg = "";

		String returnPage = "";

		boolean isFound = false;

		boolean isLocked = false;

		int attempts = 0;

		// required regex for ssn and mpi is "\\d{10}" and ^\d{9}$

		PreNcIdUser regOldUser = null;

		try {

			boolean dobExists = false;

			if (request.getParameter("userId") != null && !request.getParameter("userId").equals("")) {
				userId = request.getParameter("userId");

				if (!userId.matches("^[a-zA-Z0-9]+$")) {
					throw new DataFormatException("old useId  contains only characters and numbers.");
				}

				if (userId.length() < 5 || userId.length() > 10) {
					throw new DataFormatException("old userId should be between 5 and 10 characters");
				}

				if (request.getParameter("pwd") != null && !request.getParameter("pwd").equals("")) {
					pwd = request.getParameter("pwd");

					/*
					 * if (!pwd.matches("^[a-zA-Z0-9]+$")) { throw new
					 * DataFormatException("old password  contains only characters and numbers."
					 * ); }
					 */

					if (pwd.length() < 5 || pwd.length() > 10) {
						throw new DataFormatException("password should be between 5 and 10 characters");
					}

				} else {

					if (request.getParameter("pwdHint") != null && !request.getParameter("pwdHint").equals("")) {
						pwdQnAns = request.getParameter("pwdHint");
					}

				}

			}

			if (userId.isEmpty()) {
				throw new DataFormatException("userId should not be empty.");
			}

			if (pwd.isEmpty() && pwdQnAns.isEmpty()) {
				throw new DataFormatException("either password or password Hint  must be provided");
			}

			UserInformation userInfoBean = (UserInformation) request.getSession()
					.getAttribute(AppConstants.USERINFORMATION);

			regOldUser = (PreNcIdUser) request.getSession().getAttribute(AppConstants.registeredOldUser);

			if (regOldUser != null)

			{
				if (!pwd.isEmpty()) {
					if (regOldUser.getId_user().trim().equals(userId) && regOldUser.getId_pswd().trim().equals(pwd)) {

						isFound = true;

					}
				} else {

					if (regOldUser.getId_user().trim().equals(userId)
							&& regOldUser.getPswd_ans().trim().equalsIgnoreCase(pwdQnAns)) {

						isFound = true;
					}
				}

			}

			EcoaUser loggedUser = userService.getDBUser(ncId);

			if (isFound) {
				// user registration is successful so update mpi or id_part
				// in the NCID user table .Also needs to deactivate the old
				// user account.Also needs to retrieve
				String mpi = regOldUser.getId_part().trim();

				loggedUser.setPartId(mpi);

				// user registration is successful so update mpi or id_part
				// in the NCID user table .Also needs to deactivate the old
				// user account.Also needs to retrieve

				loggedUser.setPartId(mpi);

				String returnCode = userService.createOrUpdateUserInfo(loggedUser, AppConstants.OPERATION_UPDATE,
						AppConstants.PROFILE_UPDATE); // here it will update
														// id_part /mpi
														// number to the
														// actw_usr_detail
														// table.

				userInfoBean.setMpi(mpi);

				userInfoService.getUserInfo(userInfoBean);

				userInfoBean.setMpiTied(true);

				logger.info("\n************registration is successful  *************\n");

				userService.updateDeActivateOrGraceLogin(regOldUser.getId_user(), "DEACTIVATE");

				logger.info(
						"\n************PreNcId user account + regOldUser.getId_user()+  is deactivated,  *************\n");

				model.addAttribute("regSuccess", "Registration is successful");

				returnPage = AppConstants.ECOA_PARENT_WELCOME;

			} else {
				// since failed to provide correct details of preNcId user,
				// needs to increase NB_ATTEMPT in the ACTW_USR_DETAIL
				// table.
				// if attempts>=3 should redirect to userWelcome page with
				// message.

				if (loggedUser != null) {
					attempts = (int) loggedUser.getNbAttempt();

					attempts = attempts + 1;

					registerService.updateNbAttempt(attempts, ncId);

				}
				if (attempts >= WebsiteConfiguration.getMaxRegFailAttempts()) {
					isLocked = true;

					userInfoBean.setRegLock(true);

					model.addAttribute("regFail", AppConstants.err_305);

					return AppConstants.ECOA_USER_WELCOME;

				} else {

					model.addAttribute("errorMsg", AppConstants.err_304);
					returnPage = AppConstants.ECOA_PRE_USER_IDENTIFY_INFO;

				}

			}

		} catch (DataFormatException de)

		{
			logger.error(de.getMessage());

			model.addAttribute("errorMsg", de.getMessage());

			returnPage = AppConstants.ECOA_PRE_USER_IDENTIFY_INFO;

		}

		catch (Exception e) {
			logger.error(e.getMessage());

			ErrorDescriptor error = new ErrorDescriptor(this.getClass().getName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage(), e);

			request.setAttribute("errorBean", error);

			error = null;

			return "forward:/parentError.htm";

		}

		model.addAttribute("userId", userId);
		model.addAttribute("pwd", pwd);
		model.addAttribute("pwdQnAns", pwdQnAns);

		return returnPage;

	}

}
