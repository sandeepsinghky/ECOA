<%@ include file="../include/taglib.jsp"%>


<Script language="Javascript1.3">
	
function printme() {
	if (document.all) {
		window.print();
	} else if (document.layers) {
		window.print();
	}
}

function showLayer() {
	if (document.layers) {
		document.layers.printLayer.visibility="show";
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

}

</script>
<%
	/**
	   Name        : GuideLInesTable.jsp
	   Description : Display Child support Gudelines table.
	   Java Bean   : No
	   Form        : Yes
	   Method      : Post 
	   JavaScript  : No
	   Images      : bluesphere.gif
	   Servlet     : No
	   JSP         : GuideLines.jsp
	   09/21/2006 BST : Modifired effective date to October 1, 2006 and AOC Revision to 10/06 and 2006
	        Modified shaded area variables to 8,15,18,23,28,33
	   11/14/2014 VIN : modified effective date
	   11/18/2014 VIN : modified shaded area 
	**/
%>



<body bgcolor="#FFFFFF" text="#000000" onbeforeprint="hidePrint();"
	onafterprint="showPrint();">

	<form name="form1" method="post" action="GuideLinesTable.jsp">
		<ilayer id="printLayer" visibility="hide">
		<div id="printButton">
			<a href="javascript:void(printme());"><img
				src="images/btn-print-page.gif" border="0"></a>
		</div>
		</ilayer>
		<br> <br>
		<table width="39%">
			<tr>
				<td width="6%" height="21">
					<div align="left">
						<a href="cseGuideLines.htm"><img
							src="${pageContext.request.contextPath}/images/bluesphere.gif"
							width="18" height="17" alt="Back to Child Support GuideLines"
							border="0"></a>
					</div>
				</td>
				<td width="94%" height="21"><font
					face="Arial, Helvetica, sans-serif" size="2">Back to Child
						Support Guidelines</font></td>
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

		<c:set var="shadecounter" value="0" />
		<c:set var="childmin" value="1" />
		<c:set var="child1max" value="8" />
		<c:set var="child2max" value="15" />
		<c:set var="child3max" value="21" />
		<c:set var="child4max" value="26" />
		<c:set var="child5max" value="32" />
		<c:set var="child6max" value="38" />


		<table width="70%" border="1" cellpadding="0" cellspacing="0"
			bordercolor="#000000">
			<tr>
				<td height="84" colspan="7">
					<div align="center">
						<p>
							<font face="Arial, Helvetica, sans-serif"><b><font
									size="5" color="#0000FF"><a NAME="MBCSO"></a>North
										Carolina</font></b></font>
						</p>
						<p>
							<font color="#0000FF"><b><font
									face="Arial, Helvetica, sans-serif" size="2">Proposed
										Monthly Basic Child Support Obligations</font></b></font>
						</p>
						<p>
					</div>
				</td>
			</tr>
			<tr>
				<td width="10%" rowspan="2"><b><font color="#0000FF"
						face="Arial, Helvetica, sans-serif" size="2">Combined
							Adjusted Gross Income</font></b></td>
				<td colspan="6">
					<div align="center">
						<b><font color="#0000FF">Effective January 1, 2015</font></b>
					</div>
				</td>
			</tr>
			<tr>
				<td width="15%"><b><font color="#0000FF"
						face="Arial, Helvetica, sans-serif" size="2">One Child</font></b></td>
				<td width="15%"><b><font color="#0000FF"
						face="Arial, Helvetica, sans-serif" size="2">Two Children</font></b></td>
				<td width="15%"><b><font color="#0000FF"
						face="Arial, Helvetica, sans-serif" size="2">Three Children</font></b></td>
				<td width="15%"><b><font color="#0000FF"
						face="Arial, Helvetica, sans-serif" size="2">Four Children</font></b></td>
				<td width="15%"><b><font color="#0000FF"
						face="Arial, Helvetica, sans-serif" size="2">Five Children</font></b></td>
				<td width="15%"><b><font color="#0000FF"
						face="Arial, Helvetica, sans-serif" size="2">Six Children</font></b></td>
			</tr>

			<c:forEach var="guideLine" items="${guideLines}">

				<c:set var="shadecounter" value="${shadecounter + 1}" />

				<tr>
					<td width="10%" height="10">
						<div align="center">${guideLine.getGdlnIncome()}</div>
					</td>
					<c:choose>
						<c:when
							test="${(shadecounter >= childmin) && (shadecounter <= child1max)}">

							<td bgcolor="#CCCCCC" width="15%" height="10">
								${guideLine.getGdlnAmtChild1()}</td>

						</c:when>

						<c:otherwise>

							<td width="15%" height="10">${guideLine.getGdlnAmtChild1()}</td>

						</c:otherwise>

					</c:choose>

					<c:choose>
						<c:when
							test="${(shadecounter >= childmin) && (shadecounter <= child2max)}">

							<td bgcolor="#CCCCCC" width="15%" height="10">${guideLine.getGdlnAmtChild2()}</td>

						</c:when>

						<c:otherwise>

							<td width="15%" height="10">${guideLine.getGdlnAmtChild2()}</td>

						</c:otherwise>

					</c:choose>
					<c:choose>
						<c:when
							test="${(shadecounter >= childmin) && (shadecounter <= child3max)}">

							<td bgcolor="#CCCCCC" width="15%" height="10">${guideLine.getGdlnAmtChild3()}</td>

						</c:when>

						<c:otherwise>

							<td width="15%" height="10">${guideLine.getGdlnAmtChild3()}</td>

						</c:otherwise>

					</c:choose>

					<c:choose>
						<c:when
							test="${(shadecounter >= childmin) && (shadecounter <= child4max)}">

							<td bgcolor="#CCCCCC" width="15%" height="10"><font
								face="Arial, Helvetica, sans-serif" size="2">${guideLine.getGdlnAmtChild4()}</font></td>

						</c:when>

						<c:otherwise>

							<td width="15%" height="10">${guideLine.getGdlnAmtChild4()}</td>

						</c:otherwise>

					</c:choose>

					<c:choose>
						<c:when
							test="${(shadecounter >= childmin) && (shadecounter <= child5max)}">

							<td bgcolor="#CCCCCC" width="15%" height="10">${guideLine.getGdlnAmtChild5()}</td>

						</c:when>

						<c:otherwise>

							<td width="15%" height="10">${guideLine.getGdlnAmtChild5()}</td>

						</c:otherwise>

					</c:choose>



					<c:choose>
						<c:when
							test="${(shadecounter >= childmin) && (shadecounter <= child6max)}">

							<td bgcolor="#CCCCCC" width="15%" height="10">${guideLine.getGdlnAmtChild6()}</td>

						</c:when>

						<c:otherwise>

							<td width="15%" height="10">${guideLine.getGdlnAmtChild6()}</td>

						</c:otherwise>

					</c:choose>

				</tr>

			</c:forEach>

		</table>
		<table width="427">
			<tr>
				<td width="29%" height="21"><font
					face="Arial, Helvetica, sans-serif" size="2">AOC-A-162, Rev.
						1/15 </font></td>
			</tr>
			<tr>
				<td width="29%" height="19"><font size="2"
					face="Arial, Helvetica, sans-serif">&copy; 2015
						Administrative Office of the Courts</font></td>
			</tr>
		</table>
		<br>
		<table width="39%">
			<tr>
				<td width="6%" height="21">
					<div align="left">
						<a href="cseGuideLines.htm"><img src="images/bluesphere.gif"
							width="18" height="17" alt="Back to Child Support GuideLines"
							border="0"></a>
					</div>
				</td>
				<td width="94%" height="21">Back to Child Support Guidelines</td>
						
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

		
		<p>&nbsp;</p>
	</form>

