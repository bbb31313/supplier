package pdz.wyht.until;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;

import pdz.wyht.bean.WyAccount;
import pdz.wyht.bean.WyLY;
import pdz.wyht.bean.WyMoney;
import pdz.wyht.bean.WyQueryReport;
import pdz.wyht.bean.WyhtUser;
import pdz.wyht.grid.FilterGroup;
import pdz.wyht.grid.FilterRule;
import pdz.wyht.grid.FilterTranslator;
import pdz.wyht.grid.GridData;

import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;


@Service
public class DbContextExtension  {
	
	@Resource SessionFactory factory;
	private static Log logger = LogFactory.getLog(DbContextExtension.class);
    public GridData GetGridData(HttpServletRequest request)
    {
    	
    	 String view =request.getParameter("view"); 
    	 String sortname =request.getParameter("sortname"); 
    	 String sortorder =request.getParameter("sortorder"); 
    	 String _pagenumber =request.getParameter("page"); 
         String _pagesize =request.getParameter("pagesize");  
         
         String main_page=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
         int pagenumber = 0, pagesize = 0; 
          //可分页
          if (!(_pagenumber==null) && !(_pagesize==null))
          {
              pagenumber = Integer.valueOf(_pagenumber);
              pagesize = Integer.valueOf(_pagesize);
              if (pagesize == 0) pagesize = 20;
          }
          //可排序
          if (!(sortname==null))
          {
              sortorder = sortorder==null || sortorder.equalsIgnoreCase("asc") ? "desc" : "asc";
          }
          /*
           * where 为 json参数，格式如下： 
           * {
                "roles":[
                   {"field":"ID","value":112,"op":"equal"},
                    {"field":"Time","value":"2011-3-4","op":"greaterorequal"}
                 ],
                "op":"and","groups":null
           *  }
           *  FilterTranslator可以为以上格式的where表达式翻译为sql，并生成参数列表(FilterParam[])
           */

          FilterTranslator whereTranslator = new FilterTranslator();
          String where =request.getParameter("where");
          if (!(where==null))
          {
              //反序列化Filter Group JSON
              whereTranslator.setGroup(jsonTranslatorFilterGroup(where));
          }
            
          whereTranslator.Translate();
          String wherestr=whereTranslator.CommandText;
          if(view.equalsIgnoreCase("WyZLht")){
        	  WyhtUser wuser=(WyhtUser)request.getSession().getAttribute("wyuser");
        	  if(!wuser.getShop().equalsIgnoreCase("1")){
        		  wherestr=wherestr+" and old_mcid='"+wuser.getShop()+"'";
        	  }
          }
          
          if(view.equalsIgnoreCase("WyHT1")){
        	  view=view.substring(0,view.length()-1);
        	  WyhtUser wuser=(WyhtUser)request.getSession().getAttribute("wyuser");
        	  if(!wuser.getShop().equalsIgnoreCase("1")){
        		  wherestr=wherestr+" and status=1 and substr(wycode,0,6)='"+wuser.getShop()+"'";
        	  }else{
        		  wherestr=wherestr+" and status=1 ";
        	  }
          }
          
          if(view.equalsIgnoreCase("WyHT2")){
        	  view=view.substring(0,view.length()-1);
        	  WyhtUser wuser=(WyhtUser)request.getSession().getAttribute("wyuser");
        	  if(!wuser.getShop().equalsIgnoreCase("1")){
        		  wherestr=wherestr+" and status=2 and substr(wycode,0,6)='"+wuser.getShop()+"'";
        	  }else{
        		  wherestr=wherestr+" and status=2 ";
        	  }
          }
          if(view.equalsIgnoreCase("WyHT3")){
        	  view=view.substring(0,view.length()-1);
        	  WyhtUser wuser=(WyhtUser)request.getSession().getAttribute("wyuser");
        	  if(!wuser.getShop().equalsIgnoreCase("1")){
        		  wherestr=wherestr+" and status=3 and substr(wycode,0,6)='"+wuser.getShop()+"'";
        	  }else{
        		  wherestr=wherestr+" and status=3 ";
        	  }
          }
          
          if(view.equalsIgnoreCase("WyHT4")){
        	  view=view.substring(0,view.length()-1);
        	  WyhtUser wuser=(WyhtUser)request.getSession().getAttribute("wyuser");
        	  if(!wuser.getShop().equalsIgnoreCase("1")){
        		  wherestr=wherestr+" and status=4 and substr(wycode,0,6)='"+wuser.getShop()+"'";
        	  }else{
        		  wherestr=wherestr+" and status=4 ";
        	  }
          }
          
          if(view.equalsIgnoreCase("WyHT5")){
        	  view=view.substring(0,view.length()-1);
        	  WyhtUser wuser=(WyhtUser)request.getSession().getAttribute("wyuser");
        	  if(!wuser.getShop().equalsIgnoreCase("1")){
        		  wherestr=wherestr+" and status=5 and substr(wycode,0,6)='"+wuser.getShop()+"'";
        	  }else{
        		  wherestr=wherestr+" and status=5 ";
        	  }
          }
          
          if(view.equalsIgnoreCase("WyhtUser")){
        	  WyhtUser wuser=(WyhtUser)request.getSession().getAttribute("wyuser");
        	  if(!wuser.getShop().equalsIgnoreCase("1")){
        		  wherestr=wherestr+" and shop='"+wuser.getShop()+"'";
        	  }
          }
          if(view.equalsIgnoreCase("WyQueryReport")){
        	  WyhtUser wuser=(WyhtUser)request.getSession().getAttribute("wyuser");
        	  if(!wuser.getShop().equalsIgnoreCase("1")){
        		  wherestr=wherestr+" and shopid='"+wuser.getShop()+"'";
        	  }
          }       
          
          if(view.equalsIgnoreCase("wyly")){
        	  WyhtUser wuser=(WyhtUser)request.getSession().getAttribute("wyuser");
        	  if(!wuser.getShop().equalsIgnoreCase("1")){
        		  wherestr=wherestr+" and shop='"+wuser.getShop()+"'";
        	  }
          }  
          
          if(view.equalsIgnoreCase("WyLYMoney")){
        	  String htid=request.getParameter("htid");
        	  wherestr=wherestr+" and htid='"+htid+"'";
          }  
          
          if(request.getParameter("ctype")!=null){
        	  String htid=request.getParameter("htid");
        	  wherestr=wherestr+" and htid='"+htid+"' and costype='"+request.getParameter("ctype")+"'";
        	  sortorder="asc";
          }
          
          if(view.equalsIgnoreCase("WyJiaoNa")){
        	  wherestr=wherestr+" and costover=0 ";
          }
          if(view.equalsIgnoreCase("WyAccount")){
        	  wherestr=wherestr+" and costype=1  ";
          }
          
          logger.debug("where:"+wherestr);
          return GetGridData(main_page,view,wherestr , sortname, sortorder, pagenumber, pagesize);
    }
    
