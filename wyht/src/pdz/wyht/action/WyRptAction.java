package pdz.wyht.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import jxl.Workbook;
import jxl.write.WritableWorkbook;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import pdz.wyht.bean.RptMonPay;
import pdz.wyht.grid.GridData;
import pdz.wyht.until.DbContextExtension;
import pdz.wyht.until.ExportExcel;
import pdz.wyht.until.RptDbContext;

import com.opensymphony.xwork2.ActionSupport;

@Controller @Scope("prototype")
public class WyRptAction extends ActionSupport {
	
	@Resource RptDbContext rptDbContext;

	private GridData maxClassList;
	
	public GridData getMaxClassList() {
		return maxClassList;
	}

	public void setMaxClassList(GridData maxClassList) {
		this.maxClassList = maxClassList;
	}
		
	public String getClassList(){
		
		HttpServletRequest request=ServletActionContext.getRequest();

			maxClassList=rptDbContext.GetGridData(request);	
			return "classlist";

	}
	
}
