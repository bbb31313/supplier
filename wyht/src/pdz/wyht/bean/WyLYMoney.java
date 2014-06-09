package pdz.wyht.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="wy_lymoney")
public class WyLYMoney {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="LYid")
	private String lyid;
	@Column(name="wycode")
	private String wycode;
	@Column(name="yiname")
	private String yiname;
	@Column(name="res")
	private String res;
	@Column(name="money")
	private Long money;
	@Column(name="htid")
	private String htid;
	@Column(name="payuserid")
	private String payuserid;
	@Column(name="payuser")
	private String payuser;
	@Column(name="entryuserid")
	private String entryuserid;
	@Column(name="entrydate")
	private String entrydate;
	
	
	public String getLyid() {
		return lyid;
	}
	public void setLyid(String lyid) {
		this.lyid = lyid;
	}

	public String getWycode() {
		return wycode;
	}
	public void setWycode(String wycode) {
		this.wycode = wycode;
	}
	public String getYiname() {
		return yiname;
	}
	public void setYiname(String yiname) {
		this.yiname = yiname;
	}
	public String getRes() {
		return res;
	}
	public void setRes(String res) {
		this.res = res;
	}
	public Long getMoney() {
		return money;
	}
	public void setMoney(Long money) {
		this.money = money;
	}
	public String getHtid() {
		return htid;
	}
	public void setHtid(String htid) {
		this.htid = htid;
	}
	public String getPayuserid() {
		return payuserid;
	}
	public void setPayuserid(String payuserid) {
		this.payuserid = payuserid;
	}
	public String getPayuser() {
		return payuser;
	}
	public void setPayuser(String payuser) {
		this.payuser = payuser;
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
