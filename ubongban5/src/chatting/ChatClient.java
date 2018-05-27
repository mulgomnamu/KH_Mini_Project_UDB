package chatting;

import java.awt.Color;
import java.awt.ScrollPane;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;

public class ChatClient extends JFrame {
	  //채팅방
	   JTextField sendTF;
	   JLabel la_msg;
	   
	   JTextPane ta;
	   JScrollPane sp_ta,sp_list;   	
	   
	   JList<String> li_inwon;
	   JButton bt_emo, bt_exit;   
	   
	   JPanel p;	
	   public ChatClient() {
		  setTitle("채팅방");
		  sendTF = new JTextField(15);	  
		  la_msg = new JLabel("Message");
		  	  
		  ta = new JTextPane();
//		    ta.setLineWrap(true);//TextArea 가로길이를 벗어나는 text발생시 자동 줄바꿈
		  li_inwon = new JList<String>();
		  
		  sp_ta = new JScrollPane(ta, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		  sp_ta.setBorder(new TitledBorder("채팅 내역"));
		  sp_list = new JScrollPane(li_inwon);
		  sp_list.setBorder(new TitledBorder("인원정보"));
		  
		  bt_exit = new JButton("나가기");
		  bt_emo = new JButton("이모티콘");
		  
		  p = new JPanel();
		  
		  sp_ta.setBounds(10,10,400,620); 
		  la_msg.setBounds(10,640,60,30); 
		  sendTF.setBounds(70,640,340,30); 
		  
		  sp_list.setBounds(415,10,150,580);
		  bt_emo.setBounds(415,600,150,30);
		  bt_exit.setBounds(415,640,150,30);
		  
		  p.setLayout(null);
		  p.setBackground(Color.PINK);
		  p.add(sp_ta);
		  p.add(la_msg);
		  p.add(sendTF);
		  p.add(sp_list);
		  p.add(bt_exit);
		  p.add(bt_emo);
		  
		  add(p);
		  setBounds(300,200,600,750);
		  //setVisible(true);
		  sendTF.requestFocus();	  
		  
	   }//생성자   

}
