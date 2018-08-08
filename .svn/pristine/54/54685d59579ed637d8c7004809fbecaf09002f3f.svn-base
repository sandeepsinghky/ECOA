<%@ include file="../../../include/taglib.jsp"%>
<div id="content">
  <div class="gutter">
		<h4>Support Payment History for ${userInfo.getFirstName()}
			${userInfo.getMiddleName()} ${userInfo.getLastName()} :</h4>
		


		<!-- Name        : ClientChecks.jsp
			   Description : This Program will display the information about four twelve most recent checkpayments, 
			                 if there are any. This screen will display up to the last twelve disbursements including 
							 the disbursement amount, date credited, check mailed date and if the payment was 
							 disbursed through deposit.
			                 Added the logic and description for NCDids Card.  
			                 Also changed the text for Direct deposit and NCKids Card             
			 -->
           <hr>
		<p><b><font
				face="Arial, Helvetica, sans-serif" size="2">Below is a listing of the twelve most recent payments credited
			to your child support case. Payments are listed by date starting with
			the most recent payment.</font></b></p>

   <table width="100%" border="0">
		<tr bgcolor="#0000FF">
				<td height="20" width="70%"><font
				face="Arial, Helvetica, sans-serif" size="2" color="#FFFFFF"><b>Child Support Payment
						Information</b></font></td>
			</tr>
			<tr>
				<td width="70%"></td>
			</tr>

			<c:choose>
				<c:when test="${! empty (checkList)}">

					<c:forEach var="checkBean" items="${checkList}">

						<c:choose>
							<c:when test="${checkBean.isCheckDeposit()}">
								<c:choose>
									<c:when test="${checkDate.equals('')}">

										<c:set var="paymentDesc"
										value="A payment in the amount of $  ${checkBean.getCheckAmount()} was credited to your child support case on ${checkBean.getCheckCreditDateStrPg()}  and deposited to your bank account.Direct Deposits take 2 business days to process." />


									</c:when>
									<c:otherwise>

										<c:set var="paymentDesc"
										value="A payment in the amount of $  ${checkBean.getCheckAmount()} was credited to your child support case on ${checkBean.getCheckDateStrPg()} and deposited to your bank account.Direct Deposits take 2 business days to process." /> 
									
							 </c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>

								<c:choose>
									<c:when test="${checkBean.isDebitCardDeposit()}">

										<c:choose>
											<c:when test="${checkDate.equals('')}">
											    <c:set var="paymentDesc"
												value="A payment in the amount of $ ${checkBean.getCheckAmount()} was credited to your child support case on ${checkBean.getCheckCreditDateStrPg()} and deposited to your NCKids Card. Deposits to NCKids Card take 2 business days to process.." />  
                                       </c:when>
											<c:otherwise>

												<c:set var="paymentDesc"
												value="A payment in the amount of $ ${checkBean.getCheckAmount()} was credited to your child support case on  ${checkBean.getCheckDateStrPg()} and deposited to your NCKids Card.Direct. Deposits to NCKids Card take 2 business days to process." />

											</c:otherwise>
										</c:choose>


									</c:when>
									<c:otherwise>


										<c:choose>
											<c:when test="${checkDate.equals('')}">

												<c:set var="paymentDesc"
												value="A payment in the amount of $ ${checkBean.getCheckAmount()} was credited to your child support case on ${checkBean.getCheckCreditDateStrPg()} .Checks are mailed the next business day." />

											</c:when>
											<c:otherwise>

												<c:set var="paymentDesc"
												value="A payment in the amount of $  ${checkBean.getCheckAmount()}  was credited to your child support case on  ${checkBean.getCheckDateStrPg()} and was mailed on  ${checkBean.getCheckAmount()} was credited to your child support case on ${checkBean.getCheckCreditDateStrPg()} ." />

											</c:otherwise>
										</c:choose>

									</c:otherwise>
								</c:choose>

							</c:otherwise>
						</c:choose>
						<tr>
							<td width="96%">${paymentDesc}</td>
						</tr>
						<tr>
							<td colspan="2"><img
							src="${pageContext.request.contextPath}/images/rule.gif"
							width="60%" height="10"></td>
						</tr>


					</c:forEach>


				</c:when>
				<c:otherwise>

					<tr>
						<td colspan="2">No payments have been credited to your child
							support case in the past 13 months.</td>
					</tr>


				</c:otherwise>

			</c:choose>



		</table>
		<p>&nbsp;</p>
		<table width="70%" border="0">
			<tr>
				<td width="3%">
					<div align="left">
						<a
							href="${pageContext.request.contextPath}/cssp/user/requestPayment.htm">
							<img
							src="${pageContext.request.contextPath}/images/bluesphere.gif"
							width="18" height="17" alt="Check History" border="0">
						</a>
					</div>
				</td>
				<td width="57%"><font face="Arial, Helvetica, sans-serif"
					size="2">Request Payment History for Thirteen (13) months
						via email.</font></td>
			</tr>
			
			<tr>
				<td width="3%">
					<div align="left">
						<a
							href="${pageContext.request.contextPath}/cssp/user/CaseList/caseObligation.htm">
							
							<img
							src="${pageContext.request.contextPath}/images/bluesphere.gif"
							width="18" height="17" alt="Obligation Summary" border="0">
						</a>
					</div>
				</td>
				<td width="97%"><font face="Arial, Helvetica, sans-serif"
					size="2">View Obligation Summary</font></td>
			</tr>
			<tr>
				<td width="3%">
					<div align="left">
						<a href="${pageContext.request.contextPath}/cssp/user/parentWelcome.htm"><img src="${pageContext.request.contextPath}/images/bluesphere.gif"
							width="18" height="17" alt="Main Menu" border="0"></a>
					</div>
				</td>
				<td width="97%">Return to Main Menu</td>
			</tr>
			
		</table>
		<p>&nbsp;</p>
	</div>
</div>

