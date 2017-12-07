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
	<!-- bootstrap datepicker -->
	<script
		src="<%=path%>bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
	<link rel="stylesheet"
		href="<%=path%>bower_components/bootstrap-daterangepicker/daterangepicker.css">
	<!-- daterange picker -->
	<script
		src="<%=realpath%>/resources/bootstrap-table/bootstrap-table.js"></script>
	<link
		href="<%=realpath%>/resources/bootstrap-table/bootstrap-table.css"
		rel="stylesheet" />
	<script
		src="<%=realpath%>/resources/bootstrap-table/bootstrap-table-zh-CN.js"></script>
	<!-- /bootstrap table -->
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
				<div class="col-md-2 column">
					<dl id="customerInfo">
						<dt name="id">客户Id</dt>
						<dd>--</dd>
						<dt name="username">客户用户名</dt>
						<dd>--</dd>
						<dt name="name">客户姓名</dt>
						<dd>--</dd>
						<dt name="sex">客户性别</dt>
						<dd>--</dd>
						<dt name="level">客户优先级</dt>
						<dd>--</dd>
						<dt name="registtime">客户注册时间</dt>
						<dd>--</dd>
						<dt name="ipaddr">客户IP地址</dt>
						<dd>--</dd>
						<dt name="area">客户所在地</dt>
						<dd>--</dd>
						<dt name="access">客户来源渠道</dt>
						<dd>--</dd>
					</dl>
				</div>
				<div class="col-md-2 column">
					<dl>
						<dt name="systeminfo">客户系统信息</dt>
						<dd>--</dd>
						<dt name="hostname">客户主机号</dt>
						<dd>--</dd>
						<dt name="browserinfo">客户浏览器</dt>
						<dd>--</dd>
						<dt name="referer">搜索引擎来源</dt>
						<dd>--</dd>
						<dt name="referkeyword">搜索引擎关键字</dt>
						<dd>--</dd>
						<dt name="lastconversationtime">客户上次会话时间</dt>
						<dd>--</dd>
						<dt name="lastconversationkeyword">客户上次会话关键字</dt>
						<dd>--</dd>
						<dt name="lastconversationkeyword">客户上次会话关键字</dt>
						<dd>--</dd>
					</dl>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-4 col-md-offset-8">
			<a class="btn btn-app" id="btn_createorder"> <i
				class="fa fa-edit"></i> 生成订单
			</a> <a class="btn btn-app" id="btn_getrecommend"> <i
				class="fa fa-heart-o"></i> 推荐产品
			</a> <a class="btn btn-app" id="btn_conversationswitch"> <i
				class="fa fa-users"></i> 会话转接
			</a>
		</div>
	</div>
	<div class="row clearfix">
		<div id="toolbar" class="btn-group">
			<div class="col-md-10 column">
				<input id="inp_search" class="form-control" placeholder="搜索"
					type="text">
			</div>
			<div class="col-md-2 column">
				<button id="btn_search" type="button" class="btn btn-default">
					<span class="glyphicon glyphicon-search" aria-hidden="true"></span>搜索
				</button>
			</div>
		</div>
		<div class="col-md-8 column">
			<table id="tb_departments"></table>
		</div>
		<div class="col-md-4 column">
			<table id="tb_quickmsg"></table>
		</div>
	</div>
	<!-- =============================================================================== -->
	<!-- ==================================会话转接部分==================================== -->
	<div class="modal fade" id="myModal3" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">转接申请</h4>
				</div>
				<div class="modal-body">
					<form id="defaultForm" method="post" class="form-horizontal"
						action="order_save.action">
						<div class="form-group">
							<label class="col-lg-3 control-label">转接客户</label>
							<div class="col-lg-5">
								<input type="text" class="form-control" name="id"
									readonly="readonly" value="" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-3 control-label">转接角色</label>
							<div class="col-lg-5">
								<select>
									<option value="12312">客服班长</option>
									<option value="99754">客服产品经理</option>
									<option value="97065">客服售后经理</option>
									<option value="96054">客服维护经理</option>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-3 control-label">转接在线客服</label>
							<div class="col-lg-5">
								<select>
									<option value="12312">客服12312</option>
									<option value="99754">客服99754</option>
									<option value="97065">客服97065</option>
									<option value="96054">客服96054</option>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-3 control-label">转接原因</label>
							<div class="col-lg-5">
								<textarea class="form-control" rows="3" placeholder=""
									name="xremark"></textarea>
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-3 control-label">会话记录</label>
							<div class="col-lg-5">
								<textarea class="form-control" rows="8" placeholder=""
									name="xremark" readonly="readonly"></textarea>
							</div>
						</div>

						<div class="form-group">
							<div class="col-lg-4 col-lg-offset-1">
								<button id="recommendbtn_recommend" type="button"
									class="btn btn-block btn-primary">确认</button>
							</div>
							<div class="col-lg-4 col-lg-offset-2">
								<button id="recommendbtn_cancel" type="button"
									class="btn btn-block btn-primary">取消</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- =============================================================================== -->
	<!-- ==================================推荐商品查看表单部分================================ -->
	<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">确认</h4>
				</div>
				<div class="modal-body">
					<form id="defaultForm" method="post" class="form-horizontal"
						action="order_save.action">
						<div class="form-group">
							<label class="col-lg-3 control-label">商品ID</label>
							<div class="col-lg-5">
								<input type="text" class="form-control" name="id"
									readonly="readonly" value="" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-3 control-label">商品类别ID</label>
							<div class="col-lg-5">
								<input type="text" class="form-control" name="category_id"
									readonly="readonly" value="" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-3 control-label">商品名称</label>
							<div class="col-lg-5">
								<input type="text" class="form-control" name="name"
									readonly="readonly" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-3 control-label">商品价格</label>
							<div class="col-lg-5">
								<input type="text" class="form-control" name="price"
									readonly="readonly" />
							</div>
						</div>


						<div class="form-group">
							<label class="col-lg-3 control-label">商品描述</label>
							<div class="col-lg-5">
								<input type="text" class="form-control" name="remark"
									readonly="readonly" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-3 control-label">商品库存</label>
							<div class="col-lg-5">
								<input type="text" class="form-control" name="productstatus"
									readonly="readonly" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">商品详细描述</label>
							<div class="col-lg-5">
								<textarea class="form-control" rows="3" placeholder=""
									name="xremark" readonly="readonly"></textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">商品图片</label>
							<div class="col-lg-5">
								<img alt="" src="" name="pic" height="200" width="200">
							</div>
						</div>

						<div class="form-group">
							<div class="col-lg-4 col-lg-offset-1">
								<button id="recommendbtn_recommend" type="button"
									class="btn btn-block btn-primary">推荐</button>
							</div>
							<div class="col-lg-4 col-lg-offset-2">
								<button id="recommendbtn_cancel" type="button"
									class="btn btn-block btn-primary">取消</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- ============================================提交订单部分======================================= -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">新增</h4>
				</div>
				<div class="modal-body">
					<form id="defaultForm" method="post" class="form-horizontal"
						action="order_save.action">
						<div class="form-group">
							<label class="col-lg-3 control-label">客户账号</label>
							<div class="col-lg-5">
								<input type="text" class="form-control" name="customerId"
									readonly="readonly" value="" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-3 control-label">受理客服账号</label>
							<div class="col-lg-5">
								<input type="text" class="form-control" name="customerserviceId"
									readonly="readonly" value="" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-3 control-label">产品ID</label>
							<div class="col-lg-5">
								<input type="text" class="form-control" name="product_id" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-3 control-label">送货地址</label>
							<div class="col-lg-5">
								<input type="text" class="form-control" name="demandaddr" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-3 control-label">截至时间</label>

							<div class="input-group date col-lg-5">
								<div class="input-group-addon">
									<i class="fa fa-calendar"></i>
								</div>
								<input class="form-control pull-right" id="datepicker"
									name="demandtime" type="text">
							</div>
							<!-- /.input group -->
						</div>

						<div class="form-group">
							<label class="col-lg-3 control-label">联系电话</label>
							<div class="col-lg-5">
								<input type="text" class="form-control" name="demandphone" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-3 control-label">客户备注</label>
							<div class="col-lg-5">
								<textarea class="form-control" rows="3" placeholder="Enter ..."
									name="demandotherrequest"></textarea>
							</div>
						</div>

						<div class="form-group">
							<div class="col-lg-4 col-lg-offset-1">
								<button id="btn_sub" type="button"
									class="btn btn-block btn-primary">确认</button>
							</div>
							<div class="col-lg-4 col-lg-offset-2">
								<button type="reset" class="btn btn-block btn-primary">重置</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<!-- ==================================弹出窗口部分================================= -->
	</div>
	<script>
		//-- ==============================客服断开事件==================================== -->
		window.onunload = function() {
			if (getCookie("serviceid") != null) {
				$.post('customer_customerlogout.action', {
					ctId : getCookie("customerid")
				});
			}
		}
		//-- ==============================全局变量==================================== -->
		var webSocket = "";
		var currentcustomer = 0;
		var serviceid = 12312;
		$(function() {
			//-- ==============================公共信息保存部分==================================== -->
			setCookie("serviceid", 12312, 1);
			$("#chat").hide();
			//初始化webSocket对象
			$btninit();
			//绑定消息回车事件
			$(document).keypress(function(event) {
				if (event.keyCode == 13) {
					if (currentcustomer == 0) {
						return;
					} else {
						$("#sub_" + currentcustomer).click();
					}
				}
			});
			//模拟客户进入
			//$customerin("老张", "232");
			//$customerin("老李", "a77");
			//$customerin("老吴", "2332");
			//$customerin("老陈", "23232");
		});
		//-- ===================================总体方法======================================= -->
		//客户标签页的事件监听
		function $listen(id) {
			$("#" + id).on('click', function() {
				//1.将点击按钮的客户id保存到当前客服id中
				currentcustomer = $(this).attr("id");
				//2.获得客户基本信息
				var name = "老王";
				$.post('customerInfo_getCustomerinfo.action', {
					id : id
				}, function(result) {
					for (var p in result.customerinfo) {
						var value = result.customerinfo[p];
						if (value == null) {
							$("[name=" + p + "]").next("dd").text("--");
						} else {
							$("[name=" + p + "]").next("dd").text(result.customerinfo[p]);
						}
					}
	
				});
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
						$.post('conversation_conversationEnd.action', {
							'customer_id' : currentcustomer,
							'customerservice_id' : getCookie("serviceid"),
							'msgtype_id' : 1307
						});
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
					$webSocketSend(getCookie("serviceid"), id, 1200, content);
					$("#text_" + id).find(".direct-chat-messages:first").append($msg(true, "我", Date(), "<%=path%>dist/img/user1-128x128.jpg", content));
					$("#inp_" + id).val("");
					var div = $("#text_" + id).find(".direct-chat-messages:first");
					div.scrollTop(div.scrollTop() + 1000);
				}
			});
			$("#btn_" + id).on('click', function() {
				//会话结束部分
				$("#text_" + id).remove();
				$("#" + id).remove();
				currentcustomer = 0;
				toastr.warning('客户' + name + ':' + id + '离开房间');
				$.post('conversation_conversationEnd.action', {
					customerservice_id : parseInt(getCookie("serviceid")),
					customer_id : parseInt(id),
					msgtype_id : parseInt(1307),
				}, function(result) {
					console.log("结束成功")
				});
				$("#customerInfo").find("dd").text("--");
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
						$.post('customerservice_serviceConnectWebSocket.action', {
							'model.csId' : getCookie("serviceid"),
						});
						webSocket = $connect();
					} else {
						console.log("请求关闭")
						$.post('customerservice_serviceCutWebSocket.action', {
							'model.csId' : getCookie("serviceid"),
						});
						webSocket.send(JSON.stringify({
							msgtype_id : 1302, //断开连接请求
							customerservice_id : getCookie("serviceid"), //发送的客服ID
						}));
					}
				}
			})
		}
		//-- ==============================websocket部分========================================== -->
		function $connect() {
			console.log("建立连接")
			var serviceid = getCookie("serviceid");
			if (serviceid == 0) {
				return;
			}
			var webSocketm = new WebSocket('ws:localhost:80/channel/websocket/' + serviceid);
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
				console.log("消息类型:" + msgtype);
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
				//服务器通知客服客户下线
				case 3109:
					$("#text_" + obj.customer_id).remove();
					$("#" + obj.customer_id).remove();
					currentcustomer = 0;
					toastr.warning('客户:' + obj.customer_id + '离开房间');
					break;
				//客户发客服
				case 2100:
					if ((currentcustomer == 0) || (obj.customer_id != currentcustomer)) {
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
				case 2108:
					var content = obj.content;
					var div = $("#text_" + obj.customer_id).find(".direct-chat-messages:first");
					if (content == "confirm") {
						div.append("<p class='text-light-blue'>订单处理成功!</p>");
					}
					if (content == "cancel") {
						div.append("<p class='text-light-blue'>订单已取消</p>");
					}
					break;
				default:
					break;
				}
			};
			return webSocketm;
		}
		//-- ================================右边布局按钮事件响应部分=============================== -->
		$(function() {
			$('#datepicker').datepicker({
				autoclose : true
			})
			$("#btn_createorder").on('click', function() {
				if (currentcustomer == 0) {
					return;
				} else {
					var modal = $("#myModal");
					modal.find("[name=customerId]:first").val(currentcustomer);
					modal.find("[name=customerserviceId]:first").val(getCookie("serviceid"));
					$("#myModalLabel").text("新增");
					$('#myModal').modal();
				}
			});
		}
		)
		//-- ================================会话转接部分=============================================== -->
		$(function() {
			$("#btn_conversationswitch").on('click', function() {
				$('#myModal3').modal();
			});
	
		});
		//-- ================================获取推荐订单部分按钮========================================== -->
		function recommandProduct(result) {
			console.log("recommandProduct");
			var productJSON = result.productJSON;
			var modal = $("#myModal2");
			var div = $("#chat_window");
			modal.find("[name=id]:first").val(productJSON.id),
			modal.find("[name=category_id]:first").val(productJSON.category_id),
			modal.find("[name=name]:first").val(productJSON.name),
			modal.find("[name=price]:first").val(productJSON.price),
			modal.find("[name=remark]:first").val(productJSON.remark),
			modal.find("[name=productstatus]:first").val(productJSON.status),
			modal.find("[name=xremark]:first").val(productJSON.xremark),
			modal.find("[name=pic]:first").attr('src', productJSON.pic),
			//绑定按钮事件
			$('#myModal2').modal();
		}
		function $recommend(result) {
			$("#getrecommend").unbind();
			var productJSON = result.productJSON;
			var recommend = '<div class="attachment-block clearfix">' +
				'<img class="attachment-img" alt="Attachment Image" src="' + productJSON.pic + '">' +
				'<div class="attachment-pushed">' +
				'<h4 class="attachment-heading"><a href="">商品信息</a></h4>' +
				'<div class="attachment-text">' +
				'商品号:' + productJSON.id + '</br>' +
				'商品价格:' + productJSON.price + '</br>' +
				'<button type="button" id="getrecommend" onclick="recommandProduct(' + JSON.stringify(result).replace(/"/g, '&quot;') + ')" class="btn btn-block btn-primary">查看详情</button>' +
				'</div>' +
				'</div>' +
				'</div>'
			return recommend;
		}
		$(function() {
			$("#btn_getrecommend").on('click', function() {
				if (currentcustomer == 0) {
					return;
				}
				console.log("notnull");
				$.post("collaborativeFiltering_getRecommendProductByService.action", {
					customer_id : currentcustomer,
					content : "",
					msgtype_id : 1310
				}, function(result) {
					var msgtype = result.msgtype_id;
					var content = result.content;
					var div = $("#text_" + currentcustomer).find(".direct-chat-messages:first");
					switch (msgtype) {
					case 1310:
						$.post('product_getProduct.action', {
							id : parseInt(content),
						}, function(result) {
							console.log("1310");
							recommandProduct(result);
							//推荐商品表单确认推荐
							$("#recommendbtn_recommend").unbind();
							$("#recommendbtn_recommend").on('click', function() {
								if (currentcustomer == 0) {
									return;
								}
								if (currentcustomer == 0) {
									return;
								}
								$webSocketSend(getCookie("serviceid"), currentcustomer, 1210, content);
	
								div.append($recommend(result));
								$('#myModal2').modal('hide');
								div.scrollTop(div.scrollTop() + 1000);
								$("#recommendbtn_recommend").hide();
							})
							$("#recommendbtn_cancel").unbind();
							$("#recommendbtn_cancel").on('click', function() {
								$('#myModal2').modal('hide');
							})
						});
						break;
					//请求推荐商品失败
					case 1311:
						toastr.error("请求推荐商品失败");
						break;
					}
				});
			});
	
		})
		//-- ================================订单提交按钮========================================== -->
		function $order(result) {
			var orderJSON = result.orderJSON;
			var picpath = result.picpath;
			var order = '<div class="attachment-block clearfix">' +
				'<img class="attachment-img" alt="Attachment Image" src="' + picpath + '">' +
				'<div class="attachment-pushed">' +
				'<h4 class="attachment-heading"><a href="">确认订单信息</a></h4>' +
				'<div class="attachment-text">' +
				'订单号:' + orderJSON.id + '</br>' +
				'订单时间:' + orderJSON.demandtime + '</br>' +
				'</div>' +
				'</div>' +
				'</div>'
			return order;
		}
		$(function() {
			var modal = $("#myModal");
			$("#btn_sub").on('click', function() {
				$.post('order_confirm.action', {
					customerId : modal.find("[name=customerId]:first").val(),
					customerserviceId : modal.find("[name=customerserviceId]:first").val(),
					product_id : modal.find("[name=product_id]:first").val(),
					company_id : getCookie("companyid"),
					demandaddr : modal.find("[name=demandaddr]:first").val(),
					demandtime : modal.find("[name=demandtime]:first").val(),
					demandphone : modal.find("[name=demandphone]:first").val(),
					demandotherrequest : modal.find("[name=demandotherrequest]:first").val(),
				}, function(result) {
					modal.modal('hide');
				})
			}
			);
	
		})
		//-- ==================================表格初始化部分============================================ -->
		$(function() {
			//1.初始化table
			var oTable = new TableInit();
			oTable.Init();
			//2.初始化按钮
			var oButtonInit = new ButtonInit();
			oButtonInit.Init();
		})
		var TableInit = function() {
			var oTableInit = new Object();
			//初始化Table
			oTableInit.Init = function() {
				$('#tb_departments').bootstrapTable({
					//url : '', //请求后台的URL（*）
					contentType : "application/x-www-form-urlencoded; charset=UTF-8",
					method : 'post', //请求方式（*）
					toolbar : '#toolbar', //工具按钮用哪个容器
					striped : true, //是否显示行间隔色
					cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
					pagination : true, //是否显示分页（*）
					sortable : false, //是否启用排序
					sortOrder : "asc", //排序方式
					queryParams : oTableInit.queryParams, //传递参数（*）
					sidePagination : "server", //分页方式：client客户端分页，server服务端分页（*）
					pageNumber : 1, //初始化加载第一页，默认第一页
					pageSize : 10, //每页的记录行数（*）
					pageList : [ 10, 25, 50, 100 ], //可供选择的每页的行数（*）
					//search : true, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
					//searchOnEnterKey : true, //设置为 true时，按回车触发搜索方法，否则自动触发搜索方法
					strictSearch : true,
					showColumns : true, //是否显示所有的列
					showRefresh : true, //是否显示刷新按钮
					minimumCountColumns : 2, //最少允许的列数
					clickToSelect : true, //是否启用点击选中行
					height : 500, //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
					uniqueId : "id", //每一行的唯一标识，一般为主键列
					showToggle : true, //是否显示详细视图和列表视图的切换按钮
					cardView : false, //是否显示详细视图
					detailView : false, //是否显示父子表
					//showExport : true, //是否显示导出
					//exportDataType : "basic", //全部导出
					columns : [ {
						field : 'id',
						title : '知识库ID',
						disabled : true,
					}, {
						field : 'title',
						title : '知识标题',
						disabled : true,
						editable : {
							type : 'text',
							title : '知识标题',
							disabled : true
						}
					}, {
						field : 'category',
						title : '知识类别',
						editable : {
							type : 'text',
							title : '知识类别',
							disabled : true
						}
					}, {
						field : 'content',
						title : '知识内容',
						editable : {
							type : 'text',
							title : '知识内容',
							disabled : true
						}
					} ],
					onClickRow : function(row, $element) {
						curRow = row;
					}
				});
			};
			oTableInit.queryParams = function(params) {
				pageparams = params
			};
			return oTableInit;
		};
		var ButtonInit = function() {
			var oButtonInit = new Object();
			oButtonInit.Init = function() {
				$("#btn_search").on('click', function() {
					var search = $("#inp_search").val();
	
					if (search == "" || search == null) {
						return;
					}
					$('#tb_departments')
					var options = $('#tb_departments').bootstrapTable('getOptions');
					$.post('knowledgebase_searchByCompany.action', {
						'firstResult' : options.pageSize * (options.pageNumber - 1),
						'maxResult' : options.pageSize * options.pageNumber,
						'queryString' : search, //搜索功能
						'company_id' : getCookie("companyid")
					}, function(result) {
						$("#tb_departments").bootstrapTable('load', result);
					})
	
				})
			}
			return oButtonInit;
		}
		//-- ==================================表格初始化部分============================================ -->
		function quickinput(text) {
			if (currentcustomer == null || currentcustomer == "") {
				return;
			} else {
				var value = $("#inp_" + currentcustomer).val();
				$("#inp_" + currentcustomer).val(value + text);
			}
		}
		$(function() {
			$('#tb_quickmsg').bootstrapTable({
				url : 'quickmessage_pageQuery.action', //请求后台的URL（*）
				contentType : "application/x-www-form-urlencoded; charset=UTF-8",
				method : 'post', //请求方式（*）
				//toolbar : '#toolbar', //工具按钮用哪个容器
				striped : true, //是否显示行间隔色
				cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
				pagination : true, //是否显示分页（*）
				sortable : false, //是否启用排序
				sortOrder : "asc", //排序方式
				queryParams : quickmsgParams, //传递参数（*）
				sidePagination : "server", //分页方式：client客户端分页，server服务端分页（*）
				pageNumber : 1, //初始化加载第一页，默认第一页
				pageSize : 10, //每页的记录行数（*）
				pageList : [ 10, 25, 50, 100 ], //可供选择的每页的行数（*）
				//search : true, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
				//searchOnEnterKey : true, //设置为 true时，按回车触发搜索方法，否则自动触发搜索方法
				strictSearch : true,
				showColumns : true, //是否显示所有的列
				showRefresh : true, //是否显示刷新按钮
				minimumCountColumns : 2, //最少允许的列数
				clickToSelect : true, //是否启用点击选中行
				height : 500, //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
				uniqueId : "id", //每一行的唯一标识，一般为主键列
				showToggle : true, //是否显示详细视图和列表视图的切换按钮
				cardView : false, //是否显示详细视图
				detailView : false, //是否显示父子表
				//showExport : true, //是否显示导出
				//exportDataType : "basic", //全部导出
				columns : [ {
					field : 'id',
					title : '短语ID',
					disabled : true,
				}, {
					field : 'content',
					title : '短语内容',
					disabled : true,
				}, {
					titile : '输入',
					disabled : true,
					formatter : function(value, row, index) {
						return '<button type="button" onclick="quickinput(\'' + row.content + '\')">输入</button>';
					}
				} ],
	
				onClickRow : function(row, $element) {
					curRow = row;
				}
			});
		})
		quickmsgParams = function(params) {
			var temp = { //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
				'limit' : params.limit, //页面大小
				'offset' : params.offset, //页码
				'search' : params.search, //搜索功能
				'serviceid' : getCookie("serviceid")
			};
			return temp;
		};
		//-- ==================================快捷短语初始化部分============================================ -->	
	</script>