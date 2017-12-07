<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/services/public/starter.jsp"%>
<div id="thishead">
	<script
		src="<%=realpath%>/resources/bootstrap-table/bootstrap-table.js">
	</script>
	<link
		href="<%=realpath%>/resources/bootstrap-table/bootstrap-table.css"
		rel="stylesheet" />
	<script
		src="<%=realpath%>/resources/bootstrap-table/bootstrap-table-zh-CN.js"></script>
	<!-- /bootstrap table -->
</div>
<div id="thispage" title="留言查看<small>leavingmsg</small>">
	<div class="row">
		<div class="box box-success">
			<div class="box-header ui-sortable-handle" style="cursor: move;">
				<i class="fa fa-comments-o"></i>

				<h3 class="box-title">留言列表</h3>

				<div class="box-tools pull-right" data-toggle="tooltip" title=""
					data-original-title="Status">
					<div class="btn-group" data-toggle="btn-toggle">
						<button type="button" class="btn btn-default btn-sm active">
							<i class="fa fa-square text-green"></i>
						</button>
						<button type="button" class="btn btn-default btn-sm">
							<i class="fa fa-square text-red"></i>
						</button>
					</div>
				</div>
			</div>
			<div class="slimScrollDiv"
				style="position: relative; overflow: hidden; width: auto; height: 250px;">
				<div class="box-body chat" id="chat-box"
					style="overflow: hidden; width: auto; height: 250px;">
					<!-- chat item -->
					<div class="item">
						<img src="<%=path%>dist/img/user1-128x128.jpg" alt="user image"
							class="online">

						<p class="message">
							<a href="#" class="name"> <small
								class="text-muted pull-right"><i class="fa fa-clock-o"></i>
									2:15</small> 客户123123
							</a> 我买的那个产品出了点问题,请尽快联系我
						</p>
						<div class="attachment">
							<h4>回复</h4>

							<p class="filename">好的,我们已经收到</p>

							<div class="pull-right">
								<button type="button" class="btn btn-primary btn-sm btn-flat">回复</button>
							</div>
						</div>
						<!-- /.attachment -->
					</div>
					<!-- /.item -->
					<!-- chat item -->
					<div class="item">
						<img src="<%=path%>dist/img/user4-128x128.jpg" alt="user image"
							class="offline">

						<p class="message">
							<a href="#" class="name"> <small
								class="text-muted pull-right"><i class="fa fa-clock-o"></i>
									5:15</small> 客户66332
							</a> 我想请问那个产品要怎么使用
						</p>
					</div>
					<!-- /.item -->
					<!-- chat item -->
					<div class="item">
						<img src="<%=path%>dist/img/user3-128x128.jpg" alt="user image"
							class="offline">

						<p class="message">
							<a href="#" class="name"> <small
								class="text-muted pull-right"><i class="fa fa-clock-o"></i>
									5:30</small> 客户78923
							</a>你们的工作人员服务态度真是太好拉!
						</p>
					</div>
					<!-- /.item -->
				</div>
				<div class="slimScrollBar"
					style="background: rgb(0, 0, 0) none repeat scroll 0% 0%; width: 7px; position: absolute; top: 0px; opacity: 0.4; display: none; border-radius: 7px; z-index: 99; right: 1px; height: 187.688px;"></div>
				<div class="slimScrollRail"
					style="width: 7px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; background: rgb(51, 51, 51) none repeat scroll 0% 0%; opacity: 0.2; z-index: 90; right: 1px;"></div>
			</div>
			<!-- /.chat -->
			<div class="box-footer">
				<div class="input-group">
					<input class="form-control" placeholder="Type message...">

					<div class="input-group-btn">
						<button type="button" class="btn btn-success">
							<i class="fa fa-plus"></i>
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- /.row -->

	<div class="row"></div>
	<!--/.row-->
</div>

<!-- AdminLTE App -->
<script src="<%=path%>dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="<%=path%>dist/js/demo.js"></script>
<script>
</script>