    public FilterGroup jsonTranslatorFilterGroup(String wherestr){
		 FilterGroup group=(FilterGroup)JsonUtil.getObject(wherestr,FilterGroup.class);
		 String str2=JsonUtil.getJson(group.getRules());
		 FilterRule[] rules=(FilterRule[])JsonUtil.getArray(str2,FilterRule.class);
		 List<FilterRule> ruleList=new ArrayList<FilterRule>();
		 for (int i = 0; i < rules.length; i++) {
			ruleList.add(rules[i]);
		}
		 group.setRules(ruleList);
		 return group;
    }
    
    public GridData GetGridData(String mainpage,String view, String where, String sortname, String sortorder, int pagenumber, int pagesize)
    {
    	GridData gdata=new GridData();
    	try {
    		int total = GetGridTotal(view, where);
            List rows = GetGridRows(view, where, sortname, sortorder, pagenumber, pagesize);
            gdata.setRows(rows);
            gdata.setTotal(total);	
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
        return gdata;
    }

	private int GetGridTotal(String view, String where)  {
		String commandText;
		int cnt=0;
		Session session=factory.openSession();
		if (view.equalsIgnoreCase("WyQueryReport")) {
			commandText="select count(*) cnt from (select d.smsshopname,d.shopid,a.zlcode,a.wycode"
				       +",a.jianame,a.yiname,b.paymonth,sum(decode(b.costypeid, '1', b.money, 0)) swy,"
				       +"sum(decode(b.costypeid, '2', b.money, 0)) skt "
				       +"  from wy_ht a, wy_zlht c, bi_shop d, wy_money b "
				       +"where a.htid = b.htid and a.zlcode = c.hth and c.old_mcid = d.shopid group by "
				       +"d.smsshopname,d.shopid,a.zlcode,a.wycode,a.jianame,a.yiname,b.paymonth  order by yiname)"+" where "+where;
			List maplist=session.createSQLQuery(commandText).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
			for (int i = 0; i < maplist.size(); i++) {
				Map map=(Map)maplist.get(i);
				cnt=Integer.valueOf(map.get("CNT").toString());
			}
		}else if(view.equalsIgnoreCase("WyAccount")){
			commandText="select count(*) cnt from (select htid, costname, accountmonth, sum(account) account  from wy_account where "+where+" group by  htid, costname, accountmonth ) ";
			logger.debug("commandText:"+commandText);
			List maplist=session.createSQLQuery(commandText).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
			Map map=(Map)maplist.get(0);
			cnt=Integer.valueOf(map.get("CNT").toString());
		}else if(view.equalsIgnoreCase("WyMoney")){
			commandText="select count(*) cnt from wy_paid a,wy_ht b where a.htid=b.htid and "+where;
			
			List maplist=session.createSQLQuery(commandText).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
			Map map=(Map)maplist.get(0);
			cnt=Integer.valueOf(map.get("CNT").toString());
		}else if(view.equalsIgnoreCase("WyJiaoNa"))
		{
//			commandText="select count(*) cnt from (select aa.htid,aa.costname,aa.accountmonth,aa.accountdate,aa.account from "
//				+"(select a.htid,a.costname,a.accountmonth,a.costype,a.accountdate,sum(a.account) account,sum(a.accountznj) accountznj from wy_account a "
//				+" where "+where+" group by a.htid,a.costname,a.accountmonth,a.costype,a.accountdate ) aa)";
			commandText="select count(*) cnt from ( select a.htid,a.costname,a.accountmonth,a.costype,a.accountdate,sum(a.account) account,sum(a.accountznj) accountznj from wy_account a "
				+" where "+where+" group by a.htid,a.costname,a.accountmonth,a.costype,a.accountdate )";

			try {
				
//				Query query=session.createQuery(commandText);
//				cnt=((Number)query.iterate().next()).intValue();
				List maplist=session.createSQLQuery(commandText).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
				Map map=(Map)maplist.get(0);
				cnt=Integer.valueOf(map.get("CNT").toString());

			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (view.equalsIgnoreCase("accountColl")) {
			commandText="select count(*) cnt from (select htid,costype,b.accountmonth,b.accountdate,b.costname,sum(b.account) "
			       +" from wy_account b where "+where+" group by htid,costype,b.accountmonth,b.accountdate,b.costname )";
//		List maplist=session.createSQLQuery(commandText).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
//		for (int i = 0; i < maplist.size(); i++) {
//			Map map=(Map)maplist.get(i);
//			cnt=Integer.valueOf(map.get("CNT").toString());
//			}
			cnt=(Integer)session.createSQLQuery(commandText).addScalar("cnt", Hibernate.INTEGER).uniqueResult();
		}else if (view.equalsIgnoreCase("wyly")) {
			commandText="select count(*) cnt from (select a.htid,a.zlcode,a.wycode,a.yiname,a.qtcost,a.shop,nvl(sum(money),0) nowlymoney "
			       +" from wy_ht a,wy_lymoney b where a.htid=b.htid(+) group by"
			       +" a.htid,a.zlcode,a.wycode,a.yiname,a.qtcost,a.shop  )"+" where "+where;
		List maplist=session.createSQLQuery(commandText).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		for (int i = 0; i < maplist.size(); i++) {
			Map map=(Map)maplist.get(i);
			cnt=Integer.valueOf(map.get("CNT").toString());
			}
		}else {
			commandText="select count(*) from "+view+" where "+where;

//			System.out.println("commandText:"+commandText);
			
			try {
				
				Query query=session.createQuery(commandText);
				cnt=((Number)query.iterate().next()).intValue();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	
		}
		session.close();	
		return cnt;
	}

	private List GetGridRows(String view, String where, String sortname,
			String sortorder, int pagenumber, int pagesize) {
		int offset=pagenumber==1?0:(pagenumber-1)*pagesize;
		String hql="";
		if (view.equalsIgnoreCase("WyQueryReport")) {
			hql="select * from (select d.smsshopname,d.shopid,a.zlcode,a.wycode"
			       +",a.jianame,a.yiname,b.paymonth,sum(decode(b.costypeid, '1', b.money, 0)) swy,"
			       +"sum(decode(b.costypeid, '2', b.money, 0)) skt,sum(decode(b.costypeid, '3', b.money, 0)) sqt"
			       +"  from wy_ht a, wy_zlht c, bi_shop d, wy_money b "
			       +"where a.htid = b.htid and a.zlcode = c.hth and c.old_mcid = d.shopid group by "
			       +"d.smsshopname,d.shopid,a.zlcode,a.wycode,a.jianame,a.yiname,b.paymonth  order by yiname)"+" where "+where+" order by "+sortname+" "+sortorder;
			return getWyReportListForPage(hql, offset, pagesize);
		}else if(view.equalsIgnoreCase("WyAccount")){
			hql="select htid, costname, accountmonth, sum(account) account  from wy_account where "+where+" group by  htid, costname, accountmonth order by  "+sortname+"  "+sortorder;
				return getWyAccountByModfyForPage(hql, offset, pagesize);
		}else if(view.equalsIgnoreCase("WyMoney")){
			hql="select a.htid,b.zlcode,b.yiname,a.costname,a.paidmonth,nvl(a.paid,0) paid,nvl(a.paidznj,0) paidznj,a.ZNJREDUCE,"
				+"decode(a.payment,1,'现金',2,'刷卡',3,'减免','其他') payment,a.invoice,a.entryuserid,a.entryuser,a.entrydate"
				+"  from wy_paid a,wy_ht b where a.htid=b.htid and "+where+" order by  "+sortname+"  "+sortorder;
			return getWyMoneyListForPage(hql, offset, pagesize);
		}
		else if(view.equalsIgnoreCase("WyJiaoNa")){
			hql="select aa.htid,aa.costname,aa.accountmonth,aa.accountdate,aa.account,aa.accountznj -(select nvl(sum(paidznj), 0) from wy_paid b "
				+" where aa.costype = b.costype and aa.htid = b.htid and aa.accountmonth = b.paidmonth) as accountznj from "
				+"(select a.htid,a.costname,a.accountmonth,a.costype,a.accountdate,sum(a.account) account,sum(a.accountznj) accountznj from wy_account a "
				+" where "+where+" group by a.htid,a.costname,a.accountmonth,a.costype,a.accountdate order by "+sortname+" "+sortorder+" ) aa";
			return getWYAcountListForPage(hql, offset, pagesize);
		}else if (view.equalsIgnoreCase("wyly")) {
			hql="select * from (select a.htid,a.zlcode,a.wycode,a.yiname,a.qtcost,a.shop,nvl(sum(money),0) nowlymoney  "
				+"  from wy_ht a,wy_lymoney b where a.htid=b.htid(+) group by a.htid,a.zlcode,a.wycode,a.yiname,a.qtcost,a.shop)"+" where "+where+" order by "+sortname+" "+sortorder;
			return getWyLYListForPage(hql, offset, pagesize);
		}else if (view.equalsIgnoreCase("accountColl")) {
			hql="select * from (select htid,costype,b.accountmonth,b.accountdate,b.costname,sum(b.account) account "
			       +" from wy_account b where "+where+" group by htid,costype,b.accountmonth,b.accountdate,b.costname )"+" where "+where+" order by "+sortname+" "+sortorder;
			return getaccountCollForPage(hql, offset, pagesize);
		}else{
			
			hql="from "+view+" where "+where+" order by "+sortname+" "+sortorder;	
			//System.out.println("hql:"+hql);
			return getListForPage(hql, offset, pagesize);
		}		
	}
	
	private List<WyAccount> getaccountCollForPage(String hql, int offset, int pagesize) {
		List<WyAccount> accountList=new ArrayList<WyAccount>();
		
		try {
			Session session=factory.openSession();
//			System.out.println("hql:"+hql);
			Query query=session.createSQLQuery(hql);
			
			query.setFirstResult(offset);
		    query.setMaxResults(pagesize);
		    
		    List list = query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		    for (int i = 0; i < list.size(); i++) {
		    	WyAccount money=new WyAccount();
				Map map=(Map)list.get(i);
				money.setHtid(map.get("HTID").toString());
				money.setCostname(map.get("COSTNAME").toString());
				money.setCostype(map.get("COSTYPE").toString());
				money.setAccount(Double.valueOf(map.get("ACCOUNT").toString()));
				money.setAccountmonth(map.get("ACCOUNTMONTH").toString());
				money.setAccountdate(map.get("ACCOUNTDATE").toString());
				
				accountList.add(money);
			}
		    session.close();
 
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		return accountList;
	}
	
	private List<WyMoney> getWyMoneyListForPage(String hql, int offset, int pagesize) {
		List<WyMoney> moenyList=new ArrayList<WyMoney>();
		
		try {
			Session session=factory.openSession();
//			System.out.println("hql:"+hql);
			Query query=session.createSQLQuery(hql);
			
			query.setFirstResult(offset);
		    query.setMaxResults(pagesize);
		    
		    List list = query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		    for (int i = 0; i < list.size(); i++) {
		    	WyMoney money=new WyMoney();
				Map map=(Map)list.get(i);
				money.setHtid(map.get("HTID").toString());
				money.setCostname(map.get("COSTNAME").toString());
				money.setPaidmonth(map.get("PAIDMONTH").toString());
				money.setPaidznj(map.get("PAIDZNJ").toString());
				money.setPaid(map.get("PAID").toString());
				money.setEntrydate(map.get("ENTRYDATE").toString());
				money.setEntryuser(map.get("ENTRYUSER").toString());
				money.setEntryuserid(map.get("ENTRYUSERID").toString());
				money.setInvoice(map.get("INVOICE")!=null?map.get("INVOICE").toString():"");
				money.setPayment(map.get("PAYMENT").toString());
				money.setYiname(map.get("YINAME").toString());
				money.setZlcode(map.get("ZLCODE").toString());
				money.setZnjreduce(map.get("ZNJREDUCE")!=null?map.get("ZNJREDUCE").toString():"");
				moenyList.add(money);
			}
		    session.close();
 
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		return moenyList;
	}

	public List<WyAccount> getWyAccountByModfyForPage(final String hql, final int offset,
		    final int length){
		List<WyAccount> wyList=new ArrayList<WyAccount>();
		
		try {
			Session session=factory.openSession();
//			System.out.println("hql:"+hql);
			Query query=session.createSQLQuery(hql);
			
			query.setFirstResult(offset);
		    query.setMaxResults(length);
		    
		    List list = query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		    for (int i = 0; i < list.size(); i++) {
		    	WyAccount wy=new WyAccount();
				Map map=(Map)list.get(i);
				wy.setHtid(map.get("HTID").toString());
				wy.setCostname(map.get("COSTNAME").toString());
				wy.setAccountmonth(map.get("ACCOUNTMONTH").toString());
				wy.setAccount(Double.valueOf(map.get("ACCOUNT").toString()));
				wyList.add(wy);
			}
		    session.close();
 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return wyList;
		
	}
	
	public List<WyAccount> getWYAcountListForPage(final String hql, final int offset,
		    final int length){
		List<WyAccount> wyList=new ArrayList<WyAccount>();
		
		try {
			Session session=factory.openSession();
//			System.out.println("hql:"+hql);
			Query query=session.createSQLQuery(hql);
			
			query.setFirstResult(offset);
		    query.setMaxResults(length);
		    
		    List list = query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		    for (int i = 0; i < list.size(); i++) {
		    	WyAccount wy=new WyAccount();
				Map map=(Map)list.get(i);
				wy.setHtid(map.get("HTID").toString());
				wy.setCostname(map.get("COSTNAME").toString());
				wy.setAccountmonth(map.get("ACCOUNTMONTH").toString());
				wy.setAccountdate(map.get("ACCOUNTDATE").toString());
				wy.setAccount(Double.valueOf(map.get("ACCOUNT").toString()));
				wy.setAccountznj(Double.valueOf(map.get("ACCOUNTZNJ").toString()));
				wyList.add(wy);
			}
		    session.close();
 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return wyList;
		
	}
	
	
	public List<WyLY> getWyLYListForPage(final String hql, final int offset,
		    final int length) {

		List<WyLY> lyList=new ArrayList<WyLY>();
		try {
			Session session=factory.openSession();
//			System.out.println("hql:"+hql);
			Query query=session.createSQLQuery(hql);
			
			query.setFirstResult(offset);
		    query.setMaxResults(length);
		    
		    List list = query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		    for (int i = 0; i < list.size(); i++) {
		    	WyLY wyly=new WyLY();
				Map map=(Map)list.get(i);
				wyly.setHtid(map.get("HTID").toString());
				wyly.setLymoney(map.get("QTCOST").toString());
				wyly.setNowlymoney(map.get("NOWLYMONEY").toString());
				wyly.setWycode(map.get("WYCODE").toString());
				wyly.setZlcode(map.get("ZLCODE").toString());
				wyly.setYiname(map.get("YINAME").toString());
				lyList.add(wyly);
			}
		    session.close();
 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lyList;
	}
	
	public List<WyQueryReport> getWyReportListForPage(final String hql, final int offset,
		    final int length) {

		List<WyQueryReport> reports=new ArrayList<WyQueryReport>();
		try {
			Session session=factory.openSession();
			Query query=session.createSQLQuery(hql);
			
			query.setFirstResult(offset);
		    query.setMaxResults(length);
		    
		    List list = query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		    for (int i = 0; i < list.size(); i++) {
		    	WyQueryReport wyReport=new WyQueryReport();
				Map map=(Map)list.get(i);
				wyReport.setShop(map.get("SMSSHOPNAME").toString());
				wyReport.setJia(map.get("JIANAME").toString());
				wyReport.setKtcost(map.get("SKT").toString());
				wyReport.setMangercost(map.get("SWY").toString());
				wyReport.setPaymonth(map.get("PAYMONTH").toString());
				wyReport.setQtcost(map.get("SQT").toString());
				wyReport.setWycode(map.get("WYCODE").toString());
				wyReport.setYi(map.get("YINAME").toString());
				wyReport.setZlcode(map.get("ZLCODE").toString());
				reports.add(wyReport);
			}
		    session.close();
 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return reports;
	}
	
	/**
	 * 分页专用.使用spring的hibernateTemplate的回调机制扩展hibernateTemplate的功能实现分页
	 * @param hql
	 * @param offset
	 * @param length
	 * @return
	 */
	public List getListForPage(final String hql, final int offset,
		    final int length) {
		Session session=factory.openSession();
		Query query=session.createQuery(hql);
		query.setFirstResult(offset);
	    query.setMaxResults(length);
	    List list = query.list();
	    session.close();
		return list;
	}
    
    
}
