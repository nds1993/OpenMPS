<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
	<title>${app.title}</title>
	<meta HTTP-EQUIV="Cache-Control" CONTENT="max-age=0">
	<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
	<meta http-equiv="expires" content="0">
	<meta HTTP-EQUIV="Expires" CONTENT="Tue, 01 Jan 1980 1:00:00 GMT">
	<meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
	<script type="text/javascript">
	function forwardAction(){
		try{
		<c:if test="${!empty scriptBlock}">
			<c:out value="${scriptBlock}" escapeXml="false" />
		</c:if>
		}
		catch(e) {
		}
	}		
	</script>
</head>
<body text="#000000" bgcolor="#ffffff" onload="javascript:forwardAction();">
	<script language='JavaScript' type='text/javascript'>
	try {
		parent.parent.document.getElementById("divProg").style.display = "none";
	}catch(e){}
	</script> 

	<c:if test="${!empty htmlBlock}">
		<c:out value="${htmlBlock}" escapeXml="false" />
	</c:if>
</body>
</html>
