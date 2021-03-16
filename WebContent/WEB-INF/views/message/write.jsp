<%@page import="com.webjjang.main.controller.ExeService"%>
<%@page import="com.webjjang.util.filter.AuthorityFilter"%>
<%@page import="com.webjjang.main.controller.Beans"%>
<%@page import="com.webjjang.message.vo.MessageVO"%>
<%@page import="com.webjjang.member.vo.LoginVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 자바 부분입니다.

// 넘어오는 데이터 수집 : 받는 사람 아이디, 내용

String accepter = request.getParameter("accepter");

String content = request.getParameter("content");

// session에서 내 아이디 가져오기
// session의 내용은 /member/login.jsp 확인. 이 때 key = login이라는 것이 다르면 null이 나온다.
LoginVO vo = (LoginVO) session.getAttribute("login");

String sender = vo.getId();

// vo 객체에 데이터 넣기
MessageVO messagevo = new MessageVO();

messagevo.setContent(content);
messagevo.setSender(sender);
messagevo.setAccepter(accepter);

// DB처리 : jsp - service - dao - db
// ExeService.execute(실행할 서비스, Service에 전달되는 데이터)
ExeService.execute(Beans.get(AuthorityFilter.url), messagevo) ;

// 리스트로 자동이동
response.sendRedirect("list.jsp");

%>