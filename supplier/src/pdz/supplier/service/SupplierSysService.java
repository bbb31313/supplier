package pdz.supplier.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pdz.supplier.bean.SupplierCategory;
import pdz.supplier.bean.SupplierMenu;
import pdz.supplier.bean.SupplierMenuBean;
import pdz.supplier.bean.SupplierRoleResoure;
import pdz.supplier.bean.SupplierTool;
import pdz.supplier.bean.SupplierUser;
import pdz.supplier.bean.TreeLeaf;

@Service @Transactional
public class SupplierSysService {

	@Resource SessionFactory sessionFactory;
	@Resource SupBaseInfoService supBaseInfoService;
	

	public List<SupplierMenu> getAllMenus(SupplierUser user){
		List<SupplierMenu> menus=new ArrayList<SupplierMenu>();
		String firstID;
		List<SupplierMenuBean> firstMenus=getMenusByRole(user);
		for (SupplierMenuBean menuBean : firstMenus) {
			SupplierMenu smenu=new SupplierMenu();
			smenu.setMenuName(menuBean.getMenuName());
			smenu.setMenuNo(menuBean.getMenuNo());
			firstID=menuBean.getMenuID();
			List<SupplierMenuBean> secondMenus=getMenusByParent(firstID,user);
			smenu.setChildren(secondMenus);
			menus.add(smenu);
		}
		return menus;
	}
	
	public List<SupplierMenuBean> getMenusByParent(String pid,SupplierUser usr){
		String hql="";
		Long rid=usr.getRole();
		SupplierRoleResoure rs=getRoleResoure(usr);
		if(rs.getSecondmenu()!=null){
			hql="from SupplierMenuBean menu where menu.parentID='"+pid+"' and menu.isVisible='1' and menu.menuID not in("+rs.getSecondmenu()+") order by menu.menuID ";
		}else{
			hql="from SupplierMenuBean menu where menu.parentID='"+pid+"' and menu.isVisible='1'  order by menu.menuID ";
		}
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}
	
	
	public List<SupplierMenuBean> getMenusByRole(SupplierUser usr){
		String basehql ,hql,orderStr;
		basehql="from SupplierMenuBean menu where menu.parentID='0' and menu.isVisible='1'";
		orderStr=" order by menu.menuID ";
		hql=basehql+orderStr;
		try {
			 SupplierRoleResoure rs=getRoleResoure(usr);
			 if(rs.getFirstmenu()!=null)
					hql=basehql+" and menu.menuID not in ("+rs.getFirstmenu()+") "+orderStr;
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public SupplierUser getSupplierUser(String name,String pwd){
		String hql="from SupplierUser user where user.usersx='"+name+"' and user.password='"+pwd+"'";
				
		List<SupplierUser> users=sessionFactory.getCurrentSession().createQuery(hql).list();
		if (users.size()>0) {
			return users.get(0);
		}else{
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<SupplierTool> getToolByMenuNo(String menuno,SupplierUser usr){
		List<SupplierTool> tools=null;
		String hql;
		
		SupplierRoleResoure rs=getRoleResoure(usr);
		if (rs.getTools()!=null){
			hql="from SupplierTool stool where stool.menuNo='"+menuno+"' and stool.btnId not in ("+rs.getTools()+") and stool.isview=1 order by stool.btnId ";
		}else{
			
			hql="from SupplierTool stool where stool.menuNo='"+menuno+"' and stool.isview=1 order by stool.btnId ";
		}
		try {
			tools=sessionFactory.getCurrentSession().createQuery(hql).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tools;
	}
	
	public SupplierRoleResoure getRoleResoure(SupplierUser user) {
		
		String hql="from SupplierRoleResoure where roleid="+user.getRole();
//		System.out.println("resourceHQL:"+hql);
		List<SupplierRoleResoure> rsList=sessionFactory.getCurrentSession().createQuery(hql).list();
//		System.out.println("size:"+rsList.size());
		return (SupplierRoleResoure)rsList.get(0);
	}
	
	public List<SupplierTool> getAllTool(){
		String hql="from SupplierTool stool where stool.isview=1 order by stool.BtnId";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}
	
	public List<TreeLeaf> getCateTree(){
		List<SupplierCategory> firstCate=new ArrayList<SupplierCategory>();
		try {
			firstCate=supBaseInfoService.getCateByParent("0");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<TreeLeaf> leaf1=new ArrayList<TreeLeaf>();
		for (SupplierCategory cate1 : firstCate) {
			TreeLeaf tree1=new TreeLeaf();
			tree1.setId(cate1.getCateid());
			tree1.setText(cate1.getCatename());
			tree1.setFatherid(cate1.getParentid());
			List<SupplierCategory> secondCate=supBaseInfoService.getCateByParent(cate1.getCateid());
			List<TreeLeaf> leaf2=new ArrayList<TreeLeaf>();
			for (SupplierCategory cate2 : secondCate) {
				TreeLeaf tree2=new TreeLeaf();
				tree2.setId(cate2.getCateid());
				tree2.setText(cate2.getCatename());
				tree2.setFatherid(cate2.getParentid());
				List<SupplierCategory> thirdCate=supBaseInfoService.getCateByParent(cate2.getCateid());
				List<TreeLeaf> leaf3=new ArrayList<TreeLeaf>();
				if (thirdCate.size()>0) {
					for (SupplierCategory cate3 : thirdCate) {
						TreeLeaf tree3=new TreeLeaf();
						tree3.setId(cate3.getCateid());
						tree3.setText(cate3.getCatename());
						tree3.setFatherid(cate3.getParentid());		
						leaf3.add(tree3);
					}
					tree2.setChildren(leaf3);
				}
				leaf2.add(tree2);
			}
			tree1.setChildren(leaf2);
			leaf1.add(tree1);
		}
		return leaf1;
	}
	
	
	public boolean changePwd(SupplierUser user){
		
		try {
			sessionFactory.getCurrentSession().update(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
}
