package singleton;

import controller.BasicBBSController;
import controller.ChatController;
import controller.MarketBbsController;
import controller.MarketReplyController;
import controller.MemberController;
import controller.NoticeBoardController;
import controller.ReplyController;
import controller.ReservationController;
import controller.VoteController;
import db.DBConnection;

public class Singleton {
	
	private static Singleton single = null;
	public MemberController memCtrl;
	public VoteController voteCtrl;
	public BasicBBSController BasCtrl;
	public ReplyController repCtrl;
	public MarketBbsController marketCtrl;
	public MarketReplyController mRepCtrl;
	public ReservationController revCtrl;
	public NoticeBoardController noticeCtrl;
	public ChatController chatCtrl;
	
	private Singleton() {
		DBConnection.initConnection();
		memCtrl = new MemberController();
		voteCtrl = new VoteController();
		BasCtrl = new BasicBBSController();
		repCtrl = new ReplyController();
		marketCtrl = new MarketBbsController();
		mRepCtrl = new MarketReplyController();
		revCtrl = new ReservationController();
		noticeCtrl = new NoticeBoardController();
		chatCtrl = new ChatController();
	}
	
	public static Singleton getInstance() {
		if(single == null) {
			single = new Singleton();
		}
		return single;
	}

}
