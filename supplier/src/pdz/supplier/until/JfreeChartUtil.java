package pdz.supplier.until;

import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints;
import java.io.File;
import java.io.FileOutputStream;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.ui.TextAnchor;

public class JfreeChartUtil {
	
	/**
     * 图片保存的根目录
     * @param filename
     * @return
     */
    public String Savepath(){
        String path = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        String testpath = path.substring(0,path.lastIndexOf("WEB-INF"));
        String filepath = testpath+"images/";
        String JFpath = filepath +"core/";
        return JFpath;//Tomcat的中webapps目录下项目的images文件夹
    }
    
    /**
     *  柱状图,折线图 数据集 方法
     */
    public CategoryDataset getBarData( String[] rowKeys,     
            String[] columnKeys,double[][] data) {     
        return DatasetUtilities.createCategoryDataset(rowKeys, columnKeys, data);     
    
    }
    public void isChartPathExist(String chartPath) {     
        File file = new File(chartPath);     
        if (!file.exists()) {     
            file.mkdirs();     
        // log.info("CHART_PATH="+CHART_PATH+"create.");     
        }
             
    }
    /**
     * 
     * @Title: createBarChart3D   
     * @Description: TODO(柱状图样式)   
     * @param @param charTitle
     * @param @param x
     * @param @param y
     * @param @param Dataset
     * @param @param charName
     * @param @return    设定文件   
     * @return JFreeChart    返回类型   
     * @throws
     */
    public JFreeChart createBarChart3D(String charTitle, String x, String y, CategoryDataset Dataset, String charName){
    	JFreeChart chart = ChartFactory.createLineChart(charTitle, x, y,     
                Dataset, PlotOrientation.VERTICAL, true, true, false); 
    	chart.setTitle(new TextTitle(charTitle, new Font("黑体", Font.ITALIC,22)));
    	CategoryPlot categoryPlot = chart.getCategoryPlot();  
		NumberAxis numberaxis = (NumberAxis) categoryPlot.getRangeAxis();  
        // 设置纵坐标的标题字体和大小  
        numberaxis.setLabelFont(new Font("宋体", Font.CENTER_BASELINE, 13));  
        // 设置丛坐标的坐标值的字体颜色  
        numberaxis.setLabelPaint(Color.BLACK);  
        // 设置丛坐标的坐标轴标尺颜色  
        numberaxis.setTickLabelPaint(Color.RED);  
        // 获取横坐标  
        CategoryAxis domainAxis = categoryPlot.getDomainAxis();  
        // 设置横坐标的标题字体和大小  
        domainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 13));  
        // 设置横坐标的坐标值的字体颜色  
        domainAxis.setTickLabelPaint(Color.BLACK);  
        // 设置横坐标的坐标值的字体  
        domainAxis.setTickLabelFont(new Font("黑体", Font.PLAIN, 10)); 
        //设置横坐标的坐标值的字体倾斜角度
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(
                20 * Math.PI / 180.0));
        // 这句代码解决了底部汉字乱码的问题  
        chart.getLegend().setItemFont(new Font("黑体", 0, 16));  
        //在柱体的上面显示数据
        BarRenderer3D custombarrenderer3d = new BarRenderer3D();
        //数据字体颜色
        custombarrenderer3d.setBaseItemLabelPaint(Color.BLACK);
        custombarrenderer3d.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        custombarrenderer3d.setBaseItemLabelsVisible(true);
      //默认的数字显示在柱子中，通过如下两句可调整数字的显示
      //注意：此句很关键，若无此句，那数字的显示会被覆盖，给人数字没有显示出来的问题
        custombarrenderer3d.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
//        custombarrenderer3d.setItemLabelAnchorOffset(10D);
        categoryPlot.setRenderer(custombarrenderer3d);
        //图片路径
