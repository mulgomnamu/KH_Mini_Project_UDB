package view;

import java.util.*;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import dto.MemberDto;
import dto.VoteDto;
import service.DateLabelFormatter;
import singleton.Singleton;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.applet.Applet;

public class voteView extends JFrame implements ActionListener, MouseListener{

	JButton loginBtn = new JButton("login");
	JButton logoutBtn = new JButton("logout");

	JButton voteAddBtn;
	JButton voteResultBtn;
	
	//###########################################################
	//투표의 mainview를 보여주는 부분 투표목록을 보여줌
	//#######################################################3
	
	JTable jtable;
	JScrollPane jScrPane;
	String name;
	String columnNames[] = {
			"번호", "투표안건", "투표시작일", "투표종료일", "참여자 수"	
		};

	 JButton menubtn1,menubtn2,menubtn3,menubtn4,menubtn5;
	 JButton menutextbtn1,menutextbtn2,menutextbtn3,menutextbtn4,menutextbtn5;
	   
	 JButton mainbtn;
	 JButton loginbtn;
	 JButton joinbtn;
	 JButton btn_logOut;
	   
	Object rowData[][];
	
	public int rowNum;
	public int insertNum;
	public int seqNum=0;
	public int parentSeq=0;
	public int adminSeq=0;
	int del;
	int auth=0;
	int seq;
	boolean deleteFlag = false;
	int listlength;
	MemberDto memdto;
	JButton boardClick;
	int result =0;
	
	List<VoteDto> list = new ArrayList<VoteDto>();
	
	public voteView(MemberDto memdto, List<VoteDto> list) {
		this.name = memdto.getName();
		this.memdto = memdto;
		this.list = list;
		
		Label label3 = new Label(name + " 님 환영합니다");  
		label3.setBounds(800, 15, 200, 25);
		label3.setBackground(Color.WHITE);
		label3.setFont(new Font("윤고딕", Font.BOLD, 15));
		
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
		
		ImageIcon logoimg = new ImageIcon("img/logo1.jpg");  //이미지 경로		
		JLabel imagelabel = new JLabel("",logoimg, JLabel.CENTER);
		imagelabel.setBounds(15, 10, 190, 190);
		add(imagelabel);
		
		mainbtn = new JButton(new ImageIcon("img/logo1.png")); 
		mainbtn.setBounds(15, 10, 190, 190);
		mainbtn.setBorderPainted(false); 	// 경계선 안보이게
		mainbtn.setFocusPainted(false); 	// 버튼 선택되었을때 생기는 테두리 안보이게
		mainbtn.setContentAreaFilled(false); // 자바 기본버튼 컬러 안보이게
		mainbtn.addActionListener(this); 
//		mainbtn.setOpaque(false); // 투명화 
		add(mainbtn);


//		UtilDateModel model = new UtilDateModel();
//		//model.setDate(20,04,2014);
//		// Need this...
//		Properties p = new Properties();
//		p.put("text.today", "Today");
//		p.put("text.month", "Month");
//		p.put("text.year", "Year");
//		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
//		// Don't know about the formatter, but there it is...
//		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
//		add(datePicker).setBounds(1, 1, 200, 30);
//		
//		Date selectedDate = (Date) datePicker.getModel().getValue();
//		System.out.println("firstdate :" + selectedDate);
//		
////		Calendar selectedValue = (Calendar) datePicker.getModel().getValue();
////		Date selectedDatez = selectedValue.getTime();
////		System.out.println("seconddate :" + selectedDatez);
		
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
		
		if(memdto.getName().equals("guest")) {
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
		
		rowData = new Object[list.size()][5];
		for (int i = 0; i < list.size(); i++) {
			rowData[i][0] = i+1;
			rowData[i][1] = list.get(i).getVotecontent();
			rowData[i][2] = list.get(i).getVotestart();
			rowData[i][3] = list.get(i).getVoteend();
			rowData[i][4] = list.get(i).getVotecount();
			
			
		}
		
		
		
		jtable = new JTable(rowData, columnNames);
//	    jScrPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); // 보더 투명
	    
		jtable = new JTable(rowData, columnNames);
	    jtable.setBackground(Color.white); // 셀 배경색 변경
	    jtable.setForeground(Color.black); // 셀 글자색 변경
	    jtable.setGridColor(new Color(217, 217, 217)); // 셀 테두리
//	    jtable.setShowHorizontalLines(false); // 셀 세로줄 
	    jtable.setShowVerticalLines(false); // 셀 가로줄 
//	    jtable.setTableHeader(null); // 셀 헤더
//	    jtable.setBorder(new EmptyBorder(2,2,2,2));  
	    jtable.setRowHeight(45);	// 셀 넓이 
//	    jtable.setAutoCreateColumnsFromModel(false); // 제어못하게
	    jtable.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 17));
//	    jtable.setFocusable(false);
	    
