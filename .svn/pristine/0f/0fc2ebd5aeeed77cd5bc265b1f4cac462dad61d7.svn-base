<%@ include file="../include/taglib.jsp"%>
<%
	/**
	   Name        : ClerkOfCourtList.jsp
	   Description : This Program will display the list of all clerk of court addresses or CSE Agency addresses from the search.
	     The information presented displays the Court Name, City, State and Zip.
	
	   Java Bean   : ThirdPartyBean
	   Form        : No
	   JavaScript  : No
	   Images      : rule.gif
	   Servlet     : No
	   JSP         : SearchClerkOfCourt.jsp
	**/
%>

<script>
	function Decrement(form) {
		var iPage = parseInt(form.page.value);
		if (iPage > 0) {
			form.page.value = iPage - 1;
			form.submit();
		}
	}

	function Increment(form) {
				
		if((window.location.href).indexOf("clerkOfCourtList/1.htm") >= 0)
		{
			form.action = "${pageContext.request.contextPath}/clerkOfCourtListIter/1.htm";
		}
		else if((window.location.href).indexOf("clerkOfCourtList/2.htm") >= 0)
		{
			form.action = "${pageContext.request.contextPath}/clerkOfCourtListIter/2.htm";
		}	
		else if((window.location.href).indexOf("clerkOfCourtListIter/1.htm") >= 0)
		{
			form.action = "${pageContext.request.contextPath}/clerkOfCourtListIter/1.htm";
		}	
		else if((window.location.href).indexOf("clerkOfCourtListIter/2.htm") >= 0)
		{
			form.action = "${pageContext.request.contextPath}/clerkOfCourtListIter/2.htm";
		}	
		var iPage = parseInt(form.page.value);
		form.page.value = iPage + 1;
		
		form.submit();
	}
	
	function goToCOCListDetailPage(id, type)
	{		
		if((window.location.href).indexOf("clerkOfCourtList/1.htm") >= 0)
		{
			location.href="${pageContext.request.contextPath}/clerkOfCourtDetail/1.htm?id3pty="+id +"&id3ptyType="+type;						
		}
		else if((window.location.href).indexOf("clerkOfCourtList/2.htm") >= 0)
		{
			location.href="${pageContext.request.contextPath}/clerkOfCourtDetail/2.htm?id3pty="+id +"&id3ptyType="+type;						
		}
		else if((window.location.href).indexOf("clerkOfCourtListIter/1.htm") >= 0)
		{
			location.href="${pageContext.request.contextPath}/clerkOfCourtDetail/1.htm?id3pty="+id +"&id3ptyType="+type;						
		}
		else if((window.location.href).indexOf("clerkOfCourtListIter/2.htm") >= 0)
		{
			location.href="${pageContext.request.contextPath}/clerkOfCourtDetail/2.htm?id3pty="+id +"&id3ptyType="+type;						
		}
	}
</script>
<div id="content">
  <div class="gutter"> 
	  <table width="100%" border="0">
			<tr><td colspan="2"><b>North Carolina Clerk of Court Addresses </b></td></tr>
			<tr><td  colspan="2"><hr></td></tr>			
			<tr>
		    	<jsp:useBean id="date" class="java.util.Date" />
				<td colspan="2">Below is a listing of the ${title} Addresses found based on your search criteria as of <fmt:formatDate pattern="dd-MMM-yyyy" value="${date}" />	</td>
			</tr>
	</table>	
	<form name="List" method="post"  action="">	 
	<input type="hidden" name="page" value="${page}"> <br>		
	 	<table width="80%" border="0" cellpadding="0" cellspacing="0">	
			<tr>
				<td width="6%">&nbsp;</td>
				<td width="48%">&nbsp;</td>
				<td width="18%">&nbsp;</td>
				<td width="8%">&nbsp;</td>
				<td width="10%">&nbsp;</td>
			</tr>			
			<tr>				
				<td width="6%" height="20">&nbsp;</td>
				<td width="48%" height="20"><font><b>Clerk of Court</b></font></td>
				<td width="18%" height="20"><font><b>City</b></font></td>
				<td width="8%" height="20"><font><b>State</b></font></td>
				<td width="10%" height="20"><font><b>Zip</b></font></td>
			</tr>
			<tr>
				<td width="6%">&nbsp;</td>
				<td width="48%">&nbsp;</td>
				<td width="18%">&nbsp;</td>
				<td width="8%">&nbsp;</td>
				<td width="10%">&nbsp;</td>
			</tr>

		<c:choose>
			<c:when test="${dispCocList.size()>0}">
				<c:forEach var="coc" items="${dispCocList}">
					<tr>
						<td width="6%">
							<div align="center">								
								<a href="#" onclick="javascript:goToCOCListDetailPage('${coc.getThirdPartyId().trim()}','${fipsType}')">
								<img src="${pageContext.request.contextPath}/images/bluesphere.gif" width="18" height="17" border="0">
							    </a>
							</div>
						</td>
						<td width="48%">${coc.getThirdPartyNm()}</td>
						<td width="18%">${coc.getThirdPartyCity()}</td>
						<td width="8%">${coc.getThirdPartyState()}</td>
						<td width="10%">${coc.getThirdPartyZip5()}</td>
					</tr>
					<tr>
						<td width="6%">&nbsp;</td>
						<td width="48%">&nbsp;</td>
						<td width="18%">&nbsp;</td>
						<td width="8%">&nbsp;</td>
						<td width="10%">&nbsp;</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td width="6%"></td>
					<td colspan="6">No Court Addresses found for the search</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	<br>
	<table width="70%" border="0">
		<tr>
			<td width="75%" height="36">&nbsp;</td>
			<td width="15%" height="36">
				<div align="left"></div> <fmt:parseNumber var="pageNumber" integerOnly="true" type="number" value="${page}" /> 
				<c:if test="${page>0}">
					<a href="Javascript:Decrement(document.List)"> <img src="${pageContext.request.contextPath}/images/prev.gif" alt="Previous" border="0"></a>
				</c:if>
			</td>
			<td width="10%" height="36">
				<div align="left"></div>
				 <c:if test="${nextExists}">
					<a href="Javascript:Increment(document.List)"><img src="${pageContext.request.contextPath}/images/next.gif" alt="Next" border="0"></a>
				</c:if>
			</td>
		</tr>
	</table>
	<p>
		<input type="hidden" name=fipsType value="${fipsType}">
	</p>
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
</form>
</div>
</div>