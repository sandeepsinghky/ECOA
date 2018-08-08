<%@ include file="../../../include/taglib.jsp"%>
<div id="content">
	<div class="gutter">
		<%
			/**
			   Name        : ObligationSummary.jsp
			   Description : This program will display the Court ordered Child Support Amount that includes the 
			     current support ordered per frequency, the arrears ordered amount per frequency and 
							 the total arrears amount.
			   Java Bean   : UserInfoBean,Obligation
			           
			**/
		%>

		<table width="100%" border="0">
			<%-- <tr>
				<td bgcolor="#FFFFFF" colspan="2"><b>Support Obligation	Summary for ${ivdCase} &lt;${userInfo.getFirstName()} &nbsp;
						${userInfo.getLastName()} vs. ${otherName}&gt;</b><br>
				<hr></td>
			</tr> --%>
			<tr>
				<td><b>Support Obligation	Summary for ${ivdCase} &lt;${userInfo.getFirstName()} &nbsp;
						${userInfo.getLastName()} vs. ${otherName}&gt;</b><br>
				<hr></td>
			</tr>
			<tr>
				<td colspan="2">Below is a summary of the support obligation for the selected case.</td>
			</tr>
		</table>
		<br>
		<table width="80%" border="0">
			<!-- <tr bgcolor="#0000FF">
				<td colspan="2" height="20" bgcolor="#0000FF"><font
					color="#FFFFFF" face="Arial, Helvetica, sans-serif" size="2"><b>Court
							Ordered Obligation Summary</b></font></td>
			</tr> -->
			<tr>
				<td colspan="2"><b>Court Ordered Obligation Summary</b></td>
				
			</tr>
			<tr> <td colspan="2">&nbsp;</td></tr>
			

			<c:choose>
				<c:when test="${ob.getCsupFreq().trim().equals('ANNL')}">

					<c:set var="Csupfreq" value="Annually" />

				</c:when>

				<c:when test="${ob.getCsupFreq().trim().equals('BIWK')}">

					<c:set var="Csupfreq" value="Bi-weekly" />

				</c:when>

				<c:when test="${ob.getCsupFreq().trim().equals('MNTH')}">

					<c:set var="Csupfreq" value="Monthly" />


				</c:when>

				<c:when test="${ob.getCsupFreq().trim().equals('NOC')}">

					<c:set var="Csupfreq" value="No Charge" />

				</c:when>

				<c:when test="${ob.getCsupFreq().trim().equals('ONCE')}">

					<c:set var="Csupfreq" value="Once" />

				</c:when>

				<c:when test="${ob.getCsupFreq().trim().equals('QTLY')}">

					<c:set var="Csupfreq" value="Quarterly" />

				</c:when>

				<c:when test="${ob.getCsupFreq().trim().equals('SEAN')}">

					<c:set var="Csupfreq" value="Semi-annually" />

				</c:when>

				<c:when test="${ob.getCsupFreq().trim().equals('SEMO')}">

					<c:set var="Csupfreq" value="Semi-monthly" />

				</c:when>

				<c:when test="${ob.getCsupFreq().trim().equals('WKLY')}">

					<c:set var="Csupfreq" value="Weekly" />

				</c:when>


				<c:otherwise>
					<c:set var="Csupfreq" value=" " />

				</c:otherwise>

			</c:choose>

			<c:choose>
				<c:when test="${ob.getCsupAmt() > 0.00}">

					<tr>
						<td colspan="2">Current Support is ordered as $
							${ob.decimalFormat(ob.getCsupAmt())} ${Csupfreq}</td>
					</tr>

				</c:when>

				<c:otherwise>

					<tr>
						<td colspan="2">Current Support is ordered as $
							${ob.decimalFormat(ob.getCsupAmt())}</td>
					</tr>

				</c:otherwise>

			</c:choose>


			<tr>
				<td width="7%"></td>
				<td width="93%">Current support still owed this period is $
					${ob.decimalFormat(ob.getCsupTotal())}</td>
			</tr>
			<tr>
				<td width="7%">&nbsp;</td>
				<td width="93%">&nbsp;</td>
			</tr>
			<c:choose>
				<c:when test="${ob.getArrsFreq().trim().equals('ANNL')}">

					<c:set var="Arrsfreq" value="Annually" />

				</c:when>

				<c:when test="${ob.getArrsFreq().trim().equals('BIWK')}">

					<c:set var="Arrsfreq" value="Bi-weekly" />

				</c:when>

				<c:when test="${ob.getArrsFreq().trim().equals('MNTH')}">

					<c:set var="Arrsfreq" value="Monthly" />


				</c:when>

				<c:when test="${ob.getArrsFreq().trim().equals('NOC')}">

					<c:set var="Arrsfreq" value="No Charge" />

				</c:when>

				<c:when test="${ob.getArrsFreq().trim().equals('ONCE')}">

					<c:set var="Arrsfreq" value="Once" />

				</c:when>

				<c:when test="${ob.getArrsFreq().trim().equals('QTLY')}">

					<c:set var="Arrsfreq" value="Quarterly" />

				</c:when>

				<c:when test="${ob.getArrsFreq().trim().equals('SEAN')}">

					<c:set var="Arrsfreq" value="Semi-annually" />

				</c:when>

				<c:when test="${ob.getArrsFreq().trim().equals('SEMO')}">

					<c:set var="Arrsfreq" value="Semi-monthly" />

				</c:when>

				<c:when test="${ob.getArrsFreq().trim().equals('WKLY')}">

					<c:set var="Arrsfreq" value="Weekly" />

				</c:when>


				<c:otherwise>
					<c:set var="Arrsfreq" value=" " />
				</c:otherwise>

			</c:choose>

			<c:choose>

				<c:when test="${ob.getArrsAmt() > 0.00}">

					<tr>
						<td colspan="2">Arrears are ordered as $
							${ob.decimalFormat(ob.getArrsAmt())} ${Arrsfreq}</td>
				</c:when>


				<c:otherwise>

					<tr>
						<td colspan="2">Arrears are ordered as $
							${ob.decimalFormat(ob.getArrsAmt())}</td>


					</tr>

				</c:otherwise>

			</c:choose>

			<tr>
				<td width="7%"></td>
				<td width="93%">The balance of past due support owed is $
					${ob.decimalFormat(ob.getArrsTotal())}</td>
			</tr>

		</table>
		<p>&nbsp;</p>
		<table width="80%" border="0">
			<tr>
				<td width="6%">
					<div align="left">
						<a
							href="${pageContext.request.contextPath}/cssp/user/CaseList/caseObligation.htm"><img
							src="${pageContext.request.contextPath}/images/bluesphere.gif"
							width="18" height="17" alt="View Cases" border="0"></a>
					</div>
				</td>
				<td width="94%">View Obligation Summary on another case</td>
			</tr>
			<tr>
				<td width="6%">
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

	</div>
</div>



