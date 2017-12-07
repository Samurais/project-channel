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

	<link href="<%=realpath%>/resources/x-editable/bootstrap-editable.css"
		rel="stylesheet" />
	<script src="<%=realpath%>/resources/x-editable/bootstrap-editable.js"></script>
	<!-- /bootstrap table editable-->
	<script
		src="<%=realpath%>/resources/bootstrap-table/bootstrap-table-editable.js"></script>
	<!-- /bootstrap table editable-->
	<script src="<%=realpath%>/resources/bootstrap-table/moment.min.js"></script>
	<!-- /bootstrap table editable combodate-->
	<script src="<%=realpath%>/resources/bootbox/msgdialog.js"></script>
	<!-- 自己封装的组件代码 -->
	<script src="<%=realpath%>/resources/bootbox/toastr.js"></script>
	<link href="<%=realpath%>/resources/bootbox/toastr.css"
		rel="stylesheet" type="text/css" />
	<!-- toastr -->
	<script src="<%=realpath%>/resources/bootbox/bootstrapValidator.min.js"></script>
	<link href="<%=realpath%>/resources/bootbox/bootstrapValidator.min.css"
		rel="stylesheet" type="text/css" />
	<!-- bootstrapValidator -->
</div>
<div id="thispage" title="客服管理<small>manager</small>">
	<!-- ==================================页面内容=================================== -->

	<div class="row">
		<div class="col-xs-12">
			<div id="toolbar" class="btn-group">
				<button id="btn_add" type="button" class="btn btn-default">
					<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
				</button>
				<button id="btn_edit" type="button" class="btn btn-default">
					<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
				</button>
				<button id="btn_delete" type="button" class="btn btn-default">
					<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
				</button>
			</div>
			<table id="tb_departments"></table>
		</div>
	</div>
	<!-- ==================================表格和按钮部分================================ -->
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
					<!-- ===========================表单部分============================= -->
					<form id="defaultForm" method="post" class="form-horizontal"
						action="customerservice_save.action">
						<div class="form-group">
							<label class="col-lg-3 control-label">客服账号</label>
							<div class="col-lg-5">
								<input type="text" class="form-control" name="csId" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-3 control-label">客服密码</label>
							<div class="col-lg-5">
								<input type="password" class="form-control" name="csPassword" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-3 control-label">用户名</label>
							<div class="col-lg-5">
								<input type="text" class="form-control" name="csUsername" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-3 control-label">客服姓名</label>
							<div class="col-lg-5">
								<input type="text" class="form-control" name="csName" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-3 control-label">客服电话</label>
							<div class="col-lg-5">
								<input type="text" class="form-control" name="csPhone" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-3 control-label">性别</label>
							<div class="col-lg-5">
								<div class="radio">
									<label> <input type="radio" name="csSex" value="男"
										required data-bv-notempty-message="The gender is required" />
										男
									</label>
								</div>
								<div class="radio">
									<label> <input type="radio" name="csSex" value="女" />
										女
									</label>
								</div>
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-3 control-label">客服窗口数</label>
							<div class="col-lg-5">
								<input type="number" class="form-control" name="csWindow" />
							</div>
						</div>
						<div class="form-group" hidden="hidden">
							<label class="col-lg-3 control-label">公司</label>
							<div class="col-lg-5">
								<input type="number" id="cpId" class="form-control"
									name="companyid" value="" />
							</div>
						</div>

						<div class="form-group">
							<div class="col-lg-9 col-lg-offset-3">
								<button type="submit" class="btn btn-primary">注册</button>
							</div>
						</div>
					</form>
					<!-- ===========================表单结束部分============================= -->
				</div>
			</div>
		</div>
		<!-- ==================================弹出窗口部分================================= -->
	</div>
