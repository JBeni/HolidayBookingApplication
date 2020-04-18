package ejbholidaybookingapp;

import java.util.List;

import javax.ejb.Remote;
import entityclasses.EmployeeRoleDTO;

@Remote
public interface EmployeeRoleAppBeanRemote {
	EmployeeRoleDTO getRoleByUpperRoleName(String upperName);
	List<EmployeeRoleDTO> getRoles();
}
