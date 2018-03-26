<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
<title>维护信息添加</title>
    <style type="text/css">
    	。h1{color:#0F9; margin:0; padding:0;  font-size:25px; font-weight:bold; width:100%; vertical-align:middle}
		.output{ position:absolute;display:none;left:40%; top:30%; background-color:#36F; border:3px solid #F0C; color:#FF6}
    </style>
    <script src="/Wechat/js/jquery-3.2.1.min.js"></script>
    <script src="/Wechat/js/jquery.json.js"></script>
    <script type="text/javascript" src="/Wechat/js/jquery.form.js"></script>
    <link rel="stylesheet" type="text/css" href="/Wechat/css/addParts.css"></link>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script type="text/javascript" src="/Wechat/js/jquery-1.11.0.min.js"></script>
	<link type="text/css" rel="stylesheet" href="/Wechat/css/admin.css" />
	<link rel="stylesheet" type="text/css" href="/Wechat/css/jquery-ui.css" />
	<script src="/Wechat/js/jquery-3.2.1.min.js"></script>
    <script src="/Wechat/js/jquery.json.js"></script>
    <script type="text/javascript" src="/Wechat/js/jquery.form.js"></script>
	<script type="text/javascript" src="/Wechat/js/jquery-ui-1.10.4.custom.min.js"></script>
	<script type="text/javascript" src="/Wechat/js/jquery.ui.datepicker-zh-CN.js"></script>
	<script type="text/javascript" src="/Wechat/js/jquery-ui-timepicker-addon.js"></script>
	<script type="text/javascript" src="/Wechat/js/jquery-ui-timepicker-zh-CN.js"></script>
	<style type="text/css">
		.ui-timepicker-div .ui-widget-header { margin-bottom: 8px; }
		.ui-timepicker-div dl { text-align: left; }
		.ui-timepicker-div dl dt { float: left; clear:left; padding: 0 0 0 5px; }
		.ui-timepicker-div dl dd { margin: 0 10px 10px 45%; }
		.ui-timepicker-div td { font-size: 90%; }
		.ui-tpicker-grid-label { background: none; border: none; margin: 0; padding: 0; }
		
		.ui-timepicker-rtl{ direction: rtl; }
		.ui-timepicker-rtl dl { text-align: right; padding: 0 5px 0 0; }
		.ui-timepicker-rtl dl dt{ float: right; clear: right; }
		.ui-timepicker-rtl dl dd { margin: 0 45% 10px 10px; }
	</style>
    <script>
    $(document).ready(function () {
    	
    	
    	$.ajax({
			type: 'post', //ajax的方式  get/post
			dataType: 'json', //接收返回数据的方式
			url: "/Wechat/select/groupUserName", //路径
			timeout: 3000, //超时时间
			data: {
				userProperty: "service",
			},
			success:function(data) {
				var jsonName = data.userName;
				var jsonId=data.userId;
				var myArr=new Array();
				$.each(jsonId, function(i, item) {
		               myArr[i]=item;
			       });
				$.each(jsonName, function(i, item) {
	               var userName = '<option data-id="' + i + '" value="' + myArr[i] + '">' + item + '</option>';
			       $("#worker").append(userName);
		       });
			}
		});
    	
    	$.ajax({
			type: 'post', //ajax的方式  get/post
			dataType: 'json', //接收返回数据的方式
			url: "/Wechat/select/allMaintenanceType", //路径
			timeout: 3000, //超时时间
			data: {
				pageNo: "1",
				pageSize:"100"
			},
			success:function(data) {
				var json = data.rows;
				$.each(json, function(i, item) {
	               var maintenanceType = '<option data-id="' + i + '" value="' + item['maintenanceName'] + '">' + item['maintenanceName'] + '</option>';
			       $("#maintenanceType").append(maintenanceType);
		       });
			}
		});
    	
    	
    	
    	function changeName() {
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
    		$.ajax({
    			type: 'post', //ajax的方式  get/post
    			dataType: 'json', //接收返回数据的方式
    			url: "/Wechat/select/getMachineName", //路径
    			timeout: 3000, //超时时间
    			data: {
    				school: school,
    				compus: compus,
    			},
    			success:function(data) {
    				console.log(data);
    				var json = data.machineName;
    				$.each(json, function(i, item) {
    	               var option3 = '<option data-id="' + i + '" value="' + item + '">' + item + '</option>';
    			       $("#machine").append(option3);
    		       });
    			}
    		});
    	}
    	
    	
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
    		}else{
    			alert("请选择学校！");
    		}
    	}
    	
    	$("#select1").change(function(){
    		getCompus();
    	});
    	
    	$("#select2").change(function(){
    		changeName();
    	});
    	
    	
    	
    	$("#btn_add").click(function(){
    		var myselect1 = document.getElementById("machine"); //拿到select对象
    		var index1 = myselect1.selectedIndex; //拿到选中项的索引
    	    var machine = myselect1.options[index1].value; //拿到选中项options的text
    	    if(machine==null){
    	    	alert("请选择机器");
    	    	return;
    	    }
    	    
    	    var myselect2 = document.getElementById("worker"); //拿到select对象
    		var index2 = myselect2.selectedIndex; //拿到选中项的索引
    	    var worker = myselect2.options[index2].value; //拿到选中项options的text
    	    if(worker==null){
    	    	alert("请选择操作人员");
    	    	return;
    	    }
    	    
    	    var myselect3 = document.getElementById("maintenanceType"); //拿到select对象
    		var index3 = myselect3.selectedIndex; //拿到选中项的索引
    	    var maintenanceType = myselect3.options[index3].value; //拿到选中项options的text
    	    if(maintenanceType==null){
    	    	alert("请选择操作类型");
    	    	return;
    	    }
    	    if($("#timeInfo").val()==null || $("#timeInfo").val()==""){
    	    	alert("请输入时间");
    	    	return;
    	    }
    	    
    		
    		var dataStr={comment:$("#comment").val(),machine:machine,userID:worker,maintenance:maintenanceType,Date_main:$("#timeInfo").val()};
    		var data=$.toJSON(dataStr);
    		$.ajax({
    			type: "POST",
    			url: "/Wechat/add/maintenanceRecord" ,
    			data: data,
    			dataType:"text",
    			success: function(data) {
    				if(data=="success"){
    					alert("添加成功");
    					$("#comment").val("");
    					$("#timeInfo").val("");
    				}else{
    					alert("添加失败");
    				}
    				
    				}
    			});
    	});
    	
		$("#btn_cancel").click(function(){
    		window.history.back(-1);
    	});
		
		//日期控件
		$( "input[name='act_start_time']" ).datetimepicker();
    });
    </script> 
                                                                                  
</head>
<body>
	<div class="main">
		<div class="header">
			<span class="spa">维护信息增加</span>
		</div>
		<div class="mainbody">
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
				<span class="sp">机器ID:</span>
				<select id="machine" style="width: 300px;height: 40px;font-size:18px" >
				</select>
			</div>
			<div class="di">
				<span class="sp">操作者:</span>
				<select id="worker" style="width: 300px;height: 40px;font-size:18px" >
				</select>
			</div>
			<div class="di">
				<span class="sp">操作类型:</span>
				<select id="maintenanceType" style="width: 300px;height: 40px;font-size:18px" >
				</select>
			</div>
			<div class="di">
				<span class="sp">操作日期:</span>
				<input name="act_start_time" id="timeInfo" type="text" class="text-box" value="" placeholder="请选择时间"  readonly="readonly" style="cursor:pointer;"/>
			</div>
			<div class="di">
				<span class="sp">备注:</span>
				<input id="comment" type="text" class="inputInfo" placeholder="请输入备注"/>
			</div>
			<div class="di">
				<button class="button" id="btn_add"><span style="font-size:20px;">添加</span></button>
				<button class="button" id="btn_cancel"><span style="font-size:20px;">取消</span></button>
			</div>
		</div>

	</div>

</body>
</html>