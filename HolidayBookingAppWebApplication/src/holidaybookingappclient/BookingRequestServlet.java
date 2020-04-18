package holidaybookingappclient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ejbholidaybookingapp.BookingAppBeanRemote;
import ejbholidaybookingapp.BookingRequestAppBeanRemote;
import ejbholidaybookingutilsapp.HolidayUtilsClass;
import entityclasses.EmployeeDTO;
import entityclasses.HolidayBookingDTO;
import entityclasses.HolidayRequestDTO;
import entityclasses.PeakTimeDTO;
import entityclasses.StatusDTO;

@WebServlet("/BookingRequestServlet")
public class BookingRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private BookingRequestAppBeanRemote bookingRequestAppBeanRemote;
	@EJB
	private BookingAppBeanRemote bookingAppBeanRemote;

	private static final Logger logger = Logger.getLogger(BookingRequestServlet.class);

    public BookingRequestServlet() {
        super();
    }

	@SuppressWarnings("unused")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);

			String lastNameFilter = request.getParameter("lastNameFilter");
			String firstNameFilter = request.getParameter("firstNameFilter");
			String emailFilter = request.getParameter("emailFilter");
			String roleFilter = request.getParameter("roleEmployeeFilter");
			String departmentFilter = request.getParameter("departmentEmployeeFilter");

			List<HolidayRequestDTO> holidayRequests = new ArrayList<>();
			if (lastNameFilter == null && firstNameFilter == null && emailFilter == null && roleFilter == null && departmentFilter == null) {
				holidayRequests = bookingRequestAppBeanRemote.getAllHolidayRequests();
			} else {
				holidayRequests = bookingRequestAppBeanRemote.filterHolidayReuqestByEmployee(lastNameFilter, firstNameFilter, emailFilter, roleFilter, departmentFilter);
			}

			List<EmployeeDTO> employeesAtWork = new ArrayList<>();
			List<EmployeeDTO> employeesInHoliday = new ArrayList<>();

			String datepickerFilter = request.getParameter("datepicker-filter-employees");
			if (datepickerFilter != null) {
				java.sql.Date dayFilter = HolidayUtilsClass.formatDateFromString(datepickerFilter);
				employeesAtWork = bookingRequestAppBeanRemote.showEmployeesAtWork(dayFilter);
				employeesInHoliday = bookingRequestAppBeanRemote.showEmployeesInHoliday(dayFilter);
			}
			request.setAttribute("employeesWorking", employeesAtWork);
			request.setAttribute("employeesHoliday", employeesInHoliday);

			List<HolidayBookingDTO> holidayBookings = bookingAppBeanRemote.getAllHolidayBookings();
			request.setAttribute("holidayBookings", holidayBookings);
			request.setAttribute("holidayRequests", holidayRequests);

			request.getRequestDispatcher("/holidays.jsp").forward(request, response);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);

			java.sql.Date startDate = HolidayUtilsClass.formatDateFromString(request.getParameter("startDate"));
			java.sql.Date endDate = HolidayUtilsClass.formatDateFromString(request.getParameter("endDate"));
			int holidayDuration = (int) HolidayUtilsClass.countWeekDaysMath(startDate, endDate);

			int userId = Integer.parseInt(session.getAttribute("userId").toString());
			PeakTimeDTO peakTime = bookingRequestAppBeanRemote.getTheNotPeakTime();
			StatusDTO status = bookingRequestAppBeanRemote.getPendingStatus();

			HolidayRequestDTO newHolidayRequest = new HolidayRequestDTO(0, startDate, endDate, holidayDuration, userId, peakTime.getIdPeakTime(), status.getIdStatus(), null);
			bookingRequestAppBeanRemote.addHolidayRequest(newHolidayRequest);
			response.sendRedirect("BookingRequestServlet");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

}