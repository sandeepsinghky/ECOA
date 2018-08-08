<%@ include file="../include/taglib.jsp"%>
<div id="content">
	<div class="gutter">

		<div>
			<B>North Carolina Child Support Guidelines <br> <hr>
			</B>
		</div>
		<P>The calculation of child support is governed by North Carolina
			Child Support Guidelines established by the Conference of Chief
			District Court Judges. The worksheets are the property of and
			copyrighted by the Administrative Office of the Courts (AOC) and
			placed on this web site with permission of AOC.</P>

		<div>
			<B>General Rules Governing Child Support Modification</B>
		</div>
		<P>

			The calculation of child support involves the use of a formula which
			has certain variables. The most significant variables are each
			party's income, daycare expenses, the cost of medical insurance, and
			the living arrangements of the children. <b><br> Revised
				January 1, 2015<br></b> <br>
		<table width="100%" border="0">
			<tr>
				<td width="18">
					<div align="left">
						<a
							href="${pageContext.request.contextPath}/cseGuideLineDetails.htm"><img
							src="images/bluesphere.gif" width="18" height="17"
							alt="GuideLines" border="0"> </a>
					</div>
				</td>
				<td>North Carolina Child Support Guidelines</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td width="18" height="20">
					<div align="left">
						<a href="${pageContext.request.contextPath}/workSheetA.htm"><img
							src="images/bluesphere.gif" width="18" height="17"
							alt="Worksheet A" border="0"></a>
					</div>
				</td>
				<td height="20">North Carolina Child Support Worksheet A (Primary Custody)</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td width="18">
					<div align="left">
						<a href="${pageContext.request.contextPath}/workSheetB.htm"><img
							src="images/bluesphere.gif" width="18" height="17"
							alt="WorkSheet B" border="0"></a>
					</div>
				</td>
				<td>North Carolina Child Support Worksheet B (Joint/Shared Custody)</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td width="18">
					<div align="left">
						<a href="${pageContext.request.contextPath}/workSheetC.htm"><img
							src="images/bluesphere.gif" width="18" height="17"
							alt="WorkSheet C" border="0"></a>
					</div>
				</td>
				<td>North Carolina Child Support Worksheet C (Split Custody)</td>
			</tr>
			<tr>
				<td width="18"><a href="WorkACalcTPTCustody.jsp"></a></td>
				<td></td>
			</tr>
			
			
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
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