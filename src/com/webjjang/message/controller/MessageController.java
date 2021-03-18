package com.webjjang.message.controller;

import java.util.List;

import javax.el.ELException;
import javax.servlet.http.HttpServletRequest;

import com.webjjang.main.controller.Beans;
import com.webjjang.main.controller.Controller;
import com.webjjang.main.controller.ExeService;
import com.webjjang.member.vo.LoginVO;
import com.webjjang.message.vo.MessageVO;
import com.webjjang.util.PageObject;
import com.webjjang.util.filter.AuthorityFilter;

public class MessageController implements Controller {

	private final String MODULE = "message";
	
	private String jspInfo = null;
	
	@Override
	public String execute(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("MessageController 실행 =====================");
		
		PageObject pageObject = PageObject.getInstance(request);
		
		request.setAttribute("pageObject", pageObject);
		
		switch (AuthorityFilter.url) {
		
		case "/" + MODULE + "/list.do" :
			
			list(request, pageObject);
			
			jspInfo = MODULE + "/list";
			
			break;
			
		case "/" + MODULE + "/writeForm.do" : 
			
			jspInfo = MODULE + "/writeForm";
		
			break;

		case "/" + MODULE + "/write.do" :
			
			write(request);
			
			jspInfo = "redirect:list.do?page=1&perPageNum=" + pageObject.getPerGroupPageNum();
			
			break;
			
		case "/" + MODULE + "/view.do" :
			
			view(request);
			 
			jspInfo = MODULE + "/view";
			
			System.out.println(jspInfo);
		
			break;
			
		// 새로운 메시지 개수 가져오기
		case "/ajax/getMessageCnt.do" :

			jspInfo = "" + getMessageCnt(request);
			
			//jspInfo = "123"; Test용
		
			System.out.println(jspInfo);
		
			break;
		
		default:
			
			throw new Exception("404 페이지 오류 : 존재하지 않는 페이지 입니다.");
			
		}
		
		return jspInfo;
		
		
	}

	private void list(HttpServletRequest request, PageObject pageObject) throws Exception {
		
		pageObject.setAccepter(((LoginVO)(request.getSession().getAttribute("login"))).getId());
		
		@SuppressWarnings("unchecked")
		List<MessageVO> list = (List<MessageVO>) ExeService.execute(Beans.get(AuthorityFilter.url), pageObject);
		
		request.setAttribute("list", list);
		
	}
	
	
	private void view(HttpServletRequest request) throws Exception {
		
		MessageVO vo = new MessageVO();
		
		vo.setNo(Long.parseLong(request.getParameter("no")));
		vo.setAccepter(((LoginVO)(request.getSession().getAttribute("login"))).getId());
		
		MessageVO viewVO = (MessageVO) ExeService.execute(Beans.get(AuthorityFilter.url), vo);
		
		request.setAttribute("vo", viewVO);
		
	}
	
	private void write(HttpServletRequest request) throws Exception {
		
		MessageVO vo = new MessageVO();
		
		vo.setContent(request.getParameter("content"));
		vo.setSender(((LoginVO)request.getSession().getAttribute("login")).getId());
		vo.setAccepter(request.getParameter("accepter"));
		
		ExeService.execute(Beans.get(AuthorityFilter.url), vo);
		
	}
	
	// 새로운 메시지 개수 가져오기
	private Long getMessageCnt(HttpServletRequest request) throws Exception {
		
		LoginVO vo = (LoginVO) request.getSession().getAttribute("login");
		
		System.out.println(vo);
		
		if(vo == null) {
			
			throw new ELException("MessageController.getMessageCnt() : 로그인이 필요한 서비스 입니다.");
			
		}
		
		String id = vo.getId();
		
		return (Long) ExeService.execute(Beans.get(AuthorityFilter.url), id);
		
	}
	
}
