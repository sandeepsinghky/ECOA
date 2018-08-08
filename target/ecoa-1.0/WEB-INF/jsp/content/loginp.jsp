<%@ include file="../include/taglib.jsp"%>
<script>
{
	function valLogin() {
		
		 var pattern = /^[0-9a-zA-Z]+$/;

		var userId = document.preNcIdUserLogin.j_username.value.trim();

		var pwd = document.preNcIdUserLogin.j_password.value.trim();

		if (document.getElementById("j_username").value.trim() == "" && document.getElementById("j_password").value.trim() == "") {
				
			alert("Login name and Password are required fields.");
			document.getElementById("j_username").focus();
			return false;
		}
		if (document.getElementById("j_username").value.trim() == "") {
			alert("Login name is required.");
			document.getElementById("j_username").focus();
			return false;
		}
		if (document.getElementById("j_password").value.trim() == "") {
			alert("Password is required.");
			document.getElementById("j_password").focus();
			return false;
		}

		if (!pattern.test(userId)) {
			alert("old userId contains only characters and letters.");
			document.getElementById("j_username").focus();
			return false;
		}

		if (!(userId.length >= 5 && userId.length <= 10)) {
			alert("userId should be between 5 and 10 characters");
			document.getElementById("userId").focus();
			return false;
		}

		if (!(pwd.length >= 5 && pwd.length <= 10)) {
			alert("password should be between 5 and 10 characters");
			document.getElementById("j_password").focus();
			return false;
		} 
		return true;
	}
  }
 </script>

<div id="content">
	<div class="gutter">
		
		<h2>NORTH CAROLINA CHILD SUPPORT SERVICES: ONLINE ACCESS</h2>		
				
		 <div id="loginDiv" style="display: none">
			<h2>ERROR</h2>
			<table>
				<tr> <td class=legend id="errMsgDiv"></td>	</tr>
				<tr> <td class=center><br /> <input type=button value='Close' onclick="hideLightbox()" /> </tr>
			</table>
		</div> 
		
		<div class="centered">
			<c:if test="${not empty param.login_error}">
                     
                          <font color="red"> Your login attempt was not successful<br/>
 
                           ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</font>
			</c:if>

			<c:if test="${!empty param.success}">
				<div class="success message-box">${param.success}</div>
				<br />
			</c:if>			
			<br>
		   <dl class="feature">
				<form name ="preNcIdUserLogin" id= "preNcIdUserLogin" action="<c:url value='/j_spring_security_check'/>" method="post">
					<table>
							<tr>
								<td><label for="login" class="required">Old Login Name:</label>		</td>
								<td><input type="text" name="j_username" maxlength="10"	id="j_username" value="" title="Please enter your Login Name" /></td>
							</tr>
							<tr>
								<td><label for="password" class="required">Old Password:</label></td>
								<td><input type="password" name="j_password" maxlength="10"	id="j_password" value="" title="Please enter your Password" /></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td align="center"><input name="loginbtn" type="submit"	value="Login" onclick="return valLogin()"/></td>
							</tr>
					</table>
					<!-- <p align="center" style="color:red">Contact us at 1-800-992-9457 if you have issues logging in <u>OR</u> retry login after 4 hours.</p> 				-->
					<p align="center" style="color:red"> Contact customer service at 1-800-992-9457 if you have any issues with the website. </p>
							
				</form>
			</dl>				
			<br>			
				<!-- <p style="color:red"> -->
				<p><strong>Note: You are allowed to use your old credentials to login temporarily. They will expire in 6 months or in 10 logins whichever comes first.</strong> </p>
				<!-- <p>Please set up your NCID at: <a target="_blank" href=https://ncid.nc.gov /> NCID </a> before your old credentials expire. </p> -->
				<p>Please set up your NCID before your old credentials expire. </p>				
			<br>			 		  
		</div>		
		<hr>	
	</div>
</div>

