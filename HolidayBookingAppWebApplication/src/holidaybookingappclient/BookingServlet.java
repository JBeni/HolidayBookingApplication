package holidaybookingappclient;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ejbholidaybookingapp.BookingAppBeanRemote;
import ejbholidaybookingapp.BookingRequestAppBeanRemote;
import entityclasses.HolidayBookingDTO;
import entityclasses.HolidayRequestDTO;

@WebServlet("/BookingServlet")
public class BookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private BookingAppBeanRemote bookingAppBeanRemote;
	@EJB
	private BookingRequestAppBeanRemote bookingRequestAppBeanRemote;

	private static final Logger logger = Logger.getLogger(BookingServlet.class);

    public BookingServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String method = request.getParameter("method");
			if (method.equals("POST")) {
				doPost(request, response);
			} else {
				List<HolidayBookingDTO> holidayBookings = bookingAppBeanRemote.getAllHolidayBookings();
				request.setAttribute("holidayBookings", holidayBookings);
				request.getRequestDispatcher("/holidays.jsp").forward(request, response);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int holidayRequestId = Integer.parseInt(request.getParameter("id"));
			String statusRequest = request.getParameter("status");
	
			HolidayRequestDTO holidayRequest = bookingRequestAppBeanRemote.getHolidayRequestById(holidayRequestId);
			bookingAppBeanRemote.changeHolidayRequestStatus(holidayRequest, statusRequest);
			response.sendRedirect("BookingRequestServlet");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

}
