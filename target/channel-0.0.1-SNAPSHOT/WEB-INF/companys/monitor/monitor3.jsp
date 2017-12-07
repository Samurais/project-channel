<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/companys/public/starter.jsp"%>
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
<div id="thispage" title="智能监控<small>monitor</small>">
	<div class="row">
		<div class="col-lg-3 col-xs-6">
			<!-- small box -->
			<div class="small-box bg-aqua">
				<div class="inner">
					<h3 id="monitor_conversation">0</h3>

					<p>会话完成</p>
				</div>
				<div class="icon">
					<i class="ion ion-bag"></i>
				</div>
				<a href="#" class="small-box-footer">More info <i
					class="fa fa-arrow-circle-right"></i></a>
			</div>
		</div>
		<!-- ./col -->
		<div class="col-lg-3 col-xs-6">
			<!-- small box -->

			<div class="small-box bg-green">
				<div class="inner">
					<h3 id="monitor_degree">
						0<sup style="font-size: 20px">%</sup>
					</h3>

					<p>满意指数</p>
				</div>
				<div class="icon">
					<i class="ion ion-stats-bars"></i>
				</div>
				<a href="#" class="small-box-footer">More info <i
					class="fa fa-arrow-circle-right"></i></a>
			</div>
		</div>
		<!-- ./col -->
		<div class="col-lg-3 col-xs-6">
			<!-- small box -->
			<div class="small-box bg-yellow">
				<div class="inner">
					<h3 id="monitor_customer">0</h3>

					<p>新增客戶</p>
				</div>
				<div class="icon">
					<i class="ion ion-person-add"></i>
				</div>
				<a href="#" class="small-box-footer">More info <i
					class="fa fa-arrow-circle-right"></i></a>
			</div>
		</div>
		<!-- ./col -->
		<div class="col-lg-3 col-xs-6">
			<!-- small box -->
			<div class="small-box bg-red">
				<div class="inner">
					<h3 id="monitor_danger">0</h3>

					<p>报警次数</p>
				</div>
				<div class="icon">
					<i class="ion ion-pie-graph"></i>
				</div>
				<a href="#" class="small-box-footer">More info <i
					class="fa fa-arrow-circle-right"></i></a>
			</div>
		</div>
		<!-- ./col -->
	</div>
	<!-- /.row -->

	<div class="row">
		<div class="col-xs-12">
			<div class="row">
				<table id="tb_departments"></table>
			</div>
		</div>
	</div>
	<!--/.row-->
</div>

<!-- AdminLTE App -->
<script src="<%=path%>dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="<%=path%>dist/js/demo.js"></script>
<script>
	$(function() {
		//1.初始化table
		var oTable = new TableInit();
		oTable.Init();
	})
	//-- ==================================执行部分部分============================================ -->
	var TableInit = function() {
		var oTableInit = new Object();
		//初始化Table
		oTableInit.Init = function() {
			$('#tb_departments').bootstrapTable({
				url : 'monitor_getCustomerState.action', //请求后台的URL（*）
				contentType : "application/x-www-form-urlencoded; charset=UTF-8",
				method : 'post', //请求方式（*）
				//toolbar : '#toolbar', //工具按钮用哪个容器
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
				//strictSearch : true,
				showColumns : true, //是否显示所有的列
				showRefresh : true, //是否显示刷新按钮
				minimumCountColumns : 2, //最少允许的列数
				clickToSelect : true, //是否启用点击选中行
				height : 500, //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
				uniqueId : "id", //每一行的唯一标识，一般为主键列
				showToggle : true, //是否显示详细视图和列表视图的切换按钮
				cardView : false, //是否显示详细视图
				detailView : false, //是否显示父子表
				showExport : true, //是否显示导出
				exportDataType : "basic", //全部导出
				columns : [ {
					checkbox : true
				}, {
					field : 'id',
					title : '会话编号',
				}, {
					field : 'currentservice',
					title : '当前会话客户',
				}, {
					field : 'haveservice',
					title : '已完成会话量',
				}, {
					field : 'isonline',
					title : '在线状态',
					formatter : function(value, row, index) {
						if (value == true) {
							return '<i class="fa fa-circle text-success"></i>' + "在线";
						} else {
							return '<i class="fa fa-circle text-danger"></i>' + "离线";
						}
					}
				}, {
					field : 'firstlandtime',
					title : '第一次登陆时间',
				} ],
				onClickRow : function(row, $element) {
					curRow = row;
				}
			});
		};
		oTableInit.queryParams = function(params) {
			var temp = { //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
				'limit' : params.limit, //页面大小
				'offset' : params.offset, //页码
				//'search' : params.search, //搜索功能
				'cpId' : getCookie("companyid")
			};
			return temp;
		};
		return oTableInit;
	};
	//每十秒刷新一次
	$(function() {
		$.post("monitor_getCompanyMonitor.action", {
			cpId : getCookie("companyid"),
		}, function(result) {
			console.log(result.date.conversation);
			$("#monitor_conversation").text(result.date.conversation);
			$("#monitor_degree").html(result.date.degree + '<sup style="font-size: 20px">%</sup>');
			$("#monitor_customer").text(result.date.newcustomer);
			$("#monitor_danger").text(result.date.danger);
		});
		$('#tb_departments').bootstrapTable("refresh");
		function show() {
			$.post("monitor_getCompanyMonitor.action", {
				cpId : getCookie("companyid"),
			}, function(result) {
				console.log(result.date.conversation);
				$("#monitor_conversation").text(result.date.conversation);
				$("#monitor_degree").html(result.date.degree + '<sup style="font-size: 20px">%</sup>');
				$("#monitor_customer").text(result.date.newcustomer);
				$("#monitor_danger").text(result.date.danger);
			});
			$('#tb_departments').bootstrapTable("refresh");

		}
		setInterval(show, 10000);
	});
</script>