<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isErrorPage="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>이시스템을 사용할 권한이 없습니다.</title>
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Pragma" content="no-cache">
<link href="<%=request.getContextPath()%>/css/common.css" rel="stylesheet" type="text/css">
</head>
<body leftmargin=0 topmargin=0 marginwidth="0" marginheight="0">
<table width="770" height="500" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td valign="middle" style="padding:20px;">
        <table width="450" height="343" border="0" align="center" cellpadding="0" cellspacing="0" background="<c:url value="/html/images/error/auth_bg.png"/>">
          <tr style="height:205px"> 
            <td>&nbsp;</td>
          </tr>
        </table>    
    </td>
  </tr>
</table>
<div id="divProg" style="display:none;">
    <table cellpadding="0" cellspacing="0" border="0">
        <tr>
            <td>
                <table style="width:198px;">
                    <tr style="height:33px;">
                        <td colspan="3" style="background:url(<c:url value="/html/images/loading/loading_blue_01.gif"/>);"></td>
                    </tr>
                    <tr style="height:61px;">
                        <td style="width:67px; background:url(<c:url value="/html/images/loading/loading_blue_02.gif"/>);"></td>
                        <td style="background:url(<c:url value="/html/images/loading/loading.gif"/>);"></td>
                        <td style="width:66px; background:url(<c:url value="/html/images/loading/loading_blue_03.gif"/>);"></td>
                    </tr>
                    <tr style="height:14px;">
                        <td colspan="3" style="background:url(<c:url value="/html/images/loading/loading_blue_04.gif"/>);"></td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</div>
</body>
</html>

