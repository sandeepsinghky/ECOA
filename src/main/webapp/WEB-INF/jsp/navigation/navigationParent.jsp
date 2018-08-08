<%@ include file="../include/taglib.jsp"%>

<div id="sidebar" class="gutter">
	<div class="gutter">

		<div class="box">
			<h3>Print Direct Deposit Authorization Form</h3>
			<ul>
				<li><a target="_blank" href="https://www2.ncdhhs.gov/info/olm/forms/dss/DSS-4718-ia.pdf">English</a></li>
				<br>
				<li><a target="_blank" href="https://www2.ncdhhs.gov/info/olm/forms/dss/dss_spanish/DSS-4718sp.pdf">Spanish</a></li>
			</ul>
		</div>

		<div class="box">
			<h3>Services</h3>
			<ul>
				<sec:authorize access="isAnonymous()">
					<li><a	href="${pageContext.request.contextPath}/parentsIndex.jsp">Parents/Guardians Login</a></li>
					<br>
				</sec:authorize>
				
				<sec:authorize access="isAuthenticated()">
					<li><a href="${pageContext.request.contextPath}/cssp/user/userProfile.htm">Customer Profile</a></li>
					<br>
				</sec:authorize>

				<c:if test="${userInfo.getUserType().equals('ncId')}">

					<li><a	href="${pageContext.request.contextPath}/caseApplicationInfo/2.htm">Apply Online for Child Support Services</a></li>
					<br>
				</c:if>

				<li><a target="_blank" href="https://nc.smartchildsupport.com/">Pay	Child Support</a></li>
				
				<br>
				
				<sec:authorize access="isAuthenticated()">
					<li><a href="${pageContext.request.contextPath}/cssp/user/caseDetail.htm">Case Information</a></li>
					<br>
				</sec:authorize>

				<sec:authorize access="isAuthenticated()">
					<li><a href="${pageContext.request.contextPath}/cssp/user/paymentHistory.htm">Payment Information</a></li>
					<br>
				</sec:authorize>

				<sec:authorize access="isAuthenticated()">
					<li><a href="${pageContext.request.contextPath}/cssp/user/caseSchedule.htm">Appointments/Hearings</a></li>
					<br>
				</sec:authorize>

				<sec:authorize access="isAuthenticated()">
					<li><a href="${pageContext.request.contextPath}/cssp/user/caseObligation.htm">Obligation Summary</a></li>
					<br>
				</sec:authorize>					
					<li><a href="${pageContext.request.contextPath}/clerkOfCourtSearch/1.htm">Clerk of Court Search</a></li>
					<br>					
					<li><a href="${pageContext.request.contextPath}/cseOfficeSearch/1.htm">CSE Office Search</a></li>
					<br>				
				<li><a href="${pageContext.request.contextPath}/cseGuideLines.htm">CSE	Guidelines</a></li>
			</ul>
		</div>

		<div class="box">
			<h4>Help/Information</h4>			
			<ul>
				<li><a href="${pageContext.request.contextPath}/contactUs/2.htm">Contact Us</a></li>
				<br>
				<li><a target="_blank" href="${pageContext.request.contextPath}/progFeesAndPolicies/2.htm">Program Fees & Policies</a></li>
				<br>
				<li><a target="_blank" href="${pageContext.request.contextPath}/privacyPolicy/2.htm">Privacy and Security</a></li>
				<br>
				<sec:authorize var="loggedIn" access="isAuthenticated()" />
				<c:choose>
				 <c:when test="${loggedIn}">						
					<c:choose>
						<c:when test="${userInfo.getUserType().equals('ncId') || userInfo.getUserType().equals('pncId')}">					
							<li><a target="_blank" href="${pageContext.request.contextPath}/caseFinancialHelp/2.htm">Help Case/Financial</a></li>
						</c:when>										
					</c:choose>
				</c:when>	
				<c:otherwise>
						  <li><a target="_blank" href="${pageContext.request.contextPath}/loginHelp/2.htm">Login Help</a></li>
				</c:otherwise>	
				</c:choose>			
				<br>				
				<li><a target="_blank" href="https://nc.smartchildsupport.com/">Making A Payment</a></li>
				<br>
				<li><a target="_blank" href="${pageContext.request.contextPath}/reports/DebitCardFAQ.pdf">NCKidscard FAQ</a></li>	
			</ul>
		</div>
	</div>
</div>


