package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.StyledEditorKit.BoldAction;

import dao.MarketBbsDao;
import dto.MarketDto;
import dto.MarketReplyDto;
import dto.MemberDto;
import dto.ReplyDto;
import singleton.Singleton;

public class MarketDetailView extends JFrame implements MouseListener, ActionListener{


	private JLabel imgLabel;
	private String imagesrc;
	private ImageIcon img;
	private int getDel = 0;
	
	
	private int rowNum;
	private int seq;
	
	JTable jtable;
	JScrollPane jsp;
	JTextArea textArea_reply;
	JButton btn_reply, btn_reply_update, btn_reply_replier;
	
	String columnNames[] = {
			"글 번호", "ID", "내용", "작성시간"
	};
	
	Object rowData[][];
	
	DefaultTableModel model;
	
	Singleton s = Singleton.getInstance();

	private JLabel soldLabel;
	private JLabel titleLabel;
	private JLabel categoryLabel;
	private JLabel nameLabel;
	private JLabel priceLabel;
	private JLabel readcountLabel;
	
	private JTextArea contentsArea;
	
	private Font soldFont;
	private Font priceFont;
	
	private JButton delBtn;
	private JButton updateBtn;
	private JButton backBtn;
	private JButton reBtn;
	
	private JScrollPane jScrPane;
	private MemberDto loginDto;
	private MarketDto dto;
	
	private List<MarketReplyDto> list = new ArrayList<MarketReplyDto>();
	
	public MarketDetailView(MarketDto dto, MemberDto loginDto) {
		super("");
		setLayout(null);
		this.loginDto = loginDto;
		this.dto = dto;
		
		// dto연결시 바꿔야함
		img = new ImageIcon(dto.getImagesrc());
		Image orginalImg = img.getImage();  //ImageIcon을 Image로 변환.
		Image resize = orginalImg.getScaledInstance(280, 280, Image.SCALE_SMOOTH);

		ImageIcon resizeImg = new ImageIcon(resize); //Image로 ImageIcon 생성

		imgLabel = new JLabel(resizeImg);
		imgLabel.setBounds(130, 50, 280, 280);
		add(imgLabel);
		
		String sold = null;
		if (dto.getSold() == 0) {
			sold = "판매중";
		} else {
			sold = "판매완료";
			dto.setSold(1);
		}
		soldLabel = new JLabel(sold);
		soldLabel.setBounds(450, 80, 280, 56);
		soldFont = new Font("아리따-돋움(TTF)-SemiBold", Font.PLAIN, 25);
		soldLabel.setFont(soldFont);
		add(soldLabel);
		
		JLabel titleLb = new JLabel("제목 :");
	      titleLb.setBounds(450, 130, 150, 56);
	      titleLb.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 16));
	      add(titleLb);
	      titleLabel = new JLabel(dto.getTitle());
	      titleLabel.setBounds(520, 130, 280, 56);
	      titleLabel.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 16));
	      add(titleLabel);
	      JSeparator separator1 = new JSeparator();
	      separator1.setBounds(440, 175, 230, 1);
	      getContentPane().add(separator1);

	      JLabel categoryLb = new JLabel("품명 :");
	      categoryLb.setBounds(450, 170, 150, 56);
	      categoryLb.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 16));
	      add(categoryLb);
	      categoryLabel = new JLabel(dto.getCategory());
	      categoryLabel.setBounds(520, 170, 280, 56);
	      categoryLabel.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 16));
	      add(categoryLabel);
	      JSeparator separator2 = new JSeparator();
	      separator2.setBounds(440, 215, 230, 1);
	      getContentPane().add(separator2);

	      JLabel nameLb = new JLabel("이름 :");
	      nameLb.setBounds(450, 210, 150, 56);
	      nameLb.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 16));
	      add(nameLb);
	      nameLabel = new JLabel(dto.getName());
	      nameLabel.setBounds(520, 210, 280, 56);
	      nameLabel.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 16));
	      add(nameLabel);
	      JSeparator separator3 = new JSeparator();
	      separator3.setBounds(440, 255, 230, 1);
	      getContentPane().add(separator3);

	      JLabel priceLb = new JLabel("가격 :");
	      priceLb.setBounds(450, 253, 150, 56);
	      priceLb.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 16));
	      add(priceLb);
	      priceLabel = new JLabel(dto.getPrice() + "");
	      priceFont = new Font("아리따-돋움(TTF)-SemiBold", Font.PLAIN, 20);
	      priceLabel.setForeground(Color.RED);
	      priceLabel.setFont(soldFont);
	      priceLabel.setBounds(520, 251, 280, 56);
	      add(priceLabel);
	      JSeparator separator4 = new JSeparator();
	      separator4.setBounds(440, 305, 230, 1);
	      getContentPane().add(separator4);

	      contentsArea = new JTextArea();   
	      contentsArea.setLineWrap(true);
	      contentsArea.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 16));
	      contentsArea.setBounds(110, 340, 580, 50);
	      contentsArea.setText(dto.getContents());
	      contentsArea.setEditable(false);
	      add(contentsArea);
	      
	      if (dto.getName().equals(loginDto.getName()) || loginDto.getAuth() == 2) {
	         delBtn = new JButton("삭제");
	         delBtn.setBounds(345, 650, 100, 50);
	         delBtn.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 15));
	         add(delBtn);
	         delBtn.addActionListener(this);
	         
	         updateBtn = new JButton("수정");
	         updateBtn.setBounds(455, 650, 100, 50);
	         updateBtn.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 15));
	         add(updateBtn);
	         updateBtn.addActionListener(this);
	      }
	      
	      backBtn = new JButton("돌아가기");
	      backBtn.setBounds(565, 650, 100, 50);
	      backBtn.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 15));
	      add(backBtn);
	      backBtn.addActionListener(this);
	      
	      jScrPane = new JScrollPane(contentsArea);
	      jScrPane.setBounds(130, 350, 540, 50);
	      add(jScrPane);
		
		
		list = s.mRepCtrl.getReplyList(dto);
		
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

		jtable.setFont(new Font("나눔고딕", Font.PLAIN, 13));
		jtable.getColumn("글 번호").setPreferredWidth(20);
		jtable.getColumn("글 번호").setCellRenderer(celAlignCenter);
		jtable.getColumn("ID").setPreferredWidth(160);
		jtable.getColumn("ID").setCellRenderer(celAlignCenter);
		jtable.getColumn("내용").setPreferredWidth(590);
