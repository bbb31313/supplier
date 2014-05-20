package pdz.supplier.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="SUPPLIER_ROLE_RESOURCE")
public class SupplierRoleResoure {

	@Id
	@Column(name="ROLEID")
	private Long roleid;
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
