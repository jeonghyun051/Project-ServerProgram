package com.cos.blog2.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.cos.blog2.domain.User;
import com.cos.blog2.domain.LoginReqDto;
import com.cos.blog2.config.DB;
import com.cos.blog2.domain.JoinReqDto;

public class UserDao {

public User findByUsernameAndPassword(LoginReqDto dto) {
		
		String sql = "SELECT id, username, address, userRole FROM user2 WHERE username = ? AND password = ?";
		Connection conn = DB.getInstance();
		PreparedStatement pstmt = null;
		ResultSet rs  = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUsername());
			pstmt.setString(2, dto.getPassword());
			rs =  pstmt.executeQuery();

			if(rs.next()) {
				User user = User.builder()
						.id(rs.getInt("id"))
						.username(rs.getString("username"))
						.address(rs.getString("address"))		
						.userRole(rs.getString("userRole"))
						.build();
				return user;	
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 무조건 실행
			DB.close(conn, pstmt, rs);
		}
		
		return null;
				
	}
	
	
	public List<User> findAll(int page) {

		// SELECT 해서 Board 객체를 컬렉션에 담아서 리턴
		String sql = "SELECT * FROM user2 ORDER BY id DESC LIMIT ?,20"; // 0,4 4,4 8,4
		Connection conn = DB.getInstance();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, page * 4); // 0 -> 0, 1 ->4, 2->8
			rs = pstmt.executeQuery();
			List<User> list = new ArrayList<>();

			while (rs.next()) {

				User user = User.builder().id(rs.getInt("id")).username(rs.getString("username"))
						.name(rs.getString("name")).password(rs.getString("password")).age(rs.getInt("age"))
						.address(rs.getString("address")).residentnumber(rs.getInt("residentnumber"))
						.userRole(rs.getString("userRole")).createDate(rs.getTimestamp("createDate")).build();

				list.add(user);
				
			}
		
			return list;

		} catch (Exception e) {
			// TODO: handle exception
		} 
//		finally {
//			DB.close(conn, pstmt, rs);
//
//		}
		return null;
	}

	public int save(JoinReqDto dto) { // 회원가입
		String sql = "INSERT INTO user2(username, password, name, age, residentnumber, address, userRole, createDate) VALUES(?,?,?,?,?,?,?, now())";
		Connection conn = DB.getInstance();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUsername());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getName());
			pstmt.setInt(4, dto.getAge());
			pstmt.setInt(5, dto.getResidentnumber());
			pstmt.setString(6, dto.getAddress());
			pstmt.setString(7, dto.getRole());

			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 무조건 실행
			DB.close(conn, pstmt);
		}
		return -1;
	}

	public int findByUsername(String username) {
		String sql = "SELECT * FROM user2 WHERE username = ?";
		Connection conn = DB.getInstance();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				return 1; // 있어
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 무조건 실행
			DB.close(conn, pstmt, rs);
		}
		return -1; // 없어
	}
	
	public int deleteByUsername(String username) {
		String sql = "delete from user2 where username = ?";
		Connection conn = DB.getInstance();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			int result = pstmt.executeUpdate();

			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} 
//		finally { // 무조건 실행
//			DB.close(conn, pstmt, rs);
//		}
//		
		return -1;
	}
}
