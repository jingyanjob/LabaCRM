<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" href="css/start/jquery-ui-1.8.23.custom.css"
	rel="stylesheet" />
<script type="text/javascript" src="main.js"></script>
<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.23.custom.min.js"></script>

<title>注册</title>
<script type="text/javascript">
	function selectedVU(name, score, uid){
		currentVIPUserId = uid;
		currentVIPUserScore = score;
		$("#userwbid").val(name);
		$("#totalscoretxt").html(score);
		$("#vipusersearchstatus").html("完成！");
	}
$(document).ready(function() {
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

	var currentVIPUserId = "";
	var currentVIPUserScore = 0;

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
	
    $("#vipusersearchbtn").click(function() {
    	$("#vipusersearchstatus").html("<b><font>查询中....</font></b>");
    	var xmlDocument = "data=<data><username>" + $("#userwbid").val() + "</username></data>";
   		var xmlRequest =  $.ajax({ 
			type:"POST",
			url: baseurl + '/crm.dc?action=getVIPUser', 
			processData: false,
			data: xmlDocument,
			success:function(d){ 
				processVU(d);
				//$("#newbuinitstatus").html("初始化完成，新导入会员："+d +" 人");
			},
			error:function(){ $("#vipusersearchstatus").html("<b><font>失败！</font></b>");}
		});
   	});
   	$("#addvipuserscore").click(function() {
   		if(currentVIPUserId === ""){
   			alert("尚未查寻或者未找到该用户，请重新查询！");
   			return;
   		}
    	$("#updatevsstatus").html("<b><font>正在加积分...</font></b>");
    	var xmlDocument = "data=<data><username>" + $("#userwbid").val() + "</username>"
    				+ "<userid>" + currentVIPUserId + "</userid>"
    				+ "<perscore>" + $("#changedscore").val() + "</perscore>"
    				+ "<totalscore>" + currentVIPUserScore + "</totalscore>"
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
   	$("#reducevipuserscore").click(function() {
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
 
 });
</script>
</head>
<body style="font-size: 13px;">
<div >
	
	<div>
		<table cellpadding="0" cellspacing="0"  width="99%">
			<tr>
				<td align="left" valign="middle"><b> 在线订餐 - 发微博、私信、评论等  @本店微博 订餐+内容(例如：人数+点菜单) <img src="img/publish_button_16.gif"/></b> </td>
			</tr>
			<tr >
				<td style="border-bottom: 1px dashed gray">
					<table width="99%">
						<tr>
							<td width="100">时间</td>
							<td width="100">预定人</td>
							<td >预定信息</td>
							<td width=80 align=right>操作</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
</div>
</body>
</html>