package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;

import chatting.MainChat;
import dto.BasicBBSDto;
import dto.MarketDto;
import dto.MemberDto;
import dto.NoticeBoardDto;
import dto.calendarDto;
import singleton.Singleton;

public class mainView extends JFrame implements ActionListener,MouseListener {

	   JTable jtable;
	   JScrollPane jScrPane;
	   String name;
	   String columnNames[] = {"번호", "coffee", "size" };
	   
	   JTable jtable2;
	   JScrollPane jScrPane2;
	   String name2;
	   String columnNames2[] = {"번호", "제목", "글쓴이" };
	   
	   JButton imgbutton;	   
	   JButton mainbtn;
	   JButton menubtn1,menubtn2,menubtn3,menubtn4,menubtn5;
	   JButton menutextbtn1,menutextbtn2,menutextbtn3,menutextbtn4,menutextbtn5;
	   JButton loginbtn;
	   JButton joinbtn;	   
	   JButton logoutBtn;
	   JButton btn_logOut;

	   Object rowData[][];
	   public int rowNum;
	   Object rowData2[][];
	   public int rowNum2;
	   public int insertNum;
	   public int seqNum=0;
	   public int seqNum2=0;
	   public int parentSeq=0;
	   public int adminSeq=0;
	   int del;
	   int auth=0;
	   String id;
	   int seq;
	   boolean deleteFlag = false;
	   int listlength;

	   MemberDto memdto;
	   List<NoticeBoardDto> noticelist = new ArrayList<NoticeBoardDto>();
	   List<BasicBBSDto> getNewestlist = new ArrayList<BasicBBSDto>();
	   
	public mainView(MemberDto memdto) {
		setLayout(null);
		Singleton s = Singleton.getInstance();
		noticelist = s.noticeCtrl.getNoticeBoard();
//		this.caldto = caldto;
		this.memdto = memdto;
		//name 가져오기
		name = memdto.getName();
		
		System.out.println(memdto.getAuth());
		
        Color sepaColor = new Color(217, 217, 217);
        JSeparator separator = new JSeparator();
        separator.setBounds(162, 200, 830, 1);
        separator.setForeground(sepaColor);
        getContentPane().add(separator);
		
		mainbtn = new JButton(new ImageIcon("img/logo1.jpg")); // 로고 btn
		mainbtn.setBounds(15, 10, 190, 190);
		mainbtn.setBorderPainted(false); 	// 경계선 안보이게
		mainbtn.setFocusPainted(false); 	// 버튼 선택되었을때 생기는 테두리 안보이게
		mainbtn.setContentAreaFilled(false); // 자바 기본버튼 컬러 안보이게
		mainbtn.addActionListener(this); 
//		mainbtn.setOpaque(false); // 투명화 
		add(mainbtn);

		ImageIcon mainimg = new ImageIcon("img/mainmosion.gif");  // 메인 큰 이미지
		JLabel imagelabel2 = new JLabel("",mainimg, JLabel.CENTER);
		imagelabel2.setBounds(130, 210, 900, 250);
		add(imagelabel2);


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
		
		loginbtn = new JButton("login");
		loginbtn.setBounds(880, 10, 80, 30);
		loginbtn.setBorderPainted(false); 	// 경계선 안보이게
		loginbtn.setFocusPainted(false); 	// 버튼 선택되었을때 생기는 테두리 안보이게
		loginbtn.setContentAreaFilled(false); // 자바 기본버튼 컬러 안보이게
		loginbtn.addActionListener(this); 
		loginbtn.setFont(new Font("윤고딕", Font.BOLD, 15));
		
		joinbtn = new JButton(" join");
		joinbtn.setBounds(930, 10, 80, 30);
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
		
		Label label1 = new Label(memdto.getName() + " 님 환영합니다");  
		label1.setBounds(800, 15, 200, 25);
		label1.setBackground(Color.WHITE);
		label1.setFont(new Font("윤고딕", Font.BOLD, 15));
		
		if(memdto.getName().equals("guest")) {
			add(loginbtn);
			add(joinbtn);
		}else {
			add(btn_logOut);
		}
		
		JLabel label = new JLabel("ㅡ 입주자분들께 알림 ㅡ");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("아리따-돋움(TTF)-SemiBold", Font.PLAIN, 20));
		label.setBounds(75, 480, 240, 45);
		add(label);
		
		JLabel label11 = new JLabel("ㅡ 최근 게시물 ㅡ");
		label11.setHorizontalAlignment(SwingConstants.CENTER);
		label11.setFont(new Font("아리따-돋움(TTF)-SemiBold", Font.PLAIN, 20));
		label11.setBounds(550, 480, 240, 45);
		add(label11);
		
