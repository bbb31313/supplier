package pdz.wyht.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pdz.wyht.bean.BiShop;
import pdz.wyht.bean.SelectItem;
import pdz.wyht.bean.SmartMenu;
import pdz.wyht.bean.SmartMenuBean;
import pdz.wyht.bean.SmartTool;
import pdz.wyht.bean.WyAccount;
import pdz.wyht.bean.WyRoleResoure;
import pdz.wyht.bean.WyTime;
import pdz.wyht.bean.WyhtUser;


@Service @Transactional
public class WySysService {

	@Resource SessionFactory sessionFactory;
	
	

	public List<SmartMenu> getAllMenus(WyhtUser user){
		
		List<SmartMenu> menus=new ArrayList<SmartMenu>();
		String firstID;
		
		List<SmartMenuBean> firstMenus=getMenusByRole(user);
		
		for (SmartMenuBean menuBean : firstMenus) {
			SmartMenu smenu=new SmartMenu();
			smenu.setMenuName(menuBean.getMenuName());
			smenu.setMenuNo(menuBean.getMenuNo());
			firstID=menuBean.getMenuID();
			List<SmartMenuBean> secondMenus=getMenusByParent(firstID,user);
			if (secondMenus.size()>0) {
				smenu.setChildren(secondMenus);	
			}
			menus.add(smenu);
		}
		return menus;
	}
	
	public List<SmartMenuBean> getMenusByParent(String pid,WyhtUser usr){
		String hql="";
		Long rid=usr.getRole();
		if(rid==-1){
			hql="from SmartMenuBean menu where menu.parentID='"+pid+"' and menu.isVisible='5' order by menu.menuID ";
		}else{
			WyRoleResoure rs=getRoleResoure(usr);
			if(rs.getSecondmenu()!=null){
				hql="from SmartMenuBean menu where menu.parentID='"+pid+"' and menu.isVisible='2' and menu.menuID not in("+rs.getSecondmenu()+") order by menu.menuID ";
			}else{
				hql="from SmartMenuBean menu where menu.parentID='"+pid+"' and menu.isVisible='2'  order by menu.menuID ";
			}
		}
		
//		System.out.println("SecondMenuHQL:"+hql);
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}
	
