package ejbholidaybookingapp;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.TEmployee;

@Stateless
@LocalBean
public class HolidaySystemAppBean implements HolidaySystemAppBeanRemote {

	@PersistenceContext
	private EntityManager entityManager;

	public HolidaySystemAppBean() {
	}

	@Override
	public int login(String email, String password) throws Exception {
		@SuppressWarnings("unchecked")
		List<TEmployee> existsUser = entityManager
				.createQuery("SELECT e FROM TEmployee e WHERE e.email = :email and e.password = :password")
				.setParameter("email", email).setParameter("password", password).getResultList();

		if (existsUser.isEmpty()) {
			return 0;
		}

		return existsUser.get(0).getEmployeeRole().getIdEmpRole();
	}

}
