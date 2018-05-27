package controller;

import javax.swing.JOptionPane;

import dto.MemberDto;
import service.MemberService;
import service.MemberServiceImpl;
import singleton.Singleton;
import view.accountView;
import view.loginView;

public class MemberController {
	

	MemberServiceImpl memService = new MemberService();
	
	String loginName;
	int auth;
	
	//맨 처음 mainView에 들어올 때 guest ID를 가질 수 있게 초기화 해주는 부분 차후 MEMBER DB에 GUEST 계정을 넣어 대체할 예정이나 현재는 필요함
	public MemberDto guest() {
		String str = "guest";
		MemberDto memdto = new MemberDto(str, str, str, str, str, 0);
		
		return memdto;
	}
	
	public void login() {
		new loginView();		
	}
	
	public void regi() {
		new accountView();
	}
	
	public boolean regiAf(String id, String pwd, String dong, String ho) {
	      // DB까지 가서
		boolean b = true;
		String name = dong + "동" + ho + "호";
		if (!(nameCheck(name))) {
	    	  b = memService.addMember(new MemberDto(id, pwd, dong, ho));      
		      // 저장
		      if(b) {
		         JOptionPane.showMessageDialog(null, "회원가입 성공!!");
		         // new loginView();   // 다음 View로
		      }else {
		         JOptionPane.showMessageDialog(null, "회원가입 실패");
		         // new accountView();   // 다음 View로
		      }   
		} else {
	         JOptionPane.showMessageDialog(null, "가입되어있는 아이디나 가구입니다.");
	         // new accountView();   // 다음 View로
		}
	      
	      return b;
	   }
	
	public boolean idCheck(String id) {
		return memService.idCheck(id);
	}

	public boolean nameCheck(String name) {
		return memService.nameCheck(name);
	}
	
	public void loginAf(String id, String pwd) {
		Singleton sc = Singleton.getInstance();
		
		MemberDto dto = memService.login(new MemberDto(id, pwd, null, null));
		if(dto == null) {
			JOptionPane.showMessageDialog(null, "id나 password가 틀렸습니다");
			new loginView();
		}else {
			loginName = dto.getName();
			auth = dto.getAuth();
			MemberDto loginDto = new MemberDto(null, null, loginName, null, null, auth);
			sc.voteCtrl.home(dto);
		//	sc.marketCtrl.getBbsList(loginDto);
			JOptionPane.showMessageDialog(null, loginName + "님 환영합니다");
	//		sc.BasCtrl.BasicBBSView(loginDto);
		}
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

}



