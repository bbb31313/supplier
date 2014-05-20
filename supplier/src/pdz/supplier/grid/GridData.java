package pdz.supplier.grid;

import java.util.List;

import org.apache.struts2.json.annotations.JSON;


public class GridData {

	private int Total;
	private List Rows;
	
	@JSON(name="Total")
	public int getTotal() {
		return Total;
	}
	public void setTotal(int total) {
		Total = total;
	}
	
	@JSON(name="Rows")
	public List getRows() {
		return Rows;
	}
	public void setRows(List rows) {
		Rows = rows;
	}

}
