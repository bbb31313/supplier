package pdz.supplier.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pdz.supplier.bean.SupplierCategory;
import pdz.supplier.bean.SupplierDoc;
import pdz.supplier.bean.SupplierLicense;
import pdz.supplier.bean.SupplierMenu;
import pdz.supplier.bean.SupplierMenuBean;
import pdz.supplier.bean.SupplierUser;
import pdz.supplier.bean.TreeLeaf;

@Service @Transactional
public class SupBaseInfoService {

	@Resource SessionFactory sessionFactory;
	

	@SuppressWarnings("unchecked")
	public List<SupplierCategory> getCateByParent(String pid){
		String hql="from SupplierCategory cate where cate.parentid='"+pid+"'";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
		
	}
	
	
	public boolean saveSupDoc(SupplierDoc doc){
		try {
			sessionFactory.getCurrentSession().save(doc);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean updateSupDoc(SupplierDoc doc){
		try {
			sessionFactory.getCurrentSession().update(doc);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean delSupDoc(SupplierDoc doc){
		try {
			sessionFactory.getCurrentSession().delete(doc);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public SupplierDoc getDocByName(String supname){
		String hql="from SupplierDoc doc where doc.supname=?";
		return (SupplierDoc)sessionFactory.getCurrentSession().get(hql, supname);
	}
	
	public boolean isSupNameDouble(String supname){
		String hql="from SupplierDoc doc where doc.supname='"+supname+"'";
		List supList=sessionFactory.getCurrentSession().createQuery(hql).list();
		if(supList.size()>0){
			return true;
		}else{
			return false;
		}
		
	}
	
	public SupplierDoc getDocById(String did){
		return (SupplierDoc)sessionFactory.getCurrentSession().get(SupplierDoc.class, did);
	}
	
	public List<SupplierLicense> getLicensesBySupId(String supid){
		String hql="from SupplierLicense lse where lse.supID='"+supid+"'";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}
	
	
	public boolean addSupplierLicense(SupplierLicense lse){
		boolean flag=false;
		try {
			sessionFactory.getCurrentSession().save(lse);
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	

	public List<TreeLeaf> getCateTree(){
		List<SupplierCategory> firstCate=getCateByParent("0");
		List<TreeLeaf> leaf1=new ArrayList<TreeLeaf>();
		for (SupplierCategory cate1 : firstCate) {
			TreeLeaf tree1=new TreeLeaf();
			tree1.setId(cate1.getCateid());
			tree1.setText(cate1.getCatename());
			tree1.setFatherid(cate1.getParentid());
			List<SupplierCategory> secondCate=getCateByParent(cate1.getCateid());
			List<TreeLeaf> leaf2=new ArrayList<TreeLeaf>();
			for (SupplierCategory cate2 : secondCate) {
				TreeLeaf tree2=new TreeLeaf();
				tree2.setId(cate2.getCateid());
				tree2.setText(cate2.getCatename());
				tree2.setFatherid(cate2.getParentid());
				List<SupplierCategory> thirdCate=getCateByParent(cate2.getCateid());
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
	
	
	
}
