
<%@ include file="../include/taglib.jsp"%>

<%
	/**
	 Name        : workSheetB.jsp
	 Description : Worksheet B Screen will allow the user to enter the income, expenses, Overnights with each parent
	 and Number of children with each parent in order to calculate the recommended Child support Order.
	 Java Bean   : No
	 Form        : Yes
	 Method      : Post
	 JavaScript  : No
	 Images      : bluesphere.gif, compute.gif
	 Servlet     : No
	 JSP         : Worksheet.jsp, WorkSheetB.jsp
	 9/20/2017        Mallika      changed format of jsp to integrate with spring MVC and tiles navigation structure for ECOA application .
	 No functional changes are applied. In ActsUser application this page  corresponds to the view part of workSheetBCalc.jsp 
	 09/21/2006 BST Increased the value of Monthly Gross Income from 20,000 to 25,000 
	 INC#546902 07/14/2009 BST
	 Added Disclaimer Other Parent Income - please read the guidelines instructions for 
	 exceptions regarding other parents income. When the other parent does not have income, 
	 the worksheet will not calculate correctly.    
	
	 11/03/2010 Bhanu : Eliminated the other parent income input5 and value6 from the screen.
	 MOD#3098      : Removed comments regarding Other Parent Income.
	 : Problem Report # 2 - Added the following error message  
	 : If Income is above $25000.00, Please do calculations manually! 
	 **/
%>


