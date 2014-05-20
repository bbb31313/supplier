package pdz.supplier.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SUPPLIER_TOOL")
public class SupplierTool {

	@Id
	@Column(name="BTNID")
	private Long btnId	;
	@Column(name="BTNNAME")
	private String btnName;
	@Column(name="BTNICON")
	private String btnIcon;
	@Column(name="BTNNO")
	private String btnNo;
	@Column(name="MENUNO")
	private String menuNo;
	@Column(name="ISVIEW")
	private Long isview;
	@Column(name="EXECUTION")
	private String execution;
	
	
	public Long getBtnId() {
		return btnId;
	}
	public void setBtnId(Long btnId) {
		this.btnId = btnId;
	}
	public String getBtnName() {
		return btnName;
	}
	public void setBtnName(String btnName) {
		this.btnName = btnName;
	}
	public String getBtnIcon() {
		return btnIcon;
	}
	public void setBtnIcon(String btnIcon) {
		this.btnIcon = btnIcon;
	}
	public String getBtnNo() {
		return btnNo;
	}
	public void setBtnNo(String btnNo) {
		this.btnNo = btnNo;
	}
	public String getMenuNo() {
		return menuNo;
	}
	public void setMenuNo(String menuNo) {
		this.menuNo = menuNo;
	}
	public Long getIsview() {
		return isview;
	}
	public void setIsview(Long isview) {
		this.isview = isview;
	}
	public String getExecution() {
		return execution;
	}
	public void setExecution(String execution) {
		this.execution = execution;
	}
	
	
}
