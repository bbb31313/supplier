package pdz.wyht.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import pdz.wyht.bean.AjaxResult;
import pdz.wyht.bean.WYPMarea;
import pdz.wyht.bean.WyPaid;
import pdz.wyht.bean.WyhtUser;
import pdz.wyht.service.WypmBusService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


@Controller @Scope("prototype")
public class WypmBusAction extends ActionSupport {

	@Resource WypmBusService wypmBusService;
	

	private AjaxResult ajaxResult        ;
	
	private String wpaid                  ;
	private String shopid                 ;
	private String shopname             ;
	private String floor               ;
	private String norentArea                   ;
	private String notrentArea              ;
	private String allArea          ;
	
	
	

	

	
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
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getNorentArea() {
		return norentArea;
	}
	public void setNorentArea(String norentArea) {
		this.norentArea = norentArea;
	}
	public String getNotrentArea() {
		return notrentArea;
	}
	public void setNotrentArea(String notrentArea) {
		this.notrentArea = notrentArea;
	}
	public String getAllArea() {
		return allArea;
	}
	public void setAllArea(String allArea) {
		this.allArea = allArea;
	}
	public AjaxResult getAjaxResult() {
		return ajaxResult;
	}
	public void setAjaxResult(AjaxResult ajaxResult) {
		this.ajaxResult = ajaxResult;
	}
	
	public String getWyPMarea(){
		WYPMarea wypmArea=(WYPMarea)wypmBusService.getObj(WYPMarea.class, wpaid);
		AjaxResult aResult=new AjaxResult();
		aResult.setData(wypmArea);
		aResult.setMessage("获取成功!");
		ajaxResult=aResult;
		return "wypmresult";
	}
	
	public String addWyPMarea(){
		AjaxResult aResult=new AjaxResult();
		
		WYPMarea wypmArea=new WYPMarea();
		
		try {
			wypmArea.setAllArea(Double.valueOf(allArea));
			wypmArea.setFloor(Long.valueOf(floor));
			wypmArea.setNorentArea(Double.valueOf(norentArea));
			wypmArea.setNotrentArea(Double.valueOf(notrentArea));
			wypmArea.setShopid(shopid);
			wypmArea.setShopname(shopname);
		
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		
		
		if(wypmBusService.addObj(wypmArea)){
			aResult.setError(false);
			aResult.setMessage("保存成功!");
		}else{
			aResult.setError(true);
			aResult.setMessage("保存失败!");
		}
		
		ajaxResult=aResult;
		return "wypmresult";
	}
	
	
	public String updateWyPMarea(){
		AjaxResult aResult=new AjaxResult();
		WYPMarea wypmArea=(WYPMarea)wypmBusService.getObj(WYPMarea.class, wpaid);
		try {
			wypmArea.setAllArea(Double.valueOf(allArea));
			wypmArea.setFloor(Long.valueOf(floor));
			wypmArea.setNorentArea(Double.valueOf(norentArea));
			wypmArea.setNotrentArea(Double.valueOf(notrentArea));
			wypmArea.setShopid(shopid);
			wypmArea.setShopname(shopname);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(wypmBusService.updateObj(wypmArea)){
			aResult.setError(false);
			aResult.setMessage("编辑成功!");
		}else{
			aResult.setError(true);
			aResult.setMessage("编辑失败!");
		}
		
		ajaxResult=aResult;
		return "wypmresult";
	}
	
	public String delWyPMarea(){
		AjaxResult aResult=new AjaxResult();
		WYPMarea wypmArea=(WYPMarea)wypmBusService.getObj(WYPMarea.class, wpaid);
		if(wypmBusService.delObj(wypmArea)){
			aResult.setError(false);
			aResult.setMessage("删除成功!");
		}else{
			aResult.setError(true);
			aResult.setMessage("删除失败!");
		}
		ajaxResult=aResult;
		return "wypmresult";
	}
	
}
