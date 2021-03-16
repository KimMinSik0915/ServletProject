package com.webjjang.notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.webjjang.notice.vo.NoticeVO;
import com.webjjang.util.PageObject;
import com.webjjang.util.db.DBInfo;
import com.webjjang.util.db.DBSQL;

public class NoticeDAO {

	// 필요한 객체 선언
	Connection con = null;
	
	PreparedStatement pstmt = null;
	
	ResultSet rs = null;
	
	// 공지사항 리스트
	public List<NoticeVO> list(PageObject pageObject) throws Exception {
		
		System.out.println("NoticeDAO.list().pageObject : " + pageObject);
		
		List<NoticeVO> list = null;
		
		try {
			
			con = DBInfo.getConnection();	// 1, 2
			
			pstmt = con.prepareStatement(DBSQL.NOTICE_LIST);	// 3, 4
			
			pstmt.setLong(1, pageObject.getStartRow());
			pstmt.setLong(2, pageObject.getEndRow());
			
			rs = pstmt.executeQuery();
			
			if (rs != null) {
				
				while(rs.next()) {
					
					if (list == null) {
						
						list = new ArrayList<NoticeVO>();
						
					}
					
					NoticeVO vo = new NoticeVO();
					
					vo.setNo(rs.getLong("no"));
					vo.setTitle(rs.getString("title"));
					vo.setStartDate(rs.getString("startDate"));
					vo.setEndDate(rs.getString("endDate"));
					vo.setUpdateDate(rs.getString("updateDate"));
					
					list.add(vo);
					
				}
				
			}
			
		} catch (Exception e) {

			e.printStackTrace();
			
			throw new Exception("공지사항 리스트 처리중 DB에 오류가 발생하였습니다.");
			
		} finally {
			
			DBInfo.close(con, pstmt, rs);
			
		}
		
		return list;
		
	}
	
	// 공지사항 전체 데이터 개수 가져오기
	public long getTotalRow() throws Exception {
		
		System.out.println("NoticeDAO.getTotalRow().pageObject");
		
		long result = 0;
		
		try {
			
			con = DBInfo.getConnection();	// 1, 2
			
			pstmt = con.prepareStatement(DBSQL.NOTICE_GET_TOTALROW);	// 3, 4
			
			rs = pstmt.executeQuery();
			
			if (rs != null && rs.next()) {
				
				result = rs.getLong(1);
				
			}
			
		} catch (Exception e) {

			e.printStackTrace();
			
			throw new Exception("공지사항 전체 데이터 가져오기중 DB에 오류가 발생하였습니다.");
			
		} finally {
			
			DBInfo.close(con, pstmt, rs);
			
		}
		
		return result;
		
	}
	
	// 공지 등록
	public int write(NoticeVO vo) throws Exception {
		
		int result = 0;
		
		try {
			
			con = DBInfo.getConnection();	// 1, 2
			
			pstmt = con.prepareStatement(DBSQL.NOTICE_WIRTE);
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getStartDate());
			pstmt.setString(4, vo.getEndDate());
			
			result = pstmt.executeUpdate();
			
			System.out.println("NoticeDAO.write() :  공지 등록 완료");
			
		} catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();
			
			throw new Exception("공지 등록 DB처리중 오류가 발생하혔습니다.");
			
		} finally {
			
			DBInfo.close(con, pstmt);
			
		}
		
		
		return result;
		
	}
}
