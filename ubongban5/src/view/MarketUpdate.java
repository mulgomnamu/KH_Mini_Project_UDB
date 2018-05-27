package view;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dto.MarketDto;
import dto.MemberDto;
import singleton.Singleton;

public class MarketUpdate extends JFrame implements ActionListener, ItemListener{

	private MarketDto dto;
	private JLabel imgLabel;
	private String imagesrc;
	private ImageIcon img;
	private int soldCheck = 0;
	private File selFile;
	
	private JRadioButton soldRadiBtn;
	private JRadioButton soldoutRadiBtn;

	private JLabel soldLabel;
	private JLabel titleLabel;
	private JLabel categoryLabel;
	private JLabel nameLabel;
	private JLabel priceLabel;
	
	private String filename = null;
	
	private JTextField priceField;
	private JTextField titleField;
	private JTextField nameField;
	private JTextArea contentsArea;
	
	private Choice categoryChoice;
	
	private Font soldFont;
	
	private JButton updateBtn;
	private JButton backBtn;
	private JButton fileBtn;
	private JScrollPane jScrPane;
	
	private JFileChooser fileDial = new JFileChooser("C:\\Java");
	private JTextField fileText;
	private MemberDto memDto;
	
	public MarketUpdate(MarketDto dto, MemberDto memDto) {
		super("");
		setLayout(null);
		this.dto = dto;
		this.memDto = memDto;
		
		fileText = new JTextField(dto.getImagesrc());
		fileText.setBounds(420, 300, 280, 25);
		add(fileText);
		fileText.setEditable(false);
		
		img = new ImageIcon(dto.getImagesrc());
		Image orginalImg = img.getImage();  //ImageIcon을 Image로 변환.
		Image resize = orginalImg.getScaledInstance(280, 280, Image.SCALE_SMOOTH);

		ImageIcon resizeImg = new ImageIcon(resize); //Image로 ImageIcon 생성

		imgLabel = new JLabel(resizeImg);
		imgLabel.setBounds(100, 50, 280, 280);
		add(imgLabel);
		
		fileBtn = new JButton("파일찾기");
		fileBtn.setBounds(100, 0, 100, 40);
		add(fileBtn);
		fileBtn.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evt) {
	    		//파일 다이얼로그를 띄워 업로드할 파일을 선택한다
				fileDial.showOpenDialog(fileBtn);
				selFile = fileDial.getSelectedFile();
				fileText.setText(selFile.getAbsolutePath());
				//String filename = selFile.getName();
				
				// dto연결시 바꿔야함
				img = new ImageIcon(fileText.getText());
				Image orginalImg = img.getImage();  //ImageIcon을 Image로 변환.
				Image resize = orginalImg.getScaledInstance(280, 280, Image.SCALE_SMOOTH);

				ImageIcon resizeImg = new ImageIcon(resize); //Image로 ImageIcon 생성

				imgLabel = new JLabel(resizeImg);
				imgLabel.setBounds(100, 50, 280, 280);
//				fileBtn.setBorderPainted(false);
//				fileBtn.setContentAreaFilled(false);
//				fileBtn.setFocusPainted(false);
//				fileBtn.setText("");
				add(imgLabel);
		    }
	    });
		
		String soldSelect = null;
		soldRadiBtn = new JRadioButton("판매중");
		soldoutRadiBtn = new JRadioButton("판매완료");
		if (dto.getSold() == 0) {
			soldRadiBtn.setSelected(true);
			soldSelect = "판매중";
		} else {
			soldoutRadiBtn.setSelected(true);
			soldSelect = "판매완료";
		}
		
		ButtonGroup buttonGrp = new ButtonGroup();
        buttonGrp.add( soldRadiBtn );
        buttonGrp.add( soldoutRadiBtn );
        soldRadiBtn.setBounds(420, 105, 130, 20);
        soldoutRadiBtn.setBounds(550, 105, 130, 20);
        soldRadiBtn.addActionListener(this);
        soldoutRadiBtn.addActionListener(this);
        add(soldRadiBtn);
        add(soldoutRadiBtn);
        
