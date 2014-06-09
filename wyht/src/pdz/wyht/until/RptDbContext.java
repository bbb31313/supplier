package pdz.wyht.until;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;

import pdz.wyht.bean.RptManPay;
import pdz.wyht.bean.RptMonPay;
import pdz.wyht.bean.RptOverPay;
import pdz.wyht.bean.RptZnjPay;
import pdz.wyht.bean.RptZnjPayMon;
import pdz.wyht.bean.WyReportFeild;
import pdz.wyht.bean.WyWldmLack;
import pdz.wyht.bean.WyhtUser;
import pdz.wyht.grid.FilterGroup;
import pdz.wyht.grid.FilterRule;
import pdz.wyht.grid.FilterTranslator;
import pdz.wyht.grid.GridData;

import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;


@Service
public class RptDbContext  {
	
	@Resource SessionFactory factory;
	private static Log logger = LogFactory.getLog(RptDbContext.class);
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

          
          if(view.equalsIgnoreCase("RptManPay")){
        	  WyhtUser wuser=(WyhtUser)request.getSession().getAttribute("wyuser");
        	  if(!wuser.getShop().equalsIgnoreCase("1")){
        		  wherestr=wherestr+" and shop='"+wuser.getShop()+"'";
        	  }
        	 
        	  if(wherestr.indexOf("endentrydate")>0){
        		  wherestr=wherestr.substring(0, wherestr.indexOf("endentrydate"))+" "+wherestr.substring(wherestr.indexOf("endentrydate")+3);
        	  }
          }
          
          if(view.equalsIgnoreCase("WyHTchangeNote")){
        	 
        	  if(wherestr.indexOf("endchangedate")>0){
        		  wherestr=wherestr.substring(0, wherestr.indexOf("endchangedate"))+" "+wherestr.substring(wherestr.indexOf("endchangedate")+3);
        	  }
          }
          
          if(view.equalsIgnoreCase("RptMonPay")){
        	  WyhtUser wuser=(WyhtUser)request.getSession().getAttribute("wyuser");
        	  if(!wuser.getShop().equalsIgnoreCase("1")){
        		  wherestr=wherestr+" and shop='"+wuser.getShop()+"'";
        	  }
        	 
        	  if(wherestr.indexOf("endaccountmonth")>0){
        		  wherestr=wherestr.substring(0, wherestr.indexOf("endaccountmonth"))+" "+wherestr.substring(wherestr.indexOf("endaccountmonth")+3);
        	  }  
          }
          
          if(view.equalsIgnoreCase("WyWldmLack")){
        	  WyhtUser wuser=(WyhtUser)request.getSession().getAttribute("wyuser");
        	  if(!wuser.getShop().equalsIgnoreCase("1")){
        		  wherestr=wherestr+" and shop='"+wuser.getShop()+"'";
        	  }
        	  if(wherestr.indexOf("endaccountmonth")>0){
        		  wherestr=wherestr.substring(0, wherestr.indexOf("endaccountmonth"))+" "+wherestr.substring(wherestr.indexOf("endaccountmonth")+3);
        	  }  
          }
          
          if(view.equalsIgnoreCase("RptOverPay")){
        	  WyhtUser wuser=(WyhtUser)request.getSession().getAttribute("wyuser");
        	  if(!wuser.getShop().equalsIgnoreCase("1")){
        		  wherestr=wherestr+" and shop='"+wuser.getShop()+"'";
        	  }
        	 
        	  if(wherestr.indexOf("endaccountdate")>0){
        		  wherestr=wherestr.substring(0, wherestr.indexOf("endaccountdate"))+" "+wherestr.substring(wherestr.indexOf("endaccountdate")+3);
        	  }  
          }
          
          if(view.equalsIgnoreCase("RptZnjPay")){
        	  WyhtUser wuser=(WyhtUser)request.getSession().getAttribute("wyuser");
        	  if(!wuser.getShop().equalsIgnoreCase("1")){
        		  wherestr=wherestr+" and shop='"+wuser.getShop()+"'";
        	  }
        	 
        	  if(wherestr.indexOf("endentrydate")>0){
        		  wherestr=wherestr.substring(0, wherestr.indexOf("endentrydate"))+" "+wherestr.substring(wherestr.indexOf("endentrydate")+3);
        	  }  
          }
          if(view.equalsIgnoreCase("RptZnjPayMon")){
        	  WyhtUser wuser=(WyhtUser)request.getSession().getAttribute("wyuser");
        	  if(!wuser.getShop().equalsIgnoreCase("1")){
        		  wherestr=wherestr+" and shop='"+wuser.getShop()+"'";
        	  }
        	 
        	  if(wherestr.indexOf("endpaidmonth")>0){
        		  wherestr=wherestr.substring(0, wherestr.indexOf("endpaidmonth"))+" "+wherestr.substring(wherestr.indexOf("endpaidmonth")+3);
        	  }  
          }
          logger.debug("where:"+wherestr);
          return GetGridData(view,wherestr , sortname, sortorder, pagenumber, pagesize);
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
    
