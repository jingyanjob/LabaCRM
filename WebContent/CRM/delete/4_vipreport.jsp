<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="jquery.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
<script type="text/javascript">
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
$(document).ready(function() {
	$("#getvipusersbtn").click(function(){
		var xmlDocument = "<test/>";
        var xmlRequest =  $.ajax({ 
		    type:"POST",
		    url: baseurl + '/crm.dc?action=getVIPUserList', 
		    processData: false,
		    data: xmlDocument,
		    success:function(d){ 
		    	processVUS(d);
			},
			error:function(){ 
			  	alert(e);
		    }
		});
	})
})
</script>
</head>
<body style="font-size: 12px;">
<div >
	<div>
		<b> 会员统计</b>
	</div>
	<div>
		<table>
			<tr>
				<td width="120px">
				<td> 
					<input id="getvipusersbtn" value="查询所有会员" type="button" class="ui-accordion-header ui-helper-reset ui-state-default ui-state-active"/>
				</td>
			</tr>
			<tr>
				<td width="120px">会员总数 </td>
				<td> <div id="vipusernumbersum"></div> </td>
			</tr>
			<tr>
				<td width="120px"> </td>
				<td>
					<div id="vipuserlist">
						 
					</div>
				</td>
			</tr>
		</table>
	</div>
</div>
</body>
</html>