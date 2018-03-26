<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>添加功能</title>
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
				url:"/Wechat/add/function",
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
	<div class="easyui-panel" title="功能添加" style="width:100%;max-width:400px;padding:30px 60px;">
		<form id="ff" class="easyui-form" method="post" data-options="novalidate:true">
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" name="name" style="width:100%" data-options="label:'Name:',required:true">
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" name="url" style="width:100%" data-options="label:'URL:',required:true">
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" name="parentId" style="width:100%" data-options="label:'父级菜单编号:',required:true">
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" name="serialNum" style="width:100%" data-options="label:'序列号:',required:true">
			</div>
			<div style="margin-bottom:20px">
				<select class="easyui-combobox" name="accordion" label="是否顶级" style="width:100%"><option value="1" selected="selected">是</option><option value="0">否</option></select>
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