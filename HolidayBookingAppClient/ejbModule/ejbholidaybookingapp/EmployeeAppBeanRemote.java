package ejbholidaybookingapp;

import java.util.List;
import javax.ejb.Remote;
import entityclasses.EmployeeDTO;

@Remote
public interface EmployeeAppBeanRemote {
	List<EmployeeDTO> getEmployeesByDepartmentId(int id);
}
