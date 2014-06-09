<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="pdz.wyht.bean.WyhtUser"%>

<%


Object obj=request.getSession().getAttribute("wyuser");
if(obj==null){
	out.println("1");
}else{
	WyhtUser user=(WyhtUser)obj;
	out.println(user.getUserName());
}
%>