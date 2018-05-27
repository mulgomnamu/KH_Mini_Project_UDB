package service;

import java.util.List;

import dao.ReservationDao;
import dao.ReservationDaoImpl;
import dto.calendarDto;

public class ReservationDaoService implements ReservationDaoServiceImpl {

	ReservationDaoImpl dao = new ReservationDao();

	@Override
	public void reservationInsert(calendarDto caldto) {
		dao.reservationInsert(caldto);
		
	}

	@Override
	public List<calendarDto> getRevList(calendarDto caldto) {
		// TODO Auto-generated method stub
		return dao.getRevList(caldto);
	}
	
	
}
