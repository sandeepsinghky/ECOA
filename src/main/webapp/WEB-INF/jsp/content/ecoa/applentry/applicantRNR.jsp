<%@ include file="../../../include/taglib.jsp"%>
<script>
	function noEnterKey(evt) {
      var evt = (evt) ? evt : ((event) ? event : null);
      if (evt.keyCode == 13)  {
          return false;
      }
    }
	document.onkeypress = noEnterKey;
	
 	function validateform() {
	
		var name_regex = /^[a-zA-Z]+$/;
		
		if (document.OnlineAppRespForm.signFirstName.value == ""){
			alert("Enter your first name as signature.");
			document.OnlineAppRespForm.signFirstName.focus();
			return false;
			
		}else if (document.OnlineAppRespForm.signLastName.value == ""){
			alert("Enter your last name as signature.");
			document.OnlineAppRespForm.signLastName.focus();
			return false;
			
		}else if(!document.OnlineAppRespForm.pc12.checked){
			alert("Check the acknowledgement box.");
			document.OnlineAppRespForm.pc12.focus();
			return false;
		}else if (!name_regex.test(document.OnlineAppRespForm.signFirstName.value.trim())){
			alert("Special characters and numbers are not allowed in first name.");
			document.OnlineAppRespForm.signFirstName.focus();
			return false;
		}else if (document.OnlineAppRespForm.signMiddleInitial.value !="" && !name_regex.test(document.OnlineAppRespForm.signMiddleInitial.value.trim())){
			alert("Special characters and numbers are not allowed in middle name");
			document.OnlineAppRespForm.signMiddleInitial.focus();
			return false;		
		}else if (!name_regex.test(document.OnlineAppRespForm.signLastName.value.trim())){
			alert("Special characters and numbers are not allowed in last name");
			document.OnlineAppRespForm.signLastName.focus();
			return false;			
		}
		
		if(document.OnlineAppRespForm.mode.value.trim() === 'update' && document.OnlineAppRespForm.chkd.value.trim() === 'true'){
			if (document.OnlineAppRespForm.signFirstName.value != document.OnlineAppRespForm.fiorig.value || document.OnlineAppRespForm.signLastName.value != document.OnlineAppRespForm.lsorig.value || document.OnlineAppRespForm.signMiddleInitial.value != document.OnlineAppRespForm.miorig.value) {
		   		document.OnlineAppRespForm.pc12.disabled = false;
		   		document.OnlineAppRespForm.pc12.checked = false;
		   		document.OnlineAppRespForm.chkd.value = 'false';
		   		alert("Check the acknowledgement box.");
				document.OnlineAppRespForm.pc12.focus();
				return false;	   			
			}
		}
		document.OnlineAppRespForm.submit();
		buttonsDisabled(true);
		return true;
 	}
 	function goToPreviousPage()
 	{
	 	if(document.OnlineAppRespForm.applSbmt.value === "false")
	 	{
		 	if (confirm("Unsaved information will be lost. Leave this page?"))
		 	{        
				if(document.OnlineAppRespForm.navid.value === "2")
				{
					location.href="${pageContext.request.contextPath}/cssp/user/caseApplicationList/2.htm";
				}
				else {
					location.href="${pageContext.request.contextPath}/cssp/user/caseApplicationList/1.htm";		
				}
				buttonsDisabled(true);
			}else{
				return false;
			}
		}
	 	else{
	 		if(document.OnlineAppRespForm.navid.value === "2")
			{
				location.href="${pageContext.request.contextPath}/cssp/user/caseApplicationList/2.htm";
			}
			else {
				location.href="${pageContext.request.contextPath}/cssp/user/caseApplicationList/1.htm";		
			}
	 		buttonsDisabled(true);
	 	}
 	}
 
 	function goToNextPage()
 	{
	 	if(document.OnlineAppRespForm.applSbmt.value === "false")
	 	{
		 	if (confirm("Unsaved information will be lost. Leave this page?"))
		 	{        
				location.href="applicantInfo.htm";
				buttonsDisabled(true);
			}else{
				return false;
			}
		}
		else
		{
			location.href="applicantInfo.htm";
		}	
 	}

</script>

