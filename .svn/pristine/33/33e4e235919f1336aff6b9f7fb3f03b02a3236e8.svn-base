<%@ include file="../../../include/taglib.jsp"%>
<script>

	function noEnterKey(evt) {
      var evt = (evt) ? evt : ((event) ? event : null);
      if (evt.keyCode == 13)  {
          return false;
      }
    }
	document.onkeypress = noEnterKey;

	
	function validateform(selButton) {
		var name_regex = /^[a-zA-Z]+$/
		
		if (selButton.id==="sbmtbtn" && document.OnlineAppSbmtForm.signFirstName.value === ""){
			alert("First Name should not be empty");
			document.OnlineAppSbmtForm.signFirstName.focus();
			return false;
			
		}		
		else if (selButton.id==="sbmtbtn" && document.OnlineAppSbmtForm.signLastName.value === ""){
			alert("Last Name should not be empty");
			document.OnlineAppSbmtForm.signLastName.focus();
			return false;		
		}
		else if(selButton.id==="sbmtbtn" && !document.OnlineAppSbmtForm.ackSbmt.checked){
			alert("Check the acknowledgement checkbox");
			document.OnlineAppSbmtForm.ackSbmt.focus();
			return false;
		}
		else if (document.OnlineAppSbmtForm.signFirstName.value !="" && !name_regex.test(document.OnlineAppSbmtForm.signFirstName.value.trim())){
			alert("Special characters and numbers are not allowed in first name.");
			document.OnlineAppSbmtForm.signFirstName.focus();
			return false;
		}
		else if (document.OnlineAppSbmtForm.signMiddleInitial.value !="" && !name_regex.test(document.OnlineAppSbmtForm.signMiddleInitial.value.trim())){
			alert("Special characters and numbers are not allowed in middle name.");
			document.OnlineAppSbmtForm.signMiddleInitial.focus();
			return false;
		}
		else if (document.OnlineAppSbmtForm.signLastName.value !="" && !name_regex.test(document.OnlineAppSbmtForm.signLastName.value.trim())){
			alert("Special characters and numbers are not allowed in last name.");
			document.OnlineAppSbmtForm.signLastName.focus();
			return false;
		}
		document.OnlineAppSbmtForm.action.value = selButton.id;
		document.OnlineAppSbmtForm.submit();
		buttonsDisabled(true);
		return true;
 	}
	
	
	function goToApplListPage() {
	 	location.href="caseApplicationList.htm";
	}
	
	function goToPreviousPage() {
		if(document.OnlineAppSbmtForm.applSbmt.value === "false")
 		{
			if (confirm("Unsaved information will be lost. Leave this page?")){        
				location.href = "caseApplicationMiscInfo.htm";
				buttonsDisabled(true);
			}else{
				return false;
			}
		}else{
			location.href = "caseApplicationMiscInfo.htm";
		}
	}
	
</script>
<form name="OnlineAppSbmtForm" id="OnlineAppSbmtForm" method="post"	action="${pageContext.request.contextPath}/cssp/user/caseApplicationSubmission.htm" modelAttribute="appBean">
	<input type="hidden" name="applicationId" value="${appBean.getApplicationId()}"> 
	<input type="hidden" name="stepNum"	value="${flow.getStepNum()}">
	<input type="hidden" name="applSbmt" value="${appBean.isApplSbmtd()}">
	<input type="hidden" name="action" value="">


	<div id="aplContent">
		<div class="gutter2">
			<div class="box">
				<h2>SECTION 6: Certification Statement</h2>
				<hr />

				<c:if test="${!empty param.message}">
					<div class="error message-box">${param.message}</div>
				</c:if>

				<c:if test="${!empty param.success}">
					<div class="success message-box">${param.success}</div>
				</c:if>
				</b>
			</div>
			<div>
				<p>
					<b>Application Agreement and Signature</b>
				</p>
				<p>By submitting the application for child support services (clicking the submit button), you agree that we can do the following:</p>
				<ul>
					<li>provide you with all appropriate child support services for the case<br>
					</li>
					<li>use any information obtained to provide child support services<br>
					</li>
					<li>close your case if you do not cooperate</li>
				</ul>
			</div>
			<br>
			<%-- <div>
				<br> <input type=checkbox id='ackSbmt' ${appBean.isApplSbmtd()  ? 'checked' : ''}></input> I hereby certify that I have provided
				all requested information that is available to me, and that it is true and correct to the best of my knowledge.<br> <br>
				<p>
					<b>Applicant Signature:</b>
				</p>
				<table class=entry>
					<tr>
						<td align="left"><label>First Name:</label><input name="firstName" id="firstName" value="${appBean.getFirstName()}" size="20"	maxlength="30" readonly="readonly"></td>
						<td align="left"><label> Middle Name:</label></sub> <input name="middleName" value="${appBean.getMiddleName()}" size="1"        maxlength="1"  readonly="readonly"></td>
						<td align="left"><label>Last Name:</label></sub> <input type="text" name="lastName" value="${appBean.getLastName()}" size="20"	maxlength="30" readonly="readonly"></td>
					</tr>
				</table>
			</div> --%>
			<c:if test="${appBean.isApplSbmtd()}">
				<div disabled="disabled">
			</c:if>
			<dl class="feature">
				<dt>
					<p>
						<input type=checkbox name='ackSbmt' id='ackSbmt' ${appBean.isApplSbmtd() ? 'disabled checked' : signBean.getHasChecked().equals('y') ? 'checked' : ''}></input> 
						I have read the above information about the North Carolina Child Support Services (NCCSS) program services, policies and my rights and responsibilities or had the information explained
						to me.
					</p>
				</dt>
				<dd>
					<label id="signaturetitle">Signature: </label> 
					<input class="signature" type="text" name="signFirstName" id="nmSignF"      value="${signBean.getNmSignF()}"  size="20" maxlength="15" /> &nbsp;&nbsp;
					<input class="signature" type="text" name="signMiddleInitial" id="nmSignMI" value="${signBean.getNmSignMI()}" size="1" maxlength="1" /> &nbsp;&nbsp;
					<input class="signature" type="text" name="signLastName" id="nmSignL"	    value="${signBean.getNmSignL()}"  size="20" maxlength="17" />
				</dd>
				<br>
				<dd>
					<label id="signaturetitle"> Date: </label> <input class="signature" type="text" id="appldate" value="${signBean != null ? signBean.getDateSignedDtStr() : defaultDate}"
						size="25" maxlength="30" readonly="readonly">
				</dd>
				<br>
			</dl>
			<c:if test="${appBean.isApplSbmtd()}">
				</div>
			</c:if>
			<br><br>
			<div>
				<table width="80%" border="0">
					<tr>
						<td>&nbsp;&nbsp;</td>
						<td><input type=button value='Previous' onclick="goToPreviousPage()" /></td>
						<c:if test="${!appBean.isApplSbmtd()}">
							<td align="center"><input type="button" name="save" id="save" value="Save" onclick="return validateform(this)"></td>
							<td align="center"><input type="button" name="sbmtbtn" id="sbmtbtn" value="Submit Application" onclick="return validateform(this)"></td>
						</c:if>	
						<c:if test="${appBean.isApplSbmtd()}">					
				  			<td align="center"><input type="button" value='View / Print' id="print" onclick="return validateform(this)" /></td>
				  		</c:if>	
				 	</tr>
				</table>
			</div>
			<br><br>
		</div>
	</div>
</form>
