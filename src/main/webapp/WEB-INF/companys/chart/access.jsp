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
		title : {
			text : '用户来源',
			x : 'center'
		},
		tooltip : {
			trigger : 'item',
			formatter : "{a} <br/>{b} : {c} ({d}%)"
		},
		legend : {
			orient : 'vertical',
			left : 'left',
			data : [ 'Web端', '微信端', 'QQ端', '微博端' ]
		},
		series : [
			{
				name : '访问来源',
				type : 'pie',
				radius : '55%',
				center : [ '50%', '60%' ],
				data : [
					{
						value : 335,
						name : 'Web端'
					},
					{
						value : 310,
						name : '微信端'
					},
					{
						value : 0,
						name : 'QQ端'
					},
					{
						value : 0,
						name : '微博端'
					},
				],
				itemStyle : {
					emphasis : {
						shadowBlur : 10,
						shadowOffsetX : 0,
						shadowColor : 'rgba(0, 0, 0, 0.5)'
					}
				}
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