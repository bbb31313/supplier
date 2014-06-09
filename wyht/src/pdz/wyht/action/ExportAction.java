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
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.WritableWorkbook;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import pdz.wyht.bean.WyReportFeild;
import pdz.wyht.until.ExportExcel;
import pdz.wyht.until.RptDbContext;

import com.opensymphony.xwork2.ActionSupport;
import java.lang.reflect.Field;

@Controller
@Scope("prototype")
public class ExportAction extends ActionSupport {

	@Resource
	RptDbContext rptDbContext;
	private String downloadFileName;
	private InputStream excelStream;

	public InputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}

	public String getDownloadFileName() {

		try {

			downloadFileName = new String(downloadFileName.getBytes(),
					"ISO8859-1");

		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();

		}
		return downloadFileName;
	}

	public void setDownloadFileName(String downloadFileName) {
		this.downloadFileName = downloadFileName;
	}

	public String downLoadExecl() {

		HttpServletRequest request = ServletActionContext.getRequest();

		String view = request.getParameter("view");
		try {
			// 取报表字段信息
			List<WyReportFeild> reportFeilds = rptDbContext.getReportInfo(view);
			String rTitle = reportFeilds.get(0).getZhreport();

			// 设置导出报表的名称
			downloadFileName = rTitle + ".xls";

//			downloadFileName = new String(downloadFileName.getBytes(),"ISO8859-1");

			List<Object> objeList = rptDbContext.getDownLoadList(request);
			Field[] fields = null;
			Object obj = objeList.get(0);
			fields = obj.getClass().getDeclaredFields();
			int k = 0;
			

			// 装配报表表头信息
			String[] Title = new String[fields.length];
			String feildName;
			for (k = 0; k < fields.length; k++) {
				Field field = fields[k];
				field.setAccessible(true);
				try {
					Object valueObj = field.get(obj);
					feildName = field.getName();
					for (WyReportFeild reportFeild : reportFeilds) {
						if ((valueObj != null)
								&& (reportFeild.getEnfeild()
										.equalsIgnoreCase(feildName))) {
							Title[k] = reportFeild.getZhfeild();
							break;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}


			// 去除装配表头中没有值的表头
			// 用StringBuffer来存放数组中的非空元素，用“;”分隔

			StringBuffer sb = new StringBuffer();
			for (int cnt = 0; cnt < Title.length; cnt++) {
				// if ((Title[cnt]==null)&&("".equals(Title[cnt]))) continue;
				
				if ((Title[cnt] != null)){
					sb.append(Title[cnt]);
					if (cnt != Title.length - 1)
						sb.append(";");
				}
			}

//			System.out.println(sb);
			Title = sb.toString().split(";");
			
			ByteArrayOutputStream output = new ByteArrayOutputStream();

			WritableWorkbook workbook;

			workbook = ExportExcel.exportExcel(output, rTitle, Title, objeList);

			byte[] ba = output.toByteArray();

			excelStream = new ByteArrayInputStream(ba);

			output.flush();

			output.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "excel";
	}
}
