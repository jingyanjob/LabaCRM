<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:wb="http://open.weibo.com/wb">
<head>
<%--

 --%>
<%--<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">=
 --%>
<link type="text/css" href="jq104/css/ui-lightness/jquery.ui.menu.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="jq104/js/jquery-ui-1.10.4.custom.min.js"></script>
<script src="http://tjs.sjs.sinajs.cn/open/api/js/wb.js?appkey=595918520" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="js/mainpage.js"></script>
<title>LabaCRM Main</title>
<style type="text/css">
.mainheight{
	height:500px;
}
.lefttmaindiv{
	width:120px;left:0px;float:left;height:500px;z-index:98;
}
.rightmaindiv{
	height:504px;
	overflow-y:scroll;
	width: 865px;
	float:right;
	text-algin:center;
	border:1px solid #cccccc;
	background-color:white;
}
.subdivdef{
	text-algin:left;
	display:none;
}
.subtable{
	width:99%;border:1px solid #eeeeee;
}
</style>
</head>
<body style="font-size: 13px;">
	<div style="width:990px;">
		<div class=lefttmaindiv>
		<ul id="mainmenu" class=mainheight>
			<li>
				<a id="menu1a" href="javascript:leftSelect('reservationdiv')">在线预定</a>
			</li>
			<li>
				<a  id="menu2a"  href="javascript:leftSelect('vipdiv1')" >会员和积分</a>			
				<ul>
					<li>
					<a href="javascript:leftSelect('vipdiv1')">会员积分</a>
					</li>
					<li>
					<a href="javascript:leftSelect('vipdiv2')">会员列表</a>
					</li>
					<li>
					<a href="javascript:leftSelect('vipdiv3')">寻找新会员</a>
					</li>
					
				</ul>
			</li>
			<li>
				<a  id="menu3a"  href="javascript:leftSelect('productdiv2')">本店菜单</a>
				<ul>
					<li>
					<a href="javascript:leftSelect('productdiv2')">菜单</a>
					</li>
					<li>
					<a href="javascript:initProduct('-1');">添加菜品</a>
					</li>
					<%--
						<li>
						<a href="javascript:leftSelect('productdiv3');">菜单发布</a>
						</li>
					--%>
				</ul>
			</li>
			<li>
				<a  id="menu4a"  href="javascript:leftSelect('tuandiv1')" onclick="initSalesCase();">团购中心</a>
				<ul>
					<li>
					<a href="javascript:leftSelect('tuandiv1')">团购列表</a>
					</li>
					<li>
					<a href="javascript:leftSelect('tuandiv2')">发起团购</a>
					</li>
				</ul>
			</li>
		</ul>
		</div>
		<div class=rightmaindiv >
			<div id="reservationdiv">
					<table cellpadding="0" width="99%" cellspacing="0">
						<tr>
							<td align="left" valign="middle" style="font-size:8;border-bottom:1px solid #eeeeee;">
								用户只需   @本店微博，输入：预定 + 预定内容(例如：预定 今晚6点 2人桌，老张 186 8888 8888) 即可实现预定，更可获得会员积分，快来试试哦:)
								<wb:publish button_size="small" 
									default_text="即日起，您只需 @本微博，输入：预定 + 预定内容(例如：预定 今晚6点2人桌，老张 186...) 即可实现在线预定，更可获得会员积分，快来试试哦:) " >
								</wb:publish>
								<br />
							</td>
						</tr>
						<tr>
							<td>
								<div id="clientmsgmonitor"></div>
							</td>
						</tr>
						<tr>
							<td>
								<div id="reservationupdate"><br/><font size=2>正在更新预定列表，请稍后.</font> <br /></div>
								<div style="width: 100%;" id="reservationStatus">
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
			<div id="vipdiv" >  
				<div id="vipdiv1" class=subdivdef>
					<table cellpadding="0" cellspacing="0" width="99%">
						<tr>
							<td  align= left >
							<div  style="width:100%;background-color: #eeeeee;font-size: 15px">
							<b>会员积分查询</b>
							</div>
							会员微博名字
							<input id="userwbid" value="" />
							<input id="vipusersearchbtn" value=" 查  询  " type="button" onclick="vipUserSearch('0');" /></td>
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
					
					<div style="width:100%;margin-top:50px">
						即日起，任意对本店的转发和评论，都将获得会员积分，快点
							<wb:publish button_size="small" 
									default_text="即日起，任意对本店的转发和评论，都将获得会员积分，快来试试哦:) " >
							</wb:publish> 去告诉大家吧：
					</div>
					
				</div> 
				<div id="vipdiv2"  class=subdivdef>
					数据加载中....
				</div>
				<div id="vipdiv3" class=subdivdef>
					新会员关键字 <input id="vipsearchinput"/> <input type="button" value="开始搜索" onclick="searhVIPinWeibo();"/> <br/>
					搜索到新会员后，请“关注”他/她的微博，系统会在24小时内自动将他/她加为您的会员；
				</div>
			</div>
			<div id="productdiv"> 
				<div id="productdiv1" class=subdivdef>
					<table cellpadding="1" cellspacing="1" width="99%" style="border: 1px solid #eeeeee;">
									<tr>
										<td align=left>名称 <input id="productname" /> 分类 
										<select	id="productcat">
												<option value=0>主厨推荐</option>
												<option value=1>热菜</option>
												<option value=2>时蔬</option>
												<option value=3>冷菜</option>
												<option value=4>汤/煲</option>
												<option value=5>主食</option>
												<option value=6>酒水/饮料</option>
										</select>
										
											价格 <input id="productprice" style="width:50px" />元
											&nbsp;&nbsp;折扣 <input id="productdiscount" style="width:50px" value="10"/>折
										</td>
									</tr>
									<tr align=left>
										<td>菜品介绍<br />
										<textarea id=productdesc rows="10" style="width:99%"></textarea>
										</td>
									</tr>
									<tr>
										<td>
											<form id="productimgform_" action="/crm.dc?action=fileUpload2"
												method="post" target="_new" enctype="multipart/form-data" align=center>
												配图<input type="file" id="productimg" name="productimg" /> 
												<input name="imgbuid" type=hidden />
												<input name="subfolder" type=hidden value="prod" />
											</form>
										</td>
									</tr>
									<tr align=center>
										<td><br /> <input id="newproductbtn"
											onclick="newProduct()" type=button value=" 提 交  " /></td>
									</tr>
								</table>
				</div>
				<div id="productdiv2" class=subdivdef>
					<div id="menupublishdiv">
							<a href='http://labacrm.com/crm.dc?action=menu&id='>
								http://labacrm.com/crm.dc?action=menu&id=
							</a>
					</div>
					<div id="productlistshow" style="width:100%;background-color:#eeeeee">数据加载中....</div>
				</div>
				<%-- 
				<div id="productdiv3" class="subdivdef">
					<br /><br/>
						<div id="menupublishdiv">
							<a href='http://labacrm.com/crm.dc?action=menu&id='>
								http://labacrm.com/crm.dc?action=menu&id=
							</a>
						</div>
						
				</div>--%>
			</div>
			<div id="tuandiv">
				<div id="tuandiv1" class=subdivdef>
					<div id="salescaselistshow" style="width:100%;background-color:#eeeeee">数据加载中....</div>
				</div>
				<div id="tuandiv2" class=subdivdef>
					<table cellpadding="1" cellspacing="2" width="99%" style="border: 1px solid #eeeeee;">
						
						<tr>
							<td>结束日期：<input id="tuanenddate" style="width:90px"/>
							团购代码： <input id="tuancode" style="width:90px"/>
							原价格：<input disabled id="originalprice" style="width:60px"/>
							团购价：<input id="tuanprice" style="width:60px"/> 
							团购折扣:<input disabled id="tuandiscount" style="width:60px"/>
							</td>
						</tr>
						<tr>
							<td>团购说明 <textarea rows="10" id="salescontent" style="width:99%"></textarea>
							</td>
						</tr>
						<tr>
							<td style="border-top:1px solid #eeeeee;" align=center>
								<input type=button onclick="selectProdForSales();" value="菜 单 选 菜" />
								<div id="tuanproducts" style="width:99%;border:1 solid #eeeeee;">
								</div>
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
							 <input  type="button" onclick="newSalesCase();" value="创             建"></td>
						</tr>
					</table>
					<div style="font-size:9">
						<br>说明 ：<br> 团购活动创建后，您就可以通过微博等进行宣传，并批量通知您的粉丝啦；
					</div>
				</div>
			</div>
		</div>
	</div>
	<%--
		<div style="width:99%;text-align:center;">
			<font size="1">
				@佰传信息  -津ICP备13005372号
			 </font>
			<img src="SAELogo1.png"/>
		</div> --%>
