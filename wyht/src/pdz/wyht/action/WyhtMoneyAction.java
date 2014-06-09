package pdz.wyht.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import pdz.wyht.bean.AjaxResult;
import pdz.wyht.bean.WyLYMoney;
import pdz.wyht.bean.WyPaid;
import pdz.wyht.bean.WyhtUser;
import pdz.wyht.service.WyBusService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


@Controller @Scope("prototype")
public class WyhtMoneyAction extends ActionSupport {

	@Resource WyBusService wyBusService;
	

	private AjaxResult ajaxResult        ;
	
	private String htid                  ;
	private String money                 ;
	private String payuserid             ;
	private String payuser               ;
	private String res                   ;
	private String costtype              ;
	private String accountmonth          ;
	private String paymode               ;
	private String wypaymode               ;
	private String paydate               ;
	private String invoice               ;
	private String wyznj                 ;
	
	

	

	public String getWyznj() {
		return wyznj;
	}
	public void setWyznj(String wyznj) {
		this.wyznj = wyznj;
	}
	public String getWypaymode() {
		return wypaymode;
	}
	public void setWypaymode(String wypaymode) {
		this.wypaymode = wypaymode;
	}
	public String getPaydate() {
		return paydate;
	}
	public void setPaydate(String paydate) {
		this.paydate = paydate;
	}
	public String getInvoice() {
		return invoice;
	}
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}
	public String getAccountmonth() {
		return accountmonth;
	}
	public void setAccountmonth(String accountmonth) {
		this.accountmonth = accountmonth;
	}
	public String getPaymode() {
		return paymode;
	}
	public void setPaymode(String paymode) {
		this.paymode = paymode;
	}
	public String getCosttype() {
		return costtype;
	}
	public void setCosttype(String costtype) {
		this.costtype = costtype;
	}
	public String getRes() {
		return res;
	}
	public void setRes(String res) {
		this.res = res;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
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

	
	public AjaxResult getAjaxResult() {
		return ajaxResult;
	}
	public void setAjaxResult(AjaxResult ajaxResult) {
		this.ajaxResult = ajaxResult;
	}
	
	public String addWyZNJ(){
		AjaxResult aResult=new AjaxResult();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String curdate=sdf.format(new Date());
		WyhtUser user=(WyhtUser)ActionContext.getContext().getSession().get("wyuser");
		WyPaid wyp=new WyPaid();
		if(Double.valueOf(money)>Double.valueOf(wyznj)){
			aResult.setError(true);
			aResult.setMessage("支付金额大于应收金额!");
		}else{
			try {
				wyp.setCostname(costtype.equalsIgnoreCase("1")?"物业管理费":"空调费");
				wyp.setCostype(costtype);
				wyp.setEntrydate(curdate);
				wyp.setEntryuserid(String.valueOf(user.getUserID()));
				wyp.setHtid(htid);
				wyp.setPaidmonth(accountmonth);
				wyp.setPaidznj(Double.valueOf(money));	
				wyp.setPayment(paymode);
				wyp.setEnteruser(user.getUserName());
				if(res!=null)wyp.setZnjreduce(res);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(wyBusService.addObj(wyp)){
				aResult.setError(false);
				aResult.setMessage("保存成功!");
			}else{
				aResult.setError(true);
				aResult.setMessage("保存失败!");
			}
		}
		
		
		ajaxResult=aResult;
		return "moneyresult";
	}
	
	public String addWy(){
		AjaxResult aResult=new AjaxResult();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String curdate=sdf.format(new Date());
		WyhtUser user=(WyhtUser)ActionContext.getContext().getSession().get("wyuser");
		String[] months=accountmonth.split(",");
		String[] moneys=money.split(",");
//		System.out.println("accountmonth:"+accountmonth);
//		System.out.println("money:"+money);
		int j=0;
		for (int i = 0; i < months.length; i++) {
			WyPaid wyp=new WyPaid();
			try {
				wyp.setCostname(costtype.equalsIgnoreCase("1")?"物业管理费":"空调费");
				wyp.setCostype(costtype);
				wyp.setEntrydate(curdate);
				wyp.setEntryuserid(String.valueOf(user.getUserID()));
				wyp.setHtid(htid);
				wyp.setPaidmonth(months[i]);
				wyp.setPayment(paymode);
				wyp.setEnteruser(user.getUserName());
				wyp.setPaid(Double.valueOf(moneys[i]));
				wyp.setPaiddate(paydate);
				wyp.setInvoice(invoice);
				if(wyBusService.addObj(wyp)){
					j=j+1;
					wyBusService.updateAccountOver(htid,months[i],costtype);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		if(j==months.length){
			aResult.setError(false);
			aResult.setMessage("保存成功!");
		}else{
			aResult.setError(true);
			aResult.setMessage("保存失败!");
		}
		
		ajaxResult=aResult;
		return "moneyresult";
	}
	
	
	
}
