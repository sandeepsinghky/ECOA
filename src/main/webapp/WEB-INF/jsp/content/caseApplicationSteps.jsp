<%@ include file="../include/taglib.jsp"%>
<script>
function goToNextPage()
{
	if((window.location.href).indexOf("caseApplicationSteps/2.htm") >= 0)
	{
		location.href="${pageContext.request.contextPath}/cssProgramInfo/2.htm";
	}
	else
		{location.href="${pageContext.request.contextPath}/cssProgramInfo/1.htm";}
		
}

function goToPreviousPage()
{
	if((window.location.href).indexOf("caseApplicationSteps/2.htm") >= 0)
	{
		location.href="${pageContext.request.contextPath}/caseApplicationInfo/2.htm";
	}
	else
	{		location.href="${pageContext.request.contextPath}/caseApplicationInfo/1.htm";  }
}
</script>

<div id="content">
	<div class="gutter">
		<h2>Applying Online for Child Support Services</h2>
		<p>
			<b>To start the child support services process, please complete
				the following steps:</b>
		</p>
		<br>

		<p>
			<b>Step 1:</b>
		</p>
		<ul>			
			<li><b>Read</b> the North Carolina Child Support Services
				(NCCSS) services, policies, applicant rights and responsibilities,
				and <b>sign</b>. You must type your first and last name as your
				signature. By doing so, you agree that your electronic signature is
				the legally binding equivalent to your handwritten signature</li>
		</ul>
		
		<p>
			<b>Step 2:</b>
		</p>
		<ul>
			<li><b>Complete Section 1 - Applicant/Public Assistance Recipient Information </b></li>
			<br>
			<ul>
				<li><b>You MUST select </b> the appropriate information boxes,
					and <b>sign</b>. You must type your first and last name as
					your signature. By doing so, you agree that your electronic
					signature is the legally binding equivalent to your handwritten
					signature</li>
					<br>
				<li>If you are a minor child applicant requesting child support
					services from your own parents, you are required to complete all
					sections of the application</li>
			</ul>
		</ul>
		<p>
			<b>Step 3:</b>
		</p>
		<ul>
			<li><b>Complete Section 2 - Custodial Parent/Guardian Information </b></li><br>
			Provide the caretaker of the child(ren)'s information
			<ul>
				<li>If you are the noncustodial parent applying for service,
					provide the caretaker of the child(ren)'s information</li>
					<br>
				<li><b>You will not be required to complete the income
						section if your relationship is 'guardian', 'legal representative'
						or 'other' to the child(ren) for whom you are requesting services</b>
				</li>
			</ul>
		</ul>
		<p>
			<b>Step 4:</b>
		</p>
		<ul>
			<li><b>Complete Section 3 - Minor Child Information </b></li> <br>
			<ul>
				<li>Complete for each child for whom child support services are
					being requested. Minor child applicants must complete this section</li>
			</ul>
		</ul>
		<p>
			<b>Step 5:</b>
		</p>
		<ul>
			<li><b>Complete Section 4 - Noncustodial Parent Information	</b></li>
			<br>
			<ul>
				<li>Provide information about the noncustodial parent(s) from
					whom child support services are being requested.</li>
			</ul>
		</ul>
		<p>
			<b>Step 6:</b>
		</p>
		<ul>
			<li><b>Complete Section 5 - Additional Information </b></li>
			<br>
			<ul>
				<li>List any information that has not been provided in the
					application that may assist the child support agency in processing
					your application</li>
			</ul>
		</ul>
		<p>
			<b>Step 7:</b>
		</p>
		<ul>
			<li><b>Complete Section 6 - Certification Statement </b></li>
			<br>
			<ul>
				<li><b>You MUST sign.</b> You must type your first and last name as your signature. 
				By doing so, you agree that your electronic signature is the legally binding equivalent 
				to your handwritten signature</li>
			</ul>
		</ul>
		<p>
			<b>Step 8:</b>
		</p>
		<ul>
			<li>Print and submit your application. You may print your application in its entirety before you submit your application.</li>
		</ul>


	</div>
</div>
<div>
	<table width="70%" border="0">
		<tr>
			<td align="center"><input type=button value='Print Instructions' onclick="window.open('${pageContext.request.contextPath}/reports/css-appl-inst.pdf')" /></td>
			<td align="center"><input type=button value='Previous'
				onclick="goToPreviousPage()" /></td>
			<td align="center"><input type=button value='Next'
				onclick="goToNextPage()" /></td>
		</tr>
	</table>
	<br><br>
</div>
