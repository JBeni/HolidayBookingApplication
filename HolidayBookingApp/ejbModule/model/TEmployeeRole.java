package model;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name="T_EMPLOYEE_ROLES")
@NamedQuery(name="TEmployeeRole.findAll", query="SELECT t FROM TEmployeeRole t")
public class TEmployeeRole implements Serializable {
	private static final long serialVersionUID = 1L;

	public TEmployeeRole(int id, String empRoleName, boolean canApprove) {
		this.idEmpRole = id;
		this.nameEmpRole = empRoleName;
		this.canApprove = canApprove;
	}

	public TEmployeeRole() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_EMP_ROLE")
	private int idEmpRole;

	@Column(name="EMP_ROLE_NAME")
	private String nameEmpRole;

	@Column(name="CAN_APPROVE")
	private boolean canApprove;

	public int getIdEmpRole() {
		return idEmpRole;
	}

	public void setIdEmpRole(int idEmpRole) {
		this.idEmpRole = idEmpRole;
	}

	public String getNameEmpRole() {
		return nameEmpRole;
	}

	public void setNameEmpRole(String nameEmpRole) {
		this.nameEmpRole = nameEmpRole;
	}

	public boolean getCanApprove() {
		return canApprove;
	}

	public void setCanApprove(boolean canApprove) {
		this.canApprove = canApprove;
	}

}
