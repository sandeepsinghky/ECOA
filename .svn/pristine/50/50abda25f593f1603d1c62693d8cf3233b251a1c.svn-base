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
public class WorkSheetCController extends BasicAnnotatedFormController
{

	@Autowired
	ServletContext servletContext;

	@RequestMapping(value = "/workSheetCCalc.htm", method = RequestMethod.POST)
	public String getWorkSheetCCalc(HttpServletRequest request, Model model) throws Exception
	{

		logger.debug("\n********** IN WorkSheetCController: getWorkSheetCCalc **********\n");

		DecimalFormat df = new DecimalFormat("$###,###,##0.00");
		DecimalFormat pf = new DecimalFormat("###,###,##0.00");
		String returnPage = "";
		try
		{
			String filePath = servletContext.getRealPath("/WEB-INF");

			filePath = filePath + "/GuideLines.dat";

			double plaintiffOthrParentInc = 0.00;
			double defendantOthrParentInc = 0.00;

			double valueT1 = 0.00;
			double valueT2 = 0.00;
			double gdlnIncome = 0.00;
			int totalChildren = 0;

			double plaintiffAdjGross = 0.00;
			double defendantAdjGross = 0.00;
			double combinedAdjGross = 0.00;
			double plaintiffShareInc = 0.00;
			double defendantShareInc = 0.00;
			double plaintiffSharePct = 0.00;
			double defendantSharePct = 0.00;
			double basicObligation = 0.00;
			int iCombinedNbrChildren = 0;
			double dPlaintiffNbrChildren = 0.00;
			double plaintiffSpCustody = 0.00;
			double defendantSpCustody = 0.00;
			double plaintiffSpCustodyPct = 0.00;
			double defendantSpCustodyPct = 0.00;
			double plaintiffSpCustAdj = 0.00;
			double defendantSpCustAdj = 0.00;
			double plaintiffSPOthrCh = 0.00;
			double defendantSPOthrCh = 0.00;
			double plaintiffTotalAdj = 0.00;
			double defendantTotalAdj = 0.00;
			double combinedTotalAdj = 0.00;
			double plaintiffFairAdj = 0.00;
			double defendantFairAdj = 0.00;
			double plaintiffAdjOverShr = 0.00;
			double defendantAdjOverShr = 0.00;
			double plaintiffAdjSup = 0.00;
			double defendantAdjSup = 0.00;
			double plaintiffRecmndSup = 0.00;
			double defendantRecmndSup = 0.00;
			double combIncome = 0.00;
			// double defendantAdjGross6 = 0.00;
			double plaintiffRespOthrChildren = 0.00;
			double defendantRespOthrChildren = 0.00;
			double resultN1 = 0.00;
			double resultN2 = 0.00;

			double ChildCarePL = 1.00;

			double ChildCareDF = 1.00;

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
			double childGtr = 0.00;
			double gdlnIncomeGtr = 0.00;
			double gdlnIncomeLess = 0.00;
			FileReader fin = null;
			BufferedReader din = null;

			/**
			 * Process all the input form fields
			 */

			double plaintiffMnthGr = Double.parseDouble(request.getParameter("input1"));

			double defendantMnthGr = Double.parseDouble(request.getParameter("input2"));

			double plaintiffPreChildSupport = Double.parseDouble(request.getParameter("input3"));

			double defendantPreChildSupport = Double.parseDouble(request.getParameter("input4"));

			/*
			 * try { plaintiffOthrParentInc =
			 * Double.parseDouble(request.getParameter("input5")); } catch
			 * (java.lang.NumberFormatException e) {
			 * out.println("ParseException"); } try { defendantOthrParentInc =
			 * Double.parseDouble(request.getParameter("input6")); } catch
			 * (java.lang.NumberFormatException e) {
			 * out.println("ParseException"); }
			 */

			int plaintiffNbrChildren = Integer.parseInt(request.getParameter("input7"));

			double plaintiffNbrChildren1 = Double.parseDouble(request.getParameter("input7"));

			int defendantNbrChildren = Integer.parseInt(request.getParameter("input8"));

			double defendantNbrChildren1 = Double.parseDouble(request.getParameter("input8"));

			double plaintiffChildCare = Double.parseDouble(request.getParameter("input9"));

			double defendantChildCare = Double.parseDouble(request.getParameter("input10"));

			double plaintiffHealthCosts = Double.parseDouble(request.getParameter("input11"));

			double defendantHealthCosts = Double.parseDouble(request.getParameter("input12"));

			double plaintiffExtraExp = Double.parseDouble(request.getParameter("input13"));

			double defendantExtraExp = Double.parseDouble(request.getParameter("input14"));

			int plaintiffNbrOChildren = Integer.parseInt(request.getParameter("input15"));

			int defendantNbrOChildren = Integer.parseInt(request.getParameter("input16"));

			// Compute total number of children
			totalChildren = plaintiffNbrChildren + defendantNbrChildren;

			/**
			 * Compute Plaintiff & Defendant's responsibility for other children
			 */

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
				childGtr = 0.00;
				gdlnIncomeGtr = 0.00;
				gdlnIncomeLess = 0.00;

				// combIncome = plaintiffMnthGr + plaintiffOthrParentInc;
				combIncome = plaintiffMnthGr;
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

					if (gdlnIncome == combIncome)
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
					else if (gdlnIncome < combIncome)
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
						// din.close();
						// break;
					}
					else if (gdlnIncome > combIncome)
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

				if (combIncome == 0.00)
				{
					plaintiffRespOthrChildren = 0.00;
				}
				else
				{
					if (combIncome <= 1050.00)
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
							plaintiffRespOthrChildren = ((((combIncome - gdlnIncomeLess) / (gdlnIncomeGtr - gdlnIncomeLess)) * (childGtr - childLess)) + childLess);
						}
					}
				}
				/**
				 * Acts Mod# 2783 - If Other Parent income is zero, use full
				 * amount of obligation as adjustment. If other parent is in
				 * home, use one-half the obligation
				 */
				if (plaintiffOthrParentInc > 0.00)
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
				childGtr = 0.00;
				gdlnIncomeGtr = 0.00;
				gdlnIncomeLess = 0.00;

				// combIncome = defendantMnthGr + defendantOthrParentInc;
				combIncome = defendantMnthGr;
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

					if (gdlnIncome == combIncome)
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
					else if (gdlnIncome < combIncome)
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
						// din.close();
						// break;
					}
					else if (gdlnIncome > combIncome)
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

				if (combIncome == 0.00)
				{
					defendantRespOthrChildren = 0.00;
				}
				else
				{
					if (combIncome <= 1050.00)
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
							defendantRespOthrChildren = ((((combIncome - gdlnIncomeLess) / (gdlnIncomeGtr - gdlnIncomeLess)) * (childGtr - childLess)) + childLess);
						}
					}
				}
				/**
				 * Acts Mod# 2783 - If Other Parent income is zero, use full
				 * amount of obligation as adjustment. If other parent is in
				 * home, use one-half the obligation
				 */
				if (defendantOthrParentInc > 0.00)
				{
					defendantRespOthrChildren = defendantRespOthrChildren / 2;
				}
			}

			/**
			 * Calculate Adjusted Gross Income for Plaintiff & Defendant
			 */
			plaintiffAdjGross = plaintiffMnthGr - plaintiffPreChildSupport - plaintiffRespOthrChildren;

			defendantAdjGross = defendantMnthGr - defendantPreChildSupport - defendantRespOthrChildren;
			combinedAdjGross = plaintiffAdjGross + defendantAdjGross;

			/**
			 * Check Poverty Level based on number of children and Defendant's
			 * Gross Adjusted Income
			 */

			// Do not apply the self-sufficiency reserve incorporated into the
			// shaded area of
			// the schedule when using Worksheet C

			/*
			 * switch (totalChildren) { case 1: if (defendantAdjGross <=
			 * 1300.00) { combinedAdjGross = defendantAdjGross; poverty = 'Y'; }
			 * break; case 2: if (defendantAdjGross <= 1650.00) {
			 * combinedAdjGross = defendantAdjGross; poverty = 'Y'; } break;
			 * case 3: if (defendantAdjGross <= 1800.00) { combinedAdjGross =
			 * defendantAdjGross; poverty = 'Y'; } break; case 4: if
			 * (defendantAdjGross <= 2050.00) { combinedAdjGross =
			 * defendantAdjGross; poverty = 'Y'; } break; case 5: if
			 * (defendantAdjGross <= 2300.00) { combinedAdjGross =
			 * defendantAdjGross; poverty = 'Y'; } break; case 6: if
			 * (defendantAdjGross <= 2550.00) { combinedAdjGross =
			 * defendantAdjGross; poverty = 'Y'; } break; }
			 */

			if (combinedAdjGross < 0.00)
			{
				plaintiffShareInc = plaintiffAdjGross / combinedAdjGross;
				defendantShareInc = defendantAdjGross / combinedAdjGross;
			}
			if (combinedAdjGross > 0.00)
			{
				plaintiffShareInc = plaintiffAdjGross / combinedAdjGross;
				defendantShareInc = defendantAdjGross / combinedAdjGross;
			}

			/*
			 * if (defendantShareInc >= 1.00) { plaintiffShareInc = 0.00; }
			 */

			plaintiffSharePct = plaintiffShareInc * 100;
			defendantSharePct = defendantShareInc * 100;

			/**
			 * Obtain the basic child support oblation based on total number of
			 * childre and adjusted gross income of both plaintiff and defendant
			 */

			if (totalChildren > 0)
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
				childGtr = 0.00;
				gdlnIncomeGtr = 0.00;
				gdlnIncomeLess = 0.00;

				combIncome = plaintiffAdjGross + defendantAdjGross;
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

					if (gdlnIncome == combIncome)
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
					else if (gdlnIncome < combIncome)
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
					else if (gdlnIncome > combIncome)
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

				// MOD # 3098 11/03/2010 if (combIncome <= 800.00) {
				if (combIncome <= 1050.00)
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
						basicObligation = ((((combIncome - gdlnIncomeLess) / (gdlnIncomeGtr - gdlnIncomeLess)) * (childGtr - childLess)) + childLess);
					}
				}
			}

			/**
			 * Obtain Child Care Costs for both plaintiff and Defendant
			 *
			 * BST 10/29/2010 Income at which 100% of child care costs are
			 * calculated revised $1100 > 1850, 1500 > 2500, 1700 > 2800, 1900 >
			 * 3100, 2100 > 3400, 2300 > 3700
			 */
			switch (totalChildren)
			{
			case 1:
				if (plaintiffMnthGr < 1850.00) ChildCarePL = 1.00;
				break;
			case 2:
				if (plaintiffMnthGr < 2500.00) ChildCarePL = 1.00;
				break;
			case 3:
				if (plaintiffMnthGr < 2800.00) ChildCarePL = 1.00;
				break;
			case 4:
				if (plaintiffMnthGr < 3100.00) ChildCarePL = 1.00;
				break;
			case 5:
				if (plaintiffMnthGr < 3400.00) ChildCarePL = 1.00;
				break;
			case 6:
				if (plaintiffMnthGr < 3700.00) ChildCarePL = 1.00;
				break;
			}

			switch (totalChildren)
			{
			case 1:
				if (defendantMnthGr < 1850.00) ChildCareDF = 1.00;
				break;
			case 2:
				if (defendantMnthGr < 2500.00) ChildCareDF = 1.00;
				break;
			case 3:
				if (defendantMnthGr < 2800.00) ChildCareDF = 1.00;
				break;
			case 4:
				if (defendantMnthGr < 3100.00) ChildCareDF = 1.00;
				break;
			case 5:
				if (defendantMnthGr < 3400.00) ChildCareDF = 1.00;
				break;
			case 6:
				if (defendantMnthGr < 3700.00) ChildCareDF = 1.00;
				break;
			}

			/**
			 * Compute Split Custody Adjustments for Plaintiff and Defendant
			 */

			iCombinedNbrChildren = plaintiffNbrChildren + defendantNbrChildren;
			dPlaintiffNbrChildren = plaintiffNbrChildren1 + defendantNbrChildren1;

			if (dPlaintiffNbrChildren > 0.00)
			{
				plaintiffSpCustody = plaintiffNbrChildren1 / dPlaintiffNbrChildren;
				defendantSpCustody = defendantNbrChildren1 / dPlaintiffNbrChildren;
			}

			if (dPlaintiffNbrChildren < 0.00)
			{
				plaintiffSpCustody = plaintiffNbrChildren1 / dPlaintiffNbrChildren;
				defendantSpCustody = defendantNbrChildren1 / dPlaintiffNbrChildren;
			}

			plaintiffSpCustodyPct = plaintiffSpCustody * 100;
			defendantSpCustodyPct = defendantSpCustody * 100;

			plaintiffSpCustAdj = basicObligation * plaintiffSpCustody;
			defendantSpCustAdj = basicObligation * defendantSpCustody;

			/**
			 * Calculate Plaintiff Support for Children w/Defendant
			 */

			plaintiffSPOthrCh = defendantSpCustAdj * plaintiffShareInc;
			defendantSPOthrCh = plaintiffSpCustAdj * defendantShareInc;

			/**
			 * Calculate individual and combined adjustments
			 */
			/*
			 * if (poverty == 'Y') { plaintiffTotalAdj = 0.00; } else {
			 */
			plaintiffTotalAdj = (plaintiffChildCare * ChildCarePL) + plaintiffHealthCosts + plaintiffExtraExp;
			/* } */
			defendantTotalAdj = (defendantChildCare * ChildCareDF) + defendantHealthCosts + defendantExtraExp;
			combinedTotalAdj = plaintiffTotalAdj + defendantTotalAdj;

			/**
			 * Calculate each parent fair share of adjustment
			 */
			/*
			 * if (poverty == 'Y') { plaintiffFairAdj = 0.00; defendantFairAdj =
			 * defendantTotalAdj; } else {
			 */
			plaintiffFairAdj = combinedTotalAdj * plaintiffShareInc;
			defendantFairAdj = combinedTotalAdj * defendantShareInc;
			/* } */

			/**
			 * Calculate each parents adjustments over fair share
			 */

			if (plaintiffTotalAdj > plaintiffFairAdj) plaintiffAdjOverShr = plaintiffTotalAdj - plaintiffFairAdj;

			if (defendantTotalAdj > defendantFairAdj) defendantAdjOverShr = defendantTotalAdj - defendantFairAdj;

			/**
			 * Calculate each parent's adjusted support obligation
			 */

			plaintiffAdjSup = plaintiffSPOthrCh - plaintiffAdjOverShr;
			defendantAdjSup = defendantSPOthrCh - defendantAdjOverShr;

			/**
			 * Calculate recommended support
			 */

			resultN1 = plaintiffAdjSup;
			resultN2 = defendantAdjSup;

			if (plaintiffAdjSup > defendantAdjSup)
			{
				plaintiffRecmndSup = plaintiffAdjSup - defendantAdjSup;
				/*
				 * if (plaintiffRecmndSup < 50.00) { plaintiffRecmndSup = 50.00;
				 * }
				 */
			}

			if (defendantAdjSup > plaintiffAdjSup)
			{
				defendantRecmndSup = defendantAdjSup - plaintiffAdjSup;
				/*
				 * if (defendantRecmndSup < 50.00) { defendantRecmndSup = 50.00;
				 * }
				 */
			}
			
			model.addAttribute("plaintiffMnthGr", df.format(plaintiffMnthGr));
			model.addAttribute("defendantMnthGr", df.format(defendantMnthGr));
            model.addAttribute("plaintiffPreChildSupport", df.format(plaintiffPreChildSupport));
			model.addAttribute("defendantPreChildSupport", df.format(defendantPreChildSupport));
			model.addAttribute("plaintiffRespOthrChildren", df.format(plaintiffRespOthrChildren));
			model.addAttribute("defendantRespOthrChildren", df.format(defendantRespOthrChildren));

			model.addAttribute("plaintiffAdjGross", df.format(plaintiffAdjGross));
			model.addAttribute("defendantAdjGross", df.format(defendantAdjGross));
			model.addAttribute("combinedAdjGross", df.format(combinedAdjGross));
			model.addAttribute("plaintiffSharePct", pf.format(plaintiffSharePct) + "%");
			model.addAttribute("defendantSharePct", pf.format(defendantSharePct) + "%");
			model.addAttribute("basicObligation", df.format(basicObligation));
			model.addAttribute("plaintiffNbrChildren", plaintiffNbrChildren);
			model.addAttribute("defendantNbrChildren", defendantNbrChildren);
			model.addAttribute("iCombinedNbrChildren", iCombinedNbrChildren);
			model.addAttribute("plaintiffSpCustodyPct", pf.format(plaintiffSpCustodyPct) + "%");
			model.addAttribute("defendantSpCustodyPct", pf.format(defendantSpCustodyPct) + "%");
			model.addAttribute("plaintiffSpCustAdj", df.format(plaintiffSpCustAdj));
			model.addAttribute("defendantSpCustAdj", df.format(defendantSpCustAdj));
			model.addAttribute("plaintiffSPOthrCh", df.format(plaintiffSPOthrCh));
			model.addAttribute("defendantSPOthrCh", df.format(defendantSPOthrCh));
			model.addAttribute("plaintiffChildCare", df.format(plaintiffChildCare));
			model.addAttribute("defendantChildCare", df.format(defendantChildCare));
			model.addAttribute("plaintiffHealthCosts", df.format(plaintiffHealthCosts));
			model.addAttribute("defendantHealthCosts", df.format(defendantHealthCosts));
			model.addAttribute("plaintiffExtraExp", df.format(plaintiffExtraExp));
			model.addAttribute("defendantExtraExp", df.format(defendantExtraExp));
			model.addAttribute("plaintiffTotalAdj", df.format(plaintiffTotalAdj));
			model.addAttribute("defendantTotalAdj", df.format(defendantTotalAdj));
			model.addAttribute("combinedTotalAdj", df.format(combinedTotalAdj));
			model.addAttribute("plaintiffFairAdj", df.format(plaintiffFairAdj));
			model.addAttribute("defendantFairAdj", df.format(defendantFairAdj));
			model.addAttribute("plaintiffAdjOverShr", df.format(plaintiffAdjOverShr));
			model.addAttribute("defendantAdjOverShr", df.format(defendantAdjOverShr));
			model.addAttribute("plaintiffAdjSup", df.format(plaintiffAdjSup));
			model.addAttribute("defendantAdjSup", df.format(defendantAdjSup));
			model.addAttribute("plaintiffRecmndSup", df.format(plaintiffRecmndSup));
			model.addAttribute("defendantRecmndSup", df.format(defendantRecmndSup));

			returnPage = AppConstants.ECOA_PARENTS_WORKSHEETC_CALC;

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
