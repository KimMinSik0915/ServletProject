package com.webjjang.notice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.webjjang.main.controller.Beans;
import com.webjjang.main.controller.Controller;
import com.webjjang.main.controller.ExeService;
import com.webjjang.notice.vo.NoticeVO;
import com.webjjang.util.PageObject;
import com.webjjang.util.filter.AuthorityFilter;

public class NoticeController implements Controller {

	private final String MODULE = "notice";
	
	private String jspInfo = null;
	
	@Override
	public String execute(HttpServletRequest request) throws Exception {
		
		System.out.println("NoticeController.execute() : 실행");
		
		// 페이지 처리를 한다
		PageObject pageObject = PageObject.getInstance(request);
		
		request.setAttribute("pageObject", pageObject);
		
		// URL에 맞는 처리(switch-case)
		switch (AuthorityFilter.url) {
			
			// 공지사항 보기
			case "/" + MODULE + "/list.do" : 
				
				System.out.println("notice [module} : " + MODULE);
				
				list(request, pageObject);
				
				jspInfo = MODULE + "/list";
				
				break;
		
			case "/" + MODULE + "/view.do" :
				
				view(request);
				
				jspInfo = MODULE + "/view";
				
				break;
				
	
			// 공지사항 쓰기 Form
			case "/" + MODULE + "/writeForm.do" :
			
				jspInfo = MODULE + "/writeForm";
			
				break;
			
			// 공지사항 쓰기
			case "/" + MODULE + "/write.do" :
				
				write(request);
				
				jspInfo = "redirect:list.do?page=1&perPageNum=" + pageObject.getPerPageNum();
				
				break;
				
			// 공지사항 수정 Form
			case "/" + MODULE + "/updateForm.do" :
				
				updateForm(request);
			
				jspInfo = MODULE + "/updateForm";
			
				break;
				
			// 공지사항 수정
			case "/" + MODULE + "/update.do" :
				
				write(request);
			
				jspInfo = "redirect:list.do?page=1&perPageNum=" + pageObject.getPerPageNum();
			
				break;
				
				default:
				
				
				
				break;
		}
		
		return jspInfo;
		
	} 
	
	// 공지사항 리스트
	private void list(HttpServletRequest request, PageObject pageObject) throws Exception {
		
		@SuppressWarnings("unchecked")
		List<NoticeVO> list = (List<NoticeVO>) ExeService.execute(Beans.get(AuthorityFilter.url), pageObject);
		
		request.setAttribute("list", list);
		
	}

	// 공지사항 보기
	private void view(HttpServletRequest request) throws Exception {
		
		// 넘어오는 데이터 : no, NOticeVO 에서
		
		String strNo = request.getParameter("no");
		
		long no = Long.parseLong(strNo);
		
		System.out.println("NoticeController.view() : " + no);
		
		NoticeVO vo = (NoticeVO)ExeService.execute(Beans.get(AuthorityFilter.url), no);
		
		request.setAttribute("vo", vo);
		
	}
	
	// 공지사항 수정
	private void write(HttpServletRequest request) throws Exception {
		
		// 넘어오는 데이터 받아오기
		String title = request.getParameter("title");

		String content = request.getParameter("content");

		String startDate = request.getParameter("startDate");

		String endDate = request.getParameter("endDate");

		// vo객체에 저장한다.
		NoticeVO vo = new NoticeVO();

		vo.setTitle(title);
		vo.setContent(content);
		vo.setStartDate(startDate);
		vo.setEndDate(endDate);

		// vo객체 data 확인
		System.out.println("/Notice/write.jsp [vo] : " + vo);

		// DB에 데이터 저장 : JSP(controller) - NoticeWriteService - NoticeDAO - Notice Table - INSERT
		ExeService.execute(Beans.get(AuthorityFilter.url), vo);
		
	}
	
	// 공지 수정
	private void updateForm(HttpServletRequest request) throws Exception {
		
		NoticeVO vo = (NoticeVO)ExeService.execute(Beans.get("/notice/view.do"), Long.parseLong(request.getParameter("no")));
		
		request.setAttribute("vo", vo);
		
	}
	
}
