package pdz.wyht.bean;



/**
* <p>Title: RptZnjPay.java</p>
* <p>Description:滞纳金收取报表</p>
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company:步步高百货信息部</p>
* @author pdz
* @date 2013-12-28 下午04:52:45
* @version V1.0
*/
public class RptZnjPay {

	    private String shop     ;        //所属门店
		private String zlhtid   ;        //租赁合同号
		private String wyhtid   ;        //物业合同号
		private String wldmbm   ;        // 供应商编码
		private String wldmname ;        // 供应商名称
		private String paymode  ;        // 交纳方式
		private String payabledate;      //合同应交日期
		private String paiddate ;        //实际交纳日期
		private Double wymoney  ;        // 物业管理费金额
		private Double ktmoney  ;        // 空调用费金额
		private String overdate ;        // 逾期天数
		private Double wyznjmoney ;      // 物业滞纳金
		private Double wyznjjm;          // 减免物业滞纳金
		private Double wyznjsh;          // 实收物业滞纳金
		private Double ktznjmoney;       // 空调滞纳金
		private Double ktznjjm;          // 减免空调滞纳金
		private Double ktznjsh;          // 实收空调滞纳金
		private String pid;              // 收款单号
		private String entryman;         // 收款人
		
		
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
		public String getPaiddate() {
			return paiddate;
		}
		public void setPaiddate(String paiddate) {
			this.paiddate = paiddate;
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
		public Double getWyznjjm() {
			return wyznjjm;
		}
		public void setWyznjjm(Double wyznjjm) {
			this.wyznjjm = wyznjjm;
		}
		public Double getWyznjsh() {
			return wyznjsh;
		}
		public void setWyznjsh(Double wyznjsh) {
			this.wyznjsh = wyznjsh;
		}
		public Double getKtznjmoney() {
			return ktznjmoney;
		}
		public void setKtznjmoney(Double ktznjmoney) {
			this.ktznjmoney = ktznjmoney;
		}
		public Double getKtznjjm() {
			return ktznjjm;
		}
		public void setKtznjjm(Double ktznjjm) {
			this.ktznjjm = ktznjjm;
		}
		public Double getKtznjsh() {
			return ktznjsh;
		}
		public void setKtznjsh(Double ktznjsh) {
			this.ktznjsh = ktznjsh;
		}
		public String getPid() {
			return pid;
		}
		public void setPid(String pid) {
			this.pid = pid;
		}
		public String getEntryman() {
			return entryman;
		}
		public void setEntryman(String entryman) {
			this.entryman = entryman;
		}

		
		
}
