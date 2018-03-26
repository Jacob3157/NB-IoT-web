<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>上海磐宏</title>
	<link rel="stylesheet" type="text/css" href="/Wechat/js/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="/Wechat/js/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="/Wechat/js/easyui/demo.css">
	<script type="text/javascript" src="/Wechat/js/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="/Wechat/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="/Wechat/js/jquery.cookie.js"></script>
	<script type="text/javascript">
		$(document).ready(function () {
			
			$(function(){
				if ($.cookie("rmbUser") == "true") {
			        $("#rmbUser").attr("checked", true);
			        $("#username").val($.cookie("userName"));
			        $("#password").val($.cookie("passWord"));
			    }
			    $(window).resize(); 
			});
			
			$("body").keydown(function() {
	             if (event.keyCode == "13") {//keyCode=13是回车键
	                 $('#btn_submit').click();
	             }
	         });
			
			
			function saveUserInfo() {
			    if ($("#rmbUser").attr("checked") == true) {
			        var userName = $("#username").val();
			        var passWord = $("#password").val();
			        $.cookie("rmbUser", "true", { expires: 7 }); // 存储一个带7天期限的 cookie
			        $.cookie("userName", userName, { expires: 7 }); // 存储一个带7天期限的 cookie
			        $.cookie("passWord", passWord, { expires: 7 }); // 存储一个带7天期限的 cookie
			    }
			    else {
			        $.cookie("rmbUser", "false", { expires: -1 });        // 删除 cookie
			        $.cookie("userName", '', { expires: -1 });
			        $.cookie("passWord", '', { expires: -1 });
			    }
			}
			
			$(window).resize(function(){ 
			    $("#mydiv").css({ 
			        position: "absolute", 
			        left: ($(window).width() - $("#mydiv").outerWidth())/2, 
			        top: ($(window).height() - $("#mydiv").outerHeight())/3 
			    });        
			});
			
		});
		
		function upload(){
			$(function() {
				$.ajax({
					type: "POST",
					url: "/Wechat/check/login" ,
					data: {
						username: document.getElementById("username").value.toString(),
						password: document.getElementById("password").value.toString()
					},
					success: function(data) {
						if(data=="success"){
							post('admin', {"username": document.getElementById("username").value.toString()});
						}else{
							alert(data);
						}
					}
				 });
			   });
		}
		
		function go(){
			var oRequest=new XMLHttpRequest();
			var sParams="";
			sParams=addPostParam(sParams, "username", document.getElementById("username").value.toString());
			oRequest.open("post","admin",false);
			oRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			oRequest.send(sParams);
		}
		
		function addPostParam(sParams,sParamName,sParamValue){
			if(sParams.length>0){
				sParams+="&";
			}
			return sParams + encodeURIComponent(sParamName)+"="
			+encodeURIComponent(sParamValue);
		}
		
		function post(URL, PARAMS) {
			  var temp = document.createElement("form");
			  temp.action = URL;
			  temp.method = "post";
			  temp.style.display = "none";
			  for (var x in PARAMS) {
			    var opt = document.createElement("textarea");
			    opt.name = x;
			    opt.value = PARAMS[x];
			    // alert(opt.name)
			    temp.appendChild(opt);
			  }
			  document.body.appendChild(temp);
			  temp.submit();
			  return temp;
			}
		
	</script>
</head>
<body class="easyui-layout" style="background:url(/Wechat/images/bj_03.jpg)">
	<div id="mydiv" style="width:400px">
		<div class="easyui-panel" title="微信洗衣后台管理系统" style="width:100%;width:400px;padding:30px 60px" >
			<div style="margin-bottom:10px">
				<input id="username" class="easyui-textbox" style="width:100%;height:40px;padding:12px" data-options="prompt:'Username',iconCls:'icon-man',iconWidth:38">
			</div>
			<div style="margin-bottom:20px">
				<input id="password" class="easyui-textbox" type="password" style="width:100%;height:40px;padding:12px" data-options="prompt:'Password',iconCls:'icon-lock',iconWidth:38">
			</div>
			<div style="margin-bottom:20px">
				<input type="checkbox" checked="checked" id="rmUser">
				<span>Remember me</span>
			</div>
			<div>
				<button  class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="padding:5px 0px;width:100%;" type="submit" onclick="upload();" id="btn_submit">
					<span style="font-size:14px;">Login</span>
				</button>
			</div>
		</div>
	</div>
</body>
</html>