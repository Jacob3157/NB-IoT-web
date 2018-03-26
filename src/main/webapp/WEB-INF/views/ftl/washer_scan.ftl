<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>洗衣机一览</title>
		<link rel="stylesheet" type="text/css" href="/Wechat/css/washer_scan05.css">
		<script src="/Wechat/js/jquery-3.2.1.min.js"></script>
		<script type="text/javascript">
			function changeName() {
				var myselect1 = document.getElementById("select1"); //拿到select对象
				var index1 = myselect1.selectedIndex; //拿到选中项的索引
				 //拿到选中项options的value
				var school = myselect1.options[index1].value; //拿到选中项options的text

				var myselect2 = document.getElementById("select2"); //拿到select对象
				var index2 = myselect2.selectedIndex; //拿到选中项的索引
				 //拿到选中项options的value
				var compus = myselect2.options[index2].value; //拿到选中项options的text
				$.ajax({
					type: 'post', //ajax的方式  get/post
					dataType: 'json', //接收返回数据的方式
					url: "/Wechat/select/getMachineName", //路径
					timeout: 3000, //超时时间
					data: {
						school: school,
						compus: compus,
					},
					success:function(data) {
						var json = data.machineName;
						$.each(json, function(i, item) {
			               var option3 = '<option data-id="' + i + '" value="' + item + '">' + item + '</option>';
					       $("#select3").append(option3);
				       })
					}
				})
			}
			
//			function inquireName(){
//				var myselect1 = document.getElementById("select1"); //拿到select对象
//				var index1 = myselect1.selectedIndex; //拿到选中项的索引
//				 //拿到选中项options的value
//				var school = myselect1.options[index1].value; //拿到选中项options的text
//
//				var myselect2 = document.getElementById("select2"); //拿到select对象
//				var index2 = myselect2.selectedIndex; //拿到选中项的索引
//				 //拿到选中项options的value
//				var compus = myselect2.options[index2].value;//拿到选中项options的text
//				
//				var myselect3 = document.getElementById("select3"); //拿到select对象
//				var index3 = myselect3.selectedIndex; //拿到选中项的索引
//				myselect3.options[index3].value; //拿到选中项options的value
//				var machineName = myselect3.options[index3].text; //拿到选中项options的text
//				$.ajax({
//					type: 'post', //ajax的方式  get/post
//					dataType: 'json', //接收返回数据的方式
//					url: "www.pioteks.com:8080/Wechat/select/getMachineName", //路径
//					timeout: 3000, //超时时间
//					data: {
//							school: school,
//							compus: compus,
//							machineName: machineName,
//						},
//						success: function(data) {
//							$('#aaa').on('click', '.tanchu', function() {
//								var name = $(this).text();
//								window.location.href='scan_detail01.html';
//
//							})
//						}
//					})
//				}
		</script>
	</head>

	<body>
		<div class="main">
			<div class="header">
				<span class="spa">洗衣机一览</span>
			</div>
			<div class="mainbody">
				<div class="di">
					<span class="sp">选择学校：</span>
					<select id="select1" style="width: 300px;height: 40px;font-size:18px">
						<option>==请选择学校==</option>
						<option value="SHU">上海大学</option>
					</select>
					<span class="sp">选择校区：</span>
					<select id="select2" onchange="changeName()" style="width: 300px;height: 40px;font-size:18px">
						<option>==请选择校区==</option>
						<option value="Baoshan">宝山校区</option>
						<option value="Yanchang">延长校区</option>
						<option value="Jading">嘉定校区</option>
					</select>
					<span class="sp">选择编号：</span>
					<select id="select3" style="width: 300px;height: 40px;font-size:18px">
						<option>==请选择编号==</option>
					</select>
					<button class="button" id="aaa"><span style="font-size:20px;">搜索</span></button>
				</div>
			</div>

		</div>
	</body>

</html>