package ejbholidaybookingapp;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entityclasses.EmployeeRoleDTO;
import model.TEmployeeRole;

@Stateless
@LocalBean
public class EmployeeRoleAppBean implements EmployeeRoleAppBeanRemote {

	@PersistenceContext
	EntityManager entityManager;

    public EmployeeRoleAppBean() {
    }

	@Override
	public EmployeeRoleDTO getRoleByUpperRoleName(String upperName) {
		TEmployeeRole empRole = (TEmployeeRole) entityManager
				.createQuery("SELECT e FROM TEmployeeRole e WHERE e.Dep = :name")
				.setParameter("name", upperName).getResultList().get(0);
		EmployeeRoleDTO empRoleDTO = new EmployeeRoleDTO(
				empRole.getIdEmpRole(), empRole.getNameEmpRole(), empRole.getNameEmpRoleUpper(), empRole.getCanApprove()
			);
		return empRoleDTO;
	}

}
