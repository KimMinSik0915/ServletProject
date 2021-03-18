package com.webjjang.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.webjjang.member.vo.LoginVO;
import com.webjjang.member.vo.MemberVO;
import com.webjjang.util.db.DBInfo;
import com.webjjang.util.db.DBSQL;

public class MemberDAO {

	// 필요햔 객체 선언
	Connection con = null;
	
	PreparedStatement pstmt = null;
	
	ResultSet rs = null;
	
	// 로그인 처리를 위한 method 만들기
	public LoginVO login(LoginVO vo) throws Exception {
		
		LoginVO loginVO = null;
		
		try {
			
			con = DBInfo.getConnection();	// 드라이버 확인 및 연결객체
			
			pstmt = con.prepareStatement(DBSQL.MEMBER_LOGIN);	// SQL처리 및 데이터 세팅
			
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			
			rs = pstmt.executeQuery();	// 실행
			
			if(rs != null && rs.next()) {		// 표시 및 데이터 담기
				
				loginVO = new LoginVO();
				
				loginVO.setId(rs.getString("id"));
				loginVO.setName(rs.getString("name"));
				loginVO.setGradeNo(rs.getInt("gradeNo"));
				loginVO.setGradeName(rs.getString("gradeName"));
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			throw new Exception("로그인 DB처리중 오류가 발생하였습니다.");
			
		} finally {
			
			DBInfo.close(con, pstmt, rs);
			
		}
		
		return loginVO;
		
	}
	
	// ================================================ 회원 리스트 ===========================================================================
	public List<MemberVO> list() throws Exception {
		
		List<MemberVO> list = null;
		
		try {
			
			con = DBInfo.getConnection();
			
			pstmt = con.prepareStatement(DBSQL.MEMBER1_LIST);
			
			pstmt.setLong(1, 1);
			pstmt.setLong(2, 10);
			
			
			rs = pstmt.executeQuery();
			
			if(rs != null) {
				
				while(rs.next()) {
					
					if(list == null) {
						
						list = new ArrayList<>();
						
					}	// if(list == null)
					
					MemberVO vo = new MemberVO();
					
					vo.setId(rs.getString("id"));
					vo.setName(rs.getNString("name"));
					vo.setGender(rs.getString("gender"));
					vo.setBirth(rs.getString("birth"));
					vo.setTel(rs.getString("tel"));
					vo.setGradeNo(rs.getInt("gradeNo"));
					vo.setGradeName(rs.getString("gradeName"));
					vo.setStatus(rs.getString("status"));
					
					list.add(vo);
					
				}	// while(rs.next())
				
			}	// if(rs != null)
			
			return list;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			throw new Exception("회원관리 DB를 가져오는 중 오류가 발생하였습니다.");

		} finally {
			
			DBInfo.close(con, pstmt, rs);
			
		}
		
	}
	
	// 회원 등급 수정 =============================================================
	public int gradeModify(MemberVO vo) throws Exception {
		
		System.out.println("MemberDAO.gradeModify().vo" + vo);
		
		int result = 0;
		
		try {
			
			con = DBInfo.getConnection();	// 드라이버 확인 및 연결객체
			
			System.out.println("실행쿼리 확인 : " + DBSQL.MEMBER_GRADE_MODIFY);
			
			pstmt = con.prepareStatement(DBSQL.MEMBER_GRADE_MODIFY);	// SQL처리 및 데이터 세팅
			
			pstmt.setInt(1, vo.getGradeNo());	// SQL 쿼리에 포함되어 있는 ?의 의미에 맞는 data를 순서대로 세팅
			pstmt.setString(2, vo.getId());
			
			// 5. 실행
			// - insert, update, delete 쿼리 실행 - pstmt.executeUpdate();
			// - select : resultSet = pstmt.executeQuery();
			result = pstmt.executeUpdate();
			
			// result = 1 : 수정 완료 || result = 0 : 수정이 되지 않았다.(조건에 맞는 데이터가 없다)
			System.out.println("MemberDAO.gradeModify().result : " + result);
			
			if(result == 1) {		// 표시 및 데이터 담기
				
				System.out.println("MemberDAO.gradeModify() - 회원등급 수정 완료");
				
			} else {
				
				throw new Exception("조건에 맞는 데이터(id)가 존재하지 않습니다.");
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			throw new Exception("회원 등급 변경 DB처리중 오류가 발생하였습니다.");
			
		} finally {
			
			DBInfo.close(con, pstmt);
			
		}
		
		System.out.println("MemberDAO.gradeModify().result : " + result);
		
		return result;
		
	}
	
	// 내 정보관리 =========================================================================================
	public MemberVO view(String id) throws Exception {	// 글 보기에 필요한 정보 = vo

		System.out.println("MemberVO.view() [id]" + id);
		
		MemberVO vo = null;		// 결과를 저장해서 넘겨 주어야 할 변수, 데이터가 없어서 초기값 null 세팅 || 숫자면 0을 세팅
		
		try {
			
			con = DBInfo.getConnection();	// 1. 드라이버 확인 및 연결객체 만들기
			
			pstmt = con.prepareStatement(DBSQL.MEMBER_VIEW);		// SQL 쿼리 실행 및 데이터 세팅
			
			System.out.println("실행쿼리 확인 : " + DBSQL.MEMBER_VIEW);
			
			pstmt.setString(1, id);		// 1번 ?에 해당하는 데이터 세팅
			
			rs = pstmt.executeQuery();		// 실행
			
			if (rs != null && rs.next()) {
				
				vo = new MemberVO();		// 위의 method 바로 아래 return 타입과 같은 vo를 생성을 통해 data를 담는다.
				
				vo.setId(rs.getString("id"));
				vo.setName(rs.getString("name"));
				vo.setGender(rs.getString("gender"));
				vo.setBirth(rs.getString("birth"));
				vo.setTel(rs.getString("tel"));
				vo.setEmail(rs.getString("email"));
				vo.setRegDate(rs.getString("regDate"));
				vo.setGradeNo(rs.getInt("gradeNo"));
				vo.setGradeName(rs.getString("gradeName"));
				vo.setStatus(rs.getString("status"));
				
			} else {
				
				throw new Exception("조건에 맞는 data(id)가 존재하지 않습니다.");
				
			}
			
			System.out.println("MemberDAO.view() [vo] : " + vo);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
			throw new Exception("내정보보기 DB처리중 오류가 발생하였습니다.");
			
		} finally {
			
			DBInfo.close(con, pstmt, rs);
			
		}
		
		System.out.println("MemberDAO.view() [vo] : " + vo);
		
		return vo;
		
	}

	public int register(MemberVO vo) throws Exception {
		
		int result = 0;
		
		try {
			
			con = DBInfo.getConnection();
			
			pstmt = con.prepareStatement(DBSQL.MEMBER_REGISTER);
			
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getGender());
			pstmt.setString(5, vo.getBirth());
			pstmt.setString(6, vo.getEmail());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();
			
			throw new Exception("회원 가입 중 DB에 오류가 발생하였습니다.");
			
		} finally {
			
			DBInfo.close(con, pstmt);
			
		}
		
		return result;
		
		
	}
	
	
	//============================================================================================================================================================================================================================
	public String checkId(String id) throws Exception {
		
		String result = null;
		
		try {
			
			con = DBInfo.getConnection();
			
			pstmt = con.prepareStatement(DBSQL.MEMBER_CHECKID);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs != null && rs.next()) {
				
				result = rs.getString("id");
				
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();
			
			throw new Exception("아이디 중복체크 중 DB오류가 발생하였습니다.");
			
		} finally {
			
			DBInfo.close(con, pstmt, rs);
			
		}
		
		System.out.println("MemberDAO.checkId() : " + result);
		
		return result;
		
	}
	
}
