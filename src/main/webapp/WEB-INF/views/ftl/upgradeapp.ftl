<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
  <title>洗衣机详细信息</title> 
  <meta http-equiv="X-UA-Compatible" content="IE=edge" /> 
  <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" /> 
  <script src="/Wechat/js/jquery-3.2.1.min.js"></script> 
  <link
  href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css"
  rel="stylesheet">
   <link
  href="/Wechat/css/demos.css"
  rel="stylesheet">
  <script
  src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script type="text/javascript">
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
        // var openid="05451545441F";
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
<body style="background-color: #E0FFFF">
     <div class="col-xs-12" style="background-color: #008800;text-align: center;display: inline;font-size: 2em;padding: 10px;"><span class="glyphicon glyphicon-user" style="vertical-align:middle;color:#FFF">校园微信洗衣机服务</span></div> 
    <div role="form" class="col-xs-offset-1 col-xs-10" style="margin-top: 8%;background-color: #FAFAD2 ;padding: 5%;border-radius: 15px">
      <div class="text-align center" style=display:none id="a">
        <span id="aa" style=display:none>学(工)号不能为空!</span>
        <span id="bb" style=display:none>手机号不能为空</span>
        <span id="cc" style=display:none>输入学(工)号格式错误，请重新输入!</span>
        <span id="dd" style=display:none>输入手机格式错误，请重新输入11位手机号!</span>
      </div>
      <div class="from-group"  >
        <label for="name">姓名</label><input type="text" id="stu" class="form-control" placeholder="请输入姓名"/>
      </div>
      <div class="form-group" >
        <label for="name" >学(工)号</label>
        <input type="text" id="stuNum" class="form-control" placeholder="请输入学(工)号" onblur="validateStuID()"/>
      </div>
      <div class="form-group">
        <label for="name">手机号</label><input type="text" id="phone" class="form-control" placeholder="请输入手机号" onblur="validatePhone()"/>
      </div>
      <div class="div-conform" style="text-align: center;">
        <div rel="external nofollow" class="btn btn-success" onclick="stuSubmit();"><span>提交</span></button><br />
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