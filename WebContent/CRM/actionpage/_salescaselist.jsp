<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
<div style="width:690px;" id="tuanlistdiv">
				<div style="border: 1px solid #eeeeee; width:98%;position:absolute; tope:100px; left:120px;" id="usetuaninlist">
					<center>
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
						</center>
						<br/><br/>
						<div id="tuanusemsg"></div>
					</div>
<c:forEach items="${scs}" var="sc">
	<div style="border:1px solid #cccccc;margin: 3 3 3;width: 190px;background-color:#eeeeee;">
			<center>
				
				结束时间:<c:out value="${sc.caseendshort}"></c:out> 
				<br />
				团购代码:<c:out value="${sc.tuancode}"/>
				<br />
				团购价:<c:out value="${sc.disprice}"/>元
				<br />
				说明:<c:out value="${sc.contentshort}"></c:out>
				<a href="javascript:showSCDetail('<c:out value="${sc.id}"/>'
						,'<c:out value="${sc.vipunamelist}"/>'
						,'<c:out value="${sc.content}"/>'
						,'<c:out value="${sc.productids}"/>')" style="text-transform:none;text-decoration:none;">
					查看详情
				</a>
				&nbsp; &nbsp;
				<a href="javascript:">使用团购</a>
			</center>
	</div>
	
	
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
					<%-- tr>
						<td align=left style="border-top: 1px solid #bbbbbb;">
							<div style="max-height: 80px;overflow-y:scroll">
								<span><c:out value="${sc.vipunamelist}"/></span>							
							</div>
						</td>
					</tr>--%>
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