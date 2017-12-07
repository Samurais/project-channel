<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/services/public/starter.jsp"%>
<div id="thishead">
	<!-- link -->
	<script src="<%=realpath%>/resources/bootbox/toastr.js"></script>
	<link href="<%=realpath%>/resources/bootbox/toastr.css"
		rel="stylesheet" type="text/css" />
	<!-- toastr -->
	<script
		src="<%=realpath%>/resources/bootstrap-switch/bootstrap-switch.min.js"></script>
	<link
		href="<%=realpath%>/resources/bootstrap-switch/bootstrap-switch.min.css"
		rel="stylesheet" type="text/css" />
	<!-- bootstrap-switch -->
</div>
<div id="thispage" title="当前会话<small>currentchat</small>">
	<!-- ================================总体布局================================== -->
	<div class="row clearfix">
		<div class="col-md-12 column">
			<div class="row clearfix">
				<!-- ================================左边布局================================== -->
				<div class="col-md-3 column">
					<!-- Widget: user widget style 1 -->
					<div class="box box-widget widget-user-2">
						<!-- Add the bg color to the header using any of the bg-* classes -->
						<div class="widget-user-header bg-aqua-active">
							<!-- /.widget-user-image -->
							<input name="status" type="checkbox" data-size="small">
							<!-- 开关按钮 -->
							<h3 class="widget-user-username">当前会话列表</h3>
							<h5 class="widget-user-desc">Current chatlist</h5>
						</div>
						<div class="box-footer no-padding">
							<ul id="customer_list" class="nav nav-stacked">
								<!-- 这里展示客户的列表 -->
							</ul>
						</div>
						<!-- /.widget-user -->
					</div>
				</div>
				<!-- ================================中间布局================================== -->
				<div id="certen" class="col-md-5 column">
					<!-- 这里展示会话的chatroom -->
				</div>
				<!-- ================================右边布局================================== -->
				<div class="col-md-4 column">
					<dl>
						<dt>客户账号</dt>
						<dd>A description list is perfect for defining terms.</dd>
						<dt>客户IP来源</dt>
						<dd>Vestibulum id ligula porta felis euismod semper eget
							lacinia odio sem nec elit.</dd>
						<dd>Donec id elit non mi porta gravida at eget metus.</dd>
						<dt>客户基本信息</dt>
						<dd>Etiam porta sem malesuada magna mollis euismod.</dd>
						<dt>客服上次咨询</dt>
						<dd>Fusce dapibus, tellus ac cursus commodo, tortor mauris
							condimentum nibh, ut fermentum massa justo sit amet risus.</dd>
					</dl>
				</div>
			</div>
		</div>
	</div>
	<!-- =============================================================================== -->
