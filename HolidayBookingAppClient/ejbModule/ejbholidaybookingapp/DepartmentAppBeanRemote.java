package ejbholidaybookingapp;

import java.util.List;
import javax.ejb.Remote;
import entityclasses.DepartmentDTO;

@Remote
public interface DepartmentAppBeanRemote {
	DepartmentDTO getDepartmentSizeById(int id);
	List<DepartmentDTO> getDepartments();
}
