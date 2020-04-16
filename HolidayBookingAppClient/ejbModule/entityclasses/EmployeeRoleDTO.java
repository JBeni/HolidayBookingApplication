package entityclasses;

import java.io.Serializable;

public class EmployeeRoleDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int idEmpRole;
	private String nameEmpRole;
	private boolean canApprove;

	public EmployeeRoleDTO(int id, String empRoleName, boolean canApprove) {
		this.idEmpRole = id;
		this.nameEmpRole = empRoleName;
		this.canApprove = canApprove;
	}

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
