package pdz.supplier.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Supplier_Level")
public class SupplierLevel {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="levelID")
	private String levelid;
	@Column(name="levelName")
	private String levelName;
	@Column(name="levelType")
	private Long levelType;
	
	
	public String getLevelid() {
		return levelid;
	}
	public void setLevelid(String levelid) {
		this.levelid = levelid;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	public Long getLevelType() {
		return levelType;
	}
	public void setLevelType(Long levelType) {
		this.levelType = levelType;
	}
	
	
}
