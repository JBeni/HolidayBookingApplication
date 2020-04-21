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

import ejbholidaybookingapp.EmployeeAppBeanRemote;
import entityclasses.EmployeeDTO;

@WebServlet("/DeleteEmployeeServlet")
public class DeleteEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
    private EmployeeAppBeanRemote employeeAppBean;

	private static final Logger logger = Logger.getLogger(DeleteEmployeeServlet.class);

    public DeleteEmployeeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			String isUserValid = (String) session.getAttribute("username");
			if (isUserValid == null) {
				response.sendRedirect("HolidaySystemAppServlet");
			} else if (isUserValid.equals("standard-user")) {
				response.sendRedirect("BookingRequestServlet");
			}

			int employeeId = Integer.parseInt(request.getParameter("id"));
			EmployeeDTO queryResult = employeeAppBean.getEmployeeById(employeeId);
			if (queryResult != null) {
				employeeAppBean.deleteEmployee(queryResult);
				response.sendRedirect("EmployeesServlet");
			} else {
				// error
			}
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
