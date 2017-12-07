<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/companys/public/starter.jsp"%>
<div id="thishead"></div>
<div id="thispage" title="系统设置<small>set</small>">
	<div class="col-md-6">
		<!-- general form elements -->
		<div class="box box-primary">
			<div class="box-header with-border">
				<h3 class="box-title">系统设置</h3>
			</div>
			<!-- /.box-header -->
			<!-- form start -->
			<form role="form">
				<div class="box-body">
					<div class="form-group">
						<label for="exampleInputEmail1">智能设置</label>
					</div>

					<div class="checkbox">
						<label> <input type="checkbox"> 机器人智能问答
						</label>
					</div>
					<div class="form-group">
						<label for="exampleInputEmail1">机器人默认回答内容</label> <input
							class="form-control" id="exampleInputEmail1" placeholder="默认回答内容"
							type="text">
					</div>
					<div class="checkbox">
						<label> <input type="checkbox"> 客户智能分级
						</label>

					</div>
					<div class="form-group">
						<label for="exampleInputEmail1">客户分级级数</label> <input
							class="form-control" id="exampleInputEmail1" placeholder="客户分级级数"
							type="number">
					</div>
					<div class="checkbox">
						<label> <input type="checkbox"> 客户智能排队
						</label>
					</div>
					<div class="form-group">
						<label for="exampleInputEmail1">客户排队队伍长度</label> <input
							class="form-control" id="exampleInputEmail1" placeholder="客户分级级数"
							type="number">
					</div>
					<div class="checkbox">
						<label> <input type="checkbox"> 商品智能推荐
						</label>
					</div>

					<div class="form-group">
						<label for="exampleInputEmail1">消息记录</label>
					</div>
					<div class="checkbox">
						<label> <input type="checkbox">订单确认消息
						</label>
					</div>
					<div class="checkbox">
						<label> <input type="checkbox">订单确认消息
						</label>
					</div>
					<div class="checkbox">
						<label> <input type="checkbox">客户上线消息
						</label>
					</div>
					<div class="checkbox">
						<label> <input type="checkbox">客服上线消息
						</label>
					</div>
					<div class="checkbox">
						<label> <input type="checkbox">客户等待消息
						</label>
					</div>
					<div class="checkbox">
						<label> <input type="checkbox">客户发客服消息
						</label>
					</div>
					<div class="checkbox">
						<label> <input type="checkbox">客服发客户消息
						</label>
					</div>
					<div class="checkbox">
						<label> <input type="checkbox">会话开始消息
						</label>
					</div>
					<div class="checkbox">
						<label> <input type="checkbox">会话结束消息
						</label>
					</div>

					<div class="form-group">
						<label for="exampleInputEmail1">智能报警监控</label>
					</div>
					<div class="form-group">
						<label for="exampleInputEmail1">客户等待报警时长</label> <input
							class="form-control" id="exampleInputEmail1" placeholder="客户分级级数"
							type="text">
					</div>
					<div class="form-group">
						<label for="exampleInputEmail1">满意度报警分数</label> <input
							class="form-control" id="exampleInputEmail1" placeholder="客户分级级数"
							type="text">
					</div>

				</div>
				<!-- /.box-body -->

				<div class="box-footer">
					<button type="submit" class="btn btn-primary">确认</button>
				</div>
			</form>
		</div>
		<!-- /.box -->


	</div>
</div>

<script>

</script>
