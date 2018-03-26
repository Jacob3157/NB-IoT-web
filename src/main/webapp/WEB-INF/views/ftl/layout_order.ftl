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
		
		var sign=0;
		
		var fileName=null;
		//获取浏览器页面可见高度和宽度
		var _PageHeight = document.documentElement.clientHeight,
		  _PageWidth = document.documentElement.clientWidth;
		//计算loading框距离顶部和左部的距离（loading框的宽度为215px，高度为61px）
		var _LoadingTop = _PageHeight > 61 ? (_PageHeight - 61) / 2 : 0,
		  _LoadingLeft = _PageWidth > 215 ? (_PageWidth - 215) / 2 : 0;
		//在页面未加载完毕之前显示的loading Html自定义内容
		var _LoadingHtml = '<div id="loadingDiv" style="position:absolute;left:0;width:100%;height:' + _PageHeight + 'px;top:0;background:#f3f8ff;opacity:0.8;filter:alpha(opacity=80);z-index:10000;"><div style="position: absolute; cursor1: wait; left: ' + _LoadingLeft + 'px; top:' + _LoadingTop + 'px; width: auto; height: 57px; line-height: 57px; padding-left: 50px; padding-right: 5px; background: #fff url(/Wechat/images/loading_new.gif) no-repeat scroll 5px 10px; border: 2px solid #95B8E7; color: #696969; font-family:\'Microsoft YaHei\';">页面加载中，请等待...</div></div>';
		//呈现loading效果
		
		//加载状态为complete时移除loading效果
		function completeLoading() {
		 var loadingMask = document.getElementById('loadingDiv');
		 loadingMask.parentNode.removeChild(loadingMask);
		}
		
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
			$('#dg').datagrid('selectRow', index);
			var row=$('#dg').datagrid('getSelected');
			if(row.isChecked=='Yes'){
				return;
			}
		    if (endEditing()) {
		    	$('#dg').datagrid('selectRow', index).datagrid('editCell', {
		            index : index,
		            field : field
		        });
		        editIndex = index;
		    }
		}
		
		 function onDblClickRow(index,row){
         	$('#dlg').dialog('open');
    			$('#ff').form('clear');
    		    $('#ff').form('load',{
    			openid:row['wechatID']['openid'],
    			nickname:row['wechatID']['nickname'],
    			name:row['wechatID']['name'],
    			phone:row['wechatID']['telephone'],
    			studentId:row['wechatID']['studentID'],
    			school:row['wechatID']['school'],
    			building:row['machineID']['building'],
    			machineName:row['machineID']['machineName'],
    			/* times:row['totalTimes'],
    			timesOfUsing:row['times'], */
    		});
		 }
		
		
		var fileview = $.extend({}, $.fn.datagrid.defaults.view, { onAfterRender: function (target) { isCheckedItem(); } });
		var startTime=null;
		var endTime=null;
		var chargebackInfo=[{"id":"0","text":"操作失误"},{"id":"1","text":"测试员"},{"id":"2","text":"首次优惠"},{"id":"3","text":"不建议退款"},{"id":"4","text":"管理员"},{"id":"5","text":"优惠用户"}];
		var reason=[];
		var checkedItems=[];
		function init(){
			$('#dg').datagrid({
				title:'退单管理',
				view:fileview,
				idField: 'orderID',
				rownumbers: true,
                singleSelect: false,
                url: '',
                checkOnSelect:true,
                selectOnCheck:true,
                autoRowHeight: true,
                pagination: false,
                fitColumns: true,
                toolbar: '#tb',
                onDblClickRow:onDblClickRow,
                onClickCell:onClickCell,
                loadMsg:"Processing, please waiting...",
                queryParams: { startTime:startTime, endTime:endTime,mark:'1'},
                columns: [[
				{ field: 'orderID',checkbox:true,align:'center'},
                { field: 'out_trade_no', title: "订单编号", width: '21%'},
                { field: 'time_end', title: "订单日期", width: '14%'},
                { field: 'name', title: "真实姓名", width: '6%',formatter:function(value,rows,index){
                	return rows.wechatID.name;
                }},
                { field: 'openid', title: "openid", width: '12%',formatter:function(value,rows,index){
                	return rows.wechatID.openid.slice(16);
                }},
                { field: 'total_fee', title: "总额", width: '5%'},
                { field: 'building', title: "地点", width: '5%',formatter:function(value,rows,index){
                	return rows.machineID.building;
                }},
                { field: 'machine', title: "机器名称", width: '9%',formatter:function(value,rows,index){
                	return rows.machineID.machineName;
                }},
                { field: 'trade_state', title: "状态", width: '7%'},
                { field: 'chargeback', title: "退款建议", width: '8%',
                	editor:{type:'combobox',options:{valueField:'id',textField:'text',data:chargebackInfo}},
                	formatter:function(value,row,index){
                		for(var i=0;i<chargebackInfo.length;i++){
                			if(chargebackInfo[i].id==value){
                				return chargebackInfo[i].text;
                			}
                		}
                			return row['chargeback'];
                	}
                },
                { field: 'isChecked', title: "检查", width: '4%',formatter:function(value,rows,index){
                	if(value!=null && value=='Yes'){
						sign=1;                		
                		return value;
                	}else{
                		return 'No';
                	}
                }},
                { field: 'isGenerated', title: "生成", width: '4%'},
            ]],
            onCheck:function(rows){
            	addCheckItem();
            },
            onUncheck:function(rowIndex,rowData){
            	removeSingleItem(rowIndex, rowData);
            },
            });
			
			
		}
		
		function addCheckItem(){
			var rows=$('#dg').datagrid('getChecked');
			for(var i=0;i<rows.length;i++){
				if (findCheckedItem(rows[i].orderID) == -1) {
					reason.push(rows[i].chargeback);
                    checkedItems.push(rows[i].orderID);
                }  
			}
		}
		
		function findCheckedItem(ID) {  
            for (var i = 0; i < checkedItems.length; i++) {  
                if (checkedItems[i] == ID) return i;  
            }  
            return -1;  
        }
		
		function getNowTime(){
			var date=new Date();
		    var month = date.getMonth() + 1;
		    var strDate = date.getDate();
		    if (month >= 1 && month <= 9) {
		        month = "0" + month;
		    }
		    if (strDate >= 0 && strDate <= 9) {
		        strDate = "0" + strDate;
		    }
		    var currentdate = date.getFullYear()+""+ month + strDate;
		    return currentdate;
		}
		
		function isCheckedItem(){
			for(var i=0;i<checkedItems.length;i++){
				$('#dg').datagrid('selectRecord', checkedItems[i]); 
			}
		}
		
		 function removeSingleItem(rowIndex, rowData) {  
	            var k = findCheckedItem(rowData.orderID);  
	            if (k != -1) {  
	                checkedItems.splice(k, 1);
	                reason.splice(k, 1);
	            }  
	         }
		 function downloadFile(){
			 var url="/Wechat/check/download?fileName="+fileName;
			 window.location.href=url;
		 }
		
		$(function(){
		   init();
		   $('#dlg').dialog('close');
		   $(".datagrid-header-check").html("");
		   
		   var level=parseInt(${(admin.level)!null});
 		  	if(level<12){
 		  	alert("请务必先升级用户！");
 		  	$("#dg").datagrid("hideColumn", "orderID"); // 设置隐藏列
 		  	//$("#dg").datagrid("hideColumn", "isChecked");  设置隐藏列
 		  	$("#dg").datagrid("hideColumn", "isGenerated"); // 设置隐藏列
 		  	$("#generateFile").hide();
 		   }
 		  	
 		  	
		   $("#search").click(function(){
			   
			   if($("#startTime").val()==null || $("#endTime").val()==null || $("#endTime").val()=='' || $("#startTime").val()==''){
				   alert("请选择订单日期！");
				   return;
			   }
			   
				var queryParameter = $('#dg').datagrid("options").queryParams;
				 $('#dg').datagrid("options").url='/Wechat/select/chargeback';
	            queryParameter.startTime = $("#startTime").val();  
	            queryParameter.endTime = $("#endTime").val();
	            $("#dg").datagrid("load");
			});
		   
		   $("#generateFile").click(function(){
			   
			   if(sign==0){
				   alert("请保存退款信息！");
				   return;
			   }
			   
			   if(checkedItems.length==0){
				   alert("请选择退款订单！");
				   return;
			   }
			   document.write(_LoadingHtml);
			   fileName=getNowTime()+".txt";
	    		$.ajax({
	    			type: "POST",
	    			traditional: true,
	    			url: "/Wechat/check/generateFile" ,
	    			data: {checkedItem:checkedItems,reason:reason,fileName:fileName},
	    			dataType:"text",
	    			success: function(data) {
	    				if(data=="success"){
	    					completeLoading();
	    					if(confirm("点击确定开始下载！")){
	    						downloadFile();
	    					}
	    				}else{
	    					alert("获取失败");
	    					completeLoading();
	    				}
	    				
	    				}
	    			});  
			});
		   
		   $("#saveFile").click(function(){
			  
			   if(confirm("确认保存后，将无法修改！！！")){
				   sign=1;
				   var rows=$("#dg").datagrid("getRows");
				   var ids=[];
				   var reasons=[];
				   for(var i=0;i<rows.length;i++){
					   ids.push(rows[i].orderID);
					   reasons.push(rows[i].chargeback);
				   }
		    		$.ajax({
		    			type: "POST",
		    			traditional: true,
		    			url: "/Wechat/update/saveChargebackFile" ,
		    			data: {ids:ids,reasons:reasons},
		    			dataType:"text",
		    			success: function(data) {
		    				if(data=="success"){
		    					alert("保存成功！");
		    				}else{
		    					alert("保存失败");
		    				}
		    				
		    				}
		    			});  
			   }else{
				  return; 
			   }
			  
			   
			});
		   
		   }); 
	});
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
		Date From: <input class="easyui-datebox"   style="width:110px" id="startTime" data-option="required:true">
		To: <input class="easyui-datebox" style="width:110px" id="endTime" data-option="required:true">
		Type: 
		<select id="type" class="easyui-combobox" panelHeight="auto" style="width:100px">
			<option value="all">全部</option>
			<!-- <option value="chargeback">退单</option>
			<option value="normal">正常</option> -->
		</select>
		<a href="#" class="easyui-linkbutton" iconCls="icon-search" id="search">Search</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-save" style="margin-left:20px" id="saveFile">保存退款信息</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-save" style="margin-left:20px" id="generateFile">生成退款文件</a>
	</div>
	<div id="dlg" class="easyui-dialog" title="用户详情" style="width:450px;height:480px;padding:10px"
			data-options="
				buttons: [{
					text:'取消',
					handler:function(){
						clearForm();
						$('#dlg').dialog('close');
					}
				}]
			">
		<form id="ff" method="post">
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" name="openid" style="width:100%" data-options="label:'openid:',readonly:true">
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" name="nickname" style="width:100%" data-options="label:'NickName:',readonly:true">
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" name="name" style="width:100%" data-options="label:'Name:',readonly:true">
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" name="studentId" style="width:100%" data-options="label:'StudentId:',readonly:true">
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" id="phone" name="phone" style="width:100%" data-options="label:'Phone:',readonly:true">
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" id="school" name="school" style="width:100%" data-options="label:'School:',readonly:true">
			</div>
			
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" id="building" name="building" style="width:100%" data-options="label:'Building:',readonly:true">
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" id="machineName" name="machineName" style="width:100%" data-options="label:'maName:',readonly:true">
			</div>
			
			<!-- <div style="margin-bottom:20px">
				<input class="easyui-textbox" id="timesOfUsing" name="timesOfUsing" style="width:100%" data-options="label:'已用次数:',readonly:true">
			</div> -->
		</form>
	</div>
</body>
</html>