		Label label3 = new Label(memdto.getName() + " 님 환영합니다");  
		label3.setBounds(730, 15, 200, 25);
		label3.setBackground(Color.WHITE);
		label3.setFont(new Font("윤고딕", Font.BOLD, 15));
		add(label3);
		
/*		// 테두리 만들기
 		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new LineBorder(Color.black));
		panel.setBounds(90, 260, 400, 520);
		getContentPane().add(panel);
		panel.setLayout(null);
		*/
	
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
		btn_logOut.addMouseListener(new MouseAdapter(){ 
	        public void mouseEntered(MouseEvent e){ 
	        	btn_logOut.setForeground(Color.blue);
	         } 
	         public void mouseExited(MouseEvent e){ 
	        	 btn_logOut.setForeground(Color.black);
	          } 
	       }); 

	      rowData = new Object[noticelist.size()][3];
	      //id = list.get(0).getId();
	      for (int i = 0; i < noticelist.size(); i++) {
	         //listlength = list.size();
	         rowData[i][0] = noticelist.get(i).getSeq();
	         rowData[i][1] = noticelist.get(i).getTitle();
	         String from = noticelist.get(i).getWdate();
	         SimpleDateFormat  formatter04 = new SimpleDateFormat("yyyy-MM-dd");
	 		try {
				//date = formatter04.parse(from);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	         rowData[i][2] = from;
	   

	         if(rowData[i][0] != "-") {
	            seqNum = seqNum+1;
	            rowData[i][0] = seqNum;
	         }
	         
	      }
	      
		 jtable = new JTable(rowData, columnNames);
   
	     jScrPane = new JScrollPane(jtable);
	     jScrPane.getViewport().setBackground(Color.white); // 바닥
	     jScrPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); // 보더 투명
	     
//	     cpnt.setSelectionBackground(new EmptyBorder(2, 2, 2, 2));
	     jtable.setBackground(Color.white); // 셀 배경색 변경
	     jtable.setForeground(new Color(70, 70, 70)); // 셀 글자색 변경
	     jtable.setGridColor(Color.black); // 셀 테두리
//	     jtable.setShowHorizontalLines(false); // 셀 세로줄 
	     jtable.setShowVerticalLines(false); // 셀 가로줄 
	     jtable.setTableHeader(null); // 셀 헤더
//	     jtable.setBorder(new EmptyBorder(2,2,2,2));  
	     jtable.setRowHeight(45);	// 셀 넓이 
	     jtable.setFont(new Font("나눔고딕", Font.BOLD, 15));
	     jtable.addMouseListener(this);	   
	      
	      DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
	      celAlignCenter.setHorizontalAlignment(JLabel.CENTER);

	      jtable.getColumn("번호").setPreferredWidth(5);
	      jtable.getColumn("번호").setCellRenderer(celAlignCenter);
	      jtable.getColumn("coffee").setPreferredWidth(850);
	      jtable.getColumn("coffee").setCellRenderer(celAlignCenter);
	      jtable.getColumn("size").setPreferredWidth(450);
	      jtable.getColumn("size").setCellRenderer(celAlignCenter);

	      
	      getNewestlist = s.BasCtrl.getNewestList();
	      rowData2 = new Object[getNewestlist.size()][3];
	      //id = list.get(0).getId();
	      for (int i = 0; i < getNewestlist.size() ; i++) {
	         //listlength = list.size();
	         rowData2[i][0] = getNewestlist.get(i).getSeq();
	         rowData2[i][1] = getNewestlist.get(i).getTitle();
	         rowData2[i][2] = getNewestlist.get(i).getWdate();

	        
	         if(rowData2[i][0] != "-") {
	            seqNum2 = seqNum2+1;
	            rowData2[i][0] = seqNum2;
	         }
	         
	      }
	      
		 jtable2 = new JTable(rowData2, columnNames2);
   
	     jScrPane2 = new JScrollPane(jtable2);
	     jScrPane2.getViewport().setBackground(Color.white); // 바닥
	     jScrPane2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); // 보더 투명
	     
//	     cpnt.setSelectionBackground(new EmptyBorder(2, 2, 2, 2));
	     jtable2.setBackground(Color.white); // 셀 배경색 변경
	     jtable2.setForeground(new Color(70, 70, 70)); // 셀 글자색 변경
	     jtable2.setGridColor(Color.black); // 셀 테두리
//	     jtable.setShowHorizontalLines(false); // 셀 세로줄 
	     jtable2.setShowVerticalLines(false); // 셀 가로줄 
	     jtable2.setTableHeader(null); // 셀 헤더
