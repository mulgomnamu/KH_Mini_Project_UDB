package controller;

import java.util.ArrayList;
import java.util.List;

import dto.NoticeBoardDto;
import service.NoticeBoardService;
import service.NoticeBoardServiceImpl;
import view.NoticeDetailView;

public class NoticeBoardController {

	NoticeBoardServiceImpl noticeService = new NoticeBoardService();
	List<NoticeBoardDto> list = new ArrayList<NoticeBoardDto>();
	NoticeBoardDto noticedto = new NoticeBoardDto();
	
	public List<NoticeBoardDto> getNoticeBoard(){
		list = noticeService.getNoticeBoard();
		return list;
	}
	
	public void NoticeDetail(int seq) {
		
		noticedto.setSeq(seq);
		noticedto = noticeService.noticeBoardDetail(noticedto);
		new NoticeDetailView(noticedto);
	}
	
}
