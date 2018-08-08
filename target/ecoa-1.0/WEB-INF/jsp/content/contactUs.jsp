<%@ include file="../include/taglib.jsp"%>


<div id="content">
	<div class="gutter">
		<h2>Child Support Services Contact Information</h2>
		
		<B><u> For Child Support Case Information</u></B>
			<ul>
				<li><b>Customer Service Center: </b></li>
				<br>
					<ul>
					   <li>
						   	By Phone:
							<b>Toll-free 1-800-992-9457</b>	Hours: 7:30am to 5:30pm
						</li>
						<br>
						<li>
							By Mail:
							<b>Child Support Enforcement
							PO Box 20800
							Raleigh, NC 27619-0800</b>
						</li>
					</ul>
				<br>
				<li><b>Make or Schedule a Payment Contact: </b></li>
				<br>
				
				<ul>
					<li>
						By Mail:
						<b>NCCSCCO
						PO Box 900006
						Raleigh, NC 27675-9020</b>
					</li>
					<br>
					<!-- <li>	
						By Phone:
						Toll-free <b>1-877-361-5437</b>
						Fax        <b>1-919-787-6055</b>
					</li> -->
				</ul>
				
				<br>
				<li><b>Questions or Inquiries:</b> </li>			    
				<br>
				<ul>
					<li>
						Child Support Case Information:
						<sec:authorize var="loggedIn" access="isAuthenticated()" />
						<c:choose>
						<c:when test="${loggedIn}">
						<a href="${pageContext.request.contextPath}/feedBack/2.htm"><b> Contact us</b></a>
						</c:when> 
						<c:otherwise>
						<a href="${pageContext.request.contextPath}/feedBack/1.htm"><b> Contact us</b></a>
						</c:otherwise>
						</c:choose>
					</li>
					<br>
					<li>	
						Bank Draft Questions:
						<b>Toll-free 1-877-361-5437</b>
					</li>
					<br>
					<li>To submit a credit card payment visit: <a href= "https://nc.smartchildsupport.com">nc.smartchildsupport.com</a></li>
				</ul>			    
				<br>				
			</ul>
			
			<B><u>Anonymous Parent Tip Hotline </u></B>
			
			<p>Do you have information regarding an individual who may owe child support? </p>
			<ul>
				<li>Tip Line: Toll-free <b>1-855-449-8741</b> </li>
			
			</ul>
			
			<p>The more information you can provide about the identity and whereabouts of the individual increases the probability that child support payments may be established and enforced.</p>
			<b>If known, please provide individual's:</b>
			<ul>
				<li>Name</li>
				<li>Date of birth</li>
				<li>Address (location and directions)</li>
				<li>Cell phone (contact number)</li>
				<li>Employer</li>
				<li>Last 4 digits of their social security number</li>
				<li>Recent picture</li>
				<li>The name of who the child support is due to</li>			
				<br>	
				Thank you for your assistance.			
			</ul>
			
				
	</div>
</div>

