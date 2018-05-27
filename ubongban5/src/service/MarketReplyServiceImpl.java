package service;

import java.util.List;

import dto.MarketDto;
import dto.MarketReplyDto;

public interface MarketReplyServiceImpl {
	
	public List<MarketReplyDto> getReplyList(MarketDto m_dto);
	
	public boolean writeReply(MarketReplyDto dto);
	
	public boolean writeReplyReplier(MarketReplyDto dto, int family, int seq, int depth, int indent);
	
	public boolean deleteReply(int seq);
	
	public boolean updateReply(String content, int seq);

}
