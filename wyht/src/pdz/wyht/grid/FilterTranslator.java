package pdz.wyht.grid;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;

public class FilterTranslator {

    //几个前缀/后缀
    protected char leftToken = '[';
    protected char paramPrefixToken = '@';
    protected char rightToken = ']';
    protected char groupLeftToken = '(';
    protected char groupRightToken = ')';
    protected char likeToken = '%';
    
  /// <summary>
    /// 参数计数器
    /// </summary>
    private int paramCounter = 0;

    //几个主要的属性
    public FilterGroup Group ;
    public String CommandText ;
    
    public FilterGroup getGroup() {
		return Group;
	}

	public void setGroup(FilterGroup group) {
		Group = group;
	}

	public String getCommandText() {
		return CommandText;
	}

	public void setCommandText(String commandText) {
		CommandText = commandText;
	}

	public void Translate()
    {
        this.CommandText = TranslateGroup(this.Group);
    }
    
    public String TranslateGroup(FilterGroup group)
    {
        StringBuilder bulider = new StringBuilder();
        if (group == null) return " 1=1 ";
        boolean appended = false;
//        bulider.append(groupLeftToken);
        List<FilterRule> rules= group.getRules();
        if(rules!=null){
        	for (FilterRule rule : rules) {
        		if (appended)
                    bulider.append(GetOperatorQueryText(group.getOp()));
                bulider.append(TranslateRule(rule));
                appended = true;
			}
        }

//        bulider.append(groupRightToken);
        if (appended == false) return " 1=1 ";
        return bulider.toString();
    }

    public String TranslateRule(FilterRule rule)
    {
        StringBuilder bulider = new StringBuilder();
        if (rule == null) return " 1=1 ";
        if (rule.getValue().toString().equalsIgnoreCase("all")) {
			return " 1=1 ";
		}
        
//        bulider.append(leftToken + rule.getField() + rightToken);
        bulider.append(rule.getField());

        //操作符
        bulider.append(GetOperatorQueryText(rule.getOp()));

        String op = rule.getOp();
        
        if (op.equalsIgnoreCase("like")) 
        {
            String value = rule.getValue().toString();
//            System.out.println("value:"+value);
            if (!value.startsWith(String.valueOf(this.likeToken)))
            {
            	rule.setValue(this.likeToken + value+this.likeToken);
            }
            bulider.append(CreateFilterParam(rule.getValue(), rule.getType()));
            
        }else if (op.equalsIgnoreCase("in") ||op.equalsIgnoreCase("notin"))
        {
            String[] values = rule.getValue().toString().split(",");
            boolean appended = false;
            bulider.append("(");
            for (String value : values) {
            	if (appended) bulider.append(",");
            	bulider.append(CreateFilterParam(value, rule.getType()));
            	appended = true;
			}
            
            bulider.append(")");
        } 
        //is null 和 is not null 不需要值
        else if (op.equalsIgnoreCase("isnull") || op.equalsIgnoreCase("isnotnull"))
        {
        	bulider.append(rule.getValue().toString() + GetOperatorQueryText(op));
        } else{
        	bulider.append(CreateFilterParam(rule.getValue(),rule.getType()));
        }
        
        
        return bulider.toString();
    }
    
    private String CreateFilterParam(Object value,String type)
    {
        String paramName = "";
        if ((type==null)||(type.equalsIgnoreCase("string"))) {
			paramName="'"+value.toString()+"'";
		}else{
			paramName=value.toString();
		}

        return paramName;
    }
    
    public static String GetOperatorQueryText(String op)
    {
    	
    	if (op.equalsIgnoreCase("add")) {
    		return " + ";
		}else if (op.equalsIgnoreCase("bitwiseand")) {
			return " & ";
		}else if (op.equalsIgnoreCase("bitwisenot")) {
			return " ~ ";
		}else if (op.equalsIgnoreCase("bitwiseor")) {
			return " | ";
		}else if (op.equalsIgnoreCase("bitwisexor")) {
			return " ^ ";
		}else if (op.equalsIgnoreCase("divide")) {
			return " / ";
		}else if (op.equalsIgnoreCase("equal")) {
			return " = ";
		}else if (op.equalsIgnoreCase("greater")) {
			return " > ";
		}else if (op.equalsIgnoreCase("greaterorequal")) {
			return " >= ";
		}else if (op.equalsIgnoreCase(" is null ")) {
			return " is null ";
		}else if (op.equalsIgnoreCase(" is not null ")) {
			return " is not null ";
		}else if (op.equalsIgnoreCase("less")) {
			return " < ";
		}else if (op.equalsIgnoreCase("lessorequal")) {
			return " <= ";
		}else if (op.equalsIgnoreCase("like")) {
			return " like ";
		}else if (op.equalsIgnoreCase("modulo")) {
			return " % ";
		}else if (op.equalsIgnoreCase("multiply")) {
			return " * ";
		}else if (op.equalsIgnoreCase("notequal")) {
			return " <> ";
		}else if (op.equalsIgnoreCase("subtract")) {
			return " - ";
		}else if (op.equalsIgnoreCase("and")) {
			return " and ";
		}else if (op.equalsIgnoreCase("or")) {
			return " or ";
		}else if (op.equalsIgnoreCase("in")) {
			return " in ";
		}else if (op.equalsIgnoreCase("notin")) {
			return " not in ";
		}else {
			return " = ";
		}
    }
}
