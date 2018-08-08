package nc.dhhs.nccss.acts.ecoa.web.controller;

import java.io.BufferedReader;
import java.io.FileReader;
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

import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * @author Mallika Velagapudi
 *
 */
@Controller
public class WorkSheetBController extends BasicAnnotatedFormController
{

	@Autowired
	ServletContext servletContext;

	/**
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/workSheetBCalc.htm", method = RequestMethod.POST)
	public String getWorkSheetBInfo(HttpServletRequest request, Model model) throws Exception
	{

		logger.debug("\n********** IN WorkSheetBController: getWorkSheetBInfo **********\n");

		DecimalFormat df = new DecimalFormat("$###,###,##0.00");
		DecimalFormat pf = new DecimalFormat("###,###,##0.00");
		String returnPage = "";
		try
		{
			String filePath = servletContext.getRealPath("/WEB-INF");

			filePath = filePath + "/GuideLines.dat";

			double plaintiffGrossInc = Double.parseDouble(request.getParameter("input1"));

			double defendantGrossInc = Double.parseDouble(request.getParameter("input2"));

			double plaintiffPreCS = Double.parseDouble(request.getParameter("input3"));

			double defendantPreCS = Double.parseDouble(request.getParameter("input4"));

			int plaintiffOvernights = Integer.parseInt(request.getParameter("input7"));

			int defendantsOvernights = Integer.parseInt(request.getParameter("input8"));

			double plaintiffOvernights1 = Double.parseDouble(request.getParameter("input7"));

			double defendantsOvernights1 = Double.parseDouble(request.getParameter("input8"));

			double plaintiffChildCare = Double.parseDouble(request.getParameter("input9"));

			double defandantChildCare = Double.parseDouble(request.getParameter("input10"));

			double plaintiffHlthIns = Double.parseDouble(request.getParameter("input11"));

			double defendantHlthIns = Double.parseDouble(request.getParameter("input12"));

			double plaintiffExtraExp = Double.parseDouble(request.getParameter("input13"));

			double defendantExtraExp = Double.parseDouble(request.getParameter("input14"));

			int plaintiffNbrChildren = Integer.parseInt(request.getParameter("input15"));

			int plaintiffNbrOChildren = Integer.parseInt(request.getParameter("input17"));

			int defendantNbrOChildren = Integer.parseInt(request.getParameter("input18"));
			int defendantNbrChildren = 0;
			double plaintiffOthrInc = 0.00;
			double defendantOthrInc = 0.00;
			double gdlnAmount = 0.00;
			int totalChildren = 0;
			double plaintiffAdjGrossInc = 0.00;
			double defendantAdjGrossInc = 0.00;
			double combinedAdjGrossInc = 0.00;
			double plaintiffAdjShr = 0.00;
			double plaintiffAdjShrPct = 0.00;
			double defendantAdjShrPct = 0.00;
			double defendantAdjShr = 0.00;
			double basicObligation = 0.00;
			double sharedCustodyObligation = 0.00;
			double plaintiffShr = 0.00;
			double defendantShr = 0.00;
			int combinedOvernights = 0;
			double combinedOvernights1 = 0.00;
			double plaintiffOvernightShr = 0.00;
			double defendantOvernightShr = 0.00;
			double plaintiffOvernightShrPct = 0.00;
			double defendantOvernightShrPct = 0.00;
			double plaintiffOblgOthrParent = 0.00;
			double defendantOblgOthrParent = 0.00;
			double plaintiffTotalAdj = 0.00;
			double defendantTotalAdj = 0.00;
			double combinedTotalAdj = 0.00;
			double plaintiffFairShr = 0.00;
			double defendantFairShr = 0.00;
			double plaintiffAdjFairShr = 0.00;
			double defendantAdjFairShr = 0.00;
			double plaintiffAdjSupOblg = 0.00;
			double defendantAdjSupOblg = 0.00;
			double plaintiffRecmSup = 0.00;
			double defendantRecmSup = 0.00;
			double defendantAdjGrossInc6 = 0.00;
			double plaintiffTotalInc = 0.00;
			double defendantTotalInc = 0.00;
			double plaintiffRespOthrChildren = 0.00;
			double defendantRespOthrChildren = 0.00;
			double ChildCarePL = 1.00;
			double ChildCareDF = 1.00;
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
			double gdlnAmountLess = 0.00;
			double childGtr = 0.00;
			double gdlnAmountGtr = 0.00;
			totalChildren = plaintiffNbrChildren + defendantNbrChildren;

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
				gdlnAmountLess = 0.00;
				childGtr = 0.00;
				gdlnAmountGtr = 0.00;

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
							gdlnAmount = Double.parseDouble(token.nextToken());
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

					if (gdlnAmount == plaintiffTotalInc)
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
					else if (gdlnAmount < plaintiffTotalInc)
					{
						childL = 'Y';
						gdlnAmountLess = gdlnAmount;
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
					else if (gdlnAmount > plaintiffTotalInc)
					{
						childG = 'Y';
						gdlnAmountGtr = gdlnAmount;
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
							plaintiffRespOthrChildren = ((((plaintiffTotalInc - gdlnAmountLess) / (gdlnAmountGtr - gdlnAmountLess)) * (childGtr - childLess)) + childLess);
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
				gdlnAmountLess = 0.00;
				childGtr = 0.00;
				gdlnAmountGtr = 0.00;

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
							gdlnAmount = Double.parseDouble(token.nextToken());
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

					if (gdlnAmount == defendantTotalInc)
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
					else if (gdlnAmount < defendantTotalInc)
					{
						childL = 'Y';
						gdlnAmountLess = gdlnAmount;
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
					else if (gdlnAmount > defendantTotalInc)
					{
						childG = 'Y';
						gdlnAmountGtr = gdlnAmount;
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
							defendantRespOthrChildren = ((((defendantTotalInc - gdlnAmountLess) / (gdlnAmountGtr - gdlnAmountLess)) * (childGtr - childLess)) + childLess);
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

			plaintiffAdjGrossInc = plaintiffGrossInc - plaintiffPreCS - plaintiffRespOthrChildren;
			defendantAdjGrossInc = defendantGrossInc - defendantPreCS - defendantRespOthrChildren;
			combinedAdjGrossInc = plaintiffAdjGrossInc + defendantAdjGrossInc;

			if (combinedAdjGrossInc > 0.00)
			{
				plaintiffAdjShr = plaintiffAdjGrossInc / combinedAdjGrossInc;
				defendantAdjShr = defendantAdjGrossInc / combinedAdjGrossInc;
			}

			if (combinedAdjGrossInc < 0.00)
			{
				plaintiffAdjShr = plaintiffAdjGrossInc / combinedAdjGrossInc;
				defendantAdjShr = defendantAdjGrossInc / combinedAdjGrossInc;
			}
			/*
			 * if (defendantAdjShr >= 1.00) plaintiffAdjShr = 0.00;
			 */

