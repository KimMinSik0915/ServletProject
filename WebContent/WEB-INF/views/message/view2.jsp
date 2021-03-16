<%@page import="com.webjjang.util.filter.AuthorityFilter"%>
<%@page import="com.webjjang.main.controller.Beans"%>
<%@page import="com.webjjang.main.controller.ExeService"%>
<%@page import="com.webjjang.message.vo.MessageVO"%>
<%@page import="com.webjjang.member.vo.LoginVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

String strNO = request.getParameter("no");

long no = Long.parseLong(strNO);

// System.out.println(no);

LoginVO loginvo = (LoginVO) session.getAttribute("login");

String id = loginvo.getId();

// System.out.println(id);

// MessageVO vo = (MessageVO) ExeService.execute(Beans.get(AuthorityFilter.url), no);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메시지 보기</title>
</head>
<body>
 <div class="container">
  <table class="table">
  
   <tr>
    <th>번호</th>
    <td>${vo.no }</td>
   </tr>
   
    <tr>
    <th>보낸사람</th>
    <td>${vo.sender }</td>
   </tr>
   
    <tr>
    <th></th>
    <td>${vo.no }</td>
   </tr>
   
    <tr>
    <th>번호</th>
    <td>${vo.no }</td>
   </tr>
   
    <tr>
    <th>번호</th>
    <td>${vo.no }</td>
   </tr>
   
  </table>
 </div>
</body>
</html>