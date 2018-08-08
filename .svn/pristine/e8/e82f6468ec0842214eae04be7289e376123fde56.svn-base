<%@ include file="../include/taglib.jsp"%>
<input type="hidden" name="isApp" value="${isApp}">

<div>
	<ol class="progtrckr" data-progtrckr-steps="8">
	
		<c:choose>		
			<c:when test="${navid == 2}">
				<li class="progtrckr-done"><a href=${pageContext.request.contextPath}/cssp/user/caseApplicationList/2.htm>Back</a></li>
			</c:when>
			<c:otherwise>				
				<li class="progtrckr-done"><a href=${pageContext.request.contextPath}/cssp/user/caseApplicationList/1.htm>Back</a></li>
			</c:otherwise>						
		</c:choose>
		<c:choose>		
			<c:when test="${flow.getStepNum() >= 1}">
				<li class="progtrckr-done"><a href="${pageContext.request.contextPath}/cssp/user/applicantRNR.htm">Start</a></li>
			</c:when>
			<c:otherwise>				
				<li class="progtrckr-todo">Start</li>
			</c:otherwise>
			
		</c:choose>
		
		<c:choose>
			<c:when test="${flow.getStepNum() >= 2}">
				<li class="progtrckr-done"><a href="${pageContext.request.contextPath}/cssp/user/applicantInfo.htm">Section 1</a></li>
			</c:when>
			<c:otherwise>
				<li class="progtrckr-todo">Section 1 </li>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${flow.getStepNum() >= 3}">
				<li class="progtrckr-done"><a href="${pageContext.request.contextPath}/cssp/user/caseApplicationCPInfo.htm">Section 2</a></li>
			</c:when>
			<c:otherwise>
				<li class="progtrckr-todo">Section 2</li>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${flow.getStepNum() >= 4 }">			
				<li class="progtrckr-done"><a href="${pageContext.request.contextPath}/cssp/user/caseApplicationChildInfo.htm">Section 3</a></li>
			</c:when>
			<c:otherwise>
				<li class="progtrckr-todo">Section 3</li>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${flow.getStepNum() >= 5 }">		
				<li class="progtrckr-done"><a href="${pageContext.request.contextPath}/cssp/user/caseApplicationNCPInfo.htm">Section 4</a></li>
			</c:when>
			<c:otherwise>
				<li class="progtrckr-todo">Section 4</li>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${flow.getStepNum() >= 6 }">			
				<li class="progtrckr-done"><a href="${pageContext.request.contextPath}/cssp/user/caseApplicationMiscInfo.htm">Section 5</a></li>
			</c:when>
			<c:otherwise>
				<li class="progtrckr-todo">Section 5</li>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${flow.getStepNum()== 7}">		
				<li class="progtrckr-done"><a href="${pageContext.request.contextPath}/cssp/user/caseApplicationSubmission.htm">End</a></li>
			</c:when>
			<c:otherwise>
				<li class="progtrckr-todo">End</li>
			</c:otherwise>
		</c:choose>		
			
	</ol>

	<ol class="progtrckrText" data-progtrckr-steps="8">
	    <li class="progtrckrText"><strong>Create/Delete Application</strong></li>
		<li class="progtrckrText"><strong>Your Rights & Responsibilities</strong></li>
		<li class="progtrckrText"><strong>Benefits</strong></li>
		<li class="progtrckrText"><strong>CP Information</strong></li>
		<li class="progtrckrText"><strong>Child Information</strong></li>
		<li class="progtrckrText"><strong>NCP Information</strong></li>
		<li class="progtrckrText"><strong>Additional Information</strong></li>
		<li class="progtrckrText"><strong>Signature & Submit</strong></li>
	</ol>

</div>
