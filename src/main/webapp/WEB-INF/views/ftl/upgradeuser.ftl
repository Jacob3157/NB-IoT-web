<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"   
 "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
    <head>  
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
        <title>上海磐宏</title>
        <style type="text/css">
			* {
		   padding: 10px;
		   margin: 10px;
	       }
	       body{
	       	width: 1200px;
	       	height: 80%;
	       }
	       div{
	       	width: 1000px;
	       	height: 80%;
	       	margin:0 auto;
	       }
	       .header{
	       	width:1000px;
	       	margin:0 auto;
	       }
	       .spa{
	       	margin-left:400px;
	       }
			table.imagetable {
		    width: 80%;
	       	margin:0 auto;
			font-family: verdana, arial, sans-serif;
			font-size: 11px;
			color: #333333;
			border-width: 1px;
			border-color: #999999;
			border-collapse: collapse;
		}
		
		table.imagetable th {
			background: #b5cfd2;
			border-width: 1px;
			padding: 8px;
			border-style: solid;
			border-color: #999999;
		}
		
		table.imagetable td {
			background: #dcddc0;
			border-width: 1px;
			padding: 8px;
			border-style: solid;
			border-color: #999999;
		}
		.footer{
			width:1000px;
			height:20%;
			margin-left:200px;
			
		}
		button{
			width:60px;
			height:30px;
			text-align: center;
		}
		</style>
		<script src="/Wechat/js/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="/Wechat/js/user_list04.js"></script>
    </head>  
    <body>  
      <div class="header">
			<span class="spa">用户列表查询</span>
		</div>
		<div class="mainbody">
			<table class="imagetable" id="aaa">
				
			</table>
		</div>
		<div class="footer">
	       <a id="btn0"></a>
	       <a id="sjzl"></a>
	       <a href="#" id="btn1">首页</a>
	       <a href="#" id="btn2">上一页</a>
	       <a href="#" id="btn3">下一页</a>
	       <a href="#" id="btn4">尾页</a><br />
	       <span>转到</span>
	       <input id="changePage" type="text" size="1" maxlength="4" />
	       <span>页</span>
	       <a href="#" id="btn5">跳转</a>
      </div>
	</body>
    </body>  
</html>  