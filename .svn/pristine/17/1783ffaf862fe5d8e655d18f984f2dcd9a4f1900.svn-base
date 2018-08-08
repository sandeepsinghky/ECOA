function showLightbox(popId, theWidth, theHeight, leftPos, topPos) {
	// prep objects
	var objOverlay = document.getElementById('overlay');
	var objLightbox = document.getElementById('lightbox');
	var objLightboxDetails = document.getElementById('lightboxDetails');

	var arrayPageSize = getPageSize();
	var arrayPageScroll = getPageScroll();

	// center loadingImage if it exists
	// set height of Overlay to take up whole page and show
	objOverlay.style.height = (arrayPageSize[1] + 'px');
	objOverlay.style.display = 'block';

	var lightboxTop = arrayPageScroll[1]
			+ ((arrayPageSize[3] - 35 - theHeight) / 2);// tAM
	var lightboxLeft = ((arrayPageSize[0] - 20 - theWidth) / 2);// tAM

	objLightbox.style.top = (lightboxTop < 0) ? "0px" : lightboxTop + "px";
	objLightbox.style.left = (lightboxLeft < 0) ? "0px" : lightboxLeft + "px";

	objLightboxDetails.style.width = theWidth + 'px';// TAM
	objLightboxDetails.style.height = theHeight + 'px';// TAM

	// A small pause between the image loading and displaying is required with
	// IE,
	// this prevents the previous image displaying for a short burst causing
	// flicker.
	if (navigator.appVersion.indexOf("MSIE") != -1) {
		pause(250);
	}

	// Hide select boxes as they will 'peek' through the image in IE
	selects = document.getElementsByTagName("select");
	for (i = 0; i != selects.length; i++) {
		selects[i].style.visibility = "hidden";
	}
	objLightbox.style.display = 'block';
	arrayPageSize = getPageSize();
	objOverlay.style.height = (arrayPageSize[1] + 'px');
	var theDiv = document.getElementById(popId);
	theDiv.style.display = "block";
	objLightboxDetails.appendChild(theDiv);

}
// pause(numberMillis)
// Pauses code execution for specified time. Uses busy code, not good.
// Code from http://www.faqts.com/knowledge_base/view.phtml/aid/1602
//
function pause(numberMillis) {
	var now = new Date();
	var exitTime = now.getTime() + numberMillis;
	while (true) {
		now = new Date();
		if (now.getTime() > exitTime)
			return;
	}
}
//
// hideLightbox()
//
function hideLightbox() {
	// get objects
	objOverlay = document.getElementById('overlay');
	objLightbox = document.getElementById('lightbox');
	var objLightboxDetails = document.getElementById('lightboxDetails');
	for ( var index = 0; index < objLightboxDetails.childNodes.length; index++) {
		objLightboxDetails.childNodes[index].style.display = 'none';
	}

	// hide lightbox and overlay
	objOverlay.style.display = 'none';
	objLightbox.style.display = 'none';

	// make select boxes visible
	selects = document.getElementsByTagName("select");
	for (i = 0; i != selects.length; i++) {
		selects[i].style.visibility = "visible";
	}

	// disable keypress listener
	document.onkeypress = '';
}
function getPageScroll() {

	var yScroll;

	if (self.pageYOffset) {
		yScroll = self.pageYOffset;
	} else if (document.documentElement && document.documentElement.scrollTop) { // Explorer
																					// 6
																					// Strict
		yScroll = document.documentElement.scrollTop;
	} else if (document.body) {// all other Explorers
		yScroll = document.body.scrollTop;
	}

	arrayPageScroll = new Array('', yScroll);
	return arrayPageScroll;
}
//
// getPageSize()
// Returns array with page width, height and window width, height
// Core code from - quirksmode.org
// Edit for Firefox by pHaez
//
function getPageSize() {

	var xScroll, yScroll;

	if (window.innerHeight && window.scrollMaxY) {
		xScroll = document.body.scrollWidth;
		yScroll = window.innerHeight + window.scrollMaxY;
	} else if (document.body.scrollHeight > document.body.offsetHeight) { // all
																			// but
																			// Explorer
																			// Mac
		xScroll = document.body.scrollWidth;
		yScroll = document.body.scrollHeight;
	} else { // Explorer Mac...would also work in Explorer 6 Strict, Mozilla
				// and Safari
		xScroll = document.body.offsetWidth;
		yScroll = document.body.offsetHeight;
	}

	var windowWidth, windowHeight;
	if (self.innerHeight) { // all except Explorer
		windowWidth = self.innerWidth;
		windowHeight = self.innerHeight;
	} else if (document.documentElement
			&& document.documentElement.clientHeight) { // Explorer 6 Strict
														// Mode
		windowWidth = document.documentElement.clientWidth;
		windowHeight = document.documentElement.clientHeight;
	} else if (document.body) { // other Explorers
		windowWidth = document.body.clientWidth;
		windowHeight = document.body.clientHeight;
	}

	// for small pages with total height less then height of the viewport
	if (yScroll < windowHeight) {
		pageHeight = windowHeight;
	} else {
		pageHeight = yScroll;
	}

	// for small pages with total width less then width of the viewport
	if (xScroll < windowWidth) {
		pageWidth = windowWidth;
	} else {
		pageWidth = xScroll;
	}

	arrayPageSize = new Array(pageWidth, pageHeight, windowWidth, windowHeight);
	return arrayPageSize;
}
// initLightbox()
// Function runs on window load, going through link tags looking for
// rel="lightbox".
// These links receive onclick events that enable the lightbox display for their
// targets.
// The function also inserts html markup at the top of the page which will be
// used as a
// container for the overlay pattern and the inline image.
//
function initLightbox() {
	if (!document.getElementsByTagName) {
		return;
	}
	var anchors = document.getElementsByTagName("a");

	for ( var i = 0; i < anchors.length; i++) {
		var anchor = anchors[i];

		if (anchor.getAttribute("href")
				&& (anchor.getAttribute("rel") == "lightbox")) {
			anchor.onclick = function() {
				showLightbox(this);
				return false;
			};
		}
	}

	var objBody = document.getElementsByTagName("body").item(0);

	// create overlay div and hardcode some functional styles (aesthetic styles
	// are in CSS file)
	var objOverlay = document.createElement("div");
	objOverlay.setAttribute('id', 'overlay');
	objOverlay.style.display = 'none';
	objOverlay.style.position = 'absolute';
	objOverlay.style.top = '0';
	objOverlay.style.left = '0';
	objOverlay.style.zIndex = '90';
	objOverlay.style.width = '100%';
	objBody.insertBefore(objOverlay, objBody.firstChild);

	var arrayPageSize = getPageSize();
	var arrayPageScroll = getPageScroll();

	// create lightbox div, same note about styles as above
	var objLightbox = document.createElement("div");
	objLightbox.setAttribute('id', 'lightbox');
	objLightbox.style.display = 'none';
	objLightbox.style.position = 'absolute';
	objLightbox.style.zIndex = '100';
	objBody.insertBefore(objLightbox, objOverlay.nextSibling);

	// create details div, a container for the caption and keyboard message
	var objLightboxDetails = document.createElement("div");
	objLightboxDetails.setAttribute('id', 'lightboxDetails');
	objLightbox.appendChild(objLightboxDetails);

}
// addLoadEvent()
// Adds event to window.onload without overwriting currently assigned onload
// functions.
// Function found at Simon Willison's weblog - http://simon.incutio.com/
//
function addLoadEvent(func) {
	var oldonload = window.onload;
	if (typeof window.onload != 'function') {
		window.onload = func;
	} else {
		window.onload = function() {
			oldonload();
			func();
		};
	}

}
function showDd(id) {
	if (document.getElementById(id) != null)
		document.getElementById(id).style.visibility = "visible";
}
function hideDd(id) {
	if (document.getElementById(id) != null)
		document.getElementById(id).style.visibility = "hidden";
}
//
//showLightbox()
//Preloads images. Pleaces new image in lightbox then centers and displays.
//
function showErrMsg(errorArray,forceShow) {
if(forceShow) {
 hideLightbox();
}
var myErrorBox=document.getElementById("myErrorBox");
myErrorBox.setAttribute('class',"errorBox");
errMsg="";
objOverlay = document.getElementById('overlay');
if(objOverlay.style.display == 'none') {
	  var theWidth=400;
	  for(i=0;i<errorArray.length;i++) {
		if(i>0) {
		  errMsg += "<br>";
		}
		errMsg += errorArray[i];
	  }
	  errMsg +="<br><br><center><input type=button id='cemsgbt' class=bt1 value=Close onclick='hideLightbox()'></center>";
	  myErrorBox.innerHTML=errMsg;
	  showLightbox("myErrorBox",theWidth,200,100,100);
	  document.getElementById("cemsgbt").focus();
} else {
 for(i=0;i<errorArray.length;i++) {
		if(i>0) {
		  errMsg += "\n";
		}
		errMsg += errorArray[i];
	}
	if(errMsg != "") {
	  alert(errMsg);
	}
}
}