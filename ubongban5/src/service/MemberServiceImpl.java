package service;

import dto.MemberDto;

public interface MemberServiceImpl {

	public boolean addMember(MemberDto dto);
	
	public boolean idCheck(String id);
	public boolean nameCheck(String name);

	public MemberDto login(MemberDto memberDto);
	
	public MemberDto getName(MemberDto memdto);

}
