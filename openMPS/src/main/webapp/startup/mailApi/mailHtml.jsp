<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">    
<html>
<head>
<script type="text/javascript" src="../resources/js/jquery-1.12.0.js"></script>
<title>Java Mail</title>
<style type="text/css">
body {
    font-family: 'Apple SD Gothic Neo',arial,sans-serif;
    font-size: 14px;
}
</style>
</head>
<body>
     
<form action="mail">
    <table>
        <tr><td>보내는사람</td><td><input type="text" name="from_email"></td></tr>
        <tr><td>받는사람</td><td><input type="text" name="to_email"></td></tr>
        <tr><td>제목</td><td><input type="text" name="subtitle"></td></tr>
        <tr><td>내용</td><td><input type="text" name="content"></td></tr>
    </table>
    <input type="submit" value="보내기">
</form>
 
</body>
</html>