</body>

<script type="text/javascript">
$(document).ready(function() {
		$(function() {
		    $("#mainmenu").menu();
		});
	  	function getReservation(){
		//alert("get client msg");
			$("#reservationupdate").html("<font size='2'>正在更新预定列表，请稍后.</font>");
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
				success:function(d){ $("#reservationStatus").html(""+d +"");$("#reservationupdate").html("更新完成");},
				error:function(){ $("#reservationupdate").html("<b><font>对不起，更新出错了，请您稍后再试！</font></b>");}
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
	//新会员加载
	initBizUser();
	  
});

function leftSelect(id){	
	document.getElementById("reservationdiv").style.display = "none";
	document.getElementById("vipdiv1").style.display = "none";
	document.getElementById("vipdiv2").style.display = "none";
	document.getElementById("vipdiv3").style.display = "none";
	document.getElementById("productdiv1").style.display = "none";
	document.getElementById("productdiv2").style.display = "none";
	document.getElementById("productdiv3").style.display = "none";
	
	document.getElementById("tuandiv1").style.display = "none";
	document.getElementById("tuandiv2").style.display = "none";
	document.getElementById("menu1a").style.background = "";
	document.getElementById("menu2a").style.background = "";
	document.getElementById("menu3a").style.background = "";
	document.getElementById("menu4a").style.background = "";
	if( id === "reservationdiv"){
		document.getElementById("menu1a").style.background = "white";
	}
	
	if( id === "vipdiv1"){
		initVipUserSearch();
		document.getElementById("menu2a").style.background = "white";
	}
	if( id === "vipdiv2"){
		getVIPUserList();
		document.getElementById("menu2a").style.background = "white";
	}
	if( id === "vipdiv3"){
		document.getElementById("menu2a").style.background = "white";
	}
	if( id === "productdiv1"){
		document.getElementById("menu3a").style.background = "white";
	}
	if( id === "productdiv2"){
		document.getElementById("menu3a").style.background = "white";
		var buid = $("#hiduid").val();
		document.getElementById("menupublishdiv").innerHTML = "任何人都可以通过访问："
			+ "<a target=_new href='http://labacrm.com/crm.dc?action=menu&id="+buid+"'>http://labacrm.com/crm.dc?action=menu&id=" + buid
			+ "</a> <br />即可浏览本店菜单，并且可以直接点菜、在线预定；<br/>这么方便的东东，快去告诉大家吧  >> "
			+ "<button id='publishproductmenu' href='javascript:;'>发微博告诉大家</button>";
		WB2.anyWhere(function(W){
			var text = "即日起，您只需访问：http://labacrm.com/crm.dc?action=menu&id="+buid
				 + " 便可查看本店的菜单，并且可以直接点菜和在线预定，这么方便的东东，快来试试吧";
			    W.widget.publish({
			        'id' : 'publishproductmenu',
			        'button_size' :  "middle",//big/middle/small，默认为middle
					'button_text' :  "发微博告诉大家",//自定义button文字，默认为“发布到微博”
					'default_text' : text//本框内容，默认为空
			    });
		});
		productList();
		document.getElementById("menu3a").style.background = "white";
	}
	if( id === "productdiv3"){
		document.getElementById("menu3a").style.background = "white";
		var buid = $("#hiduid").val();
		document.getElementById("menupublishdiv").innerHTML = "任何人都可以通过访问："
			+ "<a target=_new href='http://labacrm.com/crm.dc?action=menu&id="+buid+"'>http://labacrm.com/crm.dc?action=menu&id=" + buid
			+ "</a> <br />即可浏览本店菜单，并且可以直接点菜、在线预定；<br/>这么方便的东东，快去告诉大家吧  >> "
			+ "<a id='publishproductmenu' href='javascript:;'>发微博告诉大家</a>";
		WB2.anyWhere(function(W){
			var text = "即日起，您只需访问：http://labacrm.com/crm.dc?action=menu&id="+buid
				 + " 便可查看本店的菜单，并且可以直接点菜和在线预定，这么方便的东东，快来试试吧";
			    W.widget.publish({
			        'id' : 'publishproductmenu',
			        'button_size' :  "middle",//big/middle/small，默认为middle
					'button_text' :  "发微博告诉大家",//自定义button文字，默认为“发布到微博”
					'default_text' : text//本框内容，默认为空
			    });
		});
	}
	if( id === "tuandiv1"){
		getSalesCases();
		document.getElementById("menu4a").style.background = "white";
	}
	if( id === "tuandiv2"){
		getTuanCode();
		document.getElementById("menu4a").style.background = "white";
	}
	document.getElementById(id).style.display = "block";
}
	
</script>
</html>