//		String sold = null;
//		if (dto.getSold() == 0) {
//			sold = "판매중";
//		} else if(dto.getSold() == 1){
//			sold = "판매완료";
//		}
		
		soldLabel = new JLabel(soldSelect);
		soldLabel.setBounds(420, 50, 280, 56);
		soldLabel.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 16));
		soldFont = new Font("바탕", Font.BOLD, 25);
		soldLabel.setFont(soldFont);
		add(soldLabel);
		
		titleLabel = new JLabel("제목 :");
		titleLabel.setBounds(420, 140, 80, 25);
		titleLabel.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 16));
		add(titleLabel);
		titleField = new JTextField(dto.getTitle());
		titleField.setBounds(520, 140, 180, 25);
		titleField.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 16));
		add(titleField);
		
		categoryLabel = new JLabel("품명 :");
		categoryLabel.setBounds(420, 180, 80, 25);
		add(categoryLabel);
		categoryChoice = new Choice();
		categoryChoice.setBounds(520, 180, 180, 20);
		categoryChoice.add("생활용품");
		categoryChoice.add("전자제품");
		categoryChoice.add("가구");
		categoryChoice.add("여성의류잡화");
		categoryChoice.add("남성의류잡화");
		categoryChoice.add("영화공연티켓");
		categoryChoice.add("도서관련");
		categoryChoice.add("아기용품");
		add(categoryChoice);
		
		nameLabel = new JLabel("작성자 :");
		nameLabel.setBounds(420, 220, 80, 25);
		nameLabel.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 16));
		add(nameLabel);
		nameField = new JTextField(dto.getName());
		nameField.setBounds(520, 220, 180, 25);
		nameField.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 16));
		nameField.setEnabled(false);
		add(nameField);
		
		priceLabel = new JLabel("가격 :");
		priceLabel.setBounds(420, 260, 80, 25);
		priceLabel.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 16));
		add(priceLabel);
		priceField = new JTextField(dto.getPrice()+"");
		priceField.setBounds(520, 260, 180, 25);
		priceField.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 16));
		add(priceField);

		contentsArea = new JTextArea(dto.getContents());	
		contentsArea.setLineWrap(true);
		contentsArea.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 16));
		contentsArea.setBounds(100, 340, 600, 340);
		add(contentsArea);

		jScrPane = new JScrollPane(contentsArea);
		jScrPane.setBounds(100, 350, 600, 260);
		add(jScrPane);
		
		backBtn = new JButton("돌아가기");
		backBtn.setBounds(570, 630, 130, 50);
		backBtn.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 16));
		add(backBtn);
		backBtn.addActionListener(this);
		
		updateBtn = new JButton("수정완료");
		updateBtn.setBounds(430, 630, 130, 50);
		updateBtn.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 16));
		add(updateBtn);
		updateBtn.addActionListener(this);

		setBounds(300, 100, 805, 800);
		getContentPane().setBackground(Color.WHITE);
		setVisible(true);	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Singleton sc = Singleton.getInstance();
		
		if (titleField.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "제목을 입력해 주세요");
			titleField.grabFocus();
			return;
		} else if (priceField.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "해당상품의 가격을 입력해 주세요");
			priceField.grabFocus();
			return;
		} else if (contentsArea.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "해당상품의 설명을 입력해 주세요");
			contentsArea.grabFocus();
			return;
		}
		
		if (e.getSource() == soldRadiBtn) {
			soldLabel.setText("판매중");
			soldCheck = 0;
		} else if (e.getSource() == soldoutRadiBtn) {
			soldLabel.setText("판매완료");
			soldCheck = 1;
		} else if (e.getSource() == backBtn) {
			dispose();
		} else if (e.getSource() == updateBtn) {
			Date d = new Date();				
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm");
			
			String name = sc.memCtrl.getLoginName();
			String title = titleField.getText();
			String content = contentsArea.getText();
			String wdate = sdf.format(d);
			String oriImagesrc = "";
			int sold = soldCheck;
			if (fileText.getText().equals("")) {
				// default 이미지설정
				oriImagesrc = dto.getImagesrc();
			} else {
				oriImagesrc = fileText.getText();
			}
			String category = categoryChoice.getSelectedItem();

			if (onlyNumber(priceField.getText())) {
				System.out.println("숫자통과.");
			}else {
				JOptionPane.showMessageDialog(null, "가격에 문자가 포함되어있습니다");
				priceField.setText("");
				return;
			}
			
			int price = Integer.parseInt(priceField.getText());
			
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
			
			MarketDto dto = new MarketDto(this.dto.getSeq(), title, content, name, wdate, 0, sold, 0, imagesrc, category, price);
			sc.marketCtrl.bbsUpdateAf(dto);
			dispose();
			
		}
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		
	}
	public boolean onlyNumber(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) < '0' || str.charAt(i) > '9') {
				return false;
			}
		}
		return true;
	}
	
}

