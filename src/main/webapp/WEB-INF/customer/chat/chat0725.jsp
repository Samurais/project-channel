<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/public/starter.jsp"%>
<div id="thispage">
	<div class="col-md-3">
		<script
			src="<%=realpath%>/resources/bootbox/bootstrapValidator.min.js"></script>
		<link
			href="<%=realpath%>/resources/bootbox/bootstrapValidator.min.css"
			rel="stylesheet" type="text/css" />
		<!-- bootstrapValidator -->
		<!-- ==================================订单确认表单部分================================ -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">确认</h4>
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
									<input type="text" class="form-control"
										name="customerserviceId" readonly="readonly" value="" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-lg-3 control-label">产品ID</label>
								<div class="col-lg-5">
									<input type="text" class="form-control" name="product_id"
										readonly="readonly" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-lg-3 control-label">送货地址</label>
								<div class="col-lg-5">
									<input type="text" class="form-control" name="demandaddr"
										readonly="readonly" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-lg-3 control-label">截至时间</label>

								<div class="input-group date col-lg-5">
									<div class="input-group-addon">
										<i class="fa fa-calendar"></i>
									</div>
									<input class="form-control pull-right" id="datepicker"
										name="demandtime" type="text" readonly="readonly" />
								</div>
								<!-- /.input group -->
							</div>

							<div class="form-group">
								<label class="col-lg-3 control-label">联系电话</label>
								<div class="col-lg-5">
									<input type="text" class="form-control" name="demandphone"
										readonly="readonly" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-lg-3 control-label">客户备注</label>
								<div class="col-lg-5">
									<textarea class="form-control" rows="3" placeholder="Enter ..."
										name="demandotherrequest" readonly="readonly"></textarea>
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
									<button id="orderbtn_sub" type="button"
										class="btn btn-block btn-primary">确认</button>
								</div>
								<div class="col-lg-4 col-lg-offset-2">
									<button id="orderbtn_cancel" type="button"
										class="btn btn-block btn-primary">取消</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
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
								<div class="col-lg-4 col-lg-offset-4">
									<button id="checkrecommend" type="button"
										class="btn btn-block btn-primary">关闭</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!-- ===========================聊天部分============================= -->
		<div
			class="box box box-primary direct-chat direct-chat-primary direct-chat-contacts-open">
			<div class="box-header with-border">
				<h3 class="box-title">客服咨询</h3>

				<div class="box-tools pull-right">
					<span data-toggle="tooltip" title="3 New Messages"
						class="badge bg-light-blue">0</span>
					<button type="button" class="btn btn-box-tool"
						data-widget="collapse">
						<i class="fa fa-plus"></i>
					</button>
					<button type="button" class="btn btn-box-tool"
						data-toggle="tooltip" title="Contacts"
						data-widget="chat-pane-toggle">
						<i class="fa fa-comments"></i>
					</button>
					<button type="button" class="btn btn-box-tool" data-widget="remove">
						<i class="fa fa-times"></i>
					</button>
				</div>
			</div>
			<!-- /.box-header -->
			<div class="box-body">
				<!-- Conversations are loaded here -->
				<div id="chat_window" class="direct-chat-messages"></div>
				<!-- ===========================登陆注册模块=================================== -->
				<div class="direct-chat-contacts" title="constacts_default">
					<ul class="contacts-list">
						<li><a> <label for="inp_loginid" class="control-label">账号</label>
								<input class="form-control" id="inp_loginid" placeholder="账号"
								type="text" name="inp_loginid"><label for="inp_loginid"
								class="control-label"></label>

						</a></li>
					</ul>
					<ul class="contacts-list">
						<li><a> <label for="inp_loginpassword"
								class="control-label">密码</label> <input class="form-control"
								id="inp_loginpassword" placeholder="密码" type="password"
								name="inp_loginpassword">

						</a></li>
					</ul>
					<ul class="contacts-list">
						<li><a>
								<button id="loginbtn_login" type="button"
									class="btn btn-info pull-right">登陆</button>
								<button id="loginbtn_register" type="button"
									class="btn btn-default">注册</button>
						</a></li>
					</ul>
				</div>
				<!-- ===========================注册模块=================================== -->
				<form class="direct-chat-contacts" title="constacts_register"
					hidden="hidden">
					<ul class="contacts-list">
						<li><a> <label for="inp_registerid" class="form_control">账号</label>
								<input class="form-control" id="inp_registerid" placeholder="账号"
								type="text" name="inp_registerid">

						</a></li>
					</ul>
					<ul class="contacts-list">
						<li><a> <label for="inp_registerpassword"
								class="control-label">密码</label> <input class="form-control"
								id="inp_registerpassword" placeholder="密码" type="password"
								name="inp_registerpassword">

						</a></li>
					</ul>
					<ul class="contacts-list">
						<li><a> <label for="inp_registername"
								class="control-label">姓名</label> <input class="form-control"
								id="inp_registername" placeholder="姓名" type="text"
								name="inp_registername">

						</a></li>
					</ul>
					<ul class="contacts-list">
						<li><a> <label for="inp_registerusername"
								class="control-label">用户名</label> <input class="form-control"
								id="inp_registerusername" placeholder="用户名" type="text"
								name="inp_registerusername">

						</a></li>
					</ul>
					<ul class="contacts-list">
						<li><a> <label for="inp_registerphone"
								class="control-label">电话号码</label> <input class="form-control"
								id="inp_registerphone" placeholder="电话号码" type="text"
								name="inp_registerphone">

						</a></li>
					</ul>
					<ul class="contacts-list">
						<li><a> <label for="inp_registermail"
								class="control-label">邮箱</label> <input class="form-control"
								id="inp_registermail" placeholder="邮箱" type="email"
								name="inp_registermail">
						</a></li>
					</ul>
					<ul class="contacts-list">
						<li><a>
								<div class="radio">
									<label> <input name="inp_registersex"
										id="inp_registersex" value="男" checked="" type="radio">
										男
									</label>
								</div>
								<div class="radio">
									<label> <input name="inp_registersex"
										id="inp_registersex" value="女" checked="" type="radio">
										女
									</label>
								</div>
						</a></li>
					</ul>
					<ul class="contacts-list">
						<li><a>
								<button id="registerbtn_register" type="button"
									class="btn btn-info pull-right">注册</button>
								<button id="registerbtn_cancel" type="button"
									class="btn btn-default">取消</button>
						</a></li>
					</ul>
				</form>
				<!-- ===========================注册模块=================================== -->
				<!-- /.direct-chat-pane -->
			</div>
			<!-- /.box-body -->
			<div class="box-footer">
				<div class="input-group">
					<input id="chatinput" type="text" name="message"
						placeholder="Type Message ..." class="form-control"> <span
						class="input-group-btn">
						<button id="chatbut" type="button"
							class="btn btn-primary btn-flat">发送</button>
					</span>
				</div>
			</div>
			<!-- /.box-footer-->
		</div>
		<!--/.direct-chat -->
		<button id="test" type="button">测试</button>
	</div>
	<!-- ==========================聊天結束部分============================= -->
