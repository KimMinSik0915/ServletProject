<%@page import="com.webjjang.member.vo.LoginVO"%>
<%@page import="com.webjjang.message.vo.MessageVO"%>
<%@page import="com.webjjang.util.PageObject"%>
<%@page import="com.webjjang.util.filter.AuthorityFilter"%>
<%@page import="com.webjjang.main.controller.Beans"%>
<%@page import="com.webjjang.main.controller.ExeService"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="pageObject" tagdir="/WEB-INF/tags" %>
<% 


long curPage = 1; 

String strCurPage = request.getParameter("page");

if(strCurPage != null) {
	
	curPage = Long.parseLong(strCurPage);
	
}

long perPageNum = 10;	// 한 페이지에 보여줄 데이터의 개수

String strPerPageNum = request.getParameter("perPageNum");

if(strPerPageNum != null) {
	
	perPageNum = Long.parseLong(strPerPageNum);
	
}

PageObject pageObject = new PageObject();

pageObject.setPage(curPage);
pageObject.setPerPageNum(perPageNum);

System.out.println(AuthorityFilter.url);


// 내 아이디를 가져와서 pageObject에 저장을 해준다.
pageObject.setAccepter(((LoginVO)session.getAttribute("login")).getId());


@SuppressWarnings("unchecked")
List<MessageVO> list = (List<MessageVO>) ExeService.execute(Beans.get(AuthorityFilter.url), pageObject);

request.setAttribute("list", list);
request.setAttribute("pageObject", pageObject);

%> 
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메시지 리스트</title>
<style type="text/css">

	tr {
	
		color: #777;  
	
	}

	.noRead {
	
		color: #4d0026;
	
	}
	
	.dataRow:hover {
	
		cursor: pointer;
		background: #eee;
	 
	}
	
</style>
<script type="text/javascript">

 $(function () {
	
	 $(".dataRow").click(function() {	// 이벤트 처리
		
		// alert("data 보기 클릭");
		 
		//$(this) = 자기 자신을 의미 : 이벤트가 일어난 곳(현재는 tr).find(class가 no인 객체를 찾아라).text(태그 안에 있는 글자 가져오기)
	 
		var no = $(this).find(".no").text();	// 번호 받기
		 
		location = "view.jsp?no=" + no;	// 메시지 보기로 이동
		 
	})
	 
})

</script>
</head>
<body>
 <div class="container"> 
  <h1>메시지 리스트</h1>
  <table class="table">
   <tr>
    <th>번호</th>
    <th>보낸 사람</th>
    <th>보낸 날짜</th>
    <th>받는 사람</th>
    <th>받은 날짜</th>
   </tr>
   
   <c:forEach items="${list }" var="vo" >
    <tr class='dataRow ${(empty vo.acceptDate)? "noRead" : "" }'>
     <td class="no">${vo.no }</td>
     <td>${vo.sender }</td>
     <td>${vo.sendDate }</td>
     <td>${vo.accepter }</td>
     <td>${(empty vo.acceptDate) ? "읽지 않음":vo.acceptDate }</td>
    </tr>
   </c:forEach>
   
   <tr>
    <td colspan="5">
     <a href="writeForm.jsp" class="btn btn-default">메시지 전송</a>
    </td>
   </tr>
   
   <tr>
    <td colspan="5">
     <pageObject:pageNav listURI="list.jsp" pageObject="${pageObjcet }"/>
    </td>
   </tr>
   
  </table>
 </div>
</body>
</html>