<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.productdiv { 
	background-image: url(img/productback.png);
	padding: 1px; 
	height: 100px;
	width: 405px;
	font-size:12px; 
	text-align: center;
	margin:20 10 10;
	float: left;
}

</style>

</head>
<body>
<a href="javascript:alert('11')" style="text-transform:none;text-decoration:none;">
<img src="img/labacrm121.jpg" style="border:0px solid" />
test <script type="text/javascript">
	document.write("rrrrrrrrrrrrrrrrrr");
			new function(){document.write("55555555555555555555555");}
							</script>
</a>
<div cate="1" >a</div>
<div cate=1 >b</div>
<div cate=2 >2</div>
<div cate=2 >2</div>
<div cate=3 >3</div>
<div style='width:99%;font:25px;align:center;border: 1px solid #eeeeee;margin: 200px 200px 200px;"'>正在进行新用户初始化，预计在几分钟内完成，请等待！
<div id="chkdiv" style="background-color:#eeeeee;margin: 2px 2px 00px;">
<input type="checkbox" value="1" checked="checked"/>test1
<input type="checkbox" value="2"/>test2
<input type="checkbox" value="3"/>test3
<input type="checkbox" value="4"/>test4

<input type="button" onclick="getCheckBoxValue('chkdiv')" value="12345"/>
<input type="button" onclick="testdivd()" value="aaaacccc"/>
<br />
<div style="display:none;border: 1px #bbbbbb solid; width:500px; height: 400px; position:absolute; background-color: #eeeeee" id="testdivd">
<input type="button" onclick="ctestdivd()" value="aaaacccc"/>
<br />
<div style="background-color:white;border-top:1px solid #bbbbbb;position:absolute;bottom:0;width:100%">************</div>

</div>
<input type="button" onclick="checkAll('chkdiv')" value="67890"/>
<input type="button" onclick="unCheckAll('chkdiv')" value="55555"/>
<select id="selecttest" onChange="selectx()">
	<option value=1>1xxxxxxxx</option>
	<option value=2>2xxxxxxxxxx</option>
	<option value=3>4xxxxxxxxxxxxxxxx</option>
</select>
<input id="v123" name="v123"/><input id="v123" name="v123"/><input id="v123" name="v123"/>
</div>
</div>
<div style="width: 500px; height: 200px; overflow-y:auto;">
选用精选肉排.......选用精选肉排.....选用精选肉排.....选用精选肉排.....选用精选肉排.....选用精选肉排.....选用精选肉排.....选用精选肉排.....选用精选肉排.....选用精选肉排......选用精选肉排......................
选用精选肉排.......选用精选肉排.....选用精选肉排.....选用精选肉排.....选用精选肉排.....选用精选肉排.....选用精选肉排.....选用精选肉排.....选用精选肉排.....选用精选肉排......选用精选肉排......................
选用精选肉排.......选用精选肉排.....选用精选肉排.....选用精选肉排.....选用精选肉排.....选用精选肉排.....选用精选肉排.....选用精选肉排.....选用精选肉排.....选用精选肉排......选用精选肉排......................

</div>
<input id=newdatedd onclick="getdate()" value='getdate'/>
<div style="width:1000; height:300px">
	<div style="width:300; height:100px;float:left;background-color:red; border: 1px solid white; margin: 5 5 5;"></div>
	<div style="width:300; height:100px;float:left;background-color:red; border: 1px solid white; margin: 5 5 5;"></div>
	<div style="width:300; height:100px;float:left;background-color:red; border: 1px solid white; margin: 5 5 5;"></div>
	<div style="width:300; height:100px;float:left;background-color:red; border: 1px solid white; margin: 5 5 5;"></div>
	<div style="width:300; height:100px;float:left;background-color:red; border: 1px solid white; margin: 5 5 5;"></div>
	<div style="width:300; height:100px;float:left;background-color:red; border: 1px solid white; margin: 5 5 5;"></div>	
</div>

<div style="width:1000; height:300px">
	<div class=productdiv></div>
	<div class=productdiv></div>
	<div class=productdiv></div>
	<div class=productdiv></div>
	<div class=productdiv></div>
	<div class=productdiv></div>
	<div class=productdiv></div>
</div>

<script type="text/javascript">
	function checkdivcate(){
		var divs = document.getElementsByTagName("div");
		for(var i=0; i< divs.length;i++){
			if(divs.item(i).cate === "1"){
				alert(divs.item(i).innerHTML);
			}
		}
	}
	function testdivd(){
		document.getElementById("testdivd").style.display = "block";
		var maxh = window.screen.height - 200;
		var hh;
		alert(maxh);
		if(maxh > 1000){
			hh = "500px";
		}
		if( 800 < maxh && maxh <= 1000){
			hh = 500;
		}
		if(maxh<800){
			hh = 300;
		}
		document.getElementById("testdivd").style.height = hh;
	}
	
	function ctestdivd(){
		document.getElementById("testdivd").style.display = "none";
	}
	function getdate(){
		var date = new Date();
		var dynamicTuancode = date.getFullYear()+"" + (date.getMonth()+1)+""
			+ date.getDate() +""+date.getHours()+""+date.getMinutes()+""+date.getSeconds();
		document.getElementById("newdatedd").value = dynamicTuancode;
	}

	function showdiv(){
		
	}
	function selectx(){
		alert(document.getElementById("selecttest").value);
	}
	function setv(){
		document.getElementsByName("v123").item(0).value = "1234";
		document.getElementsByName("v123").item(0).value = "12324";
		document.getElementsByName("v123").item(2).value = "1234";
	}
	setv();
	function getCheckBoxValue(divid){
	 	if(window.confirm("delete")){
	 		return;
	 	}
	 	var div = document.getElementById(divid);
		var i = div.childNodes.length;
		for(var n=0; n<i;n++){
			var item = div.childNodes.item(n);
			if(item.type == "checkbox" && item.checked){
				alert(item.value);
			}
		}
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
</script>
</body>
</html>