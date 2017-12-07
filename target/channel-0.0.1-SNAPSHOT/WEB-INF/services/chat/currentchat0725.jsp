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

					</dl>
					<a class="btn btn-app" id="btn_createorder"> <i
						class="fa fa-edit"></i> Edit
					</a> <a class="btn btn-app" id="btn_getrecommend"> <i
						class="fa fa-heart-o"></i> Likes
					</a>
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
								<input type="text" class="form-control" name="status"
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
								<button id="checkrecommend" type="button"
									class="btn btn-block btn-primary">确认</button>
							</div>
							<div class="col-lg-4 col-lg-offset-2">
								<button type="button" class="btn btn-block btn-primary">取消</button>
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
		setCookie("serviceid", 12312, 1);
		$(function() {
			//-- ==============================公共信息保存部分==================================== -->
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
			$customerin("老张", "232");
			$customerin("老李", "a77");
			$customerin("老吴", "2332");
			$customerin("老陈", "23232");
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
					$webSocketSend(serviceid, id, 1200, content);
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
					customerservice_id : parseInt(serviceid),
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
			$("#btn_getrecommend").on('click', function() {
				if (currentcustomer == 0) {
					return;
				} else {
					var modal = $("#myModal2");
				}
			});
		}
		)
		//-- ================================订单提交按钮========================================== -->
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
				}, function(result) {})
				modal.modal('hide');
			}
			);
		})
	</script>