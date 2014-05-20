package pdz.supplier.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Supplier_License")
public class SupplierLicense {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="lid")
	private String lid;
	@Column(name="supID")
	private String supID;
	@Column(name="supName")
	private String supName;
	@Column(name="licenseTypeID")
	private String licenseTypeID;
	@Column(name="licenseType")
	private String licenseType;
	@Column(name="licenseName")
	private String licenseName;
	@Column(name="licenseImage")
	private String licenseImage;
	
	
	
	public String getSupName() {
		return supName;
	}
	public void setSupName(String supName) {
		this.supName = supName;
	}
	public String getLicenseTypeID() {
		return licenseTypeID;
	}
	public void setLicenseTypeID(String licenseTypeID) {
		this.licenseTypeID = licenseTypeID;
	}
	public String getLicenseType() {
		return licenseType;
	}
	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}
	public String getLid() {
		return lid;
	}
	public void setLid(String lid) {
		this.lid = lid;
	}
	public String getSupID() {
		return supID;
	}
	public void setSupID(String supID) {
		this.supID = supID;
	}
	public String getLicenseName() {
		return licenseName;
	}
	public void setLicenseName(String licenseName) {
		this.licenseName = licenseName;
	}
	public String getLicenseImage() {
		return licenseImage;
	}
	public void setLicenseImage(String licenseImage) {
		this.licenseImage = licenseImage;
	}
	
}
