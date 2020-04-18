package ejbholidaybookingapp;

import javax.ejb.Remote;

@Remote
public interface HolidaySystemAppBeanRemote {
	int login(String email, String password) throws Exception;
}
