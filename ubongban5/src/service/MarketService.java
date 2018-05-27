package service;

import java.util.List;

import dao.MarketBbsDao;
import dto.MarketDto;

public class MarketService implements MarketServiceImpl {

	MarketBbsDao dao = new MarketBbsDao();
	
	@Override
	public List<MarketDto> getBbsList() {		
		return dao.getBbsList();
	}

	@Override
	public List<MarketDto> getFindList(String fStr) {		
		return dao.getFindList(fStr);		
	}

	@Override
	public MarketDto getBBS(int seq) {		
		return dao.getBBS(seq);		
	}

	@Override
	public void readCount(int seq) {
		dao.readCount(seq);		
	}

	@Override
	public boolean writeBbs(MarketDto dto) {
		return dao.writeBbs(dto);		
	}

	@Override
	public boolean bbsDelete(int seq) {		
		return dao.bbsDelete(seq);		
	}

	@Override
	public boolean bbsUpdate(MarketDto dto) {		
		return dao.bbsUpdate(dto);		
	}

	@Override
	public List<MarketDto> selectBbsList(String title, String content) {
		
		return dao.selectBbsList(title, content);
	}
}
