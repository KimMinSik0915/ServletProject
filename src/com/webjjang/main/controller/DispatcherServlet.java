package com.webjjang.main.controller;

import java.io.IOException;
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
		
		// /qna/list.do : /qna = subString(0, 4[indexOf("/", 1)])
		// /board/list.do : /board = subString(0,6[indexOf("/",1)])
		String module = AuthorityFilter.url.substring(0, AuthorityFilter.url.indexOf("/", 1));
		
		System.out.println("DispatcherServlet.service() [module] : " + module);
		
		
		try {
			
			Controller controller = Beans.getController(module);
			
			
			if(controller == null) {
				
				throw new Exception("DispatcherServlet.Controller = null : Error 404 : 요청하신 URL이 존재하지 않습니다.");
				 
			}
			
			String jspInfo = controller.execute(request);
			
			if(jspInfo.indexOf("redirect:") == 0) {		// sendRedirect를 하려면 리턴되는 문자열 앞에 "redirect:"를 붙여준다.
				
				// "redirect:list.dp -> jspInfo.subString("redirect.length()") = list.do
				jspInfo = jspInfo.substring("redirect:".length());
				
				response.sendRedirect(jspInfo);
				
				return;
				
			} else {		// "redirect:"가 없으면 JSP로 forword된다.                                                                                          
				
				request.getRequestDispatcher("/WEB-INF/views/" + jspInfo + ".jsp").forward(request, response);
				
			}
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			
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