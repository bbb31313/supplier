package pdz.wyht.until;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestWy {

	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {

		//TestWy ty=new TestWy();
//		Double money=ty.calWYcostSum("2013-11-19", "2014-11-18", "100", "30");
//		Double i=279.50, j=4.00;
//		DecimalFormat df=new DecimalFormat(".");
//		String str=df.format(i/j);
//		System.out.println(str);
//		System.out.println(i-Double.valueOf(str.substring(0,str.length()-1))*(j-1));
		//ty.assignKTcost("1111", "2013-11-16", "2014-12-31", "10000", 1);
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//		String str=ty.getAccountDay(sdf.parse("2013-11-18"), sdf.parse("2014-02-18"), 2);
//		System.out.println(str);

//		DateUtil dUtil=new DateUtil();
//		System.out.println(dUtil.getMonthDiffString("2013-11", 1));
		
//		String sdate="2013-12-31";
//		System.out.println(sdate.substring(0,7));
		
//		System.out.println(sdf.format(dUtil.getMonthDiff(sdf.parse("2013-11-18"), 9)));
//		System.out.println(dUtil.isFirstDayOFMonth(sdf.parse("2013-11-11")));
//		System.out.println(dUtil.isLastDayOFMonth(sdf.parse("2013-10-31")));		
//		System.out.println(ty.processDouble(3.333333333, 4));
//		ty.assignWYcost("1111", "2013-10-16", "2014-12-31", "100", "30", "2");
		
//		String str="1=1 and entrydate>='2013-12-10' and endentrydate<='2013-12-15' ";
//		String rsult=str.substring(0, str.indexOf("endentrydate"))+" "+str.substring(str.indexOf("endentrydate")+3);
//		System.out.println(rsult);
	}

	public boolean assignWYcost(String wyid, String startdate, String enddate,
			String jzmj, String wycost, String paymode) throws ParseException {
		boolean flag=false;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		DateUtil dUtil=new DateUtil();
		Date sDate=sdf.parse(startdate);
		Date eDate=sdf.parse(enddate);
		Double jzmjDouble=Double.valueOf(jzmj);
		Double wycostDouble=Double.valueOf(wycost);
		Integer mode=Integer.valueOf(paymode);
		int smonth=dUtil.getMonth(sDate);
		int syear=dUtil.getYear(sDate);
		int emonth=dUtil.getMonth(eDate);
		int eyear=dUtil.getYear(eDate);
		
		Date tmpDate=null;
		int mcnt=dUtil.calMonthDiff(sDate,eDate )+1;
		if (mcnt>1) {
			for(int i=0;i<mcnt;i++){
				tmpDate=dUtil.getMonthDiff(sDate, i);
				
				if(eDate.getTime()-tmpDate.getTime()>0){
					System.out.println("应收月份:"+dUtil.getYear(tmpDate)+"-"+dUtil.getMonth(tmpDate)+"  应收日期:"+getAccountDay(sDate,tmpDate,Integer.valueOf(paymode)));
					System.out.println("应收金额:"+getWYMCostAssign(sDate,eDate,tmpDate,jzmjDouble,wycostDouble,mode));					
				}
		
			}
		}else{
			System.out.println(syear+"-"+smonth+"-----"+sdf.format(dUtil.getDayDiff(sDate, -15)));
			System.out.println("应收金额:"+getWYMCostAssign(sDate,eDate,tmpDate,jzmjDouble,wycostDouble,mode));
		}
		return flag;
		
	}
	
	public String getAccountDay(Date sDate,Date eDate,int paymode){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		DateUtil dUtil=new DateUtil();
		if(paymode==3){
			return sdf.format(dUtil.getDayDiff(eDate, -15));
		}else if(paymode==2){
			int i=dUtil.calMonthDiff(sDate, eDate);
			if(i>=3){
				if((i%3)==0){
					return sdf.format(dUtil.getDayDiff(eDate, -15));	
				}else{
					Date tmpDate=dUtil.getMonthDiff(eDate, 0-(i%3));
					return sdf.format(dUtil.getDayDiff(tmpDate, -15));
				}
				
			}else{
				return sdf.format(dUtil.getDayDiff(sDate, -15));
			}
		}else{
			int j=dUtil.calMonthDiff(sDate, eDate);
			
			if(j>=6){
				if((j%6)==0){
					return sdf.format(dUtil.getDayDiff(eDate, -15));	
				}else{
					Date tmpDate=dUtil.getMonthDiff(eDate, 0-(j%6));
					return sdf.format(dUtil.getDayDiff(tmpDate, -15));
				}
				
			}else{
				return sdf.format(dUtil.getDayDiff(sDate, -15));
			}
		}
	}
	
	public Double getWYMCostAssign(Date sDate,Date eDate,Date curDate,Double jzmj,Double wycost,int paymode){
		DateUtil dUtil=new DateUtil();
		Double wyday=wycost/30;
		Double result=0.00;
		if(paymode==3){
			if(dUtil.calMonthDiff(sDate, eDate)>0){
				if(dUtil.calMonthDiff(sDate, curDate)==0){
					if(dUtil.isFirstDayOFMonth(sDate)){
						result=processDouble(jzmj*wycost,2);
					}else{
						Long curdays=dUtil.calDayDiff(sDate, dUtil.getLastDayMonth(sDate));
						result=processDouble((curdays+1)*jzmj*wyday, 2);
					}
				}else if(dUtil.calMonthDiff(eDate, curDate)==0){
					if(dUtil.isLastDayOFMonth(eDate)){
						result=processDouble(jzmj*wycost,2);
					}else{
						Long curdays=dUtil.calDayDiff(dUtil.getFirstDayMonth(eDate),eDate);
						result=processDouble((curdays+1)*jzmj*wyday, 2);
					}
				}else{
					result=processDouble(jzmj*wycost, 2);
				}
			}else{
				Long days=dUtil.calDayDiff(sDate, eDate);
				result=processDouble((days+1)*jzmj*wyday, 2);
			}
		}else if(paymode==2){
			int jiange=dUtil.calMonthDiff(sDate, eDate);
			if(jiange>2){
				int tje=dUtil.calMonthDiff(sDate, curDate);
				if((tje%3)==0){
					int ttje=dUtil.calMonthDiff(curDate, eDate);
					if(ttje>2){
						result=processDouble(wycost*jzmj*3,2);	
					}else{
						result=processDouble(wycost*jzmj*(ttje+1),2);
					}
				}
			}else{
				if(dUtil.calMonthDiff(sDate, curDate)==0){
					result=processDouble(wycost*jzmj*(jiange+1),2);	
				}
				
			}
		}else{
			int njiange=dUtil.calMonthDiff(sDate, eDate);
			if(njiange>5){
				int tje=dUtil.calMonthDiff(sDate, curDate);
				if((tje%6)==0){
					int ttje=dUtil.calMonthDiff(curDate, eDate);
					if(ttje>5){
						result=processDouble(wycost*jzmj*6,2);	
					}else{
						result=processDouble(wycost*jzmj*(ttje+1),2);
					}
				}
			}else{
				if(dUtil.calMonthDiff(sDate, curDate)==0){
					result=processDouble(wycost*jzmj*(njiange+1),2);	
				}
			}
		}
		
		
		return result;
	}
	
	public Double processDouble(Double data,int xiao){
		String x="";
		for(int i=0;i<=xiao;i++){
			x=x+"#";
		}
		DecimalFormat df=new DecimalFormat("."+x);
		String str=df.format(data);
		return Double.valueOf(str.substring(0,str.length()-1));
	}
	
	public Double getKTCostAssign(Date sDate,Date eDate,Date curDate,Double ktsum,int paymode){
		DateUtil dUtil=new DateUtil();
		Double result=0.00;
		DecimalFormat df=new DecimalFormat(".");
		int moncnt=dUtil.calMonthDiff(sDate, eDate);
		if((moncnt==0)&&(paymode==3))return ktsum;
		if((moncnt<=2)&&(paymode==2))return ktsum;
		if((moncnt<=5)&&(paymode==1))return ktsum;
		if(paymode==3){
			moncnt=moncnt+1;
			
			String str=df.format(ktsum/moncnt);
			Double average=Double.valueOf(str.substring(0,str.length()-1));
			Double yu=0.00;
			if((ktsum%moncnt)!=0){
				yu=ktsum-average*(moncnt-1);
				if(dUtil.calMonthDiff(curDate, eDate)==0){
					return yu;
				}else{
					return average;
				}
			}else{
				return average;
			}
			
		}else if(paymode==2){
			moncnt=moncnt+1;
			if((moncnt%3)==0){
				String jdstr=df.format(ktsum/(moncnt/3));
				
				Double jdaverage=Double.valueOf(jdstr.substring(0,jdstr.length()-1));
				int i=dUtil.calMonthDiff(sDate, curDate);
				if((i%3)==0){
				
					if((ktsum%(moncnt/3))!=0){
						Date tmpDate=dUtil.getMonthDiff(sDate,((moncnt/3)-1)*3 );
				
						if(dUtil.calMonthDiff(curDate, tmpDate)==0){
							return ktsum-jdaverage*((moncnt/3)-1);
						}else{
							return jdaverage;
						}
					}else{
						return jdaverage;
					}
				}else{
					return 0.00;
				}
			}else{
				String wjdstr=df.format(ktsum/((moncnt/3)+1));
				Double wjdaverage=Double.valueOf(wjdstr.substring(0,wjdstr.length()-1));
				int wi=dUtil.calMonthDiff(sDate, curDate);
				
				if((wi%3)==0){
					if((ktsum%((moncnt/3)+1))!=0){
						Date tmpDate=dUtil.getMonthDiff(sDate,moncnt-(moncnt%3) );
						if(dUtil.calMonthDiff(curDate, tmpDate)==0){
							
							return ktsum-processDouble(wjdaverage*((moncnt/3)), 2);
						}else{
							
							return wjdaverage;
						}
					}else{
						return wjdaverage;
					}
				}else{
					return 0.00;
				}
			}
		}else{
			moncnt=moncnt+1;
			if((moncnt%6)==0){
				String jdstr=df.format(ktsum/(moncnt/6));
				Double jdaverage=Double.valueOf(jdstr.substring(0,jdstr.length()-1));
				int i=dUtil.calMonthDiff(sDate, curDate);
				if((i%6)==0){
					if((ktsum%(moncnt/6))!=0){
						Date tmpDate=dUtil.getMonthDiff(sDate,((moncnt/6)-1)*6 );
						if(dUtil.calMonthDiff(curDate, tmpDate)==0){
							
							return ktsum-processDouble(jdaverage*((moncnt/6)-1),2);
						}else{
							return jdaverage;
						}
					}else{
						return jdaverage;
					}
				}else{
					return 0.00;
				}
			}else{
				String wjdstr=df.format(ktsum/((moncnt/6)+1));
				Double wjdaverage=Double.valueOf(wjdstr.substring(0,wjdstr.length()-1));
				int wi=dUtil.calMonthDiff(sDate, curDate);
				if((wi%6)==0){
					if((ktsum%((moncnt/6)+1))!=0){
						Date tmpDate=dUtil.getMonthDiff(sDate,moncnt-(moncnt%6) );
						
						
						if(dUtil.calMonthDiff(curDate, tmpDate)==0){
							
							return ktsum-wjdaverage*((moncnt/6));
						}else{
						
							return wjdaverage;
						}
					}else{
						
						return wjdaverage;
					}
				}else{
					return 0.00;
				}
			}
		}
		

	}
	
	public boolean assignKTcost(String wyid, String startdate, String enddate,
			String ktcost, int paymode) throws ParseException {
		boolean flag=false;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date sDate=sdf.parse(startdate);
		Date eDate=sdf.parse(enddate);
		DateUtil dUtil=new DateUtil();
		int smonth=dUtil.getMonth(sDate);
		int syear=dUtil.getYear(sDate);
		int emonth=dUtil.getMonth(eDate);
		int eyear=dUtil.getYear(eDate);
		Double ktsum=Double.valueOf(ktcost);
		
		Date tmpDate=null;
		int mcnt=dUtil.calMonthDiff(sDate,eDate )+1;

		
		if (mcnt>1) {
			for(int i=0;i<mcnt;i++){
				tmpDate=dUtil.getMonthDiff(sDate, i);
				
				if(eDate.getTime()-tmpDate.getTime()>0){
					System.out.println("应收月份:"+dUtil.getYear(tmpDate)+"-"+dUtil.getMonth(tmpDate)+"  应收日期:"+getAccountDay(sDate,tmpDate,Integer.valueOf(paymode)));
					System.out.println("应收金额:"+getKTCostAssign(sDate,eDate,tmpDate,ktsum,paymode));
					
				}
				
				
				
			}
		}else{

			System.out.println(syear+"-"+smonth+"-----"+sdf.format(dUtil.getDayDiff(sDate, -15))+"------"+ktsum);
		}
		return flag;
	}
	
	public Double calWYcostSum(String startdate, String enddate, String jzmj,
			String wycost) throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		boolean sflag=false,eflag=false;
		Double result=0.00;
		Double jzmjDouble=Double.valueOf(jzmj);
		Double wymonth=Double.valueOf(wycost);
		Double wyday=wymonth/30;
		Date start=sdf.parse(startdate);
		Date end=sdf.parse(enddate);
		DateUtil dUtil=new DateUtil();
		int monthcnt=dUtil.calMonthDiff(start, end);

		if (monthcnt>0) {
			if(!startdate.substring(startdate.length()-2).equalsIgnoreCase("01"))
			{
				
				Date moDate=dUtil.getLastDayMonth(start);
				Long days=dUtil.calDayDiff(start, moDate);
				result=(days+1)*jzmjDouble*wyday;
				
				sflag=true;
			}else {
				result=jzmjDouble*wymonth;
			}
			Long endDays=dUtil.calDayDiff(end,dUtil.getLastDayMonth(end));
			if(endDays>0)
			{
				
				eflag=true;
				result=result+((dUtil.calDayDiff(dUtil.getFirstDayMonth(end),end)+1)*jzmjDouble*wyday);
				
			}else{
				result=result+(wymonth*jzmjDouble);
			}
			
			if((monthcnt-1)>0){
				result=result+(wymonth*(monthcnt-1)*jzmjDouble);
			}
		}else{
			Long curDays=dUtil.calDayDiff(start,end);
			result=(curDays+1)*jzmjDouble*wyday;
		}
			
		return result;
	}
}