//        FileOutputStream fos_jpg = null;     
//        try {     
//            isChartPathExist(Savepath());     
//            String chartName = Savepath() + charName;     
//            fos_jpg = new FileOutputStream(chartName);     
//    
//            // 将报表保存为JPG文件     
//            ChartUtilities.writeChartAsJPEG(fos_jpg, chart,780, 370);             
//               
//        } catch (Exception e) {     
//            e.printStackTrace();     
//            return null;     
//        } finally {     
//            try {     
//                fos_jpg.close();     
//            } catch (Exception e) {     
//                e.printStackTrace();     
//            }     
//        }
        return chart;    
 }
    
    /**
     * 折线图样式
     * @param chartTitle
     * @param x
     * @param y
     * @param xyDataset
     * @param charName
     * @return
     */
     public JFreeChart createTimeXYChar(String chartTitle, String x, String y,     
                CategoryDataset xyDataset, String charName) {  
            JFreeChart chart = ChartFactory.createLineChart(chartTitle, x, y,     
                    xyDataset, PlotOrientation.VERTICAL, true, true, false);   
            // 设置消除字体的锯齿渲染(解决中文问题)
            chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
            chart.setTextAntiAlias(false);     
            chart.setBackgroundPaint(Color.RED);     
            // 设置图标题的字体重新设置title     
            Font font = new Font("宋体", Font.BOLD, 20);     
            TextTitle title = new TextTitle(chartTitle); 
            title.getBackgroundPaint();
            //设置字体颜色
            title.setPaint(Color.BLUE);
            title.setFont(font);     
            chart.setTitle(title);
            CategoryPlot plot = chart.getCategoryPlot();
            CategoryAxis valueAxis = plot.getDomainAxis();
            // 设置x轴上面的字体
            valueAxis.setTickLabelFont(new Font("宋书", Font.ITALIC, 12));
            // 设置X轴的标题文字
            valueAxis.setLabelFont(new Font("黑体", Font.CENTER_BASELINE, 13));
            //设置主标题
            TextTitle subtitle = new TextTitle("日时间段访问量对比",new Font("宋体", Font.BOLD, 12));
            chart.addSubtitle(subtitle);
            NumberAxis numberAxis = (NumberAxis) plot.getRangeAxis();
            // 设置y轴上的字体
            numberAxis.setTickLabelFont(new Font("宋体", Font.PLAIN, 13));
            // 设置y轴上的标题字体
            numberAxis.setLabelFont(new Font("黑体", Font.CENTER_BASELINE, 13));
            // 设置底部的字体
            chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));
            // 设置面板字体     
            Font labelFont = new Font("SansSerif", Font.TRUETYPE_FONT, 12);    
            chart.setBackgroundPaint(Color.WHITE);    
            CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();     
            // x轴 // 分类轴网格是否可见     
            categoryplot.setDomainGridlinesVisible(true);     
            // y轴 //数据轴网格是否可见     
            categoryplot.setRangeGridlinesVisible(true);    
            categoryplot.setRangeGridlinePaint(Color.WHITE);// 虚线色彩    
            categoryplot.setDomainGridlinePaint(Color.WHITE);// 虚线色彩    
            categoryplot.setBackgroundPaint(Color.lightGray);    
            // 设置轴和面板之间的距离     
            // categoryplot.setAxisOffset(new RectangleInsets(5D, 5D, 5D, 5D));    
            CategoryAxis domainAxis = categoryplot.getDomainAxis();    
            domainAxis.setLabelFont(labelFont);// 轴标题    
            domainAxis.setTickLabelFont(labelFont);// 轴数值    
           // domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(0.4)); 
            domainAxis.setCategoryLabelPositions(CategoryLabelPositions.STANDARD); // 横轴上的    
            // Lable     
            // 45度倾斜     
            // 设置距离图片左端距离    
            domainAxis.setLowerMargin(0.0);     
            // 设置距离图片右端距离     
            domainAxis.setUpperMargin(0.0);             
            NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();     
            numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());     
            numberaxis.setAutoRangeIncludesZero(true);    
            // 获得renderer 注意这里是下嗍造型到lineandshaperenderer！！     
            LineAndShapeRenderer lineandshaperenderer = (LineAndShapeRenderer) categoryplot.getRenderer();     
            //XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer) categoryplot.getRenderer();//改变曲线颜色
            lineandshaperenderer.setBaseShapesVisible(true); // series 点（即数据点）可见    
            lineandshaperenderer.setBaseLinesVisible(true); // series 点（即数据点）间有连线可见 
            // 显示折点数据     
            lineandshaperenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());     
            lineandshaperenderer.setBaseItemLabelsVisible(true);      
            //图片路径
//            FileOutputStream fos_jpg = null;     
//            try {     
//                isChartPathExist(Savepath());     
//                String chartName = Savepath() + charName;     
//                fos_jpg = new FileOutputStream(chartName);     
//        
//                // 将报表保存为JPG文件     
//                ChartUtilities.writeChartAsJPEG(fos_jpg, chart,780, 370);             
//                   
//            } catch (Exception e) {     
//                e.printStackTrace();     
//                return null;     
//            } finally {     
//                try {     
//                    fos_jpg.close();     
//                } catch (Exception e) {     
//                    e.printStackTrace();     
//                }     
//            }
            return chart;    
     }


}
