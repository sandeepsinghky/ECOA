<%@ include file="../include/taglib.jsp"%>

<script>

function validateFeedback() {
	var name_regex = /^[a-zA-Z ]+$/;
	if (document.FeedBackForm.firstName.value == "") {
			alert("First Name is required");
			document.FeedBackForm.firstName.focus();
			return false;
	}else if (!name_regex.test(document.FeedBackForm.firstName.value.trim())){
			alert("Invalid First Name.");
			document.FeedBackForm.firstName.focus();
			return false;
	}else if (document.FeedBackForm.middleInit.value != "" && !name_regex.test(document.FeedBackForm.middleInit.value.trim())) {
			alert("Invalid Middle Initial")
			document.FeedBackForm.middleInit.focus()
			return false
	}else if (document.FeedBackForm.lastName.value == "") {
			alert("Last Name is required");
			document.FeedBackForm.lastName.focus();
			return false;
	}else if (!name_regex.test(document.FeedBackForm.lastName.value.trim())){
			alert("Invalid Last Name.");
			document.FeedBackForm.lastName.focus();
			return false;
	}else if (document.FeedBackForm.email.value.length <= 0) {
		alert("Email Address is required");
		document.FeedBackForm.email.focus(); 	
		return false;
	} else if (document.FeedBackForm.email.value !="" && !validateEmail(document.FeedBackForm.email.value.trim())){
		alert("Invalid email address");
		document.FeedBackForm.email.focus();
		return false;	
	}else if (document.FeedBackForm.child1fname.value != "" && !name_regex.test(document.FeedBackForm.child1fname.value.trim())) {
			alert("Invalid Child First Name")
			document.FeedBackForm.child1fname.focus()
			return false
	}else if (document.FeedBackForm.child1lname.value != "" && !name_regex.test(document.FeedBackForm.child1lname.value.trim())) {
			alert("Invalid Child Last Name")
			document.FeedBackForm.child1lname.focus()
			return false
	}else if (document.FeedBackForm.child1mname.value != "" && !name_regex.test(document.FeedBackForm.child1mname.value.trim())) {
			alert("Invalid Child Middle Name")
			document.FeedBackForm.child1mname.focus()
			return false
	}else if (document.FeedBackForm.child2fname.value != "" && !name_regex.test(document.FeedBackForm.child2fname.value.trim())) {
			alert("Invalid Child First Name")
			document.FeedBackForm.child2fname.focus()
			return false
	}else if (document.FeedBackForm.child2lname.value != "" && !name_regex.test(document.FeedBackForm.child2lname.value.trim())) {
			alert("Invalid Child Last Name")
			document.FeedBackForm.child2lname.focus()
			return false
	}else if (document.FeedBackForm.child2mname.value != "" && !name_regex.test(document.FeedBackForm.child2mname.value.trim())) {
			alert("Invalid Child Middle Name")
			document.FeedBackForm.child2mname.focus()
			return false
	}else if (document.FeedBackForm.child3fname.value != "" && !name_regex.test(document.FeedBackForm.child3fname.value.trim())) {
			alert("Invalid Child First Name")
			document.FeedBackForm.child3fname.focus()
			return false
	}else if (document.FeedBackForm.child3lname.value != "" && !name_regex.test(document.FeedBackForm.child3lname.value.trim())) {
			alert("Invalid Child Last Name")
			document.FeedBackForm.child3lname.focus()
			return false
	}else if (document.FeedBackForm.child3mname.value != "" && !name_regex.test(document.FeedBackForm.child3mname.value.trim())) {
			alert("Invalid Child Middle Name")
			document.FeedBackForm.child3mname.focus()
			return false
	}else if (document.FeedBackForm.child4fname.value != "" && !name_regex.test(document.FeedBackForm.child4fname.value.trim())) {
			alert("Invalid Child First Name")
			document.FeedBackForm.child4fname.focus()
			return false
	}else if (document.FeedBackForm.child4lname.value != "" && !name_regex.test(document.FeedBackForm.child4lname.value.trim())) {
			alert("Invalid Child Last Name")
			document.FeedBackForm.child4lname.focus()
			return false
	}else if (document.FeedBackForm.child4mname.value != "" && !name_regex.test(document.FeedBackForm.child4mname.value.trim())) {
			alert("Invalid Child Middle Name")
			document.FeedBackForm.child4mname.focus()
			return false
	}else if (document.FeedBackForm.phone.value != "") {
		if (isInteger(document.FeedBackForm.phone.value)){
			if (document.FeedBackForm.phone.value.length < 10  ) {
				alert("Phone Number must be 10 digits");
				document.FeedBackForm.phone.focus(); 	
				return false;
			}
		} else {
			alert("Phone Number must be Numeric");
			document.FeedBackForm.phone.focus(); 	
			return false;
		}
	} 	

	return true;
}

