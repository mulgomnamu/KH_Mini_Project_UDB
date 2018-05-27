package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dto.BasicBBSDto;
import dto.MemberDto;
import singleton.Singleton;

public class BasicBBSView extends JFrame implements MouseListener, ItemListener, ActionListener {
	
	private int rowNum;
	private int userAuth;
	private int totalRecord;
	private int numPerPage;
	private int totalPage;
	private int curPage;
	private String userId;
	private Object keyword;
	private String select_word;
	private MemberDto m_dto;
	
	JTable jtable;
	JTable _jtable;
	JLabel label_welcome;
	JScrollPane jScrPane;
	JComboBox combo = null;
	JTextField textField_select;
	JButton mainbtn;
	JButton menubtn1,menubtn2,menubtn3,menubtn4,menubtn5;
	JButton menutextbtn1,menutextbtn2,menutextbtn3,menutextbtn4,menutextbtn5;
	JButton loginbtn;
	JButton joinbtn;
	
	JButton btn_addBoard, btn_logOut, btn_select, 
		btn_page1, btn_page2, btn_page3, btn_page4, btn_page5, 
		btn_page6, btn_page7, btn_page8, btn_page9, btn_page10;
	
	String columnNames[] = {
			"글 번호", "제목", "글쓴이", "조회수"
	};
	
	Object rowData[][];
	
	DefaultTableModel model;
	
	List<BasicBBSDto> list = new ArrayList<BasicBBSDto>();

	Singleton s = Singleton.getInstance();
	
	JButton btn_page[];
	
	public BasicBBSView(MemberDto m_dto) {
		
		setLayout(null);
		this.m_dto = m_dto;
		
		Singleton s = Singleton.getInstance();
		
        Color sepaColor = new Color(217, 217, 217);
        JSeparator separator = new JSeparator();
        separator.setBounds(162, 200, 830, 1);
        separator.setForeground(sepaColor);
        getContentPane().add(separator);
        
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		curPage = 1;	// 현재 페이지
		numPerPage = 8;	// 한 페이지에서 보일 레코드 수
		list = s.BasCtrl.getBasicBBSList(curPage, numPerPage);
		
		rowData = new Object[list.size()][4];
		
		for(int i = 0; i < list.size(); i++) {
			rowData[i][0] = i + 1;
			rowData[i][1] = list.get(i).getTitle();
			rowData[i][2] = list.get(i).getName();
			rowData[i][3] = list.get(i).getReadcount();
		}
		
		
		model = new DefaultTableModel(columnNames, 0);
		
		model.setDataVector(rowData, columnNames);
		
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		
		jtable = new JTable(model);
		jtable.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 16));
		jtable.getColumn("글 번호").setPreferredWidth(20);
		jtable.getColumn("글 번호").setCellRenderer(celAlignCenter);
		jtable.getColumn("제목").setPreferredWidth(200);
//		jtable.getColumn("제목").setCellRenderer(celAlignCenter);
		jtable.getColumn("글쓴이").setPreferredWidth(90);
		jtable.getColumn("글쓴이").setCellRenderer(celAlignCenter);
		jtable.getColumn("조회수").setPreferredWidth(30);
		jtable.getColumn("조회수").setCellRenderer(celAlignCenter);

		jtable.setRowHeight(46);
		jtable.setGridColor(Color.white);
	//	jtable.setFont(new Font("돋움", Font.PLAIN, 14));
		jtable.addMouseListener(this);
		jScrPane = new JScrollPane(jtable);
		jScrPane.getViewport().setBackground(Color.white); 
		jScrPane.setBounds(180, 240, 800, 420);
		add(jScrPane);
		
		Label label3 = new Label(m_dto.getName() + " 님 환영합니다");  
		label3.setBounds(800, 15, 200, 25);
		label3.setBackground(Color.WHITE);
		label3.setFont(new Font("윤고딕", Font.BOLD, 15));
		
		ImageIcon writebtn = new ImageIcon("img/writebtn.png");  //이미지 경로		
		
		btn_addBoard = new JButton("글쓰기",writebtn);
		btn_addBoard.setBounds(860, 670, 120, 38);
		btn_addBoard.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 14));
		btn_addBoard.addActionListener(this);
		btn_addBoard.setContentAreaFilled(false);
		btn_addBoard.setFocusPainted(false);
		btn_addBoard.setOpaque(false);
		add(btn_addBoard);
		
		mainbtn = new JButton(new ImageIcon("img/logo1.jpg")); // 로고 btn
		mainbtn.setBounds(15, 10, 190, 190);
		mainbtn.setBorderPainted(false); 	// 경계선 안보이게
		mainbtn.setFocusPainted(false); 	// 버튼 선택되었을때 생기는 테두리 안보이게
		mainbtn.setContentAreaFilled(false); // 자바 기본버튼 컬러 안보이게
		mainbtn.addActionListener(this); 
		
