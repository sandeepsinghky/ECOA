<%@ include file="../../../include/taglib.jsp"%>
<div id="content">
	<div class="gutter">
		
		<c:if test="${!empty errMsg}">
			<div class="error message-box">${errMsg}</div>
		</c:if>
		<c:if test="${empty errMsg}">
			<table width="100%" border="0">
                 <tr>
					<jsp:useBean id="date" class="java.util.Date" />
					<td colspan="2">Below is a list of all your child support cases as of  <fmt:formatDate	value="${date}" type="date" pattern="dd-MMM-yyyy" /> 
					<hr>
					</td>
					
				</tr>				
				<!-- <tr>
					<td bgcolor="#FFFFFF" colspan="2"><b> Child Support Cases</b><br> 
					<hr>									
					</td>
				</tr>              --> 
              <tr>
		           <c:if test="${!empty errorMsg}">
						<td colspan="2" height="21"><font color="#FF0000"> <b> <font size="1">${errorMsg} </font></font> </b></td>
					</c:if>
					<c:if test="${empty errorMsg}">
						<!-- <td colspan="2" height="21"><font color="#FF0000"> <b> Please select case number to view Case Information. </font> </b></font></td>
					 -->
					 <td colspan="2" height="21">Please select case number to view Case Information.</td>
					
					 </c:if>
				</tr>
			</table>
			<table width="95%" border="0">
				<!-- <tr bgcolor="#0000FF"> -->
				<!-- <tr>
					<td width="4%" height="20" bgcolor="#FFFFFF">&nbsp;</td>
					<td width="12%" height="20">
						<font color="#FFFFFF">Case Number</font>
					</b></td>
					<td width="30%" height="20">
						<b><font color="#0000FF"><font	color="#FFFFFF">Relation</font></font></b>
					</td>
					<td width="18%" height="20"><b><font color="#0000FF">
						<font color="#FFFFFF">Other Participant </font></font></b>
					</td>
					<td width="29%" height="20"><b><font color="#0000FF"><font
								color="#FFFFFF">Relation</font></font></b>
					</td>
					<td width="7%" height="20" bgcolor="#0000FF"><font
						color="#FFFFFF"><b>Under Order</b></font><font color="#FFFFFF">
					</font></td>
				</tr> -->
				<tr>
					<td width="4%" height="20">&nbsp;</td>
					<td width="12%" height="20">&nbsp;</td>
					<td width="30%" height="20">&nbsp;</td>
					<td width="18%" height="20">&nbsp;</td>
					<td width="29%" height="20">&nbsp;</td>
					<td width="7%" height="20" >&nbsp;</td>
				</tr>
				
				 <tr>
					<td>&nbsp;</td>
					<td><font size="3"><b><u>Case Number</u></b></font></td>
					<td><font size="3"><b><u>Relation</u></b></font></td>
					<td><font size="3"><b><u>Other Participant</u></b></font> </td>
					<td><font size="3"><b><u>Relation</u></b></font></td>
					<td><font size="3"><b><u>Under Order</u></b></font></td>
				</tr>
				
				<!--  <tr>
					<td width="4%" height="20">&nbsp;</td>
					<td width="12%" height="20"><b>Case Number</b></td>
					<td width="30%" height="20"><b>Relation</b></td>
					<td width="18%" height="20"><b>Other Participant</b> </td>
					<td width="29%" height="20"><b>Relation</b></td>
					<td width="7%" height="20" ><b>Under Order</b></td>
				</tr> -->
				
				<!-- <tr>
					<td width="4%" height="1"><font size="1" face="Courier"></font></td>
					<td width="12%" height="1"><font size="1" face="Courier"></font></td>
					<td width="30%" height="1"><font size="1" face="Courier"></font></td>
					<td width="18%" height="1"><font size="1" face="Courier"></font></td>
					<td width="29%" height="1"><font size="1" face="Courier"></font></td>
					<td width="7%" height="1"><font size="1" face="Courier"></font></td>
				</tr> -->

				<c:forEach var="cb" items="${caseList}">


					<%-- <tr>
						<td width="4%">
							<a href="${pageContext.request.contextPath}/cssp/user/${path}.htm?errorMsg=${cb.getErrorMsg()}&ivdCase=${cb.getIvdCase()}"><img
								src="${pageContext.request.contextPath}/images/bluesphere.gif"
								width="18" height="17" border="0"></a>
						</td>
						<td width="12%"> <font face="Verdana, Arial, Helvetica, sans-serif" size="2"color="${cb.getColorCode()}">${cb.getIvdCase()}</font>
						</td>
						<td width="30%">
							<font face="Verdana, Arial, Helvetica, sans-serif" size="2"
								color="${cb.getColorCode()}">${cb.getPartType().trim().equals("AP") ? 'Non-Custodial Parent' : 'Client'}</font></td>
						<td width="18%"><font
								face="Verdana, Arial, Helvetica, sans-serif" size="2"
								color="${cb.getColorCode()}">${cb.getOtherName()}</font></td>
						<td width="29%"><font
								face="Verdana, Arial, Helvetica, sans-serif" size="2"
								color="${cb.getColorCode()}">${cb.getOtherPartType().trim().equals("AP") ? 'Non-Custodial Parent' : 'Client'}</font></td>
						<td width="7%"><font
								face="Verdana, Arial, Helvetica, sans-serif" size="2"
								color="${cb.getColorCode()}">${cb.isUnderOrder() ? 'yes' : 'No'}</font></td>
					</tr> --%>
					
					<tr>
						<td>
							<a href="${pageContext.request.contextPath}/cssp/user/${path}.htm?errorMsg=${cb.getErrorMsg()}&ivdCase=${cb.getIvdCase()}"><img
								src="${pageContext.request.contextPath}/images/bluesphere.gif"
								width="18" height="17" border="0"></a>
						</td>
						<%-- <td><font size="2"color="${cb.getColorCode()}">${cb.getIvdCase()}</font> </td>
						<td><font size="2"	color="${cb.getColorCode()}">${cb.getPartType().trim().equals("AP") ? 'Non-Custodial Parent' : 'Client'}</font></td>
						<td><font size="2"	color="${cb.getColorCode()}">${cb.getOtherName()}</font></td>
						<td><font size="2"	color="${cb.getColorCode()}">${cb.getOtherPartType().trim().equals("AP") ? 'Non-Custodial Parent' : 'Client'}</font></td>
						<td><font size="2"	color="${cb.getColorCode()}">${cb.isUnderOrder() ? 'yes' : 'No'}</font></td>
				 --%>
				        <td><font color="${cb.getColorCode()}">${cb.getIvdCase()}</font> </td>
						<td><font color="${cb.getColorCode()}">${cb.getPartType().trim().equals("AP") ? 'Non-Custodial Parent' : 'Client'}</font></td>
						<td><font color="${cb.getColorCode()}">${cb.getOtherName()}</font></td>
						<td><font color="${cb.getColorCode()}">${cb.getOtherPartType().trim().equals("AP") ? 'Non-Custodial Parent' : 'Client'}</font></td>
						<td><font color="${cb.getColorCode()}">${cb.isUnderOrder() ? 'yes' : 'No'}</font></td>
					
				 
				 </tr>

				</c:forEach>

				<c:if test="${caseList.size() == 0}">
					<td width="4%"></td>
					<td colspan="6"><font color="#990000"><b>No Active	Child Support Cases Exist</b></font></td>
				</c:if>

				<tr>
					<td width="4%">&nbsp;</td>
					<td colspan="6">&nbsp;</td>
				</tr>
				<tr>
					<td width="4%">&nbsp;</td>
					<td colspan="6" valign="bottom">Select a Case by clicking on
						the <img
						src="${pageContext.request.contextPath}/images/bluesphere.gif"
						width="18" height="17" border="0" align="bottom"> to the
						left of the Case Number
					</td>
				</tr>

			</table>
		</c:if>
		<br>
		<table width="550" border="0">
			<td width="24">
				<div align="left">
					<a href="#"
						onClick="window.open('${pageContext.request.contextPath}/legend.htm','','top=0,left=210,height=200,width=300');"><img
						src="${pageContext.request.contextPath}/images/bluesphere.gif"
						width="18" height="17" alt="Legend" border="0"></a>
				</div>
			</td>

			<td>
				<div align="left">Description for Colors</div>
			</td>
			<tr>
				<td width="24">
					<div align="left">
						<a
							href="${pageContext.request.contextPath}/cssp/user/parentWelcome.htm"><img
							src="${pageContext.request.contextPath}/images/bluesphere.gif"
							width="18" height="17" alt="Main Menu" border="0"></a>
					</div>
				</td>

				<td width="516">Return to Main Menu</td>
			</tr>
		</table>

	</div>
</div>
</div>
