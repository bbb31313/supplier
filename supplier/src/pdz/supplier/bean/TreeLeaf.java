package pdz.supplier.bean;

import java.util.List;

public class TreeLeaf {

	private String id;
	private String value;
	private String text;
	private String icon;
	private String fatherid;
	private List<TreeLeaf> children;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getFatherid() {
		return fatherid;
	}
	public void setFatherid(String fatherid) {
		this.fatherid = fatherid;
	}
	public List<TreeLeaf> getChildren() {
		return children;
	}
	public void setChildren(List<TreeLeaf> children) {
		this.children = children;
	}

	
	
}
