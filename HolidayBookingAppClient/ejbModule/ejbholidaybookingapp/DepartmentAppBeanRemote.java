package ejbholidaybookingapp;

import javax.ejb.Remote;

import entityclasses.DepartmentDTO;

@Remote
public interface DepartmentAppBeanRemote {
	DepartmentDTO getDepartmentSizeById(int id);
}
