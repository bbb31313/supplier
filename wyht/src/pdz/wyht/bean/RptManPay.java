package pdz.wyht.bean;



/**
* <p>Title: RptManPay.java</p>
* <p>Description:收款员实收报表</p>
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company:步步高百货信息部</p>
* @author pdz
* @date 2013-12-27 下午02:07:43
* @version V1.0
*/
public class RptManPay {

	private String shop     ;     //所属门店
	private String paidmonth;      //所缴月份
	private String paydate  ;     //收款日期
	private String payid    ;     //收款单号
	private String payman   ;     //收款人姓名
	private String zlhtid   ;     //租赁合同号
	private String wyhtid   ;     //物业合同号
	private String wldmbm   ;      // 供应商编码
	private String wldmname ;      // 供应商名称
	private String paymode  ;      // 交款方式
	private Double wymoney  ;      // 物业管理费金额
	private Double ktmoney  ;      // 空调用费金额
	private Double znjmoney ;      // 滞纳金（不含减免金额）
	private Double summoney ;      // 金额合计
	
	
	
	
	
	public String getPaidmonth() {
		return paidmonth;
	}
	public void setPaidmonth(String paidmonth) {
		this.paidmonth = paidmonth;
	}
	public String getShop() {
		return shop;
	}
	public void setShop(String shop) {
		this.shop = shop;
	}
	public String getPaydate() {
		return paydate;
	}
	public void setPaydate(String paydate) {
		this.paydate = paydate;
	}
	public String getPayid() {
		return payid;
	}
	public void setPayid(String payid) {
		this.payid = payid;
	}
	public String getPayman() {
		return payman;
	}
	public void setPayman(String payman) {
		this.payman = payman;
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
	public Double getZnjmoney() {
		return znjmoney;
	}
	public void setZnjmoney(Double znjmoney) {
		this.znjmoney = znjmoney;
	}
	public Double getSummoney() {
		return summoney;
	}
	public void setSummoney(Double summoney) {
		this.summoney = summoney;
	}
	
}
