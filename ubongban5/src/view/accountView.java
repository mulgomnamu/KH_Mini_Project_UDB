package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import singleton.Singleton;

public class accountView extends JFrame implements ActionListener,MouseListener,MouseMotionListener,KeyListener {
   
   private JTextField id;
   private JTextField password;
   private JTextField dong;
   private JTextField ho;
   
   private JCheckBox chebtn, chebtn2;
   
   Singleton s = Singleton.getInstance();

   public accountView() {
      
      setTitle(" 우리동네 반상회 회원가입 ");
      setLayout(null);
      setBounds(200, 100, 640, 640);
      getContentPane().setBackground(Color.WHITE);
      getContentPane().setLayout(null);
      
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
      
      ImageIcon joinicon = new ImageIcon("img/joinicon.png");  //이미지 경로      
      JLabel imagelabel3 = new JLabel("",joinicon, JLabel.CENTER);
      imagelabel3.setBounds(30, 0, 150, 150);
      add(imagelabel3);
      
      JLabel NewLabel = new JLabel("우리동네 반상회");
      NewLabel.setBounds(18, 25, 598, 49);
      NewLabel.setFont(new Font("배달의민족 한나는 열한살", Font.PLAIN, 28));
      NewLabel.setHorizontalAlignment(SwingConstants.CENTER);
      getContentPane().add(NewLabel);
      
      JLabel NewLabel2 = new JLabel("회원가입");
      NewLabel2.setBounds(20, 63, 598, 49);
      NewLabel2.setFont(new Font("배달의민족 한나는 열한살", Font.PLAIN, 28));
      NewLabel2.setHorizontalAlignment(SwingConstants.CENTER);
      getContentPane().add(NewLabel2);
      
      // JLabel
   //   JLabel label01 = new JLabel("ID : ");
   //   label01.setBounds(90, 40, 50, 30);
   //   JLabel label02 = new JLabel("PW : ");
   //   label02.setBounds(90, 80, 50, 30);
   //   JLabel label03 = new JLabel("Dong : ");
   //   label03.setBounds(90, 120, 50, 30);
   //   JLabel label04 = new JLabel("Ho : ");
   //   label04.setBounds(90, 160, 50, 30);
   
      id = new JTextField();
      id.setText("아이디를 입력하세요");
      id.setBounds(61, 136, 400, 45);
      getContentPane().add(id);
      id.addMouseListener(this);
      id.setColumns(10);
      
      password = new JTextField();
   //   password.setHorizontalAlignment(SwingConstants.LEFT);
      password.setText("비밀번호를 입력하세요");
      password.setColumns(10);
      password.setBounds(61, 189, 400, 45);
      password.addMouseListener(this);
      getContentPane().add(password);
      
      JTextField apart = new JTextField();
      apart.setText("KH 아파트");
      apart.setColumns(10);
      apart.setEditable(false);
      apart.setBackground(Color.white);
      apart.setBounds(61, 242, 130, 43);
      apart.addMouseListener(this);
      getContentPane().add(apart);
      
      dong = new JTextField();
      dong.setText("동");
      dong.setColumns(10);
      dong.setBounds(195, 242, 130, 43);
      dong.addMouseListener(this);
      getContentPane().add(dong);

      ho = new JTextField();
      ho.setText("호수");
      ho.setColumns(10);
      ho.setBounds(331, 242, 130, 43);
      ho.addMouseListener(this);
      getContentPane().add(ho);

      JSeparator separator = new JSeparator();
      separator.setBounds(46, 310, 530, 2);
      getContentPane().add(separator);

      
      JLabel label1 = new JLabel("약관동의");
      label1.setHorizontalAlignment(SwingConstants.CENTER);
      label1.setFont(new Font("NanumGothic", Font.BOLD, 16));
      label1.setBounds(100, 320, 400, 40);
      getContentPane().add(label1);

      
      chebtn = new JCheckBox(" '우리동네반상회' 서비스 이용약관 (필수)");
      chebtn.setBackground(Color.WHITE);
      chebtn.setFont(new Font("NanumGothic", Font.PLAIN, 13));
      chebtn.setBounds(140, 380, 485, 25);
      getContentPane().add(chebtn);
      
      chebtn2 = new JCheckBox("개인정보 수집 및 이용동의 (필수)");
      chebtn2.setBackground(Color.WHITE);
      chebtn2.setFont(new Font("NanumGothic", Font.PLAIN, 13));
      chebtn2.setBounds(140, 420, 485, 25);
      getContentPane().add(chebtn2);
      
      
      // JTextField
//      idF = new JTextField();
//      idF.setBounds(150, 40, 200, 25);
      
//      password = new JTextField();
//      password.setBounds(150, 80, 200, 25);
      
//      dongF = new JTextField();
//      dongF.setBounds(150, 120, 200, 25);
//      
//      hoF = new JTextField();
//      hoF.setBounds(150, 160, 200, 25);
      
      // JButton
      JButton button01 = new JButton("회원가입");
      button01.setBounds(200, 500, 220, 50);
      button01.addActionListener(new ActionListener() {
         @Override
           public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton)e.getSource();
            String btnStr = btn.getLabel();
            
            if (id.getText().equals("")) {
               JOptionPane.showMessageDialog(null, "아이디를 입력해 주세요");
               id.grabFocus();
               return;
            } else if (password.getText().equals("")) {
               JOptionPane.showMessageDialog(null, "패스워드를 입력해 주세요");
               password.grabFocus();
               return;
            } else if (dong.getText().equals("")) {
               JOptionPane.showMessageDialog(null, "동을 입력해 주세요");
               dong.grabFocus();
               return;
            } else if (ho.getText().equals("")) {
               JOptionPane.showMessageDialog(null, "호수를 입력해 주세요");
               ho.grabFocus();
               return;
            } else {
               
            }

            if (onlyNumber(dong.getText())) {
            }else {
               JOptionPane.showMessageDialog(null, "동에 문자가 포함되어있습니다");
               dong.setText("");
               dong.grabFocus();
               return;
            }
            
            if (onlyNumber(ho.getText())) {
            }else {
               JOptionPane.showMessageDialog(null, "호수에 문자가 포함되어있습니다");
               ho.setText("");
               ho.grabFocus();
               return;
            }
            
            if (chebtn.isSelected() == false) {
               JOptionPane.showMessageDialog(null, "이용약관을 체크해주세요");
               return;
            }

            if (chebtn2.isSelected() == false) {
               JOptionPane.showMessageDialog(null, "이용약관을 체크해주세요");
               return;
            }
            
//            if(btnStr.equals("회원가입")) {
//               if (id.getText().equals("") ||
//                  password.getText().equals("") ||
//                  dong.getText().equals("") ||
//                  ho.getText().equals("")){
//                  
//                  JOptionPane.showMessageDialog(null, "모두 기입해 주십시오");
//               }
//            }
            
            boolean b = s.memCtrl.regiAf(id.getText(), password.getText(), dong.getText(), ho.getText());
            if (b) {
            	new loginView();
               dispose();
            }
         }
      });
   
      JButton checkBtn = new JButton("중복확인");
      checkBtn.setBounds(480, 136, 90, 45);
      checkBtn.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            boolean b = s.memCtrl.idCheck(id.getText());
            if(b) {
               JOptionPane.showMessageDialog(null, "사용할 수 없는 아이디 입니다");
               id.setText("");
            }else {
               JOptionPane.showMessageDialog(null, "사용할 수 있는 ID입니다");
            }
         }
      });
      
   //   add(label01);
   //   add(label02);
   //   add(label03);
   //   add(label04);
      add(id);
      add(password);
      add(dong);
      add(ho);
      add(button01);
      add(checkBtn);
      
      setVisible(true);
      
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub

   }

   @Override
   public void mouseClicked(MouseEvent arg0) {


   }

   @Override
   public void mouseEntered(MouseEvent arg0) {
      if(id.getText().equals("")) {
         id.setText("아이디를 입력해주세요");
      }else if(password.getText().equals("")) {
         password.setText("비밀번호를 입력하세요");
         password.setForeground(Color.RED);
      }else if(dong.getText().equals("")) {
         dong.setText("동을 입력해주세요");
      }else if(ho.getText().equals("")) {
         ho.setText("호를 입력해주세요");
      }else{password.setForeground(Color.black);
      }
   }

   @Override
   public void mouseExited(MouseEvent arg0) {

   }

   @Override
   public void mousePressed(MouseEvent arg0) {
      if(arg0.getSource().equals(id)) {
         id.setText("");
      }else if(arg0.getSource().equals(password)) {
         password.setText("");
      }else if(arg0.getSource().equals(dong)) {
         dong.setText("");
      }else if(arg0.getSource().equals(ho)) {
         ho.setText("");
      }
      
   }
      

   @Override
   public void mouseReleased(MouseEvent arg0) {

      
   }

   @Override
   public void mouseDragged(MouseEvent arg0) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void mouseMoved(MouseEvent arg0) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void keyPressed(KeyEvent arg0) {
      
   }

   @Override
   public void keyReleased(KeyEvent arg0) {
   
      
   }

   @Override
   public void keyTyped(KeyEvent arg0) {

      
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