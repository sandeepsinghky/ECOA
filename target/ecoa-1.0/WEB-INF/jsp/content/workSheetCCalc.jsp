<%@ include file="../include/taglib.jsp"%>

<%--
	/**
	 Name        : WorkSheetCCalc.jsp
	 Description : Worksheet C Screen will display the document with the calculated child support Obligation amount. 
	 
	 DATE CHANGED         NAME                   REASON 
	          
      9/20/2017        Mallika      changed format of jsp to integrate with spring MVC and tiles navigation structure for ECOA application .
                                     No functional changes are applied. In ActsUser application this page  corresponds to the view part of WorkSheetC.jsp          

	 02/20/02          Sujatha      Program is fixed to calculate Child care cost correctly when the income 
	 CT # 123511					   of the plaintiff is exactly $1100.00 for 1 child, $1500.00 for 2 children, 
	 1700.00 for 3 children, 1900.00 for 4 children, 2100.00 for 5 children,
	 and 2300.00 for 6 children.
	 02/21/02          Sujatha      Program is fixed to calculate Child care cost based on the gross income.
	 CT # 124245
	
	 09/26/06          Bhanu        Program is fixed to set the poverty line to "Y" correctly when the defendent 
	 Guidelines MOD   		   	   gross income is less than $1200.00 for 1 child, $1650.00 for 2 children, 
	 1800.00 for 3 children, 2050.00 for 4 children, 2300.00 for 5 children,
	 and 2550.00 for 6 children. Also reset the Revision year as 2006.
	
	 10/09/2006        Bhanu        Modified the revision number as Rev. 10/06 from 10/02 for item 4.
	 CT#313374 	
	 11/03/2010        Bhanu        The plaintiffOtherInc and defendentOthrInc is not being used.  
	 MOD#3098                      Eliminated the other parent income input5 and value6 from the screen.
	 Comented out parsing of getParameter(value5)and getParameter(value6). 
	 Maximum income eligible for $50 minimum order was increased 
	 from $950 to $1050.  
	 The self-sufficiency reserve incorporated into the shaded area
	 of the schedule do not apply when using Worksheet B.
	 The Income at which 100% of child care costs are calculated has 
	 been revised.
	 12/24/2014        VINAYAK  	Modified the revision number as Rev.
	 The Income at which 100% of child care costs are calculated has 
	 been revised.						   	   
	 **/
--%>

<script>
	
	function printMe()
	{
	     var prtContent = document.getElementById('workSheetCCalc');
		
		 var WinPrint = window.open('', '', 'height=' + screen.height + ',width=' + screen.width + ',fullscreen');
		
		 WinPrint.document.write(prtContent.innerHTML);
		
		  WinPrint.document.close();
		    WinPrint.focus();
		    WinPrint.print();
		    WinPrint.close();
	}

</script>



