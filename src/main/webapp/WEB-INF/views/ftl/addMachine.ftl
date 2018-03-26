<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
<title>洗衣机信息添加</title>
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
    	
    	function getCompus(){
    		var myselect1 = document.getElementById("select1"); //拿到select对象
    		var index1 = myselect1.selectedIndex; //拿到选中项的索引
    	    var school = myselect1.options[index1].value; //拿到选中项options的text
    		if(school=="SHU"){
    			var myArray=new Array(3);
    			myArray[0]="宝山校区";
    			myArray[1]="延长校区";
    			myArray[2]="嘉定校区";
    			$.each(myArray, function(i, item) {
    	               var option2 = '<option data-id="' + i + '" value="' + item + '">' + item + '</option>';
    			       $("#select2").append(option2);
    		       });
    		}
    	}
    	
    	$("#select1").change(function(){
    		getCompus();
    	});
    	
    	$("#btn_add").click(function(){
    		if($("#machineId").val()==null || $("#machineId").val()==""){
    			alert("machineId必须输入。");
    			return;
    		}
    		if($("#machineName").val()==null || $("#machineName").val()==""){
    			alert("机器编号必须输入。");
    			return;
    		}
    		var myselect1 = document.getElementById("select1"); //拿到select对象
    		var index1 = myselect1.selectedIndex; //拿到选中项的索引
    		 //拿到选中项options的value
    		var school = myselect1.options[index1].value; //拿到选中项options的text

    		var myselect2 = document.getElementById("select2"); //拿到select对象
    		var index2 = myselect2.selectedIndex; //拿到选中项的索引
    		 //拿到选中项options的value
    		var compus = myselect2.options[index2].value; //拿到选中项options的text
    		if(compus=="宝山校区"){
    			compus="Baoshan";
    		}
    		
    		var myselect3 = document.getElementById("machineType"); //拿到select对象
    		var index3 = myselect3.selectedIndex; //拿到选中项的索引
    		 //拿到选中项options的value
    		var machineType = myselect3.options[index3].value; //拿到选中项options的text
    		
    		var myselect4 = document.getElementById("model"); //拿到select对象
    		var index4 = myselect4.selectedIndex; //拿到选中项的索引
    		 //拿到选中项options的value
    		var model = myselect4.options[index4].value; //拿到选中项options的text
    		

    		var dataStr={machineId:$("#machineId").val(),machineName:$("#machineName").val(),machineType:machineType,model:model,school:school,compus:compus,building:$("#building").val()};
    		var data=$.toJSON(dataStr);
    		$.ajax({
    			type: "POST",
    			url: "/Wechat/add/machine" ,
    			data: data,
    			dataType:"text",
    			success: function(data) {
    				if(data=="success"){
    					alert("添加成功");
    					$("#machineId").val("");
    	   				$("#machineName").val("");
    	   				$("#machineType").val("");
    	   				$("#model").val("");
    	   				$("#select1").val("");
    	   				$("#select2").val("");
    	   				$("#building").val("");
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
			<span class="spa">机器信息增加</span>
		</div>
		<div class="mainbody">
			<div class="di">
				<span class="sp">机器ID:</span>
				<input id="machineId" class="inputInfo" type="text" placeholder="请输入机器Id"/>
			</div>
			<div class="di">
				<span class="sp">机器编号:</span>
				<input id="machineName" class="inputInfo" type="text" placeholder="请输入机器编号"/>
			</div>
			<div class="di">
				<span class="sp">机器类型:</span>
				<select id="machineType" style="width: 300px;height: 40px;font-size:18px" >
					<option value="电脑">电脑</option>
					<option value="电子">电子</option>
					<option value="机械">机械</option>
				</select>
			</div>
			<div class="di">
				<span class="sp">机器型号:</span>
				<select id="model" style="width: 300px;height: 40px;font-size:18px" >
					<option value="SWTX21WN3022">SWTX21WN3022</option>
					<option value="SWT811WN3022">SWT811WN3022</option>
					<option value="LWN311SP301NW22">LWN311SP301NW22</option>
				</select>
			</div>
			<div class="di">
				<span class="sp">学校:</span>
				<select id="select1" style="width: 300px;height: 40px;font-size:18px" >
					<option value="0">==请选择学校==</option>
					<option value="SHU">上海大学</option>
				</select>
			</div>
			<div class="di">
				<span class="sp">地点:</span>
				<select id="select2"  style="width: 300px;height: 40px;font-size:18px">
					<option value="0">==请选择校区==</option>
				</select>
			</div>
			<div class="di">
				<span class="sp">楼幢:</span>
				<input id="building" type="text" class="inputInfo" placeholder="请输入楼幢"/>
			</div>
			<div class="di">
				<button class="button" id="btn_add"><span style="font-size:20px;">添加</span></button>
				<button class="button" id="btn_cancel"><span style="font-size:20px;">取消</span></button>
			</div>
		</div>

	</div>

</body>
</html>