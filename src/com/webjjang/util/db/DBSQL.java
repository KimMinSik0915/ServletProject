package com.webjjang.util.db;

public class DBSQL {

	// 게시판 쿼리 ====================================================================================================
	public static final String BOARD_LIST 	// 게시판 리스트
	= " SELECT rnum, no, title, writer,"
	+ " TO_CHAR(writeDate, 'yyyy.mm.dd') writeDate, hit FROM( "
		+ " SELECT rownum rnum, no, title, writer, writeDate, hit FROM ("
			+ " SELECT no, title, writer, writeDate, hit FROM board"
			+ " order by no desc "
		+ " ) "
	+ ") where rnum between ? and ?  ";
	
	public static final String BOARD_VIEW 	// 게시판 글 보기
	= " SELECT no, title, content, writer, "
	+ " TO_CHAR(writeDate, 'yyyy.mm.dd') writeDate, hit "
	+ " FROM board "
	+ " WHERE no = ?";
	
	public static final String BOARD_WIRTE 	// 게시판 글 쓰기
	= " INSERT INTO board(no, title, content, writer) "
	+ " VALUES(board_seq.NEXTVAL, ?, ?, ?) ";
	
	public static final String BOARD_UPDATE	// 게시판 글 수정
	= " UPDATE board SET title = ? , content = ?, writer = ? "
	+ " WHERE no = ? ";
	
	public static final String BOARD_DELETE 	// 게시판 글 삭제
	= " DELETE FROM board "
	+ " WHERE no = ? ";
	
	public static final String BOARD_INCREASE 	// 조회수 1 증가
	= " UPDATE board SET hit = hit + 1 "
	+ " WHERE no = ? ";
	
	public static final String BOARD_GET_TOTALROW 	// 페이지 처리
	= " SELECT COUNT(*) FROM board ";
	
	
	
	// 공지사항 쿼리 ===============================================================================================
	// 1. 공지사항 리스트 : 번호, 제목, 공지시작일, 공지종료일, 최근 수정일
	public static final String NOTICE_LIST 	// 게시판 리스트
	= " SELECT rnum, no, title, "
	+ " TO_CHAR(startDate, 'yyyy.mm.dd') startDate, "
	+ " TO_CHAR(writeDate, 'yyyy.mm.dd') writeDate, "
	+ " TO_CHAR(endDate, 'yyyy.mm.dd') endDate, "
	+ " TO_CHAR(updateDate, 'yyyy.mm.dd') updateDate FROM( "
		+ " SELECT rownum rnum, no, title, startDate, writeDate, endDate, updateDate FROM ("
			+ " SELECT no, title, startDate, writeDate, endDate, updateDate FROM notice"
			+ " order by no desc "
		+ " ) "
	+ ") where rnum between ? and ?  ";
	
	// 2.
	public static final String NOTICE_GET_TOTALROW 	// 페이지 처리
	= " SELECT COUNT(*) FROM notice ";
	
	// 2. 공지사항 등록
	public static final String NOTICE_WIRTE 	// 게시판 글 쓰기
	= " INSERT INTO notice(no, title, content, startDate, endDate) "
	+ " VALUES(notice_seq.NEXTVAL, ?, ?, ?, ?) ";
	
	
	// 회원관리 쿼리 ===============================================================================================
	public static final String MEMBER_LOGIN =	// 로그인 처리
	  " SELECT m.id, m.name, m.gradeNo, g.gradeName FROM member m, grade g "
	+ " WHERE (m.id = ? AND m.pw = ?) AND (m.gradeNo = g.gradeNo) ";
	
	// 회원관리 리스트 ==============================================================================================
	public static final String MEMBER_LIST =
	  " SELECT m.id, m.name, m.gender, "
	+ " TO_CHAR(birth, 'yyyy.mm.dd') birth, "
	+ " m.status, m.tel, m.gradeNo, g.gradeName "
	+ " FROM member m, grade g "
	+ " WHERE m.gradeNo = g.gradeNo" 
	+ " ORDER BY id ASC ";
	
