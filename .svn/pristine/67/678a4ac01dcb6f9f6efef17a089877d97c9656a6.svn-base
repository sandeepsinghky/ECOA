<%@ include file="../include/taglib.jsp"%>

<script>
	
	/* function printme() {
		if (document.all) {
			window.print();
		} else if (document.layers) {
			window.print();
		}
	}

	function showLayer() {
		if (document.layers) {
			document.layers.printLayer.visibility = "show";
		}
	}

	function hidePrint() {
		var the_print;
		if (window.document.all) {
			the_print = window.document.all.printButton.style;
		} else if (document.layers) {
			the_print = window.document.printButton;
		}
		the_print.visibility = "hidden";

	}
	function showPrint() {
		var the_print;
		if (window.document.all) {
			the_print = window.document.all.printButton.style;
		} else if (document.layers) {
			the_print = window.document.printButton;
		}
		the_print.visibility = "visible";

	} */
	
	
	function printMe()
	{
	     var prtContent = document.getElementById('workSheetBCalc');
		
		 var WinPrint = window.open('', '', 'height=' + screen.height + ',width=' + screen.width + ',fullscreen');
		
		 WinPrint.document.write(prtContent.innerHTML);
		
		  WinPrint.document.close();
		    WinPrint.focus();
		    WinPrint.print();
		    WinPrint.close();
	}

</script>
<%--
	/**
	 Name        : WorkSheetB.jsp
	 Description : Worksheet B Screen will display the document with the calculated child support Obligation amount. 
	 Java Bean   : No
	 Form        : No
	 JavaScript  : No
	 Images      : btn-print-page.gif
	 Servlet     : No
	 JSP         : No                 
	 DATE CHANGED         NAME                   REASON          
         
      9/20/2017        Mallika         changed format of jsp to integrate with spring MVC and tiles navigation structure for ECOA application .
                                       No functional changes are applied. In ActsUser application this page  corresponds to the view part of WorkSheetB.jsp 
	 02/20/02          Sujatha      Program is fixed to calculate Child care cost correctly when the income 
	 CT # 123511					   of the plaintiff is exactly $1100.00 for 1 child, $1500.00 for 2 children, 
	 1700.00 for 3 children, 1900.00 for 4 children, 2100.00 for 5 children,
	 and 2300.00 for 6 children.
	 02/21/02          Sujatha      Program is fixed to calculate Child care cost based on the gross income.
	 CT # 124245
	
	 11/03/2010        Bhanu        The plaintiffOtherInc and defendentOthrInc is not being used.  
	 MOD#3098                      Eliminated the other parent income input5 and value6 from the screen.
	 Comented out parsing of getParameter(value5)and getParameter(value6). 
	 Maximum income eligible for $50 minimum order was increased 
	 from $950 to $1050.  
	 The self-sufficiency reserve incorporated into the shaded area
	 of the schedule do not apply when using Worksheet B.
	 The Income at which 100% of child care costs are calculated has 
	 been revised.
	
	 **/
--%>

