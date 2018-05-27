package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;

import dto.MemberDto;
import singleton.Singleton;

public class loginView extends JFrame implements ActionListener {

	Singleton s = Singleton.getInstance();
//	private Color color = new Color(0, 0, 0, 0);
	MemberDto memdto;
	
	public loginView() {
		setLayout(null);
		setBounds(350, 250, 650, 433);
		this.memdto = memdto;
//		ImageIcon image = new ImageIcon("backImg.jpg");
//	    JLabel imagelabel = new JLabel("", image, JLabel.CENTER);
//	    imagelabel.setBounds(0, 0, 650, 433);
	    
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ImageIcon logoimg = new ImageIcon("img/loginlogo.jpg");  //이미지 경로		
		JLabel imagelabel = new JLabel("",logoimg, JLabel.CENTER);
		imagelabel.setBounds(180, 1, 120, 120);
		add(imagelabel);
		

		JLabel label01 = new JLabel(" ID");
		label01.setBounds(80, 128, 50, 30);
		label01.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		
		JLabel label02 = new JLabel("PW");
		label02.setBounds(80, 180, 50, 30);
		label02.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		
		// JTextField
		JTextField textField01 = new JTextField();
		textField01.setBounds(150, 133, 200, 30);
		//textField01.setBackground(color);
		JPasswordField textField02 = new JPasswordField();
		textField02.setBounds(150, 180, 200, 30);	
		
		// JButton
		JButton button01 = new JButton("Login");
		button01.setBounds(70, 230, 100, 40);
		button01.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 15));
		button01.setFocusPainted(false);
//		button01.setBackground(Color.blue);
		button01.addActionListener(new ActionListener() {
			@Override
	        public void actionPerformed(ActionEvent e) {
				JButton btn = (JButton)e.getSource();				
				if(btn == button01){
					// 빈칸 첵크 생략
					s.memCtrl.loginAf(textField01.getText(), textField02.getText());
				}
				dispose();
			}
		});

		JButton button02 = new JButton("회원가입");
		button02.setBounds(190, 230, 100, 40);
		button02.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 15));
		button02.addActionListener(new ActionListener() {
			@Override
	        public void actionPerformed(ActionEvent e) {
				s.memCtrl.regi();
				dispose();
			}
		});
		
		JButton button03 = new JButton("뒤로가기");
        button03.setBounds(310, 230, 100, 40);
        button03.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 15));
        button03.addActionListener(new ActionListener() {
           @Override
             public void actionPerformed(ActionEvent e) {
              JButton btn = (JButton)e.getSource();            
              if(btn == button03){
                 // 빈칸 첵크 생략
                 new mainView(new MemberDto("", "", "guest", "", "", 0));
                 dispose();
              }
        
           }
        });

		add(label01);
		add(label02);
		add(textField01);
		add(textField02);
		add(button01);
		add(button02);
		add(button03);
	    //add(imagelabel);

		setBounds(350, 250, 480, 400);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(350, 250, 600, 433);
		getContentPane().add(panel);		
		setVisible(true);

//	    setBounds(0, 0, 400, 350);
	    getContentPane().setBackground(Color.WHITE);	
//	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {


	}

}
