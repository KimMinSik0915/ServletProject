<%@page import="com.webjjang.main.controller.Beans"%>
<%@page import="com.webjjang.main.controller.ExeService"%>
<%@page import="com.webjjang.member.vo.LoginVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="login_error.jsp" %>
<%
// 여기가 자바 입니다.

// 로그인 정보를 가져오는데 성공하면 로그인 처리를 한다.

// 데이터 받기
String id = request.getParameter("id");

String pw = request.getParameter("pw");

// 받은 데이터를 VO객체에 저장을 해서 넘겨준다.(하나의 데이터를 넘겨야 하기 때문에)

LoginVO vo = new LoginVO();

vo.setId(id);
vo.setPw(pw);

// DB처리 : 아이디, 이름, 등급번호, 등급이름을 가져온다.

// JSP - Service - DAO
String url = request.getServletPath();

LoginVO loginVO = (LoginVO) ExeService.execute(Beans.get(url), vo);


// ID나 PW가 틀린경우의 처리
if (loginVO == null) throw new Exception("로그인 정보를 확인해 주세요");
	
// 로그인 처리
session.setAttribute("login", loginVO);

response.sendRedirect("../main/main.jsp");

%>