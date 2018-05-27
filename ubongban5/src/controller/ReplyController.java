package controller;

import java.util.List;

import dto.BasicBBSDto;
import dto.ReplyDto;
import service.ReplyService;
import service.ReplyServiceImpl;

public class ReplyController {
	
	ReplyServiceImpl repService = new ReplyService();
	
	public List<ReplyDto> getReplyList(BasicBBSDto b_dto){
		return repService.getReplyList(b_dto);
	}
	public boolean writeReply(ReplyDto dto) {
		return repService.writeReply(dto);
	}
	
	public boolean writeReplyReplier(ReplyDto dto, int family, int seq, int depth, int indent) {
		return repService.writeReplyReplier(dto, family, seq, depth, indent);
	}
	
	public boolean deleteReply(int seq) {
		return repService.deleteReply(seq);
	}
	
	public boolean updateReply(String content, int seq) {
		return repService.updateReply(content, seq);
	}

}
