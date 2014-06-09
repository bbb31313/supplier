package pdz.wyht.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="smart_tool")
public class SmartTool {

	@Id
	@Column(name="BTNID")
	private Long BtnId	;
	@Column(name="BTNNAME")
	private String BtnName;
	@Column(name="BTNICON")
	private String BtnIcon;
	@Column(name="BTNNO")
	private String BtnNo;
	@Column(name="MENUNO")
	private String MenuNo;
	@Column(name="ISVIEW")
	private Long isview;
	@Column(name="EXECUTION")
	private String execution;
	
	
	
	public Long getBtnId() {
		return BtnId;
	}
	public void setBtnId(Long btnId) {
		BtnId = btnId;
	}
	public String getBtnName() {
		return BtnName;
	}
	public void setBtnName(String btnName) {
		BtnName = btnName;
	}
	public String getBtnIcon() {
		return BtnIcon;
	}
	public void setBtnIcon(String btnIcon) {
		BtnIcon = btnIcon;
	}
	public String getBtnNo() {
		return BtnNo;
	}
	public void setBtnNo(String btnNo) {
		BtnNo = btnNo;
	}
	public String getMenuNo() {
		return MenuNo;
	}
	public void setMenuNo(String menuNo) {
		MenuNo = menuNo;
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
