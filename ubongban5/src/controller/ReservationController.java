package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import dao.ReservationDao;
import dto.MemberDto;
import dto.calendarDto;
import service.MemberService;
import service.MemberServiceImpl;
import service.ReservationDaoService;
import service.ReservationDaoServiceImpl;
import view.MemoCalendar;

public class ReservationController {
	
	ReservationDaoServiceImpl revService= new ReservationDaoService();
	MemberServiceImpl memberService = new MemberService();
	calendarDto caldto = new calendarDto();
	MemberDto memdto = new MemberDto();
	List<calendarDto> list = new ArrayList<calendarDto>();
	
	//mainView를 들어갈 때 guest 계정을 받게하는 부분
	public void InsertRev(calendarDto caldto) {
		revService.reservationInsert(caldto);
		
	}
	
	public List<calendarDto> getRevList(calendarDto caldto) {
		list = revService.getRevList(caldto);
		
		
		return list;
	}

	
	
}





