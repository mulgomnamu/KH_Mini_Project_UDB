package controller;

import java.util.ArrayList;
import java.util.List;

import dto.BasicBBSDto;
import dto.MemberDto;
import service.BasicBBSService;
import service.BasicBBSServiceImpl;
import view.BasicBBSDetailView;
import view.BasicBBSInsertReplyView;
import view.BasicBBSInsertView;
import view.BasicBBSView;
import view.loginView;

public class BasicBBSController {
	
	BasicBBSServiceImpl BBBService = new BasicBBSService();
	
	public void BasicBBSView(MemberDto dto) {
		new BasicBBSView(dto);
	}
	
	public int getTotalRecord(Object keyword, String select_word, int sel) {
		return BBBService.getTotalRecord(keyword, select_word, sel);
	}
	
	public List<BasicBBSDto> getBasicBBSList(int curPage, int numPerPage){
		return BBBService.getBasicBBSList(curPage, numPerPage);
	}
	
	public boolean writeBasicBBS(BasicBBSDto dto) {
		return BBBService.writeBasicBBS(dto);
	}
	
	public void BasicBBSInsertView(MemberDto m_dto) {
		new BasicBBSInsertView(m_dto);
	}
	
	public void updateBasicBBSReadcount(int seq, int readCount) {
		BBBService.updateBasicBBSReadcount(seq, readCount);
	}
	
	public void BasicBBSDetailView(MemberDto m_dto, BasicBBSDto b_dto) {
		new BasicBBSDetailView(m_dto, b_dto);
	}
	
	public BasicBBSDto detailBasicBBS(int seq) {
		return BBBService.detailBasicBBS(seq);
	}
	
	public boolean updateBasicBBS(String title, String content, int seq) {
		return BBBService.updateBasicBBS(title, content, seq);
	}
	
	public boolean deleteBasicBBS(int seq) {
		return BBBService.deleteBasicBBS(seq);
	}
	
	public void loginView() {
		new loginView();
	}
	
	public void BasicBBSInsertReplyView(MemberDto m_dto, BasicBBSDto b_dto) {
		new BasicBBSInsertReplyView(m_dto, b_dto);
	}
	
	public boolean writeBasicBBSReplier(BasicBBSDto dto, int family, int seq, int depth, int indent) {
		return BBBService.writeBasicBBSReplier(dto, family, seq, depth, indent);
	}
	
	public List<BasicBBSDto> selectBasicBBSList(Object keyword, String select_word, int curPage, int numPerPage){
		return BBBService.selectBasicBBSList(keyword, select_word, curPage, numPerPage);
	}
	
	public List<BasicBBSDto> getNewestList(){
		List<BasicBBSDto> list = new ArrayList<BasicBBSDto>();
		list = BBBService.getNewestList();
		
		return list;
	}

}
