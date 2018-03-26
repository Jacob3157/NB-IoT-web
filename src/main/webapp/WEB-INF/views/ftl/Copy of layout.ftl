<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>主页</title>
	<link rel="stylesheet" type="text/css" href="/Wechat/js/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="/Wechat/css/default.css">
	<link rel="stylesheet" type="text/css" href="/Wechat/js/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="/Wechat/css/header.css">
	<link rel="stylesheet" type="text/css" href="/Wechat/js/easyui/demo.css">
	<script type="text/javascript" src="/Wechat/js/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="/Wechat/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="/Wechat/js/easyui.index.js"></script>
	<script type="text/javascript" >
		function sy(){
			alert("进入首页");
		}
		
		function logout(){
			$(function() {
				$.ajax({
					type: "POST",
					url: "/Wechat/check/logout" ,
					data: {
					},
					success: function(data) {
						if(data=="success"){
							window.location=("/Wechat/check/index");
						}else{
							alert("退出失败，请重试");
						}
					}
				 });
			   });
		}
		
		$(document).ready(function () {
			
		});
	</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:10%;background:#B3DFDA;padding:0px;overflow-y: hidden">
		<div class="mainheader" style="height:100%">
			<div class="mainheader_left"><h1><img src="/Wechat/images/login_04.jpg"></h1></div>
			<label id="comName" style="font-size:30px;">上海磐宏欢迎您！</label>
			<div class="mainheader_right"><a href="#" class="mainheader_Home" onclick="sy()">首页</a>
	            <a onclick="logout()" id="ctl00_lkbExit" class="mainheader_Quit">退出</a>
			</div>
			<div class="navbg"></div>
		</div>
	</div>
	<div data-options="region:'west',split:true,title:'菜单导航'" style="width:20%;padding:10px;">
		<div class="easyui-accordion" data-options="fit:true,border:false">
			<div title="洗衣机管理" style="padding:3px 3px;" id="machine">
				<div class="easyui-panel" style="padding:5px">
					<ul class="easyui-tree">
						<li style="margin:20px">洗衣机一览</li>
						<li style="margin:20px">配件一览</li>
						<li style="margin:20px">维护记录</li>
						<li style="margin:20px">维修记录</li>
						<li style="margin:20px" data-options="state:'closed'" iconCls="icon-cog">
							<span>洗衣机管理</span>
							<ul>
								<li style="margin:20px">
									<span>添加洗衣机</span>
								</li>
								<li style="margin:20px">
									<span>添加维护记录</span>
								</li>
								<li>
									<span>添加维修记录</span>
								</li>
								<li style="padding:5px">
									<span>添加维修类型</span>
								</li>
								<li style="padding:5px">
									<span>添加配件</span>
								</li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
			<div title="运营管理" style="padding:3px 3px;" id="operating">
				<div class="easyui-panel" style="padding:5px">
					<ul class="easyui-tree">
						<li style="margin:20px">单价管理</li>
						<li style="margin:20px">优惠管理</li>
					</ul>
				</div>
			</div>
			<div title="订单管理" style="padding:3px 3px" id="order" >
				<div class="easyui-panel" style="padding:5px">
					<ul class="easyui-tree">
						<li style="margin:20px">订单浏览</li>
						<li style="margin:20px">退单管理</li>
					</ul>
				</div>
			</div>
			<div title="用户管理" style="padding:3px 3px" id="user" iconCls="icon-man">
				<div class="easyui-panel" style="padding:5px">
					<ul class="easyui-tree">
						<li style="margin:20px">用户升级</li>
						<li style="margin:20px">用户降级</li>
						<li style="margin:20px">用户列表</li>
					</ul>
				</div>
			</div>
			<div title="系统管理" style="padding:3px 3px" id="system">
				<div class="easyui-panel" style="padding:5px">
					<ul class="easyui-tree">
						<li style="margin:20px">管理员授权</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:0px;text-align:center;vertical-align:middle">
		<div id="footer">
			<h2>© 2004~2017 Shanghai Pioteks Co.,Ltd</h2>
		</div>
	</div>
	<div id="center" data-options="region:'center',title:'数据显示'">
		
	</div>
</body>
</html>