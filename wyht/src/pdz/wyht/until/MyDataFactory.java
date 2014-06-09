package pdz.wyht.until;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pdz.wyht.bean.WyZLht;
import pdz.wyht.service.WyBusService;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRAbstractBeanDataSourceProvider;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class MyDataFactory extends JRAbstractBeanDataSourceProvider {

	public MyDataFactory(Class<WyZLht> beanClass) {
		super(beanClass);
		// TODO Auto-generated constructor stub
	}

	public JRDataSource create(JasperReport arg0) throws JRException {
		List<WyZLht> wList=getAllzl();
		return new JRBeanCollectionDataSource(wList);
	}
	
	public List<WyZLht> getAllzl() {
//		ApplicationContext act=new ClassPathXmlApplicationContext("beans.xml");
//		WyBusService ws=(WyBusService)act.getBean("wyBusService");
		
		
		List<WyZLht> wList=new ArrayList<WyZLht>();
		WyZLht ht=new WyZLht();
		ht.setHth(12L);
		ht.setWldmname("步步高");
		wList.add(ht);
		return wList;
	}

	public void dispose(JRDataSource arg0) throws JRException {
		// TODO Auto-generated method stub

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		MyDataFactory dataFactory=new MyDataFactory(WyZLht.class);
		int i=dataFactory.getAllzl().size();
		System.out.println(i);
	}

}
