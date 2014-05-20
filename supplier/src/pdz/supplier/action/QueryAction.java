package pdz.supplier.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import pdz.supplier.grid.GridData;
import pdz.supplier.until.DbContextExtension;



import com.opensymphony.xwork2.ActionSupport;

@Controller @Scope("prototype")
public class QueryAction extends ActionSupport {
	
	@Resource DbContextExtension dbContextExtension;

	
	private GridData supdocList;
	
	

	public String getListSupdoc(){
		HttpServletRequest request=ServletActionContext.getRequest();
		supdocList=dbContextExtension.GetGridData(request);
		return "supdoclist";
	}
}
