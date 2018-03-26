<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>主页</title>
	<link rel="stylesheet" type="text/css" href="/Wechat/js/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="/Wechat/js/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="/Wechat/css/header.css">
	<link rel="stylesheet" type="text/css" href="/Wechat/js/easyui/demo.css">
	<link rel="stylesheet" type="text/css" href="/Wechat/css/default.css">
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
		
	</script>
</head>
<body class="easyui-layout">
	
	<div id="loading-mask" style="position:absolute;top:0px; left:0px; width:100%; height:100%; background:#D2E0F2; z-index:20000">
		<div id="pageloading" style="position:absolute; top:50%; left:50%; margin:-120px 0px 0px -120px; text-align:center;  border:2px solid #8DB2E3; width:200px; height:40px;  font-size:14px;padding:10px; font-weight:bold; background:#fff; color:#15428B;"> 
    		<img src="/Wechat/images/loading.gif" align="absmiddle" /> 正在加载中,请稍候...</div>
	</div>
	
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
		<div id="nav">
			<!--  导航内容 -->
		</div>
	</div>
	<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:0px;text-align:center;vertical-align:middle">
		<div id="footer">
			<h2>© 2004~2017 Shanghai Pioteks Co.,Ltd</h2>
		</div>
	</div>
	<div id="center" data-options="region:'center',title:'数据显示'" style="overflow:hidden">
		<iframe id="mainFrame" scrolling="auto" frameborder="0"  style="width:100%;height:100%;"></iframe>
	</div>
</body>
</html>