function isInteger(s) {
	var i;
	for (i = 0; i < s.length; i++) {

		var c = s.charAt(i);
		if (!isDigit(c)) {
			return false;
	    }
	}	    
   	 // All characters are numbers.
    return true;
}

function isDigit(c) {
	return ((c >= "0") && (c <= "9"));
}
</script>

<form name="FeedBackForm" id="FeedBackForm" method="post"	action="${pageContext.request.contextPath}/feedBack.htm">
<input type="hidden" name=dest value = "ecse">
<input type="hidden" name="pageId" value = "${pageId}">
	<div id="content">
		<div class="gutter">			
			<div class="box">
				<h2>Feedback Form</h2>
				<hr />					
				<c:if test="${!empty param.message}">
					<div class="error message-box">${param.message}</div>
				</c:if>
				<c:if test="${!empty param.success}">
					<div class="success message-box">${param.success}</div>
				</c:if>
			</div>
				
			<table>
				<tr> 
		            <td width="14%">First Name: <font color="#FF0000" size="3"><b>*</b></font></td>
		            <td colspan="5"> 
		              <input type="text" name="firstName" size="15" maxlength="15">
		            </td>
		        </tr>
		        <tr> <td colspan="6">&nbsp;</td></tr>
		        <tr> 
		            <td width="14%">Middle Initial:</td>
		            <td colspan="5"> 
		              <input type="text" name="middleInit" size="1" maxlength="1">
		            </td>
         		</tr>
         		<tr> <td colspan="6">&nbsp;</td></tr>
         		<tr> 
		            <td width="14%">Last Name: <font color="#FF0000" size="3"><b>*</b></font> 
		              </b></td>
		            <td colspan="5"> 
		              <input type="text" name="lastName" size="17" maxlength="17">
		            </td>
	        			</tr>
	        			<tr> <td colspan="6">&nbsp;</td></tr>
	        			<tr> 
		            <td width="14%" valign="top">MPI#:</td>
		            <td colspan="5"> 
		              <input type="text" name="mpi" size="10" maxlength="10">
		              <br>If you 
		              pay or receive child support, please provide this number so that 
		              we can serve you better.</td>
         		</tr>
         		<tr> <td colspan="6">&nbsp;</td></tr>
         		<tr> 
		            <td width="14%" height="18">Child(ren): </td>
		            <td width="16%" height="18"> 
		              <div align="left">First Name</div>
		            </td>
		            <td width="3%" height="18">MI</td>
		            <td width="31%" height="18">Last Name</td>
		            <td width="15%" height="18">&nbsp;</td>
         		</tr>
         		<tr> 
		            <td width="14%">&nbsp;</td>
		            <td width="16%"> 
		              <div align="left"> 
		                <input type="text" name="child1fname" size="15" maxlength="15">
		              </div>
		            </td>
		            <td width="3%"> 
		              <input type="text" name="child1mname" size="1" maxlength="1">
		            </td>
		            <td width="31%"> 
		              <input type="text" name="child1lname" size="17" maxlength="17">
		            </td>
		            <td width="15%">&nbsp; </td>
		            <td width="21%">&nbsp; </td>
         		</tr>
         		<tr> 
		            <td width="14%">&nbsp;</td>
		            <td width="16%"> 
		              <div align="left"> 
		                <input type="text" name="child2fname" size="15" maxlength="15">
		              </div>
		            </td>
		            <td width="3%"> 
		              <input type="text" name="child2mname" size="1" maxlength="1">
		            </td>
		            <td width="31%"> 
		              <input type="text" name="child2lname" size="17" maxlength="17">
		            </td>
		            <td width="15%">&nbsp; </td>
		            <td width="21%">&nbsp; </td>
         		</tr>
         		<tr> 
		            <td width="14%">&nbsp;</td>
		            <td width="16%"> 
		              <div align="left"> 
		                <input type="text" name="child3fname" size="15" maxlength="15">
		              </div>
		            </td>
		            <td width="3%"> 
		              <input type="text" name="child3mname" size="1" maxlength="1">
		            </td>
		            <td width="31%"> 
		              <input type="text" name="child3lname" size="17" maxlength="17">
		            </td>
		            <td width="15%">&nbsp; </td>
		            <td width="21%">&nbsp; </td>
         		</tr>
         		<tr> 
		            <td width="14%">&nbsp;</td>
		            <td width="16%"> 
		              <div align="left"> 
		                <input type="text" name="child4fname" size="15" maxlength="15">
		              </div>
		            </td>
		            <td width="3%"> 
		              <input type="text" name="child4mname" size="1" maxlength="1">
		            </td>
		            <td width="31%"> 
		              <input type="text" name="child4lname" size="17" maxlength="17">
		            </td>
		            <td width="15%">&nbsp; </td>
		            <td width="21%">&nbsp; </td>
         		</tr>
       		 	<tr><td colspan="6">&nbsp;</td></tr>
         		<tr> 
		            <td width="14%">Address:</td>
		            <td colspan="5"> 
		              <input type="text" name="addressLine" size="40" maxlength="40">
		            </td>
          		</tr>
          		<tr><td colspan="6">&nbsp;</td></tr>
          		<tr> 
		            <td width="14%">City:</td>
		            <td colspan="3"> 
		              <input type="text" name="city" size="40" maxlength="40">
		            </td>
		            <td width="15%">State:</td>
		            <td width="21%" colspan="3"> 
		              <select name="state">
		              	<c:forEach var="state" items="${stateLookup}">
							<option value="${state.getCodeId()}">${state.getCodeDesc()}</option>
						</c:forEach>
		              </select>
		             </td>
             	</tr>
             	<tr><td colspan="6">&nbsp;</td></tr>
             	<tr> 
		            <td width="14%">County:</td>
		            <td colspan="5"> 
		              <select name="county">
		              	<c:forEach var="cnty" items="${countyLookup}">
							<option value="${cnty.getCodeId()}">${cnty.getCodeDesc()}</option>
						</c:forEach> 
		              </select> (NC Residents)</td>
          		</tr>
          		<tr><td colspan="6">&nbsp;</td></tr>
          		<tr> 
		            <td width="14%" valign="top">E-mail:<font color="#FF0000" size="3"><b>*</b></font></b></td>
		            <td colspan="5"> 
		              <input type="text" name="email" size="50" maxlength="50">
		              <br>So that we can respond to all requests, we ask that you provide an 
              				email address for correspondence.
		            </td>
          		</tr>
          		<tr><td colspan="6">&nbsp;</td></tr>
          		<tr> 
		            <td width="14%">Phone:</td>
		            <td colspan="5"> 
		              <input type="text" name="phone" size="10" maxlength="10">
		              (ex: 9195551212) </td>
          		</tr>
          		<tr><td colspan="6">&nbsp;</td></tr>				
			</table>
			<table>
				<tr> 
		            <td colspan="2"> 
		              <div align="left"><b>Area 
		                of Interest/Feedback (check one or more)</b></div>
		            </td>
	          	</tr>
	          	<tr> 
		            <td width="60%"> 
		              <input type="checkbox" name="support" value="Y">Support Issues
		             </td>
		            <td width="40%"> 
		              <input type="checkbox" name="other" value="Y">Other 
		             </td>
	         		</tr>
	         		<tr> 
		            <td width="60%"> 
		              <input type="checkbox" name="enforcement" value="Y">Enforcement Issues</td>
		            <td width="40%">&nbsp;</td>
	        		 </tr>
	       		 <tr> 
		            <td width="60%"> 
		              <input type="checkbox" name="address" value="Y">Address Change</td>
		            <td width="40%">&nbsp;</td>
	       		 </tr>
	      		 <tr><td colspan="2">&nbsp;</td></tr>
	      		 <tr> 
		            <td colspan="2"> 
		              <div align="left"><b>Please 
		                give a brief description of request</b></div>
		            </td>
          		</tr>
		        <tr><td colspan="2">&nbsp;</td>
        	 	</tr>
        	 	<tr> 
		            <td colspan="2"> 
		              <div align="left"> 
		                <textarea name="feedback" cols=60 rows=15 maxlength="3900"></textarea>
		              </div>
		            </td>
        		 </tr>
        		 <tr><td colspan="2">&nbsp;</td>
        		 <tr> 
		            <td colspan="2" height="40"> 
		              <div align="left">
		              	<b>Please allow up to 5 business days for a response.</b>
		              	<br>All required fields are denoted by <font color="#FF0000"><b><font size="3">*</font></b></font>
		              </div>
            		</td>
           		</tr>
           		<tr><td colspan="2">&nbsp;</td>
           		<tr>
           			<td align="right"><input type="submit" name="sbmt" id="sbmt" value="Submit" onclick="return validateFeedback()"></td>
          			<td align="center"><input type="button" name="clbtn" id="clbtn" value="Reset" onclick="document.getElementById('FeedBackForm').reset();"></td> 
          		</tr>
			</table>		
		</div>
	</div>
</form>








