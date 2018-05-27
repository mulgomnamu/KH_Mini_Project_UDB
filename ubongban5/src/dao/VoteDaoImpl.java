package dao;

import java.util.List;


import dto.VoteDto;

public interface VoteDaoImpl {
	
	public List<VoteDto> getVoteList();	
	public List<VoteDto> getFindList(String fStr);
	public boolean addVote(VoteDto dto);	
	public VoteDto getAgreeDisargeeAbstain(int seq);
	public boolean voteMemberListFind(String id, int seq);	
	public boolean voteMemberListCheckUpdate(int seq, String id);
	public void voteChoice(int seq, int voteResult);	
	public List<VoteDto> getVoteEndDate();
	public boolean VoteDelete(List<VoteDto> seqdto);
//	public VoteDto getVoteEndDate(int seq);
	public boolean VoteResultInsert(List<VoteDto> seqdto);
	public List<VoteDto> getVoteResult();
	
}
