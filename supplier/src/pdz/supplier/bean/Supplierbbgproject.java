package pdz.supplier.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="supplier_bbgproject")
public class Supplierbbgproject {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="spid")
	private String  spid         ;                  //系统编码
	@Column(name="supid")
	private String  supid        ;                  //供应商ID
	@Column(name="supname")
	private String  supname      ;                  //供应商名称
	@Column(name="shopid")
	private String  shopid       ;                  //合作门店ID
	@Column(name="shopname")
	private String  shopname     ;                  //合作门店名称
	@Column(name="projectname")
	private String  projectname  ;                  //项目名称
	@Column(name="evalproject")
	private String  evalproject  ;                  //项目评估
	@Column(name="evalid")
	private String  evalid       ;                  //评估人ID
	@Column(name="evalname")
	private String  evalname     ;                  //评估人名称
	@Column(name="evallevel")
	private Long    evallevel      ;                //评估结果(5:优秀；4：好；3：合格；2：差；1：很差)
	
	
	
	public String getSpid() {
		return spid;
	}
	public void setSpid(String spid) {
		this.spid = spid;
	}
	public String getSupid() {
		return supid;
	}
	public void setSupid(String supid) {
		this.supid = supid;
	}
	public String getSupname() {
		return supname;
	}
	public void setSupname(String supname) {
		this.supname = supname;
	}
	public String getShopid() {
		return shopid;
	}
	public void setShopid(String shopid) {
		this.shopid = shopid;
	}
	public String getShopname() {
		return shopname;
	}
	public void setShopname(String shopname) {
		this.shopname = shopname;
	}
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	public String getEvalproject() {
		return evalproject;
	}
	public void setEvalproject(String evalproject) {
		this.evalproject = evalproject;
	}
	public String getEvalid() {
		return evalid;
	}
	public void setEvalid(String evalid) {
		this.evalid = evalid;
	}
	public String getEvalname() {
		return evalname;
	}
	public void setEvalname(String evalname) {
		this.evalname = evalname;
	}
	public Long getEvallevel() {
		return evallevel;
	}
	public void setEvallevel(Long evallevel) {
		this.evallevel = evallevel;
	}
	
		
}
