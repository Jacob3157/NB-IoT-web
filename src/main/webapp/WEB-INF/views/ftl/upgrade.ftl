<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title></title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>用户升级</title>
		<style>
			* {
				margin: 0;
				padding: 0;
				list-style: none
			}
			
			body {
				background: #EBECED;
				font-family: '微软雅黑'
			}
			
			.form {
				width: 800px;
				height: 70%;
				margin: 100px auto;
				overflow: hidden;
				font-size: 16px;
				color: #1b1b1b;
				text-align: left;
				padding: 50px;
				border: 1px solid #ccc;
				border-radius: 10px;
			}
			
			.form div {
				padding: 5px 0;
				overflow: hidden;
			}
			
			.form label {
				width: 90px;
				display: block;
				float: left;
			}
			
			.form .infos {
				width: 200px;
				height: 26px;
				line-height: 26px;
				border: 1px solid #BFBFBF;
				padding: 2px;
				border-radius: 4px;
				float: left;
			}
			
			.form .div-phone a.send1 {
				height: 26px;
				text-decoration: none;
				line-height: 26px;
				padding: 2px;
				width: 80px;
				background: #AA8926;
				font-family: '宋体';
				color: #fff;
				font-size: 12px;
				text-align: center;
				display: block;
				float: left;
				border-radius: 2px;
				margin-left: 2px;
				-webkit-transition: all 0.2s linear;
				-moz-transition: all 0.2s linear;
				-ms-transition: all 0.2s linear;
				-o-transition: all 0.2s linear;
				transition: all 0.2s linear;
			}
			
			.form .div-phone a.send1:hover {
				text-decoration: none;
				background: #866c1b;
				-webkit-transition: all 0.2s linear;
				-moz-transition: all 0.2s linear;
				-ms-transition: all 0.2s linear;
				-o-transition: all 0.2s linear;
				transition: all 0.2s linear;
			}
			
			.form .div-phone a.send0 {
				height: 26px;
				text-decoration: none;
				line-height: 26px;
				padding: 2px;
				width: 80px;
				background: #A1A1A1;
				font-family: '宋体';
				color: #fff;
				font-size: 12px;
				text-align: center;
				display: block;
				float: left;
				border-radius: 2px;
				margin-left: 2px;
			}
			
			.form .div-phone a.send0:hover {
				background: #A1A1A1;
				font-family: '宋体';
				color: #fff;
				font-size: 12px;
				text-decoration: none;
			}
			
			.form span.error {
				height: 26px;
				line-height: 26px;
				padding: 2px;
				width: 100px;
				color: red;
				padding-left: 20px;
				display: block;
				float: left;
				margin-left: 10px;
				font-size: 12px;
				font-family: '宋体';
				background: url(../images/error.png) no-repeat left center;
			}
			
			.form #phone {
				/*手机号输入框大小调整*/
				width: 200px;
			}
			
			.form .div-conform {
				padding-right: 153px;
			}
			
			.form .div-conform a.conform {
				width: 136px;
				height: 34px;
				display: block;
				text-align: left;
				overflow: hidden;
				float: right;
				text-indent: -1000px;
			}
			/*按钮大小调整*/
			button{
				width:100px;
				height:40px;
				font-size:15px;
			}
			.div-conform{
				margin-left: 100px;
			}
			.mag{
				margin-left:20px;
			}
		</style>
		<script src="http://www.lanrenzhijia.com/ajaxjs/jquery.min.js"></script>
		<script>
			function upload(){
				var xmlHttpRequest;
				$(function() {
					if(window.XMLHttpRequest){
						xmlHttpRequest=new XMLHttpRequest()
						}else{
						xmlHttpRequest=new ActiveXObject("Microsoft.XMLHTTP");
						}
					xmlHttpRequest.onreadystatechange = function(){
						switch(xmlHttpRequest.readyState) {
					          case 0 :
					             //alert("请求未初始化");
					             break; 
					          case 1 :
					             // alert("请求启动，尚未发送");
					              break;
					         case 2 :
					            // alert("请求发送，尚未得到响应");
					             break;
					          case 3 : 
					              //alert("请求开始响应，收到部分数据");
					             break;
					         case 4 :
					             // alert("请求响应完成得到全部数据");
					              if((xmlHttpRequest.status >= 200 && xmlHttpRequest.status < 300) || xmlHttpRequest.status == 304) {
					                  var  data = xmlHttpRequest.responseText;
					                  document.getElementById("tip").style.display='block';
									  document.getElementById("meg").innerHTML=data;
									  setInterval(go, 1000);
					              }else {
					                  alert("Request was unsuccessful : " + xmlHttpRequest.status + " " + xmlHttpRequest.statusText);
					             }
					             break;
						  }
					};
					var studentID=document.getElementById("stuNum").value.toString();
					var telephone=document.getElementById("phone").value.toString();
					var openid="${openid}" ;
					var name=document.getElementById("stu").value.toString();
					var url="/Wechat/update/user?studentID="+studentID+"&telephone="+telephone+"&openid="+openid+"&name="+name;
					
					xmlHttpRequest.open("get", url, true);
					xmlHttpRequest.setRequestHeader("Content-Type"
							, "application/x-www-form-urlencoded");
					
					// 发送请求
					xmlHttpRequest.send(null);
				});
			}
			
			// post请求
			  // 格式化post 传递的数据
			 function postDataFormat(obj){
			      if(typeof obj != "object" ) {
			          alert("输入的参数必须是对象");
			          return;
			      }
			 
			     // 支持有FormData的浏览器（Firefox 4+ , Safari 5+, Chrome和Android 3+版的Webkit）
			      if(typeof FormData == "function") {
			          var data = new FormData();
			          for(var attr in obj) {
			              data.append(attr,obj[attr]);
			          }
			          return data;
			     }else {
			          // 不支持FormData的浏览器的处理 
			          var arr = new Array();
			          var i = 0;
			         for(var attr in obj) {
			              arr[i] = encodeURIComponent(attr) + "=" + encodeURIComponent(obj[attr]);
			              i++;
			        }
			          return arr.join("&");
			      }
			 }
			var x=3; //全局变量
			function go(){
				x--;
				if(x>0){
					document.getElementById("sp").innerHTML=x;
				}else{
					if (typeof WeixinJSBridge == "undefined") {
					    if (document.addEventListener) {
					        document.addEventListener('WeixinJSBridgeReady', wxPayCall, false);
					    } else if (document.attachEvent) {
					        document.attachEvent('WeixinJSBridgeReady', wxPayCall);
					        document.attachEvent('onWeixinJSBridgeReady', wxPayCall);
					    }
					}
					WeixinJSBridge.call('closeWindow');
				}
			}
			
			function validateStuID(){
				var stuID=document.getElementById("stuNum").value;
				if(stuID.length==0){
					document.getElementById("a").style.display = "";
					document.getElementById("aa").style.display = "";
					document.getElementById("cc").style.display = "none";
					document.getElementById("stuNum").focus();
					return null;
				}
				var reg=/^[0-9a-zA-Z]{8}$/g;
				if(!reg.test(stuID)){
					document.getElementById("a").style.display = "";
					document.getElementById("cc").style.display = "";
					document.getElementById("aa").style.display = "none";
					document.getElementById("stuNum").focus();
					document.getElementById("stuNum").innerHTML="";
					return null;
				}
				document.getElementById("cc").style.display = "none";
				document.getElementById("aa").style.display = "none";
				return "success";
			}
			function validatePhone(){
				var stuPho=document.getElementById("phone").value;
				if(stuPho.length==0){
					document.getElementById("a").style.display = "";
					document.getElementById("bb").style.display = "";
					document.getElementById("dd").style.display = "none";
					document.getElementById("phone").focus();
					
					return null;
				}
				
				var reg=/^\d{11}$/g;
				if(!reg.test(stuPho)){
					document.getElementById("a").style.display = "";
					document.getElementById("dd").style.display = "";
					document.getElementById("bb").style.display = "none";
					document.getElementById("phone").focus();
					document.getElementById("phone").innerHTML="";
					return null;
				}
				document.getElementById("bb").style.display = "none";
				document.getElementById("dd").style.display = "none";
				return "success";
			}
			function stuSubmit(){
				var studentID=document.getElementById("stuNum").value.toString();
				var telephone=document.getElementById("phone").value.toString();
				var name=document.getElementById("stu").value.toString();
				if(validateStuID()!="success") return;
				if(validatePhone()!="success") return;
				var str="请确认您的信息：\r 姓名："+name+"\r 学(工)号："+studentID+"\r 手机号："+telephone;
				var res=confirm(str);
				if(res){
					upload();
				}else{
					return;
				}
			}
		</script>
	</head>
	<body>
		<h3 style="text-align:center; margin-top:10px">校园微信洗衣服务系统(测试版) 用户升级</h3>
		<div class="form ">
			<div class="hander" style=display:none id="a">
				<span id="aa" style=display:none>学(工)号不能为空!</span>
				<span id="bb" style=display:none>手机号不能为空</span>
				<span id="cc" style=display:none>输入学(工)号格式错误，请重新输入!</span>
				<span id="dd" style=display:none>输入手机格式错误，请重新输入11位手机号!</span>
			</div>
			<div class="div-name">
				<label for="name">姓名</label><input type="text" id="stu" class="infos" placeholder="请输入姓名"/>
			</div>
			<div class="div-name">
				<label for="name">学(工)号</label><input type="text" id="stuNum" class="infos" placeholder="请输入学(工)号" onblur="validateStuID()"/>
			</div>
			<div class="div-name">
				<label for="name">手机号</label><input type="text" id="phone" class="infos" placeholder="请输入手机号" onblur="validatePhone()"/>
			</div>
			<div class="div-conform">
				<button rel="external nofollow" class="conform" onclick="stuSubmit();"><span>提交</span></button><br />
			</div>
			<div>
				<span class="mag" style="margin-left:5px">信息将在两个工作日内完成认证，请耐心等待！</span>
			</div>
			<div id="tip" style="display:none">
				<label for="name" id="meg"></label> <br>
				<label for="name">页面将在<span id="sp" style="display:inline">3</span><span style="display:inline">秒后关闭！</span></label>
			</div>
		</div>
	</body>
</html>