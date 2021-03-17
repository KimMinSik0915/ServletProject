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
			if(!require($("#title"), "제목")) return false;
			
			if(!require($("#content"), "내용")) return false;
			
			if(!require($("#writer"), "작성자")) return false;
				
			// 길이
			// 제목 : 4자 이상
			if(!checkLength($("#title"), "제목", 4)) return false;
			
			// 내용 : 4자 이상
			if(!checkLength($("#content"), "내용", 4)) return false;
			
			// 작성자 : 2자 이상
			if(!checkLength($("#writer"), "작성자", 2)) return false;
			
	   });
	   
   });
   
  </script>
</head>
<body>

 <div class="container">
  <h1>글 쓰기</h1>
  <form action="write.do" method="post" id="writeForm">
   <!-- 페이지에 대한 정보 넘기기 -->
  <input name="perPageNum" type="hidden" value="${pageObject.perPageNum }">
  
  
   <div class="form-group">
    <label for="title">제목</label>
    <input class="form-control" id="title" name="title" required="required" placeholder="제목은 4자 이상 입력하셔야 합니다." autofocus="autofocus">
   </div>
   
   <div class="form-group">
    <label for="content">내용</label>
    <textarea rows="5" class="form-control" id="content" name="content" required="required" placeholder="내용을 4자 이상 입력하셔야 합니다."></textarea>
   </div>
   
   <div class="form-group">
    <label for="writer">작성자</label>
    <input class="form-control" id="writer" name="writer" required="required" placeholder="작성자는 2자 이상 입력하셔야 합니다.">
   </div>
   
   <button class="btn btn-default">등록</button>
   <button type="reset" class="btn btn-default">새로 입력</button>
   <button type="button" id="cancelBtn" class="btn btn-default">취소</button>
   
  </form>
 </div>
</body>
</html>