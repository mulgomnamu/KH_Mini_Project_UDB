package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;
import dto.NoticeBoardDto;
import dto.VoteDto;

public class NoticeBoardDao implements NoticeBoardDaoImpl {

   @Override
   public List<NoticeBoardDto> getNoticeBoard() {
      
      List<NoticeBoardDto> list = new ArrayList<NoticeBoardDto>();
      
      Connection conn = null;
      PreparedStatement psmt = null;
      ResultSet rs = null;
      
      String sql = " SELECT SEQ, NAME, TITLE, CONTENT, WDATE "
            + " FROM NOTICEBOARD WHERE ROWNUM <= 5"
            + " ORDER BY WDATE DESC ";
      
      try {
         conn = DBConnection.getConnection();
         System.out.println("2/6 getBbsList Success");
         
         psmt = conn.prepareStatement(sql);
         System.out.println("3/6 getBbsList Success");
         
         rs = psmt.executeQuery();
         System.out.println("4/6 getBbsList Success");
         
         while(rs.next()){

            int seq = rs.getInt("seq");
            String name = rs.getString("name");
            String title = rs.getString("title");
            String content = rs.getString("content");
            String wdate = rs.getString("wdate");
            
            String date_s = wdate;

              // *** note that it's "yyyy-MM-dd hh:mm:ss" not "yyyy-mm-dd hh:mm:ss"  
              SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
              java.util.Date date = null;
            try {
               date = dt.parse(date_s);
            } catch (ParseException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }

              // *** same for the format String below
              SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd hh:mm");
              String formatdate = dt1.format(date);
            
              NoticeBoardDto noticedto = new NoticeBoardDto(seq, name, title, content, formatdate);
            list.add(noticedto);
            
            
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
   
   public NoticeBoardDto noticeBoardDetail(NoticeBoardDto noticedto) {
      NoticeBoardDto noticeBoarddto;
      String sql = " SELECT SEQ, NAME, TITLE, CONTENT, WDATE "
            + " FROM NOTICEBOARD "
            + " WHERE SEQ = ? ";
      
      Connection conn = null;
      PreparedStatement psmt = null;
      ResultSet rs = null;
      int count = 0;
      String plusId = null;
      
      try {
         conn = DBConnection.getConnection();   
         System.out.println("2/6 S updateBbs");
         
         psmt = conn.prepareStatement(sql);
         psmt.setInt(1, noticedto.getSeq());
         
         System.out.println("3/6 S updateBbs");
         
         //count = psmt.executeUpdate();
         rs = psmt.executeQuery();
         System.out.println("4/6 S updateBbs");
         
         while(rs.next()){            
            int seq = rs.getInt("seq");
            String name = rs.getString("name");
            String title = rs.getString("title");
            String content = rs.getString("content");
            String wdate = rs.getString("wdate");
            
            String date_s = wdate;

              // *** note that it's "yyyy-MM-dd hh:mm:ss" not "yyyy-mm-dd hh:mm:ss"  
              SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
              java.util.Date date = null;
            try {
               date = dt.parse(date_s);
            } catch (ParseException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }

              // *** same for the format String below
              SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd hh:mm");
              String formatdate = dt1.format(date);
            
            noticedto = new NoticeBoardDto(seq, name, title, content, formatdate);
         }
         
      } catch (SQLException e) {         
         e.printStackTrace();
      } finally{
         DBClose.close(psmt, conn, rs);   
         System.out.println("5/6 S updateBbs");
      }      
      
      return noticedto;
      
   }

   @Override
   public List<NoticeBoardDto> getNewestBoard() {
      // TODO Auto-generated method stub
      return null;
   }
   
   
//   @Override
//   public List<NoticeBoardDto> getNewestBoard() {
//      
//      List<NoticeBoardDto> list = new ArrayList<NoticeBoardDto>();
//      
//      Connection conn = null;
//      PreparedStatement psmt = null;
//      ResultSet rs = null;
//      
//      String sql = " SELECT SEQ, NAME, TITLE, CONTENT, WDATE "
//            + " FROM NOTICEBOARD "
//            + " ORDER BY WDATE DESC ";
//      
//      try {
//         conn = DBConnection.getConnection();
//         System.out.println("2/6 getBbsList Success");
//         
//         psmt = conn.prepareStatement(sql);
//         System.out.println("3/6 getBbsList Success");
//         
//         rs = psmt.executeQuery();
//         System.out.println("4/6 getBbsList Success");
//         
//         while(rs.next()){
//
//            int i = 1;
//            
//            NoticeBoardDto dto = new NoticeBoardDto(rs.getInt(i++),    // SEQ
//                              rs.getString(i++),    // VOTECONTENT
//                              rs.getString(i++),    // VOTESTART
//                              rs.getString(i++),    // VOTEEND
//                              rs.getString(i++));   // ABSTAIN
//            list.add(dto);
//         }      
//         System.out.println("5/6 getBbsList Success");
//         
//      } catch (SQLException e) {
//         System.out.println("getBbsList fail");
//      } finally{
//         DBClose.close(psmt, conn, rs);
//         System.out.println("6/6 getBbsList Success");
//      }
//      
//      return list;
//   }

   
   
}



