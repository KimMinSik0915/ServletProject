<%@page import="com.webjjang.main.controller.Beans"%>
<%@page import="com.webjjang.main.controller.ExeService"%>
<%@page import="com.webjjang.member.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String url = request.getServletPath();

@SuppressWarnings("unchecked")
List<MemberVO> list = (List<MemberVO>)ExeService.execute(Beans.get(url), null);

request.setAttribute("list", list);


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 리스트</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function () {
		
		$(".gradeModifyBtn").click(function () {
		
			//alert("등급변경 버튼 클릭");
			
			// id 찾기와 세팅
			var id = $(this).closest(".dataRow").find(".id").text();
			
			$("#formId").val(id);
			
			// 등급번호 찾기와 세팅
			var gradeNo = $(this).closest(".dataRow").find(".gradeNo").text();
			
			//alert(gradeNo);
			
			//alert(typeof gradeNo);
			
			$(".formGradeNo").val([gradeNo]);
			
		});
		
		// 등급 수정 form의 전달 이벤트
		// 객체의 선택이 잘 되는지 확인
		// alert($("#formGradeModifyBTN"));
		// alert($("#formGradeModifyBTN").prop("tagName"));
		
		$("#formGradeModifyBtn").click(function () {
			
			// alert("formGradeModifyBtn 클릭");
			
			// form의 data 넘기기
			// form 객체 확인
			// alert($("#gradeModifyForm"));
			
			// form의 데이터 전송하기 -> form.submit();
			$("#gradeModifyForm").submit();
			
		});
		
	});
</script>
</head>
<body>
 <div class="container">
  <h1>회원 리스트</h1>
  <table class="table">
   <thead>
    <tr>
     <th>아이디</th>
     <th>이름</th>
     <th>성별</th>
     <th>생년월일</th>
     <th>연락처</th>
     <th>등급번호</th>
     <th>등급이름</th>
     <th>상태</th>
    </tr>
   </thead>
   <tbody>
    <c:forEach items="${list }" var="vo">
     <tr class="dataRow">
      <td class="id">${vo.id }</td>
      <td>${vo.name }</td>
      <td>${vo.gender }</td>
      <td>${vo.birth }</td>
      <td>${vo.tel }</td>
      <td class="gradeNo">${vo.gradeNo }</td>
      <td>${vo.gradeName } 
       <c:if test="${vo.id != login.id }">
        <!-- 내 계정이 아니면 변경버튼이 나타난다. -->
        <button class="gradeModifyBtn"  data-toggle="modal" data-target="#myModal">변경</button>
       </c:if>
      </td>
      <td>${vo.status }</td>
     </tr>
    </c:forEach>
   </tbody>
   <tfoot>
    <tr>
     <td colspan="8">
     <a href="../main/main.jsp" class="btn btn-default">항목</a>
    </tr>
   </tfoot>
   
  </table>
 </div>
 <!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">등급 수정</h4>
      </div>
      <div class="modal-body">
       <form action="gradeModify.jsp" method="post" id="gradeModifyForm">
        <div class="form-group">
         <label for="id">아이디</label>
         <input name="id" id="formId" readonly="readonly" class="form-control">
        </div>
        <div>
         <div>
          <label>등급</label>
         </div>
         <label class="radio-inline">
          <input type="radio" name="gradeNo" value="1" class="formGradeNo">일반회원
         </label>
         <label class="radio-inline">
          <input type="radio" name="gradeNo" value="9" class="formGradeNo">관리자
         </label>
        </div>
       </form>
      </div>
      <div class="modal-footer">
       <button type="button" class="btn btn-default" id="formGradeModifyBtn" >변경</button>
       <button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
      </div>
    </div>
  </div>
</div>
 
</body>
</html>