</div>
<script>
	$(function() {
		//-- ==============================公共信息保存部分==================================== -->
		$("#chat").hide();
		//初始化webSocket对象
		$btninit();
		//绑定消息回车事件
		$(document).keypress(function(event) {
			if (event.keyCode == 13) {
				if (typeof (currentcustomer) == "undefined") {
					return;
				} else {
					$("#sub_" + currentcustomer).click();
				}
			}
		});
		//模拟客户进入
		$customerin("老张", "232");
		$customerin("老李", "a77");
		$customerin("老吴", "2332");
		$customerin("老陈", "23232");
	});
	var webSocket = "";
	var currentcustomer;
	var serviceid = 12312;
	//-- ===================================总体方法======================================= -->
	//客户标签页的事件监听
	function $listen(id) {
		$("#" + id).on('click', function() {
			//1.将点击按钮的客户id保存到当前客服id中
			currentcustomer = $(this).attr("id");
			//2.获得客户基本信息
			var name = "老王";
			//3.如果房间存在则显示,不存在则创建新房间
			$("#certen").find(".direct-chat-primary").hide();
			//如果存在则显示
			if ($("#text_" + currentcustomer).length > 0) {
				$("#text_" + currentcustomer).show();
				var div = $("#text_" + currentcustomer).find(".direct-chat-messages:first");
				div.scrollTop(div.scrollTop() + 1000);
				$("#tip_" + currentcustomer).text(0);
			} else {
				//如果不存在，则创建房间，并且绑定按钮事件
				var chat = $newchat(name, currentcustomer);
				$("#certen").append(chat);
				$("#sub_" + currentcustomer).on('click', function() {
					//if (typeof (webSocket) == "undefined") {
					if (webSocket == "") {
						toastr.error("您目前为挂起状态");
					} else {
						var content = $("#inp_" + currentcustomer).val();
						if (content == null) {
							return;
						}
						;
						$webSocketSend(serviceid, currentcustomer, 1200, content);
						$("#text_" + currentcustomer).find(".direct-chat-messages:first").append($msg(true, "我", Date(), "<%=path%>dist/img/user3-128x128.jpg", content));
						$("#inp_" + currentcustomer).val("");
						var div = $("#text_" + currentcustomer).find(".direct-chat-messages:first");
						div.scrollTop(div.scrollTop() + 1000);
					}
				});
				$("#btn_" + currentcustomer).on('click', function() {
					//===============================================
					//****************结束会话按钮**********************
					$("#text_" + currentcustomer).remove();
					$("#" + currentcustomer).remove();
					toastr.warning('客户' + name + ':' + id + '离开房间');
					$.post('conversation_conversationEnd.action',);
				});
			}
		});
	}
	//-- ==============================消息接受发送功能==================================== -->
	function $msg(issend, name, time, imgpath, msg) {
		if (issend) {
			var msg = [
				'<div class="direct-chat-msg right">',
				'<div class="direct-chat-info clearfix">',
				'<span class="direct-chat-name pull-right">' + name + '</span>',
				'<span class="direct-chat-timestamp pull-left">' + time + '</span></div>',
				'<img class="direct-chat-img" src= ' + imgpath + ' alt="Message User Image">',
				'<div class="direct-chat-text">' + msg + '</div>'
			];
		} else {
			var msg = [
				'<div class="direct-chat-msg">',
				'<div class="direct-chat-info clearfix">',
				'<span class="direct-chat-name pull-left">' + name + '</span>',
				'<span class="direct-chat-timestamp pull-right">' + time + '</span></div>',
				'<img class="direct-chat-img" src= ' + imgpath + ' alt="Message User Image">',
				'<div class="direct-chat-text">' + msg + '</div>'
			];
		}
		return msg.join("");
	}

	//-- ===================================客户进入======================================= -->
	function $customerin(name, id) {
		var row = '<li><a id=' + id + ' href="#">' + name + ":" + id +
			'<span id=tip_' + id + ' class="pull-right badge bg-green">0</span></a></li>';
		//1.将消息加入左边消息队列
		$("#customer_list").append(row);
		//2.将点击事件绑定消息队列
		$listen(id);
		//3.提示客户进入房间
		toastr.success('客户' + name + ':' + id + '进入房间');
		//4.创建房间
		var chat = $newchat(name, id);
		$("#certen").append(chat);
		$("#sub_" + id).on('click', function() {
			//if (typeof (webSocket) == "undefined") {
			if (webSocket == "") {
				toastr.error("您目前为挂起状态");
			} else {
				var content = $("#inp_" + id).val();
				if (content == null) {
					return;
				}
				;
				$webSocketSend(serviceid, id, 1200, content);
				$("#text_" + id).find(".direct-chat-messages:first").append($msg(true, "我", Date(), "<%=path%>dist/img/user3-128x128.jpg", content));
				$("#inp_" + id).val("");
				var div = $("#text_" + id).find(".direct-chat-messages:first");
				div.scrollTop(div.scrollTop() + 1000);
			}
		});
		$("#btn_" + id).on('click', function() {
			//会话结束部分
			$("#text_" + id).remove();
			$("#" + id).remove();
			toastr.warning('客户' + name + ':' + id + '离开房间');
			$.post('conversation_conversationEnd.action', {
				customerservice_id : parseInt(serviceid),
				customer_id : parseInt(id),
				msgtype_id : parseInt(1307),
			}, function(result) {console.log("结束成功")});
		}
		);
	}

	//-- ===================================生成新的唯一标识的聊天窗口======================================= -->
	//-- 生成一个带客户id的窗口
	function $newchat(name, id) {
		var chat = '<div id = text_' + id + ' class="box box-primary direct-chat direct-chat-primary" style="display:none;">' +
			'<div class="box-header with-border">' +
			'<h3 class="box-title">' + name + ":" + id + '</h3>' +
			'<div class="box-tools pull-right">' +
			'<button id=btn_' + id + ' type="button" class="btn  btn-block btn-danger btn-sm">结束会话</button>' +
			'</div>' +
			'</div>' +
			//-- /.box-header -->
			'<div class="box-body">' +
			//-- 聊天内容显示 -->
			'<div class="direct-chat-messages">' +
			'</div>' +
			//-- 弹窗界面 -->
			'<div class="direct-chat-contacts">' +
			'</div>' +
			//-- /.direct-chat-pane -->
			'</div>' +
			//-- /.box-body -->
			'<div class="box-footer">' +
			'<div class="input-group">' +
			'<input id=inp_' + id + ' name="message" placeholder="正在输入 ..." class="form-control" type="text">' +
			'<span class="input-group-btn">' +
			'<button id=sub_' + id + ' type="submit" class="btn btn-primary btn-flat">发送</button>' +
			'</span>' +
			'</div>' +
			'</div>' +
			//-- /.box-footer-->
			'</div>'
		//$("#certen").append(chat);
		return chat;
	}
	//-- ==================================================================================== -->
	function $webSocketSend(serviceid, customerid, msgtypeid, content) {
		console.log("$webSocketSend");
		if (content == null & msgtypeid == 1300) {
			webSocket.send(JSON.stringify({
				customerservice_id : parseInt(serviceid),
				msgtype_id : parseInt(msgtypeid),
			}));
			return;
		}
		;
		if (content == null & msgtypeid != 1300) {
			return;
		}
		;
		if (serviceid == null) {
			console.log("客服id不能为空")
		}
		;
		if (customerid == null) {
			console.log("==");
			webSocket.send(JSON.stringify({
				customerservice_id : parseInt(serviceid),
				msgtype_id : parseInt(msgtypeid),
				content : content
			}));
		}
		;
		if ((serviceid != null) && (customerid != null)) {
			console.log("&&");
			console.log(content);
			webSocket.send(JSON.stringify({
				customerservice_id : parseInt(serviceid),
				customer_id : parseInt(customerid),
				msgtype_id : parseInt(msgtypeid),
				content : content
			}));
			return;
		}
		;
	}
	//-- ==============================按钮开关初始化========================================== -->
	function $btninit() {
		$('[name="status"]').bootstrapSwitch({
			onText : "会话",
			offText : "挂起",
			onColor : "success",
			offColor : "info",
			size : "small",
			state : false,
			onSwitchChange : function(event, state) {
				//检查websocket对象
				if (window.WebSocket) {
					console.log('This browser supports WebSocket');
				} else {
					console.log('This browser does not supports WebSocket');
				}
				//websocket连接开关
				if (state == true) {
					//建立websocket连接
					webSocket = $connect();
				} else {
					console.log("请求关闭")
					webSocket.send(JSON.stringify({
						msgtype_id : 1302, //断开连接请求
						customerservice_id : serviceid, //发送的客服ID
					}));
				}
			}
		})
	}
	//-- ==============================websocket部分========================================== -->
	function $connect() {
		console.log("建立连接")
		var webSocketm = new WebSocket('ws://localhost:8080/channel/websocket/web');
		webSocketm.onerror = function(event) {
			console.log('连接错误');
			toastr.error('连接异常');
		};
		webSocketm.onclose = function(event) {
			console.log('连接关闭');
			webSocket = "";
		};

		webSocketm.onopen = function(event) {
			console.log('连接打开');
			$webSocketSend(serviceid, null, 1300, null);
		};

		webSocketm.onmessage = function(event) {
			console.log("收到消息");
			var obj = jQuery.parseJSON(event.data);
			var msgtype = obj.msgtype_id;
			switch (msgtype) {
			//服务器发客服
			case 3100:
				break;
			//服务器响应关闭请求
			case 3102:
				console.log("1");
				webSocketm.close();
				break;
			//服务器通知客服进入会话
			case 3104:
				$customerin("", obj.customer_id);
				break;
			//客户发客服
			case 2100:
				if ((typeof (currentcustomer) == "undefined") || (obj.customer_id != currentcustomer)) {
					console.log(obj.customer_id);
					var dom = $("#tip_" + obj.customer_id);
					var tip = dom.text();
					dom.text(parseInt(tip) + 1);
				}
				console.log("3");
				var div = $("#text_" + obj.customer_id).find(".direct-chat-messages:first");
				div.append($msg(false, obj.customer_id, obj.sendtime, "<%=path%>dist/img/user3-128x128.jpg", obj.content));
				div.scrollTop(div.scrollTop() + 1000);
				break;
			default:
				break;
			}
		};
		return webSocketm;
	}
	//-- ================================websocket发送按钮事件监听=============================== -->

	//-- ================================websocket事件方法===================================== -->
	//});
</script>