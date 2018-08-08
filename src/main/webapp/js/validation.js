//--------------------------------------------------------------------------
//This function validates the date for MM/DD/YYYY format and other validations
//--------------------------------------------------------------------------
function isValidDate(dateStr, field) {
 
 // Checks for the following valid date formats:
 // MM/DD/YYYY
 // Also separates date into month, day, and year variables
	 var datePat = /^(\d{2,2})(\/)(\d{2,2})\2(\d{4}|\d{4})$/;
	 var today = new Date();
	 
	 var matchArray = dateStr.match(datePat); // is the format ok?
	 if (matchArray == null) {
		  alert("Please enter "+field+" in MM/DD/YYYY format")
		  return false;
	 }
	 
	 month = matchArray[1]; // parse date into variables
	 day = matchArray[3];
	 year = matchArray[4];
	 if (month < 1 || month > 12) { // check month range
		  alert("Month must be between 1 and 12");
		  return false;
	 }
	 if (day < 1 || day > 31) {
		 alert("Day must be between 1 and 31");
		 return false;
	 }
	 if ((month==4 || month==6 || month==9 || month==11) && day==31) {
		  alert("Month "+month+" doesn't have 31 days!")
		  return false;
	 }
	 if (month == 2) { // check for february 29th
		  var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
		  if (day>29 || (day==29 && !isleap)) {
			   alert("February " + year + " doesn't have " + day + " days!");
			   return false;
		  }
	 }
	 
	 return true;  // date is valid
}

function beforeFutureDate(myDate, field)
{
	var today = new Date();

 	if (new Date(myDate)>today)
 	{
 		alert("Future date is not valid for "+field);
		return false;
 	 }
 	return true;
}

//validate email
function validateEmail(email) {
	var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	return re.test(email);
}

//disables all buttons
function buttonsDisabled(status)
{
	
	var submitElements = document.querySelectorAll("input[type=submit]"); 
	var buttonElements = document.querySelectorAll("input[type=button]");   
	
	for(var i = 0, len = submitElements.length; i < len; i++) {   
			submitElements[i].disabled = status;
	}
	
	for(var i = 0, len = buttonElements.length; i < len; i++) {   
			buttonElements[i].disabled = status;
	}		
}

function isDateFromBeforeDateTo(date1, date2) {
    if(date1 != date2)
    	return new Date(date1) < new Date(date2);
    else
    	return true;
}