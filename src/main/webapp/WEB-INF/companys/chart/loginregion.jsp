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

	var chart = echarts.init(document.getElementById('map'));
	chart.setOption({
		series : [ {
			type : 'map',
			map : 'china'
		} ]
	});
	option = {
		title : {
			text : '访客地区来源分析',
			subtext : '',
			x : 'center'
		},
		tooltip : {
			trigger : 'item'
		},
		legend : {
			orient : 'vertical',
			x : 'left',
			data : [ '访客地图' ]
		},
		dataRange : {
			min : 0,
			max : 2500,
			x : 'left',
			y : 'bottom',
			text : [ '高', '低' ], // 文本，默认为数值文本
			calculable : true
		},
		toolbox : {
			show : true,
			orient : 'vertical',
			x : 'right',
			y : 'center',
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		roamController : {
			show : true,
			x : 'right',
			mapTypeControl : {
				'china' : true
			}
		},
		series : [
			{
				name : '访客地图',
				type : 'map',
				mapType : 'china',
				roam : false,
				itemStyle : {
					normal : {
						label : {
							show : true
						}
					},
					emphasis : {
						label : {
							show : true
						}
					}
				},
				data : [],
			},
		]
	};

	chart.setOption(option);
	$.post('customerloginrecord_getVisitregion.action', {
		companyid : getCookie("companyid")
	}, function(result) {
		if (result != null) {
			var options = chart.getOption();    
            var arry=[]; 
            var max = 0;
            for(var i=0;i<result.length;i++){  
                thisname = result[i].name;
            	thisvalue = result[i].value;
            	if(thisname.indexOf("省")!=-1){
            		thisname = thisname.substring(0,thisname.indexOf("省"));
            	}
            	var item={
                    name:thisname,   
                    value:thisvalue,
                } 
                if(result[i].value>max){
                	max = result[i].value;
                }
                arry.push(item);
            }  
            options.dataRange.max=max;
            options.series[0].data=arry;  
            chart.hideLoading();    
            chart.setOption(options);   
		}
	});
</script>