//		mainbtn.setOpaque(false); // 투명화 
		add(mainbtn);
		
		loginbtn = new JButton("login");
		loginbtn.setBounds(850, 10, 80, 30);
		loginbtn.setBorderPainted(false); 	// 경계선 안보이게
		loginbtn.setFocusPainted(false); 	// 버튼 선택되었을때 생기는 테두리 안보이게
		loginbtn.setContentAreaFilled(false); // 자바 기본버튼 컬러 안보이게
		loginbtn.addActionListener(this); 
		loginbtn.setFont(new Font("윤고딕", Font.BOLD, 15));
		
		joinbtn = new JButton(" join");
		joinbtn.setBounds(920, 10, 80, 30);
		joinbtn.setBorderPainted(false); 	// 경계선 안보이게
		joinbtn.setFocusPainted(false); 	// 버튼 선택되었을때 생기는 테두리 안보이게
		joinbtn.setContentAreaFilled(false); // 자바 기본버튼 컬러 안보이게
		joinbtn.addActionListener(this); 
		joinbtn.setFont(new Font("윤고딕", Font.BOLD, 15));
		
		btn_logOut = new JButton(" logout");
		btn_logOut.setBounds(1000, 10, 120, 30);
		btn_logOut.setBorderPainted(false); 	// 경계선 안보이게
		btn_logOut.setFocusPainted(false); 	// 버튼 선택되었을때 생기는 테두리 안보이게
		btn_logOut.setContentAreaFilled(false); // 자바 기본버튼 컬러 안보이게
		btn_logOut.addActionListener(this); 
		btn_logOut.setFont(new Font("윤고딕", Font.BOLD, 15));
		
		if(m_dto.getName().equals("guest")) {
			add(loginbtn);
			add(joinbtn);
		}else {
			add(btn_logOut);
			add(label3);
		}
		
		menubtn1 = new JButton(new ImageIcon("img/menubtn1.png")); 
		menubtn1.setBounds(300, 90, 110, 100);
		menubtn1.setBorderPainted(false); 	// 경계선 안보이게
		menubtn1.setFocusPainted(false); 	// 버튼 선택되었을때 생기는 테두리 안보이게
		menubtn1.setContentAreaFilled(false); // 자바 기본버튼 컬러 안보이게
		menubtn1.addActionListener(this); 
		menubtn1.setOpaque(false); // 투명화 
		
		menutextbtn1 = new JButton(new ImageIcon("img/menutextbtn1.png")); 
		menutextbtn1.setBounds(260, 110, 130, 80);
		menutextbtn1.setBorderPainted(false); 	// 경계선 안보이게
		menutextbtn1.setFocusPainted(false); 	// 버튼 선택되었을때 생기는 테두리 안보이게
		menutextbtn1.setContentAreaFilled(false); // 자바 기본버튼 컬러 안보이게
		menutextbtn1.addActionListener(this); 
		add(menutextbtn1);
		
		menubtn2 = new JButton(new ImageIcon("img/menubtn2.png")); 
		menubtn2.setBounds(500, 90, 110, 100);
		menubtn2.setBorderPainted(false); 	// 경계선 안보이게
		menubtn2.setFocusPainted(false); 	// 버튼 선택되었을때 생기는 테두리 안보이게
		menubtn2.setContentAreaFilled(false); // 자바 기본버튼 컬러 안보이게
		menubtn2.addActionListener(this); 
		
		menutextbtn2 = new JButton(new ImageIcon("img/menutextbtn2.png")); 
		menutextbtn2.setBounds(405, 110, 130, 80);
		menutextbtn2.setBorderPainted(false); 	// 경계선 안보이게
		menutextbtn2.setFocusPainted(false); 	// 버튼 선택되었을때 생기는 테두리 안보이게
		menutextbtn2.setContentAreaFilled(false); // 자바 기본버튼 컬러 안보이게
		menutextbtn2.addActionListener(this); 
		add(menutextbtn2);
		
		menubtn3 = new JButton(new ImageIcon("img/menubtn3.png")); 
		menubtn3.setBounds(700, 90, 100, 100);
		menubtn3.setBorderPainted(false); 	// 경계선 안보이게
		menubtn3.setFocusPainted(false); 	// 버튼 선택되었을때 생기는 테두리 안보이게
		menubtn3.setContentAreaFilled(false); // 자바 기본버튼 컬러 안보이게
		menubtn3.addActionListener(this); 
		menubtn3.setFont(new Font("돋움", Font.BOLD, 15));

		menutextbtn3 = new JButton(new ImageIcon("img/menutextbtn3.png")); 
		menutextbtn3.setBounds(537, 110, 130, 80);
		menutextbtn3.setBorderPainted(false); 	// 경계선 안보이게
		menutextbtn3.setFocusPainted(false); 	// 버튼 선택되었을때 생기는 테두리 안보이게
		menutextbtn3.setContentAreaFilled(false); // 자바 기본버튼 컬러 안보이게
		menutextbtn3.addActionListener(this); 
		add(menutextbtn3);
		
		menubtn4 = new JButton(new ImageIcon("img/menubtn4.png")); 
		menubtn4.setBounds(900, 90, 100, 100);
		menubtn4.setBorderPainted(false); 	// 경계선 안보이게
		menubtn4.setFocusPainted(false); 	// 버튼 선택되었을때 생기는 테두리 안보이게
		menubtn4.setContentAreaFilled(false); // 자바 기본버튼 컬러 안보이게
		menubtn4.addActionListener(this); 
		
		menutextbtn4 = new JButton(new ImageIcon("img/menutextbtn4.png")); 
		menutextbtn4.setBounds(662, 110, 130, 80);
		menutextbtn4.setBorderPainted(false); 	// 경계선 안보이게
		menutextbtn4.setFocusPainted(false); 	// 버튼 선택되었을때 생기는 테두리 안보이게
		menutextbtn4.setContentAreaFilled(false); // 자바 기본버튼 컬러 안보이게
		menutextbtn4.addActionListener(this); 
		add(menutextbtn4);
		
		menubtn5 = new JButton(new ImageIcon("img/menubtn5.png")); 
		menubtn5.setBounds(1050, 90, 100, 100);
		menubtn5.setBorderPainted(false); 	// 경계선 안보이게
		menubtn5.setFocusPainted(false); 	// 버튼 선택되었을때 생기는 테두리 안보이게
		menubtn5.setContentAreaFilled(false); // 자바 기본버튼 컬러 안보이게
		menubtn5.addActionListener(this); 
		
		menutextbtn5 = new JButton(new ImageIcon("img/menutextbtn5.png")); 
		menutextbtn5.setBounds(795, 110, 130, 80);
		menutextbtn5.setBorderPainted(false); 	// 경계선 안보이게
		menutextbtn5.setFocusPainted(false); 	// 버튼 선택되었을때 생기는 테두리 안보이게
		menutextbtn5.setContentAreaFilled(false); // 자바 기본버튼 컬러 안보이게
		menutextbtn5.addActionListener(this); 
		add(menutextbtn5);
		
		
		menutextbtn1.addMouseListener(new MouseAdapter(){ 
		       //마우스가 버튼위로 들어왔을때 
		         public void mouseEntered(MouseEvent e){ 
		         ((JButton)e.getSource()).setIcon(new ImageIcon("img/menubtn1.png")); 
			        add(menutextbtn1);
			 //       menubtn1.setVisible(false);
		         } 
		         public void mouseExited(MouseEvent e){ 
		       // 	 menubtn1.setVisible(true);
		        	 ((JButton)e.getSource()).setIcon(new ImageIcon("img/menutextbtn1.png"));
		          } 
		       }); 
			
			menutextbtn2.addMouseListener(new MouseAdapter(){ 
			         public void mouseEntered(MouseEvent e){ 
			         ((JButton)e.getSource()).setIcon(new ImageIcon("img/menubtn2.png")); 
				        add(menutextbtn2);
			         } 
			         public void mouseExited(MouseEvent e){ 
			        	 ((JButton)e.getSource()).setIcon(new ImageIcon("img/menutextbtn2.png"));
			          } 
			       }); 
			menutextbtn3.addMouseListener(new MouseAdapter(){ 
		         public void mouseEntered(MouseEvent e){ 
		         ((JButton)e.getSource()).setIcon(new ImageIcon("img/menubtn3.png")); 
			        add(menutextbtn3);
		         } 
		         public void mouseExited(MouseEvent e){ 
		        	 ((JButton)e.getSource()).setIcon(new ImageIcon("img/menutextbtn3.png"));
		          } 
		       }); 
			
			menutextbtn4.addMouseListener(new MouseAdapter(){ 
		         public void mouseEntered(MouseEvent e){ 
		         ((JButton)e.getSource()).setIcon(new ImageIcon("img/menubtn4.png")); 
			        add(menutextbtn4);
		         } 
		         public void mouseExited(MouseEvent e){ 
		        	 ((JButton)e.getSource()).setIcon(new ImageIcon("img/menutextbtn4.png"));
		          } 
		       }); 
			
			menutextbtn5.addMouseListener(new MouseAdapter(){ 
		         public void mouseEntered(MouseEvent e){ 
		         ((JButton)e.getSource()).setIcon(new ImageIcon("img/menubtn5.png")); 
			        add(menutextbtn5);
		         } 
		         public void mouseExited(MouseEvent e){ 
		        	 ((JButton)e.getSource()).setIcon(new ImageIcon("img/menutextbtn5.png"));
		          } 
		       }); 
			
			loginbtn.addMouseListener(new MouseAdapter(){ 
			        public void mouseEntered(MouseEvent e){ 
			        loginbtn.setForeground(Color.blue);
			         } 
			         public void mouseExited(MouseEvent e){ 
					    loginbtn.setForeground(Color.black);
			          } 
			       });
			joinbtn.addMouseListener(new MouseAdapter(){ 
		        public void mouseEntered(MouseEvent e){ 
		        	joinbtn.setForeground(Color.blue);
		         } 
		         public void mouseExited(MouseEvent e){ 
		        	 joinbtn.setForeground(Color.black);
		          } 
		       }); 
		
		String[] key = {"글쓴이", "제목", "내용"};
		combo = new JComboBox(key);
		Object keyword = combo.getSelectedItem();
		combo.setBounds(230, 700, 130, 35);
		combo.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 13));
		add(combo);
		
		textField_select = new JTextField();
		textField_select.setFont(new Font(null, Font.PLAIN, 13));
		textField_select.setBounds(390, 700, 350, 35);
		add(textField_select);
		
		btn_select = new JButton(new ImageIcon("img/selectbtn.png")); 
		btn_select.setBounds(720, 700, 100, 35);
		btn_select.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 15));
		btn_select.addActionListener(this);
		btn_select.setBorderPainted(false); 	// 경계선 안보이게
		btn_select.setFocusPainted(false); 	// 버튼 선택되었을때 생기는 테두리 안보이게
		btn_select.setContentAreaFilled(false); // 자바 기본버튼 컬러 안보이게
		add(btn_select);
		
		int sel;
		// 페이지 분할 기능
		if(textField_select.getText().equals("")) {
			sel = 0;
			totalRecord = s.BasCtrl.getTotalRecord(keyword, select_word, sel);
		}else {
			sel = 1;
			totalRecord = s.BasCtrl.getTotalRecord(keyword, select_word, sel);
		}
