package pdz.wyht.bean;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BiShop entity. @author MyEclipse Persistence Tools
 */

@Entity
@Table(name="bi_shop")
public class BiShop implements java.io.Serializable {


	@Id
	@Column(name="SHOPID")
	private String shopid;
	@Column(name="SMSSHOPNAME")
	private String shopname;
	
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
	
	


}