<%@page import="com.webjjang.main.controller.Beans"%>
<%@page import="com.webjjang.main.controller.ExeService"%>
<%@page import="com.webjjang.notice.vo.NoticeVO"%>
<%@page import="java.util.List"%>
<%@page import="com.webjjang.util.PageObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="pageObject" tagdir="/WEB-INF/tags" %> 
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 리스트</title>
<style type="text/css">

	.dataRow:hover {
	
		cursor: pointer;
		background: #eee;
	
	}

</style>
<script type="text/javascript">

	$(function () {
		
		$(".dataRow").click(function () {
			
			//alert("click");
			
			var no = $(this).find(".no").text();
			
			//alert(no);
			
			location = "view.do?no=" + no + "&page=${pageObject.page}&perPageNum=${pageObject.perPageNum }";
			
		});
		
	});

</script>
</head>
<body>
 <div class="container">
  <h1>공지사항 리스트</h1>
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
   
    <c:forEach items="${list }" var="vo">
     <tr class="dataRow">
      <td class="no">${vo.no }</td>
      <td>${vo.title }</td>
      <td>${vo.startDate }</td>
      <td>${vo.endDate }</td>
      <td>${vo.updateDate }</td>
     </tr>
    </c:forEach>
    
    <tr>
     <td colspan="5">
      <a href="writeForm.do?&perPageNum=${pageObject.perPageNum }" class="btn btn-default">공지 등록</a>
     </td>
    </tr>
    
    <tr>
     <td colspan="5">
      <pageObject:pageNav listURI="list.do" pageObject="${pageObject }"/>
     </td>
    </tr>
    
   </table>  
 </div>
</body>
</html>