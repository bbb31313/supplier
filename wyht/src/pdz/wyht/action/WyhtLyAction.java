package pdz.wyht.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import pdz.wyht.bean.AjaxResult;
import pdz.wyht.bean.WyLYMoney;
import pdz.wyht.bean.WyhtUser;
import pdz.wyht.service.WyBusService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


@Controller @Scope("prototype")
public class WyhtLyAction extends ActionSupport {

	@Resource WyBusService wyBusService;
	

	private AjaxResult ajaxResult        ;
	private String zlcode                ;
	private String wycode                ;
	private String yiname                ;
	private String htid                  ;
	private String money                 ;
	private String payuserid             ;
	private String payuser               ;
	private String res                   ;
	private String costtype              ;
	
	

	

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
	
	public String getYiname() {
		return yiname;
	}
	public void setYiname(String yiname) {
		this.yiname = yiname;
	}
	
	public AjaxResult getAjaxResult() {
		return ajaxResult;
	}
	public void setAjaxResult(AjaxResult ajaxResult) {
		this.ajaxResult = ajaxResult;
	}
	
	public String addWylyMoney(){
		
		AjaxResult aResult=new AjaxResult();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String curdate=sdf.format(new Date());
		WyhtUser user=(WyhtUser)ActionContext.getContext().getSession().get("wyuser");
		WyLYMoney lym=new WyLYMoney();
		try {
			lym.setEntrydate(curdate);
			lym.setEntryuserid(user.getUserID().toString());
			lym.setHtid(htid);
			Long lymone=Long.valueOf(money);
			if (costtype.equalsIgnoreCase("2")) {
				lymone=Long.valueOf("-"+lymone.toString());
			}
			lym.setMoney(lymone);
			lym.setPayuser(payuser);
			lym.setPayuserid(payuserid);
			lym.setRes(res);
			lym.setWycode(wycode);
			lym.setYiname(yiname);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(wyBusService.addObj(lym)){
			aResult.setMessage("保存成功!");
		}else{
			aResult.setMessage("保存失败!");
		}
		
		ajaxResult=aResult;
		return "lyresult";
	}
	
	
}
