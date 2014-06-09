package pdz.wyht.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="smart_menu")
public class SmartMenuBean {

	@Id
	@Column(name="MenuID")
	private String   menuID;
	@Column(name="MenuNo")
	private String   menuNo     ;
	@Column(name="ParentID")
	private String   parentID   ;
	@Column(name="MenuName")
	private String   menuName   ;
	@Column(name="MenuUrl")
	private String   menuUrl    ;
	@Column(name="MenuIcon")
	private String   menuIcon   ;
	@Column(name="IsVisible")
	private String   isVisible  ;
	@Column(name="lid")
	private String   lid  ;
	@Column(name="LNAME")
	private String   lname  ;
	
	
	
	public String getLid() {
		return lid;
	}
	public void setLid(String lid) {
		this.lid = lid;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
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
	public String getParentID() {
		return parentID;
	}
	public void setParentID(String parentID) {
		this.parentID = parentID;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public String getMenuIcon() {
		return menuIcon;
	}
	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}
	public String getIsVisible() {
		return isVisible;
	}
	public void setIsVisible(String isVisible) {
		this.isVisible = isVisible;
	}
	
	
	
}
