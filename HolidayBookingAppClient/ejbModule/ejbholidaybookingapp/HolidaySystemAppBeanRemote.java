package ejbholidaybookingapp;

import java.util.List;
import javax.ejb.Remote;

import entityclasses.DepartmentDTO;
import entityclasses.EmployeeDTO;
import entityclasses.EmployeeRoleDTO;

@Remote
public interface HolidaySystemAppBeanRemote {
	boolean login(String email, String password) throws Exception;
	EmployeeDTO getEmployeeByEmail(String email);
	EmployeeDTO getEmployeeById(int id);
	List<EmployeeDTO> getAllEmployees();
	List<EmployeeDTO> getAllDeletedEmployees();
	List<DepartmentDTO> getDepartments();
	List<EmployeeRoleDTO> getRoles();
	boolean addNewEmployee(EmployeeDTO newEmp);
	boolean editEmployee(EmployeeDTO updateEmp);
	boolean deleteEmployee(EmployeeDTO deleteEmp);
}
