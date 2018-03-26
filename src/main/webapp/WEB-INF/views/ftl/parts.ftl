<html>
	<head>
		<meta charset="UTF-8">
		<title>配件一览</title>
		<link rel="stylesheet" type="text/css" href="/Wechat/css/machine.css">
		<script src="/Wechat/js/jquery-3.2.1.min.js"></script>
		<script src="/Wechat/js/parts.js"></script>
	</head>

	<body>
		<div class="main">
			<div class="header" style="width:100%;height:27px;text-align:center; background:cornflowerblue;">
				<span class="spa" style="font-size:26px;">配件一览</span>
			</div>
			<div class="mainbody">
				<div class="di" style="margin:5px 130px">
					<input id="partsId" style="width: 300px;height: 40px;font-size:18px" placeholder="请输入配件编号" />
					<button class="button" id="search"><span style="font-size:20px;">搜索</span></button>
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