<form name="OnlineAppRespForm" id="OnlineAppRespForm" method="post" action="${pageContext.request.contextPath}/cssp/user/applicantRNR.htm" modelAttribute="appBean">
	<input type="hidden" name="applicationId" value="${appBean.getApplicationId()}"> 
	<input type="hidden" name="emailId"	value="${appBean.getEmailId()}"> 
	<input type="hidden" name="ncId" value="${appBean.getNcId()}"> 
	<input type="hidden" name="mode" value="${mode}"> 
	<input type="hidden" name="isApp" value="${isApp}">
	<input type="hidden" name="stepNum" value="${flow.getStepNum()}">
	<input type="hidden" name="applSbmt" value="${appBean.isApplSbmtd()}">
	<input type="hidden" name="navid" value="${navid}">
	<input type="hidden" name="chkd" value="true">
	<input type="hidden" name="fiorig" value="${signBean.getNmSignF()}">
	<input type="hidden" name="lsorig" value="${signBean.getNmSignL()}">
	<input type="hidden" name="miorig" value="${signBean.getNmSignMI()}">

	<div id="aplContent">
		<div class="gutter2">
			<h2>APPLICANT RIGHTS AND RESPONSIBILITIES</h2>
			<hr />
			<c:if test="${!empty param.message}">
				<div class="error message-box">${param.message}</div>
			</c:if>

			<c:if test="${!empty param.success}">
				<div class="success message-box">${param.success}</div>
			</c:if>

			<p>All applicants: Either parent, alleged fathers, non-parent caretakers, minor children, social services agencies or judicial officials in a child
				support case, have the following rights and responsibilities:</p>

			<B> Rights </B>

			<ul>
				<li>To make an application for child support services at a local child support agency</li>
				<br>
				<li>To be provided information about the status of your child support case</li>
				<br>
				<li>To establish an account on the eChild Support website (www.ncchildsupport.com) to access case information</li>
				<br>
				<li>To receive notice of all pending court actions and to be provided copies of all court orders from court hearings related to
					your child support case</li>
				<br>
				<li>To request that the support order be reviewed or modified at least every three years</li>
				<br>
				<li>To request a review of case management or distribution of funds in your case</li>
				<br>
				<li>To hire a private attorney (at your own cost) to represent your interests in the child support case. The child support attorney
					represents the child support agency, and cannot represent you in child support or other legal matters regarding the child, such as
					custody and visitation</li>
			</ul>

			<B> Responsibilities </B>

			<ul>
				<li>To provide the child support agency information that may help in the progression of the child support case</li>
				<br>
				<li>To attend any appointments and/or hearings for which you have been provided notice that your participation is needed</li>
				<br>
				<li>To notify the child support agency of changes in your address and/or employment</li>
				<br>
				<li>To notify the child support agency if the child being provided services:
					<ul>
						<br>
						<li>Is no longer in your custody</li>			
						<li>Graduates or ceases to attend high school</li>
					</ul>
				</li>
				<br>
				<li>To repay any payments received in error</li>
			</ul>

			<p>
				Additional information about the Child Support program is available <a target="_blank" href="https://www.ncdhhs.gov/divisions/dss">here</a>
			</p>

			<c:if test="${appBean.isApplSbmtd()}">
				<div disabled="disabled">
			</c:if>
			<dl class="feature">
				<dt>
					<p>
						<input type=checkbox id='pc12' ${appBean.isApplSbmtd() || mode == 'update' ? 'disabled checked' : ''}></input> I have read or have had explained to me the above information about the North Carolina Child Support Services (NCCSS) program policies, 
						services and my rights and responsibilities. By signing below, I acknowledge that I have received a copy of the above information and that I give NCCSS permission to send automated e-mail messages to me regarding my application.
					</p>
				</dt>
				<dd>
					<label id="signaturetitle">Signature: </label> 
					<input class="signature" type="text" name="signFirstName" id="nmSignF" value="${signBean.getNmSignF()}"  size="20" maxlength="15" /> &nbsp;&nbsp;
					<input class="signature" type="text" name="signMiddleInitial" id="nmSignMI" value="${signBean.getNmSignMI()}" size="1" maxlength="1" /> &nbsp;&nbsp;
					<input class="signature" type="text" name="signLastName" id="nmSignL" value="${signBean.getNmSignL()}"  size="20" maxlength="17" />
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
							<td>&nbsp;&nbsp;</td><td>&nbsp;&nbsp;</td>
							<td align="left"><input type=button value='Previous' onclick="goToPreviousPage()" /></td>
							<c:if test="${!appBean.isApplSbmtd()}">
								<td>&nbsp;&nbsp;</td><td>&nbsp;&nbsp;</td>
								<td align="center"><input type="button" name="saveButton" id="save" value="Save" onclick="return validateform(this.saveButton)"></td>
							</c:if>
							<c:if test="${mode == 'update'}">
								<td>&nbsp;&nbsp;</td><td>&nbsp;&nbsp;</td>
								<td align="right"><input type=button value='Next' onclick="goToNextPage()" /></td>
							</c:if>
						</tr>
					</table>
			</div> 
			<br><br>
		</div>
	</div>
</form>
