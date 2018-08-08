<%@ include file="../../../include/taglib.jsp"%>

<script>

	$(function() {
    	$( "#brthDt" ).datepicker({
	    	changeMonth: true,
	    	changeYear: true,
	    	yearRange: "-100:+10" 
	    	 	
    	});
	});
	
	$(function() {
    	$( "#dt_emp_end" ).datepicker({
	    	changeMonth: true,
	    	changeYear: true,
	    	yearRange: "-100:+10"
	    	 
    	});
	});
	
	$(document).ready(function() {
		initLightbox();
	});
	
	function noEnterKey(evt) {
      var evt = (evt) ? evt : ((event) ? event : null);
      if (evt.keyCode == 13)  {
          return false;
      }
    }
	document.onkeypress = noEnterKey;
	
	function checkResidAddr(checkbox) {
		if (checkbox.checked) {
			document.getElementById('residential').style.display = 'none';
		} else {
			document.getElementById('residential').style.display = 'block';
		}

	}

	function showOther(selectObject)
	{
		var id = selectObject.id+"_ot";
		var optionTxt = selectObject.options[ selectObject.selectedIndex].text;
		if (optionTxt.trim()==="Other"){
			document.getElementById(id).style.display='block';
		}
		else{
			document.getElementById(selectObject.id+'Ot').value = " ";
			document.getElementById(id).style.display='none';
		}
	}

	function addPhInfo()
	{
		var phone=document.getElementById('phone');
		var value = phone.value;
		var id = "phone_"+value;
		var id_text = id+"_val";
		document.getElementById(id).style.display='block';	
		document.getElementById(id_text).value="y";	
	}
	
	function removePhInfo()
	{
		var selected = false;
		var selVal;
		
		if (document.getElementById("phone_h").style.display=='none' && document.getElementById("phone_w").style.display=='none' && document.getElementById("phone_c").style.display=='none'){
			alert("No phone number to remove.");
			return false;
		}
		
		for(i=0; i<document.getElementsByName("pcontact").length; i++ ){
			if(document.getElementsByName("pcontact")[i].checked){
				selected = true;
				selVal = document.getElementsByName("pcontact")[i].value;
				break;
			}
		}
		if (!selected){
			alert("Select phone contact to be deleted.");
			return false;
		}
		if (confirm("Are you sure You want to remove the selected phone information?")) {
			var id = "phone_"+selVal;
			var id_text = id+"_val";
			document.getElementById(id).style.display='none';	
			document.getElementById(id_text).value="";
		}else {
			return false;
		}		
		return true;	
	}
	
	function deleteIncomeType() {
		var selected = false;
		var selVal;

		for (i = 0; i < document.getElementsByName("incomeRadio").length; i++) {
			if (document.getElementsByName("incomeRadio")[i].checked) {
				selected = true;
				selVal = document.getElementsByName("incomeRadio")[i].value;
				break;
			}
		}
		if (!selected) {
			alert("Select income source to be deleted by selecting the radio button below.");
			return false;
		}

		//delete row
		if (confirm("Confirm deletion of income source?")) {
			var trow = document.getElementById("income_" + selVal);
			trow.parentNode.removeChild(trow);
		} else {
			return false;
		}
		var income_group = document.getElementsByName("incomeRadio");		
		var total;
		for (i = 0; i < income_group.length; i++) {
			var radioVal = income_group[i].value;
			var amt = parseFloat(document.getElementById("income_val_"+radioVal).value);
			if(i==0){
			 total = amt;
			}
			else{
				total = parseFloat(total) + parseFloat(amt);
			}
		}
		if(parseFloat(total) > 0){
			document.getElementById("income").value = parseFloat(total).toFixed(2);
		}else{
			document.getElementById("income").value = "0.00";
		}
		return true;
	}

	function addIncomeType() {
		var selObj = document.getElementById("incomeType");
		var selVal = selObj.options[selObj.selectedIndex].value;
		var selRow = "income_" + selVal;
		if (selVal == "0") {
			alert("Select Income Type.");
			document.getElementById("incomeType").focus();
			return false;
		}
		if (document.getElementById("incomeAmt").value == "") {
			alert("Enter income amount.");
			document.getElementById("incomeAmt").focus();
			return false;
		}

		if (!IsNumeric(document.getElementById("incomeAmt").value)) {
			alert("Enter numeric value for income amount.");
			document.getElementById("incomeAmt").focus();
			return false;
		}

		if (document.getElementById(selRow) == null) {
			var incomeTable = document.getElementById('incomeTable');
			var rowCount = incomeTable.rows.length;
			var trow = incomeTable.insertRow(incomeTable.rows.length);
			trow.id = "income_" + selVal;

			var tcell0 = trow.insertCell(0);
			var tcell1 = trow.insertCell(1);
			var tcell2 = trow.insertCell(2);

			var radio = document.createElement("input");
			radio.type = "radio"
			radio.name = "incomeRadio";
			radio.id = "incomeRadio";
			radio.value = selVal;
			tcell0.appendChild(radio);

			var label = document.createElement("label");
			label.innerHTML = selObj.options[selObj.selectedIndex].text;
			tcell1.appendChild(label);

			var incVal = document.createElement("input");
			incVal.size="15";
			incVal.readOnly=true;
			incVal.id = "income_val_" + selVal;
			incVal.name = "income_val_" + selVal;
			incVal.value = parseFloat(document.getElementById("incomeAmt").value).toFixed(2);
			document.getElementById("incomeAmt").value = "";
			tcell2.appendChild(incVal);
		} else {
			if (confirm("Income type already added. Do you want to overwrite the amount value?")) {
					document.getElementsByName("income_val_" + selVal)[0].value = parseFloat(document.getElementById("incomeAmt").value).toFixed(2);
					document.getElementById("incomeAmt").value = ""; 	
				} else {
				return false;
			}
		}
		var income_group = document.getElementsByName("incomeRadio");		
		var total;
		for (i = 0; i < income_group.length; i++) {
			var radioVal = income_group[i].value;
			var amt = parseFloat(document.getElementById("income_val_"+radioVal).value);
			if(i==0){
			 total = amt;
			}
			else{
				total = parseFloat(total) + parseFloat(amt);
			}
		}
		if(parseFloat(total) > 0){
			document.getElementById("income").value = parseFloat(total).toFixed(2);
		}else{
			document.getElementById("income").value = "0.00";
		}
		
		return true;

	}

	function toggleEmp(selectObject) {
		var value = selectObject.value;
		if (value == "y") {
			document.getElementById('lastEmp').style.display = 'none';
			document.getElementById('currEmp').style.display = 'block';
		}
		if (value == "n") {
			document.getElementById('last_emp').value = " ";
			document.getElementById('currEmp').style.display = 'none';
			document.getElementById('lastEmp').style.display = 'block';
		}
	}
	

	function validateform() {
		var ssn = document.OnlineAppCPForm.ssnNb.value;
		var date_regex = /^(0[1-9]|1[0-2])\/(0[1-9]|1\d|2\d|3[01])\/(19|20)\d{2}$/;
		var ssn_regex = /^\d{9}$/;
		var zip_regex = /^\d{5}$/;
		var name_regex = /^[a-zA-Z ]+$/;
		var alias_name_regex = /^[a-zA-Z, ]+$/;
		
		if (document.OnlineAppCPForm.applicantFNm.value == "") {
			alert("First Name should not be empty");
			document.OnlineAppCPForm.applicantFNm.focus();
			return false;
		} else if (document.OnlineAppCPForm.applicantLNm.value == "") {
			alert("Last Name should not be empty");
			document.OnlineAppCPForm.applicantLNm.focus();
			return false;
		} else if (!name_regex.test(document.OnlineAppCPForm.applicantFNm.value.trim())){
			alert("Special characters and numbers are not allowed in first name.");
			document.OnlineAppCPForm.applicantFNm.focus();
			return false;
		} else if (document.OnlineAppCPForm.applicantMNm.value !="" && !name_regex.test(document.OnlineAppCPForm.applicantMNm.value.trim())){
			alert("Special characters and numbers are not allowed in middle name.");
			document.OnlineAppCPForm.applicantMNm.focus();
			return false;
		} else if (!name_regex.test(document.OnlineAppCPForm.applicantLNm.value.trim())){
			alert("Special characters and numbers are not allowed in last name.");
			document.OnlineAppCPForm.applicantLNm.focus();
			return false;
		} else if (document.OnlineAppCPForm.applicantMdnNm.value !="" && !name_regex.test(document.OnlineAppCPForm.applicantMdnNm.value.trim())){
			alert("Special characters and numbers are not allowed in maiden name.");
			document.OnlineAppCPForm.applicantMdnNm.focus();
			return false;
		} else if (document.OnlineAppCPForm.applicantAliasNm.value !="" && !alias_name_regex.test(document.OnlineAppCPForm.applicantAliasNm.value.trim())){
			alert("Special characters (except comma) and numbers are not allowed in alias name.");
			document.OnlineAppCPForm.applicantAliasNm.focus();
			return false;
		} else if (ssn.trim() != "" && !ssn_regex.test(ssn)){
			alert("SSN should be all numbers in format '123456789'");
			document.OnlineAppCPForm.ssnNb.focus();
			return false;		
		} else if (document.OnlineAppCPForm.brthDt.value.trim() != ""
				&& (!(isValidDate(document.OnlineAppCPForm.brthDt.value, "date of birth")) || !(beforeFutureDate(document.OnlineAppCPForm.brthDt.value, "date of birth")))) {
			document.OnlineAppCPForm.brthDt.focus();
			return false;	
		}else if (document.OnlineAppCPForm.email.value !="" && !validateEmail(document.OnlineAppCPForm.email.value.trim())){
			alert("Not a valid email.");
			document.OnlineAppCPForm.email.focus();
			return false;
		} else if (document.OnlineAppCPForm.ma_city.value !="" && !name_regex.test(document.OnlineAppCPForm.ma_city.value.trim())){
			alert("Special characters and numbers are not allowed for city.");
			document.OnlineAppCPForm.ma_city.focus();
			return false;
		} else if (document.OnlineAppCPForm.res_city.value !="" && !name_regex.test(document.OnlineAppCPForm.res_city.value.trim())){
			alert("Special characters and numbers are not allowed for city.");
			document.OnlineAppCPForm.res_city.focus();
			return false;
		}else if (document.OnlineAppCPForm.ma_zip.value.trim() != "" && !zip_regex.test(document.OnlineAppCPForm.ma_zip.value)) {
			alert("Zip number should be all digits and 5 digits long");
			document.OnlineAppCPForm.ma_zip.focus();
			return false; 
		} else if (document.OnlineAppCPForm.res_zip.value.trim() != "" && !zip_regex.test(document.OnlineAppCPForm.res_zip.value)) {
			alert("Zip number should be all digits and 5 digits long");
			document.OnlineAppCPForm.res_zip.focus();
			return false; 
		} else if (document.OnlineAppCPForm.emp_zip.value.trim() != "" && !zip_regex.test(document.OnlineAppCPForm.emp_zip.value)) {
			alert("Zip number should be all digits and 5 digits long");
			document.OnlineAppCPForm.emp_zip.focus();
			return false; 
		} else if (document.getElementById('phone_h_val').value.trim() == "y" && !validatePhone(document.getElementById('h_ph1').value, 
			document.getElementById('h_ph2').value, document.getElementById('h_ph3').value)) {
			document.getElementById('h_ph1').focus();
			return false;		
		} else if (document.getElementById('phone_w_val').value.trim() == "y" && !validatePhone(document.getElementById('w_ph1').value, 
			document.getElementById('w_ph2').value, document.getElementById('w_ph3').value)) {
			document.getElementById('w_ph1').focus();
			return false;
		} else if (document.getElementById('phone_c_val').value.trim() == "y" && !validatePhone(document.getElementById('c_ph1').value, 
			document.getElementById('c_ph2').value, document.getElementById('c_ph3').value)) {
			document.getElementById('c_ph1').focus();
			return false;			
		} else if (document.getElementById('emp_chk').value.trim() == "y" && !validatePhone(document.getElementById('emp_ph1').value, 
			document.getElementById('emp_ph2').value, document.getElementById('emp_ph3').value)) {
			document.getElementById('emp_ph1').focus();
			return false;
		} else if (document.getElementById('emp_chk').value.trim() === "n" && document.getElementById('dt_emp_end').value.trim() !== "" 
			&& (!(isValidDate(document.getElementById('dt_emp_end').value, "employment end date")) || !(beforeFutureDate(document.getElementById('dt_emp_end').value, "employment ended date")))) {
			document.getElementById('dt_emp_end').focus();
			return false;		
		}
		
		var income_group = document.getElementsByName("incomeRadio");		
		var selVal = "";

		for (i = 0; i < income_group.length; i++) {
			if (i !== 0) {
				selVal = selVal + ",";
			}
			selVal = selVal + income_group[i].value;
		}
		document.OnlineAppCPForm.incomeTypeLst.value = selVal;
		
		document.OnlineAppCPForm.submit();
		buttonsDisabled(true);
		
		return true;
	}
	
	function goToPreviousPage() {
		if(document.OnlineAppCPForm.applSbmt.value === "false")
 		{
			if (confirm("Unsaved information will be lost. Leave this page?")){        
				location.href = "applicantInfo.htm";
				buttonsDisabled(true);
			}else{
				return false;
			}
		}
		else{
			location.href = "applicantInfo.htm";
		}
	}
	
	function goToNextPage() {
		if(document.OnlineAppCPForm.applSbmt.value === "false")
 		{
			if (confirm("Unsaved information will be lost. Leave this page?")){        
				location.href="caseApplicationChildInfo.htm";
				buttonsDisabled(true);
			}else{
				return false;
			}
		}
		else{
			location.href="caseApplicationChildInfo.htm";
		}
	}

	function empty(str) {
		return !str || !/[^\s]+/.test(str);
	}
	
	function IsNumeric(str){
    	return /^\d*\.?\d*$/.test(str);
	}
	
	function validatePhone(ph1, ph2, ph3) {
		var ph_regex = /^\d{3}$/;
		var ph_regex1 = /^\d{4}$/;
		if ((!empty(ph1) || !empty(ph2) || !empty(ph3))
				&& (empty(ph1) || empty(ph2) || empty(ph3))) {
			alert("Enter complete phone numner in XXX-XXX-XXXX format.");
			return false;
		} else if ((!empty(ph1) && !ph_regex.test(ph1))
				|| (!empty(ph2) && !ph_regex.test(ph2))
				|| (!empty(ph3) && !ph_regex1.test(ph3))) {
			alert("Phone number should be all digits and in XXX-XXX-XXXX format.");
			return false;
		}
		return true;
	}
	

