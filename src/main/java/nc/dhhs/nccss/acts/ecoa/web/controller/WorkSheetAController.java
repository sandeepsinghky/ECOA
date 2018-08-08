package nc.dhhs.nccss.acts.ecoa.web.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import nc.dhhs.nccss.acts.ecoa.web.exception.ErrorDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import nc.dhhs.nccss.acts.ecoa.web.service.CaseApplicationService;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * @author Mallika Velagapudi
 *
 */
@Controller
public class WorkSheetAController extends BasicAnnotatedFormController
{

	@Autowired
	protected CaseApplicationService	caseApplService;

	@Autowired
	ServletContext						servletContext;

	/**
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/worksheetACalc.htm", method = RequestMethod.POST)
	public String workSheetAcalculation(HttpServletRequest request, Model model) throws Exception
	{

		logger.debug("\n********** IN WorkSheetA controller: workSheetAcalculationt**********\n");
		String returnPage = "";
		try
		{

			String filePath = servletContext.getRealPath("/WEB-INF");

			filePath = filePath + "/GuideLines.dat";

			DecimalFormat df = new DecimalFormat("$###,###,##0.00");
			DecimalFormat pf = new DecimalFormat("###,###,##0.00");

			double plaintiffGrossInc = Double.parseDouble(request.getParameter("input1"));

			double defendantGrossInc = Double.parseDouble(request.getParameter("input2"));

			double plaintiffPreSup = Double.parseDouble(request.getParameter("input3"));

			double defendantPreSup = Double.parseDouble(request.getParameter("input4"));

			double plaintiffCCare = Double.parseDouble(request.getParameter("input7"));

			double defendantCCare = Double.parseDouble(request.getParameter("input8"));

			double plaintiffHlthCosts = Double.parseDouble(request.getParameter("input9"));

			double defendantHlthCosts = Double.parseDouble(request.getParameter("input10"));

			double plaintiffExtExp = Double.parseDouble(request.getParameter("input11"));

			double defendantExtExp = Double.parseDouble(request.getParameter("input12"));

			int plaintiffNbrChildren = Integer.parseInt(request.getParameter("input13"));

			int plaintiffNbrOChildren = Integer.parseInt(request.getParameter("input15"));

			int defendantNbrOChildren = Integer.parseInt(request.getParameter("input16"));
			double plaintiffOthrInc = 0.00;
			double defendantOthrInc = 0.00;
			int plaintiffGrossInc4 = 0;

			double plaintiffAdjGrossInc = 0.00;
			double defendantAdjGrossInc = 0.00;
			double combinedAdjGrossInc = 0.00;
			double plaintiffShr = 0.00;
			double defendantShr = 0.00;
			double plaintiffPctShr = 0.00;
			double defendantPctShr = 0.00;
			double basicObligation = 50.00;
			double plaintiffFullExpAdj = 0.00;
			double defendantFullExpAdj = 0.00;
			double combinedFullExpAdj = 0.00;
			double totalObligation = 0.00;
			double plaintiffObligation = 0.00;
			double defendantObligation = 0.00;
			double plaintiffNonCustAdj = 0.00;
			double defendantNonCustAdj = 0.00;
			double plaintiffRecmSup = 0.00;
			double defendantRecmSup = 0.00;
			int defendantRecmSup1 = 0;
			double plaintiffTotalInc = 0.00;
			double defendantTotalInc = 0.00;
			double plaintiffRespOthrChildren = 0.00;
			double defendantRespOthrChildren = 0.00;
			double ChildCarePL = 1.00;
			double ChildCareDF = 1.00;

			double gdlnIncome = 0.00;
			int totalChildren = 0;

			FileReader fin = null;
			BufferedReader din = null;

			String str = null;
			char childE = 'N';
			char childL = 'N';
			char childG = 'N';
			char poverty = 'N';
			double gdlnAmtChild1 = 0.00;
			double gdlnAmtChild2 = 0.00;
			double gdlnAmtChild3 = 0.00;
			double gdlnAmtChild4 = 0.00;
			double gdlnAmtChild5 = 0.00;
			double gdlnAmtChild6 = 0.00;
			double childEqual = 0.00;
			double childLess = 0.00;
			double gdlnIncomeLess = 0.00;
			double childGtr = 0.00;
			double gdlnIncomeGtr = 0.00;

			totalChildren = plaintiffNbrChildren + plaintiffGrossInc4;

			if (plaintiffNbrOChildren > 0)
			{
				str = null;
				childE = 'N';
				childL = 'N';
				childG = 'N';
				poverty = 'N';
				gdlnAmtChild1 = 0.00;
				gdlnAmtChild2 = 0.00;
				gdlnAmtChild3 = 0.00;
				gdlnAmtChild4 = 0.00;
				gdlnAmtChild5 = 0.00;
				gdlnAmtChild6 = 0.00;
				childEqual = 0.00;
				childLess = 0.00;
				gdlnIncomeLess = 0.00;
				childGtr = 0.00;
				gdlnIncomeGtr = 0.00;
				// plaintiffTotalInc = plaintiffGrossInc + plaintiffOthrInc;
				plaintiffTotalInc = plaintiffGrossInc;
				fin = new FileReader(filePath);
				din = new BufferedReader(fin);

				while ((str = din.readLine()) != null)
				{
					StringTokenizer token = new StringTokenizer(str, ":");
					while (token.hasMoreTokens())
					{
						try
						{
							gdlnIncome = Double.parseDouble(token.nextToken());
							gdlnAmtChild1 = Double.parseDouble(token.nextToken());
							gdlnAmtChild2 = Double.parseDouble(token.nextToken());
							gdlnAmtChild3 = Double.parseDouble(token.nextToken());
							gdlnAmtChild4 = Double.parseDouble(token.nextToken());
							gdlnAmtChild5 = Double.parseDouble(token.nextToken());
							gdlnAmtChild6 = Double.parseDouble(token.nextToken());
						}
						catch (NumberFormatException e)
						{
							logger.error(e.getMessage());
						}
					}
					if (gdlnIncome == plaintiffTotalInc)
					{
						childE = 'Y';
						switch (plaintiffNbrOChildren)
						{
						case 1:
							childEqual = gdlnAmtChild1;
							break;
						case 2:
							childEqual = gdlnAmtChild2;
							break;
						case 3:
							childEqual = gdlnAmtChild3;
							break;
						case 4:
							childEqual = gdlnAmtChild4;
							break;
						case 5:
							childEqual = gdlnAmtChild5;
							break;
						case 6:
							childEqual = gdlnAmtChild6;
							break;
						}
						din.close();
						break;
					}
					else if (gdlnIncome < plaintiffTotalInc)
					{
						childL = 'Y';
						gdlnIncomeLess = gdlnIncome;
						switch (plaintiffNbrOChildren)
						{
						case 1:
							childLess = gdlnAmtChild1;
							break;
						case 2:
							childLess = gdlnAmtChild2;
							break;
						case 3:
							childLess = gdlnAmtChild3;
							break;
						case 4:
							childLess = gdlnAmtChild4;
							break;
						case 5:
							childLess = gdlnAmtChild5;
							break;
						case 6:
							childLess = gdlnAmtChild6;
							break;
						}
					}
					else if (gdlnIncome > plaintiffTotalInc)
					{
						childG = 'Y';
						gdlnIncomeGtr = gdlnIncome;
						switch (plaintiffNbrOChildren)
						{
						case 1:
							childGtr = gdlnAmtChild1;
							break;
						case 2:
							childGtr = gdlnAmtChild2;
							break;
						case 3:
							childGtr = gdlnAmtChild3;
							break;
						case 4:
							childGtr = gdlnAmtChild4;
							break;
						case 5:
							childGtr = gdlnAmtChild5;
							break;
						case 6:
							childGtr = gdlnAmtChild6;
							break;
						}
						din.close();
						break;
					}
				}
				// Maximum income eligible for $50 minimum order was increased from
				// $950 to $1050.
				if (plaintiffTotalInc == 0.00)
				{
					plaintiffRespOthrChildren = 0.00;
				}
				else
				{
					if (plaintiffTotalInc <= 1050.00)
					{
						plaintiffRespOthrChildren = 50.00;
					}
					else
					{
						if (childE == 'Y')
						{
							plaintiffRespOthrChildren = childEqual;
						}
						else
						{
							plaintiffRespOthrChildren = ((((plaintiffTotalInc - gdlnIncomeLess) / (gdlnIncomeGtr - gdlnIncomeLess)) * (childGtr - childLess)) + childLess);
						}
					}
				}

				/**
				 * Acts Mod# 2783 - If Other Parent income is zero, use full
				 * amount of obligation as adjustment. If other parent is in
				 * home, use one-half the obligation
				 */
				if (plaintiffOthrInc > 0.00)
				{
					plaintiffRespOthrChildren = plaintiffRespOthrChildren / 2;
				}
			}

			if (defendantNbrOChildren > 0)
			{
				str = null;
				childE = 'N';
				childL = 'N';
				childG = 'N';
				poverty = 'N';
				gdlnAmtChild1 = 0.00;
				gdlnAmtChild2 = 0.00;
				gdlnAmtChild3 = 0.00;
				gdlnAmtChild4 = 0.00;
				gdlnAmtChild5 = 0.00;
				gdlnAmtChild6 = 0.00;
				childEqual = 0.00;
				childLess = 0.00;
				gdlnIncomeLess = 0.00;
				childGtr = 0.00;
				gdlnIncomeGtr = 0.00;
				// defendantTotalInc = defendantGrossInc + defendantOthrInc;
				defendantTotalInc = defendantGrossInc;
				fin = new FileReader(filePath);
				din = new BufferedReader(fin);

				while ((str = din.readLine()) != null)
				{
					StringTokenizer token = new StringTokenizer(str, ":");
					while (token.hasMoreTokens())
					{
						try
						{
							gdlnIncome = Double.parseDouble(token.nextToken());
							gdlnAmtChild1 = Double.parseDouble(token.nextToken());
							gdlnAmtChild2 = Double.parseDouble(token.nextToken());
							gdlnAmtChild3 = Double.parseDouble(token.nextToken());
							gdlnAmtChild4 = Double.parseDouble(token.nextToken());
							gdlnAmtChild5 = Double.parseDouble(token.nextToken());
							gdlnAmtChild6 = Double.parseDouble(token.nextToken());
						}
						catch (NumberFormatException e)
						{
							logger.error(e.getMessage());
						}
					}
					if (gdlnIncome == defendantTotalInc)
					{
						childE = 'Y';
						switch (defendantNbrOChildren)
						{
						case 1:
							childEqual = gdlnAmtChild1;
							break;
						case 2:
							childEqual = gdlnAmtChild2;
							break;
						case 3:
							childEqual = gdlnAmtChild3;
							break;
						case 4:
							childEqual = gdlnAmtChild4;
							break;
						case 5:
							childEqual = gdlnAmtChild5;
							break;
						case 6:
							childEqual = gdlnAmtChild6;
							break;
						}
						din.close();
						break;
					}
					else if (gdlnIncome < defendantTotalInc)
					{
						childL = 'Y';
						gdlnIncomeLess = gdlnIncome;
						switch (defendantNbrOChildren)
						{
						case 1:
							childLess = gdlnAmtChild1;
							break;
						case 2:
							childLess = gdlnAmtChild2;
							break;
						case 3:
							childLess = gdlnAmtChild3;
							break;
						case 4:
							childLess = gdlnAmtChild4;
							break;
						case 5:
							childLess = gdlnAmtChild5;
							break;
						case 6:
							childLess = gdlnAmtChild6;
							break;
						}
					}
					else if (gdlnIncome > defendantTotalInc)
					{
						childG = 'Y';
						gdlnIncomeGtr = gdlnIncome;
						switch (defendantNbrOChildren)
						{
						case 1:
							childGtr = gdlnAmtChild1;
							break;
						case 2:
							childGtr = gdlnAmtChild2;
							break;
						case 3:
							childGtr = gdlnAmtChild3;
							break;
						case 4:
							childGtr = gdlnAmtChild4;
							break;
						case 5:
							childGtr = gdlnAmtChild5;
							break;
						case 6:
							childGtr = gdlnAmtChild6;
							break;
						}
						din.close();
						break;
					}
				}
				// Maximum income eligible for $50 minimum order was increased from
				// $950 to $1050.
				if (defendantTotalInc == 0.00)
				{
					defendantRespOthrChildren = 0.00;
				}
				else
				{
					if (defendantTotalInc <= 1050.00)
					{
						defendantRespOthrChildren = 50.00;
					}
					else
					{
						if (childE == 'Y')
						{
							defendantRespOthrChildren = childEqual;
						}
						else
						{
							defendantRespOthrChildren = ((((defendantTotalInc - gdlnIncomeLess) / (gdlnIncomeGtr - gdlnIncomeLess)) * (childGtr - childLess)) + childLess);
						}
					}
				}

				/**
				 * Acts Mod# 2783 - If Other Parent income is zero, use full
				 * amount of obligation as adjustment. If other parent is in
				 * home, use one-half the obligation
				 */
				if (defendantOthrInc > 0.00)
				{
					defendantRespOthrChildren = defendantRespOthrChildren / 2;
				}

			}

			plaintiffAdjGrossInc = plaintiffGrossInc - plaintiffPreSup - plaintiffRespOthrChildren;
			defendantAdjGrossInc = defendantGrossInc - defendantPreSup - defendantRespOthrChildren;
			combinedAdjGrossInc = plaintiffAdjGrossInc + defendantAdjGrossInc;

			str = null;
			childE = 'N';
			childL = 'N';
			childG = 'N';
			poverty = 'N';
			gdlnAmtChild1 = 0.00;
			gdlnAmtChild2 = 0.00;
			gdlnAmtChild3 = 0.00;
			gdlnAmtChild4 = 0.00;
			gdlnAmtChild5 = 0.00;
			gdlnAmtChild6 = 0.00;
			childEqual = 0.00;
			childLess = 0.00;
			gdlnIncomeLess = 0.00;
			childGtr = 0.00;

			// The self-sufficiency reserve incorporated into the shaded area
			// of the schedule has been applied.
			switch (totalChildren)
			{
			case 1:
				if (defendantAdjGrossInc <= 1500.00)
				{
					combinedAdjGrossInc = defendantAdjGrossInc;
					poverty = 'Y';
				}
				break;
			case 2:
				if (defendantAdjGrossInc <= 1850.00)
				{
					combinedAdjGrossInc = defendantAdjGrossInc;
					poverty = 'Y';
				}
				break;
			case 3:
				if (defendantAdjGrossInc <= 2150.00)
				{
					combinedAdjGrossInc = defendantAdjGrossInc;
					poverty = 'Y';
				}
				break;
			case 4:
				if (defendantAdjGrossInc <= 2400.00)
				{
					combinedAdjGrossInc = defendantAdjGrossInc;
					poverty = 'Y';
				}
				break;
			case 5:
				if (defendantAdjGrossInc <= 2700.00)
				{
					combinedAdjGrossInc = defendantAdjGrossInc;
					poverty = 'Y';
				}
				break;
			case 6:
				if (defendantAdjGrossInc <= 3000.00)
				{
					combinedAdjGrossInc = defendantAdjGrossInc;
					poverty = 'Y';
				}
				break;
			}

			if (combinedAdjGrossInc > 0.00)
			{
				plaintiffShr = (plaintiffAdjGrossInc / combinedAdjGrossInc);
				defendantShr = (defendantAdjGrossInc / combinedAdjGrossInc);
			}
			if (combinedAdjGrossInc < 0.00)
			{
				plaintiffShr = (plaintiffAdjGrossInc / combinedAdjGrossInc);
				defendantShr = (defendantAdjGrossInc / combinedAdjGrossInc);
			}
			if (defendantShr >= 1.00) plaintiffShr = 0.00;

			plaintiffPctShr = plaintiffShr * 100;
			defendantPctShr = defendantShr * 100;

			fin = new FileReader(filePath);
			din = new BufferedReader(fin);

			while ((str = din.readLine()) != null)
			{
				StringTokenizer token = new StringTokenizer(str, ":");
				while (token.hasMoreTokens())
				{
					try
					{
						gdlnIncome = Double.parseDouble(token.nextToken());
						gdlnAmtChild1 = Double.parseDouble(token.nextToken());
						gdlnAmtChild2 = Double.parseDouble(token.nextToken());
						gdlnAmtChild3 = Double.parseDouble(token.nextToken());
						gdlnAmtChild4 = Double.parseDouble(token.nextToken());
						gdlnAmtChild5 = Double.parseDouble(token.nextToken());
						gdlnAmtChild6 = Double.parseDouble(token.nextToken());
					}
					catch (NumberFormatException e)
					{
						logger.error(e.getMessage());
					}
				}
				if (gdlnIncome == combinedAdjGrossInc)
				{
					childE = 'Y';
					switch (totalChildren)
					{
					case 1:
						childEqual = gdlnAmtChild1;
						break;
					case 2:
						childEqual = gdlnAmtChild2;
						break;
					case 3:
						childEqual = gdlnAmtChild3;
						break;
					case 4:
						childEqual = gdlnAmtChild4;
						break;
					case 5:
						childEqual = gdlnAmtChild5;
						break;
					case 6:
						childEqual = gdlnAmtChild6;
						break;
					}
					try
					{
						din.close();
					}
					catch (IOException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
				else if (gdlnIncome < combinedAdjGrossInc)
				{
					childL = 'Y';
					gdlnIncomeLess = gdlnIncome;
					switch (totalChildren)
					{
					case 1:
						childLess = gdlnAmtChild1;
						break;
					case 2:
						childLess = gdlnAmtChild2;
						break;
					case 3:
						childLess = gdlnAmtChild3;
						break;
					case 4:
						childLess = gdlnAmtChild4;
						break;
					case 5:
						childLess = gdlnAmtChild5;
						break;
					case 6:
						childLess = gdlnAmtChild6;
						break;
					}
				}
				else if (gdlnIncome > combinedAdjGrossInc)
				{
					childG = 'Y';
					gdlnIncomeGtr = gdlnIncome;
					switch (totalChildren)
					{
					case 1:
						childGtr = gdlnAmtChild1;
						break;
					case 2:
						childGtr = gdlnAmtChild2;
						break;
					case 3:
						childGtr = gdlnAmtChild3;
						break;
					case 4:
						childGtr = gdlnAmtChild4;
						break;
					case 5:
						childGtr = gdlnAmtChild5;
						break;
					case 6:
						childGtr = gdlnAmtChild6;
						break;
					}
					din.close();
					break;
				}
			}
			// Maximum income eligible for $50 minimum order was increased from $950
			// to $1050.
			if (combinedAdjGrossInc <= 1150.00)
			{
				basicObligation = 50.00;
			}
			else
			{
				if (childE == 'Y')
				{
					basicObligation = childEqual;
				}
				else
				{
					basicObligation = (((combinedAdjGrossInc - gdlnIncomeLess) / (gdlnIncomeGtr - gdlnIncomeLess)) * (childGtr - childLess)) + childLess;
				}
			}

			// BST 10/29/2010 Income at which 100% of child care costs are
			// calculated revised
			// $1100 > 1850, 1500 > 2500, 1700 > 2800, 1900 > 3100, 2100 > 3400,
			// 2300 > 3700
			switch (totalChildren)
			{
			case 1:
				if (plaintiffGrossInc < 1850.00) ChildCarePL = 1.00;
				break;
			case 2:
				if (plaintiffGrossInc < 2500.00) ChildCarePL = 1.00;
				break;
			case 3:
				if (plaintiffGrossInc < 2800.00) ChildCarePL = 1.00;
				break;
			case 4:
				if (plaintiffGrossInc < 3100.00) ChildCarePL = 1.00;
				break;
			case 5:
				if (plaintiffGrossInc < 3400.00) ChildCarePL = 1.00;
				break;
			case 6:
				if (plaintiffGrossInc < 3700.00) ChildCarePL = 1.00;
				break;
			}

			switch (totalChildren)
			{
			case 1:
				if (defendantGrossInc < 1850.00) ChildCareDF = 1.00;
				break;
			case 2:
				if (defendantGrossInc < 2500.00) ChildCareDF = 1.00;
				break;
			case 3:
				if (defendantGrossInc < 2800.00) ChildCareDF = 1.00;
				break;
			case 4:
				if (defendantGrossInc < 3100.00) ChildCareDF = 1.00;
				break;
			case 5:
				if (defendantGrossInc < 3400.00) ChildCareDF = 1.00;
				break;
			case 6:
				if (defendantGrossInc < 3700.00) ChildCareDF = 1.00;
				break;
			}

			if (poverty == 'Y')
			{
				plaintiffFullExpAdj = 0.00;
			}
			else
			{
				plaintiffFullExpAdj = (plaintiffCCare * ChildCarePL) + plaintiffHlthCosts + plaintiffExtExp;
			}

			defendantFullExpAdj = (defendantCCare * ChildCareDF) + defendantHlthCosts + defendantExtExp;
			combinedFullExpAdj = plaintiffFullExpAdj + defendantFullExpAdj;
			totalObligation = basicObligation + combinedFullExpAdj;
			plaintiffObligation = plaintiffShr * totalObligation;
			defendantObligation = defendantShr * totalObligation;

			plaintiffNonCustAdj = 0.00;
			defendantNonCustAdj = defendantFullExpAdj;

			plaintiffRecmSup = plaintiffObligation - plaintiffNonCustAdj;
			defendantRecmSup = defendantObligation - defendantNonCustAdj;

			model.addAttribute("plaintiffGrossInc", df.format(plaintiffGrossInc));
			model.addAttribute("defendantGrossInc", df.format(defendantGrossInc));
			model.addAttribute("plaintiffPreSup", df.format(plaintiffPreSup));
			model.addAttribute("defendantPreSup", df.format(defendantPreSup));
			model.addAttribute("plaintiffRespOthrChildren", df.format(plaintiffRespOthrChildren));
			model.addAttribute("defendantRespOthrChildren", df.format(defendantRespOthrChildren));
			model.addAttribute("defendantAdjGrossInc", df.format(defendantAdjGrossInc));
			model.addAttribute("combinedAdjGrossInc", df.format(combinedAdjGrossInc));
			model.addAttribute("plaintiffPctShr", pf.format(plaintiffPctShr) + "%");
			model.addAttribute("defendantPctShr", pf.format(defendantPctShr) + "%");
			model.addAttribute("basicObligation", df.format(basicObligation));
			model.addAttribute("totalObligation", df.format(totalObligation));
			model.addAttribute("plaintiffCCare", df.format(plaintiffCCare));
			model.addAttribute("defendantCCare", df.format(defendantCCare));
			model.addAttribute("plaintiffHlthCosts", df.format(plaintiffHlthCosts));
			model.addAttribute("defendantHlthCosts", df.format(defendantHlthCosts));
			model.addAttribute("plaintiffExtExp", df.format(plaintiffExtExp));
			model.addAttribute("defendantExtExp", df.format(defendantExtExp));
			model.addAttribute("plaintiffFullExpAdj", df.format(plaintiffFullExpAdj));
			model.addAttribute("defendantFullExpAdj", df.format(defendantFullExpAdj));
			model.addAttribute("combinedFullExpAdj", df.format(combinedFullExpAdj));
			model.addAttribute("plaintiffObligation", df.format(plaintiffObligation));
			model.addAttribute("defendantObligation", df.format(defendantObligation));
			model.addAttribute("plaintiffNonCustAdj", df.format(plaintiffNonCustAdj));
			model.addAttribute("defendantNonCustAdj", df.format(defendantNonCustAdj));
			model.addAttribute("defendantRecmSup", df.format(defendantRecmSup));

			returnPage = AppConstants.ECOA_PARENT_WORKSHEETA_CALC;

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
