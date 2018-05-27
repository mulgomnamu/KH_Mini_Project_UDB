package service;

import java.util.List;

import dto.BasicBBSDto;

public interface BasicBBSServiceImpl {
	
	public List<BasicBBSDto> getBasicBBSList(int curPage, int numPerPage);
	
	public int getTotalRecord(Object keyword, String select_word, int sel);
	
	public boolean writeBasicBBS(BasicBBSDto dto);
	
	public void updateBasicBBSReadcount(int seq, int readCount);
	
	public BasicBBSDto detailBasicBBS(int seq);
	
	public boolean updateBasicBBS(String title, String content, int seq);
	
	public boolean deleteBasicBBS(int seq);
	
	public boolean writeBasicBBSReplier(BasicBBSDto dto, int family, int seq, int depth, int indent);
	
	public List<BasicBBSDto> selectBasicBBSList(Object keyword, String select_word, int curPage, int numPerPage);
	
	public List<BasicBBSDto> getNewestList();
}
