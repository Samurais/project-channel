<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/public/starter.jsp"%>
<div id="thispage">

	<button id="btn">onclick</button>
	<textarea id="test1" rows="2" cols="10">这是文本内容</textarea>
	<!-- ********************************************* -->
	<input id="test2" type="text" value="abc"/> <label for="test2"
		id="test3">建议:</label> <a href="testchatroom.jsp">跳转testchatroom</a> 
		<a href="testwebsocket.jsp">跳转testwebsocket</a>
		<a href="testTulingApi.jsp">跳转图灵测试</a>
</div>
<script>
	$("#header").find("h1:first").append("主页面 <small>main page</small>");
	$("#content").append($("#thispage"));



	var xmlhttp;
	function loadXMLDoc(url, cfunc) {
		if (window.XMLHttpRequest) { // code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else { // code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = cfunc;
		xmlhttp.open("GET", url, true);
		xmlhttp.send();
	}
	;
	function sendrequest() {
		if (window.XMLHttpRequest) { // code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else { // code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.open("Get", "test_test1.action?password=bcd", true);
		xmlhttp.send();

	}
	;
	function myFunction() {
		loadXMLDoc("test_test1.action?password=bcd", function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				document.getElementById("test1").innerHTML = xmlhttp.responseText;
			}
		});
	}
	;
	//*****************************************test1**************************************
	function post() {
		$.post("test_test3.action", {
			key : true
		}, function(result) {
			$("#test1").html(result.key);
		});
	}

	var btn = $("#btn");
</script>