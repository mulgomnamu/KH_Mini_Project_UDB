package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dto.MarketDto;
import dto.MemberDto;
import service.MarketService;
import service.MarketServiceImpl;
import singleton.Singleton;
import view.MarketBbsView;
import view.MarketDetailView;
import view.MarketUpdate;
import view.MarketWriteView;

public class MarketBbsController {
	
	MarketServiceImpl marketService = new MarketService();
	
	private String loginName;
	private MemberDto loginDto;
	
	public void getBbsList(MemberDto loginDto) {
		this.loginDto = loginDto;
		this.loginName = loginDto.getName();
		List<MarketDto> list = marketService.getBbsList();
		new MarketBbsView(list, loginDto);
	}
	
	public void getBbsFindList(String fStr){
		List<MarketDto> list = marketService.getFindList(fStr.trim());
		if(list.size() > 0){
			//new MarketBbsView(list, loginName);		
		}else{
			JOptionPane.showMessageDialog(null, "데이터를 찾을 수 없습니다");
			//getBbsList();
		}
	}
	
	public void bbsDetail(int seq) {
		marketService.readCount(seq);	
		MarketDto dto = marketService.getBBS(seq);	
		Singleton sc = Singleton.getInstance();
		String loginId = sc.memCtrl.getLoginName();
		new MarketDetailView(dto, loginDto);
	}
	
	public void bbsWrite(MemberDto dto) {
		new MarketWriteView(dto);
	}
	
	public void bbsWriteAf(MarketDto dto){
		boolean b = marketService.writeBbs(dto);
		if(b){			
			JOptionPane.showMessageDialog(null, "성공적으로 추가되었습니다");
			getBbsList(loginDto);
		}else{
			JOptionPane.showMessageDialog(null, "추가되지 못했습니다");
			bbsWrite(loginDto);
		}
	}
	
	public void bbsDelete(int seq) {
		boolean b =  marketService.bbsDelete(seq);
		if(b){
			JOptionPane.showMessageDialog(null, "성공적으로 삭제되었습니다");
			getBbsList(loginDto);
		}else{
			JOptionPane.showMessageDialog(null, "삭제하지 못했습니다");
			bbsDetail(seq);
		}
	}
	
	public void bbsUpdate(int seq) {
		MarketDto markDto = marketService.getBBS(seq);
		new MarketUpdate(markDto, loginDto);
	}
	
	public void bbsUpdateAf(MarketDto dto) {
		boolean b = marketService.bbsUpdate(dto);
		if(b) {
			JOptionPane.showMessageDialog(null, "성공적으로 수정되었습니다");
			getBbsList(loginDto);
		}else {
			JOptionPane.showMessageDialog(null, "수정되지 못했습니다");
			//new bbsUpdateView(dto);
		}
	}

	public void bbsSelect(String title, String content) {
		List<MarketDto> getlist = new ArrayList<MarketDto>();
		getlist = marketService.selectBbsList(title, content);
		for (int i = 0; i < getlist.size(); i++) {
			System.out.println(getlist.get(i).getCategory());
		}
		new MarketBbsView(getlist, loginDto);
	}

	public void MarketDtolist(MemberDto memdto) {
		// TODO Auto-generated method stub
		
	}
}




