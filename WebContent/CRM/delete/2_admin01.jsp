<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="jquery.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript">
$(document).ready(function() {
   $("#perrepostscore").change(function() {
   		var reg= /^[0-9]*$/;
   		var intObj =  $("#perrepostscore").val();
       	if(!reg.test(intObj)){
            $("#2_admin_text1").html("<b><font color=red>只能输入数字！</font></b>");   
        }else if( intObj ===""){
        	$("#2_admin_text1").html("<b><font color=red>不能为空！</font></b>");  
        }else{
            $("#2_admin_text1").html("<b><font color=green>OK！</font></b>");
        }
   });
   $("#percommentscore").change(function() {
   		var reg= /^[0-9]*$/;
   		var intObj =  $("#percommentscore").val();
       	if(!reg.test(intObj)){
            $("#2_admin_text2").html("<b><font color=red>只能输入数字！</font></b>");   
        }else if( intObj ===""){
        	$("#2_admin_text2").html("<b><font color=red>不能为空！</font></b>");  
        }else{
            $("#2_admin_text2").html("<b><font color=green>OK！</font></b>");
        }   
   });
   $("#perdayhighscore").change(function() {
   		var reg= /^[0-9]*$/;
   		var intObj =  $("#perdayhighscore").val();
       	if(!reg.test(intObj)){
            $("#2_admin_text3").html("<b><font color=red>只能输入数字！</font></b>");   
        }else if( intObj ===""){
        	$("#2_admin_text3").html("<b><font color=red>不能为空！</font></b>");  
        }else{
            $("#2_admin_text3").html("<b><font color=green>OK！</font></b>");
        }
        if (parseInt($("#percommentscore").val()) > parseInt(intObj) 
        		|| parseInt($("#percommentscore").val()) > parseInt(intObj)) { //parseInt(
	        $("#2_admin_text3").html("<b><font color=red>每日得分上限不能小于每评论或转发得分！</font></b>");
	        
	    }
   });
   
   $("#savescorerolebtn").click(function(){
   	 $("#2_admin_text4").html("保存中...");
    var isOk = true;
    var reg = /^[0-9]*$/;
    var intObj01 = $("#perrepostscore").val();
    if (!reg.test(intObj01)) {
        $("#2_admin_text1").html("<b><font color=red>请输入整数！</font></b>");
        isOk = false;
    }
    else 
        if (intObj01 === "") {
            $("#2_admin_text1").html("<b><font color=red>不能为空！</font></b>");
        }
        else {
            $("#2_admin_text1").html("<b><font color=green>OK！</font></b>");
        }
    var intObj02 = $("#percommentscore").val();
    if (!reg.test(intObj02)) {
        $("#2_admin_text2").html("<b><font color=red>请输入整数！</font></b>");
        isOk = false;
    }
    else 
        if (intObj02 === "") {
            $("#2_admin_text1").html("<b><font color=red>不能为空！</font></b>");
        }
        else {
            $("#2_admin_text2").html("<b><font color=green>OK！</font></b>");
        }
    /*
    var intObj03 = $("#perdayhighscore").val();
    if (!reg.test(intObj03)) {
        $("#2_admin_text3").html("<b><font color=red>请输入整数！</font></b>");
        isOk = false;
    }
    else 
        if (intObj03 === "") {
            $("#2_admin_text1").html("<b><font color=red>不能为空！</font></b>");
        }
        else {
            $("#2_admin_text3").html("<b><font color=green>OK！</font></b>");
        }
       
        
    if (parseInt(intObj01) > parseInt(intObj03) ||
    	parseInt(intObj02) > parseInt(intObj03)) {
        $("#2_admin_text3").html("<b><font color=red>每日得分上限不能小于每评论或转发得分！</font></b>");
        isOk = false;
    }
	 */
    	if (isOk) {
        	var xmlDocument = "data=<data><perrepostscore>" +  $("#perrepostscore").val() +  "</perrepostscore>" +
             "<percommentscore>" + $("#percommentscore").val() + "</percommentscore>" +
             "<perdayhighscore>0</perdayhighscore></data>";
            var xmlRequest =  $.ajax({ 
			    type:"POST",
			    url: baseurl + '/crm.dc?action=saveScoreRole', 
			    processData: false,
			    data: xmlDocument,
			    success:function(d){ $("#2_admin_text4").html("<b><font>保存成功！</font></b>");},
			    error:function(){ $("#2_admin_text4").html("<b><font>失败！</font></b>");}
			});
	        /*
	          xmlRequest.done(
	        	alert("done");
	        );
	        async:false,
			    beforeSend:function(){ },
			    success:function(d){ $("#2_admin_text4").html("<b><font>保存成功！</font></b>");},
			    error:function(){ $("#2_admin_text4").html("<b><font>失败！</font></b>")}
	        */
	    }
	});
	
    $("#newbizuserinit").click(function() {
    	$("#newbuinitstatus").html("<b><font>初始化进行中，可能会花费较长时间，谢谢您的耐心等候....</font></b>");
   		var xmlRequest =  $.ajax({ 
			type:"POST",
			url: baseurl + '/crm.dc?action=newBizUserInit', 
			success:function(d){ $("#newbuinitstatus").html("初始化完成，新导入会员："+d +" 人");},
			error:function(){ $("#newbuinitstatus").html("<b><font>失败！</font></b>");}
		});
   	});
 
 });
</script>
</head>
<body style="font-size: 12px;">
<div>
<div>
<input type=button value="第一次使用，请点击此处进行初始化" id="newbizuserinit" class="ui-accordion-header ui-helper-reset ui-state-default ui-state-active"/>
</div>
<br />
<div id="newbuinitstatus">
</div>
<br />
<div>会员得转发和评论都会获得积分，每天一次或者多次转发、评论所获得的积分相同；</div>
<div>
<table>
	<tr>
		<td width="120px">转发得分</td>
		<td><input id="perrepostscore" value="<%out.print(session.getAttribute("perrepostscore")); %>" /></td>
		<td>
		<div id="2_admin_text1" />
		</td>
	</tr>
	<tr>  
		<td>评论得分</td>
		<td><input id="percommentscore" value="<%out.print(session.getAttribute("percommentscore")); %>" /></td>
		<td>
		<div id="2_admin_text2" />
		</td>
	</tr>
	<%-- 
	<tr>
		<td>每日得分上限</td>
		<td><input id="perdayhighscore" value="<%out.print(session.getAttribute("perdayhighscore")); %>" /></td>
		<td>
		<div id="2_admin_text3" />
		</td>
	</tr> --%>
	<tr>
		<td></td>
		<td><input id="savescorerolebtn" value="保 存" type="button" class="ui-accordion-header ui-helper-reset ui-state-default ui-state-active"/></td>
		<td>
		<div id="2_admin_text4" />
		</td>
	</tr>
</table>
</div>
</div>
</body>
</html>