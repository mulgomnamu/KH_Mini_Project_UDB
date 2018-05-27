package service;

import java.util.List;

import dao.MarketBbsReplyDao;
import dao.MarketBbsReplyDaoImpl;
import dto.MarketDto;
import dto.MarketReplyDto;

public class MarketReplyService implements MarketReplyServiceImpl {
	
	MarketBbsReplyDaoImpl MBRDao = new MarketBbsReplyDao();

	@Override
	public List<MarketReplyDto> getReplyList(MarketDto m_dto) {
		return MBRDao.getReplyList(m_dto);
	}

	@Override
	public boolean writeReply(MarketReplyDto dto) {
		return MBRDao.writeReply(dto);
	}

	@Override
	public boolean writeReplyReplier(MarketReplyDto dto, int family, int seq, int depth, int indent) {
		return MBRDao.writeReplyReplier(dto, family, seq, depth, indent);
	}

	@Override
	public boolean deleteReply(int seq) {
		return MBRDao.deleteReply(seq);
	}

	@Override
	public boolean updateReply(String content, int seq) {
		return MBRDao.updateReply(content, seq);
	}

}
