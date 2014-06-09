package pdz.wyht.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="wy_role_resource")
public class WyRoleResoure {

	@Id
	@Column(name="ROLEID")
	private Long roleid;
	@Column(name="SHOPID")
	private String shopid;
	@Column(name="FIRSTMENU")
	private String firstmenu;
	@Column(name="SECONDMENU")
	private String secondmenu;
	@Column(name="TOOLS")
	private String tools;
	
	
	public Long getRoleid() {
		return roleid;
	}
	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}
	public String getShopid() {
		return shopid;
	}
	public void setShopid(String shopid) {
		this.shopid = shopid;
	}
	public String getFirstmenu() {
		return firstmenu;
	}
	public void setFirstmenu(String firstmenu) {
		this.firstmenu = firstmenu;
	}
	public String getSecondmenu() {
		return secondmenu;
	}
	public void setSecondmenu(String secondmenu) {
		this.secondmenu = secondmenu;
	}
	public String getTools() {
		return tools;
	}
	public void setTools(String tools) {
		this.tools = tools;
	}
	
	
	
}
