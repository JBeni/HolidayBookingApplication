package ejbholidaybookingapp;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entityclasses.HolidayRemainingDTO;
import model.TEmployee;
import model.THolidayRemaining;

@Stateless
@LocalBean
public class HolidayRemainingAppBean implements HolidayRemainingAppBeanRemote {

	@PersistenceContext
	EntityManager entityManager;

    public HolidayRemainingAppBean() {
    }

	@Override
	public List<HolidayRemainingDTO> getAllHolidayRemaining() {
		@SuppressWarnings("unchecked")
		List<THolidayRemaining> allHolRemaining = entityManager.createNamedQuery("THolidayRemaining.findAll").getResultList();
		List<HolidayRemainingDTO> allHolRemainingDTO = new ArrayList<>();
		for (THolidayRemaining e : allHolRemaining) {
			allHolRemainingDTO.add(new HolidayRemainingDTO(e.getIdHolRemaining(), e.getHolidayDaysRemaining(), e.getOneYearDateCheck(),
				e.getFiveYearDateCheck(), e.getEmployee().getId()
			));
		}
		return allHolRemainingDTO;
	}

	@Override
	public HolidayRemainingDTO getHolidayRemainingById(int id) {
		THolidayRemaining holRemaining = (THolidayRemaining) entityManager
				.createQuery("SELECT e FROM THolidayRemaining e WHERE e.idHolRemaining = :idReq")
				.setParameter("idReq", id).getResultList().get(0);
		HolidayRemainingDTO newHolRemaining = new HolidayRemainingDTO(holRemaining.getIdHolRemaining(), holRemaining.getHolidayDaysRemaining(),
				holRemaining.getOneYearDateCheck(), holRemaining.getFiveYearDateCheck(), holRemaining.getEmployee().getId()
			);
		return newHolRemaining;
	}

	@Override
	public HolidayRemainingDTO getHolidayRemainingByUserId(int userId) {
		THolidayRemaining holRemaining = (THolidayRemaining) entityManager
				.createQuery("SELECT e FROM THolidayRemaining e WHERE e.employee.id = :idReq")
				.setParameter("idReq", userId).getResultList().get(0);
		HolidayRemainingDTO newHolRemaining = new HolidayRemainingDTO(holRemaining.getIdHolRemaining(), holRemaining.getHolidayDaysRemaining(),
				holRemaining.getOneYearDateCheck(), holRemaining.getFiveYearDateCheck(), holRemaining.getEmployee().getId()
			);
		return newHolRemaining;
	}

	@Override
	public void addHolRemainingRecord(HolidayRemainingDTO holRemaining) {
		try {
			TEmployee employee = entityManager.find(TEmployee.class, holRemaining.getIdEmp());
			THolidayRemaining newHolRemaining = new THolidayRemaining(holRemaining.getIdHolRemaining(), holRemaining.getHolidayDaysRemaining(),
					holRemaining.getOneYearDateCheck(), holRemaining.getFiveYearDateCheck(), employee
				);
			entityManager.persist(newHolRemaining);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateHolRemainingRecord(HolidayRemainingDTO updateholRemaining) {
		try {
			THolidayRemaining holRemaining = entityManager.find(THolidayRemaining.class, updateholRemaining.getIdHolRemaining());

			holRemaining.setHolidayDaysRemaining(updateholRemaining.getHolidayDaysRemaining());
			holRemaining.setOneYearDateCheck(updateholRemaining.getOneYearDateCheck());
			holRemaining.setFiveYearDateCheck(updateholRemaining.getFiveYearDateCheck());

			entityManager.merge(holRemaining);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
