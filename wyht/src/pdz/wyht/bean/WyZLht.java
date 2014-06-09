package pdz.wyht.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.struts2.json.annotations.JSON;

@Entity
@Table(name="wy_zlht")
public class WyZLht {


	@Id
	@Column(name="HTH")
	private Long hth;
	@Column(name="STATUS")
	private Long stauts;
	@Column(name="GHDWDM")
	private String ghdwdm;
	@Column(name="MCID")
	private String mcid;
	@Column(name="WLDMNAME")
	private String wldmname;
	@Column(name="ADDRESS")
	private String address;
	@Column(name="LXRMC")
	private String lxrmc;
	@Column(name="PHONE_NUMBER")
	private String phone;
	@Column(name="MCMC")
	private String mcmc;
	@Column(name="OLD_MCID")
	private String old_mcid;
	@Column(name="HTYXQ_START")
	private String startDate;
	@Column(name="HTYXQ_END")
	private String endDate;
	@Column(name="JZMJ")
	private Long jzmj;
	
	
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Long getJzmj() {
		return jzmj;
	}
	public void setJzmj(Long jzmj) {
		this.jzmj = jzmj;
	}
	public Long getHth() {
		return hth;
	}
	public void setHth(Long hth) {
		this.hth = hth;
	}
	public Long getStauts() {
		return stauts;
	}
	public void setStauts(Long stauts) {
		this.stauts = stauts;
	}
	public String getGhdwdm() {
		return ghdwdm;
	}
	public void setGhdwdm(String ghdwdm) {
		this.ghdwdm = ghdwdm;
	}
	public String getMcid() {
		return mcid;
	}
	public void setMcid(String mcid) {
		this.mcid = mcid;
	}
	public String getWldmname() {
		return wldmname;
	}
	public void setWldmname(String wldmname) {
		this.wldmname = wldmname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLxrmc() {
		return lxrmc;
	}
	public void setLxrmc(String lxrmc) {
		this.lxrmc = lxrmc;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMcmc() {
		return mcmc;
	}
	public void setMcmc(String mcmc) {
		this.mcmc = mcmc;
	}
	public String getOld_mcid() {
		return old_mcid;
	}
	public void setOld_mcid(String oldMcid) {
		old_mcid = oldMcid;
	}
	
	
}
