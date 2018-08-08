<%@ include file="../include/taglib.jsp"%>

<script>

 function confirmLogout() {
			       
	if(document.getElementById("hasActiveOnlineAppl").value == "true"){
	    if (confirm("Are you sure you want to logout? You have an active online application in progress. You have to complete and submit the application within 10 days from the start of the application.")){        
	    	return true;
		}else{
		  	return false;
		 }	    	
	}else{
	    if (confirm("Are you sure you want to logout?")){        
	    	return true;
		}else{
			return false;
		}
	}
	buttonsDisabled(true);
}
 
 
 
</script>

<input type="hidden" name="hasActiveOnlineAppl" id="hasActiveOnlineAppl" value="${sessionScope.hasActiveOnlineAppl}">
<div>

	<ul class="tbar">


		<sec:authorize access="isAuthenticated()">

			<li>Welcome: ${userInfo.getWebFirstName()}&nbsp;${userInfo.getWebLastName()}<br><a href="${pageContext.request.contextPath}/logout.htm" onClick="return confirmLogout();">Logout</a></li>

		</sec:authorize>

	</ul>
</div>
