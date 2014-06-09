package pdz.wyht.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import pdz.wyht.bean.AjaxResult;
import pdz.wyht.bean.BiShop;
import pdz.wyht.bean.CardInfo;
import pdz.wyht.bean.WyAccount;
import pdz.wyht.bean.WyHT;
import pdz.wyht.bean.WyhtUser;
import pdz.wyht.service.WyBusService;
import pdz.wyht.until.DateUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;



@Controller @Scope("prototype")
public class WyhtBusAction extends ActionSupport {

	@Resource WyBusService wyBusService;
	
	private static Log logger=LogFactory.getLog(WyhtBusAction.class);
	private AjaxResult ajaxResult;
	private String zlcode                ;
	private String wycode                ;
	private String jianame               ;
	private String jiaaddress            ;
	private String jialxname             ;
	private String jiaphone              ;
	private String jiafax                ;
	private String yiname                ;
	private String yiaddress             ;
	private String yiphone               ;
	private String yifax                 ;
	private String yilxname              ;
	private String yisfz                 ;
	private String yishop                ;
	private String startdate             ;
	private String enddate               ;
	private String wycosttypename        ;
	private String wycosttype            ;
	private String wycost                ;
	private String ktcost                ;
	private String qtcost                ;
	private String remark                ;
	private String htid                  ;
	private String money                 ;
	private String paymonth              ;
	private String payuserid             ;
	private String payuser               ;
	private String costtype              ;
	private String usersx                ;
	private String userName              ;
	private String shop                  ;
	private String role                  ;
	private String uid                  ;
	private String rolename             ;
	private String shopname             ;
	private String paymode              ;
	private String paymodename           ; 
	private String card                 ;
	private String bank                 ;
	private String cdate                ;
	private String zmoney               ;
	private String jzmj                 ;
	
	

	
	public String getJzmj() {
		return jzmj;
	}
	public void setJzmj(String jzmj) {
		this.jzmj = jzmj;
	}
	public String getZmoney() {
		return zmoney;
	}
	public void setZmoney(String zmoney) {
		this.zmoney = zmoney;
	}
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getCdate() {
		return cdate;
	}
	public void setCdate(String cdate) {
		this.cdate = cdate;
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
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getShopname() {
		return shopname;
	}
	public void setShopname(String shopname) {
		this.shopname = shopname;
	}
	public String getUsersx() {
		return usersx;
	}
	public void setUsersx(String usersx) {
		this.usersx = usersx;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getShop() {
		return shop;
	}
	public void setShop(String shop) {
		this.shop = shop;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getCosttype() {
		return costtype;
	}
	public void setCosttype(String costtype) {
		this.costtype = costtype;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getPaymonth() {
		return paymonth;
	}
	public void setPaymonth(String paymonth) {
		this.paymonth = paymonth;
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
	public String getYilxname() {
		return yilxname;
	}
	public void setYilxname(String yilxname) {
		this.yilxname = yilxname;
	}
	public String getYisfz() {
		return yisfz;
	}
	public void setYisfz(String yisfz) {
		this.yisfz = yisfz;
	}
	public String getYishop() {
		return yishop;
	}
	public void setYishop(String yishop) {
		this.yishop = yishop;
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
	public String getWycosttypename() {
		return wycosttypename;
	}
	public void setWycosttypename(String wycosttypename) {
		this.wycosttypename = wycosttypename;
	}
	public String getWycosttype() {
		return wycosttype;
	}
	public void setWycosttype(String wycosttype) {
		this.wycosttype = wycosttype;
	}
	public String getWycost() {
		return wycost;
	}
	public void setWycost(String wycost) {
		this.wycost = wycost;
	}
	public String getKtcost() {
		return ktcost;
	}
	public void setKtcost(String ktcost) {
		this.ktcost = ktcost;
	}
	public String getQtcost() {
		return qtcost;
	}
	public void setQtcost(String qtcost) {
		this.qtcost = qtcost;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public AjaxResult getAjaxResult() {
		return ajaxResult;
	}
	public void setAjaxResult(AjaxResult ajaxResult) {
		this.ajaxResult = ajaxResult;
	}
	
	public String addWyht(){
		AjaxResult aResult=new AjaxResult();
		if (!wyBusService.isHaveZlcode(zlcode)) {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			Date eDate=new Date(),sDate=new Date();
			try {
				eDate = sdf.parse(enddate);
				sDate=sdf.parse(startdate);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if((Double.valueOf(jzmj)>0) && (eDate.getTime()-sDate.getTime()>0))
			{
				WyHT wht=new WyHT();
				try {
					wht.setEnddate(enddate);
					wht.setJiaaddress(jiaaddress);
					wht.setJiafax(jiafax);
					wht.setJialxname(jialxname);
					wht.setJianame(jianame);
					wht.setJiaphone(jiaphone);
					wht.setKtcost(Double.valueOf(ktcost));
					if(qtcost!=null)wht.setQtcost(Long.valueOf(qtcost));
					wht.setRemark(remark);
					wht.setStartdate(startdate);
					wht.setWycost(Double.valueOf(wycost));
					wht.setWycosttype(wycosttype);
					wht.setWycosttypename(wycosttypename);
					wht.setYiaddress(yiaddress);
					wht.setYifax(yifax);
					wht.setYilxname(yilxname);
					wht.setYiphone(yiphone);
					wht.setYiname(yiname);
					wht.setYisfz(yisfz);
					wht.setYishop(yishop);
					wht.setZlcode(zlcode);
					wht.setStatus(1L);
					BiShop bishop=wyBusService.getZlhtByShop(zlcode);
					wht.setShop(bishop.getShopid());
					wht.setShopname(bishop.getShopname());
					wht.setPaymode(paymode);
					wht.setPaymodename(paymodename);
					wht.setWycode(wyBusService.getZlhtByShopCnt(zlcode));
					wht.setJzmj(Double.valueOf(jzmj));
					wht.setWysumcost(wyBusService.calWYcostSum(startdate,enddate,"1",wycost));
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				if (wyBusService.addObj(wht)) {
					String wyid=wyBusService.getWYhtByZLcode(zlcode);

					try {
						//分摊管理费
						wyBusService.assignWYcost(wyid,startdate,enddate,"1",wycost,paymode,1);
						//分摊空调费
						wyBusService.assignKTcost(wyid,startdate,enddate,ktcost,Integer.valueOf(paymode),1);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					aResult.setError(false);
					aResult.setMessage("保存成功!");
				}else{
					aResult.setError(true);
					aResult.setMessage("保存失败!");
				}
			}
			else
			{
				aResult.setError(true);
				aResult.setMessage("输入信息有错误!");
			}
			
		}else {
			aResult.setError(true);
			aResult.setMessage("该租赁合同已经签署了物业合同!");
		}
		
		ajaxResult=aResult;
		return "busresult";
	}
	
	public String modifyWyMoney(){
		logger.debug("modifyWyMoney_htid:"+htid);
		
		AjaxResult aResult=new AjaxResult();
		boolean errorflag=false;
		String errorMsg="";
		WyHT oldwht=(WyHT)wyBusService.getObj(WyHT.class, htid);
		Double oldWYCost=wyBusService.getWyHTcost(htid,1,paymonth);
		if(!wyBusService.modifyWyMoney(htid,paymonth,Double.valueOf(money))){
			errorflag=true;
			errorMsg="修改物业管理费失败!";
		}
		if(errorflag){
			aResult.setError(true);
			aResult.setMessage(errorMsg);
		}else{
//			DateUtil dateUtil=new DateUtil();
//			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");
//			
//			WyHT wht=(WyHT)wyBusService.getObj(WyHT.class, htid);
//			Date startDate,endDate;
//			try {
//				startDate = sdf.parse(wht.getStartdate());
//				endDate=sdf.parse(wht.getEnddate());
//				
//				//将合同的物业费做相应调整
//				wht.setWycost(wyBusService.getWyHTcost(htid,1)/dateUtil.calMonthDiff(startDate, endDate));
//				wyBusService.updateObj(wht);
//				aResult.setMessage("修改成功!");
//				//增加操作记录
//				wyBusService.addOpertorNote((WyhtUser)ActionContext.getContext().getSession().get("wyuser"), "修改物业管理费","租赁合同"+wht.getZlcode()+"将"+paymonth+"的物业管理费修改为"+money, wht);
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				aResult.setMessage("更新合同信息修改失败!");
//			}

			
			aResult.setMessage("修改成功!");
			//增加操作记录
			wyBusService.addOpertorNote((WyhtUser)ActionContext.getContext().getSession().get("wyuser"), "修改物业管理费","租赁合同"+oldwht.getZlcode()+"将"+paymonth+"的物业管理费从"+oldWYCost+"修改为"+money, oldwht);
			
		}
		    ajaxResult=aResult;
		return "busresult";
	}
	
	public String modifyKTMoney(){
		logger.debug("modifyKTMoney_htid:"+htid);
		
		AjaxResult aResult=new AjaxResult();
		boolean errorflag=false;
		String errorMsg="";
		WyHT wht=(WyHT)wyBusService.getObj(WyHT.class, htid);
		Double oldKT=wht.getKtcost();
		if(!wyBusService.modifyKTMoney(htid,Double.valueOf(money))){
			errorflag=true;
			errorMsg="修改空调费失败!";
		}else{
			wht.setKtcost(Double.valueOf(money));
			if(!wyBusService.updateObj(wht)){
				errorflag=true;
				errorMsg="更新合同空调费失败!";
			}
		}
		if(errorflag){
			aResult.setError(true);
			aResult.setMessage(errorMsg);
		}else{
			aResult.setMessage("修改成功!");
			
			//增加操作记录
			wyBusService.addOpertorNote((WyhtUser)ActionContext.getContext().getSession().get("wyuser"), "修改空调费","租赁合同"+wht.getZlcode()+"将空调费从"+oldKT+"修改为"+money, wht);
		}
		    ajaxResult=aResult;
		return "busresult";
	}
	
	public String modifyWyht(){
		
		logger.debug("modifyWyht_htid:"+htid);
		
		AjaxResult aResult=new AjaxResult();
		boolean errorflag=false;
		String errorMsg="";
		WyHT wht=(WyHT)wyBusService.getObj(WyHT.class, htid);
		DateUtil dateUtil=new DateUtil();
		String oldEndDate=wht.getEnddate();
		String startDate=wht.getStartdate();
		if(!dateUtil.compare_date(startDate,enddate)){
			try {
//				//根据结束时间判定合同是顺延还是提前结束
				boolean flag=dateUtil.compare_date(enddate, oldEndDate);
				
				if(flag){
					//修改的日期为大，顺延合同
					//以原合同结束期加一天，到新的合同结束时间新增一份合同
					String newStartDate=dateUtil.afterDate(oldEndDate,1);
					WyHT newWyHT=new WyHT();
					newWyHT.setEnddate(enddate);
					newWyHT.setJiaaddress(jiaaddress);
					newWyHT.setJiafax(jiafax);
					newWyHT.setJialxname(jialxname);
					newWyHT.setJianame(jianame);
					newWyHT.setJiaphone(jiaphone);
					newWyHT.setKtcost(Double.valueOf(ktcost));
					newWyHT.setRemark(remark);
					newWyHT.setStartdate(newStartDate);
					newWyHT.setWycost(Double.valueOf(wycost));
					newWyHT.setWycosttype(wycosttype);
					newWyHT.setWycosttypename(wycosttypename);
					newWyHT.setYiaddress(yiaddress);
					newWyHT.setYifax(yifax);
					newWyHT.setYilxname(yilxname);
					newWyHT.setYiphone(yiphone);
					newWyHT.setYiname(yiname);
					newWyHT.setYisfz(yisfz);
					newWyHT.setYishop(yishop);
					newWyHT.setZlcode(wht.getZlcode());
					newWyHT.setStatus(4L);
					newWyHT.setShop(wht.getShop());
					newWyHT.setShopname(wht.getShopname());
//					newWyHT.setPaymode(paymode);
//					newWyHT.setPaymodename(paymodename);
					String code=wht.getZlcode();
					newWyHT.setWycode(wyBusService.getZlhtByShopCnt(code));
					newWyHT.setJzmj(Double.valueOf(jzmj));
					newWyHT.setWysumcost(wyBusService.calWYcostSum(newStartDate,enddate,"1",wycost));
					boolean isadd=false;
					try {
						isadd=wyBusService.addObj(newWyHT);	
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					if(isadd){
						String wyid=wyBusService.getWYhtByZLcode(code);
						//分摊管理费
						wyBusService.assignWYcost(wyid,newStartDate,enddate,"1",wycost,wht.getPaymode(),1);
						//分摊空调费
						wyBusService.assignKTcost(wyid,newStartDate,enddate,ktcost,Integer.valueOf(wht.getPaymode()),1);
						
						//增加操作记录
						wyBusService.addOpertorNote((WyhtUser)ActionContext.getContext().getSession().get("wyuser"), "顺延合同","租赁合同"+code+"从"+oldEndDate+"顺延至"+enddate, newWyHT);
					}else{
						errorflag=true;
						errorMsg="新增顺延合同失败!";
					}
					
				}else{

					/***
					 *	修改的日期为小，提前终止合同
					 *	1、在应收表里将大于结束日期的应收月份的记录查询处理
					 *	2、在表里添加对应为负数的记录
					 *	3、将合同结束日期变更为新结束日期 
					 */
					List<WyAccount> accountList=wyBusService.getAccountList(htid,enddate);
					for (WyAccount wyAccount : accountList) {
						Double account=wyAccount.getAccount()*-1;
						Double znj=wyAccount.getAccountznj()*-1;
						
						wyAccount.setAccount(account);
						wyAccount.setAccountznj(znj);
						wyAccount.setAid(null);
						wyAccount.setAuditdate(null);
						wyAccount.setCostover("-1");
					}
					boolean is=true;
					try {
						is=wyBusService.addWyAccountBatch(accountList);
//						将超过应付有效月份的支付标记置为-1
						wyBusService.updateCostOverOver(accountList);
						//将合同的空调费做相应调整
						wht.setKtcost(wyBusService.getWyHTcost(htid,2));
						wyBusService.updateObj(wht);
					} catch (Exception e) {
						e.printStackTrace();
					}
					if(is){
						wht.setEnddate(enddate);
						wht.setStatus(4L);
					
						if(!wyBusService.updateObj(wht)){
							
							errorflag=true;
							errorMsg="合同信息更新失败!";
						}else{
							
							//增加操作记录
							wyBusService.addOpertorNote((WyhtUser)ActionContext.getContext().getSession().get("wyuser"), "终止合同","租赁合同"+wht.getZlcode()+"从"+oldEndDate+"提前至"+enddate+"终止", wht);
						}
					}else{
						
						errorflag=true;
						errorMsg="终止合同失败!";
					}
					
				}
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else{
			errorflag=true;
			errorMsg="终止时间大于合同开始时间!";
		}

	
		if(errorflag){
			aResult.setError(true);
			aResult.setMessage(errorMsg);
		}else{
			aResult.setMessage("合同变更成功!");
		}
		    ajaxResult=aResult;
		return "busresult";
	}
	
	public String auditModifyWyht(){
		
		WyHT wht=(WyHT)wyBusService.getObj(WyHT.class, htid);
		DateUtil dUtil=new DateUtil();
		boolean isHistroy=true;
		//合同永不过期
//		isHistroy=dUtil.compare_date(wht.getEnddate(), new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		
		if(isHistroy){
			wht.setStatus(3L);	
		}else{
			wht.setStatus(5L);
		}
		
		AjaxResult aResult=new AjaxResult();
		if (wyBusService.updateObj(wht)) {
			aResult.setMessage("审核通过!");
			//增加审核应收时间
			wyBusService.updateAuditAccount(htid);
			//增加操作记录
			wyBusService.addOpertorNote((WyhtUser)ActionContext.getContext().getSession().get("wyuser"), "审核合同","租赁合同"+wht.getZlcode()+"合同变更审核通过", wht);
		}else{
			aResult.setMessage("审核失败!");
		}
		ajaxResult=aResult;
		return "busresult";
	}
	
	public String changeWyht(){
		WyHT wht=(WyHT)wyBusService.getObj(WyHT.class, htid);
		
		wht.setStatus(4L);
		
		AjaxResult aResult=new AjaxResult();
		if (wyBusService.updateObj(wht)) {
			aResult.setMessage("合同状态变更成功!");
		}else{
			aResult.setMessage("合同状态变更失败!");
		}
		ajaxResult=aResult;
		return "busresult";
	}
	
	public String updateOtherWyht(){

		logger.debug("updateOtherWyht_htid:"+htid);
		
		AjaxResult aResult=new AjaxResult();
		if (true) {
			WyHT wht=(WyHT)wyBusService.getObj(WyHT.class, htid);
			Double oldwycost=wht.getWycost();
			String oldpaymode=wht.getPaymode();
			String oldStartdate=wht.getStartdate();
			String oldEnddate=wht.getEnddate();
			try {
				
				//wht.setEnddate(enddate);
				wht.setJiaaddress(jiaaddress);
				wht.setJianame(jianame);
//				wht.setKtcost(Double.valueOf(ktcost));
				wht.setRemark(remark);
				//wht.setStartdate(startdate);
//				wht.setWycost(Double.valueOf(wycost));
//				wht.setWycosttype(wycosttype);
//				wht.setWycosttypename(wycosttypename);
				wht.setYiaddress(yiaddress);
				wht.setYifax(yifax);
				wht.setYilxname(yilxname);
				wht.setYiphone(yiphone);
				wht.setYiname(yiname);
				wht.setYisfz(yisfz);
				wht.setYishop(yishop);
//				wht.setPaymode(paymode);
//				wht.setPaymodename(paymodename);
//				wht.setJzmj(Double.valueOf(jzmj));
//				wht.setWysumcost(wyBusService.calWYcostSum(startdate,enddate,"1",wycost));
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (wyBusService.updateObj(wht)) {
				
				try {
					
//					if(oldwycost-Double.valueOf(wycost)!=0){
////						清空物业应收表记录
//						wyBusService.delAccount(htid,1);
//						//分摊管理费
//						wyBusService.assignWYcost(htid,oldStartdate,oldEnddate,"1",wycost,paymode,2);	
//						System.out.println("execut wycost again");
//					}
					if(!oldpaymode.equalsIgnoreCase(paymode)){
						wyBusService.updatePayMode(htid,oldStartdate,oldEnddate,paymode);
						System.out.println("execut paymode again");
					}	
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				aResult.setMessage("更新成功!");
				
				//增加操作记录
				wyBusService.addOpertorNote((WyhtUser)ActionContext.getContext().getSession().get("wyuser"), "其他变更","租赁合同"+wht.getZlcode()+"合同进行了其他变更", wht);
			}else{
				aResult.setError(true);
				aResult.setMessage("更新失败!");
			}

		}
		    ajaxResult=aResult;
		return "busresult";
	}
		
	public String updateWyht(){
		boolean flag=wyBusService.isPayHT(htid);
		logger.debug("updatewyht_htid:"+htid);
		logger.debug("updatewyht_flag:"+flag);
		AjaxResult aResult=new AjaxResult();
		if (!flag) {
			WyHT wht=(WyHT)wyBusService.getObj(WyHT.class, htid);
			try {
				
				wht.setEnddate(enddate);
				wht.setJiaaddress(jiaaddress);
				wht.setJianame(jianame);
				wht.setKtcost(Double.valueOf(ktcost));
				wht.setRemark(remark);
				wht.setStartdate(startdate);
				wht.setWycost(Double.valueOf(wycost));
				wht.setWycosttype(wycosttype);
				wht.setWycosttypename(wycosttypename);
				wht.setYiaddress(yiaddress);
				wht.setYifax(yifax);
				wht.setYilxname(yilxname);
				wht.setYiphone(yiphone);
				wht.setYiname(yiname);
				wht.setYisfz(yisfz);
				wht.setYishop(yishop);
				wht.setPaymode(paymode);
				wht.setPaymodename(paymodename);
				wht.setJzmj(Double.valueOf(jzmj));
				wht.setWysumcost(wyBusService.calWYcostSum(startdate,enddate,"1",wycost));
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (wyBusService.updateObj(wht)) {
				
				try {
//					清空应收表记录
					wyBusService.delAccount(htid,0);
					//分摊管理费
					wyBusService.assignWYcost(htid,startdate,enddate,"1",wycost,paymode,1);
					//分摊空调费
					wyBusService.assignKTcost(htid,startdate,enddate,ktcost,Integer.valueOf(paymode),1);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				aResult.setMessage("更新成功!");
			}else{
				aResult.setError(true);
				aResult.setMessage("更新失败!");
			}

		}else{
			aResult.setError(true);
			aResult.setMessage("该合同已经开始收费，不能进行合同变更!");
		}
		    ajaxResult=aResult;
		return "busresult";
	}
	
	public String updateshopWyht(){
		WyHT wht=(WyHT)wyBusService.getObj(WyHT.class, htid);
		wht.setStatus(2L);
		AjaxResult aResult=new AjaxResult();
		if (wyBusService.updateObj(wht)) {
			aResult.setMessage("审核成功!");
		}else{
			aResult.setMessage("审核失败!");
		}
		ajaxResult=aResult;
		return "busresult";
	}
	
	public String auditWyht(){
		WyHT wht=(WyHT)wyBusService.getObj(WyHT.class, htid);
		
		wht.setStatus(3L);
		
		AjaxResult aResult=new AjaxResult();
		if (wyBusService.updateObj(wht)) {
			if(wyBusService.updateAuditAccount(htid)){
				aResult.setMessage("审核成功!");	
			}else{
				aResult.setMessage("更新应收审核时间失败!");
			}
				
		}else{
			aResult.setMessage("审核失败!");
		}
		ajaxResult=aResult;
		return "busresult";
	}
	
	public String delSimulateWyht(){
		WyHT wht=(WyHT)wyBusService.getObj(WyHT.class, htid);
		
		wht.setStatus(6L);
		
		AjaxResult aResult=new AjaxResult();
		if (wyBusService.updateObj(wht)) {
			aResult.setMessage("删除成功!");
		}else{
			aResult.setMessage("删除失败!");
		}
		ajaxResult=aResult;
		return "busresult";
	}
	
	public String returnWyht(){
		WyHT wht=(WyHT)wyBusService.getObj(WyHT.class, htid);
		
		wht.setStatus(1L);
		
		AjaxResult aResult=new AjaxResult();
		if (wyBusService.updateObj(wht)) {
			aResult.setMessage("回退成功!");
		}else{
			aResult.setMessage("回退失败!");
		}
		ajaxResult=aResult;
		return "busresult";
	}
	
	public String getWyht() {
		WyHT wyht=(WyHT)wyBusService.getObj(WyHT.class, htid);
		AjaxResult aResult=new AjaxResult();
		aResult.setData(wyht);
		aResult.setMessage("获取成功!");
		ajaxResult=aResult;
		return "busresult";
	}
	
	public String delWyht(){
		WyHT wyht=(WyHT)wyBusService.getObj(WyHT.class, htid);
		AjaxResult aResult=new AjaxResult();
		if (wyBusService.delObj(wyht)) {
			aResult.setMessage("删除成功!");
		}else{
			aResult.setMessage("删除失败!");
		}
		ajaxResult=aResult;
		return "busresult";
	}
	

	public String addUser() {
		AjaxResult aResult=new AjaxResult();
		if(!wyBusService.isHaveUsersx(usersx)){
			WyhtUser wuser=new WyhtUser();
			wuser.setPassword("123");
			wuser.setRole(Long.valueOf(role));
			wuser.setShop(shop);
			wuser.setUserName(userName);
			wuser.setUsersx(usersx);
			wuser.setShopname(shopname);
			wuser.setRolename(rolename);
			if (wyBusService.addObj(wuser)) {
				aResult.setError(false);
				aResult.setMessage("保存成功!");
			}else{
				aResult.setError(true);
				aResult.setMessage("保存失败!");
			}
		}else{
			aResult.setError(true);
			aResult.setMessage("账号已经存在!");
		}
		
		ajaxResult=aResult;
		return "busresult";
	}
	
	public String updateUser() {
		AjaxResult aResult=new AjaxResult();
		WyhtUser wuser=null;
		try {
			wuser=(WyhtUser)wyBusService.getObj(WyhtUser.class, Long.valueOf(uid));
			wuser.setRole(Long.valueOf(role));
			wuser.setShop(shop);
			wuser.setUserName(userName);
			wuser.setUsersx(usersx);
			wuser.setShopname(shopname);
			wuser.setRolename(rolename);	
		
			if (wyBusService.updateObj(wuser)) {
				aResult.setMessage("更新成功!");
			}else{
				aResult.setMessage("更新失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ajaxResult=aResult;
		return "busresult";
	}
	
	public String delUser() {
		WyhtUser wuser=(WyhtUser)wyBusService.getObj(WyhtUser.class, Long.valueOf(uid));
		
		AjaxResult aResult=new AjaxResult();
		if (wyBusService.delObj(wuser)) {
			aResult.setMessage("删除成功!");
		}else{
			aResult.setMessage("删除失败!");
		}
		ajaxResult=aResult;
		return "busresult";
	}
	
	public String getUser(){
		WyhtUser wuser=(WyhtUser)wyBusService.getObj(WyhtUser.class, Long.valueOf(uid));
		AjaxResult aResult=new AjaxResult();
		aResult.setData(wuser);
		aResult.setMessage("获取成功!");
		ajaxResult=aResult;
		return "busresult";
	}
	
	public String addCard(){
		AjaxResult aResult=new AjaxResult();
		CardInfo cInfo=new CardInfo();
		cInfo.setBank(bank);
		cInfo.setCard(card);
		cInfo.setCate(cdate);
		cInfo.setMoney(zmoney);
		if(bank.equalsIgnoreCase("1")){
			cInfo.setBankname("农业银行");	
		}else if (bank.equalsIgnoreCase("2")) {
			cInfo.setBankname("交通银行");
		}else if (bank.equalsIgnoreCase("3")) {
			cInfo.setBankname("建设银行");
		}else{
			cInfo.setBankname("招商银行");
		}
		
		if(wyBusService.addObj(cInfo)){
			aResult.setError(false);
			aResult.setMessage("保存成功");
		}else{
			aResult.setError(true);
			aResult.setMessage("保存失败");
		}
		ajaxResult=aResult;
		return "busresult";
	}
	
	public String delCard(){
		AjaxResult aResult=new AjaxResult();
		CardInfo cInfo=(CardInfo)wyBusService.getObj(CardInfo.class, card);
		if(wyBusService.delObj(cInfo)){
			aResult.setError(false);
			aResult.setMessage("删除成功");
		}else{
			aResult.setError(true);
			aResult.setMessage("删除失败");
		}
		ajaxResult=aResult;
		
		return "busresult";
	}
	
}
