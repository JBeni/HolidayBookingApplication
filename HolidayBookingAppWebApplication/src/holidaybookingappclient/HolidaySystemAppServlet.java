package holidaybookingappclient;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import ejbholidaybookingapp.BookingRequestAppBeanRemote;
import ejbholidaybookingapp.CheckConstraintsAppBeanRemote;
import ejbholidaybookingapp.EmployeeAppBeanRemote;
import ejbholidaybookingapp.HolidaySystemAppBeanRemote;
import entityclasses.EmployeeDTO;

@WebServlet("/HolidaySystemAppServlet")
public class HolidaySystemAppServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private HolidaySystemAppBeanRemote holidaySystemAppBean;
	@EJB
	private EmployeeAppBeanRemote employeeAppBean;
	@EJB
	private CheckConstraintsAppBeanRemote checkConstraintsAppBean;
	@EJB
	private BookingRequestAppBeanRemote bookingRequestAppBeanRemote;

	private static final Logger logger = Logger.getLogger(HolidaySystemAppServlet.class);

	public HolidaySystemAppServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PrintWriter out = response.getWriter();

			int roleId = holidaySystemAppBean.login(request.getParameter("email"), request.getParameter("password"));
			EmployeeDTO user = employeeAppBean.getEmployeeByEmail(request.getParameter("email"));

			// 1 for 'Head' and 2 for 'Deputy Head'
			if (roleId == 1 || roleId == 2) {
				HttpSession session = request.getSession(false);
				session.setAttribute("username", "admin");
				response.sendRedirect("EmployeesServlet");
			} else if (roleId == 1 && roleId == 2 && roleId != 0) {
				HttpSession session = request.getSession(false);
				session.setAttribute("username", "standard-user");
				session.setAttribute("userId", user.getId());
				response.sendRedirect("BookingRequestServlet");

				//request.getRequestDispatcher("/menu.jsp").forward(request, response);
			} else if (roleId == 0) {
				logger.warn("User does not exist in the databasase. Email written: " + request.getParameter("email"));
				request.setAttribute("errorLoginMessage", "Invalid username, password or employee role! Please try again.");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}

			out.close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}