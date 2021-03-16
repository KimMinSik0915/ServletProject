<%@page import="com.webjjang.util.filter.AuthorityFilter"%>
<%@page import="com.webjjang.main.controller.Beans"%>
<%@page import="com.webjjang.main.controller.ExeService"%>
<%@page import="com.webjjang.qna.vo.QnaVO"%>
<%@page import="com.webjjang.member.vo.LoginVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 자바 부분입니다.

// 넘어오는 data 받기
String title = request.getParameter("title");

String content = request.getParameter("content");

// session에서 id 받아오기
String id = ((LoginVO) session.getAttribute("login")).getId();


// VO객체를 생성하고 저장해 놓는다
QnaVO vo = new QnaVO();

vo.setTitle(title);
vo.setContent(content);
vo.setId(id);

// DB저장 처리 : JSP - service - dao - DB
ExeService.execute(Beans.get(AuthorityFilter.url), vo);

// 처리가 다 끝나면 자동으로 list로 이동
response.sendRedirect("list.jsp");


%>