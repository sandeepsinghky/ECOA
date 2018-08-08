<%@ include file="../../../include/taglib.jsp"%>
<script>
	
	function noEnterKey(evt) {
      var evt = (evt) ? evt : ((event) ? event : null);
      if (evt.keyCode == 13)  {
          return false;
      }
    }
	document.onkeypress = noEnterKey;
	
	function processNCP(formButton)
	{
		var selected = false;
		var selectedVal = "";
	
		for(i=0; i<document.getElementsByName("selectedNCP").length; i++ ){
			if(document.getElementsByName("selectedNCP")[i].checked){
				selected = true;
				selectedVal = document.getElementsByName("selectedNCP")[i].value;
				break;
			}
		}
		
		if (!selected){
			alert("Please select the NCP to process");
			return false;
		}
		
		if(formButton.name == "selectNcp"){
			document.OnlineAppNCPInfo.action.value="selectNCP";
		}
		else if(formButton.name == "deleteNcp"){
			if(selectedVal == document.getElementById("applicant").value){
				alert("Can not delete the selected NCP. This NCP is applicant of this application.");
				return false;
			}
			if (confirm("Are you sure You want to delete the selected NCP?")){        
	        	document.OnlineAppNCPInfo.action.value="deleteNCP";
	        }else{
	  			return false;
	  		}	
  		}
  		document.OnlineAppNCPInfo.submit();
  		buttonsDisabled(true);
		return true;
	}

	function goToPreviousPage()
	{
		location.href="caseApplicationChildInfo.htm";
		buttonsDisabled(true);
	}
	
	function goToNextPage()
	{
		location.href="caseApplicationMiscInfo.htm";
		buttonsDisabled(true);
	}

</script>

<form name="OnlineAppNCPInfo" id="OnlineAppNCPInfo" method="post"
	action="${pageContext.request.contextPath}/cssp/user/caseApplicationNCPInfo.htm">

	<input type="hidden" name="action" id="action" value=""> 
	<input type="hidden" name="applicant" id="applicant" value="${sessionScope.appApplicant}">
	<input type="hidden" name="stepNum" value="${flow.getStepNum()}">


	<!-- <div id=saveAndExitDiv style="display: none" class=center>
		<h1>Are you sure you want to exit?</h1>
		<br /> <input type=button value='Cancel' onclick="hideLightbox()" />&nbsp; <input type=button value='Yes, Save & Exit!'
			onclick="hideLightbox()" />
	</div> -->
	<div id="aplContent">
		<div class="gutter2">
			<div class="box">
				<h2>SECTION 4- NONCUSTODIAL PARENT INFORMATION</h2>
				<hr />
				<c:if test="${!empty param.message}">
					<div class="error message-box">${param.message}</div>
				</c:if>
				<c:if test="${!empty param.success}">
					<div class="success message-box">${param.success}</div>
				</c:if>
			</div>
			<div>

				<c:if test="${!applSubmitted}">
				<p>
					<b>Click the link below to enter noncustodial parent information</b><br> <br> <a
						href="${pageContext.request.contextPath}/cssp/user/caseApplicationNCPDetails.htm">Click here to add noncustodial parent</a>
				</p>
			</c:if>
			</div>
			<div>
				<table>
					<tr>
						<td>
							<table border="1">
								<tr>
									<th bgcolor="#C0C0C0" colspan="6">Noncustodial parent(s) added to this application</th>
								</tr>
								<tr>
									<th>&nbsp;</th>
									<th>First Name</th>
									<th>Last Name</th>
									<th>SSN</th>
								</tr>
								<c:forEach var="ncpPartList" items="${ncpPartList}">
									<tr>
										<td><input type="radio" name="selectedNCP" id="selectedNCP" value="${ncpPartList.getApplicantId()}"></td>
										<td>${ncpPartList.getApplicantFNm()}</td>
										<td>${ncpPartList.getApplicantLNm()}</td>
										<td>${ncpPartList.getSsnNb()}</td>
									</tr>
								</c:forEach>
							</table>
						</td>
						<c:if test="${not empty ncpPartList}">
							<td>&nbsp;&nbsp;</td>
							<td>
								<table>
									<tr>
										<td><input type="button" name="selectNcp" value="Select NCP" onclick="return processNCP(this)"></td>
										<c:if test="${!applSubmitted}">
										<td><input type="button" name="deleteNcp" value="Delete NCP" onclick="return processNCP(this)"></td>
										</c:if>
									</tr>
								</table>
							</td>
						</c:if>
					</tr>
				</table>
			</div>
			<br><br>
			<div>
				<table width="60%" border="0">
					<tr>
						<td>&nbsp;</td>
						<td align="left"><input type=button value='Previous' onclick="goToPreviousPage()" /></td>
						<c:if test="${!ncpPartList.isEmpty()}">
							<td align="left"><input type=button value='Next' onclick="goToNextPage()" /></td>
						</c:if>
					</tr>
				</table>	
			</div>			
			<br> <br>
		</div>
	</div>
</form>
