package main;

import dto.MemberDto;
import singleton.Singleton;
import view.loginView;
import view.mainView;

public class mainClass {

	public static void main(String[] args) {
		MemberDto mendto = new MemberDto();
		Singleton s = Singleton.getInstance();
		mendto = s.memCtrl.guest();
		new mainView(mendto);
	}

}
