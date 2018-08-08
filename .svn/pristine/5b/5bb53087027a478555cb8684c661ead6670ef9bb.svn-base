
<%@ include file="../include/taglib.jsp"%>


<SCRIPT LANGUAGE="JavaScript">
	var pledgexp = /^\d*$|^\d*\.\d{2}$/;

	function isValid(pattern, str) {
		return pattern.test(str);
	}
	//vijay modified on 03/11/2015
	function validate(num) {
		if (num.value.length == 0) {
			alert("Invalid entry! Only dollar amount(i.e 100.00 or 100) accepted in this field!");
			num.focus();
			num.select();
			return false;
		} else if (isInteger(num.value)) {
			return true;
		} else if (!isValid(pledgexp, num.value)) {
			alert("Invalid entry! Only dollar amount(i.e 100.00 or 100) accepted in this field!");
			num.focus();
			num.select();
			return false;
		} else
			return true;
	}
	//vijay modified on 03/11/2015
	function validateInt(num1) {
		if (!isInteger(num1.value)) {
			alert("Invalid entry! Only numbers are accepted!");
			num1.focus();
			num1.select();
			return false;
		} else {
			if ((num1.value) > 6) {
				alert("Invalid entry! Cannot be more than 6 children!");
				num1.focus();
				num1.select();
				return false;
			}
			return true;
		}
	}
	//vijay modified on 03/11/2015
	function validateSubmit(form) {
		if ((!validateInt(document.cso.input13))
				|| (!validate(document.cso.input1))
				|| (!validate(document.cso.input2))
				|| (!validate(document.cso.input3))
				|| (!validate(document.cso.input4))
				|| (!validateInt(document.cso.input15))
				|| (!validateInt(document.cso.input16))
				|| (!validate(document.cso.input7))
				|| (!validate(document.cso.input8))
				|| (!validate(document.cso.input9))
				|| (!validate(document.cso.input10))
				|| (!validate(document.cso.input11))
				|| (!validate(document.cso.input12))) {
			return false;
		} else if ((eval(form.input13.value) > 6)) {
			alert("Invalid entry! Total Cannot be more than 6 children!");
			form.input13.focus();
			form.input13.select();
			return false;
		} else if (eval(form.input1.value) + eval(form.input2.value) > 25000) {
			alert("If Income is above $25000.00, please do calculations manually!");
			form.input1.focus();
			form.input1.select();
			return false;
		} else if ((eval(form.input13.value) == 0)) {
			alert("Invalid entry! Cannot be less than one child!");
			form.input13.focus();
			form.input13.select();
			return false;
		} else
			return true;
	}

	function validateChild(num1) {
		var valid1 = "0123456789"
		var ok1 = "yes";
		var temp1;
		if ((num1.value.length) == 0)
			ok1 = "no";
		for (var j = 0; j < num1.value.length; j++) {
			temp1 = "" + num1.value.substring(j, j + 1);
			if (valid1.indexOf(temp1) == "-1")
				ok1 = "no";
		}
		if (ok1 == "no") {
			alert("Invalid entry! Only integers are accepted!");
			num1.focus();
			num1.select();
		} else {
			if ((num1.value) > 6) {
				alert("Invalid entry! Cannot be more than 6 children!");
				num1.focus();
				num1.select();
			}
		}
	}

	function isInteger(s) {
		var i;
		for (i = 0; i < s.length; i++) {

			var c = s.charAt(i);
			if (!isDigit(c)) {
				return false;
			}
		}
		// All characters are numbers.
		return true;
	}

	function isDigit(c) {
		return ((c >= "0") && (c <= "9"));
	}
</script>

