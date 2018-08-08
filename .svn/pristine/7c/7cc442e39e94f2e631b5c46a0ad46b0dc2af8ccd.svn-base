<%@ include file="../../../include/taglib.jsp"%>

<script>
	function noEnterKey(evt) {
      var evt = (evt) ? evt : ((event) ? event : null);
      if (evt.keyCode == 13)  {
          return false;
      }
    }
	document.onkeypress = noEnterKey;
	
	function goToHomePage()
	{
		location.href="index.jsp";
	}
	
	function toggleInfo(selectObject)
	{
		var id = selectObject.id+"_info";
		var value = selectObject.value;
		if (value=="y"){
			document.getElementById(id).style.display='block';
		}
		if (value=="n"){
			document.getElementById(id).style.display='none';
		}
	}
		
  	function confirmSaveAndExit()
	{
		showLightbox('saveAndExitDiv',400,160,100,200);
	}
	
	function validateform() 
	{	
		var result = true;
		var name_regex = /^[a-zA-Z]+$/;
		
		if (document.OnlineAppApplicantInfo.applicantFNm.value == ""){
			alert("First name should not be empty!!");
			document.OnlineAppApplicantInfo.applicantFNm.focus();
			return false;
			
		}
		
		if (document.OnlineAppApplicantInfo.applicantLNm.value == ""){
			document.OnlineAppApplicantInfo.applicantLNm.focus();
			alert("Last name should not be empty!!");
			return false;		
		}
		
		if (!name_regex.test(document.OnlineAppApplicantInfo.applicantFNm.value.trim())){
			alert("Special characters and numbers are not allowed in first name.");
			document.OnlineAppApplicantInfo.applicantFNm.focus();
			return false;
		}
		
		if (document.OnlineAppApplicantInfo.applicantMNm.value !="" && !name_regex.test(document.OnlineAppApplicantInfo.applicantMNm.value.trim())){
			alert("Special characters and numbers are not allowed in middle name.");
			document.OnlineAppApplicantInfo.applicantMNm.focus();
			return false;
		}
		
		if (!name_regex.test(document.OnlineAppApplicantInfo.applicantLNm.value.trim())){
			alert("Special characters and numbers are not allowed in last name.");
			document.OnlineAppApplicantInfo.applicantLNm.focus();
			return false;
		}
		
		if (document.OnlineAppApplicantInfo.signFirstName.value == ""){
			alert("Please enter your first name as signature!!");
			document.OnlineAppApplicantInfo.signFirstName.focus();
			return false;			
		}
		if (document.OnlineAppApplicantInfo.signLastName.value == ""){
			alert("Please enter your last name as signature!!");
			document.OnlineAppApplicantInfo.signLastName.focus();
			
			return false;			
		}
		
		if (!name_regex.test(document.OnlineAppApplicantInfo.signFirstName.value.trim())){
			alert("Special characters and numbers are not allowed in signature first name.");
			document.OnlineAppApplicantInfo.signFirstName.focus();
			return false;
		}
		
		if (document.OnlineAppApplicantInfo.signMiddleInitial.value !="" && !name_regex.test(document.OnlineAppApplicantInfo.signMiddleInitial.value.trim())){
			alert("Special characters and numbers are not allowed in signature middle name.");
			document.OnlineAppApplicantInfo.signMiddleInitial.focus();
			return false;
		}
		
		if (!name_regex.test(document.OnlineAppApplicantInfo.signLastName.value.trim())){
			alert("Special characters and numbers are not allowed in signature last name.");
			document.OnlineAppApplicantInfo.signLastName.focus();
			return false;
		}
		
		 //county selection checking code.
		
		var sel = document.getElementById('applCounty');
		 var opt;
		 var selLength = sel.options.length;
	    for ( var initial = 0;  initial < selLength; initial++ ) {
	        opt = sel.options[initial];
	        if ( opt.selected == true && opt.value != "") {
	           
	        	break;
	        }
	    }
	   
	    if(initial == selLength)
		{
	    	alert("Select county where you want to apply for services");
	    	document.OnlineAppApplicantInfo.applCounty.focus();
	    	return false;
		}
		
	    //role selection checking.
		var role = document.OnlineAppApplicantInfo.partType;
		 
		 var k=0;
		 for( k=0;k<role.length;k++)
		 {
			 if(role[k].checked)
				break;	 
		 }
		 
		 if(k==role.length)
	    	{
	    	 alert("Please select your role in application as either CP / NCP / Child CP!!");
	       
			  return false;	
		     } 
		 
		var benefitRadio = document.OnlineAppApplicantInfo.bReceipient;
		var benefit = document.OnlineAppApplicantInfo.benefits;
		   if(benefitRadio[1].checked)
			 {
			   var i=0;
			   var j=0;
			  for( i=0;i<benefit.length;i++) 
				 if (!benefit[i].checked)
					 j++;
						
			    if(j==benefit.length)
			    	{
			    	 alert("Benefit(s) must be selected to continue.");
			       
					  return false;	
				     }
			    }
		   
		   		
		if (document.getElementById('cp').checked){
			
			var c = document.OnlineAppApplicantInfo.partPrntGrdian;
			
	        
	       if(!(c[0].checked || c[1].checked))
	      {   
	      
	        alert("Your role is CP (Custodial Parent). Select Parent or Guardian to continue.");
	       
	        return false;
		   }
		
       }
		
	
		
		if(result)
		{
			
		var mode = document.OnlineAppApplicantInfo.mode.value;
		
		    
			
		if (mode =='create')
		{	
			
		   var radios = document.getElementsByName('partType');
		     var a = null;

         for (var i = 0, length = radios.length; i < length; i++) 
            if (radios[i].checked) 
               a= radios[i].value;
              
		    switch (a)
            {
               case '1': 
            	   
            	   if(confirm("Role cannot be changed after page is saved. \n \n Confirm role as custodial parent(CP)?") )
       	          
       	                       result  = true;
                            else
                               result = false;
                           break;
               
               case '2': 
            	   
            	   if(confirm("Role cannot be changed after page is saved.\n \n Confirm role as noncustodial parent(NCP)?") )
        	         {
            		   result  = true;
        	         }
                     else
                   	 {
                    	 result = false;
                   	 }
                         break;
            
            
               case '3': 
            	   
            	      if(confirm("Role cannot be changed after page is saved. \n \n Confirm role as Minor child applicant(CP)?") )
  	          		  {
                         result = true;
  	          		  }
            	      else
                      {
                         result = false;
                      }
               break;
            }
		    
		}}
		
				
		if(result)
			
	   {
		 noBenefitsRec();
		 document.OnlineAppApplicantInfo.submit();
		 buttonsDisabled(true);
		 
	   }
		return result;
	}
	
	
	function tanfChecked()

	{
		var benefit = document.OnlineAppApplicantInfo.benefits;
		
		if(benefit[0].checked)
		{
			alert("Application fee may be required.");
		}
		
		
	}
	
	function workfChecked()

	{
		var benefit = document.OnlineAppApplicantInfo.benefits;
		
		if(benefit[1].checked)
		{
			alert("Application fee may be required.");
		}
		
		
	}

	function medicalChecked()

	{
		var benefit = document.OnlineAppApplicantInfo.benefits;
		if(benefit[2].checked)
		{
			alert("Application fee may be required.");
		}
		
		
	}

	function fosterChecked()
	{
		var benefit = document.OnlineAppApplicantInfo.benefits;
		if(benefit[3].checked)
		{
			alert("Application fee may be required.");
		}
	}

	function noBenefitsRec()
	{
		var radioSelect = document.OnlineAppApplicantInfo.bReceipient;
		
		
		if(radioSelect[0].checked)
		{	
		alert("Application fee must be paid to the child support agency for services to begin.\n \nMake payments via certified check, money order or visit the local office to make cash payment.");	
		}
		
	}
	
 	function childClicked()
	{
			 
		var c = document.OnlineAppApplicantInfo.partType;
			
			
			for(var i=0;i<c.length;i++){ 
				
			 c[i].checked = (c[i].value=="1")?true:false; 
			 
			}
	} 
 
