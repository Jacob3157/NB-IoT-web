<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>主页</title>
	<link rel="stylesheet" type="text/css" href="/Wechat/js/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="/Wechat/js/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="/Wechat/css/header.css">
	<link rel="stylesheet" type="text/css" href="/Wechat/js/easyui/demo.css">
	<script type="text/javascript" src="/Wechat/js/easyui/jquery.min.js"></script>
	<script src="/Wechat/js/jquery.json.js"></script>
	<script type="text/javascript" src="/Wechat/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" >
	$(document).ready(function () {
		
		$.extend($.fn.datagrid.methods, {
		    editCell : function(jq, param) {
		        return jq.each(function() {
		            var opts = $(this).datagrid('options');
		            var fields = $(this).datagrid('getColumnFields', true).concat(
		                    $(this).datagrid('getColumnFields'));
		            for ( var i = 0; i < fields.length; i++) {
		                var col = $(this).datagrid('getColumnOption', fields[i]);
		                col.editor1 = col.editor;
		                if (fields[i] != param.field) {
		                    col.editor = null;
		                }
		            }
		            $(this).datagrid('beginEdit', param.index);
		            for ( var i = 0; i < fields.length; i++) {
		                var col = $(this).datagrid('getColumnOption', fields[i]);
		                col.editor = col.editor1;
		            }
		        });
		    }
		});

		var editIndex = undefined;
		//结束编辑 
		function endEditing() {
		    if (editIndex == undefined) {
		        return true
		    }
		    if ($('#dg').datagrid('validateRow', editIndex)) {
		        $('#dg').datagrid('endEdit', editIndex);
		        editIndex = undefined;
		        return true;
		    } else {
		        return false;
		    }
		}
		//单击单元格 
		function onClickCell(index, field) {
			
		    if (endEditing()) {
		        $('#dg').datagrid('selectRow', index).datagrid('editCell', {
		            index : index,
		            field : field
		        });
		        editIndex = index;
		    }
		}
		
		function loadFilter(data){
			if(data.rows[0]==null){
				alert("未查询到用户！");
				return;
			}else{
				return data;
			}
				
		}
		var queryContent=null;
		var type=null;
		var property=[{"id":"1","text":"未注册用户"},{"id":"2","text":"注册用户"},{"id":"3","text":"管理员"},{"id":"8","text":"优惠用户"},{"id":"10","text":"工作人员"}];
		function init(){
			$('#dg').datagrid({
				title:'用户信息查询', 
				idField: 'id',
				rownumbers: true,
                singleSelect: false,
                url: '',
                checkOnSelect:true,
                selectOnCheck:true,
                autoRowHeight: true,
                pagination: true,
                fitColumns: true,
                toolbar: '#tb',
                loadFilter:loadFilter,
                onClickCell:onClickCell,
                loadMsg:"Processing, please waiting...",
                queryParams: {queryContent:queryContent, type:type},
                columns: [[
          				{ field: 'id',checkbox:true,align:'center',hidden:'hidden'},
                        { field: 'openid', title: "openid", width: '24%'},
                        { field: 'name', title: "姓名", width: '6%'},
                        { field: 'nickname', title: "昵称", width: '10%'},
                        { field: 'school', title: "单位", width: '5%'},
                        { field: 'studentID', title: "学号", width: '8%'},
                        { field: 'telephone', title: "电话", width: '11%'},
                        { field: 'property', title: "用户属性", width: '8%',
                       		formatter:function(value,row,index){
                          		if(value==null){
                          			for(var i=0;i<property.length;i++){
	                         			if(row['userProperty']['userProperty']==property[i].id){
	                         				value=property[i].id;
	                         			};
                          			}
                          		}
                          		for(var i=0;i<property.length;i++){
                          			if(property[i].id==value){
                          				return property[i].text;
                          			}
                          		}
                          		return value;
                          	}},
                       	{field:'totalTimes',title:'已洗衣总次数',width:'10%',formatter:function(value,rows,index){
                       		if(value!=null){
                       			return parseInt(value,10);
                        	  }else{
                        		  return 0;
                        	  }
                       	}},
                       	{field:'times',title:'优惠总次数',width:'10%',formatter:function(value,rows,index){
                       		if(value!=null){
                       			return parseInt(value,10);
                        	  }else{
                        		  return 0;
                        	  }
                       	}},
                       	{field:'remainingTimes',title:'剩余优惠次数',width:'10%',align:'center'}
                      ]]
            });
			
		}
		
		
	
		
		
		
		
		
		$(function(){
			
		   init();
		   
		   $("#search").click(function(){
				var queryParameter = $('#dg').datagrid("options").queryParams;
				var queryContent=$("#queryContent").val();
				if(queryContent==null || queryContent==''){
					alert('请输入查询参数！');
					return;
				}
	            queryParameter.queryContent = queryContent;  
	            queryParameter.type = $("#type").val();
	            $('#dg').datagrid("options").url='/Wechat/select/userinfo';
	            $("#dg").datagrid("load");  
			});
		   
	
		   }); 
		
	});
	
	</script>
</head>
<body>
	
	<table id="dg">
	</table>
	
	<div id="tb" style="padding:2px 5px;">
		查询内容: 
		<select id="type" class="easyui-combobox" panelHeight="auto" style="width:100px">
			<option value="0" selected="selected">openid</option>
			<option value="1">姓名</option>
			<option value="2">昵称</option>
			<option value="3">学号</option>
			<option value="4">电话</option>
		</select>
		查询: <input class="easyui-textbox"   style="width:220px" id="queryContent">
		<a href="#" class="easyui-linkbutton" iconCls="icon-search" id="search">Search</a>
	</div>
	
</body>
</html>