		jScrPane = new JScrollPane(jtable);
		jScrPane.getViewport().setBackground(Color.white); 
		add(jScrPane).setBounds(180, 240, 800, 300);
	    jtable.addMouseListener(this);	      
		
	    DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
	    celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
	    
	    jtable.getColumn("번호").setPreferredWidth(10);
	    jtable.getColumn("번호").setCellRenderer(celAlignCenter);
	    jtable.getColumn("투표안건").setPreferredWidth(380);
	    jtable.getColumn("투표안건").setCellRenderer(celAlignCenter);
	    jtable.getColumn("투표시작일").setPreferredWidth(80);
	    jtable.getColumn("투표시작일").setCellRenderer(celAlignCenter);
	    jtable.getColumn("투표종료일").setPreferredWidth(80);
	    jtable.getColumn("투표종료일").setCellRenderer(celAlignCenter);
	    jtable.getColumn("참여자 수").setPreferredWidth(30);
	    jtable.getColumn("참여자 수").setCellRenderer(celAlignCenter);

		if(memdto.getAuth() == 2) {
//			해상도 문제 때문에 집컴에 맞춰 버튼 다른 곳으로 옮김.
//			voteAddBtn = new JButton("voteAdd");
//			voteAddBtn.addActionListener(this);
//			add(voteAddBtn).setBounds(855, 755, 100, 50);
			
			voteAddBtn = new JButton("voteAdd");
			voteAddBtn.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 15));
			voteAddBtn.addActionListener(this);
			add(voteAddBtn).setBounds(770, 550, 100, 50);

		}	
		voteResultBtn = new JButton("voteResult");
		voteResultBtn.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 15));
		voteResultBtn.addActionListener(this);
		add(voteResultBtn).setBounds(880, 550, 100, 50);
	
		setLayout(null);
		setBounds(100, 0, 1150, 850);
	    setVisible(true);
	     
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    getContentPane().setBackground(Color.WHITE);	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		String str = btn.getLabel(); 
		Singleton s = Singleton.getInstance();
		
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
        if (btn == voteAddBtn) {     
        	s.voteCtrl.addViewVote();
    //		this.dispose();
        }
        if (btn == voteResultBtn) {     
        	 s.voteCtrl.voteResult();
    //		this.dispose();
        }
		
     
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int rowNum = jtable.getSelectedRow();   
	      seq = list.get(rowNum).getSeq();
	      String content = list.get(rowNum).getVotecontent();
	      Singleton sc = Singleton.getInstance();
	      boolean FindId = sc.voteCtrl.voteMenberFind(memdto.getId(), seq);
	      
	      if(memdto.getName().equals("guest")) {
	         JOptionPane.showMessageDialog(null, "로그인을 필요로 합니다.");
	         
	      }else {
	         //String test = JOptionPane.showInputDialog(null, "기본 입력창입니다.");
	         //System.out.println(test);
	         String[] buttons = {"찬성", "반대", "기권"};
	         //   0      1      2
	         //JOptionPane.showMessageDialog(null, FindId);
	         if(FindId == false) {
	            result = JOptionPane.showOptionDialog(null,  content +" \n투표는 수정할 수 없습니다. ", "투표", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "첫번째값");
	            if(!(result  == -1)) {
	               sc.voteCtrl.voteChoice(seq, result);
	               sc.voteCtrl.voteMemberCheck(seq, memdto.getId());   
	            }
	         }else if(FindId == true) {
	            sc.voteCtrl.voteGraph(seq);
	            //JOptionPane.showMessageDialog(null, "이미 투표하셨습니다.");
	         }
	         
	         
	         
	         //this.dispose();
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
