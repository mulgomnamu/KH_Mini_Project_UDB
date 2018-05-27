package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import chatting.MainChat;

public class adminView extends JFrame implements ActionListener {
	
JButton btn;
	
	JPanel p;

	private String nickNmae = "관리자";
	private int auth = 1;
	
	public adminView() {
		
		p = new JPanel();
		
		btn = new JButton("고객과 채팅하기");
		btn.setBounds(320, 330, 150, 30);
		btn.addActionListener(this);
		p.add(btn);
		
		add(p);
		setBounds(900, 200, 500, 500);
	    setVisible(true);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if(ob == btn) {
			new MainChat(nickNmae, auth);
		}
		
	}

}
