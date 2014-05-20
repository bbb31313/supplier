package pdz.supplier.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import pdz.supplier.bean.AjaxResult;
import pdz.supplier.bean.SupplierMenu;
import pdz.supplier.bean.SupplierTool;
import pdz.supplier.bean.SupplierUser;
import pdz.supplier.bean.TreeLeaf;
import pdz.supplier.service.SupplierSysService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller @Scope("prototype")
public class JsonAction extends ActionSupport {
	@Resource SupplierSysService supplierSysService;
	
	private List<SupplierMenu> menus;
	private List<TreeLeaf> tree;
	private String username;
	private String password;
	private String OldPassword;
	private String LoginPassword;
	private AjaxResult ajaxResult;
	private String MenuNo;
		

	
	
	public String getMenuNo() {
		return MenuNo;
	}
	public void setMenuNo(String menuNo) {
		MenuNo = menuNo;
	}

	public List<TreeLeaf> getTree() {
		return tree;
	}


	public void setTree(List<TreeLeaf> tree) {
		this.tree = tree;
	}


	public String getOldPassword() {
		return OldPassword;
	}


	public void setOldPassword(String oldPassword) {
		OldPassword = oldPassword;
	}


	public String getLoginPassword() {
		return LoginPassword;
	}


	public void setLoginPassword(String loginPassword) {
		LoginPassword = loginPassword;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public List<SupplierMenu> getMenus() {
		return menus;
	}


	public void setMenus(List<SupplierMenu> menus) {
		this.menus = menus;
	}


	public AjaxResult getAjaxResult() {
		return ajaxResult;
	}


	public void setAjaxResult(AjaxResult ajaxResult) {
		this.ajaxResult = ajaxResult;
	}
	
	public String getSupplierMenus(){
		Object obj=ActionContext.getContext().getSession().get("supuser");
		
		if (obj!=null) {
			SupplierUser user=(SupplierUser)obj;
			menus=supplierSysService.getAllMenus(user);
		}
		
		return SUCCESS;
	}
	
	public String getCateTree(){
		tree=supplierSysService.getCateTree();
		return "catetree";
	}
	
	public String getToolByMenu(){
		Object obj=ActionContext.getContext().getSession().get("supuser");
		List<SupplierTool> toolList =new ArrayList<SupplierTool>();
		if (obj!=null) {
			SupplierUser user=(SupplierUser)obj;
			System.out.println("MenuNo:"+MenuNo);
			toolList=supplierSysService.getToolByMenuNo(MenuNo,user);
		}
		AjaxResult aResult=new AjaxResult();
		
		if (toolList.size()>0) {
			aResult.setData(toolList);
			aResult.setError(false);
			aResult.setMessage("工具条加载成功!");
		}else{
			aResult.setError(true);
			aResult.setMessage("工具条加载失败!");
		}
		ajaxResult=aResult;
		return "tool";
	}
	
	public String doLogin(){
		SupplierUser supuser=supplierSysService.getSupplierUser(username, password);
		AjaxResult aResult=new AjaxResult();
		if (supuser!=null) {
			ActionContext.getContext().getSession().put("supuser", supuser);
			aResult.setMessage("登录成功!");
			aResult.setError(false);
			
		}else{
			aResult.setMessage("登录失败!");
			aResult.setError(true);
		}
		ajaxResult=aResult;
		return "login";
	}
	
	public String changepwd(){
		Object obj=ActionContext.getContext().getSession().get("supuser");
		AjaxResult aResult=new AjaxResult();
		if (obj!=null) {
			SupplierUser user=(SupplierUser)obj;
			if(user.getPassword().equalsIgnoreCase(OldPassword)){
				user.setPassword(LoginPassword);
				if (supplierSysService.changePwd(user)) {
					aResult.setMessage("修改密码成功!");
					aResult.setError(false);
				}else {
					aResult.setMessage("修改密码失败!");
					aResult.setError(true);
				}
			}else{
				aResult.setMessage("旧密码输入错误!");
				aResult.setError(true);
			}
		}else{
			aResult.setMessage("用户不存在!");
			aResult.setError(true);
		}
		ajaxResult=aResult;
		return "changepwd";
	}
}
