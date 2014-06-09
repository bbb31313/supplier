package pdz.wyht.bean;


/**
* <p>Title: RptMonPay.java</p>
* <p>Description:归属月度收入报表</p>
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company:步步高百货信息部</p>
* @author pdz
* @date 2013-12-28 上午10:56:20
* @version V1.0
*/
public class RptMonPay {
	
	private String shop     ;     //所属门店
	private String month    ;     //归属月份
	private String zlhtid   ;     //租赁合同号
	private String wyhtid   ;     //物业合同号
	private String wldmbm   ;      // 供应商编码
	private String wldmname ;      // 供应商名称
	private String paymode  ;      // 交纳方式
	private String jzmj     ;      //建筑面积
	private  String ispay ;    //是否缴纳
	private Double wymoney  ;      // 物业管理费金额
	private Double ktmoney  ;      // 空调用费金额
	private Double summoney ;      //合计金额
	
	
	
	public String getIspay() {
		return ispay;
	}
	public void setIspay(String ispay) {
		this.ispay = ispay;
	}
	public String getShop() {
		return shop;
	}
	public void setShop(String shop) {
		this.shop = shop;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getZlhtid() {
		return zlhtid;
	}
	public void setZlhtid(String zlhtid) {
		this.zlhtid = zlhtid;
	}
	public String getWyhtid() {
		return wyhtid;
	}
	public void setWyhtid(String wyhtid) {
		this.wyhtid = wyhtid;
	}
	public String getWldmbm() {
		return wldmbm;
	}
	public void setWldmbm(String wldmbm) {
		this.wldmbm = wldmbm;
	}
	public String getWldmname() {
		return wldmname;
	}
	public void setWldmname(String wldmname) {
		this.wldmname = wldmname;
	}
	public String getPaymode() {
		return paymode;
	}
	public void setPaymode(String paymode) {
		this.paymode = paymode;
	}
	public String getJzmj() {
		return jzmj;
	}
	public void setJzmj(String jzmj) {
		this.jzmj = jzmj;
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
	public Double getSummoney() {
		return summoney;
	}
	public void setSummoney(Double summoney) {
		this.summoney = summoney;
	}
	
	
	

}
