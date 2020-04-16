package model;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.*;


@Entity
@Table(name="T_HOLIDAY_REQUEST")
@NamedQuery(name="THolidayRequest.findAll", query="SELECT t FROM THolidayRequest t")
public class THolidayRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	public THolidayRequest(int id, Date beginDate, Date endDate, int duration, TEmployee employee,
			TPeakTime peakTime, TStatus status) {
		this.idRequest = id;
		this.requestBeginDate = beginDate;
		this.requestEndDate = endDate;
		this.holidayDuration = duration;
		this.employee = employee;
		this.peakTime = peakTime;
		this.status = status;
	}

	public THolidayRequest() {
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
	@JoinColumn(name = "ID_PEAK_TIME", nullable = false)
	private TPeakTime peakTime;

	public TPeakTime getPeakTime() {
		return peakTime;
	}

	public void setPeakTime(TPeakTime peakTime) {
		this.peakTime = peakTime;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_STATUS", nullable = false)
	private TStatus status;

	public TStatus getStatus() {
		return status;
	}

	public void setStatus(TStatus status) {
		this.status = status;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_REQ")
	private int idRequest;

	@Column(name="BEGIN_DATE")
	private Date requestBeginDate;

	@Column(name="END_DATE")
	private Date requestEndDate;

	@Column(name="DURATION")
	private int holidayDuration;

	public int getIdRequest() {
		return idRequest;
	}

	public void setIdRequest(int idRequest) {
		this.idRequest = idRequest;
	}

	public Date getRequestBeginDate() {
		return requestBeginDate;
	}

	public void setRequestBeginDate(Date requestBeginDate) {
		this.requestBeginDate = requestBeginDate;
	}

	public Date getRequestEndDate() {
		return requestEndDate;
	}

	public void setRequestEndDate(Date requestEndDate) {
		this.requestEndDate = requestEndDate;
	}

	public int getHolidayDuration() {
		return holidayDuration;
	}

	public void setHolidayDuration(int holidayDuration) {
		this.holidayDuration = holidayDuration;
	}

}
