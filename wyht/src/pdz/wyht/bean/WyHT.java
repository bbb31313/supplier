package pdz.wyht.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="wy_ht")
public class WyHT {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="htid")
	private String  htid            ;   //系统编码
	@Column(name="zlcode")
	private String  zlcode          ;          //租赁合同号
	@Column(name="wycode")
	private String  wycode          ;          //物业合同号
	@Column(name="jianame")
	private String  jianame         ;          //甲方名称
	@Column(name="jiaaddress")
	private String  jiaaddress      ;         //甲方地址
	@Column(name="jialxname")
	private String  jialxname       ;         //甲方联系人
	@Column(name="jiaphone")
	private String  jiaphone        ;         //甲方电话
	@Column(name="jiafax")
	private String  jiafax          ;         //甲方传真
	@Column(name="yiname")
	private String  yiname          ;          //乙方名称
	@Column(name="yiaddress")
	private String  yiaddress       ;         //乙方地址
	@Column(name="yilxname")
	private String  yilxname        ;         //乙方联系人
	@Column(name="yiphone")
	private String  yiphone         ;         //乙方电话
	@Column(name="yifax")
	private String  yifax           ;         //乙方传真
	@Column(name="yishop")
	private String  yishop          ;         //乙方商铺号
	@Column(name="yisfz")
	private String  yisfz           ;         //乙方身份证
	@Column(name="startdate")
	private String  startdate       ;         //合同有效期开始日期
	@Column(name="enddate")
	private String	enddate         ;         //合同有效期结束日期
	@Column(name="wycosttype")
	private String	wycosttype      ;        //计算管理费方式(1;按经营面积，2，按建筑面积)
	@Column(name="wycosttypename")
	private String	wycosttypename      ;        //计算管理费方式(1;按经营面积，2，按建筑面积)
	@Column(name="wycost")
	private Double	wycost         ;         //物业管理费
	@Column(name="ktcost")
	private Double	ktcost          ;         //空调费
	@Column(name="qtcost")
	private Long	qtcost          ;         //其他费用
	@Column(name="remark")
	private String	remark          ;       //补充说明
	@Column(name="STATUS")
	private Long status;
	@Column(name="SHOP")
	private String shop;
	@Column(name="paymode")
	private String paymode;
	@Column(name="paymodename")
	private String paymodename;
	@Column(name="JZMJ")                          
	private Double jzmj;                          //建筑或经营面积
	@Column(name="wysumcost")     
	private Double wysumcost;                       //物业费合计
	@Column(name="shopname")     
	private String shopname;                       //门店名称
	
	
	
	
	
	
	public String getShopname() {
		return shopname;
	}
	public void setShopname(String shopname) {
		this.shopname = shopname;
	}
	public Double getWysumcost() {
		return wysumcost;
	}
	public void setWysumcost(Double wysumcost) {
		this.wysumcost = wysumcost;
	}
	public Double getJzmj() {
		return jzmj;
	}
	public void setJzmj(Double jzmj) {
		this.jzmj = jzmj;
	}
	public String getPaymode() {
		return paymode;
	}
	public void setPaymode(String paymode) {
		this.paymode = paymode;
	}
	public String getPaymodename() {
		return paymodename;
	}
	public void setPaymodename(String paymodename) {
		this.paymodename = paymodename;
	}
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
	public String getZlcode() {
		return zlcode;
	}
	public void setZlcode(String zlcode) {
		this.zlcode = zlcode;
	}
	public String getWycode() {
		return wycode;
	}
	public void setWycode(String wycode) {
		this.wycode = wycode;
	}
	public String getJianame() {
		return jianame;
	}
	public void setJianame(String jianame) {
		this.jianame = jianame;
	}
	public String getJiaaddress() {
		return jiaaddress;
	}
	public void setJiaaddress(String jiaaddress) {
		this.jiaaddress = jiaaddress;
	}
	public String getJialxname() {
		return jialxname;
	}
	public void setJialxname(String jialxname) {
		this.jialxname = jialxname;
	}
	public String getJiaphone() {
		return jiaphone;
	}
	public void setJiaphone(String jiaphone) {
		this.jiaphone = jiaphone;
	}
	public String getJiafax() {
		return jiafax;
	}
	public void setJiafax(String jiafax) {
		this.jiafax = jiafax;
	}

	public String getYiname() {
		return yiname;
	}
	public void setYiname(String yiname) {
		this.yiname = yiname;
	}
	public String getYiaddress() {
		return yiaddress;
	}
	public void setYiaddress(String yiaddress) {
		this.yiaddress = yiaddress;
	}
	public String getYilxname() {
		return yilxname;
	}
	public void setYilxname(String yilxname) {
		this.yilxname = yilxname;
	}
	public String getYiphone() {
		return yiphone;
	}
	public void setYiphone(String yiphone) {
		this.yiphone = yiphone;
	}
	public String getYifax() {
		return yifax;
	}
	public void setYifax(String yifax) {
		this.yifax = yifax;
	}
	public String getYishop() {
		return yishop;
	}
	public void setYishop(String yishop) {
		this.yishop = yishop;
	}
	public String getYisfz() {
		return yisfz;
	}
	public void setYisfz(String yisfz) {
		this.yisfz = yisfz;
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
	public String getWycosttype() {
		return wycosttype;
	}
	public void setWycosttype(String wycosttype) {
		this.wycosttype = wycosttype;
	}
	public Double getWycost() {
		return wycost;
	}
	public void setWycost(Double wycost) {
		this.wycost = wycost;
	}
	public Double getKtcost() {
		return ktcost;
	}
	public void setKtcost(Double ktcost) {
		this.ktcost = ktcost;
	}
	public Long getQtcost() {
		return qtcost;
	}
	public void setQtcost(Long qtcost) {
		this.qtcost = qtcost;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getWycosttypename() {
		return wycosttypename;
	}
	public void setWycosttypename(String wycosttypename) {
		this.wycosttypename = wycosttypename;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}	
	
	
}
