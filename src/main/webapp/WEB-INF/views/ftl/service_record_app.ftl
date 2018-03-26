<!DOCTYPE html>
<html>

<head>
	<title>维修记录</title>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	
	<link rel="stylesheet" href="/Wechat/css/jquery-weui.css">
	<link rel="stylesheet" href="/Wechat/css/weui.css">
	<link rel="stylesheet" href="/Wechat/css/demos.css">
	
	
	<script src="/Wechat/js/jquery-2.1.4.js"></script>
	<script src="/Wechat/js/fastclick.js"></script>
	<script src="/Wechat/js/jquery-weui.js"></script>
	<script>
		$(function() {
			FastClick.attach(document.body);
		});
	</script>
</head>

<body  ontouchstart >
	<div class="demo-header">
		<div class="demos-title">维修记录</div>
	</div>

	<div class="weui-cells weui-cells_form">
		<div class="weui-cell">
			<div class="weui-cell__hd"><label for="name" class="weui-label">学校</label></div>
			<div class="weui-cell__bd">
				<input class="weui-input" id="school" type="text" value="">
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd"><label for="date" class="weui-label">校区</label></div>
			<div class="weui-cell__bd">
				<input class="weui-input" id="campus" type="text" value="">
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd"><label for="date" class="weui-label">设备号</label></div>
			<div class="weui-cell__bd">
				<input class="weui-input" id="building" type="text" value="">
			</div>
		</div>
	</div>
	<SCRIPT TYPE="text/javascript">
		function inquireName(){
		var myselect1 = schooldata[$('#school').val()]; //拿到select对象
		 //拿到选中项options的value
		var myselect2 = campusdata[$('#campus').val()]; //拿到select对象
		
		var myselect3 = $('#building').val(); //拿到select对象
		if(myselect1==''||myselect2==''||myselect3==''){
			return
		}
		//添加页面跳转
		var url = encodeURI('service_detail_app?school='+myselect1+"&compus="+myselect2+"&machine="+myselect3);
		window.location.href=url;
	}
</SCRIPT>


<div id="picker-container"></div>
<a  class="weui-btn weui-btn_primary" onclick="inquireName()">查询机器</a>
	<div class="weui-msg__extra-area">
			<div class="weui-footer">
				<p class="weui-footer__links">
					<a href="javascript:void(0);" class="weui-footer__link">wxws.service.pioteks.io</a>
				</p>
				<p class="weui-footer__text">copyright © 2003-2017 wxws.service.pioteks.io</p>
			</div>
		</div>
<script>
	var schoolcode,campuscode,buildcode;
	var schooldata = {'移动互联网大赛':"beijng",'上海大学':'SHU','上海市纽约大学':"NEWYORK",'上海财经':'CAIJING','上海财经':'SHANGCAI','华东师范':'HUADONG'};
	var campusdata = {'互联网竞赛场馆'：'changguan','宝山':'Baoshan','延长':'YANCHANG','嘉定':'JIADING'};
	var buildingdata;
	schoolcode = getObjectKeys(schooldata);
	campuscode = getObjectKeys(campusdata);
	console.log(schoolcode);
	$("#school").picker({
		title: "请选择学校",
		cols: [
		{
			textAlign: 'center',
			values: schoolcode
		}
		],
		onChange: function(p, v, dv) {
			//console.log(p, v, dv);
		},
		onClose: function(p, v, d) {
			console.log("close");
		}
	});
	$("#campus").picker({
		title: "请选择校区",
		cols: [
		{
			textAlign: 'center',
			values: campuscode
		}
		],
		onChange: function(p, v, dv) {
			var school = schooldata[$('#school').val()];
			var compus = campusdata[v[0]]
			console.log(p, v, dv);
			$.ajax({
				type: "POST",
				dataType: "json",
					url: "/Wechat/select/getMachineName",//路径
					data: {
						school: school,
						compus: compus,
					},
					success: function(data) {
						console.log(data);
						buildcode = data.machineName;
						console.log(buildcode);
						/*$('#myul').html($str);*/ 	
						var par = $("#building").parent();
						$("#building").remove();
						par.html('<input class="weui-input" id="building" type="text" value="">');
						$("#building").picker({
							title: "请选择查询的机器",
							cols: [
							{
								textAlign: 'center',
								values: buildcode
							}
							]
						});
					}
				});
		}
	});
	/*$("#building").picker({
		title: "请选择住处",
		cols: [
		{
			textAlign: 'center',
			values: ['请先选择学校和校区']
		}
		],
		onChange: function(p, v, dv) {
			console.log(p, v, dv);
		},
		onClose: function(p, v, d) {
			console.log("close");
		}
	});*/
	
	 //写成标准的方法(数组是object的一种)：
	 function getObjectKeys(object)
	 {
	 	var keys = [];
	 	for (var property in object)
	 		keys.push(property);
	 	return keys;
	 }
	</script>
</body>
</html>