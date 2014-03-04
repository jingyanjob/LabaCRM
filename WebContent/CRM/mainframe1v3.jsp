<html>
<head>
<link type="text/css" href="jq104/css/ui-lightness/jquery-ui-1.10.4.custom.css" rel="stylesheet" />
 <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
 <script type="text/javascript" src="jq104/js/jquery-ui-1.10.4.custom.min.js"></script>
<script src=" http://tjs.sjs.sinajs.cn/open/api/js/wb.js?appkey=595918520"
	type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="js/mainpage.js"></script>
<title>LabaCRM Main</title>
<style type="text/css">
.mainheight{
	height:500px;
}
.lefttmaindiv{
	width:120px;left:0px;float:left;height:500px;
	}
 .rightmaindiv{
	height:503px;
	overflow-y:scroll;
	width: 860px;
	float:right;
	text-algin:left;
 	border:1px solid #dddddd;
	background-color:white;
 }
.subdivdef{
	display:none;
}
.subtable{
	width:99%;border:1px solid #eeeeee;
	}
</style>
</head>
<body style="font-size: 13px;">
	<div style="width:990px;">
 		<div class=lefttmaindiv>
		<ul id="mainmenu" class=mainheight>
				<li>
					<a href="javascript:leftSelect('reservationdiv')"></a>
					
				</li>
				<li>
				<a href="javascript:leftSelect('vipdiv1')" > </a>			
					<ul>
						<li>
						<a href="javascript:leftSelect('vipdiv1')"></a>
						</li>
						<li>
						<a href="javascript:leftSelect('vipdiv2')"></a>
						</li>
						<li>
						<a href="javascript:leftSelect('vipdiv3')"></a>
						</li>
	 				</ul>
 				</li>
 				<li>
					<a href="javascript:leftSelect('productdiv1')" onclick="initProduct();"></a>
					<ul>
						<li>
						<a href="javascript:leftSelect('productdiv1')"></a>
						</li>
						<li>
						<a href="javascript:leftSelect('productdiv2')"></a>
						</li>
	 				</ul>
				</li>		
				<li>
					<a href="javascript:leftSelect('tuandiv1')" onclick="initSalesCase();"></a>
					<ul>
						<li>
						<a href="javascript:leftSelect('tuandiv1')"></a>
						</li>
						<li>
						<a href="javascript:leftSelect('tuandiv2')"></a>
						</li>
	 				</ul>
				</li>
 			</ul>
 
		</div>
 		<div class=rightmaindiv >
 			<div id="reservationdiv">
					
			</div>
			<div id="vipdiv" >  
				<div id="vipdiv1" class=subdivdef>
					
				</div> 
				<div id="vipdiv2"  class=subdivdef>
				</div>
				<div id="vipdiv3" class=subdivdef>
				
				</div>
			</div>
		</div>
			<div id="productdiv"> 
			
			
				<div id="productdiv1" class=subdivdef>
					</div>
				<div id="productdiv2" class=subdivdef>
				</div>
					<div id="productlistshow" > </div>
			</div>
			<div id="tuandiv">
				<div id="tuandiv1" class=subdivdef>
					<div id="salescaselistshow"></div>
				<div id="tuandiv2" class=subdivdef>
					
				</div>
			</div>
 		</div>
 	</body>
<script type="text/javascript">
$(document).ready(function() {
	 $(function(){
		 $("#mainmenu").menu();
	});
});
function leftSelect(id){
//reservationdiv vipdiv1  vipdiv2 vipdiv3 productdiv1 productdiv2 tuandiv1 tuandiv2
	document.getElementById("reservationdiv").style.display = "none";
	document.getElementById("vipdiv1").style.display = "none";
	document.getElementById("vipdiv2").style.display = "none";
	document.getElementById("vipdiv3").style.display = "none";
	document.getElementById("productdiv1").style.display = "none";
	document.getElementById("productdiv2").style.display = "none";
	document.getElementById("tuandiv1").style.display = "none";
	document.getElementById("tuandiv2").style.display = "none";
	document.getElementById(id).style.display = "block";
}
</script>
</html>