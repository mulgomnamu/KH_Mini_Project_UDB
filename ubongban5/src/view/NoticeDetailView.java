package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import dto.NoticeBoardDto;

public class NoticeDetailView extends JFrame implements ActionListener, WindowListener , MouseListener{

	JButton btn1 = new JButton("back");
	JButton btn2 = new JButton("delete");
	JButton btn3 = new JButton("update");
	JButton btn4 = new JButton("comment");
	JButton btn5 = new JButton("insert");
	
	JTextField textField,textField1, readCountField, WdateField,textField5,textField6;
	JTextArea textField2, textField7;
	String id1;
	String id;
	String title;
	String content;
	int readCount;
	String wdate;
	int rowNum1;
	int SEQ;
	int auth=0;
	boolean flag = false;
	private JPanel contentPane = new JPanel(); 
	private JScrollPane scrollPane = new JScrollPane(contentPane);
	
	public NoticeDetailView(NoticeBoardDto noticedto) {
		
		JLabel label = new JLabel();
		setLayout(null);
		add(label).setBounds(18, 0, 100, 85);	
		String str = "제목";
		label.setText(str);
		label.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 15));
		textField = new JTextField(noticedto.getTitle());	
		add(textField).setBounds(100, 30, 500, 30);
		textField.setEditable(false);
		textField.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 15));
		
		JLabel label1 = new JLabel();
		add(label1).setBounds(18, 47, 100, 100);	
		String str1 = "글쓴이";
		label1.setText(str1);
		label1.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 15));
		textField1 = new JTextField(noticedto.getName());	
		add(textField1).setBounds(100, 80, 500, 30);
		textField1.setEditable(false);
		textField1.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 15));
		
		JLabel label3 = new JLabel();
		add(label3).setBounds(18, 96, 100, 100);	
		String str3 = "작성일";
		label3.setText(str3);
		label3.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 15));
		textField1 = new JTextField(noticedto.getWdate());	
		add(textField1).setBounds(100, 130, 500, 30);
		textField1.setEditable(false);
		textField1.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 15));
		
		JLabel label4 = new JLabel();
		add(label4).setBounds(18, 155, 100, 100);	
		String str4 = "내용";
		label4.setText(str4);
		label4.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 15));
		textField2 = new JTextArea(noticedto.getContent());	
		add(textField2).setBounds(100, 200, 500, 300);
		textField2.setEditable(false);
	    Border lineBorder = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);
	    textField2.setBorder(lineBorder);
	    textField2.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 18));
		
//		btn1.addActionListener(this);
//		add(btn1).setBounds(100, 550, 100, 50);
		
		setLayout(null);
		setBounds(150, 200, 700, 600);
	    getContentPane().setBackground(Color.WHITE);	
	    setVisible(true);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
