<%@ include file="../../../include/taglib.jsp"%>
<script>

	function noEnterKey(evt) {
      var evt = (evt) ? evt : ((event) ? event : null);
      if (evt.keyCode == 13)  {
          return false;
      }
    }
	document.onkeypress = noEnterKey;
	
	function processChild(formButton)
	{
		var selected = false;
		var selectedVal = "";
		
		for(i=0; i<document.getElementsByName("selectedChld").length; i++ ){
			if(document.getElementsByName("selectedChld")[i].checked){
				selected = true;
				selectedVal = document.getElementsByName("selectedChld")[i].value;
				break;
			}
		}
			
		if (!selected){
			alert("Please select the child to process");
			return false;
		}
			
		if(formButton.name == "selectChild"){
			document.OnlineAppChldInfo.action.value="selectChld";
		}
		else if(formButton.name == "deleteChild"){
			if(selectedVal == document.getElementById("applicant").value){
				alert("Can not delete the selected child. This child is applicant of this application.");
				return false;
			}
			if (confirm("Are you sure you want to delete the selected child?")){        
		        document.OnlineAppChldInfo.action.value="deleteChld";
		    }else{
		  		return false;
		  	}	
	  	}
	  	document.OnlineAppChldInfo.submit();
	  	buttonsDisabled(true);
		return true;
	}

	function goToPreviousPage()
	{
		location.href="caseApplicationCPInfo.htm";
		buttonsDisabled(true);
	}

	function goToNextPage()
	{
		location.href="caseApplicationNCPInfo.htm";
		buttonsDisabled(true);
	}
</script>

<form name="OnlineAppChldInfo" id="OnlineAppChldInfo" method="post"	action="${pageContext.request.contextPath}/cssp/user/caseApplicationChildInfo.htm">
	<input type="hidden" name="action" id="action" value="">
	<input type="hidden" name="applicant" id="applicant" value="${sessionScope.appApplicant}">
	
	<div id="aplContent">
		<div class="gutter2">
			<div class="box">
				<h2>SECTION 3- CHILD INFORMATION</h2>
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
					<b>Click the link below to enter children's information</b><br> <br> <a
						href="${pageContext.request.contextPath}/cssp/user/caseApplicationChildDetails.htm">Click here to add a child</a>
				</p>
			</c:if>
			</div>
			<div>
				<table>
					<tr>
						<td>
							<table border="1">
								<tr>
									<th bgcolor="#C0C0C0" colspan="6">Child(ren) added to this application</th>
								</tr>
								<tr>
									<th>&nbsp;</th>
									<th>First Name</th>
									<th>Last Name</th>
									<th>SSN</th>
								</tr>
								<c:forEach var="chldPartList" items="${chldPartList}">
									<tr>
										<td><input type="radio" name="selectedChld" id="selectedChld" value="${chldPartList.getApplicantId()}"></td>
										<td>${chldPartList.getApplicantFNm()}</td>
										<td>${chldPartList.getApplicantLNm()}</td>
										<td>${chldPartList.getSsnNb()}</td>
									</tr>
								</c:forEach>
							</table>
						</td>
						<c:if test="${not empty chldPartList}">
							<td>&nbsp;&nbsp;</td>
							<td>
								<table>
									<tr>
										<td><input type="button" name="selectChild" value="Select Child" onclick="return processChild(this)"></td>
									<c:if test="${!applSubmitted}">
										<td><input type="button" name="deleteChild" value="Delete Child" onclick="return processChild(this)"></td>
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
						<c:if test="${!chldPartList.isEmpty()}">
							<td align="left"><input type=button value='Next' onclick="goToNextPage()" /></td>
						</c:if>
					</tr>
				</table>	
			</div>
			<br> <br>
		</div>
	</div>
</form>
