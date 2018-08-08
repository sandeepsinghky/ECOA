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
	<script type="text/javascript" src="js/jquery.js"></script>
	
	<script language="JavaScript1.2">

	var howOften = 20; //number often in seconds to rotate
	var current = 0; //start the counter at 0
	var ns6 = document.getElementById&&!document.all; //detect netscape 6

	// place your images, text, etc in the array elements here
	var items = new Array();
    
    items[0]="<a href= ''></a> ${news.get(0).getNewsText()}"; //a linked image
    items[1]="<a href=''></a>${news.get(1).getNewsText()}"; //a linked image
    items[2]="<a href=''></a>${news.get(2).getNewsText()}"; //a linked image
    /* items[3]="<a href='link.htm'><img alt='image3 (9K)' src='/Images/image3.jpg' height='300' width='300' border='0' /></a>"; //a linked image
    items[4]="<a href='link.htm'><img alt='image4 (9K)' src='/Images/image4.jpg' height='300' width='300' border='0' /></a>"; //a linked image
    items[5]="<a href='link.htm'><img alt='image5 (18K)' src='/Images/image5.jpg' height='300' width='300' border='0' /></a>"; //a linked image */

    /* function rotater() 
    {
    	document.getElementById("placeholder").innerHTML = items[current];
    	current = (current==items.length-1) ? 0 : current + 1;
    	setTimeout("rotater()",howOften*1000);
	} */

	function rotater() 
	{
	    if(document.layers) 
	    {
	        document.placeholderlayer.document.write(items[current]);
	        document.placeholderlayer.document.close();
	    }
	    if(ns6)document.getElementById("placeholderdiv").innerHTML=items[current]
	        if(document.all)
	            placeholderdiv.innerHTML=items[current];
	
	    current = (current==items.length-1) ? 0 : current + 1; //increment or reset
	    setTimeout("rotater()",howOften*1000);
	}

	window.onload=rotater;

</script>

<script>
	var slideIndex = 1;		
	
	showDivs(slideIndex);
	
	function plusDivs(n) 
	{
	  showDivs(slideIndex += n);
	}
	
	function currentDiv(n) 
	{
	  showDivs(slideIndex = n);
	}
	
	function showDivs(n) 
	{
	  var i;
	  var x = document.getElementsById("placeholderdiv");	  
	  var dots = document.getElementsByClassName("demo");
	  
	  if (n > x.length) {slideIndex = 1}    
	  if (n < 1) {slideIndex = x.length}
	  
	  for (i = 0; i < x.length; i++) 
	  {
	     x[i].style.display = "none";  
	  }
	  
	  for (i = 0; i < dots.length; i++) 
	  {
	     dots[i].className = dots[i].className.replace(" w3-white", "");
	  }
	  
	  x[slideIndex-1].style.display = "block";  
	  dots[slideIndex-1].className += " w3-white";
	}
</script>
	
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
				<li><a href="#home">Home</a></li>
				<li><a href="parentsIndex.jsp">Parents</a></li>
				<li><a href="employersIndex.jsp">Employers</a></li>
			</ul>
		</div>     	
		<div id="content">		
			<div class="gutter">			 
				<h2>Welcome to North Carolina Child Support Services eChild	Support</h2>
				<p>Our mission is to provide family centered child support services through mutual collaboration with families and partners
					using innovative strategies to reach a common goal and self-sufficiency.</p>
				<dl class="feature">				
					<dt> <img src="images/news.jpg" width="150" height="180" alt=""/>	</dt>					
					 <dd>						
						<layer id="placeholderlayer"></layer>	
						<div id="placeholderdiv">	</div>	
						
						<!-- <div class="w3-content w3-display-container"  style="max-width:800px">						
						
						 <div class="w3-center w3-container w3-section w3-large w3-text-white w3-display-bottommiddle" style="width:100%">
							 	<div class="w3-left" onclick="plusDivs(-1)">&#10094;</div>
							 	<div class="w3-right" onclick="plusDivs(1)">&#10095;</div>
							
							    <span class="w3-badge demo w3-border w3-transparent w3-hover-white" onclick="currentDiv(1)"></span>
							    <span class="w3-badge demo w3-border w3-transparent w3-hover-white" onclick="currentDiv(2)"></span>
							    <span class="w3-badge demo w3-border w3-transparent w3-hover-white" onclick="currentDiv(3)"></span>							    
						 </div>
						  
						</div>			 -->
					</dd>	
				
				</dl>				
				
				
				<p>North Carolina Child Support Services are available to parents and/or nonparent caretakers of minor children. Services
					provided by North Carolina Child Support Services include: location, establishment of paternity, establishment or modifying of
					child support orders, enforcement of child support orders, collection and processing of child support ordered payments.</p>
			</div>
		</div>
		<div id="sidebar" class="gutter">
			<div class="gutter">				
				<div class="box">
					<h3>Print Application Form</h3>
					<ul>
						<li><a target="_blank" href="https://files.nc.gov/ncdhhs/dss-4451-ia.pdf">English</a></li>
						<br>
						<li><a target="_blank" href="https://files.nc.gov/ncdhhs/DSS-4451%2520S.pdf">Spanish</a></li>
					</ul>
				</div>
				<div class="box">
					<h3>Services</h3>
					<ul>
						<li><a href="${pageContext.request.contextPath}/caseApplicationInfo/1.htm">Apply Online for Child Support Services</a></li>
						<br>
						<li><a target="_blank" href="https://www2.ncdhhs.gov/dss/cse/index.htm">More NC Child Support Enforcement Info. Services</a></li>
						<br>
						<li><a target="_blank"	href="https://www.acf.hhs.gov/css/resource/state-and-tribal-child-support-agency-contacts">Child Support In Other States</a></li>
						<br>
						<li><a href="${pageContext.request.contextPath}/anonymousTipLine.htm">Anonymous Tip Line</a></li>
					</ul>
				</div>
				<div class="box">
					<h4>Help/Information</h4>
					<ul>
						<li><a target="_blank" href="${pageContext.request.contextPath}/contactUs/1.htm">Contact Us</a></li>
						<br>
						<li><a target="_blank" href="${pageContext.request.contextPath}/progFeesAndPolicies/1.htm">Program Fees & Policies</a></li>
						<br>
						<li><a target="_blank" href="${pageContext.request.contextPath}/privacyPolicy/1.htm">Privacy and Security</a></li>
						<br>
						<li><a target="_blank" href="${pageContext.request.contextPath}/loginHelp/1.htm">Login Help</a></li>
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