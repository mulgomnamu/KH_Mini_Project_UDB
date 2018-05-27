package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.nio.channels.MembershipKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import chatting.MainChat;
import controller.ReservationController;
import dao.MemberDao;
import db.DBClose;
import db.DBConnection;
import dto.MemberDto;
import dto.calendarDto;
import singleton.Singleton;

class CalendarDataManager{ // 6*7배열에 나타낼 달력 값을 구하는 class
   static final int CAL_WIDTH = 7;
   final static int CAL_HEIGHT = 6;
   int calDates[][] = new int[CAL_HEIGHT][CAL_WIDTH];
   int calYear;
   int calMonth;
   int calDayOfMon;
   final int calLastDateOfMonth[]={31,28,31,30,31,30,31,31,30,31,30,31};
   int calLastDate;
   Calendar today = Calendar.getInstance();
   Calendar cal;
   
   //디폴트 생성자
   public CalendarDataManager(){ 
      setToday(); 
   }
   
   //투데이 설정
   public void setToday(){
      calYear = today.get(Calendar.YEAR); 
      calMonth = today.get(Calendar.MONTH);
      calDayOfMon = today.get(Calendar.DAY_OF_MONTH);
      makeCalData(today);
   }
   
   
   private void makeCalData(Calendar cal){
      // 1일의 위치와 마지막 날짜를 구함 
      int calStartingPos = (cal.get(Calendar.DAY_OF_WEEK)+7-(cal.get(Calendar.DAY_OF_MONTH))%7)%7;
      if(calMonth == 1) calLastDate = calLastDateOfMonth[calMonth] + leapCheck(calYear);
      else calLastDate = calLastDateOfMonth[calMonth];
      // 달력 배열 초기화
      for(int i = 0 ; i<CAL_HEIGHT ; i++){
         for(int j = 0 ; j<CAL_WIDTH ; j++){
            calDates[i][j] = 0;
         }
      }
      // 달력 배열에 값 채워넣기
      for(int i = 0, num = 1, k = 0 ; i<CAL_HEIGHT ; i++){
         if(i == 0) k = calStartingPos;
         else k = 0;
         for(int j = k ; j<CAL_WIDTH ; j++){
            if(num <= calLastDate) calDates[i][j]=num++;
         }
      }
   }
   
   
   private int leapCheck(int year){ // 윤년인지 확인하는 함수
      if(year%4 == 0 && year%100 != 0 || year%400 == 0) return 1;
      else return 0;
   }
   
   
   public void moveMonth(int mon){ // 현재달로 부터 n달 전후를 받아 달력 배열을 만드는 함수(1년은 +12, -12달로 이동 가능)
      calMonth += mon;
      if(calMonth>11) while(calMonth>11){
         calYear++;
         calMonth -= 12;
      } else if (calMonth<0) while(calMonth<0){
         calYear--;
         calMonth += 12;
      }
      cal = new GregorianCalendar(calYear,calMonth,calDayOfMon);
      makeCalData(cal);
   }
}

public class MemoCalendar extends CalendarDataManager implements ActionListener{ // CalendarDataManager의 GUI + 메모기능 + 시계
   // 창 구성요소와 배치도
   JFrame mainFrame;
//      ImageIcon icon = new ImageIcon ( Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
   JButton mainbtn;
   JButton menubtn1,menubtn2,menubtn3,menubtn4,menubtn5;
   JButton menutextbtn1,menutextbtn2,menutextbtn3,menutextbtn4,menutextbtn5;
   JButton loginbtn;
   JButton joinbtn;	   
   JButton btn_logOut;
   JLabel titlelabel;
    
   JPanel calOpPanel;
   JButton todayBut;
   JLabel todayLab;
   JButton lYearBut;
   JButton lMonBut;
   JLabel curMMYYYYLab;
   JButton nMonBut;
   JButton nYearBut;
   ListenForCalOpButtons lForCalOpButtons = new ListenForCalOpButtons();
   
   JPanel calPanel;
   JButton weekDaysName[];
   JButton dateButs[][] = new JButton[6][7];
   listenForDateButs lForDateButs = new listenForDateButs(); 
   
   JPanel infoPanel;
   JLabel infoClock;
   
