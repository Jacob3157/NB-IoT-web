<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
<title>维修类型添加</title>
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
    	
    	$.ajax({
			type: 'post', //ajax的方式  get/post
			dataType: 'json', //接收返回数据的方式
			url: "/Wechat/select/getAllPartsName", //路径
			timeout: 3000, //超时时间
			data: {
				
			},
			success:function(data) {
				var jsonName = data.name;
				$.each(jsonName, function(i, item) {
	               var name = '<option data-id="' + i + '" value="' + item + '">' + item + '</option>';
			       $("#partsOne").append(name);
			       $("#partsTwo").append(name);
			       $("#partsThree").append(name);
			       $("#partsFour").append(name);
			       $("#partsFive").append(name);
			       $("#partsSix").append(name);
			       $("#partsSeven").append(name);
			       $("#partsEight").append(name);
			       $("#partsNine").append(name);
			       $("#partsTen").append(name);
		       });
			}
		});
    	
    	$("#btn_add").click(function(){
    		if($("#name").val()==null || $("#name").val()==""){
    			alert("维修类型名称必须输入。");
    			return;
    		}
    		
    		var dataStr={serviceName:$("#name").val(),partsOneName:$("#partsOne").val(),partsTwoName:$("#partsTwo").val(),partsThreeName:$("#partsThree").val(),partsFourName:$("#partsFour").val(),partsFiveName:$("#partsFive").val()
    				,partsSixName:$("#partsSix").val(),partsSevenName:$("#partsSeven").val(),partsEightName:$("#partsEight").val(),partsNineName:$("#partsNine").val(),partsTenName:$("#partsTen").val()};
    		var data=$.toJSON(dataStr);
    		$.ajax({
    			type: "POST",
    			url: "/Wechat/add/serviceType" ,
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
			<span class="spa">维修类型增加</span>
		</div>
		<div class="mainbody">
			<div class="di">
				<span class="sp">维修类型名称:</span>
				<input id="name" class="inputInfo" type="text" placeholder="请输入维护类型名称"/>
			</div>
			<div class="di">
				<span class="sp">配件一:</span>
				<select id="partsOne" style="width: 300px;height: 40px;font-size:18px" >
				<option value="0">==请选择配件==</option>
				</select>
			</div>
			<div class="di">
				<span class="sp">配件二:</span>
				<select id="partsTwo" style="width: 300px;height: 40px;font-size:18px" >
				<option value="0">==请选择配件==</option>
				</select>
			</div>
			<div class="di">
				<span class="sp">配件三:</span>
				<select id="partsThree" style="width: 300px;height: 40px;font-size:18px" >
				<option value="0">==请选择配件==</option>
				</select>
			</div>
			<div class="di">
				<span class="sp">配件四:</span>
				<select id="partsFour" style="width: 300px;height: 40px;font-size:18px" >
				<option value="0">==请选择配件==</option>
				</select>
			</div>
			<div class="di">
				<span class="sp">配件五:</span>
				<select id="partsFive" style="width: 300px;height: 40px;font-size:18px" >
				<option value="0">==请选择配件==</option>
				</select>
			</div>
			<div class="di">
				<span class="sp">配件六:</span>
				<select id="partsSix" style="width: 300px;height: 40px;font-size:18px" >
				<option value="0">==请选择配件==</option>
				</select>
			</div>
			<div class="di">
				<span class="sp">配件七:</span>
				<select id="partsSeven" style="width: 300px;height: 40px;font-size:18px" >
				<option value="0">==请选择配件==</option>
				</select>
			</div>
			<div class="di">
				<span class="sp">配件八:</span>
				<select id="partsEight" style="width: 300px;height: 40px;font-size:18px" >
				<option value="0">==请选择配件==</option>
				</select>
			</div>
			<div class="di">
				<span class="sp">配件九:</span>
				<select id="partsNine" style="width: 300px;height: 40px;font-size:18px" >
				<option value="0">==请选择配件==</option>
				</select>
			</div>
			<div class="di">
				<span class="sp">配件十:</span>
				<select id="partsTen" style="width: 300px;height: 40px;font-size:18px" >
				<option value="0">==请选择配件==</option>
				</select>
			</div>
			<div class="di">
				<button class="button" id="btn_add"><span style="font-size:20px;">添加</span></button>
				<button class="button" id="btn_cancel"><span style="font-size:20px;">取消</span></button>
			</div>
		</div>

	</div>

</body>
</html>