package dao;

import java.util.List;

import dto.NoticeBoardDto;

public interface NoticeBoardDaoImpl {

	public List<NoticeBoardDto> getNoticeBoard();
	public NoticeBoardDto noticeBoardDetail(NoticeBoardDto noticedto);
	public List<NoticeBoardDto> getNewestBoard();
}
