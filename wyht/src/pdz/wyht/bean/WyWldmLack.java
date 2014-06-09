package pdz.wyht.bean;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



/**
* <p>Title: WyWldmLack.java</p>
* <p>Description:供应商欠款余额表</p>
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company:pdz</p>
* @author pdz
* @date 2014-3-31 上午10:53:54
* @version V1.0
*/

@Entity
@Table(name="wy_wldm_qianyu")
public class WyWldmLack implements java.io.Serializable {


	@Id

	@Column(name="SHOPNAME")
	private String shopname;        //门店名称
	@Column(name="SHOPID")
	private String shopid;          //门店ID
	@Column(name="ZLCODE")
	private String  zlcode       ;  //租赁合同号
	@Column(name="HTID")
	private String  htid         ;  //物业合同号
	@Column(name="GHDWDM")
	private String  ghdwdm       ;  //供应商编码
	@Column(name="WLDAMNAME")
	private String  wldmname     ;  //供应商名称
	@Column(name="JZMJ")
	private String  jzmj         ;  //建筑面积
	@Column(name="STARTDATE")
	private String  startdate    ;  //合同开始日期
	@Column(name="ENDDATE")
	private String  enddate      ;  //合同结束日期
	@Column(name="ACCOUNTMONTH")
	private String  accountmonth ;  //应收月份
	@Column(name="WYMONEY")
	private Double  wymoney      ;  //应收物业费
	@Column(name="KTMONEY")
	private Double  ktmoney      ;  //应收空调费
	@Column(name="WYZNJ")
	private Double  wyznj        ;  //应收物业滞纳金
	@Column(name="KTZNJ")
	private Double  ktznj        ;  //应收空调滞纳金
	@Column(name="ACCOUNTSUM")
	private Double  accountsum   ;  //应收合计
	@Column(name="WYPAID")
	private Double  wypaid       ;            //已收物业费
	@Column(name="KTPAID")
	private Double  ktpaid       ;            //已收空调费
	@Column(name="WYZNJPAID")
	private Double  wyznjpaid    ;            //已收物业滞纳金
	@Column(name="KTZNJPAID")
	private Double  ktznjpaid    ;            //已收空调滞纳金
	@Column(name="PAIDSUM")
	private Double  paidsum      ;            //已收合计
	@Column(name="WYLACK")
	private Double  wylack       ;            //欠缴物业费
	@Column(name="KTLACK")
	private Double  ktlack       ;            //欠缴空调费
	@Column(name="WYZNJLACK")
	private Double  wyznjlack    ;            //欠缴物业滞纳金
	@Column(name="KTZNJLACK")
	private Double  ktznjlack    ;            //欠缴空调滞纳金
	@Column(name="LACKSUM")
	private Double  lacksum      ;            //欠缴合计
	
	
	
	
	public String getJzmj() {
		return jzmj;
	}
	public void setJzmj(String jzmj) {
		this.jzmj = jzmj;
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
	public String getZlcode() {
		return zlcode;
	}
	public void setZlcode(String zlcode) {
		this.zlcode = zlcode;
	}
	public String getHtid() {
		return htid;
	}
	public void setHtid(String htid) {
		this.htid = htid;
	}
	public String getGhdwdm() {
		return ghdwdm;
	}
	public void setGhdwdm(String ghdwdm) {
		this.ghdwdm = ghdwdm;
	}
	public String getWldmname() {
		return wldmname;
	}
	public void setWldmname(String wldmname) {
		this.wldmname = wldmname;
	}

	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getAccountmonth() {
		return accountmonth;
	}
	public void setAccountmonth(String accountmonth) {
		this.accountmonth = accountmonth;
	}
	public Double getWymoney() {
		return wymoney;
	}
	public void setWymoney(Double wymoney) {
		this.wymoney = wymoney;
	}
	public Double getKtmoney() {
		return ktmoney;
	}
	public void setKtmoney(Double ktmoney) {
		this.ktmoney = ktmoney;
	}
	public Double getWyznj() {
		return wyznj;
	}
	public void setWyznj(Double wyznj) {
		this.wyznj = wyznj;
	}
	public Double getKtznj() {
		return ktznj;
	}
	public void setKtznj(Double ktznj) {
		this.ktznj = ktznj;
	}
	public Double getAccountsum() {
		return accountsum;
	}
	public void setAccountsum(Double accountsum) {
		this.accountsum = accountsum;
	}
	public Double getWypaid() {
		return wypaid;
	}
	public void setWypaid(Double wypaid) {
		this.wypaid = wypaid;
	}
	public Double getKtpaid() {
		return ktpaid;
	}
	public void setKtpaid(Double ktpaid) {
		this.ktpaid = ktpaid;
	}
	public Double getWyznjpaid() {
		return wyznjpaid;
	}
	public void setWyznjpaid(Double wyznjpaid) {
		this.wyznjpaid = wyznjpaid;
	}
	public Double getKtznjpaid() {
		return ktznjpaid;
	}
	public void setKtznjpaid(Double ktznjpaid) {
		this.ktznjpaid = ktznjpaid;
	}
	public Double getPaidsum() {
		return paidsum;
	}
	public void setPaidsum(Double paidsum) {
		this.paidsum = paidsum;
	}
	public Double getWylack() {
		return wylack;
	}
	public void setWylack(Double wylack) {
		this.wylack = wylack;
	}
	public Double getKtlack() {
		return ktlack;
	}
	public void setKtlack(Double ktlack) {
		this.ktlack = ktlack;
	}
	public Double getWyznjlack() {
		return wyznjlack;
	}
	public void setWyznjlack(Double wyznjlack) {
		this.wyznjlack = wyznjlack;
	}
	public Double getKtznjlack() {
		return ktznjlack;
	}
	public void setKtznjlack(Double ktznjlack) {
		this.ktznjlack = ktznjlack;
	}
	public Double getLacksum() {
		return lacksum;
	}
	public void setLacksum(Double lacksum) {
		this.lacksum = lacksum;
	}
	


}