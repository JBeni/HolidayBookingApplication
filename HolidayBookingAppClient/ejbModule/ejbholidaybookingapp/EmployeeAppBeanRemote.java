package ejbholidaybookingapp;

import java.util.List;
import javax.ejb.Remote;
import entityclasses.EmployeeDTO;

@Remote
public interface EmployeeAppBeanRemote {
	EmployeeDTO getEmployeeByEmail(String email);
	EmployeeDTO getEmployeeById(int id);
	List<EmployeeDTO> getAllEmployees();
	List<EmployeeDTO> getAllDeletedEmployees();
	List<EmployeeDTO> getEmployeesByDepartmentId(int id);
	boolean addEmployee(EmployeeDTO newEmp);
	boolean updateEmployee(EmployeeDTO updateEmp);
	boolean deleteEmployee(EmployeeDTO deleteEmp);
	boolean updatePassWord(EmployeeDTO updateEmpPassword);
	boolean checkUserPassword(int userId, String password);
}
