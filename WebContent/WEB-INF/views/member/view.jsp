<%@page import="com.webjjang.main.controller.Service"%>
<%@page import="com.webjjang.member.vo.LoginVO"%>
<%@page import="com.webjjang.main.controller.Beans"%>
<%@page import="com.webjjang.main.controller.ExeService"%>
<%@page import="com.webjjang.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
System.out.println("view.jsp 실행 -----------------------");

// 넘어오는 데이터 받아오기 : ID
// session의 login에 id가 들어있다.
// session.getAttribute("login") = data type이 Object
LoginVO loginVO = (LoginVO)session.getAttribute("login");	// session에 저장된 정보가 loginVO에 담겨 있으므로 LoginVO로 캐스팅하여 사용
System.out.println("view.jsp loginVO : " + loginVO);	// server가 리스타트되면 자동 로그아웃 된다.

// 로그인이 안되어 있으면(null) login.jsp로 가라

if(loginVO == null) {
	
	response.sendRedirect("loginForm.jsp");
	
	return;
	
} 

// 필요한 정보인 id는 loginVO에 담겨 있으므로 loginvo.getId를 이용해 ID를 가져온다.
String id = loginVO.getId();
System.out.println("view.jsp id : " + id);

// 요청 URL 받아오기 
String url = request.getServletPath();
System.out.println("view.jsp url" + url);

Service service = Beans.get(url);
System.out.println("view.jsp [service] : " + service);

// VO객체에서 데이터 가져오기 (실행결과를 VO객체에 저장해야 하므로 MemberVO를 초기화)
MemberVO vo = (MemberVO)ExeService.execute(Beans.get(url), id);
System.out.println("view.jsp vo : " + vo);

request.setAttribute("vo", vo);		// 필요한 정보(id, name 등등)을 요청해야 html에서 사용할 수있다.

// response는 요청에 대한 응답!
// response.sendRedirect = 리스트로 돌아가자는 요청에 대한 응답으로 list로 돌아가게 된다.

// html안에 쉰게 사용하는 EL을 쓰려면 서버 저장객체에 넣어야 한다. 주로 사용하는 것이 request.setAttribute("vo", vo);

// class 여러개 적용하는 법 : class="conatainer div1 ..."


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 정보 관리</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
 <div class="container">
  <h1>내 정보 관리</h1>
  <table class="table">
  
    <tr>
     <th>아이디</th>
     <td>${vo.id }</td>
    </tr>
    
    <tr>
     <th>이름</th>
     <td>${vo.name }</td>     
   </tr>
   
    <tr>
     <th>성별</th>
     <td>${vo.gender }</td>
   </tr>
   
    <tr>
     <th>생년월일</th>
     <td>${vo.birth }</td>
    </tr>
    
    <tr>
     <th>전화번호</th>
     <td>${vo.tel }</td>
    </tr>
    
    <tr>
     <th>이메일</th>
     <td>${vo.email }</td>
    </tr>
    
    <tr>
     <th>회원가입일</th>
     <td>${vo.regDate }</td>
    </tr>
    
    <tr>
     <th>등급번호</th>
     <td>${vo.gradeNo }</td>
    </tr>
         
    <tr>
     <th>등급이름</th>
     <td>${vo.gradeName }</td>
    </tr>
    
    <tr>
     <th>상태</th>
     <td>${vo.status }</td>
    </tr>
   
   <tfoot>
    <tr>
     <td colspan="8" class="returnBtn">
     <a href="../main/main.jsp" class="btn btn-default">리스트로</a>
     </td>
    </tr>
   </tfoot>
   
  </table>
 </div>
</body>
</html>