package com.webjjang.message.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.webjjang.message.vo.MessageVO;
import com.webjjang.util.PageObject;
import com.webjjang.util.db.DBInfo;
import com.webjjang.util.db.DBSQL;

public class MessageDAO {

	Connection con = null;
	
	PreparedStatement pstmt = null;
	
	ResultSet rs = null;
	
	public List<MessageVO> list(PageObject pageObject) throws Exception {
		
		List<MessageVO> list = null;
		
		try {
			
			con = DBInfo.getConnection();
			
			pstmt = con.prepareStatement(DBSQL.MESSAGE_LIST);
			
			pstmt.setString(1, pageObject.getAccepter());
			pstmt.setString(2, pageObject.getAccepter());
			pstmt.setLong(3, pageObject.getStartRow());
			pstmt.setLong(4, pageObject.getEndRow());
			
			rs = pstmt.executeQuery();
			
			if (rs != null) {
				
				while(rs.next()) {
					
					if(list == null) {
						
						list = new ArrayList<MessageVO>();
						
					}
					
					MessageVO vo = new MessageVO();
					
					vo.setNo(rs.getLong("no"));
					vo.setSender(rs.getString("sender"));
					vo.setSendDate(rs.getString("sendDate"));
					vo.setAccepter(rs.getString("accepter"));
					vo.setAcceptDate(rs.getString("acceptDate"));
					
					list.add(vo);
					
				}
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();
			
			throw new Exception("메시지 리스트를 불러오는 중 DB에 오류가 발생하였습니다.");
			
		} finally {
			
			DBInfo.close(con, pstmt, rs);
			
		}
		
		
		return list;
		
	}	// end of list()
	
	// 전체 데이터 개수 구하기
	public long getTotalRow() throws Exception {
		
		long result = 0;
		
		try {
			
			con = DBInfo.getConnection();
			
			pstmt = con.prepareStatement(DBSQL.MESSAGE_GET_TOTLAROW);
			
			rs = pstmt.executeQuery();
			
			if (rs != null && rs.next()) {
				
				result = rs.getLong(1);
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();
			
			throw new Exception("전체 데이터를 가져오는 중 DB오류가 발생하였습니다.");
			
		} finally {
			
			DBInfo.close(con, pstmt, rs);
			
		}
		
		return result;
		
	}
	
	// 메시지 보내기
	public int write(MessageVO vo) throws Exception {
			
			int result = 0;
			
			try {
				
				con = DBInfo.getConnection();
				
				System.out.println(DBSQL.MESSAGE_WRITE);
				pstmt = con.prepareStatement(DBSQL.MESSAGE_WRITE);
				
				pstmt.setString(1, vo.getSender());
				pstmt.setString(2, vo.getContent());
				pstmt.setString(3, vo.getAccepter());
				
				result = pstmt.executeUpdate();

				System.out.println("MessageDAO.write() : 메시지 전송 완료");
				
			} catch (Exception e) {
				// TODO: handle exception
				
				e.printStackTrace();
				
				throw new Exception("메시지를 전송하는 중 DB오류가 발생하였습니다.");
				
			} finally {
				
				DBInfo.close(con, pstmt);
				
			}
			
			return result;
			
		}	// end of write
	
	// 메시지 보기의 읽음 처리
	public int viewUpdateRead(MessageVO vo) throws Exception {
			
		int result = 0;
		
		try {
			
			con = DBInfo.getConnection();
			
			System.out.println(DBSQL.MESSAGE_VIEW_READ);
			pstmt = con.prepareStatement(DBSQL.MESSAGE_VIEW_READ);
			
			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2, vo.getAccepter());
			
			result = pstmt.executeUpdate();
			
			System.out.println("MessageDAO.viewUpdateRead() : 메시지 전송 완료");
			
		} catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();
			
			throw new Exception("메시지 읽음 표시 중 DB오류가 발생하였습니다.");
			
		} finally {
			
			DBInfo.close(con, pstmt);
			
		}
		
		return result;
		
	}	// end of viewUpdateRead
	
	// 메시지 읽기
	public MessageVO view(long no) throws Exception {
		
		MessageVO vo = null;
		
		try {
			
			con = DBInfo.getConnection();
			
			System.out.println(DBSQL.MESSAGE_VIEW);
			pstmt = con.prepareStatement(DBSQL.MESSAGE_VIEW);
			
			pstmt.setLong(1, no);
			
			rs = pstmt.executeQuery();
			
			// 6. 데이터를 한개만 가져오므로 반복문이 필요 없어서 조건 2개를 사용한다.
			if (rs != null && rs.next()) {
				
				vo = new MessageVO();	// 위에서 선언한 VO 객체를 재활용 한다.
				
				vo.setNo(rs.getLong("no"));
				vo.setContent(rs.getString("content"));
				vo.setSender(rs.getString("sender"));
				vo.setSendDate(rs.getString("sendDate"));
				vo.setAccepter(rs.getString("accepter"));
				vo.setAcceptDate(rs.getString("acceptDate"));
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();
			
			throw new Exception("메시지를 불러오는 중 DB에 오류가 발생하였습니다.");
			
		} finally {
			
			DBInfo.close(con, pstmt, rs);
			
		}
		
		return vo;
		
	}	// end of view()
	
	public int delete(long no) throws Exception {
		
		int result = 0;
		
		try {
			
			con = DBInfo.getConnection();
			
			pstmt = con.prepareStatement(DBSQL.MESSAGE_DELETE);
			
			pstmt.setLong(1, no);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();
			
			throw new Exception("삭제 처리 중 DB에 오류가 발생하였습니다.");
			
		} finally {
			
			DBInfo.close(con, pstmt);
			
		}
		
		return result;
		
	}
	
}
