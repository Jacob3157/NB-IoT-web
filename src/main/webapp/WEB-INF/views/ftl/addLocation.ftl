<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>添加洗衣机放置点</title>
	<link rel="stylesheet" type="text/css" href="/Wechat/js/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="/Wechat/js/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="/Wechat/js/easyui/demo.css">
	<script type="text/javascript" src="/Wechat/js/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="/Wechat/js/easyui/jquery.easyui.min.js"></script>
	<script>
	$(document).ready(function(){
				
	});
	
		function submitForm(){
			$('#ff').form('submit',{
				url:"/Wechat/add/location",
				onSubmit:function(){
					return $(this).form('enableValidation').form('validate');
				},
				success: function(data){
					if(data=="success"){
						alert("添加成功");
						clearForm();
					}else{
						alert("添加失败，请联系管理员");
					}
				}
			});
		}
		
		function clearForm(){
			$('#ff').form('clear');
		}
	</script>
</head>
<body >
	<div  id="mydiv" style="margin-left:25%">
	<div style="margin:20px 0;"></div>
	<div class="easyui-panel" title="放置地点添加" style="width:100%;max-width:400px;padding:30px 60px;">
		<form id="ff" class="easyui-form" method="post" data-options="novalidate:true" accept-charset="utf-8" onsubmit="document.charset='utf-8';">
			<div style="margin-bottom:20px">
				<select class="easyui-combobox" name="school" label="学校" style="width:100%">
					<option value="SHU" selected="selected">上海大学</option>
				</select>
			</div>
			<div style="margin-bottom:20px">
				<select class="easyui-combobox" name="compus" label="校区" style="width:100%">
					<option value="Baoshan" selected="selected">宝山校区</option>
					<option value="Yanchang">延长校区</option>
					<option value="Jiading">嘉定校区</option>
				</select>
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" name="building" style="width:100%" data-options="label:'楼幢:',required:true">
			</div>
		</form>
		<div style="text-align:center;padding:5px 0">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width:80px">Submit</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="width:80px">Clear</a>
		</div>
	</div>
	</div>
</body>
</html>