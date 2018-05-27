package service;

import java.util.List;

import dao.BasicBBSDao;
import dao.BasicBBSDaoImpl;
import dto.BasicBBSDto;

public class BasicBBSService implements BasicBBSServiceImpl {
	
	BasicBBSDaoImpl BBBSDao = new BasicBBSDao();

	@Override
	public List<BasicBBSDto> getBasicBBSList(int curPage, int numPerPage) {
		return BBBSDao.getBasicBBSList(curPage, numPerPage);
	}

	@Override
	public int getTotalRecord(Object keyword, String select_word, int sel) {
		return BBBSDao.getTotalRecord(keyword, select_word, sel);
	}

	@Override
	public boolean writeBasicBBS(BasicBBSDto dto) {
		return BBBSDao.writeBasicBBS(dto);
	}

	@Override
	public void updateBasicBBSReadcount(int seq, int readCount) {
		BBBSDao.updateBasicBBSReadcount(seq, readCount);
	}

	@Override
	public BasicBBSDto detailBasicBBS(int seq) {
		return BBBSDao.detailBasicBBS(seq);
	}

	@Override
	public boolean updateBasicBBS(String title, String content, int seq) {
		return BBBSDao.updateBasicBBS(title, content, seq);
	}

	@Override
	public boolean deleteBasicBBS(int seq) {
		return BBBSDao.deleteBasicBBS(seq);
	}

	@Override
	public boolean writeBasicBBSReplier(BasicBBSDto dto, int family, int seq, int depth, int indent) {
		return BBBSDao.writeBasicBBSReplier(dto, family, seq, depth, indent);
	}

	@Override
	public List<BasicBBSDto> selectBasicBBSList(Object keyword, String select_word, int curPage, int numPerPage) {
		return BBBSDao.selectBasicBBSList(keyword, select_word, curPage, numPerPage);
	}
	
	public List<BasicBBSDto> getNewestList() {
		// TODO Auto-generated method stub
		return BBBSDao.getNewestList();
	}
	
	

}
