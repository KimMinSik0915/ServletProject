<%@page import="com.webjjang.main.controller.Beans"%>
<%@page import="com.webjjang.main.controller.ExeService"%>
<%@page import="com.webjjang.board.vo.BoardVO"%>
<%@page import="javax.websocket.SendResult"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
// 1. 넘어오는 데이터 수집
String strNo = request.getParameter("no");

long no = Long.parseLong(strNo);


// DB에 저장하는 쿼리 : delete.jsp -> service -> dao : 글 번호 데이터가 넘어온다.
String url = request.getServletPath();
int result = (Integer) ExeService.execute(Beans.get(url), no);


// 3. list로 이동
response.sendRedirect("list.jsp");

%>