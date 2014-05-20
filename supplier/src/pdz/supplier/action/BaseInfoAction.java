package pdz.supplier.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import pdz.supplier.bean.AjaxResult;
import pdz.supplier.bean.SupplierDoc;
import pdz.supplier.bean.SupplierTool;
import pdz.supplier.bean.SupplierUser;
import pdz.supplier.grid.GridData;
import pdz.supplier.service.SupBaseInfoService;
import pdz.supplier.until.DbContextExtension;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller @Scope("prototype")
public class BaseInfoAction extends ActionSupport {
	
	@Resource SupBaseInfoService supBaseInfoService;
	
	private String supID;
	private String supname;
	private String supzcdz;
	private String supzcnf;
	private String supzczj;
	private String supcategory;
	private String supcategoryID;
	private String business_address;
	private String representative;
	private String office_phone;
	private String contactman;
	private String contactphone;
	private String email;
	private String zip_code;
	private AjaxResult ajaxResult;
	private GridData supdocList;
	private String companyType;
	
	
	

	

	public String getCompanyType() {
		return companyType;
	}
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	public GridData getSupdocList() {
		return supdocList;
	}
	public void setSupdocList(GridData supdocList) {
		this.supdocList = supdocList;
	}
	
	public AjaxResult getAjaxResult() {
		return ajaxResult;
	}
	public void setAjaxResult(AjaxResult ajaxResult) {
		this.ajaxResult = ajaxResult;
	}
	public String getSupID() {
		return supID;
	}
	public void setSupID(String supID) {
		this.supID = supID;
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
	public String getBusiness_address() {
		return business_address;
	}
	public void setBusiness_address(String businessAddress) {
		business_address = businessAddress;
	}
	public String getRepresentative() {
		return representative;
	}
	public void setRepresentative(String representative) {
		this.representative = representative;
	}
	public String getOffice_phone() {
		return office_phone;
	}
	public void setOffice_phone(String officePhone) {
		office_phone = officePhone;
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
	
	
	public String addSupDoc(){
		AjaxResult aResult=new AjaxResult();
		if (supBaseInfoService.isSupNameDouble(supname)) {
			aResult.setError(true);
			aResult.setMessage("供应商名称重复,请重新录入!");
		}else{
			SupplierDoc doc=assemblySupDoc(1);
			
			if(supBaseInfoService.saveSupDoc(doc))
			{
				
				aResult.setError(false);
				aResult.setMessage("新增供应商成功!");
			}else {
				
				aResult.setError(true);
				aResult.setMessage("新增供应商失败!");
			}	
		}
		
		ajaxResult=aResult;
		return "adddoc";
	}
	
	public String getSupDoc(){
		SupplierDoc supdoc=supBaseInfoService.getDocById(supID);
		AjaxResult aResult=new AjaxResult();
		aResult.setError(false);
		aResult.setData(supdoc);
		aResult.setMessage("供应商加载成功!");
		ajaxResult=aResult;
		return "getdoc";
	}
	
	public String updateSupDoc(){
		SupplierDoc doc=assemblySupDoc(2);
		AjaxResult aResult=new AjaxResult();
		if(supBaseInfoService.updateSupDoc(doc))
		{
			aResult.setError(false);
			aResult.setMessage("更新供应商成功!");
		}else {
			aResult.setError(true);
			aResult.setMessage("更新供应商失败!");
		}
		ajaxResult=aResult;
		return "updatedoc";
	}
	
	public String delSupDoc(){
		SupplierDoc doc=new SupplierDoc();
		doc.setSupID(supID);
		AjaxResult aResult=new AjaxResult();
		if(supBaseInfoService.delSupDoc(doc))
		{
			aResult.setError(false);
			aResult.setMessage("删除供应商成功!");
		}else {
			aResult.setError(true);
			aResult.setMessage("删除供应商失败!");
		}
		ajaxResult=aResult;
		return "deldoc";
	}


	private SupplierDoc assemblySupDoc(int flag){
		SupplierUser suser=(SupplierUser)ActionContext.getContext().getSession().get("supuser");
		SupplierDoc doc;
		if (flag==1) {
			doc=new SupplierDoc();
			doc.setBusiness_address(business_address);
			doc.setContactman(contactman);
			doc.setContactphone(contactphone);
			doc.setEmail(email);
			doc.setOffice_phone(office_phone);
			doc.setRepresentative(representative);
			doc.setSupcategory(supcategory);
			doc.setSupcategoryID(supcategoryID);
			doc.setSupLEVEL("待审核");
			doc.setSupLEVEL("1");
			doc.setSupname(supname);
			doc.setSupzcdz(supzcdz);
			doc.setSupzcnf(supzcnf);
			doc.setSupzczj(supzczj);
			doc.setZip_code(zip_code);	
			doc.setCompanyType(companyType);
		}else{
			doc=supBaseInfoService.getDocById(supID);
			if(business_address!=null){
				doc.setBusiness_address(business_address);	
			}
			if(contactman!=null){
				doc.setContactman(contactman);	
			}
			if(contactphone!=null){
				doc.setContactphone(contactphone);	
			}
			if (email!=null) {
				doc.setEmail(email);	
			}
			
			if(office_phone!=null){
				doc.setOffice_phone(office_phone);	
			}
			if(representative!=null){
				doc.setRepresentative(representative);	
			}
			if (supcategory!=null) {
				doc.setSupcategory(supcategory);	
			}
			if(supcategoryID!=null){
				doc.setSupcategoryID(supcategoryID);	
			}
			if(supname!=null){
				doc.setSupname(supname);	
			}
			if(supzcdz!=null){
				doc.setSupzcdz(supzcdz);	
			}
			if(supzcnf!=null){
				doc.setSupzcnf(supzcnf);	
			}
			if (supzczj!=null) {
				doc.setSupzczj(supzczj);	
			}
			if(zip_code!=null){
				doc.setZip_code(zip_code);	
			}
				
		}
		
		return doc;
	}
}