function parentClicked(selObj)
{
	
   var c = document.OnlineAppApplicantInfo.partPrntGrdian;
	
	for(var i=0;i<c.length;i++){ 
		
		 c[i].checked = false; 
		 
		}
	if(selObj.value == 2)
	{
		alert("Enter information for whomever the child resides with in section 2. Enter your information in section 4.");	
	}else if (selObj.value == 3)
	{
		alert("Enter your information in section 2-CP Information and section 3-Child Information.");
	}
	
}
	
	function benefitsClicked()
	{
		
		
		var radioSelect = document.OnlineAppApplicantInfo.bReceipient;
		
		var benefit = document.OnlineAppApplicantInfo.benefits;
		
		
		for(var i=0;i<benefit.length;i++){ 
			
			if (benefit[i].checked){
				
				radioSelect[1].checked= true;
				radioSelect[0].checked= false;
				
			       break;
			       }
			}
		       
		        if(i==benefit.length)
		        	{
		        	radioSelect[0].checked= true;  
		        	 
		        	}
		            
		}
	
    function benefitsRadioClick()	
    {		
		var radioSelect = document.OnlineAppApplicantInfo.bReceipient;
	
		var benefit = document.OnlineAppApplicantInfo.benefits;
	
           for(var i=0;i<benefit.length;i++){ 
		
		   benefit[i].checked= false;		       
		}
        alert("Application fee may be required.");
    }
	
	function goToPreviousPage()
	{
	 	if(document.OnlineAppApplicantInfo.applSbmt.value === "false")
 		{
		 	if (confirm("Unsaved information will be lost. Leave this page?")){        
				location.href="applicantRNR.htm";
				buttonsDisabled(true);
			}else{
				return false;
			}
		}
		else{
			location.href="applicantRNR.htm";
		}	
	}
	
	function goToNextPage()
	{
	 	if(document.OnlineAppApplicantInfo.applSbmt.value === "false")
 		{
		 	if (confirm("Unsaved information will be lost. Leave this page?")){        
				location.href="caseApplicationCPInfo.htm";
				buttonsDisabled(true);
			}else{
				return false;
			}
		}
		else{
			location.href="caseApplicationCPInfo.htm";
		}	
	}
	
	
	
	
