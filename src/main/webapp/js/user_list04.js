$(document).ready(function () {
	
   var pageSize = 10; //每页显示的记录条数
   var curPage = 0; //当前页
   var direct = 0; //方向
   var page; //总页数
   var len; //总行数
   var begin=1;
   var code; //
	//页面加载完成后第一次请求数据   
   getInfo("upgrading",1,10);
	
	$("#btn1").click(function firstPage() { // 首页
   		curPage = 1;
   		getInfo("upgrading",1,10);
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
   	

   	
   	$("#pageSizeSet").click(function setPageSize() { // 设置每页显示多少条记录
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
   	});
   	
   	function getInfo(statue,pageNO,pageSize) {
		$.ajax({
			type: "POST",
			url: "/Wechat/select/getByStatue" ,
			data: {
				statue: statue,
				pageNO: pageNO,
				pageSize: pageSize
			},
			dataType:"json",
			success: function(data) {
				page=data.totalPage;
				curPage=data.currentPage;
				len=data.toal;
				code=data.rows;
				$str = '<tr><th>序号</th><th>openID</th><th>姓名</th><th>学校</th><th>学号</th><th>手机号</th><th>状态</th><th>升级时间</th><th>操作</th></tr>';
			    for(var i = 0; i < code.length; i++){
			    	var time=unixToDate(code[i]['codeTime']);
			    	var num=i+begin;
			    	$str += '<tr><td>'+num+'</td><td>'+code[i]['openid']+'</td><td>'+code[i]['name']+'</td><td>'+code[i]['school']+'</td><td>'+code[i]['studentID']+'</td><td>'+code[i]['telephone']+'</td><td>'+code[i]['code']+'</td><td>'+time+'</td><td><button class="upgrade" id='+code[i]['openid']+'>升级</button><button class="disupgrade" id='+code[i]['openid']+'>否决</button></td></tr>';
			    }
			    $('#aaa').html($str);
				display();
				
		   		$(".upgrade").click(function(){
		   	         var openid = $(this).attr("id");
		   	         upgradeUser(openid);
		   	    });
		   		
		   		$(".disupgrade").click(function(){
		   	         var openid = $(this).attr("id");
		   	         disupgradeUser(openid);
		   	    });
				
			}
		 });
	   }
   	
   	function upgradeUser(openid){
   		$.ajax({
			type: "POST",
			url: "/Wechat/update/upgradeuser" ,
			data: {
				openid:openid
			},
			dataType:"text",
			success: function(data) {
				alert(data);
				}
			});
   	}
   	
   	function disupgradeUser(openid){
   		$.ajax({
			type: "POST",
			url: "/Wechat/update/disupgradeuser" ,
			data: {
				openid:openid
			},
			dataType:"text",
			success: function(data) {
				alert(data);
				}
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
	    getInfo("upgrading",curPage+direct,10);

    }
    function display() {
		   document.getElementById("btn0").innerHTML = "当前 " + curPage + "/" + page + " 页 "; // 显示当前多少页
		   document.getElementById("sjzl").innerHTML = "数据总量 " + len + "条"; // 显示数据量
	}
    
});