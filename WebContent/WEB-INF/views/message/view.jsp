<%@page import="com.webjjang.util.filter.AuthorityFilter"%>
<%@page import="com.webjjang.main.controller.Beans"%>
<%@page import="com.webjjang.main.controller.ExeService"%>
<%@page import="com.webjjang.message.vo.MessageVO"%>
<%@page import="com.webjjang.member.vo.LoginVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
     <a href="list.do" class="btn btn-default">리스트</a>
     <a href="delete.do?no=${vo.no }" class="btn btn-default" id="delBtn">삭제</a>
     <a href="unRead.do">읽지 않음</a>
    </td>
   </tr>
   
  </table>
 </div>
</body>
</html>