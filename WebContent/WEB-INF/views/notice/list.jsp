<%@page import="com.webjjang.main.controller.Beans"%>
<%@page import="com.webjjang.main.controller.ExeService"%>
<%@page import="com.webjjang.notice.vo.NoticeVO"%>
<%@page import="java.util.List"%>
<%@page import="com.webjjang.util.PageObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="pageObject" tagdir="/WEB-INF/tags" %> 
<%
// 자바부분 데이터 가져오기 - 페이지 처리 : 페이지와 한 페이지당 표시 데이터의 갯수를 전달 받아야 한다.
String strCurPage = request.getParameter("page");

long curPage = 1;	// 현재 페이지의 기본값 세팅

if(strCurPage != null) {
	
	curPage = Long.parseLong(strCurPage);
	
}

String strPerPageNum = request.getParameter("prePageNum");

long perPageNum = 2;
 
if(strPerPageNum != null) {
	
	perPageNum = Long.parseLong(strPerPageNum);
	
}

// page 처리를 위한 객체 생성
PageObject pageObject = new PageObject();

pageObject.setPage(curPage);

pageObject.setPerPageNum(perPageNum);

// DB에서 데이터 가져오기
String url = request.getServletPath();

@SuppressWarnings("unchecked")
List<NoticeVO> list = (List<NoticeVO>) ExeService.execute(Beans.get(url), pageObject);

// 서버객체에 list 저장하기
request.setAttribute("list", list);
request.setAttribute("pageObject", pageObject);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 리스트</title>
<!-- <meta name="viewport" content="width=device-width, initial-scale=1"> -->
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"> -->
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> -->
<!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script> -->
</head>
<body>
 <div class="container">
  <h1>공지사항 리스트</h1>
   <table class="table">
   
    <tr>
     <th>번호</th>
     <th>제목</th>
     <th>공지 시작일</th>
     <th>공지 종료일</th>
     <th>최근 수정일</th>
    </tr>
    
    <c:forEach items="${list }" var="vo">
     <tr class="dataRow">
      <td>${vo.no }</td>
      <td>${vo.title }</td>
      <td>${vo.startDate }</td>
      <td>${vo.endDate }</td>
      <td>${vo.updateDate }</td>
     </tr>
    </c:forEach>
    
    <tr>
     <td colspan="5">
      <a href="writeForm.jsp" class="btn btn-default">공지 등록</a>
     </td>
    </tr>
    
    <tr>
     <td colspan="5">
      <pageObject:pageNav listURI="list.jsp" pageObject="${pageObject }"/>
     </td>
    </tr>
    
   </table>  
 </div>
</body>
</html>