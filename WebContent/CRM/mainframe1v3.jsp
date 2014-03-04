<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<link type="text/css" href="jq104/css/ui-lightness/jquery-ui-1.10.4.custom.css" rel="stylesheet" />
 <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
 <script type="text/javascript" src="jq104/js/jquery-ui-1.10.4.custom.min.js"></script>
<script src=" http://tjs.sjs.sinajs.cn/open/api/js/wb.js?appkey=595918520"
	type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="js/mainpage.js"></script>
<title>LabaCRM Main</title>
<style type="text/css">
.mainheight{
	height:500px;
}
.lefttmaindiv{
	width:120px;
	left:0px;
	float:left;
	height:500px;
}
.rightmaindiv{
	height:500px;
	overflow-y:scroll;
	width: 860px;
	float:right;
	text-algin:center;
 	border:1px solid #bbbbbb;
	background-color:white;
}
.subdivdef{
	display:none;
}
.subtable{
	width:99%;border:1px solid #eeeeee;
	}
</style>
</head>
<body style="font-size: 12px;">
	<div style="width:990px;">
 		<div class=lefttmaindiv>
		<ul id="mainmenu" class=mainheight>
				<li>
					<a href="javascript:leftSelect('reservationdiv')">在线预定</a>
				</li>
				<li>
				<a href="javascript:leftSelect('vipdiv1')" >会员、积分</a>			
					<ul>
						<li>
						<a href="javascript:leftSelect('vipdiv1')">积分查询</a>
						</li>
						<li>
						<a href="javascript:leftSelect('vipdiv2')">会员列表</a>
						</li>
						<li>
						<a href="javascript:leftSelect('vipdiv3')">增加会员</a>
						</li>
	 				</ul>
 				</li>
 				<li>
					<a href="javascript:leftSelect('productdiv1')" onclick="initProduct();">本店菜单</a>
					<ul>
						<li>
						<a href="javascript:leftSelect('productdiv1')">添加菜品</a>
						</li>
						<li>
						<a href="javascript:leftSelect('productdiv2')">菜 单</a>
						</li>
	 				</ul>
				</li>		
				<li>
					<a href="javascript:leftSelect('tuandiv1')" onclick="initSalesCase();">本店团购</a>
					<ul>
						<li>
						<a href="javascript:leftSelect('tuandiv1')">已有团购</a>
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
			<div id="vipdiv" >  
				<div id="vipdiv1" class=subdivdef>
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
				<div id="vipdiv2"  class=subdivdef>
					数据加载中....
				</div>
				<div id="vipdiv3" class=subdivdef>
					新会员关键字 <input id="vipsearchinput" /> <input type=button
						value="开始搜索" onclick="searhVIPinWeibo();"></input> <br />
					搜索到新会员后，请“关注”他/她的微博，系统会自动将他/她加为您的会员；
				</div>
			</div>
			<div id="productdiv"> 
				<div id="productdiv1" class=subdivdef>
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
				<div id="productdiv2" class=subdivdef>
					<div id="productlistshow" > </div>
				</div>
			</div>
			<div id="tuandiv">
				<div id="tuandiv1" class=subdivdef>
					<div id="salescaselistshow"></div>
				</div>
				<div id="tuandiv2" class=subdivdef>
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
				</div>
			</div>
 		</div>
 	</div>
 	</body>
<script type="text/javascript">
$(document).ready(function() {
	 $(function(){
		 $("#mainmenu").menu();
	});
});
function leftSelect(id){
//reservationdiv vipdiv1  vipdiv2 vipdiv3 productdiv1 productdiv2 tuandiv1 tuandiv2
	document.getElementById("reservationdiv").style.display = "none";
	document.getElementById("vipdiv1").style.display = "none";
	document.getElementById("vipdiv2").style.display = "none";
	document.getElementById("vipdiv3").style.display = "none";
	document.getElementById("productdiv1").style.display = "none";
	document.getElementById("productdiv2").style.display = "none";
	document.getElementById("tuandiv1").style.display = "none";
	document.getElementById("tuandiv2").style.display = "none";
	
	if(id === "vipdiv1"){
	
	}
	if(id === "vipdiv2"){
	
	}
	if(id === "vipdiv3"){
	
	}
	if(id === "productdiv1"){
	
	}
	if(id === "productdiv2"){
	
	}
	if(id === "tuandiv1"){
	
	}
	if(id === "tuandiv2"){
	
	}
	
	document.getElementById(id).style.display = "block";
}
</script>
</html>