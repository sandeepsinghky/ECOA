<%@ include file="../../../include/taglib.jsp"%>
<script>

	function noEnterKey(evt) {
      var evt = (evt) ? evt : ((event) ? event : null);
      if (evt.keyCode == 13)  {
          return false;
      }
    }
	document.onkeypress = noEnterKey;
	
	function processApp(formButton)
	{
		var selected = false;
		var selectedApp;
		
		for(i=0; i<document.getElementsByName("selectappl").length; i++ ){
			if(document.getElementsByName("selectappl")[i].checked){
				selected = true;
				selectedApp = document.getElementsByName("selectappl")[i].value;
				break;
			}
		}
		if (!selected){
			alert("Please select the application to process");
			return false;
		}
		if(formButton.name=="selectApp"){
			document.OnlineAppList.action.value="selectApplication";
		}
		else if(formButton.name=="deleteApp"){
			if (document.getElementById(selectedApp+"_status").value == "3"){
				alert("Cannot delete submitted application");
				return false;
			}
			if (confirm("Confirm deletion of application?")){        
		        document.OnlineAppList.action.value="deleteApplication";
		    }else{
		  		return false;
		  	}
	  	}
	  	document.OnlineAppList.submit();
	  	buttonsDisabled(true);		
		return true;
	}
	
	function createApp()
	{
		var selected = false;
		var selectedApp;
		//commented out this for testing purpose
		/*for(i=0; i<document.getElementsByName("selectappl").length; i++ ){
			if(document.getElementById(document.getElementsByName("selectappl")[i].value+"_status").value == "1"){
				alert("There is an active application in progress. Cannot create new application.");
				return false;
			}
		}*/
		//check here for application submit date
		//commented out this for testing purpose
		/*for(i=0; i<document.getElementsByName("selectappl").length; i++ ){
			if(document.getElementById(document.getElementsByName("selectappl")[i].value+"_status").value == "3"){
				if (dateDiff(new Date(), new Date(document.getElementById(document.getElementsByName("selectappl")[i].value+"_submitDt").value)) < "60"){
					alert("If you have submitted an online application in the last 60 days, please contact the local child support office regarding your application. Your last application submit date: "+document.getElementById(document.getElementsByName("selectappl")[i].value+"_submitDt").value);
					return false;
				}
			}
		}*/
		
		location.href='${pageContext.request.contextPath}/cssp/user/applicantRNR.htm';
		buttonsDisabled(true);
		return true;
	}
	
	function dateDiff(date1, date2)
	{
		var timeDiff = Math.abs(date2.getTime() - date1.getTime());
		var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24)); 
		return diffDays;
	}
</script>

 

	<!-- <form name="OnlineAppList" id="OnlineAppList" method="post" action="${pageContext.request.contextPath}/cssp/user/caseApplicationList.htm"> -->
	
	<form name="OnlineAppList" id="OnlineAppList" method="post" "JavaScript: if((window.location.href).includes("caseApplicationList/1.htm")){action="${pageContext.request.contextPath}/cssp/user/caseApplicationList/1.htm";} else {action ="${pageContext.request.contextPath}/cssp/user/caseApplicationList/2.htm";}>
		<input type="hidden" name="action" id="action" value="">
		
		<div id=saveAndExitDiv style="display: none" class=center>
			<h1>Are you sure you want to exit?</h1>
			<br /> <input type=button value='Cancel' onclick="hideLightbox()" />&nbsp; <input type=button value='Yes, Save & Exit!' onclick="hideLightbox()" />
		</div>
		<div id="content">
			<div class="gutter">
				<h2>Application List</h2>
				
				<hr />					
					<c:if test="${!empty message}">
						<div class="error message-box">${message}</div>
					</c:if>

					<c:if test="${!empty success}">
						<div class="success message-box">${success}</div>
					</c:if>					
				
				  <div>
					<p>
						<input type="button" id="newApp" value="Create New Application" onclick="return createApp()">
					</p>
				</div>
				<div>
					<table>
						<tr>
							<td>
								<table border="1">
									<tr>
										<th bgcolor="#C0C0C0" colspan="6">Applications Created For This Client</th>
									</tr>
									<tr>
										<th>&nbsp;</th>
										<th>&nbsp;</th>
										<th>Reference Number</th>
										<th>Create Date</th>
										<th title="Active: An application is in the process of being created. Finish and submit within 10 days from the start of the application.
										Submit: Application has been submitted and is available in read only mode.
										Completed: Application has been processed and is available in read only mode. Contact your local office for additional information.">Application Status</th>
									</tr>
									<c:forEach var="appl" items="${appList}" varStatus="tc">
										<tr>
											<td>${tc.count}.</td>
											<td>
												<input type="radio" name="selectappl" id="selectappl" value="${appl.getApplicationId()}">
												<input type="hidden" name="${appl.getApplicationId()}_status" id="${appl.getApplicationId()}_status" value="${appl.getApplStatus()}">
												<input type="hidden" name="${appl.getApplicationId()}_submitDt" id="${appl.getApplicationId()}_submitDt" value="${appl.getApplSubmitDtPgStr()}">
											</td>
											<td>${appl.getApplicationId()}</td>
											<td>${appl.getApplCreateDtPgStr()}</td>
											<td>${appl.getApplStatusDesc()}</td>
										</tr>
									</c:forEach>
								</table>
							</td>
							<c:if test="${not empty appList}">
								<td>&nbsp;&nbsp;</td>
								<td>
									<table>
										<tr>
											<td><input type="button" name="selectApp" value="Select Application" onclick="return processApp(this)"></td>
											<c:if test="${sessionScope.hasActiveOnlineAppl}">
												<td><input type="button" name="deleteApp" value="Delete Application" onclick="return processApp(this)"></td>
											</c:if>									
										</tr>
									</table>
								</td>
							</c:if>
						</tr>
					</table>
				</div>
				<br>
			</div>
		</div>
	</form>