    public List<Object> getDownLoadList(HttpServletRequest request) {
    	List<Object> objects=new ArrayList<Object>();
        FilterTranslator whereTranslator = new FilterTranslator();
        String where =request.getParameter("where");
        String view=request.getParameter("view");
        String sortname=request.getParameter("sortName");
        if (!(where==null))
        {
            //反序列化Filter Group JSON
            whereTranslator.setGroup(jsonTranslatorFilterGroup(where));
        }
          
        whereTranslator.Translate();
        String wherestr=whereTranslator.CommandText;
        if(view.equalsIgnoreCase("RptManPay")){
      	  WyhtUser wuser=(WyhtUser)request.getSession().getAttribute("wyuser");
      	  if(!wuser.getShop().equalsIgnoreCase("1")){
      		  wherestr=wherestr+" and shop='"+wuser.getShop()+"'";
      	  }
      	 
      	  
    	  if(wherestr.indexOf("endentrydate")>0){
    		  wherestr=wherestr.substring(0, wherestr.indexOf("endentrydate"))+" "+wherestr.substring(wherestr.indexOf("endentrydate")+3);
    	  }
        }
        
        if(view.equalsIgnoreCase("RptMonPay")){
      	  WyhtUser wuser=(WyhtUser)request.getSession().getAttribute("wyuser");
      	  if(!wuser.getShop().equalsIgnoreCase("1")){
      		  wherestr=wherestr+" and shop='"+wuser.getShop()+"'";
      	  }
      	 
      	  if(wherestr.indexOf("endaccountmonth")>0){
      		  wherestr=wherestr.substring(0, wherestr.indexOf("endaccountmonth"))+" "+wherestr.substring(wherestr.indexOf("endaccountmonth")+3);
      	  }  
        }
        if(view.equalsIgnoreCase("RptOverPay")){
      	  WyhtUser wuser=(WyhtUser)request.getSession().getAttribute("wyuser");
      	  if(!wuser.getShop().equalsIgnoreCase("1")){
      		  wherestr=wherestr+" and shop='"+wuser.getShop()+"'";
      	  }
      	 
      	  if(wherestr.indexOf("endaccountdate")>0){
      		  wherestr=wherestr.substring(0, wherestr.indexOf("endaccountdate"))+" "+wherestr.substring(wherestr.indexOf("endaccountdate")+3);
      	  }  
        }
        
        if(view.equalsIgnoreCase("RptZnjPay")){
      	  WyhtUser wuser=(WyhtUser)request.getSession().getAttribute("wyuser");
      	  if(!wuser.getShop().equalsIgnoreCase("1")){
      		  wherestr=wherestr+" and shop='"+wuser.getShop()+"'";
      	  }
      	 
      	  if(wherestr.indexOf("endentrydate")>0){
      		  wherestr=wherestr.substring(0, wherestr.indexOf("endentrydate"))+" "+wherestr.substring(wherestr.indexOf("endentrydate")+3);
      	  }  
        }
        if(view.equalsIgnoreCase("RptZnjPayMon")){
      	  WyhtUser wuser=(WyhtUser)request.getSession().getAttribute("wyuser");
      	  if(!wuser.getShop().equalsIgnoreCase("1")){
      		  wherestr=wherestr+" and shop='"+wuser.getShop()+"'";
      	  }
      	 
      	  if(wherestr.indexOf("endpaidmonth")>0){
      		  wherestr=wherestr.substring(0, wherestr.indexOf("endpaidmonth"))+" "+wherestr.substring(wherestr.indexOf("endpaidmonth")+3);
      	  }  
        }
        
        if(view.equalsIgnoreCase("WyWldmLack")){
      	  WyhtUser wuser=(WyhtUser)request.getSession().getAttribute("wyuser");
      	  if(!wuser.getShop().equalsIgnoreCase("1")){
      		  wherestr=wherestr+" and shop='"+wuser.getShop()+"'";
      	  }
      	  if(wherestr.indexOf("endaccountmonth")>0){
      		  wherestr=wherestr.substring(0, wherestr.indexOf("endaccountmonth"))+" "+wherestr.substring(wherestr.indexOf("endaccountmonth")+3);
      	  }  
        }
        
        logger.debug("exp_wherestr:"+wherestr);
        
        int total = GetGridTotal(view, wherestr);
        objects=GetGridRows(view, wherestr, sortname, "desc", 1, total);
    	return objects;
	}
    