<SCRIPT LANGUAGE="JavaScript">
	//vijay 02/07/2018
	function getURLParameter(name) {
		return decodeURIComponent((new RegExp('[?|&]' + name + '='
				+ '([^&;]+?)(&|#|;|$)').exec(location.search) || [ null, '' ])[1]
				.replace(/\+/g, '%20'))
				|| null;
	}

	var getParams = function(url) {
		var params = {};
		var parser = document.createElement('a');
		parser.href = url;
		var query = parser.search.substring(1);
		var vars = query.split('&');
		for (var i = 0; i < vars.length; i++) {
			var pair = vars[i].split('=');
			params[pair[0]] = decodeURIComponent(pair[1]);
		}
		return params;
	};

	function validate(num) {
		var valid = "0123456789."
		var ok = "yes";
		var temp;
		if (num.value.length == 0)
			ok = "no";
		if (num.value.length == 1)
			if (num.value == '.')
				ok = "no1";
		for (var i = 0; i < num.value.length; i++) {
			temp = "" + num.value.substring(i, i + 1);
			if (valid.indexOf(temp) == "-1")
				ok = "no";
		}
		if (ok == "no") {
			alert("Invalid entry!  Only period(.) and numbers are accepted!");
			num.focus();
			num.select();
		}
		if (ok == "no1") {
			alert("Invalid entry!  Cannot enter Only period(.)!");
			num.focus();
			num.select();
		}
	}

	function validateInt(num1) {
		var valid1 = "0123456789"
		var ok1 = "yes";
		var temp1;
		if (num1.value.length == 0)
			ok1 = "no";
		for (var j = 0; j < num1.value.length; j++) {
			temp1 = "" + num1.value.substring(j, j + 1);
			if (valid1.indexOf(temp1) == "-1")
				ok1 = "no";
		}
		if (ok1 == "no") {
			alert("Invalid entry!  Only integers are accepted!");
			num1.focus();
			num1.select();
		}

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

	//vijay modified on 03/11/2015
	function validateInt2(num1) {
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
	//vijay modified on 04/15/2015
	function validateSubmit(form) {
		totalNumberOfDays = 365 * form.input15.value;
		if ((!validateInt2(document.cso.input15))) {
			return false;
		} else if ((eval(form.input15.value) == 0)) {
			alert("Invalid entry! Cannot be less than one child!");
			form.input15.focus();
			form.input15.select();
			return false;
		} else if ((eval(form.input15.value) > 6)) {
			alert("Invalid entry! total Cannot be more than 6 children!");
			form.input15.focus();
			form.input15.select();
			return false;
		} else if (eval(form.input1.value) + eval(form.input2.value) > 25000) {
			alert("If Income is above $25000.00, please do calculations manually!");
			form.input1.focus();
			form.input1.select();
			return false;
		} else if (eval(form.input7.value) < 123) {
			alert("Invalid entry! Cannot be less than 123 days!");
			form.input7.focus();
			form.input7.select();
			return false;
		} else if (eval(form.input8.value) < 123) {
			alert("Invalid entry! Cannot be less than 123 days!");
			form.input8.focus();
			form.input8.select();
			return false;
		} else if ((eval(form.input7.value) + eval(form.input8.value)) != totalNumberOfDays) {
			alert("Invalid entry! Total must be equal to " + totalNumberOfDays
					+ " days!");
			form.input7.focus();
			form.input7.select();
			return false;
		} else
			return true;
	}
</script>


<div id="content">
	<div class="gutter">

		<STRONG> <b>North Carolina Child Support Worksheet B
				(Joint or Shared Custody)</b></STRONG> <hr> <br> <b>Use this worksheet when (a)
			Parents share custody of all the children for whom support is being
			determined, or (b) when one parent has primary physical custody of
			one or more of the children and the parents share custody of another
			child.</b>


		<FORM name="cso" method="post" action="workSheetBCalc.htm">


			<div align="left">
				<br> <b>Please be sure to replace the default values
					presented here with your own values before you calculate!</b>
			</div>
			<br>

			<table style="border-collapse: collapse" width="100%" border="0"
				cellpadding="0" cellspacing="0" bordercolor="#FFFFFF">
				<tr>
					<td style="padding-top: 1px" style=padding-bottom:1px width="50%"
						height="28"></td>
					<td style="padding-top: 1px" style=padding-bottom:1px width="25%"
						bgcolor="#0000FF" height="28"><b><font color="#FFFFFF">Plaintiff</font></b></td>
					<td style="padding-top: 1px" style=padding-bottom:1px width="25%"
						bgcolor="#0000FF" height="28"><b><font color="#FFFFFF">Defendant</font></b></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td style="padding-top: 1px" style=padding-bottom:1px width="50%"><font
						face="Arial, Helvetica, sans-serif" size="2">Number of
							children</font></td>
					<td style="padding-top: 1px" style=padding-bottom:1px width="25%">
						<input type=text name=input15 value="1"
						onBlur="validateChild(this.form.input15);" size="2" maxlength="2">
					</td>

					<td width="25%">&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td style="padding-top: 1px" style=padding-bottom:1px width="50%"><font
						face="Arial, Helvetica, sans-serif" size="2">Monthly Gross
							Income</font></td>
					<td style="padding-top: 1px" style=padding-bottom:1px width="25%"><input
						type=text name=input1 value="0.00"
						onBlur="validate(this.form.input1);" size="13" maxlength="13"></td>
					<td style="padding-top: 1px" style=padding-bottom:1px width="25%"><input
						type=text name=input2 value="0.00"
						onBlur="validate(this.form.input2);" size="13" maxlength="13"></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td style="padding-top: 1px" style=padding-bottom:1px width="50%">Pre-existing
							Child Support payment</td>
					<td style="padding-top: 1px" style=padding-bottom:1px width="25%"><input
						type=text name=input3 value="0.00"
						onBlur="validate(this.form.input3);" size="13" maxlength="13"></td>
					<td style="padding-top: 1px" style=padding-bottom:1px width="25%"><input
						type=text name=input4 value="0.00"
						onBlur="validate(this.form.input4);" size="13" maxlength="13"></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td style="padding-top: 1px" style=padding-bottom:1px width="50%"><font
						face="Arial, Helvetica, sans-serif" size="2">Number Of
							Other Children</font></td>
					<td style="padding-top: 1px" style=padding-bottom:1px width="20%"><input
						type=text name=input17 value="0"
						onBlur="validateChild(this.form.input17);" size="2" maxlength="2"></td>
					<td style="padding-top: 1px" style=padding-bottom:1px width="20%"><input
						type=text name=input18 value="0"
						onBlur="validateChild(this.form.input18);" size="2" maxlength="2"></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td style="padding-top: 1px" style=padding-bottom:1px width="50%"><font
						face="Arial, Helvetica, sans-serif" size="2">OVERNIGHTS
							with each parent</font></td>
					<td style="padding-top: 1px" style=padding-bottom:1px width="25%"><input
						type=text name=input7 value="0"
						onBlur="validateInt(this.form.input7);" size="13" maxlength="13"></td>
					<td style="padding-top: 1px" style=padding-bottom:1px width="25%"><input
						type=text name=input8 value="0"
						onBlur="validateInt(this.form.input8);" size="13" maxlength="13"></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td style="padding-top: 1px" style=padding-bottom:1px width="50%">Work
						related child care costs</td>
					<td style="padding-top: 1px" style=padding-bottom:1px width="25%"><input
						type=text name=input9 value="0.00"
						onBlur="validate(this.form.input9);" size="13" maxlength="13"></td>
					<td style="padding-top: 1px" style=padding-bottom:1px width="25%"><input
						type=text name=input10 value="0.00"
						onBlur="validate(this.form.input10);" size="13" maxlength="13"></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td style="padding-top: 1px" style=padding-bottom:1px width="50%"><font
						face="Arial, Helvetica, sans-serif" size="2">Health
							Insurance Premium costs</font></td>
					<td style="padding-top: 1px" style=padding-bottom:1px width="25%"><input
						type=text name=input11 value="0.00"
						onBlur="validate(this.form.input11);" size="13" maxlength="13"></td>
					<td style="padding-top: 1px" style=padding-bottom:1px width="25%"><input
						type=text name=input12 value="0.00"
						onBlur="validate(this.form.input12);" size="13" maxlength="13"></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td style="padding-top: 1px" style=padding-bottom:1px width="50%"><font
						face="Arial, Helvetica, sans-serif" size="2">Extraordinary
							expense</font></td>
					<td style="padding-top: 1px" style=padding-bottom:1px width="25%"><input
						type=text name=input13 value="0.00"
						onBlur="validate(this.form.input13);" size="13" maxlength="13"></td>
					<td style="padding-top: 1px" style=padding-bottom:1px width="25%"><input
						type=text name=input14 value="0.00"
						onBlur="validate(this.form.input14);" size="13" maxlength="13"></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
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
							<b> For a blank "Worksheet B" visit:<a
								href="http://www.nccourts.org/Forms/Documents/1233.pdf">
									http://www.nccourts.org/Forms/Documents/1233.pdf</a> <font
								size="0.5"><i>(Cntrl+Click to open)</i> </font>
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
								width="18" height="17" alt="View Cases" border="0"></a>
						</div>
					</td>
					<td>Back to Child Support Guidelines</td>

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
	</div>
</div>