<div id="content">
	<div class="gutter">
		<BODY onLoad="JavaScript: document.cso.input13.focus()">
			<STRONG> North Carolina Child Support Worksheet A (Primary
				Custody)</STRONG>
			<hr>
			<br>
			<b>Use this worksheet when one parent (or a third party) has
				primary physical custody of all of the children for whom support is
				being determined.</b>
			<FORM name="cso" method="post" action="worksheetACalc.htm">
				<div align="left">
					<br> <b>Please be sure to replace the default values
						presented here with your own values before you calculate!</b>
				</div>
				<br>
				<table style="border-collapse: collapse" width="100%" border="0"
					cellspacing="0" cellpadding="0" bordercolor="#FFFFFF">
					<tr>
						<td width="50%"></td>
						<td width="25%" bgcolor="#0000FF"><b><font
								color="#FFFFFF">Plaintiff</font></b></td>
						<td width="25%" bgcolor="#0000FF"><b><font
								color="#FFFFFF">Defendant</font></b></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td width="50%">Number of children</td>
						<td width="25%"><input name=input13 value="1"
							onBlur="validateChild(this.form.input13);" size="2" maxlength="2"></td>
						<td width="25%">&nbsp;</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>

					<tr>
						<td width="50%" height="31">Monthly Gross Income</td>
						<td width="25%" height="31"><input type=text name=input1
							value="0.00" size="13" maxlength="13"></td>
						<td width="25%" height="31"><input type=text name=input2
							value="0.00" size="13" maxlength="13"></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td width="50%" height="17">Pre-existing Child Support
							payment</td>
						<td width="25%" height="17"><input type=text name=input3
							value="0.00" size="13" maxlength="13"></td>
						<td width="25%" height="17"><input type=text name=input4
							value="0.00" size="13" maxlength="13"></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>

					<tr>
						<td width="50%">Number Of Other Children</td>
						<td width="25%"><input type=text name=input15 value="0"
							size="2" maxlength="2"></td>
						<td width="25%"><input type=text name=input16 value="0"
							size="2" maxlength="2"></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td width="50%">Work related child care costs</td>
						<td width="25%"><input type=text name=input7 value="0.00"
							size="13" maxlength="13"></td>
						<td width="25%"><input type=text name=input8 value="0.00"
							size="13" maxlength="13"></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td width="50%">Health Insurance Premium costs</td>
						<td width="25%"><input type=text name=input9 value="0.00"
							size="13" maxlength="13"></td>
						<td width="25%"><input type=text name=input10 value="0.00"
							size="13" maxlength="13"></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td width="50%">Extraordinary expense</td>
						<td width="25%"><input type=text name=input11 value="0.00"
							size="13" maxlength="13"></td>
						<td width="25%"><input type=text name=input12 value="0.00"
							size="13" maxlength="13"></td>
					</tr>
					<tr>
						<td width="50%">&nbsp;</td>
						<td width="25%">&nbsp;</td>
						<td width="25%">&nbsp;</td>
					</tr>
					<tr>
						<td colspan="3">
							<div align="left">
								<a
									href="JavaScript:if (validateSubmit(document.cso)){document.cso.submit();}"><img
									src="images/compute.gif" width="90" height="20" border="0"></a>
							</div>
						</td>
					</tr>
					<tr>
						<td width="50%">&nbsp;</td>
						<td width="25%">&nbsp;</td>
						<td width="25%">&nbsp;</td>
					</tr>
					<tr>
						<td colspan="3">
							<p>
								<b> For a blank "Worksheet A" visit:<a
									href="http://www.nccourts.org/Forms/Documents/1234.pdf">
										http://www.nccourts.org/Forms/Documents/1234.pdf </a> <font
									face="Arial, Helvetica, sans-serif" size="0.5"><i>(Cntrl+Click
											to open)</i> </font>
								</b>
							</p>
						</td>

					</tr>
				</table>
				<br>
				<table width="550" border="0">
					<tr>
						<td width="18">
							<div align="left">
								<a href="cseGuideLines.htm"><img src="images/bluesphere.gif"
									width="18" height="17" alt="Back to Child Support Guidelines"
									border="0"></a>
							</div>
						</td>
						<td>Back
								to Child Support Guidelines</td>
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
			</FORM>
			<div align="center"></div>
	</div>
</div>

</BODY>

