package com.webjjang.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBInfo {

	// 1.드라이버 확인 : 정보 입력
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	
	private static boolean driverCheck = false;
	
	// 1-1 DB에 대한 접속정보
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	
	private static final String ID = "java00";
	
	private static final String PW = "java00";
	
	// 만약 class를 찾으면 바로 실행이 되어야 하는것.
	static {
		
		System.out.println("DBInfo.static{}---------------------------------------------");
		
		// 여기서 Driver확인을 한번만 하도록 한다.
		try {
			
			Class.forName(DRIVER);
			
			driverCheck = true;
			
			System.out.println("DBInfo.static{} : 드라이버 확인");
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			
			
		}
		
	}
	
	// 2.Connection받기 method : 정보 필요
	public static Connection getConnection() throws SQLException {
		
		if(!driverCheck) 
			throw new SQLException("드라이버가 존재하지 않습니다.");		
		
		return DriverManager.getConnection(URL, ID, PW);
		
	}
	
	// 3.Close method - con, pstmt : Insert, Update, Delete -> result type = 1
	public static void close(Connection con, PreparedStatement pstmt) throws SQLException {
		
		if(con != null) 
			con.close();
		
		if(pstmt != null)
			pstmt.close();
		
	}
	
	// 3.Close method -
	public static void close(Connection con, PreparedStatement pstmt, ResultSet rs) throws SQLException {
		
		close(con, pstmt);
		
		if(rs != null)
			rs.close();
		
	}
	
}
