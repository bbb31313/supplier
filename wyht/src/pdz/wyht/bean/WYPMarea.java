package pdz.wyht.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="wy_pm_area")
public class WYPMarea {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="wpaid")
	private String  wpaid         ;
	@Column(name="shopid")
	private String  shopid        ;
	@Column(name="shopname")
	private String  shopname      ;
	@Column(name="floor")
	private Long    floor         ;
	@Column(name="businessArea")
	private Double   businessArea  ;
	@Column(name="norentArea")
	private Double   norentArea    ;
	@Column(name="notrentArea")
	private Double   notrentArea   ;
	@Column(name="allArea")
	private Double   allArea       ;
	@Column(name="scale")
	private Double   scale         ;
	
	
	public String getWpaid() {
		return wpaid;
	}
	public void setWpaid(String wpaid) {
		this.wpaid = wpaid;
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
	public Long getFloor() {
		return floor;
	}
	public void setFloor(Long floor) {
		this.floor = floor;
	}
	public Double getBusinessArea() {
		return businessArea;
	}
	public void setBusinessArea(Double businessArea) {
		this.businessArea = businessArea;
	}
	public Double getNorentArea() {
		return norentArea;
	}
	public void setNorentArea(Double norentArea) {
		this.norentArea = norentArea;
	}
	public Double getNotrentArea() {
		return notrentArea;
	}
	public void setNotrentArea(Double notrentArea) {
		this.notrentArea = notrentArea;
	}
	public Double getAllArea() {
		return allArea;
	}
	public void setAllArea(Double allArea) {
		this.allArea = allArea;
	}
	public Double getScale() {
		return scale;
	}
	public void setScale(Double scale) {
		this.scale = scale;
	}
	
	
}
