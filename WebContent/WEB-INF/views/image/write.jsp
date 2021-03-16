<%@page import="com.webjjang.util.filter.AuthorityFilter"%>
<%@page import="com.webjjang.main.controller.Beans"%>
<%@page import="com.webjjang.main.controller.ExeService"%>
<%@page import="com.sun.rmi.rmid.ExecOptionPermission"%>
<%@page import="com.webjjang.image.vo.ImageVO"%>
<%@page import="com.webjjang.member.vo.LoginVO"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 자바 입니다.

// 저장 위치
String path = "/upload/image/";

String realPath = request.getServletContext().getRealPath(path);

System.out.println("/image/write.jsp [realPath] : " + realPath);

// FileSize : 용량 제한(10MB)
int fileSize = 10 * 1024 * 1024;	// 1000 -> 1K, 1000K -> 1M, 1000M -> 1G, 1000G -> 1T

// 파일의 정보를 처리해 주는 객체 생성 : 파일 업로드가 자동으로 이루어진다.
// new MultiparRequest(request, 실제적인 저장위치 : 내  PC, 파일 용량 제한, 엔코딩 방식, 중복된 파일이름을 처리하는 객체)
MultipartRequest multi = new MultipartRequest(request, realPath, fileSize, "UTF-8", new DefaultFileRenamePolicy());	// 선언한 것으로 파일을 저장할 수 있다.

// System.out.println("image/write.jsp [request.title] : " + request.getParameter("title"));	// MultipartRequest 생성 후 request에서 어떤 데이터도 받아 올 수 없다.

//System.out.println("image/write.jsp [multi.title] : " + multi.getParameter("title"));	

String title = multi.getParameter("title");

String content = multi.getParameter("content");

String id = ((LoginVO)session.getAttribute("login")).getId();

String fileName = multi.getFilesystemName("imageFile");

System.out.println("image/write.jsp [fileName] : " + fileName);

// VO객체 생성 및 저장
ImageVO vo = new ImageVO();

vo.setTitle(title);
vo.setContent(content);
vo.setId(id);
vo.setFileName(path + fileName);


// DB처리
ExeService.execute(Beans.get(AuthorityFilter.url), vo);

// 리스트로 자동 이동 시킨다
response.sendRedirect("list.jsp?page=1&perPageNum=" + multi.getParameter("perPageNum"));

%>