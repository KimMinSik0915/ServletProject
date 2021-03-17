<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글 쓰기</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="../js/formUtil.js"></script>
  <script type="text/javascript">
   // 객체 선택에 문제가 있다. 아래 document가 로딩이 되면 실행되는 스크립트 작성
   // jQuery에서 사용하는 $(function(){처리문;}) = jquery(function(){처리문;})
   $(function () {		// jQuery에서 익명함수를 전달해서 저장해 놓았다가 Document가 로딩이 다 되면 호출해서 처리해준다.
	   //이벤트 처리
	   // 취소버튼 --> 이전 페이지(리스트)로 이동
	   $("#cancelBtn").click(function () {
		//alert("취소버튼을 눌렀습니다.");
		//이전 페이지로 이동
		history.back();
	   });
	   
	   // submit() 이벤트에 데이터 검사
	   $("#writeForm").submit(function () { 
// 			alert("이벤트가 발생하였습니다.");
			
			// 필수 입력
			//alert((!require($("#title"), "제목"))); 
			if(!require($("#id"), "아이디")) return false;
			
			if(!require($("#pw"), "비밀번호")) return false;
			
			// 길이
			// 제목 : 4자 이상
			if(!checkLength($("#id"), "아이디", 4)) return false;
			
			// 내용 : 4자 이상
			if(!checkLength($("#pw"), "비밀번호", 4)) return false;
			
			// 작성자 : 2자 이상
	   });
	   
   });
   
  </script>
</head>
<body>

 <div class="container">
  <h1>글 쓰기</h1>
  <form action="register.do" method="post" id="register">
   <!-- 페이지에 대한 정보 넘기기 -->
  
  
   <div class="form-group">
    <label for="id">아이디</label>
    <input class="form-control" id="id" name="id" required="required" placeholder="아이디는 4자 이상 입력하셔야 합니다." autofocus="autofocus">
   </div>
   
   <div class="form-group">
    <label for="pw">비밀번호</label>
    <input class="form-control" id="pw" name="pw" required="required" placeholder="비밀번호는 4자 이상 입력하셔야 합니다.">
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