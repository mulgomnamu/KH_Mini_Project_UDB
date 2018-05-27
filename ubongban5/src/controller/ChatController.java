package controller;

import chatting.MainChat;

public class ChatController {
	
	public void mainChat(String nickName, int auth) {
		new MainChat(nickName, auth);
	}

}
