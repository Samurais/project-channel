<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/public/starter.jsp"%>
<div id="thispage">
	<input type="text" id="1" name="date" value="输入问题">
	<label id="2">这里显示回答</label> 
	<input type="text" id="4" name="date" value="输入问题2">
	<button id="3" type="submit">button</button>
	<button id="5" type="submit">button</button>
</div>
<script>
	$("#3").click(function() {
		$.post("test_test2.action", {
			"date" : $("#1").val()
		});
	});
	$("#5").click(function() {
		$.post("test_test2.action", {
			"date" : $("#4").val()
		},function(result){
			$("#2").text(result.text);
		});
	});
</script>