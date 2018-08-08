<%@ include file="../../../include/taglib.jsp"%>

<script>
	function validateform() {

		var name_regex = /^[a-zA-Z ]+$/;

		var zip_regex = /^\d{5}$/;
		
		var phone_regex   =/^\d{10}$/;
		
		var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;

		var cityexp = /^[a-z_0-9_ ]{1,25}$/i;

		if (document.updateUserProfile.firstName.value == "") {
			alert("First Name should not be empty");
			document.updateUserProfile.firstName.focus();
			return false;
		}
		if (document.updateUserProfile.lastName.value == "") {
			alert("Last Name should not be empty");
			document.updateUserProfile.lastName.focus();
			return false;
		}
		if (!name_regex.test(document.updateUserProfile.firstName.value.trim())) {
			alert("Special characters and numbers are not allowed in first name.");
			document.updateUserProfile.firstName.focus();
			return false;
		}
		if (document.updateUserProfile.midName.value != ""
				&& !name_regex.test(document.updateUserProfile.midName.value.trim())) {
						
			alert("Special characters and numbers are not allowed in middle name.");
			document.updateUserProfile.midName.focus();
			return false;
		}
		if (!name_regex.test(document.updateUserProfile.lastName.value.trim())) {
			alert("Special characters and numbers are not allowed in last name.");
			document.updateUserProfile.lastName.focus();
			return false;

		}
		
		if (document.updateUserProfile.addrLine1.value == "") {
			alert("Address Line1 should not be empty");
            document.updateUserProfile.addrLine1.focus();
			return false;
		}
		
		 if (document.updateUserProfile.phone.value != "" && !phone_regex.test(document.updateUserProfile.phone.value)) {
			alert("phone should be 10 digits long number.");
            document.updateUserProfile.phone.focus();
			return false;
		} 
		
		if (document.updateUserProfile.email.value == "") {
			alert("e-mail should not be empty");
            document.updateUserProfile.email.focus();
			return false;
		}
		
		if (!emailPattern.test(document.updateUserProfile.email.value)) {
			alert("e-mail is not valid");
			document.updateUserProfile.email.focus();
			return false;
			
            
		}

		if (document.updateUserProfile.zipCode.value == "") {
			alert("Zip should not be empty");
			document.updateUserProfile.zipCode.focus();
			return false;
		}

		if (document.getElementById("zipCode").value.trim() != ""
				&& !zip_regex.test(document.getElementById("zipCode").value)) {
			alert("Zip number should be all digits and 5 digits long");
			document.getElementById("zipCode").focus();
			return false;
		}

		if (document.updateUserProfile.city.value == "") {
			alert("City name should not be empty");
			document.updateUserProfile.city.focus();
			return false;
		}

		if (!name_regex.test(document.updateUserProfile.city.value.trim())) {
			alert("Special characters and numbers are not allowed in city name.");
			document.updateUserProfile.city.focus();
			return false;
		}

		//county selection checking code.

		var sel = document.getElementById('state');
		var opt;
		var selLength = sel.options.length;
		for (var initial = 0; initial < selLength; initial++) {
			opt = sel.options[initial];
			if (opt.selected == true && opt.value != "") {

				break;
			}
		}

		if (initial == selLength) {
			alert("Select state of your address");
			document.updateUserProfile.state.focus();
			return false;
		}
		
		

		return true;

	}

	
</script>

