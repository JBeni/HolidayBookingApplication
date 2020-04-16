package model;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name = "T_STATUS")
@NamedQuery(name = "TStatus.findAll", query = "SELECT t FROM TStatus t")
public class TStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	public TStatus(int id, String statusName) {
		this.idStatus = id;
		this.statusName = statusName;
	}

	public TStatus() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_STATUS")
	private int idStatus;

	@Column(name = "STATUS_NAME")
	private String statusName;

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
