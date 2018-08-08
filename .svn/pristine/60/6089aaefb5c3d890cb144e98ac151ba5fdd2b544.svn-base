<%@ include file="../../../include/taglib.jsp"%>
<div id="content">
	<div class="gutter">


		<%
			/**
			   Name        : Schedules.jsp 
			   Description : This program will display a detailed description of the nature of hearing/appointment
			     including the date, time and location the hearing/appointment was scheduled for the 
							 selected case. If the user is a Client on the selected case, then the schedules will 
							 not display any appointments related to the other party on the case and vice-versa. 
							 All the appointments and hearings scheduled in the future will be displayed but the
							 past scheduled appointments and hearings displayed will be limited to only last three. 
			                 Program is fixed to display "Map it" Button only if address line 1 and   City has values in the field.
			                 All the records will be in the descending order in the array  List based on scheduled date.
			                
								
			 **/
		%>

		<table width="100%" border="0">		
			<tr>
				<td colspan="2"><b>Appointments/Hearings for &lt;${ivdCase} &gt; ${userInfo.getFirstName()}	${userInfo.getLastName()} Vs ${otherName} </b> <br> <br>
				<hr></td>
			</tr>
			<tr>
				<td colspan="2">Here is a list of Appointments/Hearings	scheduled on your behalf.</td>
			</tr>
		</table>
		<br>
		<table width="100%" border="0">
			<!-- <tr bgcolor="#0000FF">
				<td height="20" bgcolor="#0000FF" colspan="2"
				><font
					color="#FFFFFF" face="Arial, Helvetica, sans-serif" size="2"><b>Description</b></font></td>
				<td width="6%" height="20" bgcolor="#0000FF"><font
					color="#FFFFFF" face="Arial, Helvetica, sans-serif" size="2"><b>Location</b></font></td>
			</tr> -->
			
			<tr>
				<td height="20" colspan="2"><b>Description</b></td>
				<td height="20"><b>Location</b></td>
			</tr>

			<c:set var="isPastHear" value="true" />
			<c:set var="isFutureHear" value="true" />
			<c:set var="isPastAppt" value="true" />
			<c:set var="isFutureAppt" value="true" />
			<c:set var="isAppt" value="true" />
			<c:set var="isHear" value="true" />

			<c:choose>
				<c:when test="${!empty(scheduleList)}">

					<c:forEach var="s" items="${scheduleList}">

						<c:if test="${s.getScheduleCode().equals('APPT') && s.isFuture() && isFutureAppt}">

							<c:set var="isFutureAppt" value="false" />

							<c:if test="${isAppt}">

								<c:set var="isAppt" value="false" />

								<tr>
									<td colspan="2"><b>Appointments</b></font></td>
								</tr>

							</c:if>

							<tr>
								<td width="3%">&nbsp;</td>
								<td width="47%"><b>Future Appointments</b></font></td>
							</tr>

						</c:if>

						<c:if
							test="${s.getScheduleCode().equals('APPT') && !s.isFuture() && isPastAppt}">

							<c:set var="isPastAppt" value="false" />

							<c:if test="${isAppt}">

								<c:set var="isAppt" value="false" />

								<tr>
									<td colspan="2"><b>Appointments</b></font></td>
								</tr>

							</c:if>

							<tr>
								<td width="3%">&nbsp;</td>
								<td width="47%"><b>Past Appointments</b></td>
							</tr>

						</c:if>
						<c:if test="${ s.isFuture() && isFutureHear && ((s.getScheduleCode().equals('HEAR')) || (s.getScheduleCode().equals('APPL')))}">
							<c:set var="isFutureHear" value="false" />
							<c:if test="${isHear}">
								<c:set var="isHear" value="false" />
								<tr>
									<td colspan="2"><b>Hearings</b></td>
								</tr>
							</c:if>
							<tr>
								<td width="3%">&nbsp;</td>
								<td width="47%"><b>Future Hearings</b></td>
							</tr>

						</c:if>
						<c:if test="${!s.isFuture() && (isPastHear) && ((s.getScheduleCode().equals('HEAR')) || (s.getScheduleCode().equals('APPL')))}">
							<c:set var="isPastHear" value="false" />
							<c:if test="${isHear}">
								<c:set var="isHear" value="false" />
								<tr>
									<td colspan="2"><b>Hearings</b></td>
								</tr>
							</c:if>
							<tr>
								<td width="3%">&nbsp;</td>
								<td width="47%"><b>Past Hearings</b></td>
							</tr>
						</c:if>
						<tr>
							<td width="3%">&nbsp;</td>
							<td width="70%">${s.getDispText()}</td>
							<c:if test="${s.isFuture() && (! empty (s.getScheduleAddr1())) || (! empty (s.getScheduleCity()))}">

								<td width="25%" align="center"><font size="1" color="blue">${s.getScheduleAddr1()},${s.getScheduleCity()},${s.getScheduleState()},${s.getScheduleZip()}&nbsp;&nbsp;</font></td>
									
								<!-- <td width="25%" align="center"><font size="1" color="blue">${s.getScheduleAddr1()},${s.getScheduleCity()},${s.getScheduleState()},${s.getScheduleZip()}&nbsp;&nbsp;</font><a
								href="https://www.google.com/maps/place/https://www.google.com/maps/place/${s.getScheduleAddr1()},${s.getScheduleCity()},${s.getScheduleState()},${s.getScheduleZip()}"
								target="_blank">Map it</a></td>-->

							</c:if>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="2"><b>No Appointments/Hearings exist </b></td>

						<td width="6%"></td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>
		<br>
		<table width="550" border="0">
			<tr>
				<td width="20">
					<div align="left">
						<a href="${pageContext.request.contextPath}/cssp/user/CaseList/caseSchedule.htm">
						  <img src="${pageContext.request.contextPath}/images/bluesphere.gif" width="18" height="17" alt="View Cases" border="0"></a>
					</div>
				</td>
				<td align="left" width="520">View Appointments/Hearings on another case</td>

			</tr>
			<tr>
				<td width="20">
					<div align="left">
						<a href="${pageContext.request.contextPath}/cssp/user/parentWelcome.htm">
						<img src="${pageContext.request.contextPath}/images/bluesphere.gif"	width="18" height="17" alt="Main Menu" border="0"></a>
					</div>
				</td>

				<td align="left" width="520">Return to Main Menu</td>

			</tr>
		</table>


	</div>
</div>
