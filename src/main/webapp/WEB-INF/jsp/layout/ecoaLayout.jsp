<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java"%>
<%@ include file="../include/taglib.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta http-equiv="Content-Language" content="en" />
	<meta name="Description" content="DESCRIPTION" />
	<meta name="Keywords" content="KEYWORDS" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css" />		
	<link href="${pageContext.request.contextPath}/css/button.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/general.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/lightbox.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/calendar.css" rel="stylesheet" type="text/css" />		
	<link href="${pageContext.request.contextPath}/css/autocomplete.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/images/favicon.ico" rel="icon" type="image/ico" />
	<link href="${pageContext.request.contextPath}/css/jquery-ui.css" rel="stylesheet" type="text/css" />
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.pngFix.pack.js"></script>			
	<script type="text/javascript"  src="${pageContext.request.contextPath}/js/datetimepicker.js"></script>
	<script type="text/javascript"  src="${pageContext.request.contextPath}/js/utils.js"></script>
	<script type="text/javascript"  src="${pageContext.request.contextPath}/js/lightBox.js"></script>	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/hover.menu.js"></script>
	<script language="JavaScript" src="${pageContext.request.contextPath}/js/validation.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>	
	
	<title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>
<script>
	var pageContext = "${pageContext.request.contextPath}";
	$(document).ready(function() {
		initLightbox();
	});
</script>
<body>
	<div id="wrap">
	    <tiles:insertAttribute name="topBar" /> 
		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="menu" />
		<tiles:insertAttribute name="navigation" />		
		<tiles:insertAttribute name="body" />
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>