package nc.dhhs.nccss.acts.ecoa.web.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import nc.dhhs.nccss.acts.ecoa.web.exception.ErrorDescriptor;
import nc.dhhs.nccss.acts.ecoa.beans.ThirdParty;
import nc.dhhs.nccss.acts.ecoa.web.service.SearchClerkOfCourtService;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * @author Mallika Velagapudi
 *
 */
@Controller
public class ClerkOfCourtSearchController extends BasicAnnotatedFormController
{
	@Autowired
	private SearchClerkOfCourtService searchClerkService;

	@RequestMapping("/clerkOfCourtSearch/{id}.htm")
	public String getClerkOfCourtSearch(HttpServletRequest request, Model model, @PathVariable("id") int id) throws Exception
	{
		logger.debug("\n********** In ClerkOfCourtSearchController: getClerkOfCourtSearch **********\n");

		model.addAttribute("fipsType", "CORT"); //id value can be either CORT or AGCY.
		//return AppConstants.ECOA_PARENT_COURT_CLERK_SEARCH;

		if (id == 1) { return AppConstants.ECOA_PARENT_CLERK_OF_COURT_SEARCH; }

		return AppConstants.ECOA_EMPLOYER_CLERK_OF_COURT_SEARCH;
	}

	@RequestMapping("/clerkOfCourtList/{id}.htm")
	public String getClerkOfCourtList(HttpServletRequest request, Model model, @PathVariable("id") int id) throws Exception
	{
		logger.debug("\n********** In ClerkOfCourtSearchController: getSearchOfCourtList **********\n");

		ArrayList<ThirdParty> cocList = new ArrayList<ThirdParty>();
		ArrayList<ThirdParty> dispCocList = new ArrayList<ThirdParty>();

		String returnPage = "";
		boolean next = false;
		// Counter used for paging records when we have more than 10
		int iPage = 0;

		String strCity = null;
		String strCounty = null;
		int searchType = 0;

		// Indicates which source page invoked this servlet.

		String recordType = null;

		if (request.getParameter("strCity") != null && !request.getParameter("strCity").equals(""))
		{
			strCity = request.getParameter("strCity");
		}
		if (request.getParameter("strCounty") != null && !request.getParameter("strCounty").equals(""))
		{
			strCounty = request.getParameter("strCounty");
		}

		if (request.getParameter("fipsType") != null && !request.getParameter("fipsType").equals(""))
		{
			recordType = request.getParameter("fipsType");
		}

		// If source page is the clerk of court's list page, then
		// get the page number also from the request object

		try
		{

			HttpSession session = request.getSession(true);

			if (session.getAttribute("cocList") != null)
			{
				session.removeAttribute("cocList");
			}
			strCity = ((strCity == null) ? null : strCity.trim().toUpperCase());

			strCounty = ((strCounty == null) ? null : strCounty.trim());

			if (strCity == null)
			{
				if (strCounty == null)
				{
					searchType = 0;
				}
				else if (strCounty.length() > 0)
				{
					searchType = 2;
				}
				else if (strCounty.length() == 0)
				{
					searchType = 0;
				}
			}
			else if (strCity.length() > 0)
			{
				if (strCounty == null)
				{
					searchType = 1;
				}
				else if (strCounty.length() > 0)
				{
					searchType = 3;
				}
				else if (strCounty.length() == 0)
				{
					searchType = 1;
				}
			}
			else if (strCity.length() == 0)
			{
				if (strCounty == null)
				{
					searchType = 0;
				}
				else if (strCounty.length() > 0)
				{
					searchType = 2;
				}
				else if (strCounty.length() == 0)
				{
					searchType = 0;
				}
			}
			else
			{
				searchType = 5;
			}

			if (searchType <= 3)
			{
				cocList = searchClerkService.getClerkOfCourtList(strCity, strCounty, recordType, searchType, iPage);

				if (cocList.size() > 0)
				{
					session.setAttribute("cocList", cocList); //list is stored in session to retrieve records for next or previous page 
				}
			}

			if (searchType == 5) // here cocList also null.
			{
				request.setAttribute("errorMsg", AppConstants.err_316);
				if (id == 1)
				{
					//returnPage = "forward:/clerkOfCourtSearch/" + recordType + ".htm";
					returnPage = "forward:/clerkOfCourtSearch/1.htm";
				}
				else if (id == 2)
				{
					//returnPage = "forward:/clerkOfCourtSearch/" + recordType + ".htm";
					returnPage = "forward:/clerkOfCourtSearch/2.htm";
				}
			}

			if (cocList != null && cocList.size() > 1)
			{
				dispCocList = searchClerkService.getClerkOfCourtPageList(cocList, 0);

				if (dispCocList.size() == 10)
				{
					int nextPageRec = (iPage * 10) + 11;

					if (cocList.size() >= nextPageRec)
					{
						next = true;
					}

				}

				model.addAttribute("dispCocList", dispCocList);

				model.addAttribute("fipsType", recordType);

				model.addAttribute("page", String.valueOf(iPage));

				model.addAttribute("nextExists", next);
				if (id == 1)
				{
					//returnPage = AppConstants.ECOA_PARENT_COURT_CLERK_LIST;
					returnPage = AppConstants.ECOA_PARENT_CLERK_OF_COURT_LIST;
				}
				else if (id == 2)
				{
					returnPage = AppConstants.ECOA_EMPLOYER_CLERK_OF_COURT_LIST;
				}

			}
			else if (cocList != null && cocList.size() == 1 && iPage == 0)
			{
				ThirdParty tp = (ThirdParty) cocList.get(0);
				request.setAttribute("id3pty", tp.getThirdPartyId());
				request.setAttribute("id3ptyType", recordType);

				if (id == 1)
				{
					returnPage = "forward:/clerkOfCourtDetail/1.htm";
				}

				else if (id == 2)
				{
					returnPage = "forward:/clerkOfCourtDetail/2.htm";
				}
			}

			else
			{
				request.setAttribute("errorMsg", AppConstants.err_317);

				if (id == 1)
				{
					//returnPage = "forward:/clerkOfCourtSearch/" + recordType + ".htm";
					returnPage = "forward:/clerkOfCourtSearch/1.htm";
				}
				else if (id == 2)
				{
					//returnPage = "forward:/clerkOfCourtSearch/" + recordType + ".htm";
					returnPage = "forward:/clerkOfCourtSearch/2.htm";
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

	@SuppressWarnings("unchecked")
	@RequestMapping("/clerkOfCourtDetail/{id}.htm")
	public String getClerkOfCourtDetail(HttpServletRequest request, Model model, @PathVariable("id") int id) throws Exception
	{
		logger.debug("\n********** In ClerkOfCourtSearchController:getClerkOfCourtDetail **********\n");

		ThirdParty tp = null;
		String id3pty = null;
		String thirdPtyType = null;
		ArrayList<ThirdParty> cocList = null;
		String returnPage = null;

		try
		{
			if (request.getParameter("id3pty") != null && !request.getParameter("id3pty").equals(""))
			{
				id3pty = request.getParameter("id3pty");
			}
			else
			{
				id3pty = (String) request.getAttribute("id3pty");
			}

			if (request.getParameter("id3ptyType") != null && !request.getParameter("id3ptyType").equals(""))
			{
				thirdPtyType = request.getParameter("id3ptyType").toUpperCase().trim();
			}
			else
			{
				thirdPtyType = (String) request.getAttribute("id3ptyType");
			}

			boolean search = false;

			cocList = (ArrayList<ThirdParty>) request.getSession().getAttribute("cocList");

			if (cocList != null)
			{
				for (ThirdParty party : cocList)
				{
					if (party.getThirdPartyId().equals(id3pty))
					{
						search = true;
						model.addAttribute("clerkOfCourt", party);
						break;
					}
				}
			}

			if (!search)
			{
				if (id3pty != null && (id3pty.length() > 0))
				{
					tp = searchClerkService.getClerkOfCourtDetail(id3pty, thirdPtyType);
					model.addAttribute("clerkOfCourt", tp);
				}
			}
			model.addAttribute("fipsType", thirdPtyType);
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());

			model.addAttribute("errMsg", "Problem encountered, please try again after some time.");
		}
		if (id == 1)
		{
			//returnPage = AppConstants.ECOA_PARENT_COURT_CLERK_DETAIL;
			returnPage = AppConstants.ECOA_PARENT_CLERK_OF_COURT_DETAIL;
		}
		else if (id == 2)
		{
			//returnPage = AppConstants.ECOA_EMPLOYER_COURT_CLERK_DETAIL;
			returnPage = AppConstants.ECOA_EMPLOYER_CLERK_OF_COURT_DETAIL;
		}

		return returnPage;
	}

	@RequestMapping(value = "/clerkOfCourtListIter/{id}.htm", method = RequestMethod.POST)
	public String getClerkOfCourtPageList(HttpServletRequest request, Model model, @PathVariable("id") int id) throws Exception
	{
		logger.debug("\n********** In ClerkOfCourtSearchController:getClerkOfCourtPageList path id= " + id + " **********\n");

		String thirdPtyType = null;
		ArrayList<ThirdParty> cocList = null;
		ArrayList<ThirdParty> dispCocList = null;
		String returnPage = null;

		boolean next = false;

		int iPage = 0;

		try
		{

			if (request.getParameter("page") != null && !request.getParameter("page").equals(""))
			{
				iPage = Integer.parseInt(request.getParameter("page"));
			}

			if (request.getParameter("fipsType") != null && !request.getParameter("fipsType").equals(""))
			{
				thirdPtyType = request.getParameter("fipsType");
			}

			if (request.getSession().getAttribute("cocList") != null)
			{
				cocList = (ArrayList<ThirdParty>) request.getSession().getAttribute("cocList");
			}

			if (cocList != null)
			{
				dispCocList = searchClerkService.getClerkOfCourtPageList(cocList, iPage);
			}

			if (dispCocList.size() == 10)
			{
				int nextPageRec = (iPage * 10) + 11; // finds whether record number exists to show next button on jsp.

				if (cocList.size() >= nextPageRec)
				{
					next = true;
				}
			}

			model.addAttribute("dispCocList", dispCocList);
			model.addAttribute("fipsType", thirdPtyType);
			model.addAttribute("nextExists", next);
			model.addAttribute("page", iPage);
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

		//returnPage = AppConstants.ECOA_PARENT_COURT_CLERK_LIST;

		//return AppConstants.ECOA_PARENT_COURT_CLERK_LIST;

		if (id == 1)
		{

			returnPage = AppConstants.ECOA_PARENT_CLERK_OF_COURT_LIST;
			//returnPage = "redirect:/clerkOfCourtList/1.htm";
		}
		else if (id == 2)
		{
			returnPage = AppConstants.ECOA_EMPLOYER_CLERK_OF_COURT_LIST;
			//returnPage = "redirect:/clerkOfCourtList/2.htm";

		}

		return returnPage;

	}
}
