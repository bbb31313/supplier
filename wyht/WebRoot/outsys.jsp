<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%

request.getSession().removeAttribute("wyuser");
out.println("<script type='text/javascript'>top.location.href='login.htm'</script>");

%>