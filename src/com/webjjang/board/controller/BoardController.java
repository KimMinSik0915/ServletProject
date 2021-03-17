package com.webjjang.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.webjjang.board.vo.BoardVO;
import com.webjjang.main.controller.Beans;
import com.webjjang.main.controller.Controller;
import com.webjjang.main.controller.ExeService;
import com.webjjang.util.PageObject;
import com.webjjang.util.filter.AuthorityFilter;

public class BoardController implements Controller{

	private final String MODULE = "board";
	
	private String jspInfo = null;
	
	@Override
	public String execute(HttpServletRequest request) throws Exception {
		
		System.out.println("BoardController.execute() : 실행");
		
		// 페이지 처리
		PageObject pageObject = PageObject.getInstance(request);
		
		request.setAttribute("pageObject", pageObject);	// 페이지를 보여주기 위해 서버객체에 담는다.
		
		switch (AuthorityFilter.url) {
		
			// 게시판 리스트
			case "/" + MODULE + "/list.do" :
				
				// service - dao - request에 저장까지 해주는 method
				list(request, pageObject);
				
				jspInfo = MODULE + "/list";		// "/board/lsit를 넘겨준다. -> /WEN-INF/view/board/list.jsp를 이용해서 HTML을 만든다
				
				break;
				
			// 게시판 글 보기
			case "/" + MODULE + "/view.do" :
				
				// service - dao - request에 저장까지 해주는 method
				view(request);
			
				jspInfo = MODULE + "/view";		// "/board/view를 넘겨준다. -> /WEB-INF/views/board/view.jsp를 이용해서 HTML을 만든다.
				
				break;
		
			// 3-1 게시판 글 쓰기 Form
			case "/" + MODULE + "/writeForm.do" :
				
				jspInfo = MODULE + "/writeForm";		// "/board/view를 넘겨준다. -> /WEB-INF/views/board/view.jsp를 이용해서 HTML을 만든다.
				
				break;
			
			// 3-2 게시판 글 쓰기
			case "/" + MODULE + "/write.do" :
				
				write(request);
			
				jspInfo = "redirect:list.do?page=1&perPgeNum=" + pageObject.getPerGroupPageNum();		// "/board/view를 넘겨준다. -> /WEB-INF/views/board/view.jsp를 이용해서 HTML을 만든다.
				
				break;
			
			// 4-1 게시판 글 수정 Form
			case "/" + MODULE + "/updateForm.do" :
				
				updateForm(request);
				
				jspInfo = MODULE + "/updateForm";		// "/board/view를 넘겨준다. -> /WEB-INF/views/board/view.jsp를 이용해서 HTML을 만든다.
			
				break;
			
			// 4-2 게시판 글 수정
			case "/" + MODULE + "/update.do" :
				
				long no = update(request);
			
				jspInfo = "redirect:view.do?no=" + no + "&inc=0&page=" + pageObject.getPage() + "&perPageNum=" + pageObject.getPerPageNum() ;		// "/board/view를 넘겨준다. -> /WEB-INF/views/board/view.do로 자동으로 이동시킨다..
			
				break;
			
			// 5 게시판 글 삭제
			case "/" + MODULE + "/delete.do" :
				
				delete(request);
				
				jspInfo = "redirect:list.do?page=1&perPageNum" + pageObject.getPerPageNum();		// "/board/view를 넘겨준다. -> /WEB-INF/views/board/view.do로 자동으로 이동시킨다..
			
				break;
			
			default:
				
				throw new Exception("페이지 오류 404 : 존재하지 않는 페이지 입니다.");
				
		}
		
		// JSP의 정보를 가지고 리턴한다.
		return jspInfo;
		
	}
	
	// 1. 게시판 리스트 처리
	private void list(HttpServletRequest request, PageObject pageObject) throws Exception {	// JSP에 작성했던 내용들
		
		@SuppressWarnings("unchecked")
		List<BoardVO> list = (List<BoardVO>)ExeService.execute(Beans.get(AuthorityFilter.url), pageObject);

		request.setAttribute("list", list);

		
	}
	
	// 2. 게시판 글 보기
	private void view(HttpServletRequest request) throws Exception {
		
		// 여기가 java 코드 입니다. Servlet - Controller - Service - DAO -> /board/view.jsp

		// 넘어오는 data 받기 : 글번호
		String strNo = request.getParameter("no");

		long no = Long.parseLong(strNo);

		// 조회수 1 증가
		String strInc = request.getParameter("inc");

		long inc = Long.parseLong(strInc);

		BoardVO vo = (BoardVO)ExeService.execute(Beans.get(AuthorityFilter.url), new Long[] { no, inc });

		request.setAttribute("vo", vo);

	}
	
	// 3. 게시판 글 쓰기 처리
	private void write(HttpServletRequest request) throws Exception {
		
		// 1. 넘어오는 데이터 수집
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");

		BoardVO vo = new BoardVO();

		vo.setTitle(title);
		vo.setContent(content); 
		vo.setWriter(writer);

		// DB에 저장하는 쿼리 : write.do -> service -> dao
		int result = (Integer) ExeService.execute(Beans.get(AuthorityFilter.url), vo);

		System.out.println("BoardController.write() [result] : " + result);

		
	}

	// 4-1. 게시판 글 수정 폼
	private void updateForm(HttpServletRequest request) throws Exception {
		
		// 여기가 자바 부분 입니다.
		// 1. 넘어오는 데이터 받기 - 글 번호
		String strNo = request.getParameter("no");

		long no = Long.parseLong(strNo);

		// 조회수 1 증가하는 부분은 inc = 0으로 강제 세팅해서 넘겨준다.

		// 2. 글 번호에 맞는 데이터 가져오기 : BoardViewService -? /board/view.jsp
		String url = "/board/view.do"; // 현재 URL과 다르므로 강제로 세팅

		BoardVO vo = (BoardVO)ExeService.execute(Beans.get(url), new Long[] { no, 0L });
				
				
		// 3. 서버 객체에 넣기
		request.setAttribute("vo", vo);

		
	}

	// 4-2 게시판 글 수정
	private long update(HttpServletRequest request) throws Exception {
		
		// 1. 넘어오는 데이터 수집
		String strNo = request.getParameter("no");

		long no = Long.parseLong(strNo);

		String title = request.getParameter("title");

		String content = request.getParameter("content");

		String writer = request.getParameter("writer");

		// 넘어온 데이터 세팅 ---------------------------------

		BoardVO vo = new BoardVO();

		vo.setNo(no);
		vo.setTitle(title);
		vo.setContent(content); 
		vo.setWriter(writer);

		// DB에 저장하는 쿼리 : update.jsp -> service -> dao
		int result = (Integer) ExeService.execute(Beans.get(AuthorityFilter.url), vo);
		
		if(result < 1) {
			
			throw new Exception("게시판 글 수정 : 수정할 데이터가 존재하지 않습니다.");
			
		}
		
		return no;
		
	}
	
	// 5. 게시판 글 삭제
	private void delete(HttpServletRequest request) throws Exception {
		
		// 1. 넘어오는 데이터 수집
		String strNo = request.getParameter("no");

		long no = Long.parseLong(strNo);


		// DB에 저장하는 쿼리 : delete.jsp -> service -> dao : 글 번호 데이터가 넘어온다.
		String url = request.getServletPath();
		int result = (Integer) ExeService.execute(Beans.get(url), no);
		
		if(result < 1) {
			
			throw new Exception("게시판 글 삭제 오류 : 존재하지 않는 글은 삭제 할 수 없습니다.");
			
		}
		
	}
	
}
