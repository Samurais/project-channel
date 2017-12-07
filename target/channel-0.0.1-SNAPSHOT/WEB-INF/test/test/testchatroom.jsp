<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/public/starter.jsp"%>
<div id="thispage">
	<div class="col-md-3">
		<!-- DIRECT CHAT PRIMARY -->
		<div class="box box box-primary direct-chat direct-chat-primary">
			<div class="box-header with-border">
				<h3 class="box-title">Direct Chat</h3>

				<div class="box-tools pull-right">
					<span data-toggle="tooltip" title="3 New Messages"
						class="badge bg-light-blue">3</span>
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
				<div class="direct-chat-messages"></div>
				<!-- Contacts are loaded here -->
				<div class="direct-chat-contacts">
					<ul class="contacts-list">
						<li><a href="#"> <img class="contacts-list-img"
								src="<%=path%>dist/img/user1-128x128.jpg" alt="User Image">
								<div class="contacts-list-info">
									<span class="contacts-list-name"> Count Dracula <small
										class="contacts-list-date pull-right">2/28/2015</small>
									</span> <span class="contacts-list-msg">How have you been? I
										was...</span>
								</div> <!-- /.contacts-list-info -->
						</a></li>
						<!-- End Contact Item -->
					</ul>
					<!-- /.contatcts-list -->
				</div>
				<!-- /.direct-chat-pane -->
			</div>
			<!-- /.box-body -->
			<div class="box-footer">
				<div action="#" method="post">
					<div class="input-group">
						<input id="chatinput" type="text" name="message"
							placeholder="Type Message ..." class="form-control"> <span
							class="input-group-btn">
							<button id="chatbut" type="button"
								class="btn btn-primary btn-flat">Send</button>
						</span>
					</div>
				</div>
			</div>
			<!-- /.box-footer-->
		</div>
		<!--/.direct-chat -->
	</div>
	<button type="button" id="btn">點擊</button>
</div>
<script>
	$("#header").find("h1:first").append("主页面 <small>main page</small>");
	($("#content")).append($("#thispage"));
	//***************使用字符串的形式****************
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
	//***************接受消息方法*******************
	//===========================================
	function msgReceive(name, time, msg) {
		var box = $(".direct-chat-msg:first").clone();
		box.find(".direct-chat-name,.pull-left:first").text(name);
		box.find(".direct-chat-timestamp,.pull-right:first").text(time);
		box.find(".direct-chat-text:first").text(msg);
		return box;
	}
	;
	//***************发送消息方法*******************
	//===========================================
	function msgSend(name, time, msg) {
		//var box = $(".direct-chat-msg,.right:first").clone();
		var box = $(".right:first").clone();
		box.find(".direct-chat-name,.pull-right:first").text(name);
		box.find(".direct-chat-timestamp,.pull-left:first").text(time);
		box.find(".direct-chat-text:first").text(msg);
		return box;
	}
	;
	$("#btn").click(function() {
		$(".direct-chat-messages:first").append(msgReceive("大帥哥", "Sat Jul 08 09:21:05 CST 2017", "a"));
	});
	$("#chatbut").click(function() {
		var date = $("#chatinput").val();
		var div = $(".direct-chat-messages:first");
		div.append($msg(true, "我", "bb", "<%=path%>dist/img/user3-128x128.jpg", date));
		div.scrollTop(div.scrollTop() + 1000);
		$.post("robot_toRobot.action", {
			customer_id : 232,
			content : date,
			msgtype_id : 2303
		},
			function(result) {
				div.append($msg(false, "机器人", "bb", "<%=path%>dist/img/user1-128x128.jpg", result.text));
				div.scrollTop(div.scrollTop() + 1000);	
		});
	});
</script>