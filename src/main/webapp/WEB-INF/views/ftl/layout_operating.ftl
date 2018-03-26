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
		
		function init(){
			$('#dg').datagrid({
				title:'单价管理',
				idField: 'id',
				rownumbers: true,
                singleSelect: false,
                url: '/Wechat/select/allOperating',
                checkOnSelect:true,
                selectOnCheck:true,
                autoRowHeight: true,
                pagination: false,
                fitColumns: true,
                toolbar: '#tb',
                onClickCell:onClickCell,
                loadMsg:"Processing, please waiting...",
                queryParams: { school:""},
                columns: [[
				{ field: 'id',checkbox:true,align:'center',hidden:'hidden'},
                { field: 'school', title: "学校", width: '9%'},
                { field: 'compus', title: "校区", width: '9%'},
                { field: 'washingPrice', title: "洗衣单价", width: '9%',
                	editor:{type:'numberbox',options:{precision:2,required:true}},
                	formatter:function(value,row,index){
                	return value;
                }},
                { field: 'modifyTime', title: "上次修改时间", width: '15%',formatter:function(value,row,index){
                	return unixToDate(row.latestModifyTime);
                }},
                { field: 'modifyWorker', title: "上次修改人", width: '9%',formatter:function(value,rows,index){
                	if(rows.latestModifyWorker==null){
                		return null;
                	}else{
	                	return rows.latestModifyWorker.username;
                	}
                }}
            ]],
            onAfterEdit:function(rowIndex,rowData,changes){
              	if(changes.washingPrice!=undefined && changes.washingPrice!='undefined'){
              		var washingPrice=rowData.washingPrice;
              		var school=rowData.school;
              		var compus=rowData.compus;
              		$.ajax({
      	    			type: "POST",
      	    			traditional: true,
      	    			url: "/Wechat/update/opratingInfo" ,
      	    			data: {washingPrice:washingPrice,school:school,compus:compus},
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
		
		$(function(){		    
		   init();
		   $(".datagrid-header-check").html("");
		   
		   var level=parseInt(${(admin.level)!null});
 		  	if(level<12){
 		  		var e = $("#dg").datagrid('getColumnOption', 'washingPrice');
                e.editor = [];
 		   }
 		  	
		});
		  
		
		
		
	});
	</script>
</head>
<body>
	
	<table id="dg">
	</table>
	
	<div id="tb" style="padding:2px 5px;">
		Type: 
		<select id="type" class="easyui-combobox" panelHeight="auto" style="width:100px">
			<option value="all">全部</option>
			<!-- <option value="chargeback">退单</option>
			<option value="normal">正常</option> -->
		</select>
		<a href="#" class="easyui-linkbutton" iconCls="icon-search" id="search">Search</a>
	</div>
</body>
</html>