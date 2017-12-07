<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/companys/public/starter.jsp"%>
<div id="thishead">
	<!-- link -->
	<script src="<%=realpath%>/resources/echart/echarts.min.js"></script>
</div>
<div id="thispage">

	<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
	<div id="main" style="width: 1250px;height:600px;"></div>

</div>
<script>
	// 基于准备好的dom，初始化echarts实例
	var myChart = echarts.init(document.getElementById('main'));
	//app.title = '气泡图';


	//var data = [ [ 28604, 77, 17096869 ], [ 37062, 75.4, 252847810 ] ];

	//	var data = [
	//		[ [ 28604, 77, 17096869 ], [ 37062, 75.4, 252847810 ] ],
	//	];

	option = {
		backgroundColor : new echarts.graphic.RadialGradient(0.3, 0.3, 0.8, [ {
			offset : 0,
			color : '#f7f8fa'
		} ]),
		title : {
			text : '客户RFM散点图'
		},
		tooltip : {
			trigger : 'item',
			axisPointer : { // 坐标轴指示器，坐标轴触发有效
				type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
			}
		},
		legend : {
			right : 10,
			data : [ '客服RFM' ]
		},
		xAxis : {
			name:"客户最近购买金额M",
			splitLine : {
				lineStyle : {
					type : 'dashed'
				}
			}
		},
		yAxis : {
			name:"客户购买次数F",
			splitLine : {
				lineStyle : {
					type : 'dashed'
				}
			},
			scale : true
		},
		series : [ {
			name : '客户RFM',
			data : [],
			type : 'scatter',
			symbolSize : function(data) {
				//return Math.sqrt(data[2]) / 5e2;
				return Math.sqrt(data[2]) / 1;
			},
			label : {
				emphasis : {
					show : false,
					position : 'top'
				}
			},
			itemStyle : {
				normal : {
					shadowBlur : 10,
					shadowColor : 'rgba(120, 36, 50, 0.5)',
					shadowOffsetY : 5,
					color : new echarts.graphic.RadialGradient(0.4, 0.3, 1, [ {
						offset : 0,
						color : 'rgb(251, 118, 123)'
					}, {
						offset : 1,
						color : 'rgb(204, 46, 72)'
					} ])
				}
			}
		} ]
	};
	myChart.setOption(option);
	$.post('customerrfm_rfmScatter.action', {
		companyid : getCookie("companyid")
	}, function(result) {
		if (result != null) {
			var options = myChart.getOption();
			var data = [];
			for (var i = 0; i < result[0].length; i++) {
				var obj = [];
				obj.push(result[0][i].buysum);
				obj.push(result[0][i].buytimes);
				obj.push(parseInt(result[0][i].lastbuytime));
				data.push(obj);
			}
			console.log(options.series[0].data);
			console.log(Math.sqrt(17096869) / 5e2);
			console.log(Math.sqrt(17369) / 2);
			options.series[0].data = data;
			myChart.setOption(options);
		}
	});
	// 使用刚指定的配置项和数据显示图表。
</script>