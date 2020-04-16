package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


@Entity
@Table(name="T_HOLIDAY_BOOKING")
@NamedQuery(name="THolidayBooking.findAll", query="SELECT t FROM THolidayBooking t")
public class THolidayBooking implements Serializable {
	private static final long serialVersionUID = 1L;

	public THolidayBooking(int id, Date startBookingDate, Date endBookingDate, int bookingDuration, TEmployee employee, TDepartment department, THolidayRequest holidayRequest) {
		this.idBooking = id;
		this.bookingBeginDate = startBookingDate;
		this.bookingEndDate = endBookingDate;
		this.bookingDuration = bookingDuration;
		this.employee = employee;
		this.department = department;
		this.holidayRequest = holidayRequest;
	}

	public THolidayBooking() {
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_EMP", nullable = false)
	private TEmployee employee;

	public TEmployee getEmployee() {
		return employee;
	}

	public void setEmployee(TEmployee employee) {
		this.employee = employee;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_DEP", nullable = false)
	private TDepartment department;

	public TDepartment getDepartment() {
		return department;
	}

	public void setDepartment(TDepartment department) {
		this.department = department;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_REQ", nullable = false)
	private THolidayRequest holidayRequest;

	public THolidayRequest getHolidayRequest() {
		return holidayRequest;
	}

	public void setHolidayRequest(THolidayRequest holidayRequest) {
		this.holidayRequest = holidayRequest;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_BOOKING")
	private int idBooking;

	@Column(name="BOOKING_BEGIN_DATE")
	private Date bookingBeginDate;

	@Column(name="BOOKING_END_DATE")
	private Date bookingEndDate;

	@Column(name="BOOKING_DURATION")
	private int bookingDuration;

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

}
