<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/administrator/public/starter.jsp"%>
<div id="thishead">
	<!-- link -->
	<script src="<%=realpath%>/resources/echart/echarts.min.js"></script>
</div>
<div id="thispage">

	<div class="row">
		<div class="col-md-12">
			<div id="main" style="width: 1250px;height:600px;"></div>
		</div>
	</div>
</div>
<script>
	//app.title = '坐标轴刻度与标签对齐';
	var myChart = echarts.init(document.getElementById('main'));
	option = {
		color : [ '#3398DB' ],
		tooltip : {
			trigger : 'axis',
			axisPointer : { // 坐标轴指示器，坐标轴触发有效
				type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
			}
		},
		grid : {
			left : '3%',
			right : '4%',
			bottom : '3%',
			containLabel : true
		},
		xAxis : [
			{
				type : 'category',
				data : [ 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun' ],
				axisTick : {
					alignWithLabel : true
				}
			}
		],
		yAxis : [
			{
				type : 'value'
			}
		],
		series : [
			{
				name : '直接访问',
				type : 'bar',
				barWidth : '60%',
				data : [ 10, 52, 200, 334, 390, 330, 220 ]
			}
		]
	};

	myChart.setOption(option);
	$.post('administrator_getCompanyConversationCount.action', function(result) {
		if (result != null) {
			var options = myChart.getOption();
			var xAxisarry = [];
			var seriesarry = [];
			for (var i = 0; i < result.length; i++) {
				xAxisarry.push(result[i].name);
				seriesarry.push(result[i].value);
			}
			//options.dataRange.max=max;
			options.series[0].data = seriesarry;
			options.xAxis[0].data = xAxisarry;
			myChart.hideLoading();
			myChart.setOption(options);
		}
	});
</script>
</script>