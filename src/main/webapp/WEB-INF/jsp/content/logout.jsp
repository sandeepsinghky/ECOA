<%@ include file="../include/taglib.jsp"%>
<script>
{


$(document).ready(function(){initLightbox();});
    function valLogin()
    {	
    	$("input").removeClass("errorInput");
    	var errMsg="";    
        if(document.getElementById("j_username").value=="") 
        {
         errMsg += "-User Id is required.";
         $("#j_username").addClass("errorInput");
        }
        if(document.getElementById("j_password").value=="") 
        {
         errMsg += "<br>-Password is required.";
         $("#j_password").addClass("errorInput");
        }
        if(errMsg != "") 
        {
         showLightbox('loginDiv',200,120,100,200);
         document.getElementById("errMsgDiv").innerHTML=errMsg;
        } 
        else 
        {
         location.href="apl-rr.html";
        }
    }
	
  }
 </script>

<div id="content">
	<div class="gutter">
		<h2>Online CSS Application:Registration/Sign In Process</h2>
		<p>Welcome to the online Child Support Application Submission
			process. Before continuing with the application
			registration/submission please review security and privacy of
			information below carefully.</p>
		<p>There may be a non-refundable application fee which is required
			to process your application. If you any questions about the
			application, please contact customer service.</p>

		<div id="loginDiv" style="display: none">
			<h2>ERROR</h2>
			<table>
				<tr>
					<td class=legend id="errMsgDiv"></td>
				</tr>
				<tr>
					<td class=center><br /> <input type=button value='Close'
						onclick="hideLightbox()" />
				</tr>
			</table>
		</div>
		<hr>
		<div class="centered">

			<c:if test='${not empty param.login_error}'>
				<font color="red"> Your login attempt was not successful, try
					again.<br /> <br /> Reason: Bad Credentials
				</font>
			</c:if>

			<c:if test="${!empty param.success}">
				<div class="success message-box">${param.success}</div>
				<br />
			</c:if>

			<form action="<c:url value='/j_spring_security_check'/>"	method="post">
				<fieldset class="">
					<table>
						<tr>
							<td><label for="login" class="required">Login Name:</label>
							</td>
							<td><input type="text" name="j_username" maxlength="20"
								id="login" value="" title="Please enter your Login Name" /></td>

						</tr>
						<tr>
							<td><label for="password" class="required">Password:
							</label></td>
							<td><input type="password" name="j_password" maxlength="20"
								id="password" value="" title="Please enter your Password" /></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td align="center"><input name="loginbtn" type="submit"
								value="Login" /></td>
						</tr>
					</table>
				</fieldset>
			</form>
		</div>
		<hr>
		<p align="center">If you have already created your user account
			please use your credentials to login above.</p>
		<p align="center">
			If you are a new user or if you have forgot/reset your password,
			please visit <a href=https://ncid.nc.gov /> NCID </a> and come back to
			this page to login and continue the application process.
		</p>
	</div>
</div>

