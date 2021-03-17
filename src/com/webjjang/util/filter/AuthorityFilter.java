package com.webjjang.util.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import javax.servlet.annotation.WebFilter;
import com.webjjang.member.vo.LoginVO;

/**
 * Servlet Filter implementation class AuthorityFilter
 */
//@WebFilter("/AuthorityFilter")
public class AuthorityFilter implements Filter {

	// url에 대한 권한 정보를 저장하는 MAP
	// 서버가 로딩될 때 정해져야 한다. Init.init()에서 처리를 해 준다.
	private static Map<String, Integer> authMap = new HashMap<>();	//Map<url, gradeNo>
	
	// 페이지에 대한 등급 정보 저장하는 method
	{		// 데이터를 넣는방법 : AuthorityFilter.put(url, gradeNo)
		
		// 공지사항 - 등록, 수정, 삭제 = 관리자(9등급)
//		authMap.put("/notice/writeForm.do", 9);
//		authMap.put("/notice/write.do", 9);
//		authMap.put("/notice/updateForm.do", 9);
//		authMap.put("/notice/update.do", 9);
//		authMap.put("/notice/delete.do", 9);
		
		// message : list, view, write, delete = 1
		authMap.put("/message/list.do", 1);
		authMap.put("/message/view.do", 1);
		authMap.put("/message/write.do", 1);
		authMap.put("/message/writeForm.do", 1);
		authMap.put("/message/delete.do", 1);
		
		// qna
//		authMap.put("/qna/list.do", 1);
		authMap.put("/qna/view.do", 1);
		authMap.put("/qna/questionForm.do", 1);
		authMap.put("/qna/question.do", 1);
		authMap.put("/qna/answerForm.do", 1);
		authMap.put("/qna/answer.do", 1);
		authMap.put("/qna/updateForm.do", 1);
		authMap.put("/qna/update.do", 1);
		authMap.put("/qna/delete.do", 1);
		
		// image : 등록 , 수정, 삭제 : 회원(1)
		authMap.put("/image/writeForm.do", 1);
		authMap.put("/image/write.do", 1);
		authMap.put("/image/updateForm.do", 1);
		authMap.put("/image/updateFile.do", 1);
		authMap.put("/image/update.do", 1);
		authMap.put("/image/delete.do", 1);
		
	}
	
	// 요청한 url
	public static String url;
	
    /**
     * Default constructor. 
     */
    public AuthorityFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		
		HttpServletRequest req = (HttpServletRequest) request;
		
		
		// 전 처리 : 권한체크 프로그램
		System.out.println("Authority.doFilter : 권한처리");
		// jsp의 request와 여기의 requset는 같은 것이다.
		// HTTPServleyRequest가 ServletRequest를 상속하고 있다.
		url = req.getServletPath();
		
		System.out.println("Authority.doFilter.url : " + url);
		
		// 로그인 객체 꺼내기
		// 로그인 정보는 session에 있다. session이 안보인다. => request에서 꺼낼 수 있다.
		HttpSession session = req.getSession();
		
		LoginVO vo = (LoginVO)session.getAttribute("login");
		
		
		// 권한이 없는 경우의 처리
		if(!checkAuthority(vo)) {
			
			// 오류 페이지로 이동시킨다
			((HttpServletResponse)response).sendRedirect(req.getContextPath() + "/error/auth_error.do");
			
			// 호출한 쪽으로 되돌아 간다. : 없으면 계속 아래로 실행이 된다.
			return;
			
		}
		
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	private boolean checkAuthority(LoginVO vo) {
		
		// url 정보가 authMap에 있는지 확인한다.
		// 데이터가 없으면(null) 권한체크가 필요 없는 페이지 요청
		
		Integer pageGradeNo = authMap.get(url);

		if(pageGradeNo == null) {
			
			System.out.println("AuthorityFilter.checkAuthority() : 권한이 필요없는 페이지 입니다.");
			
			return true;
			
		}
		
		if(vo == null) {
			
			System.out.println("AuthorityFilter.doFilter)_: 로그인이 필요합니다.");
			
			return false;
			
		}
		
		// 여기서 부터는 로그인이 필요한 처리 입니다.(VO != null)
		System.out.println("AuthorityFilter.checkAuthority().pageGradeNo : " + pageGradeNo);
		System.out.println("AuthorityFilter.checkAuthority().userGradeNo : " + vo.getGradeNo());
	
		// 권한이 없는 페이지 요청에 대한 처리
		if(pageGradeNo > vo.getGradeNo()) {
			
			System.out.println("AuthorityFilter.checkAuthority() : 권한이 없습니다.");
			
			return false;
			
		}
		
		System.out.println("AuthorityFilter.checkAuthority() : 권한이 있습니다.");
		return true;
		
	}
	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
