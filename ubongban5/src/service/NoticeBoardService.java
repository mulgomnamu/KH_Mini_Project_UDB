package service;

import java.util.List;

import dao.NoticeBoardDao;
import dao.NoticeBoardDaoImpl;
import dto.NoticeBoardDto;

public class NoticeBoardService implements NoticeBoardServiceImpl{

	NoticeBoardDaoImpl dao = new NoticeBoardDao();
	
	@Override
	public List<NoticeBoardDto> getNoticeBoard() {
		// TODO Auto-generated method stub
		return dao.getNoticeBoard();
	}

	@Override
	public NoticeBoardDto noticeBoardDetail(NoticeBoardDto noticedto) {
		return dao.noticeBoardDetail(noticedto);
		
	}
	
	public List<NoticeBoardDto> getNewestBoard(){
		return dao.getNewestBoard();
	}

}