			plaintiffAdjShrPct = plaintiffAdjShr * 100;
			defendantAdjShrPct = defendantAdjShr * 100;

			fin = new FileReader(filePath);
			din = new BufferedReader(fin);

			str = null;
			childE = 'N';
			childL = 'N';
			childG = 'N';
			// poverty = 'N';
			gdlnAmtChild1 = 0.00;
			gdlnAmtChild2 = 0.00;
			gdlnAmtChild3 = 0.00;
			gdlnAmtChild4 = 0.00;
			gdlnAmtChild5 = 0.00;
			gdlnAmtChild6 = 0.00;
			childEqual = 0.00;
			childLess = 0.00;
			gdlnAmountLess = 0.00;
			childGtr = 0.00;
			gdlnAmountGtr = 0.00;

			while ((str = din.readLine()) != null)
			{
				StringTokenizer token = new StringTokenizer(str, ":");
				while (token.hasMoreTokens())
				{
					try
					{
						gdlnAmount = Double.parseDouble(token.nextToken());
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
				if (gdlnAmount == combinedAdjGrossInc)
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
					din.close();
					break;
				}
				else if (gdlnAmount < combinedAdjGrossInc)
				{
					childL = 'Y';
					gdlnAmountLess = gdlnAmount;
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
				else if (gdlnAmount > combinedAdjGrossInc)
				{
					childG = 'Y';
					gdlnAmountGtr = gdlnAmount;
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

			if (combinedAdjGrossInc <= 800.00)
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
					basicObligation = ((((combinedAdjGrossInc) - (gdlnAmountLess)) / ((gdlnAmountGtr) - (gdlnAmountLess))) * ((childGtr) - (childLess))) + childLess;
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

			sharedCustodyObligation = basicObligation * 1.5;

			plaintiffShr = plaintiffAdjShr * sharedCustodyObligation;
			defendantShr = defendantAdjShr * sharedCustodyObligation;

			combinedOvernights = plaintiffOvernights + defendantsOvernights;
			combinedOvernights1 = plaintiffOvernights1 + defendantsOvernights1;

			plaintiffOvernightShr = plaintiffOvernights1 / combinedOvernights1;
			defendantOvernightShr = defendantsOvernights1 / combinedOvernights1;

			plaintiffOvernightShrPct = plaintiffOvernightShr * 100.00;
			defendantOvernightShrPct = defendantOvernightShr * 100.00;

			plaintiffOblgOthrParent = plaintiffShr * defendantOvernightShr;
			defendantOblgOthrParent = defendantShr * plaintiffOvernightShr;

			plaintiffTotalAdj = (plaintiffChildCare * ChildCarePL) + plaintiffHlthIns + plaintiffExtraExp;

			defendantTotalAdj = (defandantChildCare * ChildCareDF) + defendantHlthIns + defendantExtraExp;
			combinedTotalAdj = plaintiffTotalAdj + defendantTotalAdj;

			plaintiffFairShr = combinedTotalAdj * plaintiffAdjShr;
			defendantFairShr = combinedTotalAdj * defendantAdjShr;
			plaintiffAdjFairShr = plaintiffTotalAdj - plaintiffFairShr;

			if (plaintiffAdjFairShr <= 0.00) plaintiffAdjFairShr = 0.00;

			defendantAdjFairShr = defendantTotalAdj - defendantFairShr;

			if (defendantAdjFairShr <= 0.00) defendantAdjFairShr = 0.00;

			plaintiffAdjSupOblg = plaintiffOblgOthrParent - plaintiffAdjFairShr;
			defendantAdjSupOblg = defendantOblgOthrParent - defendantAdjFairShr;

			if (plaintiffAdjSupOblg > defendantAdjSupOblg) plaintiffRecmSup = plaintiffAdjSupOblg - defendantAdjSupOblg;

			if (defendantAdjSupOblg > plaintiffAdjSupOblg) defendantRecmSup = defendantAdjSupOblg - plaintiffAdjSupOblg;

			model.addAttribute("plaintiffGrossInc", df.format(plaintiffGrossInc));
			model.addAttribute("defendantGrossInc", df.format(defendantGrossInc));
			model.addAttribute("plaintiffPreCS", df.format(plaintiffPreCS));
			model.addAttribute("defendantPreCS", df.format(defendantPreCS));
			model.addAttribute("plaintiffRespOthrChildren", df.format(plaintiffRespOthrChildren));
			model.addAttribute("defendantRespOthrChildren", df.format(defendantRespOthrChildren));
			model.addAttribute("plaintiffAdjGrossInc", df.format(plaintiffAdjGrossInc));
			model.addAttribute("defendantAdjGrossInc", df.format(defendantAdjGrossInc));
			model.addAttribute("combinedAdjGrossInc", df.format(combinedAdjGrossInc));
			model.addAttribute("plaintiffAdjShrPct", pf.format(plaintiffAdjShrPct) + "%");
			model.addAttribute("defendantAdjShrPct", pf.format(defendantAdjShrPct) + "%");
			model.addAttribute("basicObligation", df.format(basicObligation));
			model.addAttribute("sharedCustodyObligation", df.format(sharedCustodyObligation));
			model.addAttribute("plaintiffShr", df.format(plaintiffShr));
			model.addAttribute("defendantShr", df.format(defendantShr));
			model.addAttribute("plaintiffGrossInc", df.format(plaintiffGrossInc));
			model.addAttribute("plaintiffGrossInc", df.format(plaintiffGrossInc));
			model.addAttribute("plaintiffGrossInc", df.format(plaintiffGrossInc));
			model.addAttribute("plaintiffGrossInc", df.format(plaintiffGrossInc));

			model.addAttribute("plaintiffOvernights", plaintiffOvernights);
			model.addAttribute("defendantsOvernights", defendantsOvernights);
			model.addAttribute("combinedOvernights", combinedOvernights);
			model.addAttribute("plaintiffOvernightShrPct", pf.format(plaintiffOvernightShrPct) + "%");
			model.addAttribute("defendantOvernightShrPct", pf.format(defendantOvernightShrPct) + "%");
			model.addAttribute("plaintiffOblgOthrParent", df.format(plaintiffOblgOthrParent));
			model.addAttribute("defendantOblgOthrParent", df.format(defendantOblgOthrParent));
			model.addAttribute("plaintiffChildCare", df.format(plaintiffChildCare));
			model.addAttribute("defandantChildCare", df.format(defandantChildCare));
			model.addAttribute("plaintiffHlthIns", df.format(plaintiffHlthIns));
			model.addAttribute("defendantHlthIns", df.format(defendantHlthIns));
			model.addAttribute("plaintiffExtraExp", df.format(plaintiffExtraExp));
			model.addAttribute("defendantExtraExp", df.format(defendantExtraExp));
			model.addAttribute("plaintiffTotalAdj", df.format(plaintiffTotalAdj));
			model.addAttribute("defendantTotalAdj", df.format(defendantTotalAdj));
			model.addAttribute("combinedTotalAdj", df.format(combinedTotalAdj));
			model.addAttribute("plaintiffFairShr", df.format(plaintiffFairShr));
			model.addAttribute("defendantFairShr", df.format(defendantFairShr));
			model.addAttribute("plaintiffAdjFairShr", df.format(plaintiffAdjFairShr));
			model.addAttribute("defendantAdjFairShr", df.format(defendantAdjFairShr));
			model.addAttribute("plaintiffAdjSupOblg", df.format(plaintiffAdjSupOblg));
			model.addAttribute("defendantAdjSupOblg", df.format(defendantAdjSupOblg));
			model.addAttribute("plaintiffRecmSup", df.format(plaintiffRecmSup));
			model.addAttribute("defendantRecmSup", df.format(defendantRecmSup));

			returnPage = AppConstants.ECOA_PARENTS_WORKSHEETB_CALC;

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