//		JOptionPane.showMessageDialog(null, "totalRecord = " + totalRecord + " sel = " + sel);
		
		// 페이지당 보일 레코드 수를 결정하고 총 페이지 수 구하기
		totalPage = 0;		// 총 페이지 수
		if(totalRecord != 0) {
			if(totalRecord % numPerPage == 0) {
				totalPage = totalRecord / numPerPage;
			}else {
				totalPage = totalRecord / numPerPage + 1;
			}
		}
		
		btn_page = new JButton[totalPage];
		for (int i = 0; i < totalPage; i++) {
			btn_page[i] = new JButton();
			int j = i + 1;
			btn_page[i].setLabel(Integer.toString(j));
			btn_page[i].setBounds(450 + (50 * i), 652, 45, 40);
			btn_page[i].setFont(new Font(null, Font.BOLD, 12));		
			btn_page[i].setBorderPainted(false); 	// 경계선 안보이게
			btn_page[i].setFocusPainted(false); 	// 버튼 선택되었을때 생기는 테두리 안보이게
			btn_page[i].setContentAreaFilled(false);
			btn_page[i].addActionListener(this);
			
			Map map = btn_page[i].getFont().getAttributes();
			map.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
			Font font = new Font(map);
			btn_page[i].setFont(font);
			add(btn_page[i]);
	}
	//	btn_page[0].setFont(new Font("굴림", Font.PLAIN, 20));

		
		setBounds(100, 0, 1150, 850);
		setVisible(true);	
		getContentPane().setBackground(Color.WHITE);	
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		String btnStr = btn.getLabel();
		
		if(btn == loginbtn){
			new loginView();
			this.dispose();
		}
		if(btn == joinbtn){
			s.memCtrl.regi();	
			this.dispose();	
		}
		if(btn == btn_logOut) {
			new mainView(new MemberDto("", "", "guest", "", "", 0));
			this.dispose();
		}
		if(btn == mainbtn) {
			new mainView(m_dto);
			this.dispose();
		}
		if(btn == loginbtn){
			new loginView();	
		}
			
		if(btn == joinbtn){
			s.memCtrl.regi();		
		}
		
		if(btn == mainbtn) {
			new mainView(m_dto);
			this.dispose();
		}
		
		if(btn == menutextbtn1) {
			s.BasCtrl.BasicBBSView(m_dto);
			this.dispose();
		}
		
        if (btn == menutextbtn2) {     
        	s.voteCtrl.voteList(m_dto);
        	s.voteCtrl.voteRenewal();
    		this.dispose();
        }
        
        if (btn == menutextbtn3) {     
        	s.marketCtrl.getBbsList(m_dto);
        	this.dispose();
        }
        
        if (btn == menutextbtn4) { 
        	//s.revCtrl.getRevList(caldto);
        	SwingUtilities.invokeLater(new Runnable(){
                public void run(){
                   new MemoCalendar(m_dto);
                }           
             });
            this.dispose();
        }
		
        if (btn == menutextbtn5) {
            if(m_dto.getAuth() == 0) {
               return;
            }
            s.chatCtrl.mainChat(m_dto.getName(), m_dto.getAuth());
        }
		if(btn == btn_addBoard) {
			s.BasCtrl.BasicBBSInsertView(m_dto);
			
		}
		if(btn == btn_logOut) {
			s.BasCtrl.loginView();
		
		}
		else if(btn == btn_select) {
			
			curPage = 1;
			
			DefaultTableModel tableModel = (DefaultTableModel) jtable.getModel();
			
			for (int i = 0; i < list.size(); i++) {
				tableModel.removeRow(0);
			}
			keyword = combo.getSelectedItem();
			select_word = textField_select.getText();
			list = s.BasCtrl.selectBasicBBSList(keyword, select_word, curPage, numPerPage);
			
			rowData = new Object[list.size()][4];
			
			for(int i = 0; i < list.size(); i++) {
				rowData[i][0] = i + 1;
				rowData[i][1] = list.get(i).getTitle();
				rowData[i][2] = list.get(i).getName();
				rowData[i][3] = list.get(i).getReadcount();
			}
			
			for (int j = 0; j < list.size(); j++) {
				tableModel.addRow(rowData[j]);
			}
			
			for (int i = 0; i < btn_page.length; i++) {
				btn_page[i].setFont(new Font(null, Font.PLAIN, 13));
				Map map = btn_page[i].getFont().getAttributes();
				map.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
				Font font = new Font(map);
			}
			btn_page[0].setFont(new Font(null, Font.PLAIN, 13));
			Map map = btn_page[0].getFont().getAttributes();
			map.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
			Font font = new Font(map);
		}
		
		for (int k = 0; k < btn_page.length; k++) {
			
			if(btn == btn_page[k]) {
				if(textField_select.getText().equals("")) {
					curPage = k + 1;
					DefaultTableModel tableModel = (DefaultTableModel) jtable.getModel();
					
					for (int i = 0; i < list.size(); i++) {
						tableModel.removeRow(0);
					}
					
					list = s.BasCtrl.getBasicBBSList(curPage, numPerPage);
					
					rowData = new Object[list.size()][4];
					
					for(int i = 0; i < list.size(); i++) {
						rowData[i][0] = i + 1;
						rowData[i][1] = list.get(i).getTitle();
						rowData[i][2] = list.get(i).getName();
						rowData[i][3] = list.get(i).getReadcount();
					}
					
					for (int j = 0; j < list.size(); j++) {
						tableModel.addRow(rowData[j]);
					}
					for (int i = 0; i < btn_page.length; i++) {
						btn_page[i].setFont(new Font(null, Font.PLAIN, 12));
					}
					btn_page[k].setFont(new Font(null, Font.PLAIN, 14));
				}else {
					curPage = k + 1;
					DefaultTableModel tableModel = (DefaultTableModel) jtable.getModel();
					
					for (int i = 0; i < list.size(); i++) {
						tableModel.removeRow(0);
					}
					
					list = s.BasCtrl.selectBasicBBSList(combo.getSelectedItem(), textField_select.getText(), curPage, numPerPage);
					
					rowData = new Object[list.size()][4];
					
					for(int i = 0; i < list.size(); i++) {
						rowData[i][0] = i + 1;
						rowData[i][1] = list.get(i).getTitle();
						rowData[i][2] = list.get(i).getName();
						rowData[i][3] = list.get(i).getReadcount();
					}
					
					for (int j = 0; j < list.size(); j++) {
						tableModel.addRow(rowData[j]);
					}
					for (int i = 0; i < btn_page.length; i++) {
						btn_page[i].setFont(new Font(null, Font.PLAIN, 12));
						Map map = btn_page[i].getFont().getAttributes();
						map.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
						Font font = new Font(map);
					}
					btn_page[k].setFont(new Font(null, Font.PLAIN, 12));
					Map map = btn_page[k].getFont().getAttributes();
					map.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
					Font font = new Font(map);

				}
			}
		}	
      }
	

	@Override
	public void itemStateChanged(ItemEvent e) {
		
	}
	
	
	@Override
	public void mousePressed(MouseEvent e) {
		
		rowNum = jtable.getSelectedRow();
		
		int seq = list.get(rowNum).getSeq();
		int readCount = list.get(rowNum).getReadcount();
		
		if(list.get(rowNum).getDel() == 1) {
			return;
		}
		
		s.BasCtrl.updateBasicBBSReadcount(seq, readCount);
		
		BasicBBSDto b_dto = new BasicBBSDto(list.get(rowNum).getSeq(), list.get(rowNum).getTitle(), list.get(rowNum).getContent(), 
				list.get(rowNum).getName(), list.get(rowNum).getWdate(), list.get(rowNum).getReadcount(), list.get(rowNum).getDel(), 
				list.get(rowNum).getFamily(), list.get(rowNum).getParent(), list.get(rowNum).getDepth(), list.get(rowNum).getIndent());
		s.BasCtrl.BasicBBSDetailView(m_dto, b_dto);
		this.dispose();

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

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
