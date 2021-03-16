package com.webjjang.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.webjjang.board.vo.BoardVO;
import com.webjjang.util.db.DBInfo;
import com.webjjang.util.db.DBSQL;

import com.webjjang.util.PageObject;

/*
 * 필요한 method
 * list() getTotalRow() view() increase() write() update() delete()
 * 
*/
public class BoardDAO {

	// 필요한 객체 선언 : con, pstmt, rs
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// 1. 게시판 리스트 : con, pstmt, rs 모두 사용
	public List<BoardVO> list(PageObject pageObject) throws Exception {

		// 넘어오는 데이터 확인
		System.out.println("BoardDAO.list().pageObject : " + pageObject);
		
		List<BoardVO> list = null;
		
		try {
			
			// 1. 드라이버 확인 + 2. 연결
			con = DBInfo.getConnection();
			System.out.println("BoardDAO.list().con : " + con);
			
			// 3. SQL = DBSQL + 4. 실행객체 + data셋팅
			System.out.println("BoardDAO.list().DBSQL : " + DBSQL.BOARD_LIST);
			pstmt = con.prepareStatement(DBSQL.BOARD_LIST);
			
			pstmt.setLong(1, pageObject.getStartRow());	// 시작번호
			pstmt.setLong(2, pageObject.getEndRow());	// 끝나는 번호
			
			System.out.println("BoardDAO.list().pstmt : " + pstmt);
			
			// 5. 실행
			rs = pstmt.executeQuery();

			System.out.println("BoardDAO.list().rs : " + rs);
			
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
					
					System.out.println("BoardDAO.list().while().vo : " +  vo);
					
				}	// end of while (rs.next())
				
			}	// end of ir (rs != null)
			
		} catch (Exception e) {
			
			e.printStackTrace();	// 개발자를 위해서 오류를 콘솔에 표시한다.
			
			throw new Exception("게시판 리스트 실행 중 DB처리 오류"); 	// 사용자를 위한 오류처리
			
		} finally {
			
			DBInfo.close(con, pstmt, rs);		// 7. 닫기
			
		}
		
		System.out.println("BoardDAO.list().list : " + list);
		
		return list;
		
	}
	
	// 1-1 전체 data 개수 구하기
	public long getTotalRow() throws Exception {
		
		System.out.println("BoardDAO.getTotalRow");
		
		long result = 0;
		
		try {
			
			
			con = DBInfo.getConnection();
			System.out.println("BoardDAO.getTotalRow().con : " + con);
			
			pstmt = con.prepareStatement(DBSQL.BOARD_GET_TOTALROW);
			System.out.println("BoardDAO.getTotalRow().DBSQL.BOARD_GET_TOTALROW : " + DBSQL.BOARD_GET_TOTALROW);
			
			// rs는 출력해 볼수 있다. 하지만 rs.next()를 출력하면 데이터를 한개 넘기게 된다.
			rs = pstmt.executeQuery();
			System.out.println("BoardDAO.getTotalRow().re : " + rs);
			
			if (rs != null && rs.next()) {
				
				result = rs.getLong(1);
				
			}
			
			System.out.println("BoardDAO.getTotalRow().result : " + result);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			throw new Exception("게시판 데이터 전체 갯수를 가져오는 DB처리중 오류가 발생하였습니다.");
			
		} finally {
			
			DBInfo.close(con, pstmt, rs);
			
		}
		
		System.out.println("BoardDAO.getTotalRow().result : " + result);
		
		return result;
		
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
		
		int result = 0;
		
		try {
			
			// 1. 드라이버 확인, 연결 객체
			con = DBInfo.getConnection();
			
			// 3. SQL, 실행객체 및 데이터 세팅
			pstmt = con.prepareStatement(DBSQL.BOARD_INCREASE);
			
			pstmt.setLong(1, no);
			
			// 5. 실행
			result = pstmt.executeUpdate();
			
			// 6. 표시
			System.out.println("조회수 1증가 성공");
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			throw new Exception("조회수 1 증가 하는 중 DB오류가 발생하였습니다.");
			
		} finally {
			
			DBInfo.close(con, pstmt);
			
		}
		
		return result;
		
	}
	
	// 3. 게시판 글 쓰기
	public int write(BoardVO vo) throws Exception {
		
		int result = 0;
		
		try {
			
			// 1. 드라이버 확인 + 연결 객체 
			con = DBInfo.getConnection();
			
			//3. SQL작성 + 실행객체
			pstmt = con.prepareStatement(DBSQL.BOARD_WIRTE);
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getWriter());
			
			// 5. 실행
			result = pstmt.executeUpdate();
			
			// 6. 표시
			System.out.println("게시판 글쓰기 성공");
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			throw new Exception("게시판 글 쓰기 처리중 DB오류가 발생하셨습니다.");
			
			
		} finally {
			
			DBInfo.close(con, pstmt);
			
		}
			
		return result;
		
	}
	
	// 4. 게시판 글 수정
	public int update(BoardVO vo) throws Exception {
		
		int result = 0;
		
		try {
			
			// 1. 드라이버 확인 + 연결 객체 
			con = DBInfo.getConnection();
			
			//3. SQL작성 + 실행객체
			pstmt = con.prepareStatement(DBSQL.BOARD_UPDATE);
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getWriter());
			pstmt.setLong(4, vo.getNo());
			
			// 5. 실행
			result = pstmt.executeUpdate();
			
			// 6. 표시
			System.out.println("게시판 글 수정 성공");
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			throw new Exception("게시판 글 수정 처리중 DB오류가 발생하셨습니다.");
			
			
		} finally {
			
			DBInfo.close(con, pstmt);
			
		}
			
		return result;
		
	}
	
	// 5. 게시판 글 삭제
	public int delete(long no) throws Exception {
		
		int result = 0;
		
		try {
			
			// 1. 드라이버 확인. 연결객체
			con = DBInfo.getConnection();
			
			// 3. SQL, 실행객체 및 데이터 세팅
			pstmt = con.prepareStatement(DBSQL.BOARD_DELETE);
			
			pstmt.setLong(1, no);
			
			result = pstmt.executeUpdate();
			
			// 6. 출력
			
			if(result == 1)
				System.out.println("게시판 글 삭제에 성공하였습니다.");
			
			else
				System.out.println("삭제하려는 글의 정보를 확인해 주세요.");
			
		} catch (Exception e) {
			
			e.printStackTrace();	// 개발자를 위한 예외 출력(500 Error) : console창에서 출력
			
			throw new Exception("게시판 글 삭제 처리중 DB오류가 발생하였습니다.");	// 사용자를 위한 예외 처리 : JSP까지 전달한다.
			
		} finally {
			
			DBInfo.close(con, pstmt);
			
		}
		
		return result;
		
	}
	
}
