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
//基于准备好的dom，初始化echarts实例
	var myChart = echarts.init(document.getElementById('main'));


	var hours = [ '12a', '1a', '2a', '3a', '4a', '5a', '6a',
		'7a', '8a', '9a', '10a', '11a',
		'12p', '1p', '2p', '3p', '4p', '5p',
		'6p', '7p', '8p', '9p', '10p', '11p' ];
	var days = [ '星期一', '星期二', '星期三',
		'星期四', '星期五', '星期六', '星期天' ];

	var data = [ [ 0, 2, 3 ], [ 0, 1, 1 ], [ 4, 2, 1 ], [ 0, 2, 0 ], [ 2, 10, 3 ], [ 3, 11, 2 ], [ 2, 12, 1 ], [ 2, 13, 9 ], [ 2, 14, 8 ], [ 2, 15, 10 ], [ 2, 16, 6 ], [ 2, 17, 5 ], [ 2, 18, 5 ], [ 2, 19, 5 ], [ 2, 20, 7 ], [ 2, 21, 4 ], [ 2, 22, 2 ], [ 2, 23, 4 ], [ 3, 0, 7 ], [ 3, 1, 3 ], [ 3, 2, 0 ], [ 3, 3, 0 ], [ 3, 4, 0 ], [ 3, 5, 0 ], [ 3, 6, 0 ], [ 3, 7, 0 ], [ 3, 8, 1 ], [ 3, 9, 0 ], [ 3, 10, 5 ], [ 3, 11, 4 ], [ 3, 12, 7 ], [ 3, 13, 14 ], [ 3, 14, 13 ], [ 3, 15, 12 ], [ 3, 16, 9 ], [ 3, 17, 5 ], [ 3, 18, 5 ], [ 3, 19, 10 ], [ 3, 20, 6 ], [ 3, 21, 4 ], [ 3, 22, 4 ], [ 3, 23, 1 ], [ 4, 0, 1 ], [ 4, 1, 3 ], [ 4, 2, 0 ], [ 4, 3, 0 ], [ 4, 4, 0 ], [ 4, 5, 1 ], [ 4, 6, 0 ], [ 4, 7, 0 ], [ 4, 8, 0 ], [ 4, 9, 2 ], [ 4, 10, 4 ], [ 4, 11, 4 ], [ 4, 12, 2 ], [ 4, 13, 4 ], [ 4, 14, 4 ], [ 4, 15, 14 ], [ 4, 16, 12 ], [ 4, 17, 1 ], [ 4, 18, 8 ], [ 4, 19, 5 ], [ 4, 20, 3 ], [ 4, 21, 7 ], [ 4, 22, 3 ], [ 4, 23, 0 ], [ 5, 0, 2 ], [ 5, 1, 1 ], [ 5, 2, 0 ], [ 5, 3, 3 ], [ 5, 4, 0 ], [ 5, 5, 0 ], [ 5, 6, 0 ], [ 5, 7, 0 ], [ 5, 8, 2 ], [ 5, 9, 0 ], [ 5, 10, 4 ], [ 5, 11, 1 ], [ 5, 12, 5 ], [ 5, 13, 10 ], [ 5, 14, 5 ], [ 5, 15, 7 ], [ 5, 16, 11 ], [ 5, 17, 6 ], [ 5, 18, 0 ], [ 5, 19, 5 ], [ 5, 20, 3 ], [ 5, 21, 4 ], [ 5, 22, 2 ], [ 5, 23, 0 ], [ 6, 0, 1 ], [ 6, 1, 0 ], [ 6, 2, 0 ], [ 6, 3, 0 ], [ 6, 4, 0 ], [ 6, 5, 0 ], [ 6, 6, 0 ], [ 6, 7, 0 ], [ 6, 8, 0 ], [ 6, 9, 0 ], [ 6, 10, 1 ], [ 6, 11, 0 ], [ 6, 12, 2 ], [ 6, 13, 1 ], [ 6, 14, 3 ], [ 6, 15, 4 ], [ 6, 16, 0 ], [ 6, 17, 0 ], [ 6, 18, 0 ], [ 6, 19, 0 ], [ 6, 20, 1 ], [ 6, 21, 2 ], [ 6, 22, 2 ], [ 6, 23, 6 ] ];
	data = data.map(function(item) {
		return [ item[1], item[0], item[2] ];
	});

	option = {
		title : {
			text : '客服上星期满意度报表',
			link : 'https://github.com/pissang/echarts-next/graphs/punch-card'
		},
		legend : {
			data : [ 'Punch Card' ],
			left : 'right'
		},
		tooltip : {
			position : 'top',
			formatter : function(params) {
				return params.value[2] + ' commits in ' + hours[params.value[0]] + ' of ' + days[params.value[1]];
			}
		},
		grid : {
			left : 2,
			bottom : 10,
			right : 10,
			containLabel : true
		},
		xAxis : {
			type : 'category',
			data : hours,
			boundaryGap : false,
			splitLine : {
				show : true,
				lineStyle : {
					color : '#999',
					type : 'dashed'
				}
			},
			axisLine : {
				show : false
			}
		},
		yAxis : {
			type : 'category',
			data : days,
			axisLine : {
				show : false
			}
		},
		series : [ {
			name : 'Punch Card',
			type : 'scatter',
			symbolSize : function(val) {
				return val[2] * 2;
			},
			data : data,
			animationDelay : function(idx) {
				return idx * 5;
			}
		} ]
	};
	// 使用刚指定的配置项和数据显示图表。
	myChart.setOption(option);
	
</script>