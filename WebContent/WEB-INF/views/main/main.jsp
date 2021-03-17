<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
<style type="text/css">

	.noticeDataRow:hover, .boardDataRow:hover {
	
		background: #eee;
		cursor: pointer;
	
	}

</style>
<script type="text/javascript">

	$(function () {
		
		$(".noticeDataRow, .boardDataRow").click(function name() {		// click Event 처리
			
			var no = $(this).find(".no").text();
			
			if ($(this).hasClass("noticeDataRow")) {					// class를 확인해서 어디로 갈지 정한다
			
				//alert("notice click");
			
				location = "/notice/view.do?no=" + no;
			
			} else if ($(this).hasClass("boardDataRow")) {
				
				//alert("board click");
				
				location = "/board/view.do?no=" + no + "&inc=1"; 
				
			}
		
			//alert(no);
			
		});	
		
	});

</script>
</head>
<body>
 <div class="container">
  <h1>메인 페이지</h1>
  <br>
  <!-- 공지사항 자리 -->
  <div class="panel panel-default">
   <div class="panel-heading">공지사항</div>
   <div class="panel-body">
   <table class="table">
    <thead>
     <tr>
      <th>번호</th>
      <th>제목</th>
      <th>공지 시작일</th>
      <th>공지 종료일</th>
      <th>최근 수정일</th>
     </tr>
    </thead>
    <c:forEach items="${noticeList }" var="vo">
     <tr class="noticeDataRow">
      <td class="no">${vo.no }</td>
      <td>${vo.title }</td>
      <td>${vo.startDate }</td>
      <td>${vo.endDate }</td>
      <td>${vo.updateDate }</td>
     </tr>
    </c:forEach>
    </table> 
   </div>
  </div>
  <br>
  <div class="panel panel-default">
   <div class="panel-heading">게시판</div>
   <div class="panel-body">
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
      <c:forEach items="${boardList }" var="vo">
       <tr class="boardDataRow">
        <td class="no">${vo.no }</td>
        <td>${vo.title }</td>
        <td>${vo.writer }</td>
        <td>${vo.writeDate }</td>
        <td>${vo.hit }</td>
       </tr>
      </c:forEach>
     </tbody>
    </table>
   </div>
  </div>
  <br>  
 </div>
</body>
</html>