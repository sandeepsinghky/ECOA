<%@ include file="../../../include/taglib.jsp"%>
<div id="content">
	<div class="gutter">

		<%
			/**
			   Name        : CaseDetail.jsp
			   Description : This Program will allow the user to view information about their child support account. 
			     The information presented displays the participant's Case Number, Case Type, Case Processing status,
							 number of children on the case and the order status. It alos displays the case worker information and address.
						 			 
			   *-----*--------*---------*--------------------------------------* 
			   * * Program is fixed to display "Map it" *                 
			    *                   Button only if address line 1 and    *  
			   *     *        *         * City has values in the field.        * 
			   *-----*--------*---------*--------------------------------------*          
			**/
		%>

		<c:if test="${!empty errMsg}">
			<div class="error message-box">${errMsg}</div>
		</c:if>

		<c:if test="${empty errMsg}">
			<table width="100%" border="0">
				<tr>
					<td colspan="2"><b>Case Summary for	&lt;${caseBean.getIvdCase()}&gt; ${userInfo.getFirstName()}
							${userInfo.getLastName()} vs ${caseBean.getOtherName()} </b><br>
							<hr>
						<%-- <img src="${pageContext.request.contextPath}/images/rule.gif"
						width="100%" height="10"></td> --%>
				</tr>

				<tr>
					<jsp:useBean id="date" class="java.util.Date" />
					<td colspan="2">As of <fmt:formatDate value="${date}"
							type="date" pattern="dd-MMM-yyyy" />, below is a brief summary
						of the case you have selected.
					</td>
				</tr>
			</table>
			<br>
			<table width="100%" border="0">
				
				<tr>
					<td width="40%"><b>Case Number:</b></td>

					<td colspan="3">${caseBean.getIvdCase()}</td>
					<td width="6%"></td>
				</tr>
				<tr>
					<td width="40%"><b>Case Processing Status:</b></td>


					<td colspan="5">As of today your case processing status is
						${caseBean.getPrcsStatDesc()}</td>
				</tr>
				<tr>
					<td width="40%"><b>Number of Children on Case:</b></td>

					<td colspan="3">${caseBean.getChildCount()}</td>
					<td width="6%"></td>
				</tr>
				<tr>
					<td width="40%"><b>Order Status:</b></td>
					<td colspan="5">Case is ${caseBean.isUnderOrder() eq false ? 'not' : ""}
						under Court Order</td>
				</tr>
				<tr>
					<td colspan="5">&nbsp;</td>
					<td width="40%">&nbsp;</td>
				</tr>
				
				<tr>
					<td colspan="6"><b><hr></b></td>
				</tr> 
				
				<tr>
					<td width="40%"><b>Your Case Worker is:</b></td>
					<td colspan="5">${caseBean.getWrkrName()}</td>
				</tr>
				<tr>
					<td width="27%"><b>Contact Phone: </b></td>
					<td width="18%">${caseBean.getWrkrPhone()}</td>


				</tr>

				<c:if
					test="${not empty caseBean.getWrkrPhoneExt() && !caseBean.getWrkrPhoneExt().equals('000000')}">
					<td width="7%"><b>Ext :</b></td>

					<td colspan="2">${caseBean.getWrkrPhoneExt()}</td>
				</c:if>

				<c:choose>

					<c:when test="${empty caseBean.getWrkrAddr1()}">
						<tr>
							<td width="27%"><b>Local Child Support Office:</b></td>
						</tr>
					</c:when>

					<c:otherwise>

						<tr>
							<td width="40%"><b>Local Child Support Office:</b></td>

							<td colspan="5">${caseBean.getWrkrAddr1()}</td>
						</tr>
						<tr>
							<td width="40%"><b></b></td>
							<td colspan="5">${caseBean.getWrkrAddr2()}</td>
						</tr>
						<tr>
							<td width="40%"><b></b></td>
							<td colspan="5">${caseBean.getWrkrCity()},
								${caseBean.getWrkrState()} , ${caseBean.getWrkrZip()}</td>
						</tr>
						<tr>
							<td width="27%"><b></b></td>
							<td colspan="5">&nbsp;</td>
							<!--  
							<td colspan="5"><font face="Arial, Helvetica, sans-serif"
								size="2" color="#0000FF"><a
									href="https://www.google.com/maps/place/https://www.google.com/maps/place/${caseBean.getWrkrAddr1()},${caseBean.getWrkrCity()},${caseBean.getWrkrState()},${caseBean.getWrkrZip()}"
									target="_blank">Map it</a></font></td>
							-->
						</tr>

					</c:otherwise>
				</c:choose>


			</table>

		</c:if>

		<br>
		<table width="90%" border="0">
			<tr>
				<td width="3%">
					<div align="left">
						<a href="${pageContext.request.contextPath}/cssp/user/CaseList/caseDetail.htm">
						<img src="${pageContext.request.contextPath}/images/bluesphere.gif"	width="18" height="17" alt="View Cases" border="0"></a>
					</div>
				</td>
				<td width="94%">View Case Summary on another case</td>
			</tr>
			<%-- <tr>
				<td width="3%">
					<div align="left">
						<a href="Navigation?destinationServlet=CaseSchedules&source=CaseInfo"><img
							src="${pageContext.request.contextPath}/images/bluesphere.gif"
							width="18" height="17" alt="View Cases" border="0"></a>
					</div>
				</td>
				<td width="94%">View Appointments/Hearings for this case</td>
			</tr> --%>
			<tr>
				<td width="3%">
					<div align="left">
						<a href="${pageContext.request.contextPath}/cssp/user/caseSchedule.htm">
						<img src="${pageContext.request.contextPath}/images/bluesphere.gif"	width="18" height="17" alt="View Cases" border="0"></a>
					</div>
				</td>
				<td width="94%">View Appointments/Hearings for this case</td>
			</tr>
			<tr>
				<td width="3%">
					<div align="left">
						<a
							href="${pageContext.request.contextPath}/cssp/user/parentWelcome.htm"><img
							src="${pageContext.request.contextPath}/images/bluesphere.gif"
							width="18" height="17" alt="Main Menu" border="0"></a>
					</div>
				</td>
				<td width="94%">Return to Main Menu</td>
			</tr>
		</table>
		<p>&nbsp;</p>
		<p>&nbsp;</p>
	</div>

</div>
