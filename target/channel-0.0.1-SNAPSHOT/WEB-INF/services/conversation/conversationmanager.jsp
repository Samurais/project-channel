<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/services/public/starter.jsp"%>
<!-- ==================================头文件内容============================================ -->
<div id="thishead">

	<script
		src="<%=realpath%>/resources/bootstrap-table/bootstrap-table.js"></script>
	<link
		href="<%=realpath%>/resources/bootstrap-table/bootstrap-table.css"
		rel="stylesheet" />
	<script
		src="<%=realpath%>/resources/bootstrap-table/bootstrap-table-zh-CN.js"></script>
	<!-- /bootstrap table -->
</div>
<div id="thispage" title="历史会话查看<small>manager</small>">
	<!-- ==================================页面内容=================================== -->

	<div class="row">
		<table id="tb_departments"></table>
	</div>
</div>

<script>
	//-- 后期在登陆的时候初始化
	setCookie("companyid", 1122, 1);
	setCookie("customerserviceid",12312,1);
	$("#cpId").attr('value', 1122);
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
				url : 'conversation_pageQueryByService.action', //请求后台的URL（*）
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
				search : true, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
				searchOnEnterKey : true, //设置为 true时，按回车触发搜索方法，否则自动触发搜索方法
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
				showExport : true, //是否显示导出
				exportDataType : "basic", //全部导出
				columns : [ {
					checkbox : true
				}, {
					field : 'id',
					title : '会话编号',
				}, {
					field : 'begintime',
					title : '开始时间',
				}, {
					field : 'endtime',
					title : '结束时间',
				}, {
					field : 'firstkeyword',
					title : '会话主要关键字',
				}, {
					field : 'keyword',
					title : '会话关键字',
				}, {
					field : 'degree',
					title : '满意度',
				}, {
					field : 'customer_id',
					title : '客户ID',
				}, {
					field : 'customerservice_id',
					title : '客服ID',
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
				'search' : params.search, //搜索功能
				'customerservice_id' : getCookie("customerserviceid")
			};
			return temp;
		};
		return oTableInit;
	};
	//-- ==================================表格初始化部分============================================ -->	
</script>