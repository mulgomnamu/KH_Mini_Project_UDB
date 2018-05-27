package service;

import java.util.List;

import dao.ReplyDao;
import dao.ReplyDaoImpl;
import dto.BasicBBSDto;
import dto.ReplyDto;

public class ReplyService implements ReplyServiceImpl {
	
	ReplyDaoImpl repDao = new ReplyDao();

	@Override
	public List<ReplyDto> getReplyList(BasicBBSDto b_dto) {
		return repDao.getReplyList(b_dto);
	}

	@Override
	public boolean writeReply(ReplyDto dto) {
		return repDao.writeReply(dto);
	}

	@Override
	public boolean writeReplyReplier(ReplyDto dto, int family, int seq, int depth, int indent) {
		return repDao.writeReplyReplier(dto, family, seq, depth, indent);
	}

	@Override
	public boolean deleteReply(int seq) {
		return repDao.deleteReply(seq);
	}

	@Override
	public boolean updateReply(String content, int seq) {
		return repDao.updateReply(content, seq);
	}
	
	

}
