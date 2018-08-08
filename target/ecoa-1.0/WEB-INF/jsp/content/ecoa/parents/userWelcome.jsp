
<%@ include file="../../../include/taglib.jsp"%>

<div id="content">
	<div class="gutter">
	    <c:if test="${!empty regFail}">
					<div class="error message-box"> ${regFail} </div>
				</c:if>
 
		<h4 align="left">Welcome back ${userInfo.getWebFirstName()}
			${userInfo.getWebLastName()}</h4>
		<hr/>
		<h5 align="left">${userInfo.getLastLoginDate()}</h5>
		
		<h7 style ="align=left; color: red;">${errorMsg}</h7>

		<c:choose>
			<c:when test = "${userInfo.isRegLock()}">
				<div>
				<!--  <p>Registration process has been locked for you, as you have failed max 3 attempts to provide valid information to complete registration process.</p>
				 --> 
				<!--  <p>Please contact your local Child Support Enforcement Agency or Clerk of Court to get helped with registration process.</p>
				 --> 
				<!--  <p align="center" style="color:red">Please contact us at 1-800-992-9457 to get help with your registration process.</p> -->
				<p style="color:red"> Linking process has been locked due to 3 failed login attempts.</p>
				<p> Your account will be unlocked automatically in 4 hours.
					<br>Contact customer service at <b> 1-800-992-9457</b> to get help with the linking process.</p>
				
				</div>

			</c:when>
			<c:otherwise>
				<div>
					<p> The process of linking your NCID account to your child support case number must be completed to check case detail information.</p>					
					<h3>Parents</h3>					
					<p>Existing customers who have previously used this website to check case and payment information need to first complete the linking procedure to view case details. You will need your old Customer ID and password. This procedure is required due to the migration of customer login accounts to NCID. </p>
					<p>The linking procedure is required if you have an existing case and this is your first time using this website to view your case details. </p>
					
					<h3>Applicants</h3>
					<p>First time customers who have created an online application will need to complete the linking procedure after their local child support office has processed their application.  NOTE: Registration cannot be completed prior to your application being processed.</p>
				</div>

				<div>
					<p>
						<b>Link your NCID and child support case here</b> <strong><a href="${pageContext.request.contextPath}/cssp/user/userIdentifyInfo.htm">Link Now</a></strong>
				</div>

			</c:otherwise>
		</c:choose>

</div>
</div>

