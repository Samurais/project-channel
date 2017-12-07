<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/public/starter.jsp"%>
<div id="thispage" title="测试webSocket <small>main page</small>">
	<button id="connectbtn">连接</button>
	<button id="sendbtn">发送消息</button>
	<button id="closebtn">结束websocket</button>
	<div id="messages">这里显示状态</div>
</div>
<script type="text/javascript"
	src="<%=path%>bower_components/jquery/dist/jquery.serializejson.min.js"></script>
<script type="text/javascript">
	//var webSocket;
	$("#connectbtn").click(function() {
		webSocket = new WebSocket('ws://localhost:8080/channel/websocket/web');
		//var url='marco';
		//webSocket = new SockJS(url);
		//var webSocket = new SockJS('http://localhost:8080/channel/coordination');
		//var webSocket = new WebSocket('ws://localhost:8080/channel/webSocketStatus');
		webSocket.onerror = function(event) {
			alert(event.data);
		};
		webSocket.onclose = function(event) {
			alert("关闭连接");
			document.getElementById('messages').innerHTML = ('</br>' + 'onclose');
		};

		webSocket.onopen = function(event) {
			alert("连接成功");
			document.getElementById('messages').innerHTML = ('</br>' + 'open');
			webSocket.send(JSON.stringify({
				customerservice_id : parseInt(12312),
				customer_id : parseInt(232),
				msgtype_id : parseInt(2300),
				content : content
			}));
		};

		webSocket.onmessage = function(event) {
			//if(event.data=="2"){webSocket.close();return;}
			document.getElementById('messages').innerHTML = ('</br>' + event.data);
		};
	});
	$("#sendbtn").click(start);
	$("#closebtn").click(close);

	var serviceid = 12312;
	var customerid = 232;
	var msgtypeid = 2100;
	content = "你好";
	function start() {
		webSocket.send(JSON.stringify({
			customerservice_id : parseInt(serviceid),
			customer_id : parseInt(customerid),
			msgtype_id : parseInt(msgtypeid),
			content : content
		}));
		return false;

	}

	function close() {
		webSocket.close();
	}

	function robotChat(date) {
		$.post("test_test2.action", {
			date : date
		},
			function(result) {
				document.getElementById('messages').innerHTML = ('</br>' + result.text);
			});
	}
</script>