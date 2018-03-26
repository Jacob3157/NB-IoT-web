$(document).ready(function () {
	
   var pageSize = 10; //每页显示的记录条数
   var curPage = 0; //当前页
   var direct = 0; //方向
   var page; //总页数
   var len; //总行数
   var begin=1;
   var code; //
   var flag=0;
	//页面加载完成后第一次请求数据   
   getInfo(1,10);
	
	$("#btn1").click(function firstPage() { // 首页
   		curPage = 1;
   		direct=0;
   		begin=1;
   		displayPage();
   	});
   	$("#btn2").click(function frontPage() { // 上一页
   		direct = -1;
   		displayPage();
   	});
   	$("#btn3").click(function nextPage() { // 下一页
   		direct = 1;
   		displayPage();
   	});
   	$("#btn4").click(function lastPage() { // 尾页
   		curPage = page;
   		direct = 0;
   		displayPage();
   	});
   	$("#btn5").click(function changePage() { // 转页
   		curPage = document.getElementById("changePage").value * 1;
   		if(!/^[1-9]\d*$/.test(curPage)) {
   			alert("请输入正整数");
   			return;
   		}
   		if(curPage > page) {
   			alert("超出数据页面");
   			return;
   		}
   		direct = 0;
   		displayPage();
   	});
   	
   	function changeName() {
		var myselect1 = document.getElementById("select1"); //拿到select对象
		var index1 = myselect1.selectedIndex; //拿到选中项的索引
		 //拿到选中项options的value
		var school = myselect1.options[index1].value; //拿到选中项options的text

		var myselect2 = document.getElementById("select2"); //拿到select对象
		var index2 = myselect2.selectedIndex; //拿到选中项的索引
		 //拿到选中项options的value
		var compus = myselect2.options[index2].value; //拿到选中项options的text
		if(compus=="宝山校区"){
			compus="Baoshan";
		}
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
		       });
			}
		});
	}
   	
   	function changeName2() {
   		var school = $("#se_school").val();
   		var compus = $("#se_compus").val();
		if(compus=="宝山校区"){
			compus="Baoshan";
		}
		$.ajax({
			type: 'post', //ajax的方式  get/post
			dataType: 'json', //接收返回数据的方式
			url: "/Wechat/select/getBuildings", //路径
			timeout: 3000, //超时时间
			data: {
				school: school,
				compus: compus,
			},
			success:function(data) {
				var json = data.buildings;
				console.log(json);
				$.each(json, function(i, item) {
	               var option3 = '<option data-id="' + i + '" value="' + item + '">' + item + '</option>';
			       $("#su_building").append(option3);
		       });
			}
		});
	}
	
	
	function getCompus(){
		var myselect1 = document.getElementById("select1"); //拿到select对象
		var index1 = myselect1.selectedIndex; //拿到选中项的索引
	    var school = myselect1.options[index1].value; //拿到选中项options的text
		if(school=="SHU"){
			var myArray=new Array(3);
			myArray[0]="宝山校区";
			myArray[1]="延长校区";
			myArray[2]="嘉定校区";
			$.each(myArray, function(i, item) {
	               var option2 = '<option data-id="' + i + '" value="' + item + '">' + item + '</option>';
			       $("#select2").append(option2);
		       });
		}
	}
	
	function getCompus2(){
		var school = $("#se_school").val(); //拿到选中项options的text
		if(school=="SHU"){
			var myArray=new Array(3);
			myArray[0]="宝山校区";
			myArray[1]="延长校区";
			myArray[2]="嘉定校区";
			$.each(myArray, function(i, item) {
	               var option2 = '<option data-id="' + i + '" value="' + item + '">' + item + '</option>';
			       $("#se_compus").append(option2);
		       });
		}
	}
	
	$("#select1").change(function(){
		$("#select2").empty();
		$("#select2").prepend("<option value='0'>==请选择校区==</option>"); //为Select插入一个Option(第一个位置)
		curPage=0;
		direct=0;
		getCompus();
	});
	
	$("#se_school").change(function(){
		$("#se_compus").empty();
		$("#se_compus").prepend("<option value='0'>==请选择校区==</option>"); //为Select插入一个Option(第一个位置)
		curPage=0;
		direct=0;
		getCompus2();
	});
	
	
	$("#select2").change(function(){
		$("#select3").empty();
		$("#select3").prepend("<option value='0'>==请选择机器==</option>"); //为Select插入一个Option(第一个位置)
		curPage=0;
		direct=0;
		changeName();
	});
	
	$("#se_compus").change(function(){
		$("#su_building").empty();
		$("#su_building").prepend("<option value='0'>==请选择楼幢==</option>"); //为Select插入一个Option(第一个位置)
		curPage=0;
		direct=0;
		changeName2();
	});
	
	$("#aaa").click(function(){
		flag=0;
		inquireMachine();
	});
	
	$("#sea_status").click(function(){
		flag=1;
		curPage=0;
		getMachinesByStatus(1, 10);
	});
	
	$("#se_location").click(function(){
		flag=2;
		curPage=0;
		getMachinesByBuilding(1, 10);
	});
	
	function inquireMachine(){
		curPage=0;
		getInfo(1,10);
	}
	
	function post(URL, PARAMS) {
		  var temp = document.createElement("form");
		  temp.action = URL;
		  temp.method = "post";
		  temp.style.display = "none";
		  for (var x in PARAMS) {
		    var opt = document.createElement("textarea");
		    opt.name = x;
		    opt.value = PARAMS[x];
		    // alert(opt.name)
		    temp.appendChild(opt);
		  }
		  document.body.appendChild(temp);
		  temp.submit();
		  return temp;
		}

   	
   	/*$("#pageSizeSet").click(function setPageSize() { // 设置每页显示多少条记录
   		pageSize = document.getElementById("pageSize").value; //每页显示的记录条数
   		if(!/^[1-9]\d*$/.test(pageSize)) {
   			alert("请输入正整数");
   			return;
   		}
   		len = $("#aaa tr").length - 1;
   		page = len % pageSize == 0 ? len / pageSize : Math.floor(len / pageSize) + 1; //根据记录条数，计算页数
   		curPage = 1; //当前页
   		direct = 0; //方向
   		firstPage();
   	});*/
   	
   	function getInfo(pageNO,pageSize) {
   		var myselect1 = document.getElementById("select1"); //拿到select对象
		var index1 = myselect1.selectedIndex; //拿到选中项的索引
	    var school = myselect1.options[index1].value; //拿到选中项options的text
		var myselect2 = document.getElementById("select2"); //拿到select对象
		var index2 = myselect2.selectedIndex; //拿到选中项的索引
		var compus = myselect2.options[index2].value;//拿到选中项options的text
		if(compus=="宝山校区"){
			compus="Baoshan";
		}
		var myselect3 = document.getElementById("select3"); //拿到select对象
		var index3 = myselect3.selectedIndex; //拿到选中项的索引
		var machineName = myselect3.options[index3].value; //拿到选中项options的text
		$.ajax({
			type: "POST",
			url: "/Wechat/check/getMachineInfo" ,
			data: {
				school:school,
				compus:compus,
				machineName:machineName,
				pageNO: pageNO,
				pageSize: pageSize
			},
			dataType:"json",
			success: function(data) {
				console.log(data);
				page=data.totalPage;
				curPage=data.currentPage;
				len=data.total;
				code=data.rows;
				$str = '<tr><th>序号</th><th>洗衣机编号</th><th>型号</th><th>学校</th><th>校区</th><th>楼幢</th><th>状态</th><th>操作</th></tr>';
			    for(var i = 0; i < code.length; i++){
			    	var num=i+begin;
			    	$str += '<tr><td>'+num+'</td><td>'+code[i]['machineName']+'</td><td>'+code[i]['model']+'</td><td>'+code[i]['operatinginfo']['school']+'</td><td>'+code[i]['operatinginfo']['compus']+'</td><td>'+code[i]['building']+'</td><td>'+code[i]['status']+'</td><td><button class="clean" id='+code[i]['machineName']+'>维护记录</button><button class="respair" id='+code[i]['machineName']+'>维修记录</button><button class="edit" id='+code[i]['machineName']+'>编辑</button></td></tr>';
			    }
			    $('#tableMachine').html($str);
				display();
				
		   		$(".clean").click(function(){
		   	         var machineName = $(this).attr("id");
		   	         clean(machineName);
		   	    });
		   		
		   		$(".respair").click(function(){
		   	         var machineName = $(this).attr("id");
		   	         respair(machineName);
		   	    });
		   		
		   		$(".edit").click(function(){
		   	         var machineName = $(this).attr("id");
		   	         post("/Wechat/select/singleMachine",{machineName:machineName});
		   	    });
				
			}
		 });
	   }
   	
   	function getMachinesByStatus(pageNo,pageSize){
   		var myselect1 = document.getElementById("se_status"); //拿到select对象
		var index1 = myselect1.selectedIndex; //拿到选中项的索引
	    var se_status = myselect1.options[index1].value; //拿到选中项options的text
	    if(se_status=="0"){
	    	alert("请选择状态！");
	    	return;
	    }
		$.ajax({
			type: "POST",
			url: "/Wechat/select/machineByStatus" ,
			data: {
				status:se_status,
				pageNo: pageNo,
				pageSize: pageSize
			},
			dataType:"json",
			success: function(data) {
				page=data.totalPage;
				curPage=data.currentPage;
				len=data.total;
				code=data.rows;
				$str = '<tr><th>序号</th><th>洗衣机编号</th><th>型号</th><th>学校</th><th>校区</th><th>楼幢</th><th>状态</th><th>操作</th></tr>';
			    for(var i = 0; i < code.length; i++){
			    	var num=i+begin;
			    	$str += '<tr><td>'+num+'</td><td>'+code[i]['machineName']+'</td><td>'+code[i]['model']+'</td><td>'+code[i]['operatinginfo']['school']+'</td><td>'+code[i]['operatinginfo']['compus']+'</td><td>'+code[i]['building']+'</td><td>'+code[i]['status']+'</td><td><button class="clean" id='+code[i]['machineName']+'>维护记录</button><button class="respair" id='+code[i]['machineName']+'>维修记录</button><button class="edit" id='+code[i]['machineName']+'>编辑</button></td></tr>';
			    }
			    $('#tableMachine').html($str);
				display();
				
		   		$(".clean").click(function(){
		   	         var machineName = $(this).attr("id");
		   	         clean(machineName);
		   	    });
		   		
		   		$(".respair").click(function(){
		   	         var machineName = $(this).attr("id");
		   	         respair(machineName);
		   	    });
		   		
		   		$(".edit").click(function(){
		   	         var machineName = $(this).attr("id");
		   	         post("/Wechat/select/singleMachine",{machineName:machineName});
		   	    });
				
			}
		 });
   	}
   	
   	function getMachinesByBuilding(pageNo,pageSize){
   		var school = $("#se_school").val();
   		var compus = $("#se_compus").val();
   		var building = $("#su_building").val();
	    if(school=="0"){
	    	alert("请选择学校！");
	    	return;
	    }
	    if(compus=="0"){
	    	alert("请选择校区！");
	    	return;
	    }
	    if(building=="0"){
	    	alert("请选择楼幢！");
	    	return;
	    }
	    if(compus=="宝山校区"){
			compus="Baoshan";
		}
		$.ajax({
			type: "POST",
			url: "/Wechat/select/machineByBuilding" ,
			data: {
				building:building,
				school:school,
				compus:compus,
				pageNo: pageNo,
				pageSize: pageSize
			},
			dataType:"json",
			success: function(data) {
				page=data.totalPage;
				curPage=data.currentPage;
				len=data.total;
				code=data.rows;
				$str = '<tr><th>序号</th><th>洗衣机编号</th><th>型号</th><th>学校</th><th>校区</th><th>楼幢</th><th>状态</th><th>操作</th></tr>';
			    for(var i = 0; i < code.length; i++){
			    	var num=i+begin;
			    	$str += '<tr><td>'+num+'</td><td>'+code[i]['machineName']+'</td><td>'+code[i]['model']+'</td><td>'+code[i]['operatinginfo']['school']+'</td><td>'+code[i]['operatinginfo']['compus']+'</td><td>'+code[i]['building']+'</td><td>'+code[i]['status']+'</td><td><button class="clean" id='+code[i]['machineName']+'>维护记录</button><button class="respair" id='+code[i]['machineName']+'>维修记录</button><button class="edit" id='+code[i]['machineName']+'>编辑</button></td></tr>';
			    }
			    $('#tableMachine').html($str);
				display();
				
		   		$(".clean").click(function(){
		   	         var machineName = $(this).attr("id");
		   	         clean(machineName);
		   	    });
		   		
		   		$(".respair").click(function(){
		   	         var machineName = $(this).attr("id");
		   	         respair(machineName);
		   	    });
		   		
		   		$(".edit").click(function(){
		   	         var machineName = $(this).attr("id");
		   	         post("/Wechat/select/singleMachine",{machineName:machineName});
		   	    });
				
			}
		 });
   	}
   	
   	function clean(machineName){
   		alert("开发中！");
   		/*$.ajax({
			type: "POST",
			url: "/Wechat/update/upgradeuser" ,
			data: {
				machineName:machineName
			},
			dataType:"text",
			success: function(data) {
				alert(data);
				}
			});*/
   	}
   	
   	function respair(machineName){
   		alert("开发中！");
   		/*$.ajax({
			type: "POST",
			url: "/Wechat/update/disupgradeuser" ,
			data: {
				machineName:machineName
			},
			dataType:"text",
			success: function(data) {
				alert(data);
				}
			});*/
   	}
   	
