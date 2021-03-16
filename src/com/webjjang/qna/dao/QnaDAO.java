package com.webjjang.qna.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.webjjang.qna.vo.QnaVO;
import com.webjjang.util.PageObject;
import com.webjjang.util.db.DBInfo;
import com.webjjang.util.db.DBSQL;

public class QnaDAO {

	// 필요한 객체 선언
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// 1. list
	public List<QnaVO> list(PageObject pageObject) throws Exception{
		List<QnaVO> list = null;
		
		try {
			
			// 1. 2.
			con = DBInfo.getConnection();
			// 3. 4.
			System.out.println("QnaDAO.list().DBSQL.QNA_LIST : " + DBSQL.QNA_LIST);
			pstmt = con.prepareStatement(DBSQL.QNA_LIST);
			pstmt.setLong(1, pageObject.getStartRow());
			pstmt.setLong(2, pageObject.getEndRow());
			
			// 5. 
			rs = pstmt.executeQuery();
			
			// 6.
			if(rs != null) {
				while(rs.next()) {
					if(list == null) list = new ArrayList<>();
					//  - 번호, 제목, 작성자이름(작성자ID), 작성일, 조회수, 들여쓰기
					QnaVO vo = new QnaVO();
					vo.setNo(rs.getLong("no"));
					vo.setTitle(rs.getString("title"));
					vo.setName(rs.getString("name"));
					vo.setId(rs.getString("id"));
					vo.setWriteDate(rs.getString("writeDate"));
					vo.setHit(rs.getLong("hit"));
					vo.setLevNo(rs.getLong("levNo"));
					System.out.println("QnaDAO.list().vo : " + vo);
					list.add(vo);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("질문 답변 리스트 DB 처리 오류");
		} finally {
			DBInfo.close(con, pstmt, rs);
		}
		
		return list;
	}

	// 1-1. getTotalRow - 전체 데이터 갯수 가져오기
	public Long getTotalRow() throws Exception{
		Long result = 0L;
		
		try {
			
			// 1. 2.
			con = DBInfo.getConnection();
			// 3. 4.
			pstmt = con.prepareStatement(DBSQL.QNA_GET_TOTALROW);
			
			// 5. 
			rs = pstmt.executeQuery();
			
			// 6.
			if(rs != null && rs.next()) {
				result = rs.getLong(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("질문 답변 전체데이터 갯수 DB 처리 오류");
		} finally {
			DBInfo.close(con, pstmt, rs);
		}
		
		return result;
	}
	
	// 3-1. 질문하기 - question : 제목, 내용 -> 사용자 입력, 아이디 -> session의 로그인 정보 : JSP에서 수집
	public int question(QnaVO vo) throws Exception{
		int result = 0;
		
		try {
			
			// 1. 2.
			con = DBInfo.getConnection();
			// 3. 4.
			pstmt = con.prepareStatement(DBSQL.QNA_QUESTION);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getId());
			
			// 5. 
			result = pstmt.executeUpdate();
			
			// 6.
			System.out.println("QnaDAO.question() - 질문 등록 완료~!");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("질문 등록 중 DB 처리 오류");
		} finally {
			DBInfo.close(con, pstmt, rs);
		}
		
		return result;
	}
	
	// 2. view - 질문 답변 보기
	public QnaVO view(long no) throws Exception{
		
		QnaVO vo = null;
			
		try {
			
			con = DBInfo.getConnection();
			
			pstmt = con.prepareStatement(DBSQL.QNA_VIEW);
			
			pstmt.setLong(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs != null && rs.next()) {
				
				vo = new QnaVO();
				
				vo.setNo(rs.getLong("no"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setName(rs.getString("name"));
				vo.setId(rs.getString("id"));
				vo.setWriteDate(rs.getString("writeDate"));
				vo.setHit(rs.getLong("hit"));
				
				// 글보기 할 때는 관련 글 번호, 순서번호, 들여쓰기 정보를 필요로 하지 않아서 버린다.
				// 답변 쓰기 할 때는 위와 같은 정보가 필요하다. 따라서 일단 담아 놓는다,
				vo.setRefNo(rs.getLong("refNo"));
				vo.setOrdNo(rs.getLong("ordNo"));
				vo.setLevNo(rs.getLong("levNo"));
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();
			
			throw new Exception("질문 답변 글 보기 DB처리중 오류가 발생하였습니다.");
			
		} finally {
			
			DBInfo.close(con, pstmt, rs);
			
		}
		
		return vo;
		
	}
		
	// 2-1. increase - 조회수 1 증가
	public int increase(long no) throws Exception{
		
		int result = 0;
			
		try {
			
			con = DBInfo.getConnection();
			
			pstmt = con.prepareStatement(DBSQL.QNA_INCREASE);
			
			pstmt.setLong(1, no);
			
			result = pstmt.executeUpdate();
			
			System.out.println("조회수 1 증가 완료");
			
		} catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();
			
			throw new Exception("질문 답변 글 보기의 조회수 1 증가 DB처리중 오류가 발생하였습니다.");
			
		} finally {
			
			DBInfo.close(con, pstmt);
			
		}
		
		return result;
	}
		
	// 3-2 답변하기 - answer : 제목, 내용 -> 사용자 입력, 아이디 : session의 로그인 정보 : JSP에서 수집
	// 관련 글 번호, 순서번호, 들여쓰기, 부모글번호 -> 전달 받는다.
	public int answer(QnaVO vo) throws Exception{
		
		int result = 0;
			
		try {
			
			con = DBInfo.getConnection();
			
			pstmt = con.prepareStatement(DBSQL.QNA_ANSWER);
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getId());
			pstmt.setLong(4, vo.getRefNo());
			pstmt.setLong(5, vo.getOrdNo());
			pstmt.setLong(6, vo.getLevNo());
			pstmt.setLong(7, vo.getParentNo());
			
			result = pstmt.executeUpdate();
				
			
		} catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();
			
			throw new Exception("답변 달기 DB처리중 오류가 발생하였습니다.");
			
		} finally {
			
			DBInfo.close(con, pstmt);
			
		}
		
		return result;
		
	}
	
	// 3-1. increase - 조회수 1 증가
	public int ordNoIncrease(QnaVO vo) throws Exception{
		
		int result = 0;
			
		try {
			
			con = DBInfo.getConnection();
			
			pstmt = con.prepareStatement(DBSQL.QNA_ANSWER_INCREASE_ORDNO);
			
			pstmt.setLong(1, vo.getRefNo());
			pstmt.setLong(2, vo.getOrdNo());
			
			result = pstmt.executeUpdate();
			
			System.out.println("답변하기의 순서번호 1증가 완료");
			
		} catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();
			
			throw new Exception("답변하기의 순서번호 1증가 처리중 DB오류가 발생하였습니다.");
			
		} finally {
			
			DBInfo.close(con, pstmt);
			
		}
		
		return result;
	}
		
	
}
