package pdz.wyht.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import pdz.wyht.grid.GridData;
import pdz.wyht.until.DbContextExtension;

import com.opensymphony.xwork2.ActionSupport;

@Controller @Scope("prototype")
public class QueryAction extends ActionSupport {
	
	@Resource DbContextExtension dbContextExtension;

	private GridData maxClassList;
	private GridData shopBrandList;
	private GridData secondList;
	
	

	public GridData getSecondList() {
		return secondList;
	}

	public void setSecondList(GridData secondList) {
		this.secondList = secondList;
	}

	public GridData getShopBrandList() {
		return shopBrandList;
	}

	public void setShopBrandList(GridData shopBrandList) {
		this.shopBrandList = shopBrandList;
	}

	public GridData getMaxClassList() {
		return maxClassList;
	}

	public void setMaxClassList(GridData maxClassList) {
		this.maxClassList = maxClassList;
	}
	
	
	public String getClassList(){
		
		HttpServletRequest request=ServletActionContext.getRequest();
		
		maxClassList=dbContextExtension.GetGridData(request);
		return "classlist";
	}
	
	public String getAllShopBrand(){
		
		HttpServletRequest request=ServletActionContext.getRequest();
		
		shopBrandList=dbContextExtension.GetGridData(request);
		return "shoplist";
	}
	
	public String getSecond(){
		HttpServletRequest request=ServletActionContext.getRequest();
		
		secondList=dbContextExtension.GetGridData(request);
		return "secondlist";
	}
}
