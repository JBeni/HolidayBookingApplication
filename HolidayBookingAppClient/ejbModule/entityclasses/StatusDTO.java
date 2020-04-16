package entityclasses;

import java.io.Serializable;

public class StatusDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int idStatus;
	private String statusName;

	public StatusDTO(int id, String statusName) {
		this.idStatus = id;
		this.statusName = statusName;
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
