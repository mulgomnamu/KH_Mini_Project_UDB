package service;

import java.util.List;

import dto.calendarDto;

public interface ReservationDaoServiceImpl {
	public void reservationInsert(calendarDto caldto);
	public List<calendarDto> getRevList(calendarDto caldto);
}
