package pdz.supplier.until;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import pdz.supplier.grid.FilterGroup;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

public class JsonUtil {
    /**    
     * 从一个JSON 对象字符格式中得到一个java对象    
     *     
     * @param jsonString    
     * @param pojoCalss    
     * @return    
     */     
    public static Object getObject4JsonString(String jsonString, Class pojoCalss) {      
        Object pojo;      
        JSONObject jsonObject = JSONObject.fromObject(jsonString);      
        pojo = JSONObject.toBean(jsonObject, pojoCalss);      
        return pojo;      
    }      
     
     
    /**    
     * 从json HASH表达式中获取一个map，改map支持嵌套功能    
     *     
     * @param jsonString    
     * @return    
     */     
    public static Map getMap4Json(String jsonString) {      
        JSONObject jsonObject = JSONObject.fromObject(jsonString);      
        Iterator keyIter = jsonObject.keys();      
        String key;      
        Object value;      
        Map valueMap = new HashMap();      
     
        while (keyIter.hasNext()) {      
            key = (String) keyIter.next();      
            value = jsonObject.get(key);      
            valueMap.put(key, value);      
        }      
     
        return valueMap;      
    }      
     
     
    /**    
     * 从json数组中得到相应java数组    
     *     
     * @param jsonString    
     * @return    
     */     
    public static Object[] getObjectArray4Json(String jsonString) {      
        JSONArray jsonArray = JSONArray.fromObject(jsonString);      
        return jsonArray.toArray();      
     
    }      
     
     
    /**    
     * 从json对象集合表达式中得到一个java对象列表    
     *     
     * @param jsonString    
     * @param pojoClass    
     * @return    
     */     
    public static List getList4Json(String jsonString, Class pojoClass) {      
     
        JSONArray jsonArray = JSONArray.fromObject(jsonString);      
        JSONObject jsonObject;      
        Object pojoValue;      
     
        List list = new ArrayList();      
        for (int i = 0; i < jsonArray.size(); i++) {      
     
            jsonObject = jsonArray.getJSONObject(i);      
            pojoValue = JSONObject.toBean(jsonObject, pojoClass);      
            list.add(pojoValue);      
     
        }      
        return list;      
     
    }      
     
     
    /**    
     * 从json数组中解析出java字符串数组    
     *     
     * @param jsonString    
     * @return    
     */     
    public static String[] getStringArray4Json(String jsonString) {      
     
        JSONArray jsonArray = JSONArray.fromObject(jsonString);      
        String[] stringArray = new String[jsonArray.size()];      
        for (int i = 0; i < jsonArray.size(); i++) {      
            stringArray[i] = jsonArray.getString(i);      
     
        }      
     
        return stringArray;      
    }      
     
     
    /**    
     * 从json数组中解析出javaLong型对象数组    
     *     
     * @param jsonString    
     * @return    
     */     
    public static Long[] getLongArray4Json(String jsonString) {      
     
        JSONArray jsonArray = JSONArray.fromObject(jsonString);      
        Long[] longArray = new Long[jsonArray.size()];      
        for (int i = 0; i < jsonArray.size(); i++) {      
            longArray[i] = jsonArray.getLong(i);      
     
        }      
        return longArray;      
    }      
     
     
    /**    
     * 从json数组中解析出java Integer型对象数组    
     *     
     * @param jsonString    
     * @return    
     */     
    public static Integer[] getIntegerArray4Json(String jsonString) {      
     
        JSONArray jsonArray = JSONArray.fromObject(jsonString);      
        Integer[] integerArray = new Integer[jsonArray.size()];      
        for (int i = 0; i < jsonArray.size(); i++) {      
            integerArray[i] = jsonArray.getInt(i);      
     
        }      
        return integerArray;      
    }      
    
