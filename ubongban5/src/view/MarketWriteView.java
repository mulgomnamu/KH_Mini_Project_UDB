package view;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Document;

import dao.MemberDao;
import dto.MarketDto;
import dto.MemberDto;
import singleton.Singleton;

public class MarketWriteView extends JFrame implements ActionListener {


	JTextField writerText;
	JTextField titleText;
	JTextField fileText;
	JTextField categoryText;
	JTextField priceText;
	JTextArea contentArea;
	String imagesrc;
	Choice categoryChoice;
	char chr;
	File selFile;
	
	JButton btn;
	JButton backBtn;
	JButton fileBtn;
	JFileChooser fileDial = new JFileChooser("C:\\Java");
	
	String loginName;
	MemberDto dto;
		
	public MarketWriteView(MemberDto dto) {
		super("글쓰기");
		setLayout(null);
		
		this.dto = dto;
		
		ImageIcon mainimg1 = new ImageIcon("img/insert.png");  
		JLabel imagelabel2 = new JLabel("",mainimg1, JLabel.CENTER);
		imagelabel2.setBounds(230, 0, 100, 100);
		add(imagelabel2);
		
		JLabel label = new JLabel("ㅡ 중고장터 글쓰기 ㅡ");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("돋움", Font.PLAIN, 25));
		label.setBounds(300, 0, 300, 130);
		add(label);
		
		JLabel writerLabel = new JLabel("작성자:");
		writerLabel.setBounds(170, 120, 100, 15);
		add(writerLabel);		
		
		Singleton sc = Singleton.getInstance();				
		writerText = new JTextField(sc.memCtrl.getLoginName());
		writerText.setBounds(320, 120, 300, 19);
		writerText.setEditable(false);		
		add(writerText);
		
