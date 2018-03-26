<html>
	<head>
		<meta charset="UTF-8">
		<title>维护一览</title>
		<link rel="stylesheet" type="text/css" href="/Wechat/css/machine.css">
		<script src="/Wechat/js/jquery-3.2.1.min.js"></script>
		<script src="/Wechat/js/maintenance.js"></script>
	</head>

	<body>
		<div class="main">
			<div class="header" style="width:100%;height:27px;text-align:center; background:cornflowerblue;">
				<span class="spa" style="font-size:26px;">维护一览</span>
			</div>
			<div class="mainbody">
				<div class="di" style="margin:5px 130px">
					<select id="select1" style="width: 300px;height: 40px;font-size:18px" >
						<option value="0">==请选择学校==</option>
						<option value="SHU">上海大学</option>
					</select>
					<select id="select2"  style="width: 300px;height: 40px;font-size:18px">
						<option value="0">==请选择校区==</option>
					</select>
					<select id="select3" style="width: 300px;height: 40px;font-size:18px">
						<option value="0">==请选择编号==</option>
					</select>
					<button class="button" id="aaa"><span style="font-size:20px;">搜索</span></button>
				</div>
				<div>
					<table class="imagetable" id="tableMachine">
					</table>
				</div>
			</div>
			<div class="footer">
		       <a id="btn0"></a>
		       <a id="sjzl"></a>
		       <a href="#" id="btn1">首页</a>
		       <a href="#" id="btn2">上一页</a>
		       <a href="#" id="btn3">下一页</a>
		       <a href="#" id="btn4">尾页</a><br />
		       <!-- <span>转到</span>
		       <input id="changePage" type="text" size="1" maxlength="4" />
		       <span>页</span>
		       <a href="#" id="btn5">跳转</a> -->
    	  </div>

		</div>
	</body>

</html>