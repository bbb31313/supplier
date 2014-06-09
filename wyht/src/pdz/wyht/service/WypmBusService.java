package pdz.wyht.service;

import java.io.File;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pdz.wyht.bean.WyAccount;
import pdz.wyht.bean.WyHT;
import pdz.wyht.bean.WyQueryReport;
import pdz.wyht.bean.WyZLht;
import pdz.wyht.until.DateUtil;

@Service @Transactional
public class WypmBusService {

	@Resource SessionFactory sessionFactory;
	
	public boolean addObj(Object obj){
		try {
			sessionFactory.getCurrentSession().save(obj);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean updateObj(Object obj){
		try {
			sessionFactory.getCurrentSession().update(obj);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean delObj(Object obj){
		try {
			sessionFactory.getCurrentSession().delete(obj);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Object getObj(Class arg0,Serializable arg1){
		return sessionFactory.getCurrentSession().get(arg0, arg1);
	}
	
	public boolean saveORupdateObj(Object obj) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(obj);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	

	
}