    public GridData GetGridData(String view, String where, String sortname, String sortorder, int pagenumber, int pagesize)
    {

    	int total = GetGridTotal(view, where);
        List rows = GetGridRows(view, where, sortname, sortorder, pagenumber, pagesize);
        GridData gdata=new GridData();
        gdata.setRows(rows);
        gdata.setTotal(total);
        return gdata;
    }

	private int GetGridTotal(String view, String where)  {
		String commandText;
		int cnt=0;
		Session session=factory.openSession();
		StringBuffer bufSql=new StringBuffer();
		if (view.equalsIgnoreCase("RptManPay")) {
			//清空bufsql内容
			bufSql.setLength(0);
			bufSql.append("select b.shopname,a.entrydate,a.pid,a.entryuser,b.zlcode,b.htid,c.ghdwdm,c.wldmname,d.mentname");
			bufSql.append(",nvl(sum(case when costype=1 then a.paid else 0 end ),0) wymoney");
			bufSql.append(",nvl(sum(case when costype=2 then a.paid else 0 end),0)  ktmoney");
			bufSql.append(",nvl(sum(case when (payment=1 or payment=2) then a.paidznj else 0 end),0) znjmoney");
			bufSql.append(" from wy_paid a,wy_ht b,wy_zlht c,wy_payment_tab d");
			bufSql.append(" where a.htid=b.htid and a.payment<>3 and b.zlcode=c.hth and a.payment=d.mid and ");
			bufSql.append(where);
			bufSql.append(" group by b.shopname,a.entrydate,a.pid,a.entryuser,b.zlcode,b.htid,c.ghdwdm,c.wldmname,d.mentname");
			commandText="select count(*) cnt from ("+bufSql.toString()+")";
			logger.debug("RptManPay_TotalSQL: "+commandText);
			List maplist=session.createSQLQuery(commandText).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
			for (int i = 0; i < maplist.size(); i++) {
				Map map=(Map)maplist.get(i);
				cnt=Integer.valueOf(map.get("CNT").toString());
			}
		}else if(view.equalsIgnoreCase("RptMonPay")){
			//清空bufsql内容
			bufSql.setLength(0);
			bufSql.append("select b.shopname,b.zlcode,b.htid,c.ghdwdm,c.wldmname,b.paymode,b.JZMJ,a.accountmonth,a.costover");
			bufSql.append(",nvl(sum(case when costype=1 then a.account else 0 end ),0) wymoney ");
			bufSql.append(",nvl(sum(case when costype=2 then a.account else 0 end ),0) ktmoney ");
			bufSql.append(" from wy_account a,wy_ht b,wy_zlht c");
			bufSql.append(" where a.htid=b.htid and b.zlcode=c.hth and a.accountmonth<=to_char(sysdate,'yyyy-MM') and b.status=3 and ");
			bufSql.append(where);
			bufSql.append(" group by b.shopname,b.zlcode,b.htid,c.ghdwdm,c.wldmname,b.paymode,b.JZMJ,a.accountmonth,a.costover");
			commandText="select count(*) cnt from ("+bufSql.toString()+")";
			logger.debug("RptMonPay_TotalSQL: "+commandText);
			List maplist=session.createSQLQuery(commandText).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
			for (int i = 0; i < maplist.size(); i++) {
				Map map=(Map)maplist.get(i);
				cnt=Integer.valueOf(map.get("CNT").toString());
			}
		}else if(view.equalsIgnoreCase("RptOverPay")){
			//清空bufsql内容
			bufSql.setLength(0);
			bufSql.append("select b.shopname,b.zlcode,b.htid,c.ghdwdm,c.wldmname,b.paymode,a.accountmonth,a.accountdate,a.overdate");
			bufSql.append(",nvl(sum(case when a.costype=1 then a.account else 0 end ),0) wymoney  ");
			bufSql.append(",nvl(sum(case when a.costype=2 then a.account else 0 end ),0) ktmoney  ");
			bufSql.append(",nvl(sum(case when a.costype=1 then a.accountznj else 0 end ),0) wyznjmoney ");
			bufSql.append(",nvl(sum(case when a.costype=2 then a.accountznj else 0 end ),0) ktznjmoney ");
			bufSql.append(" from wy_account a,wy_ht b,wy_zlht c");
			bufSql.append(" where a.htid=b.htid and b.zlcode=c.hth and b.status=3 and a.costover=0 and a.accountznj>0 and ");
			bufSql.append(where);
			bufSql.append(" group by b.shopname,b.zlcode,b.htid,c.ghdwdm,c.wldmname,b.paymode,a.accountmonth,a.accountdate,a.overdate");
			commandText="select count(*) cnt from ("+bufSql.toString()+")";
			logger.debug("RptOverPay_TotalSQL: "+commandText);
			List maplist=session.createSQLQuery(commandText).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
			for (int i = 0; i < maplist.size(); i++) {
				Map map=(Map)maplist.get(i);
				cnt=Integer.valueOf(map.get("CNT").toString());
			}
		}else if(view.equalsIgnoreCase("RptZnjPay")){
			//清空bufsql内容
			bufSql.setLength(0);
			bufSql.append("select c.SHOPNAME,c.ZLCODE,c.ghdwdm,c.wldmname,b.mentname,c.accountdate,c.wymoney,c.ktmoney,c.overdate");
			bufSql.append(",c.wyznj,c.ktznj,a.pid,a.entrydate,a.entryuserid,a.htid,a.paidmonth,a.entryuser ");
			bufSql.append(",case when a.costype=1 and a.payment=3 then a.paidznj else  0 end jmwyznj ");
			bufSql.append(",case when a.costype=1 and a.payment<3 then a.paidznj else  0 end shwyznj ");
			bufSql.append(",case when a.costype=2 and a.payment=3 then a.paidznj else  0 end jmktznj ");
			bufSql.append(",case when a.costype=2 and a.payment<3 then a.paidznj else  0 end shktznj ");
			bufSql.append(" from wy_payment_tab b, wy_znj_info c,wy_paid a");
			bufSql.append(" where a.paidznj>0 and b.mid=a.payment and a.htid=c.htid and a.paidmonth=c.accountmonth and ");
			bufSql.append(where);
			commandText="select count(*) cnt from ("+bufSql.toString()+")";
			logger.debug("RptZnjPay_TotalSQL: "+commandText);
			List maplist=session.createSQLQuery(commandText).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
			for (int i = 0; i < maplist.size(); i++) {
				Map map=(Map)maplist.get(i);
				cnt=Integer.valueOf(map.get("CNT").toString());
			}
		}
		else {
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
		StringBuffer bufSql=new StringBuffer();
		String hql="";
		if (view.equalsIgnoreCase("RptManPay")) {
			bufSql.setLength(0);
			bufSql.append("select b.shopname,a.entrydate,a.paidmonth,a.pid,a.entryuser,b.zlcode,b.htid,c.ghdwdm,c.wldmname,d.mentname");
			bufSql.append(",nvl(sum(case when costype=1 then a.paid else 0 end ),0) wymoney");
			bufSql.append(",nvl(sum(case when costype=2 then a.paid else 0 end),0)  ktmoney");
			bufSql.append(",nvl(sum(case when (payment=1 or payment=2) then a.paidznj else 0 end),0) znjmoney");
			bufSql.append(" from wy_paid a,wy_ht b,wy_zlht c,wy_payment_tab d");
			bufSql.append(" where a.htid=b.htid and a.payment<>3 and b.zlcode=c.hth and a.payment=d.mid and ");
			bufSql.append(where);
			bufSql.append(" group by b.shopname,a.entrydate,a.paidmonth,a.pid,a.entryuser,b.zlcode,b.htid,c.ghdwdm,c.wldmname,d.mentname");
			hql="select * from ("+bufSql.toString()+") order by "+sortname+" "+sortorder;
			logger.debug("RptManPay_GetRowSQL: "+hql);
			return getRptManPayForPage(hql, offset, pagesize);
		}else if(view.equalsIgnoreCase("RptMonPay")){
			bufSql.setLength(0);
			bufSql.append("select b.shopname,b.zlcode,b.htid,c.ghdwdm,c.wldmname,b.paymode,b.JZMJ,a.accountmonth,a.costover");
			bufSql.append(",nvl(sum(case when costype=1 then a.account else 0 end ),0) wymoney ");
			bufSql.append(",nvl(sum(case when costype=2 then a.account else 0 end ),0) ktmoney ");
			bufSql.append(" from wy_account a,wy_ht b,wy_zlht c");
			bufSql.append(" where a.htid=b.htid and b.zlcode=c.hth and a.accountmonth<=to_char(sysdate,'yyyy-MM') and b.status=3 and ");
			bufSql.append(where);
			bufSql.append(" group by b.shopname,b.zlcode,b.htid,c.ghdwdm,c.wldmname,b.paymode,b.JZMJ,a.accountmonth,a.costover");
			hql="select * from ("+bufSql.toString()+") order by "+sortname+" "+sortorder;
			logger.debug("RptMonPay_GetRowSQL: "+hql);
			return getRptMonPayForPage(hql, offset, pagesize);
		}else if(view.equalsIgnoreCase("WyWldmLack")){
			bufSql.setLength(0);
			bufSql.append("select * from wy_wldm_qianyu  where ");
			bufSql.append(where);
			hql="select * from ("+bufSql.toString()+") order by "+sortname+" "+sortorder;
			logger.debug("WyWldmLack_RPT_GetRowSQL: "+hql);
			return getWyWldmLackForPage(hql, offset, pagesize);
		}else if(view.equalsIgnoreCase("RptOverPay")){
			bufSql.setLength(0);
			bufSql.append("select b.shopname,b.zlcode,b.htid,c.ghdwdm,c.wldmname,b.paymode,a.accountmonth,a.accountdate,a.overdate");
			bufSql.append(",nvl(sum(case when a.costype=1 then a.account else 0 end ),0) wymoney  ");
			bufSql.append(",nvl(sum(case when a.costype=2 then a.account else 0 end ),0) ktmoney  ");
			bufSql.append(",nvl(sum(case when a.costype=1 then a.accountznj else 0 end ),0) wyznjmoney ");
			bufSql.append(",nvl(sum(case when a.costype=2 then a.accountznj else 0 end ),0) ktznjmoney ");
			bufSql.append(" from wy_account a,wy_ht b,wy_zlht c");
			bufSql.append(" where a.htid=b.htid and b.zlcode=c.hth and b.status=3 and a.costover=0 and a.accountznj>0 and ");
			bufSql.append(where);
			bufSql.append(" group by b.shopname,b.zlcode,b.htid,c.ghdwdm,c.wldmname,b.paymode,a.accountmonth,a.accountdate,a.overdate");
			hql="select * from ("+bufSql.toString()+") order by "+sortname+" "+sortorder;
			logger.debug("RptOverPay_GetRowSQL: "+hql);
			return getRptOverPayForPage(hql, offset, pagesize);
		}else if(view.equalsIgnoreCase("RptZnjPay")){
			bufSql.setLength(0);
			bufSql.append("select c.SHOPNAME,c.ZLCODE,c.ghdwdm,c.wldmname,b.mentname,c.accountdate,c.wymoney,c.ktmoney,c.overdate");
			bufSql.append(",c.wyznj,c.ktznj,a.pid,a.entrydate,a.entryuserid,a.htid,a.paidmonth,a.entryuser ");
			bufSql.append(",case when a.costype=1 and a.payment=3 then a.paidznj else  0 end jmwyznj ");
			bufSql.append(",case when a.costype=1 and a.payment<3 then a.paidznj else  0 end shwyznj ");
			bufSql.append(",case when a.costype=2 and a.payment=3 then a.paidznj else  0 end jmktznj ");
			bufSql.append(",case when a.costype=2 and a.payment<3 then a.paidznj else  0 end shktznj ");
			bufSql.append(" from wy_payment_tab b, wy_znj_info c,wy_paid a");
			bufSql.append(" where a.paidznj>0 and b.mid=a.payment and a.htid=c.htid and a.paidmonth=c.accountmonth and ");
			bufSql.append(where);
			hql="select * from ("+bufSql.toString()+") order by "+sortname+" "+sortorder;
			logger.debug("RptZnjPay_GetRowSQL: "+hql);
			return getRptZnjPayForPage(hql, offset, pagesize);
		}else if(view.equalsIgnoreCase("RptZnjPayMon")){
			bufSql.setLength(0);
			bufSql.append("select * from wy_znjpay_mon  where ");
			bufSql.append(where);
			hql=bufSql.toString()+" order by "+sortname+" "+sortorder;
			logger.debug("RptZnjPayMon_GetRowSQL: "+hql);
			return getRptZnjPayMonForPage(hql, offset, pagesize);
		}
		else{
			
			hql="from "+view+" where "+where+" order by "+sortname+" "+sortorder;	
//			System.out.println("hql:"+hql);
			return getListForPage(hql, offset, pagesize);
		}		
	}
	
	private List getWyWldmLackForPage(String hql, int offset, int length) {
		List<WyWldmLack> reports=new ArrayList<WyWldmLack>();
		try {
			Session session=factory.openSession();
			Query query=session.createSQLQuery(hql);
			
			query.setFirstResult(offset);
		    query.setMaxResults(length);
		    
		    List list = query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		    for (int i = 0; i < list.size(); i++) {
		    	WyWldmLack lack=new WyWldmLack();
				Map map=(Map)list.get(i);
				lack.setAccountmonth(map.get("ACCOUNTMONTH").toString());
				lack.setAccountsum(Double.valueOf(map.get("ACCOUNTSUM").toString()));
				lack.setEnddate(map.get("ENDDATE").toString());
				lack.setGhdwdm(map.get("GHDWDM").toString());
				lack.setHtid(map.get("HTID").toString());
				lack.setJzmj(map.get("JZMJ").toString());
				lack.setKtlack(Double.valueOf(map.get("KTLACK").toString()));
				lack.setKtmoney(Double.valueOf(map.get("KTMONEY").toString()));
				lack.setKtpaid(Double.valueOf(map.get("KTPAID").toString()));
				lack.setKtznj(Double.valueOf(map.get("KTZNJ").toString()));
				lack.setKtznjlack(Double.valueOf(map.get("KTZNJLACK").toString()));
				lack.setKtznjpaid(Double.valueOf(map.get("KTZNJPAID").toString()));
				lack.setLacksum(Double.valueOf(map.get("LACKSUM").toString()));
				lack.setPaidsum(Double.valueOf(map.get("PAIDSUM").toString()));
				lack.setShopname(map.get("SHOPNAME").toString());
				lack.setStartdate(map.get("STARTDATE").toString());
				lack.setWldmname(map.get("WLDMNAME").toString());
				lack.setWylack(Double.valueOf(map.get("WYLACK").toString()));
				lack.setWymoney(Double.valueOf(map.get("WYMONEY").toString()));
				lack.setWypaid(Double.valueOf(map.get("WYPAID").toString()));
				lack.setWyznj(Double.valueOf(map.get("WYZNJ").toString()));
				lack.setWyznjlack(Double.valueOf(map.get("WYZNJLACK").toString()));
				lack.setWyznjpaid(Double.valueOf(map.get("WYZNJPAID").toString()));
				lack.setZlcode(map.get("ZLCODE").toString());
				reports.add(lack);
			}
		    session.close();
 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reports;
	}

	private List getRptZnjPayMonForPage(String hql, int offset, int length) {
		List<RptZnjPayMon> reports=new ArrayList<RptZnjPayMon>();
		try {
			Session session=factory.openSession();
			Query query=session.createSQLQuery(hql);
			
			query.setFirstResult(offset);
		    query.setMaxResults(length);
		    
		    List list = query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		    for (int i = 0; i < list.size(); i++) {
		    	RptZnjPayMon znjPayMon=new RptZnjPayMon();
				Map map=(Map)list.get(i);
//				znjPayMon.setShop(map.get("SHOP").toString()) ;
				znjPayMon.setGhdwdm(map.get("GHDWDM").toString());
				znjPayMon.setHtid(map.get("HTID").toString());
				znjPayMon.setJmktznj(Double.valueOf(map.get("JMKTZNJ").toString()));
				znjPayMon.setJmwyznj(Double.valueOf(map.get("JMWYZNJ").toString()));
				znjPayMon.setKtznj(Double.valueOf(map.get("KTZNJ").toString()));
				znjPayMon.setPaidmonth(map.get("PAIDMONTH").toString());
				znjPayMon.setShktznj(Double.valueOf(map.get("SHKTZNJ").toString()));
				znjPayMon.setShopname(map.get("SHOPNAME").toString());
				znjPayMon.setShwyznj(Double.valueOf(map.get("SHWYZNJ").toString()));
				znjPayMon.setWldmname(map.get("WLDMNAME").toString());
				znjPayMon.setWyznj(Double.valueOf(map.get("WYZNJ").toString()));
				znjPayMon.setZlcode(map.get("ZLCODE").toString());
				reports.add(znjPayMon);
			}
		    session.close();
 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return reports;
	}

	public List<RptZnjPay> getRptZnjPayForPage(final String hql, final int offset,
		    final int length) {

		List<RptZnjPay> reports=new ArrayList<RptZnjPay>();
		try {
			Session session=factory.openSession();
			Query query=session.createSQLQuery(hql);
			
			query.setFirstResult(offset);
		    query.setMaxResults(length);
		    
		    List list = query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		    for (int i = 0; i < list.size(); i++) {
		    	RptZnjPay znjPay=new RptZnjPay();
				Map map=(Map)list.get(i);
				znjPay.setShop(map.get("SHOPNAME").toString()) ;
				znjPay.setZlhtid(map.get("ZLCODE").toString()) ;
				znjPay.setWyhtid(map.get("HTID").toString()) ;
				znjPay.setWldmbm(map.get("GHDWDM").toString()) ;
				znjPay.setWldmname(map.get("WLDMNAME").toString()) ;
				znjPay.setPaymode(map.get("MENTNAME").toString()) ;
				znjPay.setPayabledate(map.get("ACCOUNTDATE").toString()) ;
				znjPay.setPaiddate(map.get("ENTRYDATE").toString()) ;
				znjPay.setWymoney(Double.valueOf(map.get("WYMONEY").toString())) ;
				znjPay.setKtmoney(Double.valueOf(map.get("KTMONEY").toString())) ;
				znjPay.setOverdate(map.get("OVERDATE").toString()) ;
				znjPay.setWyznjmoney(Double.valueOf(map.get("WYZNJ").toString())) ;
				znjPay.setWyznjjm(Double.valueOf(map.get("JMWYZNJ").toString())) ;
				znjPay.setWyznjsh(Double.valueOf(map.get("SHWYZNJ").toString())) ;
				znjPay.setKtznjmoney(Double.valueOf(map.get("KTZNJ").toString())) ;
				znjPay.setKtznjjm(Double.valueOf(map.get("JMKTZNJ").toString())) ;
				znjPay.setKtznjsh(Double.valueOf(map.get("SHKTZNJ").toString())) ;
				znjPay.setPid(map.get("PID").toString()) ;
				znjPay.setEntryman(map.get("ENTRYUSER").toString()) ;
				reports.add(znjPay);
			}
		    session.close();
 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return reports;
	}
	
	
	public List<RptOverPay> getRptOverPayForPage(final String hql, final int offset,
		    final int length) {

		List<RptOverPay> reports=new ArrayList<RptOverPay>();
		try {
			Session session=factory.openSession();
			Query query=session.createSQLQuery(hql);
			
			query.setFirstResult(offset);
		    query.setMaxResults(length);
		    
		    List list = query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		    for (int i = 0; i < list.size(); i++) {
		    	RptOverPay overPay=new RptOverPay();
				Map map=(Map)list.get(i);
				overPay.setShop(map.get("SHOPNAME").toString()) ;
				overPay.setZlhtid(map.get("ZLCODE").toString()) ;
				overPay.setWyhtid(map.get("HTID").toString()) ;
				overPay.setWldmbm(map.get("GHDWDM").toString()) ;
				overPay.setWldmname(map.get("WLDMNAME").toString());
				overPay.setAccountmonth(map.get("ACCOUNTMONTH").toString());
				String paymode="";
				String dataMode=map.get("PAYMODE").toString();
				if(dataMode.equalsIgnoreCase("3")){
					paymode="每月度";
				}else if (dataMode.equalsIgnoreCase("2")) {
					paymode="每季度";
				}else{
					paymode="每半年度";
				}
				overPay.setPaymode(paymode);
				overPay.setPayabledate(map.get("ACCOUNTDATE").toString()) ;
				Double wycost=0.0,ktcost=0.0;
				wycost=Double.valueOf(map.get("WYMONEY").toString());
				overPay.setWymoney(wycost) ;
				ktcost=Double.valueOf(map.get("KTMONEY").toString());
				overPay.setKtmoney(ktcost) ;
				overPay.setOverdate(map.get("OVERDATE").toString()) ;
				//计算已交滞纳金
				String accountMonth=map.get("ACCOUNTMONTH").toString();
				String htid=map.get("HTID").toString();
				String znjhql="select nvl(sum(case when costype=1 then paidznj else 0 end ),0) wyznj,nvl(sum(case when costype=2 then paidznj else 0 end ),0) ktznj from wy_paid where paidmonth='"+accountMonth+"' and htid='"+htid+"'";
				Double pwyznj=0.0,pktznj=0.0,ywyznj=0.0,yktznj=0.0,wyznj=0.0,ktznj=0.0;
				Query znjquery=session.createSQLQuery(znjhql);
				List znjlist = znjquery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
				for (int z = 0; z < znjlist.size(); z++) {
					Map znjmap=(Map)znjlist.get(z);
					pwyznj=Double.valueOf(znjmap.get("WYZNJ").toString());
					pktznj=Double.valueOf(znjmap.get("KTZNJ").toString());
				}
			
				wyznj=Double.valueOf(map.get("WYZNJMONEY").toString());
				ktznj=Double.valueOf(map.get("KTZNJMONEY").toString());
				if(Double.valueOf(map.get("WYZNJMONEY").toString())==0){
					overPay.setWyznjmoney(processDouble(Double.valueOf(map.get("WYZNJMONEY").toString()),2)) ;
				}else{
					overPay.setWyznjmoney(processDouble(Double.valueOf(map.get("WYZNJMONEY").toString())-pwyznj, 2)) ;
				}
				if(Double.valueOf(map.get("KTZNJMONEY").toString())==0){
					overPay.setKtznjmoney(processDouble(Double.valueOf(map.get("KTZNJMONEY").toString()), 2)) ;
				}else{
					overPay.setKtznjmoney(processDouble(Double.valueOf(map.get("KTZNJMONEY").toString())-pktznj,2 )) ;
				}
				if((wyznj+ktznj)==0){
					overPay.setSummoney(processDouble(wycost+ktcost,2 ));	
				}else{
					if(wyznj==0){
						overPay.setSummoney(processDouble(wycost+ktcost+wyznj+ktznj-pktznj, 2));
					}else if(ktznj==0){
						overPay.setSummoney(processDouble(wycost+ktcost+wyznj+ktznj-pwyznj, 2));
					}else{
						overPay.setSummoney(processDouble(wycost+ktcost+wyznj+ktznj-pwyznj-pktznj, 2));	
					}
					
				}
				
				reports.add(overPay);
			}
		    session.close();
 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return reports;
	}
	
	public Double processDouble(Double data,int xiao){
		String x="";
		Double retrunValue;
		for(int i=0;i<=xiao;i++){
			x=x+"#";
		}
		DecimalFormat df=new DecimalFormat("."+x);
		String str=df.format(data);
		if(str.indexOf(".")>0){
			retrunValue=Double.valueOf(str.substring(0,str.length()-1));
		}else{
			retrunValue=Double.valueOf("0");
		}
		
		return retrunValue;
	}
	
	public List<RptMonPay> getRptMonPayForPage(final String hql, final int offset,
		    final int length) {

		List<RptMonPay> reports=new ArrayList<RptMonPay>();
		try {
			Session session=factory.openSession();
			Query query=session.createSQLQuery(hql);
			
			query.setFirstResult(offset);
		    query.setMaxResults(length);
		    
		    List list = query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		    for (int i = 0; i < list.size(); i++) {
		    	RptMonPay monPay=new RptMonPay();
				Map map=(Map)list.get(i);
				monPay.setShop(map.get("SHOPNAME").toString()) ;
				monPay.setMonth(map.get("ACCOUNTMONTH").toString()) ;
				monPay.setZlhtid(map.get("ZLCODE").toString()) ;
				monPay.setWyhtid(map.get("HTID").toString()) ;
				monPay.setWldmbm(map.get("GHDWDM").toString()) ;
				monPay.setWldmname(map.get("WLDMNAME").toString());
				String paymode="";
				String dataMode=map.get("PAYMODE").toString();
				if(dataMode.equalsIgnoreCase("3")){
					paymode="每月度";
				}else if (dataMode.equalsIgnoreCase("2")) {
					paymode="每季度";
				}else{
					paymode="每半年度";
				}
				String ispay=map.get("COSTOVER").toString();
				if(ispay.equalsIgnoreCase("0")){
					monPay.setIspay("未缴");
				}else{
					monPay.setIspay("已缴");
				}
				monPay.setPaymode(paymode);
				monPay.setJzmj(map.get("JZMJ").toString()) ;
				monPay.setWymoney(Double.valueOf(map.get("WYMONEY").toString())) ;
				monPay.setKtmoney(Double.valueOf(map.get("KTMONEY").toString())) ;
				Double summoney=Double.valueOf(map.get("WYMONEY").toString())+Double.valueOf(map.get("KTMONEY").toString());
				monPay.setSummoney(summoney) ;
				reports.add(monPay);
			}
		    session.close();
 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return reports;
	}
	
	public List<RptManPay> getRptManPayForPage(final String hql, final int offset,
		    final int length) {

		List<RptManPay> reports=new ArrayList<RptManPay>();
		try {
			Session session=factory.openSession();
			Query query=session.createSQLQuery(hql);
			
			query.setFirstResult(offset);
		    query.setMaxResults(length);
		    
		    List list = query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		    for (int i = 0; i < list.size(); i++) {
		    	RptManPay manPay=new RptManPay();
				Map map=(Map)list.get(i);
				manPay.setShop(map.get("SHOPNAME").toString());
				manPay.setPaydate(map.get("ENTRYDATE").toString())   ;
				manPay.setPayid(map.get("PID").toString())       ;
				manPay.setPayman(map.get("ENTRYUSER").toString())     ;
				manPay.setZlhtid(map.get("ZLCODE").toString())     ;
				manPay.setWyhtid(map.get("HTID").toString())     ;
				manPay.setWldmbm(map.get("GHDWDM").toString())     ;
				manPay.setWldmname(map.get("WLDMNAME").toString()) ;
				manPay.setPaymode(map.get("MENTNAME").toString())   ;
				manPay.setWymoney(Double.valueOf(map.get("WYMONEY").toString()))   ;
				manPay.setKtmoney(Double.valueOf(map.get("KTMONEY").toString()))   ;
				manPay.setZnjmoney(Double.valueOf(map.get("ZNJMONEY").toString())) ;
				manPay.setPaidmonth(map.get("PAIDMONTH").toString());
				Double summoney=Double.valueOf(map.get("WYMONEY").toString())+Double.valueOf(map.get("KTMONEY").toString())+Double.valueOf(map.get("ZNJMONEY").toString());
				manPay.setSummoney(summoney) ;
				reports.add(manPay);
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

	public List<WyReportFeild> getReportInfo(String view) {
		List<WyReportFeild> rfeilds=new ArrayList<WyReportFeild>();
		try {
			Session session=factory.openSession();
			Query query=session.createSQLQuery("select * from wy_report_feild where EN_REPORT='"+view+"'");
			
		    List list = query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		    for (int i = 0; i < list.size(); i++) {
		    	WyReportFeild rFeild=new WyReportFeild();
				Map map=(Map)list.get(i);
				rFeild.setEnfeild(map.get("EN_FEILD").toString());
				rFeild.setEnreport(map.get("EN_REPORT").toString());
				rFeild.setZhfeild(map.get("ZH_FEILD").toString());
				rFeild.setZhreport(map.get("ZH_REPORT").toString());
				rfeilds.add(rFeild);
			}
		    session.close();
 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rfeilds;
	}
    
    
}