	// 회원관리 리스트 ===============================================================================================
	public static final String MEMBER1_LIST 	// 회원관리 리스트 : id, name, gender, birth, tel, status, gradeNo, gradeName
	= " SELECT rnum, id, name, gender, "
	+ " TO_CHAR(birth, 'yyyy.mm.dd') birth, tel, status, gradeNo, gradeName FROM ( "
		+ " SELECT ROWNUM rnum, id, name, gender, birth, tel,status, gradeNo, gradeName FROM ( "
			+ " SELECT m.id, m.name, m.gender, m.birth, m.tel, m.status, m.gradeNo, g.gradeName FROM member m, grade g"
			+ " WHERE m.gradeNo = g.gradeNo "
			+ " ORDER BY id ASC "
		+ " ) "
	+ " ) WHERE rnum BETWEEN ? AND ? ";
	
	// 회원등급 수정 =========================================================================================================
	public static final String MEMBER_GRADE_MODIFY = 
	  " UPDATE member set gradeNo = ? "
	+ " WHERE id = ? ";
	
	// 내 정보 관리 ==========================================================================================================
	public static final String MEMBER_VIEW =
	  " SELECT m.id, m.name, m.gender, "
	+ " TO_CHAR(m.birth, 'yyyy.mm.dd') birth, "		// alius에는 붙이지 않는다. 형식(TO_CHAR(m.xxxx, 'yyyy.mm.dd') xxxx)
	+ " m.tel, m.email, "
	+ " TO_CHAR(m.regDate, 'yyyy.mm.dd') regDate, "
	+ " m.gradeNo, g.gradeName, m.status "
	+ " FROM member m, grade g "
	+ " WHERE id = ? AND (m.gradeNo = g.gradeNo) "; 
	
	
	// 메시지 ==========================================================================================================
	public static final String MESSAGE_LIST 
	= " SELECT rnum, no, sender, "
	+ " TO_CHAR(sendDate, 'yyyy.mm.dd') sendDate,"
	+ " accepter, "
	+ " TO_CHAR(acceptDate, 'yyyy.mm.dd') acceptDate"
	+ " FROM ( "
		+ " SELECT ROWNUM rnum, no, sender, sendDate, accepter, acceptDate "
		+ " FROM ( "
			+ " SELECT no, sender, sendDate, accepter, acceptDate "
			+ " FROM message "
			+ " WHERE sender = ? OR accepter = ?"
			+ " ORDER BY no DESC "
		+ " ) "
	+ " ) WHERE rnum BETWEEN ? AND ?";
	
	
	public static final String MESSAGE_GET_TOTLAROW
	= " SELECT COUNT(*) "
	+ " FROM message ";
	
	// 메시지 쓰기
	public static final String MESSAGE_WRITE
	= " INSERT INTO message (no, sender, content, accepter) "
	+ " VALUES(message_seq.NEXTVAL, ?, ? ,?) ";
	
	// 메시지 읽기
	public static final String MESSAGE_VIEW
	= " SELECT no, content, sender,"
	+ " TO_CHAR(sendDate, 'yyyy.mm.dd') sendDate, "
	+ " accepter, "
	+ " TO_CHAR(acceptDate, 'yyyy.mm.dd') acceptDate "
	+ " FROM message "
	+ " WHERE no = ?";
	
	// 읽기 표시 처리 : 보려는 글과 같아야 하며 받은 사람이 본인이어야 한다.
	public static final String MESSAGE_VIEW_READ
	= " UPDATE message SET acceptDate = sysDate "
	+ " WHERE no = ? AND accepter = ? AND acceptDate IS NULL ";
	
	// 읽지 않음 표시 처리
	public static final String MESSAGE_UNREAD
	= " UPDATE message SET acceptDAte = null "
	+ " WHERE no = ? AND accepter = ? AND acceptDate IS NOT NULL ";
	
