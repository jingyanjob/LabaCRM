<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:wb="http://open.weibo.com/wb">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script src=" http://tjs.sjs.sinajs.cn/open/api/js/wb.js?appkey=595918520" type="text/javascript" charset="utf-8"></script>
<style type="text/css">
.usetuandiv{
	border: 1px solid #bbbbbb; 
	display:none;
	width:700px;
	height:300px;
	margin-top:20px; 
	margin-left:20px;
	background-color:white;
	z-index:99;
	text-align:center;
	position:absolute;
}
.tuanshowdiv{
	border: 1px solid #bbbbbb;
	margin-top: 3px; 
	margin-left: 3px; 
	background-color: #eeeeee;
	padding: 1px; 
	min-height: 180px;
	width: 260px; 
	font-size: 12px; 
	text-align: center;
	float: left;
}
a{
	text-decoration: none; border: none; color: black;
}
</style>
</head>
<body>
		<div class="usetuandiv" id="usetuaninlist">
						团购代码 
						<br/>
						<input id="tuancodeuse"/>
						<br/>
						消费者名称/微博 
						<br/>
						<input id="tuanuseruse"/>
						<br/>
						<input type="button" onclick="useTuan()" value="确定"/> &nbsp;&nbsp;
						<input type="button" onclick="closTuanFromList()" value="取消"/>
						<br/><br/>
						<div id="tuanusemsg"></div>
				</div>
<div style="width:99%;" id="tuanlistdiv">
<c:forEach items="${scs}" var="sc">
		<div class="tuanshowdiv">
			<table height=100% width=100% align=center>
				<tr>
					<td height=20px>
						结束时间:<c:out value="${sc.caseendshort}"></c:out> 团购代码:<c:out value="${sc.tuancode}"/>
						<br />
						团购价:<c:out value="${sc.disprice}"/>元
						<br />
					</td>
				</tr>
				<tr>
					<td align=left >
						菜单：<c:forEach items="${sc.products}" var="prod">
								<c:out value="${prod.productname}"/>-
								<c:out value="${prod.price}"/>元   
							</c:forEach>
					</td>
				</tr>
				<tr>
					<td>
						<p><c:out value="${sc.content}"></c:out> </p>
					</td>
				</tr>
				
				<tr>
					<td height=20px style="background-color:white;border-top:1px solid #bbbbbb;">
						<%--
						<a href="javascript:showSCDetail('<c:out value="${sc.id}"/>'
								,'<c:out value="${sc.vipunamelist}"/>'
								,'<c:out value="${sc.content}"/>'
								,'<c:out value="${sc.productids}"/>')" style="text-transform:none;">
							发到微博
						</a>
						--%>
						<wb:publish button_size="small" default_text="结束时间:<c:out value="${sc.caseendshort}"></c:out> 团购代码:<c:out value="${sc.tuancode}"/> 团购价:<c:out value="${sc.disprice}"/>元 , 菜单：<c:forEach items="${sc.products}" var="prod"><c:out value="${prod.productname}"/> 原价<c:out value="${prod.price}"/>元   </c:forEach>团购说明<c:out value="${sc.content}"/>" 
							default_image="">
							发到微博
						</wb:publish>
						&nbsp;<a href="javascript:useTuanFromList('${sc.tuancode}')" style="text-transform:none;">使用团购</a>
						&nbsp;<a href="javascript:deleteSalesCase('${sc.id}');">删除</a>
					</td>
				</tr>
			</table>	
		</div>
	<%--
	<div id="salescaseshowdiv${sc.id}" title="营销方案详情" style="width:600px;min-height:150px;border: 1px solid green;position:absolute; background-color: white;display:none;" >
	<table  height="100%">
		<tr>
			<td width="600px">
				<table height="100%" width="100%"> 
					<tr>
						<td align=left style="background-color: #eeeeee;">
							结束时间<c:out value="${sc.caseendshort}"/>  &nbsp; &nbsp;
							团购代码 <c:out value="${sc.tuancode}"/> &nbsp; &nbsp;
							团购价 <c:out value="${sc.disprice}"/>元
						</td>
					</tr>
					<tr>
						<td align=left >
							团购说明：<c:out value="${sc.content}"/>
						</td>
					</tr>
					<tr>
						<td align=left >
							团购菜单：
							<c:forEach items="${sc.products}" var="prod">
								<c:out value="${prod.productname}"/>-
								<c:out value="${prod.price}"/>元  ,
							</c:forEach>
						</td>
					</tr>
					<tr>
						<td height=“25px” align=center style="background-color: #eeeeee;">
							<a href="javascript:scPublishAgain();">发到微博</a>
								&nbsp;&nbsp;&nbsp;
								<a href="javascript:deleteSalesCase();">删除</a>
								&nbsp;&nbsp;&nbsp;
								<a href="javascript:closeSc__('salescaseshowdiv${sc.id}');">关闭</a>
						</td>
					</tr>
					<tr>
						<td height=”20px“ id="scpublishstatus">
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>  
	</div>
	--%>
</c:forEach>
</div>
	<script>
		function closeSc__(id){
			document.getElementById(id).style.display = "none";
			 //$("#salescaseshowdiv").hide();
		}
		function showSalesCaseListItem(id){
			if(document.getElementById(id).style.display == "none"){
				document.getElementById(id).style.display = "block";
			}else{
				document.getElementById(id).style.display = "none";
			}
		}
		$(document).ready(function() {
			$(function(){
			  $("#salescaseshowdiv").hide();
			});
		});
	</script>
</body>
</html>