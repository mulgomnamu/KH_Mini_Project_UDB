package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dto.BasicBBSDto;
import dto.MemberDto;
import dto.ReplyDto;
import singleton.Singleton;

public class BasicBBSDetailView extends JFrame implements MouseListener, ActionListener {

   private int rowNum;
   private int seq;
   private boolean b;
   private MemberDto m_dto;
   private BasicBBSDto b_dto;

   JTable jtable;
   JScrollPane jScrPane;
   JLabel label_name, label_title, label_readCount, label_wDate, label_content, 
      label_name_view, label_readCount_view, label_wDate_view, label_reply;
   JTextField textField_title_view;
   JTextArea textArea_content_view, textArea_reply;
   JButton btn_list, btn_update, btn_update1, btn_delete, btn_insert_reply, btn_reply, btn_reply_update, btn_reply_replier;
   
   String columnNames[] = {
         "글 번호", "ID", "내용", "작성시간"
   };
   
   Object rowData[][];
   
   DefaultTableModel model;
   
   List<ReplyDto> list = new ArrayList<ReplyDto>();

   Singleton s = Singleton.getInstance();
   
   public BasicBBSDetailView() {   
   }
   
   public BasicBBSDetailView(MemberDto m_dto, BasicBBSDto b_dto) {

      this.m_dto = m_dto;
      this.b_dto = b_dto;

      seq = b_dto.getSeq();
      b_dto = s.BasCtrl.detailBasicBBS(seq);
      
      String name = b_dto.getName();
      String title = b_dto.getTitle();
      String content = b_dto.getContent();
      String wdate = b_dto.getWdate();
      int readCount = b_dto.getReadcount();
      
      if(m_dto.getName().equals(name) || m_dto.getAuth() == 2)   b = true;
      else                            							 b = false;
      
      setLayout(null);
      
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
      
      btn_insert_reply = new JButton();
      btn_insert_reply.setBounds(550, 73, 100, 40);
      btn_insert_reply.setLabel("답글 추가");
      btn_insert_reply.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 16));
      btn_insert_reply.addActionListener(this);
      add(btn_insert_reply);
      
      label_title = new JLabel("제목");
      label_title.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 16));
      label_title.setBounds(100, 70, 100, 50);
      add(label_title);
      
      textField_title_view = new JTextField(title);
      textField_title_view.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 16));
      textField_title_view.setBounds(200, 70, 300, 50);
      textField_title_view.setEditable(false);
      add(textField_title_view);
   /*   
      label_readCount = new JLabel("조회수");
      label_readCount.setFont(new Font(null, Font.PLAIN, 15));
      label_readCount.setBounds(200, 240, 200, 50);
      add(label_readCount);
      
      label_readCount_view = new JLabel(toString().valueOf(readCount));
      label_readCount_view.setFont(new Font(null, Font.PLAIN, 15));
      label_readCount_view.setBounds(400, 240, 500, 50);
      add(label_readCount_view);
   */   
      label_wDate = new JLabel("작성일");
      label_wDate.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 13));
      label_wDate.setBounds(330, 140, 85, 50);
      add(label_wDate);
      
      label_wDate_view = new JLabel(wdate);
      label_wDate_view.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 13));
      label_wDate_view.setBounds(400, 140, 130, 50);
      add(label_wDate_view);
      
      label_name = new JLabel("작성자 ");
      label_name.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 13));
      label_name.setBounds(100, 140, 140, 50);
      add(label_name);
      JSeparator separator2 = new JSeparator();
      separator2.setBounds(98, 180, 182, 1);
      getContentPane().add(separator2);
      
      label_name_view = new JLabel(m_dto.getName());
      label_name_view.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 13));
      label_name_view.setBounds(200, 140, 500, 50);
      add(label_name_view);
      JSeparator separator3 = new JSeparator();
      separator3.setBounds(327, 180, 192, 1);
      getContentPane().add(separator3);
      
      label_content = new JLabel("내용");
      label_content.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 15));
      label_content.setBounds(100, 190, 100, 50);
      add(label_content);

      
      
      textArea_content_view = new JTextArea(content);
      textArea_content_view.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 16));
      textArea_content_view.setBounds(200, 200, 490, 190);
       Border lineBorder = BorderFactory.createLineBorder( new Color(217, 217, 217), 1);
       // 텍스트와 TextArea 경계 사이에 여백을 두기 위해서 emptyBorder를 생성합니다. 
       Border emptyBorder = BorderFactory.createEmptyBorder(1, 1, 1, 1);
       //TextArea에 lineBorder(검정테두리), emptyBorder(여백)로 구성된 복합 경계선을 설정합니다.
       textArea_content_view.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
      textArea_content_view.setEditable(false);
      add(textArea_content_view);
      
      btn_list = new JButton();
      btn_list.setBounds(150, 650, 100, 40);
      btn_list.setLabel("글 목록");
      btn_list.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 15));
      btn_list.addActionListener(this);
      add(btn_list);
      
      btn_update = new JButton();
      btn_update.setBounds(300, 650, 100, 40);
      btn_update.setLabel("수정");
      btn_update.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 15));
      btn_update.addActionListener(this);
      btn_update.setEnabled(b);
      add(btn_update);
      
      btn_update1 = new JButton();
      btn_update1.setBounds(300, 650, 100, 40);
      btn_update1.setLabel("수정완료");
      btn_update1.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 15));
      btn_update1.addActionListener(this);
      btn_update1.setVisible(false);
      add(btn_update1);
      
      btn_delete = new JButton();
      btn_delete.setBounds(450, 650, 100, 40);
      btn_delete.setLabel("삭제");
      btn_delete.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 15));
      btn_delete.addActionListener(this);
      btn_delete.setEnabled(b);
      add(btn_delete);
      
      
      list = s.repCtrl.getReplyList(b_dto);
      
      rowData = new Object[list.size()][4];
      
      for(int i = 0; i < list.size(); i++) {
         rowData[i][0] = i + 1;
         rowData[i][1] = list.get(i).getName();
         rowData[i][2] = list.get(i).getContent();
         rowData[i][3] = list.get(i).getWdate();
      }
      
      model = new DefaultTableModel(columnNames, 0);   
      model.setDataVector(rowData, columnNames);


       
      DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
      celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
      
      jtable = new JTable(model);
      jtable.setGridColor(new Color(70, 70, 70));
      jtable.setRowHeight(35);
      jtable.setShowVerticalLines(false); // 셀 가로줄 
      jtable.setTableHeader(null);
      jtable.addMouseListener(this);   

      jtable.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 13));
      jtable.getColumn("글 번호").setPreferredWidth(20);
      jtable.getColumn("글 번호").setCellRenderer(celAlignCenter);
      jtable.getColumn("ID").setPreferredWidth(90);
      jtable.getColumn("ID").setCellRenderer(celAlignCenter);
      jtable.getColumn("내용").setPreferredWidth(590);
