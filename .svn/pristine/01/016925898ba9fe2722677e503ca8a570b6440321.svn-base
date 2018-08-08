<%@ include file="../include/taglib.jsp"%>
<script>
	function noEnterKey(evt) {
      var evt = (evt) ? evt : ((event) ? event : null);
      if (evt.keyCode == 13)  {
          return false;
      }
    }
	document.onkeypress = noEnterKey;
	
	function goToNextPage()
	{
		location.href="login.htm?u=ncId";
	}

	function goToPreviousPage()
	{
		if((window.location.href).indexOf("cssProgramInfo/2.htm") >= 0)
		{
			location.href="${pageContext.request.contextPath}/caseApplicationSteps/2.htm";
		}
		else {
			location.href="${pageContext.request.contextPath}/caseApplicationSteps/1.htm";		
		}
			
	}
</script>

<form name="CSSProgramInfoForm" id="CSSProgramInfoForm" method="post" "JavaScript: if((window.location.href).includes("cssProgramInfo/1.htm")){action="${pageContext.request.contextPath}/cssProgramInfo/1.htm";} else {action ="${pageContext.request.contextPath}/cssProgramInfo/2.htm";}>

<div id="content">
	<div class="gutter">
		<h2>Program Information</h2>
		<p>North Carolina Child Support Services (NCCSS) administers the
			North Carolina child support program under Title IV-D of the Social Security Act. 
			Services are available to a parent, alleged fathers, non-parent caretakers, minor
			children, social services agencies and judicial officials. The child
			support program's goal is to provide the best possible services to
			families for children. Whether you are making an application for
			child support services as a recipient of assistance from other social
			services programs (Temporary Assistance to Needy Families (TANF), Foster Care and/or Medicaid) 
			or requesting child support services as a non-public assistance applicant, your involvement, information, 
			and contributions are important and required.</p>
		<p align="center"><b>NORTH CAROLINA CHILD SUPPORT SERVICES INFORMATION </b></p> 
		<p><b><u>PROGRAM SERVICES</u></b></p>
		<p>NCCSS provides services to families to assist in meeting
			financial obligations for children. There is no residency or
			citizenship eligibility requirements for services. Depending on the
			circumstances of each family, one or more of the following services
			may be appropriate:
		<ul>
			<li>Location of noncustodial parent - State, federal, local 
				and national resources are used to assist in collecting information
				about a parent, such as their residence, employment and/or
				financial assets.</li>
			<br>
			<li>Paternity establishment - A determination of parental legal
				responsibility for the child(ren) is necessary before a parent can
				be required to pay support. If a child's parents were not married to
				each other at the time of the child's birth, arrangement of
				paternity testing may be offered to the parties, if appropriate.</li>
			<br>
			<li>Support establishment - In North Carolina, support is
				determined using the guidelines established in state law. NC Child
				Support Guidelines considers both parent's monthly gross incomes,
				the amount of time the child spends with each parent and various
				expenses. NCCSS seeks to establish a court order requiring a parent
				to provide child and/or medical support for child(ren) based on the
				NC Child Support Guidelines.</li>
			<br>
			<li>Collection of support - Child support payments (either bank
				draft, money order or check) are sent to NC Child Support
				Centralized Collection (NCCSCC). A large amount of child support
				payments are deducted from a parent's wages and sent to NCCSCC by an
				employer. North Carolina Child Support Services (NCCSS) records and
				disburses all collected child support payments to families by direct
				deposit (into a bank account) or debit card.</li>
			<br>
			<li>Enforcement of an established or existing child support, spousal support, and/or medical support 
			court order is met by wage withholding, tax refund offset, liens, professional license/passport 
			revocation, credit reporting, court action or other collection remedies. Support orders may be reviewed 
			for modification (increase/decrease) every three years or more often, if warranted.</li>
			</ul>
			
			<p>NCCSS <b>does not</b> provide the following services: custody, visitation or the establishment of spousal support obligations.</p>
				<br>
			<p>Please read <u>Program Fees and Policies</u> and <u>Privacy and Security</u> in the help section before applying for the child support.</p>
	</div>
</div>
<div>
	<table width="70%" border="0">
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td align="right"><input type=button value='Previous'	onclick="goToPreviousPage()" /></td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td align="left"><input type="submit" name="nextButton" id="next" value="Next"></td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
	</table>
	<br><br>
</div>
</form>