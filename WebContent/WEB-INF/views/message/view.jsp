<%@page import="com.webjjang.util.filter.AuthorityFilter"%>
<%@page import="com.webjjang.main.controller.Beans"%>
<%@page import="com.webjjang.main.controller.ExeService"%>
<%@page import="com.webjjang.message.vo.MessageVO"%>
<%@page import="com.webjjang.member.vo.LoginVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 자바
// 넘어오는 데이터 받아오기 - 메시지 번호
String strNo = request.getParameter("no");

// long no = Long.parseLong(strNo);

// 내 아이디 정보를 꺼내야 한다.
String id = ((LoginVO)session.getAttribute("login")).getId();

// vo객체 생성 및 데이터 세팅
MessageVO vo = new MessageVO();

vo.setNo(Long.parseLong(request.getParameter("no")));
vo.setAccepter(id);	// 받는 사람이 본인인 데이터를 읽기 표시하기 위해서 accepter에 id를 저장

// DB처리 데이터 가져오기
// 1. 받은 사람이 로그인 한 사람과 같아야 하며(받은 메시지), 번호가 같고 받은 날짜가 null인 메시지(읽지 않은 메시지)는 읽음 표시를 한다.(AcceptDate를 현재 날짜로 넣어준다. : UPDATE)
// 2. 메시지 번호에 맞는 전체 메시지 정보 가져오기

MessageVO viewVO = (MessageVO)ExeService.execute(Beans.get(AuthorityFilter.url), vo);

// 서버 객체에 저장
request.setAttribute("vo", viewVO);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메시지 보기</title>
<script type="text/javascript">

	$(function () {
		
		$("#delBtn").click(function() {
			
			if(!confirm("정말 삭제하시겠습니까?")) {
				
				return false;
				
			}
			
		});
		
	});

</script>
</head>
<body>
 <div class="container">
  <h1>메시지 보기</h1>
  <table class="table">
  
   <tr>
    <th class="no">번호</th>
    <td>${vo.no }</td>
   </tr>
   
   <tr>
    <th>내용</th>
    <td><pre style="border: none; padding: 0;">${vo.content }</pre></td>
   </tr>
   
   <tr>
    <th>보낸 사람</th>
    <td>${vo.sender }</td>
   </tr>
   
   <tr>
    <th>보낸 날짜</th>
    <td>${vo.sendDate }</td>
   </tr>
   
   <tr>
    <th>받은사람</th>
    <td>${vo.accepter }</td>
   </tr>
   
   <tr>
    <th>받은 날짜</th>
    <td>${vo.acceptDate }</td>
   </tr>
   
   <tr>
    <td colspan="5">
     <a href="list.jsp" class="btn btn-default">리스트</a>
     <a href="delete.jsp?no=${vo.no }" class="btn btn-default" id="delBtn">삭제</a>
     <a href="unRead.jsp">읽지 않음</a>
    </td>
   </tr>
   
  </table>
 </div>
</body>
</html>