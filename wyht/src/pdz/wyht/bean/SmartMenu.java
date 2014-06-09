package pdz.wyht.bean;

import java.util.List;


public class SmartMenu {


	private String   menuID;
	
	private String   menuNo;
	
	private String   menuName;
	
	private String   menuIcon;
	
	private List<SmartMenuBean> children;

	public String getMenuID() {
		return menuID;
	}

	public void setMenuID(String menuID) {
		this.menuID = menuID;
	}

	public String getMenuNo() {
		return menuNo;
	}

	public void setMenuNo(String menuNo) {
		this.menuNo = menuNo;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public List<SmartMenuBean> getChildren() {
		return children;
	}

	public void setChildren(List<SmartMenuBean> children) {
		this.children = children;
	}
	
	
}
