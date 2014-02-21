var baseurl = window.location.protocol+"//"+window.location.host+"/XPWeb";
var xmlHttp; //xmlHttp object defination.
var requesting = false;
var initialDate = new Date();
var day = initialDate.getDate();
var month = initialDate.getMonth() + 1;
var year = initialDate.getYear();
var thistime;
var intDays = initialDate.getDay();
var starttime;
var endtime;
var monthDayNum;
var curmonday = new Date();
var cursunday = new Date();
var currentview = "";
var addorupdateitem = "add";
/*****weekdayscache,weekmonthcache,weekyearcache  for weekly view store date usage******/
var weekdayscache = new Array();
var weekmonthcache = new Array();
var weekyearcache = new Array();

function getXmlHttp() {
	try {
		var request = new XMLHttpRequest();
	}
	catch (e) {
		try {
			request = new ActiveXObject("Msxml2.XMLHTTP");
		}
		catch (othermicrosoft) {
			try {
				request = new ActiveXObject("Microsoft.XMLHTTP");
			}
			catch (failed) {
				request = false;
			}
		}
	}
	if (!request) {
		alert("Error initializing XMLHttpRequest!");
	} else {
		return request;
	}
}
function sendXmlReq(url, xml) {
	initialHttps();
	if(requesting){
		return;
	}
	//xmlHttp = getXmlHttp();
	//xmlHttp.onreadystatechange = updatePage;
	if(!xmlHttp){
		xmlHttp = getXmlHttp();
	}
	xmlHttp.open("POST", url, true);
	xmlHttp.setRequestHeader("Content-Type","text/html; charset=utf-8");
	xmlHttp.setRequestHeader("pageEncoding","utf-8");
	if(xml === ""){
		xmlHttp.send(null);
	}else{
		xmlHttp.send(xml);
	}
	waiting();
	return xmlHttp;
}

function updatePage() {
	if (xmlHttp.readyState == 4) {
		var responseText = xmlHttp.responseText;
		requesting = false;
	}else{
		//requesting = false;
	}
}
function waiting(){
	requesting = true;
	document.getElementById("body").style.cursor = "wait";
	document.getElementById("waitresponsediv").style.display = "block";
}
function release(){
	requesting = false;
	document.getElementById("body").style.cursor = "";
	document.getElementById("waitresponsediv").style.display = "none";
}
function showMemo(day){
    alert(day);
}

function showCurTime(_day,top, _intDays){
	day = _day;
	intDays = _intDays;
	month = document.getElementById("monthtop").value;
	year = document.getElementById("yeartop").value;
	if(day === ""){
	}else{
		thistime = document.getElementById("yeartop").value+"-"+ document.getElementById("monthtop").value+"-"+ day;
	}
	getMonthTime();
	if(currentview === "dayView"){
		displayDayView();
		gotoDayView();
	}else if(currentview === "weekView"){
		displayWeekView()
		gotoWeekView();
	}
	//gotoDayView();
}

function closeContent(){
	document.getElementById("content").style.display = "none";
}
function closeEditPanel(){
	document.getElementById("editpanel").style.display = "none";
}

function displayMainListView(){
	//document.getElementById("monthviewmaindiv").style.display = "none";
	document.getElementById("usermainpagediv").style.display = "block";
	document.getElementById("userdaypagediv").style.display = "none";
	document.getElementById("userweekpagediv").style.display = "none";
	document.getElementById("viewMemoDiv").style.display = "none";
}

function displayDayView(){
	//need deal with CSS error on IE7
	document.getElementById("usermainpagediv").style.display = "none";
	document.getElementById("userdaypagediv").style.display = "block";
	document.getElementById("userweekpagediv").style.display = "none";
	document.getElementById("viewMemoDiv").style.display = "none";
}
function displayWeekView(){
	document.getElementById("usermainpagediv").style.display = "none";
	document.getElementById("userdaypagediv").style.display = "none";
	document.getElementById("userweekpagediv").style.display = "block";
	document.getElementById("viewMemoDiv").style.display = "none";
}
function displayMonthView(){
	document.getElementById("monthviewmaindiv").style.display = "block";
	document.getElementById("viewMemoDiv").style.display = "none";
}

