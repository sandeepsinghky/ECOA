
<%@ include file="../../../include/taglib.jsp"%>

<div id="content">
	<div class="gutter">

		<table width="80%" border="0">
			<tr>
				<td colspan="2"><i><b><font face="Lucida Handwriting"
							size="2" color="#808080">e</font><font
							face="Arial, Helvetica, sans-serif" size="2" color="#808080">Child
								Support Request Confirmation</font></b></i></td>
			</tr>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>

			<c:choose>
				<c:when test="${! empty errMsg}">

					<tr>
						<td colspan="2"><font color="#FF0000"><b><font
									size="2">${errMsg} </font></b></font>
					</tr>


				</c:when>
				<c:otherwise>

					<tr>
						<td colspan="2">&nbsp;</td>
					</tr>
					<tr>
						<td colspan="2" height="20">${confirmation}</td>
					</tr>

				</c:otherwise>

			</c:choose>


			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2">&nbsp;</td>
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
				<td width="97%">Return to Main Menu</td>
			</tr>

		</table>

	</div>
</div>