package com.webjjang.member.controller;

import javax.servlet.http.HttpServletRequest;

import com.webjjang.main.controller.Beans;
import com.webjjang.main.controller.Controller;
import com.webjjang.main.controller.ExeService;
import com.webjjang.member.vo.LoginVO;
import com.webjjang.member.vo.MemberVO;
import com.webjjang.util.PageObject;
import com.webjjang.util.filter.AuthorityFilter;

public class MemberController implements Controller {

	private final String MODULE = "member";
	
	private String jspInfo = null;
	
	@Override
	public String execute(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		
		PageObject pageObject = PageObject.getInstance(request);
		
		request.setAttribute("pageObject", pageObject);
		
		switch (AuthorityFilter.url) {
		
		case "/" + MODULE + "/loginForm.do":
			
			jspInfo = MODULE + "/loginForm";

			break;

		case "/" + MODULE + "/login.do" : 
			
			login(request);
			
			jspInfo = "redirect:/board/list.do"; 
			
			break;
			
		case "/" + MODULE + "/logout.do" :
			
			logout(request);
		
			jspInfo = "redirect:/board/list.do";
			
			break;
			
		case "/" + MODULE + "/view.do" :
		
			LoginVO loginVO = (LoginVO) request.getSession().getAttribute("login");

			System.out.println("loginVO : " + loginVO);
		
			if(loginVO == null) {
				
				System.out.println("Controller : " + loginVO);
				
				request.getSession().invalidate();
				
				jspInfo = MODULE + "/loginForm";
				
				System.out.println("jspInfo : " + jspInfo);
				
			}
			
			view(request);
			
			jspInfo = MODULE + "/view";
			
			break;
		
		case "/" + MODULE + "/registerForm.do" :
			
			System.out.println(MODULE);
			
			jspInfo = MODULE + "/registerForm";
			
			System.out.println("testteststest : " + jspInfo);
		
			break;
			
		case "/" + MODULE + "/register.do" :
			
			register(request);
			
			jspInfo = "redirect:/main/main.do";
			
			break;
			
		// 아이디 중복체크
		case "/ajax/checkId.do" : 
			
			// DB에서 입력한 ID를 찾아온다.
			// 찾아온 ID를 request에 넣는다.
			
			// div안에 들어갈 코드만 있는 jsp로 이동시킨다.
			
			checkId(request);
			
			jspInfo = "member/checkId";
			
			break;
			
		default:
			
			throw new Exception("MemberContorller - 404 페이지 오류 : 존재하지 않는 페이지 입니다.");
			
		}
		 
		return jspInfo;
	}
	
	public void login(HttpServletRequest request) throws Exception {
		
		LoginVO vo = new LoginVO();
		
		vo.setId(request.getParameter("id"));
		vo.setPw(request.getParameter("pw"));
		
		LoginVO loginVO = (LoginVO) ExeService.execute(Beans.get(AuthorityFilter.url), vo);
		
		if(loginVO == null) {
			
			throw new Exception("로그인이 필요한 서비스 입니다.");
			
		}
		
		request.getSession().setAttribute("login", loginVO);
		
		System.out.println("로그인 처리가 되었습니다.");
		
	}
	
	public void logout(HttpServletRequest request) throws Exception {
		
		request.getSession().invalidate();
		
		System.out.println("로그아웃 처리가 되었습니다.");
		
	}

	public void view(HttpServletRequest request) throws Exception {
		
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("login");
		
		MemberVO vo = (MemberVO) ExeService.execute(Beans.get(AuthorityFilter.url), loginVO.getId());
		
		request.setAttribute("vo", vo);
		
	}
	
	public void register(HttpServletRequest request) throws Exception {
		
		MemberVO vo = new MemberVO();
		
		vo.setId(request.getParameter("id"));
		vo.setPw(request.getParameter("pw"));
		vo.setName(request.getParameter("name"));
		vo.setGender(request.getParameter("gender"));
		vo.setBirth(request.getParameter("birth"));
		vo.setEmail(request.getParameter("email"));
		
		ExeService.execute(Beans.get(AuthorityFilter.url), vo);
		
	}

	// 아이디 중복체크
	private void checkId(HttpServletRequest request) throws Exception {

		String id = request.getParameter("id");
		
		// DB처리
		String result = (String) ExeService.execute(Beans.get(AuthorityFilter.url), id);		
		
		// 서버 객체에 저장한다.
		
		request.setAttribute("id", result);
		
	}
	
}
