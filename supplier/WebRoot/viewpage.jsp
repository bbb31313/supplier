<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="pdz.supplier.bean.SupplierUser" %>
<%

String suppath = request.getContextPath();
String supbasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+suppath+"/";
boolean isAdmin=false;
Object obj=request.getSession().getAttribute("supuser");
if(obj==null){
	out.println("<script type='text/javascript'>top.location.href='"+supbasePath+"login.htm'</script>");
}else{
	SupplierUser sUser=(SupplierUser)obj;
	if(sUser.getRole()==1) isAdmin=true;
}
%>