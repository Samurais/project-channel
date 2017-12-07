<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/companys/public/starter.jsp"%>
<div id="thishead">
	<!-- link -->
	<script src="<%=realpath%>/resources/echart/echarts.min.js"></script>
	<script src="<%=realpath%>/resources/echart/china.js"></script>
</div>
<div id="thispage">

	<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
	<div id="map" style="width: 1200px;height:600px;"></div>

</div>
<script>
// JS

	var myChart = echarts.init(document.getElementById('map'));


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
			data : [ '访问量', '会话量', '窗口数' ]
		},
		xAxis : [
			{
				type : 'category',
				data : [ '0:00', '2:00', '4:00', '6:00', '8:00', '10:00', '12:00', '14:00', '16:00', '18:00', '20:00', '22:00' ],
				axisPointer : {
					type : 'shadow'
				}
			}
		],
		yAxis : [
			{
				type : 'value',
				name : '访问量',
				min : 0,
				max : 250,
				interval : 50,
				axisLabel : {
					formatter : '{value} 次'
				}
			},
			{
				type : 'value',
				name : '会话量',
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
				name : '访问量',
				type : 'bar',
				data : [ 2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3 ]
			},
			{
				name : '会话量',
				type : 'bar',
				data : [ 2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3 ]
			},
			{
				name : '客服窗口',
				type : 'line',
				yAxisIndex : 1,
				data : [ 2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2 ]
			}
		]
	};


	myChart.setOption(option);

	//	setInterval(function () {

	for (var i = 0; i < 5; i++) {
		data.shift();
		data.push(randomData());
	}

	myChart.setOption({
		series : [ {
			data : data
		} ]
	});
	//	}, 1000);
</script>