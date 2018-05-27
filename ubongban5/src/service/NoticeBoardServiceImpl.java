package service;

import java.util.List;

import dto.NoticeBoardDto;

public interface NoticeBoardServiceImpl {

	public List<NoticeBoardDto> getNoticeBoard();
	public NoticeBoardDto noticeBoardDetail(NoticeBoardDto noticedto);
	public List<NoticeBoardDto> getNewestBoard();
}
