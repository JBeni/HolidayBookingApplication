package entityclasses;

import java.io.Serializable;
import java.util.Date;

public class HolidayBookingDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int idBooking;
	private Date bookingBeginDate;
	private Date bookingEndDate;
	private int bookingDuration;

	private int idEmp;
	private int idDep;
	private int idHolidayReq;

	public HolidayBookingDTO(int id, Date startBookingDate, Date endBookingDate, int bookingDuration, int idEmployee, int idDepartment, int idHolidayRequest) {
		this.idBooking = id;
		this.bookingBeginDate = startBookingDate;
		this.bookingEndDate = endBookingDate;
		this.bookingDuration = bookingDuration;

		this.idEmp = idEmployee;
		this.idDep = idDepartment;
		this.idHolidayReq = idHolidayRequest;
	}

	public int getIdBooking() {
		return idBooking;
	}

	public void setIdBooking(int idBooking) {
		this.idBooking = idBooking;
	}

	public Date getBookingBeginDate() {
		return bookingBeginDate;
	}

	public void setBookingBeginDate(Date bookingBeginDate) {
		this.bookingBeginDate = bookingBeginDate;
	}

	public Date getBookingEndDate() {
		return bookingEndDate;
	}

	public void setBookingEndDate(Date bookingEndDate) {
		this.bookingEndDate = bookingEndDate;
	}

	public int getBookingDuration() {
		return bookingDuration;
	}

	public void setBookingDuration(int bookingDuration) {
		this.bookingDuration = bookingDuration;
	}

	public int getIdEmp() {
		return idEmp;
	}

	public void setIdEmp(int idEmp) {
		this.idEmp = idEmp;
	}

	public int getIdDep() {
		return idDep;
	}

	public void setIdDep(int idDep) {
		this.idDep = idDep;
	}

	public int getIdHolidayReq() {
		return idHolidayReq;
	}

	public void setIdHolidayReq(int idHolidayReq) {
		this.idHolidayReq = idHolidayReq;
	}

}
