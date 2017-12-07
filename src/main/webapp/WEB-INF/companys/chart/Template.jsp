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

	// 指定图表的配置项和数据
	var option = {
		title : {
			text : 'ECharts 入门示例'
		},
		tooltip : {},
		legend : {
			data : [ '销量' ]
		},
		xAxis : {
			data : [ "衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子" ]
		},
		yAxis : {},
		series : [ {
			name : '销量',
			type : 'bar',
			data : [ 5, 20, 36, 10, 10, 20 ]
		} ]
	};
	
	var option2 = {
		    xAxis: {
		        type: 'value'
		    },
		    yAxis: {
		        type: 'value'
		    },
		    dataZoom: [
		        {   // 这个dataZoom组件，默认控制x轴。
		            type: 'slider', // 这个 dataZoom 组件是 slider 型 dataZoom 组件
		            start: 10,      // 左边在 10% 的位置。
		            end: 60         // 右边在 60% 的位置。
		        }
		    ],
		    series: [
		        {
		            type: 'scatter', // 这是个『散点图』
		            itemStyle: {
		                normal: {
		                    opacity: 0.8
		                }
		            },
		            symbolSize: function (val) {
		                return val[2] * 40;
		            },
		            data: [["14.616","7.241","0.896"],["3.958","5.701","0.955"],["2.768","8.971","0.669"],["9.051","9.710","0.171"],["14.046","4.182","0.536"],["12.295","1.429","0.962"],["4.417","8.167","0.113"],["0.492","4.771","0.785"],["7.632","2.605","0.645"],["14.242","5.042","0.368"]]
		        }
		    ]
		}

	// 使用刚指定的配置项和数据显示图表。
	myChart.setOption(option2);
	
	
</script>