package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dto.MarketDto;
import dto.MarketReplyDto;
import dto.MemberDto;
import singleton.Singleton;

public class MarketBbsView extends JFrame implements ActionListener {

	private JButton loginbtn;
	private JButton joinbtn;	   
	private JButton logoutBtn;
	private JButton writeBtn;
	
	private JButton mainbtn;
	private JButton menubtn1,menubtn2,menubtn3,menubtn4,menubtn5;
	private JButton menutextbtn1,menutextbtn2,menutextbtn3,menutextbtn4,menutextbtn5;
	
	private ImageIcon img[];
	private ImageIcon resizeImg[];
	private Image orginalImg[];
	private Image resize[];
	private Icon icon;
	private JLabel imagelabel[];
	private JLabel titlelabel[], categorylabel[], pricelabel[];
	private JPanel marketListpanel[];
	private JPanel marketInfopanel[];
	private Container backCt[];
	private Container lastCt;
	
	private int x = 15, y = 50;
	private int xSize = 180, ySize = 170;
	
	private JButton btn[];
	
	private JTextField selectField;
	private JButton pageBtn[];
	
	private List<MarketDto> list;
	private String loginName;
	private String searchChoice = "TITLE";
	private JTextField searchText = new JTextField();
	private JButton searchBtn = new JButton(new ImageIcon("img/selectbtn.png"));
	
	private int curPage;
	
	private MemberDto dto;
	
