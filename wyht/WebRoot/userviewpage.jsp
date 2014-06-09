<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="pdz.wyht.bean.WyhtUser"%>

<%

String suppath = request.getContextPath();
String supbasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+suppath+"/";

Object obj=request.getSession().getAttribute("wyuser");
if(obj==null){
	out.println("<script type='text/javascript'>top.location.href='"+supbasePath+"login.htm'</script>");
}else{
	WyhtUser user=(WyhtUser)obj;
	if(user.getRole()!=1){
		out.println("<script type='text/javascript'>alert('您没有操作此功能的权限!');top.location.href='"+supbasePath+"login.htm'</script>");
	}
}
%>