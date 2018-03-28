<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>NB-IoT信息</title>
	<link rel="stylesheet" type="text/css" href="/Wechat/js/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="/Wechat/js/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="/Wechat/css/header.css">
	<link rel="stylesheet" type="text/css" href="/Wechat/js/easyui/demo.css">
	<script type="text/javascript" src="/Wechat/js/easyui/jquery.min.js"></script>
	<script src="/Wechat/js/jquery.json.js"></script>
	<script type="text/javascript" src="/Wechat/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" >
	$(document).ready(function () {
		
		
		var openid=null;
		function init(){
			$('#dg').datagrid({
				title:'NB-IoT信息',
                singleSelect: false,
                url: '',
                checkOnSelect:true,
                selectOnCheck:true,
                autoRowHeight: true,
                pagination: false,
                fitColumns: true,
                toolbar: '#tb',
                loadMsg:"Processing, please waiting...",
                queryParams: { openid:openid},
                columns: [[
                { field: 'location', title: "位置", width: '16%'},
                { field: 'lonLat', title: "坐标", width: '16%'},
                { field: 'id', title: "编号", width: '16%'},
                { field: 'temp', title: "温度", width: '16%'},
                { field: 'humidity', title: "湿度", width: '16%'},
                { field: 'openTimes', title: "开启次数", width: '16%'}
            ]]
            });
		}
		
		function update(){
			$('#dg1').datagrid({
				title:'NB-IoT实时信息',
                singleSelect: false,
                url: '/Wechat/select/getUdpInfo',
                checkOnSelect:true,
                selectOnCheck:true,
                autoRowHeight: true,
                pagination: false,
                fitColumns: true,
                loadMsg:"Processing, please waiting...",
                queryParams: { openid:openid},
                columns: [[
                { field: 'IP', title: "客户端IP", width: '20%'},
                { field: 'Port', title: "客户端端口", width: '10%'},
                { field: 'content', title: "消息内容", width: '35%'},
                { field: 'response', title: "回复内容", width: '35%'},
                
            ]]
            });
			$("#dg1").datagrid("load");
		}
		
		function getNowTime(){
			var date=new Date();
		    var month = date.getMonth() + 1;
		    var strDate = date.getDate();
		    if (month >= 1 && month <= 9) {
		        month = "0" + month;
		    }
		    if (strDate >= 0 && strDate <= 9) {
		        strDate = "0" + strDate;
		    }
		    var currentdate = date.getFullYear()+""+ month + strDate;
		    return currentdate;
		}
		
		$(function(){
		   init();
		   update();
		   updateCommandList();
		   updateNBConfig();
		   $(".datagrid-header-check").html("");
 		  	
		   
		   
		   $("#search").click(function(){
				var queryParameter = $('#dg').datagrid("options").queryParams;
				$("#dg").datagrid("options").url='/Wechat/check/welllidLocation';
	            queryParameter.openid = $("#seclectedLocation").val();  
	            $("#dg").datagrid("load");
			});
		   $("#change").click(function(){
			   changeCommand();
		   });
		   $("#change_name").click(function(){
			   changeCommandName();
		   });
		   $("#change_time").click(function(){
			   changeDelayTime();
		   });
		   
		   
		}); 
		setInterval(function (){
			update();
		},2000);
		
	});
	
	function udpSend(){
		$('#ff').form({
		    url:'/Wechat/check/udpSend',
		    onSubmit: function(){
		    },
		    success:function(message){
				alert(message)
		    }
		});
		// submit the form
		$('#ff').submit();  
	}
	
	function changeCommand(){
		$('#change_form').form({
		    url:'/Wechat/update/updateCommand',
		    onSubmit: function(){
		    },
		    success:function(message){
				updateCommandList();
		    }
		});
		// submit the form
		//$('#change_form').submit(); 

	}
	function changeCommandName(){
		$('#change_name').form({
		    url:'/Wechat/update/updateCommandName',
		    onSubmit: function(){
		    },
		    success:function(message){
		    	updateNBConfig();
		    }
		});

	}
	function changeDelayTime(){
		$('#change_delay_time').form({
		    url:'/Wechat/update/updateDelayTime',
		    onSubmit: function(){
		    },
		    success:function(message){
		    	updateNBConfig();
		    }
		});

	}
	function updateNBConfig(){
		$('#dg2').datagrid({
			title:'NB-IoT配置信息',
            singleSelect: false,
            url: '/Wechat/select/getNBconfig',
            checkOnSelect:true,
            selectOnCheck:true,
            autoRowHeight: true,
            pagination: false,
            fitColumns: true,
            loadMsg:"Processing, please waiting...",
            columns: [[
           	{ field: 'id', title: "ID", width: '25%'},
           	{ field: 'delayTime', title: "delay time", width: '75%'},
        ]]
        });
		$("#dg2").datagrid("load");
	}
	
	function updateCommandList(){
		$('#dg3').datagrid({
			title:'NB-IoT命令',
            singleSelect: false,
            url: '/Wechat/select/getCommandInfo',
            checkOnSelect:true,
            selectOnCheck:true,
            autoRowHeight: true,
            pagination: false,
            fitColumns: true,
            loadMsg:"Processing, please waiting...",
            columns: [[
           	{ field: 'id', title: "ID", width: '4%'},
           	{ field: 'requestName', title: "NB Name", width: '13%'},
            { field: 'request', title: "NB to Driver", width: '35%'},
            { field: 'responseName', title: "Driver Name", width: '13%'},
            { field: 'response', title: "Driver to NB", width: '35%'},
        ]]
        });
		$("#dg3").datagrid("load");
	}
	
	function unixToDate(unixTime) {
        var time = new Date(unixTime);
        var ymdhis = "";
        ymdhis += time.getUTCFullYear() + "-";
        ymdhis += (time.getUTCMonth()+1) + "-";
        ymdhis += time.getUTCDate();
        ymdhis += " " + time.getHours() + ":";
        ymdhis += time.getUTCMinutes() + ":";
        ymdhis += time.getUTCSeconds();
        return ymdhis;
    }
	
	function unix2Date(unixTime) {
        var time = new Date(unixTime);
        var ymdhis = "";
        ymdhis += (time.getUTCMonth()+1) + "/";
        ymdhis += time.getUTCDate() + "/";
        ymdhis += time.getUTCFullYear();
        return ymdhis;
    }
		
	</script>
