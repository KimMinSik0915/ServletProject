<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="pageObject" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 리스트</title>
  <style type="text/css">
  
   .dataRow:hover {
   		cursor: pointer;
   		background: #eee;
   }
   
  </style>
  
  <script type="text/javascript">
   // 객체 선택에 문제가 있다. 아래 document가 로딩이 되면 실행되는 스크립트 작성
   // jQuery에서 사용하는 $(function(){처리문;}) = jquery(function(){처리문;})
   $(function () {		// jQuery에서 익명함수를 전달해서 저장해 놓았다가 Document가 로딩이 다 되면 호출해서 처리해준다.
	   // data 한줄 선택하기와 클릭 이벤트 처리
	   $(".dataRow").click(function() {
// 			alert($(this)); 
			// $(this) = 이벤트가 일어난(현재 처리되고 있는 객체) 자신을 의미
			// .find(selector) = .앞의 객체 안에서 선택한 것을 찾아라
			// .text() = 선택된 객체(tag) 사이에 문자를 가져온다.(tag를 가져오는것이 아닌 문자만 가져온다).
			// .text(data) = 객체(tag) 사이에 문자를 세팅한다.
			var no = $(this).find(".no").text();
			
			// 조회수 1 증가를 위해 inc=1을 넘겨준다.
			location = "view.do?no=" + no + "&inc=1";
	   });
   });
  </script>
  
</head>
<body>

<div class="container">
 <h1>게시판 리스트</h1>
 <table class="table">
  <!-- 제목 -->
  <thead>
   <tr>
    <th>번호</th>
    <th>제목</th>
    <th>작성자</th>
    <th>작성일</th>
    <th>조회수</th>
   </tr>
  </thead>
  
  <tbody>
   <!-- list : request -> vo : pageContext -->
   <!-- 데이터가 있는 만큼 반복이 되어지는 부분(시작) -->
   <c:forEach items="${list }" var="vo">
    <tr class="dataRow">
     <td class="no">${vo.no }</td>
     <td>${vo.title }</td>
     <td>${vo.writer }</td>
     <td>${vo.writeDate }</td>
     <td>${vo.hit }</td>
    </tr>
   </c:forEach>
   <!-- 데이터가 있는 만큼 반복이 되어지는 부분(끝) -->
  </tbody>
  
  <tfoot>
   <tr>
    <td colspan="5">
     <a href="writeForm.do" class="btn btn-default">글 쓰기</a>
    </td>
   </tr>
   <tr>
    <td colspan="5">
     <pageObject:pageNav listURI="list.do" pageObject="${pageObject }"/> 
    </td>
   </tr>
  </tfoot>
 </table>
</div>
</body>
</html>