	public MarketBbsView(List<MarketDto> list, MemberDto dto) {
		super("게시판");
		
		this.list = list;
		this.dto = dto;
		setLayout(null);
		
		Label label3 = new Label(dto.getName() + " 님 환영합니다");  
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
		
		logoutBtn = new JButton(" logout");
		logoutBtn.setBounds(1000, 10, 120, 30);
		logoutBtn.setBorderPainted(false); 	// 경계선 안보이게
		logoutBtn.setFocusPainted(false); 	// 버튼 선택되었을때 생기는 테두리 안보이게
		logoutBtn.setContentAreaFilled(false); // 자바 기본버튼 컬러 안보이게
		logoutBtn.addActionListener(this); 
		logoutBtn.setFont(new Font("윤고딕", Font.BOLD, 15));
		
		if(dto.getName().equals("guest")) {
			add(loginbtn);
			add(joinbtn);
		}else {
			add(logoutBtn);
			add(label3);
		}
		
		
		mainbtn = new JButton(new ImageIcon("img/logo1.jpg")); // 로고 btn
		mainbtn.setBounds(15, 10, 190, 190);
		mainbtn.setBorderPainted(false); 	// 경계선 안보이게
		mainbtn.setFocusPainted(false); 	// 버튼 선택되었을때 생기는 테두리 안보이게
		mainbtn.setContentAreaFilled(false); // 자바 기본버튼 컬러 안보이게
		mainbtn.addActionListener(this); 
//		mainbtn.setOpaque(false); // 투명화 
		add(mainbtn);
		
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
	

		
			if (list.size()/8 == 0 || list.size() == 0 || list.size() == 8) {
				curPage = 1;
				System.out.println(curPage);
			} else if(list.size()/8 == 1 || list.size() == 16) {
				curPage = 2;
				System.out.println(curPage);
			} else if(list.size()/8 == 2 || list.size() == 24) {
				curPage = 3;
				System.out.println(curPage);
			} else if(list.size()/8 == 3 || list.size() == 32) {
				curPage = 4;
				System.out.println(curPage);
			}
			
			pageBtn = new JButton[curPage];
			btn = new JButton[32];
			img = new ImageIcon[list.size()];
		    titlelabel = new JLabel[list.size()];
		    marketListpanel = new JPanel[list.size()];
		    marketInfopanel = new JPanel[list.size()];
		    backCt = new Container[32];
			imagelabel = new JLabel[list.size()];
		    categorylabel = new JLabel[list.size()];
		    pricelabel = new JLabel[list.size()];
		    orginalImg = new Image[list.size()];
		    resize = new Image[list.size()];
		    resizeImg = new ImageIcon[list.size()];
		    
	    	lastCt = getContentPane();
	    	lastCt = new Container();
		    
		    for (int i = 0; i < backCt.length; i++) {
			    // 2개의 panel을 넣을 container 생성후 삽입
			    backCt[i] = getContentPane();
			    backCt[i] = new Container();

			    btn[i] = new JButton(i+"버튼");
			    btn[i].setBorderPainted(false);
			    btn[i].setContentAreaFilled(false);
			    btn[i].setFocusPainted(false);
			    btn[i].addActionListener(this);
			}
		    
			for (int i = 0; i < pageBtn.length; i++) {
				pageBtn[i] = new JButton(i+1+"");
				
				if (i == 0) {
					pageBtn[0].setBounds(550, 640, 45, 35);
				} else if (i == 1) {
					pageBtn[0].setBounds(525, 640, 45, 35);
					pageBtn[1].setBounds(575, 640, 45, 35);
				} else if (i == 2) {
					pageBtn[0].setBounds(525, 640, 45, 35);
					pageBtn[1].setBounds(550, 640, 45, 35);
					pageBtn[2].setBounds(575, 640, 45, 35);
				} else if (i == 3) {
					pageBtn[0].setBounds(480, 640, 45, 35);
					pageBtn[1].setBounds(530, 640, 45, 35);
					pageBtn[2].setBounds(580, 640, 45, 35);
					pageBtn[3].setBounds(630, 640, 45, 35);
				}
				add(pageBtn[i]);
				Map map = pageBtn[i].getFont().getAttributes();
				map.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
				Font font = new Font(map);
				pageBtn[i].setBorderPainted(false); 	// 경계선 안보이게
				pageBtn[i].setFocusPainted(false); 	// 버튼 선택되었을때 생기는 테두리 안보이게
				pageBtn[i].setFont(font);
				pageBtn[i].setContentAreaFilled(false);
				pageBtn[i].addActionListener(this);
			}
		    
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).getSold() == 1) {
					//img[i] = new ImageIcon("C:\\\\images\\\\soldout.jpg");
					img[i] = new ImageIcon("\\\\\\\\192.168.10.38\\\\Users\\\\user2\\\\images\\\\soldout.jpg");
				    
				    orginalImg[i] = img[i].getImage();  //ImageIcon을 Image로 변환.
		            resize[i] = orginalImg[i].getScaledInstance(180, 110, Image.SCALE_SMOOTH);

		            resizeImg[i] = new ImageIcon(resize[i]); //Image로 ImageIcon 생성
		            imagelabel[i] = new JLabel(resizeImg[i]);
		             
		            imagelabel[i].setBounds(0, 0, 180, 110);

		            titlelabel[i] = new JLabel("", JLabel.CENTER);
		            categorylabel[i] = new JLabel("판매완료", JLabel.CENTER);
		            categorylabel[i].setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 16));
		            pricelabel[i] = new JLabel("", JLabel.CENTER);
				}
				
				// 이미지 삽입
				if (list.get(i).getDel() == 1) {
					//img[i] = new ImageIcon("C:\\\\images\\\\deleteimg.jpg");
					img[i] = new ImageIcon("\\\\\\\\192.168.10.38\\\\Users\\\\user2\\\\images\\\\deleteimg.jpg");
				    
				    orginalImg[i] = img[i].getImage();  //ImageIcon을 Image로 변환.
		            resize[i] = orginalImg[i].getScaledInstance(180, 110, Image.SCALE_SMOOTH);

		            resizeImg[i] = new ImageIcon(resize[i]); //Image로 ImageIcon 생성
		            imagelabel[i] = new JLabel(resizeImg[i]);
		            
				    imagelabel[i].setBounds(0, 0, 180, 110);
				    
				    titlelabel[i] = new JLabel("", JLabel.CENTER);
				    categorylabel[i] = new JLabel("이글은 삭제되었습니다.", JLabel.CENTER);
				    categorylabel[i].setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 16));
				    pricelabel[i] = new JLabel("", JLabel.CENTER);
				}
				if(list.get(i).getDel() == 0 && list.get(i).getSold() == 0) {
					img[i] = new ImageIcon(list.get(i).getImagesrc());
				    
				    orginalImg[i] = img[i].getImage();  //ImageIcon을 Image로 변환.
		            resize[i] = orginalImg[i].getScaledInstance(180, 110, Image.SCALE_SMOOTH);

		            resizeImg[i] = new ImageIcon(resize[i]); //Image로 ImageIcon 생성
		            imagelabel[i] = new JLabel(resizeImg[i]);

				    imagelabel[i].setBounds(0, 0, 180, 110);
				    
				    // 정보를 label생성 후에 삽입
				    titlelabel[i] = new JLabel(list.get(i).getTitle(), JLabel.CENTER);
				    titlelabel[i].setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 16));
				    categorylabel[i] = new JLabel(list.get(i).getCategory(), JLabel.CENTER);
				    categorylabel[i].setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 16));
				    pricelabel[i] = new JLabel(list.get(i).getPrice()+"", JLabel.CENTER);
				    pricelabel[i].setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 16));
				} 
				
			    // 이미지 넣을 panel 생성
			    marketListpanel[i] = new JPanel();
			    marketListpanel[i].setBackground(Color.lightGray);
			    marketListpanel[i].setBounds(0, 0, 180, 110);
			    marketListpanel[i].setLayout(null);
			    
			    // 정보를 넣을 panel 생성
			    Color panelCl = new Color(242, 242, 242);
			    marketInfopanel[i] = new JPanel();
			    marketInfopanel[i] = new JPanel(new GridLayout(3, 1));
			    marketInfopanel[i].setBackground(panelCl);
			    marketInfopanel[i].setBounds(0, 110, 180, 60);
			    
			    // 각 panel에 정보 삽입
			    marketListpanel[i].add(imagelabel[i]);
			    marketInfopanel[i].add(titlelabel[i]);
			    marketInfopanel[i].add(categorylabel[i]);
			    marketInfopanel[i].add(pricelabel[i]);

