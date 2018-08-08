<%@ include file="../include/taglib.jsp"%>

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
    
    items[0]="<a href='link.htm' ></a> ${news.get(0).getNewsText()}"; //a linked image
    items[1]="<a href='link.htm'></a>${news.get(1).getNewsText()}"; //a linked image
    items[2]="<a href='link.htm'></a>${news.get(2).getNewsText()}"; //a linked image
    /* items[3]="<a href='link.htm'><img alt='image3 (9K)' src='/Images/image3.jpg' height='300' width='300' border='0' /></a>"; //a linked image
    items[4]="<a href='link.htm'><img alt='image4 (9K)' src='/Images/image4.jpg' height='300' width='300' border='0' /></a>"; //a linked image
    items[5]="<a href='link.htm'><img alt='image5 (18K)' src='/Images/image5.jpg' height='300' width='300' border='0' /></a>"; //a linked image */

    function rotater() 
    {
    	document.getElementById("placeholder").innerHTML = items[current];
    	current = (current==items.length-1) ? 0 : current + 1;
    	setTimeout("rotater()",howOften*1000);
	}

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
	
<div id="content">		
			<div class="gutter">
			 
				<h2>Welcome to North Carolina Child Support Services eChild	Support</h2>
				<p>Our mission is to provide family centered child support services through mutual collaboration with families and partners
					using innovative strategies to reach a common goal and self-sufficiency.</p>
				<dl class="feature">				
					<dt> <img src="images/news.jpg" width="150" height="180" alt="" />	</dt>					
					 <dd>						
						<layer id="placeholderlayer"></layer>	
						<div id="placeholderdiv">	</div>						
					</dd>				
				</dl>				
				
				
				<p>North Carolina Child Support Services are available to parents and/or nonparent caretakers of minor children. Services
					provided by North Carolina Child Support Services include: location, establishment of paternity, establishment or modifying of
					child support orders, enforcement of child support orders, collection and processing of child support ordered payments.</p>
			</div>
</div>
		
