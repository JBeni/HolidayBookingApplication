package holidaybookingappclient;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import ejbholidaybookingapp.BookingRequestAppBeanRemote;
import ejbholidaybookingutilsapp.HolidayUtilsClass;
import entityclasses.HolidayRequestDTO;
import entityclasses.PeakTimeDTO;
import entityclasses.StatusDTO;

@WebServlet("/NewHolidayRequest")
public class NewHolidayRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private BookingRequestAppBeanRemote bookingRequestAppBeanRemote;

	private static final Logger logger = Logger.getLogger(BookingRequestServlet.class);

    public NewHolidayRequest() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			String isUserValid = (String) session.getAttribute("username");
			if (isUserValid == null) {
				response.sendRedirect("HolidaySystemAppServlet");
			}
			
			request.getRequestDispatcher("/newholidayrequest.jsp").forward(request, response);
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