</script>
<form name="OnlineAppCPForm" id="OnlineAppCPForm" method="post"
	action="${pageContext.request.contextPath}/cssp/user/caseApplicationCPInfo.htm"
	modelAttribute="partInfo">
	<input type="hidden" name="partType" value="${partType}"> 
	<input type="hidden" name="partPrntGrdian" value="${partInfo != null ? partInfo.getPartPrntGrdian() : ''}">
	<input type="hidden" name="mode" value="${mode}"> 
	<input type="hidden" name="stepNum" value="${flow.getStepNum()}"> 
	<input type="hidden" name="incomeTypeLst" value="">
	<input type="hidden" name="applSbmt" value="${applSubmitted}">


	<div id=saveAndExitDiv style="display: none" class=center>
		<h1>Are you sure you want to exit?</h1>
		<br /> <input type=button value='Cancel' onclick="hideLightbox()" />&nbsp;
		<input type=button value='Yes, Save & Exit!' onclick="hideLightbox()" />
	</div>
	<div id="aplContent">
		<div class="gutter2">
			<div class="box">
				<h2>SECTION 2- CUSTODIAL PARENT/GUARDIAN INFORMATION</h2>
				<hr />
				<c:if test="${!empty param.message}">
					<div class="error message-box">${param.message}</div>
				</c:if>
				<c:if test="${!empty param.success}">
					<div class="success message-box">${param.success}</div>
				</c:if>
			</div>


			<!-- Demographic Information -->
			<c:if test="${applSubmitted}">
				<div disabled="disabled">
			</c:if>
			<div>
				<table>
					<tr>
						<td><label>First Name: </label>
							<input type="text" title="Enter first name" name="applicantFNm" value="${partInfo != null ? partInfo.getApplicantFNm() : ''}"
							size="15" maxlength="15">
						</td>
						<td>&nbsp;</td>
						<td><label>Middle Name: </label>
							<input type="text" title="Enter middle name or middle initial" name="applicantMNm" value="${partInfo != null ? partInfo.getApplicantMNm() : ''}"
							size="15" maxlength="15">
						</td>
						<td>&nbsp;</td>
						<td><label>Last Name: </label>
							<input type="text" title="Enter last name" name="applicantLNm" value="${partInfo != null ? partInfo.getApplicantLNm() : ''}"
							size="15" maxlength="17"></td>
						<td>&nbsp;</td>
						<td><label>Suffix: </label>
							<select name="applicantSufix" title="Select valid value from pick list">
								<c:forEach var="suffix" items="${suffixLookup}">
									<option value="${suffix.getCodeId()}"
										${partInfo != null && partInfo.getApplicantSufix().equals(suffix.getCodeId()) ? 'selected' : ''}>${suffix.getCodeDesc()}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr><td colspan="7">&nbsp;</td></tr>
					<tr>
						<td><label>Maiden Name: </label>
							<input type="text" title="Enter your name given at birth or prior to marrying" name="applicantMdnNm" value="${partInfo != null ? partInfo.getApplicantMdnNm() : ''}" size="15" maxlength="17">
						</td>	
						<td>&nbsp;</td>
						<td colspan="6"><label>Alias or other names used: </label>
							<input type="text" title="Enter other names or nicknames you currently use or have used in the past" name="applicantAliasNm" value="${partInfo != null ? partInfo.getApplicantAliasNm() : ''}" size="40" maxlength="40">
						</td>
					</tr>
				</table>
				<br>
				<table>
					<tr>
						<td colspan="5">
							<table>
								<tr>
									<td><label>Gender:</label> 
										<select name="applicantSex" title="Select gender from list.">
											<c:forEach var="gender" items="${genderLookup}">
												<option value="${gender.getCodeId()}"
													${partInfo != null && partInfo.getApplicantSex().equals(gender.getCodeId()) ? 'selected' : ''}>${gender.getCodeDesc()}</option>
											</c:forEach>
										</select>
									</td>
									<td>&nbsp;</td>
									<td><label>Date of Birth: </label>
										<input type="text" title="Enter date of birth in format MM/DD/YYYY" name="brthDt" value="${partInfo != null ? partInfo.getBrthDtStrPg() : ''}" size="10"
										maxlength="10" id="brthDt"></td>
									<td>&nbsp;</td>
									<td><label>Social Security No: </label>
										<input type="text" title="Enter your social security number in format XXXXXXXXX" name="ssnNb" value="${partInfo != null ? partInfo.getSsnNb() : ''}" size="9"
										maxlength="9">
									</td>
								<tr>
							</table>
						</td>
					</tr>
					<tr>
						<td colspan="5">&nbsp;</td>
					</tr>
					<tr>
						<td><label>Race:</label> 
							<select name="ethncGrp" title="Select your race from the picklist">
								<c:forEach var="ethncgrp" items="${ethncgrpLookup}">
									<option value="${ethncgrp.getCodeId()}"
										${partInfo != null && partInfo.getEthncGrp().equals(ethncgrp.getCodeId()) ? 'selected' : ''}>${ethncgrp.getCodeDesc()}</option>
								</c:forEach>
							</select>
						</td>
						<td>&nbsp;</td>
						<td colspan="3"><label>Language Preference:</label>
							 <select name="langPref" title="Select your preferred language or the language used most often in your home">
								<c:forEach var="lang" items="${langLookup}">
									<option value="${lang.getCodeId()}"
										${partInfo != null && partInfo.getLangPref().equals(lang.getCodeId()) ? 'selected' : ''}>${lang.getCodeDesc()}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="5">&nbsp;</td>
					</tr>
					<tr>
						<td colspan="5"><label>Email Address: </label>
							<input type="text" name="email" id="email" title="Enter the valid electronic mail address where you wish to receive automated messages regarding your application." name="email" value="${email == null ? '' : email}" size="50" maxlength="50">
						</td>
					</tr>
				</table>
				<br>
				<table>
					<tr>
						<td><label>Special Assistance:</label> 
							<select name="specialAssist" id="specialAssist" onchange="showOther(this)" title="Select type of special assistance needed.">
								<c:forEach var="sassist" items="${sassistLookup}">
									<option value="${sassist.getCodeId()}" ${partInfo != null && partInfo.getSpecialAssist().equals(sassist.getCodeId()) ? 'selected' : ''}>${sassist.getCodeDesc()}</option>
								</c:forEach>
							</select>
						</td>
						<td>&nbsp;</td>
						<td id="specialAssist_ot" style="display: ${partInfo != null && partInfo.getSpecialAssistStr().equals('Other') ? 'block' : 'none'}" class=center>
							<label>Other(explain): </label>
								<input type="text" name="specialAssistOt" id="specialAssistOt" value="${partInfo != null ? partInfo.getSpecialAssistOt() : ''}" size="30" maxlength="50">
						</td>
					</tr>				
				</table>
				
			</div>
			<br>
			<!-- Contact Information -->
			<div>
				<fieldset>
					<legend>
						<b>Contact Information:</b>
					</legend>
					<fieldset>
						<legend title="Enter the street address and City where you receive mail.  Select the State from the pick list.  Enter the 5 digit Zip Code for your Mail address. Check the box if your Residential address is the same as your Mail address.">Mailing Address</legend>
						<table>
							<tr>
								<td><label>Street: </label>
									<input type="text" name="ma_street" value="${mailAddr == null ? '' : mailAddr.getAddrLn1()}" size="40" maxlength="40">
								</td>
								<td>&nbsp;</td>
								<td><label>City: </label>
									<input type="text" name="ma_city" id="ma_city" value="${mailAddr == null ? '' : mailAddr.getAddrCty()}" size="25" maxlength="25">
								</td>
							</tr>
							<tr>
								<td><label>State: </label>
									<select name="ma_state">
										<c:forEach var="state" items="${stateLookup}">
											<option value="${state.getCodeId()}" ${mailAddr != null && mailAddr.getAddrSt().equals(state.getCodeId()) ? 'selected' : ''}>${state.getCodeDesc()}</option>
										</c:forEach>
									</select>
								</td>
								<td>&nbsp;</td>
								<td><label>Zip: </label>
									<input type="text" name="ma_zip" value="${mailAddr == null ? '' : mailAddr.getAddrZip5()}" size="5" maxlength="5">
								</td>
							</tr>
						</table>
					</fieldset>
					<p>
						<input type="checkbox" name="res_addr" value="res_addr" ${resAddr == null  ? 'checked' : ''} onclick="checkResidAddr(this)"> Check if residential address is same as mailing address
					</p>
					<div id="residential" style="${resAddr == null  ? 'display: none' : 'display: block'}" class=center>
						<fieldset>
							<legend title="Enter residential address if different from mailing address.">Residential Address</legend>
							<table>
								<tr>
									<td align="left"><label>Street: </label>
										<input type="text" name="res_street" value="${resAddr == null ? '' : resAddr.getAddrLn1()}" size="40" maxlength="50">
									</td>
									<td>&nbsp;</td>
									<td align="left"><label>City: </label>
										<input type="text" name="res_city" id="res_city" value="${resAddr == null ? '' : resAddr.getAddrCty()}" size="25" maxlength="25">
									</td>
								</tr>
								<tr>
									<td align="left"><label>State: </label>
										<select name="res_state">
											<c:forEach var="state" items="${stateLookup}">
												<option value="${state.getCodeId()}" ${resAddr != null && resAddr.getAddrSt().equals(state.getCodeId()) ? 'selected' : ''}>${state.getCodeDesc()}</option>
											</c:forEach>
										</select>
									</td>
									<td>&nbsp;</td>
									<td align="left"><label>Zip: </label>
										<input type="text" name="res_zip" value="${resAddr == null ? '' : resAddr.getAddrZip5()}" size="5" maxlength="5">
									</td>
								</tr>
							</table>
						</fieldset>
					</div>
					<br>
					<table>
						<tr>
							<td colspan="3"><label>County of Residence: </label> 
								<select name="resCnty" title="Select County from the picklist if you are a North Carolina resident.">
									<c:forEach var="cnty" items="${countyLookup}">
										<option value="${cnty.getCodeId()}" ${partInfo != null && partInfo.getResCnty().equals(cnty.getCodeId()) ? 'selected' : ''}>${cnty.getCodeDesc()}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td colspan="3">&nbsp;</td>
						</tr>
						<tr>
							<td colspan="3"><label>Phone Number: </label> 
								<select id="phone" title="Enter your home, cellular and work phone numbers if applicable.">
									<option value="h" selected>Home</option>
									<option value="w">Work</option>
									<option value="c">Cell</option>
								</select>
								<c:if test="${!applSubmitted}">						
									<input type="button" name="addPhone" value="Add Phone" onclick="return addPhInfo()">
									<input type="button" name="removePhone" value="Remove Phone" onclick="return removePhInfo()">
								</c:if>
							</td>
						</tr>
						<tr><td colspan="3">&nbsp;</td></tr>
						<tr id="phone_h" style="${hPh == null  ? 'display: none' : 'display: block'}">
							<td><input type="radio" id="pcontact" name="pcontact" value="h">
							<label>Home Phone Number:</label></td>
							<td colspan='2'>&nbsp;<input type="text" id="h_ph1" name="h_ph1" value="${hPh == null ? '' : hPh.substring(0, 3)}" size="3" maxlength="3"> - <input type="text" id="h_ph2" name="h_ph2" value="${hPh == null ? '' : hPh.substring(3, 6)}" size="3" maxlength="3"> - <input
								type="text" id="h_ph3" name="h_ph3" value="${hPh == null ? '' : hPh.substring(6, 10)}" size="4" maxlength="4"><input type="hidden" id="phone_h_val" name="phone_h_val" value="${hPh == null  ? '' : 'y'}">
							</td>
						</tr>
						
						<tr id="phone_w" style="${wPh == null  ? 'display: none' : 'display: block'}">
							<td><input type="radio" id="pcontact" name="pcontact" value="w">
							<label>Work Phone Number:</label></td>	
							<td colspan='2'>&nbsp;&nbsp;<input type="text" id="w_ph1" name="w_ph1" value="${wPh == null ? '' : wPh.substring(0, 3)}" size="3" maxlength="3"> - <input type="text" id="w_ph2" name="w_ph2" value="${wPh == null ? '' : wPh.substring(3, 6)}" size="3" maxlength="3"> - <input
								type="text" id="w_ph3" name="w_ph3" value="${wPh == null ? '' : wPh.substring(6, 10)}" size="4" maxlength="4"><input type="hidden" id="phone_w_val" name="phone_w_val" value="${wPh == null  ? '' : 'y'}">
							</td>
						</tr>
						
						<tr id="phone_c" style="${cPh == null  ? 'display: none' : 'display: block'}">
							<td><input type="radio" id="pcontact" name="pcontact" value="c">
							<label>Cell Phone Number:</label></td>
							<td colspan='2'>&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="c_ph1" name="c_ph1" value="${cPh == null ? '' : cPh.substring(0, 3)}" size="3" maxlength="3"> - <input type="text" id="c_ph2" name="c_ph2" value="${cPh == null ? '' : cPh.substring(3, 6)}" size="3" maxlength="3"> - <input
								type="text" id="c_ph3" name="c_ph3" value="${cPh == null ? '' : cPh.substring(6, 10)}" size="4" maxlength="4"><input type="hidden" id="phone_c_val" name="phone_c_val" value="${cPh == null  ? '' : 'y'}">
							</td>
						</tr>
						<tr><td colspan="4">&nbsp;</td></tr>
					</table>
				</fieldset>
			</div>
			<br>
			<!-- Confidentiality info -->
			<div>
				<p>
					<b><u>Confidentiality of Personal Information:</u></b>
				</p>
				<dl class="feature">
					<dt>CSS will use personal information only as allowed by law
						for the purpose of seeking child support. Indicate below whether
						there is reason that your information should not be shared with
						other participants in this case.</dt>					
					<dd>
						<input title="You must select at least one option." type="radio" name="protOrd" value="1"
							${partInfo != null && partInfo.getProtOrd() == '1' ? 'checked' : ''} />A protective
						order has been entered due to domestic violence concerns
					</dd>
					<dd>
						<input title="You must select at least one option." type="radio" name="protOrd" value="2"
							${partInfo != null && partInfo.getProtOrd() == '2' ? 'checked' : ''} />I have
						concerns about my or the child's safety due to circumstances
						involving domestic violence
					</dd>
					<dd>
						<input title="You must select at least one option." type="radio" name="protOrd" value="3"
							${partInfo != null && partInfo.getProtOrd() == '3' ? 'checked' : ''} />I have no
						concerns regarding domestic violence at this time
					</dd>
				</dl>
				<br>
			</div>
			<br>
			<!-- Income Information -->
			<div id="incomeInfo" style="${partInfo != null && partInfo.getPartPrntGrdian().equals('1')  ? 'display: block' : 'display: none'}" class=center>
				<div>
					<p align="left">
						<b><u>Income Information:</u></b>
					</p>
					<p align="left">
						<b>Is the custodial parent currently employed? </b> 
						<select name="emp_chk" id="emp_chk" onchange="toggleEmp(this)" title="Select 'Yes' or 'No'. Enter additional information after selection.">
							<option value="y" ${empInfo.getThirdPartyInfo().getThirdPartyType().equals('2') || empInfo.getThirdPartyInfo().getThirdPartyType().trim().equals('') ? 'selected' : ''}>Yes</option>
							<option value="n" ${empInfo != mull && empInfo.getThirdPartyInfo().getThirdPartyType().equals('3') ? 'selected' : ''}>No</option>
						</select>
					</p>
				</div>
				<!-- current employer -->
				<div id="currEmp" style="display: ${empInfo.getThirdPartyInfo().getThirdPartyType().trim().equals('2') || empInfo.getThirdPartyInfo().getThirdPartyType().trim().equals('') ? 'block' : 'none'}" class=center>
					<fieldset>
						<legend>
							<b>Current Employer</b>
						</legend>
						<table>
							<tr>
								<td colspan="5" align="left">
									<label>Name: </label>
									<input type="text" name="curr_emp" value="${empInfo != null ? empInfo.getThirdPartyInfo().getThirdPartyNm() : ''}" size="40" maxlength="40">
								</td>
							</tr>		
							<tr>
								<td colspan="5" align="left">
									<label>Street: </label>
									<input type="text" name="emp_street" value="${empInfo != null ? empInfo.getThirdPartyInfo().getAddressLn1() : ''}" size="40" maxlength="40">
								</td>
							</tr>
							<tr>
								<td>	
									<label>City:</label>
									<input type="text" name="emp_city" value="${empInfo != null ? empInfo.getThirdPartyInfo().getThirdPartyCity() : ''}" size="25" maxlength="25">
								</td>
								<td>&nbsp;</td>
								<td align="left"><label>State: </label>
										<select name="emp_state">
											<c:forEach var="state" items="${stateLookup}">
												<option value="${state.getCodeId()}" ${empInfo != null && empInfo.getThirdPartyInfo().getThirdPartyState().trim().equals(state.getCodeId()) ? 'selected' : ''}>${state.getCodeDesc()}</option>
											</c:forEach>
										</select>
										</td>
								<td>&nbsp;</td>
								<td>
									<label>Zip:</label>
									<input type="text" name="emp_zip" value="${empInfo != null ? empInfo.getThirdPartyInfo().getThirdPartyZip5() : ''}" size="5" maxlength="5">
								</td>
							</tr>
							
							<tr>
								<td colspan="5" align="left">
									<label>Phone Number:</label>
									<input type="text" name="emp_ph1" id="emp_ph1" value="${empInfo != null ? empInfo.getThirdPartyInfo().getPhoneAreaCode() : ''}" size="3"
									maxlength="3"> - <input type="text" name="emp_ph2" id="emp_ph2" value="${empInfo != null ? empInfo.getThirdPartyInfo().getPhoneExc() : ''}" size="3" maxlength="3"> - <input
									type="text" name="emp_ph3" id="emp_ph3" value="${empInfo != null ? empInfo.getThirdPartyInfo().getPhoneLn() : ''}" size="4" maxlength="4">
								</td>
							</tr>
						</table>
					</fieldset>
				</div>
				<!-- last known employer -->
				<div id="lastEmp" style="display: ${empInfo != null && empInfo.getThirdPartyInfo().getThirdPartyType().equals('3') ? 'block' : 'none'}" class=center>
					<fieldset>
						<legend>
							<b>Last Known Employer</b>
						</legend>
						<table>
							<tr>
								<td colspan="3" align="left">
									<label>Name: </label>
									<input type="text" name="last_emp" id="last_emp" value="${empInfo != null ? empInfo.getThirdPartyInfo().getThirdPartyNm() : ''}" size="40" maxlength="40">
								</td>
							</tr>
							<tr>
								<td>
									<label>Date employment ended: </label>
									<input type="text" name="dt_emp_end" id="dt_emp_end" value="${empInfo != null ? empInfo.getEmplmEndDtPgStr() : ''}" size="10" maxlength="10">
								</td>
								<td>&nbsp;</td>
								<td>
									<label>Reason: </label>
									<input type="text" name="end_reason" value="${empInfo != null ? empInfo.getEmplmEndReasn() : ''}" size="50" maxlength="100">
								</td>
							</tr>
							<tr>
								<td colspan="3" align="left">
									<label>Usual Occupation: </label>
									<input type="text" name="occupation" value="${empInfo != null ? empInfo.getOcupation() : ''}" size="50" maxlength="100">
								</td>
							</tr>
						</table>
					</fieldset>
				</div>
				<br><br>
				<div>
					<p align="left">
						<b>Select income source from list and
							enter income amount below:</b>
					</p>
					<table cellpadding="5">
						<tr>
							<td><label>Income Source: </label> <select id="incomeType" title="Select all sources of income.">
									<c:forEach var="incomeType" items="${incomeLookup}">
										<option value="${incomeType.getCodeId()}">${incomeType.getCodeDesc()}</option>
									</c:forEach>
								</select>
							</td>
							<td><label title="Enter gross monthly income amount in format 1000 or 1000.00">Monthly Amount: </label><input type="text" id="incomeAmt"
								value="" size="10" maxlength="8" title="Enter gross monthly income amount in format 1000 or 1000.00">
								<c:if test="${!applSubmitted}"> 
									&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value='Add Income Source' onclick="return addIncomeType()" />
								</c:if>
							</td>
							<td></td>
						</tr>
						<tr>
							<td colspan="3">&nbsp;</td>
						</tr>
						<tr>
							<td align="center" colspan="2">
								<table id="incomeTable" border="1">
									<tr>
										<th>&nbsp;</th>
										<th width="40%">Income Source</th>
										<th  width="50%">Amount (monthly gross)$</th>
									</tr>
									<c:if test="${incList != null && incList.size() > 0}">
									<c:forEach var="income" items="${incList}" varStatus="tc">
										<tr id="income_${income.getIncomeSrc()}">
											<td>
												<!--  <label>${income.getIncomeSrcDesc()} </label>--> <input
												type="radio" id="incomeRadio" name="incomeRadio"
												value="${income.getIncomeSrc()}">
											</td>
											<td><label>${income.getIncomeSrcDesc()} </label></td>
											<td><input type="text"
												id="income_val_${income.getIncomeSrc()}"
												name="income_val_${income.getIncomeSrc()}"
												value="${income.getIncomeAmtStr()}" size="15" maxlength="15" readonly="readonly"></td>
										</tr>
									</c:forEach>
									</c:if>
								</table>
							</td>
							<td>
								<c:if test="${!applSubmitted}">
								 	<input type="button" value='Delete Income Source' onclick="return deleteIncomeType()">
								 </c:if>
							</td>
						</tr>
						<tr>
							<td colspan="2">&nbsp;</td>
						</tr>
						<tr>
							<td colspan="2">&nbsp;</td>
						</tr>
						<tr>
							<td colspan="2" align="left"><label>Total Monthly Gross Income Amount: </label>
							<input type="text" title="This amount is the sum of all monthly gross income sources you reported" name="income" id="income" value="${partInfo != null ? partInfo.getIncomeStr() : '0.00'}" size="25" maxlength="25"  readonly="readonly"></td>
						</tr>
					</table>
				</div>
			</div>
			<br><br>
			<c:if test="${applSubmitted}">
				</div>
			</c:if>
			<div>
				<table width="70%">
					<tr>
						<td>&nbsp;&nbsp;</td><td>&nbsp;&nbsp;</td>
						<td align="left"><input type=button value='Previous' onclick="goToPreviousPage()" /></td>
						<c:if test="${!applSubmitted}">
							<td>&nbsp;&nbsp;</td><td>&nbsp;&nbsp;</td>
							<td align="center"><input type="button" name="saveButton" id="save" value="Save" onclick="return validateform()"></td>
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
