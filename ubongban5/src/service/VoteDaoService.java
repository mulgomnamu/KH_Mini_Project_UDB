package service;

import java.util.List;

import dao.VoteDao;
import dao.VoteDaoImpl;
import dto.VoteDto;

public class VoteDaoService implements VoteDaoServiceImpl {

	VoteDaoImpl dao = new VoteDao();
	
	@Override
	public List<VoteDto> getVoteList() {		
		return dao.getVoteList();
	}

	@Override
	public List<VoteDto> getFindList(String fStr) {		
		return dao.getFindList(fStr);		
	}

	@Override
	public boolean voteMemberListFind(String id, int seq) {		
		return dao.voteMemberListFind(id, seq);		
	}
	
	@Override
	public VoteDto getAgreeDisargeeAbstain(int seq) {
		return dao.getAgreeDisargeeAbstain(seq);
	}

	@Override
	public void voteChoice(int seq, int voteResult) {
		dao.voteChoice(seq, voteResult);		
	}

	@Override
	public boolean addVote(VoteDto dto) {
		// TODO Auto-generated method stub
		return dao.addVote(dto);		
	}

	@Override
	public boolean VoteDelete(List<VoteDto> seqdto) {		
		return dao.VoteDelete(seqdto);		
	}

	@Override
	public boolean voteMemberListCheckUpdate(int seq, String id) {		
		return dao.voteMemberListCheckUpdate(seq, id);		
	}

	@Override
	public List<VoteDto> getVoteEndDate() {
		// TODO Auto-generated method stub
		return dao.getVoteEndDate();
	}
	
	public boolean VoteResultInsert(List<VoteDto> seqdto) {
		return dao.VoteResultInsert(seqdto);
	}
	
	public List<VoteDto> getVoteResult(){
		return dao.getVoteResult();
	}

}
