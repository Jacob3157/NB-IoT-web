<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta name="renderer" content="ie-comp"/>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>管理系统</title>
	<link rel="stylesheet" type="text/css" href="/Wechat/css/css.css"/>
	<script type="text/javascript" src="/Wechat/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="/Wechat/js/jquery.min.js"></script>
	<script type="text/javascript" src="/Wechat/js/idangerous.swiper.min.js"></script>
	<link rel="stylesheet" href="/Wechat/css/lanrenzhijia.css"/>
	<!-- <script type="text/javascript">	</script>-->

</head>
<body>
<div class="top" style="background:url(/Wechat/images/login_01.jpg)">
  <div class="warp">
    <div class="lefts">校园洗衣机系统欢迎您！</div>
  </div>
</div>
<div class="dh">
  <div class="warps">
    <div style="float:left"><img src="/Wechat/images/login_04.jpg"/></div>
    <div style="float:left; width:750px">
      <ul>
        <li><a href="/Wechat/check/admin?username=${user.username}">首页</a></li>
        <li class="low"><a href="/Wechat/check/addMachine?username=${user.username}">添加洗衣机</a></li>
        <li class="high"><a href="/Wechat/check/addAdmin?username=${user.username}">添加管理员</a></li>
        <li><a href="/Wechat/check/addParts?username=${user.username}">添加配件</a></li>
        <li><a href="/Wechat/check/addMaintenanceType?username=${user.username}">添加维护类型</a></li>
        <li><a href="/Wechat/check/addMaintenanceRecord?username=${user.username}">添加维护记录</a></li>
        <li><a href="/Wechat/check/addServiceType?username=${user.username}">添加维修类型</a></li>
        <li><a href="/Wechat/check/addLocation">添加地点</a></li>
      </ul>
     <!--  <ul>
        <li>Home</li>
        <li class="admin">Machine</li>
        <li class="admin">User</li>
        <li>Activity</li>
        <li>Training</li>
        <li>Job</li>
        <li>Maintain</li>
        <li>Upgrading</li>
        <li>Releasing</li>
        <li>Management</li>
      </ul> -->
    </div>
  </div>
</div>
<div class="content" style="background:url(/Wechat/images/bj_03.jpg) top no-repeat; margin-top:-120px; ">
 <!--  <div class="wrapw" id="wrapw" style="background:url(/Wechat/images/tab_bj2.jpg) top no-repeat; margin-top:-120px;  ">
    <div class="tabs"> <a href="#" hidefocus="true" class="active" id="dl" >个人登录</a> <a href="#" hidefocus="true" id="zc" ><a>团务平台登录</a> <a>团员登录请用手机</a></div>
    <div class="swiper-container">
      <div class="swiper-wrapper" style="width: 540px; height: 325px;">
        
        <div class="swiper-slide swiper-slide-visible swiper-slide-active" style="width: 540px; height: 325px;">
          <div class="content-slide">
            <div style="margin:0 auto;">
              <div style="float:left; width:300px; margin:5px 45px;">
                <div style="float:left; font-size:14px; font-family:'微软雅黑'; line-height:24px">用户名：</div>
                <div style="float:left">
                  <input style="border:1px solid #c8c9c9; height:24px" name="userId" id="userId" value=""/>
                </div>
              </div>
              <div style="float:left; width:300px; margin:5px  45px;">
                <div style="float:left; font-size:14px; font-family:'微软雅黑'; line-height:24px">密&nbsp;&nbsp;&nbsp;码：</div>
                <div style="float:left">
                  <input style="border:1px solid #c8c9c9;height:24px; margin-left:2px;" id="password" name="password" type="password"/>
                </div>
                <br></br>
                <br></br>
                <div style="float:left; font-size:14px; font-family:'微软雅黑'; line-height:24px">验证码：</div>
                <div style="float:left;">
                  <input style="border:1px solid #c8c9c9;height:24px; width:80px" id="code" name="code"/>
                  <iframe src="image.jsp" id="ifrname1" name="ifrname1" width="130" height="26" frameborder="0" style="vertical-align:middle" marginwidth="0" marginheight="0" scrolling="no"> </iframe>
                </div>
              </div>
            </div>
            
            <a style="width:120px; float:left; margin:5px 100px;background:#FF5B66;height:50px;display:block;color:#fff;font-size:20px;line-height:50px;text-align:center;cursor:pointer;" onclick="doLogin()">登录 </a></div>
        </div>
      </div>
    </div>
  </div> -->
</div>
<div style="bottom: 18px; position: fixed; right: 15px; overflow: hidden">
  <div style="font-size: 13px; text-align: center; margin-bottom: 3px">“上海磐宏”微信公众号</div>
  <a href="#" target="_parent" style="display: block; text-align: center;"><img style="width: 135px; height: 135px" src="/Wechat/images/erweima.jpg"/></a>
  </div>


<script>
var s= "";if(s!=null&&s!="")alert(s);

var flag= "";
if(flag=="1")
{
	document.getElementById("userId").value="";
}
if(flag=="2")
{
	document.getElementById("username").value="";
	document.getElementById("idcard").value="";

}

var tabsSwiper = new Swiper('.swiper-container',{
	speed:500,
	onSlideChangeStart: function(){
		$(".tabs .active").removeClass('active');
		$(".tabs a").eq(tabsSwiper.activeIndex).addClass('active');
	}
});

$(".tabs a").on('touchstart mousedown',function(e){
	e.preventDefault()
	$(".tabs .active").removeClass('active');
	$(this).addClass('active');
	tabsSwiper.swipeTo($(this).index());
});


	function doLogin(){
		var userId = document.getElementById("userId").value;
		var password = document.getElementById("password").value;
		var code = document.getElementById("code").value;
		if(userId==""||userId==null){
			alert("用户名不能为空！");
			document.getElementById("userId").focus();
			return;
		}
		if(password==""||password==null){
			alert("密码不能为空！");
			document.getElementById("password").focus();
			return;
		}
		if(code==""||code==null){
			alert("验证码不能为空！");
			document.getElementById("code").focus();
			return;
		}
		var action = "/login.jsp?flag=1";

		document.all.forms.action = action;

		document.all.forms.submit();
	}
	function doLoginTY(){
		var username = document.getElementById("username").value;
		var idcard = document.getElementById("idcard").value;
		<!--var mobile = document.getElementById("mobile").value;-->
		var code1 = document.getElementById("code1").value;
		if(username==""||username==null){
			alert("用户名不能为空！");
			document.getElementById("username").focus();
			return;
		}
		if(idcard==""||idcard==null){
			alert("身份证号不能为空！");
			document.getElementById("idcard").focus();
			return;
		}

		if(code1==""||code1==null){
			alert("验证码不能为空！");
			document.getElementById("code1").focus();
			return;
		}
		var action = "/login.jsp?flag=2";

		document.all.forms.action = action;

		document.all.forms.submit();
	}
	function refreshImg(){
		document.getElementById("ifrname").src = "image1.jsp";
		document.getElementById("ifrname1").src = "image.jsp";
	}

</script>
	<div id="cntvlive2-is-installed"></div>
	<div style="float:left; margin-bottom:6px; margin-left:45px;">
	    <h5>"咨询热线：400 821 5854 ，热线受理时间：工作日 09:00-18:00" </h5>
	</div>
</body>
</html>