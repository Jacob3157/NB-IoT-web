<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>主页</title>
	<link rel="stylesheet" type="text/css" href="/Wechat/js/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="/Wechat/js/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="/Wechat/css/header.css">
	<link rel="stylesheet" type="text/css" href="/Wechat/js/easyui/demo.css">
	<script type="text/javascript" src="/Wechat/js/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="/Wechat/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" >
	$(document).ready(function () {
		
		var startTime=null;
		var endTime=null;
		
		function init(){
			$('#dg').datagrid({
				title:'订单一览表',
				rownumbers: true,
                singleSelect: false,
                url: '/Wechat/check/queryOrder',
                method: 'get',
                autoRowHeight: true,
                pagination: true,
                pageSize: 10,
                pageList: [10],
                fitColumns: true,
                toolbar: '#tb',
                loadMsg:"Processing, please waiting...",
                emptyMsg:'No records',
                queryParams: { startTime:startTime, endTime:endTime,mark:'0'},
                columns: [[
                { field: 'transaction_id', title: "订单编号", width: '28%'},
                { field: 'time_end', title: "订单日期", width: '16%'},
                { field: 'name', title: "真实姓名", width: '14%',formatter:function(value,rows,index){
                	return rows.wechatID.name;
                }},
                { field: 'building', title: "地点", width: '6%',formatter:function(value,rows,index){
                	return rows.machineID.building;
                }},
                { field: 'total_fee', title: "订单总额", width: '8%'},
                { field: 'machine', title: "机器名称", width: '10%',formatter:function(value,rows,index){
                	return rows.machineID.machineName;
                }},
                { field: 'trade_state', title: "状态", width: '10%'}
            ]],
            });
		}
		
		$(function(){		    
		   init();
		   $("#search").click(function(){
				var queryParameter = $('#dg').datagrid("options").queryParams;  
	            queryParameter.startTime = $("#startTime").val();  
	            queryParameter.endTime = $("#endTime").val();
	            console.log(queryParameter);
	            $("#dg").datagrid("load");  
			});
		   }); 
		
		
		
	});
		
	</script>
</head>
<body>
	
	<table id="dg">
	</table>
	
	<div id="tb" style="padding:2px 5px;">
		Date From: <input class="easyui-datebox"   style="width:110px" id="startTime">
		To: <input class="easyui-datebox" style="width:110px" id="endTime">
		Type: 
		<select id="type" class="easyui-combobox" panelHeight="auto" style="width:100px">
			<option value="all">全部</option>
			<!-- <option value="chargeback">退单</option>
			<option value="normal">正常</option> -->
		</select>
		<a href="#" class="easyui-linkbutton" iconCls="icon-search" id="search">Search</a>
		<!-- <a href="#" class="easyui-linkbutton" iconCls="icon-save" style="margin-left:20px">保存</a> -->
	</div>
</body>
</html>