   JPanel memoPanel;
   JLabel selectedDate;
   JTextArea memoArea;
   JScrollPane memoAreaSP;
   JPanel memoSubPanel;
   JButton saveBut; 
   JButton delBut; 

      
   JPanel testPanel;
   JTextField testField = new JTextField();

   
   JPanel frameBottomPanel;
      JLabel bottomInfo = new JLabel("Welcome to Memo Calendar!");
   //상수, 메세지
   final String WEEK_DAY_NAME[] = { "일", "월", "화", "수", "목", "금", "토" };
   final String title = "메모 달력 ver 1.0";
   final String SaveButMsg1 = "를 MemoData폴더에 저장하였습니다.";
   final String SaveButMsg2 = "메모를 먼저 작성해 주세요.";
   final String SaveButMsg3 = "<html><font color=red>ERROR : 파일 쓰기 실패</html>";
   final String DelButMsg1 = "메모를 삭제하였습니다.";
   final String DelButMsg2 = "작성되지 않았거나 이미 삭제된 memo입니다.";
   final String DelButMsg3 = "<html><font color=red>ERROR : 파일 삭제 실패</html>";
   final String ClrButMsg1 = "입력된 메모를 비웠습니다.";
   String placeChoice = "-";
   String timeCoice = "-";
   String date = null;
   List<calendarDto> list = new ArrayList<calendarDto>();
   JTextArea  testArea = new JTextArea();
   String name = null;
   JTextField choiceDate = new JTextField("-");

//   public static void main(String[] args){
//      SwingUtilities.invokeLater(new Runnable(){
//         public void run(){
//            new MemoCalendar();
//         }
//      });
//   }
   MemberDto memdto;
   public MemoCalendar(MemberDto memdto) { //구성요소 순으로 정렬되어 있음. 각 판넬 사이에 빈줄로 구별
	   this.memdto = memdto;
	   name = memdto.getName();
      mainFrame = new JFrame(title);
      mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      mainFrame.setBounds(100, 0, 1150, 850);
      mainFrame.setLocationRelativeTo(null);
      mainFrame.setResizable(false);
      //mainFrame.setIconImage(icon.getImage());
      try{
         UIManager.setLookAndFeel ("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");//LookAndFeel Windows 스타일 적용
         SwingUtilities.updateComponentTreeUI(mainFrame) ;
      }catch(Exception e){
         bottomInfo.setText("ERROR : LookAndFeel setting failed");
      }
      

      
      calOpPanel = new JPanel();
         todayBut = new JButton("Today");
         todayBut.setToolTipText("Today");
         todayBut.addActionListener(lForCalOpButtons);
         todayLab = new JLabel(today.get(Calendar.MONTH)+1+"/"+today.get(Calendar.DAY_OF_MONTH)+"/"+today.get(Calendar.YEAR));
         lYearBut = new JButton("<<");
         lYearBut.setToolTipText("Previous Year");
         lYearBut.addActionListener(lForCalOpButtons);
         lMonBut = new JButton("<");
         lMonBut.setToolTipText("Previous Month");
         lMonBut.addActionListener(lForCalOpButtons);
         curMMYYYYLab = new JLabel("<html><table width=100><tr><th><font size=5>"+((calMonth+1)<10?"&nbsp;":"")+(calMonth+1)+" / "+calYear+"</th></tr></table></html>");
         nMonBut = new JButton(">");
         nMonBut.setToolTipText("Next Month");
         nMonBut.addActionListener(lForCalOpButtons);
         nYearBut = new JButton(">>");
         nYearBut.setToolTipText("Next Year");
         nYearBut.addActionListener(lForCalOpButtons);
         calOpPanel.setLayout(new GridBagLayout());
         GridBagConstraints calOpGC = new GridBagConstraints();
         calOpGC.gridx = 1;
         calOpGC.gridy = 1;
         calOpGC.gridwidth = 2;
         calOpGC.gridheight = 1;
         calOpGC.weightx = 1;
         calOpGC.weighty = 1;
         calOpGC.insets = new Insets(5,5,0,0);
         calOpGC.anchor = GridBagConstraints.WEST;
         calOpGC.fill = GridBagConstraints.NONE;
         calOpPanel.add(todayBut,calOpGC);
         calOpGC.gridwidth = 3;
         calOpGC.gridx = 2;
         calOpGC.gridy = 1;
        // calOpPanel.add(todayLab,calOpGC);
         calOpGC.anchor = GridBagConstraints.CENTER;
         calOpGC.gridwidth = 1;
         calOpGC.gridx = 1;
         calOpGC.gridy = 2;
         calOpPanel.add(lYearBut,calOpGC);
         calOpGC.gridwidth = 1;
         calOpGC.gridx = 2;
         calOpGC.gridy = 2;
         calOpPanel.add(lMonBut,calOpGC);
         calOpGC.gridwidth = 2;
         calOpGC.gridx = 3;
         calOpGC.gridy = 2;
         calOpPanel.add(curMMYYYYLab,calOpGC);
         calOpGC.gridwidth = 1;
         calOpGC.gridx = 5;
         calOpGC.gridy = 2;
         calOpPanel.add(nMonBut,calOpGC);
         calOpGC.gridwidth = 1;
         calOpGC.gridx = 6;
         calOpGC.gridy = 2;
         calOpPanel.add(nYearBut,calOpGC);
      
      calPanel=new JPanel();
   //   calPanel.setBounds(x, y, width, height);
         weekDaysName = new JButton[7];
         //무슨요일인지 출력
         for(int i=0 ; i<CAL_WIDTH ; i++){
            weekDaysName[i]=new JButton(WEEK_DAY_NAME[i]);
            weekDaysName[i].setBorderPainted(false);
            weekDaysName[i].setContentAreaFilled(false);
            weekDaysName[i].setForeground(Color.WHITE);
            if(i == 0) weekDaysName[i].setBackground(new Color(200, 50, 50));
            else if (i == 6) weekDaysName[i].setBackground(new Color(50, 100, 200));
            else weekDaysName[i].setBackground(new Color(150, 150, 150));
            weekDaysName[i].setOpaque(true);
            weekDaysName[i].setFocusPainted(false);
            calPanel.add(weekDaysName[i]);
         }
         
         //날짜를 출력하는 부분
         for(int i=0 ; i<CAL_HEIGHT ; i++){
            for(int j=0 ; j<CAL_WIDTH ; j++){
               dateButs[i][j]=new JButton();
               dateButs[i][j].setBorderPainted(false);
               dateButs[i][j].setContentAreaFilled(false);
               dateButs[i][j].setBackground(Color.WHITE);
               dateButs[i][j].setOpaque(true);
               dateButs[i][j].addActionListener(lForDateButs);
               calPanel.add(dateButs[i][j]);
            }
         }
         //달력의 각각의 날짜버튼을 7개 나열하고 서로 버튼간의 여백을 2만큼씩 할당 
         calPanel.setLayout(new GridLayout(0,7,2,2));
         //calPanel.setLayout(new GridLayout(0,7,2,2));
         //달력의 왼쪽오른쪽위아래 여백공간 만들기
         calPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
         //calPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
         showCal(); // 달력을 표시
                  
         //실시간으로 표시되는 달력 출력
      infoPanel = new JPanel();
      infoPanel.setLayout(null);
      infoClock = new JLabel("", SwingConstants.RIGHT);
      infoClock.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
      infoPanel.add(infoClock);
         
      infoClock.setBounds(0, 0, 100, 30);
         //selectedDate = new JLabel("<Html><font size=3>"+(today.get(Calendar.MONTH)+1)+"/"+today.get(Calendar.DAY_OF_MONTH)+"/"+today.get(Calendar.YEAR)+"&nbsp;(Today)</html>", SwingConstants.LEFT);
//         selectedDate = new JLabel("예약내역 : ");
//         selectedDate.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 0));
              
         
      memoPanel=new JPanel();
      memoPanel.setBackground(new Color(217, 217, 217));
      LineBorder b2 = new LineBorder(Color.black,1); // 보더
   //   memoPanel.setBorder(b2);
    
      memoArea = new JTextArea();
         //memoPanel.add(memoArea);
         //memoArea.setBounds(80, 0, 300, 30);

         JLabel revContent = new JLabel("label");
         revContent.setText("예약내역");
         revContent.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 16));
         memoPanel.add(revContent).setBounds(8, 150, 73, 80);
         
         memoPanel.add(testArea);
         //testArea.setBounds(80, 180, 300, 200);
         testArea.setBorder(b2);
         testArea.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 16));
         testArea.setEditable(false);
         JLabel placeName = new JLabel("label");
         
         JScrollPane jScrPane = new JScrollPane(testArea);
 		jScrPane.setBounds(100, 200, 1720, 825);	
 		memoPanel.add(jScrPane).setBounds(80, 180, 300, 200);
 		
       placeName.setText(" 장소");
       placeName.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 16));
       memoPanel.add(placeName).setBounds(5, 25, 60, 80);
         
         final String labels[] = {"-", "테니스장", "풋살장", "스터디룸", "탁구장"};
        JFrame frame = new JFrame("Editable JComboBox");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JComboBox comboBox = new JComboBox(labels);
        comboBox.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 16));
        comboBox.setMaximumRowCount(5);
        comboBox.setEditable(false);
        memoPanel.add(comboBox);
        comboBox.setBounds(80, 50, 300, 30);
       // frame.add(comboBox, BorderLayout.NORTH);

        ActionListener actionListener = new ActionListener() {
          public void actionPerformed(ActionEvent actionEvent) {
            placeChoice = (String) comboBox.getSelectedItem();
          }
        };
        comboBox.addActionListener(actionListener);
        
       JLabel revTime = new JLabel("label");
       revTime.setText("시간");
       revTime.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 16));
       memoPanel.add(revTime).setBounds(10, 70, 70, 80);
      
      final String labels1[] = {"-", "9시", "10시", "11시", "12시", "13시", "14시", "15시", "16시", "17시", "18시"};
       JFrame frame1 = new JFrame("Editable JComboBox");
       frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       final JComboBox comboBox1 = new JComboBox(labels1);
       comboBox1.setMaximumRowCount(11);
       comboBox1.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 16));
       comboBox1.setEditable(false);
       memoPanel.add(comboBox1).setBounds(80, 95, 250, 25);
      // frame.add(comboBox, BorderLayout.NORTH);

       ActionListener actionListener1 = new ActionListener() {
         public void actionPerformed(ActionEvent actionEvent) {
           
            timeCoice = (String) comboBox1.getSelectedItem();
            
           
         }
       };
       comboBox1.addActionListener(actionListener1);
       
       JLabel titlelabel = new JLabel("ㅡ예약하기ㅡ");
 //      revTime.setText("시간");
       memoPanel.add(titlelabel).setBounds(0, 0, 120, 40);
       titlelabel.setFont(new Font("아리따-돋움(TTF)-SemiBold", Font.PLAIN, 18));
       
       JLabel revDate = new JLabel("label");
       revDate.setText(" 날짜");
       revDate.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 16));
       memoPanel.add(revDate).setBounds(5, 110, 70, 80);
       memoPanel.add(choiceDate).setBounds(80, 140, 200, 25);
       choiceDate.setEditable(false);
         
         readMemo(date);
         
         memoSubPanel=new JPanel();
   //    memoSubPanel.
         memoSubPanel.setBackground(new Color(217, 217, 217));
         saveBut = new JButton("예약하기"); 
         saveBut.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN, 16));
         saveBut.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
            	if(memdto.getAuth() == 0) {
    	            return;
    	         }
            	
               Singleton s = Singleton.getInstance();
               calendarDto caldto;
               int count = 0;
               String memo = placeChoice + timeCoice;
                if(placeChoice.equals("-") || timeCoice.equals("-") || choiceDate.getText().equals("-")) {
                   JOptionPane.showMessageDialog(null, "시간, 장소, 날짜를 선택해주세요");
                }else {
                   date = calYear+((calMonth+1)<10?"0":"")+(calMonth+1)+(calDayOfMon<10?"0":"")+calDayOfMon;
                   
                    System.out.println(date);
                    System.out.println(timeCoice);
                    System.out.println(placeChoice);
                   caldto = new calendarDto(date, timeCoice,  placeChoice, name);
                   s.revCtrl.InsertRev(caldto);
                   showCal();
                   comboBox.setSelectedItem("-");
                   comboBox1.setSelectedItem("-");
                   choiceDate.setText("-");
                }
               // System.out.println(calYear+((calMonth+1)<10?"0":"")+(calMonth+1)+(calDayOfMon<10?"0":"")+calDayOfMon);
                
               
            }               
         });
         
