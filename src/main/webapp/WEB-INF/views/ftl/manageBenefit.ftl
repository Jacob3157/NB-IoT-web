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
		var startTime=null;
		var endTime=null;
		var usrProperty=null;
		var property=[{"id":"1","text":"未注册用户"},{"id":"2","text":"注册用户"},{"id":"3","text":"管理员"},{"id":"8","text":"优惠用户"},{"id":"10","text":"工作人员"}];
		function init(){
			$('#dg').datagrid({
				title:'优惠券列表', 
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
                onClickCell:onClickCell,
                loadMsg:"Processing, please waiting...",
                queryParams: {startTime:startTime, endTime:endTime,userProperty:usrProperty},
                columns: [[
          				{ field: 'id',checkbox:true,align:'center',hidden:'hidden'},
                          { field: 'openid', title: "openid", width: '24%',formatter:function(value,rows,index){
	                          return row['user']['openid'];
                          }},
                          { field: 'name', title: "姓名", width: '6%',formatter:function(value,rows,index){
	                          return row['user']['name'];
                          }},
                          { field: 'school', title: "学校", width: '5%'},
                          { field: 'studentID', title: "学号", width: '8%'},
                          { field: 'telephone', title: "电话", width: '11%'},
                          { field: 'followDate', title: "关注时间", width: '15%',formatter:function(value,rows,index){
	                          return unixToDate(value);
                          }},
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
                       	{field:'totalTimes',title:'总次数',width:'6%'},
                       	{field:'times',title:'优惠次数',width:'7%',formatter:function(value,rows,index){
                       		if(value!=null){
                       			return parseInt(value,10);
                        	  }else{
                        		  return 0;
                        	  }
                       	}},
                       	{field:'opt',title:'操作',width:'9%',align:'center',formatter:formatOper}
                      ]],
                      onLoadSuccess:function(data){  
                    	  $(".editcls").linkbutton({text:'添加优惠',plain:true,iconCls:'icon-add'});
                      },
                      onAfterEdit:function(rowIndex,rowData,changes){
                      	if(changes.property!=undefined && changes.property!='undefined'){
                      		var openid=rowData.openid;
                      		var userProperty=rowData.property;
                      		if(userProperty==null || userProperty==""){
                      			rowIndex=rowIndex+1;
                      			alert("第"+rowIndex+"行用户属性不可为空！");
                      			return;
                      		}
                      		$.ajax({
              	    			type: "POST",
              	    			traditional: true,
              	    			url: "/Wechat/update/userProperty" ,
              	    			data: {openid:openid,userProperty:userProperty},
              	    			dataType:"text",
              	    			success: function(data) {
              	    				if(data=="success"){
              	    					alert("修改成功");
              	    				}else{
              	    					alert("修改失败");
              	    				}
              	    				
              	    				}
              	    			});  
                      	}}
            
            });
			
		}
		
		
		
		
		
		$(function(){
			
		   $('#dlg').dialog('close');
		   init();
		   $('#dlg').dialog('close');
		   var level=parseInt(${(user.level)!null});
		   if(level<=12){
			   var e = $("#dg").datagrid('getColumnOption', 'property');
               e.editor = [];
               
               $("#dg").datagrid("hideColumn", "opt"); // 设置隐藏列
		   }
		   
		   $("#search").click(function(){
				var queryParameter = $('#dg').datagrid("options").queryParams;  
	            queryParameter.startTime = $("#startTime").val();  
	            queryParameter.endTime = $("#endTime").val();
	            queryParameter.userProperty=$('#type').val();
	            $('#dg').datagrid("options").url='/Wechat/check/user';
	            $("#dg").datagrid("load");  
			});
		   
	
		   }); 
		
	});
	
	function formatOper(value, row, index){
   		var btn = '<a class="editcls" onclick="addDiscount(\''+index+'\')" href="#">添加优惠</a>';  
        return btn;
	};
	
	function addDiscount(index){
		
		 $('#dg').datagrid('clearSelections');
		 $('#dg').datagrid('selectRow', index);
		 var row=$('#dg').datagrid('getSelected');
		 var startTime=null;
		 var endTime=null;
		 if(row['startTime']==null){
			 startTime=null;
		 }else{
			 startTime=unix2Date(row['startTime']);
		 }
		 if(row['endTime']==null){
			 endTime=null;
		 }else{
			 endTime=unix2Date(row['endTime']);
		 }
		 $('#dlg').dialog('open');
		 $('#ff').form('clear');
		  $('#ff').form('load',{
			openid:row['openid'],
			nickname:row['nickname'],
			studentId:row['studentID'],
			/* times:row['totalTimes'],
			timesOfUsing:row['times'], */
		});
		 /* $("#start").datebox("setValue",startTime);
		 $("#end").datebox("setValue",endTime);  */
	};
	
	function submitForm(){
		$('#ff').form('submit',{
			url:'/Wechat/add/userOff',
		    onSubmit: function(){
		    	if($("#start").val()==null || $("#start").val()==''){
		    		alert("开始时间不能为空！");
		    		return false;
		    	}
		    	if($("#end").val()==null || $("#end").val()==''){
		    		alert("结束时间不能为空！");
		    		return false;
		    	}
		    	if($("#times").val()==null || $("#times").val()==''){
		    		alert("次数不能为空！");
		    		return false;
		    	}
		        return confirm("确认提交吗？");
		    },
		    success:function(data){
		        alert(data);
		        clearForm();
				$('#dlg').dialog('close');
		    }
		});
	}
	
	function clearForm(){
		$('#ff').form('clear');
	}
	
	function unixToDate(unixTime) {
        var time = new Date(unixTime);
        var ymdhis = "";
        ymdhis += time.getUTCFullYear() + "-";
        ymdhis += (time.getUTCMonth()+1) + "-";
        ymdhis += time.getUTCDate();
        ymdhis += " " + time.getHours() + ":";
        ymdhis += time.getUTCMinutes() + ":";
        ymdhis += time.getUTCSeconds();
        return ymdhis;
    }
	
	function unix2Date(unixTime) {
        var time = new Date(unixTime);
        var ymdhis = "";
        ymdhis += (time.getUTCMonth()+1) + "/";
        ymdhis += time.getUTCDate() + "/";
        ymdhis += time.getUTCFullYear();
        return ymdhis;
    }
	
	</script>