//		jtable.getColumn("내용").setCellRenderer(celAlignCenter);
		jtable.getColumn("작성시간").setPreferredWidth(250);
		jtable.getColumn("작성시간").setCellRenderer(celAlignCenter);

		jsp = new JScrollPane(jtable);
		jsp.getViewport().setBackground(Color.white); // 바닥
		jsp.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		jsp.setBounds(100, 410, 630, 130);
		
		add(jsp);
/*		
		label_reply = new JLabel("댓글 : ");
		label_reply.setFont(new Font(null, Font.PLAIN, 15));
		label_reply.setBounds(0, 600, 100, 50);
		add(label_reply);
*/		
		textArea_reply = new JTextArea("댓글");
		textArea_reply.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 15));
	    Border lineBorder = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);
		textArea_reply.setBorder(lineBorder);
		textArea_reply.setBounds(100, 560, 500, 80);
		add(textArea_reply);
		
		btn_reply = new JButton();
		btn_reply.setBounds(620, 570, 70, 40);
		btn_reply.setLabel("작성");
		btn_reply.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 15));
		btn_reply.setVisible(true);
		btn_reply.addActionListener(this);
		add(btn_reply);
		
		btn_reply_update = new JButton();
		btn_reply_update.setBounds(620, 570, 90, 40);
		btn_reply_update.setLabel("댓글수정");
		btn_reply_update.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 15));
		btn_reply_update.setVisible(false);
		btn_reply_update.addActionListener(this);
		add(btn_reply_update);
		
		btn_reply_replier = new JButton();
		btn_reply_replier.setBounds(620, 570, 70, 40);
		btn_reply_replier.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 15));
		btn_reply_replier.setVisible(false);
		btn_reply_replier.addActionListener(this);
		add(btn_reply_replier);
		
		

		setBounds(300, 100, 805, 800);
	    getContentPane().setBackground(Color.WHITE);
		setVisible(true);	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Singleton sc = Singleton.getInstance();
		Object obj = e.getSource();
		
		if (obj == backBtn) {
			this.dispose();
		} else if (obj == delBtn) {
			sc.marketCtrl.bbsDelete(dto.getSeq());
			dispose();
		} else if (obj == updateBtn) {
			sc.marketCtrl.bbsUpdate(dto.getSeq());
			dispose();
		}else if(obj==btn_reply_update) {

			if(textArea_reply.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "댓글을 입력해 주십시오");
				return;
			}
			s.mRepCtrl.updateReply(textArea_reply.getText(), seq);
			
			btn_reply.setVisible(true);
			btn_reply_update.setVisible(false);
			
			DefaultTableModel tableModel = (DefaultTableModel) jtable.getModel();
			
			for (int i = 0; i < list.size(); i++) {
				tableModel.removeRow(0);
			}
			
			list = s.mRepCtrl.getReplyList(dto);
			
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
			
		}else if(obj==btn_reply_replier) {

			if(textArea_reply.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "댓글을 입력해 주십시오");
				return;
			}
			
			int family = list.get(rowNum).getFamily();
			int depth = list.get(rowNum).getDepth();
			int indent = list.get(rowNum).getIndent();
			MarketReplyDto r_dto = new MarketReplyDto(0, loginDto.getName(), textArea_reply.getText(), null, 0, dto.getSeq(), family, seq, depth, indent);
			
			s.mRepCtrl.writeReplyReplier(r_dto, family, seq, depth, indent);
			
			btn_reply.setVisible(true);
			btn_reply_replier.setVisible(false);
			
			DefaultTableModel tableModel = (DefaultTableModel) jtable.getModel();
			
			for (int i = 0; i < list.size(); i++) {
				tableModel.removeRow(0);
			}
			
			list = s.mRepCtrl.getReplyList(dto);
			
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
			
		}else if(obj==btn_reply) {
			if(loginDto.getAuth() == 0) {
	            return;
	         }
			
			s.mRepCtrl.writeReply(new MarketReplyDto(0, loginDto.getName(), textArea_reply.getText(), null, 0, dto.getSeq()));
			
			DefaultTableModel tableModel = (DefaultTableModel) jtable.getModel();
			
			for (int i = 0; i < list.size(); i++) {
				tableModel.removeRow(0);
			}
			
			list = s.mRepCtrl.getReplyList(dto);
			
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
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {

		rowNum = jtable.getSelectedRow();
		
		seq = list.get(rowNum).getSeq();
		
		Singleton s = Singleton.getInstance();
		
		if(list.get(rowNum).getDel() == 1 || loginDto.getAuth() == 0) {
			return;
		}
		
		if(loginDto.getName().equals(list.get(rowNum).getName())) {
			Object[] option = {"댓글", "수정", "삭제"};
			int result = JOptionPane.showOptionDialog(null, "댓글 추가/삭제/수정", "댓글", 
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, option, null);
			if(result == JOptionPane.YES_OPTION) {
				int rowNum_ = rowNum + 1;
				btn_reply_replier.setLabel("댓글 작성");
	            btn_reply.setVisible(false);
	            btn_reply_replier.setVisible(true);
				
			}else if(result == JOptionPane.NO_OPTION) {
				
				btn_reply.setVisible(false);
				btn_reply_update.setVisible(true);
				
				textArea_reply.setText(list.get(rowNum).getContent());
				
			}else if(result == JOptionPane.CANCEL_OPTION) {
				
				s.mRepCtrl.deleteReply(seq);
				
				DefaultTableModel tableModel = (DefaultTableModel) jtable.getModel();
				
				for (int i = 0; i < list.size(); i++) {
					tableModel.removeRow(0);
				}
				
				list = s.mRepCtrl.getReplyList(dto);
				
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
		}else if(loginDto.getAuth() == 2) {
	         Object[] option = {"댓글", "삭제"};
	         int result = JOptionPane.showOptionDialog(null, "댓글 추가/삭제", "댓글", 
	               JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, option, null);
	         if(result == JOptionPane.YES_OPTION) {
	            btn_reply_replier.setLabel("댓글 작성");
	            btn_reply.setVisible(false);
	            btn_reply_replier.setVisible(true);
	         }else if(result == JOptionPane.NO_OPTION) {
	        	 
	        	s.mRepCtrl.deleteReply(seq);
					
				DefaultTableModel tableModel = (DefaultTableModel) jtable.getModel();
				
				for (int i = 0; i < list.size(); i++) {
					tableModel.removeRow(0);
				}
				
				list = s.mRepCtrl.getReplyList(dto);
				
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
	      }else if(loginDto.getAuth() != 0) {
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
	
}