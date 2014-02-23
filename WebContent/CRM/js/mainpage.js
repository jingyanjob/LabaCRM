/*global var*/
var baseurl = window.location.protocol + "//" + window.location.host;//+ "/LabaCRM";
var currentUID = "";
var currentATStr = "";
var second = 1000;
var minute = 60 * second;
var reserChkTime = 10 * minute;
//vip search and select
var currentVIPUserId = "";
var currentVIPUserScore = 0;
var errorMsg = "呀，出错了，请您几分钟后再试一下";
var loadingMsg = "数据加载中，请您稍后...";
var deleteMsg = "正在删除，请稍后...";
var gspliter = "#@";

/*----common function--*/
function deleteConfirm(){
	return window.confirm("确定删除么");
}
function getCheckBoxValue(divid){
 	var div = document.getElementById(divid);
	var i = div.childNodes.length;
	var vs = "";
	for(var n=0; n<i;n++){
		var item = div.childNodes.item(n);
		if(item.type == "checkbox" && item.checked){
			vs = vs + item.value + gspliter;
		}
	}
	return vs;
}
function getCheckBoxValueMulDivs(divid){
 	var div = document.getElementById(divid);
	var i = div.childNodes.length;
	var vs = "";
	for(var n=0; n<i;n++){
		var item = div.childNodes.item(n);
		if(item.type == "checkbox" && item.checked){
			vs = vs + item.value + gspliter;
		}
	}
	//alert(vs);
	return vs;
}
function checkAll(divid){
	var div = document.getElementById(divid);
	var i = div.childNodes.length;
	for(var n=0; n<i;n++){
		var item = div.childNodes.item(n);
		if(item.type == "checkbox" && !item.checked){
			item.checked = true;
		}
	}
}
function unCheckAll(divid){
	var div = document.getElementById(divid);
	var i = div.childNodes.length;
	for(var n=0; n<i;n++){
		var item = div.childNodes.item(n);
		if(item.type == "checkbox" && item.checked){
			item.checked = false;
		}
	}
}
/*----page 1-------*/
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
	
	    $( "#t5-tabs" ).tabs();
	    $( "#t5-tabs" ).tabs().addClass( "ui-tabs-vertical ui-helper-clearfix" );
	    $( "#t5-tabs li" ).removeClass( "ui-corner-top" ).addClass( "ui-corner-center" );
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
		document.getElementById("targetlocation").value = document.getElementById("bulocation").value;
	}
	setLocation_();
	$("#getclientmsgbtn").click(function() {//for testing use
		getReservation();
	});
	
	$(function() {
	    $("#tuanenddate").datepicker();
	});
});
	
    $("#newbizuserinit").click(function() {
    	$("#newbuinitstatus").html("<b><font>初始化进行中，可能会花费较长时间，谢谢您的耐心等候....</font></b>");
    	//alert(baseurl + '/crm.dc?action=newBizUserInit'); var currentUID = "";
    	//var currentATStr = "";
    	var posturl = baseurl + '/crm.dc?action=newBizUserInit';
    	//&currentUID='+currentUID+"&currentATStr"+currentATStr
    	var dataxml = "data=<data>";
    	dataxml = dataxml+ "<currentUID>" + $("#hiduid").val() + "</currentUID>";
    	dataxml = dataxml+ "<currentATStr>" +  $("#hidatstr").val() + "</currentATStr>";
    	dataxml = dataxml+ "</data>";
   		var xmlRequest =  $.ajax({ 
			type:"POST",
			url: posturl, 
			processData: false,
			data: dataxml,
			success:function(d){ $("#newbuinitstatus").html(""+d +"");},
			error:function(){ $("#newbuinitstatus").html("<b><font>失败！</font></b>");}
		});
   	});
											/*----page 2-------*/


