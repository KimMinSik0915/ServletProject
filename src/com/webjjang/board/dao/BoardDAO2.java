package com.webjjang.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.webjjang.board.vo.BoardVO;
import com.webjjang.util.db.DBInfo;
import com.webjjang.util.db.DBSQL;

/*
 * 필요한 method
 * list() getTotalRow() view() increase() write() update() delete()
 * 
*/
public class BoardDAO2 {

	// 필요한 객체 선언 : con, pstmt, rs
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// 1. 게시판 리스트 : con, pstmt, rs 모두 사용
	public List<BoardVO> list() throws Exception {
		
		List<BoardVO> list = null;
		
		try {
			
			// 1. 드라이버 확인 + 2. 연결
			con = DBInfo.getConnection();
			
			// 3. SQL = DBSQL + 4. 실행객체 + data셋팅
			pstmt = con.prepareStatement(DBSQL.BOARD_LIST);
			
			pstmt.setLong(1, 1);	// 시작번호
			pstmt.setLong(2, 10);	// 끝나는 번호
			
			// 5. 실행
			rs = pstmt.executeQuery();
			
			// 6. 데이터 표시 : 데이터 담기
			if (rs != null) {
				
				while (rs.next()) {
					
					if(list == null) {
						
						list = new ArrayList<BoardVO>();
						
					}	// end of if (list == null)
					
					BoardVO vo = new BoardVO();
					
					vo.setNo(rs.getLong("no"));
					vo.setTitle(rs.getString("title"));
					vo.setWriter(rs.getString("writer"));
					vo.setWriteDate(rs.getString("writeDate"));
					vo.setHit(rs.getLong("hit"));
					
					list.add(vo);
					
				}	// end of while (rs.next())
				
			}	// end of ir (rs != null)
			
			
		} catch (Exception e) {
			
			e.printStackTrace();	// 개발자를 위해서 오류를 콘솔에 표시한다.
			
			throw new Exception("게시판 리스트 실행 중 DB처리 오류"); 	// 사용자를 위한 오류처리
			
		} finally {
			
			DBInfo.close(con, pstmt, rs);		// 7. 닫기
			
		}
		
		return list;
		
	}
	
	// 1-1 전체 data 개수 구하기
	public long getTotalRow() throws Exception {
		
		return 0;
		
	}
	
	// 2. 게시판 글 보기
	public BoardVO view(long no) throws Exception {
		
		BoardVO vo = null;
		
		try {
			
			// 1. 드라이버 확인 + 2. 연결
			con = DBInfo.getConnection();
			
			// 3. SQL = DBSQL + 4. 실행객체 + data셋팅
			pstmt = con.prepareStatement(DBSQL.BOARD_VIEW);
			
			pstmt.setLong(1, no);	// 시작번호
			
			// 5. 실행 : 데이터 한개가 나온다(반복문 필요 없음)
			rs = pstmt.executeQuery();
			
			// 6. 데이터 표시 : 데이터 담기
			if (rs != null && rs.next()) {
				
				vo = new BoardVO();
				
				vo.setNo(rs.getLong("no"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setWriteDate(rs.getString("writeDate"));
				vo.setHit(rs.getLong("hit"));
					
			}	// end of if (rs != null && rs.next())
				
		} catch (Exception e) {
			
			e.printStackTrace();	// 개발자를 위해서 오류를 콘솔에 표시한다.
			
			throw new Exception("게시판 글 보기 실행 중 DB처리 오류"); 	// 사용자를 위한 오류처리
			
		} finally {
			
			DBInfo.close(con, pstmt, rs);		// 7. 닫기
			
		}
		
		return vo;
		
	}
	
	// 2-1 조회수 1 증가(리스트 -> 글 보기)
	public int increase(long no) throws Exception {
		
		return 0;
		
	}
	
	// 3. 게시판 글 쓰기
	public int write(BoardVO vo) throws Exception {
		
		int result = 0;
		
		try {
			
			// 1. 드라이버 확인 + 2. 연결
			con = DBInfo.getConnection();
			
			// 3. SQL = DBSQL + 4. 실행객체 + data셋팅
			pstmt = con.prepareStatement(DBSQL.BOARD_WIRTE);
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getWriter());
			
			// 결과값은 int type으로 나온다.
			result = pstmt.executeUpdate();
			
			return result;
			
		} catch (Exception e) {
			
			e.printStackTrace();	// 개발자를 위해서 오류를 콘솔에 표시한다.
			
			throw new Exception("게시판 글 보기 실행 중 DB처리 오류"); 	// 사용자를 위한 오류처리
			
		} finally {
			
			DBInfo.close(con, pstmt);	// 7. 닫기
			
		}
		
		
	}
	
	// 4. 게시판 글 수정
	public int update(BoardVO vo) throws Exception {
		
		return 0;
		
	}
	
	// 5. 게시판 글 삭제
	public int delete(long no) throws Exception {
		
		return 0;
		
	}
	
}
