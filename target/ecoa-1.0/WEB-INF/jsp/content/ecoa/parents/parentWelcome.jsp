
<%@ include file="../../../include/taglib.jsp"%>
<script>
	function setView() {

		var selectRole = document.welcomeParent.selectedRole;

		if (selectRole[0].checked == true) {
			if (confirm("You have selected to view your Information as a Custodial Parent")) {
				document.welcomeParent.submit();
			}
		} else if (selectRole[1].checked == true) {
			if (confirm("You have selected to view your Information as a Non-Custodial Parent")) {
				document.welcomeParent.submit();
			}
		}
	}
</script>


<div id="content">
	<div class="gutter">

      <c:if test="${!empty regSuccess}">
					<div class="success message-box">Registration process is successful!!</div>
				</c:if>

		<h4 align="left">Welcome back ${userInfo.getFirstName()}
			${userInfo.getLastName()}</h4>
		<hr />
		<h5 align="left">${userInfo.getLastLoginDate()}</h5>

		<form name="welcomeParent" id="welcomeParent" method="post"
			action="${pageContext.request.contextPath}/cssp/user/processDualRole.htm">

			<br> <br> <br> <br>

			<table width="70%" style="align: right;" border="0">



				<c:if test="${userInfo.isDualRole()}">
					<tr>

						<td style="text-align: left; color: red; font-weight: bold;">${errorMsg}</td>
					</tr>

					<tr>

						<td style="text-align: left; font-weight: bold;">Do you want
							information as the</td>
					</tr>
					<tr>
						<td style="text-align: left; color: Brown; font-weight: bold;"><input
							type="radio" name="selectedRole" id="selectedRole" value="CLI"
							${userInfo.getCaseRelshp().equals('CLI') ? 'checked' : ''}
							onclick="setView();">Custodial Parent?</td>



					</tr>

					<tr>
						<td style="text-align: left; color: Brown; font-weight: bold;"><input
							type="radio" name="selectedRole" id="selectedRole" value="AP"
							${userInfo.getCaseRelshp().equals('AP') ? 'checked' : ''}
							onclick="setView();">Non-Custodial Parent?</td>


					</tr>

				</c:if>
			</table>
			<br> <br> <br> <br> <br>

			<table width="60%" style="align: left;" border="0">

				<tr>
					<td colspan="6"><hr />
				</tr>
				<tr>
					<td colspan="6" style="font-weight: bold; text-align: left;">Account
						Information/Reminders</td>
				<tr>
					<td colspan="6">
				</tr>


				<c:if test="${userInfo.isScheduled()}">
					<tr>
						<td width="3%" valign="top" height="30"><font color="#00CC33"><img
									src="${pageContext.request.contextPath}/images/greenbox.gif"
									width="11" height="16" align="absbottom"></font></td>
						<td width="43%" height="30" valign="top"><font
								color="#00CC33">You have upcoming appointments/hearings.
									Click Appointments/Hearings to view</font></td>
					</tr>
				</c:if>
				<tr>
					<c:if test="${!userInfo.isMailPresent()}">
						<td width="3%" valign="top" height="30"><font color="#FF6633"><img
									src="${pageContext.request.contextPath}/images/orangebox.gif"
									width="11" height="16" align="absbottom"></font></td>
						<td width="43%" height="30" valign="top"><font
								color="#FF6633">Your address is unknown. Please contact
									your local child support office or clerk of court office.</font></td>
				</tr>

				</c:if>

				<c:if
					test="${!userInfo.isEmployed() && userInfo.getCaseRelshp().equals('AP')}">
					<tr>
						<td width="3%" valign="top" height="30"><font color="#663399"><img
									src="${pageContext.request.contextPath}/images/purplebox.gif"
									width="11" height="16" align="absbottom"></font></td>
						<td width="43%" height="30" valign="top"><font
								color="#663399">Your employment is unknown. Please
									contact your local child support office or clerk of court
									office.</font><</td>
					</tr>

				</c:if>
			</table>
	


</div>
</div>

