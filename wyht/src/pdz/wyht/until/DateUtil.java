package pdz.wyht.until;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	//当前日期所在月份最后一天
	public Date getLastDayMonth(Date curDate)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(curDate);
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 0);
		return cal.getTime();
	}
	
	//判断当前日期是否是当月第一天
	public boolean isFirstDayOFMonth(Date curDate) {
		Date temp=getFirstDayMonth(curDate);
		
		return calDayDiff(temp, curDate)==0;
	}
	
	//判断当前日期是否是当月最后一天
	public boolean isLastDayOFMonth(Date curDate) {
		Date temp=getLastDayMonth(curDate);
		
		return calDayDiff(temp, curDate)==0;
	}
	
	//当前日期所在月份第一天
	public Date getFirstDayMonth(Date curDate)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(curDate);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}
	
	//计算相差多少天
	public Long calDayDiff(Date sDate,Date eDate)
	{
		return (eDate.getTime()-sDate.getTime())/(1000*60*60*24);
	}
	
	//计算相差多少个月份
	public int calMonthDiff(Date sDate,Date eDate)
	{
		
		int sYear=getYear(sDate);
		int sMonth=getMonth(sDate);
		int eYear=getYear(eDate);
		int eMonth=getMonth(eDate);
		
		return (eYear-sYear)*12+(eMonth-sMonth);
	}
	
	public int getYear(Date curDate)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(curDate);
		return cal.get(Calendar.YEAR);
	}
	
	public int getMonth(Date curDate)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(curDate);
		return cal.get(Calendar.MONTH)+1;
	}
	
	public int getDay(Date curDate)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(curDate);
		return cal.get(Calendar.DAY_OF_MONTH);
	}
	
	public int getHour(Date curDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(curDate);
		return cal.get(Calendar.HOUR_OF_DAY);
	}
	
	public int getMinute(Date curDate){
		Calendar cal = Calendar.getInstance();
		cal.setTime(curDate);
		return cal.get(Calendar.MINUTE);
	}
	
	public int getSecond(Date curDate){
		Calendar cal = Calendar.getInstance();
		cal.setTime(curDate);
		return cal.get(Calendar.SECOND);
	}
	
	//当日所在月份的天数
	public int getDaysByMonth(Date curDate){
		Calendar cal=Calendar.getInstance();
		cal.setTime(curDate);
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	//获取相隔的第几天
	public Date getDayDiff(Date curDate,int diff){
		Calendar cal=Calendar.getInstance();
		cal.setTime(curDate);
		cal.add(Calendar.DATE, diff);
		return cal.getTime();
	}
	
	//获取相隔的第几个月
	public Date getMonthDiff(Date curDate,int diff){
		Calendar cal=Calendar.getInstance();
		cal.setTime(curDate);
		cal.add(Calendar.MONTH, diff);
		return cal.getTime();
	}
	
	
	public String getMonthDiffString(String month,int diff){
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		String result="";
		try {
			cal.setTime(df.parse(month+"-01"));
			cal.add(Calendar.MONTH, diff);
			Date tmpDate=cal.getTime();
			result=df.format(tmpDate).substring(0, 7);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	 *判定两个日期大小 
	 *@param date1
	 *@param date2
	 *@return true(参数1的日期大) false（参数1的日期小）
	 */
	public boolean compare_date(String date1 ,String date2){
		boolean flag=false;
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			Date beforeDate=df.parse(date1);
			Date afterDate=df.parse(date2);
			if(beforeDate.getTime()>afterDate.getTime())
				flag=true;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
		
	}
	
	/**
	 * 
	 *TODO 计算某天之后的天数 
	 *@param date1 
	 *@param diff
	 *@return "yyyy-MM-dd"格式的字符串
	 */
	public String afterDate(String date1,int diff){
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		String result=null;
		try {
			Date date=getDateAfter(df.parse(date1),1);
			result=df.format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	  * 得到几天前的时间
	  */

	 public  Date getDateBefore(Date d, int day) {
	  Calendar now = Calendar.getInstance();
	  now.setTime(d);
	  now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
	  return now.getTime();
	 }

	 /**
	  * 得到几天后的时间
	  */
	 
	 public  Date getDateAfter(Date d, int day) {
	  Calendar now = Calendar.getInstance();
	  now.setTime(d);
	  now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
	  return now.getTime();
	 }
}
