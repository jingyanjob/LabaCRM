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

<div id="salescaseshowdiv" title="营销方案详情" style="width:600px;position:absolute; top:20px; left:80px; background-color: #EEeeee;" >
	<table  height="100%">
		<tr>
			<td width="600px">
				<table height="100%" width="100%"> 
					<tr>
						<td style="height:40px" align=left>
							<div id="scshownamelist" ></div>
						</td>
					</tr>
					<tr>
						<td align=left style="width:400px">
							<div id="scshowcontent"></div>
						</td>
					</tr>
					<tr>
						<td style="height:25px" align=center>
							<%--<a href="javascript:scPublishAgain();">再发一次</a>
								&nbsp;&nbsp;&nbsp;
								--%>
								<a href="javascript:deleteSalesCase();">删除</a>
								&nbsp;&nbsp;&nbsp;
								<a href="javascript:closeSc__();">关闭</a>
						</td>
					</tr>
					<tr>
						<td>
							<div id="scpublishstatus"></div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>  
</div>
	<c:forEach items="${scs}" var="sc">
	<div style="border:1px solid #cccccc;">
	
	<table style="width: 580px;" cellpadding="1" style="padding: 3 3 3;">
		<tr>
			<td style="background-color:#eeeeee;height:35px">
				<a href="javascript:showSCDetail('<c:out value="${sc.id}"/>'
						,'<c:out value="${sc.vipunamelist}"/>'
						,'<c:out value="${sc.content}"/>'
						,'<c:out value="${sc.productids}"/>')">
				时间：<c:out value="${sc.updatetime}"></c:out>
				内容预览：<c:out value="${sc.contentshort}"></c:out>
				</a>
			</td>
		</tr>
		<%-- tr>
			<td style="display:none;" id="<c:out value="${sc.id}"/>">
				<table  cellpadding="0" width="100%">
					<tr>
						<td style="height:60px; overflow:scroll;">
							营销目标客户：<c:out value="${sc.vipunamelist}"></c:out>
						</td>
					</tr>
					<tr>
						<td>
							内容：<c:out value="${sc.content}"></c:out>
							<img style="max-height:150px;border: 0px solid ;" src="http://100train-img.stor.sinaapp.com/<c:out value="${sc.buid}"></c:out>/sales/<c:out value="${sc.imgurl}"></c:out>"/>
						</td>
					</tr>
				</table>
			</td>
		</tr>--%>
	</table>
	
	</div>
	</c:forEach>
	<script>
		function closeSc__(){
			 $("#salescaseshowdiv").hide();
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