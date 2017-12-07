<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/companys/public/starter.jsp"%>
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
	<script src="<%=realpath%>/resources/bootbox/msgdialog.js"></script>
	<!-- 自己封装的组件代码 -->
	<script src="<%=realpath%>/resources/bootbox/toastr.js"></script>
	<link href="<%=realpath%>/resources/bootbox/toastr.css"
		rel="stylesheet" type="text/css" />
	<!-- toastr -->
</div>
<div id="thispage" title="订单查看<small>manager</small>">
	<!-- ==================================页面内容=================================== -->

	<!-- ==================================页面内容=================================== -->

	<div class="row">
		<div class="col-xs-12">
			<div id="toolbar" class="btn-group">
				<button id="btn_finish" type="button" class="btn btn-default">
					<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>完成
				</button>
			</div>
			<table id="tb_departments"></table>
		</div>
	</div>
</div>

<script>
	//-- 后期在登陆的时候初始化
	setCookie("companyid", 1122, 1);
	setCookie("customerserviceid", 12312, 1);
	$("#cpId").attr('value', 1122);
	$(function() {
		//1.初始化table
		var oTable = new TableInit();
		oTable.Init();
		//2.初始化Button的点击事件
		var oButtonInit = new ButtonInit();
		oButtonInit.Init();
	})
	//-- ==================================执行部分部分============================================ -->
	var TableInit = function() {
		var oTableInit = new Object();
		//初始化Table
		oTableInit.Init = function() {
			$('#tb_departments').bootstrapTable({
				url : 'order_getCompanyOrder.action', //请求后台的URL（*）
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
					title : '订单号',
				}, {
					field : 'customerId',
					title : '客户ID',
				}, {
					field : 'product_id',
					title : '产品ID',
				}, {
					field : 'demandaddr',
					title : '送货地址',
				}, {
					field : 'demandtime',
					title : '截至时间',
				}, {
					field : 'demandphone',
					title : '客户电话',
				}, {
					field : 'demandotherrequest',
					title : '客户备注要求',
				}, {
					field : 'status',
					title : '工单状态',
					formatter : function(value, row, index) {
						if (value == 'finish') {
							return '已受理';
						} else {
							return '未受理';
						}
					}
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
				'company_id' : getCookie("companyid")
			//'customerserviceId' : getCookie("customerserviceid")
			};
			return temp;
		};
		return oTableInit;
	//-- ==================================toastr初始化部分============================================ -->	
	};
	var ButtonInit = function() {
		var oInit = new Object();
		var postdata = {};
		oInit.Init = function() {
			//-- ==================================添加按钮部分============================================ -->
			//注册删除按钮的事件
			$("#btn_finish").click(function() {
				//取表格的选中行数据
				var arrselections = $("#tb_departments").bootstrapTable('getSelections');
				if (arrselections.length <= 0) {
					toastr.warning('请选择有效数据');
					return;
				}
				var ids = "";
				console.log(arrselections.toString);
				for (var i = 0; i < arrselections.length; i++) {
					console.log(arrselections[i]);
					ids += arrselections[i].id + ",";
				}
				;
				ids = ids.substr(0, ids.lastIndexOf(","));
				$.ajax({
					type : "post",
					url : "order_finishByIds.action",
					data : {
						"ids" : ids
					},
					success : function(data, status) {
						if (status == "success") {
							toastr.success('完成订单成功');
							$("#tb_departments").bootstrapTable('refresh');
						}
					},
					error : function() {
						toastr.error('Error');
					},
					complete : function() {}
				});
			});
			//-- ==================================删除按钮部分============================================ -->	

		//-- ==================================表单保存按钮部分============================================ -->	
		};
		return oInit;
	};
	//-- ==================================表格初始化部分============================================ -->	
</script>