//				if (list.size()/8 == 0 || list.size() == 0) {
//					curPage = 1;
//					System.out.println(curPage);
//				} else if(list.size()/8 == 1 || list.size() == 16) {
//					curPage = 2;
//					System.out.println(curPage);
//				} else if(list.size()/8 == 2 || list.size() == 24) {
//					curPage = 3;
//					System.out.println(curPage);
//				} else if(list.size()/8 == 3 || list.size() == 32) {
//					curPage = 4;
//					System.out.println(curPage);
//				}
			    
			    //backCt[i].setBounds(5, 10, 180, 200);
			    backCt[i].setBackground(Color.lightGray);
			    backCt[i].add(marketListpanel[i]);
			    backCt[i].add(marketInfopanel[i]);
			    backCt[i].add(btn[i]);
			    //add(backCt[i]);
			    //add(bgPanel[1]);
			    lastCt.setBounds(180, 200, 800, 550);
		    	for (int j = 0; j < 8; j++) {
					lastCt.add(backCt[j]);
				}
				//lastCt.add(backCt[i]);
				add(lastCt);
			}
		
	    //============================================================================================================
		
		
		backCt[0].setBounds(x, y, xSize, ySize);
		backCt[1].setBounds(x + 190, y, xSize, ySize);
		backCt[2].setBounds(x + 380, y, xSize, ySize);
		backCt[3].setBounds(x + 570, y, xSize, ySize);
		
		backCt[4].setBounds(x, y + 200, xSize, ySize);
		backCt[5].setBounds(x + 190, y + 200, xSize, ySize);
		backCt[6].setBounds(x + 380, y + 200, xSize, ySize);
		backCt[7].setBounds(x + 570, y + 200, xSize, ySize);

		backCt[8].setBounds(x, y, xSize, ySize);
		backCt[9].setBounds(x + 190, y, xSize, ySize);
		backCt[10].setBounds(x + 380, y, xSize, ySize);
		backCt[11].setBounds(x + 570, y, xSize, ySize);
		
		backCt[12].setBounds(x, y + 200, xSize, ySize);
		backCt[13].setBounds(x + 190, y + 200, xSize, ySize);
		backCt[14].setBounds(x + 380, y + 200, xSize, ySize);
		backCt[15].setBounds(x + 570, y + 200, xSize, ySize);

		backCt[16].setBounds(x, y, xSize, ySize);
		backCt[17].setBounds(x + 190, y, xSize, ySize);
		backCt[18].setBounds(x + 380, y, xSize, ySize);
		backCt[19].setBounds(x + 570, y, xSize, ySize);
		
		backCt[20].setBounds(x, y + 200, xSize, ySize);
		backCt[21].setBounds(x + 190, y + 200, xSize, ySize);
		backCt[22].setBounds(x + 380, y + 200, xSize, ySize);
		backCt[23].setBounds(x + 570, y + 200, xSize, ySize);

		backCt[24].setBounds(x, y, xSize, ySize);
		backCt[25].setBounds(x + 190, y, xSize, ySize);
		backCt[26].setBounds(x + 380, y, xSize, ySize);
		backCt[27].setBounds(x + 570, y, xSize, ySize);
		
		backCt[28].setBounds(x, y + 200, xSize, ySize);
		backCt[29].setBounds(x + 190, y + 200, xSize, ySize);
		backCt[30].setBounds(x + 380, y + 200, xSize, ySize);
		backCt[31].setBounds(x + 570, y + 200, xSize, ySize);

	    // 각 btn 생성밑 삽입
		for (int j = 0; j < btn.length; j++) {
			btn[j].setBounds(0, 0, xSize, ySize);
		}
		
		if (dto.getAuth() == 1 || dto.getAuth() == 2) {
			ImageIcon writebtn = new ImageIcon("img/writebtn.png");
			writeBtn = new JButton("글쓰기",writebtn);
			writeBtn.addActionListener(this);
			writeBtn.setBounds(820, 685, 120, 38);
			writeBtn.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 16));
			writeBtn.setContentAreaFilled(false);
			writeBtn.setFocusPainted(false);
			writeBtn.setOpaque(false);
			add(writeBtn);
		}
		
		final String labels[] = {"제품명", "판매자", "카테고리"};
	    JFrame frame = new JFrame("Editable JComboBox");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    final JComboBox comboBox = new JComboBox(labels);
	    comboBox.setMaximumRowCount(3);
	    comboBox.setEditable(true);
	    comboBox.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 14));
	    add(comboBox).setBounds(270, 700, 100, 25);
	   // frame.add(comboBox, BorderLayout.NORTH);

	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	    	  searchChoice = (String) comboBox.getSelectedItem();
	    	  if(searchChoice.equals("제품명")) {
	    		  searchChoice = "TITLE";
	    		  System.out.println(searchChoice);
	    	  }else if(searchChoice.equals("판매자")) {
	    		  searchChoice = "NAME";
	    		  System.out.println(searchChoice);
	    	  }else if(searchChoice.equals("카테고리")) {
	    		  searchChoice = "CATEGORY";
	    		  System.out.println(searchChoice);
	    	  }
	      }
	    };
	    comboBox.addActionListener(actionListener);
	    
	    add(searchText).setBounds(380, 700, 300, 25);
	    add(searchBtn).setBounds(680, 695, 80, 35);
	    searchBtn.setFont(new Font(null, Font.PLAIN, 15));
	    searchBtn.addActionListener(this);
	    searchBtn.setBorderPainted(false); 	// 경계선 안보이게
	    searchBtn.setFocusPainted(false); 	// 버튼 선택되었을때 생기는 테두리 안보이게
	    searchBtn.setContentAreaFilled(false); // 자바 기본버튼 컬러 안보이게
	    searchBtn.addActionListener(this);
		
		// 검색
