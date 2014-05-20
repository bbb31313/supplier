package pdz.supplier.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Supplier_DOC")
public class SupplierDoc {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="supID")
	private String supID                       ;       //系统编码
	@Column(name="depid")
	private Long depid                         ;       //部门ID                            
	@Column(name="depname")
	private String depname                     ;       //部门名称          
	@Column(name="suggestman")
	private String suggestman                  ;       //推荐人            
	@Column(name="supname")
	private String supname                     ;       //公司名称          
	@Column(name="supzcdz")
	private String supzcdz                     ;       //注册地址          
	@Column(name="supzcnf")
	private String supzcnf                     ;       //注册年份          
	@Column(name="supzczj")
	private String supzczj                     ;       //注册资金          
	@Column(name="business_address")
	private String business_address            ;       //营业地址          
	@Column(name="businessLicense")
	private String businessLicense             ;       //营业执照          
	@Column(name="businessLicensetime")
	private String businessLicensetime         ;       //营业执照有效期    
	@Column(name="orgcode")
	private String orgcode                     ;       //组织机构代码      
	@Column(name="orgcodetime")
	private String orgcodetime                 ;       //组织机构有效期    
	@Column(name="buildtime")
	private String buildtime                   ;       //公司成立时间      
	@Column(name="businessrange")
	private String businessrange               ;       //公司经营范围      
	@Column(name="successcase")
	private String successcase                 ;       //与外单位合作项目  
	@Column(name="office_phone")
	private String office_phone                ;       //办公电话号码      
	@Column(name="representative")
	private String representative              ;       //法定代表人        
	@Column(name="contactman")
	private String contactman                  ;       //联系人            
	@Column(name="contactphone")
	private String contactphone                ;       //联系人电话号码    
	@Column(name="email")
	private String email                       ;       //EMALL             
	@Column(name="zip_code")
	private String zip_code                    ;       //邮政编码          
	@Column(name="companyType")
	private String companyType                 ;       //公司类型          
	@Column(name="supcategory")
	private String supcategory                 ;       //类别              
	@Column(name="supcategoryID")
	private String supcategoryID               ;       //类别ID            
	@Column(name="supLEVEL")
	private String supLEVEL                    ;       //级别              
	@Column(name="supLEVELID")
	private String supLEVELID                  ;       //级别ID            
	@Column(name="isguaka")
	private String isguaka                     ;       //是否挂靠          
	@Column(name="guakaman")
	private String guakaman                    ;       //挂靠人评估        
	@Column(name="remark")
	private String remark                      ;       //备注              
	
	
	public String getSupID() {
		return supID;
	}
	public void setSupID(String supID) {
		this.supID = supID;
	}
	public Long getDepid() {
		return depid;
	}
	public void setDepid(Long depid) {
		this.depid = depid;
	}
	public String getDepname() {
		return depname;
	}
	public void setDepname(String depname) {
		this.depname = depname;
	}
	public String getSuggestman() {
		return suggestman;
	}
	public void setSuggestman(String suggestman) {
		this.suggestman = suggestman;
	}
	public String getSupname() {
		return supname;
	}
	public void setSupname(String supname) {
		this.supname = supname;
	}
	public String getSupzcdz() {
		return supzcdz;
	}
	public void setSupzcdz(String supzcdz) {
		this.supzcdz = supzcdz;
	}
	public String getSupzcnf() {
		return supzcnf;
	}
	public void setSupzcnf(String supzcnf) {
		this.supzcnf = supzcnf;
	}
	public String getSupzczj() {
		return supzczj;
	}
	public void setSupzczj(String supzczj) {
		this.supzczj = supzczj;
	}
	public String getBusiness_address() {
		return business_address;
	}
	public void setBusiness_address(String businessAddress) {
		business_address = businessAddress;
	}
	public String getBusinessLicense() {
		return businessLicense;
	}
	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}
	public String getBusinessLicensetime() {
		return businessLicensetime;
	}
	public void setBusinessLicensetime(String businessLicensetime) {
		this.businessLicensetime = businessLicensetime;
	}
	public String getOrgcode() {
		return orgcode;
	}
	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}
	public String getOrgcodetime() {
		return orgcodetime;
	}
	public void setOrgcodetime(String orgcodetime) {
		this.orgcodetime = orgcodetime;
	}
	public String getBuildtime() {
		return buildtime;
	}
	public void setBuildtime(String buildtime) {
		this.buildtime = buildtime;
	}
	public String getBusinessrange() {
		return businessrange;
	}
	public void setBusinessrange(String businessrange) {
		this.businessrange = businessrange;
	}
	public String getSuccesscase() {
		return successcase;
	}
	public void setSuccesscase(String successcase) {
		this.successcase = successcase;
	}
	public String getOffice_phone() {
		return office_phone;
	}
	public void setOffice_phone(String officePhone) {
		office_phone = officePhone;
	}
	public String getRepresentative() {
		return representative;
	}
	public void setRepresentative(String representative) {
		this.representative = representative;
	}
	public String getContactman() {
		return contactman;
	}
	public void setContactman(String contactman) {
		this.contactman = contactman;
	}
	public String getContactphone() {
		return contactphone;
	}
	public void setContactphone(String contactphone) {
		this.contactphone = contactphone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getZip_code() {
		return zip_code;
	}
	public void setZip_code(String zipCode) {
		zip_code = zipCode;
	}
	public String getCompanyType() {
		return companyType;
	}
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	public String getSupcategory() {
		return supcategory;
	}
	public void setSupcategory(String supcategory) {
		this.supcategory = supcategory;
	}
	public String getSupcategoryID() {
		return supcategoryID;
	}
	public void setSupcategoryID(String supcategoryID) {
		this.supcategoryID = supcategoryID;
	}
	public String getSupLEVEL() {
		return supLEVEL;
	}
	public void setSupLEVEL(String supLEVEL) {
		this.supLEVEL = supLEVEL;
	}
	public String getSupLEVELID() {
		return supLEVELID;
	}
	public void setSupLEVELID(String supLEVELID) {
		this.supLEVELID = supLEVELID;
	}
	public String getIsguaka() {
		return isguaka;
	}
	public void setIsguaka(String isguaka) {
		this.isguaka = isguaka;
	}
	public String getGuakaman() {
		return guakaman;
	}
	public void setGuakaman(String guakaman) {
		this.guakaman = guakaman;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	                                                  
	
}