function selectedVU(name, score, uid){
	currentVIPUserId = uid;
	currentVIPUserScore = score;
	$("#userwbid").val(name);
	$("#totalscoretxt").html(score);
	$("#vipusersearchstatus").html("完成！");
}
	$("#userwbid").change(function() {
		var intObj =  $("#userwbid").val();
	    if( intObj ===""){
	    	$("#vipuserisblank").html("<b><font color=red>不能为空！</font></b>");  
	    }
	});
	$("#vipusersearchbtn").click(function() {
		vipUserSearch("0");
	});
	function initVipUserSearch(){
		$("#vipusersearchstatus").html("");
	}
	function vipUserSearch(id){
		var intObj =  $("#userwbid").val();
		if(id != "0"){
			intObj = id;
		}
	    if( intObj ===""){
	    	$("#vipuserisblank").html("<font color=red>不能为空！</font>");  
	    	return;
	    }
	    $("#vipuserisblank").html("");  
		$("#vipusersearchstatus").html("<b><font>查询中....</font></b>");
		//dataxml = dataxml+ "<currentUID>" + $("#hiduid").val() + "</currentUID>";
		var xmlDocument = "data=<data><username>" + $("#userwbid").val() + "</username>"
			+ "<currentUID>" + $("#hiduid").val() + "</currentUID>" +"</data>";
			var xmlRequest =  $.ajax({ 
			type:"POST",
			url: baseurl + '/crm.dc?action=getVIPUser', 
			processData: false,
			data: xmlDocument,
			success:function(d){ 
				$("#vipusersearchstatus").html(d);
				//processVU(d);
			},
			error:function(){ $("#vipusersearchstatus").html("<b><font>没有找到类似的会员，请重新输入.</font></b>");}
		});
	}

	function searhVIPinWeibo(){
		var url = "http://s.weibo.com/user/"+document.getElementById("vipsearchinput").value+"&Refer=weibo_user";
		window.open(url, "_new");
		//http://s.weibo.com/user/%25E6%2599%25AF%25E5%25B2%25A9&Refer=weibo_user
	}
	function processVUS(d){
		var jobj = eval("("+d+")");
		var jo = jobj.vipuser;
		var vnumber = jobj.number;
		
		//alert(jo);
		var _html = "<table ><tr>";
		
		for(var i=0 ;i< jo.length;i++){
			_html += "<td >";
			_html += "<a href='http://weibo.com/" + jo[i].userid 
					+ "' target='_new' title='打开 "+ jo[i].username+" 的微博首页' >";
			_html += jo[i].username;
			_html += "</a></td>";
			var n = i + 1;
			if((i > 0) && (n%5 === 0)){
				_html += "</tr><tr>";
			}
		}
		_html += "</tr></table>";
		$("#vipusernumbersum").html(vnumber);
		//$("#vipusernumbersum").html(vnumber);
		$("#vipuserlist").html(_html);
	}
	$("#getvipusersbtn").click(function(){
		var dataxml = "data=<data>";
	    	dataxml = dataxml+ "<currentUID>" + $("#hiduid").val() + "</currentUID>";
	    	dataxml = dataxml+ "<currentATStr>" +  $("#hidatstr").val() + "</currentATStr>";
	    	dataxml = dataxml+ "</data>";
        var xmlRequest =  $.ajax({ 
		    type:"POST",
		    url: baseurl + '/crm.dc?action=getVIPUserInfo', 
		    processData: false,
		    data: dataxml,
		    success:function(d){
		    	$("#vipusernumbersum").html(d);
			},
			error:function(){ 
				$("#vipusernumbersum").html(errorMsg);
		    }
		});
	});
	
	function getVIPUserList(){
		$("#t2-tabs-2").html(loadingMsg);
		var dataxml = "data=<data>";
		dataxml = dataxml+ "<currentUID>" + $("#hiduid").val() + "</currentUID>";
		dataxml = dataxml+ "<currentATStr>" +  $("#hidatstr").val() + "</currentATStr>";
		dataxml = dataxml+ "</data>";
		var xmlRequest =  $.ajax({ 
		    type:"POST",
		    url: baseurl + '/crm.dc?action=getVIPUserList', 
		    processData: false,
		    data: dataxml,
		    success:function(d){
		    	$("#t2-tabs-2").html(d);
			},
			error:function(){ 
				$("#t2-tabs-2").html(errorMsg);
		    }
		});
//		$('#vipuselistdiv').masonry({ 
//			//
//		});
		
	}
