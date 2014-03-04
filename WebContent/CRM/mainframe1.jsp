<%@page import="com.CRM.GlobalStaticData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" href="css/start/jquery-ui-1.8.23.custom.css" rel="stylesheet" /><base>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.10.3.custom.min.js"></script>
<script type="text/javascript" src="js/masonry.js"></script>
<script src=" http://tjs.sjs.sinajs.cn/open/api/js/wb.js?appkey=595918520"
	type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="js/mainpage.js"></script>
<title>LabaCRM Main</title>
</head>
<body style="font-size: 13px;">
	<div id="tabs" style="min-width: 900px;border: 1px solid #bbbbee;">
		<ul>
			<li><a href="#tabs-1">在线预定</a></li>
			<li><a href="#tabs-2" onclick="initVipUserSearch();">会员和积分</a></li>
			<li><a href="#tabs-4" onclick="initProduct();">本店菜单</a></li>
			<li><a href="#tabs-3" onclick="initSalesCase();">团购中心</a></li>
			<%-- li><a href="#tabs-5">系统设置和帮助</a></li> --%>
		</ul>
		<div id="tabs-1" style="width: 100%; overflow-y: scroll;height: 500px;">
				<table cellpadding="0" cellspacing="0" width="99%">
					<tr>
						<td align="left" valign="middle"><font size=2>
						如何预定：任何微博用户   @本店微博，输入：预定 + 预定内容(例如：预定 今晚6点2人桌，联系人老张 电话186xxxxxxxx) 即可</font> <a
							id="publishtoweibo" style="border: 0px white;"><img
								src="img/publish_button_16.gif" /></a> <br />
						<br /></td>
					</tr>
					<tr>
						<td>
							<div id="clientmsgmonitor"></div>
						</td>
					</tr>
					<tr>
						<td>
							<div style="width: 100%" id="reservationStatus">
								<br />
								<br /> <font size=4>正在更新预定列表，请稍后.</font> <br />
								<br />
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div style="width: 100%" id="refreshresStatus" style="float:center;">
								<br />
								<font size=2> 网站会自动刷新，确保您不会错过任何新预订</font>
								<%--
								<input type=button
									id="getclientmsgbtn" onclick="getReservation();" value="刷新预定列表"
									title="网站每5分钟会自动更新预定信息，除特殊情况，无需点击这里更新"></input>  --%>
							</div>
						</td>
					</tr>

				</table>
		</div>
		<div id="tabs-2" style="overflow-y: scroll;height: 500px;">
			<div id="t2-tabs" >
				<ul id="tabs2ul" style="height: 500px;">
					<li><a href="#t2-tabs-1" onclick="initVipUserSearch()"> 会员积分 </a></li>
					<li><a href="#t2-tabs-3"> 寻找新会员 </a></li>
					<li><a href="#t2-tabs-2" onclick="getVIPUserList()"> 会员列表</a></li>
				</ul>
				<div id="t2-tabs-1" style="text-align: left;border:1px solid #eeeeee；">
					<table cellpadding="0" cellspacing="0" Style="width:690px;border:1px solid #eeeeee" >
						<tr>
							<td  align= left>
							VIP会员微博名字
							<input id="userwbid" value="" />
							<input id="vipusersearchbtn" value=" 查 询 "
								type="button" /></td>
						</tr>
						<tr>
							<td id="vipuserisblank" />
						</tr>
						<tr>
							<td>
								<div id="vipuserid"></div>
							</td>
						</tr>
						<tr>
							<td>
								<div id="vipusersearchstatus"></div>
							</td>
						</tr>
					</table>
				</div>
				<div id="t2-tabs-2" style="max-height: 400px;max-width:700px">数据加载中....</div>
				<div id="t2-tabs-3">
					新会员关键字 <input id="vipsearchinput" /> <input type=button
						value="开始搜索" onclick="searhVIPinWeibo();"></input> <br />
					搜索到新会员后，请“关注”他/她的微博，系统会在24小时内自动将他/她加为您的会员；
				</div>
			</div>
		</div>
		<div id="tabs-3" style="overflow-y: scroll;height: 500px;">
			<div id="t3-tabs">
				<ul id="tabs3ul" style="height: 500px;">
					<%--<li><a href="#t3-tabs-3" onclick="initTuan();"> 使用团购</a></li> --%>
					<li><a href="#t3-tabs-2" onclick="getSalesCases();"> 团购列表 </a></li>
					<li><a href="#t3-tabs-1" onclick="getTuanCode();"> 发起团购 </a></li>
				</ul>
				<div id="t3-tabs-1"
					style="text-align: left; width: 600px;">
					<table cellpadding="1" cellspacing="2" width="99%" style="border: 1px solid #eeeeee;">
						
						<tr>
							<td>结束日期：<input id="tuanenddate"/>
							团购代码： <input id="tuancode" />
							</td>
						</tr>
						<tr>
							<td>团购说明 <textarea rows="4" id="salescontent" cols="73"></textarea>
							</td>
						</tr>
						<tr>
							<td style="border-top:1px solid #eeeeee;">
								<input type=button onclick="selectProdForSales();" value="菜 单 选 菜" />
								<div id="tuanproducts" style="width:99%;background-color:#eeeeee;">
								</div>
							</td>
						</tr>
						<tr>
							<td style="border-top:1px solid #eeeeee;">
								原价格：<input disabled id="originalprice" style="width:80px"/>
								团购价：<input id="tuanprice" style="width:80px"/> 
								团购折扣:<input disabled id="tuandiscount" style="width:80px"/>
							</td>
						</tr>
						<%-- 
						<tr>
							<td id="targetfilter_td"  style="border-top:1px solid #eeeeee;">
							团购地点 <input id="targetlocation"
								value="" /> <input type="button" value="智能选取会员"
								onclick="dynsalestarget();" />
								<input type="button" value="收起会员列表"
								onclick="salestargethide();" />
							</td>
						</tr>
						--%>
						<tr>
							<td style="background-color:#eeeeee;">
								<input type=hidden id="finalsalestargetids" />
								<div id="finalsalestarget"></div>
							</td>
						</tr>
						<tr>
							<td align=center style="border-top:2px solid #eeeeee;">
							 <input  type="button" onclick="newSalesCase();" value="创 建 并 发 送"></td>
						</tr>
					</table>
					<div style="font-size:9">
						<br>说明 ：<br> 团购活动，都将新建微博并 @您的会员. <br>
						为避免大量发送为您带来的负面影响，并确保营销效果，建议合理选择营销地点.<br>
						如果筛选到的会员不多，建议您先主动增加您的会员(微博关注等)；
					</div>
				</div>
				<div id="t3-tabs-2">
					<div id="salescaselistshow" style="float: left;"></div>
				</div>
				<%--<div id="t3-tabs-3">
					 <table>
						<tr>
							<td>xx年xx月</td>
							<td>营销次数</td>
							<td>总VIP联系人</td>
							<td>总互动次数(估算)</td>
							<td>总成交次数(估算)</td>
						</tr>
					</table 
					
					<div style="border: 1px solid #eeeeee; width:98%">
					<center>
						团购代码 
						<br />
						<input id="tuancodeuse"/>
						<br />
						消费者名称/微博 
						<br />
						<input id="tuanuseruse"/>
						<br />
						<input type=button onclick="useTuan()" value=提交 />
						</center>
						<br/><br/>
						<div id="tuanusemsg" ></div>
					</div>
				</div>--%>
			</div>
		</div>
		<div id="tabs-4" style="overflow-y:scroll;height: 500px;">
			<div id="t4-tabs">
				<ul id="tabs4ul" style="height: 500px;">
					<li><a href="#t4-tabs-1" onclick="initProduct()"> 添加菜品 </a></li>
					<li><a href="#t4-tabs-2" onclick="productList()"> 菜单和发布 </a></li>
					<%-- li><a href="#t4-tabs-3"> 菜单发布 </a></li>--%>
				</ul>
				<div id="t4-tabs-1" style="text-align: left;width:600px">
							<table cellpadding="2" cellspacing="1" width="99%" style="border: 1px solid #eeeeee;">
									<tr>
										<td align=left>名称 <input id="productname" /> 分类 
										<select	id="productcat">
												<option value=1>主厨推荐</option>
												<option value=2>热菜</option>
												<option value=3>冷菜</option>
												<option value=4>汤/煲</option>
												<option value=5>主食</option>
												<option value=6>酒水/饮料</option>
										</select>
										</td>
									</tr>
									<tr>
										<td align=left>
											价格 <input id="productprice" style="width:50px" />元
											&nbsp;&nbsp;折扣 <input id="productdiscount" style="width:50px" value="10"/>折
										</td>
									</tr>
									<tr align=left>
										<td>菜品介绍<br />
										<textarea id=productdesc rows="4" cols="73"></textarea>
										</td>
									</tr>
									<tr>
										<td>
											<form id="productimgform" action="/crm.dc?action=fileUpload2"
												method="post" target="_new" enctype="multipart/form-data">
												配图<input type="file" id="productimg" name="productimg" /> 
												<input name="imgbuid" type=hidden />
												<input name="subfolder" type=hidden value="prod" />
											</form>
										</td>
									</tr>
									<tr align=center>
										<td><br /> <input id="newproductbtn"
											onclick="newProduct()" type=button value="确定添加" /></td>
									</tr>
								</table>
				</div>
				<div id="t4-tabs-2">
					<div id="productlistshow" style="float: left;max-width: 700px;max-height:600px; ">数据加载中....</div>
				</div>
				<%-- div id="t4-tabs-3">
					客户打开网址： <a href="http://labacrm.com/CRM/productshow/">
						http://labacrm.com/CRM/product/productshow/ </a>即可查看并在线预定所有菜品；
				</div>--%>
			</div>
		</div>
		<%-- div id="tabs-5"
			style="min-height: 400px; overflow-y: scroll;">
			<div id="t5-tabs" style="min-height: 400px; max-height: 600px; ">
				<ul style="min-height: 400px; max-height: 600px; ">
					<li><a href="#t5-tabs-1">系统设置 </a></li>
					<li><a href="#t5-tabs-2">申请新功能</a></li>
					<%-- li><a href="#t5-tabs-3">系统用户管理</a></li>
				</ul>
				<div id="t5-tabs-1" style="valign: left;">
					网站会定时自动更新会员信息并增加新会员，<br />如您需实时增加新会员，请 <input type="button"
						value="点击此处！" id="newbizuserinit"> <br />
					<br />
					<table cellpadding="0" cellspacing="0" style="font-size: 12px;">
						<tr>
							<td width="120px">会员转发得分</td>
							<td><input id="perrepostscore" disabled="disabled"
								value="<%out.print(GlobalStaticData.perRepostScore);%>" /></td>
							<td>
								<div id="2_admin_text1"></div>
							</td>
						</tr>
						<tr>
							<td>会员评论得分</td>
							<td><input id="percommentscore" disabled="disabled"
								value="<%out.print(GlobalStaticData.perCommentScore);%>" /></td>
							<td>
								<div id="2_admin_text2"></div>
							</td>
						</tr>
						<tr>
							<td>会员每日得分上限</td>
							<td><input id="perdayhighscore" disabled="disabled"
								value="<%out.print(GlobalStaticData.perDayMaxScore);%>" /></td>
							<td>
								<div id="2_admin_text3"></div>
							</td>
						</tr>
					</table>
				</div>
				<div id="t5-tabs-2">付费用户高级功能增加选项；</div 
						<div id="t5-tabs-3" style="valign:left;">
							<table>
								<tr>
									<td>
										添加新用户 <input title="请输入用户微博名称" class=""/>
										<select>
											<option value=0>普通权限</option>
											<option value=1>高级权限</option>
											<option value=9>管理员</option>
										</select>
										<input type=button value="添加"  class=""/>
									</td>
								</tr>
								<tr>
									<td>
										<u>普通权限</u>：在线预订，会员和积分  
										<br/><u>高级权限</u>：在线预订，会员和积分，营销中心，产品中心
										<br/><u>管理员</u>：在线预订，会员和积分，营销中心，产品中心，系统设置和帮助
									</td>
								</tr>
							</table>
							
							<br/><br/>
							<table width=400px style="border-top:1px solid;">
								<tr>
									<td>用户名</td>
									<td>权限</td>
									<td width="100" align=right>操作</td>
								</tr>
							</table>
						</div> 
			</div>
		</div> 
		--%>

		<div style="height: 20px;"
			class="ui-accordion-header ui-helper-reset ui-state-default ui-state-active">
			<font size=1>LabaCRM.com</font>
		</div>
	</div>