</script>


<form name="OnlineAppApplicantInfo" id="OnlineAppApplicantInfo"	method="post" action="${pageContext.request.contextPath}/cssp/user/applicantInfo.htm" 
	modelAttribute="applicantInfo">
	<input  type="hidden" name="mode" value="${mode}"> 
	<input	type="hidden" name="stepNum" value="${flow.getStepNum()}">
	<input type="hidden" name="applSbmt" value="${appInfo.isApplSbmtd()}">

	<div id="aplContent">

		<div class="gutter2">

			<div>
				<h3>SECTION 1- APPLICANT/PUBLIC ASSISTANCE RECIPIENT INFORMATION</h3>
				<hr />
				<c:if test="${!empty param.message}">
					<div class="error message-box">${param.message}</div>
				</c:if>
				<c:if test="${!empty param.success}">
					<div class="success message-box">${param.success}</div>
				</c:if>
			</div>
			<br>
			<c:if test="${appInfo.isApplSbmtd()}">
				<div disabled="disabled">
			</c:if>
			<div>
				<p>
					<b>I,
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<sub>First Name:</sub> 
						<input class="signature" size="20"	maxlength="15" name="applicantFNm" id="applicantFNm" value="${applicantInfo.getApplicantFNm()}"
						title="Please enter your First Name" type="text">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<sub>Middle Name:</sub> 
						<input class="signature" size="20"	maxlength="15" name="applicantMNm" id="applicantMNm" value="${applicantInfo.getApplicantMNm()}"
						title="Please enter your Middle Name" type="text"> &nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<sub>Last Name:</sub> 
						<input class="signature" size="20"	maxlength="17" name="applicantLNm" id="applicantLNm" value="${applicantInfo.getApplicantLNm()}"
						title="Please enter your Last Name" type="text">
					</b>
				</p>
			</div>
			<br>

			<div>
				<b>MUST SELECT ONE</b> <br>
				<dl class="feature">
					<dt>
						<input type=radio name='bReceipient'
							title="click this button if you do not receive any benefits" ${benefitExists eq false && mode == 'update'? 'checked' : ''}
							onclick="benefitsRadioClick();" ></input>DO NOT RECEIVE:
							
					</dt>
					<br>
					<p>Temporary Assistance for Needy Families (TANF), Work
						First, Medicaid or Foster Care services from the Department of
						Social Services for the child(ren) named below. I am applying for
						services of the child support agency for the benefit of the
						child(ren) listed below. I understand that this application
						establishes a contract with North Carolina Child Support Services
						and that services will begin once the completed application and
						any required application fee are received by the local child
						support agency</p>
					<br>
					<dt>
						<input type=radio name='bReceipient'
							title="click this button if you do receive any benefits"
							${benefitExists eq true ? 'checked' : ''}></input>AM A RECIPIENT OF assistance for the child(ren) listed below: ( Select all that apply )
					</dt>
					<br>					   
					<dd>
						<label for="item01"> <input type=checkbox id=''
							name="benefits" onclick ="tanfChecked(); benefitsClicked();"  value="1"
							${benefitsMap.containsKey('1') ? 'checked': '' }></input>Temporary
							Assistance for Needy Families (TANF)
						</label>
					</dd>
					<br>
					<dd>
						<label for="item02"> <input type=checkbox id=''
							name="benefits" onclick="workfChecked(); benefitsClicked();" value="2"
							${benefitsMap.containsKey('2') ? 'checked': '' }></input>Work
							First
						</label>
					</dd>
					<br>
					<dd>
						<label for="item03"> <input type=checkbox id=''
							name="benefits" onclick="medicalChecked(); benefitsClicked();" value="3"
							${benefitsMap.containsKey('3') ? 'checked': '' }></input>Medicaid
						</label>
					</dd>
					<br>
					<dd>
						<label for="item04"> <input type=checkbox id=''
							name="benefits" onclick="fosterChecked(); benefitsClicked();" value="4"
							${benefitsMap.containsKey('4') ? 'checked': '' }></input>Foster
							Care 
						</label>
					</dd>
					
				<p> I understand that eligibility for this assistance may require me to work with the child support agency in pursuing support for the child(ren).  I agree to cooperate fully with these efforts, unless I present good cause and am granted exemption from this requirement by the Work First, Medicaid or Foster Care agency.</p>
					
						
				</dl>
			</div>

			<br>

			<p title="Name(s) will display after Section 3 is completed.">
				<b>List of child(ren) for whom child support services are being
					requested:</b>
			</p>

			<table class="reportTable">
				<tr>
					<th>First Name</th>
					<th>Middle Name</th>
					<th>Last Name</th>
				</tr>
				<c:forEach var="child" items="${childrenInfo}">
					<tr>
						<td>${child.getApplicantFNm()}</td>
						<td>${child.getApplicantMNm()}</td>
						<td>${child.getApplicantLNm()}</td>
					</tr>
				</c:forEach>
			</table>
			<br>
			<p>
				<b title="This is key info for the application and it decides your role in application">Do
					the child(ren) live with you? </b>
			</p>

			<dl class="feature">
				<dt>
					<input type="radio" name="partType" onClick="radioChanged();" id="cp" value="1" ${applicantInfo.getPartType() == '1' ? 'checked' : ''}
						${mode.equals('update') ? 'disabled' : ''} />
						Yes - Your role in	the support case is the Custodial Parent (CP), if you are or are not the child's parent.
				</dt>
				<dd>
					<input type="radio" name="partPrntGrdian" onclick="childClicked();"	value="1"
						${applicantInfo.getPartPrntGrdian() == '1' ? 'checked' : ''}
						${mode.equals('update') ? 'disabled' : ''} />Parent
				</dd>
				<dd>
					<input type="radio" name="partPrntGrdian" onclick="childClicked();"	value="2"
						${applicantInfo.getPartPrntGrdian() == '2' ? 'checked' : ''}
						${mode.equals('update') ? 'disabled' : ''} />Guardian
				</dd>
				<br>
				<dt>
					<input type="radio" name="partType" onClick="parentClicked(this);" id="ncp" value="2"
						${applicantInfo.getPartType() == '2' ? 'checked' : ''}
						${mode.equals('update') ? 'disabled' : ''} />No - Your role in the support case is the Noncustodial Parent (NCP).
				</dt>
				<br>
				<dt>
					<input type="radio" name="partType" onClick="parentClicked(this);" id="childcp" value="3"
						${applicantInfo.getPartType() == '3' ? 'checked' : ''}
						${mode.equals('update') ? 'disabled' : ''} />I am a minor child	applying for myself - Your role in the support case is the Custodial Parent (CP).
				</dt>
			</dl>

			<br>

			<fieldset>
				<p>
					Do you currently receive or have you applied for enforcement services from North Carolina, another state, a country outside of
					the United States of America (USA) or a private company for support of	any of the children included in this application? 
					<select	id="enforecment" name ="enforecmentExists" onchange="toggleInfo(this)" title="Select from picklist. Enter additional information if applicable.">
						<option value="n" ${enforceExists eq false ? 'selected' : ''}>No</option>
						<option value="y" ${enforceExists eq true ? 'selected' : ''}>Yes</option>
					</select>
				</p>

				<div id="enforecment_info"
					style="display: ${enforceExists eq true ? 'block' : 'none'}"
					class=center>
					<table>
						<tr>
							<td><label>State: </label><select name="enforceSt">
									<c:forEach var="state" items="${stateLookup}">
										<option value="${state.getCodeId()}" ${appInfo.getEnforState().equals(state.getCodeId()) ? 'selected' : ''}>
											${state.getCodeDesc()}</option>
									</c:forEach>
							</select></td>
							<td>&nbsp;</td>

							<td><label>Country: </label> <select name="enforceCntry">
									<c:forEach var="ctry" items="${countryLookup}">
										<option value="${ctry.getCodeId()}"
											${appInfo.getEnforCountry().equals(ctry.getCodeId()) ? 'selected' : ''}>${ctry.getCodeDesc()}</option>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<td colspan="3" align="left"><label>Name of private company: </label>
							<input type="text" name="enforceNm"	value="${appInfo.getEnforCompanyName()}" size="30"	maxlength="40"></td>
						</tr>
					</table>
				</div>
			</fieldset>
			<br>
			<fieldset>
				<p title="Enter your private attorney details.">	Do you currently employ an attorney or private collection agency for child support? 
					<select name="agencyExists" id="agency"	onchange="toggleInfo(this)">
						<option value="n"	${thirdPartyAgencyExists eq false ? 'selected' : ''}>No</option>
						<option value="y"	${thirdPartyAgencyExists eq true ? 'selected' : ''}>Yes</option>
					</select>
				</p>
				<div id="agency_info"	style="display: ${thirdPartyAgencyExists eq true ? 'block' : 'none'}"	class=center>
					<table>
						<tr>
							<td colspan="3" align="left"><label>Attorney or Agency Name: </label>
							
							<input type="text" name="agencyNm" value="${agencyBean.getThirdPartyNm()}" size="30" maxlength="40"></td>
						</tr>
						<tr>
							<td align="left" colspan="3"><label>Phone Number:</label>
							
								<input type="text" name="a_ph1"	value="${agencyBean.getPhoneAreaCode()}" size="3" maxlength="3">
								- <input type="text" name="a_ph2" value="${agencyBean.getPhoneExc()}" size="3" maxlength="3">
								- <input type="text" name="a_ph3" value="${agencyBean.getPhoneLn()}" size="4" maxlength="4">
							</td>
							
						</tr>
						
						<tr>
							<td><table><tr>
								<td align="left">
									<label>Street: </label>
									<input type="text" name="agStreet" value="${agencyBean.getAddressLn1()}" size="40" maxlength="40">
								</td>
								<td>&nbsp;</td>
								<td align="left">	
									<label>City:</label>
									<input type="text" name="agCity" value="${agencyBean.getThirdPartyCity()}" size="25" maxlength="25">
								</td>
								<td>&nbsp;</td>
							</tr></table></td>
						</tr>
						<tr>
							<td><table><tr>
							<td align="left"><label>State: </label> <select name="agSt">
									<c:forEach var="state" items="${stateLookup}">
										<option value="${state.getCodeId()}"
											${agencyBean.getThirdPartyState().equals(state.getCodeId()) ? 'selected' : ''}>${state.getCodeDesc()}</option>
									</c:forEach>
							</select></td>
							<td>&nbsp;</td>
							<td align="left"><label>Country: </label>
							<select name="agCtry">
									<c:forEach var="ctry" items="${countryLookup}">
										<option value="${ctry.getCodeId()}"
											${agencyBean.getCountryNm().equals(ctry.getCodeId()) ? 'selected' : ''}>${ctry.getCodeDesc()}</option>
									</c:forEach>
							</select></td>
							<td>&nbsp;</td>
							<td align="left">
								<label>Zip:</label>
									<input type="text" name="agZip"  value="${agencyBean.getThirdPartyZip5()}" size="5" maxlength="5">
							</td>
						</tr></table></td>		
						</tr>
					</table>
				</div>
			</fieldset>
			<br>
			<p
				title="Select yes to receive case related information via email">
				May we contact you by email? <select name="canContact" title="Select yes to receive case related information via email">>
					<option value="y" ${appInfo.getContactViaEmail().equals("y") ? 'selected' : ''} >Yes</option>
					<option value="n" ${appInfo.getContactViaEmail().equals("n") ? 'selected' : ''}>No</option>
				</select>
			</p>
			<br>
			<p>
				Application County<select  id="applCounty" name="applCounty" title="Your application will be sent to the county selected.">
					<c:forEach var="cnty" items="${countyLookup}">
						<option value="${cnty.getCodeId()}"
							${appInfo.getApplCounty().equals(cnty.getCodeId()) ? 'selected' : ''}>${cnty.getCodeDesc()}</option>
					</c:forEach>
				</select>
			</p>
			<br>

			<dl class="feature">
				<dt>I confirm that the information provided above is true to
					the best of my knowledge.</dt>
				<br>

				<dd>
					<label id="signaturetitle">Signature: </label> <input
						class="signature" type="text" name="signFirstName" id="nmSignF"
						value="${signBean.getNmSignF()}" size="20" maxlength="15" /> &nbsp;&nbsp;
						<input class="signature" type="text" name="signMiddleInitial"
						id="nmSignMI" value="${signBean.getNmSignMI()}" size="1"
						maxlength="1" /> &nbsp;&nbsp;
						<input class="signature" type="text" name="signLastName" id="nmSignL" value="${signBean.getNmSignL()}"
						size="20" maxlength="17" />
				</dd>

				<br>
				<dd>
					<label id="signaturetitle"> Date: </label> <input type="text"
						name="applicantSignDt" class="signature"
						value="${signBean != null ? signBean.getDateSignedDtStr() : defaultDate}" size="25"
						maxlength="30" readonly="readonly">
				</dd>

				<br>
			</dl>
			<c:if test="${appInfo.isApplSbmtd()}">
				</div>
			</c:if>
			<br><br>
			 <div>				
					<table width="80%" border="0">
						<tr>
							<td>&nbsp;&nbsp;</td><td>&nbsp;&nbsp;</td>
							<td align="left"><input type=button value='Previous' onclick="goToPreviousPage()" /></td>
							<c:if test="${!appInfo.isApplSbmtd()}">
								<td>&nbsp;&nbsp;</td><td>&nbsp;&nbsp;</td>
								<td align="center"><input type="button" name="saveButton" id="save" value="Save" onclick="return validateform();"></td>
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







