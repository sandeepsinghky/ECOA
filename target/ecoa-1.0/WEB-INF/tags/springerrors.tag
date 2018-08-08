<%@ attribute name="beanName" required="true"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<spring:hasBindErrors name="${beanName}">
	<div class="validation-errors">
		<h4>You have errors in your input!</h4>
		<ol>
			<c:forEach items="${errors.allErrors}" var="error">
				<li><spring:message message="${error}" /></li>
			</c:forEach>
		</ol>
	</div>
</spring:hasBindErrors>