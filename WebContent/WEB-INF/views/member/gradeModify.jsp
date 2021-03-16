<%@page import="com.webjjang.main.controller.Service"%>
<%@page import="com.webjjang.main.controller.Beans"%>
<%@page import="com.webjjang.main.controller.ExeService"%>
<%@page import="com.webjjang.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
// 자바부분 -----------------------------------------------------
System.out.println("gradeModify.jsp 실행---------------------------------------");		// 실행여부 확인 - eclipse console창에서 확인

// 전달되는 데이터 수집
// 1. id 받기 - 데이터 출력이 안될 경우 list.jsp부터 실행을 했어야 한다. list.jsp의 form안에 input의 name="id"로 세팅해야 한다. 실질적으로 data가 세팅되어 있어야 한다.
String id = request.getParameter("id");

System.out.println("gradeModify.jsp id : " + id);


// 2. gradeNo 문자열 받기 - list.jsp의 form안에 input의 name="gradeNo"로 세팅해야 한다.
String strGradeNo = request.getParameter("gradeNo");

System.out.println("strGradeNO = " + strGradeNo);

int gradeNo = Integer.parseInt(strGradeNo);

System.out.println("strGradeNO = " + gradeNo);


// 수집한 데이터를 DB처리 - jsp - service - dao : 매개변수로 전달 값이 2개이므로 클래스(VO)를 사용

// 저장할 Vo객체 생성
MemberVO vo = new MemberVO();

// 생성된 vo 객체에 데이터 넣기
vo.setId(id);
vo.setGradeNo(gradeNo);

// data세팅이 잘 되어있는지 확인
System.out.println("gradeModify.jsp [vo] : " + vo);

// MemberGradeModifyServcie, MemberDAO.gradeModify(vo), DBSQL.MEMBER_GRADE_MODIFY 작성
// Init.init() 초기화

// 수집한 data를 service로 넘겨 실행한다. : jsp - service - dao - memberDB
String url = request.getServletPath();

System.out.println("geradeModify.jsp [url] : " + url);

// service 선택하기 : Beans.get(url);

Service service = Beans.get(url);

System.out.println("geradeModify.jsp [servuce] : " + service);

ExeService.execute(Beans.get(url), vo);

// 자동으로 회원 리스트로 이동
response.sendRedirect("list.jsp");

%>