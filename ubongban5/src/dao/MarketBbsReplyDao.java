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
import dto.MarketDto;
import dto.MarketReplyDto;
import singleton.Singleton;

public class MarketBbsReplyDao implements MarketBbsReplyDaoImpl {

   @Override
   public List<MarketReplyDto> getReplyList(MarketDto m_dto) {

      Singleton s = Singleton.getInstance();

      List<MarketReplyDto> list = new ArrayList<MarketReplyDto>();
      
      Connection conn = null;
      PreparedStatement psmt = null;
      ResultSet rs = null;
      
      String sql = " SELECT SEQ, NAME, CONTENT, WDATE, DEL, BBSSEQ, FAMILY, PARENT, DEPTH, INDENT "
            + "FROM (SELECT ROWNUM R, A.* FROM (SELECT SEQ, NAME, CONTENT, WDATE, DEL, BBSSEQ, FAMILY, PARENT, DEPTH, INDENT "
            + "FROM MARKETREPLY WHERE BBSSEQ = ? START WITH PARENT = 0 "
            + "CONNECT BY PRIOR SEQ = PARENT ORDER SIBLINGS BY SEQ DESC) A) ";
      
      int seq = 0;
      String name = null;
      String content = null;
      String wdate = null;
      int del = 0;
      int bbsseq = 0;
      int family = 0;
      int parent = 0;
      int depth = 0;
      int indent = 0;
      
      try {

         conn = DBConnection.getConnection();
         System.out.println("2/6 getReplyList Success");
         
         psmt = conn.prepareStatement(sql);
         System.out.println("3/6 getReplyList Success");
         
         psmt.setInt(1, m_dto.getSeq());
         
         rs = psmt.executeQuery();
         System.out.println("4/6 getReplyList Success");
         
         while(rs.next()) {
            
            seq = rs.getInt(1);
            name = rs.getString(2);
            content = rs.getString(3);
            wdate = rs.getString(4);
            del = rs.getInt(5);
            bbsseq = rs.getInt(6);
            family = rs.getInt(7);
            parent = rs.getInt(6);
            depth = rs.getInt(9);
            indent = rs.getInt(10);
            if(indent != 0) {
               content = "┗" + content;
            }
            for (int i = 0; i < indent; i++) {
               content = "  " + content;
            }
            
            if(del == 1) {

               content = "이 글은 삭제되었습니다.";
               
            }
            
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

            MarketReplyDto dto = new MarketReplyDto(seq, name, content, formatdate, del, bbsseq, family, parent, depth, indent);
            list.add(dto);
         }
         System.out.println("5/6 getReplyList Success");
      } catch (SQLException e) {
         System.out.println("getReplyList fail");
      }
      finally {
         DBClose.close(psmt, conn, rs);
         System.out.println("6/6 getReplyList Success");
      }
      return list;
   }

   @Override
   public boolean writeReply(MarketReplyDto dto) {

      String sql = " INSERT INTO MARKETREPLY(SEQ, NAME, CONTENT, WDATE, DEL, BBSSEQ, FAMILY, PARENT, DEPTH, INDENT) "
            + "VALUES(SEQ_MARKETREPLY.NEXTVAL, ?, ?, SYSDATE, 0, ?, SEQ_MARKETREPLY.NEXTVAL, 0, 0, 0) ";
      
      Connection conn = null;
      PreparedStatement ptmt = null; 
      ResultSet rs = null;
      
      int count = 0;
      
      try {
         conn = DBConnection.getConnection();
         System.out.println("1/6 writeReply Success");
         
         ptmt = conn.prepareStatement(sql);
         System.out.println("2/6 writeReply Success");
         
         ptmt.setString(1, dto.getName());
         ptmt.setString(2, dto.getContent());
         ptmt.setInt(3, dto.getBbsseq());
         
         count = ptmt.executeUpdate();
         System.out.println("3/6 writeReply Success");
      } catch (SQLException e) {
         System.out.println("writeReply fail");
      }
      finally {
         DBClose.close(ptmt, conn, null);
      }
      
      return count>0?true:false;
   }

   @Override
   public boolean writeReplyReplier(MarketReplyDto dto, int family, int seq, int depth, int indent) {

      String sql = " INSERT INTO MARKETREPLY(SEQ, NAME, CONTENT, WDATE, DEL, BBSSEQ, FAMILY, PARENT, DEPTH, INDENT) "
            + "VALUES(SEQ_MARKETREPLY.NEXTVAL, ?, ?, SYSDATE, 0, ?, ?, ?, ?, ?) ";
      
      Connection conn = null;
      PreparedStatement ptmt = null; 
      ResultSet rs = null;
      
      int count = 0;
      int parent = seq;
      depth = depth + 1;
      indent = indent + 1;
      
      try {
         conn = DBConnection.getConnection();
         System.out.println("1/6 writeReplyReplier Success");
         
         ptmt = conn.prepareStatement(sql);
         System.out.println("2/6 writeReplyReplier Success");
         
         ptmt.setString(1, dto.getName());
         ptmt.setString(2, dto.getContent());
         ptmt.setInt(3, dto.getBbsseq());
         ptmt.setInt(4, family);
         ptmt.setInt(5, parent);
         ptmt.setInt(6, depth);
         ptmt.setInt(7, indent);
         
         count = ptmt.executeUpdate();
         System.out.println("3/6 writeReplyReplier Success");
      } catch (SQLException e) {
         System.out.println("writeReplyReplier fail");
      }
      finally {
         DBClose.close(ptmt, conn, null);
      }
      
      return count>0?true:false;
   }

   @Override
   public boolean deleteReply(int seq) {
      
      String sql = " UPDATE MARKETREPLY SET DEL = ? WHERE SEQ = ? ";
      
      Connection conn = null;
      PreparedStatement ptmt = null; 
      ResultSet rs = null;
      
      int count = 0;
      
      try {
         conn = DBConnection.getConnection();
         System.out.println("1/6 deleteReply Success");
         
         ptmt = conn.prepareStatement(sql);
         System.out.println("2/6 deleteReply Success");
         
         ptmt.setInt(1, 1);
         ptmt.setInt(2, seq);
         
         count = ptmt.executeUpdate();
         
         System.out.println("3/6 deleteReply Success");
      } catch (SQLException e) {
         System.out.println("deleteReply fail");
      }
      finally {
         DBClose.close(ptmt, conn, null);
      }
      return count>0?true:false;
   }

   @Override
   public boolean updateReply(String content, int seq) {
      
   String sql = " UPDATE MARKETREPLY SET CONTENT = ? WHERE SEQ = ? ";
   
   Connection conn = null;
   PreparedStatement ptmt = null; 
   ResultSet rs = null;
   int count = 0;
   
   try {
      conn = DBConnection.getConnection();
      System.out.println("1/6 updateReply Success");
      
      ptmt = conn.prepareStatement(sql);
      System.out.println("2/6 updateReply Success");
      
      ptmt.setString(1, content);
      ptmt.setInt(2, seq);
      
      count = ptmt.executeUpdate();
      System.out.println("3/6 updateReply Success");
   } catch (SQLException e) {
      System.out.println("updateReply fail");
   }
   finally {
      DBClose.close(ptmt, conn, null);
   }
   return count>0?true:false;
   }

}