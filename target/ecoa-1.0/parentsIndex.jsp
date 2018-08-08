
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="author" content="VP">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/button.css" rel="stylesheet" type="text/css" />
<link href="css/general.css" rel="stylesheet" type="text/css" />
<link href="css/lightbox.css" rel="stylesheet" type="text/css" />
<link href="css/autocomplete.css" rel="stylesheet" type="text/css" />
<link href="css/calendar.css" rel="stylesheet" type="text/css" />
<link href="images/favicon.ico" rel="icon" type="image/ico" />
<title>North Carolina Child Support Services</title>
<!--[if IE]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.pngFix.pack.js"></script>
<script type="text/javascript" src="js/datetimepicker.js"></script>
<script type="text/javascript" src="js/utils.js"></script>
<script type="text/javascript" src="js/lightBox.js"></script>
<script type="text/javascript" src="js/hover.menu.js"></script>
</head>
<body>
	<div id="wrap">
		<div id="header">		
			<a href="https://www.ncdhhs.gov/"><img id="logo"/></a>
		</div>
		<div>
			<ul class="header2">
				<h3 align="center">North Carolina Child Support Services</h3>
			</ul>
		</div>
		<div>
			<ul class="nav">
				<li><a href="index.jsp">Home</a></li>
				<li><a href="parentsIndex.jsp">Parents</a></li>
				<li><a href="employersIndex.jsp">Employers</a></li>				
			</ul>
		</div>		
		<div id="content">
			<div class="gutter">
				<h2>Welcome Parents/Guardians</h2>
								               		 
	           	<p>NCID is the new way to login to North Carolina Child Support Services.</p>
	           	
	           	<p>You can <strong><a href="loginp.htm?u=tcaId">Login</a></strong> now using your old credentials to get details about your North Carolina Child Support Services case. Your old credentials will expire after ten grace logins. 	</p>
	           	<p>Already registered for NCID? <strong><a href="loginnp.htm?u=pNcId"> Login with NCID.</a></strong></p>
	           	
	           	<!-- Produ <p>Not registered your account in NCID yet? <strong><a target="_blank" href=https://ncid.nc.gov /> Register here </a></strong></p> -->
	           	
	           	 <!-- <p>Not registered for NCID yet? <strong><a target="_blank" href=https://idpdev.nc.gov:8443/nidp/idff/sso? /> Register here (DEV NCID) </a></strong></p> -->
	           	 <p>Not registered for NCID yet? <strong><a target="_blank" href=https://ncid.nc.gov /> Register here </a></strong></p>
	           	  	
	           	<B> Custodial Parents/Guardians</B>
	           	<ul>
	           		<li>Verify the status of your child support case.</li>
	           		<li>Find out if a payment is on the way.</li>
	           		<li>Learn the amount of arrears owed to you.</li>	           	
	           	</ul>
	           	
	           	<B> Noncustodial Parents</B>
	           	<ul>
	           		<li>Ensure that a payment was received.</li>
	           		<li>Verify a payment amount.</li>	           			           	
	           	</ul>
			
			<p>Only payment information is available for child support cases handled by the Clerk of Court. Contact your Clerk of Court office for information not available on this website.</p>
			
			<!-- <p> <Strong>Note:</Strong> You can now apply for child support on-line in addition to the paper application.</p>-->
				
			</div> 
		</div>
		<div id="sidebar" class="gutter">
			<div class="gutter">
				
				<div class="box">
					<h3>Print Direct Deposit Authorization Form</h3>
					<ul>
						<li><a target="_blank" href="https://www2.ncdhhs.gov/info/olm/forms/dss/DSS-4718-ia.pdf">English</a></li>
						<br>
						<li><a target="_blank" href="https://www2.ncdhhs.gov/info/olm/forms/dss/dss_spanish/DSS-4718sp.pdf">Spanish</a></li>
					</ul>
				</div>
				<div class="box">
					<h3>Services</h3>
					<ul>
						 <li><a href="parentsIndex.jsp">Parents/Guardians Login</a></li>
						<br>
						<li><a target="_blank" href="https://nc.smartchildsupport.com/">Pay Child Support</a></li>
						<br>						
						<li><a href="${pageContext.request.contextPath}/clerkOfCourtSearch/1.htm">Clerk of Court Search</a></li>
						<br>						
						<%-- <li><a href="${pageContext.request.contextPath}/clerkOfCourtSearch/AGCY.htm">CSE Office Search</a></li> --%>
						<li><a href="${pageContext.request.contextPath}/cseOfficeSearch/1.htm">CSE Office Search</a></li>
						<br>						
						<li><a href="${pageContext.request.contextPath}/cseGuideLines.htm">CSE Guidelines</a></li>
						
					</ul>
				</div>
				<div class="box">
					<h4>Help/Information</h4>
					<ul>
						<li><a href="${pageContext.request.contextPath}/contactUs/2.htm">Contact Us</a></li>
						<br>
						<li><a target="_blank" href="${pageContext.request.contextPath}/progFeesAndPolicies/2.htm">Program Fees & Policies</a></li>
						<br>
						<li><a target="_blank" href="${pageContext.request.contextPath}/privacyPolicy/2.htm">Privacy and Security</a></li>
						<br>
						<li><a target="_blank" href="${pageContext.request.contextPath}/loginHelp/2.htm">Login Help</a></li>
						<br>												
						<li><a target="_blank" href="https://nc.smartchildsupport.com/">Making A Payment</a></li>
						<br>
						<li><a target="_blank" href="${pageContext.request.contextPath}/reports/DebitCardFAQ.pdf">NCKidscard FAQ</a></li>							 					
					</ul>
				</div>
			</div>
		</div>
		<div id="footer">		
			 <div class="fwrap">   
			         
				 <div class="left">	        
					<h3><a target="_blank" href="http://www.nc.gov/">Official Web Site of North	Carolina</a></h3>	            
				</div>
					                   
				<div class="right">	                   
					 <h3><a target="_blank" href="https://www.ncdhhs.gov/">NC DHHS </a></h3>	              
				</div>	    
				    	        
				<div class="centered">	                  
					 <h3><a target="_blank" href="http://www.ncdhhs.gov/divisions/dss">NC Division of Social Services</a></h3>  
				</div>	        	
			 </div>			 
		 </div>
	</div>
</body>
</html>