/*----sales-------*/

	function weibosales_a(){
		if($("#targetfilter_td").style.display == "none"){
			$("#targetfilter_td").style.display = "block";
			$("#targetcontent_td").style.display = "block";
			$("#targetsend_td").style.display = "block";
		}else{
			$("#targetfilter_td").style.display = "none";
			$("#targetcontent_td").style.display = "none";
			$("#targetsend_td").style.display = "none";
		}
	}
	function initSalesCase(){
		$("#finalsalestarget").html("");
		var date = new Date();
		var dynamicTuancode = date.getFullYear()+"" + (date.getMonth()+1)+""
			+ date.getDay() +""+date.getHours()+""+date.getMinutes()+""+date.getSeconds();
		document.getElementById("tuancode").value = dynamicTuancode;
	}
	function dynsalestarget(){
		$("#finalsalestarget").html("正在智能筛选营销目标客户，请您稍后；");
		var dataxml = "data=<data>";
    	dataxml = dataxml+ "<currentUID>" + $("#hiduid").val() + "</currentUID>";
    	dataxml = dataxml+ "<currentATStr>" +  $("#hidatstr").val() + "</currentATStr>";
    	dataxml = dataxml+ "<location>" +  $("#targetlocation").val() + "</location>";
    	dataxml = dataxml+ "</data>";
	    var xmlRequest =  $.ajax({ 
		    type:"POST",
		    url: baseurl + '/crm.dc?action=selectSalesTarget', 
		    processData: false,
		    data: dataxml,
		    success:function(d){
		    	var nameandid = d.split("@//@");
		    	$("#finalsalestargetids").value = nameandid[1];
		    	$("#finalsalestarget").html(nameandid[0]);
			},
			error:function(){ 
				$("#finalsalestarget").html(errorMsg);
		    }
	    });
	}
	function salestargethide(){
		document.getElementById("finalsalestarget").style.height = "15px";
		//if(document.getElementById("finalsalestarget").style.display == "block")
		//	$("#finalsalestarget").hide();
		//else
		//	$("#finalsalestarget").show();		
	}
	function newSalesCase(){
		var dataxml = "data=<data>";
		var viplist = getCheckBoxValue("salestagtnames");
		//var img = $("#salestimg").val().split("\\");
//		var n=img.length;
//		if(n >0){
//			n = n-1;
//		}
//		var imgname = img[n];
		//<td>结束日期：<input id="tuanenddate"/>
		//团购代码： <input id="tuancode" />
		var productids = checkProdForSales();
		var oriprice = document.getElementById("originalprice").value;
		var disprice = document.getElementById("tuanprice").value;
		var tuanenddate = document.getElementById("tuanenddate").value;
		var tuancode = document.getElementById("tuancode").value;
    	dataxml = dataxml+ "<currentUID>" + $("#hiduid").val() + "</currentUID>";
    	//dataxml = dataxml+ "<imgurl>" +  imgname  + "</imgurl>";
    	dataxml = dataxml+ "<tuanenddate>" +  tuanenddate  + "</tuanenddate>";
    	dataxml = dataxml+ "<tuancode>" +  tuancode  + "</tuancode>";
    	dataxml = dataxml+ "<productids>" +  productids  + "</productids>";
    	dataxml = dataxml+ "<oriprice>" +  oriprice  + "</oriprice>";
    	dataxml = dataxml+ "<disprice>" +  disprice  + "</disprice>";
    	dataxml = dataxml+ "<viplist>" +  viplist  + "</viplist>";
    	dataxml = dataxml+ "<currentATStr>" +  $("#hidatstr").val() + "</currentATStr>";
    	dataxml = dataxml+ "<content>" +  $("#salescontent").val() + "</content>";
    	dataxml = dataxml+ "</data>";
    	alert(tuanenddate);
    	//document.getElementById("salesimgform").submit();
	    var xmlRequest =  $.ajax({ 
		    type:"POST",
		    url: baseurl + '/crm.dc?action=newSalesCase', 
		    processData: false,
		    data: dataxml,
		    success:function(d){ 
		    	alert("恭喜您，团购创建并发送成功，您还可以在新创建的微博内，任意@您想通知的人！");//$("#finalsalestarget").html(d);
			},
			error:function(){ 
				alert(errorMsg);
		    }
	    });
	}
	function deleteSalesCase(){
		if(!deleteConfirm()){
			return;
		}
		var dataxml = "data=<data>";
		dataxml = dataxml+ "<currentUID>" + $("#hiduid").val() + "</currentUID>";
		dataxml = dataxml+ "<sid>" + scidforpublish + "</sid>";
		dataxml = dataxml+ "</data>";
		//alert(dataxml);
		$("#scpublishstatus").html(deleteMsg);
	    var xmlRequest =  $.ajax({ 
		    type:"POST",
		    url: baseurl + '/crm.dc?action=deleteSalescase', 
		    processData: false,
		    data: dataxml,
		    success:function(d){ 
		    	alert("删除成功");
			},
			error:function(){
				alert(errorMsg);
		    }
	    });
	}
	function selectProdForSales(){
		$("#tuanproducts").html(loadingMsg);
		var dataxml = "data=<data>";
    	dataxml = dataxml+ "<currentUID>" + $("#hiduid").val() + "</currentUID>";
    	dataxml = dataxml+ "</data>";
	    var xmlRequest =  $.ajax({ 
		    type:"POST",
		    url: baseurl + '/crm.dc?action=getProdsForSales', 
		    processData: false,
		    data: dataxml,
		    success:function(d){ 
		    	//alert(d);
		    	$("#tuanproducts").html(d);
			},
			error:function(){ 
				alert(errorMsg);
		    }
	    });
	}
	function showProdsForSale(){
		var id = "#" + document.getElementById("prodsalesel").value ;
		//alert(id);
		if(id == "#_all"){
			$("#prodforsales0").show();
			$("#prodforsales1").show();
			$("#prodforsales2").show();
			$("#prodforsales3").show();
			$("#prodforsales4").show();
			$("#prodforsales5").show();
		}else{
			$("#prodforsales0").hide();
			$("#prodforsales1").hide();
			$("#prodforsales2").hide();
			$("#prodforsales3").hide();
			$("#prodforsales4").hide();
			$("#prodforsales5").hide();
			$(id).show();
		}
	}
	function checkProdForSales(){
		var prods0 = getCheckBoxValue("prodforsales0");
		var prods1 = getCheckBoxValue("prodforsales1");
		var prods2 = getCheckBoxValue("prodforsales2");
		var prods3 = getCheckBoxValue("prodforsales3");
		var prods4 = getCheckBoxValue("prodforsales4");
		var prods5 = getCheckBoxValue("prodforsales5");
		var prods = prods0 + prods1 + prods2 + prods3 + prods4 + prods5;
		//alert(prods);
		var prod = prods.split(gspliter);
		var ids = "";
		var price = 0.0;
		if(prods === ""){
			return ;
		}
		for(var i=0; i< prod.length; i++){
			var _id = prod[i].split(";")[0];
			if(_id != "" && _id != undefined){
				ids = ids + prod[i].split(";")[0] + gspliter;
			}
			var _p = prod[i].split(";")[1];
			if(_p != "" && _p != undefined){
				price = price + parseFloat(prod[i].split(";")[1]);
			}
		}
		//alert(ids);
		document.getElementById("originalprice").value = price;
		return ids;
	}
	function getSalesCases(){
		$("#salescaselistshow").html(loadingMsg);
		var dataxml = "data=<data>";
    	dataxml = dataxml+ "<currentUID>" + $("#hiduid").val() + "</currentUID>";
    	dataxml = dataxml+ "</data>";
	    var xmlRequest =  $.ajax({ 
		    type:"POST",
		    url: baseurl + '/crm.dc?action=getSalesCases', 
		    processData: false,
		    data: dataxml,
		    success:function(d){ 
		    	$("#salescaselistshow").html(d);
			},
			error:function(){ 
				alert(errorMsg);
		    }
	    });
	}
	var scidforpublish;
	function showSCDetail(id, name , cont, imgurl){
		scidforpublish = id;
		//$("#scshowimg").html("<img style='width:200px' src=" + imgurl+" />");
		$("#scshownamelist").html( "客户名单：<br/>"+name );
		$("#scshowcontent").html( "<p>营销内容：<br/>"+cont +"</P>");
		$("#salescaseshowdiv").show();
	}
	
