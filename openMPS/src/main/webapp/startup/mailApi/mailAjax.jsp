<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">    
<html>
<head>
<script type="text/javascript" src="resources/js/jquery-1.12.0.js"></script>
<title>Java Mail</title>
<style type="text/css">
body {
    font-family: 'Apple SD Gothic Neo',arial,sans-serif;
    font-size: 14px;
}
</style>
<script type="text/javascript">
function sendEMail() {
     
    var form = document.formEMail;
 
    //
    try {
        $.ajax({
            type: 'GET',
            url: 'mail',
            dataType: 'json',
            data: {
                from_email : form.from_email.value,
                to_email : form.to_email.value,
                subtitle : form.subtitle.value,
                content : form.content.value
            },
            success: function(data)
            {
                //alert(data);
                $('.clsResult').html(data.result);
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                alert('There was an error.');
            }
        });
         
    } catch(e) {
        alert(e);
    }
     
    return false;
}
 
</script>
</head>
<body>
     
<form action="mail" name="formEMail">
    <table>
        <tr><td>보내는사람</td><td><input type="text" name="from_email"></td></tr>
        <tr><td>받는사람</td><td><input type="text" name="to_email"></td></tr>
        <tr><td>제목</td><td><input type="text" name="subtitle"></td></tr>
        <tr><td>내용</td><td><input type="text" name="content"></td></tr>
    </table>
    <input type="submit" value="보내기">
</form>
 
<hr>
 
<button onclick="javascript:sendEMail();">AJAX로 보내기</button><br/>
<p>결과</p>
<div class="clsResult">
</div>
 
</body>
</html>
