<%@ include file="../../../include/taglib.jsp"%>
<!--  Name        : NcpPayments.jsp
   Description : This program will display the information about twelve most recent payments, if there are any.
                 This program will display up to the last twelve payments made, the payment amount, the name of 
				 the custodial parent and up to five cases to which a payment was allocated and the payment 
				 amount per payee.    
				 Modified   "No payments have been credited to your account 
				                                     in the past 13 months."  
				                           To        "No payments have been received from you  
                                                      in the past 13 months." 
                                         Modified  "was credited to the account of" 
                                           To      "was credited to the child support case of"              
           
 -->

<div id="content">
  <div class="gutter">
		<h4>Support Payment History for ${userInfo.getFirstName()}	${userInfo.getMiddleName()} ${userInfo.getLastName()} :</h4>
		<hr>
      <!-- <p>
		 <b><font
				face="Arial, Helvetica, sans-serif" size="2">Below is a listing of the twelve most recent payments received from you. Payments are listed by date starting with the most latest payment.</font></b>
		  </p>
 	-->
 	<p>Below is a listing of the twelve most recent payments received from you. Payments are listed by date starting with the most latest payment.</p>
 
		
		<table width="90%" border="0">
			<!-- <tr bgcolor="#0000FF"> -->
			<tr>
				<!-- <td colspan="2" height="20"><b><font face="Arial, Helvetica, sans-serif" color="#FFFFFF" size="2">Description</font></b></td> -->
				<td colspan="2" height="20"><b>Description</td>
			</tr>
			<c:choose>
				<c:when test="${! empty (paymentList)}">
					<c:forEach var="pb" items="${paymentList}">
						<tr>
							<td colspan="2">Your $ ${pb.getAmtApplied()} payment was
								received on ${pb.getTranDate()}.</td>
						</tr>
						<c:if test="${!pb.isApplied()}">

							<c:choose>
								<c:when test="${pb.getAmtRemaining().equals('')}">
									<tr>
										<td width="7%"></td>
										<td width="93%">Currently this payment has not been applied.</td>
									</tr>
								</c:when>
								<c:otherwise>
									<tr>
										<td width="7%"></td>
										<td width="93%">$ ${pb.getAmtRemaining()} - Currently this payment has not been applied.</td>
  								    </tr>
								</c:otherwise>
							</c:choose>
						</c:if>                                      
						 <c:forEach var="j" begin="0" end="${fn:length(pb.getPayeeMpi())-1}">
							<c:if test="${!pb.getPayeeName(j).equals('')}">
								<tr>
									<td width="7%"></td>
									<td width="93%">$ ${pb.getAmtDisbursed(j)} was credited to the child support case of ${pb.getPayeeName(j)}</td>
								</tr>
							</c:if>
						</c:forEach> 
						<tr>
							<td colspan="2"><img src="${pageContext.request.contextPath}/images/rule.gif" width="60%" height="10"></td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="2">No payments have been received from you in the past 13 months.</td>
					</tr>
				</c:otherwise>
			</c:choose>

		</table>
		<p>&nbsp;</p>
		<table width="80%" border="0">
			<tr>
				<td width="3%">
					<div align="left">
						<a href="${pageContext.request.contextPath}/cssp/user/requestPayment.htm">
							<img src="${pageContext.request.contextPath}/images/bluesphere.gif" width="18" height="17" alt="Payment History" border="0">
						</a>
					</div>
				</td>
				<td width="97%">Request Payment History for Thirteen (13) months via email.</td>
			</tr>
			<tr>
				<td width="3%">
					<div align="left">
						<a href="${pageContext.request.contextPath}/cssp/user/requestCoupon.htm">
							<img src="${pageContext.request.contextPath}/images/bluesphere.gif"	width="18" height="17" alt="Payment Coupon" border="0">
						</a>
					</div>
				</td>
				<td width="97%">Request Payment Coupon via regular mail</td>
			</tr>
			<tr>
				<td width="3%">
					<div align="left">
						<a href="${pageContext.request.contextPath}/cssp/user/CaseList/caseObligation.htm">
							<img src="${pageContext.request.contextPath}/images/bluesphere.gif"	width="18" height="17" alt="Obligation Summary" border="0">
						</a>
					</div>
				</td>
				<td width="97%">View Obligation Summary</td>
			</tr>
			<tr>
				<td width="3%">
					<div align="left">
						<a href="${pageContext.request.contextPath}/cssp/user/parentWelcome.htm">
						<img src="${pageContext.request.contextPath}/images/bluesphere.gif" width="18" height="17" alt="Main Menu" border="0"></a>
					</div>
				</td>

				<td width="97%">Return to Main Menu</td>
			</tr>
		</table>

	</div>
</div>


