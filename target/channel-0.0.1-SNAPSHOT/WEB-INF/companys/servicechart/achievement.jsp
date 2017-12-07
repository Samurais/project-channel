<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/companys/public/starter.jsp"%>
<div id="thishead">
	<!-- link -->
	<script src="<%=realpath%>/resources/echart/echarts.min.js"></script>
</div>
<div id="thispage">

	<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
	<div id="main" style="width: 1200px;height:600px;"></div>
	
</div>
<script>
	// 基于准备好的dom，初始化echarts实例
	var myChart = echarts.init(document.getElementById('main'));

	option = {
			tooltip : {
				trigger : 'axis',
				axisPointer : {
					type : 'cross',
					crossStyle : {
						color : '#999'
					}
				}
			},
			toolbox : {
				feature : {
					dataView : {
						show : true,
						readOnly : false
					},
					magicType : {
						show : true,
						type : [ 'line', 'bar' ]
					},
					restore : {
						show : true
					},
					saveAsImage : {
						show : true
					}
				}
			},
			legend : {
				data : [ '订单数量', '会话数量', '访问数量' ]
			},
			xAxis : [
				{
					type : 'category',
					data : [ '1-3日', '4-6日', '7-9日', '10-12日', '13-15日', '16-18日', '19-21日', '22-24日', '25-27日', '28-30日'],
					axisPointer : {
						type : 'shadow'
					}
				}
			],
			yAxis : [
				{
					type : 'value',
					name : '订单数量',
					min : 0,
					max : 250,
					interval : 50,
					axisLabel : {
						formatter : '{value} 个'
					}
				},
				{
					type : 'value',
					name : '会话数量',
					min : 0,
					max : 25,
					interval : 5,
					axisLabel : {
						formatter : '{value} 次'
					}
				}
			],
			series : [
				{
					name : '订单数量',
					type : 'bar',
					data : [ 2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0 ]
				},
				{
					name : '会话数量',
					type : 'bar',
					data : [ 2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8]
				},
				{
					name : '访问数量',
					type : 'line',
					yAxisIndex : 1,
					data : [ 2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5]
				}
			]
		};

		myChart.setOption(option);

		//	setInterval(function () {

		for (var i = 0; i < 5; i++) {
			data.shift();
			data.push(randomData());
		}

	// 使用刚指定的配置项和数据显示图表。
	myChart.setOption(option2);
	
	
</script>