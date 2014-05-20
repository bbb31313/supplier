<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="pdz.supplier.bean.SupplierUser"%>

<%


Object obj=request.getSession().getAttribute("supuser");
if(obj==null){
	out.println("1");
}else{
	SupplierUser user=(SupplierUser)obj;
	out.println(user.getUserName());
}
%>