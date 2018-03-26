<!DOCTYPE html>
<!-- saved from url=(0108)http://localhost:8080/Wechat/check/washer_detail_app?school=SHU&compus=Baoshan&building=%E5%8D%97%E5%8C%BA14 -->
<html class="pixel-ratio-2 retina ios ios-9 ios-9-1 ios-gt-8 ios-gt-7 ios-gt-6">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
  <title>上海磐宏.我.个人中心</title> 
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
  <script type="text/javascript"></script> 
 <style type="text/css">
    /*用户信息界面 字体问题*/
  h1,h2,h3,h4,h5,h6 {font-size:80%;font-weight:normal;}
  </style>
</head> 
<body  style="background: #FFDAB9;">
  <div class="col-xs-12" style="background-color: #008800;text-align: center;display: inline;font-size: 2em"><span class="glyphicon glyphicon-user" style="vertical-align:middle;color:#FFF">用户中心</span></div> 
  <div class="container" style="background: #FFDAB9;">
    <div class="row idcard " style="background-color: #FFFFFF;border-radius: 5px;margin-top: 5%" >
      <div class="pic col-xs-4" style="margin-top: 8px">
        <img  src="/Wechat/images/pic1.jpg" class="img-thumbnail">
      </div>
      <div class="info-basic col-xs-8" style="background-color: #FFFFFF;">
        <div style="text-align: left;">
          <h4 class = "col-xs-4" style="display: inline;"><strong>姓名:</strong></h4><h4 class="col-xs-7" style="display: inline;"><strong>${user.name!"请注册！"}</strong></h4>
          <hr>
        </div>
        <div style="text-align: left;">
          <h4 class = "col-xs-4" style="display: inline;"><strong>学号:</strong></h4><h4 class="col-xs-7" style="display: inline;"><strong>${user.studentID!""}</strong></h4>
          <hr>
        </div>
        <div style="text-align: letter-spacing: ;">
          <h4 class = "col-xs-4" style="display: inline;;"><strong>电话:</strong></h4><h4 class="col-xs-7" style="display: inline;"><strong>${user.telephone!""}</strong></h4>
          <hr>
        </div>
      </div>
    </div>
     <div class="info-washing col-xs-12 " style="margin-bottom: 0%; background-color: #FFDAB9;text-align:center;padding-top: 8%;">
        <div class="col-xs-3" style="text-align: left;">
            <div type="button" class="btn btn-danger btn-circle btn-lg" style="margin-top: 15%"> 
              <h5 style="text-align: center;"><strong>已经洗衣</strong></h5>
              <h3><strong>${user.totalTimes!"0" }</strong></h3>
          </div>
        </div>
        <div class="col-xs-3" style="text-align: right; margin-top: 30%">
            <div type="button" class="btn btn-info btn-circle btn-lg" style="margin-top: 15%"> 
              <h5 style="text-align: center;"><strong>已经优惠</strong></h5>
              <h3><strong>${user.times!"0" }</strong></h3>
          </div>
        </div>
        <div class="col-xs-3" style="text-align: left;">
            <div type="button" class="btn btn-success btn-circle btn-lg" style="margin-top: 15%"> 
              <h5 style="text-align: center;"><strong>剩余优惠</strong></h5>
              <h3><strong>${remaingTimes!"0" }</strong></h3>
          </div>
        </div>
      </div>
  </div>
     <h3 class = "col-xs-12" style="display: inline;text-align: center;position: absolute;bottom: 6%"><strong>欢迎使用微信自助洗衣系统</strong></h3>
     <h5 class = "col-xs-12" style="display: inline;text-align: center;position: absolute;bottom: 3%"><strong> copyright © 2003-2018 wxws.information.pioteks.io</strong></h5>
</body>
</html>