/*    function unixToDate(unixTime) {
        var time = new Date(unixTime);
        var ymdhis = "";
        ymdhis += time.getUTCFullYear() + "-";
        ymdhis += (time.getUTCMonth()+1) + "-";
        ymdhis += time.getUTCDate();
        ymdhis += " " + time.getHours() + ":";
        ymdhis += time.getUTCMinutes() + ":";
        ymdhis += time.getUTCSeconds();
        return ymdhis;
    }*/
   	
    function displayPage() {
	   	if(curPage <= 1 && direct == -1) {
	   		direct = 0;
	   		alert("已经是第一页了");
	   		return;
	   	} else if(curPage >= page && direct == 1) {
	   		direct = 0;
	   		alert("已经是最后一页了");
	   		return;
	   	}
	   	begin = (curPage+direct - 1) * pageSize + 1; // 起始记录号
	   	
	   	switch(flag){
	   	case 0:
	   		getInfo(curPage+direct,10);
	   		break;
	   	case 1:
	   		getMachinesByStatus(curPage+direct,10);
	   		break;
	   	case 2:
	   		getMachinesByBuilding(curPage+direct,10);
	   		break;
	   	default:
	   		getInfo(curPage+direct,10);
   			break;
	   	}
	   	
	    

    }
    function display() {
		   document.getElementById("btn0").innerHTML = "当前 " + curPage + "/" + page + " 页 "; // 显示当前多少页
		   document.getElementById("sjzl").innerHTML = "数据总量 " + len + "条"; // 显示数据量
	}
    
});