//         delBut = new JButton("Delete");
//         delBut.addActionListener(new ActionListener(){
//            public void actionPerformed(ActionEvent e) {
//                              
//            }               
//         });
         
 //        clearBut = new JButton("나가기");
 //        clearBut.addActionListener(new ActionListener(){
 //           public void actionPerformed(ActionEvent arg0) {
 //              //this.dispose();
 //           }
 //        });
         
         memoSubPanel.add(saveBut);
         //memoSubPanel.add(delBut);
   //      memoSubPanel.add(clearBut);
         memoPanel.setLayout(new BorderLayout());
//         memoPanel.add(selectedDate, BorderLayout.NORTH);
//         memoPanel.add(testField, BorderLayout.CENTER);
//         memoPanel.add(testButton, BorderLayout.WEST);
       //  memoPanel.add(memoAreaSP,BorderLayout.CENTER);
         memoPanel.add(memoSubPanel,BorderLayout.SOUTH);


      //calOpPanel, calPanel을  frameSubPanelWest에 배치
      JPanel frameSubPanelWest = new JPanel();
      Dimension calOpPanelSize1 = calOpPanel.getPreferredSize();
      calPanel.setPreferredSize(calOpPanelSize1);
   //   calOpPanelSize1.height = 200;
      calOpPanelSize1.setSize(400, 380);
      calPanel.setBackground(new Color(217, 217, 217));
  //    calPanel.setBorder(b2);
      Dimension calOpPanelSize = calOpPanel.getPreferredSize();
 //     calOpPanelSize.height = 80;
      calOpPanelSize.setSize(400, 80);
      calOpPanel.setPreferredSize(calOpPanelSize);
      calOpPanel.setBackground(new Color(217, 217, 217));
 //     frameSubPanelWest.setBorder(b2);
      frameSubPanelWest.setBounds(100, 0, 450, 450);
      frameSubPanelWest.setBackground(new Color(217, 217, 217));
      frameSubPanelWest.add(calOpPanel);
      frameSubPanelWest.add(calPanel);

      //infoPanel, memoPanel을  frameSubPanelEast에 배치
      JPanel frameSubPanelEast = new JPanel();
      Dimension infoPanelSize=infoPanel.getPreferredSize();
      infoPanelSize.height = 65;
      infoPanel.setPreferredSize(infoPanelSize);
      frameSubPanelEast.setLayout(new BorderLayout());
      frameSubPanelEast.setBounds(550, 0, 450, 450);
      frameSubPanelEast.setBackground(new Color(217, 217, 217));
     // frameSubPanelEast.add(infoPanel,BorderLayout.NORTH);
      frameSubPanelEast.add(memoPanel,BorderLayout.CENTER);

