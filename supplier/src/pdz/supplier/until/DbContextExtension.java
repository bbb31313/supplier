package pdz.supplier.until;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pdz.supplier.grid.FilterGroup;
import pdz.supplier.grid.FilterRule;
import pdz.supplier.grid.FilterTranslator;
import pdz.supplier.grid.GridData;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;

@Service
public class DbContextExtension  {
	
	@Resource SessionFactory factory;
	private static Log logger=LogFactory.getLog(DbContextExtension.class);
	
    public GridData GetGridData(HttpServletRequest request)
    {
    	 String view =request.getParameter("view"); 
    	 String sortname =request.getParameter("sortname"); 
    	 String sortorder =request.getParameter("sortorder"); 
    	 String _pagenumber =request.getParameter("page"); 
         String _pagesize =request.getParameter("pagesize");  
         

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
          logger.debug("where:"+where);
          if (!(where==null))
          {
              //反序列化Filter Group JSON
              whereTranslator.setGroup(jsonTranslatorFilterGroup(where));
          }
            
          whereTranslator.Translate();

          return GetGridData(view, whereTranslator.CommandText, sortname, sortorder, pagenumber, pagesize);
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
		commandText="select count(*) from "+view+" where "+where;

		logger.debug("TotalCommandText:"+commandText);
		
		int cnt=0;
		try {
			Session session=factory.openSession();
			Query query=session.createQuery(commandText);
			cnt=((Number)query.iterate().next()).intValue();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	private List GetGridRows(String view, String where, String sortname,
			String sortorder, int pagenumber, int pagesize) {
		int offset=pagenumber==1?0:(pagenumber-1)*pagesize;
		String hql;
		hql="from "+view+" where "+where+" order by "+sortname+" "+sortorder;
		logger.debug("rowsHQL:"+hql);
		return getListForPage(hql, offset, pagesize);
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
