var userHttp, msgHttp, dreamHttp, groupHttp;
var isInitial = false;
function initialHttps() {
	if(isInitial){
		return;
	}
	try {
		userHttp = new XMLHttpRequest();
		msgHttp = new XMLHttpRequest();
		dreamHttp = new XMLHttpRequest();
		groupHttp = new XMLHttpRequest();
	}catch (e) {
		try {
			userHttp = new ActiveXObject("Msxml2.XMLHTTP");
			msgHttp = new ActiveXObject("Msxml2.XMLHTTP");
			dreamHttp = new ActiveXObject("Msxml2.XMLHTTP");
			groupHttp = new ActiveXObject("Msxml2.XMLHTTP");
		}catch (othermicrosoft) {
			try {
				userHttp = new ActiveXObject("Microsoft.XMLHTTP");
				msgHttp = new ActiveXObject("Microsoft.XMLHTTP");
				dreamHttp = new ActiveXObject("Microsoft.XMLHTTP");
				groupHttp = new ActiveXObject("Microsoft.XMLHTTP");
			}catch (e) {
				alert("Error initializing XMLHttpRequest!");
			}
		}
	}
	isInitial = true;
}