	public List<SmartMenuBean> getMenusByRole(WyhtUser usr){
		String basehql ,hql,orderStr;
		basehql="from SmartMenuBean menu where menu.parentID='0' and menu.isVisible='2'";
		orderStr=" order by menu.menuID ";
		hql=basehql+orderStr;
		try {
			if(usr.getRole()==-1){
				hql="from SmartMenuBean menu where menu.parentID='0' and menu.isVisible='5' order by menu.menuID ";
			}else{
				WyRoleResoure rs=getRoleResoure(usr);
				if(rs.getFirstmenu()!=null)
					hql=basehql+" and menu.menuID not in ("+rs.getFirstmenu()+") "+orderStr;
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
//		hql="from SmartMenuBean menu where menu.parentID='0' and menu.isVisible='2' order by menu.menuID ";
//		System.out.println("FirstMenuHQL:"+hql);
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public WyhtUser getSmartUser(String name,String pwd){
		String hql="from WyhtUser user where user.usersx='"+name+"' and user.password='"+pwd+"'";
				
		List<WyhtUser> users=sessionFactory.getCurrentSession().createQuery(hql).list();
		if (users.size()>0) {
			return users.get(0);
		}else{
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<SmartTool> getToolByMenuNo(String menuno,WyhtUser usr){
		List<SmartTool> tools=null;
		String hql;
		
		WyRoleResoure rs =getRoleResoure(usr);
		if(rs.getTools()!=null){
			hql="from SmartTool stool where stool.MenuNo='"+menuno+"' and stool.isview=1 and stool.BtnId not in ("+rs.getTools()+") ";
		}else{
			hql="from SmartTool stool where stool.MenuNo='"+menuno+"' and stool.isview=1";
		}
		
//		System.out.println("ToolsHQL:"+hql);
		try {
			tools=sessionFactory.getCurrentSession().createQuery(hql).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tools;
	}
	
	public WyRoleResoure getRoleResoure(WyhtUser user) {
//		String hql="from WyRoleResouce rs where rs.roleid="+user.getRole()+" and rs.shopid='"+user.getShop()+"'";
		String hql="from WyRoleResoure where roleid="+user.getRole()+" and shopid='"+user.getShop()+"'";
//		System.out.println("resourceHQL:"+hql);
		List<WyRoleResoure> rsList=sessionFactory.getCurrentSession().createQuery(hql).list();
//		System.out.println("size:"+rsList.size());
		return (WyRoleResoure)rsList.get(0);
	}
	
	public List<SmartTool> getAllTool(){
		String hql="from SmartTool stool where stool.isview=1 order by stool.BtnId";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}
	

	public boolean changePwd(WyhtUser user){
		
		try {
			sessionFactory.getCurrentSession().update(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public List<SelectItem> getSecondList(String pid){
		List<SelectItem> items=new ArrayList<SelectItem>();
		String hql="from SmartMenuBean sm where sm.parentID='"+pid+"'";
		List<SmartMenuBean> menuBeans=sessionFactory.getCurrentSession().createQuery(hql).list();
		for (SmartMenuBean menu : menuBeans) {
			SelectItem item=new SelectItem();
			item.setId(menu.getMenuID());
			item.setText(menu.getMenuName());
			items.add(item);
		}
		return items;
	}
	
	public Object getObj(Class arg0,Serializable arg1){
		return sessionFactory.getCurrentSession().get(arg0, arg1);
	}
	
	public List<SelectItem> getShopItems(String shopid) {
		String sql;
		List<SelectItem> stList=new ArrayList<SelectItem>();
		SelectItem kst=new SelectItem();
		kst.setId("all");
		kst.setText("全部");
		stList.add(kst);
		if(shopid.equalsIgnoreCase("1")){
			sql="from BiShop shop where length(shop.shopid)>4";
			SelectItem zst=new SelectItem();
			zst.setId("1");
			zst.setText("总部");
			stList.add(zst);
		}else{
			sql="from BiShop shop where shop.shopid='"+shopid+"'";
		}

		
		List<BiShop> list=null;
		try {
			list=sessionFactory.getCurrentSession().createQuery(sql).list();
			String id,text;
			for(BiShop shop:list){
				id=shop.getShopid();
				text=shop.getShopname();
				SelectItem st=new SelectItem();
				st.setId(id);
				st.setText(text);
				stList.add(st);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return stList;
	}

	public List<SelectItem> getUserItems(String shopid) {
		String sql;
		if(shopid.equalsIgnoreCase("1")){
			sql="from WyhtUser wuser order by usersx";
		}else{
			sql="from WyhtUser wuser where wuser.shop='"+shopid+"' order by wuser.usersx ";
		}
		
		List<SelectItem> stList=new ArrayList<SelectItem>();
		List<WyhtUser> list=null;
		try {
			list=sessionFactory.getCurrentSession().createQuery(sql).list();
			String id,text;
			for(WyhtUser user:list){
				id=user.getUserID().toString();
				text=user.getUserName();
				SelectItem st=new SelectItem();
				st.setId(id);
				st.setText(text);
				stList.add(st);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stList;
		
	}

	public List<SelectItem> getTimeItems(String timemode) {
		String hql="from WyTime where timemode="+timemode;
		List<SelectItem> stList=new ArrayList<SelectItem>();
		try {
			List<WyTime> wtlist=sessionFactory.getCurrentSession().createQuery(hql).list();
			
			for(WyTime wytime:wtlist){
				SelectItem st=new SelectItem();
				st.setId(wytime.getTimename());
				st.setText(wytime.getTimename());
				stList.add(st);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return stList;
	}

	public List<SelectItem> getAMonthItems(String htid) {
		String hql="select accountmonth from wy_account a where a.htid='"+htid+"' and a.costype=1 group by a.accountmonth order by a.accountmonth";
		List<SelectItem> stList=new ArrayList<SelectItem>();
		try {
			List wtlist=sessionFactory.getCurrentSession().createSQLQuery(hql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
			for (int i = 0; i < wtlist.size(); i++) {
				Map map=(Map)wtlist.get(i);
				SelectItem st=new SelectItem();
				st.setId(map.get("ACCOUNTMONTH").toString());
				st.setText(map.get("ACCOUNTMONTH").toString());
				stList.add(st);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return stList;
	}


}