//      Dimension frameSubPanelWestSize = frameSubPanelWest.getPreferredSize();
//      frameSubPanelWestSize.width = 410;
//      frameSubPanelWest.setPreferredSize(frameSubPanelWestSize);
      

      JPanel topPanel = new JPanel();
      topPanel.setLayout(null);
      topPanel.setBounds(0, 0, 1150, 240);
      topPanel.setBackground(Color.white);

      
      mainbtn = new JButton(new ImageIcon("img/logo1.jpg")); // 로고 btn
      mainbtn.setBounds(15, 10, 190, 190);
      mainbtn.setBorderPainted(false); 	// 경계선 안보이게
      mainbtn.setFocusPainted(false); 	// 버튼 선택되었을때 생기는 테두리 안보이게
      mainbtn.setContentAreaFilled(false); // 자바 기본버튼 컬러 안보이게
      mainbtn.addActionListener(this); 
	  //mainbtn.setOpaque(false); // 투명화 
      topPanel.add(mainbtn);
      
      menubtn1 = new JButton(new ImageIcon("img/menubtn1.png")); 
		menubtn1.setBounds(300, 90, 110, 100);
		menubtn1.setBorderPainted(false); 	// 경계선 안보이게
		menubtn1.setFocusPainted(false); 	// 버튼 선택되었을때 생기는 테두리 안보이게
		menubtn1.setContentAreaFilled(false); // 자바 기본버튼 컬러 안보이게
		menubtn1.addActionListener(this); 
		menubtn1.setOpaque(false); // 투명화 
		
		menutextbtn1 = new JButton(new ImageIcon("img/menutextbtn1.png")); 
		menutextbtn1.setBounds(260, 110, 130, 80);
		menutextbtn1.setBorderPainted(false); 	// 경계선 안보이게
		menutextbtn1.setFocusPainted(false); 	// 버튼 선택되었을때 생기는 테두리 안보이게
		menutextbtn1.setContentAreaFilled(false); // 자바 기본버튼 컬러 안보이게
		menutextbtn1.addActionListener(this); 
		 topPanel.add(menutextbtn1);
		
		menubtn2 = new JButton(new ImageIcon("img/menubtn2.png")); 
		menubtn2.setBounds(500, 90, 110, 100);
		menubtn2.setBorderPainted(false); 	// 경계선 안보이게
		menubtn2.setFocusPainted(false); 	// 버튼 선택되었을때 생기는 테두리 안보이게
		menubtn2.setContentAreaFilled(false); // 자바 기본버튼 컬러 안보이게
		menubtn2.addActionListener(this); 
		
		menutextbtn2 = new JButton(new ImageIcon("img/menutextbtn2.png")); 
		menutextbtn2.setBounds(405, 110, 130, 80);
		menutextbtn2.setBorderPainted(false); 	// 경계선 안보이게
		menutextbtn2.setFocusPainted(false); 	// 버튼 선택되었을때 생기는 테두리 안보이게
		menutextbtn2.setContentAreaFilled(false); // 자바 기본버튼 컬러 안보이게
		menutextbtn2.addActionListener(this); 
		 topPanel.add(menutextbtn2);
		
		menubtn3 = new JButton(new ImageIcon("img/menubtn3.png")); 
		menubtn3.setBounds(700, 90, 100, 100);
		menubtn3.setBorderPainted(false); 	// 경계선 안보이게
		menubtn3.setFocusPainted(false); 	// 버튼 선택되었을때 생기는 테두리 안보이게
		menubtn3.setContentAreaFilled(false); // 자바 기본버튼 컬러 안보이게
		menubtn3.addActionListener(this); 
		menubtn3.setFont(new Font("돋움", Font.BOLD, 15));

		menutextbtn3 = new JButton(new ImageIcon("img/menutextbtn3.png")); 
		menutextbtn3.setBounds(537, 110, 130, 80);
		menutextbtn3.setBorderPainted(false); 	// 경계선 안보이게
		menutextbtn3.setFocusPainted(false); 	// 버튼 선택되었을때 생기는 테두리 안보이게
		menutextbtn3.setContentAreaFilled(false); // 자바 기본버튼 컬러 안보이게
		menutextbtn3.addActionListener(this); 
		 topPanel.add(menutextbtn3);
		
		menubtn4 = new JButton(new ImageIcon("img/menubtn4.png")); 
		menubtn4.setBounds(900, 90, 100, 100);
		menubtn4.setBorderPainted(false); 	// 경계선 안보이게
		menubtn4.setFocusPainted(false); 	// 버튼 선택되었을때 생기는 테두리 안보이게
		menubtn4.setContentAreaFilled(false); // 자바 기본버튼 컬러 안보이게
		menubtn4.addActionListener(this); 
		
		menutextbtn4 = new JButton(new ImageIcon("img/menutextbtn4.png")); 
		menutextbtn4.setBounds(662, 110, 130, 80);
		menutextbtn4.setBorderPainted(false); 	// 경계선 안보이게
		menutextbtn4.setFocusPainted(false); 	// 버튼 선택되었을때 생기는 테두리 안보이게
		menutextbtn4.setContentAreaFilled(false); // 자바 기본버튼 컬러 안보이게
		menutextbtn4.addActionListener(this); 
		 topPanel.add(menutextbtn4);
		
		menubtn5 = new JButton(new ImageIcon("img/menubtn5.png")); 
		menubtn5.setBounds(1050, 90, 100, 100);
		menubtn5.setBorderPainted(false); 	// 경계선 안보이게
		menubtn5.setFocusPainted(false); 	// 버튼 선택되었을때 생기는 테두리 안보이게
		menubtn5.setContentAreaFilled(false); // 자바 기본버튼 컬러 안보이게
		menubtn5.addActionListener(this); 
		
		menutextbtn5 = new JButton(new ImageIcon("img/menutextbtn5.png")); 
		menutextbtn5.setBounds(795, 110, 130, 80);
		menutextbtn5.setBorderPainted(false); 	// 경계선 안보이게
		menutextbtn5.setFocusPainted(false); 	// 버튼 선택되었을때 생기는 테두리 안보이게
		menutextbtn5.setContentAreaFilled(false); // 자바 기본버튼 컬러 안보이게
		menutextbtn5.addActionListener(this); 
		 topPanel.add(menutextbtn5);
		
		loginbtn = new JButton("login");
		loginbtn.setBounds(880, 10, 80, 30);
		loginbtn.setBorderPainted(false); 	// 경계선 안보이게
		loginbtn.setFocusPainted(false); 	// 버튼 선택되었을때 생기는 테두리 안보이게
		loginbtn.setContentAreaFilled(false); // 자바 기본버튼 컬러 안보이게
		loginbtn.addActionListener(this); 
		loginbtn.setFont(new Font("윤고딕", Font.BOLD, 15));
		 
		
		joinbtn = new JButton(" join");
		joinbtn.setBounds(930, 10, 80, 30);
		joinbtn.setBorderPainted(false); 	// 경계선 안보이게
		joinbtn.setFocusPainted(false); 	// 버튼 선택되었을때 생기는 테두리 안보이게
		joinbtn.setContentAreaFilled(false); // 자바 기본버튼 컬러 안보이게
		joinbtn.addActionListener(this); 
		joinbtn.setFont(new Font("윤고딕", Font.BOLD, 15));
		
		btn_logOut = new JButton(" logout");
		btn_logOut.setBounds(1000, 10, 120, 30);
		btn_logOut.setBorderPainted(false); 	// 경계선 안보이게
		btn_logOut.setFocusPainted(false); 	// 버튼 선택되었을때 생기는 테두리 안보이게
		btn_logOut.setContentAreaFilled(false); // 자바 기본버튼 컬러 안보이게
		btn_logOut.addActionListener(this); 
		btn_logOut.setFont(new Font("윤고딕", Font.BOLD, 15));
		
		Label label3 = new Label(memdto.getName() + " 님 환영합니다");  
		label3.setBounds(800, 15, 200, 25);
		label3.setBackground(Color.WHITE);
		label3.setFont(new Font("윤고딕", Font.BOLD, 15));
		
		if(memdto.getName().equals("guest")) {
			topPanel.add(loginbtn);
			topPanel.add(joinbtn);
		}else {
			topPanel.add(btn_logOut);
			topPanel.add(label3);
		}
		
		
