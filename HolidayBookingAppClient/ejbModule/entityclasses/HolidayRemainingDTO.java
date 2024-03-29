package entityclasses;

import java.io.Serializable;
import java.sql.Date;

public class HolidayRemainingDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int idHolRemaining;
	private int holidayDaysRemaining;
	private Date oneYearDateCheck;
	private Date fiveYearDateCheck;
	private int idEmp;

	public HolidayRemainingDTO(int id, int holRemaining, Date oneYearDate, Date fiveYearDate, int employeeId) {
		this.idHolRemaining = id;
		this.holidayDaysRemaining = holRemaining;
		this.oneYearDateCheck = oneYearDate;
		this.fiveYearDateCheck = fiveYearDate;
		this.idEmp = employeeId;
	}

	public int getIdHolRemaining() {
		return idHolRemaining;
	}

	public void setIdHolRemaining(int idHolRemaining) {
		this.idHolRemaining = idHolRemaining;
	}

	public int getHolidayDaysRemaining() {
		return holidayDaysRemaining;
	}

	public void setHolidayDaysRemaining(int holidayDaysRemaining) {
		this.holidayDaysRemaining = holidayDaysRemaining;
	}

	public int getIdEmp() {
		return idEmp;
	}

	public void setIdEmp(int idEmp) {
		this.idEmp = idEmp;
	}

	public Date getOneYearDateCheck() {
		return oneYearDateCheck;
	}

	public void setOneYearDateCheck(Date oneYearDateCheck) {
		this.oneYearDateCheck = oneYearDateCheck;
	}

	public Date getFiveYearDateCheck() {
		return fiveYearDateCheck;
	}

	public void setFiveYearDateCheck(Date fiveYearDateCheck) {
		this.fiveYearDateCheck = fiveYearDateCheck;
	}

}
