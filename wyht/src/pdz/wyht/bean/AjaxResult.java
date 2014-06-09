package pdz.wyht.bean;

import org.apache.struts2.json.annotations.JSON;

public class AjaxResult {

	private Object data;
	private String message;
	private boolean isError;
	
	@JSON(name="Data")
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	@JSON(name="Message")
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@JSON(name="IsError")
	public boolean isError() {
		return isError;
	}
	public void setError(boolean isError) {
		this.isError = isError;
	}
}
