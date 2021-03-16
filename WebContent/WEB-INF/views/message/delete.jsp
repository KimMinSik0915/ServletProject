<%@page import="com.webjjang.util.filter.AuthorityFilter"%>
<%@page import="com.webjjang.main.controller.Beans"%>
<%@page import="com.webjjang.main.controller.ExeService"%>
<%@page import="com.webjjang.message.vo.MessageVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String strNo = request.getParameter("no");

System.out.println(strNo);

long no = Long.parseLong(strNo);

System.out.println(no);

ExeService.execute(Beans.get(AuthorityFilter.url), no);

response.sendRedirect("list.jsp");
%>