//		selectField = new JTextField();
//		selectField.setBounds(200, 380, 150, 20);
//		add(selectField);
		
//		selectBtn = new JButton("검색");
//		selectBtn.addActionListener(this);
//		selectBtn.setBounds(370, 380, 100, 20);		
//		add(selectBtn);
		
	    setBounds(100, 0, 1150, 850);
	    getContentPane().setBackground(Color.WHITE);	
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
		
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Singleton sc = Singleton.getInstance();
		Object obj = e.getSource();
		JButton bt = (JButton) e.getSource();

		if(obj == loginbtn){
			new loginView();
			this.dispose();
		}
			
		if(obj == joinbtn){
			sc.memCtrl.regi();	
			this.dispose();
		}
		
		if(obj == logoutBtn) {
			new mainView(new MemberDto("", "", "guest", "", "", 0));
			this.dispose();
		}
		
		if(obj == mainbtn) {
			new mainView(dto);
			this.dispose();
		}
		
		if(obj == menutextbtn1) {
			sc.BasCtrl.BasicBBSView(dto);
			this.dispose();
		}
		
        if (obj == menutextbtn2) {     
        	sc.voteCtrl.voteList(dto);
        	sc.voteCtrl.voteRenewal();
    		this.dispose();
        }
        
        if (obj == menutextbtn3) {     
        	sc.marketCtrl.getBbsList(dto);
        	this.dispose();
        }
        
        if (obj == menutextbtn4) { 
        	//s.revCtrl.getRevList(caldto);
        	SwingUtilities.invokeLater(new Runnable(){
                public void run(){
                   new MemoCalendar(dto);
                }           
             });
            this.dispose();
        }
		
        if (obj == menutextbtn5) {
            if(dto.getAuth() == 0) {
               return;
            }
            sc.chatCtrl.mainChat(dto.getName(), dto.getAuth());
        }else if(obj == mainbtn){
			new mainView(dto);
			this.dispose();
		}else if(obj == writeBtn){
			sc.marketCtrl.bbsWrite(dto);
			
		}else if(obj == searchBtn){
			String searchTitle = searchChoice;
			String searchContent = searchText.getText();
			sc.marketCtrl.bbsSelect(searchTitle, searchContent);
			this.dispose();
		}
		
		for (int i = 0; i < btn.length; i++) {
			if (bt.getText().equals(i+"버튼")) {
				if (list.get(i).getDel() == 0 && list.get(i).getSold() == 0) {
					sc.marketCtrl.bbsDetail(list.get(i).getSeq());
				} else if ( (list.get(i).getDel() == 1 && list.get(i).getName().equals(dto.getName()) ) || dto.getAuth() == 2) {
					sc.marketCtrl.bbsDetail(list.get(i).getSeq());
				} else if (list.get(i).getDel() == 1) {
					JOptionPane.showMessageDialog(null, "삭제된 게시물입니다");
				} else if (list.get(i).getSold() == 1 && list.get(i).getName().equals(dto.getName()) || dto.getAuth() == 2) {
					sc.marketCtrl.bbsDetail(list.get(i).getSeq());
				} else if (list.get(i).getSold() == 1) {
					JOptionPane.showMessageDialog(null, "판매완료된 게시물입니다");
				}
			}
		}
		
		for (int i = 0; i < pageBtn.length; i++) {
			if (bt.getText().equals(1+"")) {
				for (int j = 0; j < 8; j++) {
					lastCt.add(backCt[j]);
				}
				for (int j = 8; j < 32; j++) {
					lastCt.remove(backCt[j]);
				}
				lastCt.revalidate(); 
				lastCt.repaint();
			} else if (bt.getText().equals(2+"")) {
				for (int j = 8; j < 16; j++) {
					lastCt.add(backCt[j]);
				}
				for (int j = 0; j < 8; j++) {
					lastCt.remove(backCt[j]);
				}
				for (int j = 16; j < 32; j++) {
					lastCt.remove(backCt[j]);
				}
				lastCt.revalidate(); 
				lastCt.repaint();
				System.out.println(bt.getText());
			} else if (bt.getText().equals(3+"")) {
				for (int j = 16; j < 24; j++) {
					lastCt.add(backCt[j]);
				}
				for (int j = 0; j < 16; j++) {
					lastCt.remove(backCt[j]);
				}
				for (int j = 24; j < 32; j++) {
					lastCt.remove(backCt[j]);
				}
				lastCt.revalidate(); 
				lastCt.repaint();
			} else if (bt.getText().equals(4+"")) {
				for (int j = 24; j < 32; j++) {
					lastCt.add(backCt[j]);
				}
				for (int j = 0; j < 24; j++) {
					lastCt.remove(backCt[j]);
				}
				lastCt.revalidate(); 
				lastCt.repaint();
			}
		}
	}

}