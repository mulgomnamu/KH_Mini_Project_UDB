package service;

import java.util.List;

import dto.BasicBBSDto;
import dto.ReplyDto;

public interface ReplyServiceImpl {
	
	public List<ReplyDto> getReplyList(BasicBBSDto b_dto);
	
	public boolean writeReply(ReplyDto dto);
	
	public boolean writeReplyReplier(ReplyDto dto, int family, int seq, int depth, int indent);
	
	public boolean deleteReply(int seq);
	
	public boolean updateReply(String content, int seq);

}
