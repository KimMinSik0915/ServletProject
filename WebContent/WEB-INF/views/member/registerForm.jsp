<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script type="text/javascript" src="../js/formUtil.js"></script>
<script type="text/javascript">
	
	$(function () {
		
		// 최소 버튼 - 이전 페이지(리스트)로 돌아간다.
		$("#cancelBtn").click(function(){
			// alert("취소");
			// 이전페이지로 이동
			history.back();
		});
		
		// submit() 이벤트에 데이터 검사
		$("#writeForm").submit(function(){
			// alert("데이터 전달 이벤트");
			
			// 필수 입력
			// 아이디
			if(!require($("#id"), "아이디")) return false;
			// 비밀번호
			if(!require($("#pw"), "비밀번호")) return false;
			// 비밀번호 확인
			if(!require($("#pw2"), "비밀번호 확인")) return false;
			
			// 길이
			// 제목 4자 이상
			if(!checkLength($("#title"), "제목", 4)) return false;
			// 내용 4자 이상
			if(!checkLength($("#content"), "내용", 4)) return false;
			// 작성자 2자 이상
			if(!checkLength($("#writer"), "작성자", 2)) return false;
			
		});
		
		   // 아이디 중복 체크 메시지 선언
	    var id_length_error="아이디는 3자 이상이여야 합니다.";
	    // 아이디 중복 체크 하는 이벤트 처리
	    $("#id").keyup(function(){
	    	// 결과 디자인 - warning : 잘 안됨. success : 잘됨
	    	$("#checkId").removeClass("alert-warning alert-success")
	    	//alert("아이디 중복 체크");
	    	var id = $("#id").val();
	    	// 아이디가 입력이 안 되있거나 길이가 3미만인 경우 처리 
	    	if(!id || id.length <3){
	    		$("#checkId").text(id_length_error).addClass("alert-warning");
	    		return false;
	    	}
	    	// 아이디가 3자 이상인 경우 처리 - 서버에 가서 DB에 정보가 있는지 확인한 후 중복 메시지를 가져와서 div에 넣는다.
	    	$("#checkId").load("/ajax/checkId.do?id=" + id,
	    		// callback - load처리가 다끝나고 호출되는 함수
	    		function(result){
		    		// alert(result);
			    	// 넣은 글자가 "가능한" 포함하고 있으면 CSS를 성공으로 바꾼다.
			    	// alert(result.indexOf("가능한"));
			    	if(result.indexOf("가능한") >= 0)
			    		$("#checkId").addClass("alert-success");
			    	else
			    		$("#checkId").addClass("alert-warning");
	    	});
	    	
	    });
		
	});
	
</script>
</head>
<body>

 <div class="container">
  <h1>회원 가입</h1>
  <form action="register.do" method="post" id="registerForm">
   <!-- 페이지에 대한 정보 넘기기 -->
  
  
   <div class="form-group">
    <label for="id">아이디</label>
    <input class="form-control" id="id" name="id" required="required" placeholder="아이디는 3자 이상 입력하셔야 합니다." autofocus="autofocus" autocomplete="off" maxlength="20" pattern="[A-Za-z][A-Za-z0-9]{2,19}">
    <div id="checkId" class="alert alert-warning">아이디는 3자 이상입력하셔야 합니다.</div>
   </div>
   
   <div class="form-group">
    <label for="pw">비밀번호</label>
    <input class="form-control" id="pw" name="pw" required="required" placeholder="비밀번호는 4자 이상 입력하셔야 합니다." type="password">
   </div>
   
   <div class="form-group">
    <label for="name">작성자</label>
    <input class="form-control" id="name" name="name" required="required" placeholder="이름은 2자 이상 입력하셔야 합니다.">
   </div>
   
   <div class="form-group">
    <label class="radio-inline" for="gender"><input type="radio" name="gender" id="gender" checked="checked" value="남자">남자</label>
    <label class="radio-inline" for="gender"><input type="radio" name="gender" id="gender" value="여자">여자</label> 
   </div>
     
   <div class="form-group">
    <label for="birth">생년월일</label>
     <input class="form-control" id="birth" name="birth" required="required" type="date">
   </div>
    
   <div class="form-group">
    <label for="email">E-Mail</label>
     <input class="form-control" id="email" name="email" required="required" type="email">
   </div>
   
   <button class="btn btn-default">등록</button>
   <button type="reset" class="btn btn-default">새로 입력</button>
   <button type="button" id="cancelBtn" class="btn btn-default">취소</button>
   
  </form>
 </div>
</body>
</html>