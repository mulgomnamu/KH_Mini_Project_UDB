package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;

import dao.BasicBBSDao;
import dao.MemberDao;
import dto.BasicBBSDto;
import dto.MemberDto;
import oracle.net.aso.b;
import singleton.Singleton;

public class BasicBBSInsertReplyView extends JFrame implements WindowListener, ActionListener,MouseListener {
	
	private MemberDto m_dto;
	private BasicBBSDto b_dto;
	private boolean b;

	JLabel label_parent_title, label_parent_title_view, label_id, label_title, label_content, label_id_view;
	JTextField textField_title_view;
	JTextArea textArea_content_view;
	JButton btn_insert, btn_cancel, btn_;

	List<BasicBBSDto> list = new ArrayList<BasicBBSDto>();
	
	public BasicBBSInsertReplyView() {	}
	
	public BasicBBSInsertReplyView(MemberDto m_dto, BasicBBSDto b_dto) {
		
		setLayout(null);
		
		this.m_dto = m_dto;
		this.b_dto = b_dto;
		
		/*
		ImageIcon mainimg1 = new ImageIcon("img/insert.png");  
		JLabel imagelabel2 = new JLabel("",mainimg1, JLabel.CENTER);
		imagelabel2.setBounds(200, 30, 100, 100);
		add(imagelabel2);
		*/
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JLabel label = new JLabel("ㅡ 답글 작성하기ㅡ");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("아리따-돋움(TTF)-SemiBold", Font.PLAIN, 25));
		label.setBounds(240, 10, 300, 130);
		add(label);
		
		label_parent_title = new JLabel("ㄴ 원 글");
		label_parent_title.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 15));
		label_parent_title.setBounds(200, 80, 100, 100);
		add(label_parent_title);
		
		label_parent_title_view = new JLabel(b_dto.getTitle());
		label_parent_title_view.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 15));
		label_parent_title_view.setBounds(300, 80, 500, 100);
		add(label_parent_title_view);
		
		label_id = new JLabel("답글 작성자");
		label_id.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 15));
		label_id.setBounds(200, 120, 100, 80);
		add(label_id);
		
		label_id_view = new JLabel(m_dto.getName());
		label_id_view.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 15));
		label_id_view.setBounds(300, 120, 100, 80);
		add(label_id_view);
	/*	
		label_title = new JLabel("제목 : ");
		label_title.setFont(new Font(null, Font.PLAIN, 15));
		label_title.setBounds(200, 300, 200, 200);
		add(label_title);
	*/	
		textField_title_view = new JTextField("제목을 입력하세요");
		textField_title_view.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 15));
		textField_title_view.setBounds(200, 185, 450, 40);
		add(textField_title_view);
	/*	
		label_content = new JLabel("내용 : ");
		label_content.setFont(new Font(null, Font.PLAIN, 15));
		label_content.setBounds(200, 400, 200, 200);
		add(label_content);
	*/	
		textArea_content_view = new JTextArea("내용을 입력하세요");
		textArea_content_view.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 15));
	    Border lineBorder = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);
	    // 텍스트와 TextArea 경계 사이에 여백을 두기 위해서 emptyBorder를 생성합니다. 
	    Border emptyBorder = BorderFactory.createEmptyBorder(1, 1, 1, 1);
	    //TextArea에 lineBorder(검정테두리), emptyBorder(여백)로 구성된 복합 경계선을 설정합니다.
	    textArea_content_view.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));

		textArea_content_view.setBounds(200, 250, 450, 300);
		add(textArea_content_view);
		
		btn_insert = new JButton("추가");
		btn_insert.setBounds(340, 580, 130, 50);
		btn_insert.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 13));
		btn_insert.addActionListener(this);
		add(btn_insert);
		
		btn_cancel = new JButton("뒤로가기");
		btn_cancel.setBounds(520, 580, 130, 50);
		btn_cancel.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 13));
		btn_cancel.addActionListener(this);
		add(btn_cancel);
		
		setBounds(100, 0, 805, 700);
		setVisible(true);
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		JButton btn = (JButton)e.getSource();
		String btnStr = btn.getLabel();
		
		
		Singleton s = Singleton.getInstance();
		if(btnStr.equals("추가")) {
			if(textField_title_view.getText().equals("") || textArea_content_view.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "모두 기입해 주십시오");
				return;
			}
			int family = b_dto.getFamily();
			int seq = b_dto.getSeq();
			int depth = b_dto.getDepth();
			int indent = b_dto.getIndent();
			s.BasCtrl.writeBasicBBSReplier(new BasicBBSDto(textField_title_view.getText(), textArea_content_view.getText(), m_dto.getName(), 0, 0), family, seq, depth, indent);
			s.BasCtrl.BasicBBSView(m_dto);
			this.dispose();
		}else if(btnStr.equals("뒤로가기")) {
			s.BasCtrl.BasicBBSView(m_dto);
			this.dispose();
		}

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
	public void mouseClicked(MouseEvent e) {
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

	@Override
	public void mousePressed(MouseEvent e) {

		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
