package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


@Entity
@Table(name = "T_PEAK_TIME_DAYS")
@NamedQuery(name = "TPeakTime.findAll", query = "SELECT t FROM TPeakTime t")
@NamedQuery(name = "TPeakTime.getTheNoPeakTime", query = "SELECT t FROM TPeakTime t where t.isPeakTime = 0")
public class TPeakTime implements Serializable {
	private static final long serialVersionUID = 1L;

	public TPeakTime(int id, Date peakTimeBegin, Date peakTimeEnd, boolean isPeakTime) {
		this.idPeakTime = id;
		this.peakTimeBeginDate = peakTimeBegin;
		this.peakTimeEndDate = peakTimeEnd;
		this.isPeakTime = isPeakTime;
	}

	public TPeakTime() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PEAK_TIME")
	private int idPeakTime;

	@Column(name = "PEAK_TIME_BEGIN_DATE")
	private Date peakTimeBeginDate;

	@Column(name = "PEAK_TIME_END_DATE")
	private Date peakTimeEndDate;
	
	@Column(name = "IS_PEAK_TIME")
	private boolean isPeakTime;

	public int getIdPeakTime() {
		return idPeakTime;
	}

	public void setIdPeakTime(int idPeakTime) {
		this.idPeakTime = idPeakTime;
	}

	public Date getPeakTimeBeginDate() {
		return peakTimeBeginDate;
	}

	public void setPeakTimeBeginDate(Date peakTimeBeginDate) {
		this.peakTimeBeginDate = peakTimeBeginDate;
	}

	public Date getPeakTimeEndDate() {
		return peakTimeEndDate;
	}

	public void setPeakTimeEndDate(Date peakTimeEndDate) {
		this.peakTimeEndDate = peakTimeEndDate;
	}

	public boolean getIsPeakTime() {
		return isPeakTime;
	}

	public void setPeakTime(boolean isPeakTime) {
		this.isPeakTime = isPeakTime;
	}

}
