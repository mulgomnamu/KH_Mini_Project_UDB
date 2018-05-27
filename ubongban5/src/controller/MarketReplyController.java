package controller;

import java.util.List;

import dto.MarketDto;
import dto.MarketReplyDto;
import service.MarketReplyService;
import service.MarketReplyServiceImpl;

public class MarketReplyController {
	
	MarketReplyServiceImpl MRService = new MarketReplyService();
	
	public List<MarketReplyDto> getReplyList(MarketDto m_dto){
		return MRService.getReplyList(m_dto);
	}
	
	public boolean writeReply(MarketReplyDto dto) {
		return MRService.writeReply(dto);
	}
	
	public boolean writeReplyReplier(MarketReplyDto dto, int family, int seq, int depth, int indent) {
		return MRService.writeReplyReplier(dto, family, seq, depth, indent);
	}
	
	public boolean deleteReply(int seq) {
		return MRService.deleteReply(seq);
	}
	
	public boolean updateReply(String content, int seq) {
		return MRService.updateReply(content, seq);
	}

}
