<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String realpath = request.getContextPath();
	String path = request.getContextPath() + "/AdminLTE-2.4.0-rc/";
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>畅联Access | Starter</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<link rel="stylesheet"
	href="<%=path%>bower_components/bootstrap/dist/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="<%=path%>bower_components/font-awesome/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="<%=path%>bower_components/Ionicons/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="<%=path%>dist/css/AdminLTE.min.css">
<!-- AdminLTE Skins. We have chosen the skin-blue for this starter
        page. However, you can choose any other skin. Make sure you
        apply the skin class to the body tag so the changes take effect. -->
<link rel="stylesheet" href="<%=path%>dist/css/skins/skin-black.min.css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

<!-- Google Font -->

<!--
BODY TAG OPTIONS:
=================
Apply one or more of the following classes to get the
desired effect
|---------------------------------------------------------|
| SKINS         | skin-blue                               |
|               | skin-black                              |
|               | skin-purple                             |
|               | skin-yellow                             |
|               | skin-red                                |
|               | skin-green                              |
|---------------------------------------------------------|
|LAYOUT OPTIONS | fixed                                   |
|               | layout-boxed                            |
|               | layout-top-nav                          |
|               | sidebar-collapse                        |
|               | sidebar-mini                            |
|---------------------------------------------------------|
-->
<body class="hold-transition skin-black fixed">
	<div class="wrapper">

		<!-- Main Header -->
		<header class="main-header">

			<!-- public function -->
			<script type="text/javascript">
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
			
				//==================================================================
				//**************************登陆的初始化参数****************************
				setCookie("companyid", 1122, 1);
			</script>

			<!-- Logo -->
			<a href="send_companys_monitor_monitor.action" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
				<span class="logo-lg"><img alt="Access"
					src="<%=realpath%>/image/accesslogo.png" height="45" width="45">
					<b>畅联</b>Service</span>
			</a>

			<!-- Header Navbar -->
			<nav class="navbar navbar-static-top" role="navigation">
				<!-- Sidebar toggle button-->
				<a href="#" class="sidebar-toggle" data-toggle="push-menu"
					role="button"> <span class="sr-only">Toggle navigation</span>
				</a>
				<!-- Navbar Right Menu -->
				<div class="navbar-custom-menu">
					<ul class="nav navbar-nav">
						<!-- Messages: style can be found in dropdown.less-->
						<li class="dropdown messages-menu">
							<!-- Menu toggle button --> <a href="#" class="dropdown-toggle"
							data-toggle="dropdown"> <i class="fa fa-envelope-o"></i> <span
								class="label label-success">4</span>
						</a>
							<ul class="dropdown-menu">
								<li class="header">You have 4 messages</li>
								<li>
									<!-- inner menu: contains the messages -->
									<ul class="menu">
										<li>
											<!-- start message --> <a href="#">
												<div class="pull-left">
													<!-- User Image -->
													<img src="<%=path%>dist/img/user2-160x160.jpg"
														class="img-circle" alt="User Image">
												</div> <!-- Message title and timestamp -->
												<h4>
													Support Team <small><i class="fa fa-clock-o"></i> 5
														mins</small>
												</h4> <!-- The message -->
												<p>Why not buy a new awesome theme?</p>
										</a>
										</li>
										<!-- end message -->
									</ul> <!-- /.menu -->
								</li>
								<li class="footer"><a href="#">See All Messages</a></li>
							</ul>
						</li>
						<!-- /.messages-menu -->

						<!-- Notifications Menu -->
						<li class="dropdown notifications-menu">
							<!-- Menu toggle button --> <a href="#" class="dropdown-toggle"
							data-toggle="dropdown"> <i class="fa fa-bell-o"></i> <span
								class="label label-warning">10</span>
						</a>
							<ul class="dropdown-menu">
								<li class="header">You have 10 notifications</li>
								<li>
									<!-- Inner Menu: contains the notifications -->
									<ul class="menu">
										<li>
											<!-- start notification --> <a href="#"> <i
												class="fa fa-users text-aqua"></i> 5 new members joined
												today
										</a>
										</li>
										<!-- end notification -->
									</ul>
								</li>
								<li class="footer"><a href="#">View all</a></li>
							</ul>
						</li>
						<!-- Tasks Menu -->
						<li class="dropdown tasks-menu">
							<!-- Menu Toggle Button --> <a href="#" class="dropdown-toggle"
							data-toggle="dropdown"> <i class="fa fa-flag-o"></i> <span
								class="label label-danger">9</span>
						</a>
							<ul class="dropdown-menu">
								<li class="header">You have 9 tasks</li>
								<li>
									<!-- Inner menu: contains the tasks -->
									<ul class="menu">
										<li>
											<!-- Task item --> <a href="#"> <!-- Task title and progress text -->
												<h3>
													Design some buttons <small class="pull-right">20%</small>
												</h3> <!-- The progress bar -->
												<div class="progress xs">
													<!-- Change the css width attribute to simulate progress -->
													<div class="progress-bar progress-bar-aqua"
														style="width: 20%" role="progressbar" aria-valuenow="20"
														aria-valuemin="0" aria-valuemax="100">
														<span class="sr-only">20% Complete</span>
													</div>
												</div>
										</a>
										</li>
										<!-- end task item -->
									</ul>
								</li>
								<li class="footer"><a href="#">View all tasks</a></li>
							</ul>
						</li>
						<!-- User Account Menu -->
						<li class="dropdown user user-menu">
							<!-- Menu Toggle Button --> <a href="#" class="dropdown-toggle"
							data-toggle="dropdown"> <!-- The user image in the navbar-->
								<img src="<%=path%>dist/img/user2-160x160.jpg"
								class="user-image" alt="User Image"> <!-- hidden-xs hides the username on small devices so only the image appears. -->
								<span class="hidden-xs">Alexander Pierce</span>
						</a>
							<ul class="dropdown-menu">
								<!-- The user image in the menu -->
								<li class="user-header"><img
									src="<%=path%>dist/img/user2-160x160.jpg" class="img-circle"
									alt="User Image">

									<p>
										Alexander Pierce - Web Developer <small>Member since
											Nov. 2012</small>
									</p></li>
								<!-- Menu Body -->
								<li class="user-body">
									<div class="row">
										<div class="col-xs-4 text-center">
											<a href="#">Followers</a>
										</div>
										<div class="col-xs-4 text-center">
											<a href="#">Sales</a>
										</div>
										<div class="col-xs-4 text-center">
											<a href="#">Friends</a>
										</div>
									</div> <!-- /.row -->
								</li>
								<!-- Menu Footer-->
								<li class="user-footer">
									<div class="pull-left">
										<a href="#" class="btn btn-default btn-flat">Profile</a>
									</div>
									<div class="pull-right">
										<a href="#" class="btn btn-default btn-flat">Sign out</a>
									</div>
								</li>
							</ul>
						</li>
						<!-- Control Sidebar Toggle Button -->
						<li><a href="#" data-toggle="control-sidebar"><i
								class="fa fa-gears"></i></a></li>
					</ul>
				</div>
			</nav>
		</header>
		<!-- Left side column. contains the logo and sidebar -->
		<aside class="main-sidebar">

			<!-- sidebar: style can be found in sidebar.less -->
			<section class="sidebar">

				<!-- Sidebar user panel (optional) -->
				<div class="user-panel">
					<div class="pull-left image">
						<img src="<%=path%>dist/img/user2-160x160.jpg" class="img-circle"
							alt="User Image">
					</div>
					<div class="pull-left info">
						<p>Alexander Pierce</p>
						<!-- Status -->
						<a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
					</div>
				</div>

				<!-- search form (Optional) -->
				<form action="#" method="get" class="sidebar-form">
					<div class="input-group">
						<input type="text" name="q" class="form-control"
							placeholder="Search..."> <span class="input-group-btn">
							<button type="submit" name="search" id="search-btn"
								class="btn btn-flat">
								<i class="fa fa-search"></i>
							</button>
						</span>
					</div>
				</form>
				<!-- /.search form -->

				<!-- Sidebar Menu -->
				<ul class="sidebar-menu" data-widget="tree">
					<li class="header">目录</li>
					<!-- Optionally, you can add icons to the links -->
					<li><a href="send_companys_monitor_monitor.action"> <i
							class="fa fa-dashboard"></i> <span>智能监控</span>
					</a></li>

					<li class="treeview"><a href="#"> <i
							class="fa fa-cart-plus"></i> <span>产品管理</span> <span
							class="pull-right-container"> <i
								class="fa fa-angle-left pull-right"></i>
						</span>
					</a>
						<ul class="treeview-menu">
							<li><a href="send_companys_product_categorymanager.action"><i
									class="fa fa-circle-o"></i> 产品类别查看</a></li>
							<li><a href="send_companys_product_productmanager.action"><i
									class="fa fa-circle-o"></i> 产品查看</a></li>
							<li><a href="send_companys_product_order.action"><i
									class="fa fa-circle-o"></i> 工单查看</a></li>
						</ul></li>
					<li class="treeview"><a href="#"> <i class="fa fa-group"></i>
							<span>分组管理</span> <span class="pull-right-container"> <i
								class="fa fa-angle-left pull-right"></i>
						</span>
					</a>
						<ul class="treeview-menu">
							<li><a href="send_companys_role_rightmanager.action"><i
									class="fa fa-circle-o"></i> 分组管理</a></li>
							<li><a href="send_companys_role_rolemanager.action"><i
									class="fa fa-circle-o"></i> 客服分组</a></li>
						</ul></li>
					<li class="treeview"><a href="#"> <i class="fa fa-tv"></i>
							<span>客服管理</span> <span class="pull-right-container"> <i
								class="fa fa-angle-left pull-right"></i>
						</span>
					</a>
						<ul class="treeview-menu">
							<li><a href="send_companys_service_manager.action"><i
									class="fa fa-circle-o"></i> 客服管理</a></li>
							<li><a href="send_companys_service_rightmanager.action"><i
									class="fa fa-circle-o"></i> 权限管理</a></li>
							<li><a href="send_companys_service_rolemanager.action"><i
									class="fa fa-circle-o"></i> 角色管理</a></li>
							<li><a href="send_companys_service_msgmanager.action"><i
									class="fa fa-circle-o"></i> 客服会话记录查看</a></li>
							<li><a href="send_companys_service_windowoptimize.action"><i
									class="fa fa-circle-o"></i> 客服窗口优化</a></li>
						</ul></li>
					<li class="treeview"><a href="#"> <i class="fa fa-male"></i>
							<span>客户管理</span> <span class="pull-right-container"> <i
								class="fa fa-angle-left pull-right"></i>
						</span>
					</a>
						<ul class="treeview-menu">
							<li><a href="send_companys_customer_manager.action"><i
									class="fa fa-circle-o"></i> 客户管理</a></li>
							<li><a href="send_companys_customer_rfmmanager.action"><i
									class="fa fa-circle-o"></i> 客户RFM记录</a></li>

						</ul></li>
					<li class="treeview"><a href="#"> <i class="fa fa-cubes"></i>
							<span>数据分析</span> <span class="pull-right-container"> <i
								class="fa fa-angle-left pull-right"></i>
						</span>
					</a>
						<ul class="treeview-menu">
							<li><a href="send_companys_chart_referer.action"><i
									class="fa fa-circle-o"></i> 搜索引擎分析</a></li>
							<li><a href="send_companys_chart_access.action"><i
									class="fa fa-circle-o"></i> 接入渠道分析</a></li>
							<li><a href="send_companys_chart_loginregion.action"><i
									class="fa fa-circle-o"></i> 访问地区分析</a></li>
							<li><a href="send_companys_chart_flux.action"><i
									class="fa fa-circle-o"></i> 流量分析</a></li>
							<li><a href="send_companys_chart_customerrfm.action"><i
									class="fa fa-circle-o"></i> 客户价值分析</a></li>
							<li><a href="send_companys_chart_hotword.action"><i
									class="fa fa-circle-o"></i> 客服热词分析</a></li>
						</ul></li>
					<li class="treeview"><a href="#"> <i
							class="fa fa-sticky-note"></i> <span>知识库管理</span> <span
							class="pull-right-container"> <i
								class="fa fa-angle-left pull-right"></i>
						</span>
					</a>
						<ul class="treeview-menu">
							<li><a
								href="send_companys_knowledge_insideknowledgebase.action"><i
									class="fa fa-circle-o"></i> 内部知识库管理</a></li>
							<li><a
								href="send_companys_knowledge_robotknowledgebase.action"><i
									class="fa fa-circle-o"></i> 机器人知识库管理</a></li>
						</ul></li>
					<li><a
						href="send_companys_conversation_conversationmanager.action">
							<i class="fa fa-calendar-o"></i> <span>历史会话查看</span>
					</a></li>
					<li class="treeview"><a href="#"> <i
							class="fa fa-pie-chart"></i> <span>查看报表</span> <span
							class="pull-right-container"> <i
								class="fa fa-angle-left pull-right"></i>
						</span>
					</a>
						<ul class="treeview-menu">
							<li><a href=""><i class="fa fa-circle-o"></i> 客服服务率报表</a></li>
							<li><a href=""><i class="fa fa-circle-o"></i> 客服满意度报表</a></li>
							<li><a href=""><i class="fa fa-circle-o"></i> 业绩报表</a></li>
						</ul></li>
					<li class="treeview"><a href="#"> <i class="fa  fa-gear"></i>
							<span>系统设置</span> <span class="pull-right-container"> <i
								class="fa fa-angle-left pull-right"></i>
						</span>
					</a>
						<ul class="treeview-menu">
							<li><a href=""><i class="fa fa-circle-o"></i> 接入设置</a></li>
							<li><a href="send_companys_set_systemset.action"><i
									class="fa fa-circle-o"></i> 系统设置</a></li>
						</ul></li>
				</ul>
				<!-- /.sidebar-menu -->
			</section>
			<!-- /.sidebar -->
		</aside>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section id="header" class="content-header">
				<h1></h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
					<li class="active">Here</li>
				</ol>
			</section>

			<!-- Main content -->
			<section id="content" class="content container-fluid">

				<!--------------------------
        | Your Page Content Here |
        -------------------------->
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<!-- Main Footer -->
		<footer class="main-footer">
			<!-- To the right -->
			<div class="pull-right hidden-xs">Anything you want</div>
			<!-- Default to the left -->
			<strong>Copyright &copy; 2016 <a href="#">Company</a>.
			</strong> All rights reserved.
		</footer>

		<!-- Control Sidebar -->
		<aside class="control-sidebar control-sidebar-dark">
			<!-- Create the tabs -->
			<ul class="nav nav-tabs nav-justified control-sidebar-tabs">
				<li class=""><a href="#control-sidebar-home-tab"
					data-toggle="tab" aria-expanded="false"><i class="fa fa-home"></i></a></li>

				<li class=""><a href="#control-sidebar-settings-tab"
					data-toggle="tab" aria-expanded="false"><i class="fa fa-gears"></i></a></li>
			</ul>
			<!-- Tab panes -->
			<div class="tab-content">
				<!-- Home tab content -->
				<div class="tab-pane" id="control-sidebar-home-tab">
					<h3 class="control-sidebar-heading">Recent Activity</h3>
					<ul class="control-sidebar-menu">
						<li><a href="javascript:void(0)"> <i
								class="menu-icon fa fa-birthday-cake bg-red"></i>

								<div class="menu-info">
									<h4 class="control-sidebar-subheading">Langdon's Birthday</h4>

									<p>Will be 23 on April 24th</p>
								</div>
						</a></li>
						<li><a href="javascript:void(0)"> <i
								class="menu-icon fa fa-user bg-yellow"></i>

								<div class="menu-info">
									<h4 class="control-sidebar-subheading">Frodo Updated His
										Profile</h4>

									<p>New phone +1(800)555-1234</p>
								</div>
						</a></li>
						<li><a href="javascript:void(0)"> <i
								class="menu-icon fa fa-envelope-o bg-light-blue"></i>

								<div class="menu-info">
									<h4 class="control-sidebar-subheading">Nora Joined Mailing
										List</h4>

									<p>nora@example.com</p>
								</div>
						</a></li>
						<li><a href="javascript:void(0)"> <i
								class="menu-icon fa fa-file-code-o bg-green"></i>

								<div class="menu-info">
									<h4 class="control-sidebar-subheading">Cron Job 254
										Executed</h4>

									<p>Execution time 5 seconds</p>
								</div>
						</a></li>
					</ul>
					<!-- /.control-sidebar-menu -->

					<h3 class="control-sidebar-heading">Tasks Progress</h3>
					<ul class="control-sidebar-menu">
						<li><a href="javascript:void(0)">
								<h4 class="control-sidebar-subheading">
									Custom Template Design <span
										class="label label-danger pull-right">70%</span>
								</h4>

								<div class="progress progress-xxs">
									<div class="progress-bar progress-bar-danger"
										style="width: 70%"></div>
								</div>
						</a></li>
						<li><a href="javascript:void(0)">
								<h4 class="control-sidebar-subheading">
									Update Resume <span class="label label-success pull-right">95%</span>
								</h4>

								<div class="progress progress-xxs">
									<div class="progress-bar progress-bar-success"
										style="width: 95%"></div>
										</div>
						</a></li>
						<li><a href="javascript:void(0)">
								<h4 class="control-sidebar-subheading">
									Laravel Integration <span
										class="label label-warning pull-right">50%</span>
								</h4>

								<div class="progress progress-xxs">
									<div class="progress-bar progress-bar-warning"
										style="width: 50%"></div>
								</div>
						</a></li>
						<li><a href="javascript:void(0)">
								<h4 class="control-sidebar-subheading">
									Back End Framework <span class="label label-primary pull-right">68%</span>
								</h4>

								<div class="progress progress-xxs">
									<div class="progress-bar progress-bar-primary"
										style="width: 68%"></div>
								</div>
						</a></li>
					</ul>
					<!-- /.control-sidebar-menu -->

				</div>
				<!-- /.tab-pane -->
				<!-- Stats tab content -->
				<div class="tab-pane" id="control-sidebar-stats-tab">Stats Tab
					Content</div>
				<!-- /.tab-pane -->
				<!-- Settings tab content -->
				<div class="tab-pane" id="control-sidebar-settings-tab">
					<form method="post">
						<h3 class="control-sidebar-heading">General Settings</h3>

						<div class="form-group">
							<label class="control-sidebar-subheading"> Report panel
								usage <input class="pull-right" checked="" type="checkbox">
							</label>

							<p>Some information about this general settings option</p>
						</div>
						<!-- /.form-group -->

						<div class="form-group">
							<label class="control-sidebar-subheading"> Allow mail
								redirect <input class="pull-right" checked="" type="checkbox">
							</label>

							<p>Other sets of options are available</p>
						</div>
						<!-- /.form-group -->

						<div class="form-group">
							<label class="control-sidebar-subheading"> Expose author
								name in posts <input class="pull-right" checked=""
								type="checkbox">
							</label>

							<p>Allow the user to show his name in blog posts</p>
						</div>
						<!-- /.form-group -->

						<h3 class="control-sidebar-heading">Chat Settings</h3>

						<div class="form-group">
							<label class="control-sidebar-subheading"> Show me as
								online <input class="pull-right" checked="" type="checkbox">
							</label>
						</div>
						<!-- /.form-group -->

						<div class="form-group">
							<label class="control-sidebar-subheading"> Turn off
								notifications <input class="pull-right" type="checkbox">
							</label>
						</div>
						<!-- /.form-group -->

						<div class="form-group">
							<label class="control-sidebar-subheading"> Delete chat
								history <a href="javascript:void(0)" class="text-red pull-right"><i
									class="fa fa-trash-o"></i></a>
							</label>
						</div>
						<!-- /.form-group -->
					</form>
				</div>
				<!-- /.tab-pane -->
			</div>
		</aside>
		<!-- /.control-sidebar-menu -->

	</div>
	<!-- /.tab-pane -->


	<!-- jQuery 3 -->
	<script src="<%=path%>bower_components/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap 3.3.7 -->
	<script
		src="<%=path%>bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- AdminLTE App -->
	<script src="<%=path%>dist/js/adminlte.min.js"></script>
	<!-- AdminLTE App -->
	<script src="<%=path%>dist/js/demo.js"></script>
	<!-- REQUIRED JS SCRIPTS -->
	<script>$(function() {
			//被引用页面的标题
			$("#header").children("h1:first").append($("#thispage").attr('title'));
			//被引用页面的内容 
			$("#content").append($("#thispage"));
			$("head").append($("#thishead"));
		})
	</script>
	<!-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. -->
</body>
</html>