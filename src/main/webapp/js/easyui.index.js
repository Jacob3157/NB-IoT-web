$(document).ready(function () {
	

	$(function(){
		InitLeftMenu();
	});
	
	$('#loading-mask').fadeOut();
	var _menus = {
			"menus": [{
				"menuid": "1",
				"icon": "icon-sys",
				"menuname": "洗衣机管理",
				"menus": [{
					"menuid": "11",
					"menuname": "洗衣机一览",
					"url": "/Wechat/check/machine",
					"icon": "icon-cog",
				},
				{
					"menuid": "12",
					"menuname": "配件一览",
					"icon": "icon-users",
					"url": "/Wechat/check/parts",
				},
				{
					"menuid": "13",
					"menuname": "维护记录",
					"icon": "icon-users",
					"url": "/Wechat/check/maintenanceWeb",
				},
				{
					"menuid": "14",
					"menuname": "维修记录",
					"icon": "icon-users",
					"url": "/Wechat/check/serviceWeb",
				},
				{
					"menuid": "15",
					"menuname": "管理",
					"icon": "icon-cog",
					"child": [{
						"menuid": "152",
						"menuname": "添加洗衣机",
						"icon": "icon-add",
						"url": "/Wechat/check/addMachine"
					},
					{
						"menuid": "153",
						"menuname": "添加维护记录",
						"icon": "icon-add",
						"url": "/Wechat/check/addMaintenanceRecord"
					},
					{
						"menuid": "154",
						"menuname": "添加维修记录",
						"icon": "icon-add",
						"url": "/Wechat/check/addServiceRecord"
					},
					{
						"menuid": "155",
						"menuname": "添加配件",
						"icon": "icon-add",
						"url": "/Wechat/check/addParts"
					},
					{
						"menuid": "156",
						"menuname": "添加维护类型",
						"icon": "icon-add",
						"url": "/Wechat/check/addMaintenanceType"
					},
					{
						"menuid": "157",
						"menuname": "添加维修类型",
						"icon": "icon-add",
						"url": "/Wechat/check/addServiceType"
					},
					{
						"menuid": "158",
						"menuname": "添加放置地点",
						"icon": "icon-add",
						"url": "/Wechat/check/addLocation"
					}]
				}]
			},
			{
				"menuid": "2",
				"icon": "icon-sys",
				"menuname": "运营管理",
				"menus": [{
					"menuid": "21",
					"menuname": "单价管理",
					"icon": "icon-nav",
					"url": "/Wechat/check/operating"
				},
				{
					"menuid": "22",
					"menuname": "优惠管理",
					"icon": "icon-nav",
					"url": "",
				}]
			},
			{
				"menuid": "3",
				"icon": "icon-sys",
				"menuname": "订单管理",
				"menus": [{
					"menuid": "31",
					"menuname": "订单浏览",
					"icon": "icon-nav",
					"url": "/Wechat/check/allOrder"
				},
				{
					"menuid": "32",
					"menuname": "退单管理",
					"icon": "icon-nav",
					"url": "/Wechat/check/chargeback"			
				}]
			},
			{
				"menuid": "4",
				"icon": "icon-sys",
				"menuname": "用户管理",
				"menus": [{
					"menuid": "41",
					"menuname": "用户升级",
					"icon": "icon-nav",
					"url": "/Wechat/check/upgradeuser"
				},
				{
					"menuid": "42",
					"menuname": "用户降级",
					"icon": "icon-nav",
					"url": "",
				},
				{
					"menuid": "43",
					"menuname": "用户列表",
					"icon": "icon-nav",
					"url": "/Wechat/check/userlist",
				},
				{
					"menuid": "44",
					"menuname": "用户信息",
					"icon": "icon-nav",
					"url": "/Wechat/check/userinfo",
				}]
			},
			{
				"menuid": "5",
				"icon": "icon-sys",
				"menuname": "系统管理",
				"menus": [{
					"menuid": "51",
					"menuname": "管理员授权",
					"icon": "icon-nav",
					"url": ""
				},
				{
					"menuid": "52",
					"menuname": "添加管理员",
					"icon": "icon-nav",
					"url": "/Wechat/check/addAdmin"
				}]
			},
			{
				"menuid": "6",
				"icon": "icon-sys",
				"menuname": "NB-IoT",
				"menus": [{
					"menuid": "61",
					"menuname": "井盖信息",
					"icon": "icon-nav",
					"url": "/Wechat/check/getWelllidInfo"
				}]
			}]
		};
	
	//初始化左侧
	function InitLeftMenu() {
		$("#nav").accordion({animate:false,fit:true,border:false});
		var selectedPanelname = '';
	    $.each(_menus.menus, function(i, n) {
			var menulist ='';
			menulist +='<ul class="navlist">';
	        $.each(n.menus, function(j, o) {
				menulist += '<li><div ><a ref="'+o.menuid+'" href="#" rel="' + o.url + '" ><span class="icon '+o.icon+'" >&nbsp;</span><span class="nav1">' + o.menuname + '</span></a></div> ';

				if(o.child && o.child.length>0)
				{
					//li.find('div').addClass('icon-arrow');

					menulist += '<ul class="third_ul">';
					$.each(o.child,function(k,p){
						menulist += '<li><div><a ref="'+p.menuid+'" href="#" rel="' + p.url + '" ><span class="icon '+p.icon+'" >&nbsp;</span><span >' + p.menuname + '</span></a></div> </li>';
					});
					menulist += '</ul>';
				}

				menulist+='</li>';
	        });
			menulist += '</ul>';

			$('#nav').accordion('add', {
	            title: n.menuname,
	            content: menulist,
					border:false,
	            iconCls: 'icon ' + n.icon
	        });

			if(i==0)
				selectedPanelname =n.menuname;

	    });
		$('#nav').accordion('select',selectedPanelname);



		$('.navlist li a').click(function(){
			var url = $(this).attr("rel");
			var menuid = $(this).attr("ref");

			var third = find(menuid);
			if(third && third.child && third.child.length>0)
			{
				$('.third_ul').slideUp();

				var ul =$(this).parent().next();
				if(ul.is(":hidden"))
					ul.slideDown();
				else
					ul.slideUp();
			}
			else{
				$("#mainFrame").attr("src",url);
				$('.navlist li div').removeClass("selected");
				$(this).parent().addClass("selected");
			}
		}).hover(function(){
			$(this).parent().addClass("hover");
		},function(){
			$(this).parent().removeClass("hover");
		});





		//选中第一个
		//var panels = $('#nav').accordion('panels');
		//var t = panels[0].panel('options').title;
	    //$('#nav').accordion('select', t);
	}
	//获取左侧导航的图标
	function getIcon(menuid){
		var icon = 'icon ';
		$.each(_menus.menus, function(i, n) {
			 $.each(n.menus, function(j, o) {
			 	if(o.menuid==menuid){
					icon += o.icon;
				}
			 });
		});

		return icon;
	}

	function find(menuid){
		var obj=null;
		$.each(_menus.menus, function(i, n) {
			 $.each(n.menus, function(j, o) {
			 	if(o.menuid==menuid){
					obj = o;
				}
			 });
		});

		return obj;
	}



	//弹出信息窗口 title:标题 msgString:提示信息 msgType:信息类型 [error,info,question,warning]
	function msgShow(title, msgString, msgType) {
		$.messager.alert(title, msgString, msgType);
	}

	
});


