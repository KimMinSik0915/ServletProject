<%@page import="com.sun.org.apache.bcel.internal.generic.LNEG"%>
<%@page import="com.webjjang.util.filter.AuthorityFilter"%>
<%@page import="com.webjjang.main.controller.Beans"%>
<%@page import="com.webjjang.main.controller.ExeService"%>
<%@page import="com.webjjang.qna.vo.QnaVO"%>
<%@page import="com.webjjang.member.vo.LoginVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 자바 부분입니다.

// 넘어오는 data 받기
String strNo = request.getParameter("no");

String title = request.getParameter("title");

String content = request.getParameter("content");

// session에서 id 받아오기
String id = ((LoginVO) session.getAttribute("login")).getId();

// 보이지 않는 데이터 받아오기
String strRefNo = request.getParameter("refNo");

String strOrdNo = request.getParameter("ordNo");

String strLevNo = request.getParameter("levNo");

// VO객체를 생성하고 저장해 놓는다
QnaVO vo = new QnaVO();

vo.setTitle(title);
vo.setContent(content);
vo.setId(id);
vo.setRefNo(Long.parseLong(strRefNo));
vo.setOrdNo(Long.parseLong(strOrdNo) + 1);	// 관련글 번호가 같고 순서 번호가 같거나 큰 것의 순서번호를 1 증가 먼저 처리한다.
vo.setLevNo(Long.parseLong(strLevNo) + 1);
vo.setParentNo(Long.parseLong(strNo));	// 번호(no)는 insert시 sequence를 사용한다. 따라서 넘겨 받은 번호는 parentNo로 세팅해주어야 한다.

// DB저장 처리 : JSP - service - dao - DB
ExeService.execute(Beans.get(AuthorityFilter.url), vo);

// 처리가 다 끝나면 자동으로 list로 이동
response.sendRedirect("list.jsp");


%>