<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script>
	function confirmReservation(id, cid) {
		if(!window.confirm("预定确认后，状态将无法修改！")){
			return;
		}
		var xmlDocument = "data=<data><id>" + id + "</id>" + "<cid>" + cid +"</cid>"
			+ "<currentATStr>" +  $("#hidatstr").val() + "</currentATStr></data>";
			
		var xmlRequest = $.ajax({ 
			type:"POST",
			url: baseurl + '/crm.dc?action=confirmReservation', 
			processData: false,
			data: xmlDocument, 
			success:function(d){
				var _ = "#"+id;
				$(_).html("预定成功");
				alert("已通过微博通知预订客户，本次预定成功！");
			},
			error:function(){alert("抱歉，出错了，请刷新页面后再试一次；");}
		});
	}
	function showResconfirmed(){
		if(document.getElementById("resconftable").style.display == "none"){
			$("#resconftable").show();
			document.getElementById("resconfbtn").value = "收起已确认的预订";
		}else{
			$("#resconftable").hide();
			document.getElementById("resconfbtn").value = "展开已确认的预订";
		}
	}
</script>

</head>
<body>
	<table width="99%" cellpadding="1" cellspacing="0">
		<tr>
			<td align=center style="background-color:#DDDDDD;border:1px solid white;"><b>时间/年-月-日</b></td>
			<td align=center style="background-color:#DDDDDD;border:1px solid white;"><b>预定人</b></td>
			<td align=center style="background-color:#DDDDDD;border:1px solid white;"><b>预定信息</b></td>
			<td align=right style="background-color:#DDDDDD;border:1px solid white;"><b>预定状态</b></td>
		</tr>
		<c:forEach  items="${rss}" var="reserve">
			<tr >
				<td width="200" style="border:1px solid #DDDDDD;">
					<fmt:formatDate value="${reserve.createdat}" type="both" dateStyle="short" timeStyle="short"/>
				</td>
				<td width="150" style="border:1px solid #DDDDDD;">
					<a href="http://www.weibo.com/u/${reserve.username}" target="_new">	<c:out value="${reserve.username}"></c:out>  </a>
				</td>
				<td style="border:1px solid #DDDDDD;"><c:out value="${reserve.text}"></c:out></td>
				<td width="80" align=right style="border:1px solid #DDDDDD;">
					<table>
						<tr>
							<td id="${reserve.id}">
							 <%-- c:if test="${reserve.resstatus=='0'}">预定成功</c:if>	--%>
							 <c:if test="${reserve.resstatus=='2'}">
							 	<input  title="确定本次预定成功" onClick="confirmReservation(${reserve.id}, ${reserve.cid});" type=button value="预定确认"/></c:if>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</c:forEach>
	</table>
	<input type=button value="查看已确认的预订" onclick="showResconfirmed();" id="resconfbtn"></input>

	<table width="99%" id="resconftable" cellpadding="1" cellspacing="0">
		<tr>
			<td align=center style="background-color:#DDDDDD;border:1px solid white;"><b>时间/年-月-日</b></td>
			<td align=center style="background-color:#DDDDDD;border:1px solid white;"><b>预定人</b></td>
			<td align=center style="background-color:#DDDDDD;border:1px solid white;"><b>预定信息</b></td>
			<td align=right style="background-color:#DDDDDD;border:1px solid white;"><b>预定状态</b></td>
		</tr>
		<c:forEach  items="${rsconfs}" var="resconf">
			<tr >
				<td width="200" style="border:1px solid #DDDDDD;">
					<fmt:formatDate value="${resconf.createdat}" type="both" dateStyle="short" timeStyle="short"/>
				</td>
				<td width="150" style="border:1px solid #DDDDDD;">
					<a href="http://www.weibo.com/u/${resconf.username}" target="_new">	<c:out value="${resconf.username}"></c:out>  </a>				
			    </td>
				<td style="border:1px solid #DDDDDD;"><c:out value="${resconf.text}"></c:out></td>
				<td width=80 align=right style="border:1px solid #DDDDDD;">
					 预定成功			
				</td>
			</tr>
		</c:forEach>
	</table>
	<script type="text/javascript">
	
		$(document).ready(function() {
			$(function(){
			  $("#resconftable").hide();
			});
		});
		
	</script>
</body>
</html>