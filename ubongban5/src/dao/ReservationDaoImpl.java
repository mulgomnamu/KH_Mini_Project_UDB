package dao;

import java.util.List;

import dto.calendarDto;

public interface ReservationDaoImpl {
	
	public void reservationInsert(calendarDto caldto);
	
	public List<calendarDto> getRevList(calendarDto caldto);
	
}