</head>
<body>
	
	<table id="dg">
	</table>
	
	<div id="tb" style="padding:2px 5px;">
	注册起始时间: <input class="easyui-datebox"   style="width:110px" id="startTime">
		截止时间: <input class="easyui-datebox" style="width:110px" id="endTime">
		用户属性: 
		<select id="type" class="easyui-combobox" panelHeight="auto" style="width:100px">
			<option value="0" selected="selected">全部用户</option>
			<option value="1">未注册用户</option>
			<option value="2">注册用户</option>
			<option value="3">管理员</option>
			<option value="8">优惠用户</option>
			<option value="10">工作人员</option>
		</select>
		<a href="#" class="easyui-linkbutton" iconCls="icon-search" id="search">Search</a>
		<!-- <a href="#" class="easyui-linkbutton" iconCls="icon-save" style="margin-left:20px">保存</a> -->
	</div>
	
	<div id="dlg" class="easyui-dialog" title="添加优惠" style="width:400px;height:400px;padding:10px"
			data-options="
				buttons: [{
					text:'保存',
					iconCls:'icon-ok',
					handler:function(){
						submitForm();
					}
				},{
					text:'取消',
					handler:function(){
						clearForm();
						$('#dlg').dialog('close');
					}
				}]
			">
		<!-- <form id="ff" method="post">
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" name="openid" style="width:100%" data-options="label:'openid:',required:true,readonly:true">
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" name="nickname" style="width:100%" data-options="label:'Name:',required:true,readonly:true">
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" name="studentId" style="width:100%" data-options="label:'StudentId:',readonly:true">
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-datebox" id="start" name="start" style="width:100%" data-options="label:'优惠开始时间:',required:true">
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-datebox" id="end" name="end" style="width:100%" data-options="label:'优惠结束时间:',required:true">
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" id="times" name="times" style="width:100%" data-options="label:'优惠次数:',required:true">
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" id="timesOfUsing" name="timesOfUsing" style="width:100%" data-options="label:'已用次数:',readonly:true">
			</div>
		</form> -->
	</div>
</body>
</html>