package com.webjjang.main.controller;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.webjjang.board.controller.BoardController;
import com.webjjang.board.dao.BoardDAO;
import com.webjjang.board.service.BoardDeleteService;
import com.webjjang.board.service.BoardListService;
import com.webjjang.board.service.BoardUpdateService;
import com.webjjang.board.service.BoardViewService;
import com.webjjang.board.service.BoardWriteService;
import com.webjjang.image.dao.ImageDAO;
import com.webjjang.image.service.ImageDeleteService;
import com.webjjang.image.service.ImageListService;
import com.webjjang.image.service.ImageUpdateFileService;
import com.webjjang.image.service.ImageViewService;
import com.webjjang.image.service.ImageWriteService;
import com.webjjang.member.controller.MemberController;
import com.webjjang.member.dao.MemberDAO;
import com.webjjang.member.service.MemberGradeModifyService;
import com.webjjang.member.service.MemberListService;
import com.webjjang.member.service.MemberLoginService;
import com.webjjang.member.service.MemberRegisterService;
import com.webjjang.member.service.MemberViewService;
import com.webjjang.message.controller.MessageController;
import com.webjjang.message.dao.MessageDAO;
import com.webjjang.message.serviec.MessageDeleteService;
import com.webjjang.message.serviec.MessageListService;
import com.webjjang.message.serviec.MessageViewService;
import com.webjjang.message.serviec.MessageWriteService;
import com.webjjang.notice.controller.NoticeController;
import com.webjjang.notice.dao.NoticeDAO;
import com.webjjang.notice.service.NoticeListService;
import com.webjjang.notice.service.NoticeUpdateService;
import com.webjjang.notice.service.NoticeViewService;
import com.webjjang.notice.service.NoticeWriteService;
import com.webjjang.qna.dao.QnaDAO;
import com.webjjang.qna.service.QnaAnswerService;
import com.webjjang.qna.service.QnaListService;
import com.webjjang.qna.service.QnaQuestionService;
import com.webjjang.qna.service.QnaViewService;

/**
 * Servlet implementation class init
 */
@WebServlet(value = "/Init", loadOnStartup = 1)
public class Init extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Init() {
        super();
        // TODO Auto-generated constructor stub
    }
 
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		
		System.out.println("servletProject에서의 실행 ----------------------------");
		
		System.out.println("서버가 실행을 시작할 때 실행되는 처리--------------------------------------");
		
		// main =========================================================================================
		// Controller 저장
		Beans.putController("/main", new MainController());
		
		
		// 객체를 생성후 저장 ================================= 게시판 ============================================
		// dao 생성 저장
		Beans.putDAO("boardDAO", new BoardDAO());
		
		// Controller todtjd
		Beans.putController("/board", new BoardController());
		
		// service 생성 후 저장
		Beans.put("/board/list.do", new BoardListService());
		Beans.put("/board/view.do", new BoardViewService());
		Beans.put("/board/write.do", new BoardWriteService());
		Beans.put("/board/update.do", new BoardUpdateService());
		Beans.put("/board/delete.do", new BoardDeleteService());
		
		
		// service에 dao 넣기
		Beans.get("/board/list.do").setDAO(Beans.getDAO("boardDAO"));
		Beans.get("/board/view.do").setDAO(Beans.getDAO("boardDAO"));
		Beans.get("/board/write.do").setDAO(Beans.getDAO("boardDAO"));
		Beans.get("/board/update.do").setDAO(Beans.getDAO("boardDAO"));
		Beans.get("/board/delete.do").setDAO(Beans.getDAO("boardDAO"));
		
		// 생성 및 저장이 잘 되어 있는지 확인
