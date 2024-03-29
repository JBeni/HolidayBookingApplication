package model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="T_Holiday_Remaining")
@NamedQuery(name="THolidayRemaining.findAll", query="SELECT t FROM THolidayRemaining t")
public class THolidayRemaining implements Serializable {
	private static final long serialVersionUID = 1L;

	public THolidayRemaining(int id, int holRemaining, Date oneYearDate, Date fiveYearDate, TEmployee employee) {
		this.idHolRemaining = id;
		this.holidayDaysRemaining = holRemaining;
		this.oneYearDateCheck = oneYearDate;
		this.fiveYearDateCheck = fiveYearDate;
		this.employee = employee;
	}

	public THolidayRemaining() {
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_EMP", nullable = false)
	private TEmployee employee;

	public TEmployee getEmployee() {
		return employee;
	}

	public void setEmployee(TEmployee employee) {
		this.employee = employee;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_HOLIDAY_REMAINING")
	private int idHolRemaining;

	@Column(name="HOLIDAY_REMAINING")
	private int holidayDaysRemaining;

	@Column(name="ONE_YEAR_DATE_CHECK")
	private Date oneYearDateCheck;

	@Column(name="FIVE_YEAR_DATE_CHECK")
	private Date fiveYearDateCheck;

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
