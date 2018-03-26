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
    	
    	$("image_parts").hide();
    	
    	$("#btn_add").click(function(){
    		if($("#partsId").val()==null || $("#partsId").val()==""){
    			alert("partsId必须输入。");
    			return;
    		}
    		if($("#partsOnstack").val()==null || $("#partsOnstack").val()==""){
    			alert("库存量必须输入。");
    			return;
    		}
    		if($("#upfile").val()==""){
  			  alert("请选择一个图片文件");
  			  return;
  			 }
    		var reg = new RegExp("^[0-9]*$");
    		if(!reg.test($("#partsOnstack").val())){
    	        alert("库存量只能为数字。");
    	        return;
    	    }
    		var dataStr={partsId:$("#partsId").val(),name:$("#partsName").val(),description:$("#partsDescription").val(),machineType:$("#machineType").val(),onStack:$("#partsOnstack").val()};
    		var data=$.toJSON(dataStr);
    		$.ajax({
    			type: "POST",
    			url: "/Wechat/add/parts" ,
    			data: data,
    			dataType:"text",
    			success: function(data) {
    				if(data=="success"){
    					uploadPhoto();
    				}
    				
    				}
    			});
    	});
    	
		$("#btn_cancel").click(function(){
    		window.history.back(-1);
    	});
		
		function uploadPhoto(){
			 $("#form1").ajaxSubmit({
			  url:"/Wechat/add/photoUpload",
			  type:"POST",
			  data:{
				partsId : $("#partsId").val()  
			  },
			  success:function(response){
				 alert(response);
				/*   $("#ln").empty(); */
				/*   $("#ln").append("<img src='/Wechat/"+response+"' style='width:100px;height:60px'/>"); */
				$("#partsId").val("");
   				$("#partsName").val("");
   				$("#partsDescription").val("");
   				$("#machineType").val("");
   				$("#partsOnstack").val("");
   				$("#form1").hide();
   				$("image_parts").show();
   				var imgurl="/Wechat/select/getPartsImg?imgurl="+$("#partsId").val();
				$("#image_parts").attr('src',imgurl);
				alert("新增成功!");
   				
			  	},
			  error:function(msg){
			  alert("出错了");
			  }
			 });
			 
			}
    	
    });
    </script>                                                                               
</head>
<body>
	<div class="main">
		<div class="header">
			<span class="spa">配件信息增加</span>
		</div>
		<div class="mainbody">
			<div class="di">
				<span class="sp">配件ID:</span>
				<input id="partsId" class="inputInfo" type="text" placeholder="请输入配件Id"/>
			</div>
			<div class="di">
				<span class="sp">配件名称:</span>
				<input id="partsName" class="inputInfo" type="text" placeholder="请输入配件名称"/>
			</div>
			<div class="di">
				<span class="sp">适用机器类型:</span>
				<input id="machineType" class="inputInfo" type="text" placeholder="请输入机器类型"/>
			</div>
			<div class="di">
				<span class="sp">配件描述:</span>
				<input id="partsDescription" class="inputInfo" type="" placeholder="请输入配件描述" />
				
			</div>
			<div class="di">
				<span class="sp">配件库存量:</span>
				<input id="partsOnstack" type="text" class="inputInfo" placeholder="请输入配件库存"/>
			</div>
			<div class="di">
				<span class="sp">配件图片:</span>
				<form enctype="multipart/form-data" id="form1" method="post" action="">
				 	<input type="file" name="upfile" id="upfile"/>
				</form>
				<img id="image_parts" style="width:200px; height:200px"/>
			</div>
			<div class="di">
				<button class="button" id="btn_add"><span style="font-size:20px;">添加</span></button>
				<button class="button" id="btn_cancel"><span style="font-size:20px;">取消</span></button>
			</div>
		</div>

	</div>

</body>
</html>