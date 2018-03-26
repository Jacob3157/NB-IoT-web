<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
<title>配件信息添加</title>
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
    		if($("#username").val()==null || $("#username").val()==""){
    			alert("username必须输入。");
    			return;
    		}
    		if($("#password").val()==null || $("#password").val()==""){
    			alert("密码必须输入。");
    			return;
    		}
    		if($("#phone").val()==""){
  			  alert("电话必须输入。");
  			  return;
  			 }
    		if($("#level").val()==""){
    			  alert("电话必须输入。");
    			  return;
    			 }
    		var reg = new RegExp("^[0-9]*$");
    		if(!reg.test($("#level").val())){
    	        alert("等级只能为数字。");
    	        return;
    	    }
    		if(!reg.test($("#phone").val())){
    	        alert("电话只能为数字。");
    	        return;
    	    }
    		var dataStr={username:$("#username").val(),password:$("#password").val(),phone:$("#phone").val(),mail:$("#mail").val(),level:$("#level").val()};
    		var data=$.toJSON(dataStr);
    		$.ajax({
    			type: "POST",
    			url: "/Wechat/add/admin" ,
    			data: data,
    			dataType:"text",
    			success: function(data) {
    				if(data=="success"){
    					alert("添加成功")
    				}else{
    					alert("添加失败");
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
			<span class="spa">管理员信息增加</span>
		</div>
		<div class="mainbody">
			<div class="di">
				<span class="sp">用户名:</span>
				<input id="username" class="inputInfo" type="text" placeholder="请输入用户名"/>
			</div>
			<div class="di">
				<span class="sp">密码:</span>
				<input id="password" class="inputInfo" type="text" placeholder="请输入密码"/>
			</div>
			<div class="di">
				<span class="sp">电话:</span>
				<input id="phone" class="inputInfo" type="text" placeholder="请输入电话"/>
			</div>
			<div class="di">
				<span class="sp">电子邮件:</span>
				<input id="mail" class="inputInfo" type="" placeholder="请输入电子邮件"/>
			</div>
			<div class="di">
				<span class="sp">等级:</span>
				<input id="level" type="text" class="inputInfo" placeholder="请输入等级"/>
			</div>
			<div class="di">
				<button class="button" id="btn_add"><span style="font-size:20px;">添加</span></button>
				<button class="button" id="btn_cancel"><span style="font-size:20px;">取消</span></button>
			</div>
		</div>

	</div>

</body>
</html>