package dto;

/*
	CREATE TABLE BOOKINGBOARD (
   BOOKDATE VARCHAR2(50) NOT NULL,
   BOOKTIME VARCHAR2(50) NOT NULL,
   BOOKPLACE VARCHAR2(50) NOT NULL,
   NAME VARCHAR2(50) NOT NULL
);

 */

public class calendarDto {

	private String bookdate;
	private String booktime;
	private String bookplace;
	private String name;
	
	public calendarDto() {
		// TODO Auto-generated constructor stub
	}

	public calendarDto(String bookdate) {
		this.bookdate = bookdate;
	}
	
	public calendarDto(String bookdate, String booktime, String bookplace, String name) {
		super();
		this.bookdate = bookdate;
		this.booktime = booktime;
		this.bookplace = bookplace;
		this.name = name;
	}

	public String getBookdate() {
		return bookdate;
	}

	public void setBookdate(String bookdate) {
		this.bookdate = bookdate;
	}

	public String getBooktime() {
		return booktime;
	}

	public void setBooktime(String booktime) {
		this.booktime = booktime;
	}

	public String getBookplace() {
		return bookplace;
	}

	public void setBookplace(String bookplace) {
		this.bookplace = bookplace;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "calendarDto [bookdate=" + bookdate + ", booktime=" + booktime + ", bookplace=" + bookplace + ", name="
				+ name + "]";
	}
	
	
}