    /**    
     * 从json数组中解析出java Integer型对象数组    
     *     
     * @param jsonString    
     * @return    
     */     
    public static Double[] getDoubleArray4Json(String jsonString) {      
     
        JSONArray jsonArray = JSONArray.fromObject(jsonString);      
        Double[] doubleArray = new Double[jsonArray.size()];      
        for (int i = 0; i < jsonArray.size(); i++) {      
            doubleArray[i] = jsonArray.getDouble(i);      
     
        }      
        return doubleArray;      
    }      
     
     
    /**    
     * 将java对象转换成json字符串    
     *     
     * @param javaObj    
     * @return    
     */     
    public static String getJsonString4JavaPOJO(Object javaObj) {      
     
        JSONObject json;      
        json = JSONObject.fromObject(javaObj);      
        return json.toString();      
     
    }      
    
  //从普通的Bean转换为字符串
    public static String getJson(Object o){
        JSONObject jo=JSONObject.fromObject(o);
        return jo.toString();
    }
    //从Java的列表转换为字符串
    public static String getJson(List list){
        JSONArray ja=JSONArray.fromObject(list);
        return ja.toString();
    }
    //从Java对象数组转换为字符串
    public static String getJson(Object[] arry){
        JSONArray ja=JSONArray.fromObject(arry);
        return ja.toString();
    }
    //从json格式的字符串转换为Map对象
    public static Map getObject(String s){
        return     JSONObject.fromObject(s);
    }
    //从json格式的字符串转换为List数组
    public static List getArray(String s){
        return JSONArray.fromObject(s);
    }
    //从json格式的字符串转换为某个Bean
    public static Object getObject(String s,Class cls){
        JSONObject jo=JSONObject.fromObject(s);
        return JSONObject.toBean(jo, cls);
    }
    //从json格式的字符串转换为某类对象的数组
    public static Object getArray(String s,Class cls){
        JSONArray ja=JSONArray.fromObject(s);
        return JSONArray.toArray(ja, cls);
    }   
     
    /**    
     * 将java对象转换成json字符串,并设定日期格式    
     *     
     * @param javaObj    
     * @param dataFormat    
     * @return    
     */     
    public static String getJsonString4JavaPOJO(Object javaObj,      
            String dataFormat) {      
     
        JSONObject json;      
        JsonConfig jsonConfig = configJson(dataFormat);      
        json = JSONObject.fromObject(javaObj, jsonConfig);      
        return json.toString();      
     
    }      
     
    /**    
     * JSON 时间解析器具    
     *     
     * @param datePattern    
     * @return    
     */     
    public static JsonConfig configJson(String datePattern) {      
        JsonConfig jsonConfig = new JsonConfig();      
        jsonConfig.setExcludes(new String[] { "" });      
        jsonConfig.setIgnoreDefaultExcludes(false);      
        jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);      
        jsonConfig.registerJsonValueProcessor(Date.class,      
                new JsonDateValueProcessor(datePattern));      
     
        return jsonConfig;      
    }      
     
     
    /**    
     * 除去不想生成的字段（特别适合去掉级联的对象）+时间转换    
     * @param excludes 除去不想生成的字段    
     * @param datePattern    
     * @return    
     */     
    public static JsonConfig configJson(String[] excludes, String datePattern) {      
        JsonConfig jsonConfig = new JsonConfig();      
        jsonConfig.setExcludes(excludes);      
        jsonConfig.setIgnoreDefaultExcludes(true);      
        jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);      
        jsonConfig.registerJsonValueProcessor(Date.class,      
                new JsonDateValueProcessor(datePattern));      
     
        return jsonConfig;      
    } 
	 
 
	 /**
	 
	 * @param args
	 
	 */
	 
	 public static void main(String args) {
	 
		 String jsonstr="{\"roles\":[{\"field\":\"ID\",\"value\":112,\"op\":\"equal\"},{\"field\":\"Time\",\"value\":\"2011-3-4\",\"op\":\"greaterorequal\"}],\"op\":\"and\",\"groups\":null}";
		 JsonUtil.getObject4JsonString(jsonstr,FilterGroup.class);
	 }
	 

}
