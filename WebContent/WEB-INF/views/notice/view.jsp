<%@page import="java.util.List"%>
<%@page import="com.webjjang.main.controller.Beans"%>
<%@page import="com.webjjang.main.controller.ExeService"%>
<%@page import="com.webjjang.board.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글 보기</title>
  <script type="text/javascript">
   // 객체 선택에 문제가 있다. 아래 document가 로딩이 되면 실행되는 스크립트 작성
   // jQuery에서 사용하는 $(function(){처리문;}) = jquery(function(){처리문;})
   $(function () {		// jQuery에서 익명함수를 전달해서 저장해 놓았다가 Document가 로딩이 다 되면 호출해서 처리해준다.
	   // 삭제 버튼을 클릭하면 실제적으로 삭제를 진행 할 것인지에 대한 여부를 물어본다.
	   $("#deleteBtn").click(function() {
			if(!confirm("정말로 삭제 하시겠습니까?"))
				return false;	// a tag의 이동 취소  
	   });
   });
  </script>
  
</head>
<body>

<div class="container">
 <h1>게시판 글 보기</h1>
 <table class="table">
  
 <tbody> 
  <!-- 데이터가 있는 만큼 반복이 되어지는 부분(시작) -->
   <tr class="dataRow">
    <th>글 번호</th>
    <td class="no">${vo.no }</td>
   </tr>
   
   <tr>
    <th>제목</th>
    <td>${vo.title }</td>
   </tr>
   
   <tr>
    <th>내용</th>
    <td><pre style="background: #fff; border: none; padding: 0;">${vo.content }</pre></td>
   </tr>
   
   <tr>
    <th>공지 시작일</th>
    <td>${vo.startDate }</td>
   </tr>
   
   <tr>
    <th>공지 종료일</th>
    <td>${vo.endDate }</td>
   </tr>
   
   <tr>
    <th>공지 수정일</th>
    <td>${vo.updateDate }</td>
   </tr>
  <!-- 데이터가 있는 만큼 반복이 되어지는 부분(끝) -->
 </tbody>
  
  <tfoot>
   <tr>
    <td colspan="2">
     <a href="updateForm.do?no=${vo.no }&page=${pageObject.page }&perPageNum=${pageObject.perPageNum }" class="btn btn-default">글 수정</a>
     <a href="delete.do?no=${vo.no }&perPageNum=${pageObject.perPageNum }" class="btn btn-default" id="deleteBtn">글 삭제</a>
     <a href="list.do?page=${pageObject.page }&perPageNum=${pageObject.perPageNum }" class="btn btn-default">리스트</a>
    </td> 
   </tr>
  </tfoot>
  
 </table>
</div>
</body>
</html>