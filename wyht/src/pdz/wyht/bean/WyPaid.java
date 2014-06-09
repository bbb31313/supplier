package pdz.wyht.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="wy_paid")
public class WyPaid {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="pid")
	private String pid            ;
	@Column(name="htid")
	private String htid           ;
	@Column(name="costype")
	private String costype        ;
	@Column(name="costname")
	private String costname       ;
	@Column(name="paidmonth")
	private String paidmonth      ;
	@Column(name="paiddate")
	private String paiddate       ;
	@Column(name="paid")
	private Double paid           ;
	@Column(name="paidznj")
	private Double paidznj        ;
	@Column(name="znjreduce")
	private String znjreduce      ;
	@Column(name="entryuserid")
	private String entryuserid    ;
	@Column(name="entrydate")
	private String entrydate      ;
	@Column(name="PAYMENT")
	private String payment;
	@Column(name="INVOICE")
	private String invoice;
	@Column(name="ENTRYUSER")
	private String enteruser;
	
	
	
	
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getInvoice() {
		return invoice;
	}
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}
	public String getEnteruser() {
		return enteruser;
	}
	public void setEnteruser(String enteruser) {
		this.enteruser = enteruser;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
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
	public String getPaidmonth() {
		return paidmonth;
	}
	public void setPaidmonth(String paidmonth) {
		this.paidmonth = paidmonth;
	}
	public String getPaiddate() {
		return paiddate;
	}
	public void setPaiddate(String paiddate) {
		this.paiddate = paiddate;
	}
	public Double getPaid() {
		return paid;
	}
	public void setPaid(Double paid) {
		this.paid = paid;
	}

	public Double getPaidznj() {
		return paidznj;
	}
	public void setPaidznj(Double paidznj) {
		this.paidznj = paidznj;
	}
	public String getZnjreduce() {
		return znjreduce;
	}
	public void setZnjreduce(String znjreduce) {
		this.znjreduce = znjreduce;
	}
	public String getEntryuserid() {
		return entryuserid;
	}
	public void setEntryuserid(String entryuserid) {
		this.entryuserid = entryuserid;
	}
	public String getEntrydate() {
		return entrydate;
	}
	public void setEntrydate(String entrydate) {
		this.entrydate = entrydate;
	}
	
	

}
