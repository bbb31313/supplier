package pdz.supplier.grid;

import java.util.List;

public class FilterGroup {

	private List<FilterRule> rules;
	private String op;
	private List<FilterGroup> groups;
	
	public List<FilterGroup> getGroups() {
		return groups;
	}
	public void setGroups(List<FilterGroup> groups) {
		this.groups = groups;
	}
	public List<FilterRule> getRules() {
		return rules;
	}
	public void setRules(List<FilterRule> rules) {
		this.rules = rules;
	}
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}
}
