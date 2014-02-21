package com.xp.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class HtmlGenerator {
//	private XPUtil util = new XPUtil();
//	private String[] colors = {"red","red"};
//	private int count = 0;
//	private int number;
//	private UserLogic ul = new UserLogic();
//	/**
//	 * Generate day show time bar
//	 * @param bi
//	 * @param thisTime
//	 * @return
//	 */
//	public String generateTimeBar(BaseItem bi, String thisTime, int number){
//		this.number = number;
//		StringBuffer reSb = new StringBuffer();
//		int startTd = 0;
//		int endTd = 0;
//		Timestamp todays = Timestamp.valueOf(thisTime);
//		Timestamp todaye = (Timestamp)todays.clone();
//		todaye.setHours(23);
//		todaye.setMinutes(59);
//		todaye.setSeconds(59);
//		reSb.append("<table  class='daytabletimebar' cellspacing=0 cellpadding=0><tr>");
//		if(bi.getAlertType() == 'o'){
//			if(bi.getStartTime().before(todays) ){
//				startTd = 0;
//				if(bi.getEndTime().after(todaye)){
//					endTd = 48;
//				}else if(bi.getEndTime().before(todaye)){
//					endTd = (bi.getEndTime().getHours() * 2) + ((bi.getEndTime().getMinutes()<30)? 0:1);
//				}
//			}else{
//				startTd = (bi.getStartTime().getHours() * 2) + ((bi.getStartTime().getMinutes()<30)? 0:1);
//				if(bi.getEndTime().before(todaye)){
//					endTd = (bi.getEndTime().getHours() * 2) + ((bi.getEndTime().getMinutes()<30)? 0:1);				
//				}else{
//					endTd = 48;
//				}
//			}
//		}else{
//			startTd = (bi.getStartTime().getHours() * 2) + ((bi.getStartTime().getMinutes()<30)? 0:1);
//			endTd = (bi.getEndTime().getHours() * 2) + ((bi.getEndTime().getMinutes()<30)? 0:1);
//		}
//		
//		if(startTd > 0){
//			for(int i=0;i<startTd;i++){
//				reSb.append(this.getDayTimeBarTR(0));
//			}
//		}
//		
//		for(int i=startTd;i<endTd;i++){
//			reSb.append(this.getDayTimeBarTR(1));
//		}
//		if(endTd <= 47){
//			for(int i=endTd;i<48;i++){
//				reSb.append(this.getDayTimeBarTR(0));
//			}
//		}
//		reSb.append("</tr></table>");
//		return reSb.toString();
//	}
//	/**
//	 * 
//	 * @param type: 0: white, 1 red ,2 green, 3 blue 
//	 * @return
//	 */
//	private String getDayTimeBarTR(int type){
//		StringBuffer sb = new StringBuffer();
//		String color = "white";
//		switch (type){
//			case 0:
//				color = "white";
//			break;
//			case 1:
//				color = colors[number%2];
//			break;
//
//		}
//		sb.append("<td style='background-color:" + color + ";height:20px;width:2%;'></td>");//
//		
//		return sb.toString();
//	}
//	/**
//	 * Parse one day`s item list, to two part: 
//	 * 	list[0]: cross day`s items, will show in top of page
//	 *  list[1]: one day`s items, will show after list[0] showed
//	 * @param list
//	 * @return
//	 */
//	public List[] parserItemList(List list, String thisTime){
//		List[] lists = new ArrayList[2];
//		lists[0] = new ArrayList();
//		lists[1] = new ArrayList();
//		for(int i=0; i < list.size();i++){
//			BaseItem bi = (BaseItem)list.get(i);
//			if(util.isOneDayItem(bi, thisTime)){
//				lists[1].add(bi);
//			}else{
//				lists[0].add(bi);
//			}
//		}
//		return lists;
//	}
//	public String generateItemTable(List list, String thisTime){
//		if(list == null || list.size() == 0){
//			return "";
//		}
//		List[] pList = new ArrayList[2];
//		pList = this.parserItemList(list, thisTime);
//		list.clear();
//		list.addAll(pList[0]);
//		list.addAll(pList[1]);
//		StringBuffer reSb = new StringBuffer();
//		String viewType = "m_w";//for compose id
//		String id = "";
//		String prioCss = "";
//		String[] timeSlot = new String[2];
//		reSb.append("<table width='100%' cellpadding=1 cellspacing=1>");
//		
//		for(int i=0; i < list.size();i++){
//			BaseItem bi = (BaseItem)list.get(i);
//			id = "itemqviewdiv" + thisTime.split(" ")[0].split("-")[2] +viewType+bi.getId();
//			reSb.append("<tr style='cursor:hand' onclick='viewMemoItem("+bi.getId()+")'>");
//			switch (bi.getPriority()) {
//			case 0:
//				prioCss = "monthitemshowbasetdvh";
//				break;
//			case 1:
//				prioCss = "monthitemshowbasetdhigh";
//				break;
//			case 2:
//				prioCss = "monthitemshowbasetdmed";
//				break;
//			case 3:
//				prioCss = "monthitemshowbasetdlow";
//				break;
//			default:
//				prioCss = "monthitemshowbasetdlow";
//				break;
//			}
//			reSb.append("<td class='" + prioCss + "'>");
//			reSb.append("<a onmouseover='showQuickView(\""+id+"\")' onmouseout='closeQuickView(\""+id+"\")' >");
//			timeSlot = util.getItemShowTimeSlot(bi, thisTime);
//			
//			if(!timeSlot[1].equals(Constant.timeAcrossType[2])){
//				//one item cross several days
//				reSb.append("<div class='monthitemshowdivsevday'>");
//				reSb.append("<p class='dayitemtitlep'>");
//				if(bi.getAlertType()!= 'o'){
//					reSb.append("<img width=12px height=11px src='pic/cycle/cycle1.gif'/>");
//					reSb.append(bi.getStartTime().getHours() + ":" + bi.getStartTime().getMinutes());
//				}else if(timeSlot[1].equals(Constant.timeAcrossType[0])){
//					reSb.append(timeSlot[0]);
//				}
//				reSb.append("<font color=blue>"+bi.getTitle()+"</font>");
//				reSb.append("</p>");
//				reSb.append("</div>");
//			}else{
//				//one item in one day
//				reSb.append("<p class='dayitemtitlep'>");
//				reSb.append( timeSlot[0]);
//				reSb.append("<font color=blue>"+bi.getTitle()+"</font>");
//				reSb.append("</p>");
//			}
//			
//			reSb.append("</a></td>");
//			reSb.append("</tr>");
//			reSb.append("<tr><td>");
//			reSb.append(genItemQuickViewDiv(bi, id));
//			reSb.append("</td></tr>");
//		}
//		reSb.append("</table>");
//		return reSb.toString();
//	}
//	public String genItemQuickViewDiv(BaseItem bi, String id){
//		XPUtil util = new XPUtil();
//		StringBuffer sb = new StringBuffer();
//		String titleCss = "";
//		switch (bi.getPriority()) {
//		case 0:
//			titleCss = "viewmemotitlered";
//			break;
//		case 1:
//			titleCss = "viewmemotitlered";
//			break;
//		case 2:
//			titleCss = "viewmemotitlegreen";
//			break;
//		case 3:
//			titleCss = "viewmemotitleyellow";
//			break;
//		default:
//			titleCss = "viewmemotitlegreen";
//			break;
//		}
//		sb.append("<div id='" + id + "' class='itemquickviewdiv'>");
//		sb.append("<table cellpadding='1' cellspacing='0'  width='100%'>" );
//		sb.append("<tr class='"+titleCss+"'>");
//		sb.append("<td style='width:60px;font-size: 14px;'>");
//		if(bi.getAlertType()!= 'o'){
//			sb.append("<img width=13px height=13px src='pic/cycle/cycle1.gif'/>");
//		}
//		sb.append("</td>");		
//		sb.append("<td>"+Constant.memoType[bi.getType()]);
//		sb.append("</tr><tr><td  style='width:60px;font-size: 14px;'><div style='width:60px'>时间：</div></td><td style='font-size: 14px;width: 100px'><div style='width:100px'>"+ bi.getStartTime().getHours()+":"+ bi.getStartTime().getMinutes()+"-"+bi.getEndTime().getHours()+":"+bi.getEndTime().getMinutes()+"</div>");
//		if(bi.getIspublic() == 1){
//			sb.append("</td></tr><tr><td style='color:blue;width:60px;font-size: 14px;'  valign='top'>发起人：</td><td style='color:blue;font-size: 14px;'>"+ this.ul.getUser(bi.getUserId()).getNickname() );
//			sb.append("</td></tr><tr><td style='width:60px;font-size: 14px;'  valign='top'>邀请了：</td><td style='font-size: 14px;'>"+bi.getInvite());
//		}
//		sb.append("</td></tr><tr><td style='color:blue;width:60px;font-size: 14px;' valign='top'>主题：</td><td style='color:blue;font-size: 14px;'>"+bi.getTitle());
//		sb.append("</td></tr><tr><td style='width:60px;font-size: 14px;' valign='top'>内容：</td><td style='font-size: 14px;'>"+bi.getContent().replaceAll("\r\n","<br />"));
//		sb.append("</td></tr></table></div>");
//		return sb.toString();
//	}
///**********************************************************************/
//	public String generateInviteReqHtml(List list){
//		StringBuffer sb = new StringBuffer();
//		BaseItem bi;
//		String id = "invitediv_";
//		for(int i=0; i<list.size(); i++){
//			bi = (BaseItem)list.get(i);
//			id = id + bi.getId();
//			sb.append("<table cellpadding=0 cellspacing=0 class=basetable1><tr style=''>");
//			//sb.append("<td style='width:60px;font-size:13px;'>" + Constant.memoType[bi.getType()]+ "]</td>");
//			sb.append("<td style='font-size: 15px;width:50px;cursor:hand' onmouseover='showQuickView(\""+id+"\")' onmouseout='closeQuickView(\""+id+"\")' >时间： </td>");
//			sb.append("<td style='font-size: 15px;width:90px;cursor:hand' onmouseover='showQuickView(\""+id+"\")' onmouseout='closeQuickView(\""+id+"\")' >"+(bi.getStartTime().getMonth()+1)+"-"+bi.getStartTime().getDate()+" "+bi.getStartTime().getHours()+":"+bi.getStartTime().getMinutes()+"</td>");
//			sb.append("<td style='font-size: 15px;width:65px;cursor:hand' onmouseover='showQuickView(\""+id+"\")' onmouseout='closeQuickView(\""+id+"\")' >发起人： </td>");
//			sb.append("<td style='font-size: 15px;cursor:hand' onmouseover='showQuickView(\""+id+"\")' onmouseout='closeQuickView(\""+id+"\")' >" + ul.getUser( bi.getUserId()).getNickname());
//			sb.append("</td>");
//			sb.append("<td style='font-size: 15px;cursor:hand' align=left onmouseover='showQuickView(\""+id+"\")' onmouseout='closeQuickView(\""+id+"\")'  align='left'>" + bi.getTitle() + "</td>");
//			sb.append("<td style='font-size: 15px;width:50px;'><a href='javascript:acptOrRejectInvite(\"0\", \""+bi.getId()+"\")' >接受</a> </td>");
//			sb.append("<td style='font-size: 15px;width:50px;'><a href='javascript:acptOrRejectInvite(\"1\", \""+bi.getId()+"\")' >拒绝</a> </td>");
//			sb.append("</tr><tr><td>"+genItemQuickViewDiv(bi,id)+"</td>");
//			sb.append("</tr></table>");
//			
//		}
//		return sb.toString();
//	}
}