//		System.out.println(Beans.get("/board/list.do"));
//		System.out.println(Beans.getDAO("boardDAO"));
		
		// 공지사항 ===============================================================================================
		// DAO 생성 및 저장
		Beans.putDAO("noticeDAO", new NoticeDAO());
		
		Beans.putController("/notice", new NoticeController());
		
		// service 생성 후 저장
		Beans.put("/notice/list.do", new NoticeListService());
		Beans.put("/notice/view.do", new NoticeViewService());
		Beans.put("/notice/write.do", new NoticeWriteService());
		Beans.put("/notice/update.do", new NoticeUpdateService());
		
		// service에 DAO넣기
		Beans.get("/notice/list.do").setDAO(Beans.getDAO("noticeDAO"));
		Beans.get("/notice/view.do").setDAO(Beans.getDAO("noticeDAO")); 
		Beans.get("/notice/write.do").setDAO(Beans.getDAO("noticeDAO"));
		Beans.get("/notice/update.do").setDAO(Beans.getDAO("noticeDAO"));
		
		// 회원관리 ========================================================================================================
		// dao 생성 저장
		Beans.putDAO("memberDAO", new MemberDAO());
		
		Beans.putController("/member", new MemberController());
		
		// service 생성 후 저장
		Beans.put("/member/login.do", new MemberLoginService());
		Beans.put("/member/list.do", new MemberListService());
		Beans.put("/member/gradeModify.do", new MemberGradeModifyService()); 
		Beans.put("/member/register.do", new MemberRegisterService()); 
		Beans.put("/member/view.do", new MemberViewService());
		
		
		System.out.println("Init.init().Beans.get(\"/member/register.do\" : " + Beans.get("/member/register.do"));
		System.out.println("Init.init().Beans.getDAO(\"/memberDAO\" : " + Beans.getDAO("memberDAO"));

		// service에 DAO 넣기
		Beans.get("/member/list.do").setDAO(Beans.getDAO("memberDAO"));
		Beans.get("/member/login.do").setDAO(Beans.getDAO("memberDAO"));
		Beans.get("/member/gradeModify.do").setDAO(Beans.getDAO("memberDAO"));
		Beans.get("/member/register.do").setDAO(Beans.getDAO("memberDAO"));
		Beans.get("/member/view.do").setDAO(Beans.getDAO("memberDAO"));
		
		
		// 메시지 ============================================================================================================
		Beans.putDAO("messageDAO", new MessageDAO());	// messageDAO를 생성 key = "messageDAO"로 Beans로 저장해 놓는다.

		Beans.putController("/message", new MessageController());
		
		// 서비스 저장
		Beans.put("/message/list.do", new MessageListService());
		Beans.put("/message/write.do", new MessageWriteService());
		Beans.put("/message/view.do", new MessageViewService());
		Beans.put("/message/delete.do", new MessageDeleteService());
		
		// DAO넣기
		Beans.get("/message/list.do").setDAO(Beans.getDAO("messageDAO"));
		Beans.get("/message/write.do").setDAO(Beans.getDAO("messageDAO"));	// Beans에서  key = /message/write.do로 저장해 놓은 것을 꺼낸 뒤 (MessageWriteService) Beans에서 key =  messageDAO로 저장해 놓은 것을 꺼낸다(MessageDAO).
		Beans.get("/message/view.do").setDAO(Beans.getDAO("messageDAO"));	// key가 다르면 null이 나온다. = NullPointException
		Beans.get("/message/delete.do").setDAO(Beans.getDAO("messageDAO"));
		
		System.out.println("Init.init().Beans.getController(\"/message) : " +  Beans.getController("/message"));
		
		// 질문답변 객체를 생성 후 저장 ====================================
		// dao 생성 저장
		Beans.putDAO("qnaDAO", new QnaDAO());
		
		// service 생성 저장
		Beans.put("/qna/list.do", new QnaListService());
		Beans.put("/qna/question.do", new QnaQuestionService());
		Beans.put("/qna/view.do", new QnaViewService());
		Beans.put("/qna/answer.do", new QnaAnswerService());
		
		// service에 dao 넣기 - 조립
		Beans.get("/qna/list.do").setDAO(Beans.getDAO("qnaDAO"));
		Beans.get("/qna/question.do").setDAO(Beans.getDAO("qnaDAO"));
		Beans.get("/qna/view.do").setDAO(Beans.getDAO("qnaDAO"));
		Beans.get("/qna/answer.do").setDAO(Beans.getDAO("qnaDAO"));
		
		// image ===============================================================================================
		// DAO 생성 및 저장
		Beans.putDAO("imageDAO", new ImageDAO());
		
		// service 생성 후 저장
		Beans.put("/image/list.do", new ImageListService());
		Beans.put("/image/write.do", new ImageWriteService());
		Beans.put("/image/view.do", new ImageViewService());
		Beans.put("/image/updateFile.do", new ImageUpdateFileService());
		Beans.put("/image/delete.do", new ImageDeleteService());
		
		// service에 DAO넣기
		Beans.get("/image/list.do").setDAO(Beans.getDAO("imageDAO"));
		Beans.get("/image/write.do").setDAO(Beans.getDAO("imageDAO"));
		Beans.get("/image/view.do").setDAO(Beans.getDAO("imageDAO"));
		Beans.get("/image/updateFile.do").setDAO(Beans.getDAO("imageDAO"));
		Beans.get("/image/delete.do").setDAO(Beans.getDAO("imageDAO"));

		System.out.println("Init.init().Beans.get(\"/notice/update.do\" : " + Beans.get("/notice/update.do"));
		System.out.println("Init.init().Beans.getDAO(\"/noticeDAO\" : " + Beans.getDAO("noticeDAO")); 
		
		// Oracle Driver와 필요한 method 로딩 
		try {
			
			// class 안에 있는 static부분이 로딩 되고  static{}이 실행된다.
			Class.forName("com.webjjang.util.db.DBInfo");
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			
			throw new ServletException("드라이버 확인하는 처리중 오류가 발생하였습니다.");
			
		}
		
	}

}
