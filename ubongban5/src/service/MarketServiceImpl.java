package service;

import java.util.List;

import dto.MarketDto;

public interface MarketServiceImpl {

	public List<MarketDto> getBbsList();	
	public List<MarketDto> getFindList(String fStr);
	
	public boolean writeBbs(MarketDto dto);
	
	public MarketDto getBBS(int seq);
	public void readCount(int seq);
	
	public boolean bbsDelete(int seq);
	public boolean bbsUpdate(MarketDto dto);
	
	public List<MarketDto> selectBbsList(String title, String content);
}
