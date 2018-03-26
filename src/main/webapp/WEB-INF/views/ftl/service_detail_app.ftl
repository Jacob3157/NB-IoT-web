<!DOCTYPE html>
<html>
<head>
	<title>维修记录查询</title>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">

	<link rel="stylesheet" href="/Wechat/css/jquery-weui.css">
	<link rel="stylesheet" href="/Wechat/css/weui.css">
	<link rel="stylesheet" href="/Wechat/css/demos.css">
	
	<script src="/Wechat/js/jquery-2.1.4.js"></script>
	<script src="/Wechat/js/fastclick.js"></script>
	<script src="/Wechat/js/jquery-weui.js"></script>
	<script src="/Wechat/js/jquery.js"></script>
	<script type="text/javascript">

		function showdata(data) {
			console.log(data);
			page = data.totalPage;
			curPage = data.currentPage;
			pageSize = data.pageSize;
			begin =  (curPage-1)*pageSize;
			len = data.allRow;
			code = data.rows;
			$str = '<tr><th>序号</th><th>洗衣机编号</th><th>操作人：操作</th><th>操作时间</th><th>备注</th></tr>';
			if(code.length==0){
				alert("没有找到对应的数据");
				return;
			}
			for(var i = 0; i < code.length; i++) {
				var num = i + begin+1;
				//$str += '<div class="weui-flex"><div class="weui-flex__item"><div class="placeholder">序号：</div></div><div class="weui-flex__item"><div class="placeholder">'+num+'</div></div><div class="weui-flex__item"><div class="placeholder">型号</div></div><div class="weui-flex__item"><div class="placeholder">'+code[i]['model']+'</div></div></div><div class="weui-flex"><div class="weui-flex__item"><div class="placeholder">编号</div></div><div class="weui-flex__item"><div class="placeholder">'+code[i]['machineName']+'</div></div><div class="weui-flex__item"><div class="placeholder">学校</div></div><div class="weui-flex__item"><div class="placeholder">'+code[i]['operatinginfo']['school']+'</div></div></div><div class="weui-flex"><div class="weui-flex__item"><div class="placeholder">校区</div></div><div class="weui-flex__item"><div class="placeholder">'+code[i]['operatinginfo']['compus']+'</div></div><div class="weui-flex__item"><div class="placeholder">宿舍</div></div><div class="weui-flex__item"><div class="placeholder">'+code[i]['operatinginfo']['compus']+'</div></div></div>'
				$str+='<tr><td>'+num+'</td><td>'+code[i]['machineID']['machineName']+'</td><td>'+code[i]['maintenanceOperator']['name']+code[i]['maintenanceType']['maintenanceName']+'</td><td>'+code[i]['maintenanceOperator']['followDate']+'</td><td>'+code[i]['comment']+'</td></tr>';
				//$str += '<li><span><h1>序号：</h1><h2>'+num+'</h2>&nbsp;&nbsp;&nbsp;&nbsp;<h1>洗衣机编号：</h1><h2>'+code[i]['machineID']['machineName']+'</h2>&nbsp;&nbsp;&nbsp;&nbsp;<h1>操作人：</h1><h2>'+code[i]['maintenanceOperator']['name']+'</h2><br/><h1>操作类型：</h1><h2>'+code[i]['maintenanceType']['maintenanceName']+'</h2>&nbsp;&nbsp;&nbsp;&nbsp;<h1>地区：</h1><h2>'+code[i]['machineID']['building']+'</h2>&nbsp;&nbsp;&nbsp;&nbsp;<h1>操作时间：</h1><h2>'+code[i]['maintenanceOperator']['followDate']+'</h2>&nbsp;&nbsp;&nbsp;&nbsp;<h1>备注：</h1><h2>'+code[i]['comment']+'</h2></span></li>';
			}
		  $('#myul').html($str);
		}
		function GetQueryString(name){
			var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
			var r = window.location.search.substr(1).match(reg);

			if(r!=null)return  unescape(decodeURI(r[2])); return null;
		}
		$(function getInfo(pageNO, pageSize) {
			var school,compus,building;
			if(pageNO==null){
				pageNO = 0;
			}
			if(pageSize==null){
				pageSize=10;
			}
		// var myurl=new LG.URL(window.location.href);//js封装的url操作函数
		console.log(building);
		school = GetQueryString("school"); 
		compus = GetQueryString("compus");
		building1 = GetQueryString("machine");
		building = decodeURI(building1);//jquery解码函数
		$.ajax({
			type: "POST",
			dataType: "json",
					url: "/Wechat/check/getMaintenanceInfo",//路径
					data: {
						school: school,
						compus: compus,
						machineName: building,
						pageNo: 1,
						pageSize: pageSize
					},
					success: function(data) {
						showdata(data);
					}
				});
	});
</script>
</head>
<body>
	<div class="main">
		<div class="demo-header">
			<div class="demos-title">维修记录查询</div>
		</div>
		<div class="demos-content-padded tMn">
			<table  border="1">
				<tbody id="myul">

				</tbody>
			</table>
		</div>	
	</div>
	<div class="weui-msg__extra-area">
			<div class="weui-footer">
				<p class="weui-footer__links">
					<a href="javascript:void(0);" class="weui-footer__link">wxws.service.pioteks.io</a>
				</p>
				<p class="weui-footer__text">copyright © 2003-2017 wxws.service.pioteks.io</p>
			</div>
		</div>
</body>
</html>