/*		// 테두리 만들기
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new LineBorder(Color.black));
		panel.setBounds(90, 260, 400, 520);
		getContentPane().add(panel);
		panel.setLayout(null);
		*/
	
		menutextbtn1.addMouseListener(new MouseAdapter(){ 
	       //마우스가 버튼위로 들어왔을때 
	         public void mouseEntered(MouseEvent e){ 
	         ((JButton)e.getSource()).setIcon(new ImageIcon("img/menubtn1.png")); 
	         topPanel.add(menutextbtn1);
		 //       menubtn1.setVisible(false);
	         } 
	         public void mouseExited(MouseEvent e){ 
	       // 	 menubtn1.setVisible(true);
	        	 ((JButton)e.getSource()).setIcon(new ImageIcon("img/menutextbtn1.png"));
	          } 
	       }); 
		
		menutextbtn2.addMouseListener(new MouseAdapter(){ 
		         public void mouseEntered(MouseEvent e){ 
		         ((JButton)e.getSource()).setIcon(new ImageIcon("img/menubtn2.png")); 
		         topPanel.add(menutextbtn2);
		         } 
		         public void mouseExited(MouseEvent e){ 
		        	 ((JButton)e.getSource()).setIcon(new ImageIcon("img/menutextbtn2.png"));
		          } 
		       }); 
		menutextbtn3.addMouseListener(new MouseAdapter(){ 
	         public void mouseEntered(MouseEvent e){ 
	         ((JButton)e.getSource()).setIcon(new ImageIcon("img/menubtn3.png")); 
	         topPanel.add(menutextbtn3);
	         } 
	         public void mouseExited(MouseEvent e){ 
	        	 ((JButton)e.getSource()).setIcon(new ImageIcon("img/menutextbtn3.png"));
	          } 
	       }); 
		
		menutextbtn4.addMouseListener(new MouseAdapter(){ 
	         public void mouseEntered(MouseEvent e){ 
	         ((JButton)e.getSource()).setIcon(new ImageIcon("img/menubtn4.png")); 
	         topPanel.add(menutextbtn4);
	         } 
	         public void mouseExited(MouseEvent e){ 
	        	 ((JButton)e.getSource()).setIcon(new ImageIcon("img/menutextbtn4.png"));
	          } 
	       }); 
		
		menutextbtn5.addMouseListener(new MouseAdapter(){ 
	         public void mouseEntered(MouseEvent e){ 
	         ((JButton)e.getSource()).setIcon(new ImageIcon("img/menubtn5.png")); 
	         topPanel.add(menutextbtn5);
	         } 
	         public void mouseExited(MouseEvent e){ 
	        	 ((JButton)e.getSource()).setIcon(new ImageIcon("img/menutextbtn5.png"));
	          } 
	       }); 
	
		
		loginbtn.addMouseListener(new MouseAdapter(){ 
		        public void mouseEntered(MouseEvent e){ 
		        loginbtn.setForeground(Color.blue);
		         } 
		         public void mouseExited(MouseEvent e){ 
				    loginbtn.setForeground(Color.black);
		          } 
		       });
		joinbtn.addMouseListener(new MouseAdapter(){ 
	        public void mouseEntered(MouseEvent e){ 
	        	joinbtn.setForeground(Color.blue);
	         } 
	         public void mouseExited(MouseEvent e){ 
	        	 joinbtn.setForeground(Color.black);
	          } 
	       }); 
		

      
      JPanel lowPanel = new JPanel();
      lowPanel.setLayout(null);
      lowPanel.setBounds(0, 240, 1150, 900);
      lowPanel.setBackground(Color.white);
      lowPanel.add(frameSubPanelWest);
      lowPanel.add(frameSubPanelEast);
      
      Color sepaColor = new Color(217, 217, 217);
      JSeparator separator = new JSeparator();
      separator.setBounds(162, 200, 830, 1);
      separator.setForeground(sepaColor);
      topPanel.add(separator);
      

      
      mainFrame.setLayout(null);
      mainFrame.add(topPanel);
      mainFrame.add(lowPanel);
      mainFrame.setVisible(true);

      focusToday();
      ThreadConrol threadCnl = new ThreadConrol();
      threadCnl.start(); 
   }
   
   
   private void focusToday(){
      if(today.get(Calendar.DAY_OF_WEEK) == 1)
         dateButs[today.get(Calendar.WEEK_OF_MONTH)][today.get(Calendar.DAY_OF_WEEK)-1].requestFocusInWindow();
      else
         dateButs[today.get(Calendar.WEEK_OF_MONTH)-1][today.get(Calendar.DAY_OF_WEEK)-1].requestFocusInWindow();
   }
   
   
   private void readMemo(String date){
      
//      Connection conn = null;
//      PreparedStatement psmt = null;
//      ResultSet rs = null;
//      boolean findId = false;
//      String sql = "SELECT BOOKDATE, BOOKTIME, BOOKPLACE, NAME"
//            + " FROM BOOKINGBOARD "
//            + " WHERE BOOKDATE = ? ";
//      
//      try{
//         conn = DBConnection.getConnection();
//         System.out.println("2/6 getBbsList Success");
//         
//         psmt = conn.prepareStatement(sql);
//         System.out.println("3/6 getBbsList Success");
//         
//         psmt.setString(1, date);
//         
//         rs = psmt.executeQuery();
//         System.out.println("4/6 getBbsList Success");
//         
//         while(rs.next()){
//            findId = true;
//            int i = 1;
//            
//            calendarDto dto = new calendarDto(rs.getString(i++),    // SEQ
//                              rs.getString(i++),    // VOTECONTENT
//                              rs.getString(i++),    // VOTESTART
//                              rs.getString(i++)    // VOTEEND
//                              );   // ABSTAIN
//            list.add(dto);
//         }
//         String str[] = new String[list.size()];
//         //File f = new File("MemoData/"+calYear+((calMonth+1)<10?"0":"")+(calMonth+1)+(calDayOfMon<10?"0":"")+calDayOfMon+".txt");
//         // System.out.println(calYear+((calMonth+1)<10?"0":"")+(calMonth+1)+(calDayOfMon<10?"0":"")+calDayOfMon);
//         
//         if(findId){
//            for (int i = 0; i < list.size(); i++) {
//               str[i] = list.get(i).getBooktime() + " " + list.get(i).getBookplace() + " " + list.get(i).getName();
//               
//               memoArea.append(str[i] + "\n");
//            }
//            
//            //memoArea.setText(memoAreaText);
//            //in.close();   
//         }
//         else memoArea.setText("");
//      }catch (Exception e){
//         e.printStackTrace();
//      }finally{
//         DBClose.close(psmt, conn, rs);
//         System.out.println("6/6 getBbsList Success");
//      }
   }
   
   
   private void showCal(){
      for(int i=0;i<CAL_HEIGHT;i++){
         for(int j=0;j<CAL_WIDTH;j++){
            String fontColor="black";
            if(j==0) fontColor="red";
            else if(j==6) fontColor="blue";
            
            File f =new File("MemoData/"+calYear+((calMonth+1)<10?"0":"")+(calMonth+1)+(calDates[i][j]<10?"0":"")+calDates[i][j]+".txt");
            if(f.exists()){
               dateButs[i][j].setText("<html><b><font color="+fontColor+">"+calDates[i][j]+"</font></b></html>");
            }
            else dateButs[i][j].setText("<html><font color="+fontColor+">"+calDates[i][j]+"</font></html>");

            JLabel todayMark = new JLabel("<html><font color=green>*</html>");
            dateButs[i][j].removeAll();
            if(calMonth == today.get(Calendar.MONTH) &&
                  calYear == today.get(Calendar.YEAR) &&
                  calDates[i][j] == today.get(Calendar.DAY_OF_MONTH)){
               dateButs[i][j].add(todayMark);
               dateButs[i][j].setToolTipText("Today");
            }
            
            if(calDates[i][j] == 0) dateButs[i][j].setVisible(false);
            else dateButs[i][j].setVisible(true);
         }
      }
   }
   
   //버튼 리스너 
   private class ListenForCalOpButtons implements ActionListener{
      public void actionPerformed(ActionEvent e) {
         if(e.getSource() == todayBut){
            setToday();
            lForDateButs.actionPerformed(e);
            focusToday();
         }
         else if(e.getSource() == lYearBut) moveMonth(-12);
         else if(e.getSource() == lMonBut) moveMonth(-1);
         else if(e.getSource() == nMonBut) moveMonth(1);
         else if(e.getSource() == nYearBut) moveMonth(12);
         
         curMMYYYYLab.setText("<html><table width=100><tr><th><font size=5>"+((calMonth+1)<10?"&nbsp;":"")+(calMonth+1)+" / "+calYear+"</th></tr></table></html>");
         showCal();
      }
   }
   
   //여기서 달력날짜 클릭을 판단함
   private class listenForDateButs implements ActionListener{
      Singleton s = Singleton.getInstance();
      calendarDto caldto;
      public void actionPerformed(ActionEvent e) {
         
         int k=0,l=0;
         for(int i=0 ; i<CAL_HEIGHT ; i++){
            for(int j=0 ; j<CAL_WIDTH ; j++){
               if(e.getSource() == dateButs[i][j]){ 
                  k=i;
                  l=j;
               }
            }
         }
   
         if(!(k ==0 && l == 0)) calDayOfMon = calDates[k][l]; //today버튼을 눌렀을때도 이 actionPerformed함수가 실행되기 때문에 넣은 부분

         cal = new GregorianCalendar(calYear,calMonth,calDayOfMon);
         
         String dDayString = new String();
         int dDay=((int)((cal.getTimeInMillis() - today.getTimeInMillis())/1000/60/60/24));
         if(dDay == 0 && (cal.get(Calendar.YEAR) == today.get(Calendar.YEAR)) 
               && (cal.get(Calendar.MONTH) == today.get(Calendar.MONTH))
               && (cal.get(Calendar.DAY_OF_MONTH) == today.get(Calendar.DAY_OF_MONTH))) dDayString = "Today"; 
         else if(dDay >=0) dDayString = "D-"+(dDay+1);
         else if(dDay < 0) dDayString = "D+"+(dDay)*(-1);
         //choiceDate.setText("");
        choiceDate.setText(calYear+ "."+((calMonth+1)<10?"0":"")+(calMonth+1)+ "."+(calDayOfMon<10?"0":"")+calDayOfMon);
         caldto = new calendarDto(calYear+((calMonth+1)<10?"0":"")+(calMonth+1)+(calDayOfMon<10?"0":"")+calDayOfMon);
         list = s.revCtrl.getRevList(caldto);
         
         String str[] = new String[list.size()];
         //File f = new File("MemoData/"+calYear+((calMonth+1)<10?"0":"")+(calMonth+1)+(calDayOfMon<10?"0":"")+calDayOfMon+".txt");
         // System.out.println(calYear+((calMonth+1)<10?"0":"")+(calMonth+1)+(calDayOfMon<10?"0":"")+calDayOfMon);
         
         if(!(list.size()==0)){
            testArea.setText("");
            for (int i = 0; i < list.size(); i++) {
               str[i] = "예약시간:" + list.get(i).getBooktime() + " / 예약장소:" + list.get(i).getBookplace() + "\n" + " / 예약자 :" + list.get(i).getName() + "\n" + "--------------------" ;
               System.out.println( "텍스트필드에 들어가야할 값 : " +str[i] + " ");
               //memoArea.append(str[i] + "\n");
               //memoArea.setText(str[i]);
               testArea.append(str[i] + "\n");
            }
            
            
            //memoArea.setText(memoAreaText);
            //in.close();   
         }
         else testArea.setText("");
         //selectedDate.setText("<Html><font size=3>"+(calMonth+1)+"/"+calDayOfMon+"/"+calYear+"&nbsp;("+dDayString+")</html>");
         //System.out.println(dDayString + "클릭클릭");
         readMemo(date);
      }
   }
   
   //실시간 현재시각 표기
   private class ThreadConrol extends Thread{
      public void run(){
         boolean msgCntFlag = false;
         int num = 0;
         String curStr = new String();
         while(true){
            try{
               today = Calendar.getInstance();
               String amPm = (today.get(Calendar.AM_PM)==0?"AM":"PM");
               String hour;
                     if(today.get(Calendar.HOUR) == 0) hour = "12"; 
                     else if(today.get(Calendar.HOUR) == 12) hour = " 0";
                     else hour = (today.get(Calendar.HOUR)<10?" ":"")+today.get(Calendar.HOUR);
               String min = (today.get(Calendar.MINUTE)<10?"0":"")+today.get(Calendar.MINUTE);
               String sec = (today.get(Calendar.SECOND)<10?"0":"")+today.get(Calendar.SECOND);
               infoClock.setText(amPm+" "+hour+":"+min+":"+sec);

               sleep(1000);
               String infoStr = bottomInfo.getText();
               
               if(infoStr != " " && (msgCntFlag == false || curStr != infoStr)){
                  num = 5;
                  msgCntFlag = true;
                  curStr = infoStr;
               }
               else if(infoStr != " " && msgCntFlag == true){
                  if(num > 0) num--;
                  else{
                     msgCntFlag = false;
                     bottomInfo.setText(" ");
                  }
               }      
            }
            catch(InterruptedException e){
               System.out.println("Thread:Error");
            }
         }
      }
   }

