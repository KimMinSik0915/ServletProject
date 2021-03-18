package com.webjjang.main.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webjjang.util.filter.AuthorityFilter;


/**
 * Servlet implementation class DispacherServlet
 */
//@WebServlet("/DispacherServlet")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispatcherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// 이곳에서 처리해야 할 모든 URL(*.do)을 받도록 설정 : web.xml
		System.out.println("DispatcherServlet.service() : *.do");

		// 순수한 데이터를 전달하기 위한 객체 생성
		PrintWriter out = response.getWriter();
		
		// /qna/list.do : /qna = subString(0, 4[indexOf("/", 1)])
		// /board/list.do : /board = subString(0,6[indexOf("/",1)])
		int endIndex = AuthorityFilter.url.indexOf("/", 1);
						// /main/main.do
		String module = "/main";
		
		if(endIndex >= 0) {		// MODULE이 존재하면 바꾼다. /maint.do : MODULE이 존재하지 않는다. MODULE 변수에 있는 값은 바뀌지 않는다.
			
			module = AuthorityFilter.url.substring(0, endIndex);
			
			System.out.println("DispatcherServlet.service() [module] : " + module);
			
		}
		
		// 모듈에 포함이 되어 있지 않은 url의 처리 : 사이트 매쉬에 적용이 안되도록 만들어야 함으로
		if(AuthorityFilter.url.equals("/ajax/checkId.do")) {
			
			module = "/member";	// MemberController가 선택
			
		} else if (AuthorityFilter.url.equals("/ajax/getMessageCnt.do")) {
			
			module = "/message";
			
			// DB에서 데이터 가져오기 : 새로운 메시지의 갯수를 가져오는 프로그램 Controller - service - dao
			try {
				
				String data =  Beans.getController(module).execute(request);
				
				out.write(data);
				
				System.out.println("DispatcherServlet.service() [module] : " + module + " ajax : getMessageCnt");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
				
			}
			
			return;
			
		}
		
		
		try {
			
			// 실행할 Controller를 선택한다.
			Controller controller = Beans.getController(module);
			// Controller = 데이터 수집(request에서부터)과 처리된 결과 저장
			// 데이터 처리를 위한 service는 선택해서 실행
			
			if(controller == null) {
				
				throw new Exception("DispatcherServlet.Controller = null : Error 404 : 요청하신 URL이 존재하지 않습니다.");
				 
			}
			
			// Controller를 실행하고  forward나 sendRedirect 정보를 돌려 받는다.
			String jspInfo = controller.execute(request);
			
			if(jspInfo.indexOf("redirect:") == 0) {		// sendRedirect를 하려면 리턴되는 문자열 앞에 "redirect:"를 붙여준다.
				
				// "redirect:list.dp -> jspInfo.subString("redirect.length()") = list.do
				jspInfo = jspInfo.substring("redirect:".length());
				
				// /board/list.do
				response.sendRedirect(jspInfo);
				
				return;
				
			} else {		// "redirect:"가 없으면 JSP로 forword된다.                                                                                          
				
				request.getRequestDispatcher("/WEB-INF/views/" + jspInfo + ".jsp").forward(request, response);
				
			}
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			request.setAttribute("exception", e);
			
			request.getRequestDispatcher("/WEB-INF/views/error/error_page.jsp").forward(request, response);	// forward시킨 내용의 url은 변경이 되지 않는다.
			
		}
		
		// 요청한 URLd을 처리해서 출력
		/* 
		 * String url = request.getServletPath();
		 * System.out.println("DispatcherServlet.service() [url] : " + url);
		 * 
		 * String jspInfo = null;
		 * 
		 * try {
		 * 
		 * // /board로 시작을 하면 BoardController가 실행이 되도록 한다. // /board로 시작을 하면 =>
		 * url.indexOf("/board") == 0 if(url.indexOf("/board") == 0) {
		 * 
		 * BoardController boardController = new BoardController();
		 * 
		 * jspInfo = boardController.execute(request); // "board/list"
		 * 
		 * } else if(url.indexOf("/notice") == 0) {
		 * 
		 * NoticeController noticeController = new NoticeController();
		 * 
		 * jspInfo = noticeController.execute(request); // "notice/list"
		 * 
		 * } else {
		 * 
		 * System.out.println("DispatcherServlet.service() : 404 존재하지 않는 URL 입니다.");
		 * 
		 * }
		 * 
		 * // jspInfo를 가지고 JSP로 이동시키는 프로그램
		 * request.getRequestDispatcher("/WEB-INF/views/" + jspInfo +
		 * ".jsp").forward(request, response);
		 * 
		 * 
		 * } catch (Exception e) {
		 * 
		 * System.out.println("DispatcherServlet.service() : 예외 발생 처리");
		 * 
		 * e.printStackTrace();
		 * 
		 * }
		 */ 
		
	}

}
