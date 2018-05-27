package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import dao.VoteDao;
import dto.MemberDto;
import dto.VoteDto;
import service.MemberService;
import service.MemberServiceImpl;
import service.VoteDaoService;
import service.VoteDaoServiceImpl;
import view.accountView;
import view.addvoteView;
import view.graphView;
import view.loginView;
import view.mainView;
import view.voteResultView;
import view.voteView;

public class VoteController {
	String id;
	VoteDaoServiceImpl voteService= new VoteDaoService();
	MemberServiceImpl memberService = new MemberService();
	List<VoteDto> list = new ArrayList<VoteDto>();
	VoteDao dao = new VoteDao();
	VoteDto votedto = new VoteDto();
	MemberDto memdto = new MemberDto();
	
	//mainView를 들어갈 때 guest 계정을 받게하는 부분
	public void home(MemberDto memdto) {
		
		if(memdto.getName().equals("guest")) {
			memberService.getName(memdto);
		}else {
			new mainView(memdto);
		}
	}
	
	//현재 진행중인 Vote List를 가져오는 부분
	public void voteList(MemberDto memdto) {
		list = voteService.getVoteList();
		new voteView(memdto, list);
	}
	
	//관리자가 새로운 투표를 생성할 때 view창 띄움
	public void addViewVote() {
		new addvoteView();
	}
	
	//실제적으로 새로운 투표를 생성 후 데이터를 DB에 접근하게 하는 부분
	public void insertVote(VoteDto votedto) {
		voteService.addVote(votedto);
	}
	
	//사용자가 투표안건을 선택하여 찬성, 반대, 기권표 중 하나를 선택하면 그 값을 DB로 보내는 부분
	public void voteChoice(int seq, int voteResult) {
		voteService.voteChoice(seq, voteResult);
		JOptionPane.showMessageDialog(null, "투표가 완료되었습니다.");
	}
	
	//투표안건을 투표한 사람들을 뽑아오는 부분 (나중에 투표중복 체크에 쓰임)
	public void voteMemberCheck(int seq, String id) {
		voteService.voteMemberListCheckUpdate(seq, id);
	}
	
	//투표한 사람의 ID와 seq를 전해서 중복투표를 분간하는 제어문에 해당 정보를 보내는 부분
	public boolean voteMenberFind(String id, int seq) {
		boolean result = voteService.voteMemberListFind(id , seq);
		return result;
	}
	
	//투표 그래프 View에 찬반 갯수를 보냄
	public void voteGraph(int seq) {
		votedto = voteService.getAgreeDisargeeAbstain(seq);
		new graphView(votedto);
	}
	
	//voteview의 기간이 지난 투표안건에 대해 최신화를 시키는 부분
	public void voteRenewal() {
		List<VoteDto> seqlist = new ArrayList<VoteDto>();
		//VoteDto seqdto = new VoteDto();
		
		list = voteService.getVoteEndDate();
		
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i).getVoteend());
//		}
		
		Date todate_date = null;
		Date test_date = null;
		long diff = 0;
		long diffDays = 0;
		
		for (int i = 0; i < list.size(); i++) {
			String testDate = list.get(i).getVoteend();
			System.out.println(" 출력되는 날짜 : "+testDate);
//			SimpleDateFormat  formatter01 = new SimpleDateFormat("yyyy년 MM월 dd일 hh시 mm분 ss초");
//			SimpleDateFormat  formatter02 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//			SimpleDateFormat  formatter03 = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat  formatter04 = new SimpleDateFormat("yyyy-MM-dd");
			         
			//현재 날짜 구하기
//			String todate01=  formatter01.format(new Date());
//			String todate02 =  formatter02.format(new Date());
//			String todate03 =  formatter03.format(new Date());
			String todate04 =  formatter04.format(new Date());
			         
//			System.out.println("현재시간 년월일 = " + todate01);
//			System.out.println("현재시간 년월일 = " + todate02);
//			System.out.println("현재시간 년월일 = " + todate03);
			try {
				todate_date = formatter04.parse(todate04);
				test_date = formatter04.parse(testDate);
				diff = todate_date.getTime() - test_date.getTime();
				// 시간차이를 시간,분,초를 곱한 값으로 나누면 하루 단위가 나옴
				diffDays = diff / (24 * 60 * 60 * 1000);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			System.out.println(todate_date + "====== " + test_date);
//			System.out.println("날짜계산차이 : " + diff);
//			System.out.println("년차이 : " + diffDays / 365);
			
			if(diffDays >= 0) {
//				System.out.println("일차이 : " + diffDays);
//				System.out.println("시퀀스넘버" + list.get(i).getSeq());
				votedto.setSeq(list.get(i).getSeq());
				seqlist.add(votedto);
			}
			
		}
		voteService.VoteResultInsert(seqlist);
		voteService.VoteDelete(seqlist);
	}
	
	//투표가 완료된 안건을 뽑아서 view를 통해 보여주는 부분
	public void voteResult() {
		list = voteService.getVoteResult();
		new voteResultView(list);
	}
	
	//사용하지 않음
	public void insertContent(String id, String title, String content) {
//		System.out.println(id + "" + title + " "  + content);
//		dto.setId(id);
//		dto.setTitle(title);
//		dto.setContent(content);
//		bbsService.bbsWrite(dto);
	}
	
	//사용하지 않음
	public void detailContent(int rowNum, int seq, String id) {
		//new detailView(rowNum, seq, id);
	}

	
	
}





