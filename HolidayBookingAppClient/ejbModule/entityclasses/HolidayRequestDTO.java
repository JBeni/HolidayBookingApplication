package entityclasses;

import java.io.Serializable;
import java.sql.Date;

public class HolidayRequestDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int idRequest;
	private Date requestBeginDate;
	private Date requestEndDate;
	private int holidayDuration;

	private int idEmp;
	private int idPeakTime;
	private int idStatus;
	private String statusName;

	public HolidayRequestDTO(int id, Date beginDate, Date endDate, int duration, int idEmployee,
			int idPeakTime, int idStatus, String statusName) {
		this.idRequest = id;
		this.requestBeginDate = beginDate;
		this.requestEndDate = endDate;
		this.holidayDuration = duration;
		this.idEmp = idEmployee;
		this.idPeakTime = idPeakTime;
		this.idStatus = idStatus;
		this.statusName = statusName;
	}

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


	public int getIdEmp() {
		return idEmp;
	}

	public void setIdEmp(int idEmployee) {
		this.idEmp = idEmployee;
	}

	public int getIdPeakTime() {
		return idPeakTime;
	}

	public void setIdPeakTime(int idPeakTime) {
		this.idPeakTime = idPeakTime;
	}

	public int getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(int idStatus) {
		this.idStatus = idStatus;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

}
