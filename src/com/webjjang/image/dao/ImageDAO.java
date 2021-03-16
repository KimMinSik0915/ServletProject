package com.webjjang.image.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.webjjang.image.vo.ImageVO;
import com.webjjang.util.PageObject;
import com.webjjang.util.db.DBInfo;
import com.webjjang.util.db.DBSQL;

public class ImageDAO {

	Connection con = null;	// 필요한 객체 선언
	
	PreparedStatement pstmt = null;
	
	ResultSet rs = null;
	
	
	// 1. Image list -------------------------------------------------------------------------
	public List<ImageVO> list(PageObject pageObject) throws Exception {

		// 넘어오는 데이터 확인
		System.out.println("ImageDAO.list().pageObject : " + pageObject);
		
		List<ImageVO> list = null;
		
		try {
			
			// 1. 드라이버 확인 + 2. 연결
			con = DBInfo.getConnection();
			System.out.println("ImageDAO.list().con : " + con);
			
			// 3. SQL = DBSQL + 4. 실행객체 + data셋팅
			System.out.println("ImageDAO.list().DBSQL : " + DBSQL.BOARD_LIST);
			pstmt = con.prepareStatement(DBSQL.IMAGE_LIST);
			
			pstmt.setLong(1, pageObject.getStartRow());	// 시작번호
			pstmt.setLong(2, pageObject.getEndRow());	// 끝나는 번호
			
			System.out.println("ImageDAO.list().pstmt : " + pstmt);
			
			// 5. 실행
			rs = pstmt.executeQuery();

			System.out.println("ImageDAO.list().rs : " + rs);
			
			// 6. 데이터 표시 : 데이터 담기
			if (rs != null) {
				
				while (rs.next()) {
					
					if(list == null) {
						
						list = new ArrayList<ImageVO>();
						
					}	// end of if (list == null)
					
					ImageVO vo = new ImageVO();
					
					vo.setNo(rs.getLong("no"));
					vo.setTitle(rs.getString("title"));
					vo.setName(rs.getString("name"));
					vo.setId(rs.getString("id"));
					vo.setWriteDate(rs.getString("writeDate"));
					vo.setFileName(rs.getString("fileName"));
					
					list.add(vo);
					
					// System.out.println("ImageDAO.list().while().vo : " +  vo);
					
				}	// end of while (rs.next())
				
			}	// end of ir (rs != null)
			
		} catch (Exception e) {
			
			e.printStackTrace();	// 개발자를 위해서 오류를 콘솔에 표시한다.
			
			throw new Exception("이미지 게시판 리스트 실행 중 DB처리 오류"); 	// 사용자를 위한 오류처리
			
		} finally {
			
			DBInfo.close(con, pstmt, rs);		// 7. 닫기
			
		}
		
		System.out.println("ImageDAO.list().list : " + list);
		
		return list;
		
	}
	
	// 1-1 Image 전체 데이터 개수 -------------------------------------------------------------
	public long getTotalRow() throws Exception {
		
		System.out.println("ImageDAO.getTotalRow");
		
		long result = 0;
		
		try {
			
			
			con = DBInfo.getConnection();
			System.out.println("ImageDAO.getTotalRow().con : " + con);
			
			pstmt = con.prepareStatement(DBSQL.IMAGE_GET_TOTALROW);
			System.out.println("ImageDAO.getTotalRow().DBSQL.BOARD_GET_TOTALROW : " + DBSQL.BOARD_GET_TOTALROW);
			
			// rs는 출력해 볼수 있다. 하지만 rs.next()를 출력하면 데이터를 한개 넘기게 된다.
			rs = pstmt.executeQuery();
			System.out.println("ImageDAO.getTotalRow().re : " + rs);
			
			if (rs != null && rs.next()) {
				
				result = rs.getLong(1);
				
			}
			
			System.out.println("ImageDAO.getTotalRow().result : " + result);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			throw new Exception("이미지 게시판 데이터 전체 갯수를 가져오는 DB처리중 오류가 발생하였습니다.");
			
		} finally {
			
			DBInfo.close(con, pstmt, rs);
			
		}
		
		System.out.println("ImageDAO.getTotalRow().result : " + result);
		
		return result;
		
	}
	
