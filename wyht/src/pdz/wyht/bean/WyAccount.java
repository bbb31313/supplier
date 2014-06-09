package pdz.wyht.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="wy_account")
public class WyAccount {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="aid")
	private String aid;
	@Column(name="htid")
	private String htid;
	@Column(name="costype")
	private String costype;
	@Column(name="costname")
	private String costname;
	@Column(name="accountmonth")
	private String accountmonth;
	@Column(name="accountdate")
	private String accountdate;
	@Column(name="account")
	private Double account;
	@Column(name="costover")
	private String costover;
	@Column(name="accountznj")
	private Double accountznj     ;
	@Column(name="auditdate")
	private String auditdate     ;
	
	
	
	public String getAuditdate() {
		return auditdate;
	}
	public void setAuditdate(String auditdate) {
		this.auditdate = auditdate;
	}
	public Double getAccountznj() {
		return accountznj;
	}
	public void setAccountznj(Double accountznj) {
		this.accountznj = accountznj;
	}
	public String getCostover() {
		return costover;
	}
	public void setCostover(String costover) {
		this.costover = costover;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getHtid() {
		return htid;
	}
	public void setHtid(String htid) {
		this.htid = htid;
	}
	public String getCostype() {
		return costype;
	}
	public void setCostype(String costype) {
		this.costype = costype;
	}
	public String getCostname() {
		return costname;
	}
	public void setCostname(String costname) {
		this.costname = costname;
	}
	public String getAccountmonth() {
		return accountmonth;
	}
	public void setAccountmonth(String accountmonth) {
		this.accountmonth = accountmonth;
	}
	public String getAccountdate() {
		return accountdate;
	}
	public void setAccountdate(String accountdate) {
		this.accountdate = accountdate;
	}
	public Double getAccount() {
		return account;
	}
	public void setAccount(Double account) {
		this.account = account;
	}
}
