package ejbholidaybookingapp;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entityclasses.HolidayBookingDTO;
import entityclasses.HolidayRequestDTO;
import entityclasses.StatusDTO;
import model.TDepartment;
import model.TEmployee;
import model.THolidayBooking;
import model.THolidayRequest;
import model.TStatus;

@Stateless
@LocalBean
public class BookingAppBean implements BookingAppBeanRemote {

	@EJB
	private BookingRequestAppBeanRemote bookingRequestAppBeanRemote;

	@PersistenceContext
	private EntityManager entityManager;

    public BookingAppBean() {
    }

	@Override
	public List<HolidayBookingDTO> getAllHolidayBookings() {
		@SuppressWarnings("unchecked")
		List<THolidayBooking> holidayBookings = entityManager.createNamedQuery("THolidayBooking.findAll").getResultList();
		List<HolidayBookingDTO> allHolidayBookingtDTO = new ArrayList<>();
		for (THolidayBooking e : holidayBookings) {
			allHolidayBookingtDTO.add(new HolidayBookingDTO(
							e.getIdBooking(), e.getBookingBeginDate(), e.getBookingEndDate(),
							e.getBookingDuration(), e.getEmployee().getId(),
							e.getDepartment().getIdDep(), e.getHolidayRequest().getIdRequest()
					));
		}
		return allHolidayBookingtDTO;
	}

	@Override
	public boolean addHolidayBooking(HolidayBookingDTO holidayBooking) {
		TEmployee employee = entityManager.find(TEmployee.class, holidayBooking.getIdEmp());
		TDepartment department = entityManager.find(TDepartment.class, holidayBooking.getIdDep());
		THolidayRequest holRequest = entityManager.find(THolidayRequest.class, holidayBooking.getIdHolidayReq());

		THolidayBooking newHolBooking = new THolidayBooking(
				holidayBooking.getIdBooking(), holidayBooking.getBookingBeginDate(), holidayBooking.getBookingEndDate(),
				holidayBooking.getBookingDuration(), employee, department, holRequest
			);
		entityManager.persist(newHolBooking);
		return true;
	}

	@Override
	public boolean changeHolidayRequestStatus(HolidayRequestDTO holRequestDTO, String statusName) {
		THolidayRequest holRequest = entityManager.find(THolidayRequest.class, holRequestDTO.getIdRequest());

		StatusDTO statusDTO = bookingRequestAppBeanRemote.getStatusByName(statusName);
		TStatus status = entityManager.find(TStatus.class, statusDTO.getIdStatus());

		holRequest.setStatus(status);
		entityManager.merge(holRequest);

		if (statusName.equals("Accepted")) {
			addHolidayBooking(new HolidayBookingDTO(
					0, holRequest.getRequestBeginDate(), holRequest.getRequestEndDate(), holRequest.getHolidayDuration(),
					holRequest.getEmployee().getId(), holRequest.getEmployee().getDepartment().getIdDep(), holRequest.getIdRequest()
				));
		}
		return false;
	}

}
