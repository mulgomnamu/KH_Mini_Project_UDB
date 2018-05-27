package service;

import java.util.List;


import dto.VoteDto;

public interface VoteDaoServiceImpl {

	public List<VoteDto> getVoteList();	
	public boolean addVote(VoteDto dto);
	public void voteChoice(int seq, int voteResult);
	public VoteDto getAgreeDisargeeAbstain(int seq);
	public List<VoteDto> getFindList(String fStr);
	public boolean voteMemberListFind(String id, int seq);
	public boolean VoteDelete(List<VoteDto> seqdto);
	public List<VoteDto> getVoteEndDate();
	public boolean voteMemberListCheckUpdate(int seq, String id);
	public boolean VoteResultInsert(List<VoteDto> seqdto);
	public List<VoteDto> getVoteResult();
}