	public static final String MESSAGE_DELETE
	= " DELETE FROM message "
	+ "	WHERE no = ? ";
	
	
	// qna쿼리 ===========================================================================================
	// QnA 쿼리
	// 1. 리스트 - 번호, 제목, 작성자이름(작성자ID), 작성일, 조회수, 들여쓰기
	public static final String QNA_LIST 
	= "select rnum, no, title, name, id, "
	+ " to_char(writeDate, 'yyyy.mm.dd') writeDate, hit, levNo "
	+ " from( "
		+ " select rownum rnum, no, title, name, id, writeDate, hit, levNo from ("
			+ " select q.no, q.title, m.name, q.id, q.writeDate, q.hit, q.levNo "
			+ " from qna q, member m "
			+ " where q.id = m.id "
			+ " order by q.refNo desc, q.ordNo "
		+ " ) "
	+ ") where rnum between ? and ?  ";
	
	public static final String QNA_GET_TOTALROW
	= " select count(*) from qna ";
	
	// 3-1. 질문하기
	public static final String QNA_QUESTION 
	= " insert into qna(no, title, content, id, refNo, ordNo, levNo, parentNo) "
	+ " values(qna_seq.nextval, ?, ?, ?, qna_seq.nextval, 1, 0, qna_seq.nextval) ";
	
	// 3-2. 답변하기 : 3-3을 먼저 실행시킨 후 3-2를 실행해야 한다.
	public static final String QNA_ANSWER 
	= " insert into qna(no, title, content, id, refNo, ordNo, levNo, parentNo) "
	+ " values(qna_seq.nextval, ?, ?, ?, ?, ?, ?, ?) ";
	
	// 3-3. 답변하기의 경우 등록하기 전에 관련글 번호가 같고 순서번호가 같거나 큰 경우 = 순서번호를 1 증가 시켜야 한다.
	public static final String QNA_ANSWER_INCREASE_ORDNO
	= " UPDATE qna SET ordNo = ordNo + 1 "
	+ " WHERE refNo = ? AND ordNo >= ? ";
	
	// 2. 질문하기 -- 답변 쓰기를 할 때도 동일한 쿼리를 사용하려고 한다. 추가 정보 : refno(관련 글 번호), ordNo(순서번호), levNo (들여쓰기)
	public static final String QNA_VIEW
	= " select q.no, q.title, q.content, m.name, q.id, "
	+ " TO_CHAR(q.writeDate, 'yyyy.mm.dd') writeDate, "
	+ " q.hit, q.refNo, q.ordNo, q.levNo "
	+ " FROM qna q, member m "
	+ " WHERE (q.no = ?) AND (m.id = q.id) ";
	
	// 2-1 조회수 1 증가
	public static final String QNA_INCREASE 
	= " UPDATE qna SET hit = hit + 1 "
	+ " WHERE no = ? ";
	
	// 이미지 게시판 ===================================================================================================
	public static final String IMAGE_LIST 
	= "select rnum, no, title, name, id, "
	+ " TO_CHAR(writeDate, 'yyyy.mm.dd') writeDate, fileName " 
	+ " from( "
		+ " select rownum rnum, no, title, name, id, writeDate, fileName from ("
			+ " select i.no, i.title, m.name, i.id, i.writeDate, i.fileName  "
			+ " from image i, member m "
			+ " where i.id = m.id "
			+ " ORDER BY no DESC "
		+ " ) "
	+ ") WHERE rnum BETWEEN ? AND ?  ";
	
	// 페이지 처리를 위해  : 리스트
	public static final String IMAGE_GET_TOTALROW
	= " select count(*) from image ";
	
	public static final String IMAGE_WERITE
	= " INSERT INTO image (no, title, content, id, fileName) "
	+ " VALUES(image_seq.NEXTVAL, ?, ? ,?, ?) ";
	
	public static final String IMAGE_VIEW
	= " SELECT i.no, i.title, i.content, m.name, i.id, "
	+ " TO_CHAR(i.writeDate, 'yyyy.mm.dd') writeDate, i.fileName "
	+ " FROM image i, member m "
	+ " WHERE (no = ?) AND (i.id = m.id) ";
	
	// 파일정보 수정
	public static final String IMAGE_UPDATE_FILE
	= " UPDATE image SET fileName = ? "
	+ " WHERE no = ? ";
	
	// 이미지 게시판 삭제
	public static final String IMAGE_DELETE
	= " DELETE FROM image WHERE no = ? ";
	
}
