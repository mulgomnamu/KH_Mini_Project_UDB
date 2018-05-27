package dao;

import dto.MemberDto;

public interface MemberDaoImpl {
	
	public boolean addMember(MemberDto dto);	
	
	public boolean idCheck(String id);
	public boolean nameCheck(String name);
	
	public MemberDto login(MemberDto dto);
	
	public MemberDto getName(MemberDto memdto);
}


