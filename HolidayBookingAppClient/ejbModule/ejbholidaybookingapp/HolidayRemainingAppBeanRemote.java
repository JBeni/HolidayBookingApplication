package ejbholidaybookingapp;

import java.util.List;
import javax.ejb.Remote;
import entityclasses.HolidayRemainingDTO;

@Remote
public interface HolidayRemainingAppBeanRemote {
	List<HolidayRemainingDTO> getAllHolidayRemaining();
	HolidayRemainingDTO getHolidayRemainingById(int id);
	HolidayRemainingDTO getHolidayRemainingByUserId(int userId);
	void addHolRemainingRecord(HolidayRemainingDTO holRemaining);
	void updateHolRemainingRecord(HolidayRemainingDTO holRemaining);
}
