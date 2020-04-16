package ejbholidaybookingapp;

import java.util.List;
import javax.ejb.Remote;

import entityclasses.HolidayBookingDTO;
import entityclasses.HolidayRequestDTO;

@Remote
public interface BookingAppBeanRemote {
	List<HolidayBookingDTO> getAllHolidayBookings();
	boolean addHolidayBooking(HolidayBookingDTO holidayBooking);
	boolean changeHolidayRequestStatus(HolidayRequestDTO holRequest, String status);
}
