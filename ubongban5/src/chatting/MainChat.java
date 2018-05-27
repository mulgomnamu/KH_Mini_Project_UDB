package chatting;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import view.emoticonView;

public class MainChat extends JFrame implements ActionListener, Runnable {
	
	JList<String> roomInfo,roomInwon,waitInfo;
	JScrollPane sp_roomInfo, sp_roomInwon, sp_waitInfo;
	JButton bt_create, bt_enter, bt_exit;
	   
	JPanel p;
	ChatClient cc;
	emoticonView emoView;
	   
	//소켓 입출력객체
	BufferedReader in;
	OutputStream out;
	   
	String selectedRoom;
	String receivedMsg[];
	String imo[];
	   
	static String nickName;
	static int auth;

	public MainChat(String nickName, int auth) {	 
		setTitle("대기실");
		 
		this.nickName = nickName;
		this.auth = auth;
		 
		cc = new ChatClient();
		emoView = new emoticonView();
		roomInfo = new JList<String>();
		roomInfo.setBorder(new TitledBorder("방정보"));
		   
		roomInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String str = roomInfo.getSelectedValue(); //"자바방--1"
				if(str==null)return;
				System.out.println("STR="+str);
				selectedRoom = str.substring(0, str.indexOf("-"));
				//"자바방"  <----  substring(0,3)
				 
				//대화방 내의 인원정보
				sendMsg("170|"+selectedRoom);
			}	 
		});
		   
		   
		roomInwon = new JList<String>();
		roomInwon.setBorder(new TitledBorder("인원정보"));
		waitInfo = new JList<String>();
		waitInfo.setBorder(new TitledBorder("대기실정보"));
	       
		sp_roomInfo = new JScrollPane(roomInfo);
		sp_roomInwon = new JScrollPane(roomInwon);
		sp_waitInfo = new JScrollPane(waitInfo);
	     
		bt_create = new JButton("방만들기");
		bt_enter = new JButton("방들어가기");
		bt_exit = new JButton("나가기");     
	     
		p = new JPanel();
	     
		sp_roomInfo.setBounds(10, 10, 400, 540);
		sp_roomInwon.setBounds(415, 10, 150, 540);
		sp_waitInfo.setBounds(10, 560, 400, 110);
	     
		bt_create.setBounds(415,560,150,30);
		bt_enter.setBounds(415,600,150,30);
		bt_exit.setBounds(415,640,150,30);
	     
		p.setLayout(null);
		p.setBackground(Color.orange);
		p.add(sp_roomInfo);
		p.add(sp_roomInwon);
		p.add(sp_waitInfo);
		p.add(bt_create);
		p.add(bt_enter);
		p.add(bt_exit);
	     
		add(p);
		setBounds(300,200, 600, 750);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
	     
		connect();//서버연결시도 (in,out객체생성)
		new Thread(this).start();//서버메시지 대기
		sendMsg("100|");//(대기실)접속 알림
		sendMsg("150|"+nickName);//대화명 전달
	     
		eventUp();
	     
	     
		if(auth == 1) {
			//방제목
			String title = "문의";
			//방제목을 서버에게 전달
			sendMsg("160|"+title);
				 
			cc.setTitle("채팅방-["+title+"]");
				 
			sendMsg("175|");//대화방내 인원정보 요청	 
				 
			setVisible(false);
			cc.setVisible(true); //대화방이동
		    	 
		}
	     
	     
	}//생성자
	   
	private void eventUp(){//이벤트소스-이벤트처리부 연결
		//대기실(MainChat)
		bt_create.addActionListener(this);
		bt_enter.addActionListener(this);
		bt_exit.addActionListener(this);
		   
		//대화방(ChatClient)
		cc.sendTF.addActionListener(this);
		cc.bt_exit.addActionListener(this);
		cc.bt_emo.addActionListener(this);
		
		//이모티콘
		emoView.getBtn_1().addActionListener(this);
		emoView.getBtn_2().addActionListener(this);
		emoView.getBtn_3().addActionListener(this);
		emoView.getBtn_4().addActionListener(this);
		emoView.getBtn_5().addActionListener(this);
		emoView.getBtn_6().addActionListener(this);
		emoView.getBtn_7().addActionListener(this);
		emoView.getBtn_8().addActionListener(this);
		emoView.getBtn_9().addActionListener(this);
		emoView.getBtn_10().addActionListener(this);
		emoView.getBtn_11().addActionListener(this);
		emoView.getBtn_12().addActionListener(this);
		emoView.getBtn_13().addActionListener(this);
		emoView.getBtn_14().addActionListener(this);
		emoView.getBtn_15().addActionListener(this);
		emoView.getBtn_16().addActionListener(this);
	}
	   
	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if(ob==bt_create){//방만들기 요청
			String title = JOptionPane.showInputDialog(this,"방제목:");
			//방제목을 서버에게 전달
			sendMsg("160|"+title);
			 
			cc.setTitle("채팅방-["+title+"]");
			 
			sendMsg("175|");//대화방내 인원정보 요청	 
			 
			setVisible(false);
			cc.setVisible(true); //대화방이동
			 
		}else if(ob==bt_enter){//방들어가기 요청
			 
			if(selectedRoom == null){
				JOptionPane.showMessageDialog(this, "방을 선택!!");
				return;
			}
			  
			sendMsg("200|"+ selectedRoom);
			  
			sendMsg("175|");//대화방내 인원정보 요청
			 
			setVisible(false);
			cc.setVisible(true);
		}else if(ob==cc.bt_exit && auth == 1) {
			JOptionPane.showMessageDialog(this, auth);
			
			sendMsg("400|");
			
			setVisible(false);
			cc.setVisible(false);
			
		}else if(ob==cc.bt_exit && auth != 1){//대화방 나가기 요청
			sendMsg("400|");
			 
			cc.setVisible(false);
			setVisible(true); 
		}else if(ob==cc.sendTF){//(TextField입력)메시지 보내기 요청
			String msg = cc.sendTF.getText();
			if(msg.length()>0){
				sendMsg("300|"+msg); 
				cc.sendTF.setText("");
			}
		}else if(ob==cc.bt_emo) {
			emoView.setVisible(true);
		}else if(ob==emoView.getBtn_1()){
			String tftext = cc.sendTF.getText() + "-피카츄-";
			cc.sendTF.setText(tftext);
			emoView.setVisible(false);
			cc.sendTF.grabFocus();
		}else if(ob==emoView.getBtn_2()){
			String tftext = cc.sendTF.getText() + "-파이리-";
			cc.sendTF.setText(tftext);
			emoView.setVisible(false);
			cc.sendTF.grabFocus();
		}else if(ob==emoView.getBtn_3()){
			String tftext = cc.sendTF.getText() + "-미뇽-";
			cc.sendTF.setText(tftext);
			emoView.setVisible(false);
			cc.sendTF.grabFocus();
		}else if(ob==emoView.getBtn_4()){
			String tftext = cc.sendTF.getText() + "-잠만보-";
			cc.sendTF.setText(tftext);
			emoView.setVisible(false);
			cc.sendTF.grabFocus();
		}else if(ob==emoView.getBtn_5()){
			String tftext = cc.sendTF.getText() + "-휴가-";
			cc.sendTF.setText(tftext);
			emoView.setVisible(false);
			cc.sendTF.grabFocus();
		}else if(ob==emoView.getBtn_6()){
			String tftext = cc.sendTF.getText() + "-웃겨-";
			cc.sendTF.setText(tftext);
			emoView.setVisible(false);
			cc.sendTF.grabFocus();
		}else if(ob==emoView.getBtn_7()){
			String tftext = cc.sendTF.getText() + "-안녕-";
			cc.sendTF.setText(tftext);
			emoView.setVisible(false);
			cc.sendTF.grabFocus();
		}else if(ob==emoView.getBtn_8()){
			String tftext = cc.sendTF.getText() + "-슬픔-";
			cc.sendTF.setText(tftext);
			emoView.setVisible(false);
			cc.sendTF.grabFocus();
		}else if(ob==emoView.getBtn_9()){
			String tftext = cc.sendTF.getText() + "-졸림-";
			cc.sendTF.setText(tftext);
			emoView.setVisible(false);
			cc.sendTF.grabFocus();
		}else if(ob==emoView.getBtn_10()){
			String tftext = cc.sendTF.getText() + "-삐짐-";
			cc.sendTF.setText(tftext);
			emoView.setVisible(false);
			cc.sendTF.grabFocus();
		}else if(ob==emoView.getBtn_11()){
			String tftext = cc.sendTF.getText() + "-심심해-";
			cc.sendTF.setText(tftext);
			emoView.setVisible(false);
			cc.sendTF.grabFocus();
		}else if(ob==emoView.getBtn_12()){
			String tftext = cc.sendTF.getText() + "-미안-";
			cc.sendTF.setText(tftext);
			emoView.setVisible(false);
			cc.sendTF.grabFocus();
		}else if(ob==emoView.getBtn_13()){
			String tftext = cc.sendTF.getText() + "-약올리기-";
			cc.sendTF.setText(tftext);
			emoView.setVisible(false);
			cc.sendTF.grabFocus();
		}else if(ob==emoView.getBtn_14()){
			String tftext = cc.sendTF.getText() + "-잘자-";
			cc.sendTF.setText(tftext);
			emoView.setVisible(false);
			cc.sendTF.grabFocus();
		}else if(ob==emoView.getBtn_15()){
			String tftext = cc.sendTF.getText() + "-배고파-";
			cc.sendTF.setText(tftext);
			emoView.setVisible(false);
			cc.sendTF.grabFocus();
		}else if(ob==emoView.getBtn_16()){
			String tftext = cc.sendTF.getText() + "-물마시기-";
			cc.sendTF.setText(tftext);
			emoView.setVisible(false);
			cc.sendTF.grabFocus();
		}else if(ob==bt_exit){//나가기(프로그램종료) 요청
			setVisible(false);
			cc.setVisible(false);
//			  System.exit(0);//현재 응용프로그램 종료하기
		}
		  
	}//actionPerformed 
	   
	public void connect(){//(소켓)서버연결 요청
		try {
			//Socket s = new Socket(String host<서버ip>, int port<서비스번호>); 
			Socket s = new Socket("localhost", 9000);//연결시도  
//			Socket s = new Socket("192.168.10.48", 9000);//연결시도  
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			//in: 서버메시지 읽기객체    서버-----msg------>클라이언트
			out = s.getOutputStream();
			//out: 메시지 보내기, 쓰기객체    클라이언트-----msg----->서버
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}//connect
	   
	
	public void sendMsg(String msg){//서버에게 메시지 보내기
		try {
			out.write(  (msg+"\n").getBytes()  );
		}catch (IOException e) {
			e.printStackTrace();
		}
	}//sendMsg
	
	public void append(String s) {
		try {
			Document doc = cc.ta.getDocument();
			doc.insertString(doc.getLength(), s, null);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	   
	public void run(){//서버가 보낸 메시지 읽기
		//왜 run메소드 사용? GUI프로그램실행에 영향 미치지않는 코드 작성.
		//메소드호출은 순차적인 실행!!  스레드메소드는 동시실행(기다리지 않는 별도 실행)!!
		try {
			while(true){
				String msg = in.readLine();//msg: 서버가 보낸 메시지
				//msg==> "300|안녕하세요"  "160|자바방--1,오라클방--1,JDBC방--1"
				String msgs[] = msg.split("\\|");
				String protocol = msgs[0];
				switch(protocol){
					case "300":
						receivedMsg = msgs[1].split("▶");
						imo = receivedMsg[1].split("-");
						String msg_add = "";
						for (int i = 0; i < imo.length; i++) {
							
							if(imo[i].equals("피카츄")) {
								cc.ta.insertIcon(new ImageIcon("src\\chatting\\pikachu.png"));
								append(System.getProperty("line.separator"));
								cc.ta.setCaretPosition(cc.ta.getDocument().getLength());
							}else if(imo[i].equals("파이리")) {
								cc.ta.insertIcon(new ImageIcon("src\\chatting\\charmander.png"));
								append(System.getProperty("line.separator"));
								cc.ta.setCaretPosition(cc.ta.getDocument().getLength());
							}else if(imo[i].equals("미뇽")) {
								cc.ta.insertIcon(new ImageIcon("src\\chatting\\dratini.png"));
								append(System.getProperty("line.separator"));
								cc.ta.setCaretPosition(cc.ta.getDocument().getLength());
							}else if(imo[i].equals("잠만보")) {
								cc.ta.insertIcon(new ImageIcon("src\\chatting\\snorlax.png"));
								append(System.getProperty("line.separator"));
								cc.ta.setCaretPosition(cc.ta.getDocument().getLength());
							}else if(imo[i].equals("휴가")) {
								cc.ta.insertIcon(new ImageIcon("src\\chatting\\5.gif"));
								append(System.getProperty("line.separator"));
								cc.ta.setCaretPosition(cc.ta.getDocument().getLength());
							}else if(imo[i].equals("웃겨")) {
								cc.ta.insertIcon(new ImageIcon("src\\chatting\\6.gif"));
								append(System.getProperty("line.separator"));
								cc.ta.setCaretPosition(cc.ta.getDocument().getLength());
							}else if(imo[i].equals("안녕")) {
								cc.ta.insertIcon(new ImageIcon("src\\chatting\\7.gif"));
								append(System.getProperty("line.separator"));
								cc.ta.setCaretPosition(cc.ta.getDocument().getLength());
							}else if(imo[i].equals("슬픔")) {
								cc.ta.insertIcon(new ImageIcon("src\\chatting\\8.gif"));
								append(System.getProperty("line.separator"));
								cc.ta.setCaretPosition(cc.ta.getDocument().getLength());
							}else if(imo[i].equals("졸림")) {
								cc.ta.insertIcon(new ImageIcon("src\\chatting\\9.gif"));
								append(System.getProperty("line.separator"));
								cc.ta.setCaretPosition(cc.ta.getDocument().getLength());
							}else if(imo[i].equals("삐짐")) {
								cc.ta.insertIcon(new ImageIcon("src\\chatting\\10.gif"));
								append(System.getProperty("line.separator"));
								cc.ta.setCaretPosition(cc.ta.getDocument().getLength());
							}else if(imo[i].equals("심심해")) {
								cc.ta.insertIcon(new ImageIcon("src\\chatting\\11.gif"));
								append(System.getProperty("line.separator"));
								cc.ta.setCaretPosition(cc.ta.getDocument().getLength());
							}else if(imo[i].equals("미안")) {
								cc.ta.insertIcon(new ImageIcon("src\\chatting\\12.gif"));
								append(System.getProperty("line.separator"));
								cc.ta.setCaretPosition(cc.ta.getDocument().getLength());
							}else if(imo[i].equals("약올리기")) {
								cc.ta.insertIcon(new ImageIcon("src\\chatting\\13.gif"));
								append(System.getProperty("line.separator"));
								cc.ta.setCaretPosition(cc.ta.getDocument().getLength());
							}else if(imo[i].equals("잘자")) {
								cc.ta.insertIcon(new ImageIcon("src\\chatting\\14.gif"));
								append(System.getProperty("line.separator"));
								cc.ta.setCaretPosition(cc.ta.getDocument().getLength());
							}else if(imo[i].equals("배고파")) {
								cc.ta.insertIcon(new ImageIcon("src\\chatting\\15.gif"));
								append(System.getProperty("line.separator"));
								cc.ta.setCaretPosition(cc.ta.getDocument().getLength());
							}else if(imo[i].equals("물마시기")) {
								cc.ta.insertIcon(new ImageIcon("src\\chatting\\16.gif"));
								append(System.getProperty("line.separator"));
								cc.ta.setCaretPosition(cc.ta.getDocument().getLength());
							}else {
								msg_add = msg_add + imo[i];
							}
						}	
							
						append(receivedMsg[0] + "▶" + msg_add+"\n");
//						cc.ta.append(msgs[1]+"\n");
						cc.ta.setCaretPosition(cc.ta.getDocument().getLength());
				    	break;
				    	       
				    case "160"://방만들기 
				    	       //방정보를 List에 뿌리기
				    	if(msgs.length > 1){
				    		//개설된 방이 한개 이상이었을때 실행
				    		//개설된 방없음 ---->  msg="160|" 였을때 에러 
				    		String roomNames[] = msgs[1].split(",");
				    	    //"자바방--1,오라클방--1,JDBC방--1"
				    	    roomInfo.setListData(roomNames);
				    	}
				    	break;
				     
				     case "170"://(대기실에서) 대화방 인원정보
				    	   String roomInwons[] = msgs[1].split(",");
				    	   roomInwon.setListData(roomInwons);
				    	   break;
				    	   
				     case "175"://(대화방에서) 대화방 인원정보
				    	   String myRoomInwons[] = msgs[1].split(",");
				    	   cc.li_inwon.setListData(myRoomInwons);
				    	   break;
				    	   
				     case "180"://대기실 인원정보
				    	 String waitNames[] = msgs[1].split(",");
				    	 waitInfo.setListData(waitNames);
				    	 break;
				    	   
				     case "200"://대화방 입장
				    	   append("=========["+msgs[1]+"]님 입장=========\n");
						   cc.ta.setCaretPosition(cc.ta.getDocument().getLength());
				    	   break;
				    	   
				     case "400"://대화방 퇴장
				    	 append("=========["+msgs[1]+"]님 퇴장=========\n");
				    	 cc.ta.setCaretPosition(cc.ta.getDocument().getLength());
				    	 break;
				    	   
				     case "202"://개설된 방의 타이틀 제목 얻기
				    	 cc.setTitle("채팅방-["+msgs[1]+"]");
				    	 break;
				    	   
				     
				   }//클라이언트 switch
				   
			   }
		  }catch (IOException e) {
			e.printStackTrace();
		 }
	   }//run
	   
/*	   
	   public static void main(String[] args) {
		  new MainChat();
	   }
*/
}