</head>
<body>
	
	<table id="dg">
	</table>
	
	<div id="tb" style="padding:2px 5px;">
		location: 
		<select id="seclectedLocation" class="easyui-combobox" panelHeight="auto" style="width:100px">
			<option value="oiEJPv7b4KznMvTRdQuZL9W6IpfQ">南陈路</option>
			<option value="oiEJPv_P15DgDe4_cJ5nqApzD9Dk">中山广场</option>
			<option value="oiEJPv9ngNopdalLq46ZXYhaniz0">龙华西路</option>
		</select>
		<a href="#" class="easyui-linkbutton" iconCls="icon-search" id="search">Search</a>
	</div>
	
	<table id="dg1" >
	</table>
<!--  	<form id="ff" method="post" action="udpSend">
    <div>
		<label for="1">(1):</label>
		<input  name ="One" type="text" defaultVal="" value="" id = "One">
    </div>
    <div>
		<label for="2">(2):</label>
		<input name ="Two" type="text" defaultVal="" value="" >
    </div>
    <div>
		<label for="3">(3):</label>
		<input name ="Three" type="text"  value="" >
    </div>
    <div>
		<label for="4">(4):</label>
		<input name ="Four" type="text" value="" >
    </div>
    <div>
		<label for="5">(5):</label>
		<input name ="Five" type="text" value="" >
    </div>
    <div>
		<label for="6">(6):</label>
		<input name ="Six" type="text" value="" >
    </div>
    <div>
		<label for="7">(7):</label>
		<input name ="Seven" type="text" value="" >
    </div>
    <div>
		<label for="8">(8):</label>
		<input name ="Eight" type="text" value="" >
    </div>
    <div>
    	<input type="submit" value = "发送"  id="send">
    </div>
</form>
-->
	<br/>
	<br/>
	<form id="change_form" method="post" >
		<div>	
			<label>(1):</label><input name ="One" type="text" value="" >
			<label>(2):</label><input name ="Two" type="text" value="" >
			<label>(3):</label><input name ="Three" type="text" value="" >
			<label>(4):</label><input name ="Four" type="text" value="" >
		</div>
		<div>
			<label>(5):</label><input name ="Five" type="text" value="" >
			<label>(6):</label><input name ="Six" type="text" value="" >
			<label>(7):</label><input name ="Seven" type="text" value="" >
			<label>(8):</label><input name ="Eight" type="text" value="" >
		</div>
		<br/>
		<br/>
		<div>
			<label>修改第</label>
			<select name="num">
				<option value="0">0</option>
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
				<option value="9">9</option>
				<option value="10">10</option>
				<option value="11">11</option>
				<option value="12">12</option>
				<option value="13">13</option>
				<option value="14">14</option>
				<option value="15">15</option>
			</select><label>条指令</label>
			<label>修改内容为:</label><select name="type">
				<option value="1">消息内容</option>
				<option value="2">回复内容</option>
	
			</select>
			<input type="submit" value = "修改"  id="change" width = "1%">
		</div>
	</form>
	<br/>
	<br/>
	<form id = "change_name" method = "post">
		<div>
			<label>修改第</label>
			<select name="num">
				<option value="0">0</option>
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
				<option value="9">9</option>
				<option value="10">10</option>
				<option value="11">11</option>
				<option value="12">12</option>
				<option value="13">13</option>
				<option value="14">14</option>
				<option value="15">15</option>
			</select><label>条</label>
			<select name = "type">
				<option value = "1">NB</option>
				<option value = "2">Driver</option>
			</select>
			<label>命令的名字为</label>
			<input name = "name" type = "text" value="">
			<input type="submit" value = "修改"  id="change_name" width = "1%" >
		</div>
	</form>
	
	<br/>
	<br/>
	
	<form id = "change_delay_time" method = "post">
		<label>修改延迟时间为</label>
		<input name = "delayTime" type = "text" value = "">
		<label>ms</label>
		<input type="submit" value = "修改"  id="change_time" width = "1%" >
	</form>
	
	<br/>
	<br/>
	<table id = "dg2">
	</table>
	<table id= "dg3">
	
	</table>

</body>
</html>
