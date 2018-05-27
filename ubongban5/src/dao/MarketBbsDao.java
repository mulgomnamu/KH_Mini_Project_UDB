package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;
import dto.MarketDto;

public class MarketBbsDao implements MarketBbsDaoImpl {

	@Override
	public List<MarketDto> getBbsList() {
		List<MarketDto> list = new ArrayList<MarketDto>();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		
		String sql = "SELECT SEQ, TITLE, CONTENT, "
				+ "	NAME, WDATE, READCOUNT, SOLD, DEL, IMAGESRC, CATEGORY, PRICE "
				+ " FROM MARKETBBS "
				+ " ORDER BY WDATE DESC ";
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("2/6 getBbsList Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("3/6 getBbsList Success");
			
			rs = psmt.executeQuery();
			System.out.println("4/6 getBbsList Success");
			
			while(rs.next()){
				int i = 1;
				MarketDto dto = new MarketDto(rs.getInt(i++), 	// SEQ
										rs.getString(i++), 	// TITLE
										rs.getString(i++), 	// CONTENT
										rs.getString(i++), 	// NAME
										rs.getString(i++), 	// WDATE
										rs.getInt(i++),		// READCOUNT
										rs.getInt(i++),		// SOLD
										rs.getInt(i++), 	// DEL
										rs.getString(i++), 	// IMAGE
										rs.getString(i++),	// CATEGOTY
										rs.getInt(i++));	// PRICE
				list.add(dto);
			}		
			System.out.println("5/6 getBbsList Success");
			
		} catch (SQLException e) {
			System.out.println("getBbsList fail");
		} finally{
			DBClose.close(psmt, conn, rs);
			System.out.println("6/6 getBbsList Success");
		}
		
		return list;
	}

	@Override
	public List<MarketDto> getFindList(String fStr) {
		List<MarketDto> list = new ArrayList<MarketDto>();
		
		String sql = " SELECT SEQ, ID, TITLE "
				+ " FROM MARKETBBS "
				+ " WHERE TITLE LIKE ?"
				+ " ORDER BY WDATE DESC ";
		
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("2/6 getTitleFindList Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("3/6 getTitleFindList Success");
			
			psmt.setString(1, "%" + fStr + "%");
			
			rs = psmt.executeQuery();
			System.out.println("4/6 getTitleFindList Success");
			
			while(rs.next()){
				int i = 1;
				
//				MarketDto dto = new MarketDto(rs.getInt(i++), 	// SEQ
//										rs.getString(i++), 	// ID
//										rs.getString(i++), 	// TITLE
//										null, 	// CONTENT
//										null, 	// WDATE
//										0, 	// DEL
//										0);	// READCOUNT
//				list.add(dto);
			}		
			System.out.println("5/6 getTitleFindList Success");
			
		} catch (SQLException e) {
			System.out.println("getTitleFindList fail");
			e.printStackTrace();
		} finally{
			DBClose.close(psmt, conn, rs);	
			System.out.println("6/6 getTitleFindList Success");
		}
		
		return list;
	}

	@Override
	public boolean writeBbs(MarketDto dto) {
		int count = 0;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = "INSERT INTO MARKETBBS(SEQ, TITLE, CONTENT, NAME, WDATE, READCOUNT, SOLD, DEL, IMAGESRC, CATEGORY, PRICE) "
				+ " VALUES(SEQ_MARKETBBS.NEXTVAL, ?, ?, ?, SYSDATE, 0, 0, 0, ?, ?, ?) ";
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("2/6 writeBBS Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("3/6 writeBBS Success");
			
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContents());
			psmt.setString(3, dto.getName());
			psmt.setString(4, dto.getImagesrc()); // setBinaryStream();  //<-- Binary 형태로 DB 에 저장
			psmt.setString(5, dto.getCategory());
			psmt.setInt(6, dto.getPrice());
			
			count = psmt.executeUpdate();
			System.out.println("4/6 writeBBS Success");
			
		} catch (SQLException e) {	
			e.printStackTrace();
			System.out.println("writeBBS fail");
		} finally{
			DBClose.close(psmt, conn, rs);			
		}
		
