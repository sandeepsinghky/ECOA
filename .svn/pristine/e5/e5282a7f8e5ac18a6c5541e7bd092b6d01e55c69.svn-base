<%@ include file="../include/taglib.jsp"%>


<%
/**
   Name        : ClerkOfCourtDetail.jsp
   Description : This Program will allow the user to view the Clerk of Court address or CSE agency address.
                 The information presented displays the Fips Code, Court Name, Address Line1, Address Line2,
				 City, State, Zip, Phone number and Fax number from the third party table.

   Java Bean   : ThirdPartyBean
   JSP         : SearchClerkOfCourt.jsp
   Program is fixed to display "Map it" Button only if address line 1 and City has values in the field.                
        
  **/
%>

<script type="text/javascript">
function newSearch()
{
	if((window.location.href).indexOf("cseOfficeList/1.htm") >= 0)
	{
		location.href="${pageContext.request.contextPath}/cseOfficeSearch/1.htm";
	}
	else if((window.location.href).indexOf("cseOfficeList/2.htm") >= 0)
	{	location.href="${pageContext.request.contextPath}/cseOfficeSearch/2.htm"; }
	
	else if((window.location.href).indexOf("cseOfficeDetail/1.htm") >= 0)
	{
		location.href="${pageContext.request.contextPath}/cseOfficeSearch/1.htm";
	}
	else if((window.location.href).indexOf("cseOfficeDetail/2.htm") >= 0)
	{
		location.href="${pageContext.request.contextPath}/cseOfficeSearch/2.htm";
	}
}
</script>
<div id="content">
  <div class="gutter">

<table width="100%" border="0">
		<tr>
			<td colspan="2">Search	Details:<hr></td><%-- <img src="${pageContext.request.contextPath}/images/rule.gif" width="100%" height="10"> --%>
		</tr>
		<tr>
			<td bgcolor="#FFFFFF" colspan="2">Please find below a detailed view of the local Child Support Enforcement Agency you selected.
			</td>
		</tr>
	</table>
	<br>
	<table width="80%" border="0">
		<tr>
			<td width="60%">FIPS Code:</td>						
			<td width="1%">${clerkOfCourt.getThirdPartyId()}</td>
			<td width="57%"></td>
			<td width="6%"></td>
		</tr>
		<tr>
		     <td width="60%">&nbsp;</td>						
			<td width="1%">&nbsp;</td>
			<td width="57%">&nbsp;</td>
			<td width="6%">&nbsp;</td>
		</tr>
		<tr>
			<td width="60%">Name of Child Support Enforcement Agency:</td>
			<td colspan="2">${clerkOfCourt.getThirdPartyNm()}</td>
			<td width="6%">&nbsp;</td>
		</tr>
			<tr>
		     <td width="60%">&nbsp;</td>						
			<td width="1%">&nbsp;</td>
			<td width="57%">&nbsp;</td>
			<td width="6%">&nbsp;</td>
		</tr>
		<tr>
			<td width="60%">Address Line 1:</td>						
			<td colspan="2">${clerkOfCourt.getAddressLn1()}</td>
			<td width="6%"></td>
		</tr>
			<tr>
		     <td width="60%">&nbsp;</td>						
			<td width="1%">&nbsp;</td>
			<td width="57%">&nbsp;</td>
			<td width="6%">&nbsp;</td>
		</tr>
		<tr>
			<td width="60%">Address Line 2:</td>						
			<td colspan="2">${clerkOfCourt.getAddressLn2()}</td>
			<td width="6%"></td>
		</tr>
			<tr>
		     <td width="60%">&nbsp;</td>						
			<td width="1%">&nbsp;</td>
			<td width="57%">&nbsp;</td>
			<td width="6%">&nbsp;</td>
		</tr>
		<tr>
			<td width="60%">County:</td>
			<td colspan="2"></td>
			<td width="6%"></td>
		</tr>
			<tr>
		     <td width="60%">&nbsp;</td>						
			<td width="1%">&nbsp;</td>
			<td width="57%">&nbsp;</td>
			<td width="6%">&nbsp;</td>
		</tr>
		<tr>
			<td width="60%">County Contact:</td>
						
			<td colspan="3">${clerkOfCourt.getContactName()} </td>
		</tr>
			<tr>
		     <td width="60%">&nbsp;</td>						
			<td width="1%">&nbsp;</td>
			<td width="57%">&nbsp;</td>
			<td width="6%">&nbsp;</td>
		</tr>
		<tr>
			<td width="60%">City:</td>
			<td colspan="3">${clerkOfCourt.getThirdPartyCity()}</td>
		</tr>
			<tr>
		     <td width="60%">&nbsp;</td>						
			<td width="1%">&nbsp;</td>
			<td width="57%">&nbsp;</td>
			<td width="6%">&nbsp;</td>
		</tr>
		<tr>
			<td width="60%">State:</td>
			<td width="1%">${clerkOfCourt.getThirdPartyState()}</td>
			<td width="57%"></td>
			<td width="6%"></td>
		</tr>
			<tr>
		     <td width="60%">&nbsp;</td>						
			<td width="1%">&nbsp;</td>
			<td width="57%">&nbsp;</td>
			<td width="6%">&nbsp;</td>
		</tr>
		<tr>
			<td width="60%">Zip:</td>
			<td colspan="3">${clerkOfCourt.getThirdPartyZip5()}</td>
		</tr>
			<tr>
		     <td width="60%">&nbsp;</td>						
			<td width="1%">&nbsp;</td>
			<td width="57%">&nbsp;</td>
			<td width="6%">&nbsp;</td>
		</tr>
		<tr>
			<td width="60%">Phone Number:</td>
			<td colspan="3">${clerkOfCourt.getPhoneAreaCode()}-${clerkOfCourt.getPhoneExc()}-${clerkOfCourt.getPhoneLn()}</td>
		</tr>
			<tr>
		     <td width="60%">&nbsp;</td>						
			<td width="1%">&nbsp;</td>
			<td width="57%">&nbsp;</td>
			<td width="6%">&nbsp;</td>
		</tr>
		<tr>
			<td width="60%">Fax Number:</td>
			<td colspan="3"></td>
       <c:choose>
		
		<c:when test="${empty clerkOfCourt.getAddressLn1()}">
		
		<tr>
			<td width="36%"></td>
			<td colspan="3"></td>
		</tr>
		
		</c:when>
		<c:otherwise>
		
		<tr>
			<td width="36%"></td>
			<td colspan="3">&nbsp;</td>
			<!-- 
			<td colspan="3"><a href="https://www.google.com/maps/place/https://www.google.com/maps/place/${clerkOfCourt.getAddressLn1()},${clerkOfCourt.getThirdPartyCity()},${clerkOfCourt.getThirdPartyState()},${clerkOfCourt.getThirdPartyZip5()}" target="_blank">Map it</a></td>	
			-->				
		</tr>
		</c:otherwise>
		</c:choose>
		<tr>			
			<%-- <td colspan="2" height="34"><a href="${pageContext.request.contextPath}/clerkOfCourtSearch/${fipsType}.htm"> New Search</a></td> --%>
			<td colspan="2" height="34"><input type=button value='New Search' onclick="newSearch()" /></td>
			<td width="57%" height="34">&nbsp;</td>
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
	<p>
		<input type="hidden" name=fipsType value="${fipsType}">
	</p>
	<p>&nbsp;</p>
	<p>&nbsp;</p>

</div>
	</div>
