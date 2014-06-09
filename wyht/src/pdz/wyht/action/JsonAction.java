package pdz.wyht.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import pdz.wyht.bean.AjaxResult;
import pdz.wyht.bean.SelectItem;
import pdz.wyht.bean.SmartMenu;
import pdz.wyht.bean.SmartTool;
import pdz.wyht.bean.WyZLht;
import pdz.wyht.bean.WyhtUser;
import pdz.wyht.service.WySysService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller @Scope("prototype")
public class JsonAction extends ActionSupport {
	@Resource WySysService wySysService;
	
	private List<SmartMenu> menus;
	private List<SelectItem> secondSelect;
	private List<SelectItem> userItems;
	private List<SelectItem> shopItems;
	private List<SelectItem> timeItems;
	private List<SelectItem> monthItems;
	private String username;
	private String password;
	private String OldPassword;
	private String LoginPassword;
	private AjaxResult ajaxResult;
	private String MenuNo;
	private String parentid;
	private String zlid;
	private String timemode;
	private String htid;
		

	

	
	public List<SelectItem> getMonthItems() {
		return monthItems;
	}

	public void setMonthItems(List<SelectItem> monthItems) {
		this.monthItems = monthItems;
	}

	public String getHtid() {
		return htid;
	}

	public void setHtid(String htid) {
		this.htid = htid;
	}

	public List<SelectItem> getTimeItems() {
		return timeItems;
	}

	public void setTimeItems(List<SelectItem> timeItems) {
		this.timeItems = timeItems;
	}

	public String getTimemode() {
		return timemode;
	}

	public void setTimemode(String timemode) {
		this.timemode = timemode;
	}

	public List<SelectItem> getShopItems() {
		return shopItems;
	}

	public void setShopItems(List<SelectItem> shopItems) {
		this.shopItems = shopItems;
	}

	public List<SelectItem> getUserItems() {
		return userItems;
	}

	public void setUserItems(List<SelectItem> userItems) {
		this.userItems = userItems;
	}

	public String getZlid() {
		return zlid;
	}

	public void setZlid(String zlid) {
		this.zlid = zlid;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public List<SelectItem> getSecondSelect() {
		return secondSelect;
	}

	public void setSecondSelect(List<SelectItem> secondSelect) {
		this.secondSelect = secondSelect;
	}

	public String getMenuNo() {
		return MenuNo;
	}
	
	public void setMenuNo(String menuNo) {
		MenuNo = menuNo;
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


	public List<SmartMenu> getMenus() {
		return menus;
	}


	public void setMenus(List<SmartMenu> menus) {
		this.menus = menus;
	}


	public AjaxResult getAjaxResult() {
		return ajaxResult;
	}


	public void setAjaxResult(AjaxResult ajaxResult) {
		this.ajaxResult = ajaxResult;
	}
	
	public String getSmartMenus(){
		Object obj=ActionContext.getContext().getSession().get("wyuser");
		
		if (obj!=null) {
			WyhtUser user=(WyhtUser)obj;
			menus=wySysService.getAllMenus(user);
		}
		
		return SUCCESS;
	}
	
	public String getCartMenus(){
		
		WyhtUser user=new WyhtUser();
		user.setRole(-1L);
		menus=wySysService.getAllMenus(user);
		
		return SUCCESS;
	}
	
	
	public String getToolByMenu(){
		Object obj=ActionContext.getContext().getSession().get("wyuser");
		Long rid = null;
		WyhtUser user=null;
		if (obj!=null) {
			user=(WyhtUser)obj;
			rid=user.getRole();
		}
		AjaxResult aResult=new AjaxResult();
		List<SmartTool> toolList=wySysService.getToolByMenuNo(MenuNo,user);
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
	
	public String  getYiInfo() {
		
		WyZLht zLht=(WyZLht)wySysService.getObj(WyZLht.class, Long.valueOf(zlid));
		AjaxResult aResult=new AjaxResult();
		aResult.setData(zLht);
		aResult.setError(false);
		
		ajaxResult=aResult;
		
		return "tool";
	}
	
	public String doLogin(){

		WyhtUser supuser=wySysService.getSmartUser(username, password);
		AjaxResult aResult=new AjaxResult();
		if (supuser!=null) {
			ActionContext.getContext().getSession().put("wyuser", supuser);
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
		Object obj=ActionContext.getContext().getSession().get("wyuser");
		
		AjaxResult aResult=new AjaxResult();
		if (obj!=null) {
			WyhtUser user=(WyhtUser)obj;
			if(user.getPassword().equalsIgnoreCase(OldPassword)){
				user.setPassword(LoginPassword);
				if (wySysService.changePwd(user)) {
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
	
	public String secondBySeclect(){
		secondSelect=wySysService.getSecondList(parentid);
		return "seconditem";
	}
	
	public String wyuserBySeclect(){
		WyhtUser fUser=(WyhtUser)ActionContext.getContext().getSession().get("wyuser");
		String shopid=fUser.getShop();
		userItems=wySysService.getUserItems(shopid);
		return "userlist";
	}
	
	public String shopBySeclect(){
		WyhtUser fUser=(WyhtUser)ActionContext.getContext().getSession().get("wyuser");
		String shopid=fUser.getShop();
		shopItems=wySysService.getShopItems(shopid);
		return "shoplist";
	}
	
	public String timeBySeclect(){
		
		timeItems=wySysService.getTimeItems(timemode);
		return "timelist";
	}
	
	public String aMonthBySeclect(){
		
		monthItems=wySysService.getAMonthItems(htid);
		return "monthlist";
	}
}
