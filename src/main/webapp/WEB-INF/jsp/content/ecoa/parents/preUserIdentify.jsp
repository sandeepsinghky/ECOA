
<%@ include file="../../../include/taglib.jsp"%>

<script>
	
	function validateform() {
		
		var pattern  = /^[0-9a-zA-Z]+$/;
		

		var userId = document.preNcIdUserInfo.userId.value.trim();

		var pwd = document.preNcIdUserInfo.pwd.value.trim();

		var pwdHint = document.preNcIdUserInfo.pwdHint.value.trim();
		
		if (userId == "") {
			alert("userId should be provided");
			document.getElementById("userId").focus();
			return false;
		} else if(!pattern.test(userId)) {
			alert("old userId contains only characters and letters.");
			document.getElementById("userId").focus();
			return false;
		}
		
		if (!(userId.length >= 5 && userId.length<=10)) {
			alert("userId should be between 5 and 10 characters");
			document.getElementById("userId").focus();
			return false;
		} 
		
		if (!(pwd.length >= 5 && pwd.length<=10)) {
				alert("password should be between 5 and 10 characters");
				document.getElementById("pwd").focus();
				return false;
			} 


		if (pwd == "" && pwdHint =="")
		   {
				alert("Either password or password Hint  must be provided");
				document.getElementById("pwd").focus();
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

		<h4 align="left">Welcome back ${userInfo.getFirstName()}
			${userInfo.getLastName()}</h4>
		<hr />


		<form name="preNcIdUserInfo" id="preNcIdUserInfo" method="post" action="${pageContext.request.contextPath}/cssp/user/preUserIdentifyProcess.htm">
			
			<br>

			<table width="80%" style="align: right;" border="0">

				<tr>
					<td colspan="3"><b><i><font color="#999999">Last step in the linking process.</font></i></b></td>
				</tr>

				<tr>
					<td colspan="3">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3"><font ><b>Customer Identification</b></font></td>
				</tr>

				<tr>
					<td colspan="3"><font face="Arial, Helvetica, sans-serif"
						size="2">Please identify yourself to the eChild Support	System by providing your old login for eChild Support 
						(used to access case or payment information).Do NOT enter your NCID username and password. Provide either your <b>user ID</b> and <b>password</b>
							or your <b>user ID</b> and <b>password hint Question.</b>
					</font></td>

				</tr>

				<tr>
					<td colspan="3">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3"><font face="Arial, Helvetica, sans-serif" color="#FF0000"> </font></td>
				</tr>
				<tr>
					<td colspan="3" style="text-align: left; color: red; font-weight: bold;">${errorMsg}</td>
				</tr>
				<tr>

					<td style="text-align: left;colspan="3"><label>Old user ID: <font color="red">*</font>
					</label>
					<td style="text-align: left;"><input type="text" id="userId"  name="userId"	value="${userId}" size="10" maxlength="10"></td>

				</tr>
				<tr>

					<td style="text-align: left;colspan="3"><label>Old password: </label>
					<td style="text-align: left;"><input type="password" id="pwd" name="pwd" value="${pwd}" size="10" maxlength="10"></td>

				</tr>

				<tr>
					<td colspan="3">&nbsp;</td>
				</tr>

				<tr>

					<td colspan="3"><font face="Arial, Helvetica, sans-serif"><label>Answer password hint question if you forget password. </label></font></td>

				</tr>


				<tr>
					<td style="text-align: left;colspan="1"><label>Password question: </label></td>
					<td style="text-align: left;">${regOldUser.getPswd_qstn()}</td>


				</tr>

				<tr>
					<td style="text-align: left;colspan="3"><label>Answer:
					</label></td>
					<td style="text-align: left;"><input type="text"
						name="pwdHint" id="pwdHint" value="${pwdQnAns}" size="50" maxlength="50"></td>
				</tr>
				<tr>
					<td colspan="3"><font face="Arial, Helvetica, sans-serif"  color="#FF0000">
					*Note: After three failed attempts linking process will be locked. Contact customer service at 1-800-992-9457 to unlock your account.</font></td>
					</tr>
			</table>
			
			<br> <br>

			<div>
				<table width="50%" border="0">
					<tr>
						<td>&nbsp;</td>
						<td align="left"><input type=button value="Cancel"	onclick="goToPreviousPage()" /></td>

						<td>&nbsp;&nbsp;</td>
						<td>&nbsp;</td>
						<td align="left"><input type="submit" name="submit" id="submit" value="Submit" onclick="return validateform();"></td>
							
				</table>
			</div>

		</form>
	</div>
</div>