function currentDisplayControl(){
	if(currentview === "dayView"){
		displayDayView();
	}else if(currentview === "weekView"){
		displayWeekView()
	}else if(currentview === "monthview"){
		displayMonthView();
	}else{
		displayMainListView();
	}
}
function showOrHideList1(id1, id2){
	if(document.getElementById(id1).style.display == "block"){
		document.getElementById(id1).style.display = "none";
		document.getElementById(id2).innerText = "显示";
		//friendList();
	}else{
		document.getElementById(id1).style.display = "block";
		document.getElementById(id2).innerText = "隐藏";
	}
}
/**************************view item script*****************/
function viewMemoItem(_memoid){
	//currentview = "memoItemView";
	displayMemoItemView();
	getViewItem(_memoid);
}
function displayMemoItemView(){
	if(currentview === "monthview"){
		document.getElementById("monthviewmaindiv").style.display = "none";
	}else{
		document.getElementById("usermainpagediv").style.display = "none";
		document.getElementById("userdaypagediv").style.display = "none";
		document.getElementById("userweekpagediv").style.display = "none";		
	}
	document.getElementById("viewMemoDiv").style.display = "block";
}
function cancelViewMemoItem(){
	document.getElementById("viewMemoDiv").style.display = "none";
	currentDisplayControl();
}
var currentbaseitemid;
function getViewItem(_memoid){
	//alert(_memoid);
	var url = baseurl+"/memoc.memo?action=itemCtrl&actiontype=5&id="+_memoid;
	var xml = "";
	currentbaseitemid = _memoid;
	xmlHttp = itemHttp;
	regXmlHttp = sendXmlReq(url, xml);
	regXmlHttp.onreadystatechange = updateViewItem;
}
function updateViewItem(){
	try{
		if (xmlHttp.readyState == 4) {
			var responseText = xmlHttp.responseText;
			fillViewItemPage(responseText);
		}
		release();
	}catch(e){
		release();
	}
}
		
function fillViewItemPage(biXml){
	document.getElementById("starttime_view").innerText = parserBiXML(biXml, "stime");
	document.getElementById("endtime_view").innerText = parserBiXML(biXml, "etime");
	document.getElementById("alerttime_view").innerText = parserBiXML(biXml, "atime");
	document.getElementById("memotype_view").value = parserBiXML(biXml, "type");
	document.getElementById("alerttype_view").value = parserBiXML(biXml, "atype");
	document.getElementById("priority_view").value = parserBiXML(biXml, "priority");
	document.getElementById("ispublic_view").value = parserBiXML(biXml, "ispublic");
	document.getElementById("invitelist_view").innerText = parserBiXML(biXml, "invite");
	document.getElementById("titleContent_view").innerText = parserBiXML(biXml, "title");
	document.getElementById("itemContent_view").innerText = parserBiXML(biXml, "content");
	document.getElementById("location_view").innerText = parserBiXML(biXml, "location");
	var isowner = parserBiXML(biXml, "isowner");
	if(isowner == "0"){
		
	}else{
		edititemview.disabled="disabled" ;
		delitemview.disabled="disabled" ;
	}
}
/*****************************Topic List control**************************/
function getTopicFromMe(){
	//var url = "opentopic.memo?action=showTopic&itemid=" + currentbaseitemid;
	//window.open(url, "_self");
}
function addReply(){
	var xmlcontent = document.getElementById("replytopiccontent").value;
	//xmlcontent = escape(xmlcontent);
	var url = "opentopic.memo?action=addTopic";
	xmlHttp = topicHttp;
	regXmlHttp = sendXmlReq(url, xmlcontent);
	regXmlHttp.onreadystatechange = updateDiscussionPage;	
}
function updateDiscussionPage(){
	window.location.reload();
}
/******************************Discussion control**************************/
function gotoDiscussion(){
	var url = "opentopic.memo?action=showTopic&itemid=" + currentbaseitemid;
	window.open(url, "_new");
}
function addReply(){
	var xmlcontent = document.getElementById("replytopiccontent").value;
	//xmlcontent = escape(xmlcontent);
	var url = "opentopic.memo?action=addTopic";
	xmlHttp = topicHttp;
	regXmlHttp = sendXmlReq(url, xmlcontent);
	regXmlHttp.onreadystatechange = updateDiscussionPage;	
}
function updateDiscussionPage(){
	window.location.reload();
}

/*****************************Add new item*********************************/

