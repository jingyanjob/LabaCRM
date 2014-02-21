var utilDays0 = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
var utilDays1 = new Array(31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
var weekDays =  new Array("周一","周二", "周三", "周四","周五", "周六", "周日");
var weekDays1 =  new Array( "日","一","二", "三", "四","五", "六");
var baseItemObj = new Array();
function getNextDate(date){
	var curYear = date.getYear();
	var curMonth = date.getMonth();
	var curDay = date.getDate();
	var nextYear;
	var nextMonth;
	var nextDay;
	var nextDate ;
	var curMonths = ((curYear % 4 == 0))? utilDays1 : utilDays0;
	if((curDay) < curMonths[curMonth]){
		nextYear = curYear;
		nextMonth = curMonth;
		nextDay = curDay+1;
	}else{
		if((curMonth) < 11){
			nextYear = curYear;
			nextMonth = curMonth + 1;
			nextDay = 1;
		}else{
			nextYear = curYear + 1;
			nextMonth = 0;
			nextDay = 1;
		}
	}
	nextDate = new Date(nextYear, nextMonth, nextDay);
	return nextDate;
}
function getPreDate(date){
	var curYear = date.getYear();
	var curMonth = date.getMonth();
	var curDay = date.getDate();
	var preYear;
	var preMonth;
	var preDay;
	var preDate ;
	var curMonths
	if(curMonth == 0 && curDay == 1){
		curYear = curYear - 1;
		preDate = new Date((curYear ), 11, 31);
		return preDate;
	}
	curMonths = ((curYear % 4 == 0))? utilDays1 : utilDays0;
	
	if(curDay == 1){
		preYear = curYear;
		preMonth = curMonth -1;
		preDay = curMonths[preMonth];
	}else{
		preDay = curDay - 1;
		preYear = curYear;
		preMonth = curMonth;
	}
	preDate = new Date(preYear, preMonth, preDay);
	return preDate;
}

function divShowControl(divId){
	if(document.getElementById(divId).style.display == "none"){
		document.getElementById(divId).style.display = "block";
	}else{
		document.getElementById(divId).style.display = "none";
	}
}

function checkAddMemoInput(stime, etime, alerttime, type, alerttype, title, icont, priority, invitelist){
	var isOk = true;
	
	return isOk;
}
function checkTimeSlot(stime, etime){
}
function parserBiXML(biXml, tagstr){
	return getXmlTagContent(biXml, tagstr);
}
function getXmlTagContent(xmlstr, tagstr){
	var stag = "<" + tagstr + ">";
	var etag = "</" + tagstr + ">";
	var startpos = xmlstr.indexOf(stag) + stag.length;
	var endpos = xmlstr.indexOf(etag);
	xmlstr = xmlstr.substring(startpos, endpos);
	return xmlstr;
}
function getDateFromWholeDate(wholedate){
	var date;
	date = wholedate.split(" ")[0];
	return date;
}
function getHoursFromWholeDate(wholedate){
	var hours;
	hours = wholedate.split(" ")[1].split(":")[0];
	return hours;
}
function getMinutesFromWholeDate(wholedate){
	var minutes;
	minutes = wholedate.split(" ")[1].split(":")[1];
	return minutes;
}
function getDateObj(datestr){// input: yyyy-mm-dd
	var items = datestr.split("-");
	return new Date(items[0], (items[1]-1), items[2]);
}
//remove all space of str
function removespace(str){
	var v = "";
	if(str.indexOf(" ") >= 0){
		v = str.replace(" ","");
		v = removespace(v);
	}else{
		v = str;
	}
	return v;
}
function getXXXXXX(){
	
}
