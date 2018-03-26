<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>配件一览</title>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<script src="/Wechat/js/jquery-3.2.1.min.js"></script>
	<link rel="stylesheet" href="/Wechat/css/jquery-weui.css">
	<link rel="stylesheet" href="/Wechat/css/weui.css">
	<link rel="stylesheet" href="/Wechat/css/demos.css">
	
	<script src="/Wechat/js/jquery-2.1.4.js"></script>
	<script src="/Wechat/js/fastclick.js"></script>
	<script src="/Wechat/js/jquery-weui.js"></script>
	<script src="/Wechat/js/jquery.js"></script>
	<script type="text/javascript">
		function showdata(data) {
			page = data.totalPage;
			curPage = data.currentPage;
			pageSize = data.pageSize;
			begin =  (curPage-1)*pageSize;
			len = data.allRow;
			code = data.rows;
			console.log(code);
			$str = '';
			if(code.length==0){
				alert("没有对应的数据");
				return;
			}
			$str = '<tr><th>代号</th><th>名称</th><th>描述</th><th>适合类型</th><th>库存</th></tr>';
			for(var i = 0; i < code.length; i++) {
				var num = i + begin;
				//$str += '<div class="weui-flex"><div class="weui-flex__item"><div class="placeholder">序号：</div></div><div class="weui-flex__item"><div class="placeholder">'+num+'</div></div><div class="weui-flex__item"><div class="placeholder">型号</div></div><div class="weui-flex__item"><div class="placeholder">'+code[i]['model']+'</div></div></div><div class="weui-flex"><div class="weui-flex__item"><div class="placeholder">编号</div></div><div class="weui-flex__item"><div class="placeholder">'+code[i]['machineName']+'</div></div><div class="weui-flex__item"><div class="placeholder">学校</div></div><div class="weui-flex__item"><div class="placeholder">'+code[i]['operatinginfo']['school']+'</div></div></div><div class="weui-flex"><div class="weui-flex__item"><div class="placeholder">校区</div></div><div class="weui-flex__item"><div class="placeholder">'+code[i]['operatinginfo']['compus']+'</div></div><div class="weui-flex__item"><div class="placeholder">宿舍</div></div><div class="weui-flex__item"><div class="placeholder">'+code[i]['operatinginfo']['compus']+'</div></div></div>'
				$str+='<tr><td>'+code[i]['partsId']+'</td><td>'+code[i]['name']+'</td><td>'+code[i]['description']+'</td><td>'+code[i]['machineType']+'</td><td>'+code[i]['onStack']+'</td></tr>';
			//	$str += '<li><span><h1>序号：</h1><h2>'+num+'</h2>&nbsp;&nbsp;&nbsp;&nbsp;<h1>ID：</h1><h2>'+code[i]['partsId']+'</h2>&nbsp;&nbsp;&nbsp;&nbsp;<h1>名字：</h1><h2>'+code[i]['name']+'</h2><br/><h1>描述：</h1><h2>'+code[i]['description']+'</h2>&nbsp;&nbsp;&nbsp;&nbsp;<h1>适合类型:</h1><h2>'+code[i]['machineType']+'</h2>&nbsp;&nbsp;&nbsp;&nbsp;<h1>库存：</h1><h2>'+code[i]['onStack']+'</h2>&nbsp;&nbsp;&nbsp;&nbsp;<h1>备注：</h1><h2>'+code[i]['imageurl']+'</h2></span></li>';
			}
			$('#myul').html($str);
		}
		
		function getInfo() {

			pageSize=10;

		// var myurl=new LG.URL(window.location.href);//js封装的url操作函数
		school = $("#searchInput").val(); 
		console.log(school);
		$.ajax({
			type: "POST",
			dataType: "json",
					url: "/Wechat/check/getPartsInfo",//路径
					data: {
						partsId: school,
						pageNo: 1,
						pageSize: pageSize
					},
					success: function(data) {
						showdata(data);
					}
				});
	};
</script>
</head>

<body ontouchstart>
	<div class="main">
		<div class="demo-header">
			<div class="demos-title">配件管理</div>
		</div>
		<div class="weui-search-bar" id="searchBar">
			<form class="weui-search-bar__form">
				<div class="weui-search-bar__box">
					<i class="weui-icon-search"></i>
					<input type="search" onblur="getInfo()" class="weui-search-bar__input" id="searchInput" placeholder="搜索" required="">
					<a href="javascript:"  class="weui-icon-clear" id="searchClear"></a>
				</div>
				<label class="weui-search-bar__label" id="searchText" style="transform-origin: 0px 0px 0px; opacity: 1; transform: scale(1, 1);">
					<i class="weui-icon-search"></i>
					<span>请输入配件编号</span>
				</label>
			</form>
			<a href="javascript:" class="weui-search-bar__cancel-btn" id="searchCancel">取消</a>
		</div>
		<div class="demos-content-padded tMn">
			<table  border="1">
				<tbody id="myul">
				</tbody>
			</table>
		</div>	
	</div>
</body>
<script>
	$(function() {
		FastClick.attach(document.body);
	});
</script>
<script src="../js/jquery-weui.js"></script>
</html>