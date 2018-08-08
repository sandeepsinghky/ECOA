<%@ include file="../include/taglib.jsp"%>


<div id="content">
	<div class="gutter">
		<h2>Login Help Information</h2>
		
		<B><u>Your first visit? </u></B>
		<ul>
			<!-- <li>Register your account in NCID. Register here <a target="_blank" href=https://idpdev.nc.gov:8443/nidp/idff/sso?/> DEV NCID </a></li>	 -->	
			<li>Register for your NCID. Register here <a target="_blank" href=https://ncid.nc.gov/>NCID</a></li>					
		</ul>		
		<B><u>Your account already registered?</u></B>
		<ul>
			<li><strong><a href="${pageContext.request.contextPath}/loginnp.htm?u=pNcId">Login with NCID</a></strong></li>						
		</ul>
		<B><u>Forgot your username/password?</u></B>
		<ul>
			<!-- <li>Visit <a target="_blank" href=https://idpdev.nc.gov:8443/nidp/idff/sso?/> DEV NCID </a> to reset your credentials.</li>	 -->
			<li>Visit <a target="_blank" href=https://ncid.nc.gov/>NCID</a> to reset your username and password.</li>						
		</ul>
		<B><u>Having trouble registering for NCID? </u></B>
		<ul>
			<li>Your first and last name are required.</li>
			<li>When entering your email address and conforming your email address, the addresses must be the same.</li>			
			<li>Your password must be at least 8 characters and meet the stated requirements. Password, user ID and password hint answers are all case sensitive. Make sure your CAPS LOCK key is set correctly.</li>						
		</ul>
		<B><u>Did your login attempt fail? </u></B>
		<ul>
			<li><p>If you have tried all the steps above and your login still fails, contact our Customer Service Center at 1-800-992-9457.</p></li>						
		</ul>
		<B><u>Didn't receive an email confirming your registration? </u></B>
		<ul>
			<li><p>Login and check the e-mail address you provided when registering your account. We cannot contact you without a valid e-mail address.</p></li> 
			<li><p>Check your Junk Email folder to verify message was not marked as spam.</p></li>						
		</ul>
		<B><u>Having trouble linking your case to your NCID account? </u></B>
		<ul>
			<li>Did you use the correct date of birth? Format for DOB must be mm/dd/yyyy.</li>
			<li>Did you enter the correct social security number?</li>
			<li>Are you using your MPI (Master Participant Index)number to link your account? The MPI cannot be greater than 10 digits.</li>
			<li>If you have previously used this website to view case details, you will need your old user ID and password to link your existing case with your NCID account.</li>			
			<li>Verify the answer to your password hint question.</li>						
		</ul>
			
				
	</div>
</div>

