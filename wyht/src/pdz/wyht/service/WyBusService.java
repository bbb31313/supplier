package pdz.wyht.service;

import java.io.File;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sun.org.apache.bcel.internal.generic.NEW;

import pdz.wyht.bean.BiShop;
import pdz.wyht.bean.WyAccount;
import pdz.wyht.bean.WyHT;
import pdz.wyht.bean.WyHTchangeNote;
import pdz.wyht.bean.WyPaid;
import pdz.wyht.bean.WyQueryReport;
import pdz.wyht.bean.WyZLht;
import pdz.wyht.bean.WyhtUser;
import pdz.wyht.until.DateUtil;


/**
* <p>Title: WyBusService.java</p>
* <p>Description:业务操作类</p>
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company:pdz</p>
* @author pdz
* @date 2014-3-26 上午11:05:52
* @version V1.0
*/

@Service @Transactional
public class WyBusService {

	@Resource SessionFactory sessionFactory;
	
	public boolean addObj(Object obj){
		try {
			sessionFactory.getCurrentSession().save(obj);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Transactional
	public boolean updateObj(Object obj){
		try {
			sessionFactory.getCurrentSession().update(obj);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean delObj(Object obj){
		try {
			sessionFactory.getCurrentSession().delete(obj);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	

	
	public Object getObj(Class arg0,Serializable arg1){
		return sessionFactory.getCurrentSession().get(arg0, arg1);
	}
	
	public boolean saveORupdateObj(Object obj) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(obj);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public String getZlhtByShopCnt(String zl) {
		String wycode="";
		int cnt=0;
		Session session=sessionFactory.getCurrentSession();
		WyZLht zlht=(WyZLht)getObj(WyZLht.class, Long.valueOf(zl));
		String shop=zlht.getOld_mcid();
		Query query=session.createSQLQuery("select count(*) cnt from wy_zlht a,wy_ht b where a.hth=b.zlcode and a.old_mcid='"+shop+"'");
		List list=query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		for(int i=0;i<list.size();i++){
			Map map=(Map)list.get(i);
			cnt=((Number)map.get("CNT")).intValue()+1;
		}
		wycode=shop+"_"+cnt;
		return wycode;
		
	}
	
	public List<WyZLht> getAllZLht() {
		return sessionFactory.getCurrentSession().createQuery("from WyZLht").list();
	}
	
	public List<WyQueryReport> getQueryReports(String where){
		List<WyQueryReport> qReports=new ArrayList<WyQueryReport>();
		String hql="select * from (select d.smsshopname,d.shopid,a.zlcode,a.wycode"
		       +",a.jianame,a.yiname,b.paymonth,sum(decode(b.costypeid, '1', b.money, 0)) swy,"
		       +"sum(decode(b.costypeid, '2', b.money, 0)) skt "
		       +"  from wy_ht a, wy_zlht c, bi_shop d, wy_money b "
		       +"where a.htid = b.htid and a.zlcode = c.hth and c.old_mcid = d.shopid group by "
		       +"d.smsshopname,d.shopid,a.zlcode,a.wycode,a.jianame,a.yiname,b.paymonth  order by yiname)"+" where "+where+" order by yiname ";
		Query query=sessionFactory.getCurrentSession().createSQLQuery(hql);
		List list = query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		for (int i = 0; i < list.size(); i++) {
	    	WyQueryReport wyReport=new WyQueryReport();
			Map map=(Map)list.get(i);
			wyReport.setShop(map.get("SMSSHOPNAME").toString());
			wyReport.setJia(map.get("JIANAME").toString());
			wyReport.setKtcost(map.get("SKT").toString());
			wyReport.setMangercost(map.get("SWY").toString());
			wyReport.setPaymonth(map.get("PAYMONTH").toString());
			wyReport.setWycode(map.get("WYCODE").toString());
			wyReport.setYi(map.get("YINAME").toString());
			wyReport.setZlcode(map.get("ZLCODE").toString());
			qReports.add(wyReport);
		}
		return qReports;
	}

	public boolean isHaveUsersx(String usersx) {
		String hql="from WyhtUser where usersx='"+usersx+"'";
		List list=sessionFactory.getCurrentSession().createQuery(hql).list();
		if (list.size()>0) {
			return true;
		}else {
			return false;
		}
		
	}

	public boolean isHaveZlcode(String zlcode) {
		String hql="from WyHT where zlcode='"+zlcode+"'";
		List list=sessionFactory.getCurrentSession().createQuery(hql).list();
		if (list.size()>0) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * 
	 *TODO 增加操作记录
	 *@param user
	 *@param changeStyle
	 *@param changeContent
	 *@return
	 */
	public boolean addOpertorNote(WyhtUser user,String changeStyle,String changeContent,WyHT wyht) {
		boolean flag=false;
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		WyHTchangeNote changeNote=new WyHTchangeNote();
		changeNote.setChangecontent(changeContent);
		changeNote.setChangedate(sf.format(new Date()));
		changeNote.setChangestyle(changeStyle);
		changeNote.setHtid(wyht.getHtid());
		changeNote.setUserid(user.getUserID().toString());
		changeNote.setUsername(user.getUserName());
		changeNote.setZlhtid(wyht.getZlcode());
		
		flag=addObj(changeNote);
		return flag;
	}

	public BiShop getZlhtByShop(String zlcode) {
		
		
		WyZLht zlht=(WyZLht)getObj(WyZLht.class, Long.valueOf(zlcode));
		String shop=zlht.getOld_mcid();
		String hql="from BiShop a where a.shopid='"+shop+"'";
		List<BiShop> shops=sessionFactory.getCurrentSession().createQuery(hql).list();
		
		return shops.get(0);
	}

	public String getWYhtByZLcode(String zlcode) {
		String hql="select to_char(max(to_number(htid))) as MAXHTID from wy_ht where zlcode='"+zlcode+"'";
		String result=null;
		result=(String)sessionFactory.getCurrentSession().createSQLQuery(hql).addScalar("MAXHTID", Hibernate.STRING).uniqueResult();
		return result;
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
							return ktsum-wjdaverage*((moncnt/3));
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
						Date tmpDate=dUtil.getMonthDiff(sDate,((moncnt/6)-1)*63 );
						if(dUtil.calMonthDiff(curDate, tmpDate)==0){
							return ktsum-jdaverage*((moncnt/6)-1);
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
			String ktcost, int paymode,int modify) throws ParseException {
		boolean flag=false;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		List<WyAccount> accountList=new ArrayList<WyAccount>();
		Date sDate=sdf.parse(startdate);
		Date eDate=sdf.parse(enddate);
		DateUtil dUtil=new DateUtil();
		String smonth=dUtil.getMonth(sDate)>9?String.valueOf(dUtil.getMonth(sDate)):"0"+String.valueOf(dUtil.getMonth(sDate));
		int syear=dUtil.getYear(sDate);
		String emonth=dUtil.getMonth(eDate)>9?String.valueOf(dUtil.getMonth(eDate)):"0"+String.valueOf(dUtil.getMonth(eDate));
		int eyear=dUtil.getYear(eDate);
		Double ktsum=Double.valueOf(ktcost);
		Date tmpDate=null;
		String tmpMonth="";
		int mcnt=dUtil.calMonthDiff(sDate,eDate )+1;
		if (mcnt>1) {
			for(int i=0;i<mcnt;i++){
				tmpDate=dUtil.getMonthDiff(sDate, i);				
				if(eDate.getTime()-tmpDate.getTime()>0){
					//System.out.println("应收月份:"+dUtil.getYear(tmpDate)+"-"+dUtil.getMonth(tmpDate)+"  应收日期:"+getAccountDay(sDate,tmpDate,Integer.valueOf(paymode)));
					//System.out.println("应收金额:"+getKTCostAssign(sDate,eDate,tmpDate,ktsum,paymode));
					WyAccount wyKT=new WyAccount();
					wyKT.setHtid(wyid);
					wyKT.setCostype("2");
					wyKT.setCostname("空调费");
					tmpMonth=dUtil.getMonth(tmpDate)>9?String.valueOf(dUtil.getMonth(tmpDate)):"0"+String.valueOf(dUtil.getMonth(tmpDate));
					wyKT.setAccountmonth(dUtil.getYear(tmpDate)+"-"+tmpMonth);
					wyKT.setAccountdate(getAccountDay(sDate,tmpDate,Integer.valueOf(paymode)));
					wyKT.setAccount(getKTCostAssign(sDate,eDate,tmpDate,ktsum,3));
					wyKT.setAccountznj(0.00);
					wyKT.setCostover("0");
					accountList.add(wyKT);
				}
			}
			
		}else{
			//System.out.println(syear+"-"+smonth+"-----"+sdf.format(dUtil.getDayDiff(sDate, -15))+"------"+ktsum);
			WyAccount wyKT=new WyAccount();
			wyKT.setHtid(wyid);
			wyKT.setCostype("2");
			wyKT.setCostname("空调费");
			wyKT.setAccountmonth(syear+"-"+smonth);
			wyKT.setAccountdate(sdf.format(dUtil.getDayDiff(sDate, -15)));
			wyKT.setAccount(ktsum);
			wyKT.setAccountznj(0.00);
			wyKT.setCostover("0");
			accountList.add(wyKT);
		}
		
		for (WyAccount wyAccount : accountList) {
			if (modify>1) {
				Double oldaccount=getAcountByMonth(wyAccount.getHtid(), wyAccount.getAccountmonth(), wyAccount.getCostype());
				if(oldaccount==0){
					addObj(wyAccount);
				}else{
					Double newaccount=wyAccount.getAccount();
					wyAccount.setAccount(newaccount-oldaccount);
					addObj(wyAccount);
				}
			}else{
				addObj(wyAccount);	
			}
				
		}
		
		flag=true;
		return flag;
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
	
	public boolean assignWYcost(String wyid, String startdate, String enddate,
			String jzmj, String wycost, String paymode,int modify) throws ParseException {
		boolean flag=false;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		List<WyAccount> accountList=new ArrayList<WyAccount>();
		DateUtil dUtil=new DateUtil();
		Date sDate=sdf.parse(startdate);
		Date eDate=sdf.parse(enddate);
		Double jzmjDouble=Double.valueOf(jzmj);
		Double wycostDouble=Double.valueOf(wycost);
		Integer mode=Integer.valueOf(paymode);
		String smonth=dUtil.getMonth(sDate)>9?String.valueOf(dUtil.getMonth(sDate)):"0"+String.valueOf(dUtil.getMonth(sDate));
		int syear=dUtil.getYear(sDate);
		String emonth=dUtil.getMonth(eDate)>9?String.valueOf(dUtil.getMonth(eDate)):"0"+String.valueOf(dUtil.getMonth(eDate));
		int eyear=dUtil.getYear(eDate);
		String tmpMonth="";
		Date tmpDate=null;
		int mcnt=dUtil.calMonthDiff(sDate,eDate )+1;
		if (mcnt>1) {
			for(int i=0;i<mcnt;i++){
				tmpDate=dUtil.getMonthDiff(sDate, i);
				if(eDate.getTime()-tmpDate.getTime()>0){
//					System.out.println("应收月份:"+dUtil.getYear(tmpDate)+"-"+dUtil.getMonth(tmpDate)+"  应收日期:"+getAccountDay(sDate,tmpDate,Integer.valueOf(paymode)));
//					System.out.println("应收金额:"+getWYMCostAssign(sDate,eDate,tmpDate,jzmjDouble,wycostDouble,mode));
					WyAccount wyKT=new WyAccount();
					wyKT.setHtid(wyid);
					wyKT.setCostype("1");
					wyKT.setCostname("物业管理费");
					tmpMonth=dUtil.getMonth(tmpDate)>9?String.valueOf(dUtil.getMonth(tmpDate)):"0"+String.valueOf(dUtil.getMonth(tmpDate));
					wyKT.setAccountmonth(dUtil.getYear(tmpDate)+"-"+tmpMonth);
					wyKT.setAccountdate(getAccountDay(sDate,tmpDate,Integer.valueOf(paymode)));
					wyKT.setAccount(getWYMCostAssign(sDate,eDate,tmpDate,jzmjDouble,wycostDouble,3));
					wyKT.setAccountznj(0.00);
					wyKT.setCostover("0");
					accountList.add(wyKT);
					
				}
			}
			
			
		}else{
			
			WyAccount wyKT=new WyAccount();
			wyKT.setHtid(wyid);
			wyKT.setCostype("1");
			wyKT.setCostname("物业管理费");
			wyKT.setAccountmonth(syear+"-"+smonth);
			wyKT.setAccountdate(sdf.format(dUtil.getDayDiff(sDate, -15)));
			wyKT.setAccount(getWYMCostAssign(sDate,eDate,tmpDate,jzmjDouble,wycostDouble,3));
			wyKT.setAccountznj(0.00);
			wyKT.setCostover("0");
			accountList.add(wyKT);

		}
		
		for (WyAccount wyAccount : accountList) {
			if (modify>1) {
				Double oldaccount=getAcountByMonth(wyAccount.getHtid(), wyAccount.getAccountmonth(), wyAccount.getCostype());
				if(oldaccount==0){
					addObj(wyAccount);
				}else{
					Double newaccount=wyAccount.getAccount();
					wyAccount.setAccount(newaccount-oldaccount);
					addObj(wyAccount);
				}
			}else{
				addObj(wyAccount);	
			}
				
		}
		flag=true;
		return flag;
		
	}

	private Double getAcountByMonth(String htid, String accountmonth,
			String costype) {
		String hql="select nvl(sum(account),0) cnt from wy_account wy where wy.costype='"+costype+"' and wy.accountmonth='"+accountmonth+"' and wy.htid='"+htid+"'";
		Double cnt=(Double)sessionFactory.getCurrentSession().createSQLQuery(hql).addScalar("cnt", Hibernate.DOUBLE).uniqueResult();
		return cnt;
	}

	public void delAccount(String htid,int costype) {
		String hql;
		if (costype==1) {
			hql="delete from WyAccount wy where wy.costype=1 and wy.costover<>'1' and wy.htid='"+htid+"'";	
		}else if (costype==2) {
			hql="delete from WyAccount wy where wy.costype=2 and wy.costover<>'1' and wy.htid='"+htid+"'";
		}else {
			hql="delete from WyAccount wy where wy.htid='"+htid+"'";
		}
		
		Session session=sessionFactory.getCurrentSession();
		session.createQuery(hql).executeUpdate();
	}


	public void updateAccountOver(String htid, String month,String costtype) {
		String hql="update WyAccount wy set wy.costover='1' where wy.costype='"+costtype+"' and wy.accountmonth='"+month+"' and wy.htid='"+htid+"'";
		Session session=sessionFactory.getCurrentSession();
		session.createQuery(hql).executeUpdate();		
		
	}

/**
 * 
 *TODO 判断该合同是否已经开始收费 
 *@param htid
 *@return 
 */
	public boolean isPayHT(String htid) {
		String hql="from WyPaid pay where pay.htid='"+htid+"'";
		List<WyPaid> wyPaids=sessionFactory.getCurrentSession().createQuery(hql).list();
		return wyPaids.size()>0;
	}



public boolean addWyAccountBatch(List<WyAccount> objList) {
	boolean flag=false;
	Session session=sessionFactory.openSession();
	
	Transaction tx=session.beginTransaction();
	int i=0;
	
	try {
		for (WyAccount wyAccount : objList) {
			session.save(wyAccount);
			i++;
			if((i%10)==0){
				session.flush();
				session.clear();
				tx.commit();
				tx=session.beginTransaction();
			}
		}
	
		tx.commit();
	
		flag=true;
	
	} catch (Exception e) {
//		if(tx!=null){
//			
//		}
		tx.rollback();	
		e.printStackTrace();
	}finally{
		if (session!=null) {
			if(session.isOpen()){session.close();}
		}
	}
	
	return flag;
}

public List<WyAccount> getAccountList(String htid, String enddate) {
	List<WyAccount> aList=new ArrayList<WyAccount>();
//	String hql="from WyAccount a where a.account>0 and a.htid='"+htid+"' and accountmonth>'"+enddate.substring(0,7)+"' ";
	String sql="select a.* from wy_account a,(select htid,accountmonth,sum(account) accounts from wy_account  where htid='"+htid+"' group by htid,accountmonth having sum(account)>0) b "
	          +"where a.htid=b.htid and a.accountmonth=b.accountmonth and a.accountmonth>'"+enddate.substring(0,7)+"' ";
//	System.out.println(sql);
	aList=sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(WyAccount.class).list();
	return aList;
}

/**
 * 
 *修改物业管理费 
 *@param htid       合同ID
 *@param paymonth   修改月份
 *@param money      修改金额
 *@return 
 */
public boolean modifyWyMoney(String htid, String paymonth, Double money) {
	boolean flag=false;
	String hql="select htid,costype,accountmonth,accountdate,sum(account) sumaccount from wy_account "
		      +" where htid='"+htid+"' and accountmonth='"+paymonth+"' and costype=1 group by htid,costype,accountmonth,accountdate";
	List list=sessionFactory.getCurrentSession().createSQLQuery(hql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	try {
		for (int i = 0; i < list.size(); i++) {
			Map map=(Map)list.get(i);
			WyAccount account=new WyAccount();
			account.setAccount(money-Double.valueOf(map.get("SUMACCOUNT").toString()));
			account.setAccountdate(map.get("ACCOUNTDATE").toString());
			account.setAccountmonth(map.get("ACCOUNTMONTH").toString());
			account.setAccountznj(0.00);
			account.setCostname("物业管理费");
			account.setCostover("0");
			account.setCostype("1");
			account.setHtid(htid);
			addObj(account);
			flag=true;
		}	
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return flag;
}

public boolean modifyKTMoney(String htid, Double ktmoney) {
	boolean flag=false;
	String hql="select count(distinct accountmonth) as cntmonth, min(accountmonth) as minmonth, max(accountmonth) as maxmonth from wy_account a where a.costover>=0 and a.htid = '"+htid+"' and costype = 2 "
		      +"and not exists (select 1 from wy_paid b where b.htid = '"+htid+"'  and a.accountmonth = b.paidmonth and costype = 2) order by accountmonth";
	//查询原来的空调费总额
	WyHT wht=(WyHT)getObj(WyHT.class, htid);
	String oldkt=String.valueOf(wht.getKtcost());
	//计算需分配的空调费
	Double fpkt=ktmoney-Double.valueOf(oldkt);
	//查询出要分配的月份数，最小月份，最大月份
	Map map=(Map)sessionFactory.getCurrentSession().createSQLQuery(hql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).uniqueResult();
	int mcnt=Integer.valueOf(map.get("CNTMONTH").toString());
	String minmonth=map.get("MINMONTH").toString();
	String maxmonth=map.get("MAXMONTH").toString();
	
	DecimalFormat df=new DecimalFormat(".");
	String str=df.format(fpkt/mcnt);
	Double average=Double.valueOf(str.substring(0,str.length()-1));
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
	Date tmpDate,maxDate;
	DateUtil dUtil=new DateUtil();
	List<WyAccount> aList=new ArrayList<WyAccount>();
	try {
		tmpDate = sdf.parse(minmonth);
		maxDate=sdf.parse(maxmonth);
		
		while(maxDate.getTime()-tmpDate.getTime()>=0){
			WyAccount wyAc=new WyAccount();
			wyAc.setCostype("2");
			wyAc.setCostname("空调费");
			wyAc.setAccountznj(0.00);
			wyAc.setCostover("0");
			wyAc.setAccountmonth(sdf.format(tmpDate));
			wyAc.setHtid(htid);
			if (maxDate.getTime()-tmpDate.getTime()==0) {
				if((fpkt%mcnt)==0){
					wyAc.setAccount(average);	
				}else{
					wyAc.setAccount(fpkt-average*(mcnt-1));
				}
				
			}else{
				wyAc.setAccount(average);	
			}
			
			aList.add(wyAc);
			tmpDate=dUtil.getMonthDiff(tmpDate, 1);
		}
		flag=updateKTcost(aList);
		sessionFactory.getCurrentSession().createQuery("update WyAccount a set accountdate=(select max(accountdate) from WyAccount b where a.htid=b.htid and a.accountmonth=b.accountmonth) where a.accountdate is null ").executeUpdate();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	

	
	return flag;
}

private boolean updateKTcost(List<WyAccount> aList) {
	boolean flag=false;
	Session session=sessionFactory.openSession();
	Transaction tx=session.beginTransaction();
	int i=0;
	String sql;
	try {
		for (WyAccount wyAccount : aList) {
//			sql=" update wy_account a set a.account=a.account+"+wyAccount.getAccount()+" where a.htid='"+wyAccount.getHtid()+"' and a.costype=2 and a.accountmonth='"+wyAccount.getAccountmonth()+"'";
			
//			session.createSQLQuery(sql).executeUpdate();
			session.save(wyAccount);
			i++;
			if((i%10)==0){
				session.flush();
				session.clear();
				tx.commit();
				tx=session.beginTransaction();
			}
		}
	
		tx.commit();
	
		flag=true;
	
	} catch (Exception e) {
//		if(tx!=null){
//			
//		}
		tx.rollback();	
		e.printStackTrace();
	}finally{
		if (session!=null) {
			if(session.isOpen()){session.close();}
		}
	}
	return flag;
}

public void updatePayMode(String htid, String startdate, String enddate,
		String paymode) throws Exception {
	
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	List<WyAccount> accountList=new ArrayList<WyAccount>();
	Date sDate=sdf.parse(startdate);
	Date eDate=sdf.parse(enddate);
	DateUtil dUtil=new DateUtil();
	String smonth=dUtil.getMonth(sDate)>9?String.valueOf(dUtil.getMonth(sDate)):"0"+String.valueOf(dUtil.getMonth(sDate));
	int syear=dUtil.getYear(sDate);
	String emonth=dUtil.getMonth(eDate)>9?String.valueOf(dUtil.getMonth(eDate)):"0"+String.valueOf(dUtil.getMonth(eDate));
	int eyear=dUtil.getYear(eDate);

	Date tmpDate=null;
	String tmpMonth="";
	int mcnt=dUtil.calMonthDiff(sDate,eDate )+1;
	if (mcnt>1) {
		for(int i=0;i<mcnt;i++){
			tmpDate=dUtil.getMonthDiff(sDate, i);				
			if(eDate.getTime()-tmpDate.getTime()>0){


				WyAccount wyKT=new WyAccount();
				wyKT.setHtid(htid);
				
				tmpMonth=dUtil.getMonth(tmpDate)>9?String.valueOf(dUtil.getMonth(tmpDate)):"0"+String.valueOf(dUtil.getMonth(tmpDate));
				wyKT.setAccountmonth(dUtil.getYear(tmpDate)+"-"+tmpMonth);
				wyKT.setAccountdate(getAccountDay(sDate,tmpDate,Integer.valueOf(paymode)));
				
				wyKT.setAccountznj(0.00);
				wyKT.setCostover("0");
				accountList.add(wyKT);
			}
		}
		
	}else{
		//System.out.println(syear+"-"+smonth+"-----"+sdf.format(dUtil.getDayDiff(sDate, -15))+"------"+ktsum);
		WyAccount wyKT=new WyAccount();
		wyKT.setHtid(htid);
		
		wyKT.setAccountmonth(syear+"-"+smonth);
		wyKT.setAccountdate(sdf.format(dUtil.getDayDiff(sDate, -15)));
		
		accountList.add(wyKT);
	}
	
	Session session=sessionFactory.getCurrentSession();
	String hql="update WyAccount ac set ac.accountdate=?,ac.accountznj=0,ac.costover='0' where ac.htid=? and ac.accountmonth=? ";
	for (WyAccount wyAccount : accountList) {
		Query query=session.createQuery(hql);
		query.setString(0, wyAccount.getAccountdate());
		query.setString(1, wyAccount.getHtid());
		query.setString(2, wyAccount.getAccountmonth());
		query.executeUpdate();
	}

	
}

public boolean updateAuditAccount(String htid) {
	boolean flag=false;
	try {
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		String curDate=sf.format(new Date());
		String hql="update WyAccount set auditdate='"+curDate+"' where htid='"+htid+"' and auditdate is null ";
		sessionFactory.getCurrentSession().createQuery(hql).executeUpdate();
		flag=true;
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return flag;
}

public boolean updateCostOverOver(List<WyAccount> accountList) {
	boolean flag=false;
	Session session=sessionFactory.openSession();
	Transaction tx=session.beginTransaction();
	int i=0;
	String sql;
	try {
		for (WyAccount wyAccount : accountList) {
			sql=" update wy_account a set a.costover=-1 where a.htid='"+wyAccount.getHtid()+"' and a.accountmonth='"+wyAccount.getAccountmonth()+"'";
			
			session.createSQLQuery(sql).executeUpdate();

			i++;
			if((i%10)==0){
				session.flush();
				session.clear();
				tx.commit();
				tx=session.beginTransaction();
			}
		}

		tx.commit();
		flag=true;
	
	} catch (Exception e) {
//		if(tx!=null){
//			
//		}
		tx.rollback();	
		e.printStackTrace();
	}finally{
		if (session!=null) {
			if(session.isOpen()){session.close();}
		}
	}
	return flag;
}

public Double getWyHTcost(String htid,int type) {
	Double sumKt=0.00;
	try {
		String sql="select sum(b.account) from WyAccount b where b.htid='"+htid+"'  and b.costype="+type;
		sumKt=(Double)sessionFactory.getCurrentSession().createQuery(sql).uniqueResult();
		
	} catch (Exception e) {
		e.printStackTrace();
		
	}
	
	return sumKt;
}

public Double getWyHTcost(String htid,int type,String month) {
	Double sumKt=0.00;
	try {
		String sql="select sum(b.account) from WyAccount b where b.htid='"+htid+"' and b.accountmonth='"+month+"' and b.costype="+type;
		sumKt=(Double)sessionFactory.getCurrentSession().createQuery(sql).uniqueResult();
		
	} catch (Exception e) {
		e.printStackTrace();
		
	}
	
	return sumKt;
}
	
}
