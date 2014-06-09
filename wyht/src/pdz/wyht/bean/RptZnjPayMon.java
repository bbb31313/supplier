package pdz.wyht.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
* <p>Title: RptZnjPayMon.java</p>
* <p>Description:滞纳金月度收入报表</p>
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company:pdz</p>
* @author pdz
* @date 2014-3-25 下午02:24:00
* @version V1.0
 */
@Entity
@Table(name="wy_znjpay_mon")
public class RptZnjPayMon {

	@Id
	@Column(name="HTID")	
	private String htid;              // 物业合同号
	@Column(name="PAIDMONTH")			
	private String paidmonth;         //  归属月份
	@Column(name="ZLCODE")		
	private String zlcode;            //  租赁合同号
	@Column(name="SHOPNAME")		
	private String shopname;          //  所属门店
	@Column(name="GHDWDM")		
	private String ghdwdm;            //  供应商编码
	@Column(name="WLDMNAME")		
	private String wldmname;          //  供应商名称
	@Column(name="WYZNJ")				
	private Double wyznj;             //  应缴物业滞纳金
	@Column(name="KTZNJ")				
	private Double ktznj;             //   应缴空调滞纳金
	@Column(name="SHWYZNJ")			
	private Double shwyznj;           //   实收物业滞纳金
	@Column(name="JMWYZNJ")				
	private Double jmwyznj;           //    减免物业滞纳金
	@Column(name="SHKTZNJ")		
	private Double shktznj;           //    实收空调滞纳金
	@Column(name="JMKTZNJ")	
	private Double jmktznj;           //    减免空调滞纳金
	@Column(name="SHOP")
	private String shop;              //    所属门店ID
	
	
	
	public String getShop() {
		return shop;
	}
	public void setShop(String shop) {
		this.shop = shop;
	}
	public String getHtid() {
		return htid;
	}
	public void setHtid(String htid) {
		this.htid = htid;
	}
	public String getPaidmonth() {
		return paidmonth;
	}
	public void setPaidmonth(String paidmonth) {
		this.paidmonth = paidmonth;
	}
	public String getZlcode() {
		return zlcode;
	}
	public void setZlcode(String zlcode) {
		this.zlcode = zlcode;
	}
	public String getShopname() {
		return shopname;
	}
	public void setShopname(String shopname) {
		this.shopname = shopname;
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
	public Double getShwyznj() {
		return shwyznj;
	}
	public void setShwyznj(Double shwyznj) {
		this.shwyznj = shwyznj;
	}
	public Double getJmwyznj() {
		return jmwyznj;
	}
	public void setJmwyznj(Double jmwyznj) {
		this.jmwyznj = jmwyznj;
	}
	public Double getShktznj() {
		return shktznj;
	}
	public void setShktznj(Double shktznj) {
		this.shktznj = shktznj;
	}
	public Double getJmktznj() {
		return jmktznj;
	}
	public void setJmktznj(Double jmktznj) {
		this.jmktznj = jmktznj;
	}
	
	
}
