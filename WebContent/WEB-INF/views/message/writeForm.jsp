<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메시지 보내기</title>
<script type="text/javascript">

	$(function() {
		
		$("#cancelBtn").click(function () {
			
			//alert("취소버튼"); 
			
			history.back();
			
		});
		
	});

</script>
</head>
<body>
 <div class="container">
  <h1>메시지 보내기</h1>
  <form action="write.jsp" method="post">
  
   <div class="form-group">
    <label for="accepter">받는사람의 아이디</label>
    <input name="accepter" id="accepter" class="form-control" required="required">
   </div>
   
   <div class="form-group">
    <label for="content">내용</label>
    <textarea name="content" id="content" class="form-control" required="required" rows="5"></textarea>
   </div>
   
   <button class="btn btn-default">전송</button>
   <button type="reset" class="btn btn-default">새로 입력</button>
   <button class="btn btn-default" type="button" id="cancelBtn">취소</button>
   
  </form>
 </div>
</body>
</html>