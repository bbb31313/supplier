package pdz.wyht.action;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import net.sf.jasperreports.engine.JasperCompileManager;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;

import pdz.wyht.bean.WyhtUser;
import pdz.wyht.service.WyBusService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Service
public class ReportAction extends ActionSupport {

	@Resource
	WyBusService wyBusService;

	private List list;

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	@Override
	public String execute() throws Exception {
		WyhtUser wuser=(WyhtUser)ActionContext.getContext().getSession().get("wyuser");
		if (!wuser.getShop().equalsIgnoreCase("1")) {
			list  = wyBusService.getQueryReports("1=1 and shopid='"+wuser.getShop()+"'");
		}else{
			list  = wyBusService.getQueryReports("1=1");
		}
		try {

			String reportSource;
			reportSource = ServletActionContext.getServletContext()
					.getRealPath("/jasper/report2.jrxml");
			File parent = new File(reportSource).getParentFile();
			// 将.jrxml模板文件编译成为.jasper文件,当然,其文件名可以指定,如果没指定,则与.jrxml文件名一样.只是后缀不同而已
			JasperCompileManager.compileReportToFile(reportSource, new File(
					parent, "report2.jasper")
					.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

}
