package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBClose;
import db.DBConnection;
import dto.MemberDto;

public class MemberDao implements MemberDaoImpl {

	@Override
	public boolean addMember(MemberDto dto) {		
		String sql = " INSERT INTO MEMBER "
				+ " (ID, PWD, DONG, HO, NAME, AUTH) "
				+ " VALUES(?, ?, ?, ?, ?, 1) ";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		int count = 0;
						
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 addMember Success");
			
			stmt = conn.prepareStatement(sql);
			System.out.println("2/6 addMember Success");
			
			// ? -> setting
			stmt.setString(1, dto.getId());
			stmt.setString(2, dto.getPwd());
			stmt.setString(3, dto.getDong());
			stmt.setString(4, dto.getHo());
			stmt.setString(5, dto.getDong() + "동" + dto.getHo() + "호");
			
			count = stmt.executeUpdate();
			System.out.println("3/6 addMember Success");
			
		} catch (SQLException e) {
			System.out.println("addMember fail");
		} finally {
			DBClose.close(stmt, conn, null);			
		}
		
		return count>0?true:false;
	}

	@Override
	public boolean idCheck(String id) {
		String sql = " SELECT ID FROM MEMBER "
				+ " WHERE ID = '" + id + "'";
		boolean findId = false;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
				
		try {			
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);			
			rs = psmt.executeQuery(sql);
			
			while(rs.next()){			
				findId = true;			
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally{			
			DBClose.close(psmt, conn, rs);			
		}
		
		return findId;
	}
	
	@Override
	public boolean nameCheck(String name) {
		String sql = " SELECT NAME FROM MEMBER "
				+ " WHERE NAME = '" + name + "'";
		boolean findname = false;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
				
		try {			
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);			
			rs = psmt.executeQuery(sql);
			
			while(rs.next()){			
				findname = true;			
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally{			
			DBClose.close(psmt, conn, rs);			
		}
		
		return findname;
	}

	@Override
	public MemberDto login(MemberDto dto) {
		MemberDto mem = null;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT ID, DONG, HO, NAME, AUTH "
				+ " FROM MEMBER "
				+ " WHERE ID=? AND PWD=? ";
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			
			System.out.println("1/6 login Success");
			
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPwd());			
			
			System.out.println("2/6 login Success");
			
			rs = psmt.executeQuery();
			
			while(rs.next()){
				String id = rs.getString(1);
				String dong = rs.getString(2);
				String ho = rs.getString(3);
				String name = rs.getString(4);
				int auth = rs.getInt(5);				
				
				mem = new MemberDto(id, null, name, dong, ho, auth);
			}	
			System.out.println("3/6 login Success");
			
		} catch (SQLException e) {			
			System.out.println("login Fail");
		} finally{
			DBClose.close(psmt, conn, rs);		
		}
		
		return mem;
	}
	
	public MemberDto getName(MemberDto memdto) {
		//System.out.println("idcheck2222");
		String sql = " SELECT NAME FROM MEMBER "
				+ " WHERE ID = '" + memdto.getId() + "'";
		String name;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
				
		try {			
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);			
			rs = psmt.executeQuery(sql);
			
			while(rs.next()){			
				name = rs.getString(1);
				
				memdto = new MemberDto(name);	
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally{			
			DBClose.close(psmt, conn, rs);			
		}
		
		return memdto;
	}
	
	

}
