package pdz.supplier.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Supplier_CATEGORY")
public class SupplierCategory {

	@Id
	@Column(name="CATEID")
	private String cateid  ;
	@Column(name="CATENAME")
	private String catename	;
	@Column(name="PARENTID")
	private String parentid	;
	@Column(name="CATELEVEL")
	private String catelevel;
	@Column(name="CATECODE")
	private String catecode	;
	@Column(name="FULLCODE")
	private String fullcode	;
	
	
	public String getCateid() {
		return cateid;
	}
	public void setCateid(String cateid) {
		this.cateid = cateid;
	}
	public String getCatename() {
		return catename;
	}
	public void setCatename(String catename) {
		this.catename = catename;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public String getCatelevel() {
		return catelevel;
	}
	public void setCatelevel(String catelevel) {
		this.catelevel = catelevel;
	}
	public String getCatecode() {
		return catecode;
	}
	public void setCatecode(String catecode) {
		this.catecode = catecode;
	}
	public String getFullcode() {
		return fullcode;
	}
	public void setFullcode(String fullcode) {
		this.fullcode = fullcode;
	}
	
}