</div>
<script>
	//-- ==================================数据初始化部分============================================ -->
	//-- 后期在登陆的时候初始化
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
				url : 'role_getServiceRole.action', //请求后台的URL（*）
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
					title : '用户ID',
					disabled : true
				}, {
					field : 'username',
					title : '用户名称',
					disabled : true,
				}, {
					field : 'registtime',
					title : '注册时间',
				}, {
					field : 'role',
					title : '用户角色',
					editable : {
						type : 'select',
						title : '用户角色',
						source : function() {
							var thisresult = [];
							$.post('role_getTotalRole.action', {
								companyid : getCookie("companyid")
							},
								function(result) {
									console.log(result);
									for (var i = 0; i < result.length; i++) {
										console.log(result[i].value.toString());
										console.log(result[i].text);
										thisresult.push({
											value : result[i].value.toString(),
											text : result[i].text,
										})
									}
								}
							);
							console.log(thisresult.toString());
							return thisresult;
						},
						disabled : true
					}
				} ],
				onClickRow : function(row, $element) {
					curRow = row;
				},
				onEditableSave : function(field, row, oldValue, $el) {
					$("#tb_departments").bootstrapTable("resetView");
					$.ajax({
						type : "post",
						url : "customerservice_editUpdate.action",
						data : {
							//strJson : JSON.stringify(row),
							'id' : row.id,
							'username' : row.username,
							'registtime' : row.registtime,
							'role' : row.role,
							'companyid' : getCookie("companyid"),
						},
						success : function(data, status) {
							if (status == "success") {
								toastr.success('编辑成功');
							}
						},
						error : function() {
							toastr.warning('编辑失败');
						},
						complete : function() {}
					});
				}
			});
		};
		oTableInit.queryParams = function(params) {
			var temp = { //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
				'limit' : params.limit, //页面大小
				'offset' : params.offset, //页码
				'search' : params.search, //搜索功能
				'companyid' : getCookie("companyid")
			};
			return temp;
		};
		return oTableInit;
	};
	//-- ==================================表格初始化部分============================================ -->	
	toastr.options = {
		"closeButton" : false, //是否显示关闭按钮
		"debug" : false, //是否使用debug模式
		"positionClass" : "toast-top-full-width", //弹出窗的位置
		"showDuration" : "300", //显示的动画时间
		"hideDuration" : "1000", //消失的动画时间
		"timeOut" : "5000", //展现时间
		"extendedTimeOut" : "1000", //加长展示时间
		"showEasing" : "swing", //显示时的动画缓冲方式
		"hideEasing" : "linear", //消失时的动画缓冲方式
		"showMethod" : "fadeIn", //显示时的动画方式
		"hideMethod" : "fadeOut" //消失时的动画方式
	};
	//-- ==================================toastr初始化部分============================================ -->	
	var ButtonInit = function() {
		var oInit = new Object();
		var postdata = {};
		oInit.Init = function() {
			$("#btn_edit").on('click', function() {
				$("tbody").find(".editable").editable('toggleDisabled');
			});
			//-- ==================================编辑按钮部分============================================ -->
			$("#btn_add").on('click', function() {
				$("#myModalLabel").text("新增");
				$('#myModal').modal();
			});
			//-- ==================================添加按钮部分============================================ -->
			//注册删除按钮的事件
			$("#btn_delete").click(function() {
				//取表格的选中行数据
				var arrselections = $("#tb_departments").bootstrapTable('getSelections');
				if (arrselections.length <= 0) {
					toastr.warning('请选择有效数据');
					return;
				}
				Ewin.confirm({
					message : "确认要删除选择的数据吗？"
				}).on(function(e) {
					if (!e) {
						return;
					}
					var ids = "";
					console.log(arrselections.toString);
					for (var i = 0; i < arrselections.length; i++) {
						console.log(arrselections[i]);
						ids += arrselections[i].csId + ",";
					}
					;
					ids = ids.substr(0, ids.lastIndexOf(","));
					$.ajax({
						type : "post",
						url : "customerservice_deleteByIds.action",
						data : {
							"ids" : ids
						},
						success : function(data, status) {
							if (status == "success") {
								toastr.success('删除数据成功');
								$("#tb_departments").bootstrapTable('refresh');
							}
						},
						error : function() {
							toastr.error('Error');
						},
						complete : function() {}
					});
				});
			});
			//-- ==================================删除按钮部分============================================ -->	
			$("#btn_submit").click(function() {
				$.ajax({
					type : "post",
					url : "customerservice_save.action",
					data : {
						"csId" : $("#txt_userid").val(),
						"csPassword" : $("#txt_password").val(),
						'csUsername' : $("#txt_username").val(),
						'csName' : $("#txt_name").val(),
						'csPhone' : $("#txt_phone").val(),
						'csSex' : $("#txt_sex").val(),
						'companyid' : getCookie("companyid"),
					},
					success : function(data, status) {
						if (status == "success") {
							toastr.success('提交数据成功');
							$("#tb_departments").bootstrapTable('refresh');
						}
						clearForm($('#myModal'));
					},
					error : function() {
						toastr.error('Error');
					},
					complete : function() {}
				});
			})
		//-- ==================================表单保存按钮部分============================================ -->	
		};
		return oInit;
	};
	//-- ==================================按钮初始化部分============================================ -->
	$(document).ready(function() {
		$('#defaultForm')
			.bootstrapValidator({
				message : '验证失败',
				feedbackIcons : {
					valid : 'glyphicon glyphicon-ok',
					invalid : 'glyphicon glyphicon-remove',
					validating : 'glyphicon glyphicon-refresh'
				},
				fields : {
					csId : {
						message : '客服ID验证失败',
						validators : {
							notEmpty : {
								message : 'ID不能为空'
							},
							stringLength : {
								min : 6,
								max : 30,
								message : '客服ID至少6位'
							},
							remote : {
								url : 'customerservice_idValid.action',
								message : '客服ID已经存在'
							},
						}
					},
					csPassword : {
						validators : {
							stringLength : {
								min : 6,
								max : 30,
								message : '密码至少6位'
							},
							notEmpty : {
								message : 'The password is required and can\'t be empty'
							}
						}
					},
					csWindow : {
						validators : {
							stringLength : {
								min : 1,
								max : 5,
								message : '客服窗口数目只能1到5个'
							},
							notEmpty : {
								message : 'The password is required and can\'t be empty'
							}
						}
					}
				}
			})
			.on('success.form.bv', function(e) {
				// Prevent form submission
				e.preventDefault();

				// Get the form instance
				var $form = $(e.target);

				// Get the BootstrapValidator instance
				var bv = $form.data('bootstrapValidator');

				// Use Ajax to submit form data
				$.post($form.attr('action'), $form.serialize(), function(result) {
					toastr.success('提交数据成功');
					$('#myModal').modal('hide');
					$("#tb_departments").bootstrapTable('refresh');
				});
			});
		//表单重置
		$('#myModal').on('hidden.bs.modal', function() {
			$("#defaultForm").data('bootstrapValidator').destroy();
			$('#defaultForm').data('bootstrapValidator', null);
			clearForm('#myModal');
		//formValidator();
		});

		function clearForm(form) {
			//input清空
			$(':input', form).each(function() {
				var type = this.type;
				var tag = this.tagName.toLowerCase(); // normalize case
				if (type == 'text' || type == 'password' || tag == 'textarea')
					this.value = "";
				// 多选checkboxes清空
				// select 下拉框清空
				else if (tag == 'select')
					this.selectedIndex = -1;
			});
		}
		;
	});
	//-- ==================================表单验证部分============================================ -->
</script>