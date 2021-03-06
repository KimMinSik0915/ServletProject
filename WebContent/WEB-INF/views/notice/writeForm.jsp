<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지 등록</title>
<!-- 부트 스트랩 라이브러리는 siteMash - default_decorator.do에서 통합적으로 등록해서 사용하도록 한다. -->
</head>
<body>
 <div class="container">
  <h1>공지등록</h1>
  <form action="write.do" method="post">
   <input name="perPageNum" type="hidden" value="${pageObject.perPageNum }">
  
   <div class="form-group">
    <label for="title">제목</label>
    <input name="title" id="title" class="form-control">
   </div>
   
   <div class="form-group">
    <label for="content">내용</label>
    <textarea rows="5" name="content" id="content" class="form-control" ></textarea>
   </div>
   
   <div class="form-group">
    <label for="startDate">공지 시작일</label>
    <input name="startDate" id="startDate" class="form-control" type="date">
   </div>
   
   <div class="form-group">
    <label for="endDate">공지 종료일</label>
    <input name="endDate" id="endDate" class="form-control" type="date">
   </div>
   
   <button class="btn btn-default">등록</button>
   
  </form>
 </div>
</body>
</html>