//	     jtable.setBorder(new EmptyBorder(2,2,2,2));  
	     jtable2.setRowHeight(45);	// 셀 넓이 
	     jtable2.setFont(new Font("아리따-돋움(TTF)-SemiBold", Font.PLAIN, 15));
	     jtable2.addMouseListener(this);	   
	      
	      DefaultTableCellRenderer celAlignCenter2 = new DefaultTableCellRenderer();
	      celAlignCenter2.setHorizontalAlignment(JLabel.CENTER);

	      jtable2.getColumn("번호").setPreferredWidth(5);
	      jtable2.getColumn("번호").setCellRenderer(celAlignCenter2);
	      jtable2.getColumn("제목").setPreferredWidth(850);
	      jtable2.getColumn("제목").setCellRenderer(celAlignCenter2);
	      jtable2.getColumn("글쓴이").setPreferredWidth(450);
	      jtable2.getColumn("글쓴이").setCellRenderer(celAlignCenter2);

	      jtable2.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 16));
	      jtable2.setBounds(200, 200, 420, 490);
	      add(jScrPane2).setBounds(580 , 520, 420, 500);
	//      add(panel).setBounds(80, 255, 380, 500); // 공지사항 게시판 사이즈

	      jtable.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 16));
	      jtable.setBounds(15, 5, 420, 490);
	      add(jScrPane).setBounds(80, 520, 420, 500);
	//      add(panel).setBounds(80, 255, 380, 500); // 공지사항 게시판 사이즈
	      
	      setBounds(100, 0, 1150, 850);
	      setVisible(true);
	      setDefaultCloseOperation(EXIT_ON_CLOSE);
	      getContentPane().setBackground(Color.WHITE);	
//	      getContentPane().setLocation(50, 50);//화면의 어느위치에 둘것인가
		

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton btn = (JButton)e.getSource();
		Singleton s = Singleton.getInstance();
		String str = btn.getLabel();
		
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
			new mainView(memdto);
			this.dispose();
		}
		
		if(btn == menutextbtn1) {
			s.BasCtrl.BasicBBSView(memdto);
			this.dispose();
		}
		
        if (btn == menutextbtn2) {     
        	s.voteCtrl.voteList(memdto);
        	s.voteCtrl.voteRenewal();
    		this.dispose();
        }
        
        if (btn == menutextbtn3) {     
        	s.marketCtrl.getBbsList(memdto);
        	this.dispose();
        }
        
        if (btn == menutextbtn4) { 
        	//s.revCtrl.getRevList(caldto);
        	SwingUtilities.invokeLater(new Runnable(){
                public void run(){
                   new MemoCalendar(memdto);
                }           
             });
            this.dispose();
        }
		
        if (btn == menutextbtn5) {
            if(memdto.getAuth() == 0) {
               return;
            }
            s.chatCtrl.mainChat(memdto.getName(), memdto.getAuth());
        }
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
Singleton sc = Singleton.getInstance();
		
		JTable jBtn = (JTable)e.getSource();				
		if(jBtn == jtable){
			// 빈칸 첵크 생략
			int rowNum = jtable.getSelectedRow(); 
			seq = noticelist.get(rowNum).getSeq();
			sc.noticeCtrl.NoticeDetail(seq);
		}else if(jBtn == jtable2) {
			int rowNum2 = jtable2.getSelectedRow();
			int seq2 = getNewestlist.get(rowNum2).getSeq();
			
			//int seq = getNewestlist.get(rowNum2).getSeq();
			int readCount = getNewestlist.get(rowNum2).getReadcount();
			
			if(getNewestlist.get(rowNum2).getDel() == 1) {
				return;
			}
			
			sc.BasCtrl.updateBasicBBSReadcount(seq, readCount);
			
			BasicBBSDto b_dto = new BasicBBSDto(getNewestlist.get(rowNum2).getSeq(), getNewestlist.get(rowNum2).getTitle(), getNewestlist.get(rowNum2).getContent(), 
					getNewestlist.get(rowNum2).getName(), getNewestlist.get(rowNum2).getWdate(), getNewestlist.get(rowNum2).getReadcount(), getNewestlist.get(rowNum2).getDel(), 
					getNewestlist.get(rowNum2).getFamily(), getNewestlist.get(rowNum2).getParent(), getNewestlist.get(rowNum2).getDepth(), getNewestlist.get(rowNum2).getIndent());
			sc.BasCtrl.BasicBBSDetailView(memdto, b_dto);
			this.dispose();
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