//      jtable.getColumn("내용").setCellRenderer(celAlignCenter);
      jtable.getColumn("작성시간").setPreferredWidth(250);
      jtable.getColumn("작성시간").setCellRenderer(celAlignCenter);

      jScrPane = new JScrollPane(jtable);
       jScrPane.getViewport().setBackground(Color.white); // 바닥
       jScrPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
      jScrPane.setBounds(100, 410, 630, 130);
      
      add(jScrPane);
/*      
      label_reply = new JLabel("댓글 : ");
      label_reply.setFont(new Font(null, Font.PLAIN, 15));
      label_reply.setBounds(0, 600, 100, 50);
      add(label_reply);
*/      
      textArea_reply = new JTextArea("댓글");
      textArea_reply.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 15));
      textArea_reply.setBorder(lineBorder);
      textArea_reply.setBounds(100, 570, 500, 65);
      textArea_reply.addMouseListener(this);
      add(textArea_reply);

      btn_reply = new JButton();
      btn_reply.setBounds(620, 570, 110, 40);
      btn_reply.setLabel("작성");
      btn_reply.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 15));
      btn_reply.setVisible(true);
      btn_reply.addActionListener(this);
      add(btn_reply);
      
      btn_reply_update = new JButton();
      btn_reply_update.setBounds(620, 570, 110, 40);
      btn_reply_update.setLabel("댓글수정");
      btn_reply_update.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 15));
      btn_reply_update.setVisible(false);
      btn_reply_update.addActionListener(this);
      add(btn_reply_update);
      
      btn_reply_replier = new JButton();
      btn_reply_replier.setBounds(620, 570, 110, 40);
      btn_reply_replier.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 15));
      btn_reply_replier.setVisible(false);
      btn_reply_replier.addActionListener(this);
      add(btn_reply_replier);
      
      
      setBounds(100, 0, 805, 800);
      getContentPane().setBackground(Color.WHITE);
      setVisible(true);
      
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
      
      JButton btn = (JButton)e.getSource();
      String btnStr = btn.getLabel();
      
      Singleton s = Singleton.getInstance();
      
      int rowNum_ = rowNum + 1;
      
      if(btnStr.equals("답글 추가")) {
         if(m_dto.getAuth() == 0) {
            return;
         }
         s.BasCtrl.BasicBBSInsertReplyView(m_dto, b_dto);
         this.dispose();
      }else if(btnStr.equals("글 목록")) {
         s.BasCtrl.BasicBBSView(m_dto);
         this.dispose();
      }else if(btnStr.equals("수정")) {
         textField_title_view.setEditable(true);
         textArea_content_view.setEditable(true);
         btn_update.setVisible(false);
         btn_update1.setVisible(true);
      }else if(btnStr.equals("수정완료")) {

          if(textField_title_view.getText().equals("") || textArea_content_view.getText().equals("")) {
             JOptionPane.showMessageDialog(null, "모두 기입해 주십시오");
             return;
          }
          s.BasCtrl.updateBasicBBS(textField_title_view.getText(), textArea_content_view.getText(), seq);
          s.BasCtrl.BasicBBSView(m_dto);
          this.dispose();
      }else if(btnStr.equals("댓글수정")) {

         if(textArea_reply.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "댓글을 입력해 주십시오");
            return;
         }
         s.repCtrl.updateReply(textArea_reply.getText(), seq);
         
         btn_reply.setVisible(true);
         btn_reply_update.setVisible(false);
         
         DefaultTableModel tableModel = (DefaultTableModel) jtable.getModel();
         
         for (int i = 0; i < list.size(); i++) {
            tableModel.removeRow(0);
         }
         
         list = s.repCtrl.getReplyList(b_dto);
         
         rowData = new Object[list.size()][4];
         
         for(int i = 0; i < list.size(); i++) {
            rowData[i][0] = i + 1;
            rowData[i][1] = list.get(i).getName();
            rowData[i][2] = list.get(i).getContent();
            rowData[i][3] = list.get(i).getWdate();
         }
         
         for (int j = 0; j < list.size(); j++) {
            tableModel.addRow(rowData[j]);
         }
         
         textArea_reply.setText("");
         
      }else if(btnStr.equals("댓글 작성")) {

         if(textArea_reply.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "댓글을 입력해 주십시오");
            return;
         }
         
         int family = list.get(rowNum).getFamily();
         int depth = list.get(rowNum).getDepth();
         int indent = list.get(rowNum).getIndent();
         ReplyDto r_dto = new ReplyDto(0, m_dto.getName(), textArea_reply.getText(), null, 0, b_dto.getSeq(), family, seq, depth, indent);
         
         s.repCtrl.writeReplyReplier(r_dto, family, seq, depth, indent);
         
         btn_reply.setVisible(true);
         btn_reply_replier.setVisible(false);
         
         DefaultTableModel tableModel = (DefaultTableModel) jtable.getModel();
         
         for (int i = 0; i < list.size(); i++) {
            tableModel.removeRow(0);
         }
         
         list = s.repCtrl.getReplyList(b_dto);
         
         rowData = new Object[list.size()][4];
         
         for(int i = 0; i < list.size(); i++) {
            rowData[i][0] = i + 1;
            rowData[i][1] = list.get(i).getName();
            rowData[i][2] = list.get(i).getContent();
            rowData[i][3] = list.get(i).getWdate();
         }
         
         for (int j = 0; j < list.size(); j++) {
            tableModel.addRow(rowData[j]);
         }
         
         textArea_reply.setText("");
         
      }else if(btnStr.equals("삭제")) {
         
         s.BasCtrl.deleteBasicBBS(seq);
         s.BasCtrl.BasicBBSView(m_dto);
         this.dispose();
      }else if(btnStr.equals("작성")) {
         if(m_dto.getAuth() == 0) {
            return;
         }
         
         s.repCtrl.writeReply(new ReplyDto(0, m_dto.getName(), textArea_reply.getText(), null, 0, b_dto.getSeq()));
         
         DefaultTableModel tableModel = (DefaultTableModel) jtable.getModel();
         
         for (int i = 0; i < list.size(); i++) {
            tableModel.removeRow(0);
         }
         
         list = s.repCtrl.getReplyList(b_dto);
         
         rowData = new Object[list.size()][4];
         
         for(int i = 0; i < list.size(); i++) {
            rowData[i][0] = i + 1;
            rowData[i][1] = list.get(i).getName();
            rowData[i][2] = list.get(i).getContent();
            rowData[i][3] = list.get(i).getWdate();
         }
         
         for (int j = 0; j < list.size(); j++) {
            tableModel.addRow(rowData[j]);
         }
         
         textArea_reply.setText("");
      }

   }

   @Override
   public void mousePressed(MouseEvent e) {

      rowNum = jtable.getSelectedRow();
      
      if(rowNum == -1) return;
      
      seq = list.get(rowNum).getSeq();
      
      Singleton s = Singleton.getInstance();
      

      
      if(list.get(rowNum).getDel() == 1 || m_dto.getAuth() == 0) {
         return;
      }
      
      if(m_dto.getName().equals(list.get(rowNum).getName())) {
         Object[] option = {"댓글", "수정", "삭제"};
         int result = JOptionPane.showOptionDialog(null, "댓글 추가/삭제/수정", "댓글", 
               JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, option, null);
         if(result == JOptionPane.YES_OPTION) {
            btn_reply_replier.setLabel("댓글 작성");
            btn_reply.setVisible(false);
            btn_reply_replier.setVisible(true);
            
         }else if(result == JOptionPane.NO_OPTION) {
            
            btn_reply.setVisible(false);
            btn_reply_update.setVisible(true);
            
            textArea_reply.setText(list.get(rowNum).getContent());
            
         }else if(result == JOptionPane.CANCEL_OPTION) {
            
            s.repCtrl.deleteReply(seq);
            
            DefaultTableModel tableModel = (DefaultTableModel) jtable.getModel();
            
            for (int i = 0; i < list.size(); i++) {
               tableModel.removeRow(0);
            }
            
            list = s.repCtrl.getReplyList(b_dto);
            
            rowData = new Object[list.size()][4];
            
            for(int i = 0; i < list.size(); i++) {
               rowData[i][0] = i + 1;
               rowData[i][1] = list.get(i).getName();
               rowData[i][2] = list.get(i).getContent();
               rowData[i][3] = list.get(i).getWdate();
            }
            
            for (int j = 0; j < list.size(); j++) {
               tableModel.addRow(rowData[j]);
            }
            
         }
      }else if(m_dto.getAuth() == 2) {
         Object[] option = {"댓글", "삭제"};
         int result = JOptionPane.showOptionDialog(null, "댓글 추가/삭제", "댓글", 
               JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, option, null);
         if(result == JOptionPane.YES_OPTION) {
            btn_reply_replier.setLabel("댓글 작성");
            btn_reply.setVisible(false);
            btn_reply_replier.setVisible(true);
         }else if(result == JOptionPane.NO_OPTION) {
            s.repCtrl.deleteReply(seq);
            
            DefaultTableModel tableModel = (DefaultTableModel) jtable.getModel();
            
            for (int i = 0; i < list.size(); i++) {
               tableModel.removeRow(0);
            }
            
            list = s.repCtrl.getReplyList(b_dto);
            
            rowData = new Object[list.size()][4];
            
            for(int i = 0; i < list.size(); i++) {
               rowData[i][0] = i + 1;
               rowData[i][1] = list.get(i).getName();
               rowData[i][2] = list.get(i).getContent();
               rowData[i][3] = list.get(i).getWdate();
            }
            
            for (int j = 0; j < list.size(); j++) {
               tableModel.addRow(rowData[j]);
            }
         }
      }else if(m_dto.getAuth() != 0) {
         Object[] option = {"댓글", "취소"};
         int result = JOptionPane.showOptionDialog(null, "댓글 추가", "댓글", 
               JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, option, null);
         if(result == JOptionPane.YES_OPTION) {
            btn_reply_replier.setLabel("댓글 작성");
            btn_reply.setVisible(false);
            btn_reply_replier.setVisible(true);
         }
      }
      
   }

   @Override
   public void mouseReleased(MouseEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void mouseEntered(MouseEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void mouseExited(MouseEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void mouseClicked(MouseEvent e) {
	     if(e.getSource().equals(textArea_reply)) {
	    	  textArea_reply.setText("");
	       }
      
   }

}