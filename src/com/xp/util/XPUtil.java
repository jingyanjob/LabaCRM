package com.xp.util;

import java.io.BufferedReader;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;


public class XPUtil {
	/**
	 * Remove id from str, str structure should be:  id1;id2;id3;.....
	 * @param str
	 * @param id
	 * @return
	 */
	public String removeId1(String str, String id){
		if(str.indexOf(";")<=0)
			return str;
		StringBuffer value = new StringBuffer();
		String[] strs = str.split(";");
		for(int i=0;i<strs.length; i++){
			if(!strs[i].equals(id)){
				value.append(strs[i]+";");
			}
		}
		return value.toString();
	}
	public boolean isIdinIds(String ids, String id){
		boolean is = false;
		StringBuffer value = new StringBuffer();
		String[] strs = ids.split(";");
		id = id.replaceAll(";", "");
		for(int i=0;i<strs.length; i++){
			if(is)
				return is;
			if(strs[i].equals(id)){
				is = true;
			}
		}
		return is;
	}
	public static String getValue(int arg){
		String value = "00" + arg;
		if(value.length() == 3)
			value = value.substring(1);
		else
			value = value.substring(2);
		return value;
	}
	
	public static String getTime(){
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int _month = cal.get(Calendar.MONTH)+1;
		String month = getValue(_month);
		String day = getValue(cal.get(Calendar.DAY_OF_MONTH));
		String hour = getValue(cal.get(Calendar.HOUR_OF_DAY));
		String min = getValue(cal.get(Calendar.MINUTE));
		String sec = getValue(cal.get(Calendar.SECOND));
		String res = year+"-"+month+"-"+day+" "+hour+":"+min+":"+sec;
		return res;
	}
	
	public String readXMLFromReq(HttpServletRequest req){
		StringBuffer xml = new StringBuffer();
		String line = null;
		try{
			BufferedReader r= req.getReader();
			while((line = r.readLine())!= null){
				xml.append(line);
				xml.append("\r\n");
			}
			if(xml.length() > 2){
				xml.deleteCharAt(xml.length()-1);
				xml.deleteCharAt(xml.length()-1);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return xml.toString();
	}
	
	public String getXmlContent(String xml, String tag){
		String startTag = "<" + tag + ">";
		String endTag = "</" + tag + ">";
		String temp = null;
		int s = xml.indexOf(startTag) + startTag.length() ;
		int e = xml.indexOf(endTag);
		temp = xml.substring(s, e);
		return temp;
	}
	
	public String getFixedTime1(String value){
		if(value.length() == 1){
			value = "0" + value;
		}
		return value;
	}
	
	public String getWeekDayTime(String dayStart, int mDay) {
		String strs = null;
		int day = Integer.parseInt(dayStart.split("-")[2].split(" ")[0]) + mDay;
		int month = Integer.parseInt(dayStart.split("-")[1]);
		int year = Integer.parseInt(dayStart.split("-")[0]);
		if((year%4) > 0){
			if(day > Constant.month0[month-1]){
				day = day - Constant.month0[month-1];
				month = month + 1;
			}
		}else{
			if(day > Constant.month1[month-1]){
				day = day - Constant.month0[month-1] ;
				month = month + 1;
			}
		}
		if(day < 10){
			if(month < 10)
				strs = year + "-0" + month + "-0" + day + " 00:00:00";
			else
				strs = year + "-" + month + "-0" + day + " 00:00:00";
		}
			
		else{
			if(month < 10)
				strs = year + "-0" + month + "-" + day + " 00:00:00";
			else
				strs = year + "-" + month + "-" + day + " 00:00:00";
		}
		return strs;
	}
	
	public String getMoonDayTime(String dayStart, int mDay) {
		String strs = null;
		if(dayStart.split("-")[1].length() < 2){
			if((mDay+1) < 10)
				strs = dayStart.split("-")[0] + "-0" + dayStart.split("-")[1] + "-0"
					+ (mDay+1) + " 00:00:00";
			else
				strs = dayStart.split("-")[0] + "-0" + dayStart.split("-")[1] + "-"
				+ (mDay+1) + " 00:00:00";
		}else{
			if((mDay+1) < 10)
				strs = dayStart.split("-")[0] + "-" + dayStart.split("-")[1] + "-0"
					+ (mDay+1) + " 00:00:00";
			else
				strs = dayStart.split("-")[0] + "-" + dayStart.split("-")[1] + "-"
				+ (mDay+1) + " 00:00:00";
		}
		return strs;
	}

	public static String removeDotZeroOfDate(Timestamp tt){
		return tt.getYear() +"-" + tt.getMonth()+"-" + tt.getDay() + " " + tt.getHours() +":" + tt.getMinutes() +":"+tt.getSeconds();
	}
	/**
	 * @param time
	 * @return mm-dd hh:mm
	 */
	public  String getShortTime1(String time){
		String[] strs = time.split(" ");
		String[] strs1 = strs[0].split("-");
		String[] strs2 = strs[1].split(":");
		StringBuffer sb = new StringBuffer();
		sb.append(strs1[1]);
		sb.append("-");
		sb.append(strs1[2]);
		sb.append(" ");
		sb.append(strs2[0]);
		sb.append(":");
		sb.append(strs2[1]);
		return sb.toString();
	}
	/**
	 * @param time
	 * @return hh:mm
	 */
	public  String getShortTime2(String time){
		String[] strs = time.split(" ");
		String[] strs2 = strs[1].split(":");
		StringBuffer sb = new StringBuffer();
		sb.append(strs2[0]);
		sb.append(":");
		sb.append(strs2[1]);
		return sb.toString();
	}
	public static String getShortTitle(String title){
		if(title.getBytes().length > 10){
			return title.substring(0, 5);
		}else{
			return title;
		}
	}
	public static void main(String[] str){
		XPUtil util = new XPUtil();
		System.out.print(util.isIdinIds("110", "103;109;110;110;110;110;110;"));
	}
//	public static User genTempUser(){
//		User u = new User();
//		u.setNickname("体验用户");
//		u.setPassword("");
//		u.setEmail("");
//		u.setIsexpr(1);
//		return u;
//	}
}