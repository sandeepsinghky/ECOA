<%@ include file="../include/taglib.jsp"%>

<script>
	
	
 
 function printMe()
	{
		
	    var prtContent = document.getElementById('workSheetACalc');
		
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
	 Name        : WorkSheetACalc.jsp
	 Description : WorksheetACalc Screen will display the document with the calculated child support Obligation amount. 
	 Java Bean   : No
	 Form        : No
	 JavaScript  : No
	 Images      : btn-print-page.gif
	 Servlet     : No
	 JSP         : No                 
	 DATE CHANGED         NAME                REASON          
      9/20/2017        Mallika      changed format of jsp to integrate with spring MVC and tiles navigation structure for ECOA application .
                                     No functional changes are applied. In ActsUser application this page  corresponds to the view part of WorkSheetA.jsp 
	 02/20/02          Sujatha     Program is fixed to calculate Child care cost correctly when the income 
	 CT # 123511					  of the plaintiff is exactly $1100.00 for 1 child, $1500.00 for 2 children, 
	 1700.00 for 3 children, 1900.00 for 4 children, 2100.00 for 5 children,
	 and 2300.00 for 6 children.
	 02/21/02          Sujatha     Program is fixed to calculate Child care cost based on the gross income.
	 CT # 124245
	
	 09/26/06          Bhanu       Program is fixed to set the poverty line to "Y" correctly when the defendent 
	 Guidelines MOD   		   	  gross income is less than $1200.00 for 1 child, $1650.00 for 2 children, 
	 1800.00 for 3 children, 2050.00 for 4 children, 2300.00 for 5 children,
	 and 2550.00 for 6 children. Also reset the Revision year as 2006.
	
	 10/05/2006        Bhanu       Modified the revision number as Rev. 10/06 from 10/02 for item 4. 
	
	 11/03/2010        Bhanu       The plaintiffOtherInc and defendentOthrInc is not being used.  
	 MOD#3098                     Eliminated the other parent income input5 and value6 from the screen.
	 Comented out parsing of getParameter(value5)and getParameter(value6). 
	 Maximum income eligible for $50 minimum order was increased 
	 from $950 to $1050.  
	 The self-sufficiency reserve incorporated into the shaded area
	 of the schedule has been applied. 
	 The Income at which 100% of child care costs are calculated has 
	 been revised.
	 11/25/2014         Vinayak     75% Child care costs removed									 
	
	 **/
--%>

<body text="#333333" link="#0000FF" vlink="#0000FF" alink="#0000FF"
	onbeforeprint="hidePrint();" onafterprint="showPrint();"
	bgcolor="#FFFFFF">
	
<div id="content">
		<div class="gutter">

	<!-- <form>

		<ilayer id="printLayer" visibility="hide">
		<div id="printButton">
			<a href="javascript:window.print();"><img
				src="images/btn-print-page.gif" border="0"></a>
		</div>
		</ilayer>

	</form> -->
	
	
	<img alt="" src="images/btn-print-page.gif" border ="0"
            onclick="printMe()"  />
    
	

			<div id = "workSheetACalc" style="margin-left: 15px" style="margin-right:5px">

				<table width="99%" border="1px" cellpadding="0" cellspacing="0">

					<tr>
						<td rowspan="4" colspan="2">
							<p>
								<b><font face="Arial, Helvetica, sans-serif" size="3">STATE
										OF NORTH CAROLINA</font></b> <br> <br> <b><font
									face="Arial, Helvetica, sans-serif" size="2">______________________County</font></b>
							</p>
							<p>&nbsp;</p>
						</td>

						<td style="vertical-align: top" height="43">
							<p>
								<font face="Arial, Helvetica, sans-serif" size="1"><i>File
										No.</i></font>
							</p>
						</td>
						<td colspan="2" style="vertical-align: top" height="43">
							<p>
								<font face="Arial, Helvetica, sans-serif" size="1"><i>IV-D
										Case No.</i></font>
							</p>
						</td>
					</tr>
					<tr>

						<td style="vertical-align: top" height="41">
							<p>
								<font face="Arial, Helvetica, sans-serif" size="1"><i>Case
										No.(Code)</i></font>
							</p>
						</td>
						<td colspan="2" style="vertical-align: top" height="41">
							<p>
								<font face="Arial, Helvetica, sans-serif" size="1"><i>UIFSA
										Case No.</i></font>
							</p>
						</td>
					</tr>
					<tr>
						<td colspan="3" height="17">
							<div align="center">
								<font face="Arial, Helvetica, sans-serif" size="1">In the
									General Court of Justice</font>
							</div>
							<div>
								<span style="float: left"> <font
									face="Arial, Helvetica, sans-serif" size="1"> <img
										src="images/check.gif" border="1"> District
								</font></span> <span style="float: right"> <font
									face="Arial, Helvetica, sans-serif" size="1"> <img
										src="images/check.gif" border="1"> Superior Court
										Division
								</font></span>
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
								<font face="Arial, Helvetica, sans-serif" size="1"> <span
									style="float: left"><img src="images/check.gif"
										border="1"> Criminal:</span> <span style="float: right"
									style="padding-left:50px">STATE &nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></font>
							</div>
						</td>


						<td rowspan="4" colspan="3"><b><p align="center">
									<font face="Arial, Helvetica, sans-serif" size="2">WORKSHEET
										A</font>
								</p>
								<p align="center">
									<font face="Arial, Helvetica, sans-serif" size="2">CHILD
										SUPPORT OBLIGATION</font>
								</p>
								<p align="center">
									<font face="Arial, Helvetica, sans-serif" size="2">PRIMARY
										CUSTODY</font>
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
						<td colspan="2">&nbsp;</td>
						<td width="16%"><font face="Arial, Helvetica, sans-serif"
							size="1"><b>Plaintiff</b></font></td>
						<td width="16%"><font face="Arial, Helvetica, sans-serif"
							size="1"><b>Defendant</b></font></td>
						<td width="18%"><font face="Arial, Helvetica, sans-serif"
							size="1"><b>Combined</b></font></td>
					</tr>

					<dl>
						<tr>
							<td colspan="2"><font face="Arial, Helvetica, sans-serif"
								size="1"><dt>1. MONTHLY GROSS INCOME</dt></font></td>
							<td width="16%">${plaintiffGrossInc}</td>
							<td width="16%">${defendantGrossInc}</td>
							<td width="18%"></td>
						</tr>
						<tr>
							<td colspan="2"><font size="1"
								face="Arial, Helvetica, sans-serif"><dd>a. Minus
										pre-existing child support payment</dd></font></td>
							<td width="16%">${plaintiffPreSup}</td>
							<td width="16%">${defendantPreSup}</td>
							<td width="18%">&nbsp;</td>
						</tr>
						<tr>
							<td colspan="2"><font size="1"
								face="Arial, Helvetica, sans-serif"><dd>b. Minus
										responsibility for other children</dd> </font></td>
							<td width="16%">${plaintiffRespOthrChildren}</td>
							<td width="16%">${defendantRespOthrChildren}</td>
							<td width="18%">&nbsp;</td>
						</tr>
					</dl>
					<tr>
						<td colspan="2" nowrap><font size="1"
							face="Arial, Helvetica, sans-serif">2. MONTHLY ADJUSTED
								GROSS INCOME</font></td>
						<td width="16%">${plaintiffAdjGrossInc}</td>
						<td width="16%">${defendantAdjGrossInc}</td>
						<td width="18%">${combinedAdjGrossInc}</td>
					</tr>
					<tr>
						<td colspan="2"><font size="1"
							face="Arial, Helvetica, sans-serif">3. PERCENTAGE SHARE OF
								INCOME <i>(line 2 for each parent's income, divided by
									Combined income)</i><br>
						</font></td>
						<td width="16%">${plaintiffPctShr}</td>
						<td width="16%">${defendantPctShr}</td>
						<td width="18%">&nbsp;</td>
					</tr>
					<tr>
						<td colspan="2"><font size="1"
							face="Arial, Helvetica, sans-serif">4. BASIC CHILD SUPPORT
								OBLIGATION <i> (apply line 2 Combined to the Schedule of
									Basic Support Obligations- see AOC-A-162, Rev. 1/15) </i>
						</font></td>
						<td width="16%">&nbsp;</td>
						<td width="16%">&nbsp;</td>
						<td width="18%">${basicObligation}</td>
					</tr>
					<dl>
						<tr>
							<td colspan="2"><font size="1"
								face="Arial, Helvetica, sans-serif">
									<dt>
										5. ADJUSTMENTS <i>(expenses paid directly by each parent)
										</i>
									</dt>
							</font> <font size="1" face="Arial, Helvetica, sans-serif">
									<dd>
										a. Work-related child care costs <i>(See Instructions)</i>
									</dd>
							</font></td>
							<td width="16%">${plaintiffCCare}</td>
							<td width="16%">${defendantCCare}</td>
							<td width="18%">&nbsp;</td>
						</tr>
						<tr>
							<td colspan="2" height="25"><font size="1"
								face="Arial, Helvetica, sans-serif"><dd>
										b. Health Insurance premium costs - child's/children's portion
										only <i>(total premium &divide; # of persons covered
											&times; # of children subject to order = children's portion)
										</i>
									</dd> <br> </font></td>
							<td width="16%" height="25">${plaintiffHlthCosts}</td>
							<td width="16%" height="25">${defendantHlthCosts}</td>
							<td width="18%" height="25">&nbsp;</td>
						</tr>
						<tr>
							<td colspan="2"><font size="1"
								face="Arial, Helvetica, sans-serif"><dd>c.
										Extraordinary expenses</dd></font></td>
							<td width="16%">${plaintiffExtExp}</td>
							<td width="16%">${defendantExtExp}</td>
							<td width="18">&nbsp;</td>
						</tr>
						<tr>
							<td colspan="2" height="22"><font size="1"
								face="Arial, Helvetica, sans-serif"><dd>
										d. Total Adjustments <i> (for each column, add 5a, 5b, and
											5c. Add two totals for Combined amount)</i>
									</dd></font></td>
							<td width="16%" height="22">${plaintiffFullExpAdj}</td>
							<td width="16%" height="22">${defendantFullExpAdj}</td>
							<td width="18%" height="22">${combinedFullExpAdj}</td>
						</tr>
					</dl>
					<tr>
						<td colspan="2" height="17"><font size="1"
							face="Arial, Helvetica, sans-serif">6. TOTAL CHILD SUPPORT
								OBLIGATION <i>(add line 4 Combined to line 5d Combined)</i>
						</font></td>
						<td width="16%" height="17">&nbsp;</td>
						<td width="16%" height="17">&nbsp;</td>
						<td width="18%" height="17">${totalObligation}</td>
					</tr>
					<tr>
						<td colspan="2"><font size="1"
							face="Arial, Helvetica, sans-serif">7. EACH PARENT'S CHILD
								SUPPORT OBLIGATION <i>(line 3 &times; line 6 of each parent)</i>
						</font></td>
						<td width="16%">${plaintiffObligation}</td>
						<td width="16%">${defendantObligation}</td>
						<td width="18%">&nbsp;</td>
					</tr>
					<tr>
						<td colspan="2" height="17"><font size="1"
							face="Arial, Helvetica, sans-serif">8. NON-CUSTODIAL
								PARENT ADJUSTMENT<i>(enter non-custodial parent's line 5d)</i>
						</font></td>
						<td width="16%" height="17">${plaintiffNonCustAdj}</td>
						<td width="16%" height="17">${defendantNonCustAdj}</td>
						<td width="18%" height="17">&nbsp;</td>
					</tr>
					<tr>
						<td colspan="2" height="21"><font size="1"
							face="Arial, Helvetica, sans-serif">9. RECOMMENDED CHILD
								SUPPORT ORDER <i>(subtract line 8 from line 7 for the
									non-custodial parent only. Leave custodial parent column blank)</i>
						</font></td>
						<td width="16%" height="21">&nbsp;</td>
						<td width="16%" height="21">${defendantRecmSup}</td>
						<td width="18%" height="21">&nbsp;</td>
					</tr>

					<tr>
						<td style="vertical-align: top" height="26"><font size="1"
							face="Arial, Helvetica, sans-serif"><i>Date</i></font></td>
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
							face="Arial, Helvetica, sans-serif" size="1">AOC-CV0627,
								Rev. 1/15</font><br> <font size="1"
							face="Arial, Helvetica, sans-serif">&copy; 2015
								Administrative Office of the Courts</font>
					</tr>


				</table>
			</div>



			<table style="border-bottom-width: 0px" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="18">
						<div align="left">
							<a href="workSheetA.htm"><img src="images/bluesphere.gif"
								width="18" height="17" alt="NC Child Support Worksheet A"
								border="0"></a>
						</div>
					</td>
					<td><font face="Arial, Helvetica, sans-serif" size="2">NC
							Child Support Worksheet A (Primary Custody)</font></td>
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
</body>
<script language="Javascript1.3">
	
	setTimeout('showLayer();', 200);

</script>