function addNewMemo(){
	document.getElementById("optype").value = "add";
	if(currentview === "monthview"){
		document.getElementById("monthviewmaindiv").style.display = "none";
	}else{
		document.getElementById("usermainpagediv").style.display = "none";
		document.getElementById("userdaypagediv").style.display = "none";
		document.getElementById("userweekpagediv").style.display = "none";
	}
	document.getElementById("currentviewhi").value = currentview;
	document.getElementById("yearhi").value = year;
	document.getElementById("monthhi").value = month;
	document.getElementById("dayhi").value = day;
	
	
	document.getElementById("viewMemoDiv").style.display = "none";
	document.getElementById("addNewMemoDiv").style.display = "block";
	setDefaultTime();
}
function closeAddpanel(){
	currentDisplayControl();
	document.getElementById("addNewMemoDiv").style.display = "none";
}
function refreshTime(){
	var curtimeadd = year
		 + "-" + ((month< 10) ? ("0"+month):month)
		 + "-" + ((day < 10) ? ("0"+day):day);
	document.getElementById("starttime").value = curtimeadd;
	document.getElementById("endtime").value = curtimeadd;
	document.getElementById("alerttime").value = curtimeadd;
	document.getElementById("memotype").value =  3;
	document.getElementById("alerttype").value = 'o';
	document.getElementById("priority").value = 2;
	document.getElementById("invitelist").value = "";
	document.getElementById("location").value = "";
	document.getElementById("titleContent").value = "";
	document.getElementById("itemContent").value = "";
	document.getElementById("ispublic").value = 0;
	publicornot();
	changeAlertType();
}
function setDefaultTime(){
	var curtimeadd = year
		 + "-" + ((month< 10) ? ("0"+month):month)
		 + "-" + ((day < 10) ? ("0"+day):day);
	if(document.getElementById("starttime").value == ""){
		document.getElementById("starttime").value = curtimeadd;
	}
	if(document.getElementById("endtime").value == ""){
		document.getElementById("endtime").value = curtimeadd;
	}
	if(document.getElementById("alerttime").value == ""){
		document.getElementById("alerttime").value = curtimeadd;
	}			
}


/************************Edit item script***********************************/
function editItem(){
	//debugger;parseInt(value)
	var stime_edit = document.getElementById("starttime_view").innerText;
	var etime_edit = document.getElementById("endtime_view").innerText;
	var atime_edit = document.getElementById("alerttime_view").innerText;
	var type_edit = document.getElementById("memotype_view").value;
	var atype_edit = document.getElementById("alerttype_view").value;
	var priority_edit = document.getElementById("priority_view").value;
	var invite_edit = document.getElementById("invitelist_view").innerText;
	var location_edit = document.getElementById("location_view").innerText;
	var title_edit = document.getElementById("titleContent_view").innerText;
	var content_edit = document.getElementById("itemContent_view").innerText;
	var ispublic_edit = document.getElementById("ispublic_view").value;
	
	addNewMemo();
	document.getElementById("optype").value = "update";
	
	document.getElementById("starttime").value = getDateFromWholeDate(stime_edit);
	document.getElementById("starttimeh").value = getHoursFromWholeDate(stime_edit);
	document.getElementById("starttimem").value = getMinutesFromWholeDate(stime_edit);
	
	document.getElementById("endtime").value = getDateFromWholeDate(etime_edit);
	document.getElementById("endtimeh").value = getHoursFromWholeDate(etime_edit);
	document.getElementById("endtimem").value = getMinutesFromWholeDate(etime_edit);
	
	document.getElementById("alerttime").value = getDateFromWholeDate(atime_edit);
	document.getElementById("alerttimeh").value = getHoursFromWholeDate(atime_edit);
	document.getElementById("alerttimem").value = getMinutesFromWholeDate(atime_edit);
	
	document.getElementById("memotype").value =  type_edit;
	document.getElementById("alerttype").value = atype_edit;
	document.getElementById("priority").value = priority_edit;
	document.getElementById("invitelist").value = invite_edit;
	document.getElementById("location").value = location_edit;
	document.getElementById("titleContent").value = title_edit;
	document.getElementById("itemContent").value = content_edit;
	document.getElementById("ispublic").value = ispublic_edit;
	//set alert msg
	publicornot();
	changeAlertType();
	checkAllInput();
}