		JLabel titleLabel = new JLabel("제목:");
		titleLabel.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 16));
		titleLabel.setBounds(170, 155, 450, 15);
		add(titleLabel);
		
		titleText = new JTextField();
		titleText.setBounds(320, 155, 300, 19);
		add(titleText);
		
		JLabel categoryLabel = new JLabel("품명:");
		categoryLabel.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 16));
		categoryLabel.setBounds(170, 190, 100, 15);
		add(categoryLabel);
		
		categoryChoice = new Choice();
		categoryChoice.setBounds(320, 190, 300, 19);
		categoryChoice.add("생활용품");
		categoryChoice.add("전자제품");
		categoryChoice.add("가구");
		categoryChoice.add("여성의류잡화");
		categoryChoice.add("남성의류잡화");
		categoryChoice.add("영화공연티켓");
		categoryChoice.add("도서관련");
		categoryChoice.add("아기용품");
		add(categoryChoice);

		JLabel priceLabel = new JLabel("가격:");
		priceLabel.setBounds(170, 225, 100, 15);
		priceLabel.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 16));
		add(priceLabel);
		
		priceText = new JTextField(20);
		priceText.setBounds(320, 225, 300, 19);
		priceText.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 16));
		add(priceText);

		
		JLabel contentLabel = new JLabel("내용:");
		contentLabel.setBounds(170, 295, 100, 15);
		contentLabel.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 16));
		add(contentLabel);
		
		contentArea = new JTextArea();	
		contentArea.setLineWrap(true);	
		contentArea.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 16));
			
		JScrollPane scrPane = new JScrollPane(contentArea);
		scrPane.setPreferredSize(new Dimension(200, 120));
		scrPane.setBounds(170, 320, 460, 260);
		add(scrPane);
		
		btn = new JButton("글올리기");
		btn.setBounds(500, 600, 130, 50);	
		btn.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 16));	
		add(btn);
		
		backBtn = new JButton("목록");
		backBtn.setBounds(350, 600, 130, 50);		
		backBtn.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 16));
		add(backBtn);
		
		fileText = new JTextField();
		fileText.setBounds(320, 260, 300, 15);
		add(fileText);
		fileText.setEditable(false);		
		
		fileBtn = new JButton("파일찾기");
		fileBtn.setBounds(170, 255, 100, 25);
		add(fileBtn);
		fileBtn.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evt) {
	    		//파일 다이얼로그를 띄워 업로드할 파일을 선택한다
				fileDial.showOpenDialog(fileBtn);
				selFile = fileDial.getSelectedFile();
				fileText.setText(selFile.getAbsolutePath());
				
		    }
	    });
		
		btn.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				Singleton sc = Singleton.getInstance();	
				
				if (titleText.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "제목을 입력해 주세요");
					titleText.grabFocus();
					return;
				} else if (priceText.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "해당상품의 가격을 입력해 주세요");
					priceText.grabFocus();
					return;
				} else if (contentArea.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "해당상품의 설명을 입력해 주세요");
					contentArea.grabFocus();
					return;
				}
				
				if (fileText.getText().equals("") != true) {
	                String filename = selFile.getName();
	                //파일이 이미지 파일이면 lb에 미리보기를 해주자
	                filename=filename.toLowerCase(); // 파일 이름을 전부 소문자로 바꾸기
	                if(filename.endsWith(".jpg") == false){
	                	JOptionPane.showMessageDialog(null, "jpg 이미지만 업로드 가능합니다.");
	                	return;
	                }
		        }
				
				Date d = new Date();				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm");
				
				String name = sc.memCtrl.getLoginName();
				String title = titleText.getText();
				String content = contentArea.getText();
				String wdate = sdf.format(d);
				String oriImagesrc = "";
				if (fileText.getText().equals("")) {
					// default 이미지설정
		            //imagesrc = "\\\\\\\\192.168.10.38\\\\Users\\\\user2\\\\images\\\\" + fileName;
					oriImagesrc = "\\\\192.168.10.38\\Users\\user2\\images\\noImage.jpg";
				} else {
					oriImagesrc = fileText.getText();
				}
				String category = categoryChoice.getSelectedItem();

				if (onlyNumber(priceText.getText())) {
					System.out.println("숫자가없습니다.");
				}else {
					JOptionPane.showMessageDialog(null, "가격에 문자가 포함되어있습니다");
					priceText.setText("");
					priceText.grabFocus();
					return;
				}
				
				int price = Integer.parseInt(priceText.getText());
				
				try {
		            //썸네일 가로사이즈
		            int thumbnail_width = 280;

		            //썸네일 세로사이즈
		            int thumbnail_height = 280;

		            // 이미지명 가져오기
		            String[] directoryName = oriImagesrc.split("\\\\"); 
		            String fileName = directoryName[directoryName.length -1];
		            
		            //원본이미지파일의 경로+파일명
		            File origin_file_name = new File(oriImagesrc);
		            //생성할 썸네일파일의 경로+썸네일파일명
		            int i = 1;
		            // 밑에는 학원용
		            imagesrc = "\\\\\\\\192.168.10.38\\\\Users\\\\user2\\\\images\\\\" + fileName;
		            // 밑에는 집용
		            //imagesrc = "C:\\\\images\\\\" + fileName;
		            
		            File thumb_file_name = new File(imagesrc);
		            
		            BufferedImage buffer_original_image = ImageIO.read(origin_file_name);
		            BufferedImage buffer_thumbnail_image = new BufferedImage(thumbnail_width, thumbnail_height, BufferedImage.TYPE_3BYTE_BGR);

		            Graphics2D graphic = buffer_thumbnail_image.createGraphics();

		            graphic.drawImage(buffer_original_image, 0, 0, thumbnail_width, thumbnail_height, null);

		            ImageIO.write(buffer_thumbnail_image, "jpg", thumb_file_name);

		            System.out.println("썸네일 생성완료");

		        } catch (Exception e1) {
		            e1.printStackTrace();
		        }
				
				MarketDto dto = new MarketDto(0, title, content, name, wdate, 0, 0, 0, imagesrc, category, price);
				sc.marketCtrl.bbsWriteAf(dto);	
				dispose();
				
			}
		});
		
		backBtn.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				Singleton sc = Singleton.getInstance();
				//sc.marketCtrl.getBbsList(dto);
				dispose();
			}
		});
		
		setBounds(100, 100, 805, 720);
		getContentPane().setBackground(Color.WHITE);
		setVisible(true);	
	}
	
	public boolean onlyNumber(String str) {
		
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) < '0' || str.charAt(i) > '9') {
				return false;
			}
		}
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}


}


