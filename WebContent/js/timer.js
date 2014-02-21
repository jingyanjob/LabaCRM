var second = 1000;//1000=1 second
var minute = 60*second;
var short = 5*minute;
var middle = 15* minute;
var long = 30*minute;
var methods = new Array();
var timeSlot = middle;

var notifyItemList = new Array();

function timerRun(){
	timer = window.setInterval("onTimer()", minute);
}
function stop(){
	
}
function onTimer(){
	var curdate = new Date();
	var hour = curdate.getHours();
	var minute = curdate.getMinutes();
	var fixtime = ((hour<10)? ("0"+hour):hour)+":"+((minute==0)? ("0"+minute):minute );
	notification();
	itemNotify(fixtime);
}
function getNewUpdateList(){
	
}
function notification(){
}
function itemNotify(fixtime){
	alertIt(fixtime);
}