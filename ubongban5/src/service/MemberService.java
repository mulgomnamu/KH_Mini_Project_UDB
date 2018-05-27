package service;

import dao.MemberDao;
import dao.MemberDaoImpl;
import dto.MemberDto;

public class MemberService implements MemberServiceImpl {
	
	
	MemberDaoImpl dao = new MemberDao();

	@Override
	public boolean addMember(MemberDto dto) {		
		return dao.addMember(dto);		
	}

	@Override
	public boolean idCheck(String id) {		
		return dao.idCheck(id);		
	}
	
	@Override
	public boolean nameCheck(String name) {		
		return dao.nameCheck(name);		
	}
	
	@Override
	public MemberDto login(MemberDto dto) {		
		return dao.login(dto);		
	}
	
	
	public MemberDto getName(MemberDto memdto) {
		return dao.getName(memdto);
	}
	
	
}