<form name="form1">

	<div id="content">
		<div class="gutter">

			<!-- <ilayer id="printLayer" visibility="hide">
			<div id="printButton">
				<a href="javascript:window.print();"><img
					src="images/btn-print-page.gif" border="0"></a>
			</div>
			</ilayer> -->
			
			<img alt="" src="images/btn-print-page.gif" border ="0"  onclick="printMe()"  />

			<div>

				<div id = "workSheetCCalc" style="margin-left: 15px" style="margin-right:5px">
					<table width="99%" border="1" cellpadding="0" cellspacing="0">
						<tr>
							<td rowspan="4" colspan="2">
								<p>
									<b><font face="Arial, Helvetica, sans-serif" size="3">STATE
											OF NORTH CAROLINA</font></b> <br> <br> <b><font
										face="Arial, Helvetica, sans-serif" size="2">______________________
											County</font></b>
								</p>
								<p>&nbsp;</p>
							</td>

							<td colspan="2" height="38" style="vertical-align: top">
								<p>
									<font face="Arial, Helvetica, sans-serif" size="1"><i>File
											No.</i></font>
								</p>
							</td>

							<td width="11%" height="38" style="vertical-align: top">
								<p>
									<font face="Arial, Helvetica, sans-serif" size="1"><i>IV-D
											Case No.</i></font>
								</p>
							</td>
						</tr>
						<tr>
							<td colspan="2" height="33" style="vertical-align: top">
								<p>
									<font face="Arial, Helvetica, sans-serif" size="1"><i>Case
											No.(Code)</i></font>
								</p>
							</td>
							<td width="11%" height="33" style="vertical-align: top">
								<p>
									<font face="Arial, Helvetica, sans-serif" size="1"><i>UIFSA
											Case No.</i></font>
								</p>
							</td>
						</tr>

						<tr>
							<td colspan="3" height="17">
								<div align="center">
									<font size="1">In the General Court of Justice</font>
								</div>
								<div>
									<span style="float: left"> <font size="1"> <img
											src="images/check.gif" border="1"> District
									</font></span> <span style="float: right"> <font size="1"><img
											src="images/check.gif" border="1"> Superior Court
											Division </font></span>
								</div>
							</td>
						</tr>
						<tr></tr>

						<tr>
							<td height="41" rowspan="2" colspan="2">
								<div>
									<font " size="1"> <span style="float: left"><img
											src="images/check.gif" border="1"> Civil: </span> <span
										style="float: right" style="padding-right:50px">Plaintiff_____________________________</span></font>
								</div> <br>

								<div>
									<font size="1"> <span style="float: left"><img
											src="images/check.gif" border="1"> Criminal:</span> <span
										style="float: right" style="padding-left:50px">STATE
											&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></font>
								</div>
							</td>
							<td rowspan="4" colspan="3"><b>
									<p align="center">WORKSHEET C</p>
									<p align="center">CHILD SUPPORT OBLIGATION</p>

									<p align="center">SPLIT CUSTODY</p>
							</b>


								<p align="right">
									<font face="Arial, Helvetica, sans-serif" size="1">G.S.50-13.4(c)</font>
								</p></td>
						</tr>
						<tr></tr>

						<tr>
							<td align="center" colspan="2"><b>VERSUS</b></td>
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
									<font size="1">Children</font>
								</div>
							</td>
							<td width="10%">
								<div align="center">
									<font size="1">Date of Birth</font>
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<font size="1">Children</font>
								</div>
							</td>
							<td width="10%">
								<div align="center">
									<font size="1">Date of Birth</font>
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
							<td width="16%"><font size="1"><b>Plaintiff</b></font></td>
							<td width="16%"><font size="1"><b>Defendant</b></font></td>
							<td width="18%"><font size="1"><b>Combined</b></font></td>
						</tr>
						<dl>
							<tr>
								<td colspan="2"><font size="1"><dt>1. MONTHLY
											GROSS INCOME</dt></font></td>
								<td width="16%">${plaintiffMnthGr}</td>
								<td width="16%">${defendantMnthGr}</td>
								<td width="18%"></td>
							</tr>

							<tr>
								<td colspan="2"><font size="1"
									face="Arial, Helvetica, sans-serif"><dd>a. Minus
											pre-existing child support payment</dd></font></td>
								<td width="16%">${plaintiffPreChildSupport}</td>
								<td width="16%">${defendantPreChildSupport}</td>
								<td width="18%">&nbsp;</td>
							</tr>
							<tr>
								<td colspan="2" height="10"><font size="1"
									face="Arial, Helvetica, sans-serif"><dd>b. Minus
											responsibility for other children</dd> <br> </font></td>
								<td width="16%" height="10">${plaintiffRespOthrChildren}</td>
								<td width="16%" height="10">${defendantRespOthrChildren}</td>
								<td width="18%" height="10">&nbsp;</td>
								</ul>
							</tr>
						</dl>
						<tr>
							<td colspan="2"><font size="1"
								face="Arial, Helvetica, sans-serif">2. MONTHLY ADJUSTED
									GROSS INCOME</font></td>
							<td width="16%">${plaintiffAdjGross}</td>
							<td width="16%">${defendantAdjGross}</td>
							<td width="18%">${combinedAdjGross}</td>
						</tr>
						<tr>
							<td colspan="2"><font size="1"
								face="Arial, Helvetica, sans-serif">3. PERCENTAGE SHARE
									OF INCOME <i>(line 2 for each parent's income, divided by
										Combined income)</i><br>
							</font></td>
							<td width="16%">${plaintiffSharePct}</td>
							<td width="16%">${defendantSharePct}</td>
							<td width="18%">&nbsp;</td>
						</tr>
						<tr>
							<td colspan="2">
								<p>
									<font size="1" face="Arial, Helvetica, sans-serif">4.
										BASIC CHILD SUPPORT OBLIGATION <i> (apply line 2 Combined
											to the Schedule of Basic Support Obligations- see AOC-A-162,
											Rev. 1/15) </i>
									</font>
								</p>
							</td>
							<td width="16%">&nbsp;</td>
							<td width="16%">&nbsp;</td>
							<td width="18%">${basicObligation}</td>
						</tr>
						<tr>
							<td colspan="2">
								<p>
									<font size="1" face="Arial, Helvetica, sans-serif">5a.
										SPLIT CUSTODY ADJUSTMENT <i> (enter the number of children
											living with each parent &amp; total number of children) </i>
									</font>
								</p>
							</td>
							<td width="16%">${plaintiffNbrChildren}</td>
							<td width="16%">${defendantNbrChildren}</td>
							<td width="18%">${iCombinedNbrChildren}</td>
						</tr>
						<tr>
							<td colspan="2"><font size="1"
								face="Arial, Helvetica, sans-serif">5b.Number of children
									with each parent divided by total number of children</font></td>
							<td width="16%">${plaintiffSpCustodyPct}</td>
							<td width="16%">${defendantSpCustodyPct}</td>
							<td width="18%">&nbsp;</td>
						</tr>
						<tr>
							<td colspan="2"><font size="1"
								face="Arial, Helvetica, sans-serif">5c. Multiply line 4
									by line 5b for each parent</font></td>
							<td width="16%">${plaintiffSpCustAdj}</td>
							<td width="16%">${defendantSpCustAdj}</td>
							<td width="18%"></td>
						</tr>
						<tr>
							<td colspan="2"><font size="1"
								face="Arial, Helvetica, sans-serif">6a. PLAINTIFF'S
									SUPPORT FOR CHILDREN WITH DEFENDANT <i> (Multiply
										defendant's line 5c by plaintiff's line3) </i>
							</font></td>
							<td width="16%">${plaintiffSPOthrCh}</td>
							<td width="16%"></td>
							<td width="18%">&nbsp;</td>
						</tr>
						<tr>
							<td colspan="2"><font size="1"
								face="Arial, Helvetica, sans-serif">6b. DEFENDANT'S
									SUPPORT FOR CHILDREN WITH PLAINTIFF <i> (Multiply
										plaintiff's line 5c by defendant's line3)</i>
							</font></td>
							<td width="16%">&nbsp;</td>
							<td width="16%">${defendantSPOthrCh}</td>
							<td width="18%">&nbsp;</td>
						</tr>
						<dl>
							<tr>
								<td colspan="2"><font size="1"
									face="Arial, Helvetica, sans-serif">
										<dt>
											7. ADJUSTMENTS <i>(expenses paid directly by each parent)</i>
										</dt>
										<dd>
											a. Work-related child care cost ADJUSTMENTS <i>(expenses
												paid directly by each parent)</i>
										</dd>
								</font></td>
								<td width="16%">${plaintiffChildCare}</td>
								<td width="16%">${defendantChildCare}</td>
								<td width="18%">&nbsp;</td>
							</tr>
							<tr>
								<td colspan="2"><font size="1"
									face="Arial, Helvetica, sans-serif"><dd>
											b. Health Insurance premium costs - child's/children's
											portion only <i>(total preminum &divide; # of persons
												covered &times; # of children subject to order = children's
												portion ) </i>
										</dd></font></td>
								<td width="16%">${plaintiffHealthCosts}</td>
								<td width="16%">${defendantHealthCosts}</td>
								<td width="18%">&nbsp;</td>
							</tr>
							<tr>
								<td colspan="2"><font face="Arial, Helvetica, sans-serif"
									size="1"><dd>c. Extra expenses</dd></font></td>
								<td width="16%">${plaintiffExtraExp}</td>
								<td width="16%">${defendantExtraExp}</td>
								<td width="18%">&nbsp;</td>
							</tr>
							<tr>
								<td colspan="2"><font face="Arial, Helvetica, sans-serif"
									size="1"><dd>
											d. Total Adjustments <i> (For each column, add 7a,7b, and
												7c. Add two totals for Combined amount.) </i>
										</dd></font></td>
								<td width="16%">${plaintiffTotalAdj}</td>
								<td width="16%">${defendantTotalAdj}</td>
								<td width="18%">${combinedTotalAdj}</td>
							</tr>
						</dl>
						<tr>
							<td colspan="2"><font face="Arial, Helvetica, sans-serif"
								size="1">8. EACH PARENT'S FAIR SHARE OF ADJUSTMENTS <i>
										(line 7d Combined &times; line 3 for each parent) </i></font></td>
							<td width="16%">${plaintiffFairAdj}</td>
							<td width="16%">${defendantFairAdj}</td>
							<td width="18%">&nbsp;</td>
						</tr>
						<tr>
							<td colspan="2"><font face="Arial, Helvetica, sans-serif"
								size="1">9. ADJUSTMENTS PAID IN EXCESS OF FAIR SHARE <i>(line
										7d minus line 8. If negative number, enter zero) </i></font></td>
							<td width="16%">${plaintiffAdjOverShr}</td>
							<td width="16%">${defendantAdjOverShr}</td>
							<td width="18%">&nbsp;</td>
						</tr>
						<tr>
							<td colspan="2"><font face="Arial, Helvetica, sans-serif"
								size="1">10. EACH PARENT'S ADJUSTED SUPPORT
									OBLIGATION(line 6a or 6b minus line 9 for each parent.)</font></td>
							<td width="16%">${plaintiffAdjSup}</td>
							<td width="16%">${defendantAdjSup}</td>
							<td width="18%">&nbsp;</td>
						</tr>
						<tr>
							<td colspan="2"><font face="Arial, Helvetica, sans-serif"
								size="1">11. RECOMMENDED CHILD SUPPORT ORDER <i>
										(subtract lesser amount from greater amount on line 10 and
										enter result directly under greater amount.) </i></font></td>
							<td width="16%">${plaintiffRecmndSup}</td>
							<td width="16%">${defendantRecmndSup}</td>
							<td width="18%">&nbsp;</td>
						</tr>
						<tr>
							<td height="28" style="vertical-align: top"><font size="1"
								face="Arial, Helvetica, sans-serif"><i>Date</i></font></td>
							<td colspan="4" height="28" style="vertical-align: top"><font
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
								face="Arial, Helvetica, sans-serif" size="1">AOC-CV-629,
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
									<a href="workSheetC.htm"><img src="images/bluesphere.gif"
										width="18" height="17" alt="NC Child Support Worksheet C"
										border="0"></a>
								</div>
							</td>
							<td><font face="Arial, Helvetica, sans-serif" size="2">NC
									Child Support Worksheet C</font></td>
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
		</div>
	
</form>

<script language="Javascript1.3">
	
	setTimeout('showLayer();', 200);

</script>

</html>