<body text="#333333" link="#0000FF" vlink="#0000FF" alink="#0000FF"
	onbeforeprint="hidePrint();" onafterprint="showPrint();"
	bgcolor="#FFFFFF">

	<div id="content">
		<div class="gutter">

			<form name="form1">

				<!-- <ilayer id="printLayer" visibility="hide">
				<div id="printButton">
					<a href="javascript:window.print();"><img
						src="images/btn-print-page.gif" border="0"></a>
				</div>
				</ilayer> -->
				
				<img alt="" src="images/btn-print-page.gif" border ="0"  onclick="printMe()"  />
         
    
				<div>
					<div id = "workSheetBCalc" style="margin-left: 15px" style="margin-right:5px">
						<table width="99%" border="1" cellpadding="0" cellspacing="0">
							
							<tr>
								<td rowspan="4" colspan="2">
									<p>
										<b><font  size="3">STATE
												OF NORTH CAROLINA</font></b> <br> <br> <b> <font
											face="Arial, Helvetica, sans-serif" size="2">______________________County</font></b>
									</p>
									<p>&nbsp;</p>
								</td>
								<td height="33" style="vertical-align: top">
									<p>
										<font  size="1"><i>File
												No.</i></font>
									</p>
								</td>
								<td colspan="2" height="33" style="vertical-align: top">
									<p>
										<font  size="1"><i>IV-D
												Case No.</i></font>
									</p>
								</td>
							</tr>
							<tr>
								<td height="32" style="vertical-align: top">
									<p>
										<font  size="1"><i>Case
												No.(Code)</i></font>
									</p>
								</td>
								<td colspan="2" height="32" style="vertical-align: top">
									<p>
										<font  size="1"><i>UIFSA
												Case No.</i></font>
									</p>
								</td>
							</tr>

							<tr>
								<td colspan="3" height="17">
									<div align="center">
										<font face="Arial, Helvetica, sans-serif" size="1">In
											the General Court of Justice</font>
									</div>
									<div>
										<span style="float: left"> <font
											face="Arial, Helvetica, sans-serif" size="1"> <img
												src="images/check.gif" border="1"> District
										</font></span> <span style="float: right"> <font
											face="Arial, Helvetica, sans-serif" size="1"><img
												src="images/check.gif" border="1"> Superior Court
												Division </font></span>
									</div>
								</td>
							</tr>

							<tr></tr>
							<tr>
								<td height="41" rowspan="2" colspan="2">
									<div>
										<font face="Arial, Helvetica, sans-serif" size="1"> <span
											style="float: left"><img src="images/check.gif"
												border="1"> Civil: </span> <span style="float: right"
											style="padding-right:50px">Plaintiff_____________________________</span></font>
									</div> <br>

									<div>
										<font  size="1"> <span
											style="float: left"><img src="images/check.gif"
												border="1"> Criminal:</span> <span style="float: right"
											style="padding-left:50px">STATE&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></font>
									</div>
								</td>
								<td rowspan="4" colspan="3"><b><p align="center">
											WORKSHEET B
												
										</p>
										<p align="center">
											CHILD
												SUPPORT OBLIGATION
										</p>

										<p align="center">
											<font face="Arial, Helvetica, sans-serif" size="2">JOINT
												OR SHARED PHYSICAL CUSTODY</font>
										</p></b>


									<p align="right">
										<font face="Arial, Helvetica, sans-serif" size="1">G.S.50-13.4(c)</font>
									</p></td>
							</tr>
							<tr></tr>
							<tr>
								<td align="center" colspan="2"><b><font
										face="Arial, Helvetica, sans-serif" size="2">VERSUS</font></b></td>
							</tr>
							<tr>
								<td height="37" colspan="2" style="vertical-align: top">
									<p>
										<font face="Arial, Helvetica, sans-serif" size="1"><i>Name
												of Defendant</i> </font>
									</p>
								</td>
							</tr>
							<tr></tr>
							<tr>
								<td width="40%">
									<div align="center">
										<font face="Arial, Helvetica, sans-serif" size="1">Children</font>
									</div>
								</td>
								<td width="10%">
									<div align="center">
										<font face="Arial, Helvetica, sans-serif" size="1">Date
											of Birth</font>
									</div>
								</td>
								<td colspan="2">
									<div align="center">
										<font face="Arial, Helvetica, sans-serif" size="1">Children</font>
									</div>
								</td>
								<td width="10%">
									<div align="center">
										<font face="Arial, Helvetica, sans-serif" size="1">Date
											of Birth</font>
									</div>
								</td>
							</tr>

							<tr>
								<td width="40%">&nbsp;</td>
								<td width="10%">&nbsp;</td>
								<td colspan="2">&nbsp;</td>
								<td width="10%">&nbsp;</td>
							</tr>
							<tr>
								<td width="40%">&nbsp;</td>
								<td width="10%">&nbsp;</td>
								<td colspan="2">&nbsp;</td>
								<td width="10%">&nbsp;</td>
							</tr>
							<tr>
								<td width="40%">&nbsp;</td>
								<td width="10%">&nbsp;</td>
								<td colspan="2">&nbsp;</td>
								<td width="10%">&nbsp;</td>
							</tr>
							<tr>
								<td colspan="2"><font face="Arial, Helvetica, sans-serif"
									size="1"><b>STOP here if the number of overnights
											with either parent is less than 123</b>, in which case shared
										physical custody does not apply <i>(and see Worksheet A,
											AOC-CV-627).</i></font></td>
								<td width="16%"><font face="Arial, Helvetica, sans-serif"
									size="1"><b>Plaintiff</b></font></td>
								<td width="16%"><font face="Arial, Helvetica, sans-serif"
									size="1"><b>Defendant</b></font></td>
								<td width="18%"><font face="Arial, Helvetica, sans-serif"
									size="1"><b>Combined</b></font></td>
							</tr>
							<dl>
								<tr>
									<td colspan="2" height="18"><font
										 size="1"><dt>1.
												MONTHLY GROSS INCOME</dt></font></td>
									<td width="16%" height="18">${plaintiffGrossInc}</td>
									<td width="16%" height="18">${defendantGrossInc}</td>
									<td width="18%" height="18"></td>
								</tr>
								<tr>
									<td colspan="2" height="18"><font size="1"
										face="Arial, Helvetica, sans-serif"><dd>a. Minus
												pre-existing child support payment</dd></font></td>
									<td width="16%" height="18">${plaintiffPreCS}</td>
									<td width="16%" height="18">${defendantPreCS}</td>
									<td width="18%" height="18">&nbsp;</td>
								</tr>
								<tr>
									<td colspan="2" height="18"><font size="1"
										face="Arial, Helvetica, sans-serif"><dd>b. Minus
												responsibility for other children</dd></font></td>
									<td width="16%" height="18">${plaintiffRespOthrChildren}</td>
									<td width="16%" height="18">${defendantRespOthrChildren}</td>
									<td width="18%" height="18">&nbsp;</td>
								</tr>
							</dl>
							<tr>
								<td colspan="2"><font size="1"
									face="Arial, Helvetica, sans-serif">2. MONTHLY ADJUSTED
										GROSS INCOME</font></td>
								<td width="16%">${plaintiffAdjGrossInc}</td>
								<td width="16%">${defendantAdjGrossInc}</td>
								<td width="18%">${combinedAdjGrossInc}</td>
							</tr>
							<tr>
								<td colspan="2"><font size="1"
									>3. PERCENTAGE SHARE
										OF INCOME <i>(line 2 for each parent's income, divided by
											Combined income)</i><br>
								</font></td>
								<td width="16%">${plaintiffAdjShrPct}</td>
								<td width="16%">${defendantAdjShrPct}</td>
								<td width="18%">&nbsp;</td>
							</tr>
							<tr>
								<td colspan="2" height="16">
									<p>
										<font face="Arial, Helvetica, sans-serif" size="1">4.
											BASIC CHILD SUPPORT OBLIGATION <i> (apply line 2 Combined
												to the Schedule of Basic Support Obligations- see AOC-A-162,
												Rev. 1/15)</i>
										</font>
									</p>
								</td>
								<td width="16%" height="16">&nbsp;</td>
								<td width="16%" height="16">&nbsp;</td>
								<td width="18%" height="16">${basicObligation}</td>
							</tr>
							<tr>
								<td colspan="2" height="11">
									<p>
										<font face="Arial, Helvetica, sans-serif" size="1">5.
											SHARED CUSTODY BASIC OBLIGATION <i>(multiply line 4
												&times; 1.5)</i>
										</font>
									</p>
								</td>
								<td width="16%" height="11">&nbsp;</td>
								<td width="16%" height="11">&nbsp;</td>
								<td width="18%" height="11">${sharedCustodyObligation}</td>
							</tr>
							<tr>
								<td colspan="2"><font face="Arial, Helvetica, sans-serif"
									size="1">6. EACH PARENT'S PORTION OF SHARED CUSTODY
										SUPPORT OBLIGATION <i> (line 3 &times; line 5 for each
											parent) </i>
								</font></td>
								<td width="16%">${plaintiffShr}</td>
								<td width="16%">${defendantShr}</td>
								<td width="18%">&nbsp;</td>
							</tr>
							<tr>
								<td colspan="2" height="18"><font
									face="Arial, Helvetica, sans-serif" size="1">7.
										OVERNIGHTS WITH EACH PARENT <i> (must total 365 &times;
											total number of children) </i>
								</font></td>
								<td width="16%" height="18">${plaintiffOvernights}</td>
								<td width="16%" height="18">${defendantsOvernights}</td>
								<td width="18%" height="18">${combinedOvernights}</td>
							</tr>
							<tr>
								<td colspan="2" height="18"><font
									 size="1">8.
										PERCENTAGE WITH EACH PARENT <i>(line 7 divided by 365
											&times; total number of children)</i>
								</font></td>
								<td width="16%" height="18">${plaintiffOvernightShrPct}</td>
								<td width="16%" height="18">${defendantOvernightShrPct}</td>
								<td width="18%" height="18">&nbsp;</td>
							</tr>
							<tr>
								<td colspan="2"><font face="Arial, Helvetica, sans-serif"
									size="1">9. SUPPORT OBLIGATION FOR TIME WITH OTHER
										PARENT <i>(line 6 &times; other parent's line 8)</i>
								</font></td>
								<td width="16%">${plaintiffOblgOthrParent}</td>
								<td width="16%">${defendantOblgOthrParent}</td>
								<td width="18%">&nbsp;</td>
							</tr>
							<dl>
								<tr>
									<td colspan="2" height="31"><font
										face="Arial, Helvetica, sans-serif" size="1"><dt>
												10. ADJUSTMENTS <i>(expenses paid directly by each
													parent)</i>
											</dt></font>
										<p>
											<font face="Arial, Helvetica, sans-serif" size="1"><dd>a.
													Work-related child care costs</dd></font>
										</p></td>
									<td width="16%" height="31">${plaintiffChildCare}</td>
									<td width="16%" height="31">${defandantChildCare}</td>
									<td width="18%" height="31">&nbsp;</td>
								</tr>
								<tr>
									<td colspan="2" height="17"><font
										 size="1"><dd>b.
												Health Insurance premium costs - children's portion</dd></font></td>
									<td width="12%" height="17">${plaintiffHlthIns}</td>
									<td width="12%" height="17">${defendantHlthIns}</td>
									<td width="18%" height="17">&nbsp;</td>
								</tr>
								<tr>
									<td colspan="2" height="16"><font
										 size="1"><dd>c.
												Extraordinary expenses</dd></font></td>
									<td width="16%" height="16">${plaintiffExtraExp}</td>
									<td width="16%" height="16">${defendantExtraExp}</td>
									<td width="18%" height="16">&nbsp;</td>
								</tr>
								<tr>
									<td colspan="2" height="19"><font
										face="Arial, Helvetica, sans-serif" size="1"><dd>
												d. Total Adjustments <i>(For each column, add 10a,10b,
													and 10c. Add two totals for Combined amount.)</i>
											</dd></font></td>
									<td width="16%" height="19">${plaintiffTotalAdj}</td>
									<td width="16%" height="19">${defendantTotalAdj}</td>
									<td width="18%" height="19">${combinedTotalAdj}</td>
								</tr>
							</dl>
							<tr>
								<td colspan="2"><font 
									size="1">11. EACH PARENT'S FAIR SHARE OF ADJUSTMENTS <i>
											(line 10d combined times line 3 for each parent) </i></font></td>
								<td width="16%">${plaintiffFairShr}</td>
								<td width="16%">${defendantFairShr}</td>
								<td width="18%">&nbsp;</td>
							</tr>
							<tr>
								<td colspan="2" height="16">12.
										ADJUSTMENTS PAID IN EXCESS OF FAIR SHARE <i> (line 10d
											minus line 11. If negative number, enter zero) </i>
								</font></td>
								<td width="16%" height="16">${plaintiffAdjFairShr}</td>
								<td width="16%" height="16">${defendantAdjFairShr}</td>
								<td width="18%" height="16">&nbsp;</td>
							</tr>
							<tr>
								<td colspan="2" height="19">13. EACH
										PARENT'S ADJUSTED SUPPORT OBLIGATION <i>(Line 9 minus line
											12.)</i>
								</font></td>
								<td width="16%" height="19">${plaintiffAdjSupOblg}</td>
								<td width="16%" height="19">${defendantAdjSupOblg}</td>
								<td width="18%" height="19">&nbsp;</td>
							</tr>
							<tr>
								<td colspan="2" height="22"><font
									face="Arial, Helvetica, sans-serif" size="1">14.
										RECOMMENDED CHILD SUPPORT ORDER <i> (subtract lesser
											amount from greater amount in line 13 and enter result
											directly under greater amount.) </i>
								</font></td>
								<td width="16%" height="22">${plaintiffRecmSup}</td>
								<td width="16%" height="22">${defendantRecmSup}</td>
								<td width="18%" height="22">&nbsp;</td>
							</tr>
							<tr>
								<td style="vertical-align: top" height="26"><font size="1"
									><i>Date</i></font></td>
								<td style="vertical-align: top" colspan="4" height="26"><font
									size="1" face="Arial, Helvetica, sans-serif"><i>Prepared
											By (type or print)</i></font></td>
							</tr>

							<tr>
								<td colspan="5" height="21">
									<div align="center">
										<font face="Arial, Helvetica, sans-serif" size="1">(<b>NOTE</b>:<i>This
												form may be used in both civil and criminal cases.</i>)
										</font>
									</div>
								</td>
							</tr>
							<tr style="border-bottom-width: 0px">
								<td colspan="6" height="21"><font
									 size="1">AOC-CV-628,
										Rev. 1/15</font><br> <font size="1"
									face="Arial, Helvetica, sans-serif">&copy; 2015
										Administrative Office of the Courts</font>
							</tr>
						</table>
						</div>
						<br> <br>
						<table width="550" border="0">
							<tr>
								<td width="18">
									<div align="left">
										<a href="workSheetB.htm"><img src="images/bluesphere.gif"
											width="18" height="17" alt="NC Child Support Worksheet B"
											border="0"></a>
									</div>
								</td>
								<td><font face="Arial, Helvetica, sans-serif" size="2">NC
										Child Support Worksheet B</font></td>
							</tr>

						</table>
						<sec:authorize access="isAuthenticated()">
			<table width="60%">
				<c:choose>
					<c:when test="${userInfo.getUserType().equals('ncId')}">
						<c:choose>
							<c:when test="${userInfo.isMpiTied() eq true}">

								<tr>
									<td width="3%">
										<div align="left">
											<a
												href="${pageContext.request.contextPath}/cssp/user/parentWelcome.htm"><img
												src="${pageContext.request.contextPath}/images/bluesphere.gif"
												width="18" height="17" alt="Main Menu" border="0"></a>
										</div>
									</td>
									<td width="97%">Return to Main Menu</td>
								</tr>
							</c:when>
							<c:otherwise>

								<tr>
									<td width="3%">
										<div align="left">
											<a
												href="${pageContext.request.contextPath}/cssp/user/userWelcome.htm"><img
												src="${pageContext.request.contextPath}/images/bluesphere.gif"
												width="18" height="17" alt="Main Menu" border="0"></a>
										</div>
									</td>
									<td width="97%">Return to Main Menu</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>

						<tr>
							<td width="3%">
								<div align="left">
									<a
										href="${pageContext.request.contextPath}/cssp/user/parentWelcome.htm"><img
										src="${pageContext.request.contextPath}/images/bluesphere.gif"
										width="18" height="17" alt="Main Menu" border="0"></a>
								</div>
							</td>
							<td width="97%">Return to Main Menu</td>
						</tr>
					</c:otherwise>
				</c:choose>

			</table>
		</sec:authorize>
					</div>
				</div>

			</form>
		</div>
	
</body>
<script language="Javascript1.3">
	
	setTimeout('showLayer();', 200);

</script>
