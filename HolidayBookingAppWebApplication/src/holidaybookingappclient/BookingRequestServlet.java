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
import entityclasses.HolidayBookingDTO;
import entityclasses.HolidayRequestDTO;

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			String isUserValid = (String) session.getAttribute("username");
			int userId = (int) session.getAttribute("userId");
			if (isUserValid == null) {
				response.sendRedirect("HolidaySystemAppServlet");
			}

			String lastNameFilter = request.getParameter("lastNameFilter");
			String firstNameFilter = request.getParameter("firstNameFilter");
			String emailFilter = request.getParameter("emailFilter");
			String roleFilter = request.getParameter("roleEmployeeFilter");
			String departmentFilter = request.getParameter("departmentEmployeeFilter");

			List<HolidayRequestDTO> holidayRequests = new ArrayList<>();
			if (lastNameFilter == null && firstNameFilter == null && emailFilter == null && roleFilter == null && departmentFilter == null) {
				if (isUserValid.equals("admin")) {
					holidayRequests = bookingRequestAppBeanRemote.getAllHolidayRequests();
				} else if (isUserValid.equals("standard-user")) {
					holidayRequests = bookingRequestAppBeanRemote.getHolidayRequestByUserId(userId);
				}
			} else {
				holidayRequests = bookingRequestAppBeanRemote.filterHolidayReuqestByEmployee(lastNameFilter, firstNameFilter, emailFilter, roleFilter, departmentFilter);
			}

			if (isUserValid.equals("admin")) {
				List<HolidayBookingDTO> holidayBookings = bookingAppBeanRemote.getAllHolidayBookings();
				request.setAttribute("holidayBookings", holidayBookings);
			} else if (isUserValid.equals("standard-user")) {
				List<HolidayBookingDTO> holidayBookings = bookingAppBeanRemote.getHolidayBookingsByUserId(userId);
				request.setAttribute("holidayBookings", holidayBookings);
			}

			request.setAttribute("holidayRequests", holidayRequests);
			request.getRequestDispatcher("/holidays.jsp").forward(request, response);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doGet(request, response);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

}
