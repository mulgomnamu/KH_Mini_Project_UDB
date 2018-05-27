package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import chatting.MainChat;

public class userView extends JFrame implements ActionListener{
	
JButton btn1, btn2;
	
	JPanel p;
	
	private String nickNmae1 = "101동1024호";
	private String nickNmae2 = "105동104호";
	private int auth = 3;
	
	
	
	
	public userView() {
		
		p = new JPanel();
		
		btn1 = new JButton("101동1024호");
		btn1.setBounds(320, 200, 150, 30);
		btn1.addActionListener(this);
		p.add(btn1);
		
		btn2 = new JButton("105동104호");
		btn2.setBounds(320, 400, 150, 30);
		btn2.addActionListener(this);
		p.add(btn2);
		
		add(p);
		setBounds(300,200, 500, 500);
	    setVisible(true);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if(ob == btn1) {
			new MainChat(nickNmae1, auth);
		}else if(ob == btn2) {
			new MainChat(nickNmae2, auth);
		}
		
	}

}