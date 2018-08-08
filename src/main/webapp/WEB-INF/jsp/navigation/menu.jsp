<%@ include file="../include/taglib.jsp"%>
<div>
	<ul class="nav">
		<sec:authorize var="loggedIn" access="isAuthenticated()" />
		<c:choose>
			<c:when test="${loggedIn}">
				<c:choose>
					<c:when test="${userInfo.getUserType().equals('ncId')}">
						<c:choose>
							<c:when test="${userInfo.isMpiTied() eq true}">
								<li><a
									href="${pageContext.request.contextPath}/loggedInHome.htm">Home</a></li>
								<li><a
									href="${pageContext.request.contextPath}/cssp/user/parentWelcome.htm">Parents</a></li>
							</c:when>
							<c:otherwise>
								<li><a
									href="${pageContext.request.contextPath}/loggedInHome.htm">Home</a></li>
								<li><a
									href="${pageContext.request.contextPath}/cssp/user/userWelcome.htm">Parents</a></li>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<li><a
							href="${pageContext.request.contextPath}/loggedInHome.htm">Home</a></li>
						<li><a
							href="${pageContext.request.contextPath}/cssp/user/parentWelcome.htm">Parents</a></li>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<li><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
				<li><a href="${pageContext.request.contextPath}/parentsIndex.jsp">Parents</a></li>
				<li><a href="${pageContext.request.contextPath}/employersIndex.jsp">Employers</a></li> 
			</c:otherwise>
		</c:choose>

	</ul>
</div>