		return count>0?true:false;
	}

	@Override
	public MarketDto getBBS(int seq) {
		MarketDto dto = null;

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		String sql = " SELECT TITLE, CONTENT, NAME, WDATE, READCOUNT, SOLD, IMAGESRC, CATEGORY, PRICE "
				+ " FROM MARKETBBS "
				+ " WHERE SEQ=? ";

		try {		
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);	

			psmt.setInt(1, seq);			

			rs = psmt.executeQuery();

			while(rs.next()){	
				String title = rs.getString(1);
				String contents = rs.getString(2);
				String name = rs.getString(3);
				String wdate = rs.getString(4);			
				int readcount = rs.getInt(5);			
				int sold = rs.getInt(6);
				String imagesrc = rs.getString(7);
				String category = rs.getString(8);				
				int price = rs.getInt(9);	

				dto = new MarketDto(seq, title, contents, name, wdate, readcount, sold, imagesrc, category, price);	

			}
		} catch (SQLException e) {	
			e.printStackTrace();
		} finally{
			DBClose.close(psmt, conn, rs);	
		}

		return dto;
	}

	@Override
	public void readCount(int seq) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean bbsDelete(int seq) {
		MarketDto dto = null;
		String sql=" UPDATE MARKETBBS SET  "
				+" DEL=1 "
				+ " WHERE SEQ=? ";

		int count = 0;
		Connection conn=null;
		PreparedStatement psmt=null;

		try {
			conn = DBConnection.getConnection();			
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, seq);			
			count = psmt.executeUpdate();

		} catch (SQLException e) {			
			e.printStackTrace();
		} finally{
			DBClose.close(psmt, conn, null);			
		}
		return count>0?true:false;
	}

	@Override
	public boolean bbsUpdate(MarketDto dto) {
		String sql = " UPDATE MARKETBBS SET "
				+ " TITLE=?, CONTENT=?, SOLD=?, IMAGESRC=?, CATEGORY=?, PRICE=?"
				+ " WHERE SEQ=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();	
			System.out.println("2/6 S updateBbs");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContents());
			psmt.setInt(3, dto.getSold());
			psmt.setString(4, dto.getImagesrc());
			psmt.setString(5, dto.getCategory());
			psmt.setInt(6, dto.getPrice());
			psmt.setInt(7, dto.getSeq());
			
			System.out.println("3/6 S updateBbs");
			
			count = psmt.executeUpdate();
			System.out.println("4/6 S updateBbs");
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally{
			DBClose.close(psmt, conn, null);	
			System.out.println("5/6 S updateBbs");
		}		
		
		return count>0?true:false;
	}
	
	@Override
	public void bbsSoldUpdate(MarketDto dto) {
		String sql = " UPDATE MARKETBBS SET "
				+ " SOLD=?"
				+ " WHERE SEQ=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();	
			System.out.println("2/6 S updateBbs");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, dto.getSold());
			psmt.setInt(2, dto.getSeq());
			
			System.out.println("3/6 S updateBbs");
			
			count = psmt.executeUpdate();
			System.out.println("4/6 S updateBbs");
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally{
			DBClose.close(psmt, conn, null);	
			System.out.println("5/6 S updateBbs");
		}		
	}

	@Override
	public int getTotalList(Object keyWord, String selectWord, int sel) {
		List<MarketDto> list = new ArrayList<MarketDto>();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = null;
		
		int totalRecord = 0;
		if (keyWord.equals("제품명") && sel == 1) {
			sql = " SELECT COUNT(*) FROM MARKETBBS WHERE TITLE LIKE ? ";
		} else if (keyWord.equals("카테고리")) {
			sql = " SELECT COUNT(*) FROM MARKETBBS WHERE CATEGORY LIKE ? ";
		} else if (keyWord.equals("판매자")) {
			sql = " SELECT COUNT(*) FROM MARKETBBS WHERE NAME LIKE ? ";
		} else {
			sql = " SELECT COUNT(*) FROM MARKETBBS ";
			
			try {
				conn = DBConnection.getConnection();
				System.out.println("2/6 getBbsList Success");
				
				psmt = conn.prepareStatement(sql);
				System.out.println("3/6 getBbsList Success");
				
				rs = psmt.executeQuery();
				System.out.println("4/6 getBbsList Success");
				
				while(rs.next()){
					totalRecord = rs.getInt(1);
				}		
				System.out.println("5/6 getBbsList Success");
				
			} catch (SQLException e) {
				System.out.println("getBbsList fail");
			} finally{
				DBClose.close(psmt, conn, rs);
				System.out.println("6/6 getBbsList Success");
			}
			
			return totalRecord;
		}
		
		String data = "%" + selectWord + "%";
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("2/6 getBbsList Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("3/6 getBbsList Success");
			
			rs = psmt.executeQuery();
			System.out.println("4/6 getBbsList Success");
			
			psmt.setString(1, data);
			
			while(rs.next()){
				totalRecord = rs.getInt(1);
			}		
			System.out.println("5/6 getBbsList Success");
			
		} catch (SQLException e) {
			System.out.println("getBbsList fail");
		} finally{
			DBClose.close(psmt, conn, rs);
			System.out.println("6/6 getBbsList Success");
		}
		
		return totalRecord;
	}
	
	@Override
	public List<MarketDto> selectBbsList(String title, String content) {
		List<MarketDto> list = new ArrayList<MarketDto>();
		//System.out.println("!!!!!!!!!!!!!!!!");
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		boolean flag = false;
		String sql = " SELECT SEQ, TITLE, CONTENT, "
				+ "	NAME, WDATE, READCOUNT, SOLD, DEL, IMAGESRC, CATEGORY, PRICE "
				+ " FROM MARKETBBS "
				+ " WHERE " +title + " LIKE '%" + content + "%' ";
		
		System.out.println("sql : " + sql);
		try {
			conn = DBConnection.getConnection();
			System.out.println("2/6 getBbsList Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("3/6 getBbsList Success");
//			psmt.setString(1, title);	
//			psmt.setString(2, content );	
			rs = psmt.executeQuery();
			System.out.println("4/6 getBbsList Success");
			
			while(rs.next()){
				flag = true;
				int seq = rs.getInt("seq");
				String title1 = rs.getString("title");
				String content1 = rs.getString("content");
				String name = rs.getString("name");
				String wdate = rs.getString("wdate");
				int readcount = rs.getInt("readcount");
				int sold = rs.getInt("sold");
				int del = rs.getInt("del");
				String imagesrc = rs.getString("imagesrc");
				String category = rs.getString("category");
				int price = rs.getInt("price");
				MarketDto dto = new MarketDto(seq, title1, content1, name, wdate, readcount, sold, del, imagesrc, category, price );
				list.add(dto);
			}		
			
			if(flag) System.out.println("들어옴");
			else
				System.out.println("안들어옴");
			
			System.out.println("5/6 getBbsList Success");
			
		} catch (SQLException e) {
			System.out.println("getBbsList fail");
		} finally{
			DBClose.close(psmt, conn, rs);
			System.out.println("6/6 getBbsList Success");
		}
		for (int i = 0; i < list.size(); i++) {
			
			System.out.println("title : " + list.get(i).getTitle());
			System.out.println("title : " + list.get(i).getName());
			System.out.println("title : " + list.get(i).getCategory());
		}
		return list;
	}

}