	// Image 보기 ---------------------------------------------------------------------------
	public ImageVO view(long no) throws Exception {

		// 넘어오는 데이터 확인
		ImageVO vo = null;
		
		try {
			
			// 1. 드라이버 확인 + 2. 연결
			con = DBInfo.getConnection();
			System.out.println("ImageDAO.list().con : " + con);
			
			// 3. SQL = DBSQL + 4. 실행객체 + data셋팅
			System.out.println("ImageDAO.list().DBSQL : " + DBSQL.IMAGE_VIEW);
			pstmt = con.prepareStatement(DBSQL.IMAGE_VIEW);
			
			pstmt.setLong(1, no);
			
			// 5. 실행
			rs = pstmt.executeQuery();

			System.out.println("ImageDAO.list().rs : " + rs);
			
			// 6. 데이터 표시 : 데이터 담기
			if (rs != null && rs.next()) {
				
				vo = new ImageVO();
				
				vo.setNo(rs.getLong("no"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setName(rs.getString("name"));
				vo.setId(rs.getString("id"));
				vo.setWriteDate(rs.getString("writeDate"));
				vo.setFileName(rs.getString("fileName"));
			
				System.out.println("ImageDAO.view().vo : " + vo);
				
			}	// end of ir (rs != null)
			
		} catch (Exception e) {
			
			e.printStackTrace();	// 개발자를 위해서 오류를 콘솔에 표시한다.
			
			throw new Exception("이미지 게시판 보기 실행 중 DB처리 오류"); 	// 사용자를 위한 오류처리
			
		} finally {
			
			DBInfo.close(con, pstmt, rs);		// 7. 닫기
			
		}
		
		return vo;
		
	}
	
	// 3. Image 등록 ----------------------------------------------------------------------
	public int write(ImageVO vo) throws Exception {
		
		int result = 0;
		
		
		try {
			
			con = DBInfo.getConnection();
			
			pstmt = con.prepareStatement(DBSQL.IMAGE_WERITE);
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getId());
			pstmt.setString(4, vo.getFileName());
			
			result = pstmt.executeUpdate();
			
			System.out.println("ImageDAO.write() : 이미지 등록 완료");
			
		} catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();
			
			throw new Exception("이미지 등록 처리중 DB에 오류가 발생하였습니다.");
			
		} finally {
			
			DBInfo.close(con, pstmt);
			
		}
		
		
		return result;
		
	}

	// 4 - 1. Image 파일 정보 수정 (번호, 파일명) ------------------------------------------------
	public int updateFile(ImageVO vo) throws Exception {
		
		int result = 0;
		
		
		try {
			
			con = DBInfo.getConnection();
			
			pstmt = con.prepareStatement(DBSQL.IMAGE_UPDATE_FILE);
			
			pstmt.setString(1, vo.getFileName());
			pstmt.setLong(2, vo.getNo());
			
			result = pstmt.executeUpdate();
			
			System.out.println("ImageDAO.write() : 이미지 정보 수정 완료");
			
		} catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();
			
			throw new Exception("이미지 정보 수정중 DB에 오류가 발생하였습니다.");
			
		} finally {
			
			DBInfo.close(con, pstmt);
			
		}
		
		
		return result;
		
	}

	// 5. Image 게시판 글 삭제
	public int delete(long no) throws Exception {
		
		int result = 0;
		
		
		try {
			
			con = DBInfo.getConnection();
			
			pstmt = con.prepareStatement(DBSQL.IMAGE_DELETE);
			
			pstmt.setLong(1, no);
			
			result = pstmt.executeUpdate();
			
			System.out.println("ImageDAO.write() : 이미지 게시판 글 삭제 완료");
			
		} catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();
			
			throw new Exception("이미지 게시판 글 삭제중 DB에 오류가 발생하였습니다.");
			
		} finally {
			
			DBInfo.close(con, pstmt);
			
		}
		
		
		return result;
		
	}

	
}
