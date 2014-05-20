package pdz.supplier.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Supplier_Dep")
public class SupplierDep {
	
	@Id
	@Column(name="DepID")
	private Long depID;
	@Column(name="depName")
	private String depName;
	
	
	public Long getDepID() {
		return depID;
	}
	public void setDepID(Long depID) {
		this.depID = depID;
	}
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	
	
	

}
