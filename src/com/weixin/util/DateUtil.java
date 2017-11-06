package com.weixin.util;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	/*
	 *获取日期
	 * */
	public static Date getCurrentDate(){
		return new Date();
	}
	/*
	 *获取时间戳
	 * */
	public static String getTimeMin(){
		return String.valueOf(new Date().getTime());
	}
	/**
	 * 改变Date日期中年
	 * */
   public static Date getYearOfDates(Date p_date ,int i) {
          java.util.Calendar c = java.util.Calendar.getInstance();
          c.setTime(p_date);
          c.add(Calendar.YEAR,-i);
          return c.getTime();
  }
	/**
	 * 改变Date日期中月
	 * */
   public static Date getMonthOfDate(Date p_date ,int i) {
          java.util.Calendar c = java.util.Calendar.getInstance();
          c.setTime(p_date);
          c.add(Calendar.MONTH,-i);
          return c.getTime();
  }
   /**
	 * 改变Date日期中日
	 * */
  public static Date getLastOneDate(Date p_date ,int i) {
      java.util.Calendar c = java.util.Calendar.getInstance();
      c.setTime(p_date);
      c.add(Calendar.DATE,i);
      return c.getTime();
  }
  /**
	 * 改变Date日期中时
	 * */
public static Date getShiOneDate(Date p_date ,int i) {
    java.util.Calendar c = java.util.Calendar.getInstance();
    c.setTime(p_date);
    c.add(Calendar.MINUTE,i);
    return c.getTime();
}
  public static String getStringMonth(Date date){
 	 SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
      String dateString = formatter.format(date);
      return dateString;
  }
  public static String getStringDay(Date date){
 	 SimpleDateFormat formatter = new SimpleDateFormat("ddHHmmss");
      String dateString = formatter.format(date);
      return dateString;
  }
  public static String getLongStringDay(Date date){
 	 SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
      String dateString = formatter.format(date);
      return dateString;
  }
  public static String getStringMonthDate(Date date){
 	 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
      String dateString = formatter.format(date);
      return dateString;
  }
  public static String getStringMonthDateZi(Date date){
	 	 SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
	      String dateString = formatter.format(date);
	      return dateString;
	  }
    public static Date getNow(){
      Date currentTime = new Date();
      return currentTime;
  }
  public static String getFullStringDate(Date p_date)
  {
          SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM");
          String dateString = formatter.format(p_date);
          return dateString;
  }
 
  public static String dateToStr(java.util.Date dateDate){
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String dateString = formatter.format(dateDate);
      return dateString;
  }
  public static String getStringDate(){
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String dateString = formatter.format(getNow());
      return dateString;
  }
  public static String getStringDateForYM(){
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
      String dateString = formatter.format(getNow());
      return dateString;
  }
  public static String getStringHour(java.util.Date dateDate){
      SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
      String dateString = formatter.format(dateDate);
      return dateString;
  }
  public static String[] getListDate(){
 	 String[] dateList=new String[6];
 	 
 	 for(int i=0;i<6;i++){
 	 Date date=getNow();
 	 date=getMonthOfDate(date,i+1);
 	 String dateString=getFullStringDate(date);
 	 dateList[i]=dateString;
 	 }
 	 return dateList;
  }
  public static Date stringToDate(String stringDate){
 	 SimpleDateFormat simple=new SimpleDateFormat("yyyy年MM月");
 	 ParsePosition pos=new ParsePosition(0);
 	 Date strTodate=simple.parse(stringDate, pos);
 	 return strTodate;
  }
  public static Date stringToDateByHM(String stringDate){
	 	 SimpleDateFormat simple=new SimpleDateFormat("HH:mm");
	 	 ParsePosition pos=new ParsePosition(0);
	 	 Date strTodate=simple.parse(stringDate, pos);
	 	 return strTodate;
	  }
  public static Date stringToDateByAll(String stringDate){
 	 SimpleDateFormat simple=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 	 ParsePosition pos=new ParsePosition(0);
 	 Date strTodate=simple.parse(stringDate, pos);
 	 return strTodate;
  }
  public static Date stringToDateB(String stringDate){
	 	 SimpleDateFormat simple=new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss");
	 	 ParsePosition pos=new ParsePosition(0);
	 	 Date strTodate=simple.parse(stringDate, pos);
	 	 return strTodate;
	  }
  public static String stringToMinString(String string){
 	 Date d=stringToDate(string);
			String st=dateToStr(d);
			return st;
  }
  public static String stringToMaxString(String string){
 	 Date d=stringToDate(string);
			d=getMonthOfDate(d,-1);
			String st=dateToStr(d);
			return st;
  }
  public static long getLongTime(){
 	 Date date=new Date();
 	
 	 return date.getTime();
  }
  
  public static String getRandom(int num){
	  String s="0123456789";
	  StringBuffer sb=new StringBuffer();
	  for(int i=0;i<num;i++){
		  int id=(int)(Math.random()*(s.length()-1));
		  String code=s.substring(id,id+1);
		  sb.append(code);
	  }
	  return sb.toString();
  }
  
  public static boolean IsBaoXiuQi(Date date,int year){
	  Date dates=DateUtil.getLastOneDate(date, -year);
	  if(dates.getTime()>new Date().getTime()){
		  return true;
	  }else{
		  return false;
	  }
  }
  public static void main(String[] args){
	Date date=DateUtil.getLastOneDate(new Date(),1);
	System.out.println(DateUtil.dateToStr(date));
	
  }
}
