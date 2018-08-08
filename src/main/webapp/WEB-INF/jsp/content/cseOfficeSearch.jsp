<%@ include file="../include/taglib.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/browsercheck.js"></script>
<script>
	if (!is.ie) {
		document.captureEvents(Event.KEYUP);
	}
	document.onkeyup = checkValue

	function checkValue(evt) {
		var theButtonPressed;

		if (is.ie) {
			theButtonPressed = window.event.keyCode;
		} else {
			theButtonPressed = evt.which;
		}
		if (theButtonPressed == 13) {
			if (validateCity(document.Search)) {
				document.Search.submit()
			}
		}
	}

	function validateCity(form) {

		var cityexp = /^[a-z_0-9_ ]{1,25}$/i;

		var city = form.strCity.value;
		
     	if((window.location.href).indexOf("cseOfficeSearch/1.htm") >= 0)
		{
			form.action = "${pageContext.request.contextPath}/cseOfficeList/1.htm";
		}
		else {
			form.action = "${pageContext.request.contextPath}/cseOfficeList/2.htm";
		}	

		if (city.length > 0) {

			if (!cityexp.test(city)) {
				alert("Invalid characters in City");
				form.strCity.focus();
				return false;
			}
		}
		return true;

	}
</script>
<form name="Search" method="post"  onSubmit="return validateCity(document.Search)">	 
	<div id="content">
		<div class="gutter">
			<h2>Child Support Enforcement Office search</h2>
			<hr>
						
			<p>Search: Child Support Enforcement Office Addresses/Phone Numbers for North Carolina</p>
			
			<p>Fill in one or more of the fields below:</p>
			
			<table width="60%" border="0">
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>				
				<tr>
					<td colspan="4" bgcolor="#FFFFFF" height="20">
						<div align="left">
							<font color="#FF0000"> ${errorMsg == null ? " " : errorMsg}
							</font>
						</div>
					</td>
				</tr>
				<tr>
					<td width="23%">County Name:</td>
					<td width="77%" height="30"><select name="strCounty">
							<c:forEach var="cnty" items="${countyLookup}">
								<option value="${cnty.getCodeId()}">${cnty.getCodeDesc()}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td width="23%">City:</td>
					<td width="77%" height="30"><input type="text" name="strCity"
						size="25" maxlength="25" value=""> <input type="hidden"
						name="srcPage" value="Search"></td>
				</tr>
				<tr>
					<td width="23%">State:</td>
					<td width="23%">North Carolina</td>
				</tr>
				<tr>
					<td width="23%">&nbsp;</td>
					<td width="77%">&nbsp;</td>
				</tr>
				<tr>	
					<td align="left"><input type="submit" name="Search" value="Search" onclick="return validateCity(this)" /></td>							
				</tr>
				<tr>
					<td width="23%">&nbsp;</td>
					<td width="77%">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="4">To obtain a current address for a local child support office complete the fields on the form and click	&quot;Search&quot;.</td>
				</tr>
				<tr>
					<td width="23%">&nbsp;</td>
					<td width="77%">&nbsp;</td>
				</tr>

			</TABLE>
			
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
			<p>
				<input type="hidden" name="fipsType" value="${fipsType}">
			</p>
		</div>
	</div>



</FORM>

