package pdz.wyht.until;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import java.lang.reflect.Field;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExportExcel {

	/***************************************************************************
	 * @param fileName
	 *            EXCEL文件名称
	 * @param listTitle
	 *            EXCEL文件第一行列标题集合
	 * @param listContent
	 *            EXCEL文件正文数据集合
	 * @return
	 */
	public  static String exportExcel(HttpServletResponse response,String fileName, String[] Title,
			List<Object> listContent) {
		String result = "系统提示：Excel文件导出成功！";
		// 以下开始输出到EXCEL
		try {
			// 定义输出流，以便打开保存对话框______________________begin
//			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
//			response.setHeader("Content-disposition", "attachment; filename="
//					+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
//			response.setContentType("application/msexcel");// 定义输出类型
			// 定义输出流，以便打开保存对话框_______________________end

			/** **********创建工作簿************ */
			WritableWorkbook workbook = Workbook.createWorkbook(os);

			/** **********创建工作表************ */

			WritableSheet sheet = workbook.createSheet("Sheet1", 0);

			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);

			/** ************设置单元格字体************** */
			WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);
			WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10,
					WritableFont.BOLD);

			/** ************以下设置三种单元格样式，灵活备用************ */
			// 用于标题居中
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(false); // 文字是否换行

			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.NONE, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.LEFT); // 文字水平对齐
			wcf_left.setWrap(false); // 文字是否换行

			/** ***************以下是EXCEL开头大标题********************* */
			 sheet.mergeCells(0, 0, Title.length, 0);
			 sheet.addCell(new Label(0, 0, "", wcf_center));
			/** ***************以下是EXCEL第一行列标题********************* */
			for (int i = 0; i < Title.length; i++) {
				sheet.addCell(new Label(i, 1, Title[i], wcf_center));
			}
			/** ***************以下是EXCEL正文数据********************* */
			Field[] fields = null;
			int i = 2;
			for (Object obj : listContent) {
				fields = obj.getClass().getDeclaredFields();
				int j = 0;
				for (Field v : fields) {
					v.setAccessible(true);
					Object va = v.get(obj);
					if (va == null) {
						va = "";
					}
					sheet.addCell(new Label(j, i, va.toString(), wcf_left));
					j++;
				}
				i++;
			}
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();

		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			System.out.println(result);
			e.printStackTrace();
		}
		return result;
	}
	
	public  static WritableWorkbook exportExcel(OutputStream os,String fileName, String[] Title,List<Object> listContent) {
		
		WritableWorkbook workbook=null;
		try {


			/** **********创建工作簿************ */
			workbook = Workbook.createWorkbook(os);

			/** **********创建工作表************ */

 			WritableSheet sheet = workbook.createSheet(fileName, 0);

			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);

			/** ************设置单元格字体************** */
			WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);
			WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10,
					WritableFont.BOLD);
			 Object UnderlineStyle;
			WritableFont wf_title = new WritableFont(WritableFont.ARIAL, 12,  
	                    WritableFont.BOLD, false, jxl.format.UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK); // 定义格式 字体 下划线 斜体 粗体 颜色  
	        WritableFont wf_head = new WritableFont(WritableFont.ARIAL, 11,  
	                    WritableFont.BOLD, false, jxl.format.UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK); // 定义格式 字体 下划线 斜体 粗体 颜色  
	        WritableFont wf_table = new WritableFont(WritableFont.ARIAL, 11,  
	                    WritableFont.NO_BOLD, false, jxl.format.UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK); // 定义格式 字体 下划线 斜体 粗体 颜色  

			/** ************以下设置三种单元格样式，灵活备用************ */
			// 用于大标题 
			WritableCellFormat wcf_Title = new WritableCellFormat(wf_title);
			wcf_Title.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_Title.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_Title.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_Title.setWrap(false); // 文字是否换行
			
			// 用于标题居中
			WritableCellFormat wcf_center = new WritableCellFormat(wf_head);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(false); // 文字是否换行

			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.NONE, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.LEFT); // 文字水平对齐
			wcf_left.setWrap(true); // 文字是否换行

			/** ***************以下是EXCEL开头大标题********************* */
			 sheet.mergeCells(0, 0, Title.length-1, 0);
			 sheet.addCell(new Label(0, 0, fileName, wcf_Title));
			 sheet.setRowView(0, 400);//设置第一行行高为400 
			/** ***************以下是EXCEL第一行列标题********************* */
			for (int i = 0; i < Title.length; i++) {
				sheet.addCell(new Label(i, 1, Title[i], wcf_center));
				sheet.setColumnView(i, Title[i].length()+10);   //按表头设置列宽
			}
			/** ***************以下是EXCEL正文数据********************* */
			Field[] fields = null;
			int celli = 2;
			int cellj,k;
			
			for (Object obj : listContent) {
				cellj=0;
				k=0;
				fields=obj.getClass().getDeclaredFields();
				
				for (k = 0; k < fields.length; k++) {
					Field field=fields[k];
					field.setAccessible(true);
					Object feildObject=field.get(obj);
					if(feildObject!=null){
						sheet.addCell(new Label(cellj, celli, feildObject.toString(), wcf_left));
						cellj++;	
					}
				}
				
				celli++;
			}
			
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return workbook;
	}


}