</div>
<script>

	window.onunload = function() {
		if (getCookie("customerid") != null) {
			$.post('customer_customerlogout.action', {
				ctId : getCookie("customerid")
			});
		}
	}
	$("#header").find("h1:first").append("主页面 <small>main page</small>");
	($("#content")).append($("#thispage"));
	//**********************初始化样式**************
	//===========================================
	$(".direct-chat-contacts").css("background", "rgb(255, 255, 255)");
	//***************初始化cookied信息**************
	//===========================================
	clearCookie();
	setCookie("customerid", "", 1);
	setCookie("customerserviceid", "", 1);
	setCookie("companyid", "", 1);
	//***************测试用cookied信息**************
	//===========================================
	setCookie("companyid", 1122, 1);
	//**********************表单验证部分************
	//===========================================
	$('[title=constacts_default]:first')
		.bootstrapValidator({
			message : '验证失败',
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			fields : {
				inp_loginid : {
					validators : {
						stringLength : {
							min : 6,
							max : 30,
						},
						notEmpty : {
						},
					}
				},
				inp_loginpassword : {
					validators : {
						stringLength : {
							min : 6,
							max : 30,
						},
						notEmpty : {
						}
					}
				}
			}
		})
	$('[title=constacts_register]:first')
		.bootstrapValidator({
			message : '验证失败',
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			fields : {
				inp_registerid : {
					validators : {
						notEmpty : {
						},
						stringLength : {
							min : 6,
							max : 30,
						},
						remote : {
							url : 'customer_idValid.action',
						}
					}
				},
				inp_registerpassword : {
					validators : {
						stringLength : {
							min : 6,
							max : 30,
						},
						notEmpty : {
						}
					}
				},
				inp_registerusername : {
					validators : {
						notEmpty : {
						}
					}
				},
				inp_registermail : {
					validators : {
						notEmpty : {
						}
					}
				}
			}
		})
	//**********************绑定按钮方法************
	//===========================================
	//登陆
	$("#loginbtn_login").on('click', function() {
		var div = $("#chat_window");
		var loginid = $("#inp_loginid").val();
		var loginpassword = $("#inp_loginpassword").val();
		if (loginid == "" || loginpassword == "") {
			return;
		}
		$.post('customer_loginValidate.action', {
			loginid : loginid,
			loginpassword : loginpassword,
			companyid : getCookie("companyid"),
		}, function(result) {
			$("[title='Contacts']:first").trigger("click");
			switch (result) {
			case 'success':
				div.append("<p class='text-light-blue'>登陆成功</p>");
				div.scrollTop(div.scrollTop() + 1000);
				$("[title=constacts_default]:first").hide();
				$("[title=constacts_register]:first").hide();
				$("#inp_loginid").val("");
				$("#inp_loginpassword").val("");
				setCookie("customerid", loginid, 1);
				break;
			case 'false':
				div.append("<p class='text-red'>密码错误</p>");
				div.scrollTop(div.scrollTop() + 1000);
				break;
			case 'noexist':
				div.append("<p class='text-yellow'>用户不存在</p>");
				div.scrollTop(div.scrollTop() + 1000);
				break;
			}
		});
	});
	//注册
	$("#loginbtn_register").on('click', function() {
		$("[title=constacts_default]:first").hide();
		$("[title=constacts_register]:first").show();

	});
	//注册
	$("#registerbtn_register").on('click', function() {
		$.post('customer_register.action', {
			ctId : $("#inp_registerid").val(),
			ctPassword : $("#inp_registerpassword").val(),
			ctUsername : $("#inp_registerusername").val(),
			ctName : $("#inp_registername").val(),
			ctPhone : $("#inp_registerphone").val(),
			ctMailbox : $("#inp_registermail").val(),
			ctSex : $("#inp_registersex").val(),
			companyid : getCookie("companyid")
		}, function(result) {
			$("[title='Contacts']:first").trigger("click");
			$("#chat_window").append("<p class='text-light-blue'>注册成功</p>");
			div.scrollTop(div.scrollTop() + 1000);
			$("[title=constacts_default]:first").show();
			$("[title=constacts_register]:first").hide();
			$("#inp_registerid").val("");
			$("#inp_registerpassword").val("");
			$("#inp_registerusername").val("");
			$("#inp_registername").val("");
			$("#inp_registerphone").val("");
			$("#inp_registermail").val("");
			$("#inp_registersex").val("");

		});
	});
	//取消
	$("#registerbtn_cancel").on('click', function() {
		$("[title=constacts_register]:first").hide();
		$("[title=constacts_default]:first").show();
	});
	//****************订单确认信息******************
	//===========================================

	function btnconfirm(result) {
		console.log("btnconfirm");
		var orderJSON = result.orderJSON;
		var modal = $("#myModal");
		var div = $("#chat_window");
		modal.find("[name=customerId]:first").val(orderJSON.customerId),
		modal.find("[name=customerserviceId]:first").val(orderJSON.customerserviceId),
		modal.find("[name=product_id]:first").val(orderJSON.product_id),
		modal.find("[name=demandaddr]:first").val(orderJSON.demandaddr),
		modal.find("[name=demandtime]:first").val(orderJSON.demandtime),
		modal.find("[name=demandphone]:first").val(orderJSON.demandphone),
		modal.find("[name=demandotherrequest]:first").val(orderJSON.demandotherrequest),
		console.log(result.picpath);
		modal.find("[name=pic]:first").attr('src', result.picpath),
		//绑定按钮事件
		$("#orderbtn_sub").show();
		$('#myModal').modal();
		div.scrollTop(div.scrollTop() + 1000);
	}
	function orderinit(result) {
		var orderJSON = result.orderJSON;
		var modal = $("#myModal");
		var div = $("#chat_window");
		$("#orderbtn_sub").on('click', function() {
			console.log("orderbtn_sub");
			$.post('order_responseconfirm.action', {
				'shortMsg.customerservice_id' : parseInt(orderJSON.customerserviceId),
				'shortMsg.customer_id' : parseInt(orderJSON.customerId),
				'shortMsg.msgtype_id' : parseInt(2108),
				'shortMsg.content' : "confirm",
				'orderid' : parseInt(orderJSON.id),
			}, function(result) {
				$('#myModal').modal('hide');
				$("#checkproduct").hide();
				div.append("<p class='text-light-blue'>订单处理成功!</p>");
				div.scrollTop(div.scrollTop() + 1000);
			});
			$("#orderbtn_sub").unbind();
			$("#orderbtn_cancel").unbind();
		})
		$("#orderbtn_cancel").on('click', function() {
			console.log("orderbtn_sub");
			$.post('order_responseconfirm.action', {
				'shortMsg.customerservice_id' : parseInt(orderJSON.customerserviceId),
				'shortMsg.customer_id' : parseInt(orderJSON.customerId),
				'shortMsg.msgtype_id' : parseInt(2108),
				'shortMsg.content' : "cancel",
				'orderid' : parseInt(orderJSON.id),
			}, function(result) {
				$('#myModal').modal('hide');
				$("#checkproduct").hide();
				div.append("<p class='text-light-blue'>订单已取消</p>");
				div.scrollTop(div.scrollTop() + 1000);
			});
			$("#orderbtn_sub").unbind();
			$("#orderbtn_cancel").unbind();
		})
	}
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
			'<button id="checkproduct" type="button" onclick="btnconfirm(' + JSON.stringify(result).replace(/"/g, '&quot;') + ')" class="btn btn-block btn-primary">查看详情</button>' +
			'</div>' +
			'</div>' +
			'</div>'
		return order;
	}
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
		modal.find("[name=status]:first").val(productJSON.status),
		modal.find("[name=xremark]:first").val(productJSON.xremark),
		modal.find("[name=pic]:first").attr('src', productJSON.pic),
		//绑定按钮事件
		$('#myModal2').modal();
		div.scrollTop(div.scrollTop() + 1000);
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
	//***************满意度填写信息******************
	//===========================================
	function $degree() {
		var degree = '<div id="btn_degree" class="btn-group" role="group" aria-label="...">' +
			'<button type="button" class="btn btn-default">1</button>' +
			'<button type="button" class="btn btn-default">2</button>' +
			'<button type="button" class="btn btn-default">3</button>' +
			'<button type="button" class="btn btn-default">4</button>' +
			'<button type="button" class="btn btn-default">5</button>' +
			'<button type="button" class="btn btn-default">6</button>' +
			'<button type="button" class="btn btn-default">7</button>' +
			'</div>'
		return degree;
	}
	//******************消息放松方法****************
	//===========================================
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
	//***************初始默认发送按钮事件*************
	//===========================================
	$("#chatbut").click(button_toRobot);
	//***************绑定回车按钮*******************
	//===========================================
	$(document).keypress(function(event) {
		if (event.keyCode == 13) {
			$("#chatbut").click();
			$("#chatinput").val("");
		}
	});
	//***************webSocket注册函数*************
	//===========================================
	var webSocket; //全局webSocket对象
	function websocktInit() {
		var webSocket;
		webSocket = new WebSocket('ws://localhost:8080/channel/websocket/web');
		webSocket.onerror = function(event) {
			console.log("错误连接");
		};
		webSocket.onclose = function(event) {
			console.log("关闭连接");
		};
		webSocket.onopen = function(event) {
			console.log("开启连接");
			webSocket.sendmessage(null, getCookie("customerid"), 2300, null);
		};
		webSocket.onmessage = function(event) {
			console.log("收到消息");
			var obj = jQuery.parseJSON(event.data);
			callback(obj);

		};
		webSocket.sendmessage = function(serviceid, customerid, msgtypeid, content) {
			console.log("发送消息");
			console.log("msgtype:" + msgtypeid);
			if (content == null & msgtypeid == 2300) {
				console.log("webSocket:方法1")
				webSocket.send(JSON.stringify({
					customer_id : parseInt(customerid),
					msgtype_id : parseInt(msgtypeid),
				}));
				return;
			}
			if (content == null & msgtypeid != 2300) {
				console.log("webSocket:方法2")
				return;
			}
			if (customerid == null & serviceid == null) {
				webSocket.send(JSON.stringify({
					msgtype_id : parseInt(msgtypeid),
					content : content
				}));
				console.log("webSocket:方法3")
			}
			if (customerid == null & serviceid != null) {
				webSocket.send(JSON.stringify({
					customer_id : parseInt(customerid),
					msgtype_id : parseInt(msgtypeid),
					content : content
				}));
				console.log("webSocket:方法4")
			}
			if ((serviceid != null) & (customerid == null)) {
				console.log("webSocket:方法5")
				console.log(content);
				webSocket.send(JSON.stringify({
					customerservice_id : parseInt(serviceid),
					msgtype_id : parseInt(msgtypeid),
					content : content
				}));
				return;
			}
			if ((serviceid != null) & (customerid != null)) {
				console.log("webSocket:方法6")
				console.log(content);
				webSocket.send(JSON.stringify({
					customerservice_id : parseInt(serviceid),
					customer_id : parseInt(customerid),
					msgtype_id : parseInt(msgtypeid),
					content : content
				}));
				return;
			}
		}
		return webSocket;
	}
	//***************cookied函数******************
	//===========================================
	function setCookie(name, value, iDay) {
		var oDate = new Date();
		oDate.setDate(oDate.getDate() + iDay);
		document.cookie = name + '=' + encodeURIComponent(value) + ';expires=' + oDate;
	}
	function getCookie(name) {
		var arr = document.cookie.split('; ');
		var i = 0;
		for (i = 0; i < arr.length; i++) {
			var arr2 = arr[i].split('=');
			if (arr2[0] == name) {
				var getC = decodeURIComponent(arr2[1]);
				return getC;
			}
		}
		return '';
	}
	function removeCookie(name) {
		setCookie(name, '1', -1);
	}
	function clearCookie() {
		var keys = document.cookie.match(/[^ =;]+(?=\=)/g);
		if (keys) {
			for (var i = keys.length; i--;)
				document.cookie = keys[i] + '=0;expires=' + new Date(0).toUTCString()
		}
	}
	//===========================================
	//***************button按钮事件****************
	function button_toRobot() {
		var date = $("#chatinput").val();
		var div = $("#chat_window");
		div.append($msg(true, "我", new Date(), "<%=path%>dist/img/user3-128x128.jpg", date));
		div.scrollTop(div.scrollTop() + 1000);
		//检验用户消息内容，如果请求人工，则判断是否登陆
		if (getCookie("customerid") == "") {
			if (date.indexOf("人工") != -1) {
				//弹出登录窗口
				div.append($msg(false, "机器人", new Date(), "<%=path%>dist/img/user1-128x128.jpg", "请您先登陆哦"));
				div.scrollTop(div.scrollTop() + 1000);
				$("[title='Contacts']:first").trigger("click");
			} else {
				$.post("robot_toRobot.action", {
					content : date,
					msgtype_id : 2303
				}, function(result) {
					callback(result)
				})
			}
		} else {
			//智能推荐商品
			var customerid = getCookie("customerid");
			if (date.indexOf("想买") != -1) {
				$.post("collaborativeFiltering_getRecommendProduct.action", {
					customer_id : customerid,
					content : date,
					msgtype_id : 2310
				}, function(result) {
					callback(result)
				});
				return;
			}
			$.post("robot_toRobot.action", {
				customer_id : customerid,
				content : date,
				msgtype_id : 2303
			}, function(result) {
				callback(result)
			})

		}

	}
	function button_toService() {
		var date = $("#chatinput").val();
		var div = $("#chat_window");
		div.append($msg(true, "我", new Date(), "<%=path%>dist/img/user3-128x128.jpg", date));
		div.scrollTop(div.scrollTop() + 1000);
		//从Cookied中获取客户id与客服id
		var customerid = getCookie("customerid");
		var serviceid = getCookie("serviceid");
		webSocket.sendmessage(serviceid, customerid, 2100, date);
	}

	//***************发送信息的回调函数***************
	//===========================================
	//webSocket的标准消息参数:customerservice_id,customer_id,msgtype_id,content
	function callback(result) {
		var msgtype = result.msgtype_id;
		console.log("消息类型：" + msgtype);
		var content = result.content;
		console.log("消息内容: " + result.content);
		var div = $("#chat_window");
		switch (msgtype) {
		//客服发客户
		case 1200:
			div.append($msg(false, "客服" + getCookie("serviceid"), new Date(), "<%=path%>dist/img/user1-128x128.jpg", content));
			div.scrollTop(div.scrollTop() + 1000);
			break;
		//客户订单确认:
		case 1208:
			console.log("订单确认");
			if (content == null) {
				console.log("订单错误");
				return;
			}
			$.post('order_getOrder.action', {
				id : parseInt(content),
			}, function(result) {
				console.log("1208");
				div.append($order(result));
				orderinit(result);
				div.scrollTop(div.scrollTop() + 1000);
			});
			break;
		//请求推荐商品成功
		case 2310:
			$.post('product_getProduct.action', {
				id : parseInt(content),
			}, function(result) {
				console.log("2310");
				div.append($msg(false, "机器人", new Date(), "<%=path%>dist/img/user1-128x128.jpg", "你是不是想买这个呢"));
				div.append($recommend(result));
				$recommend(result);
				$("#checkrecommend").on('click', function() {
					$('#myModal2').modal('hide');
				});
				div.scrollTop(div.scrollTop() + 1000);
			});
			break;
		//请求推荐商品失败
		case 2311:
			$.post("robot_toRobot.action", {
				customer_id : getCookie("customerid"),
				content : content,
				msgtype_id : 2303
			}, function(result) {
				callback(result)
			})
			break;
		//服务器响应关闭连接请求
		case 3202:
			webSocket = websocktInit();
			console.log("3202");
			break;
		//机器人返回消息
		case 3203:
			console.log("3203");
			switch (result.content.code) {
			case 100000:
				div.append($msg(false, "机器人", new Date(), "<%=path%>dist/img/user1-128x128.jpg", result.content.text));
				div.scrollTop(div.scrollTop() + 1000);
				break;
			case 200000:
				div.append($msg(false, "机器人", new Date(), "<%=path%>dist/img/user1-128x128.jpg",
					result.content.text + "</br>" + result.content.url));
				div.scrollTop(div.scrollTop() + 1000);
				break;
			}
			break;
		//服务器通知客户进入
		case 3204:
			console.log("3204");
			webSocket = websocktInit();
			div.scrollTop(div.scrollTop() + 1000);
			var serviceid = result.customerservice_id;
			setCookie("serviceid", serviceid, 1);
			setCookie("customerserviceid", result.customerservice_id, 1);
			div.append("<p class='text-light-blue'>客服" + serviceid + "为您服务</p>");
			div.scrollTop(div.scrollTop() + 1000);
			$("#chatbut").unbind();
			$("#chatbut").click(button_toService);
			break;
		//服务器通知客户排队
		case 3205:
			console.log("3205");
			webSocket = websocktInit();
			div.append("<p class='text-light-blue'>请稍微等待，客服马上就来~</p>");
			div.scrollTop(div.scrollTop() + 1000);
			break;
		//服务器通知客户排队队列已满
		case 3206:
			console.log("3206");
			webSocket = websocktInit();
			div.append("<p class='text-light-blue'>对不起，客服正忙</p>");
			div.scrollTop(div.scrollTop() + 1000);
			break;
		//会话结束通知,请求填写满意表
		case 3207:
			console.log("3207");
			setCookie("customerserviceid", "", 1);
			div.append("<p class='text-light-blue'>会话结束</p>");
			div.append("<p class='text-light-blue'>客服" + result.customerservice_id + "很荣幸为您服务</p>");
			div.append("<p class='text-light-blue'>您对本次服务的评价:</p>");
			$("#chat_window").append($degree());
			div.append("</br>");
			div.scrollTop(div.scrollTop() + 1000);
			$("#btn_degree").find("button").on('click', function() {
				$.post('conversation_setDegree.action', {
					customerservice_id : result.customerservice_id,
					customer_id : result.customer_id,
					msgtype_id : 2307,
					content : $(this).text(),
				}, function() {
					div.append("<p class='text-light-blue'>感谢您对本次服务的评价,接下来你还可以继续和机器人聊天哦</p>");
					div.scrollTop(div.scrollTop() + 1000);
					$("#btn_degree").find("button").unbind();
				})
			});
			$("#chatbut").unbind();
			$("#chatbut").click(button_toRobot);
			break;
		//服务器通知客户客服下线
		case 3209:
			console.log("3209");
			setCookie("customerserviceid", "", 1);
			div.append("<p class='text-light-blue'>客服异常下线</p>");
			$("#chatbut").unbind();
			$("#chatbut").click(button_toRobot);
			break;
		default:
			console.log("未知状态消息");
			break;
		}

	}
	function logout() {
		console.log("触发logout");
		var logoutURL = "send_customer_chat_chat.action"; //用于注销用户的url   
		if (logoutURL == "") return;
		var userAgent = navigator.userAgent.toLowerCase();
		if (userAgent.indexOf("msie") > -1) { //IE   
			$.ajax({
				url : logoutURL,
				crossDomain : true,
				async : false,
				dataType : "jsonp"
			});
		} else { //FireFox Chrome   
			$.ajax({
				url : logoutURL,
				async : false
			});
		}
	}
</script>

<script>

	$("#test").on('click', function() {
		$.post('collaborativeFiltering_getRecommendProduct.action', {
			ctId : 123123
		});
	});
</script>