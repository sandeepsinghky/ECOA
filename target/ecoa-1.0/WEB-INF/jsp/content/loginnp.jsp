<%@ include file="../include/taglib.jsp"%>
<script>
{
	$(document).ready(function(){initLightbox();});
	
	function noEnterKey(evt) {
      var evt = (evt) ? evt : ((event) ? event : null);
      if (evt.keyCode == 13)  {
          return false;
      }
    }
	document.onkeypress = noEnterKey;
	
	function valLogin()
	{	  
		if(document.getElementById("j_username").value.trim()=="" && document.getElementById("j_password").value.trim()=="") 
	    {
	         alert("Login name and Password are required fields.");
	         document.getElementById("j_username").focus();
	         return false;
	    }
		else if(document.getElementById("j_username").value.trim()=="") 
	    {
	         alert("Login name is required.");
	         document.getElementById("j_username").focus();
	         return false;
	    }
		else if(document.getElementById("j_password").value.trim()=="") 
	   	{
		   	 alert("Password is required.");
	         document.getElementById("j_password").focus();
	         return false;
	   	}
	   	return true;	  	
	}		
  }
 </script>

<div id="content">
	<div class="gutter">
		
		<h2>North Carolina Child Support Services: Custodial Parents/Guardians Sign In Process</h2>		
		
		<p>Please enter NCID credentials below to login. </p>	
		
		<div id="loginDiv" style="display: none">
			<h2>ERROR</h2>
			<table>
				<tr> <td class=legend id="errMsgDiv"></td>	</tr>
				<tr> <td class=center><br /> <input type=button value='Close' onclick="hideLightbox()" />	</tr>
			</table>
		</div>
		
		<div class="centered">

			<c:if test='${not empty param.login_error}'>
				<font color="red"> Your login attempt was not successful, please try again.<br /> <br /> Reason: ${param.message} </font>
			</c:if>

			<c:if test="${!empty param.success}">
				<div class="success message-box">${param.success}</div>
				<br />
			</c:if>
			
			<c:if test="${!empty errMsg}">
			<div class="error message-box">${errMsg}</div>
		</c:if>
			<br>
		   <dl class="feature">				
			 <form action="<c:url value='/j_spring_security_check'/>"method="post">		
					<table>
						<tr>
							<td><label for="login" class="required">Login Name:</label>	</td>
							<td><input type="text" name="j_username" maxlength="20"	id="j_username" value="" title="Please enter your Login Name" /></td>
						</tr>
						<tr>
							<td><label for="password" class="required">Password: </label></td>
							<td><input type="password" name="j_password" maxlength="20"	id="j_password" value="" title="Please enter your Password" /></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td align="center"><input name="loginbtn" type="submit"	value="Login" onclick="return valLogin()"/> </td>							
						</tr>						
					</table>
					 <!-- <p align="center" style="color:red">Contact us at 1-800-992-9457 if you have issues logging in <u>OR</u> retry login after 4 hours.</p> 		-->
					  <p align="center" style="color:red">Contact customer service at 1-800-992-9457 if you have any issues with the website.</p>
					 
			  </form>						  
			</dl>			  
		</div>
		
		<!--<p align="center">Forgot username/password? Visit <a target="_blank" href=https://idpdev.nc.gov:8443/nidp/idff/sso?/> DEV NCID </a> to reset your credentials and return to this page to login and continue the application process.</p> -->
		
		<p align="center">Forgot username/password? Visit <a target="_blank" href=https://ncid.nc.gov/> NCID </a> to reset your credentials and return to this page to login.</p>
	
		<p align="center"> Note: You can now use online application submission service to apply for new child support cases electronically. </p>
		
	</div>
</div>

