<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String realpath = request.getContextPath();
	String path = request.getContextPath() + "/AdminLTE-2.4.0-rc/";
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">

<link rel="stylesheet"
	href="<%=path%>bower_components/bootstrap/dist/css/bootstrap.min.css">
<link href="<%=realpath%>/resources/buttoncss/button.css"
	rel="stylesheet" />

<script src="<%=path%>bower_components/jquery/dist/jquery.min.js"></script>
<script
	src="<%=path%>bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

</head>

<body>
	<h1>畅联系统免验证体验版</h1>
		<div class="row">
			<a class="button button-action button-pill button-jumbo"
				href="http://17t7665k00.iask.in/channel/send_customer_chat_finalchat.action">点击进入客户端</a>
		</div>
		<div class="row">
			<a class="button button-highlight button-pill button-jumbo"
				href="http://17t7665k00.iask.in/channel/send_services_chat_currentchat.action">点击进入客服工作台</a>
		</div>
		<div class="row">
			<a class="button button-caution button-pill button-jumbo"
				href="http://17t7665k00.iask.in/channel/send_companys_monitor_monitor.action">点击进入企业后台</a>
		</div>
		<div class="row">
			<a class="button button-royal button-pill button-jumbo"
				href="http://v.youku.com/v_show/id_XMzAxNjE2NjY5Ng==.html?spm=a2h3j.8428770.3416059.1">点击查看系统演视频</a>
		</div>
</body>
</html>
