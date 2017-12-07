<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script LANGUAGE="JavaScript">
<!--  
function openwin() {  
window.open ("send_customer_chat_testchat.action", "newwindow", "height=380, width=370, toolbar =no, menubar=no, scrollbars=no, resizable=no, location=no, status=no,top=350,left=700") //写成一行  
}  
//-->
</script>
</head>
<body onload="openwin()">任意的页面内容...
</body>
</html>
