package ejbholidaybookingapp;

import java.sql.Date;
import java.util.List;
import javax.ejb.Remote;

import entityclasses.EmployeeDTO;
import entityclasses.HolidayRequestDTO;
import entityclasses.PeakTimeDTO;
import entityclasses.StatusDTO;

@Remote
public interface BookingRequestAppBeanRemote {
	StatusDTO getPendingStatus();
	StatusDTO getRejectedStatus();
	StatusDTO getApprovedStatus();
	StatusDTO getStatusByName(String statusName);
	PeakTimeDTO getTheNotPeakTime();
	List<HolidayRequestDTO> getAllHolidayRequests();
	List<HolidayRequestDTO> getHolidayRequestByUserId(int userId);
	HolidayRequestDTO getHolidayRequestById(int id);
	boolean addHolidayRequest(HolidayRequestDTO holidayRequest);

	List<HolidayRequestDTO> filterHolidayReuqestByEmployee(String lastName, String firstName, String email, String roleName, String depName);
	List<EmployeeDTO> showEmployeesInHoliday(Date day);
	List<EmployeeDTO> showEmployeesAtWork(Date day);
}
