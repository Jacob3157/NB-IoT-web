<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/Wechat/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="/Wechat/js/jquery.form.js"></script>
<script type="text/javascript">
$(function(){
 $("#upimage").bind("click",function(){
 if($("#upfile").val()==""){
  alert("请选择一个图片文件,再点击");
  return;
 }
 $("#form1").ajaxSubmit({
  url:"/Wechat/add/photoUpload",
  type:"POST",
  beforeSubmit:function(){
	  $("#im1").val("开始上传");
  },
  success:function(response){
	  alert(response);
	  $("#ln").empty();
	  $("#ln").append("<img src='/Wechat/"+response+"' style='width:100px;height:60px'/>");
	  $("#im1").val(response);
  	},
  error:function(msg){
  alert("出错了");
  }
 });
 });
 
});
</script>
</head>
<body>
文件上传
<form enctype="multipart/form-data" id="form1" method="post" action="">
文件:
 <input type="file" name="upfile" id="upfile"><input type="button" id="upimage" value="图片上传1">
 <input type="text" name="im1" id="im1" value="" />
</form>
<div id="ln">tu</div>
</body>
</html>