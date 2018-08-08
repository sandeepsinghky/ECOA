<%@ include file="../../../include/taglib.jsp"%>
<script>

	function noEnterKey(evt) {
      var evt = (evt) ? evt : ((event) ? event : null);
      if (evt.keyCode == 13)  {
          return false;
      }
    }
	document.onkeypress = noEnterKey;

	function toggleTextBox(selectObject) {
		var value = selectObject.value;
		if (value == "y") {
			document.getElementById('miscInfoText').style.display = 'block';
		}
		if (value == "n") {
			document.getElementById("applNotes").value = " ";
			document.getElementById('miscInfoText').style.display = 'none';
		}
	}
	
	function submitform() {
		document.OnlineAppMiscForm.submit();
		buttonsDisabled(true);
		return true;
	}
	
	function goToPreviousPage()
	{
	 	
		 if(document.OnlineAppMiscForm.applSbmt.value === "false")
 		 {
		 	if (confirm("Unsaved information will be lost. Leave this page?")){        
				location.href = "caseApplicationNCPInfo.htm";
				buttonsDisabled(true);
			}else{
				return false;
			}
		}else{
			location.href = "caseApplicationNCPInfo.htm";
		}
	}
	
	function goToNextPage()
	{
		if(document.OnlineAppMiscForm.applSbmt.value === "false")
 		{
		 	if (confirm("Unsaved information will be lost. Leave this page?")){        
				location.href="caseApplicationSubmission.htm";
				buttonsDisabled(true);
			}else{
				return false;
			}
		}else{
			location.href="caseApplicationSubmission.htm";
		}
	}
	
</script>
<form name="OnlineAppMiscForm" id="OnlineAppMiscForm" method="post" action="${pageContext.request.contextPath}/cssp/user/caseApplicationMiscInfo.htm" modelAttribute="appBean">
<input type="hidden" name="applicationId" value="${appBean.getApplicationId()}">
<input type="hidden" name="applicationNotes" value="${appBean.getApplNotes()}">
<input type="hidden" name="stepNum" value="${flow.getStepNum()}">
<input type="hidden" name="applSbmt" value="${appBean.isApplSbmtd()}">
	
	<div id=saveAndExitDiv style="display: none" class=center>
		<h1>Are you sure you want to exit?</h1>
		<br /> <input type=button value='Cancel' onclick="hideLightbox()" />&nbsp; <input type=button value='Yes, Save & Exit!' onclick="hideLightbox()" />
	</div>
	<div id="aplContent">
		<div class="gutter2">
			<div class="box">
				<h2>SECTION 5: APPLICATION ADDITIONAL INFORMATION</h2>
				<hr />					
					<c:if test="${!empty param.message}">
						<div class="error message-box">${param.message}</div>
					</c:if>

					<c:if test="${!empty param.success}">
						<div class="success message-box">${param.success}</div>
					</c:if>		
			</div>
			<c:if test="${applSubmitted}">
				<div disabled="disabled">
			</c:if>
			<div>
				<p>
					<b>Do you have other information that may assist Child Support Services in processing your application? </b> 
					<select onchange="toggleTextBox(this)">
						<!--<option value="n" selected>No</option>
						  <option value="y">Yes</option>-->
						<option value="n" ${appBean.getApplNotes().equals("") ? 'selected' : ''}>No</option>
						<option value="y" ${!appBean.getApplNotes().equals("") ? 'selected' : ''}>Yes</option>
					</select>
				</p>
			</div>
			<br>
			<div id="miscInfoText" style="display: ${appBean.getApplNotes() == '' ? 'none' : 'block'}" class=center>
				<textarea name="applNotes" id="applNotes"  rows="15" cols="100" maxlength="1500">${appBean.getApplNotes()}</textarea>
			</div>
			<c:if test="${applSubmitted}">
				</div>
			</c:if>
			<br><br>
			<div>
				<table width="70%" border="0">
					<tr>
						<td>&nbsp;&nbsp;</td><td>&nbsp;&nbsp;</td>
						<td align="left"><input type=button value='Previous' onclick="goToPreviousPage()" /></td>
						<c:if test="${!applSubmitted}">
							<td>&nbsp;&nbsp;</td><td>&nbsp;&nbsp;</td>
							<td align="center"><input type="button" name="saveButton" id="save" value="Save" onclick="submitform()"></td>
						</c:if>
							<td>&nbsp;&nbsp;</td><td>&nbsp;&nbsp;</td>
							<td align="right"><input type=button value='Next' onclick="goToNextPage()" /></td>
					</tr>
				</table>
			</div>		
			<br><br>
		</div>
	</div>
</form>
