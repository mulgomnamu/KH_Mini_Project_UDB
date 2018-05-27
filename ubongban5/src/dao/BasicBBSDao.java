package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import db.DBClose;
import db.DBConnection;
import dto.BasicBBSDto;
import dto.ReplyDto;
import singleton.Singleton;

public class BasicBBSDao implements BasicBBSDaoImpl {
   

   @Override
   public List<BasicBBSDto> getBasicBBSList(int curPage, int numPerPage) {
      Singleton s = Singleton.getInstance();

      List<BasicBBSDto> list = new ArrayList<BasicBBSDto>();
      
      Connection conn = null;
      PreparedStatement psmt = null;
      ResultSet rs = null;
      String sql = null;
      
      // 첫 번째 레코드 번호와 마지막 레코드 번호 구하기
      
      // 시작 레코드 계산
      int start = (curPage - 1) * numPerPage + 1;
      // 마지막 레코드 계산
      int end = start + numPerPage - 1;
      
      sql = " SELECT SEQ, TITLE, CONTENT, NAME, WDATE, READCOUNT, DEL, FAMILY, PARENT, DEPTH, INDENT "
            + "FROM (SELECT ROWNUM R, A.* FROM (SELECT SEQ, TITLE, CONTENT, NAME, WDATE, READCOUNT, DEL, FAMILY, PARENT, DEPTH, INDENT "
            + "FROM BASICBBS START WITH PARENT = 0 "
            + "CONNECT BY PRIOR SEQ = PARENT ORDER SIBLINGS BY SEQ DESC) A) "
            + "WHERE R BETWEEN ? AND ? ";
      
      int seq = 0;
      String title = null;
      String content = null;
      String name = null;
      String wdate = null;
      int readcount = 0;
      int del = 0;
      int family = 0;
      int parent = 0;
      int depth = 0;
      int indent = 0;
      
      
        
      try {

         conn = DBConnection.getConnection();
         System.out.println("2/6 getBasicBBSList Success");
         
         psmt = conn.prepareStatement(sql);
         
         psmt.setInt(1, start);
         psmt.setInt(2, end);
         System.out.println("3/6 getBasicBBSList Success");
         
         rs = psmt.executeQuery();
         System.out.println("4/6 getBasicBBSList Success");
         
         while(rs.next()) {
            
            seq = rs.getInt(1);
            title = rs.getString(2);
            content = rs.getString(3);
            name = rs.getString(4);
            wdate = rs.getString(5);
            readcount = rs.getInt(6);
            del = rs.getInt(7);
            family = rs.getInt(8);
            parent = rs.getInt(9);
            depth = rs.getInt(10);
            indent = rs.getInt(11);
            if(indent != 0) {
               title = "┗" + title;
            }
            for (int i = 0; i < indent; i++) {
               title = "  " + title;
            }
            
            if(del == 1) {

               title = "이 글은 삭제되었습니다.";
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

            BasicBBSDto dto = new BasicBBSDto(seq, title, content, name, formatdate, readcount, del, family, parent, depth, indent);
            list.add(dto);
         }
         System.out.println("5/6 getBasicBBSList Success");
      } catch (SQLException e) {
         System.out.println("getBasicBBSList fail");
      }
      finally {
         DBClose.close(psmt, conn, rs);
         System.out.println("6/6 getBasicBBSList Success");
      }
      return list;
      
   }

   
   @Override
   public int getTotalRecord(Object keyword, String select_word, int sel) {
      
      Connection conn = null;
      PreparedStatement psmt = null;
      ResultSet rs = null;
      
      String sql = null;

      // 총 레코드 수 구하기
      int totalRecord = 0;
         sql = " SELECT COUNT(*) FROM BASICBBS ";
      if(keyword.equals("글쓴이") && sel == 1) {
         sql = " SELECT COUNT(*) FROM BASICBBS WHERE NAME LIKE ?";
      }else if(keyword.equals("제목")) {
         sql = " SELECT COUNT(*) FROM BASICBBS WHERE TITLE LIKE ?";
      }else if(keyword.equals("내용")) {
         sql = " SELECT COUNT(*) FROM BASICBBS WHERE CONTENT LIKE ?";
      }
       
      try {

         conn = DBConnection.getConnection();
         System.out.println("2/6 getTotalRecord Success");
         
         psmt = conn.prepareStatement(sql);
         System.out.println("3/6 getTotalRecord Success");
         
         rs = psmt.executeQuery();
         System.out.println("4/6 getTotalRecord Success");
         
         while(rs.next()) {
            totalRecord = rs.getInt(1);
         }
         System.out.println("5/6 getTotalRecord Success");
      } catch (SQLException e) {
         System.out.println("getTotalRecord fail");
      }
      finally {
         DBClose.close(psmt, conn, rs);
         System.out.println("6/6 getTotalRecord Success");
      }
      
      return totalRecord;
   }

   
   @Override
   public boolean writeBasicBBS(BasicBBSDto dto) {

      String sql = " INSERT INTO BASICBBS(SEQ, TITLE, CONTENT, NAME, WDATE, READCOUNT, DEL, FAMILY, PARENT, DEPTH, INDENT) "
            + "VALUES(SEQ_BASICBBS.NEXTVAL, ?, ?, ?, SYSDATE, 0, 0, SEQ_BASICBBS.NEXTVAL, 0, 0, 0) ";
      
      Connection conn = null;
      PreparedStatement ptmt = null; 
      ResultSet rs = null;
      
      int count = 0;
      
      try {
         conn = DBConnection.getConnection();
         System.out.println("1/6 writeBasicBBS Success");
         
         ptmt = conn.prepareStatement(sql);
         System.out.println("2/6 writeBasicBBS Success");
         
         ptmt.setString(1, dto.getTitle());
         ptmt.setString(2, dto.getContent());
         ptmt.setString(3, dto.getName());
         
         count = ptmt.executeUpdate();
         System.out.println("3/6 writeBasicBBS Success");
      } catch (SQLException e) {
         System.out.println("writeBasicBBS fail");
      }
      finally {
         DBClose.close(ptmt, conn, null);
      }
      
      return count>0?true:false;
      
   }
   
   @Override
   public boolean writeBasicBBSReplier(BasicBBSDto dto, int family, int seq, int depth, int indent) {

      
      
      String sql = " INSERT INTO BASICBBS(SEQ, TITLE, CONTENT, NAME, WDATE, READCOUNT, DEL, FAMILY, PARENT, DEPTH, INDENT) "
            + "VALUES(SEQ_BASICBBS.NEXTVAL, ?, ?, ?, SYSDATE, 0, 0, ?, ?, ?, ?) ";
      
      Connection conn = null;
      PreparedStatement ptmt = null; 
      ResultSet rs = null;
      
      int count = 0;
      int parent = seq;
      depth = depth + 1;
      indent = indent + 1;
      
      try {
         conn = DBConnection.getConnection();
         System.out.println("1/6 writeBasicBBSReplier Success");
         
         ptmt = conn.prepareStatement(sql);
         System.out.println("2/6 writeBasicBBSReplier Success");
         
         ptmt.setString(1, dto.getTitle());
         ptmt.setString(2, dto.getContent());
         ptmt.setString(3, dto.getName());
         ptmt.setInt(4, family);
         ptmt.setInt(5, parent);
         ptmt.setInt(6, depth);
         ptmt.setInt(7, indent);
         
         count = ptmt.executeUpdate();
         System.out.println("3/6 writeBasicBBSReplier Success");
      } catch (SQLException e) {
         System.out.println("writeBasicBBSReplier fail");
      }
      finally {
         DBClose.close(ptmt, conn, null);
      }
      
      return count>0?true:false;
      
   }

   @Override
   public BasicBBSDto detailBasicBBS(int BBSseq) {
      BasicBBSDto dto = null;
      
      Connection conn = null;
      PreparedStatement psmt = null;
      ResultSet rs = null;
      
      String sql = " SELECT SEQ, TITLE, CONTENT, NAME, WDATE, READCOUNT FROM BASICBBS WHERE SEQ = ? ";
      
      try {
         conn = DBConnection.getConnection();
         psmt = conn.prepareStatement(sql);
         
         psmt.setInt(1, BBSseq);
         rs = psmt.executeQuery();
         
         while(rs.next()) {
            int seq = rs.getInt(1);
            String title = rs.getString(2);
            String content = rs.getString(3);
            String name = rs.getString(4);
            String wdate = rs.getString(5);
            int readcount = rs.getInt(6);
            
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
            
            dto = new BasicBBSDto(seq, title, content, name, formatdate, readcount);
         }
         
      } catch (SQLException e) {
         e.printStackTrace();
      }
      finally {
         DBClose.close(psmt, conn, rs);
      }
      return dto;
      
   }

   @Override
   public boolean updateBasicBBS(String title, String content, int seq) {
         
      String sql = " UPDATE BASICBBS SET TITLE = ?, CONTENT = ? WHERE SEQ = ? ";
      
      Connection conn = null;
      PreparedStatement ptmt = null; 
      ResultSet rs = null;
      int count = 0;
      
      try {
         conn = DBConnection.getConnection();
         System.out.println("1/6 updateBoard Success");
         
         ptmt = conn.prepareStatement(sql);
         System.out.println("2/6 updateBoard Success");
         
         ptmt.setString(1, title);
         ptmt.setString(2, content);
         ptmt.setInt(3, seq);
         
         count = ptmt.executeUpdate();
         System.out.println("3/6 updateBoard Success");
      } catch (SQLException e) {
         System.out.println("updateBoard fail");
      }
      finally {
         DBClose.close(ptmt, conn, null);
      }
      return count>0?true:false;
   }

   @Override
   public boolean deleteBasicBBS(int seq) {
      
      String sql = " UPDATE BASICBBS SET DEL = ? WHERE SEQ = ? ";
      
      Connection conn = null;
      PreparedStatement ptmt = null; 
      ResultSet rs = null;
      
      int count = 0;
      
      try {
         conn = DBConnection.getConnection();
         System.out.println("1/6 deleteBoard Success");
         
         ptmt = conn.prepareStatement(sql);
         System.out.println("2/6 deleteBoard Success");
         
         ptmt.setInt(1, 1);
         ptmt.setInt(2, seq);
         
         count = ptmt.executeUpdate();
         
         System.out.println("3/6 deleteBoard Success");
      } catch (SQLException e) {
         System.out.println("deleteBoard fail");
      }
      finally {
         DBClose.close(ptmt, conn, null);
      }
      return count>0?true:false;
   }

   @Override
   public void updateBasicBBSReadcount(int seq, int readCount) {
      
      String sql = " UPDATE BASICBBS SET READCOUNT = ? WHERE SEQ = ? ";
      
      Connection conn = null;
      PreparedStatement ptmt = null; 
      ResultSet rs = null;
      
      readCount = readCount + 1;
      
      try {
         conn = DBConnection.getConnection();
         System.out.println("1/6 updateReadcount Success");
         
         ptmt = conn.prepareStatement(sql);
         System.out.println("2/6 updateReadcount Success");
         
         ptmt.setInt(1, readCount);
         ptmt.setInt(2, seq);
         
         ptmt.executeUpdate();
         System.out.println("3/6 updateReadcount Success");
      } catch (SQLException e) {
         System.out.println("updateReadcount fail");
      }
      finally {
         DBClose.close(ptmt, conn, null);
      }
      
   }

   @Override
   public List<BasicBBSDto> selectBasicBBSList(Object keyword, String select_word, int curPage, int numPerPage) {
      
      Singleton s = Singleton.getInstance();
      List<BasicBBSDto> list = new ArrayList<BasicBBSDto>();
      
      String sql = null;
      
      if(select_word.equals("")) {
         list = getBasicBBSList(curPage, numPerPage);
         return list;
      }
      
      // 시작 레코드 계산
            int start = (curPage - 1) * numPerPage + 1;
            // 마지막 레코드 계산
            int end = start + numPerPage - 1;
      
      if(keyword.equals("글쓴이")) {
         sql = " SELECT SEQ, TITLE, CONTENT, NAME, WDATE, READCOUNT, DEL, FAMILY, PARENT, DEPTH, INDENT "
               + "FROM (SELECT ROWNUM R, A.* FROM (SELECT SEQ, TITLE, CONTENT, NAME, WDATE, READCOUNT, DEL, FAMILY, PARENT, DEPTH, INDENT "
               + "FROM BASICBBS WHERE NAME LIKE ? START WITH PARENT = 0 "
               + "CONNECT BY PRIOR SEQ = PARENT ORDER SIBLINGS BY SEQ DESC) A) "
               + "WHERE R BETWEEN ? AND ? ";
      }else if(keyword.equals("제목")) {
         sql = " SELECT SEQ, TITLE, CONTENT, NAME, WDATE, READCOUNT, DEL, FAMILY, PARENT, DEPTH, INDENT "
               + "FROM (SELECT ROWNUM R, A.* FROM (SELECT SEQ, TITLE, CONTENT, NAME, WDATE, READCOUNT, DEL, FAMILY, PARENT, DEPTH, INDENT "
               + "FROM BASICBBS WHERE TITLE LIKE ? START WITH PARENT = 0 "
               + "CONNECT BY PRIOR SEQ = PARENT ORDER SIBLINGS BY SEQ DESC) A) "
               + "WHERE R BETWEEN ? AND ? ";
      }else if(keyword.equals("내용")){
         sql = " SELECT SEQ, TITLE, CONTENT, NAME, WDATE, READCOUNT, DEL, FAMILY, PARENT, DEPTH, INDENT "
               + "FROM (SELECT ROWNUM R, A.* FROM (SELECT SEQ, TITLE, CONTENT, NAME, WDATE, READCOUNT, DEL, FAMILY, PARENT, DEPTH, INDENT "
               + "FROM BASICBBS WHERE CONTENT LIKE ? START WITH PARENT = 0 "
               + "CONNECT BY PRIOR SEQ = PARENT ORDER SIBLINGS BY SEQ DESC) A) "
               + "WHERE R BETWEEN ? AND ? ";
      }
      
      String data = "%" + select_word + "%";
      
      Connection conn = null;
      PreparedStatement ptmt = null; 
      ResultSet rs = null;
      
      int seq = 0;
      String title = null;
      String content = null;
      String name = null;
      String wdate = null;
      int readcount = 0;
      int del = 0;
      int family = 0;
      int parent = 0;
      int depth = 0;
      int indent = 0;
      
      try {
         
         conn = DBConnection.getConnection();
         System.out.println("1/6 selectData Success");
         ptmt = conn.prepareStatement(sql);
         System.out.println("2/6 selectData Success");
         
         ptmt.setString(1, data);
         ptmt.setInt(2, start);
         ptmt.setInt(3, end);
         System.out.println("3/6 selectData Success");
         
         ptmt.executeUpdate();
         System.out.println("4/6 selectData Success");
         
         rs = ptmt.executeQuery(sql);
         System.out.println("5/6 selectData Success");
         
         while(rs.next()) {
            
            seq = rs.getInt(1);
            title = rs.getString(2);
            content = rs.getString(3);
            name = rs.getString(4);
            wdate = rs.getString(5);
            readcount = rs.getInt(6);
            del = rs.getInt(7);
            family = rs.getInt(8);
            parent = rs.getInt(9);
            depth = rs.getInt(10);
            indent = rs.getInt(11);
            if(indent != 0) {
               title = "┗" + title;
            }
            for (int i = 0; i < indent; i++) {
               title = "  " + title;
            }
            
            if(del == 1) {

               title = "이 글은 삭제되었습니다.";
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

            BasicBBSDto dto = new BasicBBSDto(seq, title, content, name, formatdate, readcount, del, family, parent, depth, indent);
            list.add(dto);
         }
         System.out.println("6/6 selectData Success");
      } catch (SQLException e) {
         System.out.println("selectData fail");
      }
      finally {
         DBClose.close(ptmt, conn, rs);
      }
      return list;
      
   }


   @Override
   public List<BasicBBSDto> getNewestList() {
      Singleton s = Singleton.getInstance();

      List<BasicBBSDto> list = new ArrayList<BasicBBSDto>();
      
      Connection conn = null;
      PreparedStatement psmt = null;
      ResultSet rs = null;
      String sql = null;
      
      
      sql = " SELECT SEQ, TITLE, CONTENT, NAME, WDATE, READCOUNT, DEL, FAMILY, PARENT, DEPTH, INDENT "
            + " FROM BASICBBS WHERE ROWNUM <= 5"
            + " ORDER BY WDATE DESC ";
      
      int seq = 0;
      String title = null;
      String content = null;
      String name = null;
      String wdate = null;
      int readcount = 0;
      int del = 0;
      int family = 0;
      int parent = 0;
      int depth = 0;
      int indent = 0;
      
      
      try {

         conn = DBConnection.getConnection();
         System.out.println("2/6 getBasicBBSList Success");
         
         psmt = conn.prepareStatement(sql);
      
         System.out.println("3/6 getBasicBBSList Success");
         
         rs = psmt.executeQuery();
         System.out.println("4/6 getBasicBBSList Success");
         
         while(rs.next()) {
            
            seq = rs.getInt(1);
            title = rs.getString(2);
            content = rs.getString(3);
            name = rs.getString(4);
            wdate = rs.getString(5);
            readcount = rs.getInt(6);
            del = rs.getInt(7);
            family = rs.getInt(8);
            parent = rs.getInt(9);
            depth = rs.getInt(10);
            indent = rs.getInt(11);
            
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
            
            BasicBBSDto dto = new BasicBBSDto(seq, title, content, name, formatdate, readcount, del, family, parent, depth, indent);
            list.add(dto);
         }
         System.out.println("5/6 getBasicBBSList Success");
      } catch (SQLException e) {
         System.out.println("getBasicBBSList fail");
      }
      finally {
         DBClose.close(psmt, conn, rs);
         System.out.println("6/6 getBasicBBSList Success");
      }
      return list;
   }
   
   

}