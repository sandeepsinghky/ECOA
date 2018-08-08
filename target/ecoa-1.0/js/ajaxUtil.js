    function showProgDone(errorMsg) {
   	  hideLightbox();
   	  var myErrorBox=document.getElementById("myErrorBox");
   	  myErrorBox.setAttribute('class',"errorBox");
   	  errMsg=errorMsg+"<br><br><center><input type=button class='bt1' value=Close onclick='hideLightbox()' id='pdBt'></center>";
   	  objOverlay = document.getElementById('overlay');
   	  myErrorBox.innerHTML=errMsg;
   	  showLightbox("myErrorBox",300,100,100,100);
   	}
    function showProgDoneMore(errorMsg,func) {
 	  hideLightbox();
 	  var myErrorBox=document.getElementById("myErrorBox");
 	  myErrorBox.setAttribute('class',"errorBox");
 	  errMsg=errorMsg+"<br><br><center><input type=button class='bt1' value=Close onclick='hideLightbox();"+func+"' id='pdBt'></center>";
 	  objOverlay = document.getElementById('overlay');
 	  myErrorBox.innerHTML=errMsg;
 	  showLightbox("myErrorBox",300,100,100,100);
 	}
    function showProg(errorMsg) {
    	  hideLightbox();
    	  var myErrorBox=document.getElementById("myErrorBox");
    	  myErrorBox.setAttribute('class',"errorBox");
    	  errMsg="Please wait...  <br>" + errorMsg+"<br><center><img src='/images/progress.gif'></center>";
    	  objOverlay = document.getElementById('overlay');
    	  myErrorBox.innerHTML=errMsg;
    	  showLightbox("myErrorBox",300,100,100,100);
    	}

    function startXmlReq(url,paramArray){
    	var xmlHttpReq = false;
        var self = this;
        // Mozilla/Safari
        if (window.XMLHttpRequest) {
            self.xmlHttpReq = new XMLHttpRequest();
        }
        // IE
        else if (window.ActiveXObject) {
            self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
        }
        d = new Date();
        self.xmlHttpReq.open('POST', url+"&d="+d.getMilliseconds(), true);
        self.xmlHttpReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        self.xmlHttpReq.send(getParam(paramArray));
        return self;
    }
    function getParam(paramArray) {
   	  var paramUrl="&";
   		for (x in paramArray)
   		{
   			if(paramArray[x]==null || paramArray[x]==undefined) {
   				alert("ERROR generating param: "+x);
   			} else {
   				sr=paramArray[x].toString().replace("+","%2B");
   				paramUrl+="&"+x+"="+sr;
   			}
   		}
   	  return paramUrl;
   	}
  	function buildButton(obj, func){
  		obj.setAttribute('click',func);
  		$("#"+obj.id).bind('click',func);
  	}
  
    function hideAll(numOfRec,exceptNum)
 	{
    	for(i=1;i<=numOfRec;i++)
     	{
    		if(i != exceptNum) {
    			hideSubInfo("pc"+i,pageContext+"/images/plus.png");
    			 $("#pc"+i + " input").each(function() {
    				   if($(this).attr('type')=="button" && $(this).val()=="Add Item") {
    					   $(this).addClass("hideMe");
    				   }
    		  	    });
    			$("#newItemBt"+$("#pc"+i + " #testId").val()).addClass("hideMe");
    		}
     	}
        
  	}
	
	