<div id="content">
	<div class="gutter">
		<c:if test="${!empty errMsg}">
			<div class="error message-box">${errMsg}</div>
		</c:if>
		<c:if test="${!empty param.success}">
			<div class="success message-box">${param.success}</div>
		</c:if>
		<form name="updateUserProfile" method="post"
			action="${pageContext.request.contextPath}/cssp/user/updateProfile.htm"
			modelAttribute="userProfile">
			<table width="90%" border="0">
				<tr>
					<td colspan="4"><b>Update Customer Profile: </b><br></td>
				</tr>
				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="4">Below is your current information. Please
						revise any outdated information. For security reasons the address
						you enter here updates the website only. Please contact your local
						child support agent to change your address in the child support
						system.</td>
				</tr>
				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>

				<tr>
					<td width="25%"><b>First Name: <font color="#FF0000">*</font></b></td>
					<td colspan="3"><input type="text" id="firstName"
						name="firstName" size="15" maxlength="15"
						value="${userProfile.getFirstName()}"></td>
				</tr>

				<tr>
					<td width="25%"><b>Middle Name: <font color="#FF0000">*</font></b></td>
					<td colspan="3"><input type="text" id="midName" name="midName"
						size="15" maxlength="15" value="${userProfile.getMidName()}"></td>
				</tr>

				<tr>
					<td width="25%"><b>Last Name: <font color="#FF0000">*</font></b></td>
					<td colspan="3"><input type="text" id="lastName"
						name="lastName" size="17" maxlength="17"
						value="${userProfile.getLastName()}"></td>
				</tr>

				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>


				<tr>
					<td width="25%"><b>Address Line 1: <font color="#FF0000">*</font></b></td>
					<td colspan="3"><input type="text" id="addrLine1"
						, name="addrLine1" size="40" maxlength="40"
						value="${userProfile.getAddrLine1()}"></td>
				</tr>
				<tr>
					<td width="25%"><b>Address Line 2:</b></td>
					<td colspan="3"><input type="text" id="addrLine2"
						name="addrLine2" size="40" maxlength="40"
						value="${userProfile.getAddrLine2()}"></td>
				</tr>
				<tr>
					<td width="25%"><b>City: <font color="#FF0000">*</font>
					</b></td>
					<td colspan="3"><input type="text" id="city" name="city"
						size="25" maxlength="25" value="${userProfile.getCity()}">
					</td>
				</tr>
				<tr>
					<td width="25%"><b>State: <font color="#FF0000">*</font>
					</b></td>
					<td width="18%"><select id="state" , name="state">
							<c:forEach var="state" items="${stateLookup}">
								<option value="${state.getCodeId()}"
									${userProfile.getState().equals(state.getCodeId()) ? 'selected' : ''}>
									${state.getCodeDesc()}</option>
							</c:forEach>
					</select></td>
					<td width="10%"><b>Zip: <font color="#FF0000">*</font>
					</b></td>
					<td width="56%"><input type="text" id="zipCode" name="zipCode"
						size="5" maxlength="5" value="${userProfile.getZipCode()}">
					</td>
				</tr>
				<tr>
					<td width="25%" height="16"><b>Phone:</b></td>
					<td colspan="3" height="16"><input type="text" id="phone"
						name="phone" size="10" maxlength="10"
						value="${userProfile.getPhone()}"> (ex: 9195551212)</td>
				</tr>
				<tr>
					<td width="25%" height="20"><b>Email: <font
							color="#FF0000">*</font>
					</b></td>
					<td height="20" colspan="3"><input type="text" id="email"
						name="email" size="50" maxlength="50"
						value="${userProfile.getEmail()}"></td>
				</tr>
				<tr>
					<td width="25%">&nbsp;</td>
					<td colspan="3">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="4">All the required fields are marked with an
						asterisk <b><font color="#FF0000">*</font></b>
					</td>
				</tr>
				<tr>
					<td width="25%">&nbsp;</td>
					<td colspan="3">&nbsp;</td>
				</tr>
				<div>
					<table width="50%" border="0">
						<tr>
							<td>&nbsp;&nbsp;</td>
							<td>&nbsp;</td>
							<td align="left"><input type="submit" name="submit"
								id="submit" value="Update" onclick="return validateform();"></td>
					</table>
				</div>
			</table>
			<br>
			<table width="75%" border="0">
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
			</table>
		</form>
	</div>
</div>