/*------------product------------*/
	function initProduct(){
		//$("#productdesc").value = "";
		//$("#productname").value = "";
	}
	function newProduct(){
		var dataxml = "data=<data>";
		var img = $("#productimg").val().split("\\");
		var n=img.length;
		if(n >0){
			n = n-1;
		}
		var imgname = img[n];
		
    	dataxml = dataxml+ "<currentUID>" + $("#hiduid").val() + "</currentUID>";
    	dataxml = dataxml+ "<category>" + $("#productcat").val() + "</category>";
    	dataxml = dataxml+ "<productname>" + $("#productname").val() + "</productname>";
    	dataxml = dataxml+ "<productdesc>" + $("#productdesc").val() + "</productdesc>";
    	dataxml = dataxml+ "<imgurl>" + imgname + "</imgurl>";
    	dataxml = dataxml+ "<active>0</active>";
    	dataxml = dataxml+ "<price>" + $("#productprice").val() + "</price>";
    	dataxml = dataxml+ "<incutoff>" + $("#productdiscount").val() + "</incutoff>";
    	dataxml = dataxml+ "</data>";
    	document.getElementById("productimgform").submit();
	    var xmlRequest =  $.ajax({
		    type:"POST",
		    url: baseurl + '/crm.dc?action=newProduct', 
		    processData: false,
		    data: dataxml,
		    success:function(d){ 
		    	alert("恭喜您，添加成功！");
			},
			error:function(e){
				alert(errorMsg);
		    }
	    });
	    
	}
	function productList(){
		$("#productlistshow").html(loadingMsg);
		var dataxml = "data=<data>";
		dataxml = dataxml+ "<currentUID>" + $("#hiduid").val() + "</currentUID>";
		dataxml = dataxml+ "</data>";
		//alert(dataxml);
	    var xmlRequest =  $.ajax({ 
		    type:"POST",
		    url: baseurl + '/crm.dc?action=getAllProducts', 
		    processData: false,
		    data: dataxml,
		    success:function(d){ 
		    	$("#productlistshow").html(d);
		    	
			},
			error:function(){
				alert(errorMsg);
		    }
	    });
	}
	function showProductDetail(id, name , desc, imgurl){
		productidforpublish = "productshow" + id;
	//	$("#productshowimg").html("<img style='width:250px' src=" + imgurl+" />");
	//	$("#productshowname").html( "菜名："+name );
	//	$("#productshowdesc").html( "介绍："+desc );
		//$("#productshowdiv" ).css("event.clientX");
		
	//	document.getElementById("productshowdiv").style.top = window.event.clientY - 150;
	//	document.getElementById("productshowdiv").style.left = window.event.clientx - 200;
		$(productidforpublish).show();
	}
	function deleteProduct(){
		if(!deleteConfirm()){
			return;
		}
		var dataxml = "data=<data>";
		dataxml = dataxml+ "<currentUID>" + $("#hiduid").val() + "</currentUID>";
		dataxml = dataxml+ "<pid>" + productidforpublish + "</pid>";
		dataxml = dataxml+ "</data>";
		//alert(dataxml);
		$("#productpublishstatus").html(deleteMsg);
	    var xmlRequest =  $.ajax({ 
		    type:"POST",
		    url: baseurl + '/crm.dc?action=deleteProduct', 
		    processData: false,
		    data: dataxml,
		    success:function(d){ 
		    	alert("删除成功");
			},
			error:function(){
				alert(errorMsg);
		    }
	    });
	}
	var productidforpublish = "";	
	function publishProductToWb(){
		var dataxml = "data=<data>";
		dataxml = dataxml+ "<currentUID>" + $("#hiduid").val() + "</currentUID>";
		dataxml = dataxml+ "<currentATStr>" +  $("#hidatstr").val() + "</currentATStr>";
		dataxml = dataxml+ "<pid>" + productidforpublish + "</pid>";
		dataxml = dataxml+ "</data>";
		//alert(dataxml);
		$("#productpublishstatus").html("<font color=blue>正在发布，请稍后...</font>");
	    var xmlRequest =  $.ajax({ 
		    type:"POST",
		    url: baseurl + '/crm.dc?action=publishProduct', 
		    processData: false,
		    data: dataxml,
		    success:function(d){ 
		    	$("#productpublishstatus").html("<font color=black>发布成功</font>");
			},
			error:function(){
				alert(errorMsg);
		    }
	    });
	}
									/*----page 5-------*/
	//数字输入校验
	$("#changedscore").change(function() {
   		var reg= /^[0-9]*$/;
   		var intObj =  $("#changedscore").val();
       	if(!reg.test(intObj)){
            $("#changescoretxt").html("<b><font color=red>只能输入数字！</font></b>");   
        }else if( intObj ===""){
        	$("#changescoretxt").html("<b><font color=red>不能为空！</font></b>");  
        }else{
            $("#changescoretxt").html("<b><font color=green>OK！</font></b>");
        }
   });
	
	
	function processVU(d){
		var jobj = eval("("+d+")");
		var jo = jobj.vipuser;
		var vnumber = jobj.number;
		if(vnumber === "0"){
			$("#vipusersearchstatus").html("抱歉，未查到类似用户，请重新输入查询；");
		}else{
			if(vnumber === "1"){
				currentVIPUserId = jo[0].userid;
				currentVIPUserScore = jo[0].score;
				$("#userwbid").val(jo[0].username);
				$("#totalscoretxt").html(currentVIPUserScore);
				$("#vipusersearchstatus").html("完成！");
			}else{
				var _html = "<table ><tr>";
				for(var i=0 ;i< jo.length;i++){
					_html += "<td ><a href='javascript: selectedVU(\""+jo[i].username+ "\",\"" +jo[i].score + "\",\"" +jo[i].userid +"\")' >";
					_html += jo[i].username;
					_html += "</a></td>";
					var n = i + 1;
					if((i > 0) && (n%5 === 0)){
						_html += "</tr><tr>";
					}
				}
				_html += "</tr></table>";
			    //	$("#vipusernumbersum").html(vnumber);
				//$("#vipusernumbersum").html(vnumber);
				$("#vipusersearchstatus").html(_html);
				//alert(jo.totalscore);
			//	currentVIPUserId= jo.uid;
			//	$("#vipusersearchstatus").html(jo.username);
			//	$("#totalscoretxt").html(jo.totalscore);
			//	currentVIPUserScore = jo.totalscore;
			}
			
		}
		
	}
	