</body>
<script type='text/javascript' src='js/mainpage.js'></script>
<script type="text/javascript">
	WB2.anyWhere(function(W) {
				if (document.getElementById("hiduname") != null) {
					var _screenname = document.getElementById("hiduname").value;
					var _textreserve = '您只要  @'+ _screenname + ' 输入：预定 + 预定内容(例如：预定 今晚6点2人桌，联系人老张 电话186xxxxxxxx) ,预定即送会员积分；';
					W.widget.publish({
						'id' : 'publishtoweibo',
						//button_text : 自定义button文字，默认为“发布到微博”
						'default_text' : _textreserve
					});
				}
			});
</script>

<script type="text/javascript">
$(document).ready(function() {
		
	$(function(){
		//mainframe1 page initial
		// Tabs
		$('#tabs').tabs();
		//subtabs
		$( "#t2-tabs" ).tabs();
	    $( "#t2-tabs" ).tabs().addClass( "ui-tabs-vertical ui-helper-clearfix" );
	    $( "#t2-tabs li" ).removeClass( "ui-corner-top" ).addClass( "ui-corner-center" );
	    $( "#t3-tabs" ).tabs();
	    $( "#t3-tabs" ).tabs().addClass( "ui-tabs-vertical ui-helper-clearfix" );
	    $( "#t3-tabs li" ).removeClass( "ui-corner-top" ).addClass( "ui-corner-center" );
	    //in tabs3
	    $( "#salescaselistshow" ).accordion({heightStyle: "content"});
	    $( "#t4-tabs" ).tabs();
	    $( "#t4-tabs" ).tabs().addClass( "ui-tabs-vertical ui-helper-clearfix" );
	    $( "#t4-tabs li" ).removeClass( "ui-corner-top" ).addClass( "ui-corner-center" );
	
	//    $( "#t5-tabs" ).tabs();
	 //   $( "#t5-tabs" ).tabs().addClass( "ui-tabs-vertical ui-helper-clearfix" );
	//    $( "#t5-tabs li" ).removeClass( "ui-corner-top" ).addClass( "ui-corner-center" );
	    
	    var maxh = window.screen.height;
		var divheight = 620 ;
		if(maxh >1000){
			divheight = 750;
		}
		if( 800 < maxh && maxh <= 1000){
			divheight = 620;
		}
		if(maxh<800){
			divheight = 520;
		}
		/*
		document.getElementById("tabs-1").style.height = divheight;
		document.getElementById("tabs-2").style.height = divheight;
		document.getElementById("tabs2ul").style.height = divheight;
		document.getElementById("tabs-3").style.height = divheight;
		document.getElementById("tabs3ul").style.height = divheight;
		document.getElementById("tabs-4").style.height = divheight;
		document.getElementById("tabs4ul").style.height = divheight;
		*/
	});	
	
	function getReservation(){
		//alert("get client msg");
		$("#reservationStatus").html("<font size='4'>正在更新预定列表，请稍后.</font>");
		var posturl = baseurl + '/crm.dc?action=getReservation';
    	var dataxml = "data=<data>";
    	dataxml = dataxml+ "<currentUID>" + $("#hiduid").val() + "</currentUID>";
    	dataxml = dataxml+ "<currentATStr>" +  $("#hidatstr").val() + "</currentATStr>";
    	dataxml = dataxml+ "</data>";
   		var xmlRequest =  $.ajax({ 
			type:"POST",
			url: posturl, 
			processData: false,
			data: dataxml,
			success:function(d){ $("#reservationStatus").html(""+d +"");},
			error:function(){ $("#reservationStatus").html("<b><font>对不起，更新出错了，请您稍后再试！</font></b>");}
		});
	}
	$(function(){
		getReservation();
        setInterval(getReservation,reserChkTime);//
	});
	//营销页面  默认营销地址设定
	function setLocation_(){
	//	document.getElementById("targetlocation").value = document.getElementById("bulocation").value;
	}
	setLocation_();
	$("#getclientmsgbtn").click(function() {//for testing use
		getReservation();
	});
	
	$(function() {
	    $("#tuanenddate").datepicker();
	});
});
</script>
</html>