package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import db.DBClose;
import db.DBConnection;
import dto.calendarDto;

public class ReservationDao implements ReservationDaoImpl {
	
	public void reservationInsert(calendarDto caldto) {
		int count;
		String date = caldto.getBookdate();
		String timeCoice = caldto.getBooktime();
		String placeChoice = caldto.getBookplace();
		String name = caldto.getName();
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		boolean flag = false;
		String sql = " SELECT BOOKDATE, BOOKTIME, BOOKPLACE "
	            + " FROM BOOKINGBOARD "
	            + " WHERE BOOKDATE LIKE '%" + date + "%'  "
	            + " AND  BOOKTIME LIKE '%" + timeCoice + "%' "
	            + " AND  BOOKPLACE LIKE '%" + placeChoice + "%' ";
	      
	      System.out.println("sql : " + sql);
	      try {
	         conn = DBConnection.getConnection();
	         System.out.println("2/6 getBbsList Success");
	         
	         psmt = conn.prepareStatement(sql);
	         System.out.println("3/6 getBbsList Success");
//	         psmt.setString(1, title);   
//	         psmt.setString(2, content );   
	         rs = psmt.executeQuery();
	         System.out.println("4/6 getBbsList Success");
	         
	         while(rs.next()){
	            flag = true;
	         }      
	         System.out.println("5/6 getBbsList Success");
	         
	      } catch (SQLException e) {
	         System.out.println("getBbsList fail");
	      } finally{
	         DBClose.close(psmt, conn, rs);
	         System.out.println("6/6 getBbsList Success");
	      }
		
	      if(flag) {
	    	  JOptionPane.showMessageDialog(null, "선예약이 존재합니다.");
	      }else {
	    	  sql = " INSERT INTO BOOKINGBOARD(BOOKDATE, BOOKTIME, BOOKPLACE, NAME) "
	  				+ " VALUES(?, ?, ?, ?) ";
	    	  
	    	  try {
	      	   	conn = DBConnection.getConnection();
	  			System.out.println("2/6 writeBBS Success");
	  			
	  			psmt = conn.prepareStatement(sql);
	  			System.out.println("3/6 writeBBS Success");
	  	
	  			psmt.setString(1, date);
	  			psmt.setString(2, timeCoice);
	  			psmt.setString(3, placeChoice);
	  			psmt.setString(4, name);

	  			count = psmt.executeUpdate();
	  			System.out.println("4/6 writeBBS Success");

	  			if(count > 0) {
	  				JOptionPane.showMessageDialog(null, "예약완료");
	  			}else {
	  				JOptionPane.showMessageDialog(null, "예약불가");
	  			}
	  			
	         } catch (Exception e) {
//	            bottomInfo.setText(SaveButMsg3);
	         }finally{
	  			DBClose.close(psmt, conn, rs);			
	         }
	      }
	    
		
	}

	@Override
	public List<calendarDto> getRevList(calendarDto caldto) {
		String date = caldto.getBookdate();
		List<calendarDto> list = new ArrayList<calendarDto>();
		Connection conn = null;
 		PreparedStatement psmt = null;
 		ResultSet rs = null;
 		boolean findId = false;
 		String sql = " SELECT BOOKDATE, BOOKTIME, BOOKPLACE, NAME"
 				+ " FROM BOOKINGBOARD "
 				+ " WHERE BOOKDATE = ? "
 				+ " ORDER BY BOOKPLACE, BOOKTIME ";
 	   
 		try{
 			conn = DBConnection.getConnection();
 			System.out.println("2/6 getBbsList Success");
 			
 			psmt = conn.prepareStatement(sql);
 			System.out.println("3/6 getBbsList Success");
 			//System.out.println(calYear+((calMonth+1)<10?"0":"")+(calMonth+1)+(calDayOfMon<10?"0":"")+calDayOfMon + "!!!!");
 			psmt.setString(1, date);
 			
 			rs = psmt.executeQuery();
 			System.out.println("4/6 getBbsList Success");
 			
 			while(rs.next()){
 				findId = true;
 				int i = 1;
 				
 				calendarDto dto = new calendarDto(rs.getString(i++), 	// SEQ
 										rs.getString(i++), 	// VOTECONTENT
 										rs.getString(i++), 	// VOTESTART
 										rs.getString(i++) 	// VOTEEND
 										);	// ABSTAIN
 				list.add(dto);
 			}
 			
 		}catch (Exception e1){
          e1.printStackTrace();
 		}finally{
 			DBClose.close(psmt, conn, rs);
 			System.out.println("6/6 getBbsList Success");
 		}
		return list;
	}

}