//    $("#vipusersearchbtn").click(function() {
//    	$("#vipusersearchstatus").html("<b><font>查询中....</font></b>");
//    	var xmlDocument = "data=<data><username>" + $("#userwbid").val() + "</username></data>";
//   		var xmlRequest =  $.ajax({ 
//			type:"POST",
//			url: baseurl + '/crm.dc?action=getVIPUser', 
//			processData: false,
//			data: xmlDocument,
//			success:function(d){ 
//				processVU(d);
//			},
//			error:function(){ $("#vipusersearchstatus").html("<b><font>失败！</font></b>");}
//		});
//   	});
   	$("#delete_addvipuserscore").click(function() {
   		if(currentVIPUserId === ""){
   			alert("尚未查寻或者未找到该用户，请重新查询！");
   			return;
   		}
    	$("#updatevsstatus").html("<b><font>正在加积分...</font></b>");
    	var xmlDocument = "data=<data><username>" + $("#userwbid").val() + "</username>"
    				+ "<userid>" + currentVIPUserId + "</userid>"
    				+ "<perscore>" + $("#changedscore").val() + "</perscore>"
    				+ "<totalscore>" + currentVIPUserScore + "</totalscore>"
    				+ "<currentUID>" + $("#hiduid").val() + "</currentUID>" 
    				+ "<isplus>0</isplus>"
    				+ "</data>";
   		var xmlRequest =  $.ajax({ 
			type:"POST",
			url: baseurl + '/crm.dc?action=vipScoreUpdate', 
			processData: false,
			data: xmlDocument,
			success:function(d){ 
				$("#updatevsstatus").html("成功！");
				currentVIPUserScore = parseInt(currentVIPUserScore) + parseInt($("#changedscore").val());
				$("#totalscoretxt").html( currentVIPUserScore);
			},
			error:function(){ $("#updatevsstatus").html("<b><font>失败！</font></b>");}
		});
   	});
   	$("#delete_reducevipuserscore").click(function() {
   		if(currentVIPUserId === ""){
   			alert("尚未查寻或者未找到该用户，请重新查询！");
   			return;
   		}
   		if( parseInt(currentVIPUserScore) < parseInt($("#changedscore").val())){
   			alert("积分不足，请确保减扣的积分不大于客户现有积分！");
   			return;
   		}
    	$("#updatevsstatus").html("<b><font>正在减积分...</font></b>");
    	var xmlDocument = "data=<data><username>" + $("#userwbid").val() + "</username>"
    				+ "<userid>" + currentVIPUserId + "</userid>"
    				+ "<perscore>" + $("#changedscore").val() + "</perscore>"
    				+ "<totalscore>" + currentVIPUserScore + "</totalscore>"
    				+ "<currentUID>" + $("#hiduid").val() + "</currentUID>" 
    				+ "<isplus>1</isplus>"
    				+ "</data>";
   		var xmlRequest =  $.ajax({ 
			type:"POST",
			url: baseurl + '/crm.dc?action=vipScoreUpdate', 
			processData: false,
			data: xmlDocument,
			success:function(d){ 
				$("#updatevsstatus").html("成功！");
				currentVIPUserScore = parseInt(currentVIPUserScore) - parseInt($("#changedscore").val());
				$("#totalscoretxt").html( currentVIPUserScore);
			},
			error:function(){ $("#updatevsstatus").html("<b><font>失败！</font></b>");}
		});
   	});
   	// runing auto
   	

