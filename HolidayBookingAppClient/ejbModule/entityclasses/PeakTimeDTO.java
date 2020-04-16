package entityclasses;

import java.io.Serializable;
import java.util.Date;

public class PeakTimeDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int idPeakTime;
	private Date peakTimeBeginDate;
	private Date peakTimeEndDate;
	private boolean isPeakTime;

	public PeakTimeDTO(int id, Date peakTimeBegin, Date peakTimeEnd, boolean isPeakTime) {
		this.idPeakTime = id;
		this.peakTimeBeginDate = peakTimeBegin;
		this.peakTimeEndDate = peakTimeEnd;
		this.isPeakTime = isPeakTime;
	}

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
