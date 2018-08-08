<%@ include file="../../../include/taglib.jsp"%>

<script>
	$(function() {
		$("#brthDt").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange : "-100:+10"
		});
	});

	$(function() {
		$("#dt_emp_end").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange : "-100:+10"
		});
	}); 

	$(function() {
		$("#court_odate").datepicker({
			changeMonth : true,
			changeYear : true
		});
	});

	$(function() {
		$("#medical_odate").datepicker({
			changeMonth : true,
			changeYear : true
		});
	});

	$(function() {
		$("#spousal_odate").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange : "-100:+10"
		});
	});

	$(function() {
		$("#voluntary_odate").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange : "-100:+10"
		});
	});

	$(function() {
		$("#mDt").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange : "-100:+10"
		});
	});

	$(function() {
		$("#sDt").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange : "-100:+10"
		});
	});

	$(function() {
		$("#dDt").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange : "-100:+10"
		});
	});

	$(function() {
		$("#aDt").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange : "-100:+10"
		});
	});

	function noEnterKey(evt) {
		var evt = (evt) ? evt : ((event) ? event : null);
		if (evt.keyCode == 13) {
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

	function addPhInfo() {
		var phone = document.getElementById('phone');
		var value = phone.value;
		var id = "phone_" + value;
		var id_text = id + "_val";
		document.getElementById(id).style.display = 'block';
		document.getElementById(id_text).value = "y";
	}

	function removePhInfo() {
		var selected = false;
		var selVal;

		if (document.getElementById("phone_h").style.display == 'none'
				&& document.getElementById("phone_w").style.display == 'none'
				&& document.getElementById("phone_c").style.display == 'none') {
			alert("No phone number to remove.");
			return false;
		}

		for (i = 0; i < document.getElementsByName("pcontact").length; i++) {
			if (document.getElementsByName("pcontact")[i].checked) {
				selected = true;
				selVal = document.getElementsByName("pcontact")[i].value;
				break;
			}
		}
		if (!selected) {
			alert("Select phone contact to be deleted.");
			return false;
		}
		if (confirm("Are you sure You want to remove the selected phone information?")) {
			var id = "phone_" + selVal;
			var id_text = id + "_val";
			document.getElementById(id).style.display = 'none';
			document.getElementById(id_text).value = "";
		} else {
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
			alert("Please select income source to be deleted by selecting the radio button below.");
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
			var amt = parseFloat(document.getElementById("income_val_"
					+ radioVal).value);
			if (i == 0) {
				total = amt;
			} else {
				total = parseFloat(total) + parseFloat(amt);
			}
		}
		if (parseFloat(total) > 0) {
			document.getElementById("income").value = parseFloat(total)
					.toFixed(2);
		} else {
			document.getElementById("income").value = "0.00";
		}
		return true;
	}

	function IsNumeric(str) {
		return /^\d*\.?\d*$/.test(str);
	}

	function addIncomeType() {
		var selObj = document.getElementById("incomeType");
		var selVal = selObj.options[selObj.selectedIndex].value;
		var selRow = "income_" + selVal;
		if (selVal == "0") {
			alert("Please select Income Type");
			document.getElementById("incomeType").focus();
			return false;
		}
		if (document.getElementById("incomeAmt").value == "") {
			alert("please enter amount");
			document.getElementById("incomeAmt").focus();
			return false;
		}

		if (!IsNumeric(document.getElementById("incomeAmt").value)) {
			alert("Please enter numeric value");
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
			incVal.size = "15";
			incVal.readOnly = true;
			incVal.id = "income_val_" + selVal;
			incVal.name = "income_val_" + selVal;
			incVal.value = parseFloat(
					document.getElementById("incomeAmt").value).toFixed(2);
			document.getElementById("incomeAmt").value = "";
			tcell2.appendChild(incVal);
		} else {
			if (confirm("Income type alreadt added. Do you want to overwrite the amount value?")) {
				document.getElementsByName("income_val_" + selVal)[0].value = parseFloat(
						document.getElementById("incomeAmt").value).toFixed(2);
				document.getElementById("incomeAmt").value = "";
			} else {
				return false;
			}
		}
		var income_group = document.getElementsByName("incomeRadio");
		var total;
		for (i = 0; i < income_group.length; i++) {
			var radioVal = income_group[i].value;
			var amt = parseFloat(document.getElementById("income_val_"
					+ radioVal).value);
			if (i == 0) {
				total = amt;
			} else {
				total = parseFloat(total) + parseFloat(amt);
			}
		}
		if (parseFloat(total) > 0) {
			document.getElementById("income").value = parseFloat(total)
					.toFixed(2);
		} else {
			document.getElementById("income").value = "0.00";
		}

		return true;

	}

	function deleteRow(vehicleTable) {
		try {
			var table = document.getElementById(vehicleTable);
			var rowCount = table.rows.length;
			var radioChecked = false;

			for (var i = 0; i < rowCount; i++) {
				var row = table.rows[i];
				var radioButton = row.cells[0].childNodes[0];
				if (null != radioButton && true == radioButton.checked) {
					table.deleteRow(i);
					rowCount--;
					i--;
					radioChecked = true;
				}

			}
		} catch (e) {
			alert(e);
		}

		if (!radioChecked)
			alert("Please select vehicle  to be deleted by selecting the radio button below.");
	}

	function addRow() {

		var table = document.getElementById('vehicleTable');

		basicLst = document.getElementById('vehicleLst');

		var counter = parseInt(basicLst.value);

		var rowCount = table.rows.length;

		counter == 0 && rowCount > 0 ? counter = rowCount
				: counter = counter + 1;

		var row = table.insertRow(rowCount);

		var cell0 = row.insertCell(0);
		var element0 = document.createElement("input");
		element0.type = "radio";
		element0.name = "vehicleRadio";
		element0.value = counter;

		cell0.appendChild(element0);

		var cell1 = row.insertCell(1);
		var element1 = document.createElement("input");
		element1.type = "text";

		element1.name = "make_" + counter;

		element1.size = 6;
		element1.maxLength = 6;

		cell1.appendChild(element1);

		var cell2 = row.insertCell(2);
		var element2 = document.createElement("input");
		element2.type = "text";
		element2.name = "model_" + counter;
		element2.size = 8;
		element2.maxLength = 8;
		cell2.appendChild(element2);

		var cell3 = row.insertCell(3);
		var element3 = document.createElement("input");
		element3.type = "text";
		element3.name = "year_" + counter;
		element3.size = 4;
		element3.maxLength = 4;
		cell3.appendChild(element3);

		basicLst.value = counter;

	}

	function addRowOnLoad() {

		var table = document.getElementById('vehicleTable');

		basicLst = document.getElementById('vehicleLst');

		var counter = parseInt(basicLst.value);

		var rowCount = table.rows.length;

		counter == 0 && rowCount > 0 ? counter = rowCount
				: counter = counter + 1;

		var row = table.insertRow(rowCount);

		var cell0 = row.insertCell(0);
		var element0 = document.createElement("input");
		element0.type = "radio";
		element0.name = "vehicleRadio";
		element0.value = counter;

		cell0.appendChild(element0);

		var cell1 = row.insertCell(1);
		var element1 = document.createElement("input");
		element1.type = "text";
		element1.size = 6;
		element1.maxLength = 6;
		element1.name = "make_" + counter;
		element1.value = arguments[0];
		cell1.appendChild(element1);

		var cell2 = row.insertCell(2);
		var element2 = document.createElement("input");
		element2.type = "text";
		element2.size = 8;
		element2.maxLength = 8;
		element2.name = "model_" + counter;
		element2.value = arguments[1];

		cell2.appendChild(element2);

		var cell3 = row.insertCell(3);
		var element3 = document.createElement("input");
		element3.type = "text";
		element3.size = 4;
		element3.maxLength = 4;
		element3.name = "year_" + counter;
		element3.value = arguments[2];

		cell3.appendChild(element3);

		basicLst.value = counter;

	}

	function showOther(selectObject) {
		var id = selectObject.id + "_ot";
		var optionTxt = selectObject.options[selectObject.selectedIndex].text;
		if (optionTxt.trim() === "Other") {
			document.getElementById(id).style.display = 'block';
		} else {
			document.getElementById(selectObject.id+'Ot').value = " ";
			document.getElementById(id).style.display = 'none';
		}
	}

	function toggleEmp(selectObject) {
		var value = selectObject.value;
		if (value == "y") {
			document.getElementById('lastEmp').style.display = 'none';
			document.getElementById('currEmp').style.display = 'block';
		}
		if (value == "n") {
			document.getElementById('currEmp').style.display = 'none';
			document.getElementById('lastEmp').style.display = 'block';
		}
	}

	function toggleInfo(selectObject) {

		var id = selectObject.id + "_info";
		var value = selectObject.value;

		if (value == '1') {
			document.getElementById(id).style.display = 'block';
		}
		if (value == '0' || value == '2' || value == '3') {
			document.getElementById(id).style.display = 'none';
		}
	}
	
	function toggleMisc(selectObject) {

		var id = selectObject.id + "_info";
		var value = selectObject.value;

		if (value == '1') {
			document.getElementById(id).style.display = 'block';
		}
		if (value == '0' || value == '2' || value == '3') {
			document.getElementById("noteText").value = " ";
			document.getElementById(id).style.display = 'none';
		}
	}

	function toggleOrder(selectObject) {

		var id = selectObject.id + "_info";

		var value = selectObject.value;

		if (value == '1') {
			document.getElementById(id).style.display = 'block';
		}
		if (value == '0' || value == '2' || value == '3') {

			var order = document.OnlineAppNCPForm.support_order;

			for (var i = 0; i < order.length; i++) {
				order[i].checked = false;
				changeSupport(order[i]);
			}

			document.getElementById(id).style.display = 'none';

		}
	}

	function toggleMarital(selectObject) {
		var value = selectObject.value;
		var id = selectObject.id + "_info_" + value;

		if (value == "0") {
			document.getElementById(selectObject.id + "_info_" + "1").style.display = 'none';
			document.getElementById(selectObject.id + "_info_" + "2").style.display = 'none';
			document.getElementById(selectObject.id + "_info_" + "3").style.display = 'none';
		}

		if (value == "1") {
			document.getElementById(selectObject.id + "_info_" + "2").style.display = 'none';
			document.getElementById(selectObject.id + "_info_" + "3").style.display = 'none';
			document.getElementById(id).style.display = 'block';

		}
		if (value == "2") {
			document.getElementById(selectObject.id + "_info_" + "1").style.display = 'none';
			document.getElementById(selectObject.id + "_info_" + "3").style.display = 'none';
			document.getElementById(id).style.display = 'block';
		}
		if (value == "3") {
			document.getElementById(selectObject.id + "_info_" + "1").style.display = 'none';
			document.getElementById(selectObject.id + "_info_" + "2").style.display = 'none';
			document.getElementById(id).style.display = 'block';
		}
		if (value == "4") {
			document.getElementById(selectObject.id + "_info_" + "1").style.display = 'none';
			document.getElementById(selectObject.id + "_info_" + "2").style.display = 'none';
			document.getElementById(selectObject.id + "_info_" + "3").style.display = 'none';
		}
		if (value == "5") {
			document.getElementById(selectObject.id + "_info_" + "1").style.display = 'none';
			document.getElementById(selectObject.id + "_info_" + "2").style.display = 'none';
			document.getElementById(selectObject.id + "_info_" + "3").style.display = 'none';
		}

	}

	function confirmSaveAndExit() {
		showLightbox('saveAndExitDiv', 400, 160, 100, 200);
	}

	function ncpChildLinked(selectObject) {
		var childrenSize = document.getElementById("ncp_child").length;
		var value = selectObject.id;

		if (childrenSize == 0
				&& document.getElementById(selectObject.id).checked) {
			document.getElementById(selectObject.id).checked = false;

			alert("NCP and related children must be linked to provide support order details. Save this page, return to section 3 and select children. Enter a check in the box beside the name of the NCP from whom support is being requested. Return to section 4 to complete support order details.");
		}

	}

	function changeSupport(selectObject) {

		var value = selectObject.id;

		if (document.getElementById(selectObject.id).checked) {
			var id = selectObject.id;
			document.getElementById("sorder_info_" + id).style.display = 'block';

		} else {

			var id = selectObject.id;
			document.getElementById("sorder_info_" + id).style.display = 'none';
		}

	}
	function validateform() {

		var result = true;
		var ssn = document.OnlineAppNCPForm.ssnNb.value;
		var date_regex = /^(0[1-9]|1[0-2])\/(0[1-9]|1\d|2\d|3[01])\/(19|20)\d{2}$/;
		var ssn_regex = /^\d{9}$/;
        var height_regex = /^[0-9]+$/;
		var year_regex = /^\d{4}$/;
		var zip_regex = /^\d{5}$/;
		var name_regex = /^[a-zA-Z ]+$/;
		var alias_name_regex = /^[a-zA-Z, ]+$/;

		if (document.OnlineAppNCPForm.applicantFNm.value == "") {
			alert("First Name should not be empty");
			document.OnlineAppNCPForm.applicantFNm.focus();
			return false;
		}
		if (document.OnlineAppNCPForm.applicantLNm.value == "") {
			alert("Last Name should not be empty");
			document.OnlineAppNCPForm.applicantLNm.focus();
			return false;
		}

		if (!name_regex.test(document.OnlineAppNCPForm.applicantFNm.value
				.trim())) {
			alert("Special characters and numbers are not allowed in first name.");
			document.OnlineAppNCPForm.applicantFNm.focus();
			return false;
		}

		if (document.OnlineAppNCPForm.applicantMNm.value != ""
				&& !name_regex
						.test(document.OnlineAppNCPForm.applicantMNm.value
								.trim())) {
			alert("Special characters and numbers are not allowed in middle name.");
			document.OnlineAppNCPForm.applicantMNm.focus();
			return false;
		}
		if (!name_regex.test(document.OnlineAppNCPForm.applicantLNm.value
				.trim())) {
			alert("Special characters and numbers are not allowed in last name.");
			document.OnlineAppNCPForm.applicantLNm.focus();
			return false;
		}
		if (document.OnlineAppNCPForm.applicantAliasNm.value != ""
				&& !alias_name_regex
						.test(document.OnlineAppNCPForm.applicantAliasNm.value
								.trim())) {
			alert("Special characters (except comma) and numbers are not allowed in alias name.");
			document.OnlineAppNCPForm.applicantAliasNm.focus();
			return false;
		} else if (ssn.trim() != "" && !ssn_regex.test(ssn)) {
			alert("SSN should be all numbers in format '123456789'");
			document.OnlineAppNCPForm.ssnNb.focus();
			return false;
		}
		else if (document.OnlineAppNCPForm.biCty.value != ""
			&& !name_regex
					.test(document.OnlineAppNCPForm.biCty.value
							.trim())) {
			alert("Special characters and numbers are not allowed for birth city.");
			document.OnlineAppNCPForm.biCty.focus();
			return false;
		}
		else if (document.OnlineAppNCPForm.f_first.value != ""
			&& !name_regex
					.test(document.OnlineAppNCPForm.f_first.value
							.trim())) {
			alert("Special characters and numbers are not allowed for NCP father's first name.");
			document.OnlineAppNCPForm.f_first.focus();
			return false;
		}
		else if (document.OnlineAppNCPForm.f_middle.value != ""
			&& !name_regex
					.test(document.OnlineAppNCPForm.f_middle.value
							.trim())) {
			alert("Special characters and numbers are not allowed for NCP father's middle name.");
			document.OnlineAppNCPForm.f_middle.focus();
			return false;
		}
		else if (document.OnlineAppNCPForm.f_last.value != ""
			&& !name_regex
					.test(document.OnlineAppNCPForm.f_last.value
							.trim())) {
			alert("Special characters and numbers are not allowed for NCP father's last name.");
			document.OnlineAppNCPForm.f_last.focus();
			return false;
		}
		else if (document.OnlineAppNCPForm.m_first.value != ""
			&& !name_regex
					.test(document.OnlineAppNCPForm.m_first.value
							.trim())) {
			alert("Special characters and numbers are not allowed for NCP mother's first name.");
			document.OnlineAppNCPForm.m_first.focus();
			return false;
		}
		else if (document.OnlineAppNCPForm.m_middle.value != ""
			&& !name_regex
					.test(document.OnlineAppNCPForm.m_middle.value
							.trim())) {
			alert("Special characters and numbers are not allowed for NCP mother's middle name.");
			document.OnlineAppNCPForm.m_middle.focus();
			return false;
		}
		else if (document.OnlineAppNCPForm.m_last.value != ""
			&& !name_regex
					.test(document.OnlineAppNCPForm.m_last.value
							.trim())) {
			alert("Special characters and numbers are not allowed for NCP mother's last name.");
			document.OnlineAppNCPForm.m_last.focus();
			return false;
		}
		if (document.OnlineAppNCPForm.brthDt.value.trim() != ""
				&& (!(isValidDate(document.OnlineAppNCPForm.brthDt.value,
						"date of birth")) || !(beforeFutureDate(
						document.OnlineAppNCPForm.brthDt.value, "date of birth")))) {
			document.OnlineAppNCPForm.brthDt.focus();
			return false;
		}
		if (document.OnlineAppNCPForm.email.value != ""
				&& !validateEmail(document.OnlineAppNCPForm.email.value.trim())) {
			alert("Not a valid email.");
			document.OnlineAppNCPForm.email.focus();
			return false;
		}
		if (document.OnlineAppNCPForm.arrestDt.value.trim() != ""
				&& (!(isValidDate(document.OnlineAppNCPForm.arrestDt.value,
						"date of arrest")) || !(beforeFutureDate(
						document.OnlineAppNCPForm.arrestDt.value,
						"date of arrest")))) {

			document.OnlineAppNCPForm.arrestDt.focus();
			return false;
		}

		if (document.getElementById("height").value.trim() != "" && !height_regex.test(document.getElementById("height").value)) {
			alert("Enter number for height in inches");
			document.getElementById("height").focus();
			return false;
		}
		
		if (!IsNumeric(document.getElementById("weight").value)) {
			alert("Enter numeric value");
			document.getElementById("weight").focus();
			return false;
		}
		if (document.getElementById("ma_zip").value.trim() != ""
				&& !zip_regex.test(document.getElementById("ma_zip").value
						.trim())) {
			alert("Zip number should be all digits and 5 digits long");
			document.getElementById("ma_zip").focus();
			return false;
		}
		if (document.getElementById("res_zip").value.trim() != ""
				&& !zip_regex.test(document.getElementById("res_zip").value)) {
			alert("Zip number should be all digits and 5 digits long");
			document.getElementById("res_zip").focus();
			return false;
		}

		if (document.getElementById("other_zip").value.trim() != ""
				&& !zip_regex.test(document.getElementById("other_zip").value)) {
			alert("Zip number should be all digits and 5 digits long");
			document.getElementById("other_zip").focus();
			return false;
		}

		if (document.getElementById('phone_h_val').value.trim() == "y"
				&& !validatePhone(document.getElementById('h_ph1').value,
						document.getElementById('h_ph2').value, document
								.getElementById('h_ph3').value)) {

			document.getElementById('h_ph1').focus();
			return false;

		}

		if (document.getElementById('phone_w_val').value.trim() == "y"
				&& (!validatePhone(document.getElementById('w_ph1').value,
						document.getElementById('w_ph2').value, document
								.getElementById('w_ph3').value))) {

			document.getElementById('w_ph1').focus();
			return false;

		}
		if (document.getElementById('phone_c_val').value.trim() == "y"
				&& !validatePhone(document.getElementById('c_ph1').value,
						document.getElementById('c_ph2').value, document
								.getElementById('c_ph3').value)) {

			document.getElementById('c_ph1').focus();
			return false;

		}

		if (document.getElementById('emp_chk').value.trim() == "y"
				&& (document.getElementById("emp_zip").value.trim() != "" && !zip_regex
						.test(document.getElementById("emp_zip").value))) {

			alert("Zip number should be all digits and 5 digits long");
			document.getElementById("emp_zip").focus();
			return false;
		}
		if (!validatePhone(document.getElementById('emp_ph1').value, document
				.getElementById('emp_ph2').value, document
				.getElementById('emp_ph3').value)) {
			document.getElementById('emp_ph1').focus();
			return false;

		}

		if (document.getElementById('emp_chk').value.trim() == "n"
				&& document.getElementById('dt_emp_end').value.trim() !== ""
				&& (!(isValidDate(document.OnlineAppNCPForm.dt_emp_end.value,
						"date of employment end")) || !(beforeFutureDate(
						document.OnlineAppNCPForm.dt_emp_end.value, "date of employment end")))) {

			document.getElementById('dt_emp_end').focus();
		
			return false;

		}

		var table = document.getElementById('vehicleTable');
		var emptyCell = false;

		for (var r = 1, n = table.rows.length; r < n; r++) {

			var vehicleRow = "";

			for (var c = 1, m = table.rows[r].cells.length; c < m; c++) {

				var cellString = table.rows[r].cells[c].firstChild.value;

				vehicleRow = vehicleRow + cellString;

			}

			if (vehicleRow.trim() == "") {

				alert("Provide some information for vehicle in the row  " + r
						+ " or delete the row");

				table.rows[r].cells[1].firstChild.focus();

				return false;

			}
		}

		for (var m = 1, n = table.rows.length; m < n; m++) {

			var vehicleYear = table.rows[m].cells[3].firstChild.value;

			if (vehicleYear != "" && !year_regex.test(vehicleYear))

			{

				alert("Vehicle year should be all digits and 4 digits long");

				table.rows[m].cells[3].firstChild.focus();

				return false;

			}

		}

		//selected vehicle source values.
		var vehicleLst = "";
		for (i = 0; i < document.getElementsByName("vehicleRadio").length; i++) {
			if (i != 0) {
				vehicleLst = vehicleLst + ",";
			}
			vehicleLst = vehicleLst
					+ document.getElementsByName("vehicleRadio")[i].value;
		}
		document.OnlineAppNCPForm.vehicleLst.value = vehicleLst;

		//date validation for marital status.

		var sel = document.getElementById('marital');
		var opt;
		var selLength = sel.options.length;

		for (var initial = 0; initial < selLength; initial++) {

			opt = sel.options[initial];

			if (opt.selected == true && opt.value != '0' && opt.value != '4'
					&& opt.value != '5') {

				switch (opt.value) {

				case '1':

					if (document.OnlineAppNCPForm.mDt.value.trim() != ""
							&& (!(isValidDate(
									document.OnlineAppNCPForm.mDt.value,
									"date of marriage")) || !(beforeFutureDate(
									document.OnlineAppNCPForm.mDt.value,
									"date of marriage")))) {

						document.OnlineAppNCPForm.mDt.focus();

						return false;
					}
					break;

				case '2':

					if (document.OnlineAppNCPForm.dDt.value.trim() != ""
							&& (!(isValidDate(
									document.OnlineAppNCPForm.dDt.value,
									"date of divorce")) || !(beforeFutureDate(
									document.OnlineAppNCPForm.dDt.value,
									"date of divorce")))) {

						document.OnlineAppNCPForm.dDt.focus();
						return false;

					}
					break;

				case '3':

					if (document.OnlineAppNCPForm.sDt.value.trim() != ""
							&& (!(isValidDate(
									document.OnlineAppNCPForm.sDt.value,
									"date of seperation")) || !(beforeFutureDate(
									document.OnlineAppNCPForm.sDt.value,
									"date of seperation")))) {
						document.OnlineAppNCPForm.sDt.focus();
						return false;

					}
					break;
				}

				break;

			}
		}

		//Selected income source values
		var selVal = "";
		for (i = 0; i < document.getElementsByName("incomeRadio").length; i++) {
			if (document.getElementById("income_val_"
					+ document.getElementsByName("incomeRadio")[i].value).value == "") {
				alert("please enter amount");
				document
						.getElementById(
								"income_val_"
										+ document
												.getElementsByName("incomeRadio")[i].value)
						.focus();
				return false;
			}

			if (isNaN(document.getElementById("income_val_"
					+ document.getElementsByName("incomeRadio")[i].value).value)) {
				alert("Please enter numeric value");
				document
						.getElementById(
								"income_val_"
										+ document
												.getElementsByName("incomeRadio")[i].value)
						.focus();
				return false;
			}
			if (i != 0) {
				selVal = selVal + ",";
			}
			selVal = selVal
					+ document.getElementsByName("incomeRadio")[i].value;
		}
		document.OnlineAppNCPForm.incomeTypeLst.value = selVal;

		// atleast one child needs to be selected if court order is selected.

		if (document.getElementById("court").checked)

		{
			var sel = document.getElementById('court_child');
			var opt;
			var selLength = sel.options.length;
			for (var initial = 0; initial < selLength; initial++) {
				opt = sel.options[initial];
				if (opt.selected == true && opt.value != "") {

					break;
				}
			}

			if (initial == selLength) {
				alert("Select Child(ren) included in Court Order");
				document.OnlineAppNCPForm.court_child.focus();
				return false;
			}

		}

		// atleast one child needs to be selected if medical order is selected.

		if (document.getElementById("medical").checked)

		{
			var sel = document.getElementById('medical_child');
			var opt;
			var selLength = sel.options.length;
			for (var initial = 0; initial < selLength; initial++) {
				opt = sel.options[initial];
				if (opt.selected == true && opt.value != "") {

					break;
				}
			}

			if (initial == selLength) {
				alert("Select Child(ren) included in medical support");
				document.OnlineAppNCPForm.medical_child.focus();
				return false;
			}

		}

		// atleast one child needs to be selected if spousal order is selected.

		if (document.getElementById("spousal").checked)

		{
			var sel = document.getElementById('spousal_child');
			var opt;
			var selLength = sel.options.length;
			for (var initial = 0; initial < selLength; initial++) {
				opt = sel.options[initial];
				if (opt.selected == true && opt.value != "") {

					break;
				}
			}

			if (initial == selLength) {
				alert("Select Child(ren) included in Spousal Support");
				document.OnlineAppNCPForm.spousal_child.focus();
				return false;
			}
		}

		// atleast one child needs to be selected if voluntary order is selected.

		if (document.getElementById("voluntary").checked)

		{
			var sel = document.getElementById('voluntary_child');
			var opt;
			var selLength = sel.options.length;
			for (var initial = 0; initial < selLength; initial++) {
				opt = sel.options[initial];
				if (opt.selected == true && opt.value != "") {

					break;
				}
			}

			if (initial == selLength) {
				alert("Select Child(ren) included in Voluntary Agreement");
				document.OnlineAppNCPForm.voluntary_child.focus();
				return false;
			}
		}

		if (document.getElementById("court").checked
				&& !IsNumeric(document.getElementById("court_oamt").value)) {
			alert("Please enter numeric value");
			document.getElementById("court_oamt").focus();
			return false;
		}
		if (document.getElementById("court").checked
				&& !IsNumeric(document.getElementById("court_oamt_due").value)) {
			alert("Please enter numeric value");
			document.getElementById("court_oamt_due").focus();
			return false;
		}

		if (document.getElementById("court").checked
				&& document.OnlineAppNCPForm.court_odate.value.trim() != ""
				&& (!(isValidDate(document.OnlineAppNCPForm.court_odate.value,
						"date of court order")) || !(beforeFutureDate(
						document.OnlineAppNCPForm.court_odate.value,
						"date of court order")))) {
			document.OnlineAppNCPForm.court_odate.focus();
			return false;
		}

		if (document.getElementById("medical").checked
				&& !IsNumeric(document.getElementById("medical_oamt").value)) {
			alert("Please enter numeric value");
			document.getElementById("medical_oamt").focus();
			return false;
		}
		if (document.getElementById("medical").checked
				&& !IsNumeric(document.getElementById("medical_oamt_due").value)) {
			alert("Please enter numeric value");
			document.getElementById("medical_oamt_due").focus();
			return false;
		}

		if (document.getElementById("medical").checked
				&& document.OnlineAppNCPForm.medical_odate.value.trim() != ""
				&& (!(isValidDate(
						document.OnlineAppNCPForm.medical_odate.value,
						"date of medical order")) || !(beforeFutureDate(
						document.OnlineAppNCPForm.medical_odate.value,
						"date of medical order")))) {

			document.OnlineAppNCPForm.medical_odate.focus();
			return false;
		}

		if (document.getElementById("spousal").checked
				&& !IsNumeric(document.getElementById("spousal_oamt").value)) {
			alert("Please enter numeric value");
			document.getElementById("spousal_oamt").focus();
			return false;
		}
		if (document.getElementById("spousal").checked
				&& !IsNumeric(document.getElementById("spousal_oamt_due").value)) {
			alert("Please enter numeric value");
			document.getElementById("spousal_oamt_due").focus();
			return false;
		}

		if (document.getElementById("spousal").checked && document.OnlineAppNCPForm.spousal_odate.value.trim() != ""  &&
				(!(isValidDate(document.OnlineAppNCPForm.spousal_odate.value,
						"date of spousal support"))
						|| !(beforeFutureDate(
								document.OnlineAppNCPForm.spousal_odate.value,
								"date of spousal support")))) {

			document.OnlineAppNCPForm.spousal_odate.focus();
			return false;
		}

		if (document.getElementById("voluntary").checked
				&& !IsNumeric(document.getElementById("voluntary_oamt").value)) {
			alert("Please enter numeric value");
			document.getElementById("voluntary_oamt").focus();
			return false;
		}
		if (document.getElementById("voluntary").checked
				&& !IsNumeric(document.getElementById("voluntary_oamt_due").value)) {
			alert("Please enter numeric value");
			document.getElementById("voluntary_oamt_due").focus();
			return false;
		}

		if (document.getElementById("voluntary").checked
				&& document.OnlineAppNCPForm.voluntary_odate.value.trim() != ""
				&& (!(isValidDate(
						document.OnlineAppNCPForm.voluntary_odate.value,
						"date of voluntary agreement")) || !(beforeFutureDate(
						document.OnlineAppNCPForm.voluntary_odate.value,
						"date of voluntary agreement")))) {

			document.OnlineAppNCPForm.voluntary_odate.focus();
			return false;
		}
		
		document.OnlineAppNCPForm.submit();
		buttonsDisabled(true);
		return result;
	}

	function goToPreviousPage() {

		if (document.OnlineAppNCPForm.applSbmt.value === "false") {
			if (confirm("Unsaved information will be lost. Leave this page?")) {
				location.href = "caseApplicationNCPInfo.htm";
				buttonsDisabled(true);
			} else {
				return false;
			}
		} else {
			location.href = "caseApplicationNCPInfo.htm";
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
			alert("Please enter complete phone numner in XXX-XXX-XXXX format.");
			return false;
		} else if ((!empty(ph1) && !ph_regex.test(ph1))
				|| (!empty(ph2) && !ph_regex.test(ph2))
				|| (!empty(ph3) && !ph_regex1.test(ph3))) {
			alert("phone number should be all digits and in XXX-XXX-XXXX format");
			return false;
		}
		return true;
	}
</script>

<form name="OnlineAppNCPForm" id="OnlineAppNCPForm" method="post" action="${pageContext.request.contextPath}/cssp/user/caseApplicationNCPDetails.htm" modelAttribute="partInfo">
	<input type="hidden" name="partType" value="2"> 
	<input type="hidden" name="partPrntGrdian" value="${partInfo.getPartPrntGrdian()}"> 
	<input type="hidden" name="mode" value="${mode}"> 
	<input type="hidden" name="stepNum" value="${flow.getStepNum()}"> 
	<input type="hidden" name="incomeTypeLst" value=""> 
	<input type="hidden" id="vehicleLst" name="vehicleLst" value="0">
	<input type="hidden" name="applSbmt" value="${applSubmitted}">
	
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
			<!-- Demographic Information -->
			<c:if test="${applSubmitted}">
				<div disabled="disabled">
			</c:if>
			<div>
				<table>
					<tr>
						<td><label>First Name: </label><input type="text"
							name="applicantFNm" title="Enter first Name of NonCustodial parent" value="${partInfo.getApplicantFNm()}"
							size="15" maxlength="15"></td>
						<td><label>Middle Name: </label><input type="text"
							name="applicantMNm" title="Enter middle Name of NonCustodial parent" value="${partInfo.getApplicantMNm()}"
							size="15" maxlength="15"></td>
						<td><label>Last Name: </label><input type="text"
							name="applicantLNm" title="Enter last Name of NonCustodial parent" value="${partInfo.getApplicantLNm()}"
							size="15" maxlength="17"></td>
						<td><label>Suffix: </label> <select name="applicantSufix" title="Select valid value from pick list.">
								<c:forEach var="suffix" items="${suffixLookup}">
									<option value="${suffix.getCodeId()}"
										${partInfo.getApplicantSufix().equals(suffix.getCodeId()) ? 'selected' : ''}>${suffix.getCodeDesc()}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td colspan="4">&nbsp;</td>
					</tr>
					<tr>
						<td colspan="2">Name(s) of child(ren) for this noncustodial parent:</td>
						<td colspan="2" align="left"><select id="ncp_child" 
										onchange="" multiple >
											<c:forEach var="child" items="${ncpChildList}">
												<option value="${child.getChildId()}" disabled>${child.getNameF()}${child.getNameL()}</option>
													
													
											</c:forEach>
									</select></td>
					</tr>
					<tr>
						<td colspan="4">&nbsp;</td>
					</tr>
					<tr>
						<td colspan="4"><label>Alias or other names used: </label><input
							type="text" name="applicantAliasNm" title="Enter other names or nicknames currently used or used in the past"  

"
							value="${partInfo.getApplicantAliasNm()}" size="50"
							maxlength="40"></td>
					</tr>
				</table>
				<br>
				<table>
					<tr>
						<td><label>Gender:</label> <select name="applicantSex" title="Select gender from list.">
								<c:forEach var="gender" items="${genderLookup}">
									<option value="${gender.getCodeId()}"
										${partInfo.getApplicantSex().equals(gender.getCodeId()) ? 'selected' : ''}>${gender.getCodeDesc()}</option>
								</c:forEach>
						</select></td>
						<td><label>Date Of Birth: </label><input type="text"
							name="brthDt" title="Enter date of birth in format MM/DD/YYYY" value="${partInfo.getBrthDtStrPg()}" size="10"
							maxlength="25" id="brthDt"></td>
						<td><label colspan="2">Social Security No: </label><input
							type="text" name="ssnNb" title ="Enter your social security number in format XXX-XX-XXXX" value="${partInfo.getSsnNb()}" size="9"
							maxlength="9"></td>
					</tr>
					<tr>
						<td colspan="2">&nbsp;</td>
					</tr>
					<tr>
						<td colspan="2"><label>Race:</label> <select name="ethncGrp" title="Select your race from the picklist">
								<c:forEach var="ethncgrp" items="${ethncgrpLookup}">
									<option value="${ethncgrp.getCodeId()}"
										${partInfo.getEthncGrp().equals(ethncgrp.getCodeId()) ? 'selected' : ''}>${ethncgrp.getCodeDesc()}</option>
								</c:forEach>
						</select></td>
						<td colspan="2"><label>Language Preference:</label> <select
							name="langPref" title ="Select your preferred language or the language used most often in your home">
								<c:forEach var="lang" items="${langLookup}">
									<option value="${lang.getCodeId()}"
										${partInfo.getLangPref().equals(lang.getCodeId()) ? 'selected' : ''}>${lang.getCodeDesc()}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td colspan="2">&nbsp;</td>
					</tr>
				</table>
				<table>
					<tr>
					<td><label>Special Assistance:</label> <select
						name="specialAssist" id="specialAssist"  onchange="showOther(this)"
						title="Select from the pick list if any type of special assistance is needed.  You may enter additional information if you select 'Other'">
							<c:forEach var="sassist" items="${sassistLookup}">
								<option value="${sassist.getCodeId()}"
									${partInfo.getSpecialAssist().equals(sassist.getCodeId()) ? 'selected' : ''}>${sassist.getCodeDesc()}</option>
							</c:forEach>
					</select></td>
					<td>&nbsp;</td>
					<td id="specialAssist_ot"
						style="display: ${partInfo.getSpecialAssistStr().equals('Other') ? 'block' : 'none'}" class=center>
						<label>Other(explain):</label><input type="text" name="specialAssistOt" id="specialAssistOt" value="${partInfo.getSpecialAssistOt()}" size="30" maxlength="50">
					</td>
					</tr>
				</table>
				<br>
				<table>
					<tr>
						<td><label>Birth City: </label><input type="text"
							name="biCty" id="biCty" title="Enter city where this noncustodial parent was born" value="${ncpExtDetails.getBirthCityNm()}" size="15"
							maxlength="25"></td>
						<td><label>State: </label><select name="state" title = "Select birth state from the picklist">
								<c:forEach var="state" items="${stateLookup}">
									<option value="${state.getCodeId()}"
										${ncpExtDetails.getBirthStateNm().equals(state.getCodeId()) ? 'selected' : ''}>
										${state.getCodeDesc()}</option>
								</c:forEach>
						</select></td>
						<td><label>County: </label><select name="cnty" title ="Enter County where this noncustodial parent was born">
								<c:forEach var="cnty" items="${countyLookup}">
									<option value="${cnty.getCodeId()}"
										${ncpExtDetails.getBirthCountyNm().equals(cnty.getCodeId()) ? 'selected' : ''}>${cnty.getCodeDesc()}</option>
								</c:forEach>
						</select></td>
						<td><label>Country: </label><select name="cntry" title="Select Country of birth from the picklist">
								<c:forEach var="ctry" items="${countryLookup}">
									<option value="${ctry.getCodeId()}"
										${ncpExtDetails.getBirthCountryNm().equals(ctry.getCodeId()) ? 'selected' : ''}>${ctry.getCodeDesc()}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td colspan="4">&nbsp;</td>
					</tr>
					<tr>
						<td><label>Height (in inches): </label><input type="text" id ="height" name="height"  title="Enter height in inches"
							value="${ncpExtDetails.getHeight()}" size="10" maxlength="3"></td>
						<td><label>Weight: </label><input type="text" id= "weight" name="weight" title= "Enter weight in pounds (lbs)"
							value="${ncpExtDetails.getWeightDisplay()}" size="10" maxlength="3"></td>
						<td><label>Hair Color: </label><select name="hairclr" title ="Select hair Color from picklist">
								<c:forEach var="hColor" items="${hairColorLookup}">
									<option value="${hColor.getCodeId()}"
										${ncpExtDetails.getHairColor().equals(hColor.getCodeId()) ? 'selected' : ''}>${hColor.getCodeDesc()}</option>
								</c:forEach>
						</select></td>
						<td><label>Eye Color: </label><select name="eyeClr" title= "Select eye color from picklist">
								<c:forEach var="eColor" items="${eyeColorLookup}">
									<option value="${eColor.getCodeId()}"
										${ncpExtDetails.getEyeColor().equals(eColor.getCodeId()) ? 'selected' : ''}>${eColor.getCodeDesc()}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td colspan="4">&nbsp;</td>
					</tr>
					<tr>
						<td colspan="4"><label>Identifying Marks: </label><input
							type="text" name="idMrks" value="${ncpExtDetails.getMarks()}" title="Describe identifying scars, marks or tattoos"
							size="50" maxlength="40"></td>
					</tr>
					<tr>
						<td colspan="4">&nbsp;</td>
					</tr>
					<tr>
						<td colspan="4">
							<fieldset>
								<legend>
									<b>Noncustodial parent's father name:</b>
								</legend>
								<label>First: </label><input type="text" name="f_first" id="f_first" title="Enter first name of noncustodial parent father"
									value="${ncpRelNameMap.containsKey('1')?  ncpRelNameMap['1'].getFirstNm():''}" 
									size="30" maxlength="15"> <label>Middle:</label><input type="text" name="f_middle" id="f_middle" title="Enter middle name of noncustodial parent father" value="${ncpRelNameMap.containsKey('1')?  ncpRelNameMap['1'].getMiddleNm():''}" 
									size="30" maxlength="15"> <label>Last:</label><input type="text" name="f_last" id="f_last"	title="Enter last name of noncustodial parent father" value="${ncpRelNameMap.containsKey('1')?  ncpRelNameMap['1'].getLastNm():''}"  size="30" maxlength="17">
							</fieldset>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<fieldset>
								<legend>
									<b>Noncustodial parent's mother name:</b>
								</legend>
								<label>First: </label><input type="text" name="m_first" id="m_first" title="Enter first name of noncustodial parent mother"
									value="${ncpRelNameMap.containsKey('2')?  ncpRelNameMap['2'].getFirstNm():''}" size="30" maxlength="15"> <label>Middle:
								</label><input type="text" name="m_middle" id="m_middle" title="Enter middle name of noncustodial parent mother"
									value="${ncpRelNameMap.containsKey('2')?  ncpRelNameMap['2'].getMiddleNm():''}"	size="30" maxlength="15"> <label>Last:
									</label><input type="text" name="m_last" id="m_last" title="Enter last name of noncustodial parent mother" value="${ncpRelNameMap.containsKey('2')?  ncpRelNameMap['2'].getLastNm():''}" size="30" maxlength="17">
							</fieldset>
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
						<legend>Mailing Address</legend>
						<table>
							<tr>
								<td><label>Street: </label><input type="text"
									name="ma_street" title="Enter the street address where noncustodial parent receive mail" value="${mailAddr == null ? '' : mailAddr.getAddrLn1()}" size="40" maxlength="40"></td>
								<td><label>City: </label><input type="text"	name="ma_city" title="Enter city where noncustodial parent receive mail" value="${mailAddr == null ? '' : mailAddr.getAddrCty()}" size="25" maxlength="25"></td>
							</tr>
							<tr>
								<td><label>State: </label> 
									<select name="ma_state" title="Select the State from the pick list">
										<c:forEach var="state" items="${stateLookup}">
											<option value="${state.getCodeId()}" ${mailAddr.getAddrSt().equals(state.getCodeId()) ? 'selected' : ''}>${state.getCodeDesc()}</option>
										</c:forEach>
									</select>
								</td>
								<td>
								<label>Zip: </label>
									<input type="text" name="ma_zip" title="Enter 5 digit zip code" id="ma_zip" value="${mailAddr == null ? '' : mailAddr.getAddrZip5()}" size="5" maxlength="5">
								</td>
							</tr>
						</table>
					</fieldset>
					<p>
						<input type="checkbox" name="res_addr" value="res_addr"  title="Check the box if your Residential address is the same as your Mail address." ${resAddr == null  ? 'checked' : ''} onclick="checkResidAddr(this)"> Check if residential address is same as mailing address
					</p>
					<div id="residential" style="${resAddr == null  ? 'display: none' : 'display: block'}" class=center>
						<fieldset>
							<legend>Residential Address</legend>
							<table>
								<tr>
									<td align="left">
										<label>Street: </label>
										<input type="text" name="res_street" value="${resAddr == null ? '' : resAddr.getAddrLn1()}" title="Enter the street address for the residential address" size="50" maxlength="50">
									</td>
									<td align="left">
										<label>City: </label>
										<input type="text" name="res_city" title="Enter city for the residential address" value="${resAddr == null ? '' : resAddr.getAddrCty()}" size="25" maxlength="25">
									</td>
								</tr>
								<tr>
									<td align="left">
										<label>State: </label> 
										<select name="res_state" title="Select the State from the pick list">
											<c:forEach var="state" items="${stateLookup}">
												<option value="${state.getCodeId()}"
													${resAddr.getAddrSt().equals(state.getCodeId()) ? 'selected' : ''}>${state.getCodeDesc()}</option>
											</c:forEach>
										</select>
									</td>
									<td align="left">
										<label>Zip: </label>
										<input type="text" name="res_zip" id="res_zip" title="Enter the 5 digit Zip Code for the Residential address" value="${resAddr == null ? '' : resAddr.getAddrZip5()}" size="5" maxlength="5">
									</td>
								</tr>
							</table>
						</fieldset>
					</div>
					<br>
					<table>
						<tr>
							<td colspan="4">
								<fieldset>
									<legend>
										<b title="Enter the last known address for the noncustodial parent">Last known address (if no current address):</b>
									</legend>
									<table>
										<tr>
											<td><label>Street: </label>
												<input type="text" name="other_street" value="${otherAddr == null ? '' : otherAddr.getAddrLn1()}" size="50" maxlength="40">
											</td>
											<td><label>City: </label>
												<input type="text" name="other_city" value="${otherAddr == null ? '' : otherAddr.getAddrCty()}" size="25" maxlength="25">
											</td>
										</tr>
										<tr>
											<td align="left"><label>State: </label>
												<select name="other_state">
												<c:forEach var="state" items="${stateLookup}">
													<option value="${state.getCodeId()}" ${otherAddr != null && otherAddr.getAddrSt().equals(state.getCodeId()) ? 'selected' : ''} ? 'selected' : ''}>${state.getCodeDesc()}</option>
												</c:forEach>
												</select>
											</td>
											<td><label>Zip: </label>
												<input type="text" name="other_zip" id="other_zip" value="${otherAddr == null ? '' : otherAddr.getAddrZip5()}" size="10" maxlength="5">
											</td>
										</tr>
									</table>
								</fieldset>
							</td>
						</tr>
					</table>
					<br>
					<table>
						<tr>
							<td colspan="3"><label>County of Residence: </label> 
								<select name="resCnty" title="Select County from the picklist if you are a North Carolina resident.">
									<c:forEach var="cnty" items="${countyLookup}">
										<option value="${cnty.getCodeId()}" ${partInfo.getResCnty().equals(cnty.getCodeId()) ? 'selected' : ''}>${cnty.getCodeDesc()}</option>
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
						<tr>
							<td colspan="3">&nbsp;</td>
						</tr>
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
						<tr>
							<td colspan="3">&nbsp;</td>
						</tr>
						<tr>
							<td colspan="4"><label>Email address: </label> 
							<input type="text" name="email" id="email" title="Enter the valid electronic mail address for the noncustodial parent if known"  value="${email == null ? '' : email}" size="50" maxlength="50">
							</td>
						</tr>
					</table>
				</fieldset>
			</div>
			<br>
			<fieldset>
				<legend>
					<b>Does the noncustodial parent have a driver's license?</b>
				</legend>
				<select id="license" name="hasLicense" title="Select from picklist. Provide additional information if applicable" onchange="toggleInfo(this)">
					<c:forEach var="licStatus" items="${seloptionLookup}">
									<option value="${licStatus.getCodeId()}"
										${ncpExtDetails.getDriverLicStatus().equals(licStatus.getCodeId()) ? 'selected' : ''}>${licStatus.getCodeDesc()}</option>
								</c:forEach>
				</select>
				<div id="license_info"	style="display: ${ncpExtDetails.getDriverLicStatus().equals('1') ? 'block' : 'none'}"
					class=center>
					<table>
						<tr>
							<td><label>License Number: </label><input
										type="text" name="licNum"
										value="${ncpExtDetails.getDriverlicNum()}" size="25"
										maxlength="20"></td>
							<td><label>State: </label><select
										name="licState">
											<c:forEach var="state" items="${stateLookup}">
												<option value="${state.getCodeId()}" ${ncpExtDetails.getLicenseState().equals(state.getCodeId()) ? 'selected' : ''}>
													${state.getCodeDesc()}</option>
											</c:forEach>
									</select></td>
						</tr>
					</table>
				</div>
			</fieldset>
			<br>
			<fieldset>
				<legend>
					<b>Does the noncustodial parent have a vehicle?</b>
				</legend>
				<select id="vehicle" name="vehicleInfo" title="Select from picklist. Provide additional information if applicable" onchange="toggleInfo(this)">
					<c:forEach var="vehicleStatus" items="${seloptionLookup}">
									<option value="${vehicleStatus.getCodeId()}"
										${ncpExtDetails.getOwnVehicleStatus().equals(vehicleStatus.getCodeId()) ? 'selected' : ''}>${vehicleStatus.getCodeDesc()}</option>
								</c:forEach>
				</select>
				<div id="vehicle_info"
					style="display:${ncpExtDetails.getOwnVehicleStatus().equals('1') ? 'block' : 'none'}"
					class=center>
					<INPUT type="button" value="Add Vehicle" onclick="addRow()" /> <INPUT
						type="button" value="Delete Vehicle"
						onclick="deleteRow('vehicleTable')" />
					<TABLE id="vehicleTable" width="350px" border="1">
						<tr>
							<th>Select</th>
							<th>Make</th>
							<th>Model</th>
							<th>Year</th>

						</tr>
						<c:forEach var="vehicle" items="${vehicles}" varStatus="count">

							<script>addRowOnLoad('${vehicle.getMake()}','${vehicle.getModel()}','${vehicle.getYear()}') </script>

							<%-- <tr>
										<td>
											
										<input  type="radio" id="vehicleRadio" name="vehicleRadio" value="${count.index}"></input>
											
										</td>
										
										<td><input type="text"
											id="makeId_${count.index}"
											name="make_${count.index}"
											value="${vehicle.getMake()}"></td>
											
										<td><input type="text"
											id="modelId_${count.index}"
											name="model_${count.index}"
											value="${vehicle.getModel()}"></td>
										<td><input type="text"
											id="yearId_${count.index}"
											name="year_${count.index}"
											value="${vehicle.getYear()}"></td>		
									</tr> --%>
						</c:forEach>

					</TABLE>
				</div>
			</fieldset>
			<br>
		<fieldset>
				<legend>
					<b>What is the noncustodial parent's current marital status?</b>
				</legend>
				<select id="marital" name="maritalStatus" title="Select from picklist. Provide additional information if applicable" onchange="toggleMarital(this)">
					
					<c:forEach var="marStatus" items="${maritalLookup}">
									<option value="${marStatus.getCodeId()}"
										${ncpExtDetails.getMaritalStatus().equals(marStatus.getCodeId()) ? 'selected' : ''}>${marStatus.getCodeDesc()}</option>
								</c:forEach>
				</select>
				<div id="marital_info_1" style="display:${ncpExtDetails.getMaritalStatus().equals('1') ? 'block' : 'none'}" class=center>
					<table>
						<tr>
							<td><label>Date of Marriage: </label><input type="text" id="mDt" name="mDt"
										value="${ncpExtDetails.getMaritualDtStrPg().equals('01/01/0001'.trim()) ? '' : ncpExtDetails.getMaritualDtStrPg()}" size="10"
										maxlength="25"></td>
							<td colspan="4">
								<fieldset>
									<legend>
										<b>Noncustodial parent's spouse name:</b>
									</legend>
									<label>First: </label><input type="text" name="first_m"
										value="${ncpRelNameMap.containsKey('3')?  ncpRelNameMap['3'].getFirstNm():''}" size="30" maxlength="15"> <label>Middle:	</label><input type="text" name="middle_m"
										value="${ncpRelNameMap.containsKey('3')?  ncpRelNameMap['3'].getMiddleNm():''}"	size="30" maxlength="15"> <label>Last: </label><input type="text" name="last_m"
											value="${ncpRelNameMap.containsKey('3')?  ncpRelNameMap['3'].getLastNm():''}" size="30" maxlength="17">
								</fieldset>
							</td>
						</tr>
					</table>
				</div>
				<div id="marital_info_2" style="display:${ncpExtDetails.getMaritalStatus().equals('2') ? 'block' : 'none'}" class=center>
					<table>
						<tr>
							<td><label>Date of Divorce: </label><input type="text" id="dDt" name="dDt"
										value="${ncpExtDetails.getMaritualDtStrPg().equals('01/01/0001'.trim()) ? '' : ncpExtDetails.getMaritualDtStrPg()}" size="10"
										maxlength="25"></td>
							<td colspan="4">
								<fieldset>
									<legend>
										<b>Noncustodial parent's spouse name:</b>
									</legend>
									<label>First: </label><input type="text" name="d_first"
										value="${ncpRelNameMap.containsKey('3')?  ncpRelNameMap['3'].getFirstNm():''}" size="30" maxlength="15"> <label>Middle:	</label><input type="text" name="d_middle"
										value="${ncpRelNameMap.containsKey('3')?  ncpRelNameMap['3'].getMiddleNm():''}"	size="30" maxlength="15"> <label>Last: </label><input type="text" name="d_last"
											value="${ncpRelNameMap.containsKey('3')?  ncpRelNameMap['3'].getLastNm():''}" size="30" maxlength="17">
								</fieldset>
							</td>
						</tr>
					</table>
				</div>
				<div id="marital_info_3" style="display:${ncpExtDetails.getMaritalStatus().equals('3') ? 'block' : 'none'}" class=center>
					<table>
						<tr>
							<td><label>Date of Separation: </label><input type="text" id="sDt" name="sDt"
										value="${ncpExtDetails.getMaritualDtStrPg().equals('01/01/0001'.trim()) ? '' : ncpExtDetails.getMaritualDtStrPg()}" size="10"
										maxlength="25"></td>
							<td colspan="4">
								<fieldset>
									<legend>
										<b>Noncustodial parent's spouse name:</b>
									</legend>
									<label>First: </label><input type="text" name="s_first"
										value="${ncpRelNameMap.containsKey('3')?  ncpRelNameMap['3'].getFirstNm():''}" size="30" maxlength="15"> <label>Middle:	</label><input type="text" name="s_middle"
										value="${ncpRelNameMap.containsKey('3')?  ncpRelNameMap['3'].getMiddleNm():''}"	size="30" maxlength="15"> <label>Last: </label><input type="text" name="s_last"
											value="${ncpRelNameMap.containsKey('3')?  ncpRelNameMap['3'].getLastNm():''}" size="30" maxlength="17">
								</fieldset>
								</td>
						</tr>
					</table>
				</div>
			</fieldset>
			<br>
			<fieldset>
				<legend>
					<b>Is the noncustodial parent in the military?</b>
				</legend>
				<select id="military" name="inMilitary" title="Select from picklist. Provide additional information if applicable" onchange="toggleInfo(this)">
					<c:forEach var="selStatus" items="${seloptionLookup}">
									<option value="${selStatus.getCodeId()}"
										${ncpExtDetails.getMltryStatus().equals(selStatus.getCodeId()) ? 'selected' : ''}>${selStatus.getCodeDesc()}</option>
								</c:forEach>
				</select>
				<div id="military_info"
					style="display: ${ncpExtDetails.getMltryStatus().equals('1') ? 'block' : 'none'}"
					class=center>
					<table>
						<tr>
							<td><label>Branch: </label><select name="branch">
								<c:forEach var="milBranch" items="${milBranchLookup}">
									<option value="${milBranch.getCodeId()}"
										${ncpExtDetails.getMiltryBranch().equals(milBranch.getCodeId()) ? 'selected' : ''}>${milBranch.getCodeDesc()}</option>
								</c:forEach>	    
									
							</select></td>
							<td><label>Status: </label><select name="status">
								<c:forEach var="milStatus" items="${militaryLookup}">
									<option value="${milStatus.getCodeId()}"
										${ncpExtDetails.getMiltryStatus().equals(milStatus.getCodeId()) ? 'selected' : ''}>${milStatus.getCodeDesc()}</option>
								</c:forEach>	    
									
							</select></td>
							<td><label>Last Known Duty Station: </label><input
										type="text" name="station"
										value="${ncpExtDetails.getMiltryStation()}" size="10"
										maxlength="25"></td>
						</tr>
					</table>
				</div>
			</fieldset>
			<br>
			<fieldset>
				<legend>
					<b>Does the noncustodial parent have an arrest record?</b>
				</legend>
				<select id="arrest" name="isArrested" title="Select from picklist. Provide additional information if applicable"  onchange="toggleInfo(this)">
					<c:forEach var="arrestStatus" items="${seloptionLookup}">
									<option value="${arrestStatus.getCodeId()}"
										${ncpExtDetails.getArrestStatus().equals(arrestStatus.getCodeId()) ? 'selected' : ''}>${arrestStatus.getCodeDesc()}</option>
								</c:forEach>
				</select>
				<div id="arrest_info"
					style="display: ${ncpExtDetails.getArrestStatus().equals('1') ? 'block' : 'none'}"
					class=center>
					<table>
						<tr>
							<td><label>When: </label><input type="text" id="aDt"	name="arrestDt" value="${ncpExtDetails.getArrestDtStrPg().equals('01/01/0001'.trim()) ? '' : ncpExtDetails.getArrestDtStrPg()}"
										size="10" maxlength="10"></td>
							<td><label>City: </label><input type="text"
										name="arrestCy" value="${ncpExtDetails.getArrestCity()}" size="10" maxlength="25"></td>
							<td><label>State: </label><select name="arrestSt">
									<c:forEach var="state" items="${stateLookup}">
										<option value="${state.getCodeId()}"
											${ncpExtDetails.getArrestState().equals(state.getCodeId()) ? 'selected' : ''}>
											${state.getCodeDesc()}</option>
									</c:forEach>
							</select></td>
						</tr>
					</table>
				</div>
			</fieldset>
			<br>
			<fieldset>
				<legend>
					<b>Is the noncustodial parent currently on parole/probation?</b>
				</legend>
				<select id="parole" name="onParole" title="Select from picklist. Provide additional information if applicable" onchange="toggleInfo(this)">
					<c:forEach var="paroleStatus" items="${seloptionLookup}">
									<option value="${paroleStatus.getCodeId()}"
										${ncpExtDetails.getParoleStatus().equals(paroleStatus.getCodeId()) ? 'selected' : ''}>${paroleStatus.getCodeDesc()}</option>
								</c:forEach>
				</select>
				<div id="parole_info"
					style="display: ${ncpExtDetails.getParoleStatus().equals('1')? 'block' : 'none'}" class=center>
					<table>
						<tr>
							<td><label>Where: </label><input type="text"	name="paroleLoc"  value="${ncpExtDetails.getParole()}"
										size="25" maxlength="40"></td>
						</tr>
					</table>
				</div>
			</fieldset>
			<br>
			<fieldset>
				<legend>
					<b>Is the noncustodial parent currently incarcerated?</b>
				</legend>
				<select id="incarc" name="isIncarcerated" title="Select from picklist. Provide additional information if applicable"
					onchange="toggleInfo(this)">
					<c:forEach var="incarStatus" items="${seloptionLookup}">
									<option value="${incarStatus.getCodeId()}"
										${ncpExtDetails.getIncarStatus().equals(incarStatus.getCodeId()) ? 'selected' : ''}>${incarStatus.getCodeDesc()}</option>
								</c:forEach>
				</select>
				<div id="incarc_info"
					style="display: ${ncpExtDetails.getIncarStatus().equals('1')? 'block' : 'none'}" class=center>
					<p align="left">
						<label>Where: </label><input type="text"	name="incarcLoc" value="${ncpExtDetails.getIncarcerated()}" size="25" maxlength="40">
					</p>
				</div>
			</fieldset>
			<fieldset>
				<legend>
					<b>Is the noncustodial parent currently on work release?</b>
				</legend>
				<select id="wrkRel" name="onWrkRel" title="Select from picklist. Provide additional information if applicable" onchange="toggleInfo(this)">
					<c:forEach var="workStatus" items="${seloptionLookup}">
									<option value="${workStatus.getCodeId()}"
										${ncpExtDetails.getWorkReleaseStatus().equals(workStatus.getCodeId()) ? 'selected' : ''}>${workStatus.getCodeDesc()}</option>
								</c:forEach>
				</select>
				<div id="wrkRel_info"
					style="display: ${ncpExtDetails.getWorkReleaseStatus().equals('1')? 'block' : 'none'}" class=center>
					<p align="left">
						<label>Where: </label><input type="text"	name="wrkRelLoc" value="${ncpExtDetails.getWorkRelease()}"
								size="25" maxlength="40">
					</p>
				</div>
			</fieldset>
			<br>
			<!-- Income Information -->
			<div>
					<p align="left">
						<b><u>Income Information:</u></b>
					</p>
					<p align="left">
						<b>Is the noncustodial parent currently employed? </b> 
						<select name="emp_chk" id="emp_chk" onchange="toggleEmp(this)" title="Select 'Yes' or 'No'. Enter additional information after selection.">
							<option value="y" ${empInfo.getThirdPartyInfo().getThirdPartyType().equals("4") ? 'selected' : ''}>Yes</option>
							<option value="n" ${empInfo.getThirdPartyInfo().getThirdPartyType().equals("5") ? 'selected' : ''}>No</option>
						</select>
					</p>
				</div>
				<!-- current employer -->
				<div id="currEmp" style="display: ${empInfo == null || empInfo.getThirdPartyInfo().getThirdPartyType().trim().equals('4') || empInfo.getThirdPartyInfo().getThirdPartyType().trim().equals('') ? 'block' : 'none'}" class=center>
					<fieldset>
						<legend>
							<b>Current Employer</b>
						</legend>
						<table>
							<tr>
								<td colspan="5" align="left">
									<label>Name: </label>
									<input type="text" name="curr_emp" value="${empInfo.getThirdPartyInfo().getThirdPartyNm()}" size="40" maxlength="40">
								</td>
							</tr>		
							<tr>
								<td colspan="5" align="left">
									<label>Street: </label>
									<input type="text" name="emp_street" value="${empInfo.getThirdPartyInfo().getAddressLn1()}" size="40" maxlength="40">
								</td>
							</tr>
							<tr>
								<td>	
									<label>City:</label>
									<input type="text" name="emp_city" value="${empInfo.getThirdPartyInfo().getThirdPartyCity()}" size="25" maxlength="25">
								</td>
								<td>&nbsp;</td>
								<td align="left"><label>State: </label>
										<select name="emp_state">
											<c:forEach var="state" items="${stateLookup}">
												<option value="${state.getCodeId()}" ${empInfo.getThirdPartyInfo().getThirdPartyState().trim().equals(state.getCodeId()) ? 'selected' : ''}>${state.getCodeDesc()}</option>
											</c:forEach>
										</select>
										</td>
								<td>&nbsp;</td>
								<td>
									<label>Zip:</label>
									<input type="text" name="emp_zip" id="emp_zip" value="${empInfo.getThirdPartyInfo().getThirdPartyZip5()}" size="5" maxlength="5">
								</td>
							</tr>
							
							<tr>
								<td colspan="5" align="left">
									<label>Phone Number:</label>
									<input type="text" name="emp_ph1" id="emp_ph1" value="${empInfo.getThirdPartyInfo().getPhoneAreaCode()}" size="3"
									maxlength="3"> - <input type="text" name="emp_ph2" id="emp_ph2" value="${empInfo.getThirdPartyInfo().getPhoneExc()}" size="3" maxlength="3"> - <input
									type="text" name="emp_ph3" id="emp_ph3" value="${empInfo.getThirdPartyInfo().getPhoneLn()}" size="4" maxlength="4">
								</td>
							</tr>
						</table>
					</fieldset>
				</div>
				<!-- last known employer -->
				<div id="lastEmp" style="display: ${empInfo.getThirdPartyInfo().getThirdPartyType().equals('5') ? 'block' : 'none'}" class=center>
					<fieldset>
						<legend>
							<b>Last Known Employer</b>
						</legend>
						<table>
							<tr>
								<td colspan="3" align="left">
									<label>Name: </label>
									<input type="text" name="last_emp"value="${empInfo.getThirdPartyInfo().getThirdPartyNm()}" size="40" maxlength="40">
								</td>
							</tr>
							<tr>
								<td>
									<label>Date Employment Ended: </label>
									<input type="text" name="dt_emp_end" id="dt_emp_end" value="${empInfo.getEmplmEndDtPgStr()}" size="10" maxlength="10">
								</td>
								<td>&nbsp;</td>
								<td>
									<label>Reason: </label>
									<input type="text" name="end_reason" value="${empInfo.getEmplmEndReasn()}" size="50" maxlength="100">
								</td>
							</tr>
							<tr>
								<td colspan="3" align="left">
									<label>Usual Occupation: </label>
									<input type="text" name="occupation" value="${empInfo.getOcupation()}" size="50" maxlength="100">
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
			<br><br>
			<div>
				<br><br>
				<div>
					<fieldset>
						<legend></legend>
						<div>
							<p>
								<b>Is there a support order or agreement?</b> <select name = "isSupportOrder"
									id="sorder" title="Select from picklist. If yes, enter any known details regarding the court order" onchange="toggleOrder(this)">
									<c:forEach var="orderStatus" items="${seloptionLookup}">
									<option value="${orderStatus.getCodeId()}"
										${ncpExtDetails.getOrderStatus().equals(orderStatus.getCodeId()) ? 'selected' : ''}>${orderStatus.getCodeDesc()}</option>
								</c:forEach>
								</select>
							</p>
						</div>
						<br>
						<div id="sorder_info" style="display: ${ncpExtDetails.getOrderStatus().equals('1') ? 'block': 'none' }" style="float:left">
							<input type="checkbox" name="support_order" id="court" value="1" ${ncpOrderMap.containsKey('1') ? 'checked': '' }
								onclick="ncpChildLinked(this);changeSupport(this)" />Court Order &nbsp;&nbsp;<input
								type="checkbox" id="medical" name="support_order" value="2" ${ncpOrderMap.containsKey('2') ? 'checked': '' }
								onclick="ncpChildLinked(this);changeSupport(this)" />Medical Support &nbsp;&nbsp;<input
								type="checkbox" id="spousal" name="support_order" value="3" ${ncpOrderMap.containsKey('3') ? 'checked': '' }
								onclick="ncpChildLinked(this);changeSupport(this)" />Spousal Support &nbsp;&nbsp;<input
								type="checkbox" name="support_order" id="voluntary" value="4" ${ncpOrderMap.containsKey('4') ? 'checked': '' }
								onclick="ncpChildLinked(this);changeSupport(this)" />Voluntary Agreement
						</div>
						<div id="sorder_info_court" style="display: ${ncpOrderMap.containsKey('1') ? 'block': 'none' }">

							<table>
								<tr>
									<td colspan="5">&nbsp;</td>
								</tr>
								<tr>
									<td ><b>Please enter court order details:</b></td>
								</tr>
								<tr>
									<td colspan="5">&nbsp;</td>
								</tr>
								<tr>
									<td><label>Court docket number: </label> </td>
									<td><input type="text" name="court_dnumber" value="${ncpOrderMap.containsKey('1')?  ncpOrderMap['1'].getDocketNm():''}"
										 
										size="20" maxlength="17"></td>
									<td>&nbsp;</td>
									<td><label>Order effective date: </label></td>
									<td><input type="text" id="court_odate" name="court_odate" value="${ncpOrderMap.containsKey('1')? ncpOrderMap['1'].getOrderEffDtStrPg():''}"
										size="10" maxlength="10"></td>
									<td colspan="8">&nbsp;</td>

									<td rowspan="3" colspan="4"><label>Select child(ren)
											included in order: </label> <select id="court_child" name="court_child"
										onchange="" multiple>
											<c:forEach var="child" items="${ncpChildList}">
												<option value="${child.getChildId()}" ${courtChildSet.contains(child.getChildId()) ? 'selected' : ''}>${child.getNameF()}${child.getNameL()}</option>
													
													
											</c:forEach>
									</select></td>
								</tr>
								<tr>
									<td><label>County: </label></td><td><select name="court_ocnty" >
					<c:forEach var="cnty" items="${countyLookup}">
						<option value="${cnty.getCodeId()}"
							${ncpOrderMap['1'].getCounty().equals(cnty.getCodeId()) ? 'selected' : ''}>${cnty.getCodeDesc()}</option>
					</c:forEach>
				</select></td>
									<td>&nbsp;</td>
									<td><label>State: </label><td> <select name="court_ostate">
											<c:forEach var="state" items="${stateLookup}">
												<option value="${state.getCodeId()}"
													${ncpOrderMap['1'].getState().equals(state.getCodeId()) ? 'selected' : ''}>${state.getCodeDesc()}</option>
											</c:forEach>
									</select></td>
								</tr>
								<tr>
									<td><label>Amount ordered $: </label></td>
									<td><input type="text" id="court_oamt" name="court_oamt" value="${ncpOrderMap.containsKey('1')?  ncpOrderMap['1'].getOrderAmountDisplay():''}"
										
										size="20" maxlength="10"></td>
									<td>&nbsp;</td>
									<td><label>Frequency: </label></td>
									<td>
										<select name="court_ofcy">
										<c:forEach var="freq" items="${payFreqLookup}">
										<option value="${freq.getCodeId()}"
										${ncpOrderMap.containsKey('1') && ncpOrderMap['1'].getPayFreq().equals(freq.getCodeId()) ? 'selected' : ''}>${freq.getCodeDesc()}</option>
										</c:forEach>
										</select>
									</td>
								</tr>
								<tr>
									<td><label>Amount past due $: </label></td>
									<td colspan="4"><input type="text" id="court_oamt_due" name="court_oamt_due"
										value="${ncpOrderMap.containsKey('1')?  ncpOrderMap['1'].getAmtPastDueDisplay():''}" size="20" maxlength="20"></td>
								</tr>
								<tr>
									<td><label>Payor: </label></td>
									<td><input type="text" name="court_payor" value="${ncpOrderMap.containsKey('1')?  ncpOrderMap['1'].getPayorNm():''}"
										size="20" maxlength="40"></td>
									<td>&nbsp;</td>
									<td><label>Recipient: </label></td>
									<td><input type="text" name="court_recip" value="${ncpOrderMap.containsKey('1')?  ncpOrderMap['1'].getRecipientNm():''}"
										size="20" maxlength="40"></td>
								</tr>
								<tr>
									<td colspan="5">&nbsp;</td>
								</tr>

							</table>
						</div>

						<div id="sorder_info_medical" style="display: ${ncpOrderMap.containsKey('2') ? 'block': 'none' }">

							<table>
								<tr>
									<td colspan="5">&nbsp;</td>
								</tr>
								<tr>
									<td ><b>Please enter medical support details:</b></td>
								</tr>
								<tr>
									<td colspan="5">&nbsp;</td>
								</tr>
								<tr>
									<td><label>Court docket number: </label> </td>
									<td><input type="text" name="medical_dnumber" value="${ncpOrderMap.containsKey('2')?  ncpOrderMap['2'].getDocketNm():''}"
										size="20" maxlength="17"></td>
									<td>&nbsp;</td>
									<td><label>Order effective date: </label></td>
									<td><input type="text" id="medical_odate" name="medical_odate" value="${ncpOrderMap.containsKey('2')? ncpOrderMap['2'].getOrderEffDtStrPg():''}"
										size="10" maxlength="10"></td>
									<td colspan="8">&nbsp;</td>

									<td rowspan="3" colspan="4"><label>Select child(ren)
											included in order: </label> <select id="medical_child" name="medical_child"
										onchange="" multiple>
											<c:forEach var="child" items="${ncpChildList}">
												<option value="${child.getChildId()}" ${medicalChildSet.contains(child.getChildId()) ? 'selected' : ''}>${child.getNameF()}${child.getNameL()}</option>
													
											</c:forEach>
									</select></td>
								</tr>
								<tr>
									<td><label>County: </label></td>
									<td><select name="medical_ocnty" >
					<c:forEach var="cnty" items="${countyLookup}">
						<option value="${cnty.getCodeId()}"
							${ncpOrderMap['2'].getCounty().equals(cnty.getCodeId()) ? 'selected' : ''}>${cnty.getCodeDesc()}</option>
					</c:forEach>
				</select></td>
									<td>&nbsp;</td>
									<td><label>State: </label></td><td> <select name="medical_ostate">
											<c:forEach var="state" items="${stateLookup}">
												<option value="${state.getCodeId()}"
													${ncpOrderMap['2'].getState().equals(state.getCodeId()) ? 'selected' : ''}>${state.getCodeDesc()}</option>
											</c:forEach>
									</select></td>
								</tr>
								<tr>
									<td><label>Amount ordered $: </label></td>
									<td><input type="text" id="medical_oamt" name="medical_oamt" value="${ncpOrderMap.containsKey('2')?  ncpOrderMap['2'].getOrderAmountDisplay():''}"
										size="20" maxlength="10"></td>
									<td>&nbsp;</td>
									<td><label>Frequency: </label></td>
									<td>
										<select name="medical_ofcy">
										<c:forEach var="freq" items="${payFreqLookup}">
											<option value="${freq.getCodeId()}" ${ncpOrderMap.containsKey('2') && ncpOrderMap['2'].getPayFreq().equals(freq.getCodeId()) ? 'selected' : ''}>${freq.getCodeDesc()}</option>
										</c:forEach>
										</select>
									</td>
								</tr>
								<tr>
									<td><label>Amount past due $: </label></td>
									<td colspan="4"><input type="text" name="medical_oamt_due" id="medical_oamt_due"
										value="${ncpOrderMap.containsKey('2')?  ncpOrderMap['2'].getAmtPastDueDisplay():''}" size="20" maxlength="20"></td>
								</tr>
								<tr>
									<td><label>Payor: </label></td>
									<td><input type="text" name="medical_payor" value="${ncpOrderMap.containsKey('2')?  ncpOrderMap['2'].getPayorNm():''}"
										size="20" maxlength="40"></td>
									<td>&nbsp;</td>
									<td><label>Recipient: </label></td>
									<td><input type="text" name="medical_recip" value="${ncpOrderMap.containsKey('2')?  ncpOrderMap['2'].getRecipientNm():''}"
										size="20" maxlength="40"></td>
								</tr>
								<tr>
									<td colspan="5">&nbsp;</td>
								</tr>

							</table>
						</div>

						<div id="sorder_info_spousal" style="display: ${ncpOrderMap.containsKey('3') ? 'block': 'none' }">

							<table>
								<tr>
									<td colspan="5">&nbsp;</td>
								</tr>
								<tr>
									<td ><b>Please enter spousal support details:</b></td>
								</tr>
								<tr>
									<td colspan="5">&nbsp;</td>
								</tr>
								<tr>
									<td><label>Court docket number: </label> </td>
									<td><input type="text" name="spousal_dnumber" value="${ncpOrderMap.containsKey('3')?  ncpOrderMap['3'].getDocketNm():''}"
										size="20" maxlength="17"></td>
									<td>&nbsp;</td>
									<td><label>Order effective date: </label></td>
									<td><input type="text" id="spousal_odate"  name="spousal_odate" value="${ncpOrderMap.containsKey('3')? ncpOrderMap['3'].getOrderEffDtStrPg():''}"
										size="10" maxlength="10"></td>
									<td colspan="8">&nbsp;</td>

									<td rowspan="3" colspan="4"><label>Select child(ren)
											included in order: </label> <select id="spousal_child" name="spousal_child"
										onchange="" multiple>
											<c:forEach var="child" items="${ncpChildList}">
												<option value="${child.getChildId()}" ${spousalChildSet.contains(child.getChildId()) ? 'selected' : ''}>${child.getNameF()}${child.getNameL()}</option>
													
											</c:forEach>
									</select></td>
								</tr>
								<tr>
									<td><label>County: </label></td>
									<td><select name="spousal_ocnty" >
					<c:forEach var="cnty" items="${countyLookup}">
						<option value="${cnty.getCodeId()}"
							${ncpOrderMap['3'].getCounty().equals(cnty.getCodeId()) ? 'selected' : ''}>${cnty.getCodeDesc()}</option>
					</c:forEach>
				</select></td>
									<td>&nbsp;</td>
									<td><label>State: </label></td>
									<td><select name="spousal_ostate">
											<c:forEach var="state" items="${stateLookup}">
												<option value="${state.getCodeId()}"
													${ncpOrderMap['3'].getState().equals(state.getCodeId()) ? 'selected' : ''}>${state.getCodeDesc()}</option>
											</c:forEach>
									</select></td>
								</tr>
								<tr>
									<td><label>Amount ordered $: </label></td>
									<td><input type="text" id="spousal_oamt" name="spousal_oamt" value="${ncpOrderMap.containsKey('3')?  ncpOrderMap['3'].getOrderAmountDisplay():''}"
										size="20" maxlength="10"></td>
									<td>&nbsp;</td>
									<td><label>Frequency: </label></td>
									<td>
										<select name="spousal_ofcy">
										<c:forEach var="freq" items="${payFreqLookup}">
											<option value="${freq.getCodeId()}" ${ncpOrderMap.containsKey('3') && ncpOrderMap['3'].getPayFreq().equals(freq.getCodeId()) ? 'selected' : ''}>${freq.getCodeDesc()}</option>
										</c:forEach>
										</select>									
									</td>
								</tr>
								<tr>
									<td><label>Amount past due $: </label></td>
									<td colspan="4"><input type="text" id="spousal_oamt_due" name="spousal_oamt_due"
										value="${ncpOrderMap.containsKey('3')?  ncpOrderMap['3'].getAmtPastDueDisplay():''}" size="20" maxlength="20"></td>
								</tr>
								<tr>
									<td><label>Payor: </label></td>
									<td><input type="text" name="spousal_payor" value="${ncpOrderMap.containsKey('3')?  ncpOrderMap['3'].getPayorNm():''}"
										size="20" maxlength="40"></td>
									<td>&nbsp;</td>
									<td><label>Recipient: </label></td>
									<td><input type="text" name="spousal_recip" value="${ncpOrderMap.containsKey('3')?  ncpOrderMap['3'].getRecipientNm():''}"
										size="20" maxlength="40"></td>
								</tr>
								<tr>
									<td colspan="5">&nbsp;</td>
								</tr>

							</table>
						</div>

						<div id="sorder_info_voluntary" style="display: ${ncpOrderMap.containsKey('4') ? 'block': 'none' }">

							<table>
								<tr>
									<td colspan="5">&nbsp;</td>
								</tr>
								<tr>
									<td><b>Please enter voluntary agreement details:</b></td>
								</tr>
								<tr>
									<td colspan="5">&nbsp;</td>
								</tr>
								<tr>
									<td><label>Court docket number: </label> </td>
									<td><input type="text" name="voluntary_dnumber" value="${ncpOrderMap.containsKey('4')?  ncpOrderMap['4'].getDocketNm():''}"
										size="20" maxlength="17"></td>
									<td>&nbsp;</td>
									<td><label>Order effective date: </label></td>
									<td><input type="text" id="voluntary_odate" name="voluntary_odate" value="${ncpOrderMap.containsKey('4')? ncpOrderMap['4'].getOrderEffDtStrPg():''}" size="20"
										maxlength="10"></td>
									<td colspan="8">&nbsp;</td>

									<td rowspan="3" colspan="4"><label>Select child(ren)
											included in order: </label> <select id="voluntary_child"
										name="voluntary_child" onchange="" multiple>
											<c:forEach var="child" items="${ncpChildList}">
												<option value="${child.getChildId()}" ${voluntaryChildSet.contains(child.getChildId()) ? 'selected' : ''}>${child.getNameF()}${child.getNameL()}</option>
													
											</c:forEach>
									</select></td>
								</tr>
								<tr>
									<td><label>County: </label></td>
									<td><select name="voluntary_ocnty" >
					<c:forEach var="cnty" items="${countyLookup}">
						<option value="${cnty.getCodeId()}"
							${ncpOrderMap['4'].getCounty().equals(cnty.getCodeId()) ? 'selected' : ''}>${cnty.getCodeDesc()}</option>
					</c:forEach>
				</select></td>
									<td>&nbsp;</td>
									<td><label>State: </label></td>
									<td><select name="voluntary_ostate">
											<c:forEach var="state" items="${stateLookup}">
												<option value="${state.getCodeId()}"
													${ncpOrderMap['4'].getState().equals(state.getCodeId()) ? 'selected' : ''}>${state.getCodeDesc()}</option>
											</c:forEach>
									</select></td>
								</tr>
								<tr>
									<td><label>Amount ordered $: </label></td>
									<td><input type="text" id="voluntary_oamt" name="voluntary_oamt" value="${ncpOrderMap.containsKey('4')?  ncpOrderMap['4'].getOrderAmountDisplay():''}"
										size="20" maxlength="10"></td>
									<td>&nbsp;</td>
									<td><label>Frequency: </label></td>
									<td>
										<select name="voluntary_ofcy">
										<c:forEach var="freq" items="${payFreqLookup}">
											<option value="${freq.getCodeId()}" ${ncpOrderMap.containsKey('4') && ncpOrderMap['4'].getPayFreq().equals(freq.getCodeId()) ? 'selected' : ''}>${freq.getCodeDesc()}</option>
										</c:forEach>
										</select>
									</td>
								</tr>
								<tr>
									<td><label>Amount past due $: </label></td>
									<td colspan="4"><input type="text" id="voluntary_oamt_due" name="voluntary_oamt_due"
										value="${ncpOrderMap.containsKey('4')?  ncpOrderMap['4'].getAmtPastDueDisplay():''}" size="20" maxlength="20"></td>
								</tr>
								<tr>
									<td><label>Payor: </label></td>
									<td><input type="text" name="voluntary_payor" value="${ncpOrderMap.containsKey('4')?  ncpOrderMap['4'].getPayorNm():''}"
										size="20" maxlength="40"></td>
									<td>&nbsp;</td>
									<td><label>Recipient: </label></td>
									<td><input type="text" name="voluntary_recip" value="${ncpOrderMap.containsKey('4')?  ncpOrderMap['4'].getRecipientNm():''}"
										size="20" maxlength="40"></td>
								</tr>
								<tr>
									<td colspan="5">&nbsp;</td>
								</tr>

							</table>
						</div>
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
						<br>
						<dd>
							<input type="radio" name="protOrd" value="1"
								${partInfo.getProtOrd() == '1' ? 'checked' : ''} />A protective
							order has been entered due to domestic violence concerns
						</dd>
						<dd>
							<input type="radio" name="protOrd" value="2"
								${partInfo.getProtOrd() == '2' ? 'checked' : ''} />I have
							concerns about my or the child's safety due to circumstances
							involving domestic violence
						</dd>
						<dd>
							<input type="radio" name="protOrd" value="3"
								${partInfo.getProtOrd() == '3' ? 'checked' : ''} />I have no
							concerns regarding domestic violence at this time
						</dd>
					</dl>
					<br>
				</div>
				<div>
					<p>
						<b >Additional information about the noncustodial parent?</b> <select name = "additionalInfo"
							id="misc" title="Enter additional information or comments regarding the noncustodial parent" onchange="toggleMisc(this)">
							
						  <option value="0" >No</option>
							<option value="1" ${! empty ncpExtDetails.getAdditionalNote()? 'selected' : ''}>Yes</option>
							
							
						</select>
					</p>
				</div>
				<br>
				<div id="misc_info" style="display: ${! empty ncpExtDetails.getAdditionalNote()? 'block' : 'none'}" class=center>
					<textarea  name = "noteText" id = "noteText" rows="5" cols="100" maxlength="500">${ncpExtDetails.getAdditionalNote()}</textarea>
				</div>
			</div>
			<c:if test="${applSubmitted}">
				</div>
			</c:if>
			<br><br>
			<div>
				<table width="70%" border="0">
						<tr>
							<td>&nbsp;&nbsp;&nbsp;</td>
							<td align="left"><input type=button value="Back To NCP List" onclick="goToPreviousPage()" /></td>
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

