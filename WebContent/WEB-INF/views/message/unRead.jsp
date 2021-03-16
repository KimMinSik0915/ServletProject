<%@page import="com.webjjang.member.vo.LoginVO"%>
<%@page import="com.webjjang.message.vo.MessageVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
MessageVO messageVO = new MessageVO();

messageVO.setNo(Long.parseLong(request.getParameter("no")));
messageVO.setAccepter(((LoginVO)session.getAttribute("login")).getId());


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>