/************************Day view control script****************************/
function getDayCalItem(){
	var _m = ((""+month).length == 1)? ("0" + month) : month;
	var _d = ((""+day).length==1)? ("0" + day) : day;
	starttime =  year + "-" + _m + "-" + _d + " 00:00:00";
	var url = baseurl+"/memoc.memo?action=itemCtrl&actiontype=4&stime="+starttime+"&mdays=1&month="+month;
	var xml = "";
	xmlHttp = itemDayHttp;
	regXmlHttp = sendXmlReq(url, xml);
	regXmlHttp.onreadystatechange = updateGetDayCalItem;
}
function updateGetDayCalItem(){

	try{
		if (xmlHttp.readyState == 4) {
			var responseText = xmlHttp.responseText;
			fillDayItems(responseText);
			release();
		}else{
			release();
		}
	}catch(e){
		release();
	}
}
function fillDayItems(responses){
	//daytimeshowtd.innerHTML = "helllo";// year + "-" + month + "-" + day;
	document.getElementById("daytimeshowtd").innerHTML = year + "-" + month + "-" + day;
	document.getElementById("daylistcontentdiv").innerHTML = responses;
}
function showDayView(){
	currentview = "dayView";
	currentDisplayControl();
	getDayCalItem();
}
function showPreDayView(){
	var todaydate = new Date(year, (month-1),day);
	var predate = getPreDate(todaydate);
	year = predate.getYear();
	month = (predate.getMonth()+1);
	day = predate.getDate();
	showDayView();
	//gotoDayView();

}
function showNextDayView(){
	var todaydate = new Date(year, (month-1),day);
	var nextdate = getNextDate(todaydate);
	year = nextdate.getYear();
	month = (nextdate.getMonth()+1);
	day = nextdate.getDate();
	showDayView();
	//gotoDayView();
}
function gotoDayView(){
	if(currentview === "monthview"){
		window.open(baseurl+"/personindex.jsp?currentView=dayView&year="+year+"&month="+month+"&day="+day, "_self");
	}else{
		showDayView();
	}
	
}
/************************Week view control script****************************/
function gotoWeekView(){
	if(currentview === "monthview"){
		window.open(baseurl+"/personindex.jsp?currentView=weekView&year="+year+"&month="+month+"&day="+day, "_self");
	}else{
		showWeekView();
	}
}
function getWeekCalItem(){
	var url = baseurl+"/memoc.memo?action=itemCtrl&actiontype=3&stime="+starttime+"&mdays=7&month="+month;
	var xml = "";
	if(requesting){
		return;
	}
	xmlHttp = itemWeekHttp;
	regXmlHttp = sendXmlReq(url, xml);
	regXmlHttp.onreadystatechange = updateGetWeekCalItem;
}
function updateGetWeekCalItem(){
	try{
		if (xmlHttp.readyState == 4) {
			var responseText = xmlHttp.responseText;
			fillWeekItems(responseText);
			release();
		}else{
			release();
		}
	}catch(e){
		release();
	}
}
function fillWeekItems(responses){
	var fillIt ;
	fillIt = responses.split("<IAMSPLIT/>");
	for(var i=1;i<=7;i++){
		var tempId = "weekdiv_"+i;
		if(fillIt[i-1] === "<IAMEMPTY/>"){
			document.getElementById(tempId).innerHTML = "";
		}else{
			document.getElementById(tempId).innerHTML = fillIt[i-1];
		}
	}
}

function showWeekView(){
	currentview = "weekView";
	currentDisplayControl();
	getWeeklyTime();
	fillWeekTitle();
	getWeekCalItem();
}
//Show next week view
function showNextWeekView(){
	var nextmonday = getNextDate(cursunday);
	year = nextmonday.getYear();
	month = nextmonday.getMonth() + 1;
	day = nextmonday.getDate();
	showWeekView();
}
//Show pre-week view
function showPreWeekView(){

	for(var i=0; i<7; i++){
		curmonday = getPreDate(curmonday);
	}

	year = curmonday.getYear();
	month = curmonday.getMonth() + 1;
	day = curmonday.getDate();
	showWeekView();
}
//Fill weekly table show day`s title
function fillWeekTitle(){
	var str = starttime;
	var title;
	var tempId ;
	var weekTimeShow;
	var titleDate = new Date(str.split("-")[0],(str.split("-")[1]-1),str.split("-")[2].split(" ")[0]);
	for(var i=1;i<=7;i++){
		tempId = "weektitle_"+i;
		title = weekDays[i-1] ;//+ titleDate.getYear()+"."+
		title =  title+"   " + (titleDate.getMonth() + 1) + "." + titleDate.getDate()+ "";
		if(i==1){
			curmonday = titleDate;
			weekTimeShow = (titleDate.getMonth() + 1) + "." + titleDate.getDate();
		}else if(i==7){
			cursunday = titleDate;
			weekTimeShow = weekTimeShow + " - " + (titleDate.getMonth() + 1) + "." + titleDate.getDate();
		}
		//Set weekly time title
		document.getElementById("weektimeshowtd").innerHTML = weekTimeShow;
		//Set weekly day title
		document.getElementById(tempId).innerHTML = title;
		//put day to weekdayscache
		weekdayscache[i-1] = titleDate.getDate();
		weekmonthcache[i-1] = titleDate.getMonth();
		weekyearcache[i-1] = titleDate.getYear();
		titleDate = getNextDate(titleDate);
	}
}

