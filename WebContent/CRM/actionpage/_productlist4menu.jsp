<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:wb="http://open.weibo.com/wb">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script src="http://tjs.sjs.sinajs.cn/open/api/js/wb.js?appkey=595918520" type="text/javascript" charset="utf-8"></script>
<style>
.maindiv{
	margin-top:80px;
	width:990px;
}
.productdiv { 
	background: url(http://labacrm.com/CRM/img/productback.png) no-repeat;
	padding: 1px; 
	height: 61px;
	width: 296px;
	font-size:12px; 
	text-align: center;
	margin-top:25px;
	margin-left:5px;
	margin-right:5px;
	float: left;
}
.productsubdivleft{
	width:74px;
	height:61px;
	text-align:center;
	vertical-align: middle;
	float:left;
}
.productsubdivright{
	width:222px;
	height:61px;
	text-align:center;
	vertical-align: middle;
	float:right;
	font-size:20px;
	color:white;
}
a{
	text-decoration: none; border: none; color: black;
}
/* select panel*/
.selecteddiv{
	position:fixed;
	bottom:1px;
	background-color:#aaaaaa;
	border: 1px solid #333333;
	width:990px;
	height:80px;
	background:#000;
	color:#F00; 
	filter:alpha(Opacity=60);
	-moz-opacity:0.6;
	opacity: 0.6;;
}
.selectedshowdiv{
	margin-top:3px;
	margin-left:3px;
	width:100px;
	height: 30px;
	font-size:18px;
	display: block;
	background-color: #eeeeee;
	float: left;
	filter:alpha(Opacity=100);
	-moz-opacity:1;
	opacity: 1;
	float:left;
}
.prodlistbaseback{
	width:98%;
	font-size:12px;
	height:20px;
}
.reservesubdiv{
	position: fixed;
	width: 300px;
	height: 200px;
	background-color: white;
	border: 1px solid #eeeeee;
	z-index: 70;
}
</style>
</head>
<body style="background-color:#ffffff;">
<input type=hidden id="buid" value="${bu.uid}"/>
<div id="productlistdiv" class=maindiv>
<br />
欢迎浏览 <c:out value="${bu.username}"></c:out> 的菜单，在这儿您可以直接选菜并预定；
<br/>
	<div id="_pcatecheck" class=prodlistbaseback >
		<input type=button onclick="showProductByCate('a')" id="catea" value="全部"/>
		<input type=button onclick="showProductByCate('0')" id="cate01" value="主厨推荐"/>
		<input type=button onclick="showProductByCate('1')" id="cate02" value="热菜"/>
		<input type=button onclick="showProductByCate('2')" id="cate03" value="时蔬"/>
		<input type=button onclick="showProductByCate('3')" id="cate04" value="冷菜"/>
		<input type=button onclick="showProductByCate('4')" id="cate05" value="汤/煲" />
		<input type=button onclick="showProductByCate('5')" id="cate06" value="主食">
		<input type=button onclick="showProductByCate('6')" id="cate07" value="酒水/饮料">
	</div>
	<br />
	<c:forEach items="${prods}" var="prod">
			<div  name="cateshow${prod.category}"  lang="<c:out value="${prod.category}"></c:out>" class="productdiv"> <%--ui-widget-header  ui-state-default
				<c:if test="${prod.category != '0'}" >style='display:none;'</c:if>
				
				  --%>
				<div class=productsubdivleft>
					<img src="http://100train-img.stor.sinaapp.com/<c:out value="${prod.buid}"></c:out>/prod/<c:out value="${prod.imgurl}"></c:out>" 
							style="width:74px;height:80px; border: 0px solid ;"/>
				</div>
				<div  class=productsubdivright >
					<a href="javascript:selectThis('${prod.id}','${prod.price}','${prod.productname}');" >
						<table width=100% height=100%>
							<tr>
								<td height="61px" valign="middle" align=left >
									<p><font size=3 color=white ><c:out value="${prod.productname}"></c:out> </font></p>
								</td>
								<td width="70px" align=right valign="middle">
									<p><font size= 3 color=white ><c:out value="${prod.price}" />元  </font></p>
								</td>
							</tr>
						</table>
						
					</a>
				</div>
			</div>
	</c:forEach>
	<br/>
</div>
<div id="dialog-form" title="预定信息">
  <fieldset>
    <label for="name">预定人</label><br/>
    <input type="text" name="name" id="name" class="text ui-widget-content ui-corner-all">
    <br />
    <label for="date">预定时间</label><br/>
    <input type="text" name="date" id="date" value="" class="text ui-widget-content ui-corner-all">
    <input id="time" value="6" style="width:30px" onclick="this.value=''"/> 点
    <br />
    <label for="password">电话</label><br/>
    <input type="text" name="mobile" id="mobile" value="" class="text ui-widget-content ui-corner-all">
  </fieldset>
  <div id="selectmenu4res"></div>
</div>
<div class="selecteddiv">
	<div id="selectedshow" style="widht:800px;float:left;">
		<c:forEach items="${prods}" var="prod1">
			<a href='javascript:unSelectThis("${prod1.id}", "${prod1.price}","${prod1.productname}")'>
				<div id="${prod1.id}" class=selectedshowdiv >
					<font size=3> ${prod1.productname} </font>  ${prod1.price} 
				</div>
			</a>
		</c:forEach>
	</div>
	<div style="width:188px;text-align:left;font-size:22px; height:25px;color:white;float:right" >
		<div id="totoalprice" style="width:100%;float:left;">
			合计：0 元
		</div>
		<div style="float:left;width:100%;">
			<button id="submitreserve" ><font color=black >我选好了</font></button>
		</div>
	</div>
</div>
<script type="text/javascript">
var totalPrice =0;
var menuselected = "";
function updateTips( t ) {
    tips.text( t ).addClass( "ui-state-highlight" );
    setTimeout(function() {
        tips.removeClass( "ui-state-highlight", 1500 );
    }, 500 );
}
function checkLength( o, n, min, max ) {
    if ( o.val().length > max || o.val().length < min ) {
        o.addClass( "ui-state-error" );
        updateTips( "亲，需要正确填写哦");
        return false;
      } else {
        return true;
      }
}
function reserveByMenuPage(msg, user){
	var buid = document.getElementById("buid").value;
	var dataxml = "data=<data>";
	dataxml = dataxml+ "<buid>" + buid + "</buid>";
	dataxml = dataxml+ "<msg>" +  msg + "</msg>";
	dataxml = dataxml+ "<user>" +  user + "</user>";
	dataxml = dataxml+ "</data>";
	var xmlRequest =  $.ajax({ 
		type:"POST",
		url: baseurl + '/crm.dc?action=reserveByMenuPage', 
		processData: false,
		data: dataxml,
		success:function(d){
		   // $("#vipdiv2").html(d);
		   alert("预定成功了，我们将很快和您联系以确认本次预定，谢谢");
		},
		error:function(){ 
		//errorMsg
		   alert(errorMsg);
		}
	});
}   
$(function() {
    $("#catea").button();
    $("#cate01").button();
    $("#cate02").button();	
    $("#cate03").button();	
    $("#cate04").button();	
    $("#cate05").button();	
    $("#cate06").button();	
    $("#cate07").button();	
    $("#date").datepicker();
    
    $("#dialog-form").dialog({
      autoOpen: false,
      height: 300,
      width: 350,
      modal: true,
      buttons: {
        " 确 认 ": function() {
        var bValid = true;
      //  bValid = bValid && checkLength( name, "name", 0, 100 );
      //  bValid = bValid && checkLength( name, "mobile", 11, 11 );
      //  bValid = bValid && checkLength( name, "date", 3, 16 );
        /*
          var bValid = true;
          allFields.removeClass( "ui-state-error" );
          bValid = bValid && checkLength( name, "username", 3, 16 );
          bValid = bValid && checkLength( email, "email", 6, 80 );
          bValid = bValid && checkLength( password, "password", 5, 16 );
          bValid = bValid && checkRegexp( name, /^[a-z]([0-9a-z_])+$/i, "Username may consist of a-z, 0-9, underscores, begin with a letter." );
          bValid = bValid && checkRegexp( password, /^([0-9a-zA-Z])+$/, "Password field only allow : a-z 0-9" );
 
          if ( bValid ) {
            $( "#users tbody" ).append( "<tr>" +
              "<td>" + name.val() + "</td>" +
              "<td>" + email.val() + "</td>" +
              "<td>" + password.val() + "</td>" +
            "</tr>" );
          }*/
          if(bValid){
          	var clientmsg = "该预定来源于网页菜单，请尽快与客人电话确认- 预定人：" + $("#name").val() + "  预定时间："
          		 + $("#date").val() + $("#time").val() +  "  电话：" +$("#mobile").val() 
          		 +  "  菜单：" + menuselected  + "  合计：" + totalPrice + "元 ";
	        alert(clientmsg);
	        reserveByMenuPage(clientmsg, $("#name").val());
          }
          $( this ).dialog( "close" );
        },
        Cancel: function() {
          $( this ).dialog( "close" );
        }
      },
      close: function() {
      //  allFields.val( "" ).removeClass( "ui-state-error" );
      }
    });
 
    $( "#submitreserve" )
      .button()
      .click(function() {
         if(totalPrice === 0){
           alert("亲，您还没有选菜呢.");
         }else{
          	//totalPrice =0;
		  	//menuselected = "";
		  	document.getElementById("selectmenu4res").innerHTML = "您预定了：" + menuselected + "  合计：" + totalPrice + "元" ;
		  	$( "#dialog-form" ).dialog( "open" );
         }
     });				
});

function selectThis(id,price,pname){
	 var p = parseInt(price);
	 if( document.getElementById(id).style.display === "block" ){
	 	return;
	 }
	 document.getElementById(id).style.display = "block" ;
	 totalPrice = totalPrice + p;
	 document.getElementById("totoalprice").innerHTML = "已选菜价：" + totalPrice + " 元";
	 menuselected = menuselected + " " + pname;
}
function unSelectThis(id, price, pname){
 	var p = parseInt(price);
	document.getElementById(id).style.display = "none" ;
	totalPrice = totalPrice - p;
	document.getElementById("totoalprice").innerHTML = "合计：" + totalPrice;
	menuselected = menuselected.replace(pname, "");
}
function reserved(){
	//alert(menuselected);
	var reservedivhtml = "<div class=reservesubdiv>";
	reservedivhtml = reservedivhtml + "姓名 <input id='reservename'>";
	reservedivhtml = reservedivhtml + "电话 <input id='reservemobile'>";
	reservedivhtml = reservedivhtml + "预定时间 <input id='reservetime'><br/>";
	reservedivhtml = reservedivhtml + menuselected ;
	reservedivhtml = reservedivhtml + " 总计："+ totalPrice +"元" ;
	reservedivhtml = reservedivhtml + "</div>";
	document.write(reservedivhtml);
}

function showProductByCate(id){
	var names;//= "cateshow";
	var cates;//= document.getElementsByName(names);
	if(id === "a"){
		for(var n=0; n<7; n++){
			names = "cateshow" + n;
			cates = document.getElementsByName(names);
			for(var i=0; i< cates.length; i++){
				cates.item(i).style.display = "block";
			}
		}
	}else{
		for(var n=0; n<7; n++){
			names = "cateshow" + n;
			cates = document.getElementsByName(names);
			for(var i=0; i< cates.length; i++){
				cates.item(i).style.display = "none";
			}
		}
		names = "cateshow" + id;
		cates = document.getElementsByName(names);
		for(var i=0; i< cates.length; i++){
			cates.item(i).style.display = "block";
		}
	}
	
}

function publishToWb(id, button_text){
	W.widget.publish({
        'id' : 'publish'
    });
}
</script>
</body>
</html>