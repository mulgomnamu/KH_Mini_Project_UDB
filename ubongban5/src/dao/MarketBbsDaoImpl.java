package dao;

import java.util.List;

import dto.MarketDto;

public interface MarketBbsDaoImpl {
	public List<MarketDto> getBbsList();	
	public List<MarketDto> getFindList(String fStr);
	
	public boolean writeBbs(MarketDto dto);	
	
	public MarketDto getBBS(int seq);	
	public void readCount(int seq);	
	
	public boolean bbsDelete(int seq);
	public boolean bbsUpdate(MarketDto dto);
	
	public void bbsSoldUpdate(MarketDto dto);
	
	public int getTotalList(Object keyWord, String selectWord, int sel);
	public List<MarketDto> selectBbsList(String title, String content);
	
}
