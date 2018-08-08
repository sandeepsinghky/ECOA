/**
 * 
 */
package nc.dhhs.nccss.acts.ecoa.web.controller.parents;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import nc.dhhs.nccss.acts.ecoa.web.exception.ErrorDescriptor;
import nc.dhhs.nccss.acts.ecoa.beans.PreNcIdUser;
import nc.dhhs.nccss.acts.ecoa.beans.UserInformation;
import nc.dhhs.nccss.acts.ecoa.beans.UserProfile;
import nc.dhhs.nccss.acts.ecoa.web.controller.BasicAnnotatedFormController;
import nc.dhhs.nccss.acts.ecoa.web.security.EcoaUserDetails;
import nc.dhhs.nccss.acts.ecoa.web.service.UserService;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * @author Mallika Velagapudi
 *
 */
@Controller
public class UserProfileController extends BasicAnnotatedFormController {

	@Autowired
	private UserService userService;

	@RequestMapping("/cssp/user/userProfile.htm")
	public String getUserProfile(HttpServletRequest request, Model model) {
		logger.info("\n************ in ProfileController *************\n");

		String returnPage = "";

		try {

			UserProfile userProfile = null;

			String loginName = getUserNameFromAuthPrincipal();

			UserInformation userInfoBean = (UserInformation) request.getSession()
					.getAttribute(AppConstants.USERINFORMATION);

			if (userInfoBean.getUserType().trim().equals(AppConstants.USERTYPE_NCID)) {

				userProfile = userService.getEcoaUserProfile(loginName); // get
																			// user
																			// profile

				returnPage = AppConstants.ECOA_PARENTS_USER_PROFILE;

			} else {

				// PreNcIdUser preNcIdUser = getPreNcIdUserFromAuthPrincipal();

				EcoaUserDetails userDetails = (EcoaUserDetails) SecurityContextHolder.getContext().getAuthentication()
						.getPrincipal();

				userProfile = new UserProfile();

				PreNcIdUser preNcIdUser = userDetails.getPreNCIdUser();

				userProfile.setFirstName(preNcIdUser.getFirstName());

				userProfile.setLastName(preNcIdUser.getLastName());

				userProfile.setMidName(preNcIdUser.getMiddleName());

				userProfile.setAddrLine1(preNcIdUser.getAd_part_ln_1());

				userProfile.setAddrLine2(preNcIdUser.getAd_part_ln2());

				userProfile.setCity(preNcIdUser.getCity());

				userProfile.setState(preNcIdUser.getState());

				userProfile.setZipCode(preNcIdUser.getAd_part_zip_5());

				userProfile.setPhone(preNcIdUser.getNb_tel());

				userProfile.setEmail(preNcIdUser.getEmailId());

				returnPage = AppConstants.ECOA_PRE_USER_PROFILE;

			}

			model.addAttribute("userProfile", userProfile);

		}

		catch (Exception e)

		{
			
			ErrorDescriptor error = new ErrorDescriptor(this.getClass().getName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage(), e);

			request.setAttribute("errorBean", error);

			error = null;

			return "forward:/parentError.htm";

		}

		return returnPage;
	}

	@RequestMapping("/cssp/user/updateProfile.htm")
	public String updateUserProfile(HttpServletRequest request, Model model,
			@ModelAttribute("userProfile") UserProfile userProfile) {

		logger.info("\n************ in ProfileController : updateUserProfile*************\n");

		try {

			String loginName = getUserNameFromAuthPrincipal();

			userService.updateUserProfile(userProfile, loginName);

			request.getSession().setAttribute("userEmail", userProfile.getEmail());

			UserInformation userInfoBean = (UserInformation) request.getSession()
					.getAttribute(AppConstants.USERINFORMATION);

			userInfoBean.setEmailId(userProfile.getEmail());

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

		model.addAttribute("success", "profile is updated successfully");

		return "redirect:/cssp/user/userProfile.htm";
	}

}
