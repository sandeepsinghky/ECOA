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
		$( "#maDt" ).datepicker({
	    	changeMonth: true,
	    	changeYear: true,
	    	yearRange: "-100:+10"
    	});
	});
	
	$(function() {
		$( "#paterntyTstDt" ).datepicker({
	    	changeMonth: true,
	    	changeYear: true,
	    	yearRange: "-100:+10"
    	});
	});
	
	$(function() {
		$( "#paterntyEstDt" ).datepicker({
	    	changeMonth: true,
	    	changeYear: true,
	    	yearRange: "-100:+10"
    	});
	});
	
	function noEnterKey(evt) {
      var evt = (evt) ? evt : ((event) ? event : null);
      if (evt.keyCode == 13)  {
          return false;
      }
    }
	document.onkeypress = noEnterKey;
	
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
	
	
	function toggleInfo(selectObject)
	{
		var id = selectObject.id+"_info";
		var value = selectObject.value;
		if (value=="Y"){
			document.getElementById(id).style.display='block';
		}
		if (value=="N"){
			document.getElementById(id).style.display='none';
		}
	}
	
	function toggleSel(selectObject)
	{
		var id = selectObject.id+"_info";
		var value = selectObject.value;
		if (value=="1"){
			document.getElementById(id).style.display='block';
		}
		else{
			document.getElementById(id).style.display='none';
		}
	}
	
	function toggleWedlock(selectObject)
	{
		var id = selectObject.id+"_info";
		var value = selectObject.value;
		if (value=="2"){
			document.getElementById(id).style.display='block';
		}
		if (value=="0" || value=="1" || value=="3"){
			document.getElementById(id).style.display='none';
		}
	}
	
	function toggleReside(selectObject)
	{
		var value = selectObject.value;
		var id = selectObject.id+"_"+value;
		if (value=="Y"){
			document.getElementById(selectObject.id+"_N").style.display='none';
			document.getElementById(id).style.display='block';
			
		}
		if (value=="N"){
			document.getElementById(selectObject.id+"_Y").style.display='none';
			document.getElementById(id).style.display='block';
		}
	}
	
	function toggleMarital(selectObject)
	{
		var value = selectObject.value;
		var id = selectObject.id+"_info";					
			
		if (value =="5" || value =="4" || value =="0"){
			document.getElementById(selectObject.id+"_info").style.display='none';
		}else{
			document.getElementById(selectObject.id+"_info").style.display='block';
		}
			
	}
	
	function ShowVeteran(checkbox)
	{
		if (checkbox.checked){
			document.getElementById(checkbox.id+"_info").style.display='block';
		}
		else{
			document.getElementById('vfname').value = " ";
			document.getElementById('vlname').value = " ";
			document.getElementById(checkbox.id+"_info").style.display='none';
		}
	
	}
	
	function addNCP() {
		var name_regex = /^[a-zA-Z ]+$/;
		if ((document.getElementById("ncpF").value).trim() == "") {
			alert("Enter NCP First name.");
			document.getElementById("ncpF").focus();
			return false;
		}
		
		if ((document.getElementById("ncpL").value).trim() == "") {
			alert("Enter NCP Last name.");
			document.getElementById("ncpL").focus();
			return false;
		}
		
		if ((document.getElementById("ncpF").value).trim() != "" && !name_regex.test(document.getElementById("ncpF").value.trim())) {
			alert("Special characters and numbers are not allowed in NCP first name.");
			document.getElementById("ncpF").focus();
			return false;
		}
		
		if ((document.getElementById("ncpL").value).trim() != "" && !name_regex.test(document.getElementById("ncpL").value.trim())) {
			alert("Special characters and numbers are not allowed in NCP last name.");
			document.getElementById("ncpL").focus();
			return false;
		}


			var ncpTable = document.getElementById('ncpaffilTable');
			var rowCount = ncpTable.rows.length;
			var trow = ncpTable.insertRow(ncpTable.rows.length);
			trow.id = "ncp" + rowCount;

			var tcell0 = trow.insertCell(0);
			var tcell1 = trow.insertCell(1);
			var tcell2 = trow.insertCell(2);

			var radio = document.createElement("input");
			radio.type = "radio"
			radio.name = "ncpRadio";
			radio.id = "ncpRadio";
			radio.value = rowCount;
			tcell0.appendChild(radio);
			

			var fName = document.createElement("input");
			fName.id = "NCP_fName_" + rowCount;
			fName.name = "NCP_fName_" + rowCount;
			fName.value = document.getElementById("ncpF").value;
			document.getElementById("ncpF").value = "";
			tcell1.appendChild(fName);
			
			var lName = document.createElement("input");
			lName.id = "NCP_lName_" + rowCount;
			lName.name = "NCP_lName_" + rowCount;
			lName.value = document.getElementById("ncpL").value;
			document.getElementById("ncpL").value = "";
			tcell2.appendChild(lName);

	}
	
	function removeNCP() {
		var selected = false;
		var selVal;

		for (i = 0; i < document.getElementsByName("ncpRadio").length; i++) {
			if (document.getElementsByName("ncpRadio")[i].checked) {
				selected = true;
				selVal = document.getElementsByName("ncpRadio")[i].value;
				break;
			}
		}
		if (!selected) {
			alert("Select NCP to be removed by selecting the radio button.");
			return false;
		}

		//delete row
		if (confirm("Are you sure you want to delete the selected NCP from the added list?")) {
			var trow = document.getElementById("ncp" + selVal);
			trow.parentNode.removeChild(trow);
		} else {
			return false;
		}
		return true;
	}

	function addInsurance() {
  		var checked = false;
  		var insTable = document.getElementById("insTable");
	  	for (i = 0; i < document.getElementsByName("healthCov").length; i++) 
	  	{
	  		if(document.getElementsByName("healthCov")[i].checked==true)
	  		{
	  			//checked = true;
	  			var selVal = document.getElementsByName("healthCov")[i].value;
	  			var selRow = "ins_" + selVal;
	  			if (document.getElementById(selRow) == null) 
	  			{					
					checked = true;
					//document.getElementById("hcoverage_table").style.display='block';
					var trow = insTable.insertRow(insTable.rows.length);
					trow.id = "ins_" + selVal;
					
					var tcell0 = trow.insertCell(0);
					var tcell1 = trow.insertCell(1);
					var tcell2 = trow.insertCell(2);
					var tcell3 = trow.insertCell(3);
					var tcell4 = trow.insertCell(4);
					
					var label = document.createElement("label");
					label.innerHTML = document.getElementsByName("healthCov")[i].id;
					tcell0.appendChild(label);
					
					var providerVal = document.createElement("input");
					providerVal.id = "insProvider_" + selVal;
					providerVal.name = "insProvider_" + selVal;
					providerVal.size='40';
					providerVal.value = document.getElementById("insProvider").value;
					tcell1.appendChild(providerVal);
					document.getElementById("insProvider_"+selVal).setAttribute('maxlength',40);
					
					
					var holderValF = document.createElement("input");
					holderValF.id = "holderNmF_" + selVal;
					holderValF.name = "holderNmF_" + selVal;
					holderValF.size='15';
					holderValF.value = document.getElementById("holderNmF").value;
					tcell2.appendChild(holderValF);
					document.getElementById("holderNmF_"+selVal).setAttribute('maxlength',15);
					
					var holderValL = document.createElement("input");
					holderValL.id = "holderNmL_" + selVal;
					holderValL.name = "holderNmL_" + selVal;
					holderValL.size='15';
					holderValL.maxlength='17';
					holderValL.value = document.getElementById("holderNmL").value;
					tcell3.appendChild(holderValL);
					document.getElementById("holderNmL_"+selVal).setAttribute('maxlength',17);
					
					var relCode = document.OnlineAppChildForm.famRel.value;
					var res = relCode.split(",");
					
					var relVal = document.createElement("select");
					relVal.id = "holderRel_" + selVal;
					relVal.name = "holderRel_" + selVal;
					for(j=0; j < res.length; j++){
						var option1 = document.createElement("option");
						option1.value=j;
						option1.text=res[j];
						relVal.add(option1, relVal.options[null]);
					}
					
					relVal.value = document.getElementById("holderRel").value;
					tcell4.appendChild(relVal);
	  			}
	  			else{
	  				//alert("Insurance already added");
	  			}
	  			
	  		}
	  		
	  	}
	  	if(!checked){
	  		alert("Enter insurance details then select 'Add Insurance'");
	  		document.getElementsByName("healthCov")[0].focus();
	  		return false();
	  	}
	  	document.getElementById("insProvider").value = "";
	  	document.getElementById("holderNmF").value = "";
	  	document.getElementById("holderNmL").value = "";
	  	document.getElementById("holderRel").value = "0";
	  	document.getElementById('hcoverage_details').style.display='none';

	}
	
	function checkInsurance(checkbox) {
		if (!checkbox.checked) {
			var insVal = checkbox.value;
			var trow = document.getElementById("ins_" + insVal);
			if(trow != null){
				if (confirm("Confirm removal of insurance information?")) {
					trow.parentNode.removeChild(trow);
				}else{
					checkbox.checked = "checked";
				}
			} 
			
			var checked = false;
			for (i = 0; i < document.getElementsByName("healthCov").length; i++) 
		  	{
		  		if(document.getElementsByName("healthCov")[i].checked==true)
		  		{
		  			var selVal = document.getElementsByName("healthCov")[i].value;
		  			var selRow = "ins_" + selVal;
		  			if (document.getElementById(selRow) == null) {
		  				checked = true;
		  				break;
					}
				}
			}
			if (!checked){
				if(document.getElementById('hcoverage_details').style.display=='block'){
					document.getElementById('hcoverage_details').style.display='none';
				}
			}			
		} else{
			if(document.getElementById('hcoverage_details').style.display=='none'){
				document.getElementById('hcoverage_details').style.display='block';
				alert("Enter insurance details then select 'Add Insurance'.");
			}
		}

	}
	
		
	function validateform() {
	
		var ssn = document.OnlineAppChildForm.ssnNb.value;
		var date_regex = /^(0[1-9]|1[0-2])\/(0[1-9]|1\d|2\d|3[01])\/(19|20)\d{2}$/;	
		var ssn_regex = /^\d{9}$/;
		var zip_regex = /^\d{5}$/;
		var name_regex = /^[a-zA-Z ]+$/;	
		
		if (document.OnlineAppChildForm.applicantFNm.value == ""){
			alert("First Name should not be empty");
			document.OnlineAppChildForm.applicantFNm.focus();
			return false;			
		}
		else if (document.OnlineAppChildForm.applicantLNm.value == ""){
			alert("Last Name should not be empty");
			document.OnlineAppChildForm.applicantLNm.focus();
			return false;		
		}
		else if (!name_regex.test(document.OnlineAppChildForm.applicantFNm.value.trim())){
			alert("Special characters and numbers are not allowed in first name.");
			document.OnlineAppChildForm.applicantFNm.focus();
			return false;
		} else if (document.OnlineAppChildForm.applicantMNm.value !="" && !name_regex.test(document.OnlineAppChildForm.applicantMNm.value.trim())){
			alert("Special characters and numbers are not allowed in middle name.");
			document.OnlineAppChildForm.applicantMNm.focus();
			return false;
		} else if (!name_regex.test(document.OnlineAppChildForm.applicantLNm.value.trim())){
			alert("Special characters and numbers are not allowed in last name.");
			document.OnlineAppChildForm.applicantLNm.focus();
			return false;
		}
		else if (ssn.trim() != "" && !ssn_regex.test(ssn)){
			alert("SSN should be all numbers in format '123456789'");
			document.OnlineAppChildForm.ssnNb.focus();
			return false;		
		}
		else if(document.OnlineAppChildForm.brthDt.value.trim() != "" && (!(isValidDate(document.OnlineAppChildForm.brthDt.value, "date of birth")) || !beforeFutureDate(document.OnlineAppChildForm.brthDt.value, "date of birth")))
		{
			document.OnlineAppChildForm.brthDt.focus();
			return false;	
		}
		else if (document.getElementById('reside').value.trim() == "Y") {
			if (document.OnlineAppChildForm.ntsYr.value.trim() != "" && !IsNumeric(document.OnlineAppChildForm.ntsYr.value.trim())) 
			{
				alert("Enter numeric value for nights per year child spends in the home.");
				document.OnlineAppChildForm.ntsYr.focus();
				return false;
			}
			else if (document.OnlineAppChildForm.ntsYr.value.trim() > 365){
				alert("Nights per year cannot be more than 365");
				document.OnlineAppChildForm.ntsYr.focus();
				return false;
			}
			else if (document.OnlineAppChildForm.yrs.value.trim() != "" && !IsNumeric(document.OnlineAppChildForm.yrs.value.trim())) {
				alert("Enter numeric value for the number of years in home.");
				document.OnlineAppChildForm.yrs.focus();
				return false;
			}
			else if (document.OnlineAppChildForm.mths.value.trim() != "" && !IsNumeric(document.OnlineAppChildForm.mths.value.trim())) {
				alert("Enter numeric value for the number of months in home.");
				document.OnlineAppChildForm.mths.focus();
				return false;
			}
		}
		else if (document.getElementById('reside').value.trim() == "N") {
			if (document.OnlineAppChildForm.other_zip.value.trim() != "" && !zip_regex.test(document.OnlineAppChildForm.other_zip.value)) {
				alert("Zip number should be all digits and 5 digits long");
				document.OnlineAppChildForm.other_zip.focus();
				result = false; 
			}
			if (!validatePhone(document.getElementById('ot_ph1').value, 
				document.getElementById('ot_ph2').value, document.getElementById('ot_ph3').value)) {
				document.getElementById('ot_ph1').focus();
				return false;
			}
			if (document.OnlineAppChildForm.chldResfname.value !="" && !name_regex.test(document.OnlineAppChildForm.chldResfname.value.trim())){
				alert("Special characters and numbers are not allowed in first name.");
				document.OnlineAppChildForm.chldResfname.focus();
				return false;
			} 
			if (document.OnlineAppChildForm.chldReslname.value !="" && !name_regex.test(document.OnlineAppChildForm.chldReslname.value.trim())){
				alert("Special characters and numbers are not allowed in last name.");
				document.OnlineAppChildForm.chldReslname.focus();
				return false;
			}
			if (document.OnlineAppChildForm.other_city.value !="" && !name_regex.test(document.OnlineAppChildForm.other_city.value.trim())){
				alert("Special characters and numbers are not allowed in city.");
				document.OnlineAppChildForm.other_city.focus();
				return false;
			} 
		}
		
		if (document.OnlineAppChildForm.chldPrnt1fname.value !="" && !name_regex.test(document.OnlineAppChildForm.chldPrnt1fname.value.trim())){
			alert("Special characters and numbers are not allowed in Parent1 first name.");
			document.OnlineAppChildForm.chldPrnt1fname.focus();
			return false;
		} 
		else if (document.OnlineAppChildForm.chldPrnt1mname.value !="" && !name_regex.test(document.OnlineAppChildForm.chldPrnt1mname.value.trim())){
			alert("Special characters and numbers are not allowed in Parent1 middle name.");
			document.OnlineAppChildForm.chldPrnt1mname.focus();
			return false;
		}
		else if (document.OnlineAppChildForm.chldPrnt1lname.value !="" && !name_regex.test(document.OnlineAppChildForm.chldPrnt1lname.value.trim())){
			alert("Special characters and numbers are not allowed in Parent1 last name.");
			document.OnlineAppChildForm.chldPrnt1lname.focus();
			return false;
		}
		else if (document.OnlineAppChildForm.chldPrnt2fname.value !="" && !name_regex.test(document.OnlineAppChildForm.chldPrnt2fname.value.trim())){
			alert("Special characters and numbers are not allowed in Parent2 first name.");
			document.OnlineAppChildForm.chldPrnt2fname.focus();
			return false;
		} 
		else if (document.OnlineAppChildForm.chldPrnt2mname.value !="" && !name_regex.test(document.OnlineAppChildForm.chldPrnt2mname.value.trim())){
			alert("Special characters and numbers are not allowed in Parent2 middle name.");
			document.OnlineAppChildForm.chldPrnt2mname.focus();
			return false;
		}
		else if (document.OnlineAppChildForm.chldPrnt2lname.value !="" && !name_regex.test(document.OnlineAppChildForm.chldPrnt2lname.value.trim())){
			alert("Special characters and numbers are not allowed in Parent2 last name.");
			document.OnlineAppChildForm.chldPrnt2lname.focus();
			return false;
		}
		
		
		if(document.OnlineAppChildForm.paterntyTstDt.value.trim() != "" && (!(isValidDate(document.OnlineAppChildForm.paterntyTstDt.value, "paternity test date")) || !checkForFutureDate(document.OnlineAppChildForm.paterntyTstDt.value, "paternity test date")))
		{
			document.OnlineAppChildForm.paterntyTstDt.focus();
			return false;	
		}
		else if(document.OnlineAppChildForm.paterntyEstDt.value.trim() != "" && (!(isValidDate(document.OnlineAppChildForm.paterntyEstDt.value, "paternity established date")) || !checkForFutureDate(document.OnlineAppChildForm.paterntyEstDt.value, "paternity established date")))
		{
			document.OnlineAppChildForm.paterntyEstDt.focus();
			return false;
		}
		else if(document.OnlineAppChildForm.brthDt.value.trim() != "" && document.OnlineAppChildForm.paterntyTstDt.value.trim() != "" && !isDateFromBeforeDateTo(document.OnlineAppChildForm.brthDt.value.trim(), document.OnlineAppChildForm.paterntyTstDt.value.trim()))
		{
			alert("Date of birth should not be later than paternity test date");
			document.OnlineAppChildForm.paterntyTstDt.focus();
			return false;
		}
		else if(document.OnlineAppChildForm.brthDt.value.trim() != "" && document.OnlineAppChildForm.paterntyEstDt.value.trim() != "" && !isDateFromBeforeDateTo(document.OnlineAppChildForm.brthDt.value.trim(), document.OnlineAppChildForm.paterntyEstDt.value.trim()))
		{
			alert("Date of birth should not be later than paternity establishment date");
			document.OnlineAppChildForm.paterntyEstDt.focus();
			return false;
		}
		else if(document.OnlineAppChildForm.maDt.value.trim() != "" && (!(isValidDate(document.OnlineAppChildForm.maDt.value, "parent's relationship date")) || !checkForFutureDate(document.OnlineAppChildForm.maDt.value, "parent's relationship date")))
		{
			document.OnlineAppChildForm.maDt.focus();
			return false;
		}
		
		
		
		//new NCP info to be addded and linked to child
		var selVal = "";
		for (i = 0; i < document.getElementsByName("ncpRadio").length; i++) {
			if (i != 0) {
				selVal = selVal + ",";
			}
			selVal = selVal
					+ document.getElementsByName("ncpRadio")[i].value;
		}
		document.OnlineAppChildForm.ncpToAddLst.value = selVal;
		
	
		if(document.getElementById('hcoverage').value === '1'){
			for (i = 0; i < document.getElementsByName("healthCov").length; i++) 
		  	{
		  		if(document.getElementsByName("healthCov")[i].checked==true)
		  		{
		  			var selVal = document.getElementsByName("healthCov")[i].value;
		  			var selRow = "ins_" + selVal;
		  			if (document.getElementById(selRow) == null) 
		  			{
		  				alert("Enter insurance details then select 'Add Insurance'.");
		  				document.getElementsByName("healthCov")[i].focus();
		  				return false;
		  			}
		  		}
		  	}
		}	
		document.OnlineAppChildForm.submit();
		buttonsDisabled(true);
		return true;
 	}
 	
 	function IsNumeric(str){
    	return /^\d*\.?\d*$/.test(str);
	}
	
	function goToPreviousPage(){
		if(document.OnlineAppChildForm.applSbmt.value === "false")
 		{
			if (confirm("Unsaved information will be lost. Leave this page?")){        
				location.href = "caseApplicationChildInfo.htm";
				buttonsDisabled(true);
			}else{
				return false;
			}
		}else{
			location.href = "caseApplicationChildInfo.htm";
		}
	}
	
	function empty(str) {
		return !str || !/[^\s]+/.test(str);
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
	
	function checkForFutureDate(myDate, field)
 	{
		var today = new Date();
	
     	if (new Date(myDate)>today)
     	{
     		alert("You cannot enter a date in the future for "+field);
    		 return false;
     	 }
     	return true;
 	}
	
	
	
</script>

<form name="OnlineAppChildForm" id="OnlineAppChildForm" method="post" action="${pageContext.request.contextPath}/cssp/user/caseApplicationChildDetails.htm"  modelAttribute="partInfo">
	<input type="hidden" name="partType" value="3">
	<input type="hidden" name="partPrntGrdian" value="${partInfo != null ? partInfo.getPartPrntGrdian() : ''}">
	<input type="hidden" name="mode" value="${mode}">
	<input type="hidden" name="stepNum" value="${flow.getStepNum()}">
	<input type="hidden" name="ncpToAddLst" value="">
	<input type="hidden" name="famRel" value="${famRelStr}">
	<input type="hidden" name="applSbmt" value="${applSubmitted}">
	<input type="hidden" name="applicantMdnNm" value="${partInfo.getApplicantMdnNm()}">
	<input type="hidden" name="applicantAliasNm" value="${partInfo.getApplicantAliasNm()}">
	<input type="hidden" name="protOrd" value="${partInfo.getProtOrd()}">
	<input type="hidden" name="resCnty" value="${partInfo.getResCnty()}">
	
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
			<!-- Demographic Information -->
			<c:if test="${applSubmitted}">
				<div disabled="disabled">
			</c:if>
			<div>
				<table>
					<tr>
						<td><label>First Name: </label>
							<input title="Enter first name" type="text" name="applicantFNm" value="${partInfo != null ? partInfo.getApplicantFNm() : ''}" size="15" maxlength="15">
						</td>
						<td>&nbsp;</td>
						<td><label>Middle Name: </label>
							<input title="Enter middle name or middle initial" type="text" name="applicantMNm" value="${partInfo != null ? partInfo.getApplicantMNm() : ''}" size="15" maxlength="15">
						</td>
						<td>&nbsp;</td>
						<td><label>Last Name: </label>
							<input title="Enter last name" type="text" name="applicantLNm" value="${partInfo != null ? partInfo.getApplicantLNm() : ''}" size="15" maxlength="17">
						</td>
						<td>&nbsp;</td>
						<td><label>Suffix: </label>
							<select name="applicantSufix" title="Select child's suffix from list.">
							<c:forEach var="suffix" items="${suffixLookup}">
								<option value="${suffix.getCodeId()}" ${partInfo != null && partInfo.getApplicantSufix().equals(suffix.getCodeId()) ? 'selected' : ''}>${suffix.getCodeDesc()}</option>
							</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
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
								<option value="${gender.getCodeId()}" ${partInfo != null && partInfo.getApplicantSex().equals(gender.getCodeId()) ? 'selected' : ''}>${gender.getCodeDesc()}</option>
							</c:forEach>
							</select>
						</td>
						<td>&nbsp;</td>
						<td><label>Date Of Birth: </label>
							<input type="text" title="Enter child's date of birth in MM/DD/YYYY format" name="brthDt" value="${partInfo != null ? partInfo.getBrthDtStrPg() : ""}" size="10"
							maxlength="10" id="brthDt">
						</td>
						<td>&nbsp;</td>
						<td><label colspan="2">Social Security No: </label>
							<input title="Enter 9 digit SSN" type="text" name="ssnNb" value="${partInfo != null ? partInfo.getSsnNb() : ""}" size="9" maxlength="9"> 
						</td>
					</tr>
					</table>
						</td>
					</tr>
					<tr>
						<td colspan="5">&nbsp;</td>
					</tr>
					<tr>
						<td colspan="2"><label>Race:</label> <select name="ethncGrp" title="Select your race from the picklist">
							<c:forEach var="ethncgrp" items="${ethncgrpLookup}">
								<option value="${ethncgrp.getCodeId()}" ${partInfo != null && partInfo.getEthncGrp().equals(ethncgrp.getCodeId()) ? 'selected' : ''}>${ethncgrp.getCodeDesc()}</option>
							</c:forEach>
							</select>
						</td>
						<td>&nbsp;</td>
						<td colspan="2"><label>Language Preference:</label> <select name="langPref" title="Select your preferred language or the language used most often in your home">
							<c:forEach var="lang" items="${langLookup}">
								<option value="${lang.getCodeId()}" ${partInfo != null && partInfo.getLangPref().equals(lang.getCodeId()) ? 'selected' : ''}>${lang.getCodeDesc()}</option>
							</c:forEach>	
						</select></td>
					</tr>
				</table>
				<br>
				<table>
					<tr>
						<td>
							<label>Special Assistance:</label> 
							<select name="specialAssist" id="specialAssist" onchange="showOther(this)" title="Select type of special assistance needed.">
								<c:forEach var="sassist" items="${sassistLookup}">
									<option value="${sassist.getCodeId()}" ${partInfo != null && partInfo.getSpecialAssist().equals(sassist.getCodeId()) ? 'selected' : ''}>${sassist.getCodeDesc()}</option>
								</c:forEach>
							</select>
						</td>
						<td>&nbsp;</td>
						<td id="specialAssist_ot" style="display: ${partInfo != null && partInfo.getSpecialAssistStr().equals("Other") ? 'block' : 'none'}" class=center>
							<label>Other(explain): </label>
								<input type="text" name="specialAssistOt"  id="specialAssistOt" value="${partInfo != null ? partInfo.getSpecialAssistOt() : ""}" size="30" maxlength="40">
						</td>	
					</tr>			
				</table>
				<br>
				<table>
					<tr>
						<td>
							<label>What is your relationship to this child?</label> 
							<select name="relation" id="relation" onchange="showOther(this)" title="Select relationship from pick list. You may enter additional information if 'Other' is selected">
								<c:forEach var="relation" items="${famlrelLookup}">
									<option value="${relation.getCodeId()}" ${partProfile != null && partProfile.getFamRel().equals(relation.getCodeId()) ? 'selected' : ''}>${relation.getCodeDesc()}</option>
								</c:forEach>
							</select>
						</td>
						<td>&nbsp;</td>
						<td id="relation_ot" style="display: ${partProfile != null && partProfile.getFamRel().equals("4") ? 'block' : 'none'}">
							<label>Other(Specify relationship): <label>
								<input type="text" name="otherRel" value="${partProfile != null ? partProfile.getRelOther() : ""}" size="30" maxlength="40">
						</td>	
					</tr>			
				</table>
			
				<br>
				<fieldset>
					<p>
						<label><b>Does the child live with you?</b></label>&nbsp; 
							<select name="reside" id="reside" onchange="toggleReside(this)" title="Make your selection from the picklist. You may provide additional information if 'yes'">
								<option value="Y" ${partProfile != null && partProfile.getChldLiveWith().equalsIgnoreCase('y') ? 'selected' : ''}>Yes</option>
								<option value="N" ${partProfile != null && partProfile.getChldLiveWith().equalsIgnoreCase('n') ? 'selected' : ''}>No</option>
							</select>
					<div id="reside_Y" style="display: ${(partProfile == null) || partProfile != null && !partProfile.getChldLiveWith().equalsIgnoreCase('n') ? 'block' : 'none'}">
						<table>
							<tr>
								<td><label>How many nights per year <br> does the child spend in the home?
								</label></td>
								<td>&nbsp;</td>
								<td><input type="text" name="ntsYr" id="ntsYr" value="${partProfile.getNtsYr()}" size="10" maxlength="3" title="Enter a number from 1 to 365."></td>
								<td>&nbsp;</td>
							</tr>
							<tr><td colspan='3'>&nbsp;</td></tr>
							<tr>
								<td><label>How long has the child been <br> in the home?
								</label>
								</td><td>&nbsp;</td>
								<td><input type="text" name="yrs" value="${partProfile.getNumYr()}" size="10" maxlength="4"> Years</td>
								<td><input type="text" name="mths" value="${partProfile.getNumMo()}" size="10" maxlength="4"> Months</td>
							</tr>
						</table>
					</div>
					<div id="reside_N" style="display: ${partProfile.getChldLiveWith().equalsIgnoreCase('n') ? 'block' : 'none'}" class=center>
						<p align="left">With whom does the child live?</p>
						<table>
							<tr>
								<td align="left"><label>First Name:</label></td>
								<td align="left"><input type="text" name="chldResfname" value="${partOtherMap.containsKey('7') ? partOtherMap.get('7').getFirstNm() : ''}" size="15" maxlength="15"></td>
								<td>&nbsp;</td>
								<td align="left"><label>Last Name:</label></td>
								<td align="left"><input type="text" name="chldReslname" value="${partOtherMap.containsKey('7') ? partOtherMap.get('7').getLastNm() : ''}" size="15" maxlength="17"></td>
							</tr>
							<tr>
								<td align="left"><label>Street: </label></td>
								<td align="left"><input type="text" name="other_street" value="${otherAddr == null ? "" : otherAddr.getAddrLn1()}" size="40" maxlength="40"></td>
								<td>&nbsp;</td>
								<td align="left"><label>City: </label></td>
								<td align="left"><input type="text" name="other_city" value="${otherAddr == null ? "" : otherAddr.getAddrCty()}" size="25" maxlength="25"></td>
							</tr>
							<tr>
								<td align="left"><label>State: </label></td>
								<td align="left">
								<select name="other_state">
											<c:forEach var="state" items="${stateLookup}">
												<option value="${state.getCodeId()}" ${otherAddr != null && otherAddr.getAddrSt().equals(state.getCodeId()) ? 'selected' : ''} ? 'selected' : ''}>${state.getCodeDesc()}</option>
											</c:forEach>
										</select>
								</td>
								<td>&nbsp;</td>
								<td align="left"><label>Zip: </label></td>
								<td align="left"><input type="text" name="other_zip" id="other_zip" value="${otherAddr == null ? "" : otherAddr.getAddrZip5()}" size="10" maxlength="5"></td>
							</tr>
							<tr>
								<td align="left"><label>Phone Number:</label></td>
								<td colspan="4" align="left"><input type="text" name="ot_ph1" id="ot_ph1" value="${otPh == null ? "" : otPh.substring(0, 3)}" size="3" maxlength="3"> - <input type="text" name="ot_ph2" id="ot_ph2" value="${otPh == null ? "" : otPh.substring(3, 6)}" size="3" maxlength="3">
									- <input type="text" name="ot_ph3" id="ot_ph3" value="${otPh == null ? "" : otPh.substring(6, 10)}" size="4" maxlength="4">
									<input type="hidden" id="phone_ot_val" name="phone_ot_val" value="y">
								</td>
							</tr>
						</table>
					</div>
				</fieldset>
			</div>
			<br>
			<fieldset>
				<p>
					<label title="Select from the picklist each assistance type this child currently receives. Enter additional information if applicable. "><b>Child Assistance Type:</b></label>
				<table>
					<tr>
						<td><input type=checkbox name="assistType" value="5" ${benefitsMap.containsKey('5') ? 'checked': '' }></input>TANF/Work First</td>
						<td><input type=checkbox name="assistType" value="3" ${benefitsMap.containsKey('3') ? 'checked': '' }></input>Medicaid</td>
						<td><input type=checkbox name="assistType" value="6" ${benefitsMap.containsKey('6') ? 'checked': '' }></input>Health Choice</td>
					</tr>
					<tr>
						<td><input type=checkbox name="assistType" value="7" ${benefitsMap.containsKey('7') ? 'checked': '' }></input>Foster Care</td>
						<td><input type=checkbox name="assistType" value="8" ${benefitsMap.containsKey('8') ? 'checked': '' }></input>Subsidized Child Care</td>
						<td><input type=checkbox name="assistType" value="9" ${benefitsMap.containsKey('9') ? 'checked': '' }></input>Social Security-SSI</td>
					</tr>
					<tr>
						<td><input type=checkbox name="assistType" value="10" ${benefitsMap.containsKey('10') ? 'checked': '' }></input>Social Security-SSA</td>
						<td colspan="2"><input type=checkbox id ="assist_va" name="assistType" value="11" ${benefitsMap.containsKey('11') ? 'checked': '' } onclick="ShowVeteran(this)"></input>Veterans Administrative(VA) Dependents Benefits</td>
					</tr>
				</table>
				<div id="assist_va_info" style="${benefitsMap.containsKey('11') ? 'display: block' : 'display: none'}" class=center>
					<table>
						<tr>
							<td><label>Veteran's First Name:</label></td>
							<td><input type="text" name="vfname" id="vfname" value="${benefitsMap.get("11").getVeteranNmF()}" size="15" maxlength="15"></td>
							<td>&nbsp;</td>
							<td><label>Veteran's Last Name:</label></td>
							<td><input type="text" name="vlname" id="vlname" value="${benefitsMap.get("11").getVeteranNmL()}" size="15" maxlength="17"></td>
						</tr>
					</table>
				</div>
				</p>
			</fieldset>
			<br>
			<fieldset>
				<p title="Enter the first, middle and last name(s) of the parent(s) you are seeking support from."><b>
					Select name(s) of parent(s) from whom support is being requested
					<br>OR
					<br>If parent is not found in the list, enter the name of the parent below
					<br> and select 'add NCP' button to add the parent from whom support is being requested.</b>
				</p>
				
				<table border="1">
				<tr>
					<th>&nbsp;</th>
					<th>NCP First Name</th>
					<th>NCP Last Name</th>
				</tr>
				<c:forEach var="ncpList" items="${ncpList}">
				<tr>
					<td>
						<input type="checkbox" name="ncpchldAffil" ${chldNcpMap.containsKey(ncpList.getApplicantId()) ? 'checked': '' } value="${ncpList.getApplicantId()}">
					</td>
					<td>${ncpList.getApplicantFNm()}</td>
					<td>${ncpList.getApplicantLNm()}</td>
				</tr>
				</c:forEach>
				</table>
				<br><br>
				<p><b>Add first name and last name of the parent from whom support is requested:</b></p>
				<table cellpadding="5">
					<tr>
						<td><label>NCP First Name: </label><input type="text" id="ncpF"
							value="" size="15" maxlength="15">
						</td>
						<td><label>NCP Last Name: </label><input type="text" id="ncpL"
							value="" size="15" maxlength="17">
						</td>
						 <td>
						 	<c:if test="${!applSubmitted}">
							 	<input type="button" value='Add NCP' onclick="return addNCP()" /> 
							 	<input type="button" value='Remove NCP' onclick="return removeNCP()"/>
							 </c:if>
						 </td>
					</tr>
					<tr>
						<td colspan="2">&nbsp;</td>
					</tr>
				</table>
				<table id="ncpaffilTable" border="1">
								<tr>
									<th>&nbsp;</th>
									<th>NCP First Name</th>
									<th>NCP Last Name</th>
								</tr>
				</table>
			</fieldset>
			<br>
			<fieldset>
				<p title="Enter the first, middle and last name of each parent as shown on the child's birth certificate"><b>List name(s) of parent(s) as shown on child's birth certificate:</b></p>
				<table>
					<tr>
						<td><label>Parent1 First Name:</label></td>
						<td><input type="text" name="chldPrnt1fname" id="chldPrnt1fname" value="${partOtherMap.containsKey('5') ? partOtherMap.get('5').getFirstNm() : ''}" size="15" maxlength="15"></td>
						<td>&nbsp;</td>
						<td><label>Parent1 Middle Name:</label></td>
						<td><input type="text" name="chldPrnt1mname" id="chldPrnt1mname" value="${partOtherMap.containsKey('5') ? partOtherMap.get('5').getMiddleNm() : ''}" size="15" maxlength="15"></td>
						<td>&nbsp;</td>
						<td><label>Parent1 Last Name:</label></td>
						<td><input type="text" name="chldPrnt1lname" id="chldPrnt1lname" value="${partOtherMap.containsKey('5') ? partOtherMap.get('5').getLastNm() : ''}" size="15" maxlength="17"></td>
					</tr>
					<tr>
						<td><label>Parent2 First Name:</label></td>
						<td><input type="text" name="chldPrnt2fname" id="chldPrnt2fname" value="${partOtherMap.containsKey('6') ? partOtherMap.get('6').getFirstNm() : ''}" size="15" maxlength="15"></td>
						<td>&nbsp;</td>
						<td><label>Parent2 Middle Name:</label></td>
						<td><input type="text" name="chldPrnt2mname" id="chldPrnt2mname" value="${partOtherMap.containsKey('6') ? partOtherMap.get('6').getMiddleNm() : ''}" size="15" maxlength="15"></td>
						<td>&nbsp;</td>
						<td><label>Parent2 Last Name:</label></td>
						<td><input type="text" name="chldPrnt2lname" id="chldPrnt2lname" value="${partOtherMap.containsKey('6') ? partOtherMap.get('6').getLastNm() : ''}" size="15" maxlength="17"></td>
					</tr>
				</table>
			</fieldset>
			<br>
			<fieldset>
				<p title="Select from picklist. Enter additional information if applicable."><b>Was the child's mother married to anyone when the child was conceived or born?</b>&nbsp;
					<select name="mariedBth" id="mariedBth" onchange="toggleSel(this)" title="Select from the picklist. If yes, you may enter additional information.">
						<c:forEach var="selOption" items="${seloptionLookup}">
							<option value="${selOption.getCodeId()}" ${partProfile.getMothMariedBth().equals(selOption.getCodeId()) ? 'selected' : ''}>${selOption.getCodeDesc()}</option>
						</c:forEach>
					</select>
				</p>
				<div id="mariedBth_info" style="display:  ${partProfile.getMothMariedBth().equals('1') ? 'block' : 'none'}" class=center>
					<p align="left">Married to:</p>
					<table>
						<tr>
							<td><label>First: </label></td>
							<td><input type="text" name="chldMoMridTofname" value="${partOtherMap.containsKey('8') ? partOtherMap.get('8').getFirstNm() : ''}" size="15" maxlength="15"></td>
							<td>&nbsp;</td>
							<td><label>Middle: </label></td>
							<td><input type="text" name="chldMoMridTomname" value="${partOtherMap.containsKey('8') ? partOtherMap.get('8').getMiddleNm() : ''}" size="15" maxlength="15"></td>
							<td>&nbsp;</td>
							<td><label>Last: </label></td>
							<td><input type="text" name="chldMoMridTolname" value="${partOtherMap.containsKey('8') ? partOtherMap.get('8').getLastNm() : ''}" size="15" maxlength="17"></td>
						</tr>
					</table>
				</div>
			</fieldset>
			<br>
			<fieldset>
				<p align="left" title="Select from picklist. Enter additional information if applicable.">
					<b>Was the child born during the marriage of the parents?</b> 
					<select name="wedlock" id="wedlock" onchange="toggleWedlock(this)" title="Select from the picklist then enter additional information.">
						<c:forEach var="selOption" items="${seloptionLookup}">
							<option value="${selOption.getCodeId()}" ${partProfile.getChldWdlck().equals(selOption.getCodeId()) ? 'selected' : ''}>${selOption.getCodeDesc()}</option>
						</c:forEach>
					</select>
				</p>
				<div id="wedlock_info" style="display: ${partProfile.getChldWdlck().equals('2') ? 'block' : 'none'}" class=center>
					<p align="left">
						<b>Was an Affidavit of Parentage completed?</b> <select name="affidavit" id="affidavit" onchange="toggleSel(this)">
							<!--  <option value="N" ${partProfile.getAffidCmplt().equals("")  ? 'selected' : ''}>No</option>
							<option value="Y" ${partProfile.getAffidCmplt().equalsIgnoreCase('y') ? 'selected' : ''}>Yes</option>-->
							<c:forEach var="selOption" items="${seloptionLookup}">
							<option value="${selOption.getCodeId()}" ${partProfile.getAffidCmplt().equals(selOption.getCodeId()) ? 'selected' : ''}>${selOption.getCodeDesc()}</option>
						</c:forEach>
						</select>
					</p>
					<div id="affidavit_info" style="display: ${!partProfile.getAffidCmplt().equals("") && partProfile.getAffidCmplt().equals('1') ? 'block' : 'none'}" class=center>
						<table>
							<tr>
								<td><label>State: </label></td>
								<td>
									<select name="affState">
											<c:forEach var="state" items="${stateLookup}">
												<option value="${state.getCodeId()}" ${partProfile.getAffidSt().equals(state.getCodeId()) ? 'selected' : ''}>${state.getCodeDesc()}</option>
											</c:forEach>
									</select>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</fieldset>
			<br>
			<fieldset>
				<p><b>City, State, County and Country where child was conceived:</b></p>
					<table>
						<tr>
							<td align="left"><label title="Enter city where this child was conceived">City: </label></td>
							<td align="left"><input type="text" name="chldConcCty" id="chldConcCty" value="${partProfileExtMap.containsKey('1') ? partProfileExtMap.get('1').getProfCty() : ''}" size="25" maxlength="25" title="Enter city where this child was conceived"></td>
						</tr>
						<tr>
							<td align="left"><label title="Enter County where this child was conceived">County: </label></td>
							<td align="left">
							<select name="chldConcCnty" title="Enter County where this child was conceived">
									<c:forEach var="cnty" items="${countyLookup}">
										<option value="${cnty.getCodeId()}" ${partProfileExtMap.containsKey('1')  && partProfileExtMap.get('1').getProfCnty().equals(cnty.getCodeId()) ? 'selected' : ''}>${cnty.getCodeDesc()}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td align="left"><label>State: </label></td>
							<td align="left">
								<select name="chldConcSt" id="chldConcSt" title="Select from the picklist">
									<c:forEach var="state" items="${stateLookup}">
										<option value="${state.getCodeId()}" ${partProfileExtMap.containsKey('1')  && partProfileExtMap.get('1').getProfSt().equals(state.getCodeId()) ? 'selected' : ''}>${state.getCodeDesc()}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td align="left"><label>Country: </label></td>
							<td align="left">
								<select name="chldConcCtry" id="chldConcCtry" title="Select from the picklist">
									<c:forEach var="country" items="${countryLookup}">
										<option value="${country.getCodeId()}" ${partProfileExtMap.containsKey('1')  && partProfileExtMap.get('1').getProfCntry().equals(country.getCodeId()) ? 'selected' : ''}>${country.getCodeDesc()}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
					</table>
					<br>
						<p><b>City, State, County and Country of child's birth:</b></p>
					<table>
						<tr>
							<td align="left"><label title="Enter city where this child was born">City: </label></td>
							<td align="left"><input type="text" name="chldBthCty" id="chldBthCty" value="${partProfileExtMap.containsKey('2') ? partProfileExtMap.get('2').getProfCty() : ''}" size="25" maxlength="25" title="Enter city where this child was born"></td>
						</tr>
						<tr>
							<td align="left"><label>County: </label></td>
							<td align="left">
							<select name="chldBthCnty" title="Enter County where this child was conceived">
									<c:forEach var="cnty" items="${countyLookup}">
										<option value="${cnty.getCodeId()}" ${partProfileExtMap.containsKey('2')  && partProfileExtMap.get('2').getProfCnty().equals(cnty.getCodeId()) ? 'selected' : ''}>${cnty.getCodeDesc()}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td align="left"><label>State: </label></td>
							<td align="left">
								<select name="chldBthSt" id="chldBthSt" title="Select from the picklist">
									<c:forEach var="state" items="${stateLookup}">
										<option value="${state.getCodeId()}" ${partProfileExtMap.containsKey('2')  && partProfileExtMap.get('2').getProfSt().equals(state.getCodeId()) ? 'selected' : ''}>${state.getCodeDesc()}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td align="left"><label>Country: </label></td>
							<td align="left">
								<select name="chldBthCtry" id="chldBthCtry" title="Select from the picklist">
									<c:forEach var="country" items="${countryLookup}">
										<option value="${country.getCodeId()}" ${partProfileExtMap.containsKey('2')  && partProfileExtMap.get('2').getProfCntry().equals(country.getCodeId()) ? 'selected' : ''}>${country.getCodeDesc()}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
					</table>
			</fieldset>
			<br>
			<fieldset>
				<p><b>Has paternity testing been completed for this child and parents?</b>&nbsp;
					<select name="paternityTst" id="paternityTst" onchange="toggleSel(this)" title="Select from picklist. Enter additional information if applicable. ">
						<c:forEach var="selOption" items="${seloptionLookup}">
								<option value="${selOption.getCodeId()}" ${partProfile.getPatnTst().equals(selOption.getCodeId()) ? 'selected' : ''}>${selOption.getCodeDesc()}</option>
						</c:forEach>
					</select>
				</p>
				<div id="paternityTst_info" style="display: ${partProfile.getPatnTst().equals('1') ? 'block' : 'none'}" class=center>
					<table>
						<tr>
							<td align="left"><label>Date: </label></td>
							<td align="left"><input type="text" name="paterntyTstDt" id="paterntyTstDt" value="${partPaternty.getPatnTestDtStrPg()}" size="10" maxlength="10"></td>
						</tr>
						<tr>
							<td align="left"><label>Results: </label></td>
							<td align="left"><input type="text" name="paterntyRes" id="paterntyRes" value="${partPaternty.getPatnResult()}" size="80" maxlength="100"></td>
						</tr>
					</table>
				</div>
				<br>
				<p title="Select from picklist. Enter additional information if applicable. "><b>Has paternity been established?</b>&nbsp;
					<select id="paterntyEst" name="paterntyEst" onchange="toggleSel(this)" title="Select from picklist. You may be asked to enter additional information.">
						<c:forEach var="selOption" items="${seloptionLookup}">
							<option value="${selOption.getCodeId()}" ${partProfile.getPatnEst().equals(selOption.getCodeId()) ? 'selected' : ''}>${selOption.getCodeDesc()}</option>
						</c:forEach>
					</select>
				</p>
				<div id="paterntyEst_info" style="display: ${partProfile.getPatnEst().equals('1') ? 'block' : 'none'}" class=center>
					<table>
						<tr>
							<td colspan="2">
								<table>
									<tr>
										<c:forEach var="paternty" items="${paterntyLookup}">
										 <td>
										 	<input type="radio" name="paterntyCdEst" id="paterntyCdEst" value="${paternty.getCodeId()}" ${partPaternty.getPatnEst().trim() == paternty.getCodeId() ? 'checked' : ''} />${paternty.getCodeDesc()}
										 </td>
										</c:forEach>
								
									</tr>
								</table>
							</td>
						
						</tr>
						<tr><td>&nbsp;</td></tr>
						<tr>
							<td align="left"><label>Date: </label></td>
							<td align="left"><input type="text" name="paterntyEstDt" id="paterntyEstDt" value="${partPaternty.getPatnEstDtStrPg()}" size="10" maxlength="10"></td>
						</tr>
						<tr>
							<td align="left"><label>County: </label></td>
							<td align="left">
							<select name="paterntyCnty" title="">
									<c:forEach var="cnty" items="${countyLookup}">
										<option value="${cnty.getCodeId()}" ${partPaternty.getPatnEstCnty().equals(cnty.getCodeId()) ? 'selected' : ''}>${cnty.getCodeDesc()}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td align="left"><label>State: </label></td>
							<td align="left">
								<select name="paterntySt" id="paterntySt">
									<c:forEach var="state" items="${stateLookup}">
										<option value="${state.getCodeId()}" ${partPaternty.getPatnEstSt().trim().equals(state.getCodeId()) ? 'selected' : ''}>${state.getCodeDesc()}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
					</table>
				</div>
			</fieldset>
			<br>
			
			<fieldset>
				<p align="left">
					<b>What was the parent's relationship status at the time of the child's birth?</b>&nbsp; 
					<select name="marital" id="marital" onchange="toggleMarital(this)" title="Select one from picklist">
						<c:forEach var="marital" items="${maritalLookup}">
							<option value="${marital.getCodeId()}" ${partProfile.getMaritalStatus().equals(marital.getCodeId()) ? 'selected' : ''}>${marital.getCodeDesc()}</option>
						</c:forEach>
					</select>
				<p>
				<div id="marital_info" style="display:${partProfile==null || partProfile.getMaritalStatus().equals('0') || partProfile.getMaritalStatus().equals('4') || partProfile.getMaritalStatus().equals('5')  ? 'none' : 'block'}" class=center>
					<table>
						<tr>
							<td align="left">
								<label>Date: </label>
							</td>
							<td align="left">
								<input type="text" name="maDt" id="maDt" value="${partProfile.getMaritalStatusDtStrPg()}" size="10" maxlength="10">
							</td>
						</tr>
						<tr>
							<td align="left"><label>City: </label></td>
							<td align="left"><input type="text" name="chldRelaCty" id="chldConcCty" value="${partProfileExtMap.containsKey('3') ? partProfileExtMap.get('3').getProfCty() : ''}" size="25" maxlength="25"></td>
						</tr>
						<tr>
							<td align="left"><label>County: </label></td>
							<td align="left">
							<select name="chldRelaCnty" title="">
									<c:forEach var="cnty" items="${countyLookup}">
										<option value="${cnty.getCodeId()}" ${partProfileExtMap.containsKey('3')  && partProfileExtMap.get('3').getProfCnty().equals(cnty.getCodeId()) ? 'selected' : ''}>${cnty.getCodeDesc()}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td align="left"><label>State: </label></td>
							<td align="left">
								<select name="chldRelaSt" id="chldRelaSt">
									<c:forEach var="state" items="${stateLookup}">
										<option value="${state.getCodeId()}" ${partProfileExtMap.containsKey('3')  && partProfileExtMap.get('3').getProfSt().equals(state.getCodeId()) ? 'selected' : ''}>${state.getCodeDesc()}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
					</table>
				</div>
			</fieldset>
			<br>
			<fieldset>
				<p title="Select all that apply, enter insurance details and select 'Add Insurance.' Uncheck the insurance type box to remove it from the list."><b>Does this child have health care coverage?</b>&nbsp;
				<select name="hcoverage" id="hcoverage" onchange="toggleSel(this)">
					<c:forEach var="selOption" items="${seloptionLookup}">
						<option value="${selOption.getCodeId()}" ${partProfile.getHasIns().equals(selOption.getCodeId()) ? 'selected' : ''}>${selOption.getCodeDesc()}</option>
					</c:forEach>
				</select>
				</p>
				<div id="hcoverage_info" style="${partProfile.getHasIns() == '1' ? 'display: block' : 'display: none'}" class=center>
				<table>
					<tr>
						<td>
							<table>
							<tr>
								<c:forEach var="insurance" items="${healthLookup}">
									<td>
										<input type="checkbox" name="healthCov" id="${insurance.getCodeDesc()}" value="${insurance.getCodeId()}" ${chldInsurnceMap.containsKey(insurance.getCodeId()) ? 'checked': '' } onclick="checkInsurance(this)">${insurance.getCodeDesc()}
									</td>
								</c:forEach>
							<tr>							
							</table>						
						</td>
					</tr>
					<tr>
						<td>
						<div id="hcoverage_details" style="display: none" class=center>
							<table>
								<tr>
									<td>
										<table>
											<tr>
												<td align='left'><label>Insurance Provider: </label></td>
												<td align='left'><input type="text" name="insProvider" id="insProvider" value="" size="40" maxlength="40"></td>
											</tr>
											<tr>
												<td align='left'><label>First/Last Name of Policy Holder: </label></td>
												<td align='left'><input type="text" name="holderNmF" id="holderNmF" value="" size="15" maxlength="15">&nbsp;
													<input type="text" name="holderNmL" id="holderNmL" value="" size="15" maxlength="17"></td>
											</tr>
											<tr>
												<td align='left'><label>Policy Holder Relationship to the Child: </label></td>
												<td align='left'><select name="holderRel" id="holderRel">
													<c:forEach var="relation" items="${famlrelLookup}">
														<option value="${relation.getCodeId()}" ${relation.getCodeId().equals('0')  ? 'selected' : ''}>${relation.getCodeDesc()}</option>
													</c:forEach>
												</select></td>
											</tr>
										</table>
									</td>
									<td>&nbsp;&nbsp;&nbsp;</td>
									<c:if test="${!applSubmitted}">
									<td align='right'>
										<input type="button" value='Add Insurance' onclick="return addInsurance()"/> 
									</td>
									</c:if>
								</tr>
							</table>
							</div>
						</td>
					</tr>
				</table>
				<br>
				
				  <table id="insTable" border="1">
					<tr>
						<th>Insurance</th>
						<th>Insurance Provider</th>
						<th>Policy Holder First Name</th>
						<th>Policy Holder Last Name</th>
						<th>Policy Holder Relationship to the Child</th>
					</tr>
					<c:forEach var="insCode" items="${chldInsurnceMap.keySet()}">
					<tr id="ins_${insCode}">
						<td>${chldInsurnceMap.get(insCode).getInsuranceTypeStr()}</td>
						<td><input type="text" id="insProvider_${insCode}" name="insProvider_${insCode}"
							value="${chldInsurnceMap.get(insCode).getInsuranceProvider()}" size="40" maxlength="40"></td>
						<td><input type="text" id="holderNmF_${insCode}" name="holderNmF_${insCode}"
							value="${chldInsurnceMap.get(insCode).getInsHolderF()}" size="15" maxlength="15"></td>
						<td><input type="text" id="holderNmL_${insCode}" name="holderNmL_${insCode}"
							value="${chldInsurnceMap.get(insCode).getInsHolderL()}" size="15" maxlength="17"></td>
						<td><select name="holderRel_${insCode}" id="holderRel_${insCode}">
							<c:forEach var="relation" items="${famlrelLookup}">
								<option value="${relation.getCodeId()}" ${chldInsurnceMap.get(insCode).getInsHolderRelshp().equals(relation.getCodeId()) ? 'selected' : ''}>${relation.getCodeDesc()}</option>
							</c:forEach>
							</select>
						</td>
					</tr>
					</c:forEach>				
				</table>
				
				</div>
			</fieldset>
			<c:if test="${applSubmitted}">
				</div>
			</c:if>
			<br><br>
			<div>
				<table width="70%" border="0">
						<tr>
							<td>&nbsp;&nbsp;&nbsp;</td>
							<td align="left"><input type=button value="Back To Child List" onclick="goToPreviousPage()" /></td>
							<c:if test="${!applSubmitted}">
								<td align="right"><input type="button" name="saveButton" id="save" value="Save" onclick="return validateform()"></td>
							</c:if>						
						</tr>					
				</table>
			</div>
			<br><br>
		</div>
	</div>
</form>
	
	