@Override
public void actionPerformed(ActionEvent e) {
	JButton btn = (JButton)e.getSource();
	Singleton s = Singleton.getInstance();
	String str = btn.getLabel();
	
	if(btn == loginbtn){
		new loginView();
		mainFrame.setVisible(false);
	}
		
	if(btn == joinbtn){
		s.memCtrl.regi();
		mainFrame.setVisible(false);
	}
	
	if(btn == btn_logOut) {
		new mainView(new MemberDto("", "", "guest", "", "", 0));
		mainFrame.setVisible(false);
	}
	if(btn == mainbtn) {
		new mainView(memdto);
		mainFrame.setVisible(false);
	}
	
	if(btn == menutextbtn1) {
		s.BasCtrl.BasicBBSView(memdto);
		mainFrame.setVisible(false);
	}
	
    if (btn == menutextbtn2) {     
    	s.voteCtrl.voteList(memdto);
    	s.voteCtrl.voteRenewal();
		mainFrame.setVisible(false);
    }
    
    if (btn == menutextbtn3) {     
    	s.marketCtrl.getBbsList(memdto);
		mainFrame.setVisible(false);
    }
    
    if (btn == menutextbtn4) {     
    	SwingUtilities.invokeLater(new Runnable(){
            public void run(){
               new MemoCalendar(memdto);
            }           
         });
		mainFrame.setVisible(false);
    }
	
    if (btn == menutextbtn5) {
        if(memdto.getAuth() == 0) {
           return;
        }
        s.chatCtrl.mainChat(memdto.getName(), memdto.getAuth());
    }


   
	}
}