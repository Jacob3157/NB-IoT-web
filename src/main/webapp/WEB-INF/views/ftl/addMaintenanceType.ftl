<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
<title>维护类型添加</title>
    <style type="text/css">
    	。h1{color:#0F9; margin:0; padding:0;  font-size:25px; font-weight:bold; width:100%; vertical-align:middle}
		.output{ position:absolute;display:none;left:40%; top:30%; background-color:#36F; border:3px solid #F0C; color:#FF6}
    </style>
    <script src="/Wechat/js/jquery-3.2.1.min.js"></script>
    <script src="/Wechat/js/jquery.json.js"></script>
    <script type="text/javascript" src="/Wechat/js/jquery.form.js"></script>
    <link rel="stylesheet" type="text/css" href="/Wechat/css/addParts.css"></link>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script>
    $(document).ready(function () {
    	
    	$("#btn_add").click(function(){
    		if($("#name").val()==null || $("#name").val()==""){
    			alert("维护类型名称必须输入。");
    			return;
    		}
    		
    		var dataStr={maintenanceName:$("#name").val()};
    		var data=$.toJSON(dataStr);
    		$.ajax({
    			type: "POST",
    			url: "/Wechat/add/maintenanceType" ,
    			data: data,
    			dataType:"text",
    			success: function(data) {
    				if(data=="success"){
    					alert("添加成功");
    					$("#name").val("");
    				}else{
    					alert("添加失败");
    					$("#name").val("");
    				}
    				
    				}
    			});
    	});
    	
		$("#btn_cancel").click(function(){
    		window.history.back(-1);
    	});
		
    	
    });
    </script>                                                                               
</head>
<body>
	<div class="main">
		<div class="header">
			<span class="spa">维护类型增加</span>
		</div>
		<div class="mainbody">
			<div class="di">
				<span class="sp">维护类型名称:</span>
				<input id="name" class="inputInfo" type="text" placeholder="请输入维护类型名称"/>
			</div>
			<div class="di">
				<button class="button" id="btn_add"><span style="font-size:20px;">添加</span></button>
				<button class="button" id="btn_cancel"><span style="font-size:20px;">取消</span></button>
			</div>
		</div>

	</div>

</body>
</html>