function getWeeklyTime(){
	starttime = "";
	var tempdate = new Date(year, month-1, day);
	intDays = tempdate.getDay();
	if(intDays == 0){
		intDays = 7;
	}
	if((day >= intDays) && (month > 0)){ //
		tempdate.setDate(( day - intDays + 1));
		tempdate.setYear(year);
		tempdate.setMonth((month-1));
	}else{
		if(month == 1){
			tempdate.setMonth(11);
			tempdate.setYear(year - 1);
			tempdate.setDate((32 + day - intDays));
		}else{
			tempdate.setMonth((month-2));
			var curMonths = ((year % 4 == 0))? utilDays1 : utilDays0;
			tempdate.setDate((curMonths[month-2]) + day - intDays +1);		
			tempdate.setYear(year);
		}
	}
	starttime = tempdate.getYear() + "-" + (tempdate.getMonth()+1) + "-" + tempdate.getDate() + " 00:00:00";
}
function newItemInWeekDay(dayInWeek){
	day = weekdayscache[dayInWeek -1];
	month = (weekmonthcache[dayInWeek -1]+1);
	year = weekyearcache[dayInWeek -1];
	refreshTime();
	addNewMemo();
}
/************************Month view control script****************************/
function getMonthCalItem(_starttime,_endtime,_day){
	//starttime  endtime  titleContent itemContent
	monthDayNum = _day;
	var url = baseurl+"/memoc.memo?action=itemCtrl&actiontype=2&stime="+_starttime+"&mdays="+monthDayNum;
	var xml = "";
	xmlHttp = itemMoonHttp;
	regXmlHttp = sendXmlReq(url, xml);
	regXmlHttp.onreadystatechange = updateGetMonthCalItem;
}
function gotoMonthView(y, m){
	if( m >0 && m <13){
		window.open(baseurl+"/personindexMonth.jsp?year="+y+"&month="+m, "_self");
	}else{
		window.open(baseurl+"/personindexMonth.jsp?year="+year+"&month="+month, "_self");
	}
}

function testDate(date){
	alert(date);
}
function testTime(pre){
	alert(pre + ": " + year + "-" + month + "-" + day);
}

function updateGetMonthCalItem(){
	try{
		if (xmlHttp.readyState == 4) {
			var responseText = xmlHttp.responseText;
			fillMonthItems(responseText);
			release();
		}else{
			release();
		}
	}catch(e){
		release();
	}

}
function fillMonthItems(responses){
	var fillIt ;
	fillIt = responses.split("<IAMSPLIT/>");
	for(var i=0;i<monthDayNum;i++){
		var tempId = "monthdiv_"+(i+1);
		if(fillIt[i] === "<IAMEMPTY/>"){
		}else{
			document.getElementById(tempId).innerHTML = fillIt[i];
		}
	}
}

function getMonthTime(){
	starttime = "";
	endtime = "";
	starttime = year + "-" + month + "-01"  + " 00:00:00";
	endtime = year + "-" + month + "-" + daysInMonthtop[month-1] + " 23:59:59";
}
function showDayViewInMonthView(_tday){
	day = _tday;
	gotoDayView();
}
function newItemInMonthDay(daily){
	day = daily;
	refreshTime();
	addNewMemo();
}
function preMonth(){
	if(month == 1){
		month = 12;
		year = year -1;
	}else{
		month = month -1;
	}
	gotoMonthView(year, month);
}
function nextMonth(){
	if(month == 12){
		month = 1;
		year = year + 1;
	}else{
		month = month + 1;
	}
	gotoMonthView(year, month);
}
/************************Delete memo item script****************************/
function deleteItem(title){
	var url = baseurl+"/memoc.memo?action=itemCtrl&actiontype=6&title="+title;
	var xml = "";
	xmlHttp = itemHttp;
	regXmlHttp = sendXmlReq(url, xml);
	regXmlHttp.onreadystatechange = updateDeleteItem;
}
function updateDeleteItem(){
	window.location.reload();
}

function confirmAction(msg){
	var is = window.confirm(msg);
	return is;
}