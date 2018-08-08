
<%@ include file="../../../include/taglib.jsp"%>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script>
	$(function() {
		$("#dob").datepicker();
	});

	function validateform() {
		var result = true;
		var pattern = /^\d{3}-?\d{2}-?\d{4}$/;
		var date_regex = /^(0[1-9]|1[0-2])\/(0[1-9]|1\d|2\d|3[01])\/(19|20)\d{2}$/;

		var mpi = document.userIdentifyInfo.mpi.value.trim();

		var ssn = document.userIdentifyInfo.ssn.value.trim();

		var dob = document.userIdentifyInfo.dob.value.trim();

		if (mpi == "" && ssn == "") {
			alert("Either MPI or SSN must be provided");
			document.userIdentifyInfo.mpi.focus();
			return false;
		}

		if (mpi.trim() != "" && isNaN(mpi)) {
			alert("Please enter all numbers for MPI");
			document.userIdentifyInfo.mpi.focus();
			return false;
		} else if (mpi.trim() != "" && mpi.length != 10) {
			alert("MPI should be 10 digits long");
			document.userIdentifyInfo.mpi.focus();
			return false;
		}

		if (ssn != "" && !pattern.test(ssn)) {
			alert("ssn should be like xxx-xx-xxxx.");
			document.userIdentifyInfo.ssn.focus();
			return false;
		}

		if (!(date_regex.test(dob)))

		{
			alert("Please enter date in mm/dd/yyyy format");
			document.userIdentifyInfo.dob.focus();
			return false;
		}

		return true;
	}

	function goToPreviousPage() {

		location.href = "${pageContext.request.contextPath}/cssp/user/userWelcome.htm";
	}
</script>



<div id="content">
	<div class="gutter">
	
	   <c:if test="${!empty errMsg}">
					<div class="error message-box">${errMsg}</div>
				</c:if>

		<h4 align="left">Welcome back ${userInfo.getFirstName()}
			${userInfo.getLastName()}</h4>
		<hr />


		<form name="userIdentifyInfo" id="userIdentifyInfo" method="post"
			action="${pageContext.request.contextPath}/cssp/user/userIdentifyProcess.htm">



			<table width="90%" style="align: right;" border="0">

				<tr>
					<td colspan="3"><b><i><font color="#999999">It's easy to link with 1 or 2 simple steps.</font></i></b></td>
				</tr>

				<tr>
					<td colspan="3">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3"><font size="2"><b>Customer
								Identification</b></font></td>
				</tr>
				<tr>
					<td colspan="3">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3" size="1"><font	face="Arial, Helvetica, sans-serif" >Please
							identify yourself to the eChild Support System by providing
							either your <b>MPI</b> <b>Number</b> and <b>date of birth</b> or
							your <b>social security number</b> and <b>date of birth</b>
					</font></td>

				</tr>

				<tr>
					<td colspan="3">&nbsp;</td>
				</tr>

				<tr>
					<td colspan="3"; style="text-align: left;color: red; font-weight: bold;">${errorMsg}</td>
				</tr>

				<tr>
					<td colspan="3">&nbsp;</td>
				</tr>

				<tr>

					<td style="text-align: left;colspan="3"><label><b>Date
								Of Birth: </b><font color="red">*</font></label>
					<td style="text-align: left;"><input type="text" name="dob"
						id="dob" value="${dob}" size="11" maxlength="11"><font size="2">&nbsp;(mm/dd/yyyy)</font></td>

				</tr>

				<tr>

					<td style="text-align: left;colspan="3"><label><b>MPI
								#: </b></label>
					<td style="text-align: left;"><input type="text" name="mpi"
						value="${mpi}" size="10" maxlength="10">&nbsp;&nbsp;<font
						size="2">(Master Participant Index)</font></td>

				</tr>
				<tr>

					<td style="text-align: left;colspan="3"><label><b>Social
								Security #: </b></label>
					<td style="text-align: left;"><input type="text" name="ssn"
						value="${ssn}" size="11" maxlength="11"><font size="1"><font
							size="2">&nbsp;(xxx-xx-xxxx or xxxxxxxxx)</font></td>

				</tr>



			</table>

			<br> <br>

			<div>
				<table width="50%" border="0">
					<tr>
						<td>&nbsp;</td>
						<td align="left"><input type=button value="Cancel"
							onclick="goToPreviousPage()" /></td>

						<td>&nbsp;&nbsp;</td>
						<td>&nbsp;</td>
						<td align="left"><input type="submit" name="submit" id="save"
							value="Submit" onclick="return validateform();"></td>
					</tr>
					
					<tr>
						<td>&nbsp;</td>

					<tr>
						<td colspan="3"><font face="Arial, Helvetica, sans-serif" color="#FF0000">*Note: After three failed attempts the linking process will be locked. Contact customer service at 1-800-992-9457 to unlock your account.</font></td>
					</tr>
				</table>
			</div>

		</form>
	</div>
</div>

