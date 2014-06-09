package pdz.wyht.bean;


/**
* <p>Title: RptOverPay.java</p>
* <p>Description:供应商费用欠交明细表</p>
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company:步步高百货信息部</p>
* @author pdz
* @date 2013-12-28 上午10:56:37
* @version V1.0
*/
public class RptOverPay {
	
	private String shop     ;     //所属门店
	private String zlhtid   ;     //租赁合同号
	private String wyhtid   ;     //物业合同号
	private String wldmbm   ;      // 供应商编码
	private String wldmname ;      // 供应商名称
	private String paymode  ;      // 交纳方式
	private String payabledate;    //合同应交日期
	private String accountmonth;   //合同应缴月份
	private Double wymoney  ;      // 物业管理费金额
	private Double ktmoney  ;      // 空调用费金额
	private String overdate ;      // 逾期天数
	private Double wyznjmoney ;      // 物业滞纳金
	private Double ktznjmoney;     // 空调滞纳金
	private Double summoney;     // 空调滞纳金
	
	
	
	
	public String getAccountmonth() {
		return accountmonth;
	}
	public void setAccountmonth(String accountmonth) {
		this.accountmonth = accountmonth;
	}
	public Double getSummoney() {
		return summoney;
	}
	public void setSummoney(Double summoney) {
		this.summoney = summoney;
	}
	public String getShop() {
		return shop;
	}
	public void setShop(String shop) {
		this.shop = shop;
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
	public String getPayabledate() {
		return payabledate;
	}
	public void setPayabledate(String payabledate) {
		this.payabledate = payabledate;
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
	public String getOverdate() {
		return overdate;
	}
	public void setOverdate(String overdate) {
		this.overdate = overdate;
	}
	public Double getWyznjmoney() {
		return wyznjmoney;
	}
	public void setWyznjmoney(Double wyznjmoney) {
		this.wyznjmoney = wyznjmoney;
	}
	public Double getKtznjmoney() {
		return ktznjmoney;
	}
	public void setKtznjmoney(Double ktznjmoney) {
		this.ktznjmoney = ktznjmoney;
	}

	
	

}
