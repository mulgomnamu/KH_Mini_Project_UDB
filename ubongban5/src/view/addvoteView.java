package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import dto.VoteDto;
import service.DateLabelFormatter;
import service.TextHint;
import singleton.Singleton;

public class addvoteView extends JFrame implements ActionListener {

	JTextField titleText, choiceText;
	JTextArea contentArea;
	
	JButton confirmbtn;
	JButton backBtn;
	VoteDto votedto = new VoteDto();
	JDatePickerImpl datePicker;
	
	//투표안건을 추가하기 위한 view
	
	public addvoteView() {
		super("투표추가");
		setLayout(null);
		
		
		JLabel titleLabel = new JLabel("투표 안건");
		titleLabel.setFont(new Font("아리따-돋움(TTF)-SemiBold", Font.PLAIN, 15));
		titleLabel.setBounds(10, 10, 90, 50);
		add(titleLabel);
		
		titleText = new JTextField();
		titleText.setBounds(120, 10, 350, 50);
		add(titleText);
		
		JLabel voteEndLabel = new JLabel("투표 마감일");
		voteEndLabel.setFont(new Font("아리따-돋움(TTF)-SemiBold", Font.PLAIN, 15));
		voteEndLabel.setBounds(10, 100, 90, 30);
		add(voteEndLabel);
		
		UtilDateModel model = new UtilDateModel();
		//model.setDate(20,04,2014);
		// Need this...
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		// Don't know about the formatter, but there it is...
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setBackground(Color.WHITE);
		add(datePicker).setBounds(120, 100, 200, 35);
		
//		JLabel choiceLabel = new JLabel("선택지 : ");
//		choiceLabel.setBounds(10, 100, 120, 30);
//		add(choiceLabel);
//		
//		choiceText = new JTextField();
//		choiceText.setBounds(120, 100, 350, 20);
//		add(choiceText);
		
		
//		voteEndYearText = new JTextField();
//		voteEndYearText.setBounds(120, 70, 60, 20);
//		add(voteEndYearText);
//		
//		voteEndMonthText.setBounds(190, 70, 40, 20);
//		add(voteEndMonthText);
//		
//		voteEndDateText.setBounds(240, 70, 40, 20);
//		add(voteEndDateText);
//		
//		voteEndHourText.setBounds(290, 70, 40, 20);
//		add(voteEndHourText);
		
//		JLabel contentLabel = new JLabel("내용:");
//		contentLabel.setBounds(10, 100, 120, 15);
//		add(contentLabel);
//		
//		contentArea = new JTextArea();	
//		add(contentArea).setBounds(10, 130, 460, 300);
		
		//contentArea.setLineWrap(true);	
			
		//JScrollPane scrPane = new JScrollPane(contentArea);
		//scrPane.setPreferredSize(new Dimension(200, 120));
		//scrPane.setBounds(10, 130, 460, 300);
		//add(scrPane);
		
		confirmbtn = new JButton("글올리기");
		confirmbtn.setFont(new Font("아리따-돋움(TTF)-SemiBold", Font.PLAIN, 15));
		confirmbtn.setBounds(110, 200, 100, 30);		
		add(confirmbtn);
		
		setBounds(350, 150, 500, 320);
		setVisible(true);	
	    getContentPane().setBackground(Color.WHITE);	
	    
		backBtn = new JButton("취소");
		confirmbtn.setFont(new Font("아리따-돋움(TTF)-SemiBold", Font.PLAIN, 15));
		backBtn.setBounds(280, 200, 100, 30);		
		add(backBtn);
		
		
		confirmbtn.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
																//"yyyy-MM-dd HH:mm:ss"
				SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA ); 
				Date selectedDate = (Date) datePicker.getModel().getValue();
				String endTime = formatter.format ( selectedDate );
				System.out.println("firstdate :" + endTime);
				
				Singleton sc = Singleton.getInstance();	
				formatter = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA ); 
				Date currentTime = new Date ( ); 
				String nowTime = formatter.format ( currentTime ); 
				

				String content = titleText.getText();
				String startdate = nowTime;
				String enddate = endTime;
				VoteDto dto = new VoteDto("admin", 0, content, startdate, enddate, 0, 0, 0, 0);
				sc.voteCtrl.insertVote(dto);
				dispose();
				
				
			}
		});
		
		backBtn.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		

	}

}
