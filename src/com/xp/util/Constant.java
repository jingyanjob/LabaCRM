package com.xp.util;

public class Constant {
	public final static int[] month0 = { 31, 28, 31, 30, 31, 30, 31, 31, 30,
			31, 30, 31 };
	public final static int[] month1 = { 31, 29, 31, 30, 31, 30, 31, 31, 30,
			31, 30, 31 };
	public final static String[] alertType = {"一次","按日循环","按周循环","按月循环","按年循环"};
	
	public final static String[] memoType = { "生日", "约会", "聚会", "待办事项", "个人计划",
			"纪念日", "", "", "", "", "其他" };
	public final static String[] priorities = {"很高","高","中","低" };
	
	/*
	 * F: several days action, and from today
	 * S: several days, today is not starting day
	 * O: One day
	 */
	public final static String[] timeAcrossType = {"F","S", "O"}; 
	
	public final static int titleLength = 10;

}