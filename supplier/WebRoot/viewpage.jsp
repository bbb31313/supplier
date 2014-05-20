<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%

String suppath = request.getContextPath();
String supbasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+suppath+"/";

Object obj=request.getSession().getAttribute("supuser");
if(obj==null){
	out.println("<script type='text/javascript'>top.location.href='"+supbasePath+"login.htm'</script>");
}
%>