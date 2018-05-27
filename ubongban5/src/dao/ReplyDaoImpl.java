package dao;

import java.util.List;

import dto.BasicBBSDto;
import dto.ReplyDto;

public interface ReplyDaoImpl {
	
	public List<ReplyDto> getReplyList(BasicBBSDto b_dt);
	
	public boolean writeReply(ReplyDto dto);
	
	public boolean writeReplyReplier(ReplyDto dto, int family, int seq, int depth, int indent);
	
	public boolean deleteReply(int seq);
	
	public boolean updateReply(String content, int seq);

}
