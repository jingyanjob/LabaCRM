<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script>
var tempusername, currentVIPUserId, currentVIPUserScore;

function setSc(s){ 
	currentVIPUserId = s;
	currentVIPUserScore = document.getElementById(s).value.split("@@")[0];
	tempusername = document.getElementById(s).value.split("@@")[1];
	document.getElementById('totalscoretxt').innerHTML = currentVIPUserScore;
	document.getElementById('vipscorechangestatus').innerHTML = 
		"选择了会员 ： <a target='_new' href='http://www.weibo.com/u/"+s+"'>" + tempusername+"</a>";
}
function updateScore(isPlus){
	var tempTS;
	if(isPlus){
		tempTS = parseInt(currentVIPUserScore) + parseInt($("#changedscore").val());
	}else{
		tempTS = parseInt(currentVIPUserScore) - parseInt($("#changedscore").val());
	}
	document.getElementById(currentVIPUserId).value = tempTS + "@@" + tempusername;
	
	document.getElementById('totalscoretxt').innerHTML = tempTS;
	currentVIPUserScore = tempTS;
}
$(document).ready(function() {	
	//数字输入校验
	$("#changedscore").change(function() {
			var reg= /^[0-9]*$/;
			var intObj =  $("#changedscore").val();
	   	if(!reg.test(intObj)){
	        $("#changescoretxt").html("<b><font>请输入整数，谢谢</font></b>");   
	    }else if( intObj ===""){
	    	$("#changescoretxt").html("<b><font>加减的积分不能为空，谢谢</font></b>");  
	    }else{
	        $("#changescoretxt").html("<b><font color=green></font></b>");
	    }
	});
	/*
		@TODO:连续点击可能造成数据库写入延迟
	*/
	$("#addvipuserscore").click(function() {
		if(currentVIPUserId === ""){
			alert("请选择会员！");
			return;
		}
		currentVIPUserScore = document.getElementById(currentVIPUserId).value.split("@@")[0];
		tempusername = document.getElementById(currentVIPUserId).value.split("@@")[1];
		$("#updatevsstatus").html("<b><font>正在加积分...</font></b>");
		var xmlDocument = "data=<data><username>" + tempusername + "</username>"
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
				$("#updatevsstatus").html(d);
				updateScore(true);
			},
			error:function(){ $("#updatevsstatus").html("<b><font>失败！</font></b>");}
		});
	});
	
	$("#reducevipuserscore").click(function() {
		$("#reducevipuserscore").disabled = true;
		if(currentVIPUserId === ""){
			alert("请选择会员！");
			return;
		}
		if( parseInt(currentVIPUserScore) < parseInt($("#changedscore").val())){
			alert("积分不足，请确保减扣的积分不大于客户现有积分！");
			return;
		}
		currentVIPUserScore = document.getElementById(currentVIPUserId).value.split("@@")[0];
		tempusername = document.getElementById(currentVIPUserId).value.split("@@")[1];
		$("#updatevsstatus").html("<b><font>正在减积分...</font></b>");
		var xmlDocument = "data=<data><username>" + tempusername + "</username>"
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
				$("#updatevsstatus").html(d);
				updateScore(false);
			},
			error:function(){ $("#updatevsstatus").html("<b><font>失败！</font></b>");}
		});
		$("#reducevipuserscore").disabled = false;
	});
});
</script>
</head>
<body>
	<br />
	
	<c:forEach var="vs" items="${vssm}">
		<input type=hidden id="${vs.uid}" value="${vs.totalscore}@@${vs.username}" ></input>
	</c:forEach>
	<div style="border:1px solid white;padding:3px;width:580px">
		<c:forEach var="vipu" items="${vus}">
			<a href="javascript:setSc('${vipu.uid}')" >
				<c:out value="${vipu.username}"></c:out>
			</a> &nbsp; &nbsp; 
		</c:forEach>
	</div>
	
	<div id="vipscorechangestatus"></div>
	<table style="background-color:#EEEEEE;">
		<tr>
			<td width="80px" >可用积分</td>
			<td valign="middle" >
				<div id="totalscoretxt" style="font-size:10;">
					<c:out value="${onlyone}"></c:out>
				</div>
			</td>
			<td>
				
			</td>
		</tr>
		<tr>
			<td>加减积分</td>
			<td><input id="changedscore" value="0" /></td>
			<td id="changescoretxt"></td>
		</tr>
	</table>
	<table>
		<tr>
			<td><input id="reducevipuserscore" value="减  - 使 用 积 分"
				type="button" /> <input id="addvipuserscore"
				value="加  + 增 加 积 分 " type="button" /></td>
			<td id="updatevsstatus"></td>
		</tr>
	</table>
</body>
</html>