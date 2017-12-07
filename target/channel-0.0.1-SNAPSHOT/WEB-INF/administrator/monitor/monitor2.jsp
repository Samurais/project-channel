<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/administrator/public/starter.jsp"%>
<div id="thishead">
	<!-- link -->
	<script src="<%=realpath%>/resources/echart/echarts.min.js"></script>
</div>
<div id="thispage">
	<div class="row">
		<div class="col-md-6">
			<div class="box box-solid">
				<div class="box-header with-border">
					<h3 class="box-title">Carousel</h3>
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<div id="carousel-example-generic" class="carousel slide"
						data-ride="carousel">
						<ol class="carousel-indicators">
							<li data-target="#carousel-example-generic" data-slide-to="0"
								class="active"></li>
							<li data-target="#carousel-example-generic" data-slide-to="1"
								class=""></li>
							<li data-target="#carousel-example-generic" data-slide-to="2"
								class=""></li>
							<li data-target="#carousel-example-generic" data-slide-to="3"
								class=""></li>
						</ol>
						<div class="carousel-inner">
							<div class="item active">
								<img
									src="<%=realpath%>/image/carousel01.jpg"
									alt="First slide">

								<div class="carousel-caption">First Slide</div>
							</div>
							<div class="item">
								<img
									src="<%=realpath%>/image/carousel02.jpg"
									alt="Second slide">

								<div class="carousel-caption">Second Slide</div>
							</div>
							<div class="item">
								<img
									src="<%=realpath%>/image/carousel03.jpg"
									alt="Third slide">

								<div class="carousel-caption">Third Slide</div>
							</div>
							<div class="item">
								<img
									src="<%=realpath%>/image/carousel04.jpg"
									alt="Fourth slide">

								<div class="carousel-caption">Fourth Slide</div>
							</div>
						</div>
						<a class="left carousel-control" href="#carousel-example-generic"
							data-slide="prev"> <span class="fa fa-angle-left"></span>
						</a> <a class="right carousel-control"
							href="#carousel-example-generic" data-slide="next"> <span
							class="fa fa-angle-right"></span>
						</a>
					</div>
				</div>
				<!-- /.box-body -->
			</div>
			<!-- /.box -->
		</div>
		<div class="col-md-6">
			<div class="box box-solid">
				<div class="box-header with-border">
					<h3 class="box-title">企业 留言</h3>
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<div class="box-group" id="accordion">
						<!-- we are adding the .panel class so bootstrap.js collapse plugin detects it -->
						<div class="panel box box-primary">
							<div class="box-header with-border">
								<h4 class="box-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#collapseOne" aria-expanded="false" class="collapsed">
										企业 Item #1 </a>
								</h4>
							</div>
							<div id="collapseOne" class="panel-collapse collapse"
								aria-expanded="false" style="height: 0px;">
								<div class="box-body">Anim pariatur cliche reprehenderit,
									enim eiusmod high life accusamus terry richardson ad squid. 3
									wolf moon officia aute, non cupidatat skateboard dolor brunch.
									Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon
									tempor, sunt aliqua put a bird on it squid single-origin coffee
									nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica,
									craft beer labore wes anderson cred nesciunt sapiente ea
									proident. Ad vegan excepteur butcher vice lomo. Leggings
									occaecat craft beer farm-to-table, raw denim aesthetic synth
									nesciunt you probably haven't heard of them accusamus labore
									sustainable VHS.</div>
							</div>
						</div>
						<div class="panel box box-danger">
							<div class="box-header with-border">
								<h4 class="box-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#collapseTwo" class="collapsed" aria-expanded="false">
										企业 Item #2 </a>
								</h4>
							</div>
							<div id="collapseTwo" class="panel-collapse collapse"
								aria-expanded="false" style="height: 0px;">
								<div class="box-body">有客户反应系统延迟问题很严重,请尽快回复</div>
							</div>
						</div>
						<div class="panel box box-success">
							<div class="box-header with-border">
								<h4 class="box-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#collapseThree" class="" aria-expanded="true"> 企业
										Item #3 </a>
								</h4>
							</div>
							<div id="collapseThree" class="panel-collapse collapse in"
								aria-expanded="true" style="">
								<div class="box-body">你好,我们的云系统服务到期了,我们打算续订,但是价格能不能优惠一点,详情私聊</div>
							</div>
						</div>
					</div>
				</div>
				<!-- /.box-body -->
			</div>
			<!-- /.box -->
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div id="main" style="width: 1250px;height:600px;"></div>
		</div>
	</div>
</div>
<script>
	var myChart = echarts.init(document.getElementById('main'));
	var dataAxis = [ '点', '击', '柱', '子', '或', '者', '两', '指', '在', '触', '屏', '上', '滑', '动', '能', '够', '自', '动', '缩', '放' ];
	var data = [ 220, 182, 191, 234, 290, 330, 310, 123, 442, 321, 90, 149, 210, 122, 133, 334, 198, 123, 125, 220 ];
	var yMax = 500;
	var dataShadow = [];

	for (var i = 0; i < data.length; i++) {
		dataShadow.push(yMax);
	}

	option = {
		title : {
			text : '今日企业会话量',
			subtext : 'Feature Sample: Gradient Color, Shadow, Click Zoom'
		},
		xAxis : {
			data : dataAxis,
			axisLabel : {
				inside : true,
				textStyle : {
					color : '#fff'
				}
			},
			axisTick : {
				show : false
			},
			axisLine : {
				show : false
			},
			z : 10
		},
		yAxis : {
			axisLine : {
				show : false
			},
			axisTick : {
				show : false
			},
			axisLabel : {
				textStyle : {
					color : '#999'
				}
			}
		},
		dataZoom : [
			{
				type : 'inside'
			}
		],
		series : [
			{ // For shadow
				type : 'bar',
				itemStyle : {
					normal : {
						color : 'rgba(0,0,0,0.05)'
					}
				},
				barGap : '-100%',
				barCategoryGap : '40%',
				data : dataShadow,
				animation : false
			},
			{
				type : 'bar',
				itemStyle : {
					normal : {
						color : new echarts.graphic.LinearGradient(
							0, 0, 0, 1,
							[
								{
									offset : 0,
									color : '#83bff6'
								},
								{
									offset : 0.5,
									color : '#188df0'
								},
								{
									offset : 1,
									color : '#188df0'
								}
							]
						)
					},
					emphasis : {
						color : new echarts.graphic.LinearGradient(
							0, 0, 0, 1,
							[
								{
									offset : 0,
									color : '#2378f7'
								},
								{
									offset : 0.7,
									color : '#2378f7'
								},
								{
									offset : 1,
									color : '#83bff6'
								}
							]
						)
					}
				},
				data : data
			}
		]
	};

	// Enable data zoom when user click bar.
	var zoomSize = 6;
	myChart.on('click', function(params) {
		console.log(dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)]);
		myChart.dispatchAction({
			type : 'dataZoom',
			startValue : dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)],
			endValue : dataAxis[Math.min(params.dataIndex + zoomSize / 2, data.length - 1)]
		});
	});

	myChart.setOption(option);
</script>