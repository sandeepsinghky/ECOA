<%@ include file="../include/taglib.jsp"%>
<script>
function goToNextPage()
{
	if((window.location.href).indexOf("caseApplicationInfo/2.htm") >= 0)
	{
		location.href="${pageContext.request.contextPath}/caseApplicationSteps/2.htm";
	}
	else
		location.href="${pageContext.request.contextPath}/caseApplicationSteps/1.htm";
}

function goToPreviousPage()
{
	if((window.location.href).indexOf("caseApplicationInfo/2.htm") >= 0)
	{
		location.href="${pageContext.request.contextPath}/cssp/user/parentLoggedInHome.htm";
	}
	else {
		location.href="${pageContext.request.contextPath}/loggedInHome.htm";
	}
	
}
</script>

<div id="content">
	<div class="gutter">
		<h2>Applying Online for Child Support Services</h2>
		<p>Thank you for your interest in the North Carolina Child Support
			Services (NCCSS) program. Child support services are available to all
			applicants: parents, alleged fathers, non-parent caretakers, minor
			children, social services agencies and judicial officials. If you
			decide to apply for child support services, please complete the
			application in its entirety and sign where appropriate.</p>

		<p>You will be asked to register for an individual NCID (North
			Carolina Identity Management). NCID is the standard identity
			management and access service provided to state, local, business, and
			individual users. NCID provides a high degree of security and access
			control to real-time resources. You will use your NCID and password
			to gain access to the online application as well as other state
			services (e.g. ePass). For additional information see <a href="https://it.nc.gov/services/service-directory/core-services/nc-identity-management-ncid/ncid/ncid-frequently-asked" target="_new">NCID FAQ<a>.
			</p>

		<p>If you already have an NCID as an employee of a county or state
			child support agency in North Carolina, you will need to register for
			an individual (applicant) NCID to apply for child support services online.</p>

		<p>If you are a member of the Eastern Band of Cherokee Indians
			(EBCI) living on the reservation and wish to receive child support
			services through one of the surrounding North Carolina counties, you
			may apply for services online; otherwise, please contact an EBCI
			representative for services through the EBCI.</p>

		<p>We will ask you to provide an e-mail address to send automated
			messages to you about your application. You must agree to receive
			these messages to apply for services online. The online application
			must be completed and submitted within 10 business days of when it is
			started. Before services can begin, the local child support agency
			must receive the non-refundable $25 application fee. The application
			fee must be a certified check or money order made payable to the
			specific county that you are requesting to handle your support case
			(e.g. "Wake County Child Support Services"). Some local child support
			agencies may also accept cash payment when applying in person. If
			your income is below 100 percent of the Federal Poverty Guidelines,
			you may qualify for a reduced non-refundable $10 application fee.
			Please contact the child support agency if you need assistance
			determining if you qualify for a reduced application fee.</p>

		<b>To assist with processing your application, the local child
			support agency may ask you to provide the following: </b>

		<ul>
			<li>Proof of your income, if you are the child(ren)'s parent
				(e.g. pay stubs, tax returns, etc.)</li>

			<br>
			<li>Copy of your photo ID (e.g. driver's license)</li>

			<br>
			<li>Copy of each child's state-issued birth certificate and
				Social Security card</li>

			<br>
			<li>Photo of the child's other parent (noncustodial parent from
				whom child support services are being requested)</li>

			<br>
			<li>Copy of marriage certificates; if not available, provide
				dates of marriage and/or other verification of marital status of the
				children's parents</li>

			<br>
			<li>Copy of any legal documents related to the child(ren)
				included in this application, such as:

				<ul>

					<li>Affidavit of Parentage - a legal document signed
						voluntarily by both parents (either in the hospital or at any time
						after the birth of the child) that establishes paternity</li>

					<li>Paternity order (court order establishing paternity)</li>

					<li>Child support and/or spousal support order(s) (all initial
						and modified orders)</li>

					<li>Agreement signed voluntarily by the parents for child
						support</li>

					<li>Copy of the child(ren)'s parent's marriage and/or divorce
						decree</li>

					<li>Order(s) terminating parental rights</li>

					<li>Domestic violence protective order(s)</li>
				</ul>

			</li>

			<br>
			<li>Payment records of all support paid directly to the
				custodial parent or through a court</li>
		</ul>
		<p>If you need additional information or assistance in completing
			the application, you may call the NCCSC Customer Service Center at
			1-800-